package com.martinacode.sistemaBBVA.model;

import javax.persistence.*;


@Entity
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipoDeMovimiento;
    private String metodoPago;
    private Double importe;
    @OneToOne
    @JoinColumn(name = "codigo_qr_id")
    private Qr codigoQr;
    @OneToOne
    @JoinColumn(name = "tarjeta_pago_id")
    private Tarjeta tarjetaPago;
    @OneToOne
    @JoinColumn(name = "emisor_pago_id")
    private Persona emisorPago;
    @OneToOne
    @JoinColumn(name = "receptor_pago_id")
    private Persona receptorPago;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                ", metodoPago='" + metodoPago + '\'' +
                ", importe=" + importe +
                ", codigoQr=" + codigoQr +
                ", tarjetaPago=" + tarjetaPago +
                ", emisorPago=" + emisorPago +
                ", receptorPago=" + receptorPago +
                '}';
    }
}
