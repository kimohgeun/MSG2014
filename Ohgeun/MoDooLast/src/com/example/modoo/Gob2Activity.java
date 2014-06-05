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




public class Gob2Activity extends Activity implements View.OnClickListener, OnCompletionListener

{

 	

// 미리 상수 선언	

 private static final int REC_STOP5 = 0;

 private static final int RECORDING5 = 1;
 
private static final int PLAY_STOP5 = 0;

 private static final int PLAYING5 = 1;

 private static final int PLAY_PAUSE5 = 2;



 MediaRecorder mRecorder5 = null;


 
MediaPlayer mPlayer5 = null;



 int mRecState5 = REC_STOP5;

 int mcodepState5 = PLAYING5;

 int mPlayerState5 = PLAY_STOP5;

 SeekBar mRecProgressBar5, mPlayProgressBar5;
 


Button mBtnStartRec5, mBtnStartPlay5, mBtnStopPlay5;

 String mFilePath5, mFileName5 = null;

 TextView mTvPlayMaxPoint5;

 int mCurRecTimeMs5 = 0;

 int mCurProgressTimeDisplay5 = 0;
 




MediaPlayer music5 = null;






 



// 녹음시 SeekBar처리

Handler mProgressHandler5 = new Handler()

 {

 public void handleMessage(Message msg)
 
{

 mCurRecTimeMs5 = mCurRecTimeMs5 + 100;

 mCurProgressTimeDisplay5 = mCurProgressTimeDisplay5 + 100;



 // 녹음시간이 음수이면 정지버튼을 눌러 정지시켰음을 의미하므로 

// SeekBar는 그대로 정지시키고 레코더를 정지시킨다. 

if (mCurRecTimeMs5 < 0)

 {}

 // 녹음시간이 아직 최대녹음제한시간보다 작으면 녹음중이라는 의미이므로

// SeekBar의 위치를 옮겨주고 0.1초 후에 다시 체크하도록 한다. 

else if (mCurRecTimeMs5 < 20000)
 
{

 mRecProgressBar5.setProgress(mCurProgressTimeDisplay5);

 mProgressHandler5.sendEmptyMessageDelayed(0, 100);

 }

 // 녹음시간이 최대 녹음제한 시간보다 크면 녹음을 정지 시킨다. 

else

 {

 mBtnStartRecOnClick5();

 }

 }

 };



