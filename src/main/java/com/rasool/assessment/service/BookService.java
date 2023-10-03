package com.rasool.assessment.service;

import com.rasool.assessment.model.Book;

import java.util.List;

/**
 * @author Rasool
 */
public interface BookService {

    List<Book> getAllBooks(Integer page, Integer size, String[] sort);
    Book getBookById(Long id);

    Book createBook(Book book);

    Book updateBook(Long id, Book updatedBook);

    void deleteBook(Long id);

    String borrowBook(Long bookId, Long memberId);

    String returnBook(Long bookId);

    List<Book> searchBooks(String query);
}
