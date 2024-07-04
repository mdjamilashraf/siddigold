/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ultimatetek.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author JunaidKhan
 */
@Data
@AllArgsConstructor
public class ResultVO {

    private Integer errNo;
    private String errMsg;

    public ResultVO(Integer errNo) {
        this.errNo = errNo;
    }

    public ResultVO(String errMsg) {
        super();
        this.errMsg = errMsg;
    }

}
