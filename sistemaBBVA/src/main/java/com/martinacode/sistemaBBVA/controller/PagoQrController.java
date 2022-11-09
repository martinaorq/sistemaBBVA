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
import java.util.stream.Collectors;

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

    private static final String METODO_PAGO_QR= "QR";

    @PostMapping("/pago-qr")
    @Transactional
    public String realizarPagoConQr(@RequestBody Movimiento m){
        String pagoRealizado= service.pagoRealizadoConQr(m.getTarjetaPago().getId(),m.getEmisorPago().getId(),m.getReceptorPago().getId(),m.getImporte());
        return pagoRealizado;
    }
    @GetMapping("/ver-movimientos-qr")
    public List<Movimiento> verMovimientos(){
        return movimientoRepo.findAll().stream()
                .filter(movimiento-> METODO_PAGO_QR.equals(movimiento.getMetodoPago()))
                .collect(Collectors.toList());
    }


    @DeleteMapping("/borrar-movimientos")
    public String borrarTodosLosMovimientos(){
        movimientoRepo.deleteAll();
        return "Se borraron todos los movimientos! nwn";
    }


    @DeleteMapping("/borrar-movimiento")
    public String borrarMovimiento(@RequestParam(name = "id")Long id){
        service.eliminarPagoQr(id);
        return "Se borró el movimiento ["+Long.toString(id)+"] por completo!";
    }

}
