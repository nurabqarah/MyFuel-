package com.abqarah.myfuelplus;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {

    private EditText etPrice, etUsage;
    private RadioGroup rgFuelType;
    private CheckBox cbEligible;
    private Button btnCalculate;
    private TextView tvTotalCost, tvRebate, tvFinalSaving;
    private LinearLayout layoutResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        etPrice = findViewById(R.id.etPrice);
        etUsage = findViewById(R.id.etUsage);
        rgFuelType = findViewById(R.id.rgFuelType);
        cbEligible = findViewById(R.id.cbEligible);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvTotalCost = findViewById(R.id.tvTotalCost);
        tvRebate = findViewById(R.id.tvRebate);
        tvFinalSaving = findViewById(R.id.tvFinalSaving);
        layoutResult = findViewById(R.id.layoutResult);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateFuel();
            }
        });
    }

    private void calculateFuel() {
        String priceStr = etPrice.getText().toString();
        String usageStr = etUsage.getText().toString();

        if (priceStr.isEmpty() || usageStr.isEmpty()) {
            Toast.makeText(this, "Sila masukkan harga dan penggunaan", Toast.LENGTH_SHORT).show();
            return;
        }

        double price = Double.parseDouble(priceStr);
        double usage = Double.parseDouble(usageStr);
        boolean isEligible = cbEligible.isChecked();

        // Cek jenis petrol
        int selectedId = rgFuelType.getCheckedRadioButtonId();
        boolean isRon95 = (selectedId == R.id.rbRon95);

        // FORMULA DALAM ASSIGNMENT
        // 1. Total petrol cost
        double totalCost = usage * price;

        // 2. BUDI Rebate (Hanya untuk RON95 jika eligible)
        double rebate = 0;
        if (isRon95 && isEligible) {
            rebate = usage * 1.99;
        }

        // 3. Total Saving (Final Payable Amount)
        double totalSaving = totalCost - rebate;

        // Papar keputusan
        layoutResult.setVisibility(View.VISIBLE);
        tvTotalCost.setText(String.format("Total Petrol Cost: RM %.2f", totalCost));
        tvRebate.setText(String.format("BUDI Rebate: RM %.2f", rebate));
        tvFinalSaving.setText(String.format("Total Saving (Final): RM %.2f", totalSaving));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_about) {
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}