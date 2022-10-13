package com.martinacode.sistemaBBVA.service;

import com.martinacode.sistemaBBVA.model.Compra;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICompraService {
    List<Compra> listarComprasCliente(Long idCliente);
    List<Compra> listarComprasClienteTarjeta(Long idCliente,Long idTarjeta);
}
