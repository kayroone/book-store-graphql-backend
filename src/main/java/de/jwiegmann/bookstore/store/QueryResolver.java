package de.jwiegmann.bookstore.store;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import de.jwiegmann.bookstore.domain.author.Author;
import de.jwiegmann.bookstore.domain.book.Book;
import de.jwiegmann.bookstore.domain.repository.AuthorRepository;
import de.jwiegmann.bookstore.domain.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueryResolver implements GraphQLQueryResolver {

  @Autowired
  BookRepository bookRepository;

  @Autowired
  AuthorRepository authorRepository;

  public Book bookById(Integer id) {

    return bookRepository.findById(id).get();
  }

  public Author getAuthor(Book book) {

    return book.getAuthor();
  }
}
