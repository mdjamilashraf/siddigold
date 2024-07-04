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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JunaidKhan
 */
@Entity
@Table(name = "customer_detail_othr")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerDetailOthr.findAll", query = "SELECT c FROM CustomerDetailOthr c"),
    @NamedQuery(name = "CustomerDetailOthr.findByCustCode", query = "SELECT c FROM CustomerDetailOthr c WHERE c.custCode = :custCode"),
    @NamedQuery(name = "CustomerDetailOthr.findByCustName", query = "SELECT c FROM CustomerDetailOthr c WHERE c.custName = :custName"),
    @NamedQuery(name = "CustomerDetailOthr.findByCustNameAlias", query = "SELECT c FROM CustomerDetailOthr c WHERE c.custNameAlias = :custNameAlias"),
    @NamedQuery(name = "CustomerDetailOthr.findByCustPhone", query = "SELECT c FROM CustomerDetailOthr c WHERE c.custPhone = :custPhone"),
    @NamedQuery(name = "CustomerDetailOthr.findByCustMobile", query = "SELECT c FROM CustomerDetailOthr c WHERE c.custMobile = :custMobile"),
    @NamedQuery(name = "CustomerDetailOthr.findByCustEmail", query = "SELECT c FROM CustomerDetailOthr c WHERE c.custEmail = :custEmail"),
    @NamedQuery(name = "CustomerDetailOthr.findByCustAddr", query = "SELECT c FROM CustomerDetailOthr c WHERE c.custAddr = :custAddr"),
    @NamedQuery(name = "CustomerDetailOthr.findByStateId", query = "SELECT c FROM CustomerDetailOthr c WHERE c.stateId = :stateId"),
    @NamedQuery(name = "CustomerDetailOthr.findByCityId", query = "SELECT c FROM CustomerDetailOthr c WHERE c.cityId = :cityId"),
    @NamedQuery(name = "CustomerDetailOthr.findByPinCode", query = "SELECT c FROM CustomerDetailOthr c WHERE c.pinCode = :pinCode"),
    @NamedQuery(name = "CustomerDetailOthr.findByTinNo", query = "SELECT c FROM CustomerDetailOthr c WHERE c.tinNo = :tinNo"),
    @NamedQuery(name = "CustomerDetailOthr.findByCstNo", query = "SELECT c FROM CustomerDetailOthr c WHERE c.cstNo = :cstNo"),
    @NamedQuery(name = "CustomerDetailOthr.findByInactiveFlg", query = "SELECT c FROM CustomerDetailOthr c WHERE c.inactiveFlg = :inactiveFlg"),
    @NamedQuery(name = "CustomerDetailOthr.findByInactiveUsrNo", query = "SELECT c FROM CustomerDetailOthr c WHERE c.inactiveUsrNo = :inactiveUsrNo"),
    @NamedQuery(name = "CustomerDetailOthr.findByInactiveDate", query = "SELECT c FROM CustomerDetailOthr c WHERE c.inactiveDate = :inactiveDate"),
    @NamedQuery(name = "CustomerDetailOthr.findByInactiveDsc", query = "SELECT c FROM CustomerDetailOthr c WHERE c.inactiveDsc = :inactiveDsc"),
    @NamedQuery(name = "CustomerDetailOthr.findByCrtUsrNo", query = "SELECT c FROM CustomerDetailOthr c WHERE c.crtUsrNo = :crtUsrNo"),
    @NamedQuery(name = "CustomerDetailOthr.findByCrtDate", query = "SELECT c FROM CustomerDetailOthr c WHERE c.crtDate = :crtDate"),
    @NamedQuery(name = "CustomerDetailOthr.findByCrtTrmnlNm", query = "SELECT c FROM CustomerDetailOthr c WHERE c.crtTrmnlNm = :crtTrmnlNm"),
    @NamedQuery(name = "CustomerDetailOthr.findByUpdUsrNo", query = "SELECT c FROM CustomerDetailOthr c WHERE c.updUsrNo = :updUsrNo"),
    @NamedQuery(name = "CustomerDetailOthr.findByUpdDate", query = "SELECT c FROM CustomerDetailOthr c WHERE c.updDate = :updDate"),
    @NamedQuery(name = "CustomerDetailOthr.findByUpdTrmnlNm", query = "SELECT c FROM CustomerDetailOthr c WHERE c.updTrmnlNm = :updTrmnlNm"),
    @NamedQuery(name = "CustomerDetailOthr.findByUpdCnt", query = "SELECT c FROM CustomerDetailOthr c WHERE c.updCnt = :updCnt")})
