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

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 *
 * @author JunaidKhan
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SalesOrderVO extends AuditFieldsVO implements Serializable {

    private Long orderNo;
    private Short soType;
    private Date orderDate;
    private BigInteger quotNo;
    private String curCode;
    private Long curRate;
    private Long stockRate;
    private String custCode;
    private String orderDesc;
    private String wrhsCode;
    private Long discPer;
    private Long discAmt;
    private Long taxPer;
    private Long taxAmt;
    private Short approved;
    private BigInteger aprvUsrNo;
    private Date aprvDate;
    private String aprvDsc;
    private Integer cmpNo;
    private Integer brnNo;
    private Integer brnYear;
    private Short brnUsr;
    private String custName;
    private String custAddr;
    private String formatedCustCrtDate;
    private Short custInactiveFlg;

    private List<OrderDetailsVO> ordersDtls;
}
