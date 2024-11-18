package com.example.api_cinetalk.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "filmes")
public class Filme {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;
    private String nome;
    private String genero;
    private double avaliacao;
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
