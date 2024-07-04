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

import com.ultimatetek.entity.CustomerDetailOthr;
import com.ultimatetek.model.CustomerVO;
import com.ultimatetek.services.CustUserService;
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
public class ManageCustomerView implements Serializable {

    private String custCode;
    private List<SelectItem> custList;
    private List<CustomerVO> custDetails;
    private List<CustomerVO> selectedCust;
    private Boolean showDataTable = false;

    @Autowired
    private CustUserService custUserService;

    @PostConstruct
    public void init() {
        this.custList = this.getCustomerList();
    }

    private List<SelectItem> getCustomerList() {
        List<SelectItem> List = custUserService.getCustomerUserList();
        return List;
    }

    public String showCustomer() {
        System.out.println(this.custCode);
        List<CustomerDetailOthr> custDtlsList = null;
        if (this.custCode.equalsIgnoreCase("%")) {
            custDtlsList = custUserService.getCustomerDtlsList();
        } else {
            custDtlsList = custUserService.getCustomerListByCode(this.custCode);
        }
        List<CustomerVO> custList = new ArrayList<>();
        for (CustomerDetailOthr entityObj : custDtlsList) {
            CustomerVO cust = new CustomerVO();
            cust.setCustCode(entityObj.getCustCode());
            cust.setCustName(entityObj.getCustName());
            cust.setCustEmail(entityObj.getCustEmail());
            cust.setCustPhone(entityObj.getCustPhone());
            cust.setPinCode(entityObj.getPinCode());
            cust.setCstNo(entityObj.getCstNo());
            cust.setTinNo(entityObj.getTinNo());
            custList.add(cust);
        }
        if (custList != null && !custList.isEmpty()) {
            this.showDataTable = true;
            this.setCustDetails(custList);
        }
        return null;
    }

//    public String deleteCust() {
//        try {
//            this.custUserService.deleteCustDtls(this.selectedCust);
//            JSFUtils.addFacesInformationMessage("Selected Customer Deleted Successfully");
//            return this.showCustomer();
//        } catch (Exception e) {
//            JSFUtils.addFacesErrorMessage("Error when deleting customer");
//        }
//        return null;
//    }
}
