package com.hipo.eduapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hipo.eduapp.Entity.Profesor;

@Repository
public interface  ProfesorRepository extends JpaRepository<Profesor, Long> {

}
