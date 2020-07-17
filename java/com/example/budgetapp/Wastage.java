package com.example.budgetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Wastage extends AppCompatActivity implements View.OnClickListener{

    DatabaseHelper dbHelper;
    EditText editTodayWastage;
    Button back, home, save;
    TextView totalTodayWastage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wastage);

        dbHelper = new DatabaseHelper(this);
       // dbHelper.onUpgrade(null, 1, 1);

        editTodayWastage = (EditText) findViewById(R.id.editTodayWastage);

        back = (Button) findViewById(R.id.backBtn);
        back.setOnClickListener(this);

        home = (Button) findViewById(R.id.homeBtn);
        home.setOnClickListener(this);

        save = (Button) findViewById(R.id.saveBtn);
        save.setOnClickListener(this);

        //getting today's total wastage
        totalTodayWastage = (TextView) findViewById(R.id.todayTotalWastage);
        Cursor cursorTodayWastage = dbHelper.getTodaysWastage();
        StringBuilder stringBuilderWastageToday = new StringBuilder();

        while (cursorTodayWastage.moveToNext()){

            stringBuilderWastageToday.append(cursorTodayWastage.getDouble(0));
        }

        double outputWastageToday = Double.parseDouble(stringBuilderWastageToday.toString());
        outputWastageToday = Math.round(outputWastageToday *100)/100.0;

        totalTodayWastage.setText(String.valueOf(outputWastageToday));
    }

        public void saveWastage() {

            if(editTodayWastage.getText().toString() != "") {

                double moneyWastage = Double.parseDouble(editTodayWastage.getText().toString());
                //int totalWastageValue = tryParse(totalTodayWastage.getText().toString());
                //int totalWastageMoney = moneyWastage + totalWastageValue;

                if (moneyWastage >= 0 && moneyWastage < 1000000) {

                    totalTodayWastage.setText(moneyWastage + "");

                    boolean isInsertedWastage = dbHelper.insertWastage(totalTodayWastage.getText().toString());

                    if (isInsertedWastage == true){
                        Toast toast = Toast.makeText(Wastage.this, "Data inserted", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 200);
                        toast.show();
                    }
                    else
                        Toast.makeText(Wastage.this, "Data is not inserted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast toast = Toast.makeText(Wastage.this, "Please insert a positive number, that has less than 7 digits", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 200);
                    toast.show();
                }
            }
        }

    @Override
    public void onClick(View v) {

        Intent intent;

        switch (v.getId()) {

            case R.id.backBtn:
                //onBackPressed();
                intent = new Intent(this, MainActivity2.class);
                startActivity(intent);
                break;

            case R.id.homeBtn:
                intent = new Intent(this, MainActivity2.class);
                startActivity(intent);
                break;

            case R.id.saveBtn:
                saveWastage();
                break;
        }
    }
    private int tryParse(String s){

        try {
            return Integer.parseInt(s);
        }catch (NumberFormatException e){
            return 0;
        }
    }
}
