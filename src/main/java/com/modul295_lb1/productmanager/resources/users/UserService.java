package com.modul295_lb1.productmanager.resources.users;

import com.modul295_lb1.productmanager.auth.TokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.Date;

/**
 * Service für Benutzeroperationen, einschliesslich Registrierung, Authentifizierung und Datenmanagement.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private static final long EXPIRATION_TIME = 86400000; // 24 Stunden

    /**
     * Konstruktor für UserService.
     *
     * @param userRepository das Repository für Benutzeroperationen
     * @param tokenService   der Service für Token-Management
     */
    @Autowired
    public UserService(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    /**
     * Registriert einen neuen Benutzer.
     *
     * @param userData die Benutzerdaten
     * @return die gespeicherten Benutzerdaten
     * @throws UserAlreadyExistsException wenn der Benutzer bereits existiert
     */
    @Operation(summary = "Registriert einen neuen Benutzer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Benutzer erfolgreich registriert"),
            @ApiResponse(responseCode = "400", description = "Benutzer existiert bereits")
    })
    public UserData registerUser(UserData userData) {
        if (userExists(userData.getUsername())) {
            throw new UserAlreadyExistsException("Benutzer existiert bereits");
        }
        userData.setPassword(passwordEncoder.encode(userData.getPassword()));
        return userRepository.save(userData);
    }

    /**
     * Authentifiziert einen Benutzer und gibt ein JWT-Token zurück.
     *
     * @param email    der Benutzername
     * @param password das Passwort
     * @return das generierte Authentifizierungstoken
     * @throws InvalidCredentialsException wenn die Anmeldeinformationen ungültig sind
     */
    @Operation(summary = "Authentifiziert einen Benutzer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Benutzer erfolgreich authentifiziert"),
            @ApiResponse(responseCode = "401", description = "Ungültige Anmeldeinformationen")
    })
    public String authenticateUser(@Parameter(description = "Benutzername") String email,
                                   @Parameter(description = "Passwort") String password) {
        UserData user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return generateAuthToken(user);
        }
        throw new InvalidCredentialsException("Ungültige Anmeldeinformationen");
    }

    /**
     * Gibt die Benutzerdaten für den angegebenen Benutzernamen zurück.
     *
     * @param email der Benutzername
     * @return die Benutzerdaten
     */
    @Operation(summary = "Gibt die Benutzerdaten zurück")
    public UserData getUserByEmail(@Parameter(description = "Benutzername") String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Gibt die Daten des aktuell authentifizierten Benutzers zurück.
     *
     * @return die Benutzerdaten des aktuellen Benutzers
     * @throws UserNotAuthenticatedException wenn der Benutzer nicht authentifiziert ist
     * @throws UserNotFoundException wenn der Benutzer nicht gefunden wird
     */
    @Operation(summary = "Gibt die Daten des aktuell authentifizierten Benutzers zurück")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Benutzerdaten erfolgreich abgerufen"),
            @ApiResponse(responseCode = "403", description = "Benutzer nicht authentifiziert"),
            @ApiResponse(responseCode = "404", description = "Benutzer nicht gefunden")
    })
    public UserData getCurrentUserData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UserNotAuthenticatedException("Benutzer nicht authentifiziert");
        }
        UserData currentUser = (UserData) authentication.getPrincipal();
        return userRepository.findById(currentUser.getId())
                .orElseThrow(() -> new UserNotFoundException("Benutzer nicht gefunden"));
    }

    /**
     * Aktualisiert die Benutzerdaten des angegebenen Benutzers.
     *
     * @param id       die Benutzer-ID
     * @param userData die neuen Benutzerdaten
     * @return die aktualisierten Benutzerdaten
     * @throws UserNotFoundException wenn der Benutzer nicht gefunden wird
     */
    @Operation(summary = "Aktualisiert die Benutzerdaten")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Benutzerdaten erfolgreich aktualisiert"),
            @ApiResponse(responseCode = "404", description = "Benutzer nicht gefunden")
    })
    public UserData updateUser(@Parameter(description = "Benutzer-ID") Integer id,
                               @Parameter(description = "Neue Benutzerdaten") UserData userData) {
        UserData existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Benutzer nicht gefunden"));

        existingUser.setUsername(userData.getUsername());
        existingUser.setEmail(userData.getEmail());
        existingUser.setActive(userData.getActive());
        existingUser.setIsAdmin(userData.getIsAdmin());
        return userRepository.save(existingUser);
    }

    /**
     * Generiert ein Authentifizierungstoken für den angegebenen Benutzer.
     *
     * @param user der Benutzer
     * @return das generierte Token
     */
    private String generateAuthToken(UserData user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, tokenService.getSecretKey())
                .compact();
    }

    /**
     * Überprüft, ob ein Benutzer mit dem angegebenen Benutzernamen existiert.
     *
     * @param email der Benutzername
     * @return true, wenn der Benutzer existiert, andernfalls false
     */
    public boolean userExists(@Parameter(description = "Benutzername") String email) {
        return userRepository.findByEmail(email) != null;
    }

    public UserData getUserByCredentials(@Parameter(description = "Benutzername oder Email") String emailOrUsername,
                                         @Parameter(description = "Passwort") String password) {
        UserData userData = userRepository.findByEmailOrUsername(emailOrUsername);
        if(userData == null) {
            return null;
        }

        boolean isPasswordMatch = passwordEncoder.matches(password, userData.getPassword());

        if(isPasswordMatch) {
            return userData;
        }
        return null;
    }

    public UserData findUserByEmail(@Parameter(description = "Benutzername") String email) {
        return userRepository.findByEmail(email);
    }

    public static class UserAlreadyExistsException extends RuntimeException {
        public UserAlreadyExistsException(String message) {
            super(message);
        }
    }

    public static class InvalidCredentialsException extends RuntimeException {
        public InvalidCredentialsException(String message) {
            super(message);
        }
    }

    public static class UserNotAuthenticatedException extends RuntimeException {
        public UserNotAuthenticatedException(String message) {
            super(message);
        }
    }

    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }
}
