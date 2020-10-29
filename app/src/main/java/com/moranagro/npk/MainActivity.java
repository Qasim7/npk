package com.moranagro.npk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinnerplant;
    String[] plantname = {"Buğda", "Arpa", "Qarğıdalı"};
    TextInputEditText plannedfertility;
    TextView azot, fosfor, kalium, contact;
    double a, f, k, p;
    int number;
    Button calculate;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        toolbar = findViewById(R.id.toolber);
//        toolbar.inflateMenu(R.menu.about);
//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//
//                switch (item.getItemId()){
//                    case R.id.info:
//                        Intent intent = new Intent(getApplicationContext(),About.class);
//                        startActivity(intent);
//                        return true;
//                    default:
//                return false;}
//
//            }
//        });

        plannedfertility = findViewById(R.id.ed_fertility);
        azot = findViewById(R.id.tv_azot);
        fosfor = findViewById(R.id.tv_fosfor);
        kalium = findViewById(R.id.tv_kalium);
        spinnerplant = findViewById(R.id.spinnerplant);
        calculate = findViewById(R.id.calculate);
        contact = findViewById(R.id.contact);
        contact.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:*5656"))));


        spinnerplant.setOnItemSelectedListener(this);

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, plantname);
        spinnerplant.setAdapter(adapter);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(plannedfertility.getText().toString()) || plannedfertility.getText().toString().equals(".")) {

                    Toast.makeText(getApplicationContext(), "Rəqəmi daxil edin", Toast.LENGTH_SHORT).show();
                } else {

                    p = Double.parseDouble(plannedfertility.getText().toString());
                    DecimalFormat decimalFormat = new DecimalFormat();
                    decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
                    decimalFormat.setMaximumIntegerDigits(10);
                    decimalFormat.setMaximumFractionDigits(8);


                    if (number == 0) {

                        a = p * 3.2;
                        f = p * 1.1;
                        k = p * 3.1;
                    }

                    if (number == 1) {

                        a = p * 2.8;
                        f = p * 1.3;
                        k = p * 3.6;
                    }

                    if (number == 2) {

                        a = p * 2.5;
                        f = p * 1.4;
                        k = p * 3.5;

                    }

                    azot.setText(decimalFormat.format(a));
                    fosfor.setText(decimalFormat.format(f));
                    kalium.setText(decimalFormat.format(k));

                }


            }
        });


    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.about, menu);
//        return true;
//
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.info:
//                Intent intent = new Intent(getApplicationContext(),About.class);
//                startActivity(intent);
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        azot.setText("");
        fosfor.setText("");
        kalium.setText("");

        if (position == 0) {
            number = 0;

        }

        if (position == 1) {
            number = 1;

        }

        if (position == 2) {
            number = 2;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        number = 0;
    }
}