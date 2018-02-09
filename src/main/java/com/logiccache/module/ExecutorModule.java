package com.logiccache.module;

import dagger.Module;
import dagger.Provides;

import java.util.concurrent.ExecutorService;

@Module
public class ExecutorModule {

    private final ExecutorService service;

    public ExecutorModule(ExecutorService service) {
        this.service = service;
    }

    @Provides
    ExecutorService provideExecutorService() {
        return service;
    }
}
