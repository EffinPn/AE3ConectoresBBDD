package dao;

import database.ConectaDB;
import database.SchemaDB;
import model.Coche;
import java.sql.*;
import java.util.ArrayList;

public class CocheDAO {

    private Connection con;
    private PreparedStatement ps;

    public CocheDAO() {

        this.con = new ConectaDB().getConexion();
    }

    public void addCoche(Coche coche) {

        try {

            String query = String.format("INSERT INTO %s (%s, %s, %s) VALUES (?,?,?)",
                    SchemaDB.TAB_CAR, SchemaDB.CAR_ID, SchemaDB.CAR_COLOR, SchemaDB.CAR_ANNO);
            this.ps = con.prepareStatement(query);
            ps.setInt(1, coche.getId());
            ps.setString(2, coche.getColor());
            ps.setInt(3, coche.getAnno());
            ps.execute();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void borrarCoche(int id) {
        try {

            String query = String.format("DELETE FROM %s WHERE %s = ?",
                    SchemaDB.TAB_CAR, SchemaDB.CAR_ID);
            this.ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();

            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCoche(int id, String nuevoColor, int nuevoAnno) {
        try {

            String query = String.format("UPDATE %s SET %s = ?, %s = ? WHERE %s = ?",
                    SchemaDB.TAB_CAR, SchemaDB.CAR_COLOR, SchemaDB.CAR_ANNO, SchemaDB.CAR_ID);
            this.ps = con.prepareStatement(query);
            ps.setString(1, nuevoColor);
            ps.setInt(2, nuevoAnno);
            ps.setInt(3, id);
            ps.executeUpdate();

            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Coche consultarCoche(int id) {

        try {
            String query = String.format("SELECT * FROM %s WHERE %s = ?",
                    SchemaDB.TAB_CAR, SchemaDB.CAR_ID);
            this.ps = con.prepareStatement(query);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Coche coche = new Coche();
                coche.setId(rs.getInt(SchemaDB.CAR_ID));
                coche.setColor(rs.getString(SchemaDB.CAR_COLOR));
                coche.setAnno(rs.getInt(SchemaDB.CAR_ANNO));
                return coche;
            }

            ps.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public ArrayList<Coche> consultarTodos() {

        ArrayList<Coche> coches = new ArrayList<>();

        try {
            String query = String.format("SELECT * FROM %s",
                    SchemaDB.TAB_CAR);
            this.ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Coche coche = new Coche();
                coche.setId(rs.getInt(SchemaDB.CAR_ID));
                coche.setColor(rs.getString(SchemaDB.CAR_COLOR));
                coche.setAnno(rs.getInt(SchemaDB.CAR_ANNO));
                coches.add(coche);
            }

            ps.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return coches;
    }
}