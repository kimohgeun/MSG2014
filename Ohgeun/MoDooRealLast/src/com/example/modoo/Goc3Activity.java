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




public class Goc3Activity extends Activity implements View.OnClickListener, OnCompletionListener

{

 	

// 미리 상수 선언	

 private static final int REC_STOP9 = 0;

 private static final int RECORDING9 = 1;
 
private static final int PLAY_STOP9 = 0;

 private static final int PLAYING9 = 1;

 private static final int PLAY_PAUSE9 = 2;



 MediaRecorder mRecorder9 = null;


 
MediaPlayer mPlayer9 = null;



 int mRecState9 = REC_STOP9;

 int mcodepState9 = PLAYING9;

 int mPlayerState9 = PLAY_STOP9;

 SeekBar mRecProgressBar9, mPlayProgressBar9;
 


Button mBtnStartRec9, mBtnStartPlay9, mBtnStopPlay9;

 String mFilePath9, mFileName9 = null;

 TextView mTvPlayMaxPoint9;

 int mCurRecTimeMs9 = 0;

 int mCurProgressTimeDisplay9 = 0;
 




MediaPlayer music9 = null;






 



// 녹음시 SeekBar처리

Handler mProgressHandler9 = new Handler()

 {

 public void handleMessage(Message msg)
 
{

 mCurRecTimeMs9 = mCurRecTimeMs9 + 100;

 mCurProgressTimeDisplay9 = mCurProgressTimeDisplay9 + 100;



 // 녹음시간이 음수이면 정지버튼을 눌러 정지시켰음을 의미하므로 

// SeekBar는 그대로 정지시키고 레코더를 정지시킨다. 

if (mCurRecTimeMs9 < 0)

 {}

 // 녹음시간이 아직 최대녹음제한시간보다 작으면 녹음중이라는 의미이므로

// SeekBar의 위치를 옮겨주고 0.1초 후에 다시 체크하도록 한다. 

else if (mCurRecTimeMs9 < 20000)
 
{

 mRecProgressBar9.setProgress(mCurProgressTimeDisplay9);

 mProgressHandler9.sendEmptyMessageDelayed(0, 100);

 }

 // 녹음시간이 최대 녹음제한 시간보다 크면 녹음을 정지 시킨다. 

else

 {

 mBtnStartRecOnClick9();

 }

 }

 };



