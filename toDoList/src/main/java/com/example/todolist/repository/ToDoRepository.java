package com.example.todolist.repository;

import com.example.todolist.object.entity.ToDo;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface ToDoRepository extends MongoRepository<ToDo, String> {
}
