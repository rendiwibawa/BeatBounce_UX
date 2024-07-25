package com.example.beatbounce;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BookingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_page);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<BookingItem> bookingList = new ArrayList<>();
        bookingList.add(new BookingItem("1 Juli"));
        bookingList.add(new BookingItem("2 Juli"));
        bookingList.add(new BookingItem("3 Juli"));
        bookingList.add(new BookingItem("4 Juli"));
        bookingList.add(new BookingItem("5 Juli"));
        bookingList.add(new BookingItem("6 Juli"));


        BookingAdapter adapter = new BookingAdapter(bookingList);
        recyclerView.setAdapter(adapter);
    }
}