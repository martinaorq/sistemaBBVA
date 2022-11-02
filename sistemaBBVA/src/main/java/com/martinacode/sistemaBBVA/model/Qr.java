package com.martinacode.sistemaBBVA.model;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.martinacode.sistemaBBVA.repository.Estado;

import javax.persistence.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Entity
public class Qr {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private byte[] codigoQr;
    private double importe;
    private String nombreQr;
    private String nombreMercado;
    private Estado estado;

    public Qr() {
        this.estado = Estado.PENDIENTE;
    }

    public Qr(Long id, double importe, String nombreQr, String nombreMercado, Estado estado) {
        this.id = id;
        this.importe = importe;
        this.nombreQr = nombreQr;
        this.nombreMercado = nombreMercado;
        this.codigoQr= generarCodigoQr();
        this.estado = Estado.PENDIENTE;
    }

    public byte[] generarCodigoQr(){
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = qrCodeWriter.encode(nombreQr, BarcodeFormat.QR_CODE, 250, 250);
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageConfig con = new MatrixToImageConfig( 0xFF000002 , 0xFFFFC041 ) ;

        try {
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream,con);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        byte[] pngData = pngOutputStream.toByteArray();
        this.codigoQr=pngData;
        return pngData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getCodigoQR() {
        return codigoQr;
    }

    public void setCodigoQR() {
        getCodigoQR();
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getNombreQr() {
        return nombreQr;
    }

    public void setNombreQr(String nombreQr) {
        this.nombreQr = nombreQr;
    }

    public String getNombreMercado() {
        return nombreMercado;
    }

    public void setNombreMercado(String nombreMercado) {
        this.nombreMercado = nombreMercado;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }



    @Override
    public String toString() {
        return "Qr{" +
                "id=" + id +
                ", CodigoQR=" + codigoQr +
                ", importe=" + importe +
                ", nombreQr='" + nombreQr + '\'' +
                ", nombreMercado='" + nombreMercado + '\'' +
                ", estado=" + estado +
                '}';
    }
}
