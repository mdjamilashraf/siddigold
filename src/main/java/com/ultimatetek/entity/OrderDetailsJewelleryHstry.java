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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * @author Md Junaid Khan
 */
//@Entity
//@Table(name = "order_details_jewellery_hstry")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findAll", query = "SELECT o FROM OrderDetailsJewelleryHstry o"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findBySlNo", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.slNo = :slNo"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByOrderDtlNo", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.orderDtlNo = :orderDtlNo"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByOrderNo", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.orderNo = :orderNo"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByItemCode", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.itemCode = :itemCode"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByItemUnit", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.itemUnit = :itemUnit"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByItemWeight", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.itemWeight = :itemWeight"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByItemSize", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.itemSize = :itemSize"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByItemQty", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.itemQty = :itemQty"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByMeltPer", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.meltPer = :meltPer"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByStamp", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.stamp = :stamp"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByHook", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.hook = :hook"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByDesignSample", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.designSample = :designSample"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findBySizeSample", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.sizeSample = :sizeSample"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByRefNo", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.refNo = :refNo"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByRemarks", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.remarks = :remarks"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByDays", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.days = :days"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByDueDate", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.dueDate = :dueDate"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByWrkshpCode", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.wrkshpCode = :wrkshpCode"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByRcvSample", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.rcvSample = :rcvSample"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByItemPrice", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.itemPrice = :itemPrice"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByPriceType", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.priceType = :priceType"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByOrderType", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.orderType = :orderType"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByCrtUsrNo", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.crtUsrNo = :crtUsrNo"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByCrtDate", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.crtDate = :crtDate"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByCrtTrmnlNm", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.crtTrmnlNm = :crtTrmnlNm"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByOrderStatus", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.orderStatus = :orderStatus"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByUpdUsrNo", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.updUsrNo = :updUsrNo"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByUpdDate", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.updDate = :updDate"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByUpdTrmnlNm", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.updTrmnlNm = :updTrmnlNm"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByUpdCnt", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.updCnt = :updCnt"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByRcrdNo", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.rcrdNo = :rcrdNo"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByWrkshpStatus", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.wrkshpStatus = :wrkshpStatus"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByWrkshpStatusDate", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.wrkshpStatusDate = :wrkshpStatusDate"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByWrkrName", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.wrkrName = :wrkrName"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByWrkshpCloseDate", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.wrkshpCloseDate = :wrkshpCloseDate"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByWrkshpCancelDate", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.wrkshpCancelDate = :wrkshpCancelDate"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByOrderCloseDate", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.orderCloseDate = :orderCloseDate"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByImgPath1", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.imgPath1 = :imgPath1"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByOrderCnfrmDate", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.orderCnfrmDate = :orderCnfrmDate"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByOrderAssignedDate", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.orderAssignedDate = :orderAssignedDate"),
    @NamedQuery(name = "OrderDetailsJewelleryHstry.findByOrderCancelledDate", query = "SELECT o FROM OrderDetailsJewelleryHstry o WHERE o.orderCancelledDate = :orderCancelledDate")})
