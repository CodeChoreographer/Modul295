package com.modul295_lb1.productmanager.auth;

import com.modul295_lb1.productmanager.resources.users.UserData;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

//import static com.modul295_lb1.productmanager.resources.users.UserService.SECRET_KEY;

@Service
public class TokenService {

    private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(UserData userData) {
        return Jwts.builder()
                .setSubject(userData.getEmail())
                .claim("roles", userData.getRoles())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SECRET_KEY)
                .compact();
    }

    public SecretKey getSecretKey() {
        return SECRET_KEY;
    }
}
