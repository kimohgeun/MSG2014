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
    	});
    	
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
    	});
    	
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
    	});
	
    	mp1 = MediaPlayer.create(getApplicationContext(), R.raw.cos);
        mp1.start();
	
	}
	@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
	}

}