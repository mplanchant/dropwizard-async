package com.logiccache.resources;

import com.codahale.metrics.annotation.Timed;
import com.logiccache.api.Author;
import com.logiccache.api.Book;
import org.glassfish.jersey.server.ManagedAsync;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;

@Path("book")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    @Path("/{id}")
    @Timed
    @GET
    public Book book(@PathParam("id") String id) {
        return Book.builder().id("1").author(Author.builder().id("1").name("George Orwell").build()).title("Animal Farm").build();
    }

    @ManagedAsync
    @Timed
    @GET
    public void books(@Suspended final AsyncResponse asyncResponse) {
        Book animalFarm = Book.builder().id("1").author(Author.builder().id("1").name("George Orwell").build()).title("Animal Farm").build();
        Book forWhomTheBellTolls = Book.builder().id("2").author(Author.builder().id("2").name("Ernest Hemingway").build()).title("For Whom the Bell Tolls").build();
        Book money = Book.builder().id("3").author(Author.builder().id("3").name("Martin Amis").build()).title("Money").build();
        asyncResponse.resume(Arrays.asList(animalFarm, forWhomTheBellTolls, money));
    }


}
