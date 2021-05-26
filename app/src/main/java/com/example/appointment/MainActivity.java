package com.example.appointment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getName();
    private static final String PREF_KEY = MainActivity.class.getPackage().toString();

    EditText descriptionET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        descriptionET = findViewById(R.id.editTextDescription);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LOG_TAG, "On Start");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LOG_TAG, "On Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "On Destroy");
    }

    public void onSubmit(View v) {
        Log.i(LOG_TAG, "Submit");
    }

    public void onBack(View v) {
        Log.i(LOG_TAG, "Back");
    }

    public void onAddParticipants(View v) {
        Log.i(LOG_TAG, "Add Participants");

        Intent intent = new Intent(this, AddParticipantsActivity.class);
        startActivity(intent);
    }

    public void onListAll(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onAddNew(View view) {
        Intent intent = new Intent(this, AddAppointmentActivity.class);
        startActivity(intent);
    }
}