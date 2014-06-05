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




public class Goa1Activity extends Activity implements View.OnClickListener, OnCompletionListener

{

 	

// �̸� ��� ����	

 private static final int REC_STOP = 0;

 private static final int RECORDING = 1;
 
private static final int PLAY_STOP = 0;

 private static final int PLAYING = 1;

 private static final int PLAY_PAUSE = 2;



 MediaRecorder mRecorder = null;


 
MediaPlayer mPlayer = null;



 int mRecState = REC_STOP;

 int mcodepState = PLAYING;

 int mPlayerState = PLAY_STOP;

 SeekBar mRecProgressBar, mPlayProgressBar;
 


Button mBtnStartRec, mBtnStartPlay, mBtnStopPlay;

 String mFilePath, mFileName = null;

 TextView mTvPlayMaxPoint;

 int mCurRecTimeMs = 0;

 int mCurProgressTimeDisplay = 0;
 




MediaPlayer music = null;






 



// ������ SeekBaró��

Handler mProgressHandler = new Handler()

 {

 public void handleMessage(Message msg)
 
{

 mCurRecTimeMs = mCurRecTimeMs + 100;

 mCurProgressTimeDisplay = mCurProgressTimeDisplay + 100;



 // �����ð��� �����̸� ������ư�� ���� ������������ �ǹ��ϹǷ� 

// SeekBar�� �״�� ������Ű�� ���ڴ��� ������Ų��. 

if (mCurRecTimeMs < 0)

 {}

 // �����ð��� ���� �ִ�������ѽð����� ������ �������̶�� �ǹ��̹Ƿ�

// SeekBar�� ��ġ�� �Ű��ְ� 0.1�� �Ŀ� �ٽ� üũ�ϵ��� �Ѵ�. 

else if (mCurRecTimeMs < 20000)
 
{

 mRecProgressBar.setProgress(mCurProgressTimeDisplay);

 mProgressHandler.sendEmptyMessageDelayed(0, 100);

 }

 // �����ð��� �ִ� �������� �ð����� ũ�� ������ ���� ��Ų��. 

else

 {

 mBtnStartRecOnClick();

 }

 }

 };



