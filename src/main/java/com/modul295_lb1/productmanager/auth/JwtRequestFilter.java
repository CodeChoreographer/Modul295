package com.modul295_lb1.productmanager.auth;

import com.modul295_lb1.productmanager.resources.users.UserData;
import com.modul295_lb1.productmanager.resources.users.UserService;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        String email = null;
        String jwt = null;

        // Überprüfen, ob der Header vorhanden ist und das JWT extrahieren
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            email = Jwts.parser()
                    .setSigningKey(tokenService.getSecretKey())
                    .parseClaimsJws(jwt)
                    .getBody()
                    .getSubject(); // E-Mail oder Benutzername aus dem Token extrahieren
        }

        // Wenn die E-Mail nicht null ist und der Benutzer nicht bereits authentifiziert ist
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserData user = userService.findUserByEmail(email); // Benutzer mit E-Mail abrufen
            if (user != null) {
                UserPrincipal userPrincipal = new UserPrincipal(user);
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        // Anfrage an die nächste Filter oder Controller weiterleiten
        chain.doFilter(request, response);
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(tokenService.getSecretKey())
                .parseClaimsJws(token)
                .getBody()
                .getSubject(); // E-Mail oder Benutzername aus dem Token extrahieren
    }
}
