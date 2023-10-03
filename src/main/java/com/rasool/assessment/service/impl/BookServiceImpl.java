package com.rasool.assessment.service.impl;

import com.rasool.assessment.model.Book;
import com.rasool.assessment.model.Member;
import com.rasool.assessment.repository.BookRepository;
import com.rasool.assessment.repository.MemberRepository;
import com.rasool.assessment.service.BookService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Rasool
 */
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final MemberRepository memberRepository;

    public BookServiceImpl(BookRepository bookRepository, MemberRepository memberRepository) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    public List<Book> getAllBooks(Integer page, Integer size, String[] sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return bookRepository.findAll(pageable).getContent();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book updatedBook) {
        Book book = bookRepository.findById(id).orElse(null);

        if (book != null) {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setIsbn(updatedBook.getIsbn());
            book.setPublishedDate(updatedBook.getPublishedDate());
            book.setAvailabilityStatus(updatedBook.getAvailabilityStatus());
            return bookRepository.save(book);
        }
        return null;
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public String borrowBook(Long bookId, Long memberId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        Optional<Member> memberOptional = memberRepository.findById(memberId);

        if (bookOptional.isPresent() && memberOptional.isPresent()) {
            Book book = bookOptional.get();
            Member member = memberOptional.get();

            if ("available".equals(book.getAvailabilityStatus())) {
                book.setAvailabilityStatus("borrowed");
                book.setBorrowedBy(member.getName());

                bookRepository.save(book);

                return "Book successfully borrowed.";
            } else {
                return "Book is not available for borrowing.";
            }
        } else {
            return "Book or member not found.";
        }
    }

    public String returnBook(Long bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);

        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();

            if ("borrowed".equals(book.getAvailabilityStatus())) {
                book.setAvailabilityStatus("available");
                book.setBorrowedBy(""); // Remove the borrower
                bookRepository.save(book);

                return "Book successfully returned.";
            } else {
                return "Book is not borrowed.";
            }
        } else {
            return "Book not found.";
        }
    }

    public List<Book> searchBooks(String query) {
        // Search by title or author
        return bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(query, query);
    }
}
