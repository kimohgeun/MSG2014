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

 	

// �̸� ��� ����	

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






 



// ������ SeekBaró��

Handler mProgressHandler5 = new Handler()

 {

 public void handleMessage(Message msg)
 
{

 mCurRecTimeMs5 = mCurRecTimeMs5 + 100;

 mCurProgressTimeDisplay5 = mCurProgressTimeDisplay5 + 100;



 // �����ð��� �����̸� ������ư�� ���� ������������ �ǹ��ϹǷ� 

// SeekBar�� �״�� ������Ű�� ���ڴ��� ������Ų��. 

if (mCurRecTimeMs5 < 0)

 {}

 // �����ð��� ���� �ִ�������ѽð����� ������ �������̶�� �ǹ��̹Ƿ�

// SeekBar�� ��ġ�� �Ű��ְ� 0.1�� �Ŀ� �ٽ� üũ�ϵ��� �Ѵ�. 

else if (mCurRecTimeMs5 < 20000)
 
{

 mRecProgressBar5.setProgress(mCurProgressTimeDisplay5);

 mProgressHandler5.sendEmptyMessageDelayed(0, 100);

 }

 // �����ð��� �ִ� �������� �ð����� ũ�� ������ ���� ��Ų��. 

else

 {

 mBtnStartRecOnClick5();

 }

 }

 };



 // ����� SeekBar ó��

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
 
	

	

	

 









// �̵�� ���ڴ� ������ ���� ����

mFilePath5 = "/sdcard/Download/";



 // ���ϸ��� �⵵���Ͻð����� �� ���� ��ġ�� ��Ȳ ���ֱ�

SimpleDateFormat timeStampFormat = new SimpleDateFormat(
 
	"yyyyMMddHHmmss");



 // ���ϸ� ������ ���� ���ϸ��� WJ ������ ����

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

			 // ������ �����մϴ�

			 music5.stop();
 
			try {

				 // ������ ����Ұ�츦 ����� �غ��մϴ�

				 // prepare()�� ���ܰ� 2������ �ʿ��մϴ�
 
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
 











// ��ư�� OnClick �̺�Ʈ ������ 

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



 // ��������

private void startRec5()

 {
 
mCurRecTimeMs5 = 0;

 mCurProgressTimeDisplay5 = 0;



 // SeekBar�� ���¸� 0.1���� üũ ����

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

	

 //����� ���� ����

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



 // ��������

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

 // SeekBar�� ���¸� ��� üũ 

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

 // �̵�� �÷��̾� ����

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

 Log.v("ProgressRecorder", "�̵�� �÷��̾� Prepare Error ==========> " + e);
 
}

 }



 // ��� ����

private void startPlay5()

 {

 Log.v("ProgressRecorder", "startPlay().....");



 try
 
{

 mPlayer5.start();



 // SeekBar�� ���¸� 0.1�ʸ��� üũ 

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



 // ����� �Ͻ� �����ϰ�

mPlayer5.pause();



 // ����� �Ͻ������Ǹ� ��� SeekBar �޼��� �ڵ鷯�� ȣ���Ѵ�.
 
mProgressHandler25.sendEmptyMessageDelayed(0, 0);

 }



 private void stopPlay5()

 {

 Log.v("ProgressRecorder", "stopPlay().....");



 // ����� �����ϰ�

mPlayer5.stop();



 // ��� SeekBar �޼��� �ڵ鷯�� ȣ���Ѵ�. 

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

 mPlayerState5 = PLAY_STOP5; // ����� �����




// ����� ����Ǹ� ��� SeekBar �޼��� �ڵ鷯�� ȣ���Ѵ�.

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