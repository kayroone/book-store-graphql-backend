package de.jwiegmann.bookstore.infrastructure.exception;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GraphQlExceptionHandler implements GraphQLErrorHandler {

    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> list) {

        List<GraphQLError> errors = list.stream().map(this::getNested).collect(Collectors.toList());

        return errors;
    }

    private GraphQLError getNested(GraphQLError error) {

        if (error instanceof ExceptionWhileDataFetching) {
            ExceptionWhileDataFetching exceptionError = (ExceptionWhileDataFetching) error;
            BookStoreError bookStoreError = new BookStoreError();
            bookStoreError.setMessage(exceptionError.getException().getMessage());
            bookStoreError.setPath(exceptionError.getPath());
            bookStoreError.setLocations(exceptionError.getLocations());
            bookStoreError.setExtensions(exceptionError.getExtensions());
            return bookStoreError;
        }

        return error;
    }
}
