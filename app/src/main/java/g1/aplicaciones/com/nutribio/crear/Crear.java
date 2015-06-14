package g1.aplicaciones.com.nutribio.crear;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import g1.aplicaciones.com.nutribio.DataBaseManager;
import g1.aplicaciones.com.nutribio.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import java.io.ByteArrayOutputStream;

/**
 * Created by PINEDA on 25/04/2015.
 */
public class Crear extends ActionBarActivity implements View.OnClickListener {

    private String LOG_TAG = "GenerateQRCode";
    private DataBaseManager envio;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                EditText qrInput = (EditText) findViewById(R.id.qrInput);
                String qrInputText = qrInput.getText().toString();
                Log.v(LOG_TAG, qrInputText);
                WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
                Display display = manager.getDefaultDisplay();
                Point point = new Point();
                display.getSize(point);
                int width = point.x;
                int height = point.y;
                int smallerDimension = width < height ? width : height;
                smallerDimension = smallerDimension * 3 / 4;
                CodificadorQR qrCodeEncoder = new CodificadorQR(qrInputText,
                        Contenido.Type.TEXT, BarcodeFormat.QR_CODE.toString(),
                        smallerDimension);
                try {
                    bitmap = qrCodeEncoder.encodeAsBitmap();
                    ImageView myImage = (ImageView) findViewById(R.id.imageView1);
                    myImage.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.button2:
                envio=new DataBaseManager(this);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] foto = baos.toByteArray();
                EditText titulo=(EditText)findViewById(R.id.ETtitulo_receta);
                envio.insertarReceta(titulo.getText().toString(),foto);
                //Para sacar de la base
                //ByteArrayInputStream imageStream = new ByteArrayInputStream(photo);
                //Bitmap theImage= BitmapFactory.decodeStream(imageStream);
                break;
        }
    }

}

