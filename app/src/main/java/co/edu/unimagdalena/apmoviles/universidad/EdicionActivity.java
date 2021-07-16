package co.edu.unimagdalena.apmoviles.universidad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EdicionActivity extends AppCompatActivity {

    EditText txtid, nombre, pais, ciudad, estrellas;
    Button actualizar, eliminar, regresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion);
        Intent i = getIntent();
        String id = i.getStringExtra("id");
        String nom = i.getStringExtra("nombre");
        String pa = i.getStringExtra("pais");
        final String ciud = i.getStringExtra("ciudad");
        String estr = i.getStringExtra("estrellas");

        txtid = findViewById(R.id.edtid);
        nombre = findViewById(R.id.edtnombre);
        pais = findViewById(R.id.edtpais);
        ciudad = findViewById(R.id.edtciudad);
        estrellas = findViewById(R.id.edtestrellas);

        actualizar = findViewById(R.id.btnactualizar);
        eliminar = findViewById(R.id.btneliminar);
        regresar = findViewById(R.id.btnregresar);

        txtid.setText(id);
        txtid.setEnabled(false);
        nombre.setText(nom);
        pais.setText(pa);
        ciudad.setText(ciud);
        estrellas.setText(estr);

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
                Hotel e = new Hotel(nombre.getText().toString(),pais.getText().toString(),
                        ciudad.getText().toString(), Integer.parseInt(estrellas.getText().toString()));
                e.setId(Integer.parseInt(txtid.getText().toString()));
                HotelController ec = new HotelController(getApplication());
                ec.actualizarHotel(e);
                Intent i = new Intent(getApplicationContext(),ListadoActivity.class);
                startActivity(i);
                finish();
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