package com.example.beatbounce.PembayaranPilihJam;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beatbounce.R;

import java.util.ArrayList;
import java.util.List;

public class SelectedHoursActivity extends AppCompatActivity {

    private HourAdapter adapter;
    private List<Hour> selectedHours;
    private Spinner spinnerPaymentMethod;
    private TextView textViewTotalPrice;
    private TextView textViewBiayaSewa;

    private Button buttonPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_hours);

        Toolbar toolbar = new Toolbar(this);
        toolbar.setTitle("Pilih Jam");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerViewSelected = findViewById(R.id.recyclerViewSelected);
        textViewBiayaSewa = findViewById(R.id.biayaStudio);
        textViewTotalPrice = findViewById(R.id.textViewTotalPrice);
        spinnerPaymentMethod = findViewById(R.id.spinnerPaymentMethod);
        buttonPay = findViewById(R.id.buttonPay);
        Button buttonDeleteSelected = findViewById(R.id.buttonDeleteSelected);
        Button buttonAdd = findViewById(R.id.buttonAdd);

        Intent intent = getIntent();
        selectedHours = (ArrayList<Hour>) intent.getSerializableExtra("selectedWatches");


        adapter = new HourAdapter(selectedHours, textViewTotalPrice);
        recyclerViewSelected.setAdapter(adapter);
        recyclerViewSelected.setLayoutManager(new LinearLayoutManager(this));

        updateTotalPrice();

        buttonDeleteSelected.setOnClickListener(v -> {
            List<Hour> selectedForDeletion = new ArrayList<>(adapter.getSelectedWatches());

            // Update isAvailable status to true for each selected hour
            for (Hour hour : selectedForDeletion) {
                hour.setAvailable(true);  // Use the setAvailable method here
            }

            // Remove the selected hours from the list
            selectedHours.removeAll(selectedForDeletion);

            // Notify the adapter that the data set has changed
            adapter.notifyDataSetChanged();

            // Update the total price
            updateTotalPrice();
        });


        buttonAdd.setOnClickListener(v -> {
            onBackPressed();
            adapter.notifyDataSetChanged();
        });

        // SelectedWatchesActivity.java
        buttonPay.setOnClickListener(v -> {
            String selectedPaymentMethod = spinnerPaymentMethod.getSelectedItem().toString();
            Intent intentPay = new Intent(SelectedHoursActivity.this, Pembayaran1Activity.class);
            intentPay.putExtra("watch_name", "Example Watch Name"); // Replace with actual watch name if available
            intentPay.putExtra("watch_price", getTotalPrice());
            intentPay.putExtra("payment_method", selectedPaymentMethod);
            intentPay.putExtra("selectedWatches", new ArrayList<>(selectedHours)); // Send list of selected watches
            startActivity(intentPay);
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == RESULT_OK) {
            List<Hour> newHours = (ArrayList<Hour>) data.getSerializableExtra("selectedWatches");
            if (newHours != null) {
                selectedHours.addAll(newHours);
                adapter.notifyDataSetChanged();
                updateTotalPrice();
            }
        }
    }

    private void updateTotalPrice() {
        double totalPrice = getTotalPrice();
        textViewTotalPrice.setText(String.format("%.3f", totalPrice + 10.000));
        textViewBiayaSewa.setText(String.format("%.3f", totalPrice));
    }

    private double getTotalPrice() {
        double totalPrice = 0;
        for (Hour hour : selectedHours) {
            totalPrice += hour.getPrice();
        }
        return totalPrice;
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
