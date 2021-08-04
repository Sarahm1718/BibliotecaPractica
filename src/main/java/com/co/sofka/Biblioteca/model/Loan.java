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

    public String getIdLoan() {
        return idLoan;
    }

    public void setIdLoan(String idLoan) {
        this.idLoan = idLoan;
    }

    public String getLoanData() {
        return loanData;
    }

    public void setLoanData(String loanData) {
        this.loanData = loanData;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIdResource() {
        return idResource;
    }

    public void setIdResource(String idResource) {
        this.idResource = idResource;
    }
}
