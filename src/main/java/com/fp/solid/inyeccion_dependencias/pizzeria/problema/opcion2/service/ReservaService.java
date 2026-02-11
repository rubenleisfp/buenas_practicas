package com.fp.solid.inyeccion_dependencias.pizzeria.problema.opcion2.service;


import com.fp.solid.inyeccion_dependencias.pizzeria.problema.opcion2.model.Reserva;
import com.fp.solid.inyeccion_dependencias.pizzeria.problema.opcion2.repository.ReservaRepositoryBBDD;
import com.fp.solid.inyeccion_dependencias.pizzeria.problema.opcion2.repository.ReservaRepositoryFichero;

public class ReservaService {

    private static final int MAX_PERSONAS = 8;
    private static final int UMBRAL_RESERVA_GRANDE = 6;

    // ❌ ACOPLAMIENTO DIRECTO
    private ReservaRepositoryFichero reservaRepository = new ReservaRepositoryFichero();
    //private ReservaRepositoryBBDD reservaRepository = new ReservaRepositoryBBDD();

    public void crearReserva(Reserva reserva) {

        validarReserva(reserva);

        if (reservaRepository.existeReserva(reserva.getEmail())) {
            throw new IllegalStateException("El cliente ya tiene una reserva");
        }

        boolean reservaGrande = reserva.getNumeroPersonas() > UMBRAL_RESERVA_GRANDE;
        if (reservaGrande) {
            reserva.marcarComoReservaGrande();
        }

        reservaRepository.guardar(reserva);
    }

    private void validarReserva(Reserva reserva) {

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
}
