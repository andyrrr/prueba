package com.carpoolingtec.prueba;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainMapa extends AppCompatActivity{
    Button btnCancelar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Vista vista = new Vista(this);
        setContentView(vista);

        //btnCancelar = (Button) findViewById(R.id.buttonCancelar);
        //btnCancelar.setOnClickListener(new View.OnClickListener() {
         //   @Override
         //   public void onClick(View v) {
          //      finish();
           // }
        //});
    }

    class Vista extends View{
        public Vista(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas){
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(5);
            paint.setColor(Color.BLUE);

            int ancho = canvas.getWidth();
            canvas.drawRect(10,ancho,ancho,10,paint);
        }

    }

}
