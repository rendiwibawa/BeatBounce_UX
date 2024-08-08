package com.example.beatbounce.Detail;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beatbounce.PembayaranPilihJam.HourListActivity;
import com.example.beatbounce.R;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;
import android.widget.ViewFlipper;
import android.content.Intent;
import android.widget.Button;

import java.util.List;


public class DetailActivity extends AppCompatActivity {

    private ViewFlipper viewFlipper;
    private Button bookingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_page);

        Toolbar toolbar = new Toolbar(this);
        toolbar.setTitle("Detail Page");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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


        ImageView imageViewDetail = findViewById(R.id.imageViewDetail);
        TextView textViewTitleDetail = findViewById(R.id.textViewTitleDetail);
        TextView textViewPriceDetail = findViewById(R.id.textViewPriceDetail);
        TextView textViewLocationDetail = findViewById(R.id.textViewLocationDetail);
        TextView textViewRatingDetail = findViewById(R.id.textViewRatingDetail);
        TextView textViewOwnerDetail = findViewById(R.id.textViewOwnerDetail);
        Button bookButton = findViewById(R.id.bookingButton);

        // Get data from Intent
        Intent intent = getIntent();
        int imageResId = intent.getIntExtra("imageResId", -1);
        String title = intent.getStringExtra("title");
        String price = intent.getStringExtra("price");
        String location = intent.getStringExtra("location");
        double rating = intent.getDoubleExtra("rating", 0.0);
        String owner = intent.getStringExtra("owner");





        // Set data to views
        if (imageResId != -1) {
            imageViewDetail.setImageResource(imageResId);
        }
        textViewTitleDetail.setText(title);
        textViewPriceDetail.setText(price);
        textViewLocationDetail.setText(location);
        textViewRatingDetail.setText(String.valueOf(rating));
        textViewOwnerDetail.setText(owner);


        bookButton.setOnClickListener(v -> {
            Intent bookingIntent = new Intent(DetailActivity.this, HourListActivity.class);

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
                Intent intent = new Intent(DetailActivity.this, ReviewActivity.class);

                intent.putExtra("title", title);
                intent.putExtra("price", price);
                intent.putExtra("location", location);
                intent.putExtra("rating", rating);
                intent.putExtra("imageResId", imageResId);


                // Start the ReviewActivity
                startActivity(intent);
            }
        });

        // Find the RecyclerView
        RecyclerView recyclerViewFacility = findViewById(R.id.recyclerViewFacilityDetail);

// Set its layout manager
        recyclerViewFacility.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

// Get the facility images from Intent
        // Get the facility images from Intent
        List<Integer> facilityImages = (List<Integer>) getIntent().getSerializableExtra("facilities");

        if (facilityImages != null) {
            // Set its adapter
            FacilityAdapter facilityAdapter = new FacilityAdapter(facilityImages);
            recyclerViewFacility.setAdapter(facilityAdapter);
        }

        // Find the RecyclerView for genres
        RecyclerView recyclerViewGenre = findViewById(R.id.recyclerViewTagDetail);

// Set its layout manager
        recyclerViewGenre.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

// Get the genre names from Intent
        List<String> genreNames = (List<String>) getIntent().getSerializableExtra("genres");

        if (genreNames != null) {
            // Create an instance of GenreAdapter
            GenreAdapter genreAdapter = new GenreAdapter(genreNames);

            // Set the adapter to the RecyclerView
            recyclerViewGenre.setAdapter(genreAdapter);
        }
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
