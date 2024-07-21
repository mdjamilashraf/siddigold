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
package com.ultimatetek.dao;

import com.ultimatetek.entity.OrderDetailsJewellery;
import com.ultimatetek.model.DashboardData;
import com.ultimatetek.model.OrderDetailsVO;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author JunaidKhan
 */
public interface GeneralRepo {

    public Object getAutoIncrementVal(String tabNm, String fldNm, String usrWhr);

    public String getPreAddRefNo(String custCode, Long orderNo);

    public List<Object[]> getDropdownItems(String tableNm, String itemVal, String itemLbl);

    //public List<Object[]> getDropdownItems(String tableNm, String itemVal, String itemLbl, String whr);
    public void delByCode(String tabNm, String fldNm, Object fldVal);

    public List<OrderDetailsJewellery> getSalesOrderListByParam(Long orderNo, Integer rcrdNo, String custCode, Integer ordrStatus, String wrkshpCode, Integer orderTyp, String itemCode, String refNo, Integer wrkshpStatus, String wrkrCode, Date dueDate, Date statusDate);

    public Map<Object, Object> getSelectItemMap(String entityName, String itemVal, String itemLbl, Set<String> inClaus);

    public void updateOrderStatusByAdmin(OrderDetailsVO ordrDtl);

    public void updateWrkshpStatus(OrderDetailsVO ordrDtl);

    public void editCustOrder(OrderDetailsVO ordrDtl);

    public Long chkFldValueExist(String tableNm, String fldNm, String fldVal, String usrWhr);

    public List<Object[]> getCustMeltingStampMap();

    public List<Object[]> getOrdrDtlsBycustCode(String custCode);

    public List<Object[]> getDropdownItemsOrderBy(String tableNm, String itemVal, String itemLbl, String usrWhr, String ordrByFld);

    public Object getFieldValue(String tblNm, String fldVal, Map<String, Object> parameter);

    public List<OrderDetailsJewellery> getOrdersBySearchParam(Map<String, Object> param);

    public List<Object> getAutoCompleteFldLst(String tblNm, String fldNm);

    public int updateFldVal(String tblNm, String fldNm, Object fldVal, Map<String, Object> parameter);

    public void delAllByCode(String tblNm, String fldNm, List<Object> orderNo);

    public void resetAllExistData(String tblNm, String fldNm, Object fldVal);

    public void insertIntoOrderDetailsHist(Long minOrdrNo, Long maxOrdrNo);

    public void insertIntoSalesOrdrHist(Long minOrdrNo, Long maxOrdrNo);

    public DashboardData getDashboardData();

    public List<OrderDetailsVO> getTodayOrderDue(String formattedDateForMysql);
    
    public List<Object[]> getTopCustomersByWeight(Date startDate);
}
