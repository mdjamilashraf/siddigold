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

import com.ultimatetek.entity.CustomerDetailOthr;
import com.ultimatetek.entity.MeltingStamp;
import com.ultimatetek.model.CustMeltingVO;
import com.ultimatetek.model.CustomerVO;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author JunaidKhan
 */
public interface CustUserService {

    public void saveCustomerUser(CustomerVO customerUserVO, Long userId, List<CustMeltingVO> meltingList);

    public List<SelectItem> getCustomerUserList();

//    public CustomerDetailOthr getCustomerDtls(String CustCode);
    public List<MeltingStamp> getDfltMeltingList(String CustCode);

//    public Optional<UserDetails> getUserDetailsByCode(String custCode);
    public List<CustomerDetailOthr> getCustomerDtlsList();

    public List<CustomerDetailOthr> getAllCustomerList();

    public List<CustomerDetailOthr> getCustomerListByCode(String custCode);

    public List<MeltingStamp> getDfltMeltingList();

    public CustomerDetailOthr getCustomerDtlsByCode(String custCode);

    public void deleteCustomerUser(String custCode, Long userId);

}
