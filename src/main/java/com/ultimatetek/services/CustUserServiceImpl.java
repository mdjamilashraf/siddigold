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
package com.ultimatetek.services;

import com.ultimatetek.config.JSFUtils;
import com.ultimatetek.config.StringUtils;
import com.ultimatetek.model.CustMeltingVO;
import com.ultimatetek.dao.CustomerRepo;
import com.ultimatetek.dao.DefaultMeltingRepo;
import com.ultimatetek.dao.GeneralRepo;
import com.ultimatetek.dao.UserDetailsRepo;
import com.ultimatetek.entity.CustomerDetailOthr;
import com.ultimatetek.entity.MeltingStamp;
import com.ultimatetek.entity.UserDetails;
import com.ultimatetek.model.CustomerVO;
import java.util.ArrayList;
import java.util.Base64;
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
public class CustUserServiceImpl implements CustUserService {

    @Autowired
    private GeneralRepo generalRepo;
    @Autowired
    private UserDetailsRepo userDetailsRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private DefaultMeltingRepo meltingRepo;

    @Override
    public List<MeltingStamp> getDfltMeltingList(String CustCode) {
        return meltingRepo.getDfltMeltingListByCustCode(CustCode);
    }

    @Override
    public void saveCustomerUser(CustomerVO customerUserVO, Long userId, List<CustMeltingVO> meltingList) {
        Optional<UserDetails> opt = userDetailsRepo.getUserDetailsByUserCode(customerUserVO.getCustCode());
        Optional<CustomerDetailOthr> optional = customerRepo.getCustomerDetailsByCode(customerUserVO.getCustCode());
        int usercodeExist = userDetailsRepo.checkUserByUserCode(customerUserVO.getCustCode());
        if (opt.isPresent() && optional.isPresent() && usercodeExist == 1) {
            UserDetails userDetachedEntity = opt.get();
            userDetachedEntity.setUserNo(userDetachedEntity.getUserNo());
            userDetachedEntity.setUserCode(customerUserVO.getCustCode());
            userDetachedEntity.setUserFirstName(customerUserVO.getCustName());
            if (!StringUtils.isEmpty(customerUserVO.getPassword())) {
                userDetachedEntity.setPassword(customerUserVO.getPassword());
            }
            userDetachedEntity.setGroupNo(userDetachedEntity.getGroupNo());
            if (!opt.get().getInactiveFlg().equals(customerUserVO.getInactiveFlg())) {
                userDetachedEntity.setInactiveFlg(customerUserVO.getInactiveFlg());
            }
            userDetachedEntity.setUpdDate(new Date());
            userDetachedEntity.setUpdCnt(opt.get().getUpdCnt() + 1);
            userDetachedEntity.setUpdUsrNo(userId);
            CustomerDetailOthr customerDetachedEntity = optional.get();
            customerDetachedEntity.setCustCode(customerUserVO.getCustCode());
            customerDetachedEntity.setCustName(customerUserVO.getCustName());
            customerDetachedEntity.setCustPhone(customerUserVO.getCustPhone());
            customerDetachedEntity.setCustAddr(customerUserVO.getCustAddr());
            customerDetachedEntity.setStateId(customerUserVO.getStateId());
            customerDetachedEntity.setCityId(customerUserVO.getCityId());
            customerDetachedEntity.setPinCode(customerUserVO.getPinCode());
            customerDetachedEntity.setCustMobile(customerUserVO.getCustMobile());
            customerDetachedEntity.setTinNo(customerUserVO.getTinNo());
            customerDetachedEntity.setCstNo(customerUserVO.getCstNo());
            customerDetachedEntity.setCustEmail(customerUserVO.getCustEmail());
            customerDetachedEntity.setUpdDate(new Date());
            if (!customerUserVO.getInactiveFlg().equals(optional.get().getInactiveFlg())) {
                customerDetachedEntity.setInactiveFlg(customerUserVO.getInactiveFlg());
                customerDetachedEntity.setInactiveUsrNo(null);
                customerDetachedEntity.setInactiveDate(null);
                customerDetachedEntity.setInactiveDsc(null);
            }
            customerDetachedEntity.setUpdTrmnlNm("server");
            customerDetachedEntity.setUpdUsrNo(userId);
            customerDetachedEntity.setUpdCnt(customerDetachedEntity.getUpdCnt() + 1);
            userDetailsRepo.save(userDetachedEntity);
            customerRepo.save(customerDetachedEntity);
            this.saveCustMeltingStamp(customerUserVO.getCustCode(), meltingList);
            JSFUtils.addFacesInformationMessage("updated Successfully!");
        } else {
            if (usercodeExist == 0 && opt.isEmpty() && optional.isEmpty()) {
                Integer maxuserNo = (Integer) generalRepo.getAutoIncrementVal("UserDetails", "userNo", null);
                Integer userNo = (maxuserNo == null || maxuserNo == 0) ? 1 : ++maxuserNo;
                UserDetails userEntity = new UserDetails();
                userEntity.setUserNo((long) userNo);
                userEntity.setUserCode(customerUserVO.getCustCode());
                userEntity.setUserFirstName(customerUserVO.getCustName());
                String encodedPassword = Base64.getEncoder().encodeToString(customerUserVO.getPassword().getBytes());
                userEntity.setPassword(encodedPassword);
                userEntity.setInactiveFlg(customerUserVO.getInactiveFlg());
                userEntity.setGroupNo(3);
                userEntity.setCrtUsrNo(userId);
                userEntity.setCrtDate(new Date());
                userDetailsRepo.save(userEntity);
                CustomerDetailOthr customerEntities = new CustomerDetailOthr();
                customerEntities.setCustCode(customerUserVO.getCustCode());
                customerEntities.setCustName(customerUserVO.getCustName());
                customerEntities.setCustPhone(customerUserVO.getCustPhone());
                customerEntities.setCustAddr(customerUserVO.getCustAddr());
                customerEntities.setStateId(customerUserVO.getStateId());
                customerEntities.setCityId(customerUserVO.getCityId());
                customerEntities.setPinCode(customerUserVO.getPinCode());
                customerEntities.setCustMobile(customerUserVO.getCustMobile());
                customerEntities.setInactiveFlg(customerUserVO.getInactiveFlg());
                customerEntities.setTinNo(customerUserVO.getTinNo());
                customerEntities.setCstNo(customerUserVO.getCstNo());
                customerEntities.setCustEmail(customerUserVO.getCustEmail());
                customerEntities.setCrtUsrNo(1);
                customerEntities.setCrtDate(new Date());
                customerRepo.save(customerEntities);
                this.saveCustMeltingStamp(customerUserVO.getCustCode(), meltingList);
                JSFUtils.addFacesInformationMessage("added Successfully!");
            } else if (opt.isEmpty() || optional.isEmpty()) {
                JSFUtils.addFacesErrorMessageRedirect("this Customer is already exist but Data Not Found! Contact Dev Team");
            } else {
                JSFUtils.addFacesErrorMessageRedirect("custCode already exist please change into unique userId");
            }
        }
    }

