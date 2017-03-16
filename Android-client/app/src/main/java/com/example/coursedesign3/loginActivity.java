package com.example.coursedesign3;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class loginActivity extends Activity implements View.OnClickListener {

    private EditText phoneNumText;
    private EditText passwordText;
    private Button loginButton;
    private TextView registerAccount;
    private TextView resetPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phoneNumText = (EditText)findViewById(R.id.phoneLogin);
        passwordText = (EditText)findViewById(R.id.passwordLogin);

        loginButton = (Button)findViewById(R.id.loginButton);
        loginButton.setOnClickListener(loginActivity.this);

        registerAccount = (TextView)findViewById(R.id.registerAccount);
        registerAccount.setOnClickListener(loginActivity.this);

        resetPassword = (TextView)findViewById(R.id.resetPassword);
        resetPassword.setOnClickListener(loginActivity.this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginButton:
                loginOperation();
                break;
            case R.id.registerAccount:
                Intent register = new Intent(loginActivity.this,registerActivity.class);
                loginActivity.this.startActivity(register);
                break;
            case R.id.resetPassword:
                Intent reset = new Intent(loginActivity.this,resetActivity.class);
                loginActivity.this.startActivity(reset);
                break;
            default:
                break;
        }
    }

    private void loginOperation() {
        new AsyncTask<Object,Void,Object>(){

            String result = null;
            String phone = phoneNumText.getText().toString();
            String password = passwordText.getText().toString();
            @Override
            protected Object doInBackground(Object[] params) {
                try {
                    result = HttpClientUtil.login(phone,password);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }
            @Override
            protected void onPostExecute(Object result){
                super.onPostExecute(result);
                if((result != null) && (result.toString().contains("username:"))){
                    Toast.makeText(getBaseContext(),result.toString(),Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(loginActivity.this,MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getBaseContext(),"it is wrong",Toast.LENGTH_LONG).show();
                }
            }
        }.execute();
    }

}
