package com.example.appointment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getName();

    private FirebaseFirestore firestore;
    private CollectionReference items;

    private RecyclerView recyclerView;
    private ArrayList<Appointment> appointmentList;
    private AppointmentAdapter appointmentAdapter;
    private int gridNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        appointmentList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, gridNumber));
        appointmentAdapter = new AppointmentAdapter(this, appointmentList);
        recyclerView.setAdapter(appointmentAdapter);

        firestore = FirebaseFirestore.getInstance();
        items = firestore.collection("appointments");
        queryData();

    }

    private void queryData() {
        appointmentList.clear();

        items.orderBy("priority", Query.Direction.DESCENDING).get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                Appointment item = document.toObject(Appointment.class);
//                item.setId(document.getId());
                appointmentList.add(item);
            }

            if (appointmentList.size() == 0) {
                initData();
                queryData();
            }

            appointmentAdapter.notifyDataSetChanged();
        });
    }

    private void initData() {
        Appointment a1 = new Appointment(
            "0", "proposed", new ArrayList<>(),
            "check-up", 7, "kek",
                new Date(), 30
        );

        Appointment a2 = new Appointment(
                "1", "fulfilled", new ArrayList<>(),
                "walk-in", 3, "keksz",
                new Date(), 60
        );

        items.add(a1);
        items.add(a2);
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

    public void onListAll(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onAddNew(View view) {
        Intent intent = new Intent(this, AddAppointmentActivity.class);
        startActivity(intent);
    }

    public void onDeleteItem(Appointment appointment) {
        DocumentReference ref = items.document(appointment.getId());

        ref.delete().addOnSuccessListener(succ -> {
            Log.d(LOG_TAG, "Delete successful, id: " + appointment.getId());
        }).addOnFailureListener(fail -> {
            Toast.makeText(this, "Delete failed.", Toast.LENGTH_LONG).show();
            Log.d(LOG_TAG, "Delete failed, id: " + appointment.getId());
        });

        queryData();
    }

    public void onEditItem(Appointment appointment) {
        Log.i(LOG_TAG, "Edit fn, id: " + appointment.getId());
    }
}