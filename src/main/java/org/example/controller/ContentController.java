package org.example.controller;
import org.example.DTO.*;
import org.example.logger.Loggable;
import org.example.model.Content;
import org.example.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/contents")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @Loggable
    @GetMapping
    public List<ContentDTO> getAllContents() {
        List<Content> contents = contentService.getAllContents();
        return contents.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Loggable
    @GetMapping("/{id}")
    public ContentDTO getContentById(@PathVariable Long id) {
        Content content = contentService.findById(id);
        return convertToDTO(content);
    }

    @Loggable
    @PostMapping
    public Content createContent(@RequestBody Content content) {
        return contentService.createContent(content);
    }

    @Loggable
    @PutMapping("/{id}")
    public ResponseEntity<Content> updateContent(@PathVariable Long id, @RequestBody Content contentDetails) {
        return ResponseEntity.ok(contentService.updateContent(id, contentDetails));
    }

    @Loggable
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContent(@PathVariable Long id) {
        contentService.deleteContent(id);
        return ResponseEntity.ok().build();
    }


    private ContentDTO convertToDTO(Content content) {
        ContentDTO contentDTO = new ContentDTO();
        contentDTO.setId(content.getId());
        contentDTO.setTitle(content.getTitle());
        contentDTO.setBody(content.getBody());

        // Set CategoryDTO

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(content.getCategory().getId());
        categoryDTO.setName(content.getCategory().getName());
        contentDTO.setCategory(categoryDTO);

        // Set UserDTO
        UserDTO userDTO = new UserDTO();
        userDTO.setId(content.getUser().getId());
        userDTO.setUsername(content.getUser().getUsername());
        userDTO.setRole(content.getUser().getRole());
        contentDTO.setUser(userDTO);

        // Set Comments
        List<CommentDTO> commentDTOs = content.getComments().stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setId(comment.getId());
            commentDTO.setAuthor(comment.getAuthor());
            commentDTO.setText(comment.getText());
            commentDTO.setCreatedAt(comment.getCreatedAt());
            return commentDTO;
        }).collect(Collectors.toList());
        contentDTO.setComments(commentDTOs);

        // Set Attachments
        List<AttachmentDTO> attachmentDTOs = content.getAttachments().stream().map(attachment -> {
            AttachmentDTO attachmentDTO = new AttachmentDTO(attachment.getId(),
                    attachment.getFilename(),
                    attachment.getFileType(),attachment.getUrl(),
                    attachment.getUploadedAt());

            return attachmentDTO;
        }).collect(Collectors.toList());
        contentDTO.setAttachments(attachmentDTOs);

        return contentDTO;
    }
}
