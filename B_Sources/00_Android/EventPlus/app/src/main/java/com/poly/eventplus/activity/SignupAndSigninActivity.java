package com.poly.eventplus.activity;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.nmd.libs.UtilLibs;
import com.poly.eventplus.R;

public class SignupAndSigninActivity extends AppCompatActivity implements OnClickListener {

    private Context context;
    private View imgSplashScreen;
    private View btnSignin, btnGoSignup;
    private EditText edtEmail, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_signin);
        context = SignupAndSigninActivity.this;

        imgSplashScreen = findViewById(R.id.imgSplashScreen);
        edtEmail = (EditText) findViewById(R.id.input_email);
        edtPassword = (EditText) findViewById(R.id.input_password);
        btnSignin = findViewById(R.id.btnSignin);
        btnGoSignup = findViewById(R.id.btnGoSignup);

        btnSignin.setOnClickListener(this);
        btnGoSignup.setOnClickListener(this);
        imgSplashScreen.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                imgSplashScreen.setVisibility(View.GONE);
            }
        }, 3000);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_signup_signin, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    private boolean checkNullOrEmpty(EditText email, EditText pass) {
        if (email.getText().toString().trim().isEmpty()) {
            email.requestFocus();
            UtilLibs.showErrorNullOrEmpty(email, context.getString(R.string.lbl_empty_email));
            return false;
        }
        if (pass.getText().toString().trim().isEmpty()) {
            pass.requestFocus();
            UtilLibs.showErrorNullOrEmpty(pass, context.getString(R.string.lbl_empty_password));
            return false;
        }

        return true;
    }

    private void signIn() {
        // Check network connect
        if (!UtilLibs.isNetworkConnect(context)) {
            UtilLibs.showToast(context, context.getString(R.string.lbl_alert_not_connect));
        }
        // Check null or empty
        if (!checkNullOrEmpty(edtEmail, edtPassword)) {
            return;
        }
        // Check email format
        if (!UtilLibs.validateEmail(edtEmail.getText().toString().trim())) {
            edtEmail.requestFocus();
            UtilLibs.showErrorNullOrEmpty(edtEmail, context.getString(R.string.lbl_error_email_format));
            return;
        }
        // Check password length
        if (edtPassword.getText().toString().trim().length() < 6) {
            edtPassword.requestFocus();
            UtilLibs.showErrorNullOrEmpty(edtPassword, context.getString(R.string.lbl_password_too_short));
            return;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSignin:
                signIn();
                break;
            case R.id.btnGoSignup:
                break;
        }
    }
}
