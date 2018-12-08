package com.example.taamefl2.appquest_morse;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Context context = this;
    private Handler handler = new Handler();
    private MorseEncoder morseEncoder = new MorseEncoder();
    private LogbookHandling logbook = new LogbookHandling();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setBackgroundColor(Color.BLACK);
        final EditText textForMorsing = findViewById(R.id.addText);
        final EditText textForLogging = findViewById(R.id.logText);
        final EditText textForLookbook = findViewById(R.id.logBookText);
        final Button textToMorse = findViewById(R.id.textToMorse);
        final Button textToLogbook = findViewById(R.id.textToLogbook);
        textForMorsing.setTextColor(Color.WHITE);
        textForLookbook.setTextColor(Color.WHITE);
        textForLookbook.setVisibility(View.INVISIBLE);
        textToLogbook.setVisibility(View.INVISIBLE);

        textToMorse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    // Encode Text to Morse
                    final List<Primitive> morseCode = morseEncoder.textToCode(textForMorsing.getText().toString().toUpperCase());

                    textForMorsing.setVisibility(View.INVISIBLE);
                    textToMorse.setVisibility(View.INVISIBLE);
                    textForLookbook.setVisibility(View.INVISIBLE);
                    textToLogbook.setVisibility(View.INVISIBLE);
                    textForLogging.setTextColor(Color.TRANSPARENT);

                    String log = "";
                    textForLogging.setText(log);
                    Integer backToBlack = 0; // :D hehe
                    Long startMillis = SystemClock.uptimeMillis() + 3000; // + 3000 so screen is black before sending message
                    log = startMillis.toString() + " Starting app!" + "\n" + "----------" + "\n";
                    Integer delay = 0;
                    for (final Primitive primitive : morseCode) {
                        log = log + (startMillis+delay) + " Primitive: " + primitive.getTextRepresentation() + "\n";
                        log = log + primitive.getSignalLengthInDits() + "ms displayed! " + delay.toString() + "ms delay!" + "\n";
                        log = log + "----------" + "\n";
                        handler.postAtTime(new Runnable() {
                            public void run() {
                                switch (primitive.getTextRepresentation()) {
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
                        }, startMillis + delay);
                        delay = delay + primitive.getSignalLengthInDits();
                        backToBlack++;
                    }

                    // set screen to black when code morsed
                    if (backToBlack == morseCode.size()) {
                        handler.postAtTime(new Runnable() {
                            public void run() {
                                getWindow().getDecorView().setBackgroundColor(Color.BLACK);
                            }
                        }, startMillis + delay);
                    }

                    // show log on screen
                    if (backToBlack == morseCode.size()) {
                        handler.postAtTime(new Runnable() {
                            public void run() {
                                textForLogging.setTextColor(Color.RED);
                                textForMorsing.setVisibility(View.VISIBLE);
                                textToMorse.setVisibility(View.VISIBLE);
                                textForLookbook.setVisibility(View.VISIBLE);
                                textToLogbook.setVisibility(View.VISIBLE);
                            }
                        }, startMillis + delay + 5000); // + 5000 so screen is black after sending message
                    }
                    textForLogging.setText(log);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        textToLogbook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if (logbook.checkIfLogbookInstalled(context)) {
                        logbook.passDataToLogbook(context, textForLookbook.getText().toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
    }
}
