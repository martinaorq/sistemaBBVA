package com.martinacode.sistemaBBVA.controller;

import com.martinacode.sistemaBBVA.model.Movimiento;
import com.martinacode.sistemaBBVA.model.Persona;
import com.martinacode.sistemaBBVA.model.Tarjeta;
import com.martinacode.sistemaBBVA.repository.MovimientoRepo;
import com.martinacode.sistemaBBVA.service.IPagoQrService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

@Controller
public class PagoQrController2 {
    @Autowired
    private IPagoQrService service;
    @Autowired
    private MovimientoRepo movimientoRepo;

    @RequestMapping(method = RequestMethod.GET, value = "/iniciar-pago-qr-json")
    public String iniciarPagoQr(@RequestBody Movimiento m, Model modelo){
        Movimiento mov= null;
        try {
            mov = service.iniciarPagoQr(m.getTarjetaPago().getId(),m.getEmisorPago().getId(),m.getReceptorPago().getId(),m.getImporte());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        modelo.addAttribute("qrPath",mov.getDescripcion());
        modelo.addAttribute("idMovimiento",mov.getId());
        return "codigoqr.html";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/iniciar-pago-qr")
    public String iniciarPagoQr(@RequestParam(name="idTarjeta") @NotNull Long idTarjeta
                                ,@RequestParam(name="idEmisor") @NotNull Long idEmisor
                                ,@RequestParam(name="idReceptor") @NotNull Long idReceptor
                                ,@RequestParam(name="importe") @NotNull Double importe
                                ,Model modelo){
        Movimiento mov= null;
        try {
            mov = service.iniciarPagoQr(idTarjeta,idEmisor,idReceptor,importe);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        modelo.addAttribute("qrPath",mov.getDescripcion());
        modelo.addAttribute("idMovimiento",mov.getId());
        modelo.addAttribute("estadoQr",mov.getCodigoQr().getEstado());
        modelo.addAttribute("idQr",mov.getCodigoQr().getId());
        return "codigoqr.html";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/finalizando-pago-qr")
    public String pagarQr(@RequestParam(name="movimiento") @NotNull Long idMovimiento, Model modelo){
        Movimiento mov=movimientoRepo.getReferenceById(idMovimiento);
        service.finalizarPagoQr(mov.getId());
        modelo.addAttribute("idMovimiento", mov.getId());
        modelo.addAttribute("estadoQr",mov.getCodigoQr().getEstado());
        return "pagoQrRealizado.html";
    }

}
