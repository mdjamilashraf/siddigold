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
package com.ultimatetek.controller;

import com.ultimatetek.config.JSFUtils;
import com.ultimatetek.model.WorkerVO;
import com.ultimatetek.services.WorkerServices;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author jamil
 */
@Component
@ManagedBean
@ViewScoped
@Data
public class ManageWorkerView implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ManageWorkerView.class);
    private WorkerVO worker = new WorkerVO();
    private List<WorkerVO> workers;
    private WorkerVO selectedWorkr;
    private Boolean showList = false;
    private String logUsrCode = (String)JSFUtils.getFromSession("userCode");

    @Autowired
    private WorkerServices workerServices;

    @PostConstruct
    public void init() {
        this.getWorkerList();
        if (workers != null && !workers.isEmpty()) {
            this.showList = true;
        } else {
            this.showList = false;
        }
    }

    public String addWorker() {
        this.worker.setWrkshpCode(logUsrCode);
        workerServices.addWorker(this.worker);
        this.getWorkerList();
        return "manageWorker";
    }

    private void getWorkerList() {
        List<WorkerVO> workerList = workerServices.getWorkers(logUsrCode);
        /*List<Worker> list = workerServices.getWorkerUserList();
        List<WorkerVO> workerList = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            for (Worker wrkr : list) {
                WorkerVO obj = new WorkerVO();
                obj.setWorkerId(wrkr.getWrkrId());
                obj.setWorkerName(wrkr.getWrkrName());
                obj.setWorkerMobile(wrkr.getWrkrMobile());
                obj.setWrkshpCode(wrkr.getWrkshpCode().toString());
                workerList.add(obj);
            }*/
        if (workerList != null && !workerList.isEmpty()) {
            this.setWorkers(workerList);
            this.showList = true;
        }
    }

    public void editWorker() {
        this.setWorker(this.selectedWorkr);
    }

}