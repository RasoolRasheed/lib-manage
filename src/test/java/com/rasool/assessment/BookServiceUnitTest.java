package com.rasool.assessment;

import com.rasool.assessment.model.Book;
import com.rasool.assessment.repository.BookRepository;
import com.rasool.assessment.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Rasool Rasheed
 */
class BookServiceUnitTest {
    @InjectMocks
    private BookServiceImpl bookServiceImpl;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBooks() {
        // Mock the repository call
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L,"Book1", "Author1", "123", null, "available", null));
        when(bookRepository.findAll()).thenReturn(books);

        String[] x = {"id"};
        // Call the service
        List<Book> result = bookServiceImpl.getAllBooks(0,10,x);

        // Verify the result
        assertEquals(1, result.size());
        assertEquals("Book1", result.get(0).getTitle());
    }
}
