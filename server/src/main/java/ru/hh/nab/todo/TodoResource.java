package ru.hh.nab.todo;

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

  private final TodoService todoService;

  public TodoResource(TodoService todoService) {
    this.todoService = todoService;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public TodoDto[] getTodo() {
    return todoService.getAll()
      .stream()
      .map(TodoResource::mapTodoToDto)
      .sorted((f1, f2) -> Integer.compare(f1.getId(), f2.getId()))
      .toArray(TodoDto[]::new);
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public TodoDto putTodo(TodoDto todo) {
    var reqTodo = mapTodoToEntity(todo);
    var newTodo = todoService.add(reqTodo);
    return mapTodoToDto(newTodo);
  }

  @POST
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public TodoDto updateTodo(@PathParam("id") int id, TodoDto todo) {
    var reqTodo = mapTodoToEntity(todo);
    var newTodo = todoService.update(reqTodo);
    return mapTodoToDto(newTodo);
  }

  @DELETE
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public void deleteTodo(@PathParam("id") int id) {
    todoService.deleteById(id);
  }

  private static TodoDto mapTodoToDto(Todo todo) {
    TodoDto dto = new TodoDto();
    dto.setId(todo.getId());
    dto.setTitle(todo.getTitle());
    dto.setComplete(todo.getComplete());
    return dto;
  }

  private static Todo mapTodoToEntity(TodoDto dto) {
    Todo todo = new Todo();
    todo.setId(dto.getId());
    todo.setTitle(dto.getTitle());
    todo.setComplete(dto.getComplete());
    return todo;
  }
}
