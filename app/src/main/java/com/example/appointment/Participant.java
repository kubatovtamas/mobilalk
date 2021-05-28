package com.example.appointment;

public class Participant {
    private String status;
    private String actor;
    private String necessity;

    public Participant(String status, String actor, String necessity) {
        this.status = status;
        this.actor = actor;
        this.necessity = necessity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getNecessity() {
        return necessity;
    }

    public void setNecessity(String necessity) {
        this.necessity = necessity;
    }
}
