package com.martinacode.sistemaBBVA.controller;

import com.martinacode.sistemaBBVA.model.Movimiento;
import com.martinacode.sistemaBBVA.repository.MovimientoRepo;
import com.martinacode.sistemaBBVA.repository.PersonaRepo;
import com.martinacode.sistemaBBVA.repository.TarjetaRepo;
import com.martinacode.sistemaBBVA.service.IPagoQrService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/pago-qr")
    public String realizarPagoConQr(@RequestBody Movimiento m){
        //String pagoRealizado= service.pagoConQr(m.getTarjetaPago(),m.getEmisorPago(),m.getReceptorPago(),m.getImporte());
        String pagoRealizado= m.getTarjetaPago()+m.getEmisorPago().toString()+m.getReceptorPago().toString()+Double.toString(m.getImporte());
        return m.toString();
    }
    @GetMapping("/ver-movimientos")
    public List<Movimiento> verMovimientos(){
        return movimientoRepo.findAll();
    }
}
