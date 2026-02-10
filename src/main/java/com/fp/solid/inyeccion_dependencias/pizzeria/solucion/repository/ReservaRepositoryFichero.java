package com.fp.solid.inyeccion_dependencias.pizzeria.solucion.repository;

import com.fp.solid.inyeccion_dependencias.pizzeria.solucion.model.Reserva;

import java.io.*;

public class ReservaRepositoryFichero implements ReservaRepository {

    private File fichero = new File("reservas_solucion.txt");

    @Override
    public void guardar(Reserva reserva) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichero, true))) {
            bw.write(reserva.getEmail() + ";" + reserva.getNumeroPersonas());
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Error guardando reserva", e);
        }
    }

    @Override
    public boolean existeReservaPara(String nombreCliente) {
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