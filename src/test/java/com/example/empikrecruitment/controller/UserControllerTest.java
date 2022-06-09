package com.example.empikrecruitment.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private UserController userController;

    @Test
    public void contextLoads(){
        assertNotNull(userController);
    }

    @Test
    public void shouldNotAllowPostMethod() throws Exception{
        this.mockMvc.perform(post("/users/octocat"))
                .andDo(print())
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void shouldNotFoundMethod() throws Exception{
        this.mockMvc.perform(post("/userss"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldAcceptGetMethod() throws Exception{
        this.mockMvc.perform(get("/users/octocat"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldNotFoundUser() throws Exception{
        this.mockMvc.perform(get("/users/randomNameThatNotExist"))
                    .andDo(print()).andExpect(status().isNotFound());
    }
}