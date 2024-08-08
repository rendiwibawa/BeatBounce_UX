package com.example.beatbounce.Detail;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.beatbounce.PembayaranPilihJam.HourListActivity;
import com.example.beatbounce.PembayaranPilihJam.SelectedHoursActivity;
import com.example.beatbounce.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.content.Intent;
import android.widget.Button;


public class GidDetailActivity extends AppCompatActivity {

    private ViewFlipper viewFlipper;
    private Button bookingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_page);

        viewFlipper = findViewById(R.id.viewFlipper);

        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);

        // Optionally set auto flipping
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(3000); // flip every 3 seconds
        viewFlipper.startFlipping();

        // Handle click event to show next view
        viewFlipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.showNext();
            }
        });

        // To programmatically trigger the click event and show next view
        viewFlipper.performClick(); // This will trigger the OnClickListener

        // Find the booking button
//        bookingButton = findViewById(R.id.fabBooking);
//
//        // Set an OnClickListener for the booking button
//        bookingButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Create an Intent to start ActivityChooseHour
//                Intent intent = new Intent(GidDetailActivity.this, SelectedHoursActivity.class);
//
//                // Start the ActivityChooseHour
//                startActivity(intent);
//            }
//        });

        ImageView imageViewDetail = findViewById(R.id.imageViewDetail);
        TextView textViewTitleDetail = findViewById(R.id.textViewTitleDetail);
        TextView textViewPriceDetail = findViewById(R.id.textViewPriceDetail);
        TextView textViewLocationDetail = findViewById(R.id.textViewLocationDetail);
        TextView textViewRatingDetail = findViewById(R.id.textViewRatingDetail);
        MaterialButton button = (MaterialButton) findViewById(R.id.fabBooking);

        // Get data from Intent
        Intent intent = getIntent();
        int imageResId = intent.getIntExtra("imageResId", -1);
        String title = intent.getStringExtra("title");
        String price = intent.getStringExtra("price");
        String location = intent.getStringExtra("location");
        String rating = intent.getStringExtra("rating");

        // Set data to views
        if (imageResId != -1) {
            imageViewDetail.setImageResource(imageResId);
        }
        textViewTitleDetail.setText(title);
        textViewPriceDetail.setText(price);
        textViewLocationDetail.setText(location);
        textViewRatingDetail.setText(rating);

        button.setOnClickListener(v -> {
            Intent bookingIntent = new Intent(GidDetailActivity.this, HourListActivity.class);

            bookingIntent.putExtra("imageResId", imageResId);
            bookingIntent.putExtra("title", title);
            bookingIntent.putExtra("price", price);
            bookingIntent.putExtra("location", location);
            bookingIntent.putExtra("rating", rating);

            startActivity(bookingIntent);

        });

        // Find the TextView
        TextView lihatSemua = findViewById(R.id.lihatSemua);

// Set an OnClickListener
        lihatSemua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start ReviewActivity
                Intent intent = new Intent(GidDetailActivity.this, ReviewActivity.class);

                intent.putExtra("title", title);
                intent.putExtra("price", price);
                intent.putExtra("location", location);
                intent.putExtra("rating", rating);
                intent.putExtra("imageResId", imageResId);

                // Start the ReviewActivity
                startActivity(intent);
            }
        });


    }

}
