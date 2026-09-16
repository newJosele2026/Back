CREATE TABLE usuario (
    id                SERIAL PRIMARY KEY,
    nombre            VARCHAR(255) NOT NULL,
    fecha_de_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);