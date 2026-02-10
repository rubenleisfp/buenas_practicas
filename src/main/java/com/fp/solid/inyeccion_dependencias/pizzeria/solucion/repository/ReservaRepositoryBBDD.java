package com.fp.solid.inyeccion_dependencias.pizzeria.solucion.repository;

import com.fp.solid.inyeccion_dependencias.pizzeria.solucion.model.Reserva;

public class ReservaRepositoryBBDD implements ReservaRepository {

    @Override
    public void guardar(Reserva reserva) {
        System.out.println("INSERT INTO reservas ...");
    }

    @Override
    public boolean existeReservaPara(String nombreCliente) {
        System.out.println("SELECT * FROM reservas WHERE cliente = ?");
        return false;
    }
}