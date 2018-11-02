package com.serhatekici.kennyiyakala;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        Intent intent = getIntent();
        String alici = intent.getStringExtra("sonuc");
        if (alici!=null) {
            Toast.makeText(this, alici, Toast.LENGTH_SHORT).show();
        }
    }

    public void basla(View view){

        if (editText.getText().toString().matches("")){
           // Toast.makeText(getApplicationContext(),"Lütfen bir sayı giriniz!",Toast.LENGTH_LONG).show();
            editText.setError("Lütfen bir sayı giriniz");

        }
        else if(Integer.parseInt(editText.getText().toString()) < 100)
        {
            editText.setError("En az 100 girilmelidir.");
        }
        else
        {
            Intent  intent = new Intent(getApplicationContext(),Main2Activity.class);
            //intent.putExtra("sayac",editText.getText().toString());
            intent.putExtra("sayac",editText.getText().toString());
            startActivity(intent);
        }
    }
}
