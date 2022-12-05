package com.martinacode.sistemaBBVA.service;

import java.io.IOException;

public class WriteToPathException extends RuntimeException {
    public WriteToPathException(String message) {
        super(message);
    }
}
