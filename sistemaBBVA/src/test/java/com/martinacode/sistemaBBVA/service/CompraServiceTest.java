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
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;


@DataJpaTest
@RunWith(MockitoJUnitRunner.class)
class CompraServiceTest {

    @InjectMocks
    @Spy
    private CompraService service;

    @Test
    void when2LlamolistarComprasClienteThenMeDevuelveListadeUnSoloCliente(){
        CompraRepo cr= new CompraRepoStub();
        CompraService serviceCompra = new CompraService(cr);

        assertEquals(3, serviceCompra.listarComprasCliente(1L).size());
        assertEquals(1, serviceCompra.listarComprasCliente(2L).size());
    }

    @Test
    void when2listarComprasClienteTarjetaThenSoloDevuelveComprasDeUnaSolaTarjetaDeUnSoloCliente(){
        CompraRepo cr= new CompraRepoStub();
        CompraService serviceCompra = new CompraService(cr);

        assertEquals(2, serviceCompra.listarComprasClienteTarjeta(1L,1L).size());
        assertEquals(1, serviceCompra.listarComprasClienteTarjeta(1L,2L).size());
        assertEquals(1, serviceCompra.listarComprasClienteTarjeta(2L,3L).size());
    }
}