package com.example.appointment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Appointment {
    private String id;
    private String status;
    private ArrayList<Participant> participants;
    private String appointmentType;
    private int priority;
    private String description;
    private Date start;
    private int duration;

    public Appointment() {
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", participants=" + participants +
                ", appointmentType='" + appointmentType + '\'' +
                ", priority=" + priority +
                ", description='" + description + '\'' +
                ", start=" + start +
                ", duration=" + duration +
                '}';
    }

    public Appointment(String id, String status, ArrayList<Participant> participants, String appointmentType, int priority, String description, Date start, int duration) {
        this.id = id;
        this.status = status;
        this.participants = participants;
        this.appointmentType = appointmentType;
        this.priority = priority;
        this.description = description;
        this.start = start;
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<Participant> participants) {
        this.participants = participants;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
