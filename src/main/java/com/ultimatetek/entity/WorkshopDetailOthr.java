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
@Table(name = "workshop_detail_othr")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WorkshopDetailOthr.findAll", query = "SELECT w FROM WorkshopDetailOthr w"),
    @NamedQuery(name = "WorkshopDetailOthr.findByWrkshpCode", query = "SELECT w FROM WorkshopDetailOthr w WHERE w.wrkshpCode = :wrkshpCode"),
    @NamedQuery(name = "WorkshopDetailOthr.findByWrkshpName", query = "SELECT w FROM WorkshopDetailOthr w WHERE w.wrkshpName = :wrkshpName"),
    @NamedQuery(name = "WorkshopDetailOthr.findByWrkshpNameAlias", query = "SELECT w FROM WorkshopDetailOthr w WHERE w.wrkshpNameAlias = :wrkshpNameAlias"),
    @NamedQuery(name = "WorkshopDetailOthr.findByWrkshpPhone", query = "SELECT w FROM WorkshopDetailOthr w WHERE w.wrkshpPhone = :wrkshpPhone"),
    @NamedQuery(name = "WorkshopDetailOthr.findByWrkshpMobile", query = "SELECT w FROM WorkshopDetailOthr w WHERE w.wrkshpMobile = :wrkshpMobile"),
    @NamedQuery(name = "WorkshopDetailOthr.findByWrkshpEmail", query = "SELECT w FROM WorkshopDetailOthr w WHERE w.wrkshpEmail = :wrkshpEmail"),
    @NamedQuery(name = "WorkshopDetailOthr.findByWrkshpAddr", query = "SELECT w FROM WorkshopDetailOthr w WHERE w.wrkshpAddr = :wrkshpAddr"),
    @NamedQuery(name = "WorkshopDetailOthr.findByStateId", query = "SELECT w FROM WorkshopDetailOthr w WHERE w.stateId = :stateId"),
    @NamedQuery(name = "WorkshopDetailOthr.findByCityId", query = "SELECT w FROM WorkshopDetailOthr w WHERE w.cityId = :cityId"),
    @NamedQuery(name = "WorkshopDetailOthr.findByPinCode", query = "SELECT w FROM WorkshopDetailOthr w WHERE w.pinCode = :pinCode"),
    @NamedQuery(name = "WorkshopDetailOthr.findByTinNo", query = "SELECT w FROM WorkshopDetailOthr w WHERE w.tinNo = :tinNo"),
    @NamedQuery(name = "WorkshopDetailOthr.findByCstNo", query = "SELECT w FROM WorkshopDetailOthr w WHERE w.cstNo = :cstNo"),
    @NamedQuery(name = "WorkshopDetailOthr.findByInactiveFlg", query = "SELECT w FROM WorkshopDetailOthr w WHERE w.inactiveFlg = :inactiveFlg"),
    @NamedQuery(name = "WorkshopDetailOthr.findByInactiveUsrNo", query = "SELECT w FROM WorkshopDetailOthr w WHERE w.inactiveUsrNo = :inactiveUsrNo"),
    @NamedQuery(name = "WorkshopDetailOthr.findByInactiveDate", query = "SELECT w FROM WorkshopDetailOthr w WHERE w.inactiveDate = :inactiveDate"),
    @NamedQuery(name = "WorkshopDetailOthr.findByInactiveDsc", query = "SELECT w FROM WorkshopDetailOthr w WHERE w.inactiveDsc = :inactiveDsc"),
    @NamedQuery(name = "WorkshopDetailOthr.findByCrtUsrNo", query = "SELECT w FROM WorkshopDetailOthr w WHERE w.crtUsrNo = :crtUsrNo"),
    @NamedQuery(name = "WorkshopDetailOthr.findByCrtDate", query = "SELECT w FROM WorkshopDetailOthr w WHERE w.crtDate = :crtDate"),
    @NamedQuery(name = "WorkshopDetailOthr.findByCrtTrmnlNm", query = "SELECT w FROM WorkshopDetailOthr w WHERE w.crtTrmnlNm = :crtTrmnlNm"),
    @NamedQuery(name = "WorkshopDetailOthr.findByUpdUsrNo", query = "SELECT w FROM WorkshopDetailOthr w WHERE w.updUsrNo = :updUsrNo"),
    @NamedQuery(name = "WorkshopDetailOthr.findByUpdDate", query = "SELECT w FROM WorkshopDetailOthr w WHERE w.updDate = :updDate"),
    @NamedQuery(name = "WorkshopDetailOthr.findByUpdTrmnlNm", query = "SELECT w FROM WorkshopDetailOthr w WHERE w.updTrmnlNm = :updTrmnlNm"),
    @NamedQuery(name = "WorkshopDetailOthr.findByUpdCnt", query = "SELECT w FROM WorkshopDetailOthr w WHERE w.updCnt = :updCnt"),
    @NamedQuery(name = "WorkshopDetailOthr.findByPrvShowCust", query = "SELECT w FROM WorkshopDetailOthr w WHERE w.prvShowCust = :prvShowCust")})
