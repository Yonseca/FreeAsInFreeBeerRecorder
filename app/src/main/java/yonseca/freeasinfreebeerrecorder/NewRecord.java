package yonseca.freeasinfreebeerrecorder;

import android.content.SharedPreferences;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class NewRecord extends AppCompatActivity {

    final MediaRecorder mic = new MediaRecorder();
    Button startButton, stopButton;
    private SharedPreferences preferences;
    private Calendar date = new GregorianCalendar();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startButton = (Button) findViewById(R.id.button);
        stopButton = (Button) findViewById(R.id.button2);
        preferences = getSharedPreferences("Settings", MODE_PRIVATE);
        preferences.edit().putString("filePath", getFilesDir().getAbsolutePath() + "/" + date.get(Calendar.YEAR) + date.get(Calendar.MONTH) + date.get(Calendar.DAY_OF_MONTH) + date.get(Calendar.HOUR_OF_DAY) + date.get(Calendar.MINUTE) + ".ogg");
        micOn();
        setContentView(R.layout.activity_new_record);

    }

    /*
     * @return A brand new MediaRecorded instance ready to be used.
     */
    public void micOn() {
        try {

            int audioSourceMax = MediaRecorder.getAudioSourceMax();

            mic.setAudioSource(MediaRecorder.AudioSource.MIC);
            mic.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP); // 3GP audio format
            mic.setAudioEncoder(MediaRecorder.AudioEncoder.VORBIS); // OGG Vorbis
            mic.setOutputFile(preferences.getString("filePath", ""));


            mic.prepare();

            startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mic.start();
                }
            });

            stopButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mic.stop();

                    mic.release();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
