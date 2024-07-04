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
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JunaidKhan
 */
@Entity
@Table(name = "user_group")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserGroup.findAll", query = "SELECT u FROM UserGroup u"),
    @NamedQuery(name = "UserGroup.findByGroupNo", query = "SELECT u FROM UserGroup u WHERE u.groupNo = :groupNo"),
    @NamedQuery(name = "UserGroup.findByGroupName", query = "SELECT u FROM UserGroup u WHERE u.groupName = :groupName"),
    @NamedQuery(name = "UserGroup.findByAllowSalesOrder", query = "SELECT u FROM UserGroup u WHERE u.allowSalesOrder = :allowSalesOrder"),
    @NamedQuery(name = "UserGroup.findByAllowSalesReturn", query = "SELECT u FROM UserGroup u WHERE u.allowSalesReturn = :allowSalesReturn"),
    @NamedQuery(name = "UserGroup.findByAllowSalesTrans", query = "SELECT u FROM UserGroup u WHERE u.allowSalesTrans = :allowSalesTrans"),
    @NamedQuery(name = "UserGroup.findByAllowAddAccnt", query = "SELECT u FROM UserGroup u WHERE u.allowAddAccnt = :allowAddAccnt"),
    @NamedQuery(name = "UserGroup.findByAllowAccntModule", query = "SELECT u FROM UserGroup u WHERE u.allowAccntModule = :allowAccntModule"),
    @NamedQuery(name = "UserGroup.findByAllowPurchaseModule", query = "SELECT u FROM UserGroup u WHERE u.allowPurchaseModule = :allowPurchaseModule"),
    @NamedQuery(name = "UserGroup.findByAllowSalesModule", query = "SELECT u FROM UserGroup u WHERE u.allowSalesModule = :allowSalesModule"),
    @NamedQuery(name = "UserGroup.findByInactiveFlg", query = "SELECT u FROM UserGroup u WHERE u.inactiveFlg = :inactiveFlg"),
    @NamedQuery(name = "UserGroup.findByCrtUsrNo", query = "SELECT u FROM UserGroup u WHERE u.crtUsrNo = :crtUsrNo"),
    @NamedQuery(name = "UserGroup.findByCrtDate", query = "SELECT u FROM UserGroup u WHERE u.crtDate = :crtDate"),
    @NamedQuery(name = "UserGroup.findByCrtTrmnlNm", query = "SELECT u FROM UserGroup u WHERE u.crtTrmnlNm = :crtTrmnlNm"),
    @NamedQuery(name = "UserGroup.findByUpdUsrNo", query = "SELECT u FROM UserGroup u WHERE u.updUsrNo = :updUsrNo"),
    @NamedQuery(name = "UserGroup.findByUpdDate", query = "SELECT u FROM UserGroup u WHERE u.updDate = :updDate"),
    @NamedQuery(name = "UserGroup.findByUpdTrmnlNm", query = "SELECT u FROM UserGroup u WHERE u.updTrmnlNm = :updTrmnlNm"),
    @NamedQuery(name = "UserGroup.findByUpdCnt", query = "SELECT u FROM UserGroup u WHERE u.updCnt = :updCnt")})
public class UserGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "group_no")
    private Integer groupNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "group_name")
    private String groupName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "allow_sales_order")
    private short allowSalesOrder;
    @Basic(optional = false)
    @NotNull
    @Column(name = "allow_sales_return")
    private short allowSalesReturn;
    @Basic(optional = false)
    @NotNull
    @Column(name = "allow_sales_trans")
    private short allowSalesTrans;
    @Basic(optional = false)
    @NotNull
    @Column(name = "allow_add_accnt")
    private short allowAddAccnt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "allow_accnt_module")
    private short allowAccntModule;
    @Basic(optional = false)
    @NotNull
    @Column(name = "allow_purchase_module")
    private short allowPurchaseModule;
    @Basic(optional = false)
    @NotNull
    @Column(name = "allow_sales_module")
    private short allowSalesModule;
    @Basic(optional = false)
    @NotNull
    @Column(name = "inactive_flg")
    private int inactiveFlg;
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
    @Size(min = 1, max = 2147483647)
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupNo")
    private Collection<UserDetails> userDetailsCollection;

    public UserGroup() {
    }

    public UserGroup(Integer groupNo) {
        this.groupNo = groupNo;
    }

    public UserGroup(Integer groupNo, String groupName, short allowSalesOrder, short allowSalesReturn, short allowSalesTrans, short allowAddAccnt, short allowAccntModule, short allowPurchaseModule, short allowSalesModule, int inactiveFlg, long crtUsrNo, Date crtDate, String crtTrmnlNm, int updCnt) {
        this.groupNo = groupNo;
        this.groupName = groupName;
        this.allowSalesOrder = allowSalesOrder;
        this.allowSalesReturn = allowSalesReturn;
        this.allowSalesTrans = allowSalesTrans;
        this.allowAddAccnt = allowAddAccnt;
        this.allowAccntModule = allowAccntModule;
        this.allowPurchaseModule = allowPurchaseModule;
        this.allowSalesModule = allowSalesModule;
        this.inactiveFlg = inactiveFlg;
        this.crtUsrNo = crtUsrNo;
        this.crtDate = crtDate;
        this.crtTrmnlNm = crtTrmnlNm;
        this.updCnt = updCnt;
    }

    public Integer getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(Integer groupNo) {
        this.groupNo = groupNo;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public short getAllowSalesOrder() {
        return allowSalesOrder;
    }

    public void setAllowSalesOrder(short allowSalesOrder) {
        this.allowSalesOrder = allowSalesOrder;
    }

    public short getAllowSalesReturn() {
        return allowSalesReturn;
    }

    public void setAllowSalesReturn(short allowSalesReturn) {
        this.allowSalesReturn = allowSalesReturn;
    }

    public short getAllowSalesTrans() {
        return allowSalesTrans;
    }

    public void setAllowSalesTrans(short allowSalesTrans) {
        this.allowSalesTrans = allowSalesTrans;
    }

    public short getAllowAddAccnt() {
        return allowAddAccnt;
    }

    public void setAllowAddAccnt(short allowAddAccnt) {
        this.allowAddAccnt = allowAddAccnt;
    }

    public short getAllowAccntModule() {
        return allowAccntModule;
    }

    public void setAllowAccntModule(short allowAccntModule) {
        this.allowAccntModule = allowAccntModule;
    }

    public short getAllowPurchaseModule() {
        return allowPurchaseModule;
    }

    public void setAllowPurchaseModule(short allowPurchaseModule) {
        this.allowPurchaseModule = allowPurchaseModule;
    }

    public short getAllowSalesModule() {
        return allowSalesModule;
    }

    public void setAllowSalesModule(short allowSalesModule) {
        this.allowSalesModule = allowSalesModule;
    }

    public int getInactiveFlg() {
        return inactiveFlg;
    }

    public void setInactiveFlg(int inactiveFlg) {
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

    @XmlTransient
    public Collection<UserDetails> getUserDetailsCollection() {
        return userDetailsCollection;
    }

    public void setUserDetailsCollection(Collection<UserDetails> userDetailsCollection) {
        this.userDetailsCollection = userDetailsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupNo != null ? groupNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserGroup)) {
            return false;
        }
        UserGroup other = (UserGroup) object;
        if ((this.groupNo == null && other.groupNo != null) || (this.groupNo != null && !this.groupNo.equals(other.groupNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ultimatetek.entity.UserGroup[ groupNo=" + groupNo + " ]";
    }

}
