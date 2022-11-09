package com.martinacode.sistemaBBVA.service;

import com.martinacode.sistemaBBVA.model.Persona;
import com.martinacode.sistemaBBVA.model.Qr;
import com.martinacode.sistemaBBVA.model.Tarjeta;
import org.springframework.stereotype.Service;

@Service
public interface IPagoQrService {
    String pagoRealizadoConQr(Long tarjetaPago, Long emisorPago, Long receptorPago, Double importe);
    String eliminarPagoQr(Long id);
}
