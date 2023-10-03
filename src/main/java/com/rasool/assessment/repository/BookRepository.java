package com.rasool.assessment.repository;

import com.rasool.assessment.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Rasool
 */
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String query, String query1);
}