public class OrderDetailsJewelleryHstry implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sl_no")
    private Integer slNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "order_dtl_no")
    private long orderDtlNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "order_no")
    private long orderNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "item_code")
    private String itemCode;
    @Size(max = 5)
    @Column(name = "item_unit")
    private String itemUnit;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "item_weight")
    private Float itemWeight;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
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
    @Size(max = 20)
    @Column(name = "stamp")
    private String stamp;
    @Size(max = 50)
    @Column(name = "hook")
    private String hook;
    @Size(max = 50)
    @Column(name = "design_sample")
    private String designSample;
    @Size(max = 50)
    @Column(name = "size_sample")
    private String sizeSample;
    @Size(max = 10)
    @Column(name = "ref_no")
    private String refNo;
    @Size(max = 100)
    @Column(name = "remarks")
    private String remarks;
    @Column(name = "days")
    private Short days;
    @Column(name = "due_date")
    @Temporal(TemporalType.DATE)
    private Date dueDate;
    @Size(max = 25)
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
    @Size(max = 1)
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
    @Size(max = 10)
    @Column(name = "crt_trmnl_nm")
    private String crtTrmnlNm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "order_status")
    private int orderStatus;
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
    @Column(name = "rcrd_no")
    private Integer rcrdNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "wrkshp_status")
    private int wrkshpStatus;
    @Column(name = "wrkshp_status_date")
    @Temporal(TemporalType.DATE)
    private Date wrkshpStatusDate;
    @Size(max = 100)
    @Column(name = "wrkr_name")
    private String wrkrName;
    @Column(name = "wrkshp_close_date")
    @Temporal(TemporalType.DATE)
    private Date wrkshpCloseDate;
    @Column(name = "wrkshp_cancel_date")
    @Temporal(TemporalType.DATE)
    private Date wrkshpCancelDate;
    @Column(name = "order_close_date")
    @Temporal(TemporalType.DATE)
    private Date orderCloseDate;
    @Size(max = 250)
    @Column(name = "img_path1")
    private String imgPath1;
    @Column(name = "order_cnfrm_date")
    @Temporal(TemporalType.DATE)
    private Date orderCnfrmDate;
    @Column(name = "order_assigned_date")
    @Temporal(TemporalType.DATE)
    private Date orderAssignedDate;
    @Column(name = "order_cancelled_date")
    @Temporal(TemporalType.DATE)
    private Date orderCancelledDate;

    public OrderDetailsJewelleryHstry() {
    }

    public OrderDetailsJewelleryHstry(Integer slNo) {
        this.slNo = slNo;
    }

    public OrderDetailsJewelleryHstry(Integer slNo, long orderDtlNo, long orderNo, String itemCode, String itemSize, int itemQty, float meltPer, short rcvSample, double itemPrice, int orderType, long crtUsrNo, Date crtDate, int orderStatus, int updCnt, int wrkshpStatus) {
        this.slNo = slNo;
        this.orderDtlNo = orderDtlNo;
        this.orderNo = orderNo;
        this.itemCode = itemCode;
        this.itemSize = itemSize;
        this.itemQty = itemQty;
        this.meltPer = meltPer;
        this.rcvSample = rcvSample;
        this.itemPrice = itemPrice;
        this.orderType = orderType;
        this.crtUsrNo = crtUsrNo;
        this.crtDate = crtDate;
        this.orderStatus = orderStatus;
        this.updCnt = updCnt;
        this.wrkshpStatus = wrkshpStatus;
    }

    public Integer getSlNo() {
        return slNo;
    }

    public void setSlNo(Integer slNo) {
        this.slNo = slNo;
    }

    public long getOrderDtlNo() {
        return orderDtlNo;
    }

    public void setOrderDtlNo(long orderDtlNo) {
        this.orderDtlNo = orderDtlNo;
    }

    public long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(long orderNo) {
        this.orderNo = orderNo;
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

    public Float getItemWeight() {
        return itemWeight;
    }

    public void setItemWeight(Float itemWeight) {
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

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
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

    public Integer getRcrdNo() {
        return rcrdNo;
    }

    public void setRcrdNo(Integer rcrdNo) {
        this.rcrdNo = rcrdNo;
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

    public String getImgPath1() {
        return imgPath1;
    }

    public void setImgPath1(String imgPath1) {
        this.imgPath1 = imgPath1;
    }

    public Date getOrderCnfrmDate() {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (slNo != null ? slNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderDetailsJewelleryHstry)) {
            return false;
        }
        OrderDetailsJewelleryHstry other = (OrderDetailsJewelleryHstry) object;
        if ((this.slNo == null && other.slNo != null) || (this.slNo != null && !this.slNo.equals(other.slNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ultimatetek.entity.OrderDetailsJewelleryHstry[ slNo=" + slNo + " ]";
    }
    
}
