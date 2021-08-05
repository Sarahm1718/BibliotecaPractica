package com.co.sofka.Biblioteca.service;
import com.co.sofka.Biblioteca.dto.ResourcebDTO;
import com.co.sofka.Biblioteca.mapper.PrestamoMapper;
import com.co.sofka.Biblioteca.mapper.RecursoMapper;
import com.co.sofka.Biblioteca.model.Resourceb;
import com.co.sofka.Biblioteca.repositories.RepositoryLoan;
import com.co.sofka.Biblioteca.repositories.RepositoryResourceb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServiceResourceb {
    @Autowired
    RepositoryResourceb repositoryResourceb;
    RecursoMapper mapper= new RecursoMapper();

    public ResourcebDTO crear(ResourcebDTO resourcebDTO) {
        Resourceb empleado = mapper.fromDTO(resourcebDTO);
        return mapper.fromCollection(repositoryResourceb.save(empleado));
    }

    public ResourcebDTO obtenerPorId(String id) {
        Resourceb recursos = repositoryResourceb.findById(id).orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        return mapper.fromCollection(recursos);
    }

    public List<ResourcebDTO> obtenerTodos() {
        List<Resourceb> recursos = (List<Resourceb>) repositoryResourceb.findAll();
        return mapper.fromCollectionList( recursos);
    }

    public ResourcebDTO modificar(ResourcebDTO resourcebDTO) {
        Resourceb prestamo = mapper.fromDTO(resourcebDTO);
        repositoryResourceb.findById(prestamo.getIdResource()).orElseThrow(() -> new RuntimeException("prestámo no encontrado"));
        return mapper.fromCollection(repositoryResourceb.save(prestamo));
    }

    public void borrar(String id) {
        repositoryResourceb.deleteById(id);
    }
}
