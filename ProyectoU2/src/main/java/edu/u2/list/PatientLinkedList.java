package edu.u2.list;

import edu.u2.model.Node;
import edu.u2.model.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientLinkedList {

    private int size = 0;
    private Node head;

    // crea la lista vacía
    public PatientLinkedList() {
        this.head = null;
    }

    // inserta al final de la lista
    public void add(Patient patient) {
        Node newNode = new Node(patient);

        if (head == null) {
            head = newNode;
            return;
        }

        Node current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(newNode);
        size++;
    }

    // búsqueda secuencial: primera coincidencia por apellido
    public Patient searchFirstByLastName(String lastName) {
        Node current = head;

        while (current != null) {
            if (current.getData().getLastName().equalsIgnoreCase(lastName)) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }

    // búsqueda secuencial: última coincidencia por apellido
    public Patient searchLastByLastName(String lastName) {
        Node current = head;
        Patient lastMatch = null;

        while (current != null) {
            if (current.getData().getLastName().equalsIgnoreCase(lastName)) {
                lastMatch = current.getData();
            }
            current = current.getNext();
        }
        return lastMatch;
    }

    // búsqueda secuencial: todos los pacientes con cierta prioridad
    public List<Patient> findAllByPriority(int priority) {
        List<Patient> result = new ArrayList<>();
        Node current = head;

        while (current != null) {
            if (current.getData().getPriority() == priority) {
                result.add(current.getData());
            }
            current = current.getNext();
        }
        return result;
    }

    // imprime la lista completa
    public void printList() {
        Node current = head;

        while (current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }
    }

    public int size() {
        return this.size;
    }
}

