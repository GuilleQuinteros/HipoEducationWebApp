package com.hipo.eduapp.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hipo.eduapp.Entity.Alumno;
import com.hipo.eduapp.Entity.Curso;
import com.hipo.eduapp.Repository.AlumnoRepository;


import java.util.List;

@Service
public class AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    public List<Alumno> obtenerTodos() {
        return alumnoRepository.findAll();
    }

    public Alumno guardar(Alumno alumno) {
       return alumnoRepository.save(alumno);
    }

    public Alumno buscarPorDni(String dni) {
        return alumnoRepository.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
    }

    public List<Alumno> buscarAlumnosPorCurso(Curso curso) {
        if (curso == null || curso.getId() == null) {
            throw new IllegalArgumentException("Alumno sin curso") {
            };
       }
       return alumnoRepository.findByCurso(curso);
    }
      

    public void eliminar (Long id){
        alumnoRepository.deleteById(id);
    }

    

}