 // ����� SeekBar ó��

Handler mProgressHandler2 = new Handler()
 
{

 public void handleMessage(Message msg)

 {

 if (mPlayer == null) return;



 try

 {

 if (mPlayer.isPlaying())
 
{

 mPlayProgressBar.setProgress(mPlayer.getCurrentPosition());

 mProgressHandler2.sendEmptyMessageDelayed(0, 100);

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





 music = MediaPlayer.create(this, R.raw.cs);

	

	 music.setLooping(true);
 
	

	

	

 









// �̵�� ���ڴ� ������ ���� ����

mFilePath = "/sdcard/Download/";



 // ���ϸ��� �⵵���Ͻð����� �� ���� ��ġ�� ��Ȳ ���ֱ�

SimpleDateFormat timeStampFormat = new SimpleDateFormat(
 
	"yyyyMMddHHmmss");



 // ���ϸ� ������ ���� ���ϸ��� WJ ������ ����

mFileName = "/WJ"

		 + timeStampFormat.format(new Date()).toString()
 
		+ "Rec.mp4";


 mBtnStartRec = (Button) findViewById(R.id.btnStartRec);

 mBtnStartPlay = (Button) findViewById(R.id.btnStartPlay);
 
mBtnStopPlay = (Button) findViewById(R.id.btnStopPlay);

 mRecProgressBar = (SeekBar) findViewById(R.id.recProgressBar);

 mPlayProgressBar = (SeekBar) findViewById(R.id.playProgressBar);
 
mTvPlayMaxPoint = (TextView) findViewById(R.id.tvPlayMaxPoint);





 music = MediaPlayer.create(this, R.raw.cs);





 mBtnStartRec.setOnClickListener(this);
 
mBtnStartPlay.setOnClickListener(this);

 mBtnStopPlay.setOnClickListener(this);



 }

 public void button(View v){
 
		

		if(music.isPlaying()){

			 // ������ �����մϴ�

			 music.stop();
 
			try {

				 // ������ ����Ұ�츦 ����� �غ��մϴ�

				 // prepare()�� ���ܰ� 2������ �ʿ��մϴ�
 
				music.prepare();

			 } catch (IllegalStateException e) {

				 // TODO Auto-generated catch block
 
				e.printStackTrace();

			 } catch (IOException e) {

				 // TODO Auto-generated catch block
 
				e.printStackTrace();

			 }

			 music.seekTo(0);

 			

		}else{

		 music.start();

			 Thread();
 
		}

	 }

	

 public void Thread(){

		 Runnable task = new Runnable(){
 
			public void run(){

			

				 while(music.isPlaying()){
 
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
 











// ��ư�� OnClick �̺�Ʈ ������ 

public void onClick(View v)

 {

 switch(v.getId())

 {

 case R.id.btnStartRec:
 
mBtnStartRecOnClick();



 break;

 case R.id.btnStartPlay:

 mBtnStartPlayOnClick();

 break;

 case R.id.btnStopPlay:
 
mBtnStopPlayOnClick();

 break;



 default:

 break;

 }

 }






 private void mBtnStartRecOnClick()
 
{

 if (mRecState == REC_STOP)

 {

 mRecState = RECORDING;

 startRec();
 music.start();

 updateUI();

 }

 else if (mRecState == RECORDING)
 
{

 mRecState = REC_STOP;

 stopRec();
 music.stop();

 updateUI();

 }

 }



 // ��������

private void startRec()

 {
 
mCurRecTimeMs = 0;

 mCurProgressTimeDisplay = 0;



 // SeekBar�� ���¸� 0.1���� üũ ����

mProgressHandler.sendEmptyMessageDelayed(0, 100);



 if (mRecorder == null)
 
{

 mRecorder = new MediaRecorder();

 mRecorder.reset();

 }

 else

 {

 mRecorder.reset();

 }



 try

 {

	

 //����� ���� ����

mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);

 mRecorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
 
mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);

 mRecorder.setOutputFile(mFilePath + mFileName);

 mRecorder.prepare();

 mRecorder.start();

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



 // ��������

private void stopRec()

 {

 try

 {

 mRecorder.stop();

 }

 catch(Exception e){
 
	

}

 finally

 {

 mRecorder.release();

 mRecorder = null;

 }



 mCurRecTimeMs = -999;

 // SeekBar�� ���¸� ��� üũ 

mProgressHandler.sendEmptyMessageDelayed(0, 0);

 }



 private void mBtnStartPlayOnClick()

 {

 if (mPlayerState == PLAY_STOP)

 {

 mPlayerState = PLAYING;

 initMediaPlayer();

 startPlay();

 updateUI();

 }

 else if (mPlayerState == PLAYING)
 
{

 mPlayerState = PLAY_PAUSE;

 pausePlay();

 updateUI();

 }

 else if (mPlayerState == PLAY_PAUSE)

 {

 mPlayerState = PLAYING;
 
startPlay();

 updateUI();

 }

 }



 private void mBtnStopPlayOnClick()

 {

 if (mPlayerState == PLAYING || mPlayerState == PLAY_PAUSE)
 
{

 mPlayerState = PLAY_STOP;

 stopPlay();

 releaseMediaPlayer();

 updateUI(); 

}

 }

 private void initMediaPlayer()
 
{

 // �̵�� �÷��̾� ����

if (mPlayer == null)

 mPlayer = new MediaPlayer();

 else

 mPlayer.reset();



 mPlayer.setOnCompletionListener(this);
 
String fullFilePath = mFilePath + mFileName;



 try

 {

 mPlayer.setDataSource(fullFilePath);

 mPlayer.prepare(); 

int point = mPlayer.getDuration();
 
mPlayProgressBar.setMax(point);



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


 
mTvPlayMaxPoint.setText(maxMinPointStr + maxSecPointStr);



 mPlayProgressBar.setProgress(0);

 }

 catch(Exception e)

 {

 Log.v("ProgressRecorder", "�̵�� �÷��̾� Prepare Error ==========> " + e);
 
}

 }



 // ��� ����

private void startPlay()

 {

 Log.v("ProgressRecorder", "startPlay().....");



 try
 
{

 mPlayer.start();



 // SeekBar�� ���¸� 0.1�ʸ��� üũ 

mProgressHandler2.sendEmptyMessageDelayed(0, 100);

 }

 catch (Exception e)
 
{

 e.printStackTrace();

 Toast.makeText(this, "error : " + e.getMessage(), 0).show();

 }

 }



 private void pausePlay()

 {

 Log.v("ProgressRecorder", "pausePlay().....");



 // ����� �Ͻ� �����ϰ�

mPlayer.pause();



 // ����� �Ͻ������Ǹ� ��� SeekBar �޼��� �ڵ鷯�� ȣ���Ѵ�.
 
mProgressHandler2.sendEmptyMessageDelayed(0, 0);

 }



 private void stopPlay()

 {

 Log.v("ProgressRecorder", "stopPlay().....");



 // ����� �����ϰ�

mPlayer.stop();



 // ��� SeekBar �޼��� �ڵ鷯�� ȣ���Ѵ�. 

mProgressHandler2.sendEmptyMessageDelayed(0, 0);

 }



 private void releaseMediaPlayer()
 
{

 Log.v("ProgressRecorder", "releaseMediaPlayer().....");

 mPlayer.release();

 mPlayer = null;

 mPlayProgressBar.setProgress(0);

 }
 


public void onCompletion(MediaPlayer mp)

 {

 mPlayerState = PLAY_STOP; // ����� �����




// ����� ����Ǹ� ��� SeekBar �޼��� �ڵ鷯�� ȣ���Ѵ�.

mProgressHandler2.sendEmptyMessageDelayed(0, 0);
 


updateUI();

 }



 private void updateUI()

 {

 if (mRecState == REC_STOP) 

{

 mBtnStartRec.setText("Rec");
 
mRecProgressBar.setProgress(0);

 }

 else if (mRecState == RECORDING)

 mBtnStartRec.setText("Stop");



 if (mPlayerState == PLAY_STOP)
 
{

 mBtnStartPlay.setText("Play");

 mPlayProgressBar.setProgress(0);

 }

 else if (mPlayerState == PLAYING)

 mBtnStartPlay.setText("Pause");
 
else if (mPlayerState == PLAY_PAUSE)

 mBtnStartPlay.setText("Start");

 }

}