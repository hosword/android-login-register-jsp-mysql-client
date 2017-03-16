package com.example.coursedesign3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class welcomepageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomepage);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(welcomepageActivity.this,loginActivity.class);
                startActivity(i);
                welcomepageActivity.this.finish();
            }
        },3000);
    }
}
