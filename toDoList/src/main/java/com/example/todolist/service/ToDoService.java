package com.example.todolist.service;

import com.example.todolist.exception.NoToDoFoundException;
import com.example.todolist.object.entity.ToDo;
import com.example.todolist.repository.ToDoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {

    @Autowired
    ToDoRepository toDoRepository;

    public List<ToDo> getTodos() {
        return toDoRepository.findAll();
    }

    public ToDo insertToDo(ToDo toDo) {
       return toDoRepository.insert(toDo);
    }

    public List<ToDo> deleteToDo(String id) {
        toDoRepository.deleteById(id);
        return getTodos();
    }

    public List<ToDo> updateToDoDone(String id, ToDo toDo) {
        ToDo oldToDo = toDoRepository.findById(id).orElseThrow(NoToDoFoundException::new);
        if(!toDo.getText().isEmpty()){
            oldToDo.setText(toDo.getText());
        }
        oldToDo.setDone(toDo.isDone());
        toDoRepository.save(oldToDo);
        return getTodos();
    }

}
