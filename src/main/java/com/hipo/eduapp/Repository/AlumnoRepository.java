package com.hipo.eduapp.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hipo.eduapp.Entity.Alumno;
import com.hipo.eduapp.Entity.Curso;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    Optional<Alumno> findByDni(String dni);


    @Query("SELECT a FROM Alumno a WHERE a.curso = :curso")
    List<Alumno> findByCurso(@Param("curso") Curso curso);
}
