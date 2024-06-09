package org.example.controller;
import org.example.model.Content;
import org.example.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contents")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @GetMapping
    public List<Content> getAllContents() {
        return contentService.getAllContents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Content> getContentById(@PathVariable Long id) {
        return contentService.getContentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Content createContent(@RequestBody Content content) {
        return contentService.createContent(content);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Content> updateContent(@PathVariable Long id, @RequestBody Content contentDetails) {
        return ResponseEntity.ok(contentService.updateContent(id, contentDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContent(@PathVariable Long id) {
        contentService.deleteContent(id);
        return ResponseEntity.ok().build();
    }
}
