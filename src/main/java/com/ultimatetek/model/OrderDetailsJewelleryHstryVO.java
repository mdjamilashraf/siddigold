/*
 * Copyright 2023 JoinFaces.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ultimatetek.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author Md Junaid Khan
 */

@Data
public class OrderDetailsJewelleryHstryVO extends AuditFieldsVO implements Serializable{

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

}
