package com.fp.solid.inyeccion_dependencias.pizzeria.problema.opcion1;




public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.probarReserva(new Reserva("Ana@gmail.com", 4));     // OK
        main.probarReserva(new Reserva("Luis@gmail.com", 7));    // Reserva grande
        main.probarReserva(new Reserva("Ana@gmail.com", 2));     // Duplicada
        main.probarReserva(new Reserva("", 3));        // Nombre inválido
        main.probarReserva(new Reserva("Carlos@gmail.com", 10)); // Demasiadas personas
    }

    private  void probarReserva(Reserva reserva) {
        ReservaService servicio = new ReservaService();
        try {
            servicio.crearReserva(reserva);

            System.out.println("Reserva creada para "
                    + reserva.getEmail()
                    + " (" + reserva.getNumeroPersonas() + " personas)"
                    + (reserva.isReservaGrande() ? " [RESERVA GRANDE]" : "")
            );

        } catch (IllegalStateException |  IllegalArgumentException e) {
            System.out.println("❌ Error al crear reserva para '"
                    + reserva.getEmail() + "': "
                    + e.getMessage());
        }
    }
}