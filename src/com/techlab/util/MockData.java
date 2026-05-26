package com.techlab.util;

import com.techlab.models.Producto;
import com.techlab.services.ProductoService;

public class MockData {

    public static void cargarDatosIniciales(ProductoService service) {
        service.guardar(new Producto("Café molido 500g", 4500, 30, "Bebidas"));
        service.guardar(new Producto("Yerba mate 1kg", 3200, 50, "Bebidas"));
        service.guardar(new Producto("Galletitas dulces", 1850, 100, "Almacén"));
        service.guardar(new Producto("Aceite de oliva 500ml", 6700, 20, "Almacén"));
        service.guardar(new Producto("Chocolate amargo 70%", 2900, 15, "Golosinas"));
        System.out.println("\nSe cargaron 5 productos de prueba.\n");
    }
}
