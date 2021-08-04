package com.co.sofka.Biblioteca.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Loan {
    @Id
    private String idLoan;
    private String loanData;
    private String userId;
    private String idResource;
}
