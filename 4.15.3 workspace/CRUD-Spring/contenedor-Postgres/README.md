# Contenedor PostgreSQL 17

Pasos para levantar la base de datos que usa el CRUD-Spring.

## Paso 1: Levantar el contenedor

Desde esta carpeta:

```
docker compose up -d
```

## Paso 2: Crear la tabla usuario

```
docker exec -it postgres17 psql -U postgres -d test -f /dev/stdin < usuario.sql
```

O manualmente:

```
docker exec -it postgres17 psql -U postgres -d test -c "CREATE TABLE usuario (id SERIAL PRIMARY KEY, nombre VARCHAR(255) NOT NULL, fecha_de_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP);"
```

## Datos de conexion (Spring)

Estan en `src/main/resources/jdbc.properties`:

- URL JDBC: `jdbc:postgresql://localhost:5432/test`
- Usuario: `postgres`
- Contrasena: `postgres`

## Comandos utiles

| Comando | Descripcion |
| --- | --- |
| `docker compose up -d` | Inicia el contenedor en segundo plano |
| `docker compose ps` | Estado del contenedor |
| `docker compose down` | Detiene y elimina el contenedor (los datos se conservan en el volumen) |
| `docker compose down -v` | Detiene, elimina y borra el volumen `pgdata` |