package com.logiccache.core;

import com.logiccache.api.Book;

import java.util.List;

public interface BookService {
    Book retrieveBook(String id);

    List<Book> retrieveAllBooks();
}
