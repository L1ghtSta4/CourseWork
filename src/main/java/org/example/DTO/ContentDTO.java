package org.example.DTO;

import lombok.Data;
import java.util.List;

@Data
public class ContentDTO {
    private Long id;
    private String title;
    private String body;
    private CategoryDTO category;
    private UserDTO user;
    private List<CommentDTO> comments;
    private List<AttachmentDTO> attachments;
}

