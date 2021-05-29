package com.example.appointment;

import com.google.common.base.Objects;

public class Participant {
    private String status;
    private String actor;
    private String necessity;

    public Participant() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equal(status, that.status) &&
                Objects.equal(actor, that.actor) &&
                Objects.equal(necessity, that.necessity);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(status, actor, necessity);
    }

    @Override
    public String toString() {
        return "Participant{" +
                "status='" + status + '\'' +
                ", actor='" + actor + '\'' +
                ", necessity='" + necessity + '\'' +
                '}';
    }

    public void setNecessity(String necessity) {
        this.necessity = necessity;
    }
}
