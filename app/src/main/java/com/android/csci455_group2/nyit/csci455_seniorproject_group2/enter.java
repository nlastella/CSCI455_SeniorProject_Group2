package com.android.csci455_group2.nyit.csci455_seniorproject_group2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.Context.*;

public class enter extends AppCompatActivity {

    private static final String[] items = {"Cigarettes","Alcohol","Other"};
    private ArrayAdapter<String> adapter;
    private Spinner spin;
    private EditText moneyInput;

    private DatePicker datePicker;
    private Button submit;

    private String item;
    private int money;
    private String date;
    SQLiteDatabase db;

    public static boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        spin = (Spinner)findViewById(R.id.itemSpinner);
        moneyInput = (EditText)findViewById(R.id.moneyTextInput);
        submit = (Button)findViewById(R.id.Submit);
        datePicker=(DatePicker)this.findViewById(R.id.datePicker1);

        db = openOrCreateDatabase("test.db", MODE_PRIVATE, null);


        //db.execSQL("CREATE TABLE demo (item varchar, money int, date varchar)");

        ArrayList<String> itemsArrayList = new ArrayList<>();
        for(String s : items){
            itemsArrayList.add(s);
        }
        
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, itemsArrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);


        spin.setVisibility(View.VISIBLE);
        spin.setMinimumWidth(800);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            spin.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }


        submit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {

                String yearStr = String.valueOf(datePicker.getYear());
                String monthStr = String.valueOf(datePicker.getMonth() + 1);
                String dayStr = String.valueOf(datePicker.getDayOfMonth());

                if (!isNumeric(moneyInput.getText().toString())) {
                    Toast.makeText(enter.this, "Money must be number!!!", Toast.LENGTH_LONG).show();
                }
                else if(moneyInput.getText().toString().isEmpty()){
                    Toast.makeText(enter.this, "Money can not be empty!!!", Toast.LENGTH_LONG).show();
                }

                else {
                    item = spin.getSelectedItem().toString();
                    money = Integer.parseInt(moneyInput.getText().toString());
                    date = yearStr + "/" + monthStr + "/" + dayStr;

                    try {
                        db.execSQL("insert into demo values (?,?,?)", new Object[]{item, money, date});
                    } catch (Exception e) {
                        Log.d("Database:", e.toString());
                    }
                }

                Cursor cursor = db.rawQuery("SELECT * FROM demo", null);

                Log.d("Database:", "item/money/date");
                while (cursor.moveToNext()) {
                    Log.d("Database:", cursor.getString(0) +" || "+ String.valueOf(cursor.getInt(1)) + "||" + cursor.getString(2));
                }
            }
        });

    }

    public void OnDestroy(){
        super.onDestroy();
        db.close();
    }
}