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

import com.ultimatetek.entity.MeltingStamp;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jamil
 */
@Repository
public interface DefaultMeltingRepo extends JpaRepository<MeltingStamp, Integer> {

    @Query("Select m FROM MeltingStamp m where 1=1 and m.custCode=?1")
    public List<MeltingStamp> getDfltMeltingListByCustCode(String CustCode);

    @Query("Select m FROM MeltingStamp m where 1=1 and m.custCode is null and m.dfltFlg=1")
    public List<MeltingStamp> getDefaultMeltingList();

    @Query("update MeltingStamp m set m.meltingPer=?1 ,m.stamp=?2 where m.meltStampId=?3")
    @Modifying
    @Transactional
    public void updCustMelting(Integer meltPer, String stamp, Integer meltStampId);

    @Query("Select m from MeltingStamp m where 1=1 and m.meltStampId=?1")
    public Optional<MeltingStamp> getMeltingDtlsByMeltingId(Integer meltStampId);

    @Query("Select m.stamp from MeltingStamp m where 1=1 and m.meltingPer=?1 and dfltFlg=1")
    public String getDfltMeltingStamp(Integer meltingPer);

    @Query("Select m.stamp from MeltingStamp m where 1=1 and m.custCode=?1 and m.meltingPer=?2")
    public String getCustomerStamp( String custCode, Integer meltingPer);

}
