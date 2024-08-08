package com.example.beatbounce.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Patterns;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.example.beatbounce.Home.HomeActivity;
import com.example.beatbounce.R;
import com.example.beatbounce.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
    }

    private void initView() {
        SpannableString spannableString = new SpannableString(getString(R.string.text_login));
        spannableString.setSpan(new UnderlineSpan(), 18, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(ResourcesCompat.getColor(getResources(), R.color.orange, null)), 18, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 18, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new CustomTypefaceSpan(ResourcesCompat.getFont(this, R.font.poppins_bold)), 18, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.textLogin.setText(spannableString);

        binding.textLogin.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        binding.btnRegister.setOnClickListener(v -> {
            resetError();
            String name = binding.etName.getText().toString();
            String email = binding.etEmail.getText().toString();
            String password = binding.etPassword.getText().toString();
            boolean cbTerms = binding.cbTerms.isChecked();
            boolean isValid = true;

            if (name.isEmpty()) {
                binding.etName.setError(getString(R.string.error_name_empty));
                isValid = false;
            }

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

            if (!cbTerms) {
                binding.cbTerms.setError(getString(R.string.error_terms_unchecked));
                isValid = false;
            }

            if (isValid) {
                Toast.makeText(this, getString(R.string.text_register_success), Toast.LENGTH_SHORT).show();

                SharedPreferences sharedPref = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("FULL_NAME", name);
                editor.putString("EMAIL", email);
                editor.apply();

                Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
//                intent.putExtra("FULL_NAME", name);
//                intent.putExtra("EMAIL", email);

                startActivity(intent);

                finish();

            }
        });
    }

    private void resetError() {
        binding.etName.setError(null);
        binding.etEmail.setError(null);
        binding.etPassword.setError(null);
        binding.cbTerms.setError(null);
    }
}