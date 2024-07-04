/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ultimatetek.controller;

import com.ultimatetek.config.JSFUtils;
import com.ultimatetek.config.StringUtils;
import com.ultimatetek.dao.GeneralRepo;
import com.ultimatetek.model.CustMeltingVO;
import com.ultimatetek.entity.CustomerDetailOthr;
import com.ultimatetek.entity.MeltingStamp;
import com.ultimatetek.model.CustomerVO;
import com.ultimatetek.services.CustUserService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import lombok.Data;
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
public class AddCustomerView implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddCustomerView.class);
    private CustomerVO customerUserVO = new CustomerVO();
    private List<CustMeltingVO> meltingList;
    private List<CustomerVO> customerList;
    private CustomerVO custDetails;
    private CustomerVO selectedCustomer;
    private Boolean showList = false;
    private Boolean readOnlyFld = false;
    private Boolean showSearchDtls = false;
    private Boolean pswdReqFld = false;
    private String CustCode;
    private List<CustomerVO> filteredCustomers;
    private Long userId = (Long) JSFUtils.getFromSession("userId");

    @Autowired
    private CustUserService custUserService;
    @Autowired
    private GeneralRepo gnrRepo;

    @PostConstruct
    public void init() {
        this.meltingList = this.assign();
        this.getCustomerUserList();
        if (customerList != null && !customerList.isEmpty()) {
            this.showList = true;
        } else {
            this.showList = false;
        }
        this.pswdReqFld = true;
    }

    private List<CustMeltingVO> assign() {
        List<CustMeltingVO> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new CustMeltingVO(i + 1, null, null));
        }
        return list;
    }

    public void searchByCode() {
        CustomerDetailOthr customerDetailOthr = custUserService.getCustomerDtlsByCode(this.customerUserVO.getCustCode());
        if (customerDetailOthr != null) {
            CustomerVO cust = new CustomerVO();
            cust.setCustCode(customerDetailOthr.getCustCode());
            long cnt = gnrRepo.chkFldValueExist("MeltingStamp", "custCode", cust.getCustCode(), "");
            if (cnt == 0) {
                cust.setDefaultStamp(true);
            } else {
                cust.setDefaultStamp(false);
            }
            cust.setCustName(customerDetailOthr.getCustName());
            cust.setCustEmail(customerDetailOthr.getCustEmail());
            cust.setCustAddr(customerDetailOthr.getCustAddr());
            cust.setInactiveFlg(customerDetailOthr.getInactiveFlg());
            cust.setStateId(customerDetailOthr.getStateId());
            cust.setCityId(customerDetailOthr.getCityId());
            cust.setCustPhone(customerDetailOthr.getCustPhone());
            cust.setCustMobile(customerDetailOthr.getCustMobile());
            cust.setPinCode(customerDetailOthr.getPinCode());
            cust.setCstNo(customerDetailOthr.getCstNo());
            cust.setTinNo(customerDetailOthr.getTinNo());
            this.setCustomerUserVO(cust);
            this.showSearchDtls = true;
            this.pswdReqFld = false;
        } else {
            this.showSearchDtls = false;
            this.pswdReqFld = true;
        }
    }

    public void getCustomerUserList() {
        List<CustomerDetailOthr> custDtlsList = custUserService.getCustomerDtlsList();
        List<CustomerVO> custList = new ArrayList<>();
        for (CustomerDetailOthr entityObj : custDtlsList) {
            CustomerVO cust = new CustomerVO();
            cust.setCustCode(entityObj.getCustCode());
            long cnt = gnrRepo.chkFldValueExist("MeltingStamp", "custCode", cust.getCustCode(), "");
            if (cnt == 0) {
                cust.setDefaultStamp(true);
            } else {
                cust.setDefaultStamp(false);
            }
            cust.setCustName(entityObj.getCustName());
            cust.setCustEmail(entityObj.getCustEmail());
            cust.setCustAddr(entityObj.getCustAddr());
            cust.setInactiveFlg(entityObj.getInactiveFlg());
            cust.setStateId(entityObj.getStateId());
            cust.setCityId(entityObj.getCityId());
            cust.setCustPhone(entityObj.getCustPhone());
            cust.setCustMobile(entityObj.getCustMobile());
            cust.setPinCode(entityObj.getPinCode());
            cust.setCstNo(entityObj.getCstNo());
            cust.setTinNo(entityObj.getTinNo());
            custList.add(cust);
        }
        this.setCustomerList(custList);
        this.showList = true;
    }

    private void getMeltList() {
        List<MeltingStamp> meltingStampEntityData = custUserService.getDfltMeltingList(this.CustCode);
        List<CustMeltingVO> defaultMelting = new ArrayList<>();
        if (!meltingStampEntityData.isEmpty()) {
            int indx = 0;
            for (MeltingStamp obj : meltingStampEntityData) {
                CustMeltingVO custMeltingVO = new CustMeltingVO();
                custMeltingVO.setIndex(++indx);
                custMeltingVO.setMeltPer(obj.getMeltingPer());
                custMeltingVO.setStamp(obj.getStamp());
                custMeltingVO.setMeltStampId(obj.getMeltStampId());
                defaultMelting.add(custMeltingVO);
            }
            this.setMeltingList(defaultMelting);
            defaultMelting.add(new CustMeltingVO(defaultMelting.size() + 1, null, null));
        }
    }

    public void resetData() {
        this.customerUserVO.setCustCode(null);
        this.customerUserVO.setCustName(null);
        this.customerUserVO.setCustEmail(null);
        this.customerUserVO.setCustAddr(null);
        this.customerUserVO.setStateId(null);
        this.customerUserVO.setCityId(null);
        this.customerUserVO.setCustPhone(null);
        this.customerUserVO.setInactiveFlg(null);
        this.customerUserVO.setPinCode(null);
        this.customerUserVO.setCstNo(null);
        this.customerUserVO.setTinNo(null);
        this.meltingList.isEmpty();
        JSFUtils.reloadThePage();
    }

    public String addCustomerUser() {
        try {
//            String usrWhr = "";
//            if (gnrRepo.chkFldValueExist("UserDetails", "userCode", this.customerUserVO.getCustCode(), usrWhr) > 0) {
//                JSFUtils.addFacesErrorMessageRedirect("CustomerCode already exists Please Change Customer Code");
//                return null;
//            } else 
            if (this.customerUserVO.getDefaultStamp() == true && !StringUtils.isEmpty(meltingList.get(0).getStamp())) {
                JSFUtils.addFacesErrorMessageRedirect("please uncheck default Stamp or add value in melting% or stamp");
                return null;
            } else {
                custUserService.saveCustomerUser(this.customerUserVO, this.userId, this.meltingList);
                this.getCustomerUserList();
                return "addCust";
            }

        } catch (Exception e) {
            JSFUtils.addFacesErrorMessageRedirect("Server Error");
            return null;
        }

    }

    public void editCustomer() {
        this.setCustCode(this.selectedCustomer.getCustCode());
        if (!custUserService.getDfltMeltingList(this.selectedCustomer.getCustCode()).isEmpty()) {
            this.getMeltList();
        } else {
            this.meltingList = this.assign();
        }
        this.setCustomerUserVO(this.selectedCustomer);
        this.readOnlyFld = true;
        CustomerDetailOthr customerDetailOthr = custUserService.getCustomerDtlsByCode(this.selectedCustomer.getCustCode());
        if (customerDetailOthr != null) {
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
        CustomerVO cust = (CustomerVO) value;
        if (cust == null || cust.getCustName() == null) {
            return true;
        }
        return cust.getCustCode().toLowerCase().contains(filterText)
                || cust.getCustName().toLowerCase().contains(filterText);

    }

    public void deleteCustomer() {
        if (!StringUtils.isEmpty(this.selectedCustomer.getCustCode())) {
            try {
                custUserService.deleteCustomerUser(this.selectedCustomer.getCustCode(), this.userId);
                this.getCustomerUserList();
            } catch (Exception e) {
                JSFUtils.addFacesErrorMessageRedirect("Server Error");
            }
        }
    }
}
