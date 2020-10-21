package de.jwiegmann.bookstore.application;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.collect.Lists;
import de.jwiegmann.bookstore.domain.author.Author;
import de.jwiegmann.bookstore.domain.book.Book;
import de.jwiegmann.bookstore.domain.author.AuthorRepository;
import de.jwiegmann.bookstore.domain.book.BookRepository;
import de.jwiegmann.bookstore.domain.book.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class QueryResolver implements GraphQLQueryResolver {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    public Book bookById(Integer id) {

        Optional<Book> book = bookRepository.findById(id);

        if (book.isPresent()) {
            return book.get();
        }

        throw new BookNotFoundException(id);
    }

    public List<Book> getAllBooks() {

        return Lists.newArrayList(bookRepository.findAll());
    }

    public Author getAuthor(Book book) {

        return book.getAuthor();
    }
}
