package dao;

import database.ConectaDB;
import database.SchemaDB;
import model.Pasajero;
import java.sql.*;
import java.util.ArrayList;

public class PasajeroDAO {

    private Connection con;
    private PreparedStatement ps;

    public PasajeroDAO() {
        this.con = new ConectaDB().getConexion();
    }

    public void addPasajero(Pasajero pasajero) {
        try {
            String query = String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES (?,?,?,?)",
                    SchemaDB.TAB_PSJRS, SchemaDB.PSJRS_ID, SchemaDB.PSJRS_NAME, SchemaDB.PSJRS_EDAD, SchemaDB.PSJRS_PESO);
            this.ps = con.prepareStatement(query);
            ps.setInt(1, pasajero.getId());
            ps.setString(2, pasajero.getNombre());
            ps.setInt(3, pasajero.getEdad());
            ps.setDouble(4, pasajero.getPeso());
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void borrarPasajero(int id) {
        try {
            String query = String.format("DELETE FROM %s WHERE %s = ?",
                    SchemaDB.TAB_PSJRS, SchemaDB.PSJRS_ID);
            this.ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePasajero(int id, String nuevoNombre, int nuevaEdad, double nuevoPeso) {
        try {
            String query = String.format("UPDATE %s SET %s = ?, %s = ?, %s = ? WHERE %s = ?",
                    SchemaDB.TAB_PSJRS, SchemaDB.PSJRS_NAME, SchemaDB.PSJRS_EDAD, SchemaDB.PSJRS_PESO, SchemaDB.PSJRS_ID);
            this.ps = con.prepareStatement(query);
            ps.setString(1, nuevoNombre);
            ps.setInt(2, nuevaEdad);
            ps.setDouble(3, nuevoPeso);
            ps.setInt(4, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Pasajero consultarPasajero(int id) {
        try {
            String query = String.format("SELECT * FROM %s WHERE %s = ?",
                    SchemaDB.TAB_PSJRS, SchemaDB.PSJRS_ID);
            this.ps = con.prepareStatement(query);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Pasajero pasajero = new Pasajero();
                pasajero.setId(rs.getInt(SchemaDB.PSJRS_ID));
                pasajero.setNombre(rs.getString(SchemaDB.PSJRS_NAME));
                pasajero.setEdad(rs.getInt(SchemaDB.PSJRS_EDAD));
                pasajero.setPeso(rs.getFloat(SchemaDB.PSJRS_PESO));
                return pasajero;
            }

            ps.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public ArrayList<Pasajero> consultarTodos() {
        ArrayList<Pasajero> pasajeros = new ArrayList<>();

        try {
            String query = String.format("SELECT * FROM %s",
                    SchemaDB.TAB_PSJRS);
            this.ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pasajero pasajero = new Pasajero();
                pasajero.setId(rs.getInt(SchemaDB.PSJRS_ID));
                pasajero.setNombre(rs.getString(SchemaDB.PSJRS_NAME));
                pasajero.setEdad(rs.getInt(SchemaDB.PSJRS_EDAD));
                pasajero.setPeso(rs.getFloat(SchemaDB.PSJRS_PESO));
                pasajeros.add(pasajero);
            }

            ps.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pasajeros;
    }
}
