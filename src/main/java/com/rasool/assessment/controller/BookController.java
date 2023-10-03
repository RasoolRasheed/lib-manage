package com.rasool.assessment.controller;

import com.rasool.assessment.model.Book;
import com.rasool.assessment.payload.common.ApiResponse;
import com.rasool.assessment.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Rasool
 */
@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public Book createBook(@Valid @RequestBody Book book) {
        return bookService.createBook(book);
    }

    @GetMapping
    public List<Book> getAllBooks(@RequestParam(defaultValue = "0") Integer page,
                                  @RequestParam(defaultValue = "10") Integer size,
                                  @RequestParam(defaultValue = "id,asc") String[] sort){
        return bookService.getAllBooks(page, size, sort);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @Valid @RequestBody Book updatedBook) {
        return bookService.updateBook(id, updatedBook);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ApiResponse<>(HttpStatus.OK,"Book successfully deleted.", true,null);
    }

    @PostMapping("/{bookId}/borrow/{memberId}")
    public ApiResponse<String> borrowBook(@PathVariable Long bookId, @PathVariable Long memberId) {
        String result = bookService.borrowBook(bookId, memberId);
        return new ApiResponse<>(HttpStatus.OK,HttpStatus.OK.getReasonPhrase(), true,result);
    }

    @PostMapping("/{bookId}/return")
    public ApiResponse<String> returnBook(@PathVariable Long bookId) {
        String result = bookService.returnBook(bookId);
        return new ApiResponse<>(HttpStatus.OK,HttpStatus.OK.getReasonPhrase(), true,result);
    }

    @GetMapping("/search")
    public ApiResponse<List<Book>> searchBooks(@RequestParam(name = "query") String query) {
        List<Book> foundBooks = bookService.searchBooks(query);
        return new ApiResponse<>(HttpStatus.OK,HttpStatus.OK.getReasonPhrase(), true,foundBooks);
    }
}