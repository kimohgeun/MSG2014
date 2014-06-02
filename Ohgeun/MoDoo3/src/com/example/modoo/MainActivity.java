package com.example.modoo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;


public class MainActivity extends Activity {
       
	MediaPlayer mp;
	protected String mRecordingFile;
	SoundPool sound;
	int soundId;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        startActivity(new Intent(this, SplashActivity.class));
    	sound=new SoundPool(5,AudioManager.STREAM_MUSIC,0);
    	soundId=sound.load(this,R.raw.ring,1);
        
        Button btnCallMain = (Button) findViewById(R.id.BtnCalltoSub);
        
    	btnCallMain.setOnClickListener(new OnClickListener() {

    	@Override

    	public void onClick(View v)

    	{
        sound.play(soundId,1f,1f,0,0,1f);
        
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
