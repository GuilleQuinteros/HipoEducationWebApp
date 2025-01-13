package com.hipo.eduapp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hipo.eduapp.Entity.Alumno;
import com.hipo.eduapp.Entity.Curso;
import com.hipo.eduapp.Repository.CursoRepository;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepositoty;

    @Autowired
    private AlumnoService alumnoService;

    public List<Curso> listarTodos(){
        return cursoRepositoty.findAll();
    }

    public Curso guardar(Curso curso){
        return cursoRepositoty.save(curso);
    }

    public Curso buscarPorId(Long id) {
        return cursoRepositoty.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("El curso con ID " + id + " no fue encontrado"));
    }

    public List<Alumno> buscarAlumnosPorCurso(Curso curso) {
        return alumnoService.buscarAlumnosPorCurso(curso);
    }

    public void eliminar (Long id){
        cursoRepositoty.deleteById(id);
    }
}
