package com.example.api_cinetalk.controller;

import com.example.api_cinetalk.dto.LoginDTO;
import com.example.api_cinetalk.dto.UsuarioDTO;
import com.example.api_cinetalk.model.Usuario;
import com.example.api_cinetalk.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UsuarioDTO user) {
        try {
            Usuario saveUser = authService.registrarUsuario(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao registrar o usuario " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginData) {
        Optional<String> token = authService.authenticarUsuario(loginData.email(), loginData.senha());
        if (token.isPresent()) {
            return ResponseEntity.ok(Map.of("token", token.get()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais Invalidas");
        }
    }
}
