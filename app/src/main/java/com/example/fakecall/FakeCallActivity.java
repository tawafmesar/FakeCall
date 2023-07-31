package com.example.fakecall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class FakeCallActivity extends AppCompatActivity {

    private TextView textViewName;
    private TextView textViewPhoneNumber;
    private ImageButton btnAnswer;
    private ImageButton btnEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake_call);

        // Get the name and phone number from the Intent extras
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String phoneNumber = intent.getStringExtra("phoneNumber");

        // Initialize the views
        textViewName = findViewById(R.id.textViewName);
        textViewPhoneNumber = findViewById(R.id.textViewPhoneNumber);
        btnAnswer = findViewById(R.id.btnAnswer);
        btnEnd = findViewById(R.id.btnEnd);

        // Set the name and phone number in the TextViews
        textViewName.setText(name);
        textViewPhoneNumber.setText( phoneNumber);


        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FakeCallActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // This flag clears other activities on top of MainActivity
                startActivity(intent);
                finish();

            }
        });

        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = intent.getStringExtra("name");
                String phoneNumber = intent.getStringExtra("phoneNumber");

                Intent intent = new Intent(FakeCallActivity.this, OngoingCallActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("phoneNumber", phoneNumber);
                startActivity(intent);
            }
        });

    }



}