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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerUserTest {
    @MockBean
    private ServiceUser serviceUser;

    @Autowired
    private MockMvc mockMvc1;

    @Test
    @DisplayName("createUser")
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


    @Test
    @DisplayName("BuscarUser")
    void findbyId() throws Exception {
        UserDTO returnUser = new UserDTO();
        UserDTO userDTO = new UserDTO();


        returnUser.setUserId("hut");
        returnUser.setName("sara");
        returnUser.setDataUser("04/08/1994");

        userDTO.setUserId("hut");
        userDTO.setName("lina");
        userDTO.setDataUser("02/06/1494");

        doReturn(userDTO).when(serviceUser).crearUser(any());

        mockMvc1.perform(get("/usuarios/buscarUsuario/hut")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(returnUser)))
                .andExpect(status().isOk());


    }

    @Test
    @DisplayName("BuscarUser")
    void findAll() throws Exception {
        UserDTO returnUser = new UserDTO();
        UserDTO userDTO = new UserDTO();


        returnUser.setUserId("hut");
        returnUser.setName("sara");
        returnUser.setDataUser("04/08/1994");

        userDTO.setUserId("hut");
        userDTO.setName("lina");
        userDTO.setDataUser("02/06/1494");

        doReturn(userDTO).when(serviceUser).crearUser(any());

        mockMvc1.perform(get("/usuarios/obtenerTodosUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(returnUser)))
                .andExpect(status().isOk())
         .andExpect(content().contentType(MediaType.APPLICATION_JSON));


    }

    @Test
    @DisplayName("actualizar")
    void update() throws Exception {
        UserDTO returnUser = new UserDTO();
        UserDTO userDTO = new UserDTO();


        returnUser.setUserId("hut");
        returnUser.setName("sara");
        returnUser.setDataUser("04/08/1994");

        userDTO.setUserId("hut");
        userDTO.setName("lina");
        userDTO.setDataUser("02/06/1494");

        doReturn(userDTO).when(serviceUser).crearUser(any());

        mockMvc1.perform(put("/usuarios/modificarUsuario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(returnUser)))
                .andExpect(status().isOk());


    }

    @Test
    @DisplayName("borrarUser")
    void delete() throws Exception {
        UserDTO returnUser = new UserDTO();
        UserDTO userDTO = new UserDTO();


        returnUser.setUserId("hut");
        returnUser.setName("sara");
        returnUser.setDataUser("04/08/1994");

        userDTO.setUserId("hut");
        userDTO.setName("lina");
        userDTO.setDataUser("02/06/1494");

        doReturn(userDTO).when(serviceUser).crearUser(any());

        mockMvc1.perform(MockMvcRequestBuilders.delete("/usuarios/borrarUsuario/hut")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(returnUser)))
                .andExpect(status().isOk());

    }


}