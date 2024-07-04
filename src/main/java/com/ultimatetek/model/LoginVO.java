package com.ultimatetek.model;

import lombok.Data;

@Data
public class LoginVO {

    private String userName;
    private String password;
    private String cnfPassword;

    public LoginVO() {
    }

}
