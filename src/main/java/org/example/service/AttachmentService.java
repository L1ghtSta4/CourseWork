package org.example.service;


import org.example.DTO.AttachmentDTO;
import org.example.logger.Loggable;
import org.example.model.Attachment;
import org.example.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Loggable
    public List<AttachmentDTO> getAllAttachments() {
        List<Attachment> attachments = attachmentRepository.findAll();
        return attachments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

    }
    @Loggable
    public AttachmentDTO getAttachmentById(Long id) {
        Attachment attachment = attachmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attachment not found"));
        return convertToDTO(attachment);
    }

    @Loggable
    public Attachment createAttachment(Attachment attachment) {
        return attachmentRepository.save(attachment);
    }

    @Loggable
    public Attachment updateAttachment(Long id, Attachment attachmentDetails) {
        Attachment attachment = attachmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attachment not found"));
        attachment.setFilename(attachmentDetails.getFilename());
        attachment.setFileType(attachmentDetails.getFileType());
        attachment.setUrl(attachmentDetails.getUrl());
        attachment.setUploadedAt(attachmentDetails.getUploadedAt());
        attachment.setContent(attachmentDetails.getContent());
        return attachmentRepository.save(attachment);
    }

    @Loggable
    public void deleteAttachment(Long id) {
        Attachment attachment = attachmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attachment not found"));
        attachmentRepository.delete(attachment);
    }

    private AttachmentDTO convertToDTO(Attachment attachment) {
        return new AttachmentDTO(attachment.getId(),
                attachment.getFilename(),
                attachment.getFileType(),attachment.getUrl(),
                attachment.getUploadedAt());
    }
}

