/*
 * Copyright 2022 JoinFaces.
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
package com.ultimatetek.services;

import com.ultimatetek.config.JSFUtils;
import com.ultimatetek.config.StringUtils;
import com.ultimatetek.dao.GeneralRepo;
import com.ultimatetek.dao.UserDetailsRepo;
import com.ultimatetek.dao.WorkshopDetailOthrRepo;
import com.ultimatetek.entity.UserDetails;
import com.ultimatetek.entity.WorkshopDetailOthr;
import com.ultimatetek.model.WorkshopVO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.faces.model.SelectItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author JunaidKhan
 */
@Service
public class WorkShopUserServiceImpl implements WorkShopUserService {

    @Autowired
    private GeneralRepo generalRepo;
    @Autowired
    private UserDetailsRepo userDetailsRepo;
    @Autowired
    private WorkshopDetailOthrRepo workshopDetailOthrRepo;

    @Override
    public WorkshopDetailOthr getWorkshopDtls(String wrkshpCode) {
        return workshopDetailOthrRepo.getWorkshopDtlsByCode(wrkshpCode);
    }

    @Override
    public Optional<UserDetails> getUserDetailsByCode(String wrkshpCode) {
        return userDetailsRepo.getUserDetailsByUserCode(wrkshpCode);
    }

    @Override
    public void saveUserWorkshop(WorkshopVO wrkshpVO, Long userId) {
        Optional<UserDetails> opt = userDetailsRepo.getUserDetailsByUserCode(wrkshpVO.getWrkshpCode());
        Optional<WorkshopDetailOthr> optional = workshopDetailOthrRepo.getWorkShopDetailsByWrkshpCode(wrkshpVO.getWrkshpCode());
        if (opt.isPresent() && opt.get().getUserNo() != null && optional.isPresent()) {
            UserDetails userDetachedEntity = opt.get();
            userDetachedEntity.setUserNo(userDetachedEntity.getUserNo());
            userDetachedEntity.setUserCode(wrkshpVO.getWrkshpCode());
            userDetachedEntity.setUserFirstName(wrkshpVO.getWrkshpName());
            if (!StringUtils.isEmpty(wrkshpVO.getPassword())) {
                userDetachedEntity.setPassword(wrkshpVO.getPassword());
            }
            userDetachedEntity.setGroupNo(userDetachedEntity.getGroupNo());
            userDetachedEntity.setInactiveFlg(wrkshpVO.getInactiveFlg());
            userDetachedEntity.setUpdDate(new Date());
            userDetachedEntity.setUpdCnt(opt.get().getUpdCnt() + 1);
            userDetachedEntity.setUpdUsrNo(userId);
            userDetailsRepo.save(userDetachedEntity);
            WorkshopDetailOthr wrkShpdetachedEntity = optional.get();
            wrkShpdetachedEntity.setWrkshpCode(wrkshpVO.getWrkshpCode());
            wrkShpdetachedEntity.setWrkshpName(wrkshpVO.getWrkshpName());
            wrkShpdetachedEntity.setPinCode(wrkshpVO.getPinCode());
            wrkShpdetachedEntity.setWrkshpPhone(wrkshpVO.getWrkshpPhone());
            wrkShpdetachedEntity.setWrkshpEmail(wrkshpVO.getWrkshpEmail());
            wrkShpdetachedEntity.setWrkshpAddr(wrkshpVO.getWrkshpAddr());
            wrkShpdetachedEntity.setWrkshpMobile(wrkshpVO.getWrkshpMobile());
            wrkShpdetachedEntity.setStateId(wrkshpVO.getStateId());
            wrkShpdetachedEntity.setInactiveFlg(wrkshpVO.getInactiveFlg());
            wrkShpdetachedEntity.setCityId(wrkshpVO.getCityId());
            wrkShpdetachedEntity.setCstNo(wrkshpVO.getCstNo());
            wrkShpdetachedEntity.setTinNo(wrkshpVO.getTinNo());
            wrkShpdetachedEntity.setUpdDate(new Date());
            wrkShpdetachedEntity.setUpdTrmnlNm("server");
            wrkShpdetachedEntity.setUpdUsrNo(userId);
            wrkShpdetachedEntity.setUpdCnt(wrkShpdetachedEntity.getUpdCnt() + 1);
            if (wrkshpVO.getShwCustFlg() == true) {
                wrkShpdetachedEntity.setPrvShowCust(1);
            } else {
                wrkShpdetachedEntity.setPrvShowCust(0);
            }
            workshopDetailOthrRepo.save(wrkShpdetachedEntity);
            JSFUtils.addFacesInformationMessage("updated Successfully!");
        } else {
            Integer maxuserNo = (Integer) generalRepo.getAutoIncrementVal("UserDetails", "userNo",null);
            Integer userNo = (maxuserNo == null || maxuserNo == 0) ? 1 : ++maxuserNo;
            UserDetails userEntity = new UserDetails();
            userEntity.setUserNo((long) userNo);
            userEntity.setUserCode(wrkshpVO.getWrkshpCode());
            userEntity.setUserFirstName(wrkshpVO.getWrkshpName());
            userEntity.setPassword(wrkshpVO.getPassword());
            userEntity.setInactiveFlg(wrkshpVO.getInactiveFlg());
            userEntity.setGroupNo(2);
            userEntity.setCrtUsrNo(1);
            userEntity.setCrtDate(new Date());
            userDetailsRepo.save(userEntity);
            WorkshopDetailOthr workshopDetailEntity = new WorkshopDetailOthr();
            workshopDetailEntity.setWrkshpCode(wrkshpVO.getWrkshpCode());
            workshopDetailEntity.setWrkshpName(wrkshpVO.getWrkshpName());
            workshopDetailEntity.setPinCode(wrkshpVO.getPinCode());
            workshopDetailEntity.setWrkshpPhone(wrkshpVO.getWrkshpPhone());
            workshopDetailEntity.setWrkshpEmail(wrkshpVO.getWrkshpEmail());
            workshopDetailEntity.setWrkshpAddr(wrkshpVO.getWrkshpAddr());
            workshopDetailEntity.setWrkshpMobile(wrkshpVO.getWrkshpMobile());
            workshopDetailEntity.setInactiveFlg(wrkshpVO.getInactiveFlg());
            workshopDetailEntity.setStateId(wrkshpVO.getStateId());
            workshopDetailEntity.setCityId(wrkshpVO.getCityId());
            workshopDetailEntity.setCstNo(wrkshpVO.getCstNo());
            workshopDetailEntity.setTinNo(wrkshpVO.getTinNo());
            workshopDetailEntity.setCrtUsrNo(1);
            workshopDetailEntity.setCrtDate(new Date());
            if (wrkshpVO.getShwCustFlg() == true) {
                workshopDetailEntity.setPrvShowCust(1);
            } else {
                workshopDetailEntity.setPrvShowCust(0);
            }
            workshopDetailOthrRepo.save(workshopDetailEntity);
            JSFUtils.addFacesInformationMessage("added Successfully!");
        }

    }

