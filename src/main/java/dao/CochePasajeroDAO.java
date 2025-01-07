package dao;


import database.ConectaDB;
import database.SchemaDB;
import model.Coche;
import model.Pasajero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CochePasajeroDAO {

    private Connection con;
    private PreparedStatement ps;

    public CochePasajeroDAO() {

        this.con = new ConectaDB().getConexion();
    }

    public void addPasajeroACoche(int idPasajero, int idCoche) {

        try {

            String query = String.format("INSERT INTO %s (%s, %s) VALUES (?,?)",
                    SchemaDB.TAB_CAR_PSJRS, SchemaDB.CAR_PSJRS_IDCAR, SchemaDB.CAR_PSJRS_IDPAS);
            this.ps = con.prepareStatement(query);
            ps.setInt(1, idCoche);
            ps.setInt(2, idPasajero);
            ps.execute();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void borrarPasajeroDeCoche(int idPasajero, int idCoche) {

        try {

            String query = String.format("DELETE FROM %s WHERE %s = ? AND %s = ?",
                    SchemaDB.TAB_CAR_PSJRS, SchemaDB.CAR_PSJRS_IDPAS, SchemaDB.CAR_PSJRS_IDCAR);
            this.ps = con.prepareStatement(query);
            ps.setInt(1, idPasajero);
            ps.setInt(2, idCoche);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Pasajero> consultarTodos(int cocheId) {

        ArrayList<Pasajero> pasajeros = new ArrayList<>();

        try {
            // Hacemos un JOIN para obtener los datos de la tabla 'pasajero'
            String query = String.format("SELECT p.%s, p.%s, p.%s, p.%s " +
                            "FROM %s p JOIN %s cp ON p.%s = cp.%s WHERE cp.%s = ?",
                    SchemaDB.PSJRS_ID, SchemaDB.PSJRS_NAME, SchemaDB.PSJRS_EDAD, SchemaDB.PSJRS_PESO,
                    SchemaDB.TAB_PSJRS, SchemaDB.TAB_CAR_PSJRS,
                    SchemaDB.PSJRS_ID, SchemaDB.CAR_PSJRS_IDPAS, SchemaDB.CAR_PSJRS_IDCAR);

            this.ps = con.prepareStatement(query);
            ps.setInt(1, cocheId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pasajero p = new Pasajero();
                p.setId(rs.getInt(SchemaDB.PSJRS_ID));
                p.setNombre(rs.getString(SchemaDB.PSJRS_NAME));
                p.setEdad(rs.getInt(SchemaDB.PSJRS_EDAD));
                p.setPeso(rs.getFloat(SchemaDB.PSJRS_PESO));
                pasajeros.add(p);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pasajeros;
    }



}
