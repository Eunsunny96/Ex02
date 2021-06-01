package com.example.ex02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }
    public  void  onClick(View v){
        Intent intent =null;
        switch (v.getId()){
            case R.id.button1:
                intent = new Intent(this, ListDemo1.class);
                break;
            case R.id.button2:
                intent = new Intent(this, ListXml.class);
                break;
            case R.id.button3:
                intent = new Intent(this, ListArray.class);
                break;
            case R.id.button4:
                intent = new Intent(this, PhonebookActivity.class);
                break;
            case R.id.button5:
                intent = new Intent(this, ProductActivity.class);
                break;
            case R.id.button6:
                intent = new Intent(this, SpinnerActivity.class);
                break;
            case R.id.button7:
                intent = new Intent(this, GridActivity.class);
                break;
            case R.id.button8:
                intent = new Intent(this, AutoActivity.class);
                break;
        }
        startActivity(intent);
    }
}