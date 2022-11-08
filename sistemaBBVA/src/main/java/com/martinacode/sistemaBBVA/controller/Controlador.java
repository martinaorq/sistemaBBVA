package com.martinacode.sistemaBBVA.controller;

import com.martinacode.sistemaBBVA.model.Compra;
import com.martinacode.sistemaBBVA.model.Persona;
import com.martinacode.sistemaBBVA.model.Tarjeta;
import com.martinacode.sistemaBBVA.repository.CompraRepo;
import com.martinacode.sistemaBBVA.repository.PersonaRepo;
import com.martinacode.sistemaBBVA.repository.TarjetaRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class Controlador {
    Logger logger = LoggerFactory.getLogger(Controlador.class);

    @Autowired
    private TarjetaRepo repoTarjeta;
    @Autowired
    private CompraRepo repoCompra;
    @Autowired
    private PersonaRepo repoPersona;

    @GetMapping("/personas")
    public List<Persona> listarPersona(){
        return repoPersona.findAll();
    }

    @GetMapping("/tarjetas")
    public List<Tarjeta> listarTarjetas(){
        return repoTarjeta.findAll();
    }

    @GetMapping("/compras")
    public List<Compra> listarCompras(){
        return repoCompra.findAll();
    }

    @PostMapping("/insertarPersona")
    public String insertarPersona(@RequestBody Persona p){
        repoPersona.save(p);
        return "Se insertó persona correctamente.";
    }

    @PostMapping("/insertarTarjeta")
    public String insertarTarjeta(@RequestBody Tarjeta t){
        repoTarjeta.save(t);
        return "Se insertó tarjeta correctamente.";
    }

    @PostMapping("/insertarCompra")
    public String insertarTarjeta(@RequestBody Compra c){
        repoCompra.save(c);
        return "Se insertó compra correctamente.";
    }

    @DeleteMapping("/borrarTarjeta")
    public String eliminarTarjeta(@RequestParam(name="id")Long id){
        repoTarjeta.deleteById(id);
        return "Se borró la tarjeta correctamente";
    }

    @DeleteMapping("/borrarCompra")
    public String eliminarcompra(@RequestParam(name="id")Long id){
        repoCompra.deleteById(id);
        return "Se borró la compra correctamente";
    }

    @DeleteMapping("/borrarPersona")
    public String eliminarPersonacompra(@RequestParam(name="id")Long id){
        repoPersona.deleteById(id);
        return "Se borró la persona correctamente";
    }

}
