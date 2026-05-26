package com.techlab.services;

import com.techlab.models.Pedido;

import java.util.ArrayList;
import java.util.List;

public class PedidoService {

    private List<Pedido> pedidos = new ArrayList<Pedido>();
    private static int contadorId = 1;

    public Pedido guardarPedido(Pedido pedido) {
        pedido.setId(contadorId);
        contadorId++;

        this.pedidos.add(pedido);
        return pedido;
    }

    public List<Pedido> listarTodos() {
        return this.pedidos;
    }

    public double calcularCosto(double precio, int cantidad) {
        return precio * cantidad;
    }

}
