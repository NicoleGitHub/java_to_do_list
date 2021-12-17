package com.example.todolist.service;

import com.example.todolist.exception.NoToDoFoundException;
import com.example.todolist.object.entity.ToDo;
import com.example.todolist.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {

    private ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public List<ToDo> getTodos() {
        return toDoRepository.findAll();
    }

    public void insertToDo(ToDo toDo) {
        toDoRepository.insert(toDo);
    }

    public void deleteToDo(String id) {
        toDoRepository.deleteById(id);
    }

    public void updateToDoDone(String id, ToDo toDo) {
        ToDo oldToDo = toDoRepository.findById(id).orElseThrow(NoToDoFoundException::new);
        if(toDo.getText() != null){
            oldToDo.setText(toDo.getText());
        }
        if(oldToDo.isDone() != null) {
            oldToDo.setDone(!toDo.isDone());
        }
        toDoRepository.save(oldToDo);
    }

    public void clearAll() {
        toDoRepository.deleteAll();
    }
}
