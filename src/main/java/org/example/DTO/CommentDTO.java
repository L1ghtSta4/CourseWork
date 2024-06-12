package org.example.DTO;




import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CommentDTO {
    private Long id;
    private String author;
    private String text;
    private LocalDateTime createdAt;
}

