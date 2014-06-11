package com.example.modoo;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class CsActivity extends Activity  
{
	MediaPlayer mp1;
	@Override
	protected void onCreate(Bundle savedInstanceState) 	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cs);
	
		Button btnCallMain = (Button) findViewById(R.id.csb1);
    	btnCallMain.setOnClickListener(new OnClickListener() {
    	@Override
    	public void onClick(View v)
    	{
    	Log.i("onClick", "C-G-Am-F");
    	Intent intentSubActivity = 
    	new Intent(CsActivity.this, Js1Activity.class);
    	startActivity(intentSubActivity);
    	}
    	});//C-G-Am-F 버튼클릭시 주법선택 화면으로 전환
    	
    	Button btnCallMain2 = (Button) findViewById(R.id.csb2);
    	btnCallMain2.setOnClickListener(new OnClickListener() {
    	@Override
    	public void onClick(View v)
    	{
    	Log.i("onClick", "D-A-Bm-G");
    	Intent intentSubActivity = 
    	new Intent(CsActivity.this, Js2Activity.class);
    	startActivity(intentSubActivity);
    	}
    	});//D-A-Bm-G 버튼 클릭시 주법화면으로 전환
    	
    	Button btnCallMain3 = (Button) findViewById(R.id.csb3);
    	btnCallMain3.setOnClickListener(new OnClickListener() {
    	@Override
    	public void onClick(View v)
    	{
    	Log.i("onClick", "G-D-Em-C");
    	Intent intentSubActivity = 
    	new Intent(CsActivity.this, Js2Activity.class);
    	startActivity(intentSubActivity);
    	}
    	});//G-D-Em-C버튼 클릭시 주법화면으로 전환
	
    	mp1 = MediaPlayer.create(getApplicationContext(), R.raw.cos);
        mp1.start();//코드를 선택하세요라는 음성이 나옴
	
	}
	@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
	}

}