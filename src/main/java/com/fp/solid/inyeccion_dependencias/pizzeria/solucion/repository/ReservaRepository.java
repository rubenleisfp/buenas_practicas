package com.fp.solid.inyeccion_dependencias.pizzeria.solucion.repository;

import com.fp.solid.inyeccion_dependencias.pizzeria.solucion.model.Reserva;

public interface ReservaRepository {
    void guardar(Reserva reserva);
    boolean existeReservaPara(String nombreCliente);
}