package com.example.taamefl2.appquest_morse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;

import java.util.List;

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
                    morseEncoder.textToCode(textForMorsing.getText().toString());
                    //TODO: add logic to send morse to display and do magic stuff of dooooom
                } catch (Exception e) {
                    e.printStackTrace();
                }
                textForMorsing.setText("");
            }
        });
    }
}
