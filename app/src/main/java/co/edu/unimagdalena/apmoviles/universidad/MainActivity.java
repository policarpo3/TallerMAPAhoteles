package co.edu.unimagdalena.apmoviles.universidad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Hotel e;
    HotelController ec;
    EditText ciudad, nombre, pais, estrellas;
    Button agregar, mostrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        agregar = findViewById(R.id.btnguardar);
        mostrar = findViewById(R.id.btnlistado);
        ciudad = findViewById(R.id.edtciudad);
        nombre = findViewById(R.id.edtnombre);
        estrellas = findViewById(R.id.edtestrellas);
        pais = findViewById(R.id.edtpais);
        agregar.setOnClickListener(this);
        mostrar.setOnClickListener(this);
        ec = new HotelController(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnguardar: //agregarEstudiante(e);
               if(TextUtils.isEmpty(ciudad.getText().toString()) || TextUtils.isEmpty(nombre.getText().toString()) ||
               TextUtils.isEmpty(pais.getText().toString()) || TextUtils.isEmpty(estrellas.getText().toString())){
                  Toast.makeText(this,"Los datos no pueden ser vacíos", Toast.LENGTH_LONG).show();
               }
                else{
                    e = new Hotel(nombre.getText().toString(), pais.getText().toString(), ciudad.getText().toString(),
                            Integer.parseInt(estrellas.getText().toString()));
                    if (ec.buscarHotel(e)){
                        Toast.makeText(this,"Código ya existe", Toast.LENGTH_LONG).show();
                    }
                    else{
                        ec.agregarHotel(e);
                        nombre.setText("");
                        pais.setText("");
                        ciudad.setText("");
                        estrellas.setText("");
                    }
               }
                break;
            case R.id.btnlistado:
               /* Cursor c = ec.allEstudiantes();
                String cadena = "";
                while (c.moveToNext()){
                    cadena = cadena + c.getString(1) + ",";
                }
                Toast.makeText(this,cadena,Toast.LENGTH_LONG).show();*/
                Intent i = new Intent(this, ListadoActivity.class);
                startActivity(i);
                break;
        }
    }


}
