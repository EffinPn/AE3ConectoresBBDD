package database;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@AllArgsConstructor

public class ConectaDB {

    private static Connection conexion;

public Connection getConexion() {
    
    if(conexion == null){
        creaConexion();
        System.out.println("Exito");
        return conexion;
    } else {
        return conexion;
    }

}

    private void creaConexion() {

    String url = "jdbc:mysql://localhost:3306/concesionario";

    try{

        conexion = DriverManager.getConnection(url, "root", "");
    } catch (SQLException e) {
        System.out.println("Error:" + e.getMessage());
    }
    }

}
