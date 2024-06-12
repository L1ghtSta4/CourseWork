package org.example.controller;

import org.example.DTO.CommentDTO;
import org.example.logger.Loggable;
import org.example.model.Comment;
import org.example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;


    private CommentDTO convertToDTO(Comment comment) {
        if (comment == null) {
            return null;
        }
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setAuthor(comment.getAuthor()); // assuming Comment has a User author
        commentDTO.setText(comment.getText());
        commentDTO.setCreatedAt(comment.getCreatedAt());
        return commentDTO;
    }

    @Loggable
    @GetMapping
    public List<CommentDTO> getAllComments() {
        return commentService.getAllComments().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Loggable
    @GetMapping("/{id}")
    public CommentDTO getCommentById(@PathVariable Long id) {
        return convertToDTO(commentService.getCommentById(id));
    }


    @Loggable
    @PostMapping
    public Comment createComment(@RequestBody Comment comment) {
        return commentService.createComment(comment);
    }

    @Loggable
    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable Long id, @RequestBody Comment commentDetails) {
        return commentService.updateComment(id, commentDetails);
    }

    @Loggable
    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}
