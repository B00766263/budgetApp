package com.example.budgetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseMonthSpending extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_month_spending);

        Button currentMonth = (Button) findViewById(R.id.currentMonthBtn);
        currentMonth.setOnClickListener(this);

        Button lastMonth = (Button) findViewById(R.id.lastMonthBtn);
        lastMonth.setOnClickListener(this);

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
                intent = new Intent(this, CurrentMonth.class);
                startActivity(intent);
                break;

            case R.id.lastMonthBtn:
                intent = new Intent(this, LastMonth.class);
                startActivity(intent);
                break;

            case R.id.backBtn:
                onBackPressed();
                break;

            case R.id.homeBtn:
                intent = new Intent(this, com.example.budgetapp.MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
