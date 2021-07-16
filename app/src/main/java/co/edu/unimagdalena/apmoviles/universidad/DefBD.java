package co.edu.unimagdalena.apmoviles.universidad;


    public class DefBD {

        public static final String nameDb = "Universidad";
        public static final String tabla_hotel = "hotel";
        public static final String col_id = "id";
        public static final String col_pais = "pais";
        public static final String col_nombre = "nombre";
        public static final String col_ciudad = "ciudad";
        public static final String col_estrellas = "estrellas";

        public static final String create_tabla_est = "CREATE TABLE IF NOT EXISTS " + DefBD.tabla_hotel + " ( " +
                DefBD.col_id + " integer primary key autoincrement," +
                DefBD.col_nombre + " text," +
                DefBD.col_pais + " text," +
                DefBD.col_estrellas + " integer," +
                DefBD.col_ciudad + " text" +
                ");";


}
