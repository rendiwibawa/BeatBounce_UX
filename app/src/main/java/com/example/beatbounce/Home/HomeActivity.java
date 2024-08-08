package com.example.beatbounce.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.beatbounce.History.HistoryActivity;
import com.example.beatbounce.R;
import com.example.beatbounce.StudioFavoritActivity;
import com.example.beatbounce.StudioPencarianActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout searchLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        searchLocation = findViewById(R.id.searchLocation);

        searchLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, StudioPencarianActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.DateRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<CardItem> cardItemList = new ArrayList<>();
        cardItemList.add(new CardItem(R.drawable.studio, "Studio A", "Rp 150.000 / Jam"));
        cardItemList.add(new CardItem(R.drawable.studio, "Studio B", "Rp 200.000 / Jam"));
        cardItemList.add(new CardItem(R.drawable.studio, "Studio C", "Rp 200.000 / Jam"));
        cardItemList.add(new CardItem(R.drawable.studio, "Studio A", "Rp 150.000 / Jam"));
        cardItemList.add(new CardItem(R.drawable.studio, "Studio B", "Rp 200.000 / Jam"));
        cardItemList.add(new CardItem(R.drawable.studio, "Studio C", "Rp 200.000 / Jam"));

        CardAdapter adapter = new CardAdapter(cardItemList);
        recyclerView.setAdapter(adapter);

        RecyclerView recyclerViewBesar = findViewById(R.id.recyclerViewBesar);
        recyclerViewBesar.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<CardBesarItem> cardBesarItemList = new ArrayList<>();
        cardBesarItemList.add(new CardBesarItem(R.drawable.studio, "Studio A", "Rp 150.000 / Jam", "Location", "4.5"));
        cardBesarItemList.add(new CardBesarItem(R.drawable.studio, "Studio B", "Rp 200.000 / Jam", "Location", "4.5"));
        cardBesarItemList.add(new CardBesarItem(R.drawable.studio, "Studio C", "Rp 200.000 / Jam", "Location", "4.5"));
        cardBesarItemList.add(new CardBesarItem(R.drawable.studio, "Studio A", "Rp 150.000 / Jam", "Location", "4.5"));
        cardBesarItemList.add(new CardBesarItem(R.drawable.studio, "Studio B", "Rp 200.000 / Jam", "Location", "4.5"));
        cardBesarItemList.add(new CardBesarItem(R.drawable.studio, "Studio C", "Rp 200.000 / Jam", "Location", "4.5"));

        CardBesarAdapter adapterBesar = new CardBesarAdapter(cardBesarItemList);
        recyclerViewBesar.setAdapter(adapterBesar);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_home:
                    return true;
                case R.id.bottom_fafourite:
                    startActivity(new Intent(getApplicationContext(), StudioFavoritActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_history:
                    startActivity(new Intent(getApplicationContext(), HistoryActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_search:
                    startActivity(new Intent(getApplicationContext(), StudioPencarianActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
            }
            return false;
        });
    }
}