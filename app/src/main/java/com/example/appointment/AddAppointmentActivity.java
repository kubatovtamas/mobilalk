package com.example.appointment;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddAppointmentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String LOG_TAG = AddAppointmentActivity.class.getName();

    Spinner appointmentStatusSpinner;
    Spinner participantStatusSpinner;
    Spinner participantActorSpinner;
    Spinner participantRequiredSpinner;
    RadioGroup radioGroup;
    SeekBar prioritySeekBar;
    SeekBar durationSeekBar;
    EditText descriptionET;
    Button dateButton;
    private RecyclerView recyclerView;
    private ParticipantAdapter participantAdapter;
    private int gridNumber = 1;
    private DatePickerDialog datePickerDialog;

    private Appointment appointment;
    private int appointmentPriority;
    private int appointmentDuration;
    private Date appointmentDate;
    private ArrayList<Participant> participantsList;

    private FirebaseFirestore firestore;
    private CollectionReference appointmentCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);

        firestore = FirebaseFirestore.getInstance();
        appointmentCollection = firestore.collection("appointments");

        participantsList = new ArrayList<>();

        // Appointment status spinner
        appointmentStatusSpinner = findViewById(R.id.spinnerStatus);
        appointmentStatusSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> appointmentStatusArrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.appointment_statuses, android.R.layout.simple_spinner_item);
        appointmentStatusArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        appointmentStatusSpinner.setAdapter(appointmentStatusArrayAdapter);

        // Participant status spinner
        participantStatusSpinner = findViewById(R.id.spinnerParticipantStatus);
        participantStatusSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> participantStatusArrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.participant_statuses, android.R.layout.simple_spinner_item);
        participantStatusArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        participantStatusSpinner.setAdapter(participantStatusArrayAdapter);

        // Participant actor spinner
        participantActorSpinner = findViewById(R.id.spinnerParticipantActor);
        participantActorSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> participantActorArrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.participant_actors, android.R.layout.simple_spinner_item);
        participantActorArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        participantActorSpinner.setAdapter(participantActorArrayAdapter);

        // Participant required spinner
        participantRequiredSpinner = findViewById(R.id.spinnerParticipantRequired);
        participantRequiredSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> participantRequiredArrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.participant_required, android.R.layout.simple_spinner_item);
        participantRequiredArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        participantRequiredSpinner.setAdapter(participantRequiredArrayAdapter);

        // Participants recycler view fill
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, gridNumber));
        participantAdapter = new ParticipantAdapter(this, participantsList);
        recyclerView.setAdapter(participantAdapter);

        prioritySeekBar = findViewById(R.id.seekBarPriority);
        prioritySeekBar.setProgress(0);
        prioritySeekBar.incrementProgressBy(1);
        prioritySeekBar.setMax(10);
        prioritySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                appointmentPriority = progress;
                Toast.makeText(getApplicationContext(),"Priority: " + progress, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        durationSeekBar = findViewById(R.id.seekBarDuration);
        durationSeekBar.setProgress(0);
        durationSeekBar.incrementProgressBy(30);
        durationSeekBar.setMax(240);
        durationSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                appointmentDuration = progress;
                Toast.makeText(getApplicationContext(),"Duration (minutes): " + progress, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        dateButton = findViewById(R.id.dateButton);
        dateButton.setText("Select Appointment Date");
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = year + "-" + (month + 1) + "-" + dayOfMonth;
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onSubmit(View v) {
        String status = appointmentStatusSpinner.getSelectedItem().toString();

        radioGroup = findViewById(R.id.radioGroup);
        int checkedId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = radioGroup.findViewById(checkedId);
        String appointmentType = radioButton.getText().toString();

        descriptionET = findViewById(R.id.editTextDescription);
        String description = descriptionET.getText().toString();

        String id = appointmentCollection.document().getId();

        String dateString = dateButton.getText().toString();
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (Exception e) {
            date = new Date();
        }

        appointment = new Appointment(id, status, this.participantsList, appointmentType,
                this.appointmentPriority, description, date, this.appointmentDuration);

        appointmentCollection.document(id).set(appointment);

        onBack(new View(this));
//        Log.i(LOG_TAG, "Submit, NEW APPOINTMENT: \n" + appointment);
    }

    public void onBack(View v) {
        Log.i(LOG_TAG, "Back");

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onAddParticipant(View v) {
        Log.i(LOG_TAG, "Add Participants");
        String participantStatus = participantStatusSpinner.getSelectedItem().toString();
        String participantActor = participantActorSpinner.getSelectedItem().toString();
        String participantRequired = participantRequiredSpinner.getSelectedItem().toString();

        Participant newParticipant = new Participant(participantStatus, participantActor, participantRequired);

        participantsList.add(newParticipant);

        participantAdapter.notifyDataSetChanged();
    }

    public void onDeleteParticipant(Participant participant) {
        Log.i(LOG_TAG, "Delete participant: " + participant);
        participantsList.remove(participant);
        participantAdapter.notifyDataSetChanged();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedItem = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }
}