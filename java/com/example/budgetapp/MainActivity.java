package com.example.budgetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button spending = (Button) findViewById(R.id.btnSpending);
        spending.setOnClickListener(this);

        Button wastage = (Button) findViewById(R.id.btnWastage);
        wastage.setOnClickListener(this);

        Button statistics = (Button) findViewById(R.id.btnStatistics);
        statistics.setOnClickListener(this);

        Button household = (Button) findViewById(R.id.btnHousehold);
        household.setOnClickListener(this);

        Button planner = (Button) findViewById(R.id.btnPlanner);
        planner.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intent;

        switch (v.getId()) {

            case R.id.btnSpending:
                intent = new Intent(this, Spending.class);
                startActivity(intent);
                break;

            case R.id.btnWastage:
                intent = new Intent(this, Wastage.class);
                startActivity(intent);
                break;

            case R.id.btnStatistics:
                intent = new Intent(this, Statistics.class);
                startActivity(intent);
                break;

            case R.id.btnHousehold:
                intent = new Intent(this, Household.class);
                startActivity(intent);
                break;

            case R.id.btnPlanner:
                intent = new Intent(this, Planner.class);
                startActivity(intent);
                break;

        }
    }
}
