package org.example.controller;

import org.example.DTO.AttachmentDTO;
import org.example.logger.Loggable;
import org.example.model.Attachment;
import org.example.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attachments")
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;

    @Loggable
    @GetMapping("/attachments")
    @PreAuthorize("hasAuthority('USER')")
    public List<AttachmentDTO> getAllAttachments() {
        return attachmentService.getAllAttachments();
    }

    @Loggable
    @GetMapping("/attachments/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public AttachmentDTO getAttachmentById(@PathVariable Long id) {
        return attachmentService.getAttachmentById(id);
    }

    @Loggable
    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public Attachment createAttachment(@RequestBody Attachment attachment) {
        return attachmentService.createAttachment(attachment);
    }

    @Loggable
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public Attachment updateAttachment(@PathVariable Long id, @RequestBody Attachment attachmentDetails) {
        return attachmentService.updateAttachment(id, attachmentDetails);
    }

    @Loggable
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public void deleteAttachment(@PathVariable Long id) {
        attachmentService.deleteAttachment(id);
    }


}
