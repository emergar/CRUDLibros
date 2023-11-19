package com.example.crudlibro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.crudlibro.controladores.LibroBD;
import com.example.crudlibro.modelos.Libro;

import java.util.ArrayList;
import java.util.List;

public class ListadoLibrosActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> nombresLibros;
    ArrayList<Integer> idLibros;
    LibroBD libroBD;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_libros);
        init();
    }

    private void init(){
        context = this.getApplicationContext();
        libroBD = new LibroBD(context, "LibrosBD.db", null, 1);
        listView = findViewById(R.id.listaLibros);
        llenarListView();
    }

    private void llenarListView() {
        nombresLibros = new ArrayList<String>();
        idLibros = new ArrayList<Integer>();

        List<Libro> listaLibros = libroBD.lista();
        for(int i=0; i<listaLibros.size(); i++){
            Libro l = listaLibros.get(i);
            nombresLibros.add( l.getTitulo() );
            idLibros.add( l.getId() );
        }
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(
                        context,
                        android.R.layout.simple_list_item_1,
                        nombresLibros
                );
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Libro libro = libroBD.elemento( idLibros.get(i));
                    Bundle bolsa = new Bundle();
                    bolsa.putInt( "id", libro.getId() );
                    bolsa.putString( "titulo", libro.getTitulo() );
                    bolsa.putString( "subtitulo", libro.getSubtitulo() );
                    bolsa.putString( "autor", libro.getAutor() );
                    bolsa.putString( "isbn", libro.getIsbn() );
                    bolsa.putInt( "anio_publicacion", libro.getAnioPublicacion() );
                    bolsa.putDouble( "precio", libro.getPrecio() );

                    Intent intent = new Intent( context, GestionarLibroActivity.class  );
                    intent.putExtras( bolsa );
                    startActivity( intent );
                }
            }
        );
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        init();
    }
}