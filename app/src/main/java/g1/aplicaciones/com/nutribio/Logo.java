package g1.aplicaciones.com.nutribio;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;


public class Logo extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        Thread portada = new Thread() {
            public void run() {
                try {
                    int tiempo = 0;
                    while (tiempo < 3000) {
                        sleep(100);
                        tiempo = tiempo + 100;
                    }
                    Intent i = new Intent("g1.aplicaciones.com.nutribio.Menu");
                    startActivity(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    finish();
                }
            }
        };
        portada.start();
    }

}
