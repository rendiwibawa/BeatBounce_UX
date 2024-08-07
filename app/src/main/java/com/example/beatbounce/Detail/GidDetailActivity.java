package com.example.beatbounce.Detail;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.beatbounce.R;

import android.view.View;
import android.widget.ViewFlipper;

public class GidDetailActivity extends AppCompatActivity {

    private ViewFlipper viewFlipper;

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
    }

}
