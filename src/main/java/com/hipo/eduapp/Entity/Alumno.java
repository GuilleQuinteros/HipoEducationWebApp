package com.hipo.eduapp.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
public class Alumno {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String legajo;
    private String dni;
    private String nombre;
    private String apellido;
    private int añoIngreso;
    private String padre;
    private String telefonoPadre;
    private String madre;
    private String telefonoMadre;
    private String tutor;
    private String telefonoTutor;
    private int añoEgreso;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
}
