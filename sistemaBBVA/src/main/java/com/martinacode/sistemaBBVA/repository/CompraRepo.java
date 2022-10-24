package com.martinacode.sistemaBBVA.repository;

import com.martinacode.sistemaBBVA.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompraRepo extends JpaRepository <Compra, Long> {
}
