package com.hipo.eduapp.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class DetalleHistorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "historial_id")
    private HistorialAcademico historialAcademico;

    @ManyToOne
    @JoinColumn(name = "materia_id")
    private Materias materia;

    private boolean esMateriaPrevia; // Indica si la materia es previa

    @OneToMany(mappedBy = "detalleHistorial", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notas> notas;

    @OneToMany(mappedBy = "detalleHistorial", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recuperatorio> recuperatorios;

    // Nota final calculada
    private Double notaFinal;

    // Getters y Setters
}