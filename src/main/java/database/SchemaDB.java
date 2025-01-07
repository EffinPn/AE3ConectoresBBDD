package database;

public interface SchemaDB {

    String DB_NAME = "concesionario";

    String TAB_CAR = "coche";
    String TAB_PSJRS = "pasajero";
    String TAB_CAR_PSJRS = "coche_pasajero";

    String CAR_ID = "Id";
    String CAR_COLOR = "Color";
    String CAR_ANNO = "Anno";

    String PSJRS_ID = "id";
    String PSJRS_NAME = "nombre";
    String PSJRS_EDAD = "edad";
    String PSJRS_PESO = "peso";

    String CAR_PSJRS_IDCAR = "coche_id";
    String CAR_PSJRS_IDPAS = "pasajero_id";
}
