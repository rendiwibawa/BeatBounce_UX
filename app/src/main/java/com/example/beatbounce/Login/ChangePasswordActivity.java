package com.example.beatbounce.Login;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;


import androidx.appcompat.app.AppCompatActivity;

import com.example.beatbounce.R;
import com.example.beatbounce.databinding.ActivityChangePasswordBinding;


public class ChangePasswordActivity extends AppCompatActivity {

    // Membuat variabel binding dengan tipe data ActivityChangePasswordBinding
    private ActivityChangePasswordBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = new Toolbar(this);
        toolbar.setTitle("Pilih Jam");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Menginisialisasi variabel binding dengan memanggil metode inflate() dari ActivityChangePasswordBinding
        binding = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        // Menampilkan layout yang diinginkan
        setContentView(binding.getRoot());

        initView();
    }

    private void initView() {
        // Menambahkan aksi klik pada TextView tvForgotPassword
        binding.tvForgotPassword.setOnClickListener(v -> {
            // Membuat objek dialog dengan tipe data ResetPasswordDialog
            ResetPasswordDialog dialog = new ResetPasswordDialog();

            // Menampilkan dialog
            dialog.show(getSupportFragmentManager(), dialog.getTag());
        });


        // Menambahkan aksi klik pada Button btnSave
        binding.btnSave.setOnClickListener(v -> {
            // Mengambil nilai dari EditText etCurrentPassword, etNewPassword, dan etPasswordConfirmation
            String currentPassword = binding.etCurrentPassword.getText().toString();
            String newPassword = binding.etNewPassword.getText().toString();
            String confirmPassword = binding.etPasswordConfirmation.getText().toString();

            // Membuat variabel isValid dengan tipe data boolean dan memberikan nilai true
            boolean isValid = true;

            // Mengecek apakah nilai dari currentPassword kosong
            if (currentPassword.isEmpty()) {
                // Menampilkan pesan error pada EditText etCurrentPassword
                binding.etCurrentPassword.setError(getString(R.string.error_password_empty));

                // Mengubah nilai isValid menjadi false
                isValid = false;
            }

            // Mengecek apakah nilai dari newPassword kosong
            if (newPassword.isEmpty()) {
                // Menampilkan pesan error pada EditText etNewPassword
                binding.etNewPassword.setError(getString(R.string.error_password_empty));

                // Mengubah nilai isValid menjadi false
                isValid = false;
            }

            // Mengecek apakah nilai dari confirmPassword kosong
            if (confirmPassword.isEmpty()) {
                // Menampilkan pesan error pada EditText etPasswordConfirmation
                binding.etPasswordConfirmation.setError(getString(R.string.error_password_empty));

                // Mengubah nilai isValid menjadi false
                isValid = false;
            }

            // Mengecek apakah nilai dari newPassword tidak sama dengan nilai dari confirmPassword
            if (!newPassword.equals(confirmPassword)) {
                // Menampilkan pesan error pada EditText etPasswordConfirmation
                binding.etPasswordConfirmation.setError(getString(R.string.error_password_not_match));

                // Mengubah nilai isValid menjadi false
                isValid = false;
            }

            // Mengecek apakah nilai isValid bernilai true
            if (isValid) {
                // Menampilkan pesan sukses
                Toast.makeText(this, getString(R.string.change_password_success), Toast.LENGTH_SHORT).show();

                // Menutup halaman
                finish();
            }
        });

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