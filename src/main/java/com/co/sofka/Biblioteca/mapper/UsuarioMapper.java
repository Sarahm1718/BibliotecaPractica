package com.co.sofka.Biblioteca.mapper;

import com.co.sofka.Biblioteca.dto.UserDTO;
import com.co.sofka.Biblioteca.model.User;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UsuarioMapper {
    public User fromDTO(UserDTO dto){
        User usuario = new User();
        usuario.setUserId(dto.getUserId());
        usuario.setName(dto.getName());
        usuario.setLastName(dto.getLastName());
        return usuario;
    }
    public UserDTO fromCollection(User collection){
        UserDTO usuarioDTO = new UserDTO();
        usuarioDTO.setUserId(collection.getUserId());
        usuarioDTO.setName(collection.getName());
        usuarioDTO.setLastName(collection.getLastName());
        return usuarioDTO;
    }

    public List<UserDTO> fromCollectionList(List<User> collection) {
        if (collection == null) {
            return null;

        }
        List<UserDTO> list = new ArrayList(collection.size());
        Iterator listTracks = collection.iterator();

        while(listTracks.hasNext()) {
            User usuario = (User) listTracks.next();
            list.add(fromCollection(usuario));
        }

        return list;
    }
}
