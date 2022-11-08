package com.martinacode.sistemaBBVA.repository;

import com.martinacode.sistemaBBVA.model.Qr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QrRepo extends JpaRepository<Qr,Long> {
}
