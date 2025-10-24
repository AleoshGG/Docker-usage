# Arquitectura de Microservicios con Docker

Este proyecto demuestra una arquitectura de microservicios simple utilizando Docker. Incluye una aplicación frontend, una API backend y una base de datos, cada una ejecutándose en su propio contenedor.

## Servicios Incluidos

El entorno se compone de los siguientes servicios, definidos en el archivo `docker-compose.yml`:

1.  **`database`**:

    - **Imagen**: `postgres:16.2`
    - **Descripción**: Una base de datos PostgreSQL para persistir los datos de la aplicación.
    - **Dockerfile**: `Database/Dockerfile`
    - **Puerto (host:container)**: `5000:5432`
    - **Nombre del contenedor**: `db-alexis`

2.  **`api`**:

    - **Imagen**: Construida desde `demomicroservices/Dockerfile`
    - **Descripción**: Una API RESTful desarrollada en Java (Spring Boot) que gestiona la lógica de negocio.
    - **Dockerfile**: `demomicroservices/Dockerfile`
    - **Puerto (host:container)**: `8080:8080`
    - **Nombre del contenedor**: `api-alexis`

3.  **`frontend`**:
    - **Imagen**: Construida desde `demofront/Dockerfile`
    - **Descripción**: Una aplicación de una sola página (SPA) desarrollada en Angular que consume la API.
    - **Dockerfile**: `demofront/Dockerfile`
    - **Puerto (host:container)**: `3000:3000`
    - **Nombre del contenedor**: `fe-alexis`

## Cómo Levantar el Entorno

Para levantar todo el entorno, sigue estos pasos:

### Prerrequisitos

- Tener [Docker](https://www.docker.com/get-started) instalado y en ejecución en tu sistema.

### Instrucciones

1.  **Clona o descarga este repositorio.**

2.  **Abre una terminal en la raíz del proyecto.**

3.  **Ejecuta el siguiente comando para construir las imágenes y levantar los contenedores:**

    ```bash
    docker-compose up -d
    ```

    - El flag `-d` (detached) ejecuta los contenedores en segundo plano.
    - La primera vez que ejecutes este comando, Docker descargará las imágenes base y construirá las imágenes de los servicios, lo que puede tardar unos minutos.

4.  **Verifica que los contenedores estén en ejecución:**

    ```bash
    docker-compose ps
    ```

    Deberías ver los tres servicios (`db-alexis`, `api-alexis`, `fe-alexis`) en estado "Up" o "Running".

## Comunicación entre Servicios

Los servicios se comunican entre sí a través de una red puente (`bridge`) definida en `docker-compose.yml` llamada `local-network`.

- **Frontend -> API**: El frontend (Angular) se comunica con la API a través del nombre del servicio `api-alexis` en el puerto `8080`. La URL de la API que se configuraría en el frontend sería `http://localhost:8080`.

- **API -> Database**: La API (Java) se conecta a la base de datos usando el nombre del servicio `db-alexis` en el puerto `5432`. La cadena de conexión JDBC sería algo como `jdbc:postgresql://db-alexis:5432/alexis`.

Gracias a la red de Docker, los contenedores pueden resolverse por sus nombres de servicio, lo que facilita la comunicación interna sin exponer todos los puertos al host.
