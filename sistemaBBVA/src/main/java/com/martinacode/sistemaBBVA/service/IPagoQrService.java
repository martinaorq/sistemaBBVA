package com.martinacode.sistemaBBVA.service;

import com.martinacode.sistemaBBVA.model.Movimiento;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface IPagoQrService {

    Movimiento iniciarPagoQr(Long tarjetaPago, Long emisorPago, Long receptorPago, Double importe) throws IOException;
    String pagoRealizadoConQr(Long tarjetaPago, Long emisorPago, Long receptorPago, Double importe);
    String eliminarPagoQr(Long id);
    Movimiento finalizarPagoQr(Long idMovimiento);
}
