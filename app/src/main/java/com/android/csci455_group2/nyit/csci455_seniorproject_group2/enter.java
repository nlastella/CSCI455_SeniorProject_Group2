package com.android.csci455_group2.nyit.csci455_seniorproject_group2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
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
import java.io.*;
import static android.content.Context.*;

public class enter extends AppCompatActivity {

    static ArrayList<recordableItem> itemList = new ArrayList<>();
    static ArrayList<String> itemsArrayList = new ArrayList<>();
    static ArrayList<Transaction> itemTrans = new ArrayList<>();

    private ArrayAdapter<String> adapter;
    private Spinner spin;

    private EditText moneyInput;
    private DatePicker datePicker;
    private Button submit;
    private Button bView;
    private Button bCompare;

    private String item;
    private int amount;
    private String date;
    private int arrayLoc;

    private final String savedItems = "item_data";
    private final String savedTransactions = "transaction_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        /*itemList = new ArrayList<>();
        itemList.add(new recordableItem("temp", 0, 0));
        if(!readItemFileToArray())
            writeItemFile();

        itemTrans = new ArrayList<>();
        itemTrans.add(new Transaction(0, 0, "0/0/0"));
        if(!readTransactionFileToArray())
            writeTransactionFile();

        final String PREFS_NAME = "MyPrefsFile";
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        if (settings.getBoolean("my_first_time", true)) {
            Log.d("Comments", "First time");
            createItemList();
            writeItemFile();
            writeTransactionFile();
            settings.edit().putBoolean("my_first_time", false).apply();
        }else{
            readItemFileToArray();
            readTransactionFileToArray();
        }*/

        if(!checkItemListMade())
            createItemList();


        spin = (Spinner)findViewById(R.id.itemSpinner);
        moneyInput = (EditText)findViewById(R.id.moneyTextInput);
        submit = (Button)findViewById(R.id.Submit);
        datePicker = (DatePicker)this.findViewById(R.id.datePicker1);
        bView = (Button) findViewById(R.id.buttonView);
        bCompare = (Button) findViewById(R.id.buttonCompare);
        
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, itemsArrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setVisibility(View.VISIBLE);
        spin.setMinimumWidth(800);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            spin.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }

        bView.setOnClickListener(new OnClickListener(){
            public void onClick(View v) {
                startActivity(new Intent(enter.this, UserDataViewActivity.class));
            }
        });

        bCompare.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(enter.this, ComparisonPage.class));
            }
        });


        submit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {

                String yearStr = String.valueOf(datePicker.getYear());
                String monthStr = String.valueOf(datePicker.getMonth() + 1);
                String dayStr = String.valueOf(datePicker.getDayOfMonth());

                if (!isNumeric(moneyInput.getText().toString())) {
                    Toast.makeText(enter.this, "Amount purchased must be a number!!!", Toast.LENGTH_LONG).show();
                } else if (moneyInput.getText().toString().isEmpty()) {
                    Toast.makeText(enter.this, "You can't purchase nothing!!!", Toast.LENGTH_LONG).show();
                } else {
                    item = spin.getSelectedItem().toString();
                    amount = Integer.parseInt(moneyInput.getText().toString());
                    date = yearStr + "/" + monthStr + "/" + dayStr;
                    arrayLoc = findItemLoc(item);

                    itemList.get(arrayLoc).purchaseItem(amount);
                    itemTrans.add(new Transaction(arrayLoc, amount, date));
                    //writeTransactionFile();

                    startActivity(new Intent(enter.this, UserDataViewActivity.class));
                }


            }
        });
    }

    public static boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public void createItemList(){
        itemList.clear();
        itemList.add(new recordableItem("Alcohol", 12, 0));
        itemList.add(new recordableItem("Cigarettes", 10, 0));
        itemList.add(new recordableItem("Other", 10, 0));
        itemList.add(new recordableItem("Other", 5, 0));
        itemList.add(new recordableItem("Other", 1, 0));

        itemsArrayList.clear();
        for(int i = 0; i < itemList.size(); i++){
            itemsArrayList.add(itemList.get(i).getListName());
        }
    }

    public boolean checkItemListMade(){
        if(itemList.size() == 0){
            return false;
        }
        return true;
    }

    public int findItemLoc(String checkName){
        for(int i = 0; i < itemList.size(); i++){
            if(itemList.get(i).getListName().equals(checkName)){
                return i;
            }
        }
        return -1;
    }

    public boolean readItemFileToArray(){
        String fileData = readFile(savedItems);
        String[] entries = null;
        if(fileData != null){
            entries = fileData.split("|");
        }

        String[] entry = null;

        if(entries != null) {
            for (String s: entries) {
                entry = s.split(",");
                itemList.add(new recordableItem(entry[0], Double.parseDouble(entry[1]), Integer.parseInt(entry[2])));
            }
            return true;
        }
        return false;
    }

    public boolean writeItemFile(){
        FileOutputStream fos;
        String separator = "|";

        if(itemList.size() > 0){
            try{
                fos = openFileOutput(savedItems, Context.MODE_PRIVATE);
                for(int i = 0; i < itemList.size(); i++){
                    fos.write(itemList.get(i).toSaveable().getBytes());
                    fos.write(separator.getBytes());
                }
                fos.close();
            }catch(Exception e){
                //some exception here
            }
            return true;
        }
        return false;

    }

    public boolean readTransactionFileToArray(){
        String fileData = readFile(savedTransactions);
        String[] entries = null;
        String[] entry = {"","",""};

        if(fileData != null)
            entries = fileData.split("|");

        if(entries != null) {
            for (int i = 0; i < entries.length-1; i++) {
                entry = entries[i].split(",");
                itemTrans.add(new Transaction(Integer.parseInt(entry[0]), Integer.parseInt(entry[1]), entry[2]));
            }
            return true;
        }
        return false;
    }

    public boolean writeTransactionFile(){
        FileOutputStream fos;
        String separator = "|";

        if(itemTrans.size() > 0){
            try{
                fos = openFileOutput(savedTransactions, Context.MODE_PRIVATE);
                for(int i = 0; i < itemTrans.size(); i++){
                    fos.write(itemTrans.get(i).toSaveable().getBytes());
                    fos.write(separator.getBytes());
                }
                fos.close();
            }catch(Exception e){
                //some exception here
            }
        }
         return false;
    }

    public String readFile(String fileName){
        FileInputStream fis;
        String fileData = "";

        try{
            fis = openFileInput(fileName);
            if(fis != null){
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
                String readString = "";
                StringBuilder sb = new StringBuilder();

                while((readString = br.readLine()) != null){
                    sb.append(readString);
                }

                fis.close();
                fileData = sb.toString();
            }
        }catch(Exception e){
            //some exception here
        }
        return fileData;
    }


    public void OnDestroy(){
        writeItemFile();
        writeTransactionFile();
        super.onDestroy();
    }
}