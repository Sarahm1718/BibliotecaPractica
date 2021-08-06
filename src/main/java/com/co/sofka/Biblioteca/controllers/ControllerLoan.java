package com.co.sofka.Biblioteca.controllers;

import com.co.sofka.Biblioteca.dto.LoanDTO;
import com.co.sofka.Biblioteca.service.ServiceLoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/prestamos")
public class ControllerLoan {
    @Autowired
    ServiceLoan serviceLoan;

    @PostMapping("/crearPrestamo")
    public String create(@RequestBody LoanDTO loanDTO) {
        return serviceLoan.saveLoan(loanDTO);
    }

    @PutMapping("/prestar/{id}")
    public ResponseEntity prestar(@PathVariable("id")String id) {
        return new ResponseEntity(serviceLoan.prestar(id), HttpStatus.OK);
    }

    @PutMapping("/devolver/{id}")
    public ResponseEntity devolver(@PathVariable("id")String id) {
        return new ResponseEntity(serviceLoan.devolverPrestamo(id), HttpStatus.OK);
    }

    @GetMapping("/buscarPrestamo/{id}")
    public LoanDTO findbyId(@PathVariable("id") String id) {
        return serviceLoan.getByIdLoan(id);
    }

    @GetMapping("/todosPrestamo")
    public ResponseEntity<List<LoanDTO>> findAll() {
        return new ResponseEntity(serviceLoan.getAllLoan(), HttpStatus.OK);
    }

    @PutMapping("/modificarPrestamo")
    public ResponseEntity<LoanDTO> update(@RequestBody LoanDTO loanDTO) {
        if (loanDTO.getIdLoan() != null) {
            return new ResponseEntity(serviceLoan.modifyLoan(loanDTO), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/borrarPrestamo/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        try {
            serviceLoan.deleteLoan(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscarIdRecurso/{idResource}")
    public ResponseEntity<List<LoanDTO>> buscarTipoRecurso(@PathVariable("idResource") String idResource) {
        return new ResponseEntity(serviceLoan.buscarPorIdRecurso(idResource), HttpStatus.OK);
    }

}
