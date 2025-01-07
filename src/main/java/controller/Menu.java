package controller;

import dao.CocheDAO;
import dao.CochePasajeroDAO;
import dao.PasajeroDAO;
import model.Coche;
import model.Pasajero;

import java.util.Scanner;


public class Menu {

    private CocheDAO cocheDAO;
    private PasajeroDAO pasajeroDAO;

    public Menu() {
        cocheDAO = new CocheDAO();
        pasajeroDAO = new PasajeroDAO();
    }

    public void menu(Scanner scan) {

        scan = new Scanner(System.in);
        int opcion = 0;
        System.out.println("Bienvenido");

        while (opcion != 13) {

            System.out.println("Elige una función:");
            System.out.println("1.Añadir coche");
            System.out.println("2.Borrar coche");
            System.out.println("3.Consultar coche");
            System.out.println("4.Modificar coche");
            System.out.println("5.Listar coches");
            System.out.println("6.Añadir pasajero");
            System.out.println("7.Borrar pasajero");
            System.out.println("8.Consultar pasajero");
            System.out.println("9.Listar pasajeros");
            System.out.println("10.Añadir pasajero a coche");
            System.out.println("11.Eliminar pasajero de coche");
            System.out.println("12.Listar pasajeros de un coche");
            System.out.println("13.Salir");

            opcion = Integer.valueOf(scan.nextLine());

            switch (opcion) {
                case 1:
                    System.out.println("Introduce el color del coche:");
                    String color = scan.nextLine();
                    System.out.println("Introduce el año del coche:");
                    int anno = Integer.valueOf(scan.nextLine());
                    cocheDAO.addCoche(new Coche(0, anno, color));
                    System.out.println("Coche añadido.");
                    break;

                case 2:
                    System.out.println("Introduce el ID del coche a borrar:");
                    int idCocheBorrar = Integer.valueOf(scan.nextLine());
                    cocheDAO.borrarCoche(idCocheBorrar);
                    System.out.println("Coche borrado.");
                    break;

                case 3:
                    System.out.println("Introduce el ID del coche a consultar:");
                    int idCocheConsultar = Integer.valueOf(scan.nextLine());
                    System.out.println(cocheDAO.consultarCoche(idCocheConsultar));
                    break;

                case 4:
                    System.out.println("Introduce el ID del coche a modificar:");
                    int idCocheModificar = Integer.valueOf(scan.nextLine());
                    System.out.println("Introduce el nuevo color:");
                    String nuevoColor = scan.nextLine();
                    System.out.println("Introduce el nuevo año:");
                    int nuevoAnno = Integer.valueOf(scan.nextLine());
                    cocheDAO.updateCoche(idCocheModificar, nuevoColor, nuevoAnno);
                    System.out.println("Coche modificado.");
                    break;

                case 5:
                    System.out.println("Listado de coches:");
                    cocheDAO.consultarTodos().forEach(System.out::println);
                    break;

                case 6:
                    System.out.println("Introduce el nombre del pasajero:");
                    String nombre = scan.nextLine();
                    System.out.println("Introduce la edad del pasajero:");
                    int edad = Integer.valueOf(scan.nextLine());
                    System.out.println("Introduce el peso del pasajero:");
                    float peso = Float.valueOf(scan.nextLine());
                    pasajeroDAO.addPasajero(new Pasajero(0, edad, nombre, peso));
                    System.out.println("Pasajero añadido.");
                    break;

                case 7:
                    System.out.println("Introduce el ID del pasajero a borrar:");
                    int idPasajeroBorrar = Integer.valueOf(scan.nextLine());
                    pasajeroDAO.borrarPasajero(idPasajeroBorrar);
                    System.out.println("Pasajero borrado.");
                    break;

                case 8:
                    System.out.println("Introduce el ID del pasajero a consultar:");
                    int idPasajeroConsultar = Integer.valueOf(scan.nextLine());
                    System.out.println(pasajeroDAO.consultarPasajero(idPasajeroConsultar));
                    break;

                case 9:
                    System.out.println("Listado de pasajeros:");
                    pasajeroDAO.consultarTodos().forEach(System.out::println);
                    break;

                case 10:
                    System.out.println("Introduce el ID del coche:");
                    int idCocheParaPasajero = Integer.valueOf(scan.nextLine());
                    System.out.println("Introduce el ID del pasajero:");
                    int idPasajeroParaCoche = Integer.valueOf(scan.nextLine());
                    new CochePasajeroDAO().addPasajeroACoche(idPasajeroParaCoche, idCocheParaPasajero);
                    System.out.println("Pasajero añadido al coche.");
                    break;

                case 11:
                    System.out.println("Introduce el ID del coche:");
                    int idCocheEliminarPasajero = Integer.valueOf(scan.nextLine());
                    System.out.println("Introduce el ID del pasajero a eliminar:");
                    int idPasajeroEliminarCoche = Integer.valueOf(scan.nextLine());
                    new CochePasajeroDAO().borrarPasajeroDeCoche(idPasajeroEliminarCoche, idCocheEliminarPasajero);
                    System.out.println("Pasajero eliminado del coche.");
                    break;

                case 12:
                    System.out.println("Introduce el ID del coche:");
                    int idCochePasajeros = Integer.valueOf(scan.nextLine());
                    System.out.println("Pasajeros asociados al coche:");
                    CochePasajeroDAO cpDao = new CochePasajeroDAO();
                    System.out.println(cpDao.consultarTodos(idCochePasajeros));
                    break;

                case 13:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

}
