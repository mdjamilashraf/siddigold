/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ultimatetek.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author jamil
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetailsVO extends AuditFieldsVO implements Serializable {

    private Short srlNo;
    private String itemCode;
    private String itemName;
    private String itemUnit = "grm";
    private Float weight;
    private Float netWeight;
    private Integer qty;
    private String itemSize;
    private Float meltPer;
    private String hook;
    private String remark;
    private String stamp;
    private String refNo;
    private String designSample;
    private String sizeSample;
    private Short days;
    private Date dueDate;
    private String formatedDueDate;
    private String workshop;
    private String wrkshpCode;
    private String sample;
    private Boolean rcvSample = false;
    private Boolean returnSample = false;
    private String fixRate = "R0";
    private Integer orderStatus;
    private String status;
    private Integer orderTyp = 1;
    private String orderTypLbl;
    private Long orderNo;
    private String ordrNo;
    private String formatedOrdrDate;
    private String rowStatus = "Original";
    private Long orderDtlNo;
    private Double itemPrice;
    private Date orderDate;
    private Integer wrkshpStatus;
    private String wrkshpStatusNm;
    private Date wrkshpStatusDate;
    private String formatedStatusDate;
    private String wrkrName;
    private String custCode;
    private String custName;
    private Date wrkshpCloseDate;
    private Date wrkshpCancelDate;
    private Date orderCloseDate;
    private String formatedWrkshpCloseDate;
    private String formatedWrkshpCancelDate;
    private String formatedOrderCloseDate;
//    private String formatedOrderConfirmDate;
//    private String formatedOrderAssignedDate;
//    private String formatedOrderCancelDate;
//    private String formatedOrderPendingDate;
    private String type;
    private String[] imageData;
//    private List<String> imgPath1;
    private String[] imgPath1;
    private String rprtLbl;
    private Object rprtVal;
    

    public OrderDetailsVO(Long orderDtlNo, String rprtLbl, Object rprtVal) {
        this.orderDtlNo = orderDtlNo;
        this.rprtLbl = rprtLbl;
        this.rprtVal = rprtVal;
    }

    public OrderDetailsVO() {
    }

}
