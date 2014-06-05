package com.example.modoo;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Js1Activity extends Activity {
	MediaPlayer mp2;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		mp2 = MediaPlayer.create(getApplicationContext(), R.raw.jus);
	    mp2.start();
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.jsa);
	    // TODO Auto-generated method stub
	    Button btnCallMain = (Button) findViewById(R.id.js1b1);
    	btnCallMain.setOnClickListener(new OnClickListener() {
    	@Override
    	public void onClick(View v)
    	{
    	Log.i("onClick", "Stroke");
    	Intent intentSubActivity = 
    	new Intent(Js1Activity.this, Goa1Activity.class);
    	startActivity(intentSubActivity);
    	}
    	});
    	
    	Button btnCallMain2 = (Button) findViewById(R.id.js1b2);
    	btnCallMain2.setOnClickListener(new OnClickListener() {
    	@Override
    	public void onClick(View v)
    	{
    	Log.i("onClick", "Percussive");
    	Intent intentSubActivity = 
    	new Intent(Js1Activity.this, Goa2Activity.class);
    	startActivity(intentSubActivity);
    	}
    	});
    	
    	Button btnCallMain3 = (Button) findViewById(R.id.js1b3);
    	btnCallMain3.setOnClickListener(new OnClickListener() {
    	@Override
    	public void onClick(View v)
    	{
    	Log.i("onClick", "Arpeggio");
    	Intent intentSubActivity = 
    	new Intent(Js1Activity.this, Goa3Activity.class);
    	startActivity(intentSubActivity);
    	}
    	});
	}

}
