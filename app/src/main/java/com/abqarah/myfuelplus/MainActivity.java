package com.abqarah.myfuelplus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Cari ID yang kita bagi dekat XML tadi
        EditText etPhone = findViewById(R.id.etPhone);
        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = etPhone.getText().toString();

                if (phone.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Sila masukkan nombor telefon", Toast.LENGTH_SHORT).show();
                } else {
                    // ARAHAN: Pindah dari MainActivity ke CalculatorActivity
                    Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
                    startActivity(intent);
                    finish(); // Tutup Login screen supaya bila tekan back tak masuk balik
                }
            }
        });
    }
}