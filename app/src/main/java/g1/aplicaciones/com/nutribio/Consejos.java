package g1.aplicaciones.com.nutribio;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by PINEDA on 23/05/2015.
 */
public class Consejos extends ActionBarActivity implements View.OnClickListener{

    private DataBaseManager manager;
    private Cursor cursor;
    private ListView lista;
    private SimpleCursorAdapter adapter;
    private EditText consejo;
    private Button buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consejos);
        manager=new DataBaseManager(this);
        lista= (ListView) findViewById(R.id.ListView);
        consejo= (EditText) findViewById(R.id.ETbuscarconsejo);
        buscar= (Button) findViewById(R.id.BTbuscarconsejo);
        buscar.setOnClickListener(this);
        manager.insertarConsejo("Mejora tu energia","DEPORTE","Trotar una hora al dia");
        String[]from=new String[]{manager.CN_NAME,manager.CN_DESCRIPTION};
        int[] to=new int[]{android.R.id.text1,android.R.id.text2};
        cursor=manager.cargarCursorConsejos();
        adapter=new SimpleCursorAdapter(this,android.R.layout.two_line_list_item,cursor,from,to,0);
        lista.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
    if(view.getId()==R.id.BTbuscarconsejo){
    Cursor c=manager.buscarConsejo(consejo.getText().toString().toUpperCase());
    adapter.changeCursor(c);
    }
    }
}

