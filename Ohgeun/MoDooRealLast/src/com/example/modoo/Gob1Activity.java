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




public class Gob1Activity extends Activity implements View.OnClickListener, OnCompletionListener

{

 	

// �̸� ��� ����	

 private static final int REC_STOP4 = 0;

 private static final int RECORDING4 = 1;
 
private static final int PLAY_STOP4 = 0;

 private static final int PLAYING4 = 1;

 private static final int PLAY_PAUSE4 = 2;



 MediaRecorder mRecorder4 = null;


 
MediaPlayer mPlayer4 = null;



 int mRecState4 = REC_STOP4;

 int mcodepState4 = PLAYING4;

 int mPlayerState4 = PLAY_STOP4;

 SeekBar mRecProgressBar4, mPlayProgressBar4;
 


Button mBtnStartRec4, mBtnStartPlay4, mBtnStopPlay4;

 String mFilePath4, mFileName4 = null;

 TextView mTvPlayMaxPoint4;

 int mCurRecTimeMs4 = 0;

 int mCurProgressTimeDisplay4 = 0;
 




MediaPlayer music4 = null;






 



// ������ SeekBaró��

Handler mProgressHandler4 = new Handler()

 {

 public void handleMessage(Message msg)
 
{

 mCurRecTimeMs4 = mCurRecTimeMs4 + 100;

 mCurProgressTimeDisplay4 = mCurProgressTimeDisplay4 + 100;



 // �����ð��� �����̸� ������ư�� ���� ������������ �ǹ��ϹǷ� 

// SeekBar�� �״�� ������Ű�� ���ڴ��� ������Ų��. 

if (mCurRecTimeMs4 < 0)

 {}

 // �����ð��� ���� �ִ�������ѽð����� ������ �������̶�� �ǹ��̹Ƿ�

// SeekBar�� ��ġ�� �Ű��ְ� 0.1�� �Ŀ� �ٽ� üũ�ϵ��� �Ѵ�. 

else if (mCurRecTimeMs4 < 20000)
 
{

 mRecProgressBar4.setProgress(mCurProgressTimeDisplay4);

 mProgressHandler4.sendEmptyMessageDelayed(0, 100);

 }

 // �����ð��� �ִ� �������� �ð����� ũ�� ������ ���� ��Ų��. 

else

 {

 mBtnStartRecOnClick4();

 }

 }

 };



