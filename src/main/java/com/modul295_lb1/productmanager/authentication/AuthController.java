package com.modul295_lb1.productmanager.authentication;

import com.modul295_lb1.productmanager.model.AuthRequest;
import com.modul295_lb1.productmanager.model.AuthResponse;
import com.modul295_lb1.productmanager.authentication.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> createToken(@RequestBody AuthRequest request) {
        String token = jwtUtil.generateToken(request.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
