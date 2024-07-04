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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author ULTCPU20
 */
@Entity
@Table(name = "worker")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Worker.findAll", query = "SELECT w FROM Worker w"),
    @NamedQuery(name = "Worker.findByWrkrId", query = "SELECT w FROM Worker w WHERE w.wrkrId = :wrkrId"),
    @NamedQuery(name = "Worker.findByWrkrName", query = "SELECT w FROM Worker w WHERE w.wrkrName = :wrkrName"),
    @NamedQuery(name = "Worker.findByWrkrNameAlias", query = "SELECT w FROM Worker w WHERE w.wrkrNameAlias = :wrkrNameAlias"),
    @NamedQuery(name = "Worker.findByWrkrMobile", query = "SELECT w FROM Worker w WHERE w.wrkrMobile = :wrkrMobile"),
    @NamedQuery(name = "Worker.findByInactiveFlg", query = "SELECT w FROM Worker w WHERE w.inactiveFlg = :inactiveFlg"),
    @NamedQuery(name = "Worker.findByCrtUsrNo", query = "SELECT w FROM Worker w WHERE w.crtUsrNo = :crtUsrNo"),
    @NamedQuery(name = "Worker.findByCrtDate", query = "SELECT w FROM Worker w WHERE w.crtDate = :crtDate"),
    @NamedQuery(name = "Worker.findByCrtTrmnlNm", query = "SELECT w FROM Worker w WHERE w.crtTrmnlNm = :crtTrmnlNm"),
    @NamedQuery(name = "Worker.findByUpdUsrNo", query = "SELECT w FROM Worker w WHERE w.updUsrNo = :updUsrNo"),
    @NamedQuery(name = "Worker.findByUpdDate", query = "SELECT w FROM Worker w WHERE w.updDate = :updDate"),
    @NamedQuery(name = "Worker.findByUpdTrmnlNm", query = "SELECT w FROM Worker w WHERE w.updTrmnlNm = :updTrmnlNm"),
    @NamedQuery(name = "Worker.findByUpdCnt", query = "SELECT w FROM Worker w WHERE w.updCnt = :updCnt")})
public class Worker implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "wrkr_id")
    private Long wrkrId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "wrkr_name")
    private String wrkrName;
    @Size(max = 100)
    @Column(name = "wrkr_name_alias")
    private String wrkrNameAlias;
    @Size(max = 15)
    @Column(name = "wrkr_mobile")
    private String wrkrMobile;
    @Basic(optional = false)
    @NotNull
    @Column(name = "inactive_flg")
    private short inactiveFlg;
    @Basic(optional = false)
    @NotNull
    @Column(name = "crt_usr_no", updatable = false)
    private long crtUsrNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "crt_date", updatable = false)
    @Temporal(TemporalType.DATE)
    private Date crtDate;
    @Size(max = 50)
    @Column(name = "crt_trmnl_nm")
    private String crtTrmnlNm;
    @Column(name = "upd_usr_no")
    private BigInteger updUsrNo;
    @Column(name = "upd_date")
    @Temporal(TemporalType.DATE)
    private Date updDate;
    @Size(max = 2147483647)
    @Column(name = "upd_trmnl_nm")
    private String updTrmnlNm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "upd_cnt")
    private int updCnt;
    @JoinColumn(name = "wrkshp_code", referencedColumnName = "wrkshp_code")
    @ManyToOne(optional = false)
    private WorkshopDetailOthr wrkshpCode;

    public Worker() {
    }

    public Worker(Long wrkrId) {
        this.wrkrId = wrkrId;
    }

    public Worker(Long wrkrId, String wrkrName, short inactiveFlg, long crtUsrNo, Date crtDate, int updCnt) {
        this.wrkrId = wrkrId;
        this.wrkrName = wrkrName;
        this.inactiveFlg = inactiveFlg;
        this.crtUsrNo = crtUsrNo;
        this.crtDate = crtDate;
        this.updCnt = updCnt;
    }

    public Long getWrkrId() {
        return wrkrId;
    }

    public void setWrkrId(Long wrkrId) {
        this.wrkrId = wrkrId;
    }

    public String getWrkrName() {
        return wrkrName;
    }

    public void setWrkrName(String wrkrName) {
        this.wrkrName = wrkrName;
    }

    public String getWrkrNameAlias() {
        return wrkrNameAlias;
    }

    public void setWrkrNameAlias(String wrkrNameAlias) {
        this.wrkrNameAlias = wrkrNameAlias;
    }

    public String getWrkrMobile() {
        return wrkrMobile;
    }

    public void setWrkrMobile(String wrkrMobile) {
        this.wrkrMobile = wrkrMobile;
    }

    public short getInactiveFlg() {
        return inactiveFlg;
    }

    public void setInactiveFlg(short inactiveFlg) {
        this.inactiveFlg = inactiveFlg;
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

    public WorkshopDetailOthr getWrkshpCode() {
        return wrkshpCode;
    }

    public void setWrkshpCode(WorkshopDetailOthr wrkshpCode) {
        this.wrkshpCode = wrkshpCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wrkrId != null ? wrkrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Worker)) {
            return false;
        }
        Worker other = (Worker) object;
        if ((this.wrkrId == null && other.wrkrId != null) || (this.wrkrId != null && !this.wrkrId.equals(other.wrkrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ultimatetek.entity.Worker[ wrkrId=" + wrkrId + " ]";
    }

}
