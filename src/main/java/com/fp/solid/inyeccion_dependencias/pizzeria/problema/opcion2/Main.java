package com.fp.solid.inyeccion_dependencias.pizzeria.problema.opcion2;

import com.fp.solid.inyeccion_dependencias.pizzeria.problema.opcion2.model.Reserva;
import com.fp.solid.inyeccion_dependencias.pizzeria.problema.opcion2.service.ServicioReservas;

public class Main {

    ServicioReservas servicio = new ServicioReservas();

    public static void main(String[] args) {
        Main main = new Main();
        main.probarReserva(new Reserva("Ana@gmail.com", 4));     // OK
        main.probarReserva(new Reserva( "Luis@gmail.com", 7));    // Reserva grande
        main.probarReserva(new Reserva("Ana@gmail.com", 2));     // Duplicada
        main.probarReserva(new Reserva( "", 3));        // Nombre inválido
        main.probarReserva(new Reserva("Carlos@gmail.com", 10)); // Demasiadas personas
    }

    private void probarReserva(Reserva reserva) {
        try {
            servicio.crearReserva(reserva);
            System.out.println("✔ Reserva creada para "
                    + reserva.getEmail()
                    + " (" + reserva.getEmail() + " personas)");
        } catch (Exception e) {
            System.out.println("❌ Error para '"
                    + reserva.getEmail() + "': "
                    + e.getMessage());
        }
    }
}
