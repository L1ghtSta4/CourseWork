package org.example.repository;

import org.example.logger.Loggable;
import org.example.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

    @Loggable
    @Override
    List<AuditLog> findAll();

    @Loggable
    @Override
    Optional<AuditLog> findById(Long aLong);


}