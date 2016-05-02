package com.android.csci455_group2.nyit.csci455_seniorproject_group2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import static com.android.csci455_group2.nyit.csci455_seniorproject_group2.UserDataViewActivity.*;

public class Comparison_Page extends AppCompatActivity {
TextView moneySpent;
    ListView CompareItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparison__page);
        //CONVERTING TOTAL SPENT FROM double TO A DISPLAYABLE String
        String moneySpentDisp = Double.toString(UserDataViewActivity.totalSpent);
        //SENDING STRING DISPLAY TO ACTIVITY
        moneySpent = (TextView)findViewById(R.id.totalspentview);
        moneySpent.setText(moneySpentDisp);


            }


    }





