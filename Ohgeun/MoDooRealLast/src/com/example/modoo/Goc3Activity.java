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

 	

// �̸� ��� ����	

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






 



// ������ SeekBaró��

Handler mProgressHandler9 = new Handler()

 {

 public void handleMessage(Message msg)
 
{

 mCurRecTimeMs9 = mCurRecTimeMs9 + 100;

 mCurProgressTimeDisplay9 = mCurProgressTimeDisplay9 + 100;



 // �����ð��� �����̸� ������ư�� ���� ������������ �ǹ��ϹǷ� 

// SeekBar�� �״�� ������Ű�� ���ڴ��� ������Ų��. 

if (mCurRecTimeMs9 < 0)

 {}

 // �����ð��� ���� �ִ�������ѽð����� ������ �������̶�� �ǹ��̹Ƿ�

// SeekBar�� ��ġ�� �Ű��ְ� 0.1�� �Ŀ� �ٽ� üũ�ϵ��� �Ѵ�. 

else if (mCurRecTimeMs9 < 20000)
 
{

 mRecProgressBar9.setProgress(mCurProgressTimeDisplay9);

 mProgressHandler9.sendEmptyMessageDelayed(0, 100);

 }

 // �����ð��� �ִ� �������� �ð����� ũ�� ������ ���� ��Ų��. 

else

 {

 mBtnStartRecOnClick9();

 }

 }

 };



 // ����� SeekBar ó��

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
 
	

	

	

 









// �̵�� ���ڴ� ������ ���� ����

mFilePath9 = "/sdcard/Download/";



 // ���ϸ��� �⵵���Ͻð����� �� ���� ��ġ�� ��Ȳ ���ֱ�

SimpleDateFormat timeStampFormat = new SimpleDateFormat(
 
	"yyyyMMddHHmmss");



 // ���ϸ� ������ ���� ���ϸ��� WJ ������ ����

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

			 // ������ �����մϴ�

			 music9.stop();
 
			try {

				 // ������ ����Ұ�츦 ����� �غ��մϴ�

				 // prepare()�� ���ܰ� 2������ �ʿ��մϴ�
 
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
 











// ��ư�� OnClick �̺�Ʈ ������ 

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



 // ��������

private void startRec9()

 {
 
mCurRecTimeMs9 = 0;

 mCurProgressTimeDisplay9 = 0;



 // SeekBar�� ���¸� 0.1���� üũ ����

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

	

 //����� ���� ����

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



 // ��������

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

 // SeekBar�� ���¸� ��� üũ 

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

 // �̵�� �÷��̾� ����

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

 Log.v("ProgressRecorder", "�̵�� �÷��̾� Prepare Error ==========> " + e);
 
}

 }



 // ��� ����

private void startPlay9()

 {

 Log.v("ProgressRecorder", "startPlay().....");



 try
 
{

 mPlayer9.start();



 // SeekBar�� ���¸� 0.1�ʸ��� üũ 

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



 // ����� �Ͻ� �����ϰ�

mPlayer9.pause();



 // ����� �Ͻ������Ǹ� ��� SeekBar �޼��� �ڵ鷯�� ȣ���Ѵ�.
 
mProgressHandler29.sendEmptyMessageDelayed(0, 0);

 }



 private void stopPlay9()

 {

 Log.v("ProgressRecorder", "stopPlay().....");



 // ����� �����ϰ�

mPlayer9.stop();



 // ��� SeekBar �޼��� �ڵ鷯�� ȣ���Ѵ�. 

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

 mPlayerState9 = PLAY_STOP9; // ����� �����




// ����� ����Ǹ� ��� SeekBar �޼��� �ڵ鷯�� ȣ���Ѵ�.

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