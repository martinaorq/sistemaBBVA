package com.martinacode.sistemaBBVA.model;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipoDeMovimiento;
    private String metodoPago;
    private Double importe;
    private LocalDate fecha;
    @ManyToOne
    @JoinColumn
    private Qr codigoQr;
    @ManyToOne
    @JoinColumn
    private Tarjeta tarjetaPago;
    @ManyToOne
    @JoinColumn
    private Persona emisorPago;
    @ManyToOne
    @JoinColumn
    private Persona receptorPago;

    public Movimiento() {
    }

    public Movimiento(Long id, String tipoDeMovimiento, String metodoPago, Double importe, LocalDate fecha, Qr codigoQr, Tarjeta tarjetaPago, Persona emisorPago, Persona receptorPago) {
        this.id = id;
        this.tipoDeMovimiento = tipoDeMovimiento;
        this.metodoPago = metodoPago;
        this.importe = importe;
        this.fecha = fecha;
        this.codigoQr = codigoQr;
        this.tarjetaPago = tarjetaPago;
        this.emisorPago = emisorPago;
        this.receptorPago = receptorPago;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public String getTipoDeMovimiento() {
        return tipoDeMovimiento;
    }

    public void setTipoDeMovimiento(String tipoDeMovimiento) {
        this.tipoDeMovimiento = tipoDeMovimiento;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Persona getReceptorPago() {
        return receptorPago;
    }

    public void setReceptorPago(Persona receptorPago) {
        this.receptorPago = receptorPago;
    }

    public Persona getEmisorPago() {
        return emisorPago;
    }

    public void setEmisorPago(Persona emisorPago) {
        this.emisorPago = emisorPago;
    }

    public Tarjeta getTarjetaPago() {
        return tarjetaPago;
    }

    public void setTarjetaPago(Tarjeta tarjetaPago) {
        this.tarjetaPago = tarjetaPago;
    }

    public Qr getCodigoQr() {
        return codigoQr;
    }

    public void setCodigoQr(Qr codigoQr) {
        this.codigoQr = codigoQr;
    }

    @Override
    public String toString() {
        return "Movimiento{" +
                "id=" + id +
                ", tipoDeMovimiento='" + tipoDeMovimiento + '\'' +
                ", fecha=" + fecha +
                ", metodoPago='" + metodoPago + '\'' +
                ", importe=" + importe +
                ", codigoQr=" + codigoQr +
                ", tarjetaPago=" + tarjetaPago +
                ", emisorPago=" + emisorPago +
                ", receptorPago=" + receptorPago +
                '}';
    }
}
