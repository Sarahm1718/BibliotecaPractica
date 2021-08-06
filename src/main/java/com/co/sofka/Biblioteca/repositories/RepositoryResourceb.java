package com.co.sofka.Biblioteca.repositories;


import com.co.sofka.Biblioteca.model.Resourceb;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface RepositoryResourceb extends MongoRepository<Resourceb, String> {
    Iterable<Resourceb> findByTypeResource(String typeResource);
    Iterable<Resourceb> findByThematic(String thematic);
}
