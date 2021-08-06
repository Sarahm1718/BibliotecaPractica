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
import java.util.Optional;

@Service
public class ServiceResourceb {
    @Autowired
    RepositoryResourceb repositoryResourceb;
    RecursoMapper mapper= new RecursoMapper();

    public ResourcebDTO crear(ResourcebDTO resourcebDTO) {
        Resourceb resourceb = mapper.fromDTO(resourcebDTO);
        return mapper.fromCollection(repositoryResourceb.save(resourceb));
    }


    public String obtenerPorId(String id) {Optional<Resourceb> resourceb = repositoryResourceb.findById(id);

        if (resourceb.isEmpty()) {
            return "El recurso no existe.";
        }
        if (resourceb.get().isAvailability(Boolean.FALSE)== false) {

            return "El recurso no está disponible.";
        } else {
            return "El recurso si está disponible.";
        }
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

    public List<ResourcebDTO> obtenerRecursosTipo(String typeResource) {
        List<Resourceb> recurso = (List<Resourceb>) repositoryResourceb.findByTypeResource(typeResource);
        return mapper.fromCollectionList(recurso);
    }

    public List<ResourcebDTO> obtenerTematica(String thematic){
        List<Resourceb> recurso = (List<Resourceb>) repositoryResourceb.findByThematic(thematic);
        return mapper.fromCollectionList(recurso);
    }
}
