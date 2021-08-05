package com.co.sofka.Biblioteca.controllers;

import com.co.sofka.Biblioteca.dto.ResourcebDTO;
import com.co.sofka.Biblioteca.dto.UserDTO;
import com.co.sofka.Biblioteca.model.User;
import com.co.sofka.Biblioteca.service.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/usuarios")
public class ControllerUser {
    @Autowired
    ServiceUser serviceUser;

    @PostMapping("/crearRecurso")
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
        return new ResponseEntity(serviceUser.crearUser(userDTO), HttpStatus.CREATED);
    }

    @GetMapping("/buscarRecurso/{id}")
    public ResponseEntity<UserDTO> findbyId(@PathVariable("id") String id) {
        return new ResponseEntity(serviceUser.obtenerPorIdUser(id), HttpStatus.OK);
    }

    @GetMapping("/todosRecurso")
    public ResponseEntity<List<UserDTO>> findAll() {
        return new ResponseEntity(serviceUser.obtenerTodosUser(), HttpStatus.OK);
    }
    @PutMapping("/modificarRecurso")
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO) {
        if (userDTO.getUserId() != null) {
            return new ResponseEntity(serviceUser.modificarUser(userDTO), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/borrarPrestamo/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        try {
            serviceUser.borrarUser(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
