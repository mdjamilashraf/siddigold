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
 * @author jamil
 */
@Entity
@Table(name = "order_details_jewellery")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderDetailsJewellery.findAll", query = "SELECT o FROM OrderDetailsJewellery o")})
public class OrderDetailsJewellery implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "order_dtl_no")
    private Long orderDtlNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "item_code")
    private String itemCode;
    @Column(name = "item_unit")
    private String itemUnit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "item_weight")
    private float itemWeight;
    @Basic(optional = false)
    @NotNull
    @Column(name = "item_size")
    private String itemSize;
    @Basic(optional = false)
    @NotNull
    @Column(name = "item_qty")
    private int itemQty;
    @Basic(optional = false)
    @NotNull
    @Column(name = "melt_per")
    private float meltPer;
    @Column(name = "stamp")
    private String stamp;
    @Column(name = "hook")
    private String hook;
    @Column(name = "design_sample")
    private String designSample;
    @Column(name = "size_sample")
    private String sizeSample;
    @Column(name = "ref_no")
    private String refNo;
    @Column(name = "remarks")
    private String remarks;
    @Column(name = "days")
    private Short days;
    @Column(name = "due_date")
    @Temporal(TemporalType.DATE)
    private Date dueDate;
    //@Size(max = 10)
    @Column(name = "wrkshp_code")
    private String wrkshpCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rcv_sample")
    private short rcvSample;
    @Basic(optional = false)
    @NotNull
    @Column(name = "item_price")
    private double itemPrice;
    @Column(name = "price_type")
    private String priceType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "order_type")
    private int orderType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "crt_usr_no")
    private long crtUsrNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "crt_date")
    @Temporal(TemporalType.DATE)
    private Date crtDate;
    @Column(name = "crt_trmnl_nm")
    private String crtTrmnlNm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "order_status")
    private Integer orderStatus;
    @Column(name = "upd_usr_no")
    private Long updUsrNo;
    @Column(name = "upd_date")
    @Temporal(TemporalType.DATE)
    private Date updDate;
    @Column(name = "upd_trmnl_nm")
    private String updTrmnlNm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "upd_cnt")
    private Integer updCnt;
    @Column(name = "rcrd_no")
    private Integer rcrdNo;
    @JoinColumn(name = "order_no", referencedColumnName = "order_no")
    @ManyToOne(optional = false)
    private SalesOrder salesOrder;
    @Column(name = "wrkshp_status")
    private Integer wrkshpStatus;
    @Temporal(TemporalType.DATE)
    @Column(name = "wrkshp_status_date")
    private Date wrkshpStatusDate;
    @Column(name = "wrkr_name")
    private String wrkrName;
    @Temporal(TemporalType.DATE)
    @Column(name = "wrkshp_close_date")
    private Date wrkshpCloseDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "wrkshp_cancel_date")
    private Date wrkshpCancelDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "order_close_date")
    private Date orderCloseDate;
    @Size(max = 250)
    @Column(name = "img_path1")
    private String imgPath1;
    @Temporal(TemporalType.DATE)
    @Column(name = "wrkshp_issue_wrkr_date")
    private Date wrkshpIssueWrkrDate;
//    @Temporal(TemporalType.DATE)
//    @Column(name = "order_cnfrm_date")
//    private Date orderCnfrmDate;
//    @Temporal(TemporalType.DATE)
//    @Column(name = "order_assigned_date")
//    private Date orderAssignedDate;
//    @Temporal(TemporalType.DATE)
//    @Column(name = "order_cancelled_date")
//    private Date orderCancelledDate;


    /*   public Date getOrderCnfrmDate() {
        return orderCnfrmDate;
    }

    public void setOrderCnfrmDate(Date orderCnfrmDate) {
        this.orderCnfrmDate = orderCnfrmDate;
    }

    public Date getOrderAssignedDate() {
        return orderAssignedDate;
    }

    public void setOrderAssignedDate(Date orderAssignedDate) {
        this.orderAssignedDate = orderAssignedDate;
    }

    public Date getOrderCancelledDate() {
        return orderCancelledDate;
    }

    public void setOrderCancelledDate(Date orderCancelledDate) {
        this.orderCancelledDate = orderCancelledDate;
    }
     */
    public String getImgPath1() {
        return imgPath1;
    }

    public void setImgPath1(String imgPath1) {
        this.imgPath1 = imgPath1;
    }

    public Long getOrderDtlNo() {
        return orderDtlNo;
    }

    public void setOrderDtlNo(Long orderDtlNo) {
        this.orderDtlNo = orderDtlNo;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemUnit() {
        return itemUnit;
    }

    public void setItemUnit(String itemUnit) {
        this.itemUnit = itemUnit;
    }

    public float getItemWeight() {
        return itemWeight;
    }

    public void setItemWeight(float itemWeight) {
        this.itemWeight = itemWeight;
    }

    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(String itemSize) {
        this.itemSize = itemSize;
    }

    public int getItemQty() {
        return itemQty;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }

    public float getMeltPer() {
        return meltPer;
    }

    public void setMeltPer(float meltPer) {
        this.meltPer = meltPer;
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

    public String getHook() {
        return hook;
    }

    public void setHook(String hook) {
        this.hook = hook;
    }

    public String getDesignSample() {
        return designSample;
    }

    public void setDesignSample(String designSample) {
        this.designSample = designSample;
    }

    public String getSizeSample() {
        return sizeSample;
    }

    public void setSizeSample(String sizeSample) {
        this.sizeSample = sizeSample;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Short getDays() {
        return days;
    }

    public void setDays(Short days) {
        this.days = days;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getWrkshpCode() {
        return wrkshpCode;
    }

    public void setWrkshpCode(String wrkshpCode) {
        this.wrkshpCode = wrkshpCode;
    }

    public short getRcvSample() {
        return rcvSample;
    }

    public void setRcvSample(short rcvSample) {
        this.rcvSample = rcvSample;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
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

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
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

    public int getRcrdNo() {
        return rcrdNo;
    }

    public void setRcrdNo(int rcrdNo) {
        this.rcrdNo = rcrdNo;
    }

    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }

    public int getWrkshpStatus() {
        return wrkshpStatus;
    }

    public void setWrkshpStatus(int wrkshpStatus) {
        this.wrkshpStatus = wrkshpStatus;
    }

    public Date getWrkshpStatusDate() {
        return wrkshpStatusDate;
    }

    public void setWrkshpStatusDate(Date wrkshpStatusDate) {
        this.wrkshpStatusDate = wrkshpStatusDate;
    }

    public String getWrkrName() {
        return wrkrName;
    }

    public void setWrkrName(String wrkrName) {
        this.wrkrName = wrkrName;
    }

    public Date getWrkshpCloseDate() {
        return wrkshpCloseDate;
    }

    public void setWrkshpCloseDate(Date wrkshpCloseDate) {
        this.wrkshpCloseDate = wrkshpCloseDate;
    }

    public Date getWrkshpCancelDate() {
        return wrkshpCancelDate;
    }

    public void setWrkshpCancelDate(Date wrkshpCancelDate) {
        this.wrkshpCancelDate = wrkshpCancelDate;
    }

    public Date getOrderCloseDate() {
        return orderCloseDate;
    }

    public void setOrderCloseDate(Date orderCloseDate) {
        this.orderCloseDate = orderCloseDate;
    }

    public Date getWrkshpIssueWrkrDate() {
        return wrkshpIssueWrkrDate;
    }

    public void setWrkshpIssueWrkrDate(Date wrkshpIssueWrkrDate) {
        this.wrkshpIssueWrkrDate = wrkshpIssueWrkrDate;
    }

}
