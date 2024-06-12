package org.example.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AttachmentDTO {
    private Long id;
    private String filename;
    private String fileType;
    private String url;
    private LocalDateTime uploadedAt;
}

