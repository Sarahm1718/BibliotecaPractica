package com.co.sofka.Biblioteca.controllers;

import com.co.sofka.Biblioteca.dto.ResourcebDTO;
import com.co.sofka.Biblioteca.service.ServiceResourceb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recursos")
public class ControllerResourceb {
    @Autowired
    ServiceResourceb serviceResourceb;

    @PostMapping("/crearRecurso")
    public ResponseEntity<ResourcebDTO> create(@RequestBody ResourcebDTO resourcebDTO) {
        return new ResponseEntity(serviceResourceb.crear(resourcebDTO), HttpStatus.CREATED);
    }


    @GetMapping("/buscarRecurso/{id}")
    public ResponseEntity<ResourcebDTO> findbyId(@PathVariable("id") String id) {
        return new ResponseEntity(serviceResourceb.obtenerPorId(id), HttpStatus.OK);
    }

    @GetMapping("/todosRecurso")
    public ResponseEntity<List<ResourcebDTO>> findAll() {
        return new ResponseEntity(serviceResourceb.obtenerTodos(), HttpStatus.OK);
    }
    @PutMapping("/modificarRecurso")
    public ResponseEntity<ResourcebDTO> update(@RequestBody ResourcebDTO resourcebDTO) {
        if (resourcebDTO.getIdResource() != null) {
            return new ResponseEntity(serviceResourceb.modificar(resourcebDTO), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/borrarPrestamo/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        try {
            serviceResourceb.borrar(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/todosRecursos/{typeResource}")
    public ResponseEntity<List<ResourcebDTO>> buscarTipoRecurso(@PathVariable("typeResource") String typeResource) {
        return new ResponseEntity(serviceResourceb.obtenerRecursosTipo(typeResource), HttpStatus.OK);
    }

    @GetMapping("/tematicas/{thematic}")
    public ResponseEntity<List<ResourcebDTO>> buscarTematica(@PathVariable("thematic") String thematic){
        return new ResponseEntity(serviceResourceb.obtenerTematica(thematic), HttpStatus.OK);
    }
}
