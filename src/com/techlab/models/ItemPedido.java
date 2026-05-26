package com.techlab.models;

public class ItemPedido {
    private int productoId;
    private int cantidad;

    public ItemPedido(int productoId, int cantidad) {
        this.productoId = productoId;
        this.cantidad = cantidad;
    }

    public int getProductoId() {
        return this.productoId;
    }

    public int getCantidad() {
        return this.cantidad;
    }


}
