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
package com.ultimatetek.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author jamil
 */
@Entity
@Table(name = "sales_order")
@NamedQueries({
    @NamedQuery(name = "SalesOrder.findAll", query = "SELECT s FROM SalesOrder s")})
public class SalesOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "order_no")
    private Long orderNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "so_type")
    private short soType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "order_date")
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    @Column(name = "quot_no")
    private BigInteger quotNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "cur_code")
    private String curCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cur_rate")
    private long curRate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stock_rate")
    private long stockRate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "cust_code")
    private String custCode;
    @Size(max = 10)
    @Column(name = "order_desc")
    private String orderDesc;
    @Size(max = 10)
    @Column(name = "wrhs_code")
    private String wrhsCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "disc_per")
    private long discPer;
    @Basic(optional = false)
    @NotNull
    @Column(name = "disc_amt")
    private long discAmt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tax_per")
    private long taxPer;
    @Column(name = "tax_amt")
    private Long taxAmt;
    @Column(name = "approved")
    private Short approved;
    @Column(name = "aprv_usr_no")
    private BigInteger aprvUsrNo;
    @Column(name = "aprv_date")
    @Temporal(TemporalType.DATE)
    private Date aprvDate;
    @Size(max = 10)
    @Column(name = "aprv_dsc")
    private String aprvDsc;
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
    @Size(min = 1, max = 10)
    @Column(name = "crt_trmnl_nm")
    private String crtTrmnlNm;
    @Column(name = "upd_usr_no")
    private BigInteger updUsrNo;
    @Column(name = "upd_date")
    @Temporal(TemporalType.DATE)
    private Date updDate;
    @Size(max = 10)
    @Column(name = "upd_trmnl_nm")
    private String updTrmnlNm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "upd_cnt")
    private int updCnt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cmp_no")
    private int cmpNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "brn_no")
    private int brnNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "brn_year")
    private int brnYear;
    @Column(name = "brn_usr")
    private Short brnUsr;
    @Column(name = "stop_seq")
    private Integer stopSeq;

    public SalesOrder() {
    }

    public SalesOrder(Long orderNo) {
        this.orderNo = orderNo;
    }

    public SalesOrder(Long orderNo, short soType, Date orderDate, String curCode, long curRate, long stockRate, String custCode, long discPer, long discAmt, long taxPer, long crtUsrNo, Date crtDate, String crtTrmnlNm, int updCnt, int cmpNo, int brnNo, int brnYear) {
        this.orderNo = orderNo;
        this.soType = soType;
        this.orderDate = orderDate;
        this.curCode = curCode;
        this.curRate = curRate;
        this.stockRate = stockRate;
        this.custCode = custCode;
        this.discPer = discPer;
        this.discAmt = discAmt;
        this.taxPer = taxPer;
        this.crtUsrNo = crtUsrNo;
        this.crtDate = crtDate;
        this.crtTrmnlNm = crtTrmnlNm;
        this.updCnt = updCnt;
        this.cmpNo = cmpNo;
        this.brnNo = brnNo;
        this.brnYear = brnYear;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public short getSoType() {
        return soType;
    }

    public void setSoType(short soType) {
        this.soType = soType;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public BigInteger getQuotNo() {
        return quotNo;
    }

    public void setQuotNo(BigInteger quotNo) {
        this.quotNo = quotNo;
    }

    public String getCurCode() {
        return curCode;
    }

    public void setCurCode(String curCode) {
        this.curCode = curCode;
    }

    public long getCurRate() {
        return curRate;
    }

    public void setCurRate(long curRate) {
        this.curRate = curRate;
    }

    public long getStockRate() {
        return stockRate;
    }

    public void setStockRate(long stockRate) {
        this.stockRate = stockRate;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public String getWrhsCode() {
        return wrhsCode;
    }

    public void setWrhsCode(String wrhsCode) {
        this.wrhsCode = wrhsCode;
    }

    public long getDiscPer() {
        return discPer;
    }

    public void setDiscPer(long discPer) {
        this.discPer = discPer;
    }

    public long getDiscAmt() {
        return discAmt;
    }

    public void setDiscAmt(long discAmt) {
        this.discAmt = discAmt;
    }

    public long getTaxPer() {
        return taxPer;
    }

    public void setTaxPer(long taxPer) {
        this.taxPer = taxPer;
    }

    public Long getTaxAmt() {
        return taxAmt;
    }

    public void setTaxAmt(Long taxAmt) {
        this.taxAmt = taxAmt;
    }

    public Short getApproved() {
        return approved;
    }

    public void setApproved(Short approved) {
        this.approved = approved;
    }

    public BigInteger getAprvUsrNo() {
        return aprvUsrNo;
    }

    public void setAprvUsrNo(BigInteger aprvUsrNo) {
        this.aprvUsrNo = aprvUsrNo;
    }

    public Date getAprvDate() {
        return aprvDate;
    }

    public void setAprvDate(Date aprvDate) {
        this.aprvDate = aprvDate;
    }

    public String getAprvDsc() {
        return aprvDsc;
    }

    public void setAprvDsc(String aprvDsc) {
        this.aprvDsc = aprvDsc;
    }

    public long getCrtUsrNo() {
        return crtUsrNo;
    }

    public void setCrtUsrNo(long crtUsrNo) {
        this.crtUsrNo = crtUsrNo;
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    public String getCrtTrmnlNm() {
        return crtTrmnlNm;
    }

    public void setCrtTrmnlNm(String crtTrmnlNm) {
        this.crtTrmnlNm = crtTrmnlNm;
    }

    public BigInteger getUpdUsrNo() {
        return updUsrNo;
    }

    public void setUpdUsrNo(BigInteger updUsrNo) {
        this.updUsrNo = updUsrNo;
    }

    public Date getUpdDate() {
        return updDate;
    }

    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }

    public String getUpdTrmnlNm() {
        return updTrmnlNm;
    }

    public void setUpdTrmnlNm(String updTrmnlNm) {
        this.updTrmnlNm = updTrmnlNm;
    }

    public int getUpdCnt() {
        return updCnt;
    }

    public void setUpdCnt(int updCnt) {
        this.updCnt = updCnt;
    }

    public int getCmpNo() {
        return cmpNo;
    }

    public void setCmpNo(int cmpNo) {
        this.cmpNo = cmpNo;
    }

    public int getBrnNo() {
        return brnNo;
    }

    public void setBrnNo(int brnNo) {
        this.brnNo = brnNo;
    }

    public int getBrnYear() {
        return brnYear;
    }

    public void setBrnYear(int brnYear) {
        this.brnYear = brnYear;
    }

    public Short getBrnUsr() {
        return brnUsr;
    }

    public void setBrnUsr(Short brnUsr) {
        this.brnUsr = brnUsr;
    }

    public Integer getStopSeq() {
        return stopSeq;
    }

    public void setStopSeq(Integer stopSeq) {
        this.stopSeq = stopSeq;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderNo != null ? orderNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SalesOrder)) {
            return false;
        }
        SalesOrder other = (SalesOrder) object;
        if ((this.orderNo == null && other.orderNo != null) || (this.orderNo != null && !this.orderNo.equals(other.orderNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ultimatetek.entity.SalesOrder[ orderNo=" + orderNo + " ]";
    }
    
}
