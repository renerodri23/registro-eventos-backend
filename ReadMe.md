# Registro de Eventos Tecnológicos - Backend

Backend REST desarrollado con Spring Boot siguiendo el enfoque de Arquitectura Hexagonal (Ports and Adapters).
El sistema permite gestionar usuarios, autenticación mediante JWT, eventos tecnológicos e inscripciones a eventos.

---

## Descripción General

Este proyecto implementa una API REST orientada a la gestión de eventos tecnológicos, donde los usuarios pueden:

* Registrarse en el sistema
* Autenticarse mediante JWT
* Crear y consultar eventos
* Inscribirse y cancelar inscripciones a eventos

La arquitectura está diseñada para mantener la lógica de negocio desacoplada de la infraestructura, facilitando el mantenimiento, pruebas y escalabilidad.

---

## Tecnologías Utilizadas

* Java 17
* Spring Boot
* Spring Security
* JWT (JSON Web Token)
* JPA / Hibernate
* MySQL
* Maven
* JUnit y Mockito (Testing)

---

## Arquitectura del Proyecto

El proyecto sigue el patrón de Arquitectura Hexagonal, separando responsabilidades en capas bien definidas:

### Domain

Contiene las entidades del dominio y las reglas de negocio principales.
Ejemplo: User, Event, Inscription.

### Application

Incluye los casos de uso, comandos y servicios que orquestan la lógica del sistema.
Ejemplo: UserService, EventService, AuthService, InscriptionService.

### Adapters

#### Inbound (Entrada)

Controladores REST que exponen la API al exterior.

* Controllers
* DTOs
* Mappers REST

#### Outbound (Salida)

Implementaciones de persistencia y componentes externos.

* Repositorios JPA
* Configuración de seguridad
* Integración con base de datos

Este diseño permite cambiar la base de datos, seguridad o framework web sin afectar la lógica de negocio.

---

## Seguridad y Autenticación

La autenticación se implementa mediante JWT (JSON Web Token).

### Flujo de autenticación

1. El usuario se registra en el sistema.
2. Realiza login con su email y contraseña.
3. El sistema genera un token JWT.
4. El cliente envía el token en cada petición protegida.

Header requerido:

```
Authorization: Bearer {token}
```

Los endpoints protegidos validan el token antes de ejecutar la lógica del negocio.

---

## Endpoints Principales

### Autenticación

* POST /RegistroEventoTecnologicos/auth/login

### Usuarios

* POST /RegistroEventoTecnologicos/users/create
* POST /RegistroEventoTecnologicos/users/update
* GET /RegistroEventoTecnologicos/users/find-by-id/{userId}
* GET /RegistroEventoTecnologicos/users/find-by-email/{email}
* GET /RegistroEventoTecnologicos/users/get-all-users
* POST /RegistroEventoTecnologicos/users/exists-by-email

### Eventos

* POST /RegistroEventoTecnologicos/evento/create
* POST /RegistroEventoTecnologicos/evento/update
* POST /RegistroEventoTecnologicos/evento/search-event
* GET /RegistroEventoTecnologicos/evento/find-by-id/{eventId}
* GET /RegistroEventoTecnologicos/evento/get-all-events

### Inscripciones

* POST /RegistroEventoTecnologicos/inscriptions/create
* POST /RegistroEventoTecnologicos/inscriptions/cancel
* GET /RegistroEventoTecnologicos/inscriptions/find-user-inscriptions/{userId}
* GET /RegistroEventoTecnologicos/inscriptions/find-event-inscriptions/{eventId}

---

## Base de Datos

El proyecto utiliza MySQL como motor de base de datos y JPA/Hibernate como capa de persistencia.

El script de creación de la base de datos se encuentra en:

```
src/main/resources/db/schema.sql
```

Pasos:
1. Crear la base de datos en MySQL.
2. Ejecutar el script `schema.sql` para crear las tablas necesarias.
3. Configurar las credenciales de conexión en `application.properties`.

Configura las credenciales en el archivo:

```
src/main/resources/application.properties
```

---

## Cómo Ejecutar el Proyecto

### 1. Clonar el repositorio

```
git clone https://github.com/tu-usuario/tu-repositorio.git](https://github.com/renerodri23/registro-eventos-backend
```

### 2. Configurar la base de datos

Crear la base de datos en MySQL y actualizar el `application.properties`.

### 3. Ejecutar la aplicación

```
mvn clean install
mvn spring-boot:run
```

La aplicación se ejecutará en:

```
http://localhost:8080
```

---

## Testing

El proyecto incluye pruebas automatizadas para garantizar la calidad del código:

### Unit Tests

Pruebas enfocadas en la capa de Application (Services), utilizando Mockito para simular dependencias.


```
src/test/java
```



---

## Mejoras Futuras

* Servicios y Endpoints para funcionalidades adicionales
* Implementación de roles y permisos para usuarios
* Integración con servicios externos (ej. envío de correos)
* Configuración de Docker para despliegue más sencillo
