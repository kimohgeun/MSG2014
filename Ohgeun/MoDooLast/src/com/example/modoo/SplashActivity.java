package com.example.modoo;

import com.example.modoo.R;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class SplashActivity extends Activity {
	@Override
public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.splash);
	
ImageButton link = (ImageButton)findViewById(R.id.ib1);
link.setOnClickListener(new OnClickListener() {
	@Override
	public void onClick(View v)
	{
		Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.lezhin.com/"));
		startActivity(intent);  		
	}
	});
	  	
	
Handler handler = new Handler () {
@Override
public void handleMessage(Message msg)
{
super.handleMessage(msg);
finish();
}
};
handler.sendEmptyMessageDelayed(0, 10000);
}
}