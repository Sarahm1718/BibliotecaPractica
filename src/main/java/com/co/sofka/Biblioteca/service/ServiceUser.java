package com.co.sofka.Biblioteca.service;

import com.co.sofka.Biblioteca.dto.ResourcebDTO;
import com.co.sofka.Biblioteca.dto.UserDTO;
import com.co.sofka.Biblioteca.mapper.RecursoMapper;
import com.co.sofka.Biblioteca.mapper.UsuarioMapper;
import com.co.sofka.Biblioteca.model.Resourceb;
import com.co.sofka.Biblioteca.model.User;
import com.co.sofka.Biblioteca.repositories.RepositoryResourceb;
import com.co.sofka.Biblioteca.repositories.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServiceUser {
        @Autowired
        RepositoryUser repositoryUser;
        UsuarioMapper mapper= new UsuarioMapper();

        public UserDTO crearUser(UserDTO userDTO) {
            User empleado = mapper.fromDTO(userDTO);
            return mapper.fromCollection(repositoryUser.save(empleado));
        }

        public UserDTO obtenerPorIdUser(String id) {
            User recursos = repositoryUser.findById(id).orElseThrow(() -> new RuntimeException("Libro no encontrado"));
            return mapper.fromCollection(recursos);
        }

        public List<UserDTO> obtenerTodosUser() {
            List<User> user = (List<User>) repositoryUser.findAll();
            return mapper.fromCollectionList( user);
        }

        public UserDTO modificarUser(UserDTO userDTO) {
            User prestamo = mapper.fromDTO(userDTO);
            repositoryUser.findById(prestamo.getUserId()).orElseThrow(() -> new RuntimeException("prestámo no encontrado"));
            return mapper.fromCollection(repositoryUser.save(prestamo));
        }

        public void borrarUser(String id) {
            repositoryUser.deleteById(id);
        }
    }

