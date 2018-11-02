package com.serhatekici.kennyiyakala;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Main2Activity extends AppCompatActivity {

    int skor;
    TextView skorText;
    TextView zamanText;

    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView imageView10;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;
    int milisaniye;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        zamanText=findViewById(R.id.zamanText);
        skorText=findViewById(R.id.skorText);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);
        imageView10=findViewById(R.id.imageView10);

        imageArray = new ImageView[]{imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,imageView10};

        Intent intent = getIntent();
        String alici = intent.getStringExtra("sayac");
        milisaniye=Integer.parseInt(alici);
        new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                zamanText.setText("Geri Sayım: " + millisUntilFinished/1000);
                for (ImageView image:imageArray)
                {
                    image.setVisibility((View.INVISIBLE));
                }


            }

            @Override
            public void onFinish() {

                Toast.makeText(Main2Activity.this,"Başla",Toast.LENGTH_SHORT).show();
                zamanText.setText("Zaman: 0");

                resimGizle();
                skor=0;
                new CountDownTimer(10000,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        zamanText.setText("Zaman: " + millisUntilFinished/1000);
                    }

                    @Override
                    public void onFinish() {

                        zamanText.setText("Zaman doldu");
                        handler.removeCallbacks(runnable);
                        for (ImageView image:imageArray)
                        {
                            image.setVisibility((View.INVISIBLE));
                        }
                        AlertDialog.Builder alert = new AlertDialog.Builder(Main2Activity.this);
                        alert.setTitle("Yeniden Başla");
                        alert.setMessage("Emin misin?");
                        alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        });
                        alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent  intent = new Intent(getApplicationContext(),MainActivity.class);
                                //intent.putExtra("sayac",editText.getText().toString());
                                intent.putExtra("sonuc",skorText.getText().toString());
                                startActivity(intent);
                            }
                        });
                        alert.show();
                    }
                }.start();
            }
        }.start();


    }

    public void skorarttir(View view){
        skor++;
        skorText.setText("Skor:" + skor);

    }

    public void resimGizle(){
        handler= new Handler();
        runnable= new Runnable() {
            @Override
            public void run() {
                for (ImageView image:imageArray)
                {
                    image.setVisibility((View.INVISIBLE));
                }
                Random random = new Random();
                int i= random.nextInt(9);
                imageArray[i].setVisibility((View.VISIBLE));
                handler.postDelayed(this,milisaniye);
            }
        };
        handler.post(runnable);
    }

    public void geridon (View view){
        Intent  intent = new Intent(getApplicationContext(),MainActivity.class);
        //intent.putExtra("sayac",editText.getText().toString());
        intent.putExtra("sonuc",skorText.getText().toString());
        startActivity(intent);
    }
}
