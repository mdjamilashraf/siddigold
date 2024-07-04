/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ultimatetek.services;

import com.ultimatetek.config.JSFUtils;
import com.ultimatetek.config.ProjectConst;
import com.ultimatetek.config.PswdSecurity;
import com.ultimatetek.model.AdminUsersVO;
import com.ultimatetek.dao.GeneralRepo;
import com.ultimatetek.dao.UserDetailsRepo;
import com.ultimatetek.entity.UserDetails;
import java.security.SignatureException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jamil
 */
@Service
public class AdmUserServicesImpl implements AdmUserServices {

    @Autowired
    private GeneralRepo generalRepo;
    @Autowired
    private UserDetailsRepo userDetailsRepo;

    public AdmUserServicesImpl() {
        System.out.println("initialte object");
    }

    @Override
    public void saveAdmUser(AdminUsersVO adminUsersVO, Long userId) throws SignatureException {
        Optional<UserDetails> opt = userDetailsRepo.getUserDetailsByUserCode(adminUsersVO.getUserCode());
        int usercodeExist = userDetailsRepo.checkUserByUserCode(adminUsersVO.getUserCode());
        if (opt.isPresent() && opt.get().getUserNo() != null && usercodeExist == 1) {
            UserDetails detachedEntity = opt.get();
            detachedEntity.setUserNo(detachedEntity.getUserNo());
            detachedEntity.setUserCode(adminUsersVO.getUserCode());
            detachedEntity.setUserFirstName(adminUsersVO.getUserName());
            detachedEntity.setGroupNo(detachedEntity.getGroupNo());
            detachedEntity.setInactiveFlg(adminUsersVO.getInactiveFlg());
            detachedEntity.setPassword(detachedEntity.getPassword());//for temperory password is not update
            detachedEntity.setUpdDate(new Date());
            detachedEntity.setUpdCnt(opt.get().getUpdCnt() + 1);
            detachedEntity.setUpdUsrNo(userId);
            userDetailsRepo.save(detachedEntity);
            JSFUtils.addFacesInformationMessage("updated Successfully!");
        } else {
            if (usercodeExist == 0) {
                Integer maxuserNo = (Integer) generalRepo.getAutoIncrementVal("UserDetails", "userNo", null);
                Integer userNo = (maxuserNo == null || maxuserNo == 0) ? 1 : ++maxuserNo;
                UserDetails entity = new UserDetails();
                entity.setUserNo((long) userNo);
                entity.setUserCode(adminUsersVO.getUserCode());
                entity.setUserFirstName(adminUsersVO.getUserName());
                String encodedPassword = PswdSecurity.calculateRFC2104HMAC(adminUsersVO.getPassword(), ProjectConst.SECRET_KEY);
                entity.setPassword(encodedPassword);
                entity.setGroupNo(1);
                entity.setInactiveFlg(adminUsersVO.getInactiveFlg());
                entity.setCrtUsrNo(userId);
                entity.setCrtDate(new Date());
                entity.setUpdCnt(0);
                userDetailsRepo.save(entity);
                JSFUtils.addFacesInformationMessage("added Successfully!");
            } else {
                JSFUtils.addFacesErrorMessageRedirect("userId already exist please change into unique userId");
            }

        }
    }

    @Override
    public List<UserDetails> getAdmnUserList() {
        return userDetailsRepo.getAdminList();
    }

    @Override
    public UserDetails getAdminUserByCode(String userCode) {
        return userDetailsRepo.getUserDtlsByUserCode(userCode);
    }

}
