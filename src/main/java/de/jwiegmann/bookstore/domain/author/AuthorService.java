package de.jwiegmann.bookstore.domain.author;

import de.jwiegmann.bookstore.domain.author.Author;
import de.jwiegmann.bookstore.domain.author.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public Author findOrCreateAuthor(String firstName, String lastName) {

        List<Author> authors = authorRepository.findByLastName(lastName);

        if (authors.size() > 0) {
            Optional<Author> existingAuthor = authors.stream()
                    .filter(a -> a.getLastName().equals(lastName) && a.getFirstName().equals(firstName))
                    .findFirst();
            if (existingAuthor.isPresent()) {
                return existingAuthor.get();
            }
        }

        return authorRepository.save(Author.newBuilder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .build()
        );
    }
}
