package de.jwiegmann.bookstore.store;

import de.jwiegmann.bookstore.domain.book.Book;
import de.jwiegmann.bookstore.domain.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Book save(Book book) {

        return bookRepository.save(book);
    }

    public void delete(Book book) {

        bookRepository.delete(book);
    }

    public Optional<Book> findById(Integer id) {

        return bookRepository.findById(id);
    }
}


