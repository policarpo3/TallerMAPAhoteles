package co.edu.unimagdalena.apmoviles.universidad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class ListadoActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    ListView listado;
    HotelController hotelController;
    SearchView filter;
    HotelCursorAdapter adapter;
    private BaseDatos bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        listado = findViewById(R.id.lstlistado);
        filter = findViewById(R.id.svfilter);
        hotelController = new HotelController(this);
        Cursor c = hotelController.allHoteles2();
        adapter = new HotelCursorAdapter(this,c,false);
        listado.setAdapter(adapter);
        listado.setTextFilterEnabled(true);
        adapter.setFilterQueryProvider(new FilterQueryProvider() {
            @Override
            public Cursor runQuery(CharSequence constraint) {
                return hotelController.filtrarHotel(constraint);
            }
        });
        filter.setOnQueryTextListener(this);
        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView txtid = view.findViewById(R.id.txtid);
                TextView nombre = view.findViewById(R.id.txtnombre);
                TextView pais = view.findViewById(R.id.txtpais);
                TextView ciudad = view.findViewById(R.id.txtciudad);
                TextView estrellas = view.findViewById(R.id.txtestrellas);
                Intent i = new Intent(getApplicationContext(), EdicionActivity.class);
                i.putExtra("id", txtid.getText().toString());
                i.putExtra("nombre", nombre.getText().toString());
                i.putExtra("pais", pais.getText().toString());
                i.putExtra("ciudad", ciudad.getText().toString());
                i.putExtra("estrellas", estrellas.getText().toString());
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.i("search", newText);
        String text = newText;
        adapter.getFilter().filter(newText);
        adapter.notifyDataSetChanged();
        return false;
    }
}
