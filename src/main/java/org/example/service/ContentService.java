package org.example.service;

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

    public List<Content> getAllContents() {
        return contentRepository.findAll();
    }

    public Optional<Content> getContentById(Long id) {
        return contentRepository.findById(id);
    }

    public Content createContent(Content content) {
        return contentRepository.save(content);
    }

    public void deleteContent(Long id) {
        contentRepository.deleteById(id);
    }

    public Content updateContent(Long id, Content contentDetails) {
        Content content = contentRepository.findById(id).orElseThrow(() -> new RuntimeException("Content not found"));
        content.setTitle(contentDetails.getTitle());
        content.setBody(contentDetails.getBody());
        return contentRepository.save(content);
    }
}
