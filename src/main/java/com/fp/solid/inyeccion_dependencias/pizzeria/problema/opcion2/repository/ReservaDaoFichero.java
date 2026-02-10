package com.fp.solid.inyeccion_dependencias.pizzeria.problema.opcion2.repository;

import java.io.*;

public class ReservaDaoFichero {

    private static final String FICHERO = "reservas_opcion2.txt";

    public void guardar(String emailCliente, int numeroPersonas, boolean reservaGrande) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHERO, true))) {
            bw.write(emailCliente + ";" + numeroPersonas + ";" + reservaGrande);
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Error guardando reserva", e);
        }
    }

    public boolean existeReserva(String nombreCliente) {

        File fichero = new File(FICHERO);
        if (!fichero.exists()) return false;

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(nombreCliente + ";")) {
                    return true;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error leyendo reservas", e);
        }
        return false;
    }
}
