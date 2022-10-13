package com.martinacode.sistemaBBVA.controller;

import com.martinacode.sistemaBBVA.model.Compra;
import com.martinacode.sistemaBBVA.service.ICompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompraController {

    @Autowired
    private ICompraService servicioCompra;


    @GetMapping("/mis-compras")
    public List<Compra> listarCompras(@RequestParam(name="id")Long idCliente){
        return servicioCompra.listarComprasCliente(idCliente);
    }

    @GetMapping("/mis-compras-tarjeta")
    public List<Compra> listarComprasTarjeta(@RequestParam(name="idC")Long idCliente,@RequestParam(name="idT") Long idTarjeta){
        return servicioCompra.listarComprasClienteTarjeta(idCliente,idTarjeta);
    }
}
