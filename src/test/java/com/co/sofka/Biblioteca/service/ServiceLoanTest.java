package com.co.sofka.Biblioteca.service;
import com.co.sofka.Biblioteca.dto.LoanDTO;
import com.co.sofka.Biblioteca.model.Loan;
import com.co.sofka.Biblioteca.model.Resourceb;
import com.co.sofka.Biblioteca.repositories.RepositoryLoan;
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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ServiceLoanTest {
    @Mock
    private RepositoryLoan repositoryLoan;

    @Mock
    private RepositoryResourceb repositoryResourceb;

    @InjectMocks
    private ServiceLoan serviceLoan;

    @InjectMocks
    private  ServiceResourceb serviceResourceb;

    private Date objDate = new Date();
    private String strDateFormat = "hh: mm: ss a dd-MMM-aaaa";
    private SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);

    @Test
    @DisplayName("Loan test")
    public void findAll(){
        var loan1 = new Loan();
        loan1.setIdLoan("dytg");
        loan1.setLoanData(objSDF.format(objDate));
        loan1.setUserId("grey");
        loan1.setIdResource("jyta");
        loan1.setDeliverData(objSDF.format(objDate));

        var loan2 = new Loan();
        loan2.setIdLoan("dhtr");
        loan2.setLoanData(objSDF.format(objDate));
        loan2.setUserId("juan");
        loan2.setIdResource("jfrs");
        loan2.setDeliverData(objSDF.format(objDate));

        var loan3 = new Loan();
        loan3.setIdLoan("dgdg");
        loan3.setLoanData(objSDF.format(objDate));
        loan3.setUserId("fre");
        loan3.setIdResource("sarg");
        loan3.setDeliverData(objSDF.format(objDate));

        var lista = new ArrayList<Loan>();
        lista.add(loan1);
        lista.add(loan2);
        lista.add(loan3);
        Mockito.when(repositoryLoan.findAll()).thenReturn(lista);
        var respuesta = serviceLoan.getAllLoan();

        Assertions.assertEquals(3,respuesta.size());
        Assertions.assertEquals(loan1.getIdLoan(),respuesta.get(0).getIdLoan());
        Assertions.assertEquals(loan2.getIdLoan(),respuesta.get(1).getIdLoan());
        Assertions.assertEquals(loan3.getIdLoan(),respuesta.get(2).getIdLoan());
    }

    @Test
    void saveLoan(){
        var loan1 = new Loan();
        loan1.setIdLoan("dytg");
        loan1.setLoanData(objSDF.format(objDate));
        loan1.setUserId("grey");
        loan1.setIdResource("jyta");
        loan1.setDeliverData(objSDF.format(objDate));

        var loan3 = new LoanDTO();
        loan3.setIdLoan("dgdg");
        loan3.setLoanData(objSDF.format(objDate));
        loan3.setUserId("fre");
        loan3.setIdResource("sarg");
        loan3.setDeliverData(objSDF.format(objDate));

        Mockito.when(repositoryLoan.save(any())).thenReturn("Puede realizar su prestámo.");

        var respuesta = serviceLoan.saveLoan(loan3);

        //Necesitamos saber como obtener la respuesta para que funcione.
        Assertions.assertEquals(loan1.getIdLoan(), respuesta.toString());
    }

    @Test
    void modifyLoan(){
        var loan1 = new Loan();
        loan1.setIdLoan("dytg");
        loan1.setLoanData(objSDF.format(objDate));
        loan1.setUserId("grey");
        loan1.setIdResource("jyta");
        loan1.setDeliverData(objSDF.format(objDate));

        var loan3 = new LoanDTO();
        loan3.setIdLoan("dytg");
        loan3.setLoanData(objSDF.format(objDate));
        loan3.setUserId("grey");
        loan3.setIdResource("jyta");
        loan3.setDeliverData(objSDF.format(objDate));

        Mockito.when(repositoryLoan.save(any())).thenReturn((loan1));

        var respuesta = serviceLoan.modifyLoan(loan3);
        Assertions.assertEquals(loan1.getIdLoan(), respuesta.getIdLoan());
    }

    @Test
    void deleteLoan(){
        var loan = new Loan();
        loan.setIdLoan("1");

        Mockito.doNothing().when(repositoryLoan).deleteById("1");

        serviceLoan.deleteLoan("1");

        Mockito.verify(repositoryLoan).deleteById("1");
        Mockito.verify(repositoryLoan).findById("1");

    }

    @Test
    void getByIdLoan(){
        var loan = new Loan();
        loan.setIdLoan("3");


        Mockito.when(repositoryLoan.existsById("3")).thenReturn(true);
        Mockito.when(repositoryLoan.findById("3")).thenReturn(Optional.of(loan));

        LoanDTO find = serviceLoan.getByIdLoan("3");
        Mockito.verify(repositoryLoan).findById("3");

        Assertions.assertEquals(loan.getIdLoan(),find.getIdLoan());
    }

    @Test
    void prestar(){
        var recurso = new Resourceb();
        recurso.setIdResource("17");
        recurso.setTittle("conejito");
        recurso.setNombreAutor("sara");
        recurso.setTypeResource("revista");
        recurso.setThematic("infantil");
        recurso.setAvailability(true);

        var prestamo = new Loan();
        prestamo.setIdLoan("12");
        prestamo.setLoanData("11/11");
        prestamo.setUserId("34");
        prestamo.setIdResource("17");
        prestamo.setDeliverData("17/09");

        Mockito.when(repositoryResourceb.findById("17")).thenReturn(Optional.of(recurso));

        String find = serviceLoan.prestar("17");

        Assertions.assertEquals("El prestámo ha sido confirmado",find);
    }

    @Test
    void devolverPrestamo(){
        var recurso = new Resourceb();
        recurso.setIdResource("17");
        recurso.setTittle("conejito");
        recurso.setNombreAutor("sara");
        recurso.setTypeResource("revista");
        recurso.setThematic("infantil");
        recurso.setAvailability(false);

        var prestamo = new Loan();
        prestamo.setIdLoan("12");
        prestamo.setLoanData("11/11");
        prestamo.setUserId("34");
        prestamo.setIdResource("17");
        prestamo.setDeliverData("17/09");

        Mockito.when(repositoryResourceb.findById("17")).thenReturn(Optional.of(recurso));

        String find = serviceLoan.devolverPrestamo("17");

        Assertions.assertEquals("El recurso lo has devuelto exitosamente.",find);
    }

    }


