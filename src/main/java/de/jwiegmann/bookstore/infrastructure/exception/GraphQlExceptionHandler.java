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
            StoreError storeError = new StoreError();
            storeError.setMessage(exceptionError.getException().getMessage());
            storeError.setPath(exceptionError.getPath());
            storeError.setLocations(exceptionError.getLocations());
            storeError.setExtensions(exceptionError.getExtensions());
            return storeError;
        }

        return error;
    }
}
