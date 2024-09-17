/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ultimatetek.controller;

import com.ultimatetek.config.DateUtils;
import com.ultimatetek.config.JSFUtils;
import com.ultimatetek.config.SalesOrderUtils;
import com.ultimatetek.model.OrderDetailsVO;
import com.ultimatetek.model.OrderSearchParamVO;
import com.ultimatetek.services.SalesOrderService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import lombok.Data;
import org.primefaces.PrimeFaces;
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
public class WorkshopOrdersView implements Serializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkshopOrdersView.class);
    private List<OrderDetailsVO> orders;
    private List<OrderDetailsVO> selectedOrdrLst;
    private OrderDetailsVO selectedOrders;
    private OrderSearchParamVO searchParam = new OrderSearchParamVO();
    private String custCode;
    private Integer ordrStatus = 2;
    private Integer wrkshpStatus = 1;
    private Integer rcrdNo;
    private String wrkshpCode;
    private String workerCode;
    private String itemCode;
    private String custRefNo;
    private Integer orderTyp=0;
    private Date dueDate;
    private Date statusDate;
    private Long orderNo;
    private List<SelectItem> custList;
    private List<SelectItem> allCustList;
    private List<SelectItem> ordrStatusList;
    private List<SelectItem> workshopList;
    private List<SelectItem> orderTypList;
    private List<SelectItem> workerList;
    private List<SelectItem> wrkshpStatusList;
    private List<SelectItem> priorityTypList;
    private boolean showList = false;
    private Integer updatedOrdrStatus;
    private Map<String, String> mapItemCode = new HashMap<>();
    private Long userId = (Long)JSFUtils.getFromSession("userId");
    private String wrkshpUsrCode = (String)JSFUtils.getFromSession("userCode");
    private Integer userGrp = (Integer) JSFUtils.getFromSession("userGrp");
    private float totalOrdrWt = 0f;
    private List<SelectItem> printColumns;
    private String[] selectedColumn;
    
    @Autowired
    private SalesOrderService salesOrderService;
    @PostConstruct
    public void init() {
        this.custList = salesOrderService.getCustList();
        this.allCustList = salesOrderService.getAllCustList();
        this.ordrStatusList = salesOrderService.getOrdrStatusList();
        this.wrkshpStatusList = salesOrderService.getWrkshpStatusList();
        this.workshopList = salesOrderService.getWorkshopList();
        
        this.orderTypList = salesOrderService.getOrderTypList();
        this.priorityTypList = salesOrderService.getPriorityTypList();
        this.mapItemCode = salesOrderService.getItemCodeMap();
        if (this.userGrp == 2) {
            this.wrkshpCode = this.wrkshpUsrCode;
            this.searchParam.setWrkshpCode(this.wrkshpUsrCode);
            this.workerList = salesOrderService.getWorkerList(this.wrkshpCode);
        } else {
            this.workerList = salesOrderService.getWorkerList(null);
        }
       //this.orders = salesOrderService.getSalesOrderListByParam(this.custCode, this.orderNo,this.rcrdNo, null, this.wrkshpCode, this.orderTyp, this.itemCode, this.custRefNo, this.wrkshpStatus, this.workerCode, this.dueDate, this.statusDate);
       this.searchParam.setWrkshpStatus(1);
       this.orders = salesOrderService.getOrdersBySearchParam(this.searchParam);
       this.showList = this.orders != null && !this.orders.isEmpty();
       this.totalOrdrWt = this.showList ? SalesOrderUtils.calculateTotleWt(this.orders) : 0;
       this.getColumns();
    }
    public void fetchOrder() {
        if (this.userGrp == 2) {
            this.wrkshpCode = this.wrkshpUsrCode;
            this.searchParam.setWrkshpCode(this.wrkshpUsrCode);
        }
        //this.searchParam.setOrderStatus(2);
        this.orders = salesOrderService.getOrdersBySearchParam(this.searchParam);
        //this.orders = salesOrderService.getSalesOrderListByParam(this.custCode, this.orderNo,this.rcrdNo, null,this.wrkshpCode, this.orderTyp, this.itemCode, this.custRefNo, this.wrkshpStatus, this.workerCode, this.dueDate, this.statusDate);
        this.showList = this.orders != null && !this.orders.isEmpty();
        this.totalOrdrWt = this.showList ? SalesOrderUtils.calculateTotleWt(this.orders) : 0;
    }
    public List<String> completeText(String query) {
        List<String> results = salesOrderService.getItemCodeList("%"+query+"%");
        System.out.print(results.size());
        return results;
    }

    private String findOrdrStatusName(String statusTyp) {
        switch (statusTyp) {
            case "1":
                return "Received";
            case "2":
                return "Issued to worker";
            case "3":
                return "Closed";
            case "4":
                return "Cancelled";
//            case "5":
//                return "Pending";
            default:
                break;
        }
        return null;
    }
    
    public String updateOrderDtl () {
        try {
            for (OrderDetailsVO ordrDtl : this.orders) {
                if (ordrDtl.getRowStatus().equalsIgnoreCase("updated")) {
                    LOGGER.info("===="+ordrDtl.getOrderNo()+"\t"+ordrDtl.getWrkshpStatus()+"\t"+ordrDtl.getSrlNo());
                    salesOrderService.updateWrkshpStatus(ordrDtl);
                }
            }
            JSFUtils.addFacesInformationMessage("Successfully updated");
            this.fetchOrder();
        } catch (Exception e) {
            e.printStackTrace();
            JSFUtils.addFacesErrorMessage("Error when update orders");
        }
        return null;
    }
    public void updateWrkshpStatus() {
        for (OrderDetailsVO ordrDtl : this.orders) {
            ordrDtl.setWrkshpStatus(this.updatedOrdrStatus);
            ordrDtl.setWrkshpStatusNm(findOrdrStatusName(String.valueOf(this.updatedOrdrStatus)));
            ordrDtl.setRowStatus("updated");
        }
    }
    
    public void changeWrkrNm(OrderDetailsVO ordrDtlVO) {
        if (orders != null && !orders.isEmpty()) {
            for (OrderDetailsVO orderDtl : orders) {
                if (orderDtl.getOrderNo().equals(ordrDtlVO.getOrderNo())
                        && orderDtl.getSrlNo() ==  ordrDtlVO.getSrlNo()) {
                    orderDtl.setWrkrName(ordrDtlVO.getWrkrName());
                    orderDtl.setRowStatus("updated");
                    break;
                }
            }
        }
    }
    
    public void changeWrkshpStatus(OrderDetailsVO ordrDtlVO) {
        if (orders != null && !orders.isEmpty()) {
            for (OrderDetailsVO orderDtl : orders) {
                if (orderDtl.getOrderNo().equals(ordrDtlVO.getOrderNo())
                        && orderDtl.getSrlNo() ==  ordrDtlVO.getSrlNo()) {
                    String statusTyp = String.valueOf(ordrDtlVO.getWrkshpStatus());
                    orderDtl.setWrkshpStatusNm(this.findOrdrStatusName(statusTyp));
                    orderDtl.setRowStatus("updated");
                    break;
                }
            }
        }
    }
    
    public void onSelectOrdrs(OrderDetailsVO ordrDtl) {
        System.out.println("==========="+this.selectedOrders);
        this.setSelectedOrders(ordrDtl);
        System.out.println(this.selectedOrders);
    }
    public void editCustOrder() {
        if (this.selectedOrders != null) {
            System.out.println("value is ===="+this.selectedOrders);
            this.selectedOrders.setUpdUsrNo(this.userId);
            this.selectedOrders.setUpdDate(DateUtils.getTodayDate());
            salesOrderService.editCustOrder(this.selectedOrders);
            this.fetchOrder();
        } else {
            System.out.println(this.selectedOrders);
        }
    }
    public void deleteCustOrder() {
        this.fetchOrder();
    }

    public void printReport() {
            String[] selectColums=this.selectedColumn;
        if (this.selectedOrdrLst.size()>=1 && selectColums.length>=1) {
            System.out.println(this.selectedOrdrLst.size());
            JSFUtils.storeOnSession("OrderDtls", this.selectedOrdrLst);
            JSFUtils.storeOnSession("ReportColumn", selectColums);
            PrimeFaces.current().executeScript("printReport()");
        } else {
            JSFUtils.addFacesErrorMessage("Please Select atleast One Column or Selection Required to take report");
        }
    }
    public void printReportList() {
        String[] selectColums=this.selectedColumn;
        if (this.selectedOrdrLst.size()>=1 && selectColums.length>=1) {
            this.selectedOrdrLst.sort((o1, o2)-> o1.getOrderDtlNo().compareTo(o2.getOrderDtlNo()));
            JSFUtils.storeOnSession("OrderDtls", this.selectedOrdrLst);
            JSFUtils.storeOnSession("ReportColumn", selectColums);
            PrimeFaces.current().executeScript("printReportList()");
        } else {
            JSFUtils.addFacesErrorMessage("Please Select atleast One Column or Selection Required to take report");
        }
    }
    private void getColumns(){
       List<SelectItem>  columns = new ArrayList<>();
        columns.add(new SelectItem(1, "Order No"));
        columns.add(new SelectItem(2, "SL No"));
        columns.add(new SelectItem(3, "Item Name"));
        columns.add(new SelectItem(4, "Weight"));
        columns.add(new SelectItem(5, "Size"));
        columns.add(new SelectItem(6, "Qty"));
        columns.add(new SelectItem(7, "%"));
        columns.add(new SelectItem(8, "Stamp"));
        columns.add(new SelectItem(9, "Hook"));
        columns.add(new SelectItem(10, "DesignSample"));
        columns.add(new SelectItem(11, "Size Sample"));
        columns.add(new SelectItem(12, "Remarks"));
        columns.add(new SelectItem(13, "Due Date"));
        columns.add(new SelectItem(14, "Customer"));
        columns.add(new SelectItem(15, "Cust Ref No."));
        columns.add(new SelectItem(16, "Type"));
        columns.add(new SelectItem(17, "Worker Name"));
        this.setPrintColumns(columns);
        this.selectedColumn = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17"};
     }
}
