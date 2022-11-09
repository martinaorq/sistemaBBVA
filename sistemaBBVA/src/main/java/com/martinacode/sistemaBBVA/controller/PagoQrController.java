package com.martinacode.sistemaBBVA.controller;

import com.martinacode.sistemaBBVA.model.Movimiento;
import com.martinacode.sistemaBBVA.repository.MovimientoRepo;
import com.martinacode.sistemaBBVA.repository.PersonaRepo;
import com.martinacode.sistemaBBVA.repository.TarjetaRepo;
import com.martinacode.sistemaBBVA.service.IPagoQrService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class PagoQrController {

    @Autowired
    private IPagoQrService service;
    @Autowired
    private MovimientoRepo movimientoRepo;
    @Autowired
    private PersonaRepo pRepo;
    @Autowired
    private TarjetaRepo tRepo;

    @PostMapping("/pago-qr")
    @Transactional
    public String realizarPagoConQr(@RequestBody Movimiento m){
        String pagoRealizado= service.pagoRealizadoConQr(m.getTarjetaPago().getId(),m.getEmisorPago().getId(),m.getReceptorPago().getId(),m.getImporte());
        //String pagoRealizado= m.getTarjetaPago()+m.getEmisorPago().toString()+m.getReceptorPago().toString()+Double.toString(m.getImporte());
        return pagoRealizado;
    }
    @GetMapping("/ver-movimientos")
    public List<Movimiento> verMovimientos(){
        return movimientoRepo.findAll();
    }

    /*
    @DeleteMapping("/borrar-movimientos")
    public String borrarMovimientos(){
        movimientoRepo.deleteAll();
        return "Se borraron todos los movimientos! nwn";
    }
    */
    @DeleteMapping("/borrar-movimiento")
    public String borrarMovimiento(@RequestParam(name = "id")Long id){
        service.eliminarPagoQr(id);
        return "Se borró el movimiento ["+Long.toString(id)+"] por completo!";
    }

}
