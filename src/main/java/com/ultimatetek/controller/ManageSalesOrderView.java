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
import java.util.Arrays;
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
public class ManageSalesOrderView implements Serializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(ManageSalesOrderView.class);
    private List<OrderDetailsVO> orders;
    private OrderDetailsVO selectedOrders;
    private OrderSearchParamVO searchParam = new OrderSearchParamVO();
    private List<OrderDetailsVO> selectedOrdrLst;
    private List<SelectItem> custList;
    private List<SelectItem> allCustList;
    private List<SelectItem> ordrStatusList;
    private List<SelectItem> workshopList;
    private List<SelectItem> orderTypList;
    private List<SelectItem> priorityTypList;
    private boolean showList = false;
    private Integer updatedOrdrStatus;
    private Map<String, String> mapItemCode = new HashMap<>();
    private Map<String, String> mapMeltingStamp = null;
    private Long userId = (Long)JSFUtils.getFromSession("userId");
    private String custUsrCode = (String)JSFUtils.getFromSession("userCode");
    private Integer userGrp = (Integer) JSFUtils.getFromSession("userGrp");
    private float totalOrdrWt = 0f;
    private String imagefile;
    private List<String> imagePath;
    private List<SelectItem> printColumns;
    private String[] selectedColumn;
    
    
    @Autowired
    private SalesOrderService salesOrderService;
    @PostConstruct
    public void init() {
      
        this.custList = salesOrderService.getCustList();
        this.allCustList = salesOrderService.getAllCustList();
        this.ordrStatusList = salesOrderService.getOrdrStatusList();
        this.workshopList = salesOrderService.getWorkshopList();
        this.orderTypList = salesOrderService.getOrderTypList();
        this.priorityTypList = salesOrderService.getPriorityTypList();
       // this.itemList = itemServices.getItemCodeList();                      
        this.mapItemCode = salesOrderService.getItemCodeMap();
       this.mapMeltingStamp = salesOrderService.getMeltingStampMap();
       if (this.userGrp == 3) {
            //this.wrkshpCode = this.wrkshpUsrCode;
            this.searchParam.setCustCode(custUsrCode);
       }
       this.searchParam.setOrderStatus(1);
       this.orders = salesOrderService.getOrdersBySearchParam(this.searchParam);
       this.showList = this.orders != null && !this.orders.isEmpty();
       this.totalOrdrWt = this.showList ? SalesOrderUtils.calculateTotleWt(this.orders) : 0;
       this.getColumns();
    }
    public void fetchOrder() {
        if (this.userGrp == 3) {
            this.searchParam.setCustCode(custUsrCode);
        }
        this.orders = salesOrderService.getOrdersBySearchParam(this.searchParam);
        //this.orders = salesOrderService.getSalesOrderListByParam(this.custCode, this.orderNo,this.rcrdNo, this.ordrStatus,this.wrkshpCode, this.orderTyp, this.itemCode, this.custRefNo,null, null, this.dueDate, null);
        this.showList = this.orders != null && !this.orders.isEmpty();
        this.totalOrdrWt = this.showList ? SalesOrderUtils.calculateTotleWt(this.orders) : 0;
    }
    public List<String> completeText(String query) {
        List<String> results = salesOrderService.getItemCodeList("%"+query+"%");
        System.out.print(results.size());
        return results;
    }
    public void onSelectCode() {
        String selectedItemCode = this.selectedOrders.getItemCode();
        this.selectedOrders.setItemName(mapItemCode.get(selectedItemCode));
    }
    public void changeOrdrStatus(OrderDetailsVO ordrDtlVO) {
        if (orders != null && !orders.isEmpty()) {
            for (OrderDetailsVO orderDtl : orders) {
                if (orderDtl.getOrderNo().equals(ordrDtlVO.getOrderNo())
                        && orderDtl.getSrlNo() ==  ordrDtlVO.getSrlNo()) {
                    String statusTyp = String.valueOf(ordrDtlVO.getOrderStatus());
                    orderDtl.setStatus(this.findOrdrStatusName(statusTyp));
                    orderDtl.setRowStatus("updated");
                    break;
                }
            }
        }
    }
    
    public void onChangeDays(OrderDetailsVO ordrDtlVO) {
        for (OrderDetailsVO orderDtl : orders) {
            if (orderDtl.getOrderNo().equals(ordrDtlVO.getOrderNo())
                        && orderDtl.getSrlNo() == ordrDtlVO.getSrlNo()) {
                //convert meltPer into string without decimal
                int days = ordrDtlVO.getDays();
                Date dueDate = DateUtils.incrementDateNew(new Date(), days);
                orderDtl.setDueDate(dueDate);
                String strFormatDt = DateUtils.convertDateToStringFormat(dueDate, "dd/MM/yyyy");
                orderDtl.setFormatedDueDate(strFormatDt);
                orderDtl.setRowStatus("updated");
                break;
            }
        }
    }

    private String findOrdrStatusName(String statusTyp) {
        switch (statusTyp) {
            case "1":
                return "Confirmed";
            case "2":
                return "Assigned";
            case "3":
                return "Closed";
            case "4":
                return "Cancelled";
            case "5":
                return "Pending";
            default:
                break;
        }
        return null;
    }
    
    public String updateOrderDtl () {
        try {
            for (OrderDetailsVO ordrDtl : this.orders) {
                if (ordrDtl.getRowStatus().equalsIgnoreCase("updated")) {
                    LOGGER.info("Admin===="+ordrDtl.getOrderNo()+"\t"+ordrDtl.getOrderStatus());
                    salesOrderService.updateOrderDtl(ordrDtl);
                }
            }
            JSFUtils.addFacesInformationMessage("Successfully updated");
            this.fetchOrder();
        } catch (Exception e) {
            JSFUtils.addFacesErrorMessage("Error when update orders");
        }
        return null;
    }
    public void updateOrdrStatus() {
        for (OrderDetailsVO ordrDtl : this.orders) {
            ordrDtl.setOrderStatus(this.updatedOrdrStatus);
            ordrDtl.setStatus(findOrdrStatusName(String.valueOf(this.updatedOrdrStatus)));
            ordrDtl.setRowStatus("updated");
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
        if (this.selectedOrdrLst.size()>=1  && selectColums.length>=1) {
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
            this.selectedOrdrLst.sort((o1, o2)
                  -> o1.getOrderDtlNo().compareTo(
                      o2.getOrderDtlNo()));
            JSFUtils.storeOnSession("OrderDtls", this.selectedOrdrLst);
            JSFUtils.storeOnSession("ReportColumn", selectColums);
            PrimeFaces.current().executeScript("printReportList()");
        } else {
            JSFUtils.addFacesErrorMessage("Please Select atleast One Column or Selection Required to take report");
        }
    }
    
    public void onDaysChageEvnt() {
        Date newDueDt = DateUtils.incrementDateNew(this.selectedOrders.getOrderDate(), this.selectedOrders.getDays().intValue());
        this.selectedOrders.setDueDate(newDueDt);
        this.selectedOrders.setFormatedDueDate(DateUtils.convertDateToStringFormat(newDueDt, "dd/MM/yyyy"));
    }
    
    public void onChangeMelting() {
        String stamp = this.salesOrderService.getMeltingStamp(this.selectedOrders.getCustCode(), this.selectedOrders.getMeltPer());
        if (stamp != null) {
            this.selectedOrders.setStamp(stamp);
        }
        String meltPer =  String.valueOf(this.selectedOrders.getMeltPer().intValue());
        this.selectedOrders.setStamp(mapMeltingStamp.get(meltPer));
    } 
    
     
     public List<String> getimages(){
        String[] fileNames =this.selectedOrders.getImgPath1();
      if(fileNames!=null){
        List<String>listOfImages = new ArrayList<>(Arrays.asList(fileNames));
        this.setImagePath(listOfImages);
        return listOfImages;
      }else{
         return null; 
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
