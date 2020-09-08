package de.jwiegmann.bookstore;

import de.jwiegmann.bookstore.domain.author.Author;
import de.jwiegmann.bookstore.domain.book.Book;
import de.jwiegmann.bookstore.domain.repository.AuthorRepository;
import de.jwiegmann.bookstore.domain.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GraphQlBookStoreApplication {

  public static void main(String[] args) {
    SpringApplication.run(GraphQlBookStoreApplication.class, args);
  }

  private static final Logger LOG = LoggerFactory.getLogger(GraphQlBookStoreApplication.class);

  @Bean
  public CommandLineRunner bookDemo(BookRepository bookRepository) {

    return (args) -> {
      bookRepository.save(
              Book.newBuilder()
                      .withName("Warbreaker")
                      .withPageCount(340)
                      .withAuthorId(3)
                      .build());
      bookRepository.save(
              Book.newBuilder()
                      .withName("In the Name of the Wind")
                      .withPageCount(840)
                      .withAuthorId(4)
                      .build());
    };
  }

  @Bean
  public CommandLineRunner authorDemo(AuthorRepository authorRepository) {

    return (args) -> {
      authorRepository.save(
              Author.newBuilder()
                      .withFirstName("Brandon")
                      .withLastName("Sanderson")
                      .build()
      );
      authorRepository.save(
              Author.newBuilder()
                      .withFirstName("Patrick")
                      .withLastName("Rothfuss")
                      .build()
      );
    };
  }
}
