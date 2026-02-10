package com.fp.solid.inyeccion_dependencias.pizzeria.solucion.repository;

import com.fp.solid.inyeccion_dependencias.pizzeria.solucion.model.Reserva;
import com.fp.solid.inyeccion_dependencias.pizzeria.solucion.repository.database.DriverHelper;

import java.sql.*;

public class ReservaRepositoryBBDD implements ReservaRepository {

    private static final String INSERT_RESERVA = "INSERT INTO reserva (email, numero_personas, reserva_grande, fecha_creacion) VALUES (?, ?, ?, NOW())";
    private static final String EXISTS_RESERVA = "SELECT count(*) FROM reserva WHERE email = ?";

    @Override
    public void guardar(Reserva reserva) {
        try (Connection connection = DriverHelper.getConnection()) {

            try (PreparedStatement ps = connection.prepareStatement(INSERT_RESERVA)) {
                ps.setString(1, reserva.getEmail());
                ps.setInt(2, reserva.getNumeroPersonas());
                ps.setBoolean(3, reserva.isReservaGrande());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error guardando reserva", e);
        }
    }

    @Override
    public boolean existeReserva(String emailCliente) {
        try (Connection connection = DriverHelper.getConnection()) {

            try (PreparedStatement ps = connection.prepareStatement(EXISTS_RESERVA)) {
                ps.setString(1, emailCliente);
                try (ResultSet resultSet = ps.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt(1) > 0;
                    }
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al contar los films.", e);
        }
    }
}