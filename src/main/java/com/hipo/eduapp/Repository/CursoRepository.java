package com.hipo.eduapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hipo.eduapp.Entity.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

}
