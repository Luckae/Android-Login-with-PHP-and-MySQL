package com.ucare.app.ucare;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.Calendar;
import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Toolbar tool;

    private RadioGroup radioGroup;
    RadioButton radio_first, radio_second, radio_third;
    Button register;
    TextView reg;
    EditText First_Name, Last_Name, Email, Phone,Date, Country, State, Password ;
    String F_Name_Holder, L_Name_Holder, EmailHolder, Phone_Holder, Country_Holder, State_Holder, Date_Holder, PasswordHolder, Radio_Holder,checkedd;
    String finalResult ;
    /**Replace with your own api
    This is a test api
     */
    String HttpURL = "http://youcare.lawsonluke.com.ng/register.php/";
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        First_Name = (EditText) findViewById(R.id.first_name);
        Last_Name = (EditText) findViewById(R.id.last_name);
        Email = (EditText) findViewById(R.id.email);
        Phone = (EditText) findViewById(R.id.phone_no);
        Country = (EditText) findViewById(R.id.country);
        State = (EditText) findViewById(R.id.state);
        Date = (EditText) findViewById(R.id.date);
        Password = (EditText) findViewById(R.id.password);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);

        radio_first = (RadioButton)findViewById(R.id.radio0) ;
        radio_second = (RadioButton)findViewById(R.id.radio1);
        radio_third = (RadioButton) findViewById(R.id.radio2);

        register = (Button) findViewById(R.id.button);
        reg = (TextView) findViewById(R.id.registered);


        Calendar calendar = Calendar.getInstance();
        final int Day = calendar.get(Calendar.DAY_OF_MONTH);
        final int Month = calendar.get(Calendar.MONTH);
        final int Year = calendar.get(Calendar.YEAR);

        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int myYear, int mMonth, int Mday) {
                        Date.setText(Mday + "/" + mMonth + "/" + myYear);

                    }
                }, Day, Month, Year);
                dialog.show();
            }

        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckEditTextIsEmptyOrNot();

                if (CheckEditText) {

                    // If EditText is not empty and CheckEditText = True then this block will execute.

                    UserRegisterFunction(F_Name_Holder, L_Name_Holder, EmailHolder, Phone_Holder,Country_Holder,State_Holder,Date_Holder,PasswordHolder,Radio_Holder);
                }
                else {


                    // If EditText is empty then this block will execute
                    // Toast.makeText(MainActivity.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

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


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,UserLoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void CheckEditTextIsEmptyOrNot(){

        if(radio_first.isChecked()){
            checkedd = radio_first.getText().toString();
            // Radio_Holder = checkedd;
        }
        else if(radio_second.isChecked()){
            checkedd = radio_second.getText().toString();
            // Radio_Holder = checkedd;
        }
        else if(radio_third.isChecked()){
            checkedd = radio_third.getText().toString();
            // Radio_Holder = checkedd;
        }

        F_Name_Holder = First_Name.getText().toString();
        L_Name_Holder = Last_Name.getText().toString();
        EmailHolder = Email.getText().toString();
        Phone_Holder = Phone.getText().toString();
        Country_Holder = Country.getText().toString();
        State_Holder = State.getText().toString();
        Date_Holder = Date.getText().toString();
        Radio_Holder = checkedd;
        PasswordHolder = Password.getText().toString();



        if(TextUtils.isEmpty(F_Name_Holder) || TextUtils.isEmpty(L_Name_Holder) || TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(Phone_Holder) || TextUtils.isEmpty(Country_Holder) || TextUtils.isEmpty(State_Holder) || TextUtils.isEmpty(Date_Holder) || TextUtils.isEmpty(PasswordHolder) || TextUtils.isEmpty(Radio_Holder))
        {

            CheckEditText = false;

        }
        else {

            CheckEditText = true ;
        }

    }

    public void UserRegisterFunction(final String F_Name, final String L_Name, final String email, final String Phone_name, final String Country_name, final String State_name, final String Date_name, final String password, final String Radio_name){

        class UserRegisterFunctionClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                //progressDialog = ProgressDialog.show(MainActivity.this,"Ucare","Loading ... please wait",true,false);
                progressDialog = new ProgressDialog(MainActivity.this, R.style.MyAlertDialogStyle);
                progressDialog.setTitle("LawsonCare. Healthcare beyond clinic");
                progressDialog.setMessage("Loading ... please wait");
                progressDialog.setCancelable(false);
                progressDialog.setIcon(R.mipmap.ic_launcher);
                progressDialog.show();

            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();
                Toast.makeText(MainActivity.this,httpResponseMsg, Toast.LENGTH_LONG).show();



                if(httpResponseMsg.equalsIgnoreCase("Registration Successful")){

                    Intent intent = new Intent(MainActivity.this, UserLoginActivity.class);
                    startActivity(intent);
                    finish();

                    final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                    emailIntent.setType("text/html");
                    emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Thank you for signing up");
                    emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml(
                            "<h1>Welcome to Ucare "+First_Name

                    ));
                    startActivity(Intent.createChooser(emailIntent, "Email:"));
                } else if(httpResponseMsg.equalsIgnoreCase("")){
                    Toast.makeText(MainActivity.this,"Could not complete", Toast.LENGTH_LONG).show();
                }

            }


            @Override
            protected String doInBackground(String... params) {

                hashMap.put("F_name",params[0]);

                hashMap.put("L_name",params[1]);

                hashMap.put("email",params[2]);

                hashMap.put("Phone_name",params[3]);

                hashMap.put("Country_name",params[4]);

                hashMap.put("State_name",params[5]);

                hashMap.put("Date_name",params[6]);

                hashMap.put("password",params[7]);

                hashMap.put("Radio_name",params[8]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        UserRegisterFunctionClass userRegisterFunctionClass = new UserRegisterFunctionClass();

        userRegisterFunctionClass.execute(F_Name,L_Name,email,Phone_name,Country_name,State_name,Date_name,password,Radio_name);



    }

}

