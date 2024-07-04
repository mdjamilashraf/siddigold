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
import com.ultimatetek.model.OrderDetailsVO;
import com.ultimatetek.model.OrderSearchParamVO;
import com.ultimatetek.services.SalesOrderService;
import java.io.Serializable;
import java.util.Date;
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
 * @author Md Junaid Khan
 */
@Component
@ManagedBean
@ViewScoped
@Data
public class ResetOrdersView implements Serializable {

    private List<OrderDetailsVO> orders;
    private OrderDetailsVO selectedOrders;
    private OrderSearchParamVO searchParam = new OrderSearchParamVO();
    private List<OrderDetailsVO> selectedOrdrLst;
    private List<SelectItem> custList;
    private List<SelectItem> allCustList;

    private boolean showList = false;
    private Integer userGrp = (Integer) JSFUtils.getFromSession("userGrp");

    @Autowired
    private SalesOrderService salesOrderService;

    @PostConstruct
    public void init() {
        this.custList = salesOrderService.getCustList();
        this.allCustList = salesOrderService.getAllCustList();
    }

    public void fetchOrder() {

        Date checkToDate = this.searchParam.getTo();
        if (this.searchParam.getFrom() != null || (this.searchParam.getOrderNo() != null && this.searchParam.getFrom() != null
                && checkToDate != null) || this.searchParam.getOrderNo() != null) {
            if (checkToDate == null) {
                this.searchParam.setTo(this.searchParam.getFrom());
            } else if (checkToDate != null && this.searchParam.getOrderNo() != null) {
                this.searchParam.setTo(null);
            }
            this.orders = salesOrderService.getOrdersBySearchParam(this.searchParam);
            this.showList = this.orders != null && !this.orders.isEmpty();
        } else if (this.searchParam.getFrom() == null && checkToDate != null) {
            JSFUtils.addFacesErrorMessage("From date empty!");
        } else if (this.searchParam.getFrom() == null && checkToDate == null && this.searchParam.getOrderNo() == null) {
            JSFUtils.addFacesErrorMessage("please set atleast one field for filter!");
        }
    }

    public void downloadOrder() {

        try {
            if (!this.selectedOrdrLst.isEmpty()) {
                salesOrderService.downloadSelectedOrders(this.selectedOrdrLst);
                JSFUtils.addFacesInformationMessage("Orders Download Successfully");
            } else {
                JSFUtils.addFacesErrorMessage("Please Select Item to Download");
            }
        } catch (Exception e) {

        }

    }

    public void downloadAllOrder() {

        try {
            if (!this.orders.isEmpty()) {
                salesOrderService.downloadSelectedOrders(this.orders);
                this.fetchOrder();
                JSFUtils.addFacesInformationMessage("All Orders Downloaded Successfully");
            } else {
                JSFUtils.addFacesErrorMessage("unable to download orders Please Contact admin");
            }
        } catch (Exception e) {
        }
    }

    public void deleteOrder() {
        try {
            if (!this.selectedOrdrLst.isEmpty()) {
                salesOrderService.delSelectedorders(this.selectedOrdrLst);
                this.fetchOrder();
                JSFUtils.addFacesInformationMessage("Orders Deleted Successfully");
            } else {
                JSFUtils.addFacesErrorMessage("Please Select Item to Delete");
            }
        } catch (Exception e) {

        }
    }

    public void deleteAllOrders() {
        try {
            if (!this.orders.isEmpty()) {
                salesOrderService.delSelectedorders(this.orders);
//                JSFUtils.removeSessionBean("SalesOrderView");
                this.fetchOrder();
                JSFUtils.addFacesInformationMessage("All Orders Deleted Successfully");
            } else {
                JSFUtils.addFacesErrorMessage("unable to Delete all orders Please Contact admin ");
            }
        } catch (Exception e) {
            JSFUtils.addFacesErrorMessage("Server Error");
        }
    }

}
