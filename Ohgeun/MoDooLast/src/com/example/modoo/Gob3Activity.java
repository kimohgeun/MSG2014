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




public class Gob3Activity extends Activity implements View.OnClickListener, OnCompletionListener

{

 	

// 미리 상수 선언	

 private static final int REC_STOP6 = 0;

 private static final int RECORDING6 = 1;
 
private static final int PLAY_STOP6 = 0;

 private static final int PLAYING6 = 1;

 private static final int PLAY_PAUSE6 = 2;



 MediaRecorder mRecorder6 = null;


 
MediaPlayer mPlayer6 = null;



 int mRecState6 = REC_STOP6;

 int mcodepState6 = PLAYING6;

 int mPlayerState6 = PLAY_STOP6;

 SeekBar mRecProgressBar6, mPlayProgressBar6;
 


Button mBtnStartRec6, mBtnStartPlay6, mBtnStopPlay6;

 String mFilePath6, mFileName6 = null;

 TextView mTvPlayMaxPoint6;

 int mCurRecTimeMs6 = 0;

 int mCurProgressTimeDisplay6 = 0;
 




MediaPlayer music6 = null;






 



// 녹음시 SeekBar처리

Handler mProgressHandler6 = new Handler()

 {

 public void handleMessage(Message msg)
 
{

 mCurRecTimeMs6 = mCurRecTimeMs6 + 100;

 mCurProgressTimeDisplay6 = mCurProgressTimeDisplay6 + 100;



 // 녹음시간이 음수이면 정지버튼을 눌러 정지시켰음을 의미하므로 

// SeekBar는 그대로 정지시키고 레코더를 정지시킨다. 

if (mCurRecTimeMs6 < 0)

 {}

 // 녹음시간이 아직 최대녹음제한시간보다 작으면 녹음중이라는 의미이므로

// SeekBar의 위치를 옮겨주고 0.1초 후에 다시 체크하도록 한다. 

else if (mCurRecTimeMs6 < 20000)
 
{

 mRecProgressBar6.setProgress(mCurProgressTimeDisplay6);

 mProgressHandler6.sendEmptyMessageDelayed(0, 100);

 }

 // 녹음시간이 최대 녹음제한 시간보다 크면 녹음을 정지 시킨다. 

else

 {

 mBtnStartRecOnClick6();

 }

 }

 };



