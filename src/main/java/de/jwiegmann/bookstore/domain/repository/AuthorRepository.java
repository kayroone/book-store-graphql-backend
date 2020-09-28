package de.jwiegmann.bookstore.domain.repository;

import de.jwiegmann.bookstore.domain.author.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Integer> {

    List<Author> findByLastName(String lastName);
}
