package com.example.beatbounce.PembayaranPilihJam;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beatbounce.Home.HomeActivity;
import com.example.beatbounce.R;

import java.util.List;

public class Pembayaran1Activity extends AppCompatActivity {

    private TextView hargaBooking;
    private TextView paymentWatchPrice;

    private TextView paymentMethod;
    private TextView countDownTimer;
    private RecyclerView recyclerViewWatches;
    private HourAdapter hourAdapter;

    private Button btnSelesai;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran1);


        // Create and configure Toolbar programmatically
//        Toolbar toolbar = new Toolbar(this);
//        toolbar.setTitle("Pembayaran");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        hargaBooking = findViewById(R.id.hargaBooking);
        paymentWatchPrice = findViewById(R.id.paymentWatchPrice);
        paymentMethod = findViewById(R.id.paymentMethod);
        countDownTimer = findViewById(R.id.countDownTimer);
        recyclerViewWatches = findViewById(R.id.recyclerViewWatches);
        btnSelesai = findViewById(R.id.btnSelesai);

        btnSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pembayaran1Activity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            String watchName = intent.getStringExtra("watch_name");
            double watchPrice = intent.getDoubleExtra("watch_price", 0.0);
            String paymentMethodSelected = intent.getStringExtra("payment_method");
            List<Hour> hours = (List<Hour>) intent.getSerializableExtra("selectedWatches");

            // Update TextViews with details
//            paymentWatchName.setText("Watch: " + watchName);
            paymentWatchPrice.setText(String.format("%.3f", watchPrice + 10.000));
            hargaBooking.setText(String.format("%.3f", watchPrice));
            paymentMethod.setText(paymentMethodSelected);

            // Set up RecyclerView
            TextView totalPriceTextView = findViewById(R.id.totalPriceTextView);
            hourAdapter = new HourAdapter(hours, totalPriceTextView);
            recyclerViewWatches.setAdapter(hourAdapter);
            recyclerViewWatches.setLayoutManager(new LinearLayoutManager(this));
        }

        startCountDownTimer();
    }

    private void startCountDownTimer() {
        new CountDownTimer(5 * 60 * 1000, 1000) { // 5 minutes countdown

            @Override
            public void onTick(long millisUntilFinished) {
                int minutes = (int) (millisUntilFinished / 1000) / 60;
                int seconds = (int) (millisUntilFinished / 1000) % 60;
                countDownTimer.setText(String.format("%02d:%02d", minutes, seconds));
            }

            @Override
            public void onFinish() {
                countDownTimer.setText("Waktu habis");
            }
        }.start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Handle the back button action
            onBackPressed(); // This will navigate back to the previous activity
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
