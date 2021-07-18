package co.edu.unimagdalena.apmoviles.universidad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

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
        Cursor c = hotelController.allHoteles2(); //en esta linea es donde se traen los datos de la base de datos
        adapter = new HotelCursorAdapter(this,c,false);//se pasan los datos por el adaptador
        listado.setAdapter(adapter);//se ponen los datos en la lista
        listado.setTextFilterEnabled(true);
        adapter.setFilterQueryProvider(new FilterQueryProvider() {
            @Override
            public Cursor runQuery(CharSequence constraint) {
                return hotelController.filtrarHotel(constraint);
            }
        });
        filter.setOnQueryTextListener(this);// para que funcione el buscador, pero asi se desactive sigue seleccionado

        //ocultar teclado
        //Termina de ocultar teclado
        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView txtid = view.findViewById(R.id.txtid);
                TextView nombre = view.findViewById(R.id.txtnombre);
                TextView departamento = view.findViewById(R.id.txtdepartamento);
                TextView ciudad = view.findViewById(R.id.txtciudad);
                TextView estrellas = view.findViewById(R.id.txtestrellas);
                TextView direccion = view.findViewById(R.id.txtdireccion);//nuevo
                TextView latitud = view.findViewById(R.id.txtlatitud);//nuevo
                TextView longitud = view.findViewById(R.id.txtlongitud);//nuevo
                //ahora viene instancia para mandar los datos para editar, donde alla se reciben y se editan
                Intent i = new Intent(getApplicationContext(), EdicionActivity.class);
                i.putExtra("id", txtid.getText().toString());
                i.putExtra("nombre", nombre.getText().toString());
                i.putExtra("departamento", departamento.getText().toString());
                i.putExtra("ciudad", ciudad.getText().toString());
                i.putExtra("estrellas", estrellas.getText().toString());
                i.putExtra("direccion", direccion.getText().toString());//nuevo
                i.putExtra("latitud", latitud.getText().toString());//nuevo
                i.putExtra("longitud", longitud.getText().toString());//nuevo
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
