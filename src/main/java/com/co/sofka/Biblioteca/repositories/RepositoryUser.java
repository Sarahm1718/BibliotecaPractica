package com.co.sofka.Biblioteca.repositories;


import com.co.sofka.Biblioteca.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryUser extends MongoRepository<User, String> {

}
