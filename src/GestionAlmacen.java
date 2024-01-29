import com.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GestionAlmacen {
    private static ArrayList<Articulo> listaArticulos = new ArrayList<>();
    private static final String NOMBRE_ARCHIVO = "articulos.dat";
    private static final String NOMBRE_CSV = "articulos.csv";

    public static void main(String[] args) {
        cargarArticulosDesdeArchivo();

        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            mostrarMenu();
            opcion = obtenerOpcion(scanner);

            switch (opcion) {
                case 1:
                    agregarArticulo(scanner);
                    break;
                case 2:
                    borrarArticulo(scanner);
                    break;
                case 3:
                    consultarArticulo(scanner);
                    break;
                case 4:
                    listarArticulos();
                    break;
                case 5:
                    exportarArticulosACSV();
                    break;
                case 6:
                    guardarArticulosEnArchivo();
                    System.out.println("¡Programa terminado!");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }

        } while (opcion != 6);
    }

    /**
     * Intentamos leer el archivo.dat con FileInputStream.
     * En caso de existir, metemos los datos en el ArrayList "listaArticulos".
     */
    private static void cargarArticulosDesdeArchivo() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(NOMBRE_ARCHIVO))) {
            listaArticulos = (ArrayList<Articulo>) ois.readObject();
            System.out.println("Artículos cargados correctamente desde el archivo.");
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe. Se creará uno nuevo al finalizar.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    /**
     * Instanciamos un ObjectOutputStream y le insertamos la lista de artículos
     * con el método writeObject.
     */
    private static void guardarArticulosEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NOMBRE_ARCHIVO))) {
            oos.writeObject(listaArticulos);
            System.out.println("Artículos guardados correctamente en el archivo.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n*** Menú ***");
        System.out.println("1. Añadir nuevo artículo");
        System.out.println("2. Borrar artículo por id");
        System.out.println("3. Consultar artículo por id");
        System.out.println("4. Listado de todos los artículos");
        System.out.println("5. Exportar artículos a archivo CSV");
        System.out.println("6. Terminar el programa");
    }

    private static int obtenerOpcion(Scanner scanner) {
        System.out.print("Seleccione una opción: ");
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Limpiar el buffer del scanner
            return -1;
        }
    }

    /**
     * Pédimos al usuario los campos de Articulo para añadirlos a listaArticulos.
     */
    private static void agregarArticulo(Scanner scanner) {
        System.out.println("Ingrese los datos del nuevo artículo:");

        int id;
        String nombre;
        String descripcion;
        int cantidad;
        float precio;

        if (listaArticulos.isEmpty()) {
            id = 1;
        } else {
            id = listaArticulos.getLast().getId() + 1;
        }

//        do {
//            System.out.print("ID del artículo: ");
//            id = scanner.nextInt();
//        } while (idExistente(id));

        scanner.nextLine();
        System.out.print("nombre del artículo: ");
        nombre = scanner.nextLine();
        System.out.print("descripción del artículo: ");
        descripcion = scanner.nextLine();
        System.out.print("cantidad del artículo: ");
        cantidad = scanner.nextInt();
        System.out.print("precio del artículo: ");
        precio = scanner.nextFloat();

        Articulo nuevoArticulo = new Articulo(id, nombre, descripcion, cantidad, precio);
        listaArticulos.add(nuevoArticulo);

        System.out.println("Artículo añadido correctamente.");
    }

    /**
     * Recorremos la listaArticulos para comprobar si existe algún artículo con
     * el id pasado como parámetro.
     */
    private static boolean idExistente(int id) {
        for (Articulo articulo : listaArticulos) {
            if (articulo.getId() == id) {
                System.out.println("Ya existe un artículo con ese ID. Inténtalo de nuevo.");
                return true;
            }
        }
        return false;
    }

    /**
     * Recorremos la listaArticulos hasta encontrar el artículo con el id pasado
     * como parámetro para borrarlo de la lista con el método remove().
     */
    private static void borrarArticulo(Scanner scanner) {
        System.out.println("Inserte el ID del artículo que desea borrar: ");
        int idBorrar = scanner.nextInt();

        boolean encontrado = false;
        for (Articulo articulo : listaArticulos) {
            if (articulo.getId() == idBorrar) {
                listaArticulos.remove(articulo);
                System.out.println("Artículo con ID " + idBorrar + " borrado correctamente.");
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró ningún artículo con el ID " + idBorrar);
        }
    }

    /**
     * Recorremos la listaArticulos hasta encontrar el artículo con el id pasado
     * como parámetro para mostrarlo por pantalla.
     */
    private static void consultarArticulo(Scanner scanner) {
        System.out.println("Inserte el ID del artículo que desea consultar: ");
        int idConsultar = scanner.nextInt();

        boolean encontrado = false;
        for (Articulo articulo : listaArticulos) {
            if (articulo.getId() == idConsultar) {
                System.out.println(articulo);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró ningún artículo con el ID " + idConsultar);
        }
    }

    /**
     * Recorremos la listaArticulos y mostramos por pantalla cada Articulo.
     */
    private static void listarArticulos() {
        for (Articulo a : listaArticulos) {
            System.out.println(a);
        }
    }

    /**
     * Nos valemos de la biblioteca opencsv para generar un .csv insertando
     * tantos rows como artículos obtenga al recorrer la listaArticulos.
     */
    private static void exportarArticulosACSV() {
        try (CSVWriter writer = new CSVWriter(new FileWriter(NOMBRE_CSV))) {
            // Insertamos el primer row con los campos/atributos de Articulo
            String[] header = {"ID", "Nombre", "Descripción", "Stock", "Precio"};
            writer.writeNext(header);

            for (Articulo articulo : listaArticulos) {
                String[] row = {
                        String.valueOf(articulo.getId()),
                        articulo.getNombre(),
                        articulo.getDescripcion(),
                        String.valueOf(articulo.getCantidad()),
                        String.valueOf(articulo.getPrecio())
                };
                writer.writeNext(row);
            }

            System.out.println("Artículos exportados correctamente a CSV.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
