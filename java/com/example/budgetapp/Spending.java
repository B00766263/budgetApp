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

public class Spending extends AppCompatActivity implements View.OnClickListener{

    DatabaseHelper dbHelper;
    EditText addFood, addClothes, addMedicine, addCar, addBills, addRent, addOther;
    TextView totalToday, totalWeek;
    TextView totalFood, totalClothes, totalMed, totalCar, totalBills, totalRent, totalOther;
    TextView totalWeekFood, totalWeekClothes, totalWeekMed, totalWeekCar, totalWeekBills, totalWeekRent, totalWeekOther;
    Button back, home, save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spending);

        dbHelper = new DatabaseHelper(this);

        addFood = (EditText) findViewById(R.id.addFood);
        addClothes = (EditText) findViewById(R.id.addClothes);
        addMedicine = (EditText) findViewById(R.id.addMedicine);
        addCar = (EditText) findViewById(R.id.addCar);
        addBills = (EditText) findViewById(R.id.addBills);
        addRent = (EditText) findViewById(R.id.addRent);
        addOther = (EditText) findViewById(R.id.addOther);

        back = (Button) findViewById(R.id.backBtn);
        back.setOnClickListener(this);

        home = (Button) findViewById(R.id.homeBtn);
        home.setOnClickListener(this);

        save = (Button) findViewById(R.id.saveBtn);
        save.setOnClickListener(this);
        //saveSpending();

        //getting total food spending for today
        totalFood = (TextView) findViewById(R.id.totalFood);
        Cursor cursorTotalFoodTodaySpending = dbHelper.getTodaysSpendingCategory("FOOD");
        StringBuilder stringBuilderFoodToday = new StringBuilder();

        while (cursorTotalFoodTodaySpending.moveToNext()){

            stringBuilderFoodToday.append(cursorTotalFoodTodaySpending.getDouble(0));
        }

        double outputFoodToday = Double.parseDouble(stringBuilderFoodToday.toString());
        outputFoodToday = Math.round(outputFoodToday *100)/100.0;

        totalFood.setText(String.valueOf(outputFoodToday));

        //getting total clothes/shoes spending for today
        totalClothes = (TextView) findViewById(R.id.totalClothes);

        Cursor cursorTotalClothesTodaySpending = dbHelper.getTodaysSpendingCategory("CLOTHES/SHOES");
        StringBuilder stringBuilderClothesToday = new StringBuilder();

        while (cursorTotalClothesTodaySpending.moveToNext()){

            stringBuilderClothesToday.append(cursorTotalClothesTodaySpending.getDouble(0));
        }

        double outputClothesToday = Double.parseDouble(stringBuilderClothesToday.toString());
        outputClothesToday = Math.round(outputClothesToday *100)/100.0;

        totalClothes.setText(String.valueOf(outputClothesToday));

        //getting total medicine spending for today
        totalMed = (TextView) findViewById(R.id.totalMed);
        Cursor cursorTotalMedTodaySpending = dbHelper.getTodaysSpendingCategory("MEDICINES");
        StringBuilder stringBuilderMedToday = new StringBuilder();

        while (cursorTotalMedTodaySpending.moveToNext()){

            stringBuilderMedToday.append(cursorTotalMedTodaySpending.getDouble(0));
        }

        double outputMedToday = Double.parseDouble(stringBuilderMedToday.toString());
        outputMedToday = Math.round(outputMedToday *100)/100.0;

        totalMed.setText(String.valueOf(outputMedToday));

        //getting total car spending for today
        totalCar = (TextView) findViewById(R.id.totalCar);
        Cursor cursorTotalCarTodaySpending = dbHelper.getTodaysSpendingCategory("CAR");
        StringBuilder stringBuilderCarToday = new StringBuilder();

        while (cursorTotalCarTodaySpending.moveToNext()){

            stringBuilderCarToday.append(cursorTotalCarTodaySpending.getDouble(0));
        }
        double outputCarToday = Double.parseDouble(stringBuilderCarToday.toString());
        outputCarToday = Math.round(outputCarToday *100)/100.0;

        totalCar.setText(String.valueOf(outputCarToday));

        //getting total bills spending for today
        totalBills = (TextView) findViewById(R.id.totalBills);
        Cursor cursorTotalBillsTodaySpending = dbHelper.getTodaysSpendingCategory("BILLS");
        StringBuilder stringBuilderBillsToday = new StringBuilder();

        while (cursorTotalBillsTodaySpending.moveToNext()){

            stringBuilderBillsToday.append(cursorTotalBillsTodaySpending.getDouble(0));
        }

        double outputBillsToday = Double.parseDouble(stringBuilderBillsToday.toString());
        outputBillsToday = Math.round(outputBillsToday *100)/100.0;

        totalBills.setText(String.valueOf(outputBillsToday));

        //getting total rent spending for today
        totalRent = (TextView) findViewById(R.id.totalRent);
        Cursor cursorTotalRentTodaySpending = dbHelper.getTodaysSpendingCategory("RENT");
        StringBuilder stringBuilderRentToday = new StringBuilder();

        while (cursorTotalRentTodaySpending.moveToNext()){

            stringBuilderRentToday.append(cursorTotalRentTodaySpending.getDouble(0));
        }

        double outputRentToday = Double.parseDouble(stringBuilderRentToday.toString());
        outputRentToday = Math.round(outputRentToday *100)/100.0;

        totalRent.setText(String.valueOf(outputRentToday));

        //getting total other spending for today
        totalOther = (TextView) findViewById(R.id.totalOther);
        Cursor cursorTotalOtherTodaySpending = dbHelper.getTodaysSpendingCategory("OTHER");
        StringBuilder stringBuilderOtherToday = new StringBuilder();

        while (cursorTotalOtherTodaySpending.moveToNext()){

            stringBuilderOtherToday.append(cursorTotalOtherTodaySpending.getDouble(0));
        }

        double outputOtherToday = Double.parseDouble(stringBuilderOtherToday.toString());
        outputOtherToday = Math.round(outputOtherToday *100)/100.0;

        totalOther.setText(String.valueOf(outputOtherToday));

        //getting total food spending for current week
        totalWeekFood = (TextView) findViewById(R.id.totalWeekFood);
        Cursor cursorTotalFoodWeekSpending = dbHelper.getCurrentWeekSpendingCategory("FOOD");
        StringBuilder stringBuilderFoodCW = new StringBuilder();

        while (cursorTotalFoodWeekSpending.moveToNext()){

            stringBuilderFoodCW.append(cursorTotalFoodWeekSpending.getDouble(0));
        }

        double outputWeekFood = Double.parseDouble(stringBuilderFoodCW.toString());
        outputWeekFood = Math.round(outputWeekFood *100)/100.0;

        totalWeekFood.setText(String.valueOf(outputWeekFood));

        //getting total clothes/shoes spending for current week
        totalWeekClothes = (TextView) findViewById(R.id.totalWeekClothes);
        Cursor cursorTotalClothesWeekSpending = dbHelper.getCurrentWeekSpendingCategory("CLOTHES/SHOES");
        StringBuilder stringBuilderClothesCW = new StringBuilder();

        while (cursorTotalClothesWeekSpending.moveToNext()){

            stringBuilderClothesCW.append(cursorTotalClothesWeekSpending.getDouble(0));
        }

        double outputWeekClothes = Double.parseDouble(stringBuilderClothesCW.toString());
        outputWeekClothes = Math.round(outputWeekClothes *100)/100.0;

        totalWeekClothes.setText(String.valueOf(outputWeekClothes));

        //getting total medicine spending for current week
        totalWeekMed = (TextView) findViewById(R.id.totalWeekMed);
        Cursor cursorMedWeekSpending = dbHelper.getCurrentWeekSpendingCategory("MEDICINES");
        StringBuilder stringBuilderMedCW = new StringBuilder();

        while (cursorMedWeekSpending.moveToNext()){

            stringBuilderMedCW.append(cursorMedWeekSpending.getDouble(0));
        }

        double outputWeekMed = Double.parseDouble(stringBuilderMedCW.toString());
        outputWeekMed = Math.round(outputWeekMed *100)/100.0;

        totalWeekMed.setText(String.valueOf(outputWeekMed));

        //getting total car spending for current week
        totalWeekCar = (TextView) findViewById(R.id.totalWeekCar);
        Cursor cursorCarWeekSpending = dbHelper.getCurrentWeekSpendingCategory("CAR");
        StringBuilder stringBuilderCarCW = new StringBuilder();

        while (cursorCarWeekSpending.moveToNext()){

            stringBuilderCarCW.append(cursorCarWeekSpending.getDouble(0));
        }

        double outputWeekCar = Double.parseDouble(stringBuilderCarCW.toString());
        outputWeekCar = Math.round(outputWeekCar *100)/100.0;

        totalWeekCar.setText(String.valueOf(outputWeekCar));

        //getting total bills spending for current week
        totalWeekBills = (TextView) findViewById(R.id.totalWeekBills);
        Cursor cursorBillsWeekSpending = dbHelper.getCurrentWeekSpendingCategory("BILLS");
        StringBuilder stringBuilderBillsCW = new StringBuilder();

        while (cursorBillsWeekSpending.moveToNext()){

            stringBuilderBillsCW.append(cursorBillsWeekSpending.getDouble(0));
        }

        double outputWeekBills = Double.parseDouble(stringBuilderBillsCW.toString());
        outputWeekBills = Math.round(outputWeekBills *100)/100.0;

        totalWeekBills.setText(String.valueOf(outputWeekBills));

        //getting total rent spending for current week
        totalWeekRent = (TextView) findViewById(R.id.totalWeekRent);
        Cursor cursorRentWeekSpending = dbHelper.getCurrentWeekSpendingCategory("RENT");
        StringBuilder stringBuilderRentCW = new StringBuilder();

        while (cursorRentWeekSpending.moveToNext()){

            stringBuilderRentCW.append(cursorRentWeekSpending.getDouble(0));
        }

        double outputWeekRent = Double.parseDouble(stringBuilderRentCW.toString());
        outputWeekRent = Math.round(outputWeekRent *100)/100.0;

        totalWeekRent.setText(String.valueOf(outputWeekRent));

        //getting total other spending for current week
        totalWeekOther = (TextView) findViewById(R.id.totalWeekOther);
        Cursor cursorOtherWeekSpending = dbHelper.getCurrentWeekSpendingCategory("OTHER");
        StringBuilder stringBuilderOtherCW = new StringBuilder();

        while (cursorOtherWeekSpending.moveToNext()){

            stringBuilderOtherCW.append(cursorOtherWeekSpending.getDouble(0));
        }

        double outputWeekOther = Double.parseDouble(stringBuilderOtherCW.toString());
        outputWeekOther = Math.round(outputWeekOther *100)/100.0;

        totalWeekOther.setText(String.valueOf(outputWeekOther));

        //getting current week's total spending
        totalWeek = (TextView) findViewById(R.id.totalWeek);
        Cursor cursorWeekSpending = dbHelper.getCurrentWeeksTotalSpending();
        StringBuilder stringBuilderTotalWeek = new StringBuilder();

        while (cursorWeekSpending.moveToNext()){

            stringBuilderTotalWeek.append(cursorWeekSpending.getDouble(0));
        }
        double outputTotalWeek = Double.parseDouble(stringBuilderTotalWeek.toString());
        outputTotalWeek = Math.round(outputTotalWeek *100)/100.0;

        totalWeek.setText(String.valueOf(outputTotalWeek));

        //getting today's total spending
        totalToday = (TextView) findViewById(R.id.totalToday);
        Cursor cursorTodaySpending = dbHelper.getTodaysTotalSpending();
        StringBuilder stringBuilderTotalToday = new StringBuilder();

        while (cursorTodaySpending.moveToNext()){

            stringBuilderTotalToday.append(cursorTodaySpending.getDouble(0));
        }

        double outputTotalToday = Double.parseDouble(stringBuilderTotalToday.toString());
        outputTotalToday = Math.round(outputTotalToday *100)/100.0;

        totalToday.setText(String.valueOf(outputTotalToday));
    }

    //code for saving inserted data into the database
    public void saveSpending() {

                        if(addFood.getText().toString() != ""){

                            double moneyFood = Double.parseDouble(addFood.getText().toString());
                            //int totalFoodValue = tryParse(totalFood.getText().toString());
                            //int totalFoodMoney = moneyFood +  totalFoodValue;

                            if (moneyFood >= 0 && moneyFood < 1000000) {

                                totalFood.setText(moneyFood + "");

                                boolean isInsertedFood = dbHelper.insertSpending(totalFood.getText().toString(), "FOOD");

                                if (isInsertedFood == true) {
                                    Toast toast = Toast.makeText(Spending.this, "Data inserted", Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 200);
                                    toast.show();
                                }
                                else
                                    Toast.makeText(Spending.this, "Data is not inserted", Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast toast = Toast.makeText(Spending.this, "Please insert a positive number, that has less than 7 digits", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 200);
                                toast.show();
                            }
                        }

                        if(addClothes.getText().toString() != "") {

                            double moneyClothes = Double.parseDouble(addClothes.getText().toString());
                            //int totalClothesValue = tryParse(totalClothes.getText().toString());
                            //int totalClothesMoney = moneyClot hes + totalClothesValue;

                            if (moneyClothes >= 0 && moneyClothes < 1000000) {

                                totalClothes.setText(moneyClothes + "");

                                boolean isInsertedClothes = dbHelper.insertSpending(totalClothes.getText().toString(), "CLOTHES/SHOES");

                                if (isInsertedClothes == true){
                                    Toast toast = Toast.makeText(Spending.this, "Data inserted", Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 200);
                                    toast.show();
                                }
                                else
                                    Toast.makeText(Spending.this, "Data is not inserted", Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast toast = Toast.makeText(Spending.this, "Please insert a positive number, that has less than 7 digits", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 200);
                                toast.show();
                            }
                        }

                        if(addMedicine.getText().toString() != "") {

                            double moneyMed = Double.parseDouble(addMedicine.getText().toString());
                            //int totalMedValue = tryParse(totalMed.getText().toString());
                            //int totalMedMoney = moneyMed + totalMedValue;

                            if (moneyMed >= 0 && moneyMed < 1000000) {

                                totalMed.setText(moneyMed + "");

                                boolean isInsertedMed = dbHelper.insertSpending(totalMed.getText().toString(), "MEDICINES");

                                if (isInsertedMed == true){
                                    Toast toast = Toast.makeText(Spending.this, "Data inserted", Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 200);
                                    toast.show();
                                }
                                else
                                    Toast.makeText(Spending.this, "Data is not inserted", Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast toast = Toast.makeText(Spending.this, "Please insert a positive number, that has less than 7 digits", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 200);
                                toast.show();
                            }

                        }

                        if(addCar.getText().toString() != "") {

                            double moneyCar = Double.parseDouble(addCar.getText().toString());
                            //int totalCarValue = tryParse(totalCar.getText().toString());
                            //int totalCarMoney = moneyCar + totalCarValue;

                            if (moneyCar >= 0 && moneyCar < 1000000) {

                                totalCar.setText(moneyCar + "");

                                boolean isInsertedCar = dbHelper.insertSpending(totalCar.getText().toString(), "CAR");

                                if (isInsertedCar == true){
                                    Toast toast = Toast.makeText(Spending.this, "Data inserted", Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 200);
                                    toast.show();
                                }
                                else
                                    Toast.makeText(Spending.this, "Data is not inserted", Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast toast = Toast.makeText(Spending.this, "Please insert a positive number, that has less than 7 digits", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 200);
                                toast.show();
                            }
                        }

                        if(addBills.getText().toString() != "") {

                            double moneyBills = Double.parseDouble(addBills.getText().toString());
                            //int totalBillsValue = tryParse(totalBills.getText().toString());
                            //int totalBillsMoney = moneyBills + totalBillsValue;

                            if (moneyBills >= 0 && moneyBills < 1000000) {

                                totalBills.setText(moneyBills + "");

                                boolean isInsertedBills = dbHelper.insertSpending(totalBills.getText().toString(), "BILLS");

                                if (isInsertedBills == true){
                                    Toast toast = Toast.makeText(Spending.this, "Data inserted", Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 200);
                                    toast.show();
                                }
                                else
                                    Toast.makeText(Spending.this, "Data is not inserted", Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast toast = Toast.makeText(Spending.this, "Please insert a positive number, that has less than 7 digits", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 200);
                                toast.show();
                            }
                        }

                        if(addRent.getText().toString() !="") {

                            double moneyRent = Double.parseDouble(addRent.getText().toString());
                            //int totalRentValue = tryParse(totalRent.getText().toString());
                            //int totalRentMoney = moneyRent + totalRentValue;

                            if (moneyRent >= 0 && moneyRent < 1000000) {

                                totalRent.setText(moneyRent + "");

                                boolean isInsertedRent = dbHelper.insertSpending(totalRent.getText().toString(), "RENT");

                                if (isInsertedRent == true){
                                    Toast toast = Toast.makeText(Spending.this, "Data inserted", Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 200);
                                    toast.show();
                                }
                                else
                                    Toast.makeText(Spending.this, "Data is not inserted", Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast toast = Toast.makeText(Spending.this, "Please insert a positive number, that has less than 7 digits", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 200);
                                toast.show();
                            }
                        }

                        if(addOther.getText().toString() !="") {

                            double moneyOther = Double.parseDouble(addOther.getText().toString());
                            //int totalOtherValue = tryParse(totalOther.getText().toString());
                            //int totalOtherMoney = moneyOther + totalOtherValue;

                            if (moneyOther >= 0 && moneyOther < 1000000) {

                                totalOther.setText(moneyOther + "");

                                boolean isInsertedOther = dbHelper.insertSpending(totalOther.getText().toString(), "OTHER");

                                if (isInsertedOther == true){
                                    Toast toast = Toast.makeText(Spending.this, "Data inserted", Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 200);
                                    toast.show();
                                }
                                else
                                    Toast.makeText(Spending.this, "Data is not inserted", Toast.LENGTH_LONG).show();
                            }
                            else {
                            Toast toast = Toast.makeText(Spending.this, "Please insert a positive number, that has less than 7 digits", Toast.LENGTH_SHORT);
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
                intent = new Intent(this, com.example.budgetapp.MainActivity2.class);
                startActivity(intent);
                break;

            case R.id.homeBtn:
                intent = new Intent(this, com.example.budgetapp.MainActivity2.class);
                startActivity(intent);
                break;

            case R.id.saveBtn:
                saveSpending();
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

    /*dbHelper.addCategory("FOOD");
        dbHelper.addCategory("CLOTHES/SHOES");
        dbHelper.addCategory("MEDICINES");
        dbHelper.addCategory("CAR");
        dbHelper.addCategory("BILLS");
        dbHelper.addCategory("RENT");
        dbHelper.addCategory("OTHER");
*/
}

