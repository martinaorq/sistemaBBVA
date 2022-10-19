package com.martinacode.sistemaBBVA.repository;

import com.martinacode.sistemaBBVA.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepo extends JpaRepository<Persona,Long> {
}
