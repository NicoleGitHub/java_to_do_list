package com.example.todolist.controller;

import com.example.todolist.object.entity.ToDo;
import com.example.todolist.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@CrossOrigin(origins = "http://localhost:3000")
public class ToDoController {

    @Autowired
    ToDoService toDoService;

    @GetMapping
    public List<ToDo> getTodos() { return toDoService.getTodos(); }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ToDo addCompany(@RequestBody ToDo toDo) {
        return toDoService.insertToDo(toDo);
    }

    @DeleteMapping("/{id}")
    public List<ToDo> deleteEmployee(@PathVariable String id) {
        return toDoService.deleteToDo(id);
    }

    @PutMapping("/{id}")
    public List<ToDo> updateCompany(@PathVariable String id, @RequestBody ToDo toDo) {
        return toDoService.updateToDoDone(id, toDo);
    }



}
