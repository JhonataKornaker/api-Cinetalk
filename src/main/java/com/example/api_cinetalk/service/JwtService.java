package com.example.api_cinetalk.service;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

import java.util.Date;

@Component
public class JwtService {
    private final String secretKey = "CineTalkSecreto";

    public String generateToken(String userId) {
        System.out.println("Gerando token para o usuário com ID: " + userId);
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 864000000))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String extraUserName (String token) {
        System.out.println("Extraindo nome de usuário do token: " + token);
        String test = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        System.out.println("Nome extraido: " + test);
        return test;
    }

    public boolean validarToken (String token, String userId) {
        System.out.println("Validando token para o usuário: " + userId);
        return extraUserName(token).equals(userId) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        System.out.println("Verificando se o token expirou");
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }
}
