package com.techlab.models;

public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int stock;
    private String categoria;

    public Producto(String nombre, double precio, int stock, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }

    public int getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public double getPrecio() {
        return this.precio;
    }

    public int getStock() {
        return this.stock;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "ID: " + this.id +
                " | " + this.nombre +
                " | $" + this.precio +
                " | Stock: " + this.stock +
                " | Categoría: " + this.categoria;
    }

}
