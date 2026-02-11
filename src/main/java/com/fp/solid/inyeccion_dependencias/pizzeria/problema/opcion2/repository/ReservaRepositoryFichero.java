package com.fp.solid.inyeccion_dependencias.pizzeria.problema.opcion2.repository;

import com.fp.solid.inyeccion_dependencias.pizzeria.problema.opcion2.model.Reserva;

import java.io.*;

public class ReservaRepositoryFichero {

    private static final String FICHERO = "reservas_opcion2.txt";

    public void guardar(Reserva reserva) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHERO, true))) {
            bw.write(reserva.getEmail() + ";" + reserva.getNumeroPersonas() + ";" + reserva.isReservaGrande());
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Error guardando reserva", e);
        }
    }

    public boolean existeReserva(String emailCliente) {

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
