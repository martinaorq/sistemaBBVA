package com.martinacode.sistemaBBVA.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private String entidad;

    public Tarjeta(){}

    public Tarjeta(Long id,String numero, String entidad) {
        this.id=id;
        this.numero = numero;
        this.entidad = entidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    @Override
    public String toString() {
        return "Tarjeta{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", entidad='" + entidad + '\'' +
                '}';
    }
}
