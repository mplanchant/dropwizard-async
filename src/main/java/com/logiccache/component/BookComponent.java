package com.logiccache.component;

import com.logiccache.module.BookModule;
import com.logiccache.module.ExecutorModule;
import com.logiccache.resources.BookResource;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {BookModule.class, ExecutorModule.class})
public interface BookComponent {
    BookResource inject();
}