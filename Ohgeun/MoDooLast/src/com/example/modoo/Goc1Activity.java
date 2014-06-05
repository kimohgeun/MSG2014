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




public class Goc1Activity extends Activity implements View.OnClickListener, OnCompletionListener

{

 	

// �̸� ��� ����	

 private static final int REC_STOP7 = 0;

 private static final int RECORDING7 = 1;
 
private static final int PLAY_STOP7 = 0;

 private static final int PLAYING7 = 1;

 private static final int PLAY_PAUSE7 = 2;



 MediaRecorder mRecorder7 = null;


 
MediaPlayer mPlayer7 = null;



 int mRecState7 = REC_STOP7;

 int mcodepState7 = PLAYING7;

 int mPlayerState7 = PLAY_STOP7;

 SeekBar mRecProgressBar7, mPlayProgressBar7;
 


Button mBtnStartRec7, mBtnStartPlay7, mBtnStopPlay7;

 String mFilePath7, mFileName7 = null;

 TextView mTvPlayMaxPoint7;

 int mCurRecTimeMs7 = 0;

 int mCurProgressTimeDisplay7 = 0;
 




MediaPlayer music7 = null;






 



// ������ SeekBaró��

Handler mProgressHandler7 = new Handler()

 {

 public void handleMessage(Message msg)
 
{

 mCurRecTimeMs7 = mCurRecTimeMs7 + 100;

 mCurProgressTimeDisplay7 = mCurProgressTimeDisplay7 + 100;



 // �����ð��� �����̸� ������ư�� ���� ������������ �ǹ��ϹǷ� 

// SeekBar�� �״�� ������Ű�� ���ڴ��� ������Ų��. 

if (mCurRecTimeMs7 < 0)

 {}

 // �����ð��� ���� �ִ�������ѽð����� ������ �������̶�� �ǹ��̹Ƿ�

// SeekBar�� ��ġ�� �Ű��ְ� 0.1�� �Ŀ� �ٽ� üũ�ϵ��� �Ѵ�. 

else if (mCurRecTimeMs7 < 20000)
 
{

 mRecProgressBar7.setProgress(mCurProgressTimeDisplay7);

 mProgressHandler7.sendEmptyMessageDelayed(0, 100);

 }

 // �����ð��� �ִ� �������� �ð����� ũ�� ������ ���� ��Ų��. 

else

 {

 mBtnStartRecOnClick7();

 }

 }

 };



