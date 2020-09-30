package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.onbarcode.barcode.android.AndroidColor;
import com.onbarcode.barcode.android.AndroidFont;
import com.onbarcode.barcode.android.Code93;
import com.onbarcode.barcode.android.IBarcode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 0);
        createPdf();

      /*  binding.button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    binding.barcdeImage.setImageBitmap(textToImage("1234567890123" ,250,250));
                } catch (WriterException e) {
                    e.printStackTrace();
                }

            }
        });*/
    }
    private void createPdf() {
       binding.button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                PdfDocument pdfDocument = new PdfDocument();
                Paint paint = new Paint();
                paint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
                paint.setTextSize(30.0f);
                PdfDocument.Page page = pdfDocument.startPage(new PdfDocument.PageInfo.Builder(2000, 3000, 1).create());
                Canvas canvas = page.getCanvas();
                canvas.drawText("إقرار إستلام /Receiving Avowal", 700.0f, 30.0f, paint);
                canvas.drawText("التاريخ/Date : " + currentDate + "           الوقت/Time : " + currentTime + " ", 550.0f, 70.0f, paint);
                canvas.drawText("استلمت أنا ....................................... رقم قومي .............................  مندوب (شركة هايبروان للتجارة) البضاعة الموجودة بالشحنات المذكورأرقامها بالأسفل", 10.0f, 110.0f, paint);
                canvas.drawText("وذلك لتسليمها لعملاء الشركة وتحصيل قيمتها منهم على أن ألتزم برد الطلبيات التي لم تسلم للعملاء لمخزن الشركة بنفس حالة إستلامها وتسديد ما أقوم بتحصيله", 30.0f, 150.0f, paint);
                canvas.drawText("من العملاء لخزينة الشركة وتعتبر البضاعة وما أقوم بتحصيله من العملاء هو أمانة في ذمتي أتعهد بتسليمها للشركة, وإذا أخلللت بذلك أكون مبددا وخائنا للأمانة . ", 30.0f, 190.0f, paint);
                canvas.drawText("وأتحمل كافة المسئولية الجنائية والمدنية المترتبة على ذلك. ", 550.0f, 230.0f, paint);

              /*  canvas.drawText("I ............................................................. id no. .......................................  as (HyperOne Co.) delivery associate admit that I received the products in the packages numbers mentioned ", 20.0f, 230.0f, paint);
                canvas.drawText("below to deliver to (HyperOne Co.) customers and collect their values from them and I commit to return the failed delivery  to (HyperOne Co.) warehouse in", 20.0f, 270.0f, paint);
                canvas.drawText("the same receiving condition and pay what I had collected to (HyperOne Co.) cashier.", 20.0f, 310.0f, paint);
                canvas.drawText("the products and the money that belong to (HyperOne Co.) are fidelity I pledge to give it to (HyperOne Co.), if I didn't do this, I will be a traitor to the fidelity", 20.0f, 350.0f, paint);
                canvas.drawText("and bear all criminal and civil liability arising therefrom.", 20.0f, 230.0f, paint);*/
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(2.0f);
                Paint paint2 = paint;
                //top of header row  line change bottom
                //bottom of table    change top
                canvas.drawRect(10.0f, 2600.0f, 1940.0f, 250.0f, paint2);
                paint.setTextAlign(Paint.Align.RIGHT);
                paint.setStyle(Paint.Style.FILL);

                canvas.drawText("S/م", 1925.0f, 280.0f, paint);
                canvas.drawLine(1870.0f, 250.0f, 1870.0f, 2600.0f, paint2);
                canvas.drawText("outBound", 1830.0f, 280.0f, paint);
                canvas.drawLine(1660.0f, 250.0f, 1660.0f, 2600.0f, paint2);
                canvas.drawText("رقم الشحنة", 1500.0f, 280.0f, paint);
                canvas.drawLine(1150.0f, 250.0f, 1150.0f, 2600.0f, paint2);
                canvas.drawText("قيمة الشحنة", 1140.0f, 280.0f, paint);
                canvas.drawLine(997.0f, 250.0f, 997.0f, 2600.0f, paint2);
                canvas.drawText("طريقة الدفع", 990.0f, 280.0f, paint);
                canvas.drawLine(850.0f, 250.0f, 850.0f, 2600.0f, paint2);
//                canvas.drawText("نوع الشحنة", 700.0f, 280.0f, paint);
//                canvas.drawLine(660.0f, 780.0f, 660.0f, 250.0f, paint2);
                canvas.drawText("إسم العميل", 760.0f, 280.0f, paint);
                canvas.drawLine(500.0f, 250.0f, 500.0f, 2600.0f, paint2);
//                canvas.drawText("نوع الشحنة", 580.0f, 280.0f, paint);
//                canvas.drawLine(500.0f, 780.0f, 500.0f, 250.0f, paint2);
                canvas.drawText("عنوان العميل", 480.0f, 280.0f, paint);
//                canvas.drawLine(400.0f, 780.0f, 400.0f, 250.0f, paint2);
//                canvas.drawText("تليفون العميل", 380.0f, 280.0f, paint);
                canvas.drawLine(300.0f, 250.0f, 300.0f, 2600.0f, paint2);
                canvas.drawText("ملاحظات", 180.0f, 280.0f, paint);

                //bottom of header row  line
                canvas.drawLine(20.0f, 300.0f, 1940.0f, 300.0f, paint2);

                canvas.drawText("توقيع المستلم/Receiver sign", 1500.0f, 2670.0f, paint);
                canvas.drawText("توقيع مسئول أمن المخزن", 1000.0f, 2670.0f, paint);
                canvas.drawText("توقيع منسق التوصيل", 600.0f, 2670.0f, paint);

                try {
                    testCODE93(canvas,1150.0f, 310.0f);
                    canvas.drawLine(20.0f, 400.0f, 1540.0f, 400.0f, paint2);


                } catch (Exception e) {
                    e.printStackTrace();
                }

                pdfDocument.finishPage(page);
                try {
                    pdfDocument.writeTo(new FileOutputStream(new File(Environment.getExternalStorageDirectory(), "/HyperOne.pdf")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                pdfDocument.close();
            }
        });
    }

    private static void testCODE93(Canvas canvas , float left, float top) throws Exception
    {
        Code93 barcode = new Code93();

        /*
           Code 93 Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)
                A - Z (Uppercase letters)
                - (Dash), $ (Dollar), % (Percentage), (Space), . (Point), / (Slash), + (Plus)
        */
        barcode.setData("11223322-02");

        // Unit of Measure, pixel, cm, or inch
        barcode.setUom(IBarcode.UOM_PIXEL);
        // barcode bar module width (X) in pixel
        barcode.setX(3f);
        // barcode bar module height (Y) in pixel
        barcode.setY(60f);

        // barcode image margins
//        barcode.setLeftMargin(1f);
//        barcode.setRightMargin(1f);
//        barcode.setTopMargin(1f);
//        barcode.setBottomMargin(1f);

        // barcode image resolution in dpi
        barcode.setResolution(72);

        // disply barcode encoding data below the barcode
       // barcode.setShowText(true);
        // barcode encoding data font style
         //   barcode.setTextFont(new AndroidFont("Arial", Typeface.NORMAL, 16));
        // space between barcode and barcode encoding data
        barcode.setTextMargin(10);
        barcode.setTextColor(AndroidColor.black);

        // barcode bar color and background color in Android device
        barcode.setForeColor(AndroidColor.black);
        barcode.setBackColor(AndroidColor.white);

        /*
        specify your barcode drawing area
	    */
        RectF bounds = new RectF(left, top, 0, 0);
        barcode.drawBarcode(canvas, bounds);
    }

    /*private Bitmap textToImage(String text, int width, int height) throws WriterException, NullPointerException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.DATA_MATRIX.CODE_93,
                    width, height, null);
        } catch (IllegalArgumentException Illegalargumentexception) {
            return null;
        }

        int bitMatrixWidth = bitMatrix.getWidth();
        int bitMatrixHeight = bitMatrix.getHeight();
        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        int colorWhite = 0xFFFFFFFF;
        int colorBlack = 0xFF000000;

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;
            for (int x = 0; x < bitMatrixWidth; x++) {
                pixels[offset + x] = bitMatrix.get(x, y) ? colorBlack : colorWhite;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, width, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }*/

}