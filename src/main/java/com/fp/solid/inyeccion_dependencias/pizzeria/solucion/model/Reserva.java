package com.fp.solid.inyeccion_dependencias.pizzeria.solucion.model;

public class Reserva {

    private String email;
    private int numeroPersonas;
    private boolean reservaGrande;

    public Reserva(String email, int numeroPersonas) {
        this.email = email;
        this.numeroPersonas = numeroPersonas;
        this.reservaGrande = false;
    }

    public String getEmail() {
        return email;
    }

    public int getNumeroPersonas() {
        return numeroPersonas;
    }

    public boolean isReservaGrande() {
        return reservaGrande;
    }

    public void marcarComoReservaGrande() {
        this.reservaGrande = true;
    }
}
