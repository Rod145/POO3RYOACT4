CREATE DATABASE SistemaTarjetas;
GO

USE SistemaTarjetas;
GO

CREATE TABLE Usuario (
    id INT IDENTITY(1,1) PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    edad INT NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    activo BIT DEFAULT 1 -- 1 para activo, 0 para inactivo (baja lógica)
);

CREATE TABLE Tarjeta (
    id INT IDENTITY(1,1) PRIMARY KEY,
    usuario_id INT FOREIGN KEY REFERENCES Usuario(id),
    clave VARCHAR(50) NOT NULL,
    numero VARCHAR(20) UNIQUE NOT NULL,
    fecha_exp DATE NOT NULL,
    saldo DECIMAL(10,2) DEFAULT 0.00,
    tipo VARCHAR(20) NOT NULL, -- Ej: 'Débito' o 'Crédito'
    limite_credito DECIMAL(10,2) NULL, -- Solo aplica si es de crédito
    activo BIT DEFAULT 1
);


SELECT @@SERVERNAME

CREATE LOGIN java
WITH PASSWORD = '12345'

USE SistemaTarjetas

CREATE USER java FOR LOGIN java
ALTER ROLE db_owner ADD MEMBER java