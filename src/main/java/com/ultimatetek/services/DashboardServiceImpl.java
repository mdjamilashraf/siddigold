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

import com.ultimatetek.dao.GeneralRepo;
import com.ultimatetek.model.DashboardData;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jamil
 */
@Service
public class DashboardServiceImpl implements DashboardService {
    
    @Autowired
    private GeneralRepo generalRepo;

    @Override
    public DashboardData generateDashboardData() {
        return generalRepo.getDashboardData();
//        Object[] objects =  generalRepo.getDashboardData();
//        DashboardData data = new DashboardData();
//        data.setTodayOrderCnt((Integer)objects[0]);
//        data.setMonthlyOrderCnt((Integer)objects[1]);
//        return data;
    }
    
}
