package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.databinding.ActivityMainBinding;

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
    }
    private void createPdf() {
       binding.button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                PdfDocument pdfDocument = new PdfDocument();
                Paint paint = new Paint();
                paint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
                paint.setTextSize(12.0f);
                PdfDocument.Page page = pdfDocument.startPage(new PdfDocument.PageInfo.Builder(1200, 1200, 1).create());
                Canvas canvas = page.getCanvas();
                canvas.drawText("إقرار إستلام /Receiving Avowal", 500.0f, 30.0f, paint);
                canvas.drawText("التاريخ/Date : " + currentDate + "           الوقت/Time : " + currentTime + " ", 800.0f, 70.0f, paint);
                canvas.drawText("استلمت أنا ............................................................... رقم قومي ....................................................  مندوب (شركة هايبروان للتجارة) البضاعة الموجودة بالشحنات المذكور أرقامها بالأسفل وذلك لتسليمها لعملاء الشركة", 80.0f, 90.0f, paint);
                canvas.drawText("وتحصيل قيمتها منهم على أن ألتزم برد الطلبيات التي لم تسلم للعملاء لمخزن الشركة بنفس حالة إستلامها وتسديد ما أقوم بتحصيله من العملاء لخزينة الشركة, وتعتبر البضاعة وما أقوم بتحصيله ", 300.0f, 110.0f, paint);
                canvas.drawText("من العملاء هو أمانة في ذمتي أتعهد بتسليمها للشركة, وإذا أخلللت بذلك أكون مبددا وخائنا للأمانة وأتحمل كافة المسئولية الجنائية والمدنية المترتبة على ذلك. ", 460.0f, 130.0f, paint);
                canvas.drawText("I ............................................................. id no. .......................................  as (HyperOne Co.) delivery associate admit that I received the products in the packages numbers mentioned ", 20.0f, 150.0f, paint);
                canvas.drawText("below to deliver to (HyperOne Co.) customers and collect their values from them and I commit to return the failed delivery  to (HyperOne Co.) warehouse in", 20.0f, 170.0f, paint);
                canvas.drawText("the same receiving condition and pay what I had collected to (HyperOne Co.) cashier.", 20.0f, 190.0f, paint);
                canvas.drawText("the products and the money that belong to (HyperOne Co.) are fidelity I pledge to give it to (HyperOne Co.), if I didn't do this, I will be a traitor to the fidelity", 20.0f, 210.0f, paint);
                canvas.drawText("and bear all criminal and civil liability arising therefrom.", 20.0f, 230.0f, paint);
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(2.0f);
                Paint paint2 = paint;
                canvas.drawRect(20.0f, 780.0f, 1180.0f, 250.0f, paint2);
                paint.setTextAlign(Paint.Align.RIGHT);
                paint.setStyle(Paint.Style.FILL);
                canvas.drawText("S/م", 1170.0f, 280.0f, paint);
                canvas.drawLine(1140.0f, 780.0f, 1140.0f, 250.0f, paint2);
                canvas.drawText("رقم الشحنة", 1110.0f, 280.0f, paint);
                canvas.drawLine(1030.0f, 780.0f, 1030.0f, 250.0f, paint2);
                canvas.drawText("قيمة الشحنة", 1010.0f, 280.0f, paint);
                canvas.drawLine(930.0f, 780.0f, 930.0f, 250.0f, paint2);
                canvas.drawText("طريقة الدفع", 900.0f, 280.0f, paint);
                canvas.drawLine(800.0f, 780.0f, 800.0f, 250.0f, paint2);
                canvas.drawText("نوع الشحنة", 780.0f, 280.0f, paint);
                canvas.drawLine(700.0f, 780.0f, 700.0f, 250.0f, paint2);
                canvas.drawText("إسم العميل", 680.0f, 280.0f, paint);
                canvas.drawLine(600.0f, 780.0f, 600.0f, 250.0f, paint2);
                canvas.drawText("نوع الشحنة", 580.0f, 280.0f, paint);
                canvas.drawLine(500.0f, 780.0f, 500.0f, 250.0f, paint2);
                canvas.drawText("عنوان العميل", 480.0f, 280.0f, paint);
                canvas.drawLine(400.0f, 780.0f, 400.0f, 250.0f, paint2);
                canvas.drawText("تليفون العميل", 380.0f, 280.0f, paint);
                canvas.drawLine(300.0f, 780.0f, 300.0f, 250.0f, paint2);
                canvas.drawText("ملاحظات", 180.0f, 280.0f, paint);
                canvas.drawLine(20.0f, 300.0f, 1180.0f, 300.0f, paint2);
                canvas.drawText("توقيع المستلم/Receiver sign", 1150.0f, 850.0f, paint);
                canvas.drawText("توقيع مسئول أمن المخزن", 650.0f, 850.0f, paint);
                canvas.drawText("توقيع منسق التوصيل", 200.0f, 850.0f, paint);
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
}