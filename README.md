# 📝 ToDoExpress

API REST para gestión de tareas con auditoría integrada y autenticación JWT, construida con **Spring Boot 3**, **Java 21**, **Spring Security** y **JPA/Hibernate**. Es un backend puro: no incluye vistas ni motor de plantillas.

---

## 🚀 Tecnologías

| Tecnología | Versión |
|---|---|
| Java | 21 |
| Spring Boot | 3.5.14 |
| Spring Data JPA | — |
| Spring Security | — |
| JJWT (JSON Web Tokens) | 0.12.6 |
| Bean Validation | — |
| MapStruct | 1.5.5.Final |
| Lombok | — |
| H2 Database (dev) | — |
| MySQL (prod) | — |
| Gradle | 8.14.4 |

---

## 📁 Estructura del proyecto

```
src/
├── main/
│   ├── java/urian1983/todoexpress/
│   │   ├── config/                   # Configuración de seguridad
│   │   ├── controller/
│   │   │   ├── AuthController.java   # Registro e inicio de sesión
│   │   │   ├── TaskController.java   # Endpoints REST para tareas
│   │   │   ├── AuditController.java  # Endpoints REST para auditoría
│   │   │   └── UserController.java   # Gestión de usuarios (solo ADMIN)
│   │   ├── dto/                      # Records de Request/Response
│   │   ├── exceptions/               # Handler global y excepciones propias
│   │   ├── mapper/                   # Interfaces MapStruct
│   │   ├── model/                    # Entidades JPA y enums
│   │   ├── repository/               # Repositorios Spring Data
│   │   ├── security/                 # JWT: servicio, filtro y UserDetailsService
│   │   ├── service/                  # Interfaces e implementaciones
│   │   └── ToDoExpressApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/urian1983/todoexpress/
        └── ToDoExpressApplicationTests.java
```

---

## 🔐 Autenticación y seguridad

La API es **stateless** y usa **JWT**. Tras registrarte o iniciar sesión recibes un token que debes enviar en la cabecera de las peticiones protegidas:

```
Authorization: Bearer <token>
```

Las contraseñas se almacenan cifradas con **BCrypt**. El token expira a la hora de emitirse (configurable).

### Roles

| Rol | Descripción |
|---|---|
| `USER` | Rol asignado automáticamente al registrarse |
| `ADMIN` | Acceso adicional a la gestión de usuarios |

### Permisos por ruta

| Ruta | Método | Acceso |
|---|---|---|
| `/api/auth/**` | Todos | Público |
| `/api/tasks/**` | `GET` | Público |
| `/api/audits/**` | `GET` | Público |
| `/api/tasks/**` | `POST`, `PUT`, `DELETE` | `USER` o `ADMIN` |
| `/api/audits/**` | `POST` | `USER` o `ADMIN` |
| `/api/users/**` | Todos | Solo `ADMIN` |

---

## 🔌 Endpoints

### Auth — `/api/auth`

| Método | Ruta | Descripción | Respuestas |
|---|---|---|---|
| `POST` | `/api/auth/register` | Registrar usuario (rol `USER`) y obtener token | `201`, `400`, `409` |
| `POST` | `/api/auth/login` | Iniciar sesión y obtener token | `200`, `400`, `401` |

### Tasks — `/api/tasks`

| Método | Ruta | Descripción |
|---|---|---|
| `POST` | `/api/tasks` | Crear una tarea |
| `GET` | `/api/tasks` | Listar todas las tareas |
| `GET` | `/api/tasks/{id}` | Obtener tarea por ID |
| `PUT` | `/api/tasks/{id}` | Actualizar tarea |
| `DELETE` | `/api/tasks/{id}` | Eliminar tarea |

**Filtros** sobre `GET /api/tasks`:

| Ejemplo | Descripción |
|---|---|
| `GET /api/tasks?priority=HIGH` | Tareas con la prioridad indicada |
| `GET /api/tasks?status=DONE` | Tareas con el estado indicado |
| `GET /api/tasks?description=login` | Búsqueda parcial por descripción, sin distinguir mayúsculas. Devuelve una lista |

> Si el listado completo o cualquiera de los filtros no encuentra ninguna tarea, la API responde `404` con el mensaje `No tasks found`, en lugar de una lista vacía.

### Audits — `/api/audits`

| Método | Ruta | Descripción |
|---|---|---|
| `POST` | `/api/audits` | Crear registro de auditoría |
| `GET` | `/api/audits` | Listar todos los registros |
| `GET` | `/api/audits/{id}` | Obtener registro por ID |

### Users — `/api/users` (solo `ADMIN`)

| Método | Ruta | Descripción |
|---|---|---|
| `GET` | `/api/users/{id}` | Obtener usuario por ID |
| `PUT` | `/api/users/{id}` | Actualizar usuario |
| `DELETE` | `/api/users/{id}` | Eliminar usuario |

---

## 📦 Modelos

### RegisterRequest

```json
{
  "username": "urian",
  "password": "mi_password",
  "confirmPassword": "mi_password"
}
```

### LoginRequest

```json
{
  "username": "urian",
  "password": "mi_password"
}
```

