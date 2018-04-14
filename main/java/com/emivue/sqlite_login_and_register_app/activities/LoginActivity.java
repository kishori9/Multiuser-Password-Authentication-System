package com.emivue.sqlite_login_and_register_app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.EditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatTextView;

import com.emivue.sqlite_login_and_register_app.R;

import helper.InputValidation;
import sql.DatabaseHelper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = LoginActivity.this;

    private LinearLayout nestedScrollView;

    private LinearLayout textInputLayoutEmail;
    private LinearLayout textInputLayoutPassword;

    private EditText textInputEditTextEmail;
    private EditText textInputEditTextPassword;

    private Button appCompatButtonLogin;
    private AppCompatTextView appCompatTextViewRegister;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        initListeners();
        initObjects();


    }
    private void initViews(){

        nestedScrollView =  findViewById(R.id.nestedScrollView);
        textInputLayoutEmail =  findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword =  findViewById(R.id.textInputLayoutPassword);
        textInputEditTextEmail =  findViewById(R.id.textInputEditEmail);
        Log.i("ET",textInputEditTextEmail.toString());
        textInputEditTextPassword =  findViewById(R.id.textInputEditPassword);
        appCompatButtonLogin =  findViewById(R.id.AppCompatButtonLogin);
        appCompatTextViewRegister =  findViewById(R.id.textViewLinkRegister);
    }

    private void initListeners(){
        appCompatButtonLogin.setOnClickListener(this);
        appCompatTextViewRegister.setOnClickListener(this);
    }



    public void initObjects(){
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);
    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.AppCompatButtonLogin:
                verifyFromSQLite();
                break;
            case R.id.textViewLinkRegister:
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
                break;
        }
    }

    public void verifyFromSQLite(){

        if(!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail,  getString(R.string.error_message_email))){
            return;
        }

        if(!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputEditTextEmail, getString(R.string.error_message_email))){
            return;
        }
        if(!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))){
            return;
        }
        if(databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim()
                , textInputEditTextPassword.getText().toString().trim())){
            Intent accountsIntent = new Intent(activity, FingerprintActivity.class);
            accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);
        } else {
            Toast.makeText(this, getString(R.string.error_valid_email_password), Toast.LENGTH_LONG).show();
        }
    }
    public void emptyInputEditText(){
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }
}
