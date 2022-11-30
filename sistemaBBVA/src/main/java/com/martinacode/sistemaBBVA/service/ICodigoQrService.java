package com.martinacode.sistemaBBVA.service;

import com.google.zxing.WriterException;
import com.martinacode.sistemaBBVA.model.Qr;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface ICodigoQrService {
    Qr nuevoCodigoQr(double importe, String nombreQr, String nombreMercado);
    byte[] generarCodigoQr(Qr qr);

    String generarImagenQr(Qr qr) ;
    String getIdPath(String idQr);
    String consultarDatosQr(Qr codigoQr);
}
