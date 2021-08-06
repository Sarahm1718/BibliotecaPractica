package com.co.sofka.Biblioteca.service;

import com.co.sofka.Biblioteca.dto.LoanDTO;
import com.co.sofka.Biblioteca.mapper.PrestamoMapper;
import com.co.sofka.Biblioteca.model.Loan;
import com.co.sofka.Biblioteca.model.Resourceb;
import com.co.sofka.Biblioteca.repositories.RepositoryLoan;
import com.co.sofka.Biblioteca.repositories.RepositoryResourceb;
import com.co.sofka.Biblioteca.repositories.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceLoan {

    @Autowired
    RepositoryLoan repositoryLoan;
    @Autowired
    RepositoryUser repositoryUser;
    @Autowired
    RepositoryResourceb repositoryResourceb;

    PrestamoMapper mapper= new PrestamoMapper();

    public String saveLoan(LoanDTO loanDTO) {
        Loan prestamo = mapper.fromDTO(loanDTO);
        if(repositoryResourceb.existsById(prestamo.getIdResource())&&repositoryUser.existsById(prestamo.getUserId())){
            Optional<Resourceb> recurso = repositoryResourceb.findById(loanDTO.getIdResource());
            if (recurso.get().isAvailability(Boolean.TRUE)){
                recurso.get().setAvailability(Boolean.FALSE);
                mapper.fromCollection(repositoryLoan.save(prestamo));
                return "Puede realizar su prestámo.";
            }else{
                return "No puede hacer la creación del prestámo";
            }
        }
        return "No se puede hacer el prestámo, si no existe el usuario o el recurso.";
    }

    public String prestar(String id){
        Resourceb resourceb = repositoryResourceb.findById(id).orElseThrow(()
                -> new RuntimeException("Asegurese de verificar si puede realizar su prestámo"));
        if (resourceb.isAvailability(Boolean.TRUE)){
            resourceb.setAvailability(Boolean.FALSE);
            repositoryResourceb.save(resourceb);
            return "El prestámo ha sido confirmado";
        }else {
            return "Asegurese de verificar si puede realizar su prestámo";
        }
    }

    public String devolverPrestamo(String id){
        Resourceb resourceb = repositoryResourceb.findById(id).orElseThrow(()
                -> new RuntimeException("No se encontró el recurso"));
        if (resourceb.isAvailability(Boolean.TRUE)){
            return "El recurso no lo has prestado, por lo tanto no puedes devolverlo.";
        }else {
            resourceb.setAvailability(Boolean.TRUE);
            repositoryResourceb.save(resourceb);
            return "El recurso lo has devuelto exitosamente.";
        }
    }

    public LoanDTO getByIdLoan(String id) {
        Optional<Loan> resources = repositoryLoan.findById(id);
        if (repositoryLoan.existsById(id)){
            return mapper.fromCollection(resources.get());
        }else {
            throw  new IllegalArgumentException("No existe");
        }
    }

    public List<LoanDTO> getAllLoan() {
        List<Loan> prestamo = (List<Loan>) repositoryLoan.findAll();
        return mapper.fromCollectionList( prestamo);
    }

    public LoanDTO modifyLoan(LoanDTO loanDTO) {
        Loan prestamo = mapper.fromDTO(loanDTO);
        repositoryLoan.findById(prestamo.getIdLoan());
        return mapper.fromCollection(repositoryLoan.save(prestamo));
    }

    public void deleteLoan(String id) {
        repositoryLoan.deleteById(id);
    }

    public List<LoanDTO> buscarPorIdRecurso(String idResource){
        List<Loan> prestamo = (List<Loan>) repositoryLoan.findByIdResource(idResource);
        return mapper.fromCollectionList(prestamo);
    }
}
