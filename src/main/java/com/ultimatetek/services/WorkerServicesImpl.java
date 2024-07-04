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
import com.ultimatetek.dao.GeneralRepo;
import com.ultimatetek.dao.WorkshopDetailOthrRepo;
import com.ultimatetek.entity.Worker;
import com.ultimatetek.model.WorkerVO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ultimatetek.dao.WorkerRepo;

/**
 *
 * @author ULTCPU20
 */
@Service
public class WorkerServicesImpl implements WorkerServices {

    @Autowired
    private GeneralRepo generalRepo;
    @Autowired
    private WorkerRepo workerRepo;
    @Autowired
    private WorkshopDetailOthrRepo workshopDetailOthrRepo;

    @Override
    public void addWorker(WorkerVO workerVO) {
        Optional<Worker> opt = workerRepo.getWorkerByWrkrId(workerVO.getWorkerId());
        if (opt.isPresent()) {
            Worker detachedEntity = opt.get();
            detachedEntity.setWrkrId(detachedEntity.getWrkrId());
            detachedEntity.setWrkrName(workerVO.getWorkerName());
            detachedEntity.setWrkrMobile(workerVO.getWorkerMobile());
            detachedEntity.setWrkshpCode(detachedEntity.getWrkshpCode());
            detachedEntity.setCrtDate(detachedEntity.getCrtDate());
            detachedEntity.setUpdDate(new Date());
            detachedEntity.setUpdTrmnlNm("server");
            detachedEntity.setUpdCnt(opt.get().getUpdCnt() + 1);
            workerRepo.save(detachedEntity);
            JSFUtils.addFacesInformationMessage("updated Successfully!");
        } else {
            Integer maxwrkrId = (Integer) generalRepo.getAutoIncrementVal("Worker", "wrkrId", null);
            Integer wrkrId = (maxwrkrId == null || maxwrkrId == 0) ? 1 : ++maxwrkrId;
            Worker entity = new Worker();
            entity.setWrkrId((long) wrkrId);
            entity.setWrkrName(workerVO.getWorkerName());
            entity.setWrkrMobile(workerVO.getWorkerMobile());
            entity.setWrkshpCode(workshopDetailOthrRepo.getWorkshopCode(workerVO.getWrkshpCode()));
            entity.setCrtUsrNo(1);
            entity.setCrtDate(new Date());
            workerRepo.save(entity);
            JSFUtils.addFacesInformationMessage("added Successfully!");
        }
    }

    @Override
    public List<Worker> getWorkerUserList() {
        return workerRepo.getWorkerList();
    }

    @Override
    public List<WorkerVO> getWorkers(String wrkshpCode) {
        List<Worker> entites = workerRepo.getWorkersByCode(wrkshpCode);
        List<WorkerVO> workerList = new ArrayList<>();
        if (entites != null && !entites.isEmpty()) {
            for (Worker wrkr : entites) {
                WorkerVO obj = new WorkerVO();
                obj.setWorkerId(wrkr.getWrkrId());
                obj.setWorkerName(wrkr.getWrkrName());
                obj.setWorkerMobile(wrkr.getWrkrMobile());
                obj.setWrkshpCode(wrkr.getWrkshpCode().toString());
                workerList.add(obj);
            }
        }
        return workerList;
    }
}
