/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package com.ultimatetek.controller;

import com.ultimatetek.config.JSFUtils;
import com.ultimatetek.config.ProjectConst;
import com.ultimatetek.config.PswdSecurity;
import com.ultimatetek.entity.UserDetails;
import com.ultimatetek.services.AuthenticationService;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author jamil
 */
@Component("AuthenticationManageBean")
@ManagedBean(name = "AuthenticationManageBean")
@SessionScoped
@Data
public class AuthenticationManageBean implements Serializable {

    private String userName;
    private String password;

    @Autowired
    private AuthenticationService authService;

    public String login() {

        try {
            UserDetails userDetails = authService.getUserDetails(this.userName);
            if (userDetails != null && userDetails.getInactiveFlg() == 0) {
                String encryptedPsword = PswdSecurity.calculateRFC2104HMAC(password, ProjectConst.SECRET_KEY);
                if (!encryptedPsword.equals(userDetails.getPassword())) {
                    JSFUtils.addFacesErrorMessageRedirect("Invalid password");
                    return null;
                }
                JSFUtils.storeOnSession("isAuthentic", true);
                JSFUtils.storeOnSession("userId", userDetails.getUserNo());
                JSFUtils.storeOnSession("userCode", userDetails.getUserCode());
                JSFUtils.storeOnSession("userGrp", userDetails.getGroupNo());
                JSFUtils.storeOnSession("userNm", userDetails.getUserFirstName());
                return "/secured/home.xhtml?faces-redirect=true";
            } else {
                JSFUtils.storeOnSession("isAuthentic", false);
                JSFUtils.addFacesErrorMessageRedirect("Invalid username or password");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isLoggedIn() {
        // return currentUser != null;
        return false;
    }

    public String signout() {

        JSFUtils.removeSessionBean("AuthenticationManageBean");
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/signOut.xhtml?faces-redirect=true";
    }
}
