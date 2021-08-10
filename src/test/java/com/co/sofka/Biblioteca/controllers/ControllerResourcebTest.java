package com.co.sofka.Biblioteca.controllers;

import com.co.sofka.Biblioteca.dto.ResourcebDTO;
import com.co.sofka.Biblioteca.dto.UserDTO;
import com.co.sofka.Biblioteca.service.ServiceResourceb;
import com.co.sofka.Biblioteca.service.ServiceUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class ControllerResourcebTest {



        @MockBean
        private ServiceResourceb serviceResourceb;

        @Autowired
        private MockMvc mockMvc1;

        @Test
        @DisplayName("createresourceb")
        public void create() throws Exception {
            ResourcebDTO returnResourceb = new ResourcebDTO();
            ResourcebDTO resourcebDTO = new ResourcebDTO();

            returnResourceb.setIdResource("pol");
            returnResourceb.setTittle("los libros magicos");
            returnResourceb.setNombreAutor("johan");
            returnResourceb.setTypeResource("revista");
            returnResourceb.setThematic("juvenil");
            returnResourceb.setAvailability(true);

            resourcebDTO.setIdResource("pol");
            resourcebDTO.setTittle("los libros ");
            resourcebDTO.setNombreAutor("lina");
            resourcebDTO.setTypeResource("libro");
            resourcebDTO.setThematic("juvenil");
            resourcebDTO.setAvailability(false);

            doReturn(resourcebDTO).when(serviceResourceb).crear(any());

            mockMvc1.perform(post("/recursos/crearRecurso")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(returnResourceb)))
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.idResource", is("pol")));




        }
        static String asJsonString(final Object obj) {
            try {
                return new ObjectMapper().writeValueAsString(obj);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    @Test
    @DisplayName("buscarRecurso/{id}")
    public void findbyId() throws Exception{

        ResourcebDTO returnResourceb = new ResourcebDTO();
        ResourcebDTO resourcebDTO = new ResourcebDTO();

        returnResourceb.setIdResource("pol");
        returnResourceb.setTittle("los libros magicos");
        returnResourceb.setNombreAutor("johan");
        returnResourceb.setTypeResource("revista");
        returnResourceb.setThematic("juvenil");
        returnResourceb.setAvailability(true);

        resourcebDTO.setIdResource("pol");
        resourcebDTO.setTittle("los libros ");
        resourcebDTO.setNombreAutor("lina");
        resourcebDTO.setTypeResource("libro");
        resourcebDTO.setThematic("juvenil");
        resourcebDTO.setAvailability(false);


        doReturn(resourcebDTO).when(serviceResourceb).crear(any());

        mockMvc1.perform(get("/recursos/buscarRecurso/pol")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(returnResourceb)))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("todosRecurso")
    public void findAll() throws Exception{

        ResourcebDTO returnResourceb = new ResourcebDTO();
        ResourcebDTO resourcebDTO = new ResourcebDTO();

        returnResourceb.setIdResource("pol");
        returnResourceb.setTittle("los libros magicos");
        returnResourceb.setNombreAutor("johan");
        returnResourceb.setTypeResource("revista");
        returnResourceb.setThematic("juvenil");
        returnResourceb.setAvailability(true);

        resourcebDTO.setIdResource("pol");
        resourcebDTO.setTittle("los libros ");
        resourcebDTO.setNombreAutor("lina");
        resourcebDTO.setTypeResource("libro");
        resourcebDTO.setThematic("juvenil");
        resourcebDTO.setAvailability(false);

        doReturn(resourcebDTO).when(serviceResourceb).crear(any());


        mockMvc1.perform(get("/recursos/todosRecurso")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(returnResourceb)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    @DisplayName("todosRecursos/{typeResource}")
    public void buscarTipoRecurso() throws Exception{

        ResourcebDTO returnResourceb = new ResourcebDTO();
        ResourcebDTO resourcebDTO = new ResourcebDTO();


        returnResourceb.setIdResource("pol");
        returnResourceb.setTittle("los libros magicos");
        returnResourceb.setNombreAutor("johan");
        returnResourceb.setTypeResource("revista");
        returnResourceb.setThematic("juvenil");
        returnResourceb.setAvailability(true);

        resourcebDTO.setIdResource("pol");
        resourcebDTO.setTittle("los libros ");
        resourcebDTO.setNombreAutor("lina");
        resourcebDTO.setTypeResource("libro");
        resourcebDTO.setThematic("juvenil");
        resourcebDTO.setAvailability(false);

        doReturn(resourcebDTO).when(serviceResourceb).crear(any());

        mockMvc1.perform(get("/recursos/todosRecursos/libro")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(returnResourceb)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("tematicas/{thematic}")
    public void buscarTematica() throws Exception{

        ResourcebDTO returnResourceb = new ResourcebDTO();
        ResourcebDTO resourcebDTO = new ResourcebDTO();


        returnResourceb.setIdResource("pol");
        returnResourceb.setTittle("los libros magicos");
        returnResourceb.setNombreAutor("johan");
        returnResourceb.setTypeResource("revista");
        returnResourceb.setThematic("juvenil");
        returnResourceb.setAvailability(true);

        resourcebDTO.setIdResource("pol");
        resourcebDTO.setTittle("los libros ");
        resourcebDTO.setNombreAutor("lina");
        resourcebDTO.setTypeResource("libro");
        resourcebDTO.setThematic("juvenil");
        resourcebDTO.setAvailability(false);

        doReturn(resourcebDTO).when(serviceResourceb).crear(any());

        mockMvc1.perform(get("/recursos/tematicas/juvenil")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(returnResourceb)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }


    }

