package com.example.beatbounce;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BookingActivity extends AppCompatActivity {

    private List<BookingHour> currentBookingHoursList;
    private boolean[] selectedHours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_page);

        RecyclerView dateRecyclerView = findViewById(R.id.DateRecyclerView);
        dateRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        Random random = new Random();

        List<BookingHour> bookingHoursList1 = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            boolean randomBoolean = random.nextBoolean();
            String hour = String.format("%02d:00 - %02d:00", i, i + 1);
            bookingHoursList1.add(new BookingHour(hour, 100, randomBoolean)); // all prices are 100 on 1st July
        }

        List<BookingHour> bookingHoursList2 = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            long price = 100 + i * 4; // prices vary from 100 to 200 on 2nd July
            boolean randomBoolean = random.nextBoolean();
            String hour = String.format("%02d:00 - %02d:00", i, i + 1);
            bookingHoursList2.add(new BookingHour(hour, price, randomBoolean));
        }

        List<BookingDate> bookingList = new ArrayList<>();
        bookingList.add(new BookingDate("1 Juli", bookingHoursList1));
        bookingList.add(new BookingDate("2 Juli", bookingHoursList2));
        bookingList.add(new BookingDate("3 Juli", bookingHoursList2));
        bookingList.add(new BookingDate("4 Juli", bookingHoursList2));
        bookingList.add(new BookingDate("5 Juli", bookingHoursList2));
        bookingList.add(new BookingDate("6 Juli", bookingHoursList2));

        BookingHourAdapter hourAdapter = new BookingHourAdapter(bookingHoursList1);
        currentBookingHoursList = bookingHoursList1; // Set the initial list

        BookingDateAdapter dateAdapter = new BookingDateAdapter(bookingList);
        dateAdapter.setOnDateClickListener(new BookingDateAdapter.OnDateClickListener() {
            @Override
            public void onDateClick(List<BookingHour> hours) {
                currentBookingHoursList = hours; // Update the current list
                hourAdapter.updateData(hours);

                // Check if all hours are available
                boolean allAvailable = true;
                for (BookingHour hour : hours) {
                    if (!hour.isAvailable()) {
                        allAvailable = false;
                        break;
                    }
                }

                // Update the switch
                Switch switchFilter = findViewById(R.id.switch_filter);
                switchFilter.setChecked(allAvailable);
            }
        });
        dateRecyclerView.setAdapter(dateAdapter);

        // Hour Recycler View
        RecyclerView hourRecyclerView = findViewById(R.id.HourRecyclerView);
        hourRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        hourRecyclerView.setAdapter(hourAdapter);

        Switch switchFilter = findViewById(R.id.switch_filter);
        switchFilter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Filter your hour data here and update the RecyclerView
                    List<BookingHour> filteredList = filterData(currentBookingHoursList);
                    hourAdapter.updateData(filteredList);
                } else {
                    // If the switch is off, show all data
                    hourAdapter.updateData(currentBookingHoursList);
                }
            }
        });
    }

    private List<BookingHour> filterData(List<BookingHour> bookingList) {
        List<BookingHour> filteredList = new ArrayList<>();
        for (BookingHour item : bookingList) {
            if (item.isAvailable()) {
                filteredList.add(item);
            }
        }
        return filteredList;
    }
    // Method to update total price
    public void updateTotalPrice(long totalPrice) {
        TextView totalPriceTextView = findViewById(R.id.totalPriceTextView); // Ensure this TextView is properly referenced
        totalPriceTextView.setText("Rp. " + totalPrice);
    }

    // Initialize the selectedHours array when updating the hours list
    private void updateHoursList(List<BookingHour> hours) {
        selectedHours = new boolean[hours.size()]; // Initialize the array with the size of hours list
        // Rest of your update logic...
    }
}
