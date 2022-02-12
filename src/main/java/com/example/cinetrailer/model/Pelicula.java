package com.example.cinetrailer.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indicar que es incrementable
    @Column(name = "idpelicula")
    private Integer id;

    @NotBlank // No esten en blanco
    private String titulo;

    @NotBlank
    private String sinopsis;

    @NotEmpty
    @NonNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaEstreno;

    @NotBlank
    private String youtubeTrailerId;

    private String rutaPortada;

    @ManyToMany(fetch = FetchType.LAZY) // Realizar carga solo cuando se necesite
    @JoinTable(name = "generopelicula", // declarar nombre de tablas y llaves foraneas
    joinColumns = @JoinColumn(name = "idpelicula"),
    inverseJoinColumns = @JoinColumn(name = "idgenero"))
    private List<Genero> generos;

    @Transient // Se trata de una propiedad tmporal
    private MultipartFile portada;
}
