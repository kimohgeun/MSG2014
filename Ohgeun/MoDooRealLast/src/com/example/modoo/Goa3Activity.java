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




public class Goa3Activity extends Activity implements View.OnClickListener, OnCompletionListener

{

 	

// 미리 상수 선언	

 private static final int REC_STOP3 = 0;

 private static final int RECORDING3 = 1;
 
private static final int PLAY_STOP3 = 0;

 private static final int PLAYING3 = 1;

 private static final int PLAY_PAUSE3 = 2;



 MediaRecorder mRecorder3 = null;


 
MediaPlayer mPlayer3 = null;



 int mRecState3 = REC_STOP3;

 int mcodepState3 = PLAYING3;

 int mPlayerState3 = PLAY_STOP3;

 SeekBar mRecProgressBar3, mPlayProgressBar3;
 


Button mBtnStartRec3, mBtnStartPlay3, mBtnStopPlay3;

 String mFilePath3, mFileName3 = null;

 TextView mTvPlayMaxPoint3;

 int mCurRecTimeMs3 = 0;

 int mCurProgressTimeDisplay3 = 0;
 




MediaPlayer music3 = null;






 



// 녹음시 SeekBar처리

Handler mProgressHandler3 = new Handler()

 {

 public void handleMessage(Message msg)
 
{

 mCurRecTimeMs3 = mCurRecTimeMs3 + 100;

 mCurProgressTimeDisplay3 = mCurProgressTimeDisplay3 + 100;



 // 녹음시간이 음수이면 정지버튼을 눌러 정지시켰음을 의미하므로 

// SeekBar는 그대로 정지시키고 레코더를 정지시킨다. 

if (mCurRecTimeMs3 < 0)

 {}

 // 녹음시간이 아직 최대녹음제한시간보다 작으면 녹음중이라는 의미이므로

// SeekBar의 위치를 옮겨주고 0.1초 후에 다시 체크하도록 한다. 

else if (mCurRecTimeMs3 < 20000)
 
{

 mRecProgressBar3.setProgress(mCurProgressTimeDisplay3);

 mProgressHandler3.sendEmptyMessageDelayed(0, 100);

 }

 // 녹음시간이 최대 녹음제한 시간보다 크면 녹음을 정지 시킨다. 

else

 {

 mBtnStartRecOnClick3();

 }

 }

 };



