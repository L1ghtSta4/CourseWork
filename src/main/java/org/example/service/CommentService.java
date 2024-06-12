package org.example.service;


import org.example.logger.Loggable;
import org.example.model.Comment;
import org.example.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Loggable
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Loggable
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
    }

    @Loggable
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Loggable
    public Comment updateComment(Long id, Comment commentDetails) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        comment.setAuthor(commentDetails.getAuthor());
        comment.setText(commentDetails.getText());
        comment.setCreatedAt(commentDetails.getCreatedAt());
        comment.setContent(commentDetails.getContent());
        return commentRepository.save(comment);
    }

    @Loggable
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        commentRepository.delete(comment);
    }
}

