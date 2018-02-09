package com.logiccache.module;

import com.logiccache.core.BookService;
import com.logiccache.core.BookServiceImpl;
import dagger.Module;
import dagger.Provides;

@Module
public class BookModule {
    @Provides
    BookService provideBookService() {
        return new BookServiceImpl();
    }
}
