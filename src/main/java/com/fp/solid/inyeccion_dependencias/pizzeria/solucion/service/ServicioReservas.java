package com.fp.solid.inyeccion_dependencias.pizzeria.solucion.service;

import com.fp.solid.inyeccion_dependencias.pizzeria.solucion.model.Reserva;
import com.fp.solid.inyeccion_dependencias.pizzeria.solucion.repository.ReservaRepository;

public class ServicioReservas {

    private static final int MAX_PERSONAS = 8;
    private static final int UMBRAL_RESERVA_GRANDE = 6;

    private final ReservaRepository reservaRepository;

    public ServicioReservas(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public void crearReserva(Reserva reserva) {

        validarReserva(reserva);

        if (reservaRepository.existeReservaPara(reserva.getEmail())) {
            throw new IllegalStateException("El cliente ya tiene una reserva");
        }

        if (reserva.getNumeroPersonas() > UMBRAL_RESERVA_GRANDE) {
            reserva.marcarComoReservaGrande();
        }

        reservaRepository.guardar(reserva);
    }

    private void validarReserva(Reserva reserva) {

        if (reserva.getEmail() == null || reserva.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email de cliente obligatorio");
        }

        if (reserva.getNumeroPersonas() <= 0) {
            throw new IllegalArgumentException("Número de personas inválido");
        }

        if (reserva.getNumeroPersonas() > MAX_PERSONAS) {
            throw new IllegalArgumentException("No se permiten reservas de más de " + MAX_PERSONAS + " personas");
        }
    }
}
