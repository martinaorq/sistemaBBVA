package com.martinacode.sistemaBBVA.controller;

import com.martinacode.sistemaBBVA.model.Persona;
import com.martinacode.sistemaBBVA.repository.CompraRepo;
import com.martinacode.sistemaBBVA.repository.PersonaRepo;
import com.martinacode.sistemaBBVA.repository.TarjetaRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class controlador {
    Logger logger = LoggerFactory.getLogger(controlador.class);

    @Autowired
    private TarjetaRepo repoTarjeta;
    @Autowired
    private CompraRepo repoCompra;
    @Autowired
    private PersonaRepo repoPersona;

    @GetMapping
    public List<Persona> listar(){
        return repoPersona.findAll();
    }

    @PostMapping
    public String insertarPersona(@RequestBody Persona p){
        repoPersona.save(p);
        return "Se insertó persona correctamente.";
    }

}
