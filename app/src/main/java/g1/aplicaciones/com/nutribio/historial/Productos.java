package g1.aplicaciones.com.nutribio.historial;

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
public class Productos extends ActionBarActivity implements View.OnClickListener {

    private DataBaseManager manager;
    private Cursor cursor;
    private ListView lista;
    private SimpleCursorAdapter adapter;
    private EditText producto;
    private Button buscar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        manager=new DataBaseManager(this);
        lista= (ListView) findViewById(R.id.ListViewTres);
        producto= (EditText) findViewById(R.id.ETbuscarproducto);
        buscar= (Button) findViewById(R.id.BTbuscarproducto);
        buscar.setOnClickListener(this);
        manager.insertarProducto("0123456789","Manzana","80");
        String[]from=new String[]{manager.PR_NAME,manager.PR_CALORIES};
        int[] to=new int[]{android.R.id.text1,android.R.id.text2};
        cursor=manager.cargarCursorProductos();
        adapter=new SimpleCursorAdapter(this,android.R.layout.two_line_list_item,cursor,from,to,0);
        lista.setAdapter(adapter);
    }
    @Override
    public void onClick(View view) {
    if(view.getId()==R.id.BTbuscarproducto){
    Cursor c=manager.buscarProducto(producto.getText().toString().toUpperCase());
    adapter.changeCursor(c);
    }
    }
}
