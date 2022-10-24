package com.martinacode.sistemaBBVA.repository;

import com.martinacode.sistemaBBVA.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepo extends JpaRepository<Persona,Long> {
}
