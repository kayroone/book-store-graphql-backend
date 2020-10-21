package de.jwiegmann.bookstore.domain.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class BookPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void fireBookCreatedEvent(final Book book) {

        BookCreatedEvent bookCreatedEvent = new BookCreatedEvent(this, book);
        applicationEventPublisher.publishEvent(bookCreatedEvent);
    }
}
