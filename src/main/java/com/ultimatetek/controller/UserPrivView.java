/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ultimatetek.controller;

import com.ultimatetek.config.JSFUtils;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 *
 * @author jamil
 */
@Component
@ManagedBean
@SessionScoped
@Data
public class UserPrivView implements Serializable {

    private Integer privAddAdminUser = 0;
    private Integer privEditAdminUser = 0;
    private Integer privAddWorkshopUser = 0;
    private Integer privEditWorkshopUser = 0;
    private Integer privAddCustomer = 0;
    private Integer privEditCustomer = 0;
    private Integer privItemMst = 0;
    private Integer privDfltMelting = 0;
    private Integer privWorkerMgmnt = 0;
    private Integer privEntryOrdr = 0;
    private Integer privOrdrStatus = 0;
    private Integer privWorkshopOrdr = 0;
    private Integer privResetOrders=0;
    private Integer userGrp = (Integer) JSFUtils.getFromSession("userGrp");

    @PostConstruct
    public void init() {
        switch (userGrp) {
            case 3:
                this.privEntryOrdr = 1;
                this.privOrdrStatus = 1;
                break;
                
            case 2:
                this.privWorkerMgmnt = 1;
                this.privEntryOrdr = 0;
                this.privOrdrStatus = 0;
                this.privWorkshopOrdr = 1;
                break;
            default:
                this.privAddAdminUser = 1;
                this.privEditAdminUser = 1;
                this.privAddWorkshopUser = 1;
                this.privEditWorkshopUser = 1;
                this.privAddCustomer = 1;
                this.privEditCustomer = 1;
                this.privItemMst = 1;
                this.privDfltMelting = 1;
                this.privWorkerMgmnt = 0;
                this.privEntryOrdr = 1;
                this.privOrdrStatus = 1;
                this.privWorkshopOrdr = 1;
                this.privResetOrders=1;
                break;
        }
    }

}
