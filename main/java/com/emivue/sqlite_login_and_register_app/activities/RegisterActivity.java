
package com.emivue.sqlite_login_and_register_app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.emivue.sqlite_login_and_register_app.R;

import helper.InputValidation;
import model.User;
import sql.DatabaseHelper;
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = RegisterActivity.this;

    private NestedScrollView nestedScrollView;

    private LinearLayout textInputLayoutName;
    private LinearLayout textInputLayoutEmail;
    private LinearLayout textInputLayoutPassword;
    private LinearLayout textInputLayoutConfirmPassword;

    private EditText textInputEditTextName;
    private EditText textInputEditTextEmail;
    private EditText textInputEditTextPassword;
    private EditText textInputEditTextConfirmPassword;

    private Button appCompatButtonRegister;
    private TextView appCompatTextViewLogin;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;
    Handler handler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();
        initListeners();
        initObjects();
    }


    private void initViews() {

//        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutName =  findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail =  findViewById(R.id.textInputLayoutEmail);
        textInputLayoutConfirmPassword =  findViewById(R.id.textInputLayoutConfirmPassword);
        textInputLayoutPassword =  findViewById(R.id.textInputLayoutPassword);

        textInputEditTextName = (EditText) findViewById(R.id.textInputEditName);
        textInputEditTextEmail = (EditText) findViewById(R.id.textInputEditEmail);
        textInputEditTextPassword = (EditText) findViewById(R.id.textInputEditPassword);
        textInputEditTextConfirmPassword = (EditText) findViewById(R.id.textInputEditConfirmPassword);

        appCompatButtonRegister =  findViewById(R.id.appCompatButtonRegister);
        appCompatTextViewLogin =  findViewById(R.id.textViewLinkLogin);
    }

    private void initListeners() {
        appCompatButtonRegister.setOnClickListener(this);
        appCompatTextViewLogin.setOnClickListener(this);
    }

    public void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);
        user = new User();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonRegister:
                postDataToSQLite();
                break;
            case R.id.textViewLinkLogin:
                finish();
                break;
        }
    }

    public void postDataToSQLite() {

        if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputEditTextEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword,  textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword, textInputEditTextConfirmPassword, getString(R.string.error_password_match))) {
            return;

        }

 /*   public void postDataToSQLite() {

        if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputEditTextEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword, textInputEditTextConfirmPassword, getString(R.string.error_password_match))) {
            return;

        }     */

        if(!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())){
            user.setName(textInputEditTextName.getText().toString().trim());
            user.setEmail(textInputEditTextEmail.getText().toString().trim());
            user.setPassword(textInputEditTextPassword.getText().toString().trim());

            databaseHelper.addUser(user);
            Toast.makeText(this,getString(R.string.success_message), Toast.LENGTH_LONG).show();
            emptyInputEditText();
            handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent=new Intent(RegisterActivity.this,UsersActivity.class);
                    startActivity(intent);
                    finish();
                }
            },5000);
        } else {
            Toast.makeText(this, getString(R.string.error_email_exists), Toast.LENGTH_LONG).show();

        }
    }

   // public String userName(){
     //   return textInputEditTextName.getText().toString().trim();
   // }
    private void emptyInputEditText(){
        textInputEditTextName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);

    }
}




