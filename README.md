# 📝 ToDoExpress

API REST para gestión de tareas con auditoría integrada, construida con **Spring Boot 3**, **Java 21** y **JPA/Hibernate**.

---

## 🚀 Tecnologías

| Tecnología | Versión |
|---|---|
| Java | 21 |
| Spring Boot | 3.5.14 |
| Spring Data JPA | — |
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

**`TaskPriority`**: `LOW` · `MEDIUM` · `HIGH`  
**`TaskStatus`**: `IN_PROGRESS` · `DONE`

### AuditRequest
```json
{
  "level": "INFO",
  "taskId": 1,
  "message": "Tarea actualizada correctamente"
}
```

**`LogLevel`**: `INFO` · `ERROR`

---

## ⚙️ Configuración

El proyecto usa H2 en memoria por defecto. Para usar MySQL, añade a `application.properties`:

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
./gradlew bootRun          # Linux / macOS
gradlew.bat bootRun        # Windows
```

La API quedará disponible en `http://localhost:8080`.

---

## 🧪 Tests

```bash
./gradlew test
```

---

## 📋 Características destacadas

- **Auditoría automática**: cada creación o actualización de tarea genera un registro de auditoría con nivel `INFO`. Los errores no controlados también se persisten con nivel `ERROR` a través del `GlobalExceptionHandler`.
- **Validación**: los campos obligatorios de `TaskRequest` están anotados con `@NotBlank` y `@NotNull`.
- **Mapeo con MapStruct**: conversión limpia entre entidades y DTOs sin código boilerplate.
- **Manejo de errores centralizado**: `GlobalExceptionHandler` devuelve respuestas uniformes en formato `ErrorResponseDTO`.

---

## 👤 Autor

**urian1983** — [GitHub](https://github.com/urian1983)