public class WorkshopDetailOthr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "wrkshp_code")
    private String wrkshpCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "wrkshp_name")
    private String wrkshpName;
    @Size(max = 100)
    @Column(name = "wrkshp_name_alias")
    private String wrkshpNameAlias;
    @Size(max = 15)
    @Column(name = "wrkshp_phone")
    private String wrkshpPhone;
    @Size(max = 15)
    @Column(name = "wrkshp_mobile")
    private String wrkshpMobile;
    @Size(max = 50)
    @Column(name = "wrkshp_email")
    private String wrkshpEmail;
    @Size(max = 500)
    @Column(name = "wrkshp_addr")
    private String wrkshpAddr;
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
    @Column(name = "prv_show_cust")
    private Integer prvShowCust;

    public WorkshopDetailOthr() {
    }

    public WorkshopDetailOthr(String wrkshpCode) {
        this.wrkshpCode = wrkshpCode;
    }

    public WorkshopDetailOthr(String wrkshpCode, String wrkshpName, Short inactiveFlg, long crtUsrNo, Date crtDate, int updCnt) {
        this.wrkshpCode = wrkshpCode;
        this.wrkshpName = wrkshpName;
        this.inactiveFlg = inactiveFlg;
        this.crtUsrNo = crtUsrNo;
        this.crtDate = crtDate;
        this.updCnt = updCnt;
    }

    public String getWrkshpCode() {
        return wrkshpCode;
    }

    public void setWrkshpCode(String wrkshpCode) {
        this.wrkshpCode = wrkshpCode;
    }

    public String getWrkshpName() {
        return wrkshpName;
    }

    public void setWrkshpName(String wrkshpName) {
        this.wrkshpName = wrkshpName;
    }

    public String getWrkshpNameAlias() {
        return wrkshpNameAlias;
    }

    public void setWrkshpNameAlias(String wrkshpNameAlias) {
        this.wrkshpNameAlias = wrkshpNameAlias;
    }

    public String getWrkshpPhone() {
        return wrkshpPhone;
    }

    public void setWrkshpPhone(String wrkshpPhone) {
        this.wrkshpPhone = wrkshpPhone;
    }

    public String getWrkshpMobile() {
        return wrkshpMobile;
    }

    public void setWrkshpMobile(String wrkshpMobile) {
        this.wrkshpMobile = wrkshpMobile;
    }

    public String getWrkshpEmail() {
        return wrkshpEmail;
    }

    public void setWrkshpEmail(String wrkshpEmail) {
        this.wrkshpEmail = wrkshpEmail;
    }

    public String getWrkshpAddr() {
        return wrkshpAddr;
    }

    public void setWrkshpAddr(String wrkshpAddr) {
        this.wrkshpAddr = wrkshpAddr;
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

    public Integer getPrvShowCust() {
        return prvShowCust;
    }

    public void setPrvShowCust(Integer prvShowCust) {
        this.prvShowCust = prvShowCust;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wrkshpCode != null ? wrkshpCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WorkshopDetailOthr)) {
            return false;
        }
        WorkshopDetailOthr other = (WorkshopDetailOthr) object;
        if ((this.wrkshpCode == null && other.wrkshpCode != null) || (this.wrkshpCode != null && !this.wrkshpCode.equals(other.wrkshpCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ultimatetek.entity.WorkshopDetailOthr[ wrkshpCode=" + wrkshpCode + " ]";
    }

}
