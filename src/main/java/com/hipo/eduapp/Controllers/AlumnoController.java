package com.hipo.eduapp.Controllers;


import com.hipo.eduapp.Entity.Alumno;
import com.hipo.eduapp.Repository.AlumnoRepository;
import com.hipo.eduapp.Service.AlumnoService;
import com.hipo.eduapp.Service.CursoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;
    @Autowired
    private CursoService cursoService;

    @GetMapping("/lista")
    public String listarAlumnos(Model model) {
        model.addAttribute("alumnos", alumnoService.obtenerTodos());
        return "lista_alumnos.html";
    }

    @GetMapping("/nuevo")
    public String nuevoAlumnoForm(Model model) {
        model.addAttribute("alumno", new Alumno());
        model.addAttribute("curso", cursoService.listarTodos());
        return "crear_alumno.html";
    }

    @PostMapping("/guardar")
public String guardarAlumno(@ModelAttribute Alumno alumno, Model model) {
    alumnoService.guardar(alumno); // Guarda el alumno en la base de datos
    
    model.addAttribute("alumno", new Alumno()); // Envía un nuevo objeto vacío al formulario
    model.addAttribute("mensaje", "Alumno guardado exitosamente");
    model.addAttribute("curso", cursoService.listarTodos());
    return "crear_alumno"; // Retorna la misma plantilla
}


    @GetMapping("/{dni}")
    public String verNotas(@PathVariable String dni, Model model) {
        Alumno alumno = alumnoService.buscarPorDni(dni);
        model.addAttribute("alumno", alumno);
        return "alumnos/notas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminaralumno(@PathVariable Long id){
        alumnoService.eliminar(id);
        return "redirect:/alumnos/lista";
    }
}