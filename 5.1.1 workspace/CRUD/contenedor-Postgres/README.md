# Contenedor PostgreSQL 17

Comandos ejecutados y explicacion paso a paso para crear un contenedor de PostgreSQL 17.

## Paso 1: Crear el archivo docker-compose.yml

Se creo este archivo en la carpeta `contenedor`:

```yaml
services:
  postgres:
    image: postgres:17
    container_name: postgres17
    restart: unless-stopped
    environment:
      POSTGRES_DB: test
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
    ports:
      - "5432:5432"
    volumes:
      - pgdata:/var/lib/postgresql/data

volumes:
  pgdata:
```

Explicacion:

- `services.postgres`: define el servicio llamado `postgres`.
- `image: postgres:17`: usa la imagen oficial de PostgreSQL version 17.
- `container_name: postgres17`: nombre fijo del contenedor.
- `restart: unless-stopped`: se reinicia automaticamente salvo que se detenga manualmente.
- `environment`: configuracion inicial de la base de datos:
  - `POSTGRES_DB: test` -> crea la base de datos `test`.
  - `POSTGRES_USER: postgres` -> usuario `postgres`.
  - `POSTGRES_PASSWORD: postgres` -> contrasena `postgres`.
- `ports: "5432:5432"`: expone el puerto 5432 del contenedor al puerto 5432 del equipo.
- `volumes: pgdata`: volumen para persistir los datos aunque se borre el contenedor.

## Paso 2: Levantar el contenedor

Desde la carpeta `contenedor` se ejecuto:

```
docker compose up -d
```

- `-d`: modo desatendido (detached), el contenedor corre en segundo plano.
- Docker descarga la imagen `postgres:17` la primera vez y luego crea e inicia el contenedor `postgres17`.

## Paso 3: Verificar que el contenedor este activo

```
docker compose ps
```

Muestra el estado de los servicios definidos en el `docker-compose.yml`.

## Paso 4: Conectar a la base de datos

```
docker exec -it postgres17 psql -U postgres -d test -c "SELECT version();"
```

- `docker exec`: ejecuta un comando dentro del contenedor.
- `-it`: modo interactivo.
- `psql -U postgres -d test`: abre el cliente de PostgreSQL como usuario `postgres` en la base `test`.
- `SELECT version();`: comprueba que la conexion funciona.

## Datos de conexion para tu aplicacion (Spring Boot)

- URL JDBC: `jdbc:postgresql://localhost:5432/test`
- Usuario: `postgres`
- Contrasena: `postgres`

## Comandos utiles

| Comando | Descripcion |
| --- | --- |
| `docker compose up -d` | Inicia el contenedor en segundo plano |
| `docker compose ps` | Estado del contenedor |
| `docker compose down` | Detiene y elimina el contenedor (los datos se conservan en el volumen) |
| `docker compose down -v` | Detiene y elimina el contenedor y tambien borra el volumen `pgdata` |
| `docker restart postgres17` | Reinicia el contenedor |