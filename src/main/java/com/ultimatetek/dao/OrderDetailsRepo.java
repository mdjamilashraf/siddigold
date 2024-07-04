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

import com.ultimatetek.entity.OrderDetailsJewellery;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author jamil
 */
public interface OrderDetailsRepo extends JpaRepository<OrderDetailsJewellery, Long> {

    @Query("select ordrDtl from OrderDetailsJewellery ordrDtl where ordrDtl.salesOrder.orderNo=?1 and ordrDtl.salesOrder.custCode=?2")
    public List<OrderDetailsJewellery> getSalesOrderListByParam(Long orderNo, String custCode);

    @Query("from OrderDetailsJewellery where salesOrder.orderNo IN:orderNo")
    public List<OrderDetailsJewellery> getOrderDtlsListByOrderNo(@Param("orderNo") List<Object> orderNo);

    @Query("select ordrDtl from OrderDetailsJewellery ordrDtl where ordrDtl.salesOrder.orderNo=?1")
    public List<OrderDetailsJewellery> getOrderDtlsListByCode(Long orderNo);

    @Query("select ordrDtl from OrderDetailsJewellery ordrDtl where 1=1 and refNo=?1")
    public OrderDetailsJewellery getOrderDtlsByRefNo(String refNo);

    @Query("select ordrDtl.imgPath1 from OrderDetailsJewellery ordrDtl where 1=1 and ordrDtl.orderDtlNo=?1")
    public String getImagePath(Long orderDtlNo);

    @Query("select ordrDtl from OrderDetailsJewellery ordrDtl where 1=1 and ordrDtl.orderDtlNo=?1")
    public OrderDetailsJewellery getOrderDtlsByOrderDtlNo(Long orderDtlNo);

    @Query("Select Max(ordrDtl.orderDtlNo)+1 from OrderDetailsJewellery ordrDtl where ordrDtl.salesOrder.orderNo=ordrDtl.salesOrder.orderNo and ordrDtl.salesOrder.custCode=?1")
    public Long getMaxOrdrByCustCode(String custCode);


}
