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

import com.ultimatetek.dao.GeneralRepo;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author jamil
 */
@Configuration
public class OnloadDataConfig {
    
    private static Map<Object, Object> itemsMap;
    private static List<Object> list; 
    
    @Autowired
    private GeneralRepo gnrRepo;
    
    @PostConstruct
    void init() {
        System.out.println("com.ultimatetek.config.OnloadDataConfig.init()");
        itemsMap = gnrRepo.getSelectItemMap("ItemMst", "itmCode", "itmName", new HashSet<>());
        list = gnrRepo.getAutoCompleteFldLst("ItemMst", "itmCode");
    }

    public static Map<Object, Object> getItemsMap() {
        return itemsMap;
    }

    public static List<Object> getList() {
        return list;
    }

    
}