 // 재생시 SeekBar 처리

Handler mProgressHandler29 = new Handler()
 
{

 public void handleMessage(Message msg)

 {

 if (mPlayer9 == null) return;



 try

 {

 if (mPlayer9.isPlaying())
 
{

 mPlayProgressBar9.setProgress(mPlayer9.getCurrentPosition());

 mProgressHandler29.sendEmptyMessageDelayed(0, 100);

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
 
setContentView(R.layout.goc3);





 music9 = MediaPlayer.create(this, R.raw.ga);

	

	 music9.setLooping(true);
 
	

	

	

 









// 미디어 레코더 저장할 파일 생성

mFilePath9 = "/sdcard/Download/";



 // 파일명을 년도월일시간분초 로 생성 겹치는 상황 없애기

SimpleDateFormat timeStampFormat = new SimpleDateFormat(
 
	"yyyyMMddHHmmss");



 // 파일명 위에서 정한 파일명을 WJ 폴더에 저장

mFileName9 = "/WJ"

		 + timeStampFormat.format(new Date()).toString()
 
		+ "Rec.mp4";


 mBtnStartRec9 = (Button) findViewById(R.id.btnStartRec);

 mBtnStartPlay9 = (Button) findViewById(R.id.btnStartPlay);
 
mBtnStopPlay9 = (Button) findViewById(R.id.btnStopPlay);

 mRecProgressBar9 = (SeekBar) findViewById(R.id.recProgressBar);

 mPlayProgressBar9 = (SeekBar) findViewById(R.id.playProgressBar);
 
mTvPlayMaxPoint9 = (TextView) findViewById(R.id.tvPlayMaxPoint);





 music9 = MediaPlayer.create(this, R.raw.ga);





 mBtnStartRec9.setOnClickListener(this);
 
mBtnStartPlay9.setOnClickListener(this);

 mBtnStopPlay9.setOnClickListener(this);



 }

 public void button9(View v){
 
		

		if(music9.isPlaying()){

			 // 음악을 정지합니다

			 music9.stop();
 
			try {

				 // 음악을 재생할경우를 대비해 준비합니다

				 // prepare()은 예외가 2가지나 필요합니다
 
				music9.prepare();

			 } catch (IllegalStateException e) {

				 // TODO Auto-generated catch block
 
				e.printStackTrace();

			 } catch (IOException e) {

				 // TODO Auto-generated catch block
 
				e.printStackTrace();

			 }

			 music9.seekTo(0);

 			

		}else{

		 music9.start();

			 Thread9();
 
		}

	 }

	

 public void Thread9(){

		 Runnable task = new Runnable(){
 
			public void run(){

			

				 while(music9.isPlaying()){
 
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
 
mBtnStartRecOnClick9();



 break;

 case R.id.btnStartPlay:

 mBtnStartPlayOnClick9();

 break;

 case R.id.btnStopPlay:
 
mBtnStopPlayOnClick9();

 break;



 default:

 break;

 }

 }






 private void mBtnStartRecOnClick9()
 
{

 if (mRecState9 == REC_STOP9)

 {

 mRecState9 = RECORDING9;

 startRec9();
 music9.start();

 updateUI9();

 }

 else if (mRecState9 == RECORDING9)
 
{

 mRecState9 = REC_STOP9;

 stopRec9();
 music9.stop();

 updateUI9();

 }

 }



 // 녹음시작

private void startRec9()

 {
 
mCurRecTimeMs9 = 0;

 mCurProgressTimeDisplay9 = 0;



 // SeekBar의 상태를 0.1초후 체크 시작

mProgressHandler9.sendEmptyMessageDelayed(0, 100);



 if (mRecorder9 == null)
 
{

 mRecorder9 = new MediaRecorder();

 mRecorder9.reset();

 }

 else

 {

 mRecorder9.reset();

 }



 try

 {

	

 //오디오 파일 생성

mRecorder9.setAudioSource(MediaRecorder.AudioSource.MIC);

 mRecorder9.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
 
mRecorder9.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);

 mRecorder9.setOutputFile(mFilePath9 + mFileName9);

 mRecorder9.prepare();

 mRecorder9.start();

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

private void stopRec9()

 {

 try

 {

 mRecorder9.stop();

 }

 catch(Exception e){
 
	

}

 finally

 {

 mRecorder9.release();

 mRecorder9 = null;

 }



 mCurRecTimeMs9 = -999;

 // SeekBar의 상태를 즉시 체크 

mProgressHandler9.sendEmptyMessageDelayed(0, 0);

 }



 private void mBtnStartPlayOnClick9()

 {

 if (mPlayerState9 == PLAY_STOP9)

 {

 mPlayerState9 = PLAYING9;

 initMediaPlayer9();

 startPlay9();

 updateUI9();

 }

 else if (mPlayerState9 == PLAYING9)
 
{

 mPlayerState9 = PLAY_PAUSE9;

 pausePlay9();

 updateUI9();

 }

 else if (mPlayerState9 == PLAY_PAUSE9)

 {

 mPlayerState9 = PLAYING9;
 
startPlay9();

 updateUI9();

 }

 }



 private void mBtnStopPlayOnClick9()

 {

 if (mPlayerState9 == PLAYING9 || mPlayerState9 == PLAY_PAUSE9)
 
{

 mPlayerState9 = PLAY_STOP9;

 stopPlay9();

 releaseMediaPlayer9();

 updateUI9(); 

}

 }

 private void initMediaPlayer9()
 
{

 // 미디어 플레이어 생성

if (mPlayer9 == null)

 mPlayer9 = new MediaPlayer();

 else

 mPlayer9.reset();



 mPlayer9.setOnCompletionListener(this);
 
String fullFilePath = mFilePath9 + mFileName9;



 try

 {

 mPlayer9.setDataSource(fullFilePath);

 mPlayer9.prepare(); 

int point = mPlayer9.getDuration();
 
mPlayProgressBar9.setMax(point);



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


 
mTvPlayMaxPoint9.setText(maxMinPointStr + maxSecPointStr);



 mPlayProgressBar9.setProgress(0);

 }

 catch(Exception e)

 {

 Log.v("ProgressRecorder", "미디어 플레이어 Prepare Error ==========> " + e);
 
}

 }



 // 재생 시작

private void startPlay9()

 {

 Log.v("ProgressRecorder", "startPlay().....");



 try
 
{

 mPlayer9.start();



 // SeekBar의 상태를 0.1초마다 체크 

mProgressHandler29.sendEmptyMessageDelayed(0, 100);

 }

 catch (Exception e)
 
{

 e.printStackTrace();

 Toast.makeText(this, "error : " + e.getMessage(), 0).show();

 }

 }



 private void pausePlay9()

 {

 Log.v("ProgressRecorder", "pausePlay().....");



 // 재생을 일시 정지하고

mPlayer9.pause();



 // 재생이 일시정지되면 즉시 SeekBar 메세지 핸들러를 호출한다.
 
mProgressHandler29.sendEmptyMessageDelayed(0, 0);

 }



 private void stopPlay9()

 {

 Log.v("ProgressRecorder", "stopPlay().....");



 // 재생을 중지하고

mPlayer9.stop();



 // 즉시 SeekBar 메세지 핸들러를 호출한다. 

mProgressHandler29.sendEmptyMessageDelayed(0, 0);

 }



 private void releaseMediaPlayer9()
 
{

 Log.v("ProgressRecorder", "releaseMediaPlayer().....");

 mPlayer9.release();

 mPlayer9 = null;

 mPlayProgressBar9.setProgress(0);

 }
 


public void onCompletion(MediaPlayer mp)

 {

 mPlayerState9 = PLAY_STOP9; // 재생이 종료됨




// 재생이 종료되면 즉시 SeekBar 메세지 핸들러를 호출한다.

mProgressHandler29.sendEmptyMessageDelayed(0, 0);
 


updateUI9();

 }



 private void updateUI9()

 {

 if (mRecState9 == REC_STOP9) 

{

 mBtnStartRec9.setText("Rec");
 
mRecProgressBar9.setProgress(0);

 }

 else if (mRecState9 == RECORDING9)

 mBtnStartRec9.setText("Stop");



 if (mPlayerState9 == PLAY_STOP9)
 
{

 mBtnStartPlay9.setText("Play");

 mPlayProgressBar9.setProgress(0);

 }

 else if (mPlayerState9 == PLAYING9)

 mBtnStartPlay9.setText("Pause");
 
else if (mPlayerState9 == PLAY_PAUSE9)

 mBtnStartPlay9.setText("Start");

 }

}