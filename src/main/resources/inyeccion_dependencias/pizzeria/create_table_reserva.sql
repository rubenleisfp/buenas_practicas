-- Script de MySQL para crear la tabla Reserva
-- Sistema de Reservas de Pizzería

-- Crear base de datos si no existe
CREATE DATABASE IF NOT EXISTS buenas_practicas
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

-- Usar la base de datos
USE buenas_practicas;

-- Eliminar tabla si existe (para desarrollo)
DROP TABLE IF EXISTS Reserva;

-- Crear tabla Reserva
CREATE TABLE Reserva (
    email VARCHAR(255) NOT NULL,
    numero_personas INT NOT NULL,
    reserva_grande BOOLEAN NOT NULL DEFAULT FALSE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (email),
    CONSTRAINT chk_numero_personas_positivo CHECK (numero_personas > 0),
    CONSTRAINT chk_maximo_personas CHECK (numero_personas <= 8)
);

