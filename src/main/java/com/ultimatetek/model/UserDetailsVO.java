/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ultimatetek.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author JunaidKhan
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDetailsVO extends AuditFieldsVO {

    private Long userNo;
    private String userCode;
    private String userFirstName;
    private String userLastName;
    private String password;
    private String userPhone;
    private String userEmail;
    private Date startDateTime;
    private Date endDateTime;
    private Integer passwordLoginAttempt;
    private Integer inactiveFlg;
    private Integer updCnt;
    private Integer groupNo;
    private String userName;
    private String cnfPassword;
    private List<OrderDetailsVO> customerDetails;

    public UserDetailsVO(Long userNo, String userCode) {
        this.userNo = userNo;
        this.userCode = userCode;
    }
}
