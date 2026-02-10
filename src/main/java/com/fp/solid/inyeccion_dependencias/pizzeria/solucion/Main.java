package com.fp.solid.inyeccion_dependencias.pizzeria.solucion;

import com.fp.solid.inyeccion_dependencias.pizzeria.solucion.model.Reserva;
import com.fp.solid.inyeccion_dependencias.pizzeria.solucion.repository.ReservaRepository;
import com.fp.solid.inyeccion_dependencias.pizzeria.solucion.repository.ReservaRepositoryFichero;
import com.fp.solid.inyeccion_dependencias.pizzeria.solucion.service.ReservaService;

public class Main {

    ReservaService reservaService;

    public static void main(String[] args) {
        Main main = new Main();
        // Unico punto de la app que deberíamos tocar para cambiar donde obtenemos o persistimos la informacion,
        // es decir, para cambiar la implementación de mi repository
        main.cfgInyeccion();

        // Casos de prueba
        main.probarReserva(new Reserva("Ana@gmail.com", 4));     // OK
        main.probarReserva(new Reserva("Luis@gmail.com", 7));    // Reserva grande
        main.probarReserva(new Reserva("Ana@gmail.com", 2));     // Duplicada
        main.probarReserva(new Reserva("", 3));        // Nombre inválido
        main.probarReserva(new Reserva("Carlos@gmail.com", 10)); // Demasiadas personas
    }

    public  void cfgInyeccion() {
        // Elegimos la implementación concreta (inyección manual)
        ReservaRepository reservaRepository = new ReservaRepositoryFichero();
        //ReservaRepository reservaRepository = new ReservaRepositoryBBDD();
        // Inyectamos el repositorio en el servicio
        this.reservaService = new ReservaService(reservaRepository);
    }

    private  void probarReserva(Reserva reserva) {
        try {
            reservaService.crearReserva(reserva);

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
