package com.example.speechrecognizer;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Build;
import android.os.VibrationEffect;
import androidx.appcompat.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Vibrator;
import android.content.DialogInterface;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.loadinganimation.LoadingAnimation;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {
    private static final int SPEECH_REQUEST_CODE = 0;
    private SpeechRecognizer speechRecognizer;
    TextView txt;
    LoadingAnimation loadingAnim;
    TextToSpeech t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Vibrator vibrator;
        vibrator= (Vibrator) getSystemService(VIBRATOR_SERVICE);


        txt = findViewById(R.id.textView);
        loadingAnim = findViewById(R.id.loadingAnim);
        loadingAnim.setVisibility(View.INVISIBLE);
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });
       startSpeechRecognition();
    }

    private void startSpeechRecognition() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {
                loadingAnim.setVisibility(View.VISIBLE);
                loadingAnim.setProgressVector(getDrawable(R.drawable.mic));
                loadingAnim.setTextViewVisibility(true);
                loadingAnim.setTextStyle(true);
                //loadingAnim.setTextColor(Color.WHITE);
                //loadingAnim.setTextSize(12F);
                loadingAnim.setTextMsg("");
                loadingAnim.setEnlarge(6);
            }

            @Override

            public void onBeginningOfSpeech() {
                loadingAnim.setTextMsg("Listening");
            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {
                startSpeechRecognition();
                onBeginningOfSpeech();

            }

            @Override
            public void onResults(Bundle results) {
                loadingAnim.setVisibility(View.INVISIBLE);
                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);


                ArrayList<String> result = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                String str = result.get(0).toString();
                String find = "Durva" ;
                String find1 = "Balaji";
                String find2 = "Aishwarya";
                int i = str.indexOf(find);
                int j = str.indexOf(find1);
                int k = str.indexOf(find2);

                if (result != null && result.size() > 0) {

                    if (Build.VERSION.SDK_INT >= 26 && result.get(0).toString().equals("Balaji"))
                    {
                        vibrator.vibrate(VibrationEffect.createOneShot(1200, VibrationEffect.DEFAULT_AMPLITUDE));
                        toneGen1.startTone(ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_INTERGROUP,400);
                        startSpeechRecognition();
                        onBeginningOfSpeech();

                    }
                    else if (Build.VERSION.SDK_INT >= 26 && result.get(0).toString().equals("Durva")){
                        vibrator.vibrate(VibrationEffect.createOneShot(1200, VibrationEffect.DEFAULT_AMPLITUDE));
                        toneGen1.startTone(ToneGenerator.TONE_CDMA_EMERGENCY_RINGBACK,400);
                        startSpeechRecognition();
                        onBeginningOfSpeech();
                    }
                    else if (Build.VERSION.SDK_INT >= 26 && result.get(0).toString().equals("Aishwarya")){
                        vibrator.vibrate(VibrationEffect.createOneShot(1200, VibrationEffect.DEFAULT_AMPLITUDE));
                        toneGen1.startTone(ToneGenerator.TONE_CDMA_EMERGENCY_RINGBACK,400);
                        startSpeechRecognition();
                        onBeginningOfSpeech();}

                    else if(result != null && result.size() > 0 && i>0 && Build.VERSION.SDK_INT >= 26){

                        vibrator.vibrate(VibrationEffect.createOneShot(1200, VibrationEffect.DEFAULT_AMPLITUDE));
                        toneGen1.startTone(ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_INTERGROUP,400);
                        startSpeechRecognition();
                        onBeginningOfSpeech();

                    }

                    else if(result != null && result.size() > 0 && j>0 && Build.VERSION.SDK_INT >= 26){

                        vibrator.vibrate(VibrationEffect.createOneShot(1200, VibrationEffect.DEFAULT_AMPLITUDE));
                        toneGen1.startTone(ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_INTERGROUP,400);
                        startSpeechRecognition();
                        onBeginningOfSpeech();

                    }

                    else if(result != null && result.size() > 0 && k>0 && Build.VERSION.SDK_INT >= 26){

                        vibrator.vibrate(VibrationEffect.createOneShot(1200, VibrationEffect.DEFAULT_AMPLITUDE));
                        toneGen1.startTone(ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_INTERGROUP,400);
                        startSpeechRecognition();
                        onBeginningOfSpeech();

                    }

                    else {
                        builder.setMessage("Sorry "+result.get(0).toString()+" You are not in our Database");
                        builder.setTitle("Alert !");
                        builder.setPositiveButton("Retry", (DialogInterface.OnClickListener) (dialog, which) -> {
                            startSpeechRecognition();
                            onBeginningOfSpeech();
                        });
                              }
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();



                }
            }

            public void onPartialResults(Bundle bundle) {
            }

            @Override
            public void onEvent(int i, Bundle bundle) {
            }
        });

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
        speechRecognizer.startListening(intent);
    }
}
