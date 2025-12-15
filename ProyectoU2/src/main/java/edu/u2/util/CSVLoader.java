package edu.u2.util;

import edu.u2.list.PatientLinkedList;
import edu.u2.model.Appointment;
import edu.u2.model.InventoryItem;
import edu.u2.model.Patient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CSVLoader {

    private static final DateTimeFormatter DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // carga citas desde csv
    public static Appointment[] loadAppointments(String fileName) {

        List<Appointment> list = new ArrayList<>();

        try {
            InputStream is = CSVLoader.class.getResourceAsStream("/" + fileName);

            if (is == null) {
                System.out.println("No se encontró el archivo: " + fileName);
                return new Appointment[0];
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {

                // salta encabezado
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] parts = line.split(";");

                if (parts.length != 3) {
                    continue;
                }

                list.add(new Appointment(
                        parts[0].trim(),                      // id
                        parts[1].trim(),                      // apellido
                        "", LocalDateTime.parse(parts[2].trim())  // fechaHora ISO
                ));
            }

        } catch (Exception e) {
            System.out.println("Error al cargar citas: " + e.getMessage());
        }

        return list.toArray(new Appointment[0]);
    }

    // carga inventario desde csv

    public static InventoryItem[] loadInventory(String fileName) {

        List<InventoryItem> list = new ArrayList<>();

        try {
            InputStream is = CSVLoader.class.getResourceAsStream("/" + fileName);

            if (is == null) {
                System.out.println("No se encontró el archivo: " + fileName);
                return new InventoryItem[0];
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {

                // salta encabezado
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] parts = line.contains(";")
                        ? line.split(";")
                        : line.split(",");
                if (parts.length < 3) {
                    // Saltar líneas que no tienen suficientes campos
                    continue;
                }

                // --- CÓDIGO CORREGIDO: Manejo de NumberFormatException por línea ---
                try {
                    String  id = parts[0].trim();
                    String name = parts[1].trim();
                    int quantity = Integer.parseInt(parts[2].trim());

                    // Asegurar que el nombre no esté vacío, si es un requisito
                    if (name.isEmpty()) {
                        System.out.println("Advertencia: Nombre vacío en línea: " + line);
                        continue;
                    }

                    list.add(new InventoryItem(id, name, quantity));

                } catch (NumberFormatException e) {
                    // Capturar y reportar el error de conversión para esa línea específica
                    System.out.println("Advertencia: Error de formato numérico en la línea: " + line);
                }
                // ------------------------------------------------------------------
            }
            // Agregar cierre de recursos
            br.close();
        } catch (Exception e) {
            System.out.println("Error general al cargar inventario: " + e.getMessage());
            // Se sugiere imprimir la traza completa para depuración avanzada: e.printStackTrace();
        }
        return list.toArray(new InventoryItem[0]);
    }


    public static PatientLinkedList loadPatients(String fileName) {

        PatientLinkedList list = new PatientLinkedList();

        try {
            // Opción 1 (Consistente con loadAppointments/loadInventory):
            // Busca el archivo en la raíz del classpath (por el "/")
            InputStream is = CSVLoader.class.getResourceAsStream("/" + fileName);

            if (is == null) {
                System.out.println("No se encontró el archivo: " + fileName);
                return list; // Retorna la lista vacía
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            boolean firstLine = true; // Flag para saltar el encabezado

            while ((line = br.readLine()) != null) {

                // === AÑADIDO: Lógica para saltar el encabezado ===
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                // ===============================================

                if (line.isBlank()) continue;

                String[] parts = line.split(";");
                if (parts.length != 3) continue;

               String id = parts[0].trim();
                String lastName = parts[1].trim();
                // Control de errores de parseo
                int priority = 0;
                try {
                    priority = Integer.parseInt(parts[2].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Advertencia: Prioridad no válida en línea: " + line);
                    continue; // Salta esta línea si la prioridad no es un número
                }

                list.add(new Patient(id, lastName, priority));
            }

            // Cierra el BufferedReader/InputStream
            br.close();
            is.close();

        } catch (Exception e) {
            System.out.println("Error al cargar pacientes: " + e.getMessage());
        }

        return list;
    }


}
