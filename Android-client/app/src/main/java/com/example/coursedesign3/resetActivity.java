package com.example.coursedesign3;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

public class resetActivity extends Activity {

    private TextView vertifyView;
    int times = 15;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
            vertifyView = (TextView) findViewById(R.id.getVertification);
            vertifyView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    vertifyView.setEnabled(false);
                    vertifyView.setSelected(true);
                    handler.sendMessageDelayed(Message.obtain(handler,0),1000);
                }
            });
    }

    private Handler handler = new Handler(new Handler.Callback(){
        @Override
        public  boolean handleMessage(Message msg){
            times--;
            if(times == 0){
                vertifyView.setEnabled(true);
                handler.removeMessages(0);
                vertifyView.setText("获取验证码");
                times = 15;
            }else{
                handler.sendMessageDelayed(Message.obtain(handler,0),1000);
                vertifyView.setText(times+"秒");
            }
            return true;
        }
    });
}