    @Transactional
    public void saveCustMeltingStamp(String custCode, List<CustMeltingVO> meltingList
    ) {
        List<MeltingStamp> entities = new ArrayList<>();
        Integer stampId = (Integer) generalRepo.getAutoIncrementVal("MeltingStamp", "meltStampId", null);
        for (CustMeltingVO custMelting : meltingList) {
//            if (custMelting.getMeltStampId() != null && custMelting.getMeltStampId() > 0) {
            if (custMelting.getMeltPer() != null && !StringUtils.isEmpty(custMelting.getStamp()) && custMelting.getMeltStampId() != null) {
                Optional<MeltingStamp> opt = meltingRepo.getMeltingDtlsByMeltingId(custMelting.getMeltStampId());
                MeltingStamp detachedEntity = opt.get();
                detachedEntity.setMeltingPer(custMelting.getMeltPer());
                detachedEntity.setStamp(custMelting.getStamp());
                meltingRepo.save(detachedEntity);
            } else if (custMelting.getMeltPer() == null && custMelting.getMeltStampId() != null) {
                generalRepo.delByCode("MeltingStamp", "meltStampId", custMelting.getMeltStampId());
            } //update the melt per and stamp
            //meltingRepo.updCustMelting(custMelting.getMeltPer(), custMelting.getStamp(), custMelting.getMeltStampId());
            else if ((custMelting.getMeltPer() != null && custMelting.getStamp() != null) && custMelting.getMeltStampId() == null) {
                MeltingStamp entity = new MeltingStamp();
                entity.setMeltStampId(++stampId);
                entity.setMeltingPer(custMelting.getMeltPer());
                entity.setStamp(custMelting.getStamp());
                entity.setCustCode(custCode);
                entity.setDfltFlg((short) 0);
                entities.add(entity);
            }
        }
        if (!entities.isEmpty()) {
            meltingRepo.save(entities);
        }

    }

