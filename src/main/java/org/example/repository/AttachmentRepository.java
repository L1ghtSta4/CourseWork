package org.example.repository;

import org.example.logger.Loggable;
import org.example.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

    @Loggable
    List<Attachment> findAll();

    @Override
    @Loggable
    Optional<Attachment> findById(Long id);

    @Loggable
    void deleteById(Long id);
}
