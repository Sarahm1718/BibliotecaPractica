package com.co.sofka.Biblioteca.service;

import com.co.sofka.Biblioteca.dto.ResourcebDTO;
import com.co.sofka.Biblioteca.model.Resourceb;
import com.co.sofka.Biblioteca.repositories.RepositoryResourceb;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import static org.mockito.ArgumentMatchers.any;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ServiceResourcebTest {

    @Mock
    private RepositoryResourceb repositoryResourceb;

    @InjectMocks
    private ServiceResourceb serviceResourceb;

    private Date objDate = new Date();
    private String strDateFormat = "hh: mm: ss a dd-MMM-aaaa";
    private SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);

    @Test
    @DisplayName("Resourceb test")
    public void findAll(){
        var resourceb1 = new Resourceb();
        resourceb1.setIdResource("sa");
        resourceb1.setTittle("hola");
        resourceb1.setNombreAutor("sara");
        resourceb1.setTypeResource("libro");
        resourceb1.setThematic("juvenil");
        resourceb1.setAvailability(true);

        var resourceb2 = new Resourceb();
        resourceb2.setIdResource("la");
        resourceb2.setTittle("holasa");
        resourceb2.setNombreAutor("juan");
        resourceb2.setTypeResource("revista");
        resourceb2.setThematic("adultos");
        resourceb2.setAvailability(true);

        var resourceb3 = new Resourceb();
        resourceb3.setIdResource("ui");
        resourceb3.setTittle("como ser inteligente");
        resourceb3.setNombreAutor("lucas");
        resourceb3.setTypeResource("libro");
        resourceb3.setThematic("superacion");
        resourceb3.setAvailability(true);

        var lista = new ArrayList<Resourceb>();
        lista.add(resourceb1);
        lista.add(resourceb2);
        lista.add(resourceb3);
        Mockito.when(repositoryResourceb.findAll()).thenReturn(lista);
        var respuesta = serviceResourceb.obtenerTodos();

        Assertions.assertEquals(3,respuesta.size());
        Assertions.assertEquals(resourceb1.getIdResource(),respuesta.get(0).getIdResource());
        Assertions.assertEquals(resourceb2.getIdResource(),respuesta.get(1).getIdResource());
        Assertions.assertEquals(resourceb3.getIdResource(),respuesta.get(2).getIdResource());

    }

    @Test
    void crear() {
        var resourceb1 = new Resourceb();
        resourceb1.setIdResource("sa");
        resourceb1.setTittle("hola");
        resourceb1.setNombreAutor("sara");
        resourceb1.setTypeResource("libro");
        resourceb1.setThematic("juvenil");
        resourceb1.setAvailability(true);

        var resourceb3 = new ResourcebDTO();
        resourceb3.setIdResource("ui");
        resourceb3.setTittle("como ser inteligente");
        resourceb3.setNombreAutor("lucas");
        resourceb3.setTypeResource("libro");
        resourceb3.setThematic("superacion");
        resourceb3.setAvailability(true);

        Mockito.when(repositoryResourceb.save(any())).thenReturn((resourceb1));

        var resultado = serviceResourceb.crear(resourceb3);
        Assertions.assertEquals(resourceb1.getIdResource(),resultado.getIdResource());
    }

}