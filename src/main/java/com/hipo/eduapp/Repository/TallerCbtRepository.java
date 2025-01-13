package com.hipo.eduapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hipo.eduapp.Entity.TallerCbt;

@Repository
public interface TallerCbtRepository extends JpaRepository<TallerCbt, Long> {

}
