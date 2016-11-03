package yonseca.freeasinfreebeerrecorder;

import android.content.SharedPreferences;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
        preferences.edit().putString("filePath", getFullFilePath());
        micOn();
        setContentView(R.layout.activity_new_record);

    }


    /**
     * @return Full file path. Should end up with something like YYYYmmddHHMM.ogg
     */
    @NonNull
    private String getFullFilePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(getFilesDir().getAbsolutePath())
                .append(date.get(Calendar.YEAR))
                .append(date.get(Calendar.MONTH))
                .append(date.get(Calendar.DAY_OF_MONTH))
                .append(date.get(Calendar.HOUR_OF_DAY))
                .append(date.get(Calendar.MINUTE))
                .append(".ogg");

        return sb.toString();
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
            mic.setOutputFile(preferences.getString("filePath", null));


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
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        mic.release();
        super.onPause();
    }

    /**
     * Dispatch onResume() to fragments.  Note that for better inter-operation
     * with older versions of the platform, at the point of this call the
     * fragments attached to the activity are <em>not</em> resumed.  This means
     * that in some cases the previous state may still be saved, not allowing
     * fragment transactions that modify the state.  To correctly interact
     * with fragments in their proper state, you should instead override
     * {@link #onResumeFragments()}.
     */
    @Override
    protected void onResume() {
        micOn();
        super.onResume();
    }
}