 // 재생시 SeekBar 처리

Handler mProgressHandler23 = new Handler()
 
{

 public void handleMessage(Message msg)

 {

 if (mPlayer3 == null) return;



 try

 {

 if (mPlayer3.isPlaying())
 
{

 mPlayProgressBar3.setProgress(mPlayer3.getCurrentPosition());

 mProgressHandler23.sendEmptyMessageDelayed(0, 100);

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





 music3 = MediaPlayer.create(this, R.raw.ca);

	

	 music3.setLooping(true);
 
	

	

	

 









// 미디어 레코더 저장할 파일 생성

mFilePath3 = "/sdcard/Download/";



 // 파일명을 년도월일시간분초 로 생성 겹치는 상황 없애기

SimpleDateFormat timeStampFormat = new SimpleDateFormat(
 
	"yyyyMMddHHmmss");



 // 파일명 위에서 정한 파일명을 WJ 폴더에 저장

mFileName3 = "/WJ"

		 + timeStampFormat.format(new Date()).toString()
 
		+ "Rec.mp4";


 mBtnStartRec3 = (Button) findViewById(R.id.btnStartRec);

 mBtnStartPlay3 = (Button) findViewById(R.id.btnStartPlay);
 
mBtnStopPlay3 = (Button) findViewById(R.id.btnStopPlay);

 mRecProgressBar3 = (SeekBar) findViewById(R.id.recProgressBar);

 mPlayProgressBar3 = (SeekBar) findViewById(R.id.playProgressBar);
 
mTvPlayMaxPoint3 = (TextView) findViewById(R.id.tvPlayMaxPoint);





 music3 = MediaPlayer.create(this, R.raw.ca);





 mBtnStartRec3.setOnClickListener(this);
 
mBtnStartPlay3.setOnClickListener(this);

 mBtnStopPlay3.setOnClickListener(this);



 }

 public void button3(View v){
 
		

		if(music3.isPlaying()){

			 // 음악을 정지합니다

			 music3.stop();
 
			try {

				 // 음악을 재생할경우를 대비해 준비합니다

				 // prepare()은 예외가 2가지나 필요합니다
 
				music3.prepare();

			 } catch (IllegalStateException e) {

				 // TODO Auto-generated catch block
 
				e.printStackTrace();

			 } catch (IOException e) {

				 // TODO Auto-generated catch block
 
				e.printStackTrace();

			 }

			 music3.seekTo(0);

 			

		}else{

		 music3.start();

			 Thread3();
 
		}

	 }

	

 public void Thread3(){

		 Runnable task = new Runnable(){
 
			public void run(){

			

				 while(music3.isPlaying()){
 
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
 
mBtnStartRecOnClick3();



 break;

 case R.id.btnStartPlay:

 mBtnStartPlayOnClick3();

 break;

 case R.id.btnStopPlay:
 
mBtnStopPlayOnClick3();

 break;



 default:

 break;

 }

 }






 private void mBtnStartRecOnClick3()
 
{

 if (mRecState3 == REC_STOP3)

 {

 mRecState3 = RECORDING3;

 startRec3();
 music3.start();

 updateUI3();

 }

 else if (mRecState3 == RECORDING3)
 
{

 mRecState3 = REC_STOP3;

 stopRec3();
 music3.stop();

 updateUI3();

 }

 }



 // 녹음시작

private void startRec3()

 {
 
mCurRecTimeMs3 = 0;

 mCurProgressTimeDisplay3 = 0;



 // SeekBar의 상태를 0.1초후 체크 시작

mProgressHandler3.sendEmptyMessageDelayed(0, 100);



 if (mRecorder3 == null)
 
{

 mRecorder3 = new MediaRecorder();

 mRecorder3.reset();

 }

 else

 {

 mRecorder3.reset();

 }



 try

 {

	

 //오디오 파일 생성

mRecorder3.setAudioSource(MediaRecorder.AudioSource.MIC);

 mRecorder3.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
 
mRecorder3.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);

 mRecorder3.setOutputFile(mFilePath3 + mFileName3);

 mRecorder3.prepare();

 mRecorder3.start();

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

private void stopRec3()

 {

 try

 {

 mRecorder3.stop();

 }

 catch(Exception e){
 
	

}

 finally

 {

 mRecorder3.release();

 mRecorder3 = null;

 }



 mCurRecTimeMs3 = -999;

 // SeekBar의 상태를 즉시 체크 

mProgressHandler3.sendEmptyMessageDelayed(0, 0);

 }



 private void mBtnStartPlayOnClick3()

 {

 if (mPlayerState3 == PLAY_STOP3)

 {

 mPlayerState3 = PLAYING3;

 initMediaPlayer3();

 startPlay3();

 updateUI3();

 }

 else if (mPlayerState3 == PLAYING3)
 
{

 mPlayerState3 = PLAY_PAUSE3;

 pausePlay3();

 updateUI3();

 }

 else if (mPlayerState3 == PLAY_PAUSE3)

 {

 mPlayerState3 = PLAYING3;
 
startPlay3();

 updateUI3();

 }

 }



 private void mBtnStopPlayOnClick3()

 {

 if (mPlayerState3 == PLAYING3 || mPlayerState3 == PLAY_PAUSE3)
 
{

 mPlayerState3 = PLAY_STOP3;

 stopPlay3();

 releaseMediaPlayer3();

 updateUI3(); 

}

 }

 private void initMediaPlayer3()
 
{

 // 미디어 플레이어 생성

if (mPlayer3 == null)

 mPlayer3 = new MediaPlayer();

 else

 mPlayer3.reset();



 mPlayer3.setOnCompletionListener(this);
 
String fullFilePath = mFilePath3 + mFileName3;



 try

 {

 mPlayer3.setDataSource(fullFilePath);

 mPlayer3.prepare(); 

int point = mPlayer3.getDuration();
 
mPlayProgressBar3.setMax(point);



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


 
mTvPlayMaxPoint3.setText(maxMinPointStr + maxSecPointStr);



 mPlayProgressBar3.setProgress(0);

 }

 catch(Exception e)

 {

 Log.v("ProgressRecorder", "미디어 플레이어 Prepare Error ==========> " + e);
 
}

 }



 // 재생 시작

private void startPlay3()

 {

 Log.v("ProgressRecorder", "startPlay().....");



 try
 
{

 mPlayer3.start();



 // SeekBar의 상태를 0.1초마다 체크 

mProgressHandler23.sendEmptyMessageDelayed(0, 100);

 }

 catch (Exception e)
 
{

 e.printStackTrace();

 Toast.makeText(this, "error : " + e.getMessage(), 0).show();

 }

 }



 private void pausePlay3()

 {

 Log.v("ProgressRecorder", "pausePlay().....");



 // 재생을 일시 정지하고

mPlayer3.pause();



 // 재생이 일시정지되면 즉시 SeekBar 메세지 핸들러를 호출한다.
 
mProgressHandler23.sendEmptyMessageDelayed(0, 0);

 }



 private void stopPlay3()

 {

 Log.v("ProgressRecorder", "stopPlay().....");



 // 재생을 중지하고

mPlayer3.stop();



 // 즉시 SeekBar 메세지 핸들러를 호출한다. 

mProgressHandler23.sendEmptyMessageDelayed(0, 0);

 }



 private void releaseMediaPlayer3()
 
{

 Log.v("ProgressRecorder", "releaseMediaPlayer().....");

 mPlayer3.release();

 mPlayer3 = null;

 mPlayProgressBar3.setProgress(0);

 }
 


public void onCompletion(MediaPlayer mp)

 {

 mPlayerState3 = PLAY_STOP3; // 재생이 종료됨




// 재생이 종료되면 즉시 SeekBar 메세지 핸들러를 호출한다.

mProgressHandler23.sendEmptyMessageDelayed(0, 0);
 


updateUI3();

 }



 private void updateUI3()

 {

 if (mRecState3 == REC_STOP3) 

{

 mBtnStartRec3.setText("Rec");
 
mRecProgressBar3.setProgress(0);

 }

 else if (mRecState3 == RECORDING3)

 mBtnStartRec3.setText("Stop");



 if (mPlayerState3 == PLAY_STOP3)
 
{

 mBtnStartPlay3.setText("Play");

 mPlayProgressBar3.setProgress(0);

 }

 else if (mPlayerState3 == PLAYING3)

 mBtnStartPlay3.setText("Pause");
 
else if (mPlayerState3 == PLAY_PAUSE3)

 mBtnStartPlay3.setText("Start");

 }

}