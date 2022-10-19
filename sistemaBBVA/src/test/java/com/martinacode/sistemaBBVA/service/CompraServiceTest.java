package com.martinacode.sistemaBBVA.service;

import com.martinacode.sistemaBBVA.model.Compra;
import com.martinacode.sistemaBBVA.model.Persona;
import com.martinacode.sistemaBBVA.model.Tarjeta;
import com.martinacode.sistemaBBVA.repository.CompraRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class CompraServiceTest {

    @Mock
    private CompraRepo compraRepo;
    private CompraService compraService=new CompraService();
    private Compra compra1= new Compra("Vea",
            "500 ARS",
            "Compra mensual",
            LocalDate.of(2022,10,17),
            new Persona(45079626L,"Martina"),
            new Tarjeta("34","Mastercard"
            ));
    private Compra compra2= new Compra("Carrefour",
            "500 ARS",
            "Compra mensual",
            LocalDate.of(2022,10,17),
            new Persona(45079626L,"Martina"),
            new Tarjeta("34","Mastercard"
            ));
    private Compra compra3= new Compra(
            "Vea",
            "500 ARS",
            "Compra mensual",
            LocalDate.of(2022,10,17),
            new Persona(45079626L,"Martina"),
            new Tarjeta("34","Visa"
            ));
    private Compra compra4=new Compra(
            "Vea",
            "500 ARS",
            "Compra mensual",
            LocalDate.of(2022,10,17),
            new Persona(22229626L,"Pepe"),
            new Tarjeta("34","Mastercard"
            ));

    @BeforeEach
    void setUp() {
        compraRepo.deleteAll();
        compraRepo.save(compra1);
        compraRepo.save(compra2);
        compraRepo.save(compra3);
        compraRepo.save(compra4);
    }

    @Test
    void whenListarComprasClienteThenListarComprasSoloDelClienteEspecificado() {
        //given
        List<Compra>expected= Arrays.asList(compra1,compra2,compra3);
        //when
        List<Compra> result=compraService.listarComprasClienteTarjeta(1L,1L);
        //then
        assertEquals(expected,result);


    }

    @Test
    void whenListarComprasClienteTarjetaThenListarSoloComprasDeUnsoloClienteSobreUnaSolaTarjeta() {
    }
}