 // 재생시 SeekBar 처리

Handler mProgressHandler26 = new Handler()
 
{

 public void handleMessage(Message msg)

 {

 if (mPlayer6 == null) return;



 try

 {

 if (mPlayer6.isPlaying())
 
{

 mPlayProgressBar6.setProgress(mPlayer6.getCurrentPosition());

 mProgressHandler26.sendEmptyMessageDelayed(0, 100);

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
 
setContentView(R.layout.goa1);





 music6 = MediaPlayer.create(this, R.raw.da);

	

	 music6.setLooping(true);
 
	

	

	

 









// 미디어 레코더 저장할 파일 생성

mFilePath6 = "/sdcard/Download/";



 // 파일명을 년도월일시간분초 로 생성 겹치는 상황 없애기

SimpleDateFormat timeStampFormat = new SimpleDateFormat(
 
	"yyyyMMddHHmmss");



 // 파일명 위에서 정한 파일명을 WJ 폴더에 저장

mFileName6 = "/WJ"

		 + timeStampFormat.format(new Date()).toString()
 
		+ "Rec.mp4";


 mBtnStartRec6 = (Button) findViewById(R.id.btnStartRec);

 mBtnStartPlay6 = (Button) findViewById(R.id.btnStartPlay);
 
mBtnStopPlay6 = (Button) findViewById(R.id.btnStopPlay);

 mRecProgressBar6 = (SeekBar) findViewById(R.id.recProgressBar);

 mPlayProgressBar6 = (SeekBar) findViewById(R.id.playProgressBar);
 
mTvPlayMaxPoint6 = (TextView) findViewById(R.id.tvPlayMaxPoint);





 music6 = MediaPlayer.create(this, R.raw.da);





 mBtnStartRec6.setOnClickListener(this);
 
mBtnStartPlay6.setOnClickListener(this);

 mBtnStopPlay6.setOnClickListener(this);



 }

 public void button6(View v){
 
		

		if(music6.isPlaying()){

			 // 음악을 정지합니다

			 music6.stop();
 
			try {

				 // 음악을 재생할경우를 대비해 준비합니다

				 // prepare()은 예외가 2가지나 필요합니다
 
				music6.prepare();

			 } catch (IllegalStateException e) {

				 // TODO Auto-generated catch block
 
				e.printStackTrace();

			 } catch (IOException e) {

				 // TODO Auto-generated catch block
 
				e.printStackTrace();

			 }

			 music6.seekTo(0);

 			

		}else{

		 music6.start();

			 Thread6();
 
		}

	 }

	

 public void Thread6(){

		 Runnable task = new Runnable(){
 
			public void run(){

			

				 while(music6.isPlaying()){
 
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
 
mBtnStartRecOnClick6();



 break;

 case R.id.btnStartPlay:

 mBtnStartPlayOnClick6();

 break;

 case R.id.btnStopPlay:
 
mBtnStopPlayOnClick6();

 break;



 default:

 break;

 }

 }






 private void mBtnStartRecOnClick6()
 
{

 if (mRecState6 == REC_STOP6)

 {

 mRecState6 = RECORDING6;

 startRec6();
 music6.start();

 updateUI6();

 }

 else if (mRecState6 == RECORDING6)
 
{

 mRecState6 = REC_STOP6;

 stopRec6();
 music6.stop();

 updateUI6();

 }

 }



 // 녹음시작

private void startRec6()

 {
 
mCurRecTimeMs6 = 0;

 mCurProgressTimeDisplay6 = 0;



 // SeekBar의 상태를 0.1초후 체크 시작

mProgressHandler6.sendEmptyMessageDelayed(0, 100);



 if (mRecorder6 == null)
 
{

 mRecorder6 = new MediaRecorder();

 mRecorder6.reset();

 }

 else

 {

 mRecorder6.reset();

 }



 try

 {

	

 //오디오 파일 생성

mRecorder6.setAudioSource(MediaRecorder.AudioSource.MIC);

 mRecorder6.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
 
mRecorder6.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);

 mRecorder6.setOutputFile(mFilePath6 + mFileName6);

 mRecorder6.prepare();

 mRecorder6.start();

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

private void stopRec6()

 {

 try

 {

 mRecorder6.stop();

 }

 catch(Exception e){
 
	

}

 finally

 {

 mRecorder6.release();

 mRecorder6 = null;

 }



 mCurRecTimeMs6 = -999;

 // SeekBar의 상태를 즉시 체크 

mProgressHandler6.sendEmptyMessageDelayed(0, 0);

 }



 private void mBtnStartPlayOnClick6()

 {

 if (mPlayerState6 == PLAY_STOP6)

 {

 mPlayerState6 = PLAYING6;

 initMediaPlayer6();

 startPlay6();

 updateUI6();

 }

 else if (mPlayerState6 == PLAYING6)
 
{

 mPlayerState6 = PLAY_PAUSE6;

 pausePlay6();

 updateUI6();

 }

 else if (mPlayerState6 == PLAY_PAUSE6)

 {

 mPlayerState6 = PLAYING6;
 
startPlay6();

 updateUI6();

 }

 }



 private void mBtnStopPlayOnClick6()

 {

 if (mPlayerState6 == PLAYING6 || mPlayerState6 == PLAY_PAUSE6)
 
{

 mPlayerState6 = PLAY_STOP6;

 stopPlay6();

 releaseMediaPlayer6();

 updateUI6(); 

}

 }

 private void initMediaPlayer6()
 
{

 // 미디어 플레이어 생성

if (mPlayer6 == null)

 mPlayer6 = new MediaPlayer();

 else

 mPlayer6.reset();



 mPlayer6.setOnCompletionListener(this);
 
String fullFilePath = mFilePath6 + mFileName6;



 try

 {

 mPlayer6.setDataSource(fullFilePath);

 mPlayer6.prepare(); 

int point = mPlayer6.getDuration();
 
mPlayProgressBar6.setMax(point);



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


 
mTvPlayMaxPoint6.setText(maxMinPointStr + maxSecPointStr);



 mPlayProgressBar6.setProgress(0);

 }

 catch(Exception e)

 {

 Log.v("ProgressRecorder", "미디어 플레이어 Prepare Error ==========> " + e);
 
}

 }



 // 재생 시작

private void startPlay6()

 {

 Log.v("ProgressRecorder", "startPlay().....");



 try
 
{

 mPlayer6.start();



 // SeekBar의 상태를 0.1초마다 체크 

mProgressHandler26.sendEmptyMessageDelayed(0, 100);

 }

 catch (Exception e)
 
{

 e.printStackTrace();

 Toast.makeText(this, "error : " + e.getMessage(), 0).show();

 }

 }



 private void pausePlay6()

 {

 Log.v("ProgressRecorder", "pausePlay().....");



 // 재생을 일시 정지하고

mPlayer6.pause();



 // 재생이 일시정지되면 즉시 SeekBar 메세지 핸들러를 호출한다.
 
mProgressHandler26.sendEmptyMessageDelayed(0, 0);

 }



 private void stopPlay6()

 {

 Log.v("ProgressRecorder", "stopPlay().....");



 // 재생을 중지하고

mPlayer6.stop();



 // 즉시 SeekBar 메세지 핸들러를 호출한다. 

mProgressHandler26.sendEmptyMessageDelayed(0, 0);

 }



 private void releaseMediaPlayer6()
 
{

 Log.v("ProgressRecorder", "releaseMediaPlayer().....");

 mPlayer6.release();

 mPlayer6 = null;

 mPlayProgressBar6.setProgress(0);

 }
 


public void onCompletion(MediaPlayer mp)

 {

 mPlayerState6 = PLAY_STOP6; // 재생이 종료됨




// 재생이 종료되면 즉시 SeekBar 메세지 핸들러를 호출한다.

mProgressHandler26.sendEmptyMessageDelayed(0, 0);
 


updateUI6();

 }



 private void updateUI6()

 {

 if (mRecState6 == REC_STOP6) 

{

 mBtnStartRec6.setText("Rec");
 
mRecProgressBar6.setProgress(0);

 }

 else if (mRecState6 == RECORDING6)

 mBtnStartRec6.setText("Stop");



 if (mPlayerState6 == PLAY_STOP6)
 
{

 mBtnStartPlay6.setText("Play");

 mPlayProgressBar6.setProgress(0);

 }

 else if (mPlayerState6 == PLAYING6)

 mBtnStartPlay6.setText("Pause");
 
else if (mPlayerState6 == PLAY_PAUSE6)

 mBtnStartPlay6.setText("Start");

 }

}