package org.example.controller;

import org.example.DTO.AuditLogDTO;
import org.example.logger.Loggable;
import org.example.model.AuditLog;
import org.example.service.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/audit-logs")
public class AuditLogController {

    @Autowired
    private AuditLogService auditLogService;
    private AuditLogDTO convertToDTO(AuditLog auditLog) {
        return new AuditLogDTO(
                auditLog.getId(),
                auditLog.getOperation(),
                auditLog.getTimestamp()
        );
    }


    @Loggable
    @GetMapping
    public List<AuditLogDTO> getAllAuditLogs() {
        return auditLogService.getAllAuditLogs().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Loggable
    @GetMapping("/{id}")
    public AuditLogDTO getAuditLogById(@PathVariable Long id) {
        AuditLog auditLog = auditLogService.getAuditLogById(id);
        return convertToDTO(auditLog);
    }

    @Loggable
    @PostMapping
    public AuditLog createAuditLog(@RequestBody AuditLog auditLog) {
        return auditLogService.createAuditLog(auditLog);
    }

    @Loggable
    @PutMapping("/{id}")
    public AuditLog updateAuditLog(@PathVariable Long id, @RequestBody AuditLog auditLogDetails) {
        return auditLogService.updateAuditLog(id, auditLogDetails);
    }

    @Loggable
    @DeleteMapping("/{id}")
    public void deleteAuditLog(@PathVariable Long id) {
        auditLogService.deleteAuditLog(id);
    }
}
