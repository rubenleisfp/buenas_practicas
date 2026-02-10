package com.fp.solid.inyeccion_dependencias.pizzeria.problema.opcion2.service;

import com.fp.solid.inyeccion_dependencias.pizzeria.problema.opcion2.repository.ReservaDaoFichero;

public class ServicioReservas {

    private static final int MAX_PERSONAS = 8;
    private static final int UMBRAL_RESERVA_GRANDE = 6;

    // ❌ ACOPLAMIENTO DIRECTO
    private ReservaDaoFichero reservaDao = new ReservaDaoFichero();

    public void crearReserva(String nombreCliente, int numeroPersonas) {

        validarReserva(nombreCliente, numeroPersonas);

        if (reservaDao.existeReserva(nombreCliente)) {
            throw new IllegalStateException("El cliente ya tiene una reserva");
        }

        boolean reservaGrande = numeroPersonas > UMBRAL_RESERVA_GRANDE;

        reservaDao.guardar(nombreCliente, numeroPersonas, reservaGrande);
    }

    private void validarReserva(String nombreCliente, int numeroPersonas) {

        if (nombreCliente == null || nombreCliente.isBlank()) {
            throw new IllegalArgumentException("Nombre obligatorio");
        }

        if (numeroPersonas <= 0) {
            throw new IllegalArgumentException("Número de personas inválido");
        }

        if (numeroPersonas > MAX_PERSONAS) {
            throw new IllegalArgumentException("No se permiten más de " + MAX_PERSONAS);
        }
    }
}
