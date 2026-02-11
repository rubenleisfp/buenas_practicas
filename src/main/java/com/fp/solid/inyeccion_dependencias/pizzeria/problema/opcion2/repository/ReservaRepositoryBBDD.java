package com.fp.solid.inyeccion_dependencias.pizzeria.problema.opcion2.repository;



import com.fp.solid.inyeccion_dependencias.pizzeria.problema.opcion2.model.Reserva;
import com.fp.solid.inyeccion_dependencias.pizzeria.problema.opcion2.repository.database.DriverHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservaRepositoryBBDD {

    private static final String INSERT_RESERVA = "INSERT INTO reserva (email, numero_personas, reserva_grande, fecha_creacion) VALUES (?, ?, ?, NOW())";
    private static final String EXISTS_RESERVA = "SELECT count(*) FROM reserva WHERE email = ?";


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