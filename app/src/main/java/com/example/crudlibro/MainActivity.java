package com.example.crudlibro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Context context;
    Button btnListar, btnRegistrar, btnBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        context = getApplicationContext();
        btnRegistrar = findViewById(R.id.btnregistrar);
        btnBuscar = findViewById(R.id.btnbuscar);
        btnListar = findViewById(R.id.btnlistar);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId() ){
            case R.id.btnregistrar:
                Toast.makeText(context, "Registrar", Toast.LENGTH_LONG).show();
                Intent i = new Intent(context, GestionarLibroActivity.class);
                Bundle bolsa = new Bundle();
                bolsa.putInt("id", 0);
                i.putExtras(bolsa);
                startActivity(i);
                break;
            case R.id.btnlistar:
                Toast.makeText(context, "Listar", Toast.LENGTH_LONG).show();
                Intent i2 = new Intent(context, ListadoLibrosActivity.class);
                startActivity(i2);
                break;
            case R.id.btnbuscar:
                Toast.makeText(context, "Buscar", Toast.LENGTH_LONG).show();
                Intent i3 = new Intent(context, BuscarLibroActivity.class);
                startActivity(i3);
                break;
            case R.id.btnlistarrv:
                Toast.makeText(context, "Listar2", Toast.LENGTH_LONG).show();
                Intent i4 = new Intent(context, ListadoLibros2Activity.class);
                startActivity(i4);
                break;
        }
    }
}