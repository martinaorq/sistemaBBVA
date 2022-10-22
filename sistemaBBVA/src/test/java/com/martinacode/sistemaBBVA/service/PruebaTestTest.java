package com.martinacode.sistemaBBVA.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class PruebaTestTest {

    @Test
    void whenPongoNumeroMenorACerothentiraException() throws Exception {
        var entidad=new PruebaTest();
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(()->entidad.setNumX(-1))
                .withMessage("El num no puede ser menor a cero");
    }

    @Test
    void whenPongoNumeroMayorANumBthentiraException() {
        var entidad=new PruebaTest();
        entidad.setNumB(10);
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(()->entidad.setNumX(13))
                .withMessage("El num no puede ser mayor al valor numB");
    }

    @Test
    void whenPongoMayoraCeroYMenoraBThenCorreBien() throws Exception {
        var entidad=new PruebaTest();
        entidad.setNumB(9);
        entidad.setNumX(7);
        assertThat(entidad.getNumX()).isEqualTo(7);
    }

    @Test
    void whenUsoGetNumBthenReciboNumB(){
        var entidad=new PruebaTest();
        entidad.setNumB(4);
        assertThat(entidad.getNumB()).isEqualTo(4);
    }
}