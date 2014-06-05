package com.example.modoo;

import java.io.IOException;

import java.text.SimpleDateFormat;

import java.util.Date;



 
import android.app.Activity;

import android.media.MediaPlayer;

import android.media.MediaPlayer.OnCompletionListener;

import android.media.MediaRecorder;

import android.os.Bundle;
 
import android.os.Handler;

import android.os.Message;

import android.util.Log;

import android.view.View;

import android.widget.Button;

import android.widget.SeekBar;
 
import android.widget.TextView;

import android.widget.Toast;




public class Goc2Activity extends Activity implements View.OnClickListener, OnCompletionListener

{

 	

// 미리 상수 선언	

 private static final int REC_STOP8 = 0;

 private static final int RECORDING8 = 1;
 
private static final int PLAY_STOP8 = 0;

 private static final int PLAYING8 = 1;

 private static final int PLAY_PAUSE8 = 2;



 MediaRecorder mRecorder8 = null;


 
MediaPlayer mPlayer8 = null;



 int mRecState8 = REC_STOP8;

 int mcodepState8 = PLAYING8;

 int mPlayerState8 = PLAY_STOP8;

 SeekBar mRecProgressBar8, mPlayProgressBar8;
 


Button mBtnStartRec8, mBtnStartPlay8, mBtnStopPlay8;

 String mFilePath8, mFileName8 = null;

 TextView mTvPlayMaxPoint8;

 int mCurRecTimeMs8 = 0;

 int mCurProgressTimeDisplay8 = 0;
 




MediaPlayer music8 = null;






 



// 녹음시 SeekBar처리

Handler mProgressHandler8 = new Handler()

 {

 public void handleMessage(Message msg)
 
{

 mCurRecTimeMs8 = mCurRecTimeMs8 + 100;

 mCurProgressTimeDisplay8 = mCurProgressTimeDisplay8 + 100;



 // 녹음시간이 음수이면 정지버튼을 눌러 정지시켰음을 의미하므로 

// SeekBar는 그대로 정지시키고 레코더를 정지시킨다. 

if (mCurRecTimeMs8 < 0)

 {}

 // 녹음시간이 아직 최대녹음제한시간보다 작으면 녹음중이라는 의미이므로

// SeekBar의 위치를 옮겨주고 0.1초 후에 다시 체크하도록 한다. 

else if (mCurRecTimeMs8 < 20000)
 
{

 mRecProgressBar8.setProgress(mCurProgressTimeDisplay8);

 mProgressHandler8.sendEmptyMessageDelayed(0, 100);

 }

 // 녹음시간이 최대 녹음제한 시간보다 크면 녹음을 정지 시킨다. 

else

 {

 mBtnStartRecOnClick8();

 }

 }

 };



 // 재생시 SeekBar 처리

Handler mProgressHandler28 = new Handler()
 
{

 public void handleMessage(Message msg)

 {

 if (mPlayer8 == null) return;



 try

 {

 if (mPlayer8.isPlaying())
 
{

 mPlayProgressBar8.setProgress(mPlayer8.getCurrentPosition());

 mProgressHandler28.sendEmptyMessageDelayed(0, 100);

 }

 }

 catch (IllegalStateException e)
 
{}

 catch (Exception e)

 {}

 }

 };



 @Override

 public void onCreate(Bundle savedInstanceState)

 {

 super.onCreate(savedInstanceState);
 
setContentView(R.layout.goc2);





 music8 = MediaPlayer.create(this, R.raw.gp);

	

	 music8.setLooping(true);
 
	

	

	

 









// 미디어 레코더 저장할 파일 생성

mFilePath8 = "/sdcard/Download/";



 // 파일명을 년도월일시간분초 로 생성 겹치는 상황 없애기

SimpleDateFormat timeStampFormat = new SimpleDateFormat(
 
	"yyyyMMddHHmmss");



 // 파일명 위에서 정한 파일명을 WJ 폴더에 저장

mFileName8 = "/WJ"

		 + timeStampFormat.format(new Date()).toString()
 
		+ "Rec.mp4";


 mBtnStartRec8 = (Button) findViewById(R.id.btnStartRec);

 mBtnStartPlay8 = (Button) findViewById(R.id.btnStartPlay);
 
mBtnStopPlay8 = (Button) findViewById(R.id.btnStopPlay);

 mRecProgressBar8 = (SeekBar) findViewById(R.id.recProgressBar);

 mPlayProgressBar8 = (SeekBar) findViewById(R.id.playProgressBar);
 
mTvPlayMaxPoint8 = (TextView) findViewById(R.id.tvPlayMaxPoint);





 music8 = MediaPlayer.create(this, R.raw.gp);





 mBtnStartRec8.setOnClickListener(this);
 
mBtnStartPlay8.setOnClickListener(this);

 mBtnStopPlay8.setOnClickListener(this);



 }

