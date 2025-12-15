package edu.u2.model;

public class Patient {

    private String id;
    private String lastName;
    private int priority;

    // constructor del paciente
    public Patient(String id, String lastName, int priority) {
        this.id = id;
        this.lastName = lastName;
        this.priority = priority;
    }


    // obtiene el id
    public String getId() {
        return id;
    }

    // obtiene el apellido
    public String getLastName() {
        return lastName;
    }

    // obtiene la prioridad
    public int getPriority() {
        return priority;
    }

    // representación para consola
    @Override
    public String toString() {
        return "Paciente [" +
                "id=" + id +
                ", apellido=" + lastName +
                ", prioridad=" + priority +
                "]";
    }
}
