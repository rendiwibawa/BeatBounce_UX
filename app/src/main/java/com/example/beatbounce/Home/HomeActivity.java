package com.example.beatbounce.Home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.beatbounce.History.HistoryActivity;
import com.example.beatbounce.Login.ProfileActivity;
import com.example.beatbounce.R;
import com.example.beatbounce.StudioFavoritActivity;
import com.example.beatbounce.StudioPencarianActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout searchLocation;
    private TextView userTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        searchLocation = findViewById(R.id.searchLocation);
        userTextView = findViewById(R.id.userTextView);

        SharedPreferences sharedPref = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String userName = sharedPref.getString("FULL_NAME", "User");

        userTextView.setText(userName);

        searchLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, StudioPencarianActivity.class);
                startActivity(intent);
            }
        });

        searchLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, StudioPencarianActivity.class);
                startActivity(intent);
            }
        });



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

        RecyclerView recyclerViewKecil = findViewById(R.id.newPlaceRecyclerView);
        recyclerViewKecil.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<CardItem> cardItemList = new ArrayList<>();
        cardItemList.add(new CardItem(R.drawable.studio, "Studio A", "Rp 150.000 / Jam"));
        cardItemList.add(new CardItem(R.drawable.studio, "Studio B", "Rp 200.000 / Jam"));
        cardItemList.add(new CardItem(R.drawable.studio, "Studio C", "Rp 200.000 / Jam"));
        cardItemList.add(new CardItem(R.drawable.studio, "Studio A", "Rp 150.000 / Jam"));
        cardItemList.add(new CardItem(R.drawable.studio, "Studio B", "Rp 200.000 / Jam"));
        cardItemList.add(new CardItem(R.drawable.studio, "Studio C", "Rp 200.000 / Jam"));

        CardAdapter adapter = new CardAdapter(cardItemList);
        recyclerViewKecil.setAdapter(adapter);


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

                case R.id.botton_account:
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
            }
            return false;
        });
    }
}