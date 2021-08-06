package com.co.sofka.Biblioteca.service;
import com.co.sofka.Biblioteca.dto.LoanDTO;
import com.co.sofka.Biblioteca.model.Loan;
import com.co.sofka.Biblioteca.repositories.RepositoryLoan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ServiceLoanTest {
    @MockBean
    private RepositoryLoan repositoryLoan;

    @Autowired
    private ServiceLoan serviceLoan;

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

        Mockito.when(repositoryLoan.save(any())).thenReturn((loan1));

       // var resultado = serviceLoan.saveLoan(loan3);
        //Assertions.assertEquals(loan1.getIdLoan(),resultado);
    }
}