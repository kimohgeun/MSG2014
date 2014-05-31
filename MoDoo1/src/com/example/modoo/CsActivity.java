package com.example.modoo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class CsActivity extends Activity  
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cs);
		
		Button btnCallMain = (Button) findViewById(R.id.csb1);
		btnCallMain.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				Log.i("onClick", "D-A-Bm-G");
				Intent intentSubActivity = 
						new Intent(getApplicationContext(), Js1Activity.class);
				startActivity(intentSubActivity);
			}
		
		});
		Button btnCallMain2 = (Button) findViewById(R.id.csb2);
		btnCallMain2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				Log.i("onClick", "C-G-Am-F");
				Intent intentSubActivity2 = 
						new Intent(getApplicationContext(), Js2Activity.class);
				startActivity(intentSubActivity2);
			}
		
		});
		Button btnCallMain3 = (Button) findViewById(R.id.csb3);
		btnCallMain3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				Log.i("onClick", "A-B-C-D");
				Intent intentSubActivity3 = 
						new Intent(getApplicationContext(), Js2Activity.class);
				startActivity(intentSubActivity3);
			}
		
		});
		
		
	}
	@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
	}

}