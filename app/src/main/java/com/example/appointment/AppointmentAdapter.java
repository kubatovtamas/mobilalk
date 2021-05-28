package com.example.appointment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {
    private static final String LOG_TAG = AppointmentAdapter.class.getName();

    private ArrayList<Appointment> appointments;
    private Context context;

    public AppointmentAdapter(Context context, ArrayList<Appointment> appointmentsData) {
        this.appointments = appointmentsData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.appointment_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Appointment currentItem = appointments.get(position);

        holder.bindTo(currentItem);
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView statusText;
        private TextView typeText;
        private TextView priorityText;
        private TextView startText;
        private TextView durationText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            statusText = itemView.findViewById(R.id.itemStatus);
            typeText = itemView.findViewById(R.id.itemType);
            priorityText = itemView.findViewById(R.id.itemPriority);
            startText = itemView.findViewById(R.id.itemStart);
            durationText = itemView.findViewById(R.id.itemDuration);
        }

        public void bindTo(Appointment currentItem) {
            statusText.setText(currentItem.getStatus());
            typeText.setText(currentItem.getAppointmentType());
            priorityText.setText(String.valueOf(currentItem.getPriority()));
            startText.setText(currentItem.getStart().toString());
            durationText.setText(String.valueOf(currentItem.getDuration()));

            itemView.findViewById(R.id.delete_appointment).setOnClickListener(
                    view -> ((MainActivity) context).onDeleteItem(currentItem)
            );

            itemView.findViewById(R.id.edit_appointment).setOnClickListener(
                    view -> ((MainActivity) context).onEditItem(currentItem)
            );
        }
    }
}
