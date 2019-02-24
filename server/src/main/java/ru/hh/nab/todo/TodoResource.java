package ru.hh.nab.todo;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/todo")
public class TodoResource {
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<TodoDto> getTodo() {
    TodoDto todo = new TodoDto();
    todo.setId(1);
    todo.setTitle("Hello World!");
    todo.setCompleted(true);
    return Arrays.asList(todo);
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public TodoDto addTodo(TodoDto todo) {
    todo.setId(1);
    return todo;
  }

  @POST
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public void updateTodo(@PathParam("id") int id, TodoDto todo) {
    
  }

  @DELETE
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public void updateTodo(@PathParam("id") int id) {
    
  }

}
