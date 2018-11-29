package com.example.taamefl2.appquest_morse;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private MorseEncoder morseEncoder = new MorseEncoder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText textForMorsing = findViewById(R.id.addText);

        final Button textToMorse = findViewById(R.id.textToMorse);
        textToMorse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    // Encode Text to Morse
                    final List<Primitive> morseCode = morseEncoder.textToCode(textForMorsing.getText().toString());

                    // Get textinput and button out of the way:
                    ((ViewGroup)textForMorsing.getParent()).removeView(textForMorsing);
                    ((ViewGroup)textToMorse.getParent()).removeView(textToMorse);

                    // Flash screen (TODO: Check Timing, Error Handling, Commenting, Make functions and not line of doooom)
                    final Handler handler = new Handler();
                    for (int a = 0; a < morseCode.size() ;a++) {
                        final int finalA = a;
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                switch (morseCode.get(finalA).getTextRepresentation()){
                                    case "·":
                                        getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                                        break;
                                    case "−":
                                        getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                                        break;
                                    case " ":
                                        getWindow().getDecorView().setBackgroundColor(Color.BLACK);
                                        break;
                                    case "   ":
                                        getWindow().getDecorView().setBackgroundColor(Color.BLACK);
                                        break;
                                    case " / ":
                                        getWindow().getDecorView().setBackgroundColor(Color.BLACK);
                                        break;
                                    default:
                                        getWindow().getDecorView().setBackgroundColor(Color.RED);
                                        break;
                                }
                            }
                            }, morseCode.get(finalA).getSignalLengthInDits() * 500 * finalA);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                textForMorsing.setText("");
            }
        });
    }
}
