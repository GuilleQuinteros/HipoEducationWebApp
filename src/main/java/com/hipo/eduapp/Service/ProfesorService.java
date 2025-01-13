package com.hipo.eduapp.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hipo.eduapp.Entity.Materias;
import com.hipo.eduapp.Entity.Profesor;
import com.hipo.eduapp.Entity.TallerCbt;
import com.hipo.eduapp.Enums.TipoProfesor;
import com.hipo.eduapp.Repository.MateriaRepository;
import com.hipo.eduapp.Repository.ProfesorRepository;
import com.hipo.eduapp.Repository.TallerCbtRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private TallerCbtRepository tallerCbtRepository;

    @Autowired 
    private MateriaRepository materiaRepository;

    @Autowired
    private SessionFactory sessionFactory;
    
    public List<Profesor> listarProfesores() {
        return profesorRepository.findAll();
    }

    
    public Profesor guardarProfesor(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    

    public Profesor buscarPorId(Long id) {
        return profesorRepository.findById(id).orElseThrow(() -> 
        new IllegalArgumentException("El profesor con ID " + id + " no existe."));
    }

    @Transactional
    public void eliminarProfesor(Long id) {
        Profesor profesor = profesorRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Profesor no encontrado"));
        // Limpiar la relación de las materias asociadas al profesor
        for (Materias materia : profesor.getMaterias()) {
        materia.setProfesor(null);  // Eliminar la asignación del profesor
        materiaRepository.save(materia);  // Guardar los cambios en las materias
        }
        // Ahora eliminar el profesor
        profesorRepository.delete(profesor);

    }

    @Transactional // Para que se ejecute en una transacción de base de datos
    public void actualizarProfesor(Profesor profesor) {
        Optional<Profesor> respuesta = profesorRepository.findById(profesor.getId());
        if (respuesta.isPresent()) { // Si el profesor existe en la base de datos se actualiza
            Profesor profesorActual = respuesta.get();
            profesorActual.setNombre(profesor.getNombre());
            profesorActual.setApellido(profesor.getApellido());
            profesorActual.setDni(profesor.getDni());
            profesorActual.setTipo(profesor.getTipo());
            profesorRepository.save(profesorActual);
        } else {
            throw new EntityNotFoundException("El profesor con ID " + profesor.getId() + " no existe.");
        }
    }





    public boolean existePorId(Long id) {
        return profesorRepository.existsById(id);
    }


    public void reasignarMaterias(Long profesorAnteriorId, Long profesorNuevoId) {
        
        
    }

    
    
}   
