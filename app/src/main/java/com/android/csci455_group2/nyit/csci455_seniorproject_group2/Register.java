package com.android.csci455_group2.nyit.csci455_seniorproject_group2;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    private EditText et_name;
    private EditText et_age;
    private EditText et_username;
    private EditText et_password;
    private Button btn_register;
    private static Handler handler = new android.os.Handler() ;
    private String info;
    private ProgressDialog dialog;
    private TextView testTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        //get items
        et_name = (EditText) findViewById(R.id.name);
        et_age = (EditText) findViewById(R.id.age);
        et_username = (EditText) findViewById(R.id.username);
        et_password = (EditText) findViewById(R.id.password);
        btn_register = (Button) findViewById(R.id.button);
        testTV = (TextView)findViewById(R.id.test);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // search network(cannot search wifi)
                if (!checkNetwork()) {
                    Toast toast = Toast.makeText(Register.this, "No Internet Connection", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                // Information dialog
                dialog = new ProgressDialog(Register.this);
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
            info = WebService_02.executeHttpGet(et_name.getText().toString(),et_age.getText().toString(),et_username.getText().toString(), et_password.getText().toString());
            // info = WebServicePost.executeHttpPost(username.getText().toString(), password.getText().toString());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    testTV.setText(info);
                    dialog.dismiss();
                }
            });
        }
    }

    private boolean checkNetwork() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager.getActiveNetworkInfo() != null) {
            return connManager.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }

}
