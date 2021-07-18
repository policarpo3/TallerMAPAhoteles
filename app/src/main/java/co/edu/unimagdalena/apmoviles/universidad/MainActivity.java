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
    EditText ciudad, nombre, departamento, estrellas, txtid, direccion, latitud, longitud;//4 nuevos
    Button agregar, mostrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        agregar = findViewById(R.id.btnguardar);
        mostrar = findViewById(R.id.btnlistado);

        txtid = findViewById(R.id.edtcodigo);//nuevo
        ciudad = findViewById(R.id.edtciudad);
        nombre = findViewById(R.id.edtnombre);
        estrellas = findViewById(R.id.edtestrellas);
        departamento = findViewById(R.id.edtdepartamento);
        direccion = findViewById(R.id.edtdireccion);//nuevo
        latitud = findViewById(R.id.edtlatitud);//nuevo
        longitud = findViewById(R.id.edtlongitud);//nuevo

        agregar.setOnClickListener(this);
        mostrar.setOnClickListener(this);
        ec = new HotelController(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnguardar: //agregarHotel(e);
               if(TextUtils.isEmpty(txtid.getText().toString()) || TextUtils.isEmpty(ciudad.getText().toString()) || TextUtils.isEmpty(nombre.getText().toString()) ||
                  TextUtils.isEmpty(departamento.getText().toString()) || TextUtils.isEmpty(estrellas.getText().toString()) ||
                  TextUtils.isEmpty(direccion.getText().toString()) || TextUtils.isEmpty(latitud.getText().toString()) || TextUtils.isEmpty(longitud.getText().toString()) ){
                    //Si ninguno de los campos estaban vacios
                    Toast.makeText(this,"No pueden haber casillas vacias", Toast.LENGTH_LONG).show();

               }else{
                    e = new Hotel(Integer.parseInt(txtid.getText().toString()), nombre.getText().toString(), departamento.getText().toString(), ciudad.getText().toString(),
                            Integer.parseInt(estrellas.getText().toString()),
                            direccion.getText().toString(), Integer.parseInt(latitud.getText().toString()), Integer.parseInt(longitud.getText().toString()) );//3 nuevos + el id
                    //Crea un nuevo hotel e, pero sin ingresarlo aun a la base de datos
                    if (ec.buscarHotel(e)){//si no encontro el hotel, es decir sino estaba repetido, como busca por id
                        Toast.makeText(this,"Código ya existe", Toast.LENGTH_LONG).show();
                    }
                    else{            //si quedo listo para crear
                        ec.agregarHotel(e);//Se agrega el nuevo hotel
                        //y luego se reinician los editText para que se muestren limpios de nuevo
                        txtid.setText("");//nuevo
                        nombre.setText("");
                        departamento.setText("");
                        ciudad.setText("");
                        estrellas.setText("");
                        direccion.setText("");//nuevo
                        latitud.setText("");//nuevo
                        longitud.setText("");//nuevo
                    }
               }
                break;
            case R.id.btnlistado:
                Intent i = new Intent(this, ListadoActivity.class);
                startActivity(i);
                break;
            case R.id.btnvermapa:
                Intent j = new Intent(this, ListadoActivity.class);// cambiar aqui para moverse
                startActivity(j);
                break;
        }
    }


}
