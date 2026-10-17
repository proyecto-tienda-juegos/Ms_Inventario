# Inventario Service

Microservicio encargado del stock disponible de cada juego. Guarda solo el `juegoId` (referencia al microservicio Juegos) y la cantidad disponible — no duplica la info del juego.

## Requisitos previos

- Java 17
- Maven
- MySQL corriendo en `localhost:3306`
- El **Eureka Server** (`infra/eureka-server`) debe estar corriendo en `http://localhost:8761` antes de levantar este microservicio

## 1. Clonar el repositorio

```bash
git clone <URL_DE_TU_REPOSITORIO>
cd inventario-service
```

## 2. Configurar la base de datos

Abre `src/main/resources/application.properties` y reemplaza `TU_PASSWORD_AQUI` por la contraseña de tu MySQL local.

## 3. Compilar

```bash
mvn clean install
```

## 4. Ejecutar

```bash
mvn spring-boot:run
```

Queda disponible en `http://localhost:8083` (o vía Gateway en `http://localhost:8080/api/inventario`).

## 5. Generar el .jar

```bash
mvn clean package
java -jar target/inventario-service-1.0.0.jar
```

## 6. Probar los endpoints con Postman

| Método | URL | Body (JSON) |
|---|---|---|
| GET | `http://localhost:8083/api/inventario` | — |
| GET | `http://localhost:8083/api/inventario/1` | — |
| GET | `http://localhost:8083/api/inventario/juego/1` | — (busca el stock del juego con id 1) |
| POST | `http://localhost:8083/api/inventario` | `{"juegoId":1,"cantidadDisponible":50,"stockMinimo":5}` |
| PUT | `http://localhost:8083/api/inventario/1` | `{"juegoId":1,"cantidadDisponible":45,"stockMinimo":5}` |
| DELETE | `http://localhost:8083/api/inventario/1` | — |

## Nota

El endpoint `GET /api/inventario/juego/{juegoId}` es el que usará el microservicio **Carrito** para validar si hay stock antes de agregar un juego al carrito.
