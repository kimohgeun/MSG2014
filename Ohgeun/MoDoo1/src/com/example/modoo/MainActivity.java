package com.example.modoo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.media.MediaPlayer;


public class MainActivity extends Activity {
       
	MediaPlayer mp;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        startActivity(new Intent(this, SplashActivity.class));
    	Button btnCallMain = (Button) findViewById(R.id.BtnCalltoSub);

    	btnCallMain.setOnClickListener(new OnClickListener() {

    	@Override

    	public void onClick(View v)

    	{

    	Log.i("onClick", "START");

    	Intent intentSubActivity = 

    	new Intent(MainActivity.this, CsActivity.class);

    	startActivity(intentSubActivity);

    	}

    	});
    
    	   mp = MediaPlayer.create(getApplicationContext(), R.raw.bgm);
           mp.start();
            
         
        }

   

    	
    	
   
	
	


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