 // ����� SeekBar ó��

Handler mProgressHandler27 = new Handler()
 
{

 public void handleMessage(Message msg)

 {

 if (mPlayer7 == null) return;



 try

 {

 if (mPlayer7.isPlaying())
 
{

 mPlayProgressBar7.setProgress(mPlayer7.getCurrentPosition());

 mProgressHandler27.sendEmptyMessageDelayed(0, 100);

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
 
setContentView(R.layout.goc1);





 music7 = MediaPlayer.create(this, R.raw.gs);

	

	 music7.setLooping(true);
 
	

	

	

 









// �̵�� ���ڴ� ������ ���� ����

mFilePath7 = "/sdcard/Download/";



 // ���ϸ��� �⵵���Ͻð����� �� ���� ��ġ�� ��Ȳ ���ֱ�

SimpleDateFormat timeStampFormat = new SimpleDateFormat(
 
	"yyyyMMddHHmmss");



 // ���ϸ� ������ ���� ���ϸ��� WJ ������ ����

mFileName7 = "/WJ"

		 + timeStampFormat.format(new Date()).toString()
 
		+ "Rec.mp4";


 mBtnStartRec7 = (Button) findViewById(R.id.btnStartRec);

 mBtnStartPlay7 = (Button) findViewById(R.id.btnStartPlay);
 
mBtnStopPlay7 = (Button) findViewById(R.id.btnStopPlay);

 mRecProgressBar7 = (SeekBar) findViewById(R.id.recProgressBar);

 mPlayProgressBar7 = (SeekBar) findViewById(R.id.playProgressBar);
 
mTvPlayMaxPoint7 = (TextView) findViewById(R.id.tvPlayMaxPoint);





 music7 = MediaPlayer.create(this, R.raw.gs);





 mBtnStartRec7.setOnClickListener(this);
 
mBtnStartPlay7.setOnClickListener(this);

 mBtnStopPlay7.setOnClickListener(this);



 }

 public void button7(View v){
 
		

		if(music7.isPlaying()){

			 // ������ �����մϴ�

			 music7.stop();
 
			try {

				 // ������ ����Ұ�츦 ����� �غ��մϴ�

				 // prepare()�� ���ܰ� 2������ �ʿ��մϴ�
 
				music7.prepare();

			 } catch (IllegalStateException e) {

				 // TODO Auto-generated catch block
 
				e.printStackTrace();

			 } catch (IOException e) {

				 // TODO Auto-generated catch block
 
				e.printStackTrace();

			 }

			 music7.seekTo(0);

 			

		}else{

		 music7.start();

			 Thread7();
 
		}

	 }

	

 public void Thread7(){

		 Runnable task = new Runnable(){
 
			public void run(){

			

				 while(music7.isPlaying()){
 
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
 
mBtnStartRecOnClick7();



 break;

 case R.id.btnStartPlay:

 mBtnStartPlayOnClick7();

 break;

 case R.id.btnStopPlay:
 
mBtnStopPlayOnClick7();

 break;



 default:

 break;

 }

 }






 private void mBtnStartRecOnClick7()
 
{

 if (mRecState7 == REC_STOP7)

 {

 mRecState7 = RECORDING7;

 startRec7();
 music7.start();

 updateUI7();

 }

 else if (mRecState7 == RECORDING7)
 
{

 mRecState7 = REC_STOP7;

 stopRec7();
 music7.stop();

 updateUI7();

 }

 }



 // ��������

private void startRec7()

 {
 
mCurRecTimeMs7 = 0;

 mCurProgressTimeDisplay7 = 0;



 // SeekBar�� ���¸� 0.1���� üũ ����

mProgressHandler7.sendEmptyMessageDelayed(0, 100);



 if (mRecorder7 == null)
 
{

 mRecorder7 = new MediaRecorder();

 mRecorder7.reset();

 }

 else

 {

 mRecorder7.reset();

 }



 try

 {

	

 //����� ���� ����

mRecorder7.setAudioSource(MediaRecorder.AudioSource.MIC);

 mRecorder7.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
 
mRecorder7.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);

 mRecorder7.setOutputFile(mFilePath7 + mFileName7);

 mRecorder7.prepare();

 mRecorder7.start();

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

private void stopRec7()

 {

 try

 {

 mRecorder7.stop();

 }

 catch(Exception e){
 
	

}

 finally

 {

 mRecorder7.release();

 mRecorder7 = null;

 }



 mCurRecTimeMs7 = -999;

 // SeekBar�� ���¸� ��� üũ 

mProgressHandler7.sendEmptyMessageDelayed(0, 0);

 }



 private void mBtnStartPlayOnClick7()

 {

 if (mPlayerState7 == PLAY_STOP7)

 {

 mPlayerState7 = PLAYING7;

 initMediaPlayer7();

 startPlay7();

 updateUI7();

 }

 else if (mPlayerState7 == PLAYING7)
 
{

 mPlayerState7 = PLAY_PAUSE7;

 pausePlay7();

 updateUI7();

 }

 else if (mPlayerState7 == PLAY_PAUSE7)

 {

 mPlayerState7 = PLAYING7;
 
startPlay7();

 updateUI7();

 }

 }



 private void mBtnStopPlayOnClick7()

 {

 if (mPlayerState7 == PLAYING7 || mPlayerState7 == PLAY_PAUSE7)
 
{

 mPlayerState7 = PLAY_STOP7;

 stopPlay7();

 releaseMediaPlayer7();

 updateUI7(); 

}

 }

 private void initMediaPlayer7()
 
{

 // �̵�� �÷��̾� ����

if (mPlayer7 == null)

 mPlayer7 = new MediaPlayer();

 else

 mPlayer7.reset();



 mPlayer7.setOnCompletionListener(this);
 
String fullFilePath = mFilePath7 + mFileName7;



 try

 {

 mPlayer7.setDataSource(fullFilePath);

 mPlayer7.prepare(); 

int point = mPlayer7.getDuration();
 
mPlayProgressBar7.setMax(point);



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


 
mTvPlayMaxPoint7.setText(maxMinPointStr + maxSecPointStr);



 mPlayProgressBar7.setProgress(0);

 }

 catch(Exception e)

 {

 Log.v("ProgressRecorder", "�̵�� �÷��̾� Prepare Error ==========> " + e);
 
}

 }



 // ��� ����

private void startPlay7()

 {

 Log.v("ProgressRecorder", "startPlay().....");



 try
 
{

 mPlayer7.start();



 // SeekBar�� ���¸� 0.1�ʸ��� üũ 

mProgressHandler27.sendEmptyMessageDelayed(0, 100);

 }

 catch (Exception e)
 
{

 e.printStackTrace();

 Toast.makeText(this, "error : " + e.getMessage(), 0).show();

 }

 }



 private void pausePlay7()

 {

 Log.v("ProgressRecorder", "pausePlay().....");



 // ����� �Ͻ� �����ϰ�

mPlayer7.pause();



 // ����� �Ͻ������Ǹ� ��� SeekBar �޼��� �ڵ鷯�� ȣ���Ѵ�.
 
mProgressHandler27.sendEmptyMessageDelayed(0, 0);

 }



 private void stopPlay7()

 {

 Log.v("ProgressRecorder", "stopPlay().....");



 // ����� �����ϰ�

mPlayer7.stop();



 // ��� SeekBar �޼��� �ڵ鷯�� ȣ���Ѵ�. 

mProgressHandler27.sendEmptyMessageDelayed(0, 0);

 }



 private void releaseMediaPlayer7()
 
{

 Log.v("ProgressRecorder", "releaseMediaPlayer().....");

 mPlayer7.release();

 mPlayer7 = null;

 mPlayProgressBar7.setProgress(0);

 }
 


public void onCompletion(MediaPlayer mp)

 {

 mPlayerState7 = PLAY_STOP7; // ����� �����




// ����� ����Ǹ� ��� SeekBar �޼��� �ڵ鷯�� ȣ���Ѵ�.

mProgressHandler27.sendEmptyMessageDelayed(0, 0);
 


updateUI7();

 }



 private void updateUI7()

 {

 if (mRecState7 == REC_STOP7) 

{

 mBtnStartRec7.setText("Rec");
 
mRecProgressBar7.setProgress(0);

 }

 else if (mRecState7 == RECORDING7)

 mBtnStartRec7.setText("Stop");



 if (mPlayerState7 == PLAY_STOP7)
 
{

 mBtnStartPlay7.setText("Play");

 mPlayProgressBar7.setProgress(0);

 }

 else if (mPlayerState7 == PLAYING7)

 mBtnStartPlay7.setText("Pause");
 
else if (mPlayerState7 == PLAY_PAUSE7)

 mBtnStartPlay7.setText("Start");

 }

}