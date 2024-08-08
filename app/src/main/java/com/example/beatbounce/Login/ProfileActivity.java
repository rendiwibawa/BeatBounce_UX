package com.example.beatbounce.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.beatbounce.R;
import com.example.beatbounce.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;
    private TextView tvChangePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Setup toolbar
//        android.widget.Toolbar toolbar = new Toolbar(this);
//        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tvChangePassword = findViewById(R.id.tv_change_password);

        // Load and set user data
        loadUserData();

        // Initialize views and set listeners
        initView();
    }

    private void loadUserData() {
        // Get data from SharedPreferences
        SharedPreferences sharedPref = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String fullName = sharedPref.getString("FULL_NAME", "");
        String email = sharedPref.getString("EMAIL", "");
        String nickname = sharedPref.getString("EMAIL_PREFIX", "");

        // Set data to TextViews
        binding.etName.setText(fullName.isEmpty() ? nickname : fullName);
        binding.etEmail.setText(email);
    }

    private void initView() {
        binding.btnSave.setOnClickListener(v -> saveProfile());
        binding.btnBack.setOnClickListener(v -> finish());
        tvChangePassword.setOnClickListener(v -> startActivity(new Intent(this, ChangePasswordActivity.class)));
    }

    private void saveProfile() {
        String name = binding.etName.getText().toString();
        String email = binding.etEmail.getText().toString();

        if (!validateInput(name, email)) {
            return;
        }

        String emailPrefix = email.split("@")[0];
        SharedPreferences sharedPref = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("FULL_NAME", name);
        editor.putString("EMAIL", email);
        editor.putString("EMAIL_PREFIX", emailPrefix);
        editor.apply();

        // Show success message
        Toast.makeText(ProfileActivity.this, "Profile saved successfully", Toast.LENGTH_SHORT).show();
    }

    private boolean validateInput(String name, String email) {
        boolean isValid = true;

        if (name.isEmpty()) {
            binding.etName.setError(getString(R.string.error_name_empty));
            isValid = false;
        }

        if (email.isEmpty()) {
            binding.etEmail.setError(getString(R.string.error_email_empty));
            isValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etEmail.setError(getString(R.string.error_email_invalid));
            isValid = false;
        }

        if (!isValid) {
            // Show error message
            Toast.makeText(ProfileActivity.this, "Failed to save profile", Toast.LENGTH_SHORT).show();
        }

        return isValid;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
