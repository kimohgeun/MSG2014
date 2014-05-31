package com.example.modoo;

import com.example.modoo.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
public class SplashActivity extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        
        Handler handler = new Handler () {
            @Override
            public void handleMessage(Message msg)
            {
               
                super.handleMessage(msg);
                finish();
            }
        };
        handler.sendEmptyMessageDelayed(0, 3000);
      
    }
}

