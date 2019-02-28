package ru.hh.nab.todo;

import org.springframework.beans.BeanUtils;

import java.util.Comparator;

public class TodoService {

    private final TodoDao todoDao;

    public TodoService(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    public TodoDto[] getAll() {
        return todoDao.getAll()
                .stream()
                .map(TodoService::mapTodoToDto)
                .sorted(Comparator.comparingInt(TodoDto::getId))
                .toArray(TodoDto[]::new);
    }

    public TodoDto add(TodoDto todo) {
        Todo newTodo = mapTodoToEntity(todo);
        todoDao.saveNew(newTodo);
        return mapTodoToDto(newTodo);
    }

    public TodoDto update(TodoDto todo) {
        Todo updateTodo = mapTodoToEntity(todo);
        todoDao.update(updateTodo);
        return mapTodoToDto(updateTodo);
    }

    public void deleteById(int id) {
        todoDao.deleteById(id);
    }

    private static TodoDto mapTodoToDto(Todo todo) {
        TodoDto dto = new TodoDto();
        BeanUtils.copyProperties(todo, dto);
        return dto;
    }

    private static Todo mapTodoToEntity(TodoDto dto) {
        Todo todo = new Todo();
        BeanUtils.copyProperties(dto, todo);
        return todo;
    }
}