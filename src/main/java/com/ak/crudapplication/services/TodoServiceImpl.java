package com.ak.crudapplication.services;

import com.ak.crudapplication.exception.TodoNotFoundException;
import com.ak.crudapplication.model.Todo;
import com.ak.crudapplication.model.TodoStatus;
import com.ak.crudapplication.repositories.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TodoServiceImpl implements TodoService{
    private final TodoRepository todoRepository;
    public TodoServiceImpl(TodoRepository todoRepository){
        this.todoRepository=todoRepository;
    }

    @Override
    public List<Todo> getTodos() {
        List<Todo> todos = new ArrayList<>();
       todoRepository.findAll().forEach(todos::add);
        return todos;
    }

    @Override
    public Todo getTodoById(Long id) throws TodoNotFoundException {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if(todoOptional.isEmpty()){
            throw new TodoNotFoundException("Todo not present of ID: "+id);

        }
        return todoOptional.get();
    }

    @Override
    public Todo insert(Todo todo) {
        Todo saveTodo = todoRepository.save(todo);
        return saveTodo;
    }

    @Override
    public void updateTodo(Long id, Todo todo) throws TodoNotFoundException {
        Optional<Todo> todoFromDbOptional = todoRepository.findById(id);
        if(todoFromDbOptional.isEmpty()){
            throw new TodoNotFoundException("Todo is not present for a given ID: "+id);
        }
        Todo todoFromDb = todoFromDbOptional.get();
        log.info(todoFromDb.toString());
        todoFromDb.setTodoStatus(todo.getTodoStatus());
        todoFromDb.setDescription(todo.getDescription());
        todoFromDb.setTitle(todo.getTitle());
        todoRepository.save(todoFromDb);
    }

    @Override
    public void deleteTodo(Long todoId) {
        todoRepository.deleteById(todoId);
    }

    public Todo getTodoByBuilder(){
        return Todo.builder().todoStatus(TodoStatus.COMPLETED).id(4L).description("This is demo todo").title("Demo todo").build();
    }
    public List<Todo> getTodoListByBuilder(){
        List<Todo> todosList=new ArrayList<>();
        Todo todo1 = Todo.builder().todoStatus(TodoStatus.NOT_COMPLETED).id(5L).description("This is todo 5").title("todo 5").build();
        todosList.add(todo1);
        Todo todo2 = Todo.builder().todoStatus(TodoStatus.NOT_COMPLETED).id(6L).description("This is todo 6").title("todo 6").build();
        todosList.add(todo2);
        return todosList;
    }

    public List<Todo> getTodoWithoutBuilder(){
        List<Todo> todoList=new ArrayList<>();
        Todo todo=new Todo(8,"Todo 8","This is todo 8",TodoStatus.NOT_COMPLETED, ZonedDateTime.now(),ZonedDateTime.now());
        todoList.add(todo);
        return todoList;

    }
}
