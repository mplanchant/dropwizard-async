package com.logiccache.core;

import com.logiccache.api.Author;
import com.logiccache.api.Book;

import javax.inject.Singleton;
import java.util.Arrays;
import java.util.List;

@Singleton
public class BookServiceImpl implements BookService {

    @Override
    public Book retrieveBook(String id) {
        return Book.builder()
                .id("1")
                .title("Animal Farm")
                .author(Author.builder().id("1").name("George Orwell").build())
                .build();
    }

    @Override
    public List<Book> retrieveAllBooks() {
        return Arrays.asList(
                Book.builder()
                        .id("1")
                        .title("Animal Farm")
                        .author(Author.builder().id("1").name("George Orwell").build())
                        .build(),
                Book.builder()
                        .id("2")
                        .title("For Whom the Bell Tolls")
                        .author(Author.builder().id("1").name("Ernest Hemingway").build())
                        .build(),
                Book.builder()
                        .id("3")
                        .title("The Ragged-Trousered Philanthropists")
                        .author(Author.builder().id("1").name("Robert Tressell").build())
                        .build()
        );
    }
}
