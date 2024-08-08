package com.example.beatbounce;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.beatb.R;
import com.example.beatb.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;

    private TextView tvChangePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = new Toolbar(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_profile);

        tvChangePassword = findViewById(R.id.tv_change_password);

        TextView input_name = findViewById(R.id.et_name);
        TextView email_user = findViewById(R.id.et_email);
//        TextView input_nick_name = findViewById(R.id.et_name);


        Intent intent = getIntent();
        String full_name = intent.getStringExtra("FULL_NAME");
        String email_users = intent.getStringExtra("EMAIL");
        String nick_name = intent.getStringExtra("EMAIL_PREFIX");
        String email_user_login = intent.getStringExtra("EMAIL");

        if (full_name != null && !full_name.isEmpty()) {
            input_name.setText(full_name);
        } else if (nick_name != null && !nick_name.isEmpty()) {
            input_name.setText(nick_name);
        }
        email_user.setText(email_users);
        email_user.setText(email_user_login);

        initView();
    }

    private void initView() {
        binding.btnSave.setOnClickListener(v -> {
            String name = binding.etName.getText().toString();
            String email = binding.etEmail.getText().toString();
            boolean isValid = true;

            if (email.isEmpty()) {
                binding.etEmail.setError(getString(R.string.error_email_empty));
                isValid = false;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.etEmail.setError(getString(R.string.error_email_invalid));
                isValid = false;
            }

            if (name.isEmpty()) {
                binding.etName.setError(getString(R.string.error_name_empty));
                isValid = false;
            }

            if (isValid) {
                Toast.makeText(this, getString(R.string.text_profile_saved), Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnBack.setOnClickListener(v -> finish());

        tvChangePassword.setOnClickListener(v -> {
            Intent intent = new Intent(this, ChangePasswordActivity.class);
            startActivity(intent);
        });
    }
}