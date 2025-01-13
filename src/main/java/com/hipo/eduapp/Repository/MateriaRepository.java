package com.hipo.eduapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hipo.eduapp.Entity.Materias;
import com.hipo.eduapp.Entity.Profesor;
import com.hipo.eduapp.Enums.TipoProfesor;

@Repository
public interface MateriaRepository extends  JpaRepository <Materias, Long> {
    
    @Query("SELECT DISTINCT m FROM Materias m " +
           "LEFT JOIN FETCH m.cursos c " +
           "LEFT JOIN FETCH m.profesor p")
    List<Materias> findAllWithCursosAndProfesor();

    // Buscar materias asociadas a un profesor por su ID
    List<Materias> findByProfesorId(Long profesorId);

    // Buscar materias que no tienen ningún profesor asignado
    List<Materias> findByProfesorIsNull();

    // Buscar materias por el tipo del profesor asociado
    List<Materias> findByProfesor_Tipo(TipoProfesor tipo);

}
