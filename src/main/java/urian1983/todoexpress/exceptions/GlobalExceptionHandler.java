package urian1983.todoexpress.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import urian1983.todoexpress.dto.ErrorResponseDTO;
import urian1983.todoexpress.model.Audit;
import urian1983.todoexpress.model.LogLevel;
import urian1983.todoexpress.repository.AuditRepository;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final AuditRepository auditRepository;

    public GlobalExceptionHandler(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleException(Exception ex) {
        auditRepository.save(new Audit(LogLevel.ERROR,0L, ex.getMessage()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponseDTO(ex.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFoundException(NotFoundException ex) {
        auditRepository.save(new Audit(LogLevel.ERROR,0L, ex.getMessage()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDTO(ex.getMessage()));
    }

}
