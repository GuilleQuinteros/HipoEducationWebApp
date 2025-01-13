package com.hipo.eduapp.Entity;

import java.util.ArrayList;
import java.util.List;

import com.hipo.eduapp.Enums.TipoProfesor;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String dni;
    private String email;

    // Nuevo atributo teléfono
    private String telefono;

    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Materias> materias = new ArrayList<>(); // Relación con Materias (puede ser vacía)

    @Enumerated(EnumType.STRING)
    private TipoProfesor tipo;

    @OneToOne
    @JoinColumn(name = "taller_cbt_id", nullable = true)  // Permitir valores nulos
    private TallerCbt tallerCbt;


}
