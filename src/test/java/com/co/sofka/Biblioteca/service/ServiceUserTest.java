package com.co.sofka.Biblioteca.service;
import com.co.sofka.Biblioteca.dto.UserDTO;
import com.co.sofka.Biblioteca.model.User;
import com.co.sofka.Biblioteca.repositories.RepositoryUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class ServiceUserTest {
    @Mock
    private RepositoryUser repositoryUser;

    @InjectMocks
    private ServiceUser serviceUser;

    private Date objDate = new Date();
    private String strDataFormat = "hh: mm: ss a dd-MMM-aaaa";
    private SimpleDateFormat objSDF = new SimpleDateFormat(strDataFormat);

    @Test
    @DisplayName("User test")
    public void findAll(){
        var user1 = new User();
        user1.setUserId("grey");
        user1.setName("sara durango");
        user1.setDataUser(objSDF.format(objDate));

        var user2 = new User();
        user2.setUserId("frey");
        user2.setName("carolina hincapie");
        user2.setDataUser(objSDF.format(objDate));

        var user3 = new User();
        user3.setUserId("saol");
        user3.setName("johan bermudez");
        user3.setDataUser(objSDF.format(objDate));

        var lista = new ArrayList<User>();
        lista.add(user1);
        lista.add(user2);
        lista.add(user3);
        Mockito.when(repositoryUser.findAll()).thenReturn(lista);
        var respuesta= serviceUser.obtenerTodosUser();

        Assertions.assertEquals(3, respuesta.size());
        Assertions.assertEquals(user1.getUserId(), respuesta.get(0).getUserId());
        Assertions.assertEquals(user2.getUserId(), respuesta.get(1).getUserId());
        Assertions.assertEquals(user3.getUserId(), respuesta.get(2).getUserId());
    }

    @Test
    void crearUser(){
        var user1 = new User();
        user1.setUserId("grey");
        user1.setName("sara durango");
        user1.setDataUser(objSDF.format(objDate));

        var user3 = new UserDTO();
        user3.setUserId("grey");
        user3.setName("sara durango");
        user3.setDataUser(objSDF.format(objDate));

        Mockito.when(repositoryUser.save(any())).thenReturn((user1));

        var resultado = serviceUser.crearUser(user3);
        Assertions.assertEquals(user1.getUserId(),resultado.getUserId());
    }
    @Test
    void obtenerPorIdUser(){
        var user = new User();
        user.setUserId("6");

        Mockito.when(repositoryUser.existsById("6")).thenReturn(true);

        Mockito.when(repositoryUser.findById("6")).thenReturn(Optional.of(user));

        UserDTO find = serviceUser.obtenerPorIdUser("6");

        Mockito.verify(repositoryUser).findById("6");

        Assertions.assertEquals(user.getUserId(),find.getUserId());
    }

    @Test
    void modificarUser(){
        var user1 = new User();
        user1.setUserId("grey");
        user1.setName("sara durango");
        user1.setDataUser(objSDF.format(objDate));

        var user2 = new UserDTO();
        user2.setUserId("frey");
        user2.setName("carolina hincapie");
        user2.setDataUser(objSDF.format(objDate));

        Mockito.when(repositoryUser.findById(anyString())).thenReturn(Optional.of(user1));

        Mockito.when(repositoryUser.save(any(User.class))).thenReturn(user1);

        UserDTO find = serviceUser.modificarUser(user2);
        Assertions.assertEquals(user2.getUserId(),find.getUserId());
    }

    @Test
    void borrarUser(){
        var user = new User();
        user.setUserId("1");

        Mockito.doNothing().when(repositoryUser).deleteById("1");

        serviceUser.borrarUser("1");

        Mockito.verify(repositoryUser).deleteById("1");
        Mockito.verify(repositoryUser).findById("1");

    }

}