package com.example.beatbounce.PembayaranPilihJam;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beatbounce.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HourListActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SELECTED_WATCHES = 1;
    private List<Hour> hourList;
    private List<Hour> currentBookingHoursList;
    private List<BookingDate> bookingList;
    private HourAdapter hourAdapter;
    private TextView studioNameTextView;
    private TextView studioAddressTextView;

    private boolean[] selectedHours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_hour);
        studioNameTextView = findViewById(R.id.studioNameTextView); // replace with your actual TextView id
        studioAddressTextView = findViewById(R.id.studioAddressTextView);

        RecyclerView dateRecyclerView = findViewById(R.id.DateRecyclerView);
        dateRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        RecyclerView hourRecyclerView = findViewById(R.id.HourRecyclerView);
        hourRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Setup toolbar with back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Pilih Jadwal");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }



        // Get data from Intent
        Intent intent1 = getIntent();
        int imageResId = intent1.getIntExtra("imageResId", -1);
        String title = intent1.getStringExtra("title");
        String location = intent1.getStringExtra("location");
        String rating = intent1.getStringExtra("rating");
        String studioName = intent1.getStringExtra("title");

        // Set studio name to TextView
        studioNameTextView.setText(studioName);

        // Retrieve and clean price string from Intent
        String priceString = getIntent().getStringExtra("price");
        double price = 0.0;

        if (priceString != null) {
            try {
                // Remove non-numeric characters, leaving only digits and decimal points
                String cleanedPriceString = priceString.replaceAll("[^\\d.,]", "").replace(",", ".");
                price = Double.parseDouble(cleanedPriceString);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                // Handle the exception as needed (e.g., set a default value or notify the user)
            }
        }


        Button buttonShowSelected = findViewById(R.id.buttonShowSelected);


//         Set up dates
        Random random = new Random();

        List<Hour> bookingHoursList1 = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            boolean randomBoolean = random.nextBoolean();
            String hour = String.format("%02d:00 - %02d:00", i, i + 1);
            bookingHoursList1.add(new Hour(hour, price, randomBoolean));
        }

        List<Hour> bookingHoursList2 = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            boolean randomBoolean = random.nextBoolean();
            String hour = String.format("%02d:00 - %02d:00", i, i + 1);
            bookingHoursList2.add(new Hour(hour, price, randomBoolean));
        }

//        hourAdapter = new HourAdapter(bookingHoursList1);
//        hourRecyclerView.setAdapter(hourAdapter);
        TextView totalPriceTextView = findViewById(R.id.totalPriceTextView);
        hourAdapter = new HourAdapter(bookingHoursList1, totalPriceTextView);
        hourRecyclerView.setAdapter(hourAdapter);
//
        bookingList = new ArrayList<>();
        bookingList.add(new BookingDate("1 Juli", bookingHoursList1));
        bookingList.add(new BookingDate("2 Juli", bookingHoursList2));
        bookingList.add(new BookingDate("3 Juli", bookingHoursList2));
        bookingList.add(new BookingDate("4 Juli", bookingHoursList2));
        bookingList.add(new BookingDate("5 Juli", bookingHoursList2));
        bookingList.add(new BookingDate("6 Juli", bookingHoursList2));
//
        BookingDateAdapter dateAdapter = new BookingDateAdapter(bookingList);



        dateAdapter.setOnDateClickListener(new BookingDateAdapter.OnDateClickListener() {
            @Override
            public void onDateClick(List<Hour> hours) {
                hourAdapter.resetTotalPrice();
                // Clear the selected hours from the previous date
                hourAdapter.getSelectedWatches().clear();
                // Update the data for the new date
                currentBookingHoursList = hours;
                hourAdapter.updateData(hours);
                // Update the available addresses count
                updateAvailableAddressesCount();
            }
        });
        dateRecyclerView.setAdapter(dateAdapter);
        if (bookingList.size() > 0) {
            // Use the adapter's listener to trigger the onDateClick method
            dateRecyclerView.post(() -> {
                BookingDateAdapter.OnDateClickListener listener = dateAdapter.getOnDateClickListener();
                if (listener != null) {
                    listener.onDateClick(bookingList.get(0).getHours());
                }
            });
        }

        buttonShowSelected.setOnClickListener(v -> {
            // Get the selected hours from the current date
            ArrayList<Hour> selectedHours = new ArrayList<>(hourAdapter.getSelectedWatches());

            if (selectedHours.isEmpty()) {
                // Show an alert if no hours are selected
                new AlertDialog.Builder(HourListActivity.this)
                        .setTitle("Error")
                        .setMessage("Please select at least one hour.")
                        .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                            // Dismiss the dialog
                            dialog.dismiss();
                        })
                        .show();
            } else {
                Intent intent = new Intent(HourListActivity.this, SelectedHoursActivity.class);
                intent.putExtra("imageResId", imageResId);
                intent.putExtra("title", title);
                intent.putExtra("location", location);
                intent.putExtra("rating", rating);
                intent.putExtra("selectedWatches", selectedHours);
                // Get the total price from the HourAdapter
                double totalPrice = hourAdapter.getTotalPrice();
                intent.putExtra("totalPrice", totalPrice);

                startActivityForResult(intent, REQUEST_CODE_SELECTED_WATCHES);
            }
        });
        // Find the switch
        Switch mySwitch = findViewById(R.id.switch_filter);

        // Set an OnCheckedChangeListener
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The switch is checked
                    // Filter the currentBookingHoursList to show only available hours
                    List<Hour> availableHours = new ArrayList<>();
                    for (Hour hour : currentBookingHoursList) {
                        if (hour.isAvailable()) {
                            availableHours.add(hour);
                        }
                    }
                    // Update the adapter with the filtered hours
                    hourAdapter.updateData(availableHours);
                } else {
                    // The switch is not checked
                    // Show all hours
                    hourAdapter.updateData(currentBookingHoursList);
                }
            }
        });


    }
    private void updateAvailableAddressesCount() {
        int availableAddressesCount = 0;
        for (Hour hour : currentBookingHoursList) {
            if (hour.isAvailable()) {
                availableAddressesCount++;
            }
        }
        studioAddressTextView.setText(String.valueOf(availableAddressesCount)+" Jadwal tersedia");
    }
    private List<Hour> filterData(List<Hour> bookingList) {
        List<Hour> filteredList = new ArrayList<>();
        for (Hour item : bookingList) {
            if (item.isAvailable()) {
                filteredList.add(item);
            }
        }
        return filteredList;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECTED_WATCHES && resultCode == RESULT_OK) {
            List<Hour> updatedHours = (ArrayList<Hour>) data.getSerializableExtra("updatedWatches");
            hourAdapter.updateData(updatedHours);
        }
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
    private void updateHoursList(List<Hour> hours) {
        selectedHours = new boolean[hours.size()]; // Initialize the array with the size of hours list
        // Rest of your update logic...
    }

}




