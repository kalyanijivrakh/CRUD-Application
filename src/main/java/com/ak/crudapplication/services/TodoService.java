package com.ak.crudapplication.services;

import com.ak.crudapplication.exception.TodoNotFoundException;
import com.ak.crudapplication.model.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> getTodos();

    Todo getTodoById(Long id) throws TodoNotFoundException;

    Todo insert(Todo todo);

    void updateTodo(Long id, Todo todo) throws TodoNotFoundException;

    void deleteTodo(Long todoId);
}
