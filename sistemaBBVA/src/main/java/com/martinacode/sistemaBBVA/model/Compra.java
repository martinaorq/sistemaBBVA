package com.martinacode.sistemaBBVA.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String precio;
    private String descripcion;
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn
    private Persona persona;

    @ManyToOne
    @JoinColumn
    private Tarjeta tarjeta;

    public Compra(){}

    public Compra(Long id,String nombre, String precio, String descripcion, LocalDate fecha, Persona persona, Tarjeta tarjeta) {
        this.id=id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.persona = persona;
        this.tarjeta = tarjeta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }
}
