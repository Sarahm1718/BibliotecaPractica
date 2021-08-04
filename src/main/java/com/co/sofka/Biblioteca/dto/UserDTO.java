package com.co.sofka.Biblioteca.dto;

public class UserDTO {
    private String userId;
    private String name;
    private String dataUser;

    public UserDTO(){

    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataUser() {
        return dataUser;
    }

    public void setDataUser(String dataUser) {
        this.dataUser = dataUser;
    }
}
