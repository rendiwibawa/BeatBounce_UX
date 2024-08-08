package com.example.beatbounce;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beatbounce.History.HistoryActivity;
import com.example.beatbounce.Home.HomeActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class StudioPencarianActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StudioPencarianAdapter studioAdapter;
    private Button btnSemua;
    private Button btnTerdekat;
    private Button btnTermurah;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studio_pencarian);

        btnSemua = findViewById(R.id.semua);
        btnTerdekat = findViewById(R.id.terdekat);
        btnTermurah = findViewById(R.id.termurah);

        recyclerView = findViewById(R.id.recycler_view_studios);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        studioAdapter = new StudioPencarianAdapter(getSemuaStudios());
        recyclerView.setAdapter(studioAdapter);

        int spacing = getResources().getDimensionPixelSize(R.dimen.item_spacing);
        recyclerView.addItemDecoration(new SpaceItemDecoration(spacing));

        btnSemua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studioAdapter.updateData(getSemuaStudios());
                btnSemua.setBackgroundResource(R.drawable.shape_button_selector);
                btnSemua.setTextColor(getResources().getColor(R.color.orange));

                btnTerdekat.setBackgroundResource(R.drawable.shape_button_menunggu);
                btnTerdekat.setTextColor(getResources().getColor(R.color.gray));

                btnTermurah.setBackgroundResource(R.drawable.shape_button_menunggu);
                btnTermurah.setTextColor(getResources().getColor(R.color.gray));
            }
        });

        btnTerdekat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studioAdapter.updateData(getTerdekatStudios());
                btnTerdekat.setBackgroundResource(R.drawable.shape_button_selector);
                btnTerdekat.setTextColor(getResources().getColor(R.color.orange));

                btnSemua.setBackgroundResource(R.drawable.shape_button_menunggu);
                btnSemua.setTextColor(getResources().getColor(R.color.gray));

                btnTermurah.setBackgroundResource(R.drawable.shape_button_menunggu);
                btnTermurah.setTextColor(getResources().getColor(R.color.gray));
            }
        });

        btnTermurah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studioAdapter.updateData(getTermurahStudios());
                btnTermurah.setBackgroundResource(R.drawable.shape_button_selector);
                btnTermurah.setTextColor(getResources().getColor(R.color.orange));

                btnSemua.setBackgroundResource(R.drawable.shape_button_menunggu);
                btnSemua.setTextColor(getResources().getColor(R.color.gray));

                btnTerdekat.setBackgroundResource(R.drawable.shape_button_menunggu);
                btnTerdekat.setTextColor(getResources().getColor(R.color.gray));
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_search);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_home:
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
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
                    return true;
            }
            return false;
        });
    }


    private List<Studio> getSemuaStudios() {
        List<Studio> studios = new ArrayList<>();

        studios.add(new Studio(
                "Studio Ballroom Dance - XBY",
                R.drawable.modern_dance,
                "Bekasi",
                "150.000",
                "4.1"
        ));

        studios.add(new Studio(
                "TI Studio Dance for Ballet",
                R.drawable.studio_dance_jazz,
                "Bekasi",
                "345.000",
                "4.3"
        ));

        return studios;
    }

    private List<Studio> getTerdekatStudios() {
        List<Studio> studios = new ArrayList<>();

        studios.add(new Studio(
                "TI Studio Dance for Ballet",
                R.drawable.studio_dance_jazz,
                "Bekasi",
                "345.000",
                "4.3"
        ));

        return studios;
    }

    private List<Studio> getTermurahStudios() {
        List<Studio> studios = new ArrayList<>();

        studios.add(new Studio(
                "Studio Ballroom Dance - XBY",
                R.drawable.studio_dance_jazz,
                "Bekasi",
                "150.000",
                "4.1"
        ));

        studios.add(new Studio(
                "TI Studio Dance for Ballet",
                R.drawable.studio_dance_ballet,
                "Bekasi",
                "345.000",
                "4.3"
        ));

        return studios;
    }
}
