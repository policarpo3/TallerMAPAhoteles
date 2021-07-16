package co.edu.unimagdalena.apmoviles.universidad;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.Locale;

public class HotelCursorAdapter extends CursorAdapter {
    public HotelCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.fila_hotel,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtid = view.findViewById(R.id.txtid);
        TextView txtnombre = view.findViewById(R.id.txtnombre);
        TextView txtpais = view.findViewById(R.id.txtpais);
        TextView txtciudad = view.findViewById(R.id.txtciudad);
        TextView txtestrellas = view.findViewById(R.id.txtestrellas);

        String id = cursor.getString(0);
        String nombre = cursor.getString(1);
        String pais = cursor.getString(2);
        String ciudad = cursor.getString(3);
        String estrellas = cursor.getString(4);

        txtid.setText(id);
        txtnombre.setText(nombre);
        txtpais.setText(pais);
        txtciudad.setText(ciudad);
        txtestrellas.setText(estrellas);

    }
}
