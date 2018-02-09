package com.logiccache.resources;

import com.codahale.metrics.annotation.Timed;
import com.logiccache.api.Book;
import com.logiccache.core.BookService;
import jersey.repackaged.com.google.common.util.concurrent.SettableFuture;
import org.glassfish.jersey.server.ManagedAsync;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

@Path("book")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    private final ExecutorService executorService;
    private final BookService bookService;
    private final static Logger LOGGER = LoggerFactory.getLogger(BookResource.class);

    @Inject
    public BookResource(ExecutorService executorService, BookService bookService) {
        this.executorService = executorService;
        this.bookService = bookService;
    }

    @GET
    @Timed
    @Path("/sync")
    public List<Book> sync() throws InterruptedException {
        LOGGER.info("sync {}", Thread.currentThread().getId());
        Thread.sleep(50);
        return bookService.retrieveAllBooks();
    }

    @ManagedAsync
    @Timed
    @GET
    @Path("/async")
    public void async(@Suspended final AsyncResponse asyncResponse) throws InterruptedException {
        LOGGER.info("async {}", Thread.currentThread().getId());
        Thread.sleep(50);
        asyncResponse.resume(bookService.retrieveAllBooks());
    }

    @GET
    @Path("/async_future")
    @Timed
    public void asyncFuture(@Suspended final AsyncResponse asyncResponse) {
        LOGGER.info("async_future {}", Thread.currentThread().getId());
        CompletableFuture<List<Book>> completableFuture = new CompletableFuture<>();
        executorService.submit(() -> {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            completableFuture.complete(bookService.retrieveAllBooks());
        });
        completableFuture.thenAcceptAsync(asyncResponse::resume);
    }

    @GET
    @Path("/sync_future")
    @Timed
    public List<Book> syncFuture() throws ExecutionException, InterruptedException {
        LOGGER.info("sync_future {}", Thread.currentThread().getId());
        SettableFuture<List<Book>> settableFuture = SettableFuture.create();
        executorService.submit(() -> {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            settableFuture.set(bookService.retrieveAllBooks());
        });
        return settableFuture.get();
    }
}
