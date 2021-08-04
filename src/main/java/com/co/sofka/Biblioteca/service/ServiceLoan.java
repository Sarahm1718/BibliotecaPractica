package com.co.sofka.Biblioteca.service;

import com.co.sofka.Biblioteca.dto.LoanDTO;
import com.co.sofka.Biblioteca.mapper.PrestamoMapper;
import com.co.sofka.Biblioteca.model.Loan;
import com.co.sofka.Biblioteca.repositories.RepositoryLoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceLoan {

    @Autowired
    RepositoryLoan repositoryLoan;
    PrestamoMapper mapper= new PrestamoMapper();

    public LoanDTO saveLoan(LoanDTO loanDTO) {
        Loan empleado = mapper.fromDTO(loanDTO);
        return mapper.fromCollection(repositoryLoan.save(empleado));
    }

    public LoanDTO getByIdLoan(String id) {
        Loan recursos = repositoryLoan.findById(id).orElseThrow();
        return mapper.fromCollection(recursos);
    }

    public List<LoanDTO> getAllLoan() {
        List<Loan> recursos = (List<Loan>) repositoryLoan.findAll();
        return mapper.fromCollectionList( recursos);
    }

    public LoanDTO modifyLoan(LoanDTO loanDTO) {
        Loan prestamo = mapper.fromDTO(loanDTO);
        repositoryLoan.findById(prestamo.getIdLoan()).orElseThrow(() -> new RuntimeException("prestámo no encontrado"));
        return mapper.fromCollection(repositoryLoan.save(prestamo));
    }

    public void deleteLoan(String id) {
        repositoryLoan.deleteById(id);
    }

}
