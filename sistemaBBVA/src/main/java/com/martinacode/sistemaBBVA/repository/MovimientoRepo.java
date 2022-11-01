package com.martinacode.sistemaBBVA.repository;

import com.martinacode.sistemaBBVA.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoRepo extends JpaRepository<Movimiento,Long > {
}
