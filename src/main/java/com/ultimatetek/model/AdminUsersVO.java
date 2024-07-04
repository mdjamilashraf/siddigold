/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ultimatetek.model;

import lombok.Data;

/**
 *
 * @author Nadeem
 */
@Data
public class AdminUsersVO {

    private Long userNo;
    private String userCode;
    private String userName;
    private String password;

    private Integer userLocked;
    private Integer userActive = 1;
    private Short inactiveFlg;
    private String Status;
//private Date inactive_datetime;
//private Date stime;
//private Date etime;
//private Date sdatetime;
//private Date edatetime;
//private String phone;
//private String note;
//private int pwd_re_try_attempts;
//private int pwd_expire_in_months;
//private String terminal_name;
//private int is_rsa; 
//private int agt_brn_no;

}
