package com.martinacode.sistemaBBVA.service;

import com.google.zxing.WriterException;
import com.martinacode.sistemaBBVA.model.Qr;
import com.martinacode.sistemaBBVA.repository.Estado;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CodigoQrService implements ICodigoQrService {

    @Override
    public Qr nuevoCodigoQr(double importe, String nombrePersona, String nombreMercado) {
        Qr nuevoQr=new Qr();
        nuevoQr.setImporte(importe);
        nuevoQr.setNombreQr(Double.toString(importe)+nombrePersona+nombreMercado+"QR");
        nuevoQr.setNombreMercado(nombreMercado);
        nuevoQr.setCodigoQR();
        return nuevoQr;
    }

    @Override
    public String consultarDatosQr(Qr codigoQr) {
        return codigoQr.toString();
    }

}
