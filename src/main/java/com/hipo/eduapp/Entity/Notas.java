package com.hipo.eduapp.Entity;

import com.hipo.eduapp.Enums.TipoNota;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Notas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double valor;

     @Enumerated(EnumType.STRING)
    private TipoNota tipo; // TRIMESTRAL, CUATRIMESTRAL, FINAL

    @ManyToOne
    @JoinColumn(name = "alumno_id")
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "materia_id")
    private Materias materia;


    @ManyToOne
    @JoinColumn(name = "detalle_historial_id")
    private DetalleHistorial detalleHistorial;

}
