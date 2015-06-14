package g1.aplicaciones.com.nutribio.crear;

import android.graphics.Bitmap;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.EnumMap;
import java.util.Map;

/**
 * Created by PINEDA on 25/04/2015.
 */
public class CodificadorQR {

    private static final int WHITE = 0xFFFFFFFF;
    private static final int BLACK = 0xFF000000;

    private int dimension = Integer.MIN_VALUE;
    private String contents = null;
    private BarcodeFormat format = null;
    private boolean encoded = false;

    public CodificadorQR(String data, String type, String format, int dimension) {
        this.dimension = dimension;
        encoded = encodeContents(data, type, format);
    }

    private boolean encodeContents(String data, String type, String formatString) {
        format = null;
        if (formatString != null) {
            try {
                format = BarcodeFormat.valueOf(formatString);
            } catch (IllegalArgumentException iae) {
            }
        }
        if (format == null || format == BarcodeFormat.QR_CODE) {
            this.format = BarcodeFormat.QR_CODE;
            encodeQRCodeContents(data, type);
        } else if (data != null && data.length() > 0) {
            contents = data;
        }
        return contents != null && contents.length() > 0;
    }

    private void encodeQRCodeContents(String data, String type) {
        if (type.equals(Contenido.Type.TEXT)) {
            if (data != null && data.length() > 0) {
                contents = data;
            }
        }
    }

    public Bitmap encodeAsBitmap() throws WriterException {
        if (!encoded) return null;

        Map<EncodeHintType, Object> hints = null;
        String encoding = guessAppropriateEncoding(contents);
        if (encoding != null) {
            hints = new EnumMap<>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, encoding);
        }
        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix result = writer.encode(contents, format, dimension, dimension, hints);
        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    private static String guessAppropriateEncoding(CharSequence contents) {
        for (int i = 0; i < contents.length(); i++) {
            if (contents.charAt(i) > 0xFF) { return "UTF-8"; }
        }
        return null;
    }
}