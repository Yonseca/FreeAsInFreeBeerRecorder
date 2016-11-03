package yonseca.freeasinfreebeerrecorder.Settings;

import android.media.MediaRecorder;

import java.io.File;

/**
 * Created by yonseca on 25/10/16.
 */

public final class RecordingSettings {

    // public static Uri =
    public static int audioEncoder = MediaRecorder.AudioEncoder.VORBIS;
    public static int outputFormat = MediaRecorder.OutputFormat.THREE_GPP;
    public static File path = null;
}
