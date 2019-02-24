package ru.hh.nab.todo;

import java.util.ArrayList;
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
  
  private List<TodoDto> todos = new ArrayList<TodoDto>();
  private int id = 0;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<TodoDto> getTodo() {
    return todos;
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public TodoDto putTodo(TodoDto todo) {
    todo.setId(++id);
    todos.add(todo);
    return todo;
  }

  @POST
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public TodoDto updateTodo(@PathParam("id") int id, TodoDto todo) {
    todos.replaceAll(x -> x.getId() == id ? todo : x);
    return todo;
  }

  @DELETE
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public void deleteTodo(@PathParam("id") int id) {
    todos.removeIf(x -> x.getId() == id);
  }

}
