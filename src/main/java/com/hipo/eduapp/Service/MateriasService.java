package com.hipo.eduapp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hipo.eduapp.Entity.Curso;
import com.hipo.eduapp.Entity.Materias;
import com.hipo.eduapp.Entity.Profesor;
import com.hipo.eduapp.Repository.CursoRepository;
import com.hipo.eduapp.Repository.MateriaRepository;
import com.hipo.eduapp.Repository.ProfesorRepository;

import jakarta.transaction.Transactional;

@Service
public class MateriasService {

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public List<Materias> listarTodas() {
        return materiaRepository.findAllWithCursosAndProfesor();
    }

    
    public void guardar(Materias materia, List<Long> cursoIds) {
        if (materia.getProfesor() != null) {
            Profesor profesor = profesorRepository.findById(materia.getProfesor().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Profesor no encontrado"));
            
            // Si la materia ya está asociada al profesor, no la agregamos de nuevo
            if (!profesor.getMaterias().contains(materia)) {
                profesor.getMaterias().add(materia);
            }
            
            // Asignamos la materia al profesor
            materia.setProfesor(profesor);
            
            // Guardamos la materia (actualiza la referencia en la tabla de materias)
            materiaRepository.save(materia);
            
            // Guardamos los cambios en el profesor (actualiza la relación en la tabla de profesores)
            profesorRepository.save(profesor);
        } else {
            // Si no se tiene un profesor asignado, se guarda la materia sin profesor
            materiaRepository.save(materia);
        }

        // Asignamos los cursos seleccionados
    if (cursoIds != null) {
        List<Curso> cursos = cursoRepository.findAllById(cursoIds);  // Obtener cursos por sus IDs
        for (Curso curso : cursos) {
            if (!curso.getMaterias().contains(materia)) {
                curso.getMaterias().add(materia);  // Asignar materia al curso
            }
        }
        cursoRepository.saveAll(cursos);  // Guardar cambios en cursos
    }
    }

    // Actualizar materia existente
    @Transactional
    public void actualizarMateria(Materias materia) {
        if (materia.getId() != null) {
            // Recuperar la materia existente por su ID
            Materias materiaExistente = materiaRepository.findById(materia.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Materia no encontrada"));
    
            // Actualizar los campos de la materia
            materiaExistente.setNombre(materia.getNombre());
    
            // Si se asigna un nuevo profesor, se actualiza la relación
            if (materia.getProfesor() != null) {
                Profesor profesor = profesorRepository.findById(materia.getProfesor().getId())
                        .orElseThrow(() -> new IllegalArgumentException("Profesor no encontrado"));
    
                // Si la materia ya estaba asignada a otro profesor, eliminamos la relación anterior
                if (materiaExistente.getProfesor() != null && !materiaExistente.getProfesor().equals(profesor)) {
                    materiaExistente.getProfesor().getMaterias().remove(materiaExistente); // Eliminar la materia del antiguo profesor
                }
    
                // Asignamos el nuevo profesor
                materiaExistente.setProfesor(profesor);
                if (!profesor.getMaterias().contains(materiaExistente)) {
                    profesor.getMaterias().add(materiaExistente); // Agregar la materia al nuevo profesor
                }
            }
    
            // Guardar la materia actualizada
            materiaRepository.save(materiaExistente);
        }
}
    

    public void eliminar(Long id) {
        materiaRepository.deleteById(id);
    }


    public Materias buscarPorId(Long id) {
        return materiaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Materia no encontrada"));
    }

}