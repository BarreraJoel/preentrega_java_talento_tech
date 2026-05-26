package com.techlab.exceptions;

public class ProductoNoEncontradoException extends RuntimeException {

    public ProductoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
