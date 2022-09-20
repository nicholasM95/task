package com.example.task.todo;

import com.example.task.todo.resource.TodoWebRequestResource;
import com.example.task.utils.WithCustomUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class TodoIntegrationTest {

    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
    }

    @Transactional
    @Test
    @WithCustomUser(id = "bdbace41-9566-4a69-8d54-d388a191cab9", name = "Kris", role = "ROLE_USER")
    public void create() throws Exception {
        TodoWebRequestResource resource = new TodoWebRequestResource();
        resource.setTask("test task");

        mockMvc.perform(post("/todo")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(resource)))
                .andExpect(status().isCreated())
                .andExpect(header().string(HttpHeaders.LOCATION, startsWith("/todo")))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.task").value("test task"));
    }

    @Test
    @WithCustomUser(id = "bdbace41-9566-4a69-8d54-d388a191cab9", name = "Kris", role = "ROLE_USER")
    public void findAll() throws Exception {
        mockMvc.perform(get("/todo")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("fff8e263-d992-4b17-9e6f-f83968cb81e8"))
                .andExpect(jsonPath("$[0].task").value("My first task"))
                .andExpect(jsonPath("$[1].id").value("edf0a283-2317-4039-a173-c76065f55c22"))
                .andExpect(jsonPath("$[1].task").value("My second task"))
                .andExpect(jsonPath("$[2].id").doesNotExist());
    }

    @Test
    @WithCustomUser(id = "bdbace41-9566-4a69-8d54-d388a191cab9", name = "Kris", role = "ROLE_USER")
    public void findById() throws Exception {
        mockMvc.perform(get("/todo/fff8e263-d992-4b17-9e6f-f83968cb81e8")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("fff8e263-d992-4b17-9e6f-f83968cb81e8"))
                .andExpect(jsonPath("$.task").value("My first task"));
    }

    @Test
    @WithCustomUser(id = "bdbace41-9566-4a69-8d54-d388a191cab9", name = "Kris", role = "ROLE_USER")
    public void findByIdNotFound() throws Exception {
        mockMvc.perform(get("/todo/d823f5f8-846e-4cce-8b95-d0831099d076")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Transactional
    @Test
    @WithCustomUser(id = "bdbace41-9566-4a69-8d54-d388a191cab9", name = "Kris", role = "ROLE_USER")
    public void updateById() throws Exception {
        TodoWebRequestResource resource = new TodoWebRequestResource();
        resource.setTask("My first task - 2");

        mockMvc.perform(put("/todo/fff8e263-d992-4b17-9e6f-f83968cb81e8")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(resource)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("fff8e263-d992-4b17-9e6f-f83968cb81e8"))
                .andExpect(jsonPath("$.task").value("My first task - 2"));
    }

    @Test
    @WithCustomUser(id = "bdbace41-9566-4a69-8d54-d388a191cab9", name = "Kris", role = "ROLE_USER")
    public void updateByIdNotFound() throws Exception {
        TodoWebRequestResource resource = new TodoWebRequestResource();
        resource.setTask("My first task - 2");

        mockMvc.perform(put("/todo/d003cc55-a459-4da8-8425-1d155c3eeae7")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(resource)))
                .andExpect(status().isNotFound());
    }

    @Transactional
    @Test
    @WithCustomUser(id = "bdbace41-9566-4a69-8d54-d388a191cab9", name = "Kris", role = "ROLE_USER")
    public void deleteById() throws Exception {
        mockMvc.perform(delete("/todo/fff8e263-d992-4b17-9e6f-f83968cb81e8")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithCustomUser(id = "bdbace41-9566-4a69-8d54-d388a191cab9", name = "Kris", role = "ROLE_USER")
    public void deleteByIdNotFound() throws Exception {
        mockMvc.perform(delete("/todo/bf7e55a4-3051-42bd-aee8-fb30fa6405e1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
