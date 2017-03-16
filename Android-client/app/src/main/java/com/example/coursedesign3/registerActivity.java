package com.example.coursedesign3;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class registerActivity extends Activity implements View.OnClickListener{

    private EditText phoneText;
    private EditText verEText;
    private TextView verText;
    private EditText passwordText;
    private Button regButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        phoneText = (EditText)findViewById(R.id.regPhone);
        verEText = (EditText)findViewById(R.id.verEText);

        verText = (TextView)findViewById(R.id.verText);
        verText.setOnClickListener(this);

        passwordText = (EditText)findViewById(R.id.regPassword);

        regButton = (Button)findViewById(R.id.regButton);
        regButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.verText:
                sendMessageClick(view);
                break;
            case R.id.regButton:
                registerOperation();
//                Intent jump = new Intent(registerActivity.this,MainActivity.class);
//                startActivity(jump);
                break;
            default:
                break;
        }
    }

    private Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    if(msg.arg1==0){
                        verText.setText("获取验证码");
                        verText.setClickable(true);
                    }else{
                        verText.setText(msg.arg1+"秒");
                        verText.setClickable(false);
                    }
                    break;
            }
        }
    };

    public void sendMessageClick(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 15;i>=0;i--){
                    Message msg = myHandler.obtainMessage();
                    msg.arg1 = i;
                    myHandler.sendMessage(msg);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void registerOperation() {
        new AsyncTask<Object,Void,Object>(){

            String result = null;
            String phone = phoneText.getText().toString();
            String password = passwordText.getText().toString();
            @Override
            protected Object doInBackground(Object[] params) {
                try {
                    result = HttpClientUtil.register(phone,password);
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
                    Intent intent = new Intent(registerActivity.this,loginActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getBaseContext(),"it is wrong",Toast.LENGTH_LONG).show();
                }
            }
        }.execute();
    }


}
