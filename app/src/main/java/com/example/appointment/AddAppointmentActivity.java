package com.example.appointment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

import java.util.ArrayList;

public class AddAppointmentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String LOG_TAG = AddAppointmentActivity.class.getName();

    Spinner statusSpinner;
    RadioGroup radioGroup;
    SeekBar prioritySeekBar;
    SeekBar durationSeekBar;
    EditText descriptionET;
    private RecyclerView recyclerView;
    private ArrayList<Participant> participantsList;
    private ParticipantAdapter participantAdapter;
    private int gridNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);

        participantsList = new ArrayList<>();

        statusSpinner = findViewById(R.id.spinnerStatus);
        statusSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.appointment_statuses, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(arrayAdapter);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, gridNumber));
        participantAdapter = new ParticipantAdapter(this, participantsList);
        recyclerView.setAdapter(participantAdapter);

        initData();
    }

    private void initData() {
        Participant p1 = new Participant("Accepted", "Patient", "Required");
        Participant p2 = new Participant("Tentative", "Practitioner", "Required");

        participantsList.clear();
        participantsList.add(p1);
        participantsList.add(p1);
        participantsList.add(p2);
        participantsList.add(p2);
        participantAdapter.notifyDataSetChanged();
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
        String status = statusSpinner.getSelectedItem().toString();

        radioGroup = findViewById(R.id.radioGroup);
        int checkedId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = radioGroup.findViewById(checkedId);
        String appointmentType = radioButton.getText().toString();

        descriptionET = findViewById(R.id.editTextDescription);
        String description = descriptionET.getText().toString();


        Log.i(LOG_TAG, "Submit");
        Log.i(LOG_TAG, "Status: " + status);
        Log.i(LOG_TAG, "appointmentType: " + appointmentType);
        Log.i(LOG_TAG, "description: " + description);
    }

    public void onBack(View v) {
        Log.i(LOG_TAG, "Back");

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onAddParticipants(View v) {
        Log.i(LOG_TAG, "Add Participants");

        Intent intent = new Intent(this, AddParticipantsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedItem = parent.getItemAtPosition(position).toString();
        Log.i(LOG_TAG, selectedItem);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO
    }
}