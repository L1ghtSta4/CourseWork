package org.example.DTO;



import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AuditLogDTO {
    private Long id;
    private String operation;
    private LocalDateTime timestamp;
}

