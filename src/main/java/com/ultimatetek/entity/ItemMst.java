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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JunaidKhan
 */
@Entity
@Table(name = "item_mst")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemMst.findAll", query = "SELECT i FROM ItemMst i"),
    @NamedQuery(name = "ItemMst.findByItmCode", query = "SELECT i FROM ItemMst i WHERE i.itmCode = :itmCode"),
    @NamedQuery(name = "ItemMst.findByItmName", query = "SELECT i FROM ItemMst i WHERE i.itmName = :itmName"),
    @NamedQuery(name = "ItemMst.findByItmNameAlias", query = "SELECT i FROM ItemMst i WHERE i.itmNameAlias = :itmNameAlias"),
    @NamedQuery(name = "ItemMst.findByItmDesc", query = "SELECT i FROM ItemMst i WHERE i.itmDesc = :itmDesc"),
    @NamedQuery(name = "ItemMst.findByMainGrpCode", query = "SELECT i FROM ItemMst i WHERE i.mainGrpCode = :mainGrpCode"),
    @NamedQuery(name = "ItemMst.findByItmTypNo", query = "SELECT i FROM ItemMst i WHERE i.itmTypNo = :itmTypNo"),
    @NamedQuery(name = "ItemMst.findByCrtUsrNo", query = "SELECT i FROM ItemMst i WHERE i.crtUsrNo = :crtUsrNo"),
    @NamedQuery(name = "ItemMst.findByCrtDate", query = "SELECT i FROM ItemMst i WHERE i.crtDate = :crtDate"),
    @NamedQuery(name = "ItemMst.findByCrtTrmnlNm", query = "SELECT i FROM ItemMst i WHERE i.crtTrmnlNm = :crtTrmnlNm"),
    @NamedQuery(name = "ItemMst.findByUpdUsrNo", query = "SELECT i FROM ItemMst i WHERE i.updUsrNo = :updUsrNo"),
    @NamedQuery(name = "ItemMst.findByUpdDate", query = "SELECT i FROM ItemMst i WHERE i.updDate = :updDate"),
    @NamedQuery(name = "ItemMst.findByUpdTrmnlNm", query = "SELECT i FROM ItemMst i WHERE i.updTrmnlNm = :updTrmnlNm"),
    @NamedQuery(name = "ItemMst.findByUpdCnt", query = "SELECT i FROM ItemMst i WHERE i.updCnt = :updCnt")})
public class ItemMst implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "itm_code")
    private String itmCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "itm_name")
    private String itmName;
    @Size(max = 100)
    @Column(name = "itm_name_alias")
    private String itmNameAlias;
    @Size(max = 500)
    @Column(name = "itm_desc")
    private String itmDesc;
    @Size(max = 15)
    @Column(name = "main_grp_code")
    private String mainGrpCode;
    @Column(name = "itm_typ_no")
    private Short itmTypNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "crt_usr_no")
    private long crtUsrNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "crt_date")
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
    @Size(max = 50)
    @Column(name = "upd_trmnl_nm")
    private String updTrmnlNm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "upd_cnt")
    private int updCnt;

    public ItemMst() {
    }

    public ItemMst(String itmCode) {
        this.itmCode = itmCode;
    }

    public ItemMst(String itmCode, String itmName, long crtUsrNo, Date crtDate, int updCnt) {
        this.itmCode = itmCode;
        this.itmName = itmName;
        this.crtUsrNo = crtUsrNo;
        this.crtDate = crtDate;
        this.updCnt = updCnt;
    }

    public String getItmCode() {
        return itmCode;
    }

    public void setItmCode(String itmCode) {
        this.itmCode = itmCode;
    }

    public String getItmName() {
        return itmName;
    }

    public void setItmName(String itmName) {
        this.itmName = itmName;
    }

    public String getItmNameAlias() {
        return itmNameAlias;
    }

    public void setItmNameAlias(String itmNameAlias) {
        this.itmNameAlias = itmNameAlias;
    }

    public String getItmDesc() {
        return itmDesc;
    }

    public void setItmDesc(String itmDesc) {
        this.itmDesc = itmDesc;
    }

    public String getMainGrpCode() {
        return mainGrpCode;
    }

    public void setMainGrpCode(String mainGrpCode) {
        this.mainGrpCode = mainGrpCode;
    }

    public Short getItmTypNo() {
        return itmTypNo;
    }

    public void setItmTypNo(Short itmTypNo) {
        this.itmTypNo = itmTypNo;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itmCode != null ? itmCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemMst)) {
            return false;
        }
        ItemMst other = (ItemMst) object;
        if ((this.itmCode == null && other.itmCode != null) || (this.itmCode != null && !this.itmCode.equals(other.itmCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ultimatetek.entity.ItemMst[ itmCode=" + itmCode + " ]";
    }
    
}
