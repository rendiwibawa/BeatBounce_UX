package com.example.beatbounce.Home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.beatbounce.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        RecyclerView recyclerViewBesar = findViewById(R.id.recyclerViewBesar);
        recyclerViewBesar.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<String> genres = Arrays.asList("Balet", "Dance", "Gymnastic", "Samba", "Tradisional");
        List<Integer> facilities = Arrays.asList(R.drawable.lighting, R.drawable.mushola, R.drawable.sound, R.drawable.wifi);

        List<Studio> studiosBesar = new ArrayList<>();
        studiosBesar.add(new Studio(R.drawable.studio, "Studio A", "Rp 150.000 / Jam", "Owner1", facilities, genres, 4.5, "Bogor"));
        studiosBesar.add(new Studio(R.drawable.studio, "Studio B", "Rp 200.000 / Jam", "Owner2", facilities, genres, 4.5, "Bogor"));
        studiosBesar.add(new Studio(R.drawable.studio, "Studio C", "Rp 200.000 / Jam", "Owner3", facilities, genres, 4.4, "Bogor"));
        studiosBesar.add(new Studio(R.drawable.studio, "Studio D", "Rp 150.000 / Jam", "Owner4", facilities, genres, 4.3, "Bogor"));
        studiosBesar.add(new Studio(R.drawable.studio, "Studio E", "Rp 200.000 / Jam", "Owner5", facilities, genres, 4.9, "Bogor"));
        studiosBesar.add(new Studio(R.drawable.studio, "Studio F", "Rp 200.000 / Jam", "Owner6", facilities, genres, 4.8, "Bogor"));

        StudioAdapter adapterBesar = new StudioAdapter(studiosBesar);
        recyclerViewBesar.setAdapter(adapterBesar);


//        besar punya
//        RecyclerView recyclerViewBesar = findViewById(R.id.recyclerViewBesar);
//        recyclerViewBesar.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//
//        List<CardBesarItem> cardBesarItemList = new ArrayList<>();
//        cardBesarItemList.add(new CardBesarItem(R.drawable.studio, "Studio A", "Rp 150.000 / Jam", "Location", "4.5"));
//        cardBesarItemList.add(new CardBesarItem(R.drawable.studio, "Studio B", "Rp 200.000 / Jam", "Location", "4.5"));
//        cardBesarItemList.add(new CardBesarItem(R.drawable.studio, "Studio C", "Rp 200.000 / Jam", "Location", "4.5"));
//        cardBesarItemList.add(new CardBesarItem(R.drawable.studio, "Studio A", "Rp 150.000 / Jam", "Location", "4.5"));
//        cardBesarItemList.add(new CardBesarItem(R.drawable.studio, "Studio B", "Rp 200.000 / Jam", "Location", "4.5"));
//        cardBesarItemList.add(new CardBesarItem(R.drawable.studio, "Studio C", "Rp 200.000 / Jam", "Location", "4.5"));
//
//        CardBesarAdapter adapterBesar = new CardBesarAdapter(cardBesarItemList);
//        recyclerViewBesar.setAdapter(adapterBesar);





//    kecil punya

        RecyclerView recyclerView = findViewById(R.id.newPlaceRecyclerView);
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


    }
}