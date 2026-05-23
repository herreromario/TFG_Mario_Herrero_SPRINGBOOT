# API_STOCKMEAL

API REST para la gestión de stock de productos, recetas y producción.

## Descripción

`API_STOCKMEAL` es una aplicación Spring Boot que expone servicios REST para:

- Listar productos, platos e ingredientes.
- Consultar recetas y capacidad de producción.
- Registrar y consultar histórico de producción.

La aplicación utiliza Spring Boot 4, Spring Data JPA y MySQL.

## Tecnologías

- Java 17
- Spring Boot 4.0.3
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- Lombok
- Maven

## Requisitos

- JDK 17
- Maven 3.x
- MySQL funcionando

## Configuración

Actualiza el archivo `src/main/resources/application.properties` con los datos de tu base de datos MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gestion_stock?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=mysql
spring.jpa.hibernate.ddl-auto=none
```

Asegúrate de que la base de datos `gestion_stock` exista y de tener las tablas necesarias.

## Compilar y ejecutar

Desde la raíz del proyecto:

```bash
./mvnw clean package
./mvnw spring-boot:run
```

En Windows:

```powershell
.\mvnw.cmd clean package
.\mvnw.cmd spring-boot:run
```

## Endpoints

### Productos

- `GET /productos` - Devuelve todos los productos.
- `GET /productos/platos` - Devuelve solo los productos tipo plato.
- `GET /productos/ingredientes` - Devuelve solo los productos tipo ingrediente.

### Recetas

- `GET /recetas` - Devuelve todas las recetas.
- `GET /recetas/{id}` - Devuelve los detalles de la receta con el ID especificado.
- `GET /recetas/capacidad` - Devuelve la capacidad de producción por receta.

### Producción

- `GET /produccion` - Devuelve el histórico completo de producción.
- `GET /produccion/fecha/{fecha}` - Devuelve producción de una fecha específica. Formato: `YYYY-MM-DD`.
- `GET /produccion/rango?desde=YYYY-MM-DD&hasta=YYYY-MM-DD` - Devuelve producción en un rango de fechas.
- `GET /produccion/plato/{idProducto}` - Devuelve producción filtrada por plato.
- `POST /produccion` - Registra una nueva producción.

Ejemplo de cuerpo para POST `/produccion`:

```json
{
  "idProducto": 1,
  "cantidad": 10,
  "fecha": "2026-05-23"
}
```

## Estructura del proyecto

- `src/main/java/com/stockmeal/api` - Código fuente principal.
- `src/main/java/com/stockmeal/api/controllers` - Controladores REST.
- `src/main/java/com/stockmeal/api/dto` - Objetos de transferencia de datos.
- `src/main/java/com/stockmeal/api/models` - Entidades JPA.
- `src/main/java/com/stockmeal/api/repositories` - Repositorios JPA.
- `src/main/java/com/stockmeal/api/services` - Lógica de negocio.

## Notas

- El proyecto utiliza validación con `jakarta.validation` para los request DTO.
- Si deseas habilitar la creación automática de tablas en desarrollo, ajusta `spring.jpa.hibernate.ddl-auto` a `update` o `create`.

---
