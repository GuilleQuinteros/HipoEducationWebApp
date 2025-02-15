package com.hipo.eduapp.Entity;

import com.hipo.eduapp.Enums.TipoRecuperatorio;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Recuperatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double nota;

    @Enumerated(EnumType.STRING)
    private TipoRecuperatorio tipo; // DICIEMBRE, FEBRERO

    @ManyToOne
    @JoinColumn(name = "detalle_historial_id")
    private DetalleHistorial detalleHistorial;

    // Getters y Setters
}