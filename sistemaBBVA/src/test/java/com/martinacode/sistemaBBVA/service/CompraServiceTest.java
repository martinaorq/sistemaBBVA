package com.martinacode.sistemaBBVA.service;

import com.martinacode.sistemaBBVA.model.Compra;
import com.martinacode.sistemaBBVA.model.Persona;
import com.martinacode.sistemaBBVA.model.Tarjeta;
import com.martinacode.sistemaBBVA.repository.CompraRepo;
import org.junit.jupiter.api.BeforeAll;
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
        service.setRepoCompra(repositorio);
    }

    @Test
    void whenLlamolistarComprasClienteThenMeDevuelveListadeUnSoloCliente() {

        Persona martina= new Persona(1L,45079626L,"martina");

        Compra compra1=new Compra();
        compra1.setId(1L);
        compra1.setPersona(martina);

        service.getRepoCompra();
        verify(service).getRepoCompra();

        doReturn(asList(compra1)).when(service).listarComprasCliente(anyLong());
        List <Compra> compraActual= service.listarComprasCliente(anyLong());
        assertEquals(service.listarComprasCliente(anyLong()),asList(compra1));

    }

    @Test
    void whenlistarComprasClienteTarjetaThenSoloDevuelveComprasDeUnaSolaTarjetaDeUnSoloCliente() {

        Persona martina= new Persona(1L,45079626L,"martina");

        Tarjeta tarjeta1=new Tarjeta();
        tarjeta1.setId(1L);

        Compra compra1=new Compra(); //compra1: tarjeta1, martina
        compra1.setId(1L);
        compra1.setPersona(martina);
        compra1.setTarjeta(tarjeta1);

        List <Compra> compras = asList(compra1);

        doReturn(compras).when(service).listarComprasClienteTarjeta(anyLong(),anyLong());
        List <Compra> compraActual= service.listarComprasClienteTarjeta(anyLong(),anyLong());
        assertThat(service.listarComprasClienteTarjeta(anyLong(),anyLong())).isEqualTo(compras);

        doReturn(compras).when(service).listarComprasClienteTarjeta(1L,1L);
        List <Compra> compraActual2= service.listarComprasClienteTarjeta(1L,1L);
        assertThat(compraActual2).isEqualTo(compras);

        verify(service).listarComprasClienteTarjeta(1L,1L);

    }
}