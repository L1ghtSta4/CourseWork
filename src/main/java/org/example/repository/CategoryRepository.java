package org.example.repository;

import org.example.logger.Loggable;
import org.example.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Loggable
    @Override
    List<Category> findAll();

    @Loggable
    @Override
    Optional<Category> findById(Long aLong);
}
