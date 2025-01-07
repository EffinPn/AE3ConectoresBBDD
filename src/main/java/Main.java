import controller.Menu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Inicializamos el menú
        Menu menu = new Menu();

        // Ejecutamos el menú y le pasamos nuestro scáner.
        menu.menu(scan);

        // Cerramos el scáner al salir
        scan.close();

        System.out.println("Programa finalizado.");
    }
}
