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
package com.ultimatetek.controller;

import com.ultimatetek.config.JSFUtils;
import com.ultimatetek.entity.WorkshopDetailOthr;
import com.ultimatetek.model.WorkshopVO;
import com.ultimatetek.services.WorkShopUserService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author JunaidKhan
 */
@Component
@ManagedBean
@ViewScoped
@Data
public class ManageWorkshopView implements Serializable {

    private String wrkshpCode;
    private List<SelectItem> wrkshpUserList;
    private List<WorkshopVO> wrkshpDetails;
    private List<WorkshopVO> selectedWrkshp;
    private Boolean showDataTable = false;

    @Autowired
    private WorkShopUserService workShopUserService;

    @PostConstruct
    public void init() {
        this.wrkshpUserList = this.getWorkshopUserList();
    }

    public List<SelectItem> getWorkshopUserList() {
        List<SelectItem> List = workShopUserService.getWorkshopUserList();
        return List;
    }

    public String showWorkshopUser() {
        System.out.println(this.wrkshpCode);
        List<WorkshopDetailOthr> workshopDtlsList = null;
        if (this.wrkshpCode.equalsIgnoreCase("%")) {
            workshopDtlsList = workShopUserService.getWorkshopDtlsList();
        } else {
            workshopDtlsList = workShopUserService.getWorkshopListByCode(this.wrkshpCode);
        }
        List<WorkshopVO> workshopList = new ArrayList<>();
        for (WorkshopDetailOthr entityObj : workshopDtlsList) {
            WorkshopVO workshop = new WorkshopVO();
            workshop.setWrkshpCode(entityObj.getWrkshpCode());
            workshop.setWrkshpName(entityObj.getWrkshpName());
            workshop.setWrkshpEmail(entityObj.getWrkshpEmail());
            workshop.setWrkshpPhone(entityObj.getWrkshpPhone());
            workshop.setPinCode(entityObj.getPinCode());
            workshop.setCstNo(entityObj.getCstNo());
            workshop.setTinNo(entityObj.getTinNo());
            workshopList.add(workshop);
        }
        if (workshopList != null && !workshopList.isEmpty()) {
            this.showDataTable = true;
            this.setWrkshpDetails(workshopList);
        }
        return null;
    }

    public String deleteWorkshopUser() {
        try {
            this.workShopUserService.deleteWorkShopDtls(this.selectedWrkshp);
            JSFUtils.addFacesInformationMessage("Selected Workshop Deleted Successfully");
            return this.showWorkshopUser();
        } catch (Exception e) {
            JSFUtils.addFacesErrorMessage("Error when deleting Workshop");
        }
        return null;
    }
}
