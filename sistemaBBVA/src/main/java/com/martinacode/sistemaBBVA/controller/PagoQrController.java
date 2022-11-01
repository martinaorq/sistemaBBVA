package com.martinacode.sistemaBBVA.controller;

import com.martinacode.sistemaBBVA.model.Movimiento;
import com.martinacode.sistemaBBVA.service.IPagoQrService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PagoQrController {

    @Autowired
    private IPagoQrService service;

    @PostMapping("/pago-qr")
    public String realizarPagoConQr(@RequestBody @NotNull Movimiento m){
        String pagoRealizado= service.pagoConQr(m.getMetodoPago(), m.getTarjetaPago(),m.getEmisorPago(),m.getReceptorPago(),m.getImporte());
        return pagoRealizado;
    }
}
