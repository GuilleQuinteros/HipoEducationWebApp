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
import org.springframework.web.bind.annotation.ResponseBody;

import com.hipo.eduapp.Entity.Alumno;
import com.hipo.eduapp.Entity.Curso;
import com.hipo.eduapp.Service.AlumnoService;
import com.hipo.eduapp.Service.CursoService;

@Controller
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping("/listar")
    public String listarCursos(Model model){
        model.addAttribute("curso", cursoService.listarTodos());
        return "gestion_cursos.html";
    }

    @GetMapping("/nuevo")

    public String crearCurso (Model model) {
        model.addAttribute("curso", new Curso());
        return "crear_cursos.html";
    }

    @PostMapping("/guardar")
    public String guardarCurso(@ModelAttribute Curso curso){
        cursoService.guardar(curso);
        return "redirect:/cursos/nuevo";
    }

    @GetMapping("/{id}/alumnos")
    public String listarAlumnosPorCurso(@PathVariable Long id, Model model) {

    Curso curso = cursoService.buscarPorId(id);//recibe el id del curso

    List<Alumno> alumnos = alumnoService.buscarAlumnosPorCurso(curso);//busca los alumnos por curso
        model.addAttribute("curso", curso);//agrega el curso al modelo
        model.addAttribute("alumnos", alumnos);
        return "modal_alumnos.html";
}
    


    @GetMapping("/eliminar/{id}")
    public String eliminarCurso(@PathVariable Long id){
        cursoService.eliminar(id);
        return "redirect:/cursos/listar";
    }
}