 // ����� SeekBar ó��

Handler mProgressHandler24 = new Handler()
 
{

 public void handleMessage(Message msg)

 {

 if (mPlayer4 == null) return;



 try

 {

 if (mPlayer4.isPlaying())
 
{

 mPlayProgressBar4.setProgress(mPlayer4.getCurrentPosition());

 mProgressHandler24.sendEmptyMessageDelayed(0, 100);

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





 music4 = MediaPlayer.create(this, R.raw.ds);

	

	 music4.setLooping(true);
 
	

	

	

 









// �̵�� ���ڴ� ������ ���� ����

mFilePath4 = "/sdcard/Download/";



 // ���ϸ��� �⵵���Ͻð����� �� ���� ��ġ�� ��Ȳ ���ֱ�

SimpleDateFormat timeStampFormat = new SimpleDateFormat(
 
	"yyyyMMddHHmmss");



 // ���ϸ� ������ ���� ���ϸ��� WJ ������ ����

mFileName4 = "/WJ"

		 + timeStampFormat.format(new Date()).toString()
 
		+ "Rec.mp4";


 mBtnStartRec4 = (Button) findViewById(R.id.btnStartRec);

 mBtnStartPlay4 = (Button) findViewById(R.id.btnStartPlay);
 
mBtnStopPlay4 = (Button) findViewById(R.id.btnStopPlay);

 mRecProgressBar4 = (SeekBar) findViewById(R.id.recProgressBar);

 mPlayProgressBar4 = (SeekBar) findViewById(R.id.playProgressBar);
 
mTvPlayMaxPoint4 = (TextView) findViewById(R.id.tvPlayMaxPoint);





 music4 = MediaPlayer.create(this, R.raw.ds);





 mBtnStartRec4.setOnClickListener(this);
 
mBtnStartPlay4.setOnClickListener(this);

 mBtnStopPlay4.setOnClickListener(this);



 }

 public void button4(View v){
 
		

		if(music4.isPlaying()){

			 // ������ �����մϴ�

			 music4.stop();
 
			try {

				 // ������ ����Ұ�츦 ����� �غ��մϴ�

				 // prepare()�� ���ܰ� 2������ �ʿ��մϴ�
 
				music4.prepare();

			 } catch (IllegalStateException e) {

				 // TODO Auto-generated catch block
 
				e.printStackTrace();

			 } catch (IOException e) {

				 // TODO Auto-generated catch block
 
				e.printStackTrace();

			 }

			 music4.seekTo(0);

 			

		}else{

		 music4.start();

			 Thread4();
 
		}

	 }

	

 public void Thread4(){

		 Runnable task = new Runnable(){
 
			public void run(){

			

				 while(music4.isPlaying()){
 
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
 
mBtnStartRecOnClick4();



 break;

 case R.id.btnStartPlay:

 mBtnStartPlayOnClick4();

 break;

 case R.id.btnStopPlay:
 
mBtnStopPlayOnClick4();

 break;



 default:

 break;

 }

 }






 private void mBtnStartRecOnClick4()
 
{

 if (mRecState4 == REC_STOP4)

 {

 mRecState4 = RECORDING4;

 startRec4();
 music4.start();

 updateUI4();

 }

 else if (mRecState4 == RECORDING4)
 
{

 mRecState4 = REC_STOP4;

 stopRec4();
 music4.stop();

 updateUI4();

 }

 }



 // ��������

private void startRec4()

 {
 
mCurRecTimeMs4 = 0;

 mCurProgressTimeDisplay4 = 0;



 // SeekBar�� ���¸� 0.1���� üũ ����

mProgressHandler4.sendEmptyMessageDelayed(0, 100);



 if (mRecorder4 == null)
 
{

 mRecorder4 = new MediaRecorder();

 mRecorder4.reset();

 }

 else

 {

 mRecorder4.reset();

 }



 try

 {

	

 //����� ���� ����

mRecorder4.setAudioSource(MediaRecorder.AudioSource.MIC);

 mRecorder4.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
 
mRecorder4.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);

 mRecorder4.setOutputFile(mFilePath4 + mFileName4);

 mRecorder4.prepare();

 mRecorder4.start();

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

private void stopRec4()

 {

 try

 {

 mRecorder4.stop();

 }

 catch(Exception e){
 
	

}

 finally

 {

 mRecorder4.release();

 mRecorder4 = null;

 }



 mCurRecTimeMs4 = -999;

 // SeekBar�� ���¸� ��� üũ 

mProgressHandler4.sendEmptyMessageDelayed(0, 0);

 }



 private void mBtnStartPlayOnClick4()

 {

 if (mPlayerState4 == PLAY_STOP4)

 {

 mPlayerState4 = PLAYING4;

 initMediaPlayer4();

 startPlay4();

 updateUI4();

 }

 else if (mPlayerState4 == PLAYING4)
 
{

 mPlayerState4 = PLAY_PAUSE4;

 pausePlay4();

 updateUI4();

 }

 else if (mPlayerState4 == PLAY_PAUSE4)

 {

 mPlayerState4 = PLAYING4;
 
startPlay4();

 updateUI4();

 }

 }



 private void mBtnStopPlayOnClick4()

 {

 if (mPlayerState4 == PLAYING4 || mPlayerState4 == PLAY_PAUSE4)
 
{

 mPlayerState4 = PLAY_STOP4;

 stopPlay4();

 releaseMediaPlayer4();

 updateUI4(); 

}

 }

 private void initMediaPlayer4()
 
{

 // �̵�� �÷��̾� ����

if (mPlayer4 == null)

 mPlayer4 = new MediaPlayer();

 else

 mPlayer4.reset();



 mPlayer4.setOnCompletionListener(this);
 
String fullFilePath = mFilePath4 + mFileName4;



 try

 {

 mPlayer4.setDataSource(fullFilePath);

 mPlayer4.prepare(); 

int point = mPlayer4.getDuration();
 
mPlayProgressBar4.setMax(point);



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


 
mTvPlayMaxPoint4.setText(maxMinPointStr + maxSecPointStr);



 mPlayProgressBar4.setProgress(0);

 }

 catch(Exception e)

 {

 Log.v("ProgressRecorder", "�̵�� �÷��̾� Prepare Error ==========> " + e);
 
}

 }



 // ��� ����

private void startPlay4()

 {

 Log.v("ProgressRecorder", "startPlay().....");



 try
 
{

 mPlayer4.start();



 // SeekBar�� ���¸� 0.1�ʸ��� üũ 

mProgressHandler24.sendEmptyMessageDelayed(0, 100);

 }

 catch (Exception e)
 
{

 e.printStackTrace();

 Toast.makeText(this, "error : " + e.getMessage(), 0).show();

 }

 }



 private void pausePlay4()

 {

 Log.v("ProgressRecorder", "pausePlay().....");



 // ����� �Ͻ� �����ϰ�

mPlayer4.pause();



 // ����� �Ͻ������Ǹ� ��� SeekBar �޼��� �ڵ鷯�� ȣ���Ѵ�.
 
mProgressHandler24.sendEmptyMessageDelayed(0, 0);

 }



 private void stopPlay4()

 {

 Log.v("ProgressRecorder", "stopPlay().....");



 // ����� �����ϰ�

mPlayer4.stop();



 // ��� SeekBar �޼��� �ڵ鷯�� ȣ���Ѵ�. 

mProgressHandler24.sendEmptyMessageDelayed(0, 0);

 }



 private void releaseMediaPlayer4()
 
{

 Log.v("ProgressRecorder", "releaseMediaPlayer().....");

 mPlayer4.release();

 mPlayer4 = null;

 mPlayProgressBar4.setProgress(0);

 }
 


public void onCompletion(MediaPlayer mp)

 {

 mPlayerState4 = PLAY_STOP4; // ����� �����




// ����� ����Ǹ� ��� SeekBar �޼��� �ڵ鷯�� ȣ���Ѵ�.

mProgressHandler24.sendEmptyMessageDelayed(0, 0);
 


updateUI4();

 }



 private void updateUI4()

 {

 if (mRecState4 == REC_STOP4) 

{

 mBtnStartRec4.setText("Rec");
 
mRecProgressBar4.setProgress(0);

 }

 else if (mRecState4 == RECORDING4)

 mBtnStartRec4.setText("Stop");



 if (mPlayerState4 == PLAY_STOP4)
 
{

 mBtnStartPlay4.setText("Play");

 mPlayProgressBar4.setProgress(0);

 }

 else if (mPlayerState4 == PLAYING4)

 mBtnStartPlay4.setText("Pause");
 
else if (mPlayerState4 == PLAY_PAUSE4)

 mBtnStartPlay4.setText("Start");

 }

}