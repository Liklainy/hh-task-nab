package ru.hh.nab.todo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class TodoResource {

  @GET
  @Path("/todo")
  @Produces(MediaType.APPLICATION_JSON)
  public TodoDto hello() {
    TodoDto todo = new TodoDto();
    todo.setId(1);
    todo.setTitle("Hello World!");
    todo.setCompleted(true);
    return todo;
  }
}
