package com.example.budgetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseWeekSpending extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_week_spending);

        Button currentWeek = (Button) findViewById(R.id.currentMonthBtn);
        currentWeek.setOnClickListener(this);

        Button lastWeek = (Button) findViewById(R.id.lastMonthBtn);
        lastWeek.setOnClickListener(this);

        Button back = (Button) findViewById(R.id.backBtn);
        back.setOnClickListener(this);

        Button home = (Button) findViewById(R.id.homeBtn);
        home.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intent;

        switch (v.getId()) {

            case R.id.currentMonthBtn:
                intent = new Intent(this, CurrentWeek.class);
                startActivity(intent);
                break;

            case R.id.lastMonthBtn:
                intent = new Intent(this, LastWeek.class);
                startActivity(intent);
                break;

            case R.id.backBtn:
                onBackPressed();
                break;

            case R.id.homeBtn:
                intent = new Intent(this, com.example.budgetapp.MainActivity2.class);
                startActivity(intent);
                break;
        }
    }
}
