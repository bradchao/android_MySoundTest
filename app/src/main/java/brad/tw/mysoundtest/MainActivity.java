package brad.tw.mysoundtest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.media.SoundPool;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private SoundPool soundPool;
    private int soundBomb, soundScream;

    private MediaRecorder recorder;
    private File sdroot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);

        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);

        }

        sdroot = Environment.getExternalStorageDirectory();

        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        //soundBomb = soundPool.load(this, R.raw.bomb, 1);
        soundScream = soundPool.load(this, R.raw.scream, 1);

        recorder = new MediaRecorder();


    }

    public void test1(View v){
        //soundPool.play(soundBomb,0.5f,0.5f,1,0,1);
    }
    public void test2(View v){
        soundPool.play(soundScream,0.5f,0.5f,1,0,1);
    }

    public void test3(View v){
//        Intent it = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
//        startActivityForResult(it,1);

        recorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        recorder.setOutputFile(sdroot.getAbsolutePath() + "/brad.3gpp");

        try {
            recorder.prepare();
            recorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void test4(View v){
        recorder.stop();
        recorder.release();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){

        }
    }
}
