package de.jwiegmann.bookstore;

import de.jwiegmann.bookstore.domain.author.Author;
import de.jwiegmann.bookstore.domain.book.Book;
import de.jwiegmann.bookstore.domain.author.AuthorRepository;
import de.jwiegmann.bookstore.domain.book.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GraphQlBookStoreApplication {

  public static void main(String[] args) {
    SpringApplication.run(GraphQlBookStoreApplication.class, args);
  }

  @Bean
  public CommandLineRunner bookDemo(AuthorRepository authorRepository, BookRepository bookRepository) {

    return (args) -> {

      Author brandon = authorRepository.save(
              Author.newBuilder()
                      .withFirstName("Brandon")
                      .withLastName("Sanderson")
                      .build()
      );
      Author patrick = authorRepository.save(
              Author.newBuilder()
                      .withFirstName("Patrick")
                      .withLastName("Rothfuss")
                      .build()
      );

      bookRepository.save(
              Book.newBuilder()
                      .withName("Warbreaker")
                      .withPageCount(340)
                      .withAuthor(brandon)
                      .build()
      );
      bookRepository.save(
              Book.newBuilder()
                      .withName("In the Name of the Wind")
                      .withPageCount(840)
                      .withAuthor(patrick)
                      .build()
      );
    };
  }
}
