package com.co.sofka.Biblioteca.mapper;
import com.co.sofka.Biblioteca.dto.ResourcebDTO;
import com.co.sofka.Biblioteca.model.Resourceb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecursoMapper {
    public Resourceb fromDTO(ResourcebDTO dto){
        Resourceb recurso = new Resourceb();
        recurso.setIdResource(dto.getIdResource());
        recurso.setTittle(dto.getTittle());
        recurso.setNombreAutor(dto.getNombreAutor());
        recurso.setTypeResource(dto.getTypeResource());
        recurso.setThematic(dto.getThematic());
        recurso.setAvailability(dto.isAvailability());

        return recurso;
    }
    public ResourcebDTO fromCollection(Resourceb collection){
        ResourcebDTO recursoDTO = new ResourcebDTO();
        recursoDTO.setIdResource(collection.getIdResource());
        recursoDTO.setTittle(collection.getTittle());
        recursoDTO.setNombreAutor(collection.getNombreAutor());
        recursoDTO.setTypeResource(collection.getTypeResource());
        recursoDTO.setThematic(collection.getThematic());
        recursoDTO.setAvailability(collection.isAvailability());
        return recursoDTO;
    }

    public List<ResourcebDTO> fromCollectionList(List<Resourceb> collection) {
        if (collection == null) {
            return null;

        }
        List<ResourcebDTO> list = new ArrayList(collection.size());
        Iterator listTracks = collection.iterator();

        while(listTracks.hasNext()) {
            Resourceb recurso = (Resourceb) listTracks.next();
            list.add(fromCollection(recurso));
        }

        return list;
    }
}
