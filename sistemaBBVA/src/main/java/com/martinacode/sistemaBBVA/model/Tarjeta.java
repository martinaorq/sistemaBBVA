package com.martinacode.sistemaBBVA.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private String entidad;

    @JsonBackReference
    @OneToMany(mappedBy = "tarjetaPago")
    private List<Movimiento> movimientos= new ArrayList<>();

    public Tarjeta() {
    }

    public Tarjeta(Long id, String numero, String entidad) {
        this.id = id;
        this.numero = numero;
        this.entidad = entidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(Movimiento movimiento) {
        this.movimientos.add(movimiento);
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

    public String borrarMovimiento(Long id){
        this.movimientos.remove(id);
        return "Se ha borrado el movimiento! [repositorio Tarjeta]";
    }

    @Override
    public String toString() {
        return "Tarjeta{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", entidad='" + entidad + '\'' ;
    }
}
