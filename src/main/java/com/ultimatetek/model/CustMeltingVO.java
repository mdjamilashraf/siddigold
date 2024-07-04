/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ultimatetek.model;

import java.io.Serializable;

/**
 *
 * @author jamil
 */
public class CustMeltingVO implements Serializable {

    private Integer meltStampId;

    public Integer getMeltStampId() {
        return meltStampId;
    }

    public void setMeltStampId(Integer meltStampId) {
        this.meltStampId = meltStampId;
    }
    private Integer index;
    private Integer meltPer;
    private String stamp;

    public CustMeltingVO() {
    }

    public CustMeltingVO(Integer index, Integer meltPer, String stamp) {
        this.index = index;
        this.meltPer = meltPer;
        this.stamp = stamp;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getMeltPer() {
        return meltPer;
    }

    public void setMeltPer(Integer meltPer) {
        this.meltPer = meltPer;
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

}
