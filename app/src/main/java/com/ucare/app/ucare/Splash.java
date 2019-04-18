package com.ucare.app.ucare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        Intent intent = new Intent(Splash.this, UserLoginActivity.class);
        startActivity(intent);
        finish();
    }
}
