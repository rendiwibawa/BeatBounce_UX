package com.example.beatbounce.History;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.beatbounce.Home.HomeActivity;
import com.example.beatbounce.Login.ProfileActivity;
import com.example.beatbounce.PembayaranPilihJam.Pembayaran1Activity;
import com.example.beatbounce.R;
import com.example.beatbounce.SpaceItemDecoration;
import com.example.beatbounce.Studio;
import com.example.beatbounce.StudioAdapter;
import com.example.beatbounce.StudioAdapterMenunggu;
import com.example.beatbounce.StudioFavoritActivity;
import com.example.beatbounce.StudioPencarianActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StudioAdapter studioAdapter;
    private StudioAdapterMenunggu studioAdapterMenunggu;

    private Button btnSelesai;
    private Button btnMenunggu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        btnSelesai = findViewById(R.id.btnSelesai);
        btnMenunggu = findViewById(R.id.btnMenunggu);

        recyclerView = findViewById(R.id.recycler_view_studios);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        studioAdapter = new StudioAdapter(getSelesaiStudios());
        studioAdapterMenunggu = new StudioAdapterMenunggu(getMenungguStudios());
        recyclerView.setAdapter(studioAdapter);

        int spacing = getResources().getDimensionPixelSize(R.dimen.item_spacing);
        recyclerView.addItemDecoration(new SpaceItemDecoration(spacing));

        btnSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update data for "SELESAI" button
                List<Studio> selesaiStudios = getSelesaiStudios();
                studioAdapter.updateData(selesaiStudios);
                recyclerView.setAdapter(studioAdapter); // Ensure RecyclerView uses the correct adapter

                btnSelesai.setBackgroundResource(R.drawable.shape_button_selector);
                btnSelesai.setTextColor(getResources().getColor(R.color.orange));

                btnMenunggu.setBackgroundResource(R.drawable.shape_button_menunggu);
                btnMenunggu.setTextColor(getResources().getColor(R.color.gray));
            }
        });

        btnMenunggu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update data for "MENUNGGU" button
                List<Studio> menungguStudios = getMenungguStudios();
                studioAdapterMenunggu.updateData(menungguStudios);
                recyclerView.setAdapter(studioAdapterMenunggu); // Set RecyclerView adapter to studioAdapterMenunggu

                btnMenunggu.setBackgroundResource(R.drawable.shape_button_selector);
                btnMenunggu.setTextColor(getResources().getColor(R.color.orange));

                btnSelesai.setBackgroundResource(R.drawable.shape_button_menunggu);
                btnSelesai.setTextColor(getResources().getColor(R.color.gray));
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_history);
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

    private List<Studio> getSelesaiStudios() {
        List<Studio> studios = new ArrayList<>();
        // Add data for "SELESAI" button
        studios.add(new Studio(
                "Studio BaletRoom",
                R.drawable.studio_dance_jazz,
                "Bekasi",
                "355.000",
                "4.9"
        ));

        studios.add(new Studio(
                "TI Studio Dance for Ballet",
                R.drawable.studio_dance_ballet,
                "Berlina",
                "345.000",
                "4.8"
        ));
        return studios;
    }

    private List<Studio> getMenungguStudios() {
        List<Studio> studios = new ArrayList<>();
        // Add data for "MENUNGGU" button
        studios.add(new Studio(
                "TI Studio Dance for Ballet",
                R.drawable.studio_dance_ballet,
                "Berlina",
                "345.000",
                "4.8"
        ));

        return studios;
    }
}
