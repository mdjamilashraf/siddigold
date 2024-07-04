/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ultimatetek.controller;

import com.ultimatetek.config.JSFUtils;
import com.ultimatetek.config.ResetPasswordToEncryptInterface;
import com.ultimatetek.entity.UserDetails;
import com.ultimatetek.model.AdminUsersVO;
import com.ultimatetek.services.AdmUserServices;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import lombok.Data;
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
public class AddAdminUserView implements Serializable {

    private AdminUsersVO adminUsersVO = new AdminUsersVO();
    private List<AdminUsersVO> admins;
    private AdminUsersVO selectedAdmin;
    private Boolean showList = false;
    private Boolean readOnlyFld = false;
    private Boolean showSearchDtls = false;
    private Boolean pswdReqFld = false;
    private Long userId = (Long) JSFUtils.getFromSession("userId");

    @Autowired
    private AdmUserServices admUserServices;
    @Autowired
    private ResetPasswordToEncryptInterface resetPasswordToEncryptInterface;

    @PostConstruct
    public void init() {
        this.getAdminList();
        if (admins != null && !admins.isEmpty()) {
            this.showList = true;
        } else {
            this.showList = false;
        }
        this.pswdReqFld = true;
//        resetPasswordToEncryptInterface.changeAllUserPasswordtoencrypt();//this method is run only once when we need to encrypt all password
    }

    public String addAdminUser() {
        try {
            admUserServices.saveAdmUser(this.adminUsersVO, this.userId);
            this.getAdminList();
            return "addAdminUser";
        } catch (Exception e) {
            JSFUtils.addFacesErrorMessageRedirect("Server Error");
            return null;
        }
    }

    public void resetData() {
        this.adminUsersVO.setUserCode(null);
        this.adminUsersVO.setUserName(null);
        this.adminUsersVO.setPassword(null);
        this.adminUsersVO.setUserActive(null);
        JSFUtils.reloadThePage();
    }

    public void searchByCode() {
        this.pswdReqFld = false;
        System.out.println(this.adminUsersVO.getUserCode());
        UserDetails userDetails = admUserServices.getAdminUserByCode(this.adminUsersVO.getUserCode());
        if (userDetails != null) {
            AdminUsersVO userVO = new AdminUsersVO();
            userVO.setUserCode(userDetails.getUserCode());
            userVO.setUserName(userDetails.getUserFirstName());
            userVO.setInactiveFlg(userDetails.getInactiveFlg());
            userVO.setPassword(userDetails.getPassword());
            this.setAdminUsersVO(userVO);
            this.showSearchDtls = true;
        } else {
            this.showSearchDtls = false;
            this.pswdReqFld = true;
        }
    }

    private void getAdminList() {
        List<UserDetails> list = admUserServices.getAdmnUserList();
        List<AdminUsersVO> admList = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            for (UserDetails user : list) {
                AdminUsersVO obj = new AdminUsersVO();
                obj.setUserCode(user.getUserCode());
                obj.setUserName(user.getUserFirstName());
                obj.setInactiveFlg(user.getInactiveFlg());
                if (user.getInactiveFlg() == null || user.getInactiveFlg() == 0) {
                    obj.setStatus("Yes");
                } else {
                    obj.setStatus("No");
                }
                admList.add(obj);
            }
            this.setAdmins(admList);
            this.showList = true;
        }
    }

    public void editAdminUser() {
        this.setAdminUsersVO(this.selectedAdmin);
        this.readOnlyFld = true; // assign true when try to edit
        this.pswdReqFld = false;
//        UserDetails userDetails = admUserServices.getAdminUserByCode(this.selectedAdmin.getUserCode());
//        if (userDetails != null) {
//            this.pswdReqFld = false;
//        } else {
//            this.pswdReqFld = true;
//        }

    }

}
