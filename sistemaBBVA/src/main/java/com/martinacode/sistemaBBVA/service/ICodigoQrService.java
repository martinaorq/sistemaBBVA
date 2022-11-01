package com.martinacode.sistemaBBVA.service;

import com.google.zxing.WriterException;
import com.martinacode.sistemaBBVA.model.Qr;
import com.martinacode.sistemaBBVA.repository.CodigoQr;
import com.martinacode.sistemaBBVA.repository.Estado;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface ICodigoQrService {
    CodigoQr generarCodigo(String texto, int ancho, int alto) throws IOException, WriterException;
    Qr crearEntidadCodigoQr(double importe, String nombreQr, String nombreMercado);
    String consultarDatosQr(Qr codigoQr);
}
