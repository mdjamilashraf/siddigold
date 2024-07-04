/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ultimatetek.controller;

import com.ultimatetek.config.DateUtils;
import com.ultimatetek.config.JSFUtils;
import com.ultimatetek.config.OnloadDataConfig;
import com.ultimatetek.config.ValidationUtils;
import com.ultimatetek.model.OrderDetailsVO;
import com.ultimatetek.model.ResultVO;
import com.ultimatetek.services.ItemServices;
import com.ultimatetek.services.SalesOrderService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import lombok.Data;
import org.apache.commons.collections.map.MultiKeyMap;
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
@SessionScoped
@Data
public class SalesOrderView implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(SalesOrderView.class);
    private List<OrderDetailsVO> orders;
    private String custCode;
    private Long orderNo;
    private String orderDate = DateUtils.getTodayDateInString();
    private List<SelectItem> itemList;
    private List<SelectItem> workshopList;
    private List<SelectItem> custList;
    private Map<String, String> mapItemCode = new HashMap<>();
    private Map<String, String> mapMeltingStamp = new HashMap<>();
    private MultiKeyMap multiKeyMap = new MultiKeyMap();
    private Float totalWeight = 0.0f;
    private OrderDetailsVO selectedRow;
    private Integer userGrp = (Integer) JSFUtils.getFromSession("userGrp");
    private String userNm = (String) JSFUtils.getFromSession("userNm");

    @Autowired
    private ItemServices itemServices;
    @Autowired
    private SalesOrderService salesOrderService;

    @PostConstruct
    public void init() {
        if (this.custCode != null) {
            this.custCode = null;
        }
        List<OrderDetailsVO> list = new ArrayList<>();
        for (short i = 1; i <= 50; i++) {
            OrderDetailsVO ordrDtl = new OrderDetailsVO();
            ordrDtl.setSrlNo((short) i);
            list.add(ordrDtl);
        }
        this.setOrders(list);
//        this.orderNo = salesOrderService.preAddOrderNo();
        this.custList = salesOrderService.getCustList();
        this.workshopList = salesOrderService.getWorkshopList();
        this.mapItemCode = salesOrderService.getItemCodeMap();
        this.mapMeltingStamp = salesOrderService.getMeltingStampMap();
        this.multiKeyMap = salesOrderService.getCustMeltingStampMap();
        if (this.userGrp == 3) {
            this.itemList = this.populateItems();
            this.custCode = (String) JSFUtils.getFromSession("userCode");
        }
    }

    public void onSelectItemCode(OrderDetailsVO order) {
        int arrayIndex = order.getSrlNo() - 1;
        this.orders.get(arrayIndex).setItemName(mapItemCode.get(order.getItemCode()));
    }

    public void onChangeMeltingPer(OrderDetailsVO order) {
        for (OrderDetailsVO ordr : orders) {
            if (order.getSrlNo() == ordr.getSrlNo()) {
                //convert meltPer into string without decimal
                String meltPer = String.valueOf(order.getMeltPer());
                if (meltPer.contains(".")) {
                    meltPer = meltPer.substring(0, meltPer.indexOf("."));
                }
                String custStamp = (String) this.multiKeyMap.get(this.custCode, Integer.parseInt(meltPer));
                if (custStamp != null) {
                    ordr.setStamp(custStamp);
                } else {
                    ordr.setStamp(mapMeltingStamp.get(meltPer));
                }
            }
        }
    }

    public void onChangeDays(OrderDetailsVO order) {
        for (OrderDetailsVO ordr : orders) {
            if (order.getSrlNo() == ordr.getSrlNo()) {
                //convert meltPer into string without decimal
                int days = order.getDays();
                Date dueDate = DateUtils.incrementDateNew(new Date(), days);
                ordr.setDueDate(dueDate);
                String strFormatDt = DateUtils.convertDateToStringFormat(dueDate, "dd/MM/yyyy");
                ordr.setFormatedDueDate(strFormatDt);
                break;
            }
        }
    }

    public void assingDefaultQty(OrderDetailsVO order) {
        for (OrderDetailsVO ordr : orders) {
            if (order.getSrlNo() == ordr.getSrlNo()) {
                ordr.setQty(1);
                break;
            }
        }
    }

    public void calculateWeight(OrderDetailsVO order, Integer qty) {
        float initWt = 0;
        for (OrderDetailsVO ordr : orders) {
            if (ordr.getItemCode() != null
                    && ordr.getWeight() != null
                    && ordr.getQty() != null) {
                short pieceWiseVal = itemServices.chkPieceWiseValue(ordr.getItemCode());
                if (pieceWiseVal == 1) {
                    initWt = initWt + ordr.getQty() * ordr.getWeight();
                } else {
                    initWt = initWt + ordr.getWeight();
                }
            }
        }
        this.totalWeight = initWt;
        System.out.println("Totel weight===================" + totalWeight);
    }

    public List<String> completeText(String query) {
//        Map<Object, Object> map = OnloadDataConfig.getItemsMap();
//        List<String> results = salesOrderService.getItemCodeList("%" + query + "%");
//        System.out.print(results.size());
//        return results;
        List<String> results = new ArrayList<>();
        for (Object obj : OnloadDataConfig.getList()) {
            String itm = obj.toString().toUpperCase();
            if (itm.startsWith(query.toUpperCase())) {
                results.add((String) obj);
            }
        }

        return results;
    }

    public String saveOrder(Long orderNo) {
        try {
            ResultVO result = ValidationUtils.validateOrders(this.getCustCode(), this.orders, this.userGrp);
            if (result != null) {
                JSFUtils.addFacesErrorMessage(result.getErrMsg());
            } else {
                int cnt = salesOrderService.getOrderNoValue(orderNo);

                if (cnt == 0) {
                    salesOrderService.saveSalesOrder(this.getCustCode(), this.orders, orderNo);
                    JSFUtils.addFacesInformationMessage("Order has been confirmed");
                    JSFUtils.reloadThePage();
                    JSFUtils.removeSessionBean("salesOrderView");
                } else {
                    JSFUtils.addFacesErrorMessage("Given Order is Already Exist!");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            JSFUtils.addFacesErrorMessage("Error while saving order");
        }
        return null;
    }

    public void reset() {
        this.init();
        JSFUtils.reloadThePage();

    }

    public void clearOrderRow() {
        int srlNo = this.selectedRow.getSrlNo();
        try {
            this.orders.get(srlNo - 1).setItemCode(null);
            this.orders.get(srlNo - 1).setItemName(null);
            this.orders.get(srlNo - 1).setWeight(null);
            this.orders.get(srlNo - 1).setItemSize(null);
            this.orders.get(srlNo - 1).setQty(null);
            this.orders.get(srlNo - 1).setMeltPer(null);
            this.orders.get(srlNo - 1).setStamp(null);
            this.orders.get(srlNo - 1).setHook(null);
            this.orders.get(srlNo - 1).setDesignSample(null);
            this.orders.get(srlNo - 1).setSizeSample(null);
            this.orders.get(srlNo - 1).setRefNo(null);
            this.orders.get(srlNo - 1).setRemark(null);
            this.orders.get(srlNo - 1).setDays(null);
            this.orders.get(srlNo - 1).setDueDate(null);
            this.orders.get(srlNo - 1).setFormatedDueDate(null);
            this.orders.get(srlNo - 1).setWorkshop(null);
            this.orders.get(srlNo - 1).setRcvSample(false);
            this.orders.get(srlNo - 1).setFixRate("R0");
            this.orders.get(srlNo - 1).setOrderTyp(1);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    private List<SelectItem> populateItems() { //for customer
        Map<Object, Object> map = OnloadDataConfig.getItemsMap();
        List<SelectItem> list = new ArrayList();
        list.add(new SelectItem(null, "Select..."));
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            list.add(new SelectItem((String) entry.getKey(), (String) entry.getValue()));
        }
        return list;
    }
}
