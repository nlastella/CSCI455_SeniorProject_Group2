package com.android.csci455_group2.nyit.csci455_seniorproject_group2;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private EditText et_userName; //username
    private EditText et_passWord; //password
    private Button bt_login;      //login button
    private Button bt_register;  //jump to register
    private ProgressDialog dialog;//progress dialog
    private AlertDialog.Builder error;
    private String info;
    private TextView testTV;
    private static Handler handler = new android.os.Handler() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        //get item
        et_userName = (EditText)findViewById(R.id.username);
        et_passWord = (EditText)findViewById(R.id.password);
        bt_login = (Button)findViewById(R.id.login);
        testTV = (TextView)findViewById(R.id.test);
        bt_register = (Button)findViewById(R.id.register);

        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, Register.class));
            }
        });

        //set button listener
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // search network(cannot search wifi)
                if (!checkNetwork()) {
                    Toast toast = Toast.makeText(LoginActivity.this, "No Internet Connection", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                // Information dialog
                dialog = new ProgressDialog(LoginActivity.this);
                dialog.setTitle("Information");
                dialog.setMessage("Logging in, please wait...");
                dialog.setCancelable(false);
                dialog.show();
                // create sub_thread, doing Get and Post transfer
                new Thread(new MyThread()).start();


            }
        });
    }



    // sub_thread get data, main thread change data
    public class MyThread implements Runnable {
        @Override
        public void run() {
            info = WebService.executeHttpGet(et_userName.getText().toString(), et_passWord.getText().toString());
            //info = WebServicePost.executeHttpPost(username.getText().toString(), password.getText().toString());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    testTV.setText(info);
                    dialog.dismiss();
                    if(!testTV.getText().toString().equals("")) {
                        startActivity(new Intent(LoginActivity.this, enter.class));
                    } else{
                        error = new AlertDialog.Builder(LoginActivity.this);
                        error.setTitle("Error!");
                        error.setMessage("Incorrect username or password. Try again.");
                        error.setCancelable(true);
                        error.show();
                    }
                }
            });
        }
    }
    // search Internet
    private boolean checkNetwork() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager.getActiveNetworkInfo() != null) {
            return connManager.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }

}
