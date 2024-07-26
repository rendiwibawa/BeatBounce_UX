package com.example.beatbounce;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Pembayaran1 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setTitle("Pembayaran");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pembayaran1);
    }
}
