package com.android.csci455_group2.nyit.csci455_seniorproject_group2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//import android.R;
import com.android.csci455_group2.nyit.csci455_seniorproject_group2.R;

public class Register extends AppCompatActivity implements View.OnClickListener{


    Button bRegister;
    EditText etName, etAge, etUsername, etPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);

        bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(this);
    }


    public void onClick(View v){
        switch (v.getId()){
            case R.id.bRegister:
                break;

        }

    }
}
