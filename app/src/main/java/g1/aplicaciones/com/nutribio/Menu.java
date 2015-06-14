package g1.aplicaciones.com.nutribio;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by PINEDA on 23/05/2015.
 */
public class Menu extends ActionBarActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button lector=(Button)findViewById(R.id.BTlector);
        Button crear=(Button)findViewById(R.id.BTcrear);
        Button historial=(Button)findViewById(R.id.BThistorial);
        Button consejos=(Button)findViewById(R.id.BTconsejos);
        Button facebook=(Button)findViewById(R.id.BTfacebook);
        Button informacion=(Button)findViewById(R.id.BTinformacion);
        lector.setOnClickListener(this);
        crear.setOnClickListener(this);
        historial.setOnClickListener(this);
        consejos.setOnClickListener(this);
        facebook.setOnClickListener(this);
        informacion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch(v.getId()){
            case R.id.BTlector:
                i= new Intent("android.intent.action.MAIN");
                startActivity(i);
                break;
            case R.id.BTcrear:
                i= new Intent("g1.aplicaciones.com.nutribio.crear.Crear");
                startActivity(i);
                break;
            case R.id.BThistorial:
                i= new Intent("g1.aplicaciones.com.nutribio.historial.Consumo");
                startActivity(i);
                break;
            case R.id.BTconsejos:
                i= new Intent("g1.aplicaciones.com.nutribio.Consejos");
                startActivity(i);
                break;
            case R.id.BTfacebook:
                i= new Intent("android.intent.action.MAIN");
                startActivity(i);
                break;
            case R.id.BTinformacion:
                i= new Intent("g1.aplicaciones.com.nutribio.Informacion");
                startActivity(i);
                break;
        }
    }
}