 // 재생시 SeekBar 처리

Handler mProgressHandler25 = new Handler()
 
{

 public void handleMessage(Message msg)

 {

 if (mPlayer5 == null) return;



 try

 {

 if (mPlayer5.isPlaying())
 
{

 mPlayProgressBar5.setProgress(mPlayer5.getCurrentPosition());

 mProgressHandler25.sendEmptyMessageDelayed(0, 100);

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





 music5 = MediaPlayer.create(this, R.raw.dp);

	

	 music5.setLooping(true);
 
	

	

	

 









// 미디어 레코더 저장할 파일 생성

mFilePath5 = "/sdcard/Download/";



 // 파일명을 년도월일시간분초 로 생성 겹치는 상황 없애기

SimpleDateFormat timeStampFormat = new SimpleDateFormat(
 
	"yyyyMMddHHmmss");



 // 파일명 위에서 정한 파일명을 WJ 폴더에 저장

mFileName5 = "/WJ"

		 + timeStampFormat.format(new Date()).toString()
 
		+ "Rec.mp4";


 mBtnStartRec5 = (Button) findViewById(R.id.btnStartRec);

 mBtnStartPlay5 = (Button) findViewById(R.id.btnStartPlay);
 
mBtnStopPlay5 = (Button) findViewById(R.id.btnStopPlay);

 mRecProgressBar5 = (SeekBar) findViewById(R.id.recProgressBar);

 mPlayProgressBar5 = (SeekBar) findViewById(R.id.playProgressBar);
 
mTvPlayMaxPoint5 = (TextView) findViewById(R.id.tvPlayMaxPoint);





 music5 = MediaPlayer.create(this, R.raw.dp);





 mBtnStartRec5.setOnClickListener(this);
 
mBtnStartPlay5.setOnClickListener(this);

 mBtnStopPlay5.setOnClickListener(this);



 }

 public void button5(View v){
 
		

		if(music5.isPlaying()){

			 // 음악을 정지합니다

			 music5.stop();
 
			try {

				 // 음악을 재생할경우를 대비해 준비합니다

				 // prepare()은 예외가 2가지나 필요합니다
 
				music5.prepare();

			 } catch (IllegalStateException e) {

				 // TODO Auto-generated catch block
 
				e.printStackTrace();

			 } catch (IOException e) {

				 // TODO Auto-generated catch block
 
				e.printStackTrace();

			 }

			 music5.seekTo(0);

 			

		}else{

		 music5.start();

			 Thread5();
 
		}

	 }

	

 public void Thread5(){

		 Runnable task = new Runnable(){
 
			public void run(){

			

				 while(music5.isPlaying()){
 
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
 
mBtnStartRecOnClick5();



 break;

 case R.id.btnStartPlay:

 mBtnStartPlayOnClick5();

 break;

 case R.id.btnStopPlay:
 
mBtnStopPlayOnClick5();

 break;



 default:

 break;

 }

 }






 private void mBtnStartRecOnClick5()
 
{

 if (mRecState5 == REC_STOP5)

 {

 mRecState5 = RECORDING5;

 startRec5();
 music5.start();

 updateUI5();

 }

 else if (mRecState5 == RECORDING5)
 
{

 mRecState5 = REC_STOP5;

 stopRec5();
 music5.stop();

 updateUI5();

 }

 }



 // 녹음시작

private void startRec5()

 {
 
mCurRecTimeMs5 = 0;

 mCurProgressTimeDisplay5 = 0;



 // SeekBar의 상태를 0.1초후 체크 시작

mProgressHandler5.sendEmptyMessageDelayed(0, 100);



 if (mRecorder5 == null)
 
{

 mRecorder5 = new MediaRecorder();

 mRecorder5.reset();

 }

 else

 {

 mRecorder5.reset();

 }



 try

 {

	

 //오디오 파일 생성

mRecorder5.setAudioSource(MediaRecorder.AudioSource.MIC);

 mRecorder5.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
 
mRecorder5.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);

 mRecorder5.setOutputFile(mFilePath5 + mFileName5);

 mRecorder5.prepare();

 mRecorder5.start();

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

private void stopRec5()

 {

 try

 {

 mRecorder5.stop();

 }

 catch(Exception e){
 
	

}

 finally

 {

 mRecorder5.release();

 mRecorder5 = null;

 }



 mCurRecTimeMs5 = -999;

 // SeekBar의 상태를 즉시 체크 

mProgressHandler5.sendEmptyMessageDelayed(0, 0);

 }



 private void mBtnStartPlayOnClick5()

 {

 if (mPlayerState5 == PLAY_STOP5)

 {

 mPlayerState5 = PLAYING5;

 initMediaPlayer5();

 startPlay5();

 updateUI5();

 }

 else if (mPlayerState5 == PLAYING5)
 
{

 mPlayerState5 = PLAY_PAUSE5;

 pausePlay5();

 updateUI5();

 }

 else if (mPlayerState5 == PLAY_PAUSE5)

 {

 mPlayerState5 = PLAYING5;
 
startPlay5();

 updateUI5();

 }

 }



 private void mBtnStopPlayOnClick5()

 {

 if (mPlayerState5 == PLAYING5 || mPlayerState5 == PLAY_PAUSE5)
 
{

 mPlayerState5 = PLAY_STOP5;

 stopPlay5();

 releaseMediaPlayer5();

 updateUI5(); 

}

 }

 private void initMediaPlayer5()
 
{

 // 미디어 플레이어 생성

if (mPlayer5 == null)

 mPlayer5 = new MediaPlayer();

 else

 mPlayer5.reset();



 mPlayer5.setOnCompletionListener(this);
 
String fullFilePath = mFilePath5 + mFileName5;



 try

 {

 mPlayer5.setDataSource(fullFilePath);

 mPlayer5.prepare(); 

int point = mPlayer5.getDuration();
 
mPlayProgressBar5.setMax(point);



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


 
mTvPlayMaxPoint5.setText(maxMinPointStr + maxSecPointStr);



 mPlayProgressBar5.setProgress(0);

 }

 catch(Exception e)

 {

 Log.v("ProgressRecorder", "미디어 플레이어 Prepare Error ==========> " + e);
 
}

 }



 // 재생 시작

private void startPlay5()

 {

 Log.v("ProgressRecorder", "startPlay().....");



 try
 
{

 mPlayer5.start();



 // SeekBar의 상태를 0.1초마다 체크 

mProgressHandler25.sendEmptyMessageDelayed(0, 100);

 }

 catch (Exception e)
 
{

 e.printStackTrace();

 Toast.makeText(this, "error : " + e.getMessage(), 0).show();

 }

 }



 private void pausePlay5()

 {

 Log.v("ProgressRecorder", "pausePlay().....");



 // 재생을 일시 정지하고

mPlayer5.pause();



 // 재생이 일시정지되면 즉시 SeekBar 메세지 핸들러를 호출한다.
 
mProgressHandler25.sendEmptyMessageDelayed(0, 0);

 }



 private void stopPlay5()

 {

 Log.v("ProgressRecorder", "stopPlay().....");



 // 재생을 중지하고

mPlayer5.stop();



 // 즉시 SeekBar 메세지 핸들러를 호출한다. 

mProgressHandler25.sendEmptyMessageDelayed(0, 0);

 }



 private void releaseMediaPlayer5()
 
{

 Log.v("ProgressRecorder", "releaseMediaPlayer().....");

 mPlayer5.release();

 mPlayer5 = null;

 mPlayProgressBar5.setProgress(0);

 }
 


public void onCompletion(MediaPlayer mp)

 {

 mPlayerState5 = PLAY_STOP5; // 재생이 종료됨




// 재생이 종료되면 즉시 SeekBar 메세지 핸들러를 호출한다.

mProgressHandler25.sendEmptyMessageDelayed(0, 0);
 


updateUI5();

 }



 private void updateUI5()

 {

 if (mRecState5 == REC_STOP5) 

{

 mBtnStartRec5.setText("Rec");
 
mRecProgressBar5.setProgress(0);

 }

 else if (mRecState5 == RECORDING5)

 mBtnStartRec5.setText("Stop");



 if (mPlayerState5 == PLAY_STOP5)
 
{

 mBtnStartPlay5.setText("Play");

 mPlayProgressBar5.setProgress(0);

 }

 else if (mPlayerState5 == PLAYING5)

 mBtnStartPlay5.setText("Pause");
 
else if (mPlayerState5 == PLAY_PAUSE5)

 mBtnStartPlay5.setText("Start");

 }

}