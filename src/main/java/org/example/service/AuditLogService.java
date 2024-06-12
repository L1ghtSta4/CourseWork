package org.example.service;


import org.example.logger.Loggable;
import org.example.model.AuditLog;
import org.example.repository.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditLogService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Loggable
    public List<AuditLog> getAllAuditLogs() {
        return auditLogRepository.findAll();
    }

    @Loggable
    public AuditLog getAuditLogById(Long id) {
        return auditLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AuditLog not found"));
    }

    @Loggable
    public AuditLog createAuditLog(AuditLog auditLog) {
        return auditLogRepository.save(auditLog);
    }

    @Loggable
    public AuditLog updateAuditLog(Long id, AuditLog auditLogDetails) {
        AuditLog auditLog = auditLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AuditLog not found"));
        auditLog.setOperation(auditLogDetails.getOperation());
        auditLog.setTimestamp(auditLogDetails.getTimestamp());
        auditLog.setUser(auditLogDetails.getUser());
        return auditLogRepository.save(auditLog);
    }

    @Loggable
    public void deleteAuditLog(Long id) {
        AuditLog auditLog = auditLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AuditLog not found"));
        auditLogRepository.delete(auditLog);
    }
}

