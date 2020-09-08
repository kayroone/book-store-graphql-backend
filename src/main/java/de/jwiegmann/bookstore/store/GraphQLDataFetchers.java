package de.jwiegmann.bookstore.store;

import de.jwiegmann.bookstore.domain.book.Book;
import de.jwiegmann.bookstore.domain.repository.AuthorRepository;
import de.jwiegmann.bookstore.domain.repository.BookRepository;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GraphQLDataFetchers {

  @Autowired
  BookRepository bookRepository;

  @Autowired
  AuthorRepository authorRepository;

  public DataFetcher getBookByIdDataFetcher() {

    return dataFetchingEnvironment -> {
      String id = dataFetchingEnvironment.getArgument("id");
      Integer bookId = Integer.valueOf(id);
      return bookRepository.findById(bookId).orElse(null);
    };
  }

  public DataFetcher getAuthorDataFetcher() {

    return dataFetchingEnvironment -> {
      Book book = dataFetchingEnvironment.getSource();
      return authorRepository.findById(book.getAuthorId()).orElse(null);
    };
  }
}
