package de.jwiegmann.bookstore.infrastructure.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.List;
import java.util.Map;

public class StoreError implements GraphQLError {

  private String message;
  private List<Object> path;
  private List<SourceLocation> locations;
  private Map<String, Object> extensions;

  public void setMessage(String message) {
    this.message = message;
  }

  public void setExtensions(Map<String, Object> extensions) {
    this.extensions = extensions;
  }

  public void setPath(List<Object> path) {
    this.path = path;
  }

  public void setLocations(List<SourceLocation> locations) {
    this.locations = locations;
  }

  @Override
  public String getMessage() {
    return message;
  }

  @Override
  public List<SourceLocation> getLocations() {
    return locations;
  }

  @Override
  @JsonIgnore
  public ErrorType getErrorType() {
    return null;
  }

  @Override
  public List<Object> getPath() {
    return path;
  }

  @Override
  public Map<String, Object> getExtensions() {
    return extensions;
  }
}
