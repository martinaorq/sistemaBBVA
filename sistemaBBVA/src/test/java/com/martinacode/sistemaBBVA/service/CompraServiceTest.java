package com.martinacode.sistemaBBVA.service;

import com.martinacode.sistemaBBVA.model.Compra;
import com.martinacode.sistemaBBVA.model.Persona;
import com.martinacode.sistemaBBVA.model.Tarjeta;
import com.martinacode.sistemaBBVA.repository.CompraRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;


@DataJpaTest
@ExtendWith(MockitoExtension.class)
class CompraServiceTest {

    @Spy
    private CompraService service;
    @Mock
    private CompraRepo repositorio;

    @BeforeEach
    void setUp() {
    }

    @Test
    void whenLlamolistarComprasClienteThenMeDevuelveListadeUnSoloCliente() {

        Persona martina= new Persona(1L,45079626L,"martina");
        //Persona pepe= new Persona(2L,4507333L,"pepe");

        Compra compra1=new Compra();
        compra1.setId(1L);
        compra1.setPersona(martina);

        /*Compra compra2=new Compra();
        compra2.setId(2L);
        compra2.setPersona(pepe);*/

        doReturn(asList(compra1)).when(service).listarComprasCliente(1L);

        List<Compra> actual= service.listarComprasCliente(1L);
        assertEquals(actual,asList(compra1));

    }

    @Test
    void whenlistarComprasClienteTarjetaThenSoloDevuelveComprasDeUnaSolaTarjetaDeUnSoloCliente() {

        Persona martina= new Persona(1L,45079626L,"martina");

        Tarjeta tarjeta1=new Tarjeta();
        tarjeta1.setId(1L);

        Tarjeta tarjeta2=new Tarjeta();
        tarjeta2.setId(2L);

        Compra compra1=new Compra(); //compra1: tarjeta1, martina
        compra1.setId(1L);
        compra1.setPersona(martina);
        compra1.setTarjeta(tarjeta1);

        Compra compra2=new Compra(); //compra2: tarjeta2, martina
        compra2.setId(2L);
        compra2.setPersona(martina);
        compra1.setTarjeta(tarjeta2);


        doReturn(asList(compra1)).when(service).listarComprasClienteTarjeta(1L,1L);
        List<Compra> comprasTarjetaActual = service.listarComprasClienteTarjeta(1L,1L);
        assertEquals(comprasTarjetaActual,asList(compra1));
    }
}