package com.example.api_cinetalk.service;

import com.example.api_cinetalk.model.Usuario;
import com.example.api_cinetalk.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    /*public UserDetails loadUserById(String userId) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado" + userId));

        return new User(
                usuario.getEmail(),
                usuario.getSenha(),
                Collections.emptyList()
        );
    }*/

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado: " + userId));

        return new org.springframework.security.core.userdetails.User(
                usuario.getEmail(),
                usuario.getSenha(),
                Collections.emptyList()
        );// Aqui você pode adicionar as roles do usuário
    }
}
