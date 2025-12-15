package edu.u2.sorting;

import java.time.LocalDateTime;

public class Registro {

    public String id;
    public String apellido;
    public LocalDateTime fechaHora;
    public int prioridad;
    public int stock;

    public Registro(String id, String apellido, LocalDateTime fechaHora, int prioridad, int stock) {
        this.id = id;
        this.apellido = apellido;
        this.fechaHora = fechaHora;
        this.prioridad = prioridad;
        this.stock = stock;
    }

    public String toStringCitas() {
        return String.format(
                "%-10s | %-12s | %-20s",
                id != null ? id : "",
                apellido != null ? apellido : "",
                fechaHora != null ? fechaHora.toString() : ""
        );
    }

    public String toStringPacientes() {
        return String.format(
                "%-10s | %-12s | %-10s",
                id != null ? id : "",
                apellido != null ? apellido : "",
                prioridad != 0 ? prioridad : ""
        );
    }

    public String toStringInventario() {
        return String.format(
                "%-10s | %-20s | %-8s",
                id != null ? id : "",
                apellido != null ? apellido : "",
                stock != 0 ? stock : ""
        );
    }
}

