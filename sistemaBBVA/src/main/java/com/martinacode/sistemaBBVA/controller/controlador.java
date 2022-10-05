package com.martinacode.sistemaBBVA.controller;

import com.martinacode.sistemaBBVA.repository.CompraRepo;
import com.martinacode.sistemaBBVA.repository.PersonaRepo;
import com.martinacode.sistemaBBVA.repository.TarjetaRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class controlador {
    Logger logger = LoggerFactory.getLogger(controlador.class);

    @Autowired
    private TarjetaRepo repoTarjeta;
    @Autowired
    private CompraRepo repoCompra;
    @Autowired
    private PersonaRepo repoPersona;

    @PostMapping
    public String insertarPersona(){
        return "Se insertó persona correctamente.";
    }

}
