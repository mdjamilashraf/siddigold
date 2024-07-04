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
package com.ultimatetek.config;

import com.ultimatetek.model.OrderDetailsVO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jamil
 */
public class SalesOrderUtils {
    private static Map<Integer, String> orderStatusMap = new HashMap<>();
    private static Map<Integer, String> mapOrderType = new HashMap<>();
    private static Map<Integer, String> wrkshpStatusMap = new HashMap<>();
    static 
    {
        orderStatusMap.put(1, "Confirmed");
        orderStatusMap.put(2, "Assigned");
        orderStatusMap.put(3, "Closed");
        orderStatusMap.put(4, "Cancelled");
        orderStatusMap.put(5, "Pending");
        
        mapOrderType.put(1, "Order");
        mapOrderType.put(2, "Repair");
        
        wrkshpStatusMap.put(1, "Received");
        wrkshpStatusMap.put(2, "Issued to worker");
        wrkshpStatusMap.put(3, "Closed");
        wrkshpStatusMap.put(4, "Cancelled");
    }
    
    public static String getStatus(int statusTyp) {
        return orderStatusMap.get(statusTyp);
    }
    public static String getOrderTypLbl(int orderTyp) {
        return mapOrderType.get(orderTyp);
    }

    public static String getWorkshopStatus(int wrkshpStatus) {
        return wrkshpStatusMap.get(wrkshpStatus);
    }

    public static float calculateTotleWt(List<OrderDetailsVO> orders) {
        float itmsWt = 0;
        for (OrderDetailsVO ordrDtlVO : orders) {
            itmsWt += ordrDtlVO.getNetWeight();
        }
        return itmsWt;
    
    }
}
