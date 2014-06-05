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




public class Goa2Activity extends Activity implements View.OnClickListener, OnCompletionListener

{

 	

// �̸� ��� ����	

 private static final int REC_STOP2 = 0;

 private static final int RECORDING2 = 1;
 
 private static final int PLAY_STOP2 = 0;

 private static final int PLAYING2= 1;

 private static final int PLAY_PAUSE2 = 2;



 MediaRecorder mRecorder2 = null;


 
MediaPlayer mPlayer2 = null;



 int mRecState2 = REC_STOP2;

 int mcodepState2 = PLAYING2;

 int mPlayerState2 = PLAY_STOP2;

 SeekBar mRecProgressBar2, mPlayProgressBar2;
 


Button mBtnStartRec2, mBtnStartPlay2, mBtnStopPlay2;

 String mFilePath2, mFileName2 = null;

 TextView mTvPlayMaxPoint2;

 int mCurRecTimeMs2 = 0;

 int mCurProgressTimeDisplay2 = 0;
 




MediaPlayer music2 = null;






 



// ������ SeekBaró��

Handler mProgressHandler22 = new Handler()

 {

 public void handleMessage(Message msg)
 
{

 mCurRecTimeMs2 = mCurRecTimeMs2 + 100;

 mCurProgressTimeDisplay2 = mCurProgressTimeDisplay2 + 100;



 // �����ð��� �����̸� ������ư�� ���� ������������ �ǹ��ϹǷ� 

// SeekBar�� �״�� ������Ű�� ���ڴ��� ������Ų��. 

if (mCurRecTimeMs2 < 0)

 {}

 // �����ð��� ���� �ִ�������ѽð����� ������ �������̶�� �ǹ��̹Ƿ�

// SeekBar�� ��ġ�� �Ű��ְ� 0.1�� �Ŀ� �ٽ� üũ�ϵ��� �Ѵ�. 

else if (mCurRecTimeMs2 < 20000)
 
{

 mRecProgressBar2.setProgress(mCurProgressTimeDisplay2);

 mProgressHandler22.sendEmptyMessageDelayed(0, 100);

 }

 // �����ð��� �ִ� �������� �ð����� ũ�� ������ ���� ��Ų��. 

else

 {

 mBtnStartRecOnClick2();

 }

 }

 };



