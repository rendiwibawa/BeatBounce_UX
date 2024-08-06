package com.example.beatbounce.PembayaranPilihJam;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beatbounce.Detail.DetailActivity;
import com.example.beatbounce.R;

import java.util.ArrayList;
import java.util.List;

public class HourListActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SELECTED_WATCHES = 1;
    private List<Hour> hourList;
    private List<Hour> currentBookingHoursList;
    private List<BookingDate> bookingList;
    private HourAdapter hourAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView dateRecyclerView = findViewById(R.id.DateRecyclerView);
        dateRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        RecyclerView hourRecyclerView = findViewById(R.id.recyclerView);
        hourRecyclerView.setLayoutManager(new LinearLayoutManager(this));

//        Toolbar toolbar = new Toolbar(this);
//        toolbar.setTitle("Pilih Jam");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get data from Intent
        Intent intent1 = getIntent();
        int imageResId = intent1.getIntExtra("imageResId", -1);
        String title = intent1.getStringExtra("title");
        String location = intent1.getStringExtra("location");
        String rating = intent1.getStringExtra("rating");

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

        // Initialize hourList with some example data
        hourList = new ArrayList<>();
        hourList.add(new Hour("08:00 - 09:00", price, true));
        hourList.add(new Hour("09:00 - 10:00", price, true));
        hourList.add(new Hour("11:00 - 12:00", price, false));


        hourAdapter = new HourAdapter(hourList);
        hourRecyclerView.setAdapter(hourAdapter);

        // Set up dates
//        Random random = new Random();
//
//        List<Hour> bookingHoursList1 = new ArrayList<>();
//        for (int i = 0; i < 24; i++) {
//            boolean randomBoolean = random.nextBoolean();
//            String hour = String.format("%02d:00 - %02d:00", i, i + 1);
//            bookingHoursList1.add(new Hour(hour, 100, randomBoolean));
//        }
//
//        List<Hour> bookingHoursList2 = new ArrayList<>();
//        for (int i = 0; i < 24; i++) {
//            long price = 100 + i * 4;
//            boolean randomBoolean = random.nextBoolean();
//            String hour = String.format("%02d:00 - %02d:00", i, i + 1);
//            bookingHoursList2.add(new Hour(hour, price, randomBoolean));
//        }
//
//        bookingList = new ArrayList<>();
//        bookingList.add(new BookingDate("1 Juli", bookingHoursList1));
//        bookingList.add(new BookingDate("2 Juli", bookingHoursList2));
//        bookingList.add(new BookingDate("3 Juli", bookingHoursList2));
//        bookingList.add(new BookingDate("4 Juli", bookingHoursList2));
//        bookingList.add(new BookingDate("5 Juli", bookingHoursList2));
//        bookingList.add(new BookingDate("6 Juli", bookingHoursList2));
//
//        BookingDateAdapter dateAdapter = new BookingDateAdapter(bookingList);
//        dateAdapter.setOnDateClickListener(new BookingDateAdapter.OnDateClickListener() {
//            @Override
//            public void onDateClick(List<Hour> hours) {
//                currentBookingHoursList = hours;
//                hourAdapter.updateData(hours);
//            }
//        });
//        dateRecyclerView.setAdapter(dateAdapter);
//
        buttonShowSelected.setOnClickListener(v -> {
            Intent intent = new Intent(HourListActivity.this, SelectedHoursActivity.class);
            intent.putExtra("imageResId", imageResId);
            intent.putExtra("title", title);
            intent.putExtra("location", location);
            intent.putExtra("rating", rating);


            intent.putExtra("selectedWatches", new ArrayList<>(hourAdapter.getSelectedWatches()));
            startActivityForResult(intent, REQUEST_CODE_SELECTED_WATCHES);



        });
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

}




