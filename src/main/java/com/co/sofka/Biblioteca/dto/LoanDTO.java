package com.co.sofka.Biblioteca.dto;

public class LoanDTO {
    private String idLoan;
    private String loanData;
    private String userId;
    private String idResource;
    private String deliverData;

    public LoanDTO(){
    }


    public String getIdLoan() {
        return idLoan;
    }

    public void setIdLoan(String idLoan) {
        this.idLoan = idLoan;
    }

    public String getDeliverData() {
        return deliverData;
    }

    public void setDeliverData(String deliverData) {
        this.deliverData = deliverData;
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