 // ����� SeekBar ó��

Handler mProgressHandler222 = new Handler()
 
{

 public void handleMessage(Message msg)

 {

 if (mPlayer2 == null) return;



 try

 {

 if (mPlayer2.isPlaying())
 
{

 mPlayProgressBar2.setProgress(mPlayer2.getCurrentPosition());

 mProgressHandler222.sendEmptyMessageDelayed(0, 100);

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





 music2 = MediaPlayer.create(this, R.raw.cp);

	

	 music2.setLooping(true);
 
	

	

	

 









// �̵�� ���ڴ� ������ ���� ����

mFilePath2 = "/sdcard/Download/";



 // ���ϸ��� �⵵���Ͻð����� �� ���� ��ġ�� ��Ȳ ���ֱ�

SimpleDateFormat timeStampFormat = new SimpleDateFormat(
 
	"yyyyMMddHHmmss");



 // ���ϸ� ������ ���� ���ϸ��� WJ ������ ����

mFileName2 = "/WJ"

		 + timeStampFormat.format(new Date()).toString()
 
		+ "Rec.mp4";


 mBtnStartRec2 = (Button) findViewById(R.id.btnStartRec);

 mBtnStartPlay2 = (Button) findViewById(R.id.btnStartPlay);
 
mBtnStopPlay2 = (Button) findViewById(R.id.btnStopPlay);

 mRecProgressBar2 = (SeekBar) findViewById(R.id.recProgressBar);

 mPlayProgressBar2 = (SeekBar) findViewById(R.id.playProgressBar);
 
mTvPlayMaxPoint2 = (TextView) findViewById(R.id.tvPlayMaxPoint);





 music2 = MediaPlayer.create(this, R.raw.cp);





 mBtnStartRec2.setOnClickListener(this);
 
mBtnStartPlay2.setOnClickListener(this);

 mBtnStopPlay2.setOnClickListener(this);



 }

 public void button2(View v){
 
		

		if(music2.isPlaying()){

			 // ������ �����մϴ�

			 music2.stop();
 
			try {

				 // ������ ����Ұ�츦 ����� �غ��մϴ�

				 // prepare()�� ���ܰ� 2������ �ʿ��մϴ�
 
				music2.prepare();

			 } catch (IllegalStateException e) {

				 // TODO Auto-generated catch block
 
				e.printStackTrace();

			 } catch (IOException e) {

				 // TODO Auto-generated catch block
 
				e.printStackTrace();

			 }

			 music2.seekTo(0);

 			

		}else{

		 music2.start();

			 Thread2();
 
		}

	 }

	

 public void Thread2(){

		 Runnable task = new Runnable(){
 
			public void run(){

			

				 while(music2.isPlaying()){
 
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
 
mBtnStartRecOnClick2();



 break;

 case R.id.btnStartPlay:

 mBtnStartPlayOnClick2();

 break;

 case R.id.btnStopPlay:
 
mBtnStopPlayOnClick2();

 break;



 default:

 break;

 }

 }






 private void mBtnStartRecOnClick2()
 
{

 if (mRecState2 == REC_STOP2)

 {

 mRecState2 = RECORDING2;

 startRec2();
 music2.start();

 updateUI2();

 }

 else if (mRecState2 == RECORDING2)
 
{

 mRecState2 = REC_STOP2;

 stopRec2();
 music2.stop();

 updateUI2();

 }

 }



 // ��������

private void startRec2()

 {
 
mCurRecTimeMs2 = 0;

 mCurProgressTimeDisplay2 = 0;



 // SeekBar�� ���¸� 0.1���� üũ ����

mProgressHandler22.sendEmptyMessageDelayed(0, 100);



 if (mRecorder2 == null)
 
{

 mRecorder2 = new MediaRecorder();

 mRecorder2.reset();

 }

 else

 {

 mRecorder2.reset();

 }



 try

 {

	

 //����� ���� ����

mRecorder2.setAudioSource(MediaRecorder.AudioSource.MIC);

 mRecorder2.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
 
mRecorder2.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);

 mRecorder2.setOutputFile(mFilePath2 + mFileName2);

 mRecorder2.prepare();

 mRecorder2.start();

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

private void stopRec2()

 {

 try

 {

 mRecorder2.stop();

 }

 catch(Exception e){
 
	

}

 finally

 {

 mRecorder2.release();

 mRecorder2 = null;

 }



 mCurRecTimeMs2 = -999;

 // SeekBar�� ���¸� ��� üũ 

mProgressHandler22.sendEmptyMessageDelayed(0, 0);

 }



 private void mBtnStartPlayOnClick2()

 {

 if (mPlayerState2 == PLAY_STOP2)

 {

 mPlayerState2 = PLAYING2;

 initMediaPlayer2();

 startPlay2();

 updateUI2();

 }

 else if (mPlayerState2 == PLAYING2)
 
{

 mPlayerState2 = PLAY_PAUSE2;

 pausePlay2();

 updateUI2();

 }

 else if (mPlayerState2 == PLAY_PAUSE2)

 {

 mPlayerState2 = PLAYING2;
 
startPlay2();

 updateUI2();

 }

 }



 private void mBtnStopPlayOnClick2()

 {

 if (mPlayerState2 == PLAYING2|| mPlayerState2 == PLAY_PAUSE2)
 
{

 mPlayerState2 = PLAY_STOP2;

 stopPlay2();

 releaseMediaPlayer2();

 updateUI2(); 

}

 }

 private void initMediaPlayer2()
 
{

 // �̵�� �÷��̾� ����

if (mPlayer2 == null)

 mPlayer2 = new MediaPlayer();

 else

 mPlayer2.reset();



 mPlayer2.setOnCompletionListener(this);
 
String fullFilePath = mFilePath2 + mFileName2;



 try

 {

 mPlayer2.setDataSource(fullFilePath);

 mPlayer2.prepare(); 

int point = mPlayer2.getDuration();
 
mPlayProgressBar2.setMax(point);



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


 
mTvPlayMaxPoint2.setText(maxMinPointStr + maxSecPointStr);



 mPlayProgressBar2.setProgress(0);

 }

 catch(Exception e)

 {

 Log.v("ProgressRecorder", "�̵�� �÷��̾� Prepare Error ==========> " + e);
 
}

 }



 // ��� ����

private void startPlay2()

 {

 Log.v("ProgressRecorder", "startPlay().....");



 try
 
{

 mPlayer2.start();



 // SeekBar�� ���¸� 0.1�ʸ��� üũ 

mProgressHandler222.sendEmptyMessageDelayed(0, 100);

 }

 catch (Exception e)
 
{

 e.printStackTrace();

 Toast.makeText(this, "error : " + e.getMessage(), 0).show();

 }

 }



 private void pausePlay2()

 {

 Log.v("ProgressRecorder", "pausePlay().....");



 // ����� �Ͻ� �����ϰ�

mPlayer2.pause();



 // ����� �Ͻ������Ǹ� ��� SeekBar �޼��� �ڵ鷯�� ȣ���Ѵ�.
 
mProgressHandler222.sendEmptyMessageDelayed(0, 0);

 }



 private void stopPlay2()

 {

 Log.v("ProgressRecorder", "stopPlay().....");



 // ����� �����ϰ�

mPlayer2.stop();



 // ��� SeekBar �޼��� �ڵ鷯�� ȣ���Ѵ�. 

mProgressHandler222.sendEmptyMessageDelayed(0, 0);

 }



 private void releaseMediaPlayer2()
 
{

 Log.v("ProgressRecorder", "releaseMediaPlayer().....");

 mPlayer2.release();

 mPlayer2 = null;

 mPlayProgressBar2.setProgress(0);

 }
 


public void onCompletion(MediaPlayer mp)

 {

 mPlayerState2 = PLAY_STOP2; // ����� �����




// ����� ����Ǹ� ��� SeekBar �޼��� �ڵ鷯�� ȣ���Ѵ�.

mProgressHandler222.sendEmptyMessageDelayed(0, 0);
 


updateUI2();

 }



 private void updateUI2()

 {

 if (mRecState2 == REC_STOP2) 

{

 mBtnStartRec2.setText("Rec");
 
mRecProgressBar2.setProgress(0);

 }

 else if (mRecState2 == RECORDING2)

 mBtnStartRec2.setText("Stop");



 if (mPlayerState2 == PLAY_STOP2)
 
{

 mBtnStartPlay2.setText("Play");

 mPlayProgressBar2.setProgress(0);

 }

 else if (mPlayerState2 == PLAYING2)

 mBtnStartPlay2.setText("Pause");
 
else if (mPlayerState2 == PLAY_PAUSE2)

 mBtnStartPlay2.setText("Start");

 }

}