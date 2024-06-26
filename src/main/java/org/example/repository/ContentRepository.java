package org.example.repository;
import org.example.logger.Loggable;
import org.example.model.Content;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface ContentRepository extends JpaRepository<Content, Long> {

    @Loggable
    @Override
    List<Content> findAll();


    @Loggable
    @Override
    Optional<Content> findById(Long aLong);
}
