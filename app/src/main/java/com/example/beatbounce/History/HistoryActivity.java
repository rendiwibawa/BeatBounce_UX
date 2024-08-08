package com.example.beatbounce.History;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.beatbounce.Home.HomeActivity;
import com.example.beatbounce.PembayaranPilihJam.Pembayaran1Activity;
import com.example.beatbounce.R;
import com.example.beatbounce.SpaceItemDecoration;
import com.example.beatbounce.Studio;
import com.example.beatbounce.StudioAdapter;
import com.example.beatbounce.StudioFavoritActivity;
import com.example.beatbounce.StudioPencarianActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StudioAdapter studioAdapter;

    private Button btnMenunggu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        btnMenunggu = findViewById(R.id.btnMenunggu);

        btnMenunggu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryActivity.this, Pembayaran1Activity.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.recycler_view_studios);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        studioAdapter = new StudioAdapter(getStudios());
        recyclerView.setAdapter(studioAdapter);

        int spacing = getResources().getDimensionPixelSize(R.dimen.item_spacing);
        recyclerView.addItemDecoration(new SpaceItemDecoration(spacing));

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
            }
            return false;
        });
    }

    private List<Studio> getStudios() {
        List<Studio> studios = new ArrayList<>();

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

}
