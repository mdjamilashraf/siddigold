/*
 * Copyright 2022 JoinFaces.
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
package com.ultimatetek.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 *
 * @author JunaidKhan
 */
@Entity
@Table(name = "item_dtl")
@Data
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemDtl.findAll", query = "SELECT i FROM ItemDtl i"),
    @NamedQuery(name = "ItemDtl.findByItmCode", query = "SELECT i FROM ItemDtl i WHERE i.itmCode = :itmCode"),
    @NamedQuery(name = "ItemDtl.findByItmUnt", query = "SELECT i FROM ItemDtl i WHERE i.itmUnt = :itmUnt")})
@IdClass(ItemDtlPK.class)
public class ItemDtl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "itm_code")
    private String itmCode;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "itm_unt")
    private String itmUnt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "itm_size")
    private int itmSize;
    @Basic(optional = false)
    @NotNull
    @Column(name = "itm_price")
    private BigDecimal itmPrice;
    @Size(max = 50)
    @Column(name = "itm_barcode")
    private String itmBarcode;
    @Column(name = "vat_per")
    private BigDecimal vatPer;
    @Column(name = "cst_per")
    private BigDecimal cstPer;
    @Column(name = "cost_rate")
    private BigDecimal costRate;
    @Column(name = "sales_rate")
    private BigDecimal salesRate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "piece_wise_value")
    private short pieceWiseValue;
    @Basic(optional = false)
    @NotNull
    @Column(name = "crt_usr_no")
    private long crtUsrNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "crt_date")
    @Temporal(TemporalType.DATE)
    private Date crtDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "crt_trmnl_nm")
    private String crtTrmnlNm;
    @Column(name = "upd_usr_no")
    private BigInteger updUsrNo;
    @Column(name = "upd_date")
    @Temporal(TemporalType.DATE)
    private Date updDate;
    @Size(max = 50)
    @Column(name = "upd_trmnl_nm")
    private String updTrmnlNm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "upd_cnt")
    private int updCnt;

    public ItemDtl() {
    }
}
