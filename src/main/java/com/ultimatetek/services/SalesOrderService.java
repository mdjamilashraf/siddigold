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

import com.ultimatetek.model.OrderDetailsVO;
import com.ultimatetek.model.OrderSearchParamVO;
import java.util.List;
import java.util.Map;
import javax.faces.model.SelectItem;
import org.apache.commons.collections.map.MultiKeyMap;

/**
 *
 * @author jamil
 */
public interface SalesOrderService {

    List<String> getItemCodeList(String itemCode);

    List<SelectItem> getWorkshopList();

    void saveSalesOrder(String custCode, List<OrderDetailsVO> ordrDtl, Long orderNo);

    List<SelectItem> getCustList();

    Long preAddOrderNo();

    Map<String, String> getItemCodeMap();

    Map<String, String> getMeltingStampMap();

    //public List<OrderDetailsVO> getSalesOrderListByParam(String custCode, Long orderNo, Integer rcrdNo, Integer ordrStatus, String wrkshpCode, Integer orderTyp, String itemCode, String refNo, Integer wrkshpStatus,String wrkrCode, Date dueDate, Date statusDate);
    public List<SelectItem> getOrdrStatusList();

    public List<SelectItem> getWrkshpStatusList();

    public List<SelectItem> getOrderTypList();

    public void updateOrderDtl(OrderDetailsVO ordrDtl);

    public void editCustOrder(OrderDetailsVO selectedOrders);

    public MultiKeyMap getCustMeltingStampMap();

    public List<SelectItem> getWorkerList(String wrkshopCode);

    public void updateWrkshpStatus(OrderDetailsVO ordrDtl);

    public String getMeltingStamp(String custCode, Float meltPer);

    public List<OrderDetailsVO> getOrdersBySearchParam(OrderSearchParamVO searchParam);

    public List<SelectItem> getAllCustList();

    public String getImageFilesName(OrderDetailsVO selectedOrders);

    public void delSelectedorders(List<OrderDetailsVO> selectedOrdrLst);

    public void downloadSelectedOrders(List<OrderDetailsVO> selectedOrdrLst);

    public int getOrderNoValue(Long orderNo);
    
    public List<SelectItem> getPriorityTypList();

}
