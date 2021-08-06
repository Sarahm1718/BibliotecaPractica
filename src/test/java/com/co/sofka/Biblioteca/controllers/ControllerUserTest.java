package com.co.sofka.Biblioteca.controllers;

import com.co.sofka.Biblioteca.dto.UserDTO;
import com.co.sofka.Biblioteca.service.ServiceUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.hamcrest.Matchers.is;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerUserTest {
    @MockBean
    private ServiceUser serviceUser;

    @Autowired
    private MockMvc mockMvc1;

    @Test
    @DisplayName("create user")
    public void create() throws Exception {
        UserDTO returnUser = new UserDTO();
        UserDTO userDTO = new UserDTO();

        returnUser.setUserId("hut");
        returnUser.setName("sara");
        returnUser.setDataUser("04/08/1994");

        userDTO.setUserId("hut");
        userDTO.setName("lina");
        userDTO.setDataUser("02/06/1494");

        doReturn(userDTO).when(serviceUser).crearUser(any());

        mockMvc1.perform(post("/usuarios/crearUsuario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(returnUser)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userId", is("hut")));




}
    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}