package com.example.todolist.service;

import com.example.todolist.object.entity.ToDo;
import com.example.todolist.repository.ToDoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class ToDoServiceTest {

    @Mock
    ToDoRepository toDoRepository;

    @InjectMocks
    ToDoService toDoService;

    @BeforeEach
    void cleanRepository(){
        toDoRepository.deleteAll();
    }

    @Test
    void should_return_todos_when_getTodos_given() {

        //given
        ToDo toDo = new ToDo("1", "text 1", false);
        toDoService.insertToDo(toDo);
        List<ToDo> expected = Arrays.asList(toDo);
        given(toDoRepository.findAll())
                .willReturn(expected);

        //when
        List<ToDo> actual = toDoService.getTodos();

        //then
        verify(toDoRepository).findAll();
        assertEquals(actual, expected);

    }

    @Test
    void should_return_nothing_when_insertToDo_given_todo() {

        //given
        ToDo toDo = new ToDo("1", "text 1", false);

        //when
        toDoService.insertToDo(toDo);

        //then
        verify(toDoRepository).insert(toDo);

    }

    @Test
    void should_return_nothing_when_deleteById_given_todo() {

        //given
        ToDo toDo = new ToDo("1", "text 1", false);
        String id = toDo.getId();

        //when
        toDoService.deleteToDo(id);

        //then
        verify(toDoRepository).deleteById(id);

    }

    @Test
    void should_return_nothing_when_updateToDoDone_given_todo_with_done() {

        //given
        ToDo toDo = new ToDo("1", null, false);
        String id = toDo.getId();
        toDoRepository.insert(toDo);
        given(toDoRepository.findById(id))
                .willReturn(java.util.Optional.of(toDo));

        //when
        toDoService.updateToDoDone(id, toDo);

        //then
        verify(toDoRepository).save(toDo);
        verify(toDoRepository).findById(id);

    }

    @Test
    void should_return_nothing_when_updateToDoDone_given_todo_with_text() {

        //given
        ToDo toDo = new ToDo("1", "text 1", null);
        String id = toDo.getId();
        toDoRepository.insert(toDo);
        given(toDoRepository.findById(id))
                .willReturn(java.util.Optional.of(toDo));

        //when
        toDoService.updateToDoDone(id, toDo);

        //then
        verify(toDoRepository).save(toDo);
        verify(toDoRepository).findById(id);

    }

}
