package com.example.budgetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Statistics extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        Button todaySpending = (Button) findViewById(R.id.todayBtn);
        todaySpending.setOnClickListener(this);

        Button weekSpending = (Button) findViewById(R.id.weekButton);
        weekSpending.setOnClickListener(this);

        Button monthSpending = (Button) findViewById(R.id.monthButton);
        monthSpending.setOnClickListener(this);

        Button back = (Button) findViewById(R.id.backBtn);
        back.setOnClickListener(this);

        Button home = (Button) findViewById(R.id.homeBtn);
        home.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent intent;

        switch (v.getId()) {

            case R.id.todayBtn:
                intent = new Intent(this, ChooseTodaySpending.class);
                startActivity(intent);
                break;

            case R.id.weekButton:
                intent = new Intent(this, ChooseWeekSpending.class);
                startActivity(intent);
                break;

            case R.id.monthButton:
                intent = new Intent(this, ChooseMonthSpending.class);
                startActivity(intent);
                break;

            case R.id.backBtn:
                onBackPressed();

            case R.id.homeBtn:
                intent = new Intent(this, MainActivity2.class);
                startActivity(intent);
                break;
        }
    }
}