 public void button8(View v){
 
		

		if(music8.isPlaying()){

			 // 음악을 정지합니다

			 music8.stop();
 
			try {

				 // 음악을 재생할경우를 대비해 준비합니다

				 // prepare()은 예외가 2가지나 필요합니다
 
				music8.prepare();

			 } catch (IllegalStateException e) {

				 // TODO Auto-generated catch block
 
				e.printStackTrace();

			 } catch (IOException e) {

				 // TODO Auto-generated catch block
 
				e.printStackTrace();

			 }

			 music8.seekTo(0);

 			

		}else{

		 music8.start();

			 Thread8();
 
		}

	 }

	

 public void Thread8(){

		 Runnable task = new Runnable(){
 
			public void run(){

			

				 while(music8.isPlaying()){
 
					try {

						 Thread.sleep(1000);

					 } catch (InterruptedException e) {
 
						// TODO Auto-generated catch block

						 e.printStackTrace();

					 }
 
				

				}

			 }

		 };
 
		Thread thread = new Thread(task);

		 thread.start();

	 }
 











// 버튼의 OnClick 이벤트 리스너 

public void onClick(View v)

 {

 switch(v.getId())

 {

 case R.id.btnStartRec:
 
mBtnStartRecOnClick8();



 break;

 case R.id.btnStartPlay:

 mBtnStartPlayOnClick8();

 break;

 case R.id.btnStopPlay:
 
mBtnStopPlayOnClick8();

 break;



 default:

 break;

 }

 }






 private void mBtnStartRecOnClick8()
 
{

 if (mRecState8 == REC_STOP8)

 {

 mRecState8 = RECORDING8;

 startRec8();
 music8.start();

 updateUI8();

 }

 else if (mRecState8 == RECORDING8)
 
{

 mRecState8 = REC_STOP8;

 stopRec8();
 music8.stop();

 updateUI8();

 }

 }



 // 녹음시작

private void startRec8()

 {
 
mCurRecTimeMs8 = 0;

 mCurProgressTimeDisplay8 = 0;



 // SeekBar의 상태를 0.1초후 체크 시작

mProgressHandler8.sendEmptyMessageDelayed(0, 100);



 if (mRecorder8 == null)
 
{

 mRecorder8 = new MediaRecorder();

 mRecorder8.reset();

 }

 else

 {

 mRecorder8.reset();

 }



 try

 {

	

 //오디오 파일 생성

mRecorder8.setAudioSource(MediaRecorder.AudioSource.MIC);

 mRecorder8.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
 
mRecorder8.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);

 mRecorder8.setOutputFile(mFilePath8 + mFileName8);

 mRecorder8.prepare();

 mRecorder8.start();

 }
 
catch (IllegalStateException e)

 {

 Toast.makeText(this, "IllegalStateException", 1).show();

 }

 catch (IOException e)

 {

 Toast.makeText(this, "IOException", 1).show();
 
}

 }



 // 녹음정지

private void stopRec8()

 {

 try

 {

 mRecorder8.stop();

 }

 catch(Exception e){
 
	

}

 finally

 {

 mRecorder8.release();

 mRecorder8 = null;

 }



 mCurRecTimeMs8 = -999;

 // SeekBar의 상태를 즉시 체크 

mProgressHandler8.sendEmptyMessageDelayed(0, 0);

 }



 private void mBtnStartPlayOnClick8()

 {

 if (mPlayerState8 == PLAY_STOP8)

 {

 mPlayerState8 = PLAYING8;

 initMediaPlayer8();

 startPlay8();

 updateUI8();

 }

 else if (mPlayerState8 == PLAYING8)
 
{

 mPlayerState8 = PLAY_PAUSE8;

 pausePlay8();

 updateUI8();

 }

 else if (mPlayerState8 == PLAY_PAUSE8)

 {

 mPlayerState8 = PLAYING8;
 
startPlay8();

 updateUI8();

 }

 }



 private void mBtnStopPlayOnClick8()

 {

 if (mPlayerState8 == PLAYING8 || mPlayerState8 == PLAY_PAUSE8)
 
{

 mPlayerState8 = PLAY_STOP8;

 stopPlay8();

 releaseMediaPlayer8();

 updateUI8(); 

}

 }

 private void initMediaPlayer8()
 
