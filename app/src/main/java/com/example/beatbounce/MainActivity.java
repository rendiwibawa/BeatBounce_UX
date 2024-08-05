package com.example.beatbounce;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ganti_password);

        // Find the button by its ID
        Button showDialogButton = findViewById(R.id.button_show_dialog);

        // Set an OnClickListener for the button
//        showDialogButton.setOnClickListener(v -> showResetPasswordDialog());
    }
//    private void showResetPasswordDialog() {
//        // Inflate the dialog layout
//        LayoutInflater inflater = getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.dialog_reset_password, null);
//
//        // Create the AlertDialog
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
//        dialogBuilder.setView(dialogView);
//        AlertDialog alertDialog = dialogBuilder.create();
//
//        // Set the buttons' click listeners
//        Button continueButton = dialogView.findViewById(R.id.button_continue);
//        Button cancelButton = dialogView.findViewById(R.id.button_cancel);
//
//        continueButton.setOnClickListener(v -> {
//            // Handle continue action
//            alertDialog.dismiss();
//        });
//
//        cancelButton.setOnClickListener(v -> {
//            // Handle cancel action
//            alertDialog.dismiss();
//        });
//
//        // Show the dialog
//        alertDialog.show();
//    }
}