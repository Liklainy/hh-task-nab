package ru.hh.nab.todo;

import javax.ws.rs.*;
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
        return todoService.getAll();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TodoDto putTodo(TodoDto todo) {
        return todoService.add(todo);
    }

    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public TodoDto updateTodo(@PathParam("id") int id, TodoDto todo) {
        return todoService.update(todo);
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteTodo(@PathParam("id") int id) {
        todoService.deleteById(id);
    }
}
