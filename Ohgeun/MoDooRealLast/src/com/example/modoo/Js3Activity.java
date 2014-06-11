package com.example.modoo;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Js3Activity extends Activity {
	MediaPlayer mp4;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		mp4 = MediaPlayer.create(getApplicationContext(), R.raw.jus);
	    mp4.start();//�ڵ带 �����ϼ��� ������ ������ �Ѵ�.
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.jsc);
	    // TODO Auto-generated method stub
	    Button btnCallMain = (Button) findViewById(R.id.js3b1);
    	btnCallMain.setOnClickListener(new OnClickListener() {
    	@Override
    	public void onClick(View v)
    	{
    	Log.i("onClick", "Stroke");
    	Intent intentSubActivity = 
    	new Intent(Js3Activity.this, Goc1Activity.class);
    	startActivity(intentSubActivity);
    	}
    	});//Stroke ��ư�� Ŭ���� ������ ���ȭ������ ��ȯ�� �ǰ��Ѵ�.
    	
    	Button btnCallMain2 = (Button) findViewById(R.id.js3b2);
    	btnCallMain2.setOnClickListener(new OnClickListener() {
    	@Override
    	public void onClick(View v)
    	{
    	Log.i("onClick", "Percussive");
    	Intent intentSubActivity = 
    	new Intent(Js3Activity.this, Goc2Activity.class);
    	startActivity(intentSubActivity);
    	}
    	});//Percussive ��ư�� Ŭ���� ������ ���ȭ������ ��ȯ�� �ǰ��Ѵ�.
    	
    	Button btnCallMain3 = (Button) findViewById(R.id.js3b3);
    	btnCallMain3.setOnClickListener(new OnClickListener() {
    	@Override
    	public void onClick(View v)
    	{
    	Log.i("onClick", "Arpeggio");
    	Intent intentSubActivity = 
    	new Intent(Js3Activity.this, Goc3Activity.class);
    	startActivity(intentSubActivity);
    	}
    	});//Arpeggio ��ư�� Ŭ���� ������ ���ȭ������ ��ȯ�� �ǰ��Ѵ�.
	}

}
