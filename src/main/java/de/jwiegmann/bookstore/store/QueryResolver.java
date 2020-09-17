package de.jwiegmann.bookstore.store;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import de.jwiegmann.bookstore.domain.author.Author;
import de.jwiegmann.bookstore.domain.book.Book;
import de.jwiegmann.bookstore.domain.repository.AuthorRepository;
import de.jwiegmann.bookstore.domain.repository.BookRepository;
import de.jwiegmann.bookstore.infrastructure.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

  public Author getAuthor(Book book) {

    return book.getAuthor();
  }
}
