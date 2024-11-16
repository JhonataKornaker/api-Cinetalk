package com.example.api_cinetalk.service;

import com.example.api_cinetalk.dto.UsuarioDTO;
import com.example.api_cinetalk.model.Usuario;
import com.example.api_cinetalk.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Usuario registrarUsuario(UsuarioDTO usuarioDTO) {
        Optional<Usuario> existent = usuarioRepository.findByEmail(usuarioDTO.email());
        if (existent.isPresent()) {
            throw new RuntimeException("Email ja esta em uso");
        }

        Usuario usuario = new Usuario();
        usuario.setId(UUID.randomUUID().toString());  // Gerando UUID manualmente como String
        usuario.setNome(usuarioDTO.nome());
        usuario.setEmail(usuarioDTO.email());
        usuario.setSenha(passwordEncoder.encode(usuarioDTO.senha()));

        //usuario.setFilmes(new ArrayList<>());

        return usuarioRepository.save(usuario);
    }

    public Optional<String> authenticarUsuario(String email, String senha) {
        Optional<Usuario> user = usuarioRepository.findByEmail(email);
        if(user.isPresent() && passwordEncoder.matches(senha, user.get().getSenha())) {
            return Optional.of(jwtService.generateToken(user.get().getId()));
        }
        return Optional.empty();
    }
}
