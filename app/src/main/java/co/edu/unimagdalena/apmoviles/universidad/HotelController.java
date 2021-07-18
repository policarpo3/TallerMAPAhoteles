package co.edu.unimagdalena.apmoviles.universidad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class HotelController {
    private BaseDatos bd;
    private Context c;
    public HotelController(Context c) {
        this.bd = new BaseDatos(c,1);
        this.c = c;
    }
    public void agregarHotel(Hotel e){
        try {
            SQLiteDatabase sql = bd.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put(DefBD.col_id, e.getId());//Nuevo
            valores.put(DefBD.col_estrellas, e.getEstrellas());
            valores.put(DefBD.col_nombre, e.getNombre());
            valores.put(DefBD.col_departamento, e.getDepartamento());
            valores.put(DefBD.col_ciudad, e.getCiudad());
            valores.put(DefBD.col_direccion, e.getDireccion());//nuevo
            valores.put(DefBD.col_latitud, e.getLatitud());//nuevo
            valores.put(DefBD.col_longitud, e.getLongitud());//nuevo
            long id = sql.insert(DefBD.tabla_hotel, null, valores);
            //sql.execSQL("insert into " + DefBD.tabla_est + " values (" + e.getCodigo() + "," + e.getNombre() + "," + e.getPrograma() +");");
            Toast.makeText(c, "Hotel registrado", Toast.LENGTH_LONG).show();
        }
        catch(Exception ex){
            Toast.makeText(c, "Error agregando hotel " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public boolean buscarHotel(Hotel e){
        String args[] = new String[] {"" + e.getId()};
        String[] columnas = {DefBD.col_id,DefBD.col_nombre};
        String col[] = new String[] {DefBD.col_id,DefBD.col_nombre};
        SQLiteDatabase sql = bd.getReadableDatabase();
      Cursor c = sql.query(DefBD.tabla_hotel,null,"id=?",args,null,null,null);
        if (c.getCount()>0){
            bd.close();
            return true;
        }
        else{
            bd.close();
            return false;
        }
    }

    public Cursor allHoteles(){// para el listado pero no utilizado
        try{
            SQLiteDatabase sql = bd.getReadableDatabase();
 Cursor c = sql.query(DefBD.tabla_hotel,null,null,null,null,null,null);
            return c;
        }
        catch (Exception ex){
            Toast.makeText(c, "Error consulta hoteles " + ex.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public Cursor allHoteles2(){//para el listado
        try{
            SQLiteDatabase sql = bd.getReadableDatabase();
            Cursor cur = sql.rawQuery("select id as _id , nombre, departamento, ciudad, estrellas, direccion, latitud, longitud from hotel", null);
            return cur;
        }
        catch (Exception ex){
            Toast.makeText(c, "Error consulta hoteles " + ex.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public void eliminarHotel(int id){
        try{
            SQLiteDatabase sql = bd.getReadableDatabase();
            String[] args = {""+id};
           sql.delete(DefBD.tabla_hotel,"id=?",args);
            Toast.makeText(c, "Hotel eliminado", Toast.LENGTH_LONG).show();
        }
        catch (Exception ex){
            Toast.makeText(c, "Error eliminar hoteles " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void actualizarHotel(Hotel e){
        try{
            SQLiteDatabase sql = bd.getReadableDatabase();
            String[] args = {""+e.getId()};
            ContentValues valores = new ContentValues();
            //como el id no se modifica aqui no va lo de id
            valores.put(DefBD.col_nombre, e.getNombre());
            valores.put(DefBD.col_departamento, e.getDepartamento());
            valores.put(DefBD.col_ciudad, e.getCiudad());
            valores.put(DefBD.col_estrellas, e.getEstrellas());
            valores.put(DefBD.col_direccion, e.getDireccion());//nuevo
            valores.put(DefBD.col_latitud, e.getLatitud());//nuevo
            valores.put(DefBD.col_longitud, e.getLongitud());//nuevo
            sql.update(DefBD.tabla_hotel,valores,"id=?",args);
            Toast.makeText(c, "Hotel actualizado", Toast.LENGTH_LONG).show();
        }
        catch (Exception ex){
            Toast.makeText(c, "Error actualizar hoteles " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public Cursor filtrarHotel(CharSequence filtro){
        SQLiteDatabase sql = bd.getWritableDatabase();
        String query = "SELECT id as _id, nombre, departamento, ciudad, estrellas, direccion, latitud, longitud FROM hotel "
                + "where departamento like '%" + filtro + "%' or ciudad like '%" + filtro + "%' or estrellas like '%" + filtro + "%'"
                + "ORDER BY nombre ASC";

        return  sql.rawQuery(query, null);

    }
   }


