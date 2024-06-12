package org.example.service;

import org.example.logger.Loggable;
import org.example.model.Content;
import org.example.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @Loggable
    public List<Content> getAllContents() {
        return contentRepository.findAll();
    }

    @Loggable
    public Content findById(Long id) {
        return contentRepository.findById(id).orElse(null);
    }

    @Loggable
    public Content createContent(Content content) {
        return contentRepository.save(content);
    }

    @Loggable
    public void deleteContent(Long id) {
        contentRepository.deleteById(id);
    }

    @Loggable
    public Content updateContent(Long id, Content contentDetails) {
        Content content = contentRepository.findById(id).orElseThrow(() -> new RuntimeException("Content not found"));
        content.setTitle(contentDetails.getTitle());
        content.setBody(contentDetails.getBody());
        return contentRepository.save(content);
    }
}
