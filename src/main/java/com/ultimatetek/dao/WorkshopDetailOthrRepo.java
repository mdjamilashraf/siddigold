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

import com.ultimatetek.entity.WorkshopDetailOthr;
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
public interface WorkshopDetailOthrRepo extends JpaRepository<WorkshopDetailOthr, String> {

    @Query("Select w FROM WorkshopDetailOthr w where 1=1 and w.wrkshpCode=?1")
    public List<WorkshopDetailOthr> getWorkshopDtlsListByCode(String wrkshpCode);

    @Query("Select w FROM WorkshopDetailOthr w where 1=1 and w.wrkshpCode=?1")
    public WorkshopDetailOthr getWorkshopDtlsByCode(String wrkshpCode);

    @Query("Select w.wrkshpCode FROM WorkshopDetailOthr w where w.wrkshpCode=?1")
    public WorkshopDetailOthr getWorkshopCode(String code);

    @Query("Select w FROM WorkshopDetailOthr w where 1=1 and w.wrkshpCode=?1")
    public Optional<WorkshopDetailOthr> getWorkShopDetailsByWrkshpCode(String wrkshpCode);
}
