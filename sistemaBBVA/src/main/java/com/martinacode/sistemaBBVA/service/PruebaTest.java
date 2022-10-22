package com.martinacode.sistemaBBVA.service;

public class PruebaTest {
    private int numX;
    private int numB;

    public int getNumX() {
        return numX;
    }

    public void setNumX(int numX) throws Exception {
        if(numX <0){
            throw new Exception("El num no puede ser menor a cero");
        } else if (numX> numB) {
            throw new Exception("El num no puede ser mayor al valor numB");
        }else {
            this.numX = numX;
        }
    }

    public int getNumB() {
        return numB;
    }

    public void setNumB(int numB) {
        this.numB = numB;
    }
}
