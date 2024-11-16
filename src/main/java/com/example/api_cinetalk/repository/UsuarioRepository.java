package com.example.api_cinetalk.repository;

import com.example.api_cinetalk.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findById(String id);
    Optional<Usuario> findByEmail(String email);
}
