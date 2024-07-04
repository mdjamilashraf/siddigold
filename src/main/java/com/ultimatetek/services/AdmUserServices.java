/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ultimatetek.services;

import com.ultimatetek.entity.UserDetails;
import com.ultimatetek.model.AdminUsersVO;
import java.security.SignatureException;
import java.util.List;

/**
 *
 * @author jamil
 */
public interface AdmUserServices {

    public void saveAdmUser(AdminUsersVO adminUsersVO, Long userId) throws SignatureException;

    public List<UserDetails> getAdmnUserList();

    public UserDetails getAdminUserByCode(String userCode);

}
