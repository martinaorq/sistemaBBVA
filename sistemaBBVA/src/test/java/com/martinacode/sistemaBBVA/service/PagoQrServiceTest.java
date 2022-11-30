package com.martinacode.sistemaBBVA.service;

import com.martinacode.sistemaBBVA.model.Movimiento;
import com.martinacode.sistemaBBVA.model.Persona;
import com.martinacode.sistemaBBVA.model.Qr;
import com.martinacode.sistemaBBVA.model.Tarjeta;
import com.martinacode.sistemaBBVA.repository.Estado;
import com.martinacode.sistemaBBVA.repository.MovimientoRepo;
import com.martinacode.sistemaBBVA.repository.PersonaRepo;
import com.martinacode.sistemaBBVA.repository.TarjetaRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PagoQrServiceTest {

    @InjectMocks
    PagoQrService service;

    @Mock
    CodigoQrService qrService;

    @Mock
    MovimientoRepo movimientoRepo;

    @Mock
    PersonaRepo personaRepo;

    @Mock
    TarjetaRepo tarjetaRepo;

    @Mock
    Movimiento movimientoMock;

    Persona persona1;
    Persona persona2;
    Tarjeta tarjeta1;
    Qr codigoQr1;

    Movimiento mov;

    @BeforeEach
    void setUp() {
        movimientoRepo.deleteAll();
        persona1=new Persona(1L,34567873L,"Martina");
        persona2=new Persona(2L,34567773L,"Lautaro");
        tarjeta1=new Tarjeta(1L,"5305754015298832","VISA");

        codigoQr1=new Qr();

        mov=new Movimiento();
        mov.setId(1L);
        mov.setTipoDeMovimiento("Pago con QR");
        mov.setMetodoPago("QR");
        mov.setTarjetaPago(tarjeta1);
        mov.setEmisorPago(persona1);
        mov.setReceptorPago(persona2);
        mov.setImporte(203.3);
        mov.setFecha(LocalDate.now());
        mov.setDescripcion("path");

        codigoQr1.setId(1L);
        codigoQr1.setMovimiento(mov);
        codigoQr1.setImporte(mov.getImporte());
        mov.setCodigoQr(codigoQr1);

    }

    @Test
    void pagoRealizadoConQr() throws IOException {
        when(personaRepo.getReferenceById(anyLong())).thenReturn(persona1,persona2);
        doReturn(tarjeta1).when(tarjetaRepo).getReferenceById(1L);
        doReturn(codigoQr1).when(qrService).nuevoCodigoQr(anyDouble(),anyString(),anyString());

        String resultado= service.pagoRealizadoConQr(1L,1L,2L,203.3);

        mov.getCodigoQr().setEstado(Estado.CERRADO);

        assertEquals("Pago QR realizado!",resultado);
        assertNotEquals(null, resultado);
    }

    @Test
    void iniciarPagoQr() throws IOException {
        when(personaRepo.getReferenceById(anyLong())).thenReturn(persona1,persona2);
        doReturn(tarjeta1).when(tarjetaRepo).getReferenceById(1L);
        doReturn(codigoQr1).when(qrService).nuevoCodigoQr(anyDouble(),anyString(),anyString());
        doReturn("path").when(qrService).generarImagenQr(any());

        Movimiento nuevoMovimiento= service.iniciarPagoQr(1L,1L,2L,203.3);
        nuevoMovimiento.setId(1L);
        assertEquals(nuevoMovimiento.toString(), mov.toString());
        assertNotEquals(null, nuevoMovimiento);

    }

    @Test
    void finalizarPagoQr() {
        doReturn(mov).when(movimientoRepo).getReferenceById(1L);
        Movimiento m= service.finalizarPagoQr(1L);

        assertEquals(Estado.CERRADO,m.getCodigoQr().getEstado());
        assertNotEquals(null,m);

        mov.getCodigoQr().setEstado(Estado.CERRADO);
        assertEquals(mov,m);
    }

    @Test
    void eliminarPagoQr() {
        doReturn(mov).when(movimientoRepo).getReferenceById(1L);
        String resultado= service.eliminarPagoQr(1L);

        assertEquals(0,movimientoRepo.count());
        assertNotEquals(1,movimientoRepo.count());
        assertNotEquals(null,resultado);
    }
}