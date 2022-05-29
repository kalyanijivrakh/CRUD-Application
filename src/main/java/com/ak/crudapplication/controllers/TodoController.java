package com.ak.crudapplication.controllers;

import com.ak.crudapplication.exception.TodoNotFoundException;
import com.ak.crudapplication.model.Todo;
import com.ak.crudapplication.services.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {
    private final TodoService todoService;
    public TodoController(TodoService todoService){
        this.todoService=todoService;
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodo(){
        List<Todo> todosList = todoService.getTodos();
        return new ResponseEntity<>(todosList, HttpStatus.OK);

    }

    @GetMapping({"/{todoId}"})
    public ResponseEntity<Todo> getTodo(@PathVariable Long todoId) throws TodoNotFoundException {
        Todo todo = todoService.getTodoById(todoId);
        return new ResponseEntity<>(todo,HttpStatus.OK);
    }

    @DeleteMapping({"/{todoId}"})
    public ResponseEntity<Todo> deleteById(@PathVariable("todoId") Long todoId){
        todoService.deleteTodo(todoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<Todo> saveTodo(@RequestBody Todo todo){
        Todo saveTodo = todoService.insert(todo);
        return new ResponseEntity<>(saveTodo,HttpStatus.OK);
    }

    @PutMapping({"/{todoId}"})
    public ResponseEntity<Todo> updateTodo(@RequestBody Todo todo, @PathVariable("todoId") Long todoId) throws TodoNotFoundException {
        todoService.updateTodo(todoId,todo);
        return new ResponseEntity<>(todo,HttpStatus.OK);
    }
}
