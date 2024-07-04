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

import com.ultimatetek.config.JSFUtils;
import com.ultimatetek.dao.DefaultMeltingRepo;
import com.ultimatetek.dao.GeneralRepo;
import com.ultimatetek.entity.MeltingStamp;
import com.ultimatetek.model.DefaultMeltingVO;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ULTCPU20
 */
@Service
public class DefaultMeltingServiceImpl implements DefaultMeltingService {

    @Autowired
    private DefaultMeltingRepo meltingRepo;
    @Autowired
    private GeneralRepo generalRepo;

    @Override
    public void saveDefaultMelting(DefaultMeltingVO defaultMelting) {
        Optional<MeltingStamp> opt = meltingRepo.getMeltingDtlsByMeltingId(defaultMelting.getMeltStampId());
        if (opt.isPresent() && opt.get().getMeltStampId() != null) {
            MeltingStamp detachedEntity = opt.get();
            detachedEntity.setMeltingPer(defaultMelting.getMeltingPer());
            detachedEntity.setStamp(defaultMelting.getStamp());
            meltingRepo.save(detachedEntity);
            JSFUtils.addFacesInformationMessage("updated Successfully!");
        } else {
            Integer maxMeltStampId = (Integer) generalRepo.getAutoIncrementVal("MeltingStamp", "meltStampId", null);
            Integer MeltStampId = (maxMeltStampId == null || maxMeltStampId == 0) ? 1 : ++maxMeltStampId;
            MeltingStamp entity = new MeltingStamp();
            entity.setMeltStampId(MeltStampId);
            entity.setMeltingPer(defaultMelting.getMeltingPer());
            entity.setStamp(defaultMelting.getStamp());
            entity.setDfltFlg((short) 1);
            meltingRepo.save(entity);
            JSFUtils.addFacesInformationMessage("added Successfully!");
        }
    }

    @Override
    public List<MeltingStamp> getDefaultMeltingList() {
        return meltingRepo.getDefaultMeltingList();
    }
}
