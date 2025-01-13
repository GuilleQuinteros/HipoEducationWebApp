package com.hipo.eduapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hipo.eduapp.Entity.Notas;

@Repository
public interface NotasRepository extends JpaRepository<Notas, Long> {

}
