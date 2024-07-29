package com.example.beatbounce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

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
    }
}