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
package com.ultimatetek.dao;

import com.ultimatetek.entity.CustomerDetailOthr;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author JunaidKhan
 */
@Repository
public interface CustomerRepo extends JpaRepository<CustomerDetailOthr, String> {

    @Query("Select c FROM CustomerDetailOthr c where 1=1 and c.custCode=?1")
    public List<CustomerDetailOthr> getCustomerDtlsListByCode(String custCode);

    @Query("Select c FROM CustomerDetailOthr c where 1=1 and c.custCode=?1")
    public CustomerDetailOthr getCustomerDtlsByCode(String custCode);

    @Query("Select c FROM CustomerDetailOthr c where 1=1 and c.custCode=?1")
    public Optional<CustomerDetailOthr> getCustomerDetailsByCode(String custCode);

    @Query("Select c from CustomerDetailOthr c where 1=1 and c.inactiveFlg=0")
    public List<CustomerDetailOthr> findActiveCustomers();

    @Query("Select c.custName from CustomerDetailOthr c where 1=1 and c.custCode=?1")
    public String getCustomerNmbyCode(String custCode);

}
