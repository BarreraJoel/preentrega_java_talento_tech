package com.techlab.models;

import java.util.ArrayList;

public class Pedido {

    private int id;
    private ArrayList<ItemPedido> items;
    private double costoTotal = 0;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public double getCostoTotal() {
        return this.costoTotal;
    }

    public void setItems(ArrayList<ItemPedido> items) {
        this.items = items;
    }

    public ArrayList<ItemPedido> getItems() {
        return this.items;
    }
}