public class CustomerDetailOthr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "cust_code")
    private String custCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "cust_name")
    private String custName;
    @Size(max = 100)
    @Column(name = "cust_name_alias")
    private String custNameAlias;
    @Size(max = 15)
    @Column(name = "cust_phone")
    private String custPhone;
    @Size(max = 15)
    @Column(name = "cust_mobile")
    private String custMobile;
    @Size(max = 50)
    @Column(name = "cust_email")
    private String custEmail;
    @Size(max = 500)
    @Column(name = "cust_addr")
    private String custAddr;
    @Column(name = "state_id")
    private Integer stateId;
    @Column(name = "city_id")
    private Integer cityId;
    @Size(max = 10)
    @Column(name = "pin_code")
    private String pinCode;
    @Size(max = 20)
    @Column(name = "tin_no")
    private String tinNo;
    @Size(max = 20)
    @Column(name = "cst_no")
    private String cstNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "inactive_flg")
    private Short inactiveFlg;
    @Column(name = "inactive_usr_no")
    private Integer inactiveUsrNo;
    @Column(name = "inactive_date")
    @Temporal(TemporalType.DATE)
    private Date inactiveDate;
    @Size(max = 100)
    @Column(name = "inactive_dsc")
    private String inactiveDsc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "crt_usr_no")
    private long crtUsrNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "crt_date")
    @Temporal(TemporalType.DATE)
    private Date crtDate;
    @Size(max = 100)
    @Column(name = "crt_trmnl_nm")
    private String crtTrmnlNm;
    @Column(name = "upd_usr_no")
    private Long updUsrNo;
    @Column(name = "upd_date")
    @Temporal(TemporalType.DATE)
    private Date updDate;
    @Size(max = 100)
    @Column(name = "upd_trmnl_nm")
    private String updTrmnlNm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "upd_cnt")
    private int updCnt;

    public CustomerDetailOthr() {
    }

    public CustomerDetailOthr(String custCode) {
        this.custCode = custCode;
    }

    public CustomerDetailOthr(String custCode, String custName, Short inactiveFlg, long crtUsrNo, Date crtDate, int updCnt) {
        this.custCode = custCode;
        this.custName = custName;
        this.inactiveFlg = inactiveFlg;
        this.crtUsrNo = crtUsrNo;
        this.crtDate = crtDate;
        this.updCnt = updCnt;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustNameAlias() {
        return custNameAlias;
    }

    public void setCustNameAlias(String custNameAlias) {
        this.custNameAlias = custNameAlias;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getCustMobile() {
        return custMobile;
    }

    public void setCustMobile(String custMobile) {
        this.custMobile = custMobile;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public String getCustAddr() {
        return custAddr;
    }

    public void setCustAddr(String custAddr) {
        this.custAddr = custAddr;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getTinNo() {
        return tinNo;
    }

    public void setTinNo(String tinNo) {
        this.tinNo = tinNo;
    }

    public String getCstNo() {
        return cstNo;
    }

    public void setCstNo(String cstNo) {
        this.cstNo = cstNo;
    }

    public Short getInactiveFlg() {
        return inactiveFlg;
    }

    public void setInactiveFlg(Short inactiveFlg) {
        this.inactiveFlg = inactiveFlg;
    }

    public Integer getInactiveUsrNo() {
        return inactiveUsrNo;
    }

    public void setInactiveUsrNo(Integer inactiveUsrNo) {
        this.inactiveUsrNo = inactiveUsrNo;
    }

    public Date getInactiveDate() {
        return inactiveDate;
    }

    public void setInactiveDate(Date inactiveDate) {
        this.inactiveDate = inactiveDate;
    }

    public String getInactiveDsc() {
        return inactiveDsc;
    }

    public void setInactiveDsc(String inactiveDsc) {
        this.inactiveDsc = inactiveDsc;
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

    public Long getUpdUsrNo() {
        return updUsrNo;
    }

    public void setUpdUsrNo(Long updUsrNo) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (custCode != null ? custCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerDetailOthr)) {
            return false;
        }
        CustomerDetailOthr other = (CustomerDetailOthr) object;
        if ((this.custCode == null && other.custCode != null) || (this.custCode != null && !this.custCode.equals(other.custCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ultimatetek.entity.CustomerDetailOthr[ custCode=" + custCode + " ]";
    }

}