{

 // 미디어 플레이어 생성

if (mPlayer8 == null)

 mPlayer8 = new MediaPlayer();

 else

 mPlayer8.reset();



 mPlayer8.setOnCompletionListener(this);
 
String fullFilePath = mFilePath8 + mFileName8;



 try

 {

 mPlayer8.setDataSource(fullFilePath);

 mPlayer8.prepare(); 

int point = mPlayer8.getDuration();
 
mPlayProgressBar8.setMax(point);



 int maxMinPoint = point / 1000 / 60;

 int maxSecPoint = (point / 1000) % 60;

 String maxMinPointStr = "";
 
String maxSecPointStr = "";



 if (maxMinPoint < 10)

 maxMinPointStr = "0" + maxMinPoint + ":";

 else

 maxMinPointStr = maxMinPoint + ":";
 


if (maxSecPoint < 10)

 maxSecPointStr = "0" + maxSecPoint;

 else

 maxSecPointStr = String.valueOf(maxSecPoint);


 
mTvPlayMaxPoint8.setText(maxMinPointStr + maxSecPointStr);



 mPlayProgressBar8.setProgress(0);

 }

 catch(Exception e)

 {

 Log.v("ProgressRecorder", "미디어 플레이어 Prepare Error ==========> " + e);
 
}

 }



 // 재생 시작

private void startPlay8()

 {

 Log.v("ProgressRecorder", "startPlay().....");



 try
 
{

 mPlayer8.start();



 // SeekBar의 상태를 0.1초마다 체크 

mProgressHandler28.sendEmptyMessageDelayed(0, 100);

 }

 catch (Exception e)
 
{

 e.printStackTrace();

 Toast.makeText(this, "error : " + e.getMessage(), 0).show();

 }

 }



 private void pausePlay8()

 {

 Log.v("ProgressRecorder", "pausePlay().....");



 // 재생을 일시 정지하고

mPlayer8.pause();



 // 재생이 일시정지되면 즉시 SeekBar 메세지 핸들러를 호출한다.
 
mProgressHandler28.sendEmptyMessageDelayed(0, 0);

 }



 private void stopPlay8()

 {

 Log.v("ProgressRecorder", "stopPlay().....");



 // 재생을 중지하고

mPlayer8.stop();



 // 즉시 SeekBar 메세지 핸들러를 호출한다. 

mProgressHandler28.sendEmptyMessageDelayed(0, 0);

 }



 private void releaseMediaPlayer8()
 
{

 Log.v("ProgressRecorder", "releaseMediaPlayer().....");

 mPlayer8.release();

 mPlayer8 = null;

 mPlayProgressBar8.setProgress(0);

 }
 


public void onCompletion(MediaPlayer mp)

 {

 mPlayerState8 = PLAY_STOP8; // 재생이 종료됨




// 재생이 종료되면 즉시 SeekBar 메세지 핸들러를 호출한다.

mProgressHandler28.sendEmptyMessageDelayed(0, 0);
 


updateUI8();

 }



 private void updateUI8()

 {

 if (mRecState8 == REC_STOP8) 

{

 mBtnStartRec8.setText("Rec");
 
mRecProgressBar8.setProgress(0);

 }

 else if (mRecState8 == RECORDING8)

 mBtnStartRec8.setText("Stop");



 if (mPlayerState8 == PLAY_STOP8)
 
{

 mBtnStartPlay8.setText("Play");

 mPlayProgressBar8.setProgress(0);

 }

 else if (mPlayerState8 == PLAYING8)

 mBtnStartPlay8.setText("Pause");
 
else if (mPlayerState8 == PLAY_PAUSE8)

 mBtnStartPlay8.setText("Start");

 }

}