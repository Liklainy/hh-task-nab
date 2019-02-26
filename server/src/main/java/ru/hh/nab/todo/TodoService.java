package ru.hh.nab.todo;

import java.util.List;

public class TodoService {

    private final TodoDao todoDao;

    public TodoService(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    public List<Todo> getAll() {
        return todoDao.getAll();
    }

    public Todo add(Todo todo) {
        todoDao.saveNew(todo);
        return todo;
    }

    public Todo update(Todo todo) {
        todoDao.update(todo);
        return todo;
    }

    public void deleteById(int id) {
        todoDao.deleteById(id);
    }
}