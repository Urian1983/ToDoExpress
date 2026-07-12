# 📝 ToDoExpress

API REST para gestión de tareas con auditoría integrada, construida con **Spring Boot 3**, **Java 21** y **JPA/Hibernate**.

---

## 🚀 Tecnologías

| Tecnología | Versión |
|---|---|
| Java | 21 |
| Spring Boot | 3.5.14 |
| Spring Data JPA | — |
| Springdoc OpenAPI (Swagger UI) | 2.x |
| MapStruct | 1.5.5.Final |
| Lombok | — |
| H2 Database (dev) | — |
| MySQL (prod) | — |
| Gradle | 8.14.4 |

---

## 📁 Estructura del proyecto

```
src/main/java/
├── controller/
│   ├── TaskController.java       # Endpoints REST para tareas
│   └── AuditController.java      # Endpoints REST para auditoría
├── urian1983/todoexpress/
│   ├── dto/                      # Records de Request/Response
│   ├── exceptions/               # Handler global + NotFoundException
│   ├── mapper/                   # Interfaces MapStruct
│   ├── model/                    # Entidades JPA y enums
│   ├── repository/               # Repositorios Spring Data
│   └── service/                  # Interfaces e implementaciones
```

---

## 📖 Documentación de la API (Swagger)

La API incluye documentación interactiva mediante **Springdoc OpenAPI + Swagger UI**.

Una vez iniciada la aplicación, puedes acceder a:

| Recurso | URL |
|---|---|
| Swagger UI | `http://localhost:8080/swagger-ui/index.html` |
| OpenAPI JSON | `http://localhost:8080/v3/api-docs` |

Desde Swagger podrás:

- Explorar todos los endpoints disponibles.
- Consultar los modelos de petición y respuesta.
- Ejecutar peticiones directamente desde el navegador.
- Visualizar los códigos de respuesta de cada operación.

---

## 🔌 Endpoints

### Tasks — `/api/tasks`

| Método | Ruta | Descripción |
|---|---|---|
| `POST` | `/api/tasks` | Crear una tarea |
| `GET` | `/api/tasks` | Listar todas las tareas |
| `GET` | `/api/tasks/{id}` | Obtener tarea por ID |
| `PUT` | `/api/tasks/{id}` | Actualizar tarea |
| `DELETE` | `/api/tasks/{id}` | Eliminar tarea |

### Audits — `/api/audits`

| Método | Ruta | Descripción |
|---|---|---|
| `POST` | `/api/audits` | Crear registro de auditoría |
| `GET` | `/api/audits` | Listar todos los registros |
| `GET` | `/api/audits/{id}` | Obtener registro por ID |

---

## 📦 Modelos

### TaskRequest

```json
{
  "description": "Implementar autenticación",
  "priority": "HIGH",
  "status": "IN_PROGRESS"
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

**LogLevel:** `INFO` · `ERROR`

---

## ⚙️ Configuración

El proyecto usa **H2** en memoria por defecto.

Para utilizar **MySQL**, configura `application.properties`:

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

La aplicación estará disponible en:

- API REST: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`

---

## 🧪 Tests

```bash
./gradlew test
```

---

## 📋 Características

- ✅ API REST desarrollada con Spring Boot 3.
- ✅ Documentación automática mediante Swagger/OpenAPI.
- ✅ Auditoría automática: cada creación o actualización de tarea genera un registro con nivel `INFO`.
- ✅ Persistencia automática de errores con nivel `ERROR` mediante `GlobalExceptionHandler`.
- ✅ Validación de datos utilizando Bean Validation (`@NotBlank`, `@NotNull`).
- ✅ Conversión entre entidades y DTOs mediante MapStruct.
- ✅ Manejo centralizado de excepciones con respuestas uniformes (`ErrorResponseDTO`).
- ✅ Base de datos H2 para desarrollo y soporte para MySQL en producción.

## 👤 Autor

**urian1983** — [GitHub](https://github.com/urian1983)
