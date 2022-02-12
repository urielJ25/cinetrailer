package com.example.cinetrailer.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data // Generar set y get
@Entity
public class Genero {
    @Id // Declara que id trata de la entidad
    @Column(name = "idgenero") //Indicar en la db
    private Integer id;
    private String titulo;

}
