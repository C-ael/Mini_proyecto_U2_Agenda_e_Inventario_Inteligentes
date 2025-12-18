package edu.u2.model;

import java.time.LocalDateTime;

public class Appointment implements Comparable<Appointment> {

    private String id;
    private String lastName;
    private LocalDateTime dateTime;

    // crea una cita con identificador, apellido y fechaHora
    public Appointment(String id, String lastName, String s, LocalDateTime dateTime) {
        this.id = id;
        this.lastName = lastName;
        this.dateTime = dateTime;
    }

    // retorna la fecha y hora de la cita
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    // compara citas usando la fecha y hora
    @Override
    public int compareTo(Appointment other) {
        return this.dateTime.compareTo(other.dateTime);
    }

    // representación simple para impresión en consola
    @Override
    public String toString() {
        return id + " | " + lastName + " | " + dateTime;
    }
}
