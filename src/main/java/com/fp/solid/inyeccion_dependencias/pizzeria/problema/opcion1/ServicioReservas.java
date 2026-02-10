package com.fp.solid.inyeccion_dependencias.pizzeria.problema.opcion1;

import java.io.*;

public class ServicioReservas {

    private static final int MAX_PERSONAS = 8;
    private static final int UMBRAL_RESERVA_GRANDE = 6;
    private static final String FICHERO = "reservas_opcion1.txt";

    public void crearReserva(Reserva reserva) {

        validarReserva(reserva);

        // ❌ LECTURA DE FICHERO EN EL SERVICIO
        if (existeReserva(reserva.getEmail())) {
            throw new IllegalStateException("El cliente ya tiene una reserva");
        }

        boolean reservaGrande = reserva.getNumeroPersonas() > UMBRAL_RESERVA_GRANDE;

        // ❌ ESCRITURA DE FICHERO EN EL SERVICIO
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHERO, true))) {
            bw.write(reserva.getEmail() + ";" + reserva.getNumeroPersonas() + ";" + reservaGrande);
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Error guardando reserva", e);
        }
    }

    private void validarReserva(Reserva reserva) {

        // ❌ VALIDACIÓN + LÓGICA MEZCLADA
        if (reserva.getEmail() == null || reserva.getEmail().isBlank()) {
            throw new IllegalArgumentException("Nombre obligatorio");
        }

        if (reserva.getNumeroPersonas() <= 0) {
            throw new IllegalArgumentException("Número de personas inválido");
        }

        if (reserva.getNumeroPersonas() > MAX_PERSONAS) {
            throw new IllegalArgumentException("No se permiten más de " + MAX_PERSONAS);
        }
    }

    private boolean existeReserva(String emailCliente) {

        File fichero = new File(FICHERO);
        if (!fichero.exists()) return false;

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(emailCliente + ";")) {
                    return true;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error leyendo reservas", e);
        }
        return false;
    }
}
