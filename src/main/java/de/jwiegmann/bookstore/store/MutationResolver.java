package de.jwiegmann.bookstore.store;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import de.jwiegmann.bookstore.domain.author.Author;
import de.jwiegmann.bookstore.domain.book.Book;
import de.jwiegmann.bookstore.domain.repository.AuthorRepository;
import de.jwiegmann.bookstore.domain.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MutationResolver implements GraphQLMutationResolver {

  @Autowired
  BookRepository bookRepository;

  @Autowired
  AuthorRepository authorRepository;

  public Book createBook(String name, Integer pageCount, String firstName, String lastName) {

    Author author = authorRepository.save(
            Author.newBuilder()
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .build()
    );

    Book book = Book.newBuilder()
            .withName(name)
            .withPageCount(pageCount)
            .withAuthor(author)
            .build();

    return bookRepository.save(book);
  }
}
