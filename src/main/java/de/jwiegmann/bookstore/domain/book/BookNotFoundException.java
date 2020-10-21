package de.jwiegmann.bookstore.domain.book;

import de.jwiegmann.bookstore.infrastructure.exception.GraphQlException;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BookNotFoundException extends GraphQlException implements GraphQLError {

  private final Integer bookId;

  public BookNotFoundException(Integer bookId) {
    this.bookId = bookId;
  }

  @Override
  public String getMessage() {
    return String.format("Book with id %s not found", bookId);
  }

  @Override
  public List<SourceLocation> getLocations() {
    return null;
  }

  @Override
  public ErrorType getErrorType() {
    return null;
  }

  @Override
  public Map<String, Object> getExtensions() {
    return Collections.singletonMap("bookId", bookId);
  }
}
