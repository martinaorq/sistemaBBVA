package com.martinacode.sistemaBBVA.model;


import com.martinacode.sistemaBBVA.repository.CodigoQr;
import com.martinacode.sistemaBBVA.repository.Estado;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Qr {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private CodigoQr CodigoQR;
    private double importe;
    private String nombreQr;
    private String nombreMercado;
    private Estado estado;

    public Qr() {
    }

    public Qr(Long id, CodigoQr codigoQR, double importe, String nombreQr, String nombreMercado, Estado estado) {
        this.id = id;
        CodigoQR = codigoQR;
        this.importe = importe;
        this.nombreQr = nombreQr;
        this.nombreMercado = nombreMercado;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CodigoQr getCodigoQR() {
        return CodigoQR;
    }

    public void setCodigoQR(CodigoQr codigoQR) {
        CodigoQR = codigoQR;
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
                ", CodigoQR=" + CodigoQR +
                ", importe=" + importe +
                ", nombreQr='" + nombreQr + '\'' +
                ", nombreMercado='" + nombreMercado + '\'' +
                ", estado=" + estado +
                '}';
    }
}
