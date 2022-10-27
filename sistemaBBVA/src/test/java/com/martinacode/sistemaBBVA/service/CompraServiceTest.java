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

    @InjectMocks
    @Spy
    private CompraService service;
    @Mock
    private CompraRepo repositorio;

    private Persona martina;
    private Tarjeta tarjeta;
    private Compra compra;
    private List <Compra> compras;

    private static final Long ID_CLIENTE = 1L;
    private static final Long ID_TARJETA=1L;
    private static final Long DNI_CLIENTE=45079626L;
    private static final String NOMBRE_CLIENTE="martina";

    @BeforeEach
    void setUp() {
        service=Mockito.spy(new CompraService(repositorio));

        martina= new Persona(ID_CLIENTE,DNI_CLIENTE,NOMBRE_CLIENTE);

        tarjeta=new Tarjeta();
        tarjeta.setId(ID_TARJETA);

        compra=new Compra(); //compra1: tarjeta1, martina
        compra.setId(1L);
        compra.setPersona(martina);
        compra.setTarjeta(tarjeta);

        compras= asList(compra);

    }

    @Test
    void name() {
        service.setRepoCompra(repositorio);
        service.getRepoCompra();
        verify(service).getRepoCompra();
    }

    @Test
    void whenLlamolistarComprasClienteThenMeDevuelveListadeUnSoloCliente() {

        doReturn(compras).when(service).listarComprasCliente(anyLong());
        List <Compra> compraActual= service.listarComprasCliente(ID_CLIENTE);
        assertEquals(compraActual,compras);
        assertEquals(compraActual.get(0),compras.get(0));

    }

    @Test
    void whenlistarComprasClienteTarjetaThenSoloDevuelveComprasDeUnaSolaTarjetaDeUnSoloCliente() {

        doReturn(compras).when(service).listarComprasClienteTarjeta(anyLong(),anyLong());
        List <Compra> compraActual= service.listarComprasClienteTarjeta(ID_CLIENTE,ID_TARJETA);
        assertThat(compraActual).isEqualTo(compras);
        assertThat(service.listarComprasClienteTarjeta(anyLong(),anyLong())).isEqualTo(compras);
        verify(service).listarComprasClienteTarjeta(ID_CLIENTE,ID_TARJETA);

    }
}