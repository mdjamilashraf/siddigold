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

import com.ultimatetek.entity.SalesOrder;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jamil
 */
@Repository
public interface SalesOrderRepo extends JpaRepository<SalesOrder, Long> {

    @Query("select s from SalesOrder s where 1=1 and s.custCode=?1")
    public List<SalesOrder> getSalesOrdrByCustCode(String custCode);

    @Modifying
    @Query("update SalesOrder s set s.custCode=?1 where s.orderNo=?2")
    public void updateCustCode(String custCode, Long orderNo);

    @Query("select count(*) from SalesOrder s where 1=1 and s.custCode=?1")
    public Long checkSalesOrdrByCustCode(String custCode);

    @Override
    Page<SalesOrder> findAll(Pageable pageable);

    @Query(value = "from SalesOrder so where so.orderNo IN :orderNo")
    public List<SalesOrder> findAllByOrderNoList(@Param("orderNo") List<Object> orderNo);

    @Query("select so.orderNo from SalesOrder so order by so.orderNo")
    public List<Long> findAllByOrderByOrderNo();

    @Query("select count(1) from SalesOrder s where 1=1 and s.orderNo=?1")
    public long checkValueExistByOrderNo(Long orderNo);

}
