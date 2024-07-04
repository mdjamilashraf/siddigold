/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ultimatetek.controller;

import com.ultimatetek.config.JSFUtils;
import com.ultimatetek.config.StringUtils;
import com.ultimatetek.dao.GeneralRepo;
import com.ultimatetek.entity.WorkshopDetailOthr;
import com.ultimatetek.model.WorkshopVO;
import com.ultimatetek.services.WorkShopUserService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author jamil
 */
@Component
@ManagedBean
@ViewScoped
@Data
public class AddWorkshopUserView implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddWorkshopUserView.class);
    private WorkshopVO wrkshpVO = new WorkshopVO();
    private List<WorkshopVO> workshopList;
    private WorkshopVO wrkDetails;
    private WorkshopVO selectedWorkshp;
    private Boolean showList = false;
    private Boolean readOnlyFld = false;
    private Boolean showSearchDtls = false;
    private Boolean pswdReqFld = false;
    private String wrkshpCode;
    private List<WorkshopVO> filteredWorkshop;
    private Long userId = (Long) JSFUtils.getFromSession("userId");

    @Autowired
    private WorkShopUserService workShopUserService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private GeneralRepo gnrRepo;

    @PostConstruct
    public void init() {
        this.getWorkShopUserList();
        if (workshopList != null && !workshopList.isEmpty()) {
            this.showList = true;
        } else {
            this.showList = false;
        }
        this.pswdReqFld = true;
    }

    public void searchByCode() {
        System.out.println(this.wrkshpVO.getWrkshpCode());
        WorkshopDetailOthr workshopDetailOthr = workShopUserService.getWorkshopDtlsByCode(this.wrkshpVO.getWrkshpCode());
        if (workshopDetailOthr != null) {
            WorkshopVO userVO = new WorkshopVO();
            userVO.setWrkshpCode(workshopDetailOthr.getWrkshpCode());
            userVO.setWrkshpName(workshopDetailOthr.getWrkshpName());
            userVO.setWrkshpEmail(workshopDetailOthr.getWrkshpEmail());
            userVO.setInactiveFlg(workshopDetailOthr.getInactiveFlg());
            userVO.setWrkshpAddr(workshopDetailOthr.getWrkshpAddr());
            userVO.setStateId(workshopDetailOthr.getStateId());
            userVO.setCityId(workshopDetailOthr.getCityId());
            userVO.setWrkshpPhone(workshopDetailOthr.getWrkshpPhone());
            userVO.setWrkshpMobile(workshopDetailOthr.getWrkshpMobile());
            userVO.setPinCode(workshopDetailOthr.getPinCode());
            userVO.setCstNo(workshopDetailOthr.getCstNo());
            userVO.setTinNo(workshopDetailOthr.getTinNo());
            if (workshopDetailOthr.getPrvShowCust().equals(1)) {
                userVO.setShwCustFlg(true);
            } else {
                userVO.setShwCustFlg(false);
            }
            this.setWrkshpVO(userVO);
            this.showSearchDtls = true;
        } else {
            this.showSearchDtls = false;
            this.pswdReqFld = true;
        }

    }

    public void getWorkShopUserList() {
        List<WorkshopDetailOthr> workshopDtlsList = workShopUserService.getWorkshopDtlsList();
        List<WorkshopVO> workshopList = new ArrayList<>();
        for (WorkshopDetailOthr entityObj : workshopDtlsList) {
            WorkshopVO workshop = new WorkshopVO();
            workshop.setWrkshpCode(entityObj.getWrkshpCode());
            workshop.setWrkshpName(entityObj.getWrkshpName());
            workshop.setWrkshpEmail(entityObj.getWrkshpEmail());
            workshop.setWrkshpAddr(entityObj.getWrkshpAddr());
            workshop.setStateId(entityObj.getStateId());
            workshop.setCityId(entityObj.getCityId());
            workshop.setWrkshpPhone(entityObj.getWrkshpPhone());
            workshop.setWrkshpMobile(entityObj.getWrkshpMobile());
            workshop.setInactiveFlg(entityObj.getInactiveFlg());
            workshop.setPinCode(entityObj.getPinCode());
            workshop.setCstNo(entityObj.getCstNo());
            workshop.setTinNo(entityObj.getTinNo());
            if (entityObj.getPrvShowCust().equals(1)) {
                workshop.setShwCustFlg(true);
            } else {
                workshop.setShwCustFlg(false);
            }
            workshopList.add(workshop);
        }
        this.setWorkshopList(workshopList);
        this.showList = true;
    }

    public void resetData() {
        this.wrkshpVO.setWrkshpCode(null);
        this.wrkshpVO.setWrkshpName(null);
        this.wrkshpVO.setWrkshpEmail(null);
        this.wrkshpVO.setWrkshpAddr(null);
        this.wrkshpVO.setStateId(null);
        this.wrkshpVO.setCityId(null);
        this.wrkshpVO.setWrkshpPhone(null);
        this.wrkshpVO.setInactiveFlg(null);
        this.wrkshpVO.setPinCode(null);
        this.wrkshpVO.setCstNo(null);
        this.wrkshpVO.setTinNo(null);
        JSFUtils.reloadThePage();
    }

    public String addUserWorkshop() {
        try {
            workShopUserService.saveUserWorkshop(this.wrkshpVO, this.userId);
            this.getWorkShopUserList();
            return "addWorkshopUser";
        } catch (Exception e) {
            e.printStackTrace();
            JSFUtils.addFacesErrorMessageRedirect("Server Error");
            return null;
        }

    }

    public void editWorkShop() {
        this.setWrkshpVO(this.selectedWorkshp);
        this.readOnlyFld = true;
        WorkshopDetailOthr workshopDetailOthr = workShopUserService.getWorkshopDtlsByCode(this.selectedWorkshp.getWrkshpCode());
        if (workshopDetailOthr != null) {
            this.pswdReqFld = false;
        } else {
            this.pswdReqFld = true;
        }

    }

    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (StringUtils.isEmpty(filterText)) {
            return true;
        }
        WorkshopVO wrkshp = (WorkshopVO) value;
        if (wrkshp == null || wrkshp.getWrkshpName() == null) {
            return true;
        }
        return wrkshp.getWrkshpCode().toLowerCase().contains(filterText)
                || wrkshp.getWrkshpName().toLowerCase().contains(filterText);

    }

}
