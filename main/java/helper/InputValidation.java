package helper;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.util.regex.Pattern;

public class InputValidation {

    private static Context context;
    public InputValidation(Context context){
        this.context = context;
    }

 /*
    public boolean isInputEditTextFilled(EditText textInputEditText,LinearLayout textInputLayout,String message){

        String value = textInputEditText.getText().toString().trim();
        if(value.isEmpty()) {
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        }
        else{
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }   */
    public boolean isInputEditTextFilled(EditText textInputEditText,LinearLayout textInputLayout,String message){
        String value = textInputEditText.getText().toString().trim();
        if(value.isEmpty()) {
            textInputEditText.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        }

        else {
            return true;
        }
    }


    public boolean isInputEditTextEmail(EditText textInputEditText, EditText textInputLayout, String message){

        String value = textInputEditText.getText().toString().trim();
        if(value.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(value).matches()){
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        } else {
            textInputLayout.setEnabled(true);
        }
        return true;


    }

    public boolean isInputEditTextMatches(EditText textInputEditText, EditText textInputEditText2, EditText textInputLayout, String message){
        String value1 = textInputEditText.getText().toString().trim();
        String value2 = textInputEditText2.getText().toString().trim();

        if(!value1.contentEquals(value2)){
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText2);
            return false;
        } else {
            textInputLayout.setEnabled(true);
        }

        return true;
    }

    public boolean isInputPasswordMatches(EditText textInputEditText, EditText textInputEditText2, EditText textInputLayout, String message){
        String value1 = textInputEditText.getText().toString().trim();
        String value2 = textInputEditText2.getText().toString().trim();

        if(!value1.contentEquals(value2)){
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText2);
            return false;
        } else {
            textInputLayout.setEnabled(true);
        }

        return true;
    }


    private void hideKeyboardFrom(View view){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

}
