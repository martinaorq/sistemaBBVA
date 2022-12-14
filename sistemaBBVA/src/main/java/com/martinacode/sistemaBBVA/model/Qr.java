package com.martinacode.sistemaBBVA.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.martinacode.sistemaBBVA.repository.Estado;
import lombok.Data;

import javax.persistence.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Arrays;

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

    @OneToOne(mappedBy="codigoQr")
    @JsonBackReference
    private Movimiento movimiento;

    public Qr() {
        this.estado = Estado.PENDIENTE;
    }

    public Qr(Long id, double importe, String nombreQr, String nombreMercado, Estado estado, byte[] codigoQr) {
        this.id = id;
        this.importe = importe;
        this.nombreQr = nombreQr;
        this.nombreMercado = nombreMercado;
        this.codigoQr= codigoQr;
        this.estado = Estado.PENDIENTE;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movimiento getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(Movimiento movimiento) {
        this.movimiento = movimiento;
    }

    public byte[] getCodigoQR() {
        return codigoQr;
    }

    public void setCodigoQR(byte[] codigoQr) {
        this.codigoQr=codigoQr;
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
                //", codigoQr=" + Arrays.toString(codigoQr) +
                ", importe=" + importe +
                ", nombreQr='" + nombreQr + '\'' +
                ", nombreMercado='" + nombreMercado + '\'' +
                ", estado=" + estado +
                ", movimiento=" + movimiento +
                '}';
    }
}
