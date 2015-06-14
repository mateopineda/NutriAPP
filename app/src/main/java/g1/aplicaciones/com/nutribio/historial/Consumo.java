package g1.aplicaciones.com.nutribio.historial;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import g1.aplicaciones.com.nutribio.DataBaseManager;
import g1.aplicaciones.com.nutribio.R;


/**
 * Created by PINEDA on 24/05/2015.
 */
public class Consumo extends ActionBarActivity implements View.OnClickListener{


    private DataBaseManager manager;
    private Cursor cursor;
    private ListView lista;
    private SimpleCursorAdapter adapter;
    private EditText consulta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumo);
        manager=new DataBaseManager(this);
        lista= (ListView) findViewById(R.id.ListViewDos);
        manager.insertarConsejo("Mejora tu energia","DEPORTE","Trotar una hora al dia");
        String[]from=new String[]{manager.CN_NAME,manager.CN_DESCRIPTION};
        int[] to=new int[]{android.R.id.text1,android.R.id.text2};
        cursor=manager.cargarCursorConsejos();
        adapter=new SimpleCursorAdapter(this,android.R.layout.two_line_list_item,cursor,from,to,0);
        lista.setAdapter(adapter);
        Button peso = (Button) findViewById(R.id.BTpeso);
        Button productos = (Button) findViewById(R.id.BTproductos);
        Button consumo = (Button) findViewById(R.id.BTbuscarconsumo);
        consulta= (EditText) findViewById(R.id.ETbuscarconsumo);
        peso.setOnClickListener(this);
        productos.setOnClickListener(this);
        consumo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.BTpeso:
                i= new Intent( "g1.aplicaciones.com.nutribio.historial.Peso");
                startActivity(i);
                break;

            case R.id.BTproductos:
                i= new Intent( "g1.aplicaciones.com.nutribio.historial.Productos");
                startActivity(i);
                break;
            case R.id.BTbuscarconsumo:
                Cursor c=manager.buscarConsejo(consulta.getText().toString().toUpperCase());
                adapter.changeCursor(c);
                break;
        }
    }
}
