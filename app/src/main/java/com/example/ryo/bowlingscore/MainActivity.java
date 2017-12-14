package com.example.ryo.bowlingscore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"!!!!!!!!!!!!!!!!!!onCreate");

        setContentView(R.layout.activity_main);
        findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // クリック時の処理
                Log.i(TAG,"voice_icon touth");
                startSpeechRecognition();
            }
        });
    }
    private SpeechRecognizer mRecognizer;
    private RecognitionListener mRecognitionListener = new RecognitionListener() {
        @Override
        public void onError(int error) {
            Log.e(TAG, "onError: " + SpeechRecognizerUtil.getErrorMessage(error));
        }

        @Override
        public void onResults(Bundle results) {
            ArrayList<String> values = results
                    .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            String val = values.get(0);
            Log.i(TAG, "認識結果: " + val);
            toastMake(val, 0, -200);
            startSpeechRecognition();
        }

        @Override public void onBeginningOfSpeech() {}
        @Override public void onBufferReceived(byte[] arg0) {}
        @Override public void onEndOfSpeech() {}
        @Override public void onEvent(int arg0, Bundle arg1) {}
        @Override public void onPartialResults(Bundle arg0) {}
        @Override public void onReadyForSpeech(Bundle arg0) {}
        @Override public void onRmsChanged(float arg0) {}
    };

    private void startSpeechRecognition() {
        Log.i(TAG,"startSpeechRecognition");
        // Need to destroy a recognizer to consecutive recognition?
        if (mRecognizer != null) {
            mRecognizer.destroy();
        }

        // Create a recognizer.
        mRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        mRecognizer.setRecognitionListener(mRecognitionListener);

        // Start recognition.
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mRecognizer.startListening(intent);
    }
    private void toastMake(String message, int x, int y){
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        // 位置調整
        toast.setGravity(Gravity.BOTTOM, x, 100);
        toast.show();
    }
}
