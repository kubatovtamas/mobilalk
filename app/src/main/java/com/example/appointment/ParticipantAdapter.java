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

public class ParticipantAdapter extends RecyclerView.Adapter<ParticipantAdapter.ViewHolder> {
    private static final String LOG_TAG = ParticipantAdapter.class.getName();

    private ArrayList<Participant> participants;
    private Context context;

    public ParticipantAdapter(Context context, ArrayList<Participant> participantsData) {
        this.participants = participantsData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.participant_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ParticipantAdapter.ViewHolder holder, int position) {
        Participant currentItem = participants.get(position);

        holder.bindTo(currentItem);
    }

    @Override
    public int getItemCount() {
        return participants.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView statusText;
        private TextView actorText;
        private TextView necessityText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            statusText = itemView.findViewById(R.id.itemStatus);
            actorText = itemView.findViewById(R.id.itemType);
            necessityText = itemView.findViewById(R.id.itemPriority);
        }

        public void bindTo(Participant currentItem) {
            statusText.setText(currentItem.getStatus());
            actorText.setText(currentItem.getActor());
            necessityText.setText(currentItem.getNecessity());

            itemView.findViewById(R.id.delete_participant).setOnClickListener(
                    view -> ((AddAppointmentActivity) context).onDeleteParticipant(currentItem)
            );
        }
    }
}


