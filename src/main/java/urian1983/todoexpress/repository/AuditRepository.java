package urian1983.todoexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import urian1983.todoexpress.model.Audit;

public interface AuditRepository extends JpaRepository<Audit, Long> {
}
