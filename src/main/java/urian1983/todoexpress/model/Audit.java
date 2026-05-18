package urian1983.todoexpress.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="audit")
@AllArgsConstructor
@NoArgsConstructor
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private LogLevel level;
    private Long taskId;
    private String message;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Audit(Long taskId, String message) {
        this.taskId = taskId;
        this.message = message;
        this.level = LogLevel.INFO;
        this.createdAt = LocalDateTime.now();
    }

    public Audit(LogLevel level, Long taskId, String message) {
        this.level = level;
        this.taskId = taskId;
        this.message = message;
    }
}
