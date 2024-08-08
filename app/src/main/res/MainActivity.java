package com.example.beatb;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Patterns;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.example.beatb.databinding.ActivityLoginBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
    }

    private void initView() {
        SpannableString spannableString = new SpannableString(getString(R.string.text_register));
        spannableString.setSpan(new UnderlineSpan(), 18, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(ResourcesCompat.getColor(getResources(), R.color.orange, null)), 18, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 18, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new CustomTypefaceSpan(ResourcesCompat.getFont(this, R.font.poppins_bold)), 18, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.textRegister.setText(spannableString);

        binding.btnLogin.setOnClickListener(this::onClick);

        binding.textRegister.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void resetError() {
        binding.etEmail.setError(null);
        binding.etPassword.setError(null);
    }

    private void onClick(View v) {
        resetError();
        String email = binding.etEmail.getText().toString();
        String password = binding.etPassword.getText().toString();
        boolean isValid = true;

        if (email.isEmpty()) {
            binding.etEmail.setError(getString(R.string.error_email_empty));
            isValid = false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etEmail.setError(getString(R.string.error_email_invalid));
            isValid = false;
        }

        if (password.isEmpty()) {
            binding.etPassword.setError(getString(R.string.error_password_empty));
            isValid = false;
        }

        if (password.length() < 6) {
            binding.etPassword.setError(getString(R.string.error_password_short));
            isValid = false;
        }

        if (isValid) {
//            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
//            startActivity(intent);

            String[] emailParts = email.split("@gmail.com");
            if (emailParts.length > 0) {
                String emailPrefix = emailParts[0];

                // Kirim emailPrefix ke aktivitas lain
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtra("EMAIL_PREFIX", emailPrefix);
                intent.putExtra("EMAIL", email);
                startActivity(intent);
            }
        }
    }
}