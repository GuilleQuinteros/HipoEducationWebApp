package com.hipo.eduapp.Controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hipo.eduapp.Entity.Materias;
import com.hipo.eduapp.Entity.Profesor;
import com.hipo.eduapp.Repository.ProfesorRepository;
import com.hipo.eduapp.Service.MateriasService;
import com.hipo.eduapp.Service.ProfesorService;

import jakarta.persistence.EntityNotFoundException;

@Controller
@RequestMapping("/profesores")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private MateriasService materiasService;

    @Autowired
    private ProfesorRepository profesorRepository;

    @GetMapping("/listar")
    public String listarProfesores (Model model){
        List<Profesor> profesores = profesorService.listarProfesores();
        model.addAttribute("profesores", profesores);
        return "listar_profesores";
    }

    @GetMapping("/nuevo")
    public String nuevoProfesor (Model model){
        model.addAttribute("materias", materiasService.listarTodas());
        model.addAttribute("profesor", new Profesor());
        return "formulario_profesor.html";
    }

    @PostMapping("/guardar")
    public String guardarProfesor(@ModelAttribute Profesor profesor, Model model) {
    try {
        // Log para verificar el ID recibido
        System.out.println("Profesor recibido con ID: " + profesor.getId());

        Profesor profesorGuardado = profesorService.guardarProfesor(profesor);

        model.addAttribute("mensaje", "Profesor guardado con éxito.");
    } catch (DataIntegrityViolationException e) {
        // Manejar violaciones de integridad, por ejemplo, si hay un DNI duplicado
        model.addAttribute("error", "El DNI ingresado ya existe.");
    } catch (Exception e) {
        // Capturar otras excepciones y proporcionar un mensaje más genérico
        model.addAttribute("error", "Error al guardar el profesor: " + e.getMessage());
        e.printStackTrace(); // Para ver la traza de la excepción en los logs
    }

    return "redirect:/profesores/listar";
}

    @GetMapping("/editar/{id}")
public String editarProfesor(@PathVariable("id") Long id, Model model) {
    Profesor profesor = profesorService.buscarPorId(id);
    if (profesor == null) {
        model.addAttribute("error", "El profesor con ID " + id + " no existe.");
        return "redirect:/profesores/listar"; // Redirigir a la lista de profesores
    }

    model.addAttribute("materias", materiasService.listarTodas());
    model.addAttribute("profesor", profesor);
    return "editar_profesor.html";
}


    @PostMapping("/editar/{id}")
    public String actualizarProfesor(@PathVariable Long id, @ModelAttribute Profesor profesor, Model model) {
        try {
            profesorService.actualizarProfesor(profesor);
            model.addAttribute("mensaje", "Profesor actualizado con éxito.");
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("error", "Error al actualizar el profesor: " + e.getMessage());
        }
        return "redirect:/profesores/listar";
    }

    //endpoints de eliminacion de profesores
    @GetMapping("/eliminar/{id}")
    public String eliminarProfesor(@PathVariable Long id){
        profesorService.eliminarProfesor(id);
        return "redirect:/profesores/listar";
    }





    //endpoints de reasignacion de materias
    @GetMapping("/reasignarMaterias")
    public String mostrarFormularioReasignacion(Model model) {
        List<Profesor> profesores = profesorService.listarProfesores();
        model.addAttribute("profesores", profesores);
        return "reasignarMaterias"; // Nombre del archivo Thymeleaf (sin extensión .html)
    }

    @PostMapping("/reasignarMaterias")
    public String reasignarMaterias(
            @RequestParam Long profesorAnteriorId,
            @RequestParam(required = false) Long profesorNuevoId,
            Model model) {
        try {
            profesorService.reasignarMaterias(profesorAnteriorId, profesorNuevoId);
            model.addAttribute("mensaje", "Materias reasignadas con éxito.");
        } catch (Exception e) {
            model.addAttribute("error", "Error al reasignar materias: " + e.getMessage());
        }
        return mostrarFormularioReasignacion(model); // Volvemos a cargar el formulario con el mensaje
    }
}
