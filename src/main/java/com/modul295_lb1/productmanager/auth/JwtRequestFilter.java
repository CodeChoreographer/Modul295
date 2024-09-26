package com.modul295_lb1.productmanager.auth;

import com.modul295_lb1.productmanager.resources.users.UserData;
import com.modul295_lb1.productmanager.resources.users.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
        
    // Schritt 1
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    //private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
            
    @Override
    protected void doFilterInternal(
                                    HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain
    ) throws ServletException, IOException {
        // Schritt 2
        final String authorizationHeader = request.getHeader("Authorization");

        String email = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            email = Jwts.parser().setSigningKey(tokenService.getSecretKey()).parseClaimsJws(jwt).getBody().getSubject();
        }

        // Schritt 3
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserData user = userService.findUserByEmail(email);
            UserPrincipal userPrincipal = new UserPrincipal(user);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
            // Schritt 4
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        chain.doFilter(request, response);
    }
}