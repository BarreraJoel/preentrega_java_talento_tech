package com.techlab.ui;

import com.techlab.models.ItemPedido;
import com.techlab.models.Pedido;
import com.techlab.models.Producto;
import com.techlab.services.PedidoService;
import com.techlab.services.ProductoService;
import com.techlab.util.Validador;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuProducto {

    private final Scanner sc;
    private final ProductoService productoService;
    private final PedidoService pedidoService;

    public MenuProducto(Scanner sc, ProductoService productoService, PedidoService pedidoService) {
        this.sc = sc;
        this.productoService = productoService;
        this.pedidoService = pedidoService;
    }

    public String mostrarMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n==============================\n");
        sb.append(" TECHLAB | SISTEMA DE GESTIÓN\n");
        sb.append("==============================\n");
        sb.append("1) Agregar producto\n");
        sb.append("2) Listar productos\n");
        sb.append("3) Buscar producto por ID\n");
        sb.append("4) Actualizar producto\n");
        sb.append("5) Eliminar producto\n");
        sb.append("6) Crear un pedido\n");
        sb.append("7) Listar pedidos\n");
        sb.append("8) Salir\n");
        return sb.toString();
    }

    public void agregarProducto() {
        System.out.println("--- Nuevo producto ---");
        String nombre = Validador.leerTexto(sc, "Nombre: ");
        double precio = Validador.leerDouble(sc, "Precio: ");
        int stock = Validador.leerEntero(sc, "Stock: ");
        String categoria = Validador.leerTexto(sc, "Categoría: ");

        Producto p = new Producto(nombre, precio, stock, categoria);
        Producto guardado = productoService.guardar(p);

        System.out.println("✔ Producto agregado con id " + guardado.getId());
    }

    public void listarProductos() {
        List<Producto> lista = productoService.listarTodos();

        if (lista.isEmpty()) {
            System.out.println("No hay productos cargados.");
            return;
        }

        System.out.println("--- Catálogo ---");
        for (Producto p : lista) {
            System.out.println(p);
        }
    }

    public void buscarProducto() {
        int id = Validador.leerEntero(sc, "Ingrese el id del producto: ");
        Producto p = productoService.obtenerPorId(id);
        System.out.println("\nDetalle del producto\n" + p);
    }

    public void actualizarProducto() {
        int id = Validador.leerEntero(sc, "Ingrese el id del producto a actualizar: ");

        Producto actual = productoService.obtenerPorId(id);
        System.out.println("Datos actuales: " + actual);

        System.out.println("--- Ingrese los nuevos datos ---");
        String nombre = Validador.leerTexto(sc, "Nombre: ");
        double precio = Validador.leerDouble(sc, "Precio: ");
        int stock = Validador.leerEntero(sc, "Stock: ");
        String categoria = Validador.leerTexto(sc, "Categoría: ");

        Producto datos = new Producto(nombre, precio, stock, categoria);
        Producto actualizado = productoService.actualizar(id, datos);

        System.out.println("Producto actualizado: " + actualizado);
    }

    public void eliminarProducto() {
        int id = Validador.leerEntero(sc, "Ingrese el id del producto a eliminar: ");
        productoService.eliminar(id);
        System.out.println("Producto eliminado.");
    }

    public void crearPedido() {
        System.out.println("\n--- Crear pedido ---");
        String opcion;
        Pedido pedido = new Pedido();
        ArrayList<ItemPedido> itemsPedido = new ArrayList<ItemPedido>();

        do {
            this.listarProductos();
            int productoId = Validador.leerEntero(sc, "Elija el ID de un producto: ");
            Producto p = this.productoService.obtenerPorId(productoId);
            int cantidad = Validador.leerEntero(sc, "Ingrese la cantidad: ");
            Validador.validarCantidadStock(p.getStock(), cantidad);

            ItemPedido item = new ItemPedido(productoId, cantidad);
            itemsPedido.add(item);

            do {
                opcion = Validador.leerTexto(sc, "\n¿Quiere agregar algo más? (S/N): ").toUpperCase();
            } while (!opcion.equals("S") && !opcion.equals("N"));

        } while (opcion.equals("S"));

        if (itemsPedido.size() > 0) {
            for (ItemPedido itemPedido : itemsPedido) {
                this.productoService.actualizarStock(itemPedido.getProductoId(), itemPedido.getCantidad());
            }
            pedido.setItems(itemsPedido);
            Pedido nuevoPedido = pedidoService.guardarPedido(pedido);
            System.out.println("Pedido agregado con id " + nuevoPedido.getId());
        }
    }

    public void listarPedidos() {
        List<Pedido> lista = pedidoService.listarTodos();
        double costoAux = 0;
        System.out.println("\n--- Pedidos ---");

        if (lista.isEmpty()) {
            System.out.println("No hay pedidos creados.");
            return;
        }

        for (Pedido pedido : lista) {
            costoAux = 0;
            System.out.println("PEDIDO #" + pedido.getId());
            for (ItemPedido item : pedido.getItems()) {
                Producto p = this.productoService.obtenerPorId(item.getProductoId());
                System.out.println("(" + p.toString() + ") x" + item.getCantidad());
                double costoItem = this.pedidoService.calcularCosto(p.getPrecio(), item.getCantidad());
                costoAux += costoItem;
            }
            pedido.setCostoTotal(costoAux);
            System.out.println("Costo total: $" + pedido.getCostoTotal() + "\n");
        }
    }

}