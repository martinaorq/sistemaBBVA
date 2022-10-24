package com.martinacode.sistemaBBVA.repository;

import com.martinacode.sistemaBBVA.model.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarjetaRepo extends JpaRepository <Tarjeta,Long> {
}
