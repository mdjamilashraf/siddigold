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

import com.ultimatetek.entity.ItemMst;
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
public interface ItemMstRepo extends JpaRepository<ItemMst, String> {

    @Query("Select itmCode from ItemMst where upper(itmCode) like ?1")
    public List<String> getItemCodeList(String code);

    @Query("Select i from ItemMst i where i.itmCode=?1")
    Optional<ItemMst> getItemMstByCode(String itmCode);

    @Query("Select i.itmName from ItemMst i where i.itmCode=?1")
    public String getItemNameByCode(String itmCode);

    @Query("Select i.itmCode from ItemMst i where i.itmName=?1")
    public String getItemCodeByItemName(String itmName);

 

}