    @Override
    public List<SelectItem> getWorkshopUserList() {
        List<SelectItem> list = new ArrayList<>();
        list.add(new SelectItem(null, "Select"));
        list.add(new SelectItem("%", "All"));
        List<Object[]> objects = generalRepo.getDropdownItems("WorkshopDetailOthr", "wrkshpCode", "wrkshpName");
        for (Object[] obj : objects) {
            list.add(new SelectItem(obj[0], (String) obj[1]));
        }
        return list;
    }

    @Override
    public void updateWorkshopUser(WorkshopVO wrkshpVO) {
        UserDetails userDetails = userDetailsRepo.getUserDtlsByUserCode(wrkshpVO.getWrkshpCode());
        UserDetails userEntity = new UserDetails();
        userEntity.setUserNo(userDetails.getUserNo());
        userEntity.setUserCode(userDetails.getUserCode());
        userEntity.setUserFirstName(wrkshpVO.getWrkshpName());
        userEntity.setPassword(userDetails.getPassword());
//        if (wrkshpVO.getUserActive() == 0) {
//            userEntity.setInactiveFlg((short) 1);
//        }
        userEntity.setGroupNo(userDetails.getGroupNo());
        userEntity.setCrtUsrNo(userDetails.getCrtUsrNo());
        userEntity.setCrtDate(userDetails.getCrtDate());
        userEntity.setUpdDate(new Date());
        userEntity.setUpdTrmnlNm("server");
        userEntity.setUpdCnt(userDetails.getUpdCnt() + 1);
        userDetailsRepo.save(userEntity);
        WorkshopDetailOthr workshopDetailEntity = new WorkshopDetailOthr();
        workshopDetailEntity.setWrkshpCode(wrkshpVO.getWrkshpCode());
        workshopDetailEntity.setWrkshpName(wrkshpVO.getWrkshpName());
        workshopDetailEntity.setPinCode(wrkshpVO.getPinCode());
        workshopDetailEntity.setWrkshpPhone(wrkshpVO.getWrkshpPhone());
        workshopDetailEntity.setWrkshpEmail(wrkshpVO.getWrkshpEmail());
        workshopDetailEntity.setWrkshpAddr(wrkshpVO.getWrkshpAddr());
        workshopDetailEntity.setWrkshpMobile(wrkshpVO.getWrkshpMobile());
        workshopDetailEntity.setStateId(wrkshpVO.getStateId());
        workshopDetailEntity.setCityId(wrkshpVO.getCityId());
        workshopDetailEntity.setCstNo(wrkshpVO.getCstNo());
        workshopDetailEntity.setTinNo(wrkshpVO.getTinNo());
        workshopDetailEntity.setCrtUsrNo(wrkshpVO.getCrtUsrNo());
        workshopDetailEntity.setCrtDate(wrkshpVO.getCrtDate());
        workshopDetailEntity.setUpdDate(new Date());
        workshopDetailEntity.setUpdTrmnlNm("server");
        workshopDetailEntity.setUpdCnt(wrkshpVO.getUpdCnt() + 1);
        workshopDetailOthrRepo.save(workshopDetailEntity);
    }

    @Override
    @Transactional
    public void deleteWorkShopDtls(List<WorkshopVO> selectedWrkshp) {
        for (WorkshopVO workShop : selectedWrkshp) {
            generalRepo.delByCode("UserDetails", "userCode", workShop.getWrkshpCode());
            generalRepo.delByCode("WorkshopDetailOthr", "wrkshpCode", workShop.getWrkshpCode());
        }
    }

    @Override
    public List<WorkshopDetailOthr> getWorkshopDtlsList() {
        return workshopDetailOthrRepo.findAll();
    }

    @Override
    public List<WorkshopDetailOthr> getWorkshopListByCode(String wrkshpCode) {
        return workshopDetailOthrRepo.getWorkshopDtlsListByCode(wrkshpCode);
    }

    @Override
    public WorkshopDetailOthr getWorkshopDtlsByCode(String wrkshpCode) {
        return workshopDetailOthrRepo.getWorkshopDtlsByCode(wrkshpCode);
    }

}
