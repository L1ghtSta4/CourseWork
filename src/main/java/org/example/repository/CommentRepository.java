package org.example.repository;

import org.example.logger.Loggable;
import org.example.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Loggable
    @Override
    List<Comment> findAll();

    @Loggable
    @Override
    Optional<Comment> findById(Long aLong);
}
