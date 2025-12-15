package edu.u2.model;

import java.time.LocalDateTime;

public class Appointment implements Comparable<Appointment> {

    private String id;
    private String lastName;
    private LocalDateTime dateTime;

    public Appointment(String id, String lastName, String s, LocalDateTime dateTime) {
        this.id = id;
        this.lastName = lastName;
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public int compareTo(Appointment other) {
        return this.dateTime.compareTo(other.dateTime);
    }

    @Override
    public String toString() {
        return id + " | " + lastName + " | " + dateTime;
    }
}
