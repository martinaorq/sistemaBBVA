package com.martinacode.sistemaBBVA.controller;

import com.martinacode.sistemaBBVA.model.Compra;
import com.martinacode.sistemaBBVA.model.Movimiento;
import com.martinacode.sistemaBBVA.model.Persona;
import com.martinacode.sistemaBBVA.repository.MovimientoRepo;
import com.martinacode.sistemaBBVA.service.IMovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovimientoController {

    @Autowired
    private IMovimientoService movimientoService;

    @Autowired
    private MovimientoRepo movimientoRepo;

    @GetMapping("/ver-movimientos")
    public List<Movimiento> verMovimientos(){
        return movimientoRepo.findAll();
    }

    @PostMapping("/insertarMovimiento")
    public String insertarMovimiento(@RequestBody Movimiento m){
        movimientoService.insertarMovimiento(m);
        return "Se insertó movimiento correctamente.";
    }

    @GetMapping("/mis-movimientos")
    public List<Movimiento> listarMovimientos(@RequestParam(name="id")Long idCliente){
        return movimientoService.listarMovimientoCliente(idCliente);
    }

    @GetMapping("/mis-movimientos-tarjeta")
    public List<Movimiento> listarMovimientosTarjeta(@RequestParam(name="idC")Long idCliente,@RequestParam(name="idT") Long idTarjeta) {
        return movimientoService.listarMovimientoClienteTarjeta(idCliente,idTarjeta);
    }

    @DeleteMapping("/borrar-movimiento")
    public String borrarMovimiento(@RequestParam(name="id")Long idMovimiento){
        return movimientoService.eliminarMovimiento(idMovimiento);
    }
}
