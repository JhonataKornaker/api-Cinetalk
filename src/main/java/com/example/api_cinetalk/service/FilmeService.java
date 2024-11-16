package com.example.api_cinetalk.service;

import com.example.api_cinetalk.dto.FilmeDTO;
import com.example.api_cinetalk.model.Filme;
import com.example.api_cinetalk.model.Usuario;
import com.example.api_cinetalk.repository.FilmeRepository;
import com.example.api_cinetalk.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Filme salvarFilme(FilmeDTO filmeDTO, String userId) {
        // Recuperar o usuário do banco de dados usando o userId
        Usuario user = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Filme filme = new Filme();
        filme.setNome(filmeDTO.nome());
        filme.setGenero(filmeDTO.genero());
        filme.setAvaliacao(filmeDTO.avaliacao());
        filme.setComentario(filmeDTO.comentario());

        filme.setUsuario(user);

        return filmeRepository.save(filme);
    }

    public List<Filme> listarFilmesByUser(String userId) {
        return filmeRepository.findByUsuarioId(userId);
    }

    public void deletarFilme(String id) {
        filmeRepository.deleteById(id);
    }
}
