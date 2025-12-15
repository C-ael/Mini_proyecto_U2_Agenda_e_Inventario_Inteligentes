package edu.u2.model;

public class Node {

    private Patient data; // guarda el paciente
    private Node next; // apunta al siguiente nodo

    // crea un nodo con un paciente
    public Node(Patient data) {
        this.data = data;
        this.next = null;
    }

    // obtiene el paciente
    public Patient getData() {
        return data;
    }

    // obtiene el siguiente nodo
    public Node getNext() {
        return next;
    }

    // establece el siguiente nodo
    public void setNext(Node next) {
        this.next = next;
    }
}
