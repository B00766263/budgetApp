package com.example.budgetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChooseTodaySpending extends AppCompatActivity implements View.OnClickListener{

    DatabaseHelper dbHelper;
    TextView moneySpentToday, moneyWastedToday, moneyWellSpentToday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_today_spending);

        dbHelper = new DatabaseHelper(this);
        //dbHelper.onUpgrade(null, 1, 1);

        Button back = (Button) findViewById(R.id.backBtn);
        back.setOnClickListener(this);

        Button home = (Button) findViewById(R.id.homeBtn);
        home.setOnClickListener(this);

        //getting today's total spending
        moneySpentToday = (TextView) findViewById(R.id.moneySpentToday);
        Cursor cursorTodaySpending = dbHelper.getTodaysTotalSpending();
        StringBuilder stringBuilderTotalToday = new StringBuilder();

        while (cursorTodaySpending.moveToNext()){

            stringBuilderTotalToday.append(cursorTodaySpending.getDouble(0));
        }

        double outputSpentToday = Double.parseDouble(stringBuilderTotalToday.toString());
        outputSpentToday = Math.round(outputSpentToday *100)/100.0;

        moneySpentToday.setText(String.valueOf(outputSpentToday));
        //String spentToday= stringBuilderTotalToday.toString();
        //moneySpentToday.setText(spentToday);

        //getting today's total wastage
        moneyWastedToday = (TextView) findViewById(R.id.moneyWastedToday);
        Cursor cursorTodayWastage = dbHelper.getTodaysWastage();
        StringBuilder stringBuilderWastageToday = new StringBuilder();

        while (cursorTodayWastage.moveToNext()){

            stringBuilderWastageToday.append(cursorTodayWastage.getDouble(0));
        }

        double outputWastageToday = Double.parseDouble(stringBuilderWastageToday.toString());
        outputWastageToday = Math.round(outputWastageToday *100)/100.0;

        moneyWastedToday.setText(String.valueOf(outputWastageToday));
        //String wastedToday = stringBuilderWastageToday.toString();
        //moneyWastedToday.setText(wastedToday);

       //getting today's well spent money
        moneyWellSpentToday = (TextView) findViewById(R.id.moneyWellSpentToday);
        double moneyWellSpent = outputSpentToday - outputWastageToday;
        moneyWellSpent = Math.round(moneyWellSpent *100)/100.0;
        //double moneyWellSpent = tryParse(spentToday) - tryParse(wastedToday);

        moneyWellSpentToday.setText(moneyWellSpent + "");
    }

    @Override
    public void onClick(View v) {

        Intent intent;

        switch (v.getId()) {

            case R.id.backBtn:
                onBackPressed();
                break;

            case R.id.homeBtn:
                intent = new Intent(this, com.example.budgetapp.MainActivity2.class);
                startActivity(intent);
                break;
        }
    }
    private double tryParse(String s){

        try {
            return Double.parseDouble(s);
        }catch (NumberFormatException e){
            return 0;
        }
    }
}
