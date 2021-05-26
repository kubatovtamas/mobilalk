package com.example.appointment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class AddAppointmentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String LOG_TAG = AddAppointmentActivity.class.getName();
    private static final String PREF_KEY = AddAppointmentActivity.class.getPackage().toString();

    Spinner statusSpinner;
    EditText descriptionET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);

        descriptionET = findViewById(R.id.editTextDescription);

        statusSpinner = findViewById(R.id.spinnerStatus);
        statusSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.appointment_statuses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(adapter);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // TODO
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO
    }
}