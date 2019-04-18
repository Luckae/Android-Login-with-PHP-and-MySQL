package com.ucare.app.ucare;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class UserLoginActivity extends Activity {
    EditText Email, Password;
    TextView log;
    Button LogIn;
    String PasswordHolder, EmailHolder;
    String finalResult;
    String HttpURL = "http://youcare.lawsonluke.com.ng/login.php/";
    Boolean CheckEditText;
    ProgressDialog progressDialog;
    HashMap<String, String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    SharedPreferences sp;

    public static final String UserEmail = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        Email = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.password);
        LogIn = (Button) findViewById(R.id.Login);
        log = (TextView) findViewById(R.id.textLog);


        sp = getSharedPreferences("login",MODE_PRIVATE);

        if(sp.getBoolean("logged",false)){
            goToHome();
        }

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserLoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CheckEditTextIsEmptyOrNot();

                if (CheckEditText) {

                    UserLoginFunction(EmailHolder, PasswordHolder);

                } else {

                    // Toast.makeText(UserLoginActivity.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                    // Get the custom layout view.
                    View toastView = getLayoutInflater().inflate(R.layout.activity_toast_custorm_view, null);

                    // Initiate the Toast instance.
                    Toast toast = new Toast(getApplicationContext());
                    // Set custom view in toast.
                    toast.setView(toastView);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0,0);
                    toast.show();
                }
            }
        });
    }


    public void CheckEditTextIsEmptyOrNot() {

        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();

        if (TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)) {
            CheckEditText = false;
        } else {

            CheckEditText = true;
        }
    }

    public void goToHome(){
        Intent i = new Intent(this,DashBoard.class);
        startActivity(i);
    }


    public void UserLoginFunction(final String email, final String password) {

        class UserLoginClass extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                // progressDialog = ProgressDialog.show(UserLoginActivity.this, "Loading Data", null, true, true);
                progressDialog = new ProgressDialog(UserLoginActivity.this, R.style.MyAlertDialogStyle);
                progressDialog.setTitle("Receive healthcare beyond clinic");
                progressDialog.setMessage("Loading ... just a moment");
                progressDialog.setCancelable(false);
                progressDialog.setIcon(R.mipmap.ic_launcher);
                progressDialog.show();
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                if (httpResponseMsg.equalsIgnoreCase("Data Matched")) {

                    finish();

                    Intent intent = new Intent(UserLoginActivity.this, DashBoard.class);

                    intent.putExtra(UserEmail, email);

                    startActivity(intent);
                    sp.edit().putBoolean("logged",true).apply();

                } else {

                    Toast.makeText(UserLoginActivity.this, httpResponseMsg, Toast.LENGTH_LONG).show();
                }

            }
            @Override
            protected String doInBackground(String... params) {

                hashMap.put("email",params[0]);

                hashMap.put("password",params[1]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        UserLoginClass userLoginClass = new UserLoginClass();

        userLoginClass.execute(email,password);
    }
}