    @Override
    public List<SelectItem> getCustomerUserList() {
        List<SelectItem> list = new ArrayList<>();
        list.add(new SelectItem(null, "Select"));
        list.add(new SelectItem("%", "All"));
        List<Object[]> objects = generalRepo.getDropdownItems("CustomerDetailOthr", "custCode", "custName");
        for (Object[] obj : objects) {
            list.add(new SelectItem(obj[0], (String) obj[1]));
        }
        return list;

    }

    @Override
    public void deleteCustomerUser(String custCode, Long userId) {
        CustomerDetailOthr customerDetailOthr = customerRepo.getCustomerDtlsByCode(custCode);
        UserDetails userDetails = userDetailsRepo.getUserDtlsByUserCode(custCode);
        if (customerDetailOthr != null) {
            customerDetailOthr.setInactiveFlg((short) 1);
            customerDetailOthr.setInactiveUsrNo((int) (long) userId);
            customerDetailOthr.setInactiveDate(new Date());
            customerDetailOthr.setInactiveDsc("INACTIVE");
            /*
            Map<String, Object> parameter = new HashMap<>();
            parameter.put("inactiveFlg", Short.valueOf("1"));
            generalRepo.updateFldVal("UserDetails", "userCode", custCode, parameter);
             */
            userDetails.setInactiveFlg((short) 1);
            customerRepo.save(customerDetailOthr);
            userDetailsRepo.save(userDetails);
            JSFUtils.addFacesInformationMessage("Deleted Successfully!");
        } else {
            JSFUtils.addFacesErrorMessageRedirect("Selected Customer Does not Exist Please Contact Admin...");
        }
    }

    @Override
    public List<CustomerDetailOthr> getCustomerDtlsList() {
        return customerRepo.findActiveCustomers();//gives List Of customers where inactiveFlg=0
    }

    @Override
    public List<CustomerDetailOthr> getAllCustomerList() {
        return customerRepo.findAll();//gives List Of All customers where inactiveFlg=0 and inactiveFlg=1
    }

    @Override
    public List<CustomerDetailOthr> getCustomerListByCode(String custCode
    ) {
        return customerRepo.getCustomerDtlsListByCode(custCode);
    }

    @Override
    public List<MeltingStamp> getDfltMeltingList() {
        return meltingRepo.findAll();
    }

    @Override
    public CustomerDetailOthr getCustomerDtlsByCode(String custCode
    ) {
        return customerRepo.getCustomerDtlsByCode(custCode);
    }

//    @Override
//    public Optional<UserDetails> getUserDetailsByCode(String custCode) {
//        return userDetailsRepo.getUserDetailsByUserCode(custCode);
//    }
    //    @Override
//    public CustomerDetailOthr getCustomerDtls(String CustCode) {
//        return customerRepo.getCustomerDtlsByCode(CustCode);
//    }
}
