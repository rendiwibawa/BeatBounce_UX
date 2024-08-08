package com.example.beatbounce.Detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.beatbounce.R;

public class ReviewActivity extends AppCompatActivity {

    private ImageView imageViewDetail;
    private TextView textViewTitleDetail;
    private TextView textViewRatingDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        imageViewDetail = findViewById(R.id.viewImage1);
        textViewTitleDetail = findViewById(R.id.textViewTitleDetail);
        textViewRatingDetail = findViewById(R.id.textViewRatingDetail);

        Intent intent = getIntent();
        int imageResId = intent.getIntExtra("imageResId", -1);
        String title = intent.getStringExtra("title");
        String rating = intent.getStringExtra("rating");

        // Set data to views
        if (imageResId != -1) {
            imageViewDetail.setImageResource(imageResId);
        }
        textViewTitleDetail.setText(title);
        textViewRatingDetail.setText(rating);

    }

    // Method to handle back button press
    public void onBackPressed(View view) {
        finish();
    }
}