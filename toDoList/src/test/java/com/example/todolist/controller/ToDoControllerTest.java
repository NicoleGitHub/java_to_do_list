package com.example.todolist.controller;

import com.example.todolist.object.entity.ToDo;
import com.example.todolist.service.ToDoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ToDoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ToDoService toDoService;

    @BeforeEach
    void cleanRepository(){
        toDoService.clearAll();
    }

    @Test
    void should_return_todos_when_get_all_given() throws Exception {

        //given
        ToDo toDo = new ToDo("61bbf9d9692c4f32f2f336b2", "text 1", false);
        toDoService.insertToDo(toDo);

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[*].text").value(toDo.getText()))
                .andExpect(jsonPath("$[*].done").value(toDo.isDone()));

    }

    @Test
    void should_when_post_todo_given_todo() throws Exception {

        //given
        String todo = "    {\n" +
                "        \"id\": \"61bbf9d9692c4f32f2f336b2\",\n" +
                "        \"text\": \"text 1\",\n" +
                "        \"done\": \"false\"\n" +
                "    }";

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(todo))
                .andExpect(status().isCreated());

    }

    @Test
    void should_when_delete_todo_given_id() throws Exception {

        //given
        ToDo toDo = new ToDo("61bbf9d9692c4f32f2f336b2", "text 1", false);
        toDoService.insertToDo(toDo);

        //when
        mockMvc.perform(MockMvcRequestBuilders.delete("/todos/" + toDo.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        //then
        assertEquals(0, toDoService.getTodos().size());

    }

    @Test
    void should_when_update_todo_given_todo_with_text() throws Exception {

        //given
        ToDo toDo = new ToDo("61bbf9d9692c4f32f2f336b2", "text 1", null);
        toDoService.insertToDo(toDo);
        String todo = "    {\n" +
                "        \"id\": \"61bbf9d9692c4f32f2f336b2\",\n" +
                "        \"text\": \"text 1\",\n" +
                "        \"done\": \"false\"\n" +
                "    }";

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.put("/todos/" + toDo.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(todo))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void should_when_update_todo_given_todo_with_done() throws Exception {

        //given
        ToDo toDo = new ToDo("61bbf9d9692c4f32f2f336b2", null, false);
        toDoService.insertToDo(toDo);
        String todo = "    {\n" +
                "        \"id\": \"61bbf9d9692c4f32f2f336b2\",\n" +
                "        \"text\": \"text 1\",\n" +
                "        \"done\": \"true\"\n" +
                "    }";

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.put("/todos/" + toDo.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(todo))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

}
