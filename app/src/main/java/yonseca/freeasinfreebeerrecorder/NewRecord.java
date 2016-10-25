package yonseca.freeasinfreebeerrecorder;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class NewRecord extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        micOn();
        setContentView(R.layout.activity_new_record);

    }

    /*
     * @return A brand new MediaRecorded instance ready to be used.
     */
    private MediaRecorder micOn() {
        MediaRecorder mic = new MediaRecorder();
        int audioSourceMax = MediaRecorder.getAudioSourceMax();
        if (audioSourceMax > 0) {
            mic.setAudioSource(MediaRecorder.AudioSource.MIC);
        }
        mic.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP); // 3GP audio format
        mic.setAudioEncoder(MediaRecorder.AudioEncoder.VORBIS); // OGG Vorbis


        return mic;
    }
}