### AuthResponse

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "user": {
    "id": 1,
    "username": "urian",
    "role": "USER"
  }
}
```

### UserRequest

```json
{
  "username": "urian",
  "password": "nueva_password"
}
```

La contraseña debe tener al menos 8 caracteres.

### UserResponse

```json
{
  "id": 1,
  "username": "urian",
  "role": "USER"
}
```

**Role:** `USER` · `ADMIN`

### TaskRequest

```json
{
  "description": "Implementar autenticación",
  "priority": "HIGH",
  "status": "IN_PROGRESS"
}
```

### TaskResponse

```json
{
  "id": 1,
  "description": "Implementar autenticación",
  "priority": "HIGH",
  "status": "IN_PROGRESS",
  "createdAt": "2026-07-12T10:00:00",
  "updatedAt": "2026-07-12T14:30:00"
}
```

**TaskPriority:** `LOW` · `MEDIUM` · `HIGH`

**TaskStatus:** `IN_PROGRESS` · `DONE`

### AuditRequest

```json
{
  "level": "INFO",
  "taskId": 1,
  "message": "Tarea actualizada correctamente"
}
```

### AuditResponse

```json
{
  "id": 1,
  "level": "INFO",
  "taskId": 1,
  "message": "Tarea actualizada correctamente",
  "createdAt": "2026-07-12T21:43:08"
}
```

**LogLevel:** `INFO` · `WARNING` · `ERROR` · `DEBUG`

### ErrorResponseDTO

Formato uniforme de las respuestas de error:

```json
{
  "message": "Descripción del error"
}
```

### Manejo de errores y auditoría

Todos los errores los gestiona `GlobalExceptionHandler`, que devuelve un `ErrorResponseDTO` y guarda además un registro de auditoría (con `taskId` = `0`):

| Situación | HTTP | Nivel de auditoría |
|---|---|---|
| Recurso no encontrado (`NotFoundException`) | `404` | `WARNING` |
| Usuario ya existente (`UserAlreadyExistsException`) | `409` | `WARNING` |
| Argumento inválido (`IllegalArgumentException`), p. ej. contraseñas que no coinciden | `400` | `WARNING` |
| Credenciales incorrectas (`BadCredentialsException`) | `401` | `WARNING` |
| Validación de datos fallida (`MethodArgumentNotValidException`) | `400` | `ERROR` |
| Acceso denegado por rol (`AccessDeniedException`), p. ej. un `USER` en `/api/users/**` | `403` | No se audita |
| Cualquier otro error no controlado | `500` | `ERROR` |

> Las peticiones rechazadas por las reglas de ruta de `SecurityConfig` (por ejemplo, un `POST /api/tasks` sin token) las resuelve el filtro de Spring Security antes de llegar a los controllers, por lo que su respuesta no sigue el formato `ErrorResponseDTO`.

---

## ⚙️ Configuración

### Variables de `application.properties`

```properties
spring.application.name=ToDoExpress
jwt.secret=${JWT_SECRET:una-clave-larga-de-desarrollo-cambiar-en-produccion-minimo-256bits}
jwt.expiration-ms=3600000
```

| Propiedad | Descripción |
|---|---|
| `jwt.secret` | Clave de firma de los tokens. Se lee de la variable de entorno `JWT_SECRET`; si no existe se usa una clave de desarrollo |
| `jwt.expiration-ms` | Duración del token en milisegundos (3600000 = 1 hora) |

> ⚠️ **En producción define siempre `JWT_SECRET`** con una clave aleatoria de al menos 256 bits. No uses la clave por defecto.

```bash
export JWT_SECRET="tu-clave-secreta-larga-y-aleatoria"
```

### Base de datos

El proyecto usa **H2** en memoria por defecto.

Para utilizar **MySQL**, añade en `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/todoexpress
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
spring.jpa.hibernate.ddl-auto=update
```

---

## ▶️ Ejecución

```bash
# Clonar el repositorio
git clone https://github.com/urian1983/ToDoExpress.git

cd ToDoExpress

# Ejecutar con Gradle Wrapper
./gradlew bootRun      # Linux / macOS
gradlew.bat bootRun    # Windows
```

La API estará disponible en `http://localhost:8080`.

### Ejemplo de uso

```bash
# 1. Registrar un usuario y obtener el token
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"urian","password":"mi_password","confirmPassword":"mi_password"}'

# 2. Crear una tarea usando el token
curl -X POST http://localhost:8080/api/tasks \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{"description":"Implementar autenticación","priority":"HIGH","status":"IN_PROGRESS"}'

# 3. Listar tareas de prioridad alta (público)
curl "http://localhost:8080/api/tasks?priority=HIGH"
```

---

## 🧪 Tests

```bash
./gradlew test
```

---

## 📋 Características

- ✅ API REST desarrollada con Spring Boot 3 (backend puro, sin vistas).
- ✅ Autenticación stateless con JWT y contraseñas cifradas con BCrypt.
- ✅ Control de acceso por roles (`USER` y `ADMIN`) a nivel de ruta y de método.
- ✅ Registro e inicio de sesión de usuarios.
- ✅ Filtrado de tareas por prioridad, estado y descripción (búsqueda parcial).
- ✅ Auditoría automática: cada creación o actualización de tarea genera un registro con nivel `INFO` (el borrado no se audita).
- ✅ Persistencia automática de errores en auditoría mediante `GlobalExceptionHandler`: nivel `WARNING` para errores de cliente (404, 409, 400, 401) y `ERROR` para fallos de validación y errores internos.
- ✅ Validación de datos utilizando Bean Validation (`@NotBlank`, `@NotNull`, `@Size`).
- ✅ Conversión entre entidades y DTOs mediante MapStruct.
- ✅ Manejo centralizado de excepciones con respuestas uniformes (`ErrorResponseDTO`).
- ✅ Base de datos H2 para desarrollo y soporte para MySQL en producción.

---

## 👤 Autor

**urian1983** — [GitHub](https://github.com/urian1983)
