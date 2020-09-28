package de.jwiegmann.bookstore.domain.author;

import de.jwiegmann.bookstore.domain.builder.DefaultBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Author {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String firstName;
  private String lastName;

  public Integer getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public static AuthorBuilder newBuilder() {
    return new AuthorBuilder();
  }

  public static class AuthorBuilder extends DefaultBuilder<Author> {

    public AuthorBuilder() {
      super();
    }

    public AuthorBuilder withId(Integer id) {
      this.instance.id = id;
      return this;
    }

    public AuthorBuilder withFirstName(String firstName) {
      this.instance.firstName = firstName;
      return this;
    }

    public AuthorBuilder withLastName(String lastName) {
      this.instance.lastName = lastName;
      return this;
    }
  }
}
