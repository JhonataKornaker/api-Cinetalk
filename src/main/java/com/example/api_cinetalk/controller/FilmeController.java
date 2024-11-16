package com.example.api_cinetalk.controller;

import com.example.api_cinetalk.dto.FilmeDTO;
import com.example.api_cinetalk.model.Filme;
import com.example.api_cinetalk.model.Usuario;
import com.example.api_cinetalk.repository.UsuarioRepository;
import com.example.api_cinetalk.service.FilmeService;
import com.example.api_cinetalk.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movie")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    @PostMapping
    public ResponseEntity<?> addMovie(HttpServletRequest request, @RequestBody FilmeDTO filme) {
        // Recuperar o token do header Authorization (formato: Bearer <token>)
        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Token inválido ou não fornecido");
        }

        // Remover o "Bearer " do token
        token = token.substring(7);

        // Recuperar o id do usuário do token
        String userId = jwtService.extraUserName(token);

        // Validar o token
        if (!jwtService.validarToken(token, userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Token inválido ou expirado");
        }

        // Associar o filme ao usuário
        //filme.setUsuario(user);

        // Salvar o filme no banco de dados
        Filme salveFilme = filmeService.salvarFilme(filme, userId);

        return ResponseEntity.ok(salveFilme);
    }

    @GetMapping
    public ResponseEntity<?> listarMeusFilmes(HttpServletRequest request) {
        // Recuperar o token do header Authorization (formato: Bearer )
        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Token inválido ou não fornecido");
        }

        // Remover o "Bearer " do token
        token = token.substring(7);

        // Recuperar o id do usuário do token
        String userId = jwtService.extraUserName(token);

        // Validar o token
        if (!jwtService.validarToken(token, userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Token inválido ou expirado");
        }

        // Listar os filmes do usuário
        List<Filme> filmes = filmeService.listarFilmesByUser(userId);
        if (filmes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(filmes);
    }
}

