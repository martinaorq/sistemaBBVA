package com.martinacode.sistemaBBVA.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long dni;
    private String nombre;

    public Persona() {
    }

    @OneToMany
    @JoinColumn
    private List<Tarjeta> tarjetas = new ArrayList<>();

    @JsonBackReference(value = "emisor")
    @OneToMany(mappedBy = "emisorPago")
    private List<Movimiento> movimientosPago = new ArrayList<>();

    @JsonBackReference(value = "receptor")
    @OneToMany(mappedBy = "receptorPago")
    private List<Movimiento> movimientosCobro = new ArrayList<>();

    public Persona(Long id, Long dni, String nombre) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
    }

    public String borrarMovimientoCobro(Long id){
        this.getMovimientosCobro().remove(id);
        return "Se ha borrado el movimiento! [repositorio Persona:Receptor]";
    }

    public String borrarMovimientoPago(Long id){
        this.getMovimientosPago().remove(id);
        return "Se ha borrado el movimiento! [repositorio Persona:Emisor]";
    }

    public List<Movimiento> getMovimientosPago() {
        return movimientosPago;
    }

    public void setMovimientosPago(Movimiento movimientoPago) {
        this.movimientosPago.add(movimientoPago);
    }

    public List<Movimiento> getMovimientosCobro() {
        return movimientosCobro;
    }

    public void setMovimientosCobro(Movimiento movimientoCobro) {
        this.movimientosCobro.add(movimientoCobro);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Tarjeta> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(List<Tarjeta> tarjetas) {
        this.tarjetas = tarjetas;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", dni=" + dni +
                ", nombre='" + nombre + '\'' +
                ", tarjetas=" + tarjetas
                /*", movimientosPago=" + movimientosPago +
                ", movimientosCobro=" + movimientosCobro +
                '}'*/
                ;
    }
}
