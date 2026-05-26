package com.techlab.services;

import com.techlab.exceptions.ProductoNoEncontradoException;
import com.techlab.models.Producto;
import com.techlab.util.Validador;

import java.util.ArrayList;
import java.util.List;

public class ProductoService {

    private List<Producto> productos = new ArrayList<>();

    private static int contadorId = 1;

    public Producto guardar(Producto p) {
        Validador.validarNombre(p.getNombre());
        Validador.validarPrecio(p.getPrecio());
        Validador.validarStock(p.getStock());
        Validador.validarCategoria(p.getCategoria());

        p.setId(contadorId);
        contadorId++;

        productos.add(p);
        return p;
    }

    public List<Producto> listarTodos() {
        return productos;
    }

    public Producto obtenerPorId(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) {
                return p;
            }
        }
        throw new ProductoNoEncontradoException("No se encontró un producto con id " + id);
    }

    public Producto actualizar(int id, Producto datos) {
        Producto p = obtenerPorId(id);

        Validador.validarNombre(datos.getNombre());
        Validador.validarPrecio(datos.getPrecio());
        Validador.validarStock(datos.getStock());
        Validador.validarCategoria(datos.getCategoria());

        p.setNombre(datos.getNombre());
        p.setPrecio(datos.getPrecio());
        p.setStock(datos.getStock());
        p.setCategoria(datos.getCategoria());

        return p;
    }

    public Producto actualizarStock(int productoId, int cantidad) {
        Producto p = obtenerPorId(productoId);

        Validador.validarCantidadStock(p.getStock(), cantidad);

        p.setStock(p.getStock() - cantidad);

        return p;
    }

    public void eliminar(int id) {
        Producto p = obtenerPorId(id);
        productos.remove(p);
    }
}
