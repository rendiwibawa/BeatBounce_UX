package com.example.yourapp;  // Replace with your actual package name

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

import com.example.beatbounce.R;

public class ProfileActivity extends AppCompatActivity {

    String[] item = {"Indonesia", "English"};
    ArrayAdapter<String> adapterItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil);

        adapterItems = new ArrayAdapter<String>(this, R.layout.l)
        // Find the Spinner by its ID
        Spinner languageSpinner = findViewById(R.id.language_spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.language_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        languageSpinner.setAdapter(adapter);
    }
}
