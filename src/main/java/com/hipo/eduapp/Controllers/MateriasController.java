package com.hipo.eduapp.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hipo.eduapp.Entity.Curso;
import com.hipo.eduapp.Entity.Materias;
import com.hipo.eduapp.Entity.Profesor;
import com.hipo.eduapp.Repository.ProfesorRepository;
import com.hipo.eduapp.Service.CursoService;
import com.hipo.eduapp.Service.MateriasService;
import com.hipo.eduapp.Service.ProfesorService;

@Controller
@RequestMapping("/materias")
public class MateriasController {
    @Autowired
    private MateriasService materiasService;

    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private CursoService cursoService;

    @GetMapping("/listar")
    public String listarMaterias(Model model){
        model.addAttribute("materias", materiasService.listarTodas());
        return "listar_materias";
    }

    @GetMapping("/nueva")
    public String nuevaMateria (@RequestParam(required = false) Long id,Model model){
        Materias materia = (id != null) ? materiasService.buscarPorId(id) : new Materias();
    List<Profesor> profesores = profesorService.listarProfesores();
    List<Curso> cursos = cursoService.listarTodos();  // Obtener todos los cursos

    model.addAttribute("materia", materia);
    model.addAttribute("profesores", profesores);
    model.addAttribute("cursos", cursos);
    return "formulario_materia";
    }

    @PostMapping("/guardar")
    public String guardarMateria(@ModelAttribute("materia") Materias materia, 
    @RequestParam(required = false) Long profesorId, 
    @RequestParam("cursos") List<Long> cursoIds){
         // Validar si se seleccionó un profesor
         if (profesorId != null) {
            Profesor profesorExistente = profesorService.buscarPorId(profesorId);
            if (profesorExistente != null) {
                materia.setProfesor(profesorExistente);
            }
        } else {
            materia.setProfesor(null); // Permitir guardar sin profesor asignado
        }
        // Guardar o actualizar materia
        if (materia.getId() == null) {
            materiasService.guardar(materia, cursoIds);
        } else {
            materiasService.actualizarMateria(materia);
        }
        
        return "redirect:/materias/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarMateria(@PathVariable Long id, Model model){
        Materias materia = materiasService.buscarPorId(id);
        List<Profesor> profesores = profesorService.listarProfesores();
        
        model.addAttribute("materia", materia);
        model.addAttribute("profesores", profesores);
        return "formulario_materia";
    }

    @PostMapping("/actualizar")
public String actualizarMateria(@ModelAttribute("materia") Materias materia) {
    materiasService.actualizarMateria(materia);
    return "redirect:/materias/listar";
}

    @GetMapping("/eliminar/{id}")
    public String eliminarMateria(@PathVariable Long id){
        materiasService.eliminar(id);
        return "redirect:/materias/listar";
    }


    @GetMapping("/buscarPorCurso")
    public String buscarMateriasPorCurso(Model model) {
        // Listar todos los cursos para la selección
        List<Curso> cursos = cursoService.listarTodos();
        model.addAttribute("cursos", cursos);
        return "buscar_materias_por_curso";
    }

    @PostMapping("/buscarPorCurso")
    public String mostrarMateriasPorCurso(@RequestParam Long cursoId, Model model) {
         // Obtener las materias asociadas al curso seleccionado
         Curso curso = cursoService.buscarPorId(cursoId);
         List<Materias> materias = curso.getMaterias();
         
         // Reenviar los cursos al modelo para que la lista desplegable se siga mostrando
         List<Curso> cursos = cursoService.listarTodos();
         model.addAttribute("cursos", cursos);
         
         model.addAttribute("curso", curso);
         model.addAttribute("materias", materias);
         return "buscar_materias_por_curso";
     }
    }

