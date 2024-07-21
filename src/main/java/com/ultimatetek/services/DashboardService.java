/*
 * Copyright 2024 JoinFaces.
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

import com.ultimatetek.model.DashboardData;
import com.ultimatetek.model.OrderDetailsVO;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jamil
 */
public interface DashboardService {

    public DashboardData generateDashboardData();

    public List<OrderDetailsVO> getTodayOrderDue();
    
    public List<OrderDetailsVO> getTopCustomersByWeight(Date startDate);
    
}
