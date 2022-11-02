package com.martinacode.sistemaBBVA.service;

import com.martinacode.sistemaBBVA.model.Persona;
import com.martinacode.sistemaBBVA.model.Qr;
import com.martinacode.sistemaBBVA.model.Tarjeta;
import org.springframework.stereotype.Service;

@Service
public interface IPagoQrService {
    String pagoConQr(Tarjeta tarjetaPago, Persona emisorPago, Persona receptorPago, Double importe);
}
