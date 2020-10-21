package de.jwiegmann.bookstore.application;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import de.jwiegmann.bookstore.domain.author.Author;
import de.jwiegmann.bookstore.domain.author.AuthorService;
import de.jwiegmann.bookstore.domain.book.Book;
import de.jwiegmann.bookstore.domain.book.BookPublisher;
import de.jwiegmann.bookstore.domain.book.BookService;
import de.jwiegmann.bookstore.domain.book.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MutationResolver implements GraphQLMutationResolver {

    @Autowired
    BookService bookService;

    @Autowired
    AuthorService authorService;

    @Autowired
    BookPublisher bookPublisher;

    public Book createBook(String name, Integer pageCount, String firstName, String lastName) {

        Author author = authorService.findOrCreateAuthor(firstName, lastName);

        Book book = Book.newBuilder()
                .withName(name)
                .withPageCount(pageCount)
                .withAuthor(author)
                .build();

        bookPublisher.fireBookCreatedEvent(book);

        return bookService.save(book);
    }

    public Boolean deleteBookById(Integer id) {

        Optional<Book> book = bookService.findById(id);

        if (book.isPresent()) {
            bookService.delete(book.get());
            return true;
        }

        throw new BookNotFoundException(id);
    }
}
