package com.fp.solid.inyeccion_dependencias.pizzeria.problema.opcion2;

import com.fp.solid.inyeccion_dependencias.pizzeria.problema.opcion2.service.ServicioReservas;

public class Main {

    ServicioReservas servicio = new ServicioReservas();

    public static void main(String[] args) {
        Main main = new Main();
        ServicioReservas servicio = new ServicioReservas();

        main.probarReserva(servicio, "Ana@gmail.com", 4);     // OK
        main.probarReserva(servicio, "Luis@gmail.com", 7);    // Reserva grande
        main.probarReserva(servicio, "Ana@gmail.com", 2);     // Duplicada
        main.probarReserva(servicio, "", 3);        // Nombre inválido
        main.probarReserva(servicio, "Carlos@gmail.com", 10); // Demasiadas personas
    }

    private void probarReserva(ServicioReservas servicio,
                                      String nombreCliente,
                                      int numeroPersonas) {
        try {
            servicio.crearReserva(nombreCliente, numeroPersonas);
            System.out.println("✔ Reserva creada para "
                    + nombreCliente
                    + " (" + numeroPersonas + " personas)");
        } catch (Exception e) {
            System.out.println("❌ Error para '"
                    + nombreCliente + "': "
                    + e.getMessage());
        }
    }
}
