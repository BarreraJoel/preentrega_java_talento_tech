import java.util.Scanner;

import com.techlab.exceptions.ProductoNoEncontradoException;
import com.techlab.exceptions.StockInsuficienteException;
import com.techlab.services.PedidoService;
import com.techlab.services.ProductoService;
import com.techlab.ui.MenuProducto;
import com.techlab.util.MockData;
import com.techlab.util.Validador;

public class App {
    public static void main(String[] args) throws Exception {
        ProductoService service = new ProductoService();
        PedidoService pedidoService = new PedidoService();
        Scanner sc = new Scanner(System.in);
        MenuProducto menu = new MenuProducto(sc, service, pedidoService);

        MockData.cargarDatosIniciales(service);

        int opcion;

        do {
            System.out.println(menu.mostrarMenu());
            opcion = Validador.leerEntero(sc, "Elija una opción: ");

            try {
                switch (opcion) {
                    case 1 -> menu.agregarProducto();
                    case 2 -> menu.listarProductos();
                    case 3 -> menu.buscarProducto();
                    case 4 -> menu.actualizarProducto();
                    case 5 -> menu.eliminarProducto();
                    case 6 -> menu.crearPedido();
                    case 7 -> menu.listarPedidos();
                    case 8 -> System.out.println("¡Hasta luego!");
                    default -> System.out.println("Opción inválida. Elija un número del 1 al 8.");
                }
            } catch (ProductoNoEncontradoException | StockInsuficienteException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Dato inválido: " + e.getMessage());
            }

            System.out.println();

        } while (opcion != 8);

        sc.close();
    }

}
