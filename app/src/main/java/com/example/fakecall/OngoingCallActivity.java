package com.example.fakecall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class OngoingCallActivity extends AppCompatActivity {
    private TextView textViewName;
    private TextView textViewPhoneNumber;
    private TextView textViewTimer;
    private ImageButton btnEndCall;
    private CountDownTimer countDownTimer;
    private long timeElapsed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ongoing_call);


        // Get the name and phone number from the Intent extras
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String phoneNumber = intent.getStringExtra("phoneNumber");

        // Initialize the views
        textViewName = findViewById(R.id.textViewName);
        textViewPhoneNumber = findViewById(R.id.textViewPhoneNumber);

        btnEndCall = findViewById(R.id.btnEnd);

        // Set the name and phone number in the TextViews
        textViewName.setText(name);
        textViewPhoneNumber.setText( phoneNumber);

        textViewTimer = findViewById(R.id.textViewTimer);


        // Set up the CountDownTimer
        countDownTimer = new CountDownTimer(Long.MAX_VALUE, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeElapsed = (Long.MAX_VALUE - millisUntilFinished) / 1000;
                updateTimerDisplay();
            }

            @Override
            public void onFinish() {
                // Timer finished (not implemented in this example)
            }
        };
        countDownTimer.start();

        btnEndCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDestroy();
                Intent intent = new Intent(OngoingCallActivity.this, MainActivity.class);

                startActivity(intent);
            }
        });
    }
    // Update the timer display in HH:mm:ss format
    private void updateTimerDisplay() {
        int hours = (int) (timeElapsed / 3600);
        int minutes = (int) ((timeElapsed % 3600) / 60);
        int seconds = (int) (timeElapsed % 60);
        String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        textViewTimer.setText(timeString);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Make sure to cancel the CountDownTimer to avoid memory leaks
        countDownTimer.cancel();
    }

}