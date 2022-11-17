package com.martinacode.sistemaBBVA.service;

import com.martinacode.sistemaBBVA.model.Compra;
import com.martinacode.sistemaBBVA.model.Movimiento;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IMovimientoService {
    String eliminarMovimiento(Long idMovimiento);
    String insertarMovimiento(Movimiento m);
    List<Movimiento> listarMovimientoCliente(Long idCliente);
    List<Movimiento> listarMovimientoClienteTarjeta(Long idCliente,Long idTarjeta);
}
