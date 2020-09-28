package de.jwiegmann.bookstore.domain.book;

import de.jwiegmann.bookstore.domain.author.Author;
import de.jwiegmann.bookstore.domain.builder.DefaultBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String name;
  private Integer pageCount;

  @ManyToOne
  private Author author;

  protected Book() {
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Integer getPageCount() {
    return pageCount;
  }

  public Author getAuthor() {
    return author;
  }

  public static BookBuilder newBuilder() {
    return new BookBuilder();
  }

  public static class BookBuilder extends DefaultBuilder<Book> {

    public BookBuilder() {
      super();
    }

    public BookBuilder withId(Integer id) {
      this.instance.id = id;
      return this;
    }

    public BookBuilder withName(String name) {
      this.instance.name = name;
      return this;
    }

    public BookBuilder withPageCount(Integer pageCount) {
      this.instance.pageCount = pageCount;
      return this;
    }

    public BookBuilder withAuthor(Author author) {
      this.instance.author = author;
      return this;
    }
  }
}
