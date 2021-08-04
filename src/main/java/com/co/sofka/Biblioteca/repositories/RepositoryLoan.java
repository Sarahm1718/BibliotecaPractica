package com.co.sofka.Biblioteca.repositories;


import com.co.sofka.Biblioteca.model.Loan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryLoan extends MongoRepository<Loan, String> {

}
