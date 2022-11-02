package com.martinacode.sistemaBBVA.service;

import com.google.zxing.WriterException;
import com.martinacode.sistemaBBVA.model.Qr;
import com.martinacode.sistemaBBVA.repository.CodigoQr;
import com.martinacode.sistemaBBVA.repository.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CodigoQrService implements ICodigoQrService {

    @Override
    public CodigoQr generarCodigo(String texto, int ancho, int alto){
        CodigoQr codigoQr= null;
        try {
            codigoQr = new CodigoQr(texto,ancho,alto);
        } catch (WriterException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return codigoQr;
    }

    @Override
    public Qr crearCodigoQr(double importe, String nombrePersona, String nombreMercado) {
        Qr nuevoCodigo=new Qr();
        String textForQr = nombrePersona+nombreMercado+Double.toString(importe);
        nuevoCodigo.setCodigoQR(generarCodigo(textForQr,250,250));
        nuevoCodigo.setImporte(importe);
        //Nombre del que generó el Qr
        nuevoCodigo.setNombreQr(nombrePersona+"QR");
        nuevoCodigo.setNombreMercado(nombreMercado);
        nuevoCodigo.setEstado(Estado.PENDIENTE);
        return nuevoCodigo;
    }

    @Override
    public String consultarDatosQr(Qr codigoQr) {
        return codigoQr.toString();
    }

}
