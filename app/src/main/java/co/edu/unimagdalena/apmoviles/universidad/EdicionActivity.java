package co.edu.unimagdalena.apmoviles.universidad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EdicionActivity extends AppCompatActivity {

    EditText txtid, nombre, departamento, ciudad, estrellas, direccion, latitud, longitud;//3 nuevos
    TextView mensajeError;
    Button actualizar, eliminar, regresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion);
        Intent i = getIntent();
        //se traen los datos del hotel seleccionado, es en el listado donde se trae de la base de datos
        String id = i.getStringExtra("id");
        String nom = i.getStringExtra("nombre");
        String dep = i.getStringExtra("departamento");
        final String ciud = i.getStringExtra("ciudad");
        String estr = i.getStringExtra("estrellas");
        String direc = i.getStringExtra("direccion");//nuevo
        String lati = i.getStringExtra("latitud");//nuevo
        String longi = i.getStringExtra("longitud");//nuevo

        //se asignan variables a los editText para trabajar con ellos
        txtid = findViewById(R.id.edtid);
        nombre = findViewById(R.id.edtnombre);
        departamento = findViewById(R.id.edtdepartamento);
        ciudad = findViewById(R.id.edtciudad);
        estrellas = findViewById(R.id.edtestrellas);
        direccion = findViewById(R.id.edtdireccion2);//nuevo
        latitud = findViewById(R.id.edtlatitud2);//nuevo
        longitud = findViewById(R.id.edtlongitud2);//nuevo

        actualizar = findViewById(R.id.btnactualizar);
        eliminar = findViewById(R.id.btneliminar);
        regresar = findViewById(R.id.btnregresar);

        //Se le asignan a los editText los datos extraidos del intent que a su vez es de la base de datos
        txtid.setText(id);
        //la sgt linea es para que no se pueda editar el codigo, sino entonces quitar esa linea pero al poner editar codigo entonces habria que comprobar si esta repetido
        txtid.setEnabled(false);
        nombre.setText(nom);
        departamento.setText(dep);
        ciudad.setText(ciud);
        estrellas.setText(estr);
        direccion.setText(direc);//nuevo
        latitud.setText(lati);//nuevo
        longitud.setText(longi);//nuevo

        //Añado varibale para mostrar error cuando los datos esten vacios
        mensajeError = findViewById(R.id.textViewMensajeError);

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HotelController ec = new HotelController(getApplication());
                ec.eliminarHotel(Integer.parseInt(txtid.getText().toString()));
                Intent i = new Intent(getApplicationContext(),ListadoActivity.class);
                startActivity(i);
                finish();
            }
        });
        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mensajeError.setText("");//por si acaso pero no dberia darse el caso
                if(TextUtils.isEmpty(txtid.getText().toString()) || TextUtils.isEmpty(ciudad.getText().toString()) || TextUtils.isEmpty(nombre.getText().toString()) ||
                        TextUtils.isEmpty(departamento.getText().toString()) || TextUtils.isEmpty(estrellas.getText().toString()) ||
                        TextUtils.isEmpty(direccion.getText().toString()) || TextUtils.isEmpty(latitud.getText().toString()) || TextUtils.isEmpty(longitud.getText().toString()) ){
                    //Si ninguno de los campos estaban vacios
                    //Toast.makeText(this,"No pueden haber casillas vacias", Toast.LENGTH_LONG).show();
                    mensajeError.setText("No pueden haber casillas vacias");
                    //Metodo de ocultar teclado cuando hay una view
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    //Fin ocultar teclado cuando hay una view
                }else {
                    Hotel e = new Hotel(Integer.parseInt(txtid.getText().toString()), nombre.getText().toString(), departamento.getText().toString(),
                            ciudad.getText().toString(), Integer.parseInt(estrellas.getText().toString()),
                            direccion.getText().toString(), Integer.parseInt(latitud.getText().toString()), Integer.parseInt(longitud.getText().toString()));
                    //la sgt linea solo va si en el creador de hotel no lleva el parametro ID
                    //e.setId(Integer.parseInt(txtid.getText().toString()));
                    HotelController ec = new HotelController(getApplication());
                    ec.actualizarHotel(e);
                    Intent i = new Intent(getApplicationContext(), ListadoActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ListadoActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}