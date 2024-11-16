package com.example.api_cinetalk.repository;

import com.example.api_cinetalk.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmeRepository extends JpaRepository<Filme, String> {
    List<Filme> findByUsuarioId(String userId);
}
