package com.example.todolist.controller;

import com.example.todolist.object.entity.ToDo;
import com.example.todolist.service.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@CrossOrigin(origins = "http://localhost:3000")
public class ToDoController {

    private ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping
    public List<ToDo> getTodos() { return toDoService.getTodos(); }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addCompany(@RequestBody ToDo toDo) {
        toDoService.insertToDo(toDo);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable String id) {
        toDoService.deleteToDo(id);
    }

    @PutMapping("/{id}")
    public void updateCompany(@PathVariable String id, @RequestBody ToDo toDo) {
        toDoService.updateToDoDone(id, toDo);
    }

}
