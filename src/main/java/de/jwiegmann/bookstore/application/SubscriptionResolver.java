package de.jwiegmann.bookstore.application;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import de.jwiegmann.bookstore.domain.book.Book;
import de.jwiegmann.bookstore.domain.book.BookCreatedEvent;
import org.reactivestreams.Publisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.FluxProcessor;

@Component
public class SubscriptionResolver implements GraphQLSubscriptionResolver {

    private final FluxProcessor<Book, Book> processor = DirectProcessor.create();

    @EventListener
    public void bookCreated(BookCreatedEvent bookCreatedEvent) {

        Book createdBook = bookCreatedEvent.getBook();
        if (createdBook != null) {
            processor.onNext(createdBook);
        }
    }

    public Publisher<Book> bookCreated() {

        return processor;
    }
}
