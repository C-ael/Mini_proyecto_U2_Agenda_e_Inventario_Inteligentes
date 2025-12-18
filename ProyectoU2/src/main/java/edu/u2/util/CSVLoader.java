package edu.u2.util;

import edu.u2.list.PatientLinkedList;
import edu.u2.model.Appointment;
import edu.u2.model.InventoryItem;
import edu.u2.model.Patient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CSVLoader {

    // carga citas desde un archivo csv
    public static Appointment[] loadAppointments(String fileName) {

        List<Appointment> list = new ArrayList<>();

        try {
            // obtiene el archivo desde resources
            InputStream is = CSVLoader.class.getResourceAsStream("/" + fileName);

            if (is == null) {
                System.out.println("No se encontró el archivo: " + fileName);
                return new Appointment[0];
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {

                // salta el encabezado
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] parts = line.split(";");
                if (parts.length != 3) continue;

                // crea la cita con id, apellido y fechaHora
                list.add(new Appointment(
                        parts[0].trim(),
                        parts[1].trim(),
                        "",
                        LocalDateTime.parse(parts[2].trim())
                ));
            }

            br.close();
            is.close();

        } catch (Exception e) {
            System.out.println("Error al cargar citas: " + e.getMessage());
        }

        return list.toArray(new Appointment[0]);
    }

    // carga inventario desde un archivo csv
    public static InventoryItem[] loadInventory(String fileName) {

        List<InventoryItem> list = new ArrayList<>();

        try {
            // obtiene el archivo desde resources
            InputStream is = CSVLoader.class.getResourceAsStream("/" + fileName);

            if (is == null) {
                System.out.println("No se encontró el archivo: " + fileName);
                return new InventoryItem[0];
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {

                // salta el encabezado
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                // soporta separador ; o ,
                String[] parts = line.contains(";") ? line.split(";") : line.split(",");
                if (parts.length < 3) continue;

                try {
                    String id = parts[0].trim();
                    String name = parts[1].trim();
                    int quantity = Integer.parseInt(parts[2].trim());

                    // ignora registros sin nombre
                    if (name.isEmpty()) {
                        System.out.println("Advertencia: nombre vacío en línea: " + line);
                        continue;
                    }

                    list.add(new InventoryItem(id, name, quantity));

                } catch (NumberFormatException e) {
                    System.out.println("Advertencia: error numérico en línea: " + line);
                }
            }

            br.close();
            is.close();

        } catch (Exception e) {
            System.out.println("Error al cargar inventario: " + e.getMessage());
        }

        return list.toArray(new InventoryItem[0]);
    }

    // carga pacientes y construye la lista simplemente enlazada
    public static PatientLinkedList loadPatients(String fileName) {

        PatientLinkedList list = new PatientLinkedList();

        try {
            // obtiene el archivo desde resources
            InputStream is = CSVLoader.class.getResourceAsStream("/" + fileName);

            if (is == null) {
                System.out.println("No se encontró el archivo: " + fileName);
                return list;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {

                // salta el encabezado
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                if (line.isBlank()) continue;

                String[] parts = line.split(";");
                if (parts.length != 3) continue;

                String id = parts[0].trim();
                String lastName = parts[1].trim();

                try {
                    int priority = Integer.parseInt(parts[2].trim());
                    list.add(new Patient(id, lastName, priority));
                } catch (NumberFormatException e) {
                    System.out.println("Advertencia: prioridad inválida en línea: " + line);
                }
            }

            br.close();
            is.close();

        } catch (Exception e) {
            System.out.println("Error al cargar pacientes: " + e.getMessage());
        }

        return list;
    }
}
