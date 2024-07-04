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
import com.ultimatetek.entity.MeltingStamp;
import com.ultimatetek.model.DefaultMeltingVO;
import com.ultimatetek.services.DefaultMeltingService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import lombok.Data;
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
public class DefaultMeltingView implements Serializable {

    private DefaultMeltingVO dfltMelting = new DefaultMeltingVO();
    private List<DefaultMeltingVO> meltingList;
    private DefaultMeltingVO selectedDfltMltng;
    private Boolean showList = false;

    @Autowired
    private DefaultMeltingService defaultMeltingService;

    @PostConstruct
    public void init() {
        this.getMeltingStampList();
        if (meltingList != null && !meltingList.isEmpty()) {
            this.showList = true;
        } else {
            this.showList = false;
        }

    }

    public String addMeltingStamp() {
        defaultMeltingService.saveDefaultMelting(this.dfltMelting);
        this.getMeltingStampList();
        return "dfltMelting";
    }

    public void resetData() {
        this.dfltMelting.setMeltingPer(null);
        this.dfltMelting.setStamp(null);;
        JSFUtils.reloadThePage();
    }

    private void getMeltingStampList() {
        /**
         * Display added stamp
         */
        List<MeltingStamp> list = defaultMeltingService.getDefaultMeltingList();
        List<DefaultMeltingVO> meltStampList = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            for (MeltingStamp stmp : list) {
                DefaultMeltingVO obj = new DefaultMeltingVO();
                obj.setMeltStampId(stmp.getMeltStampId());
                obj.setMeltingPer(stmp.getMeltingPer());
                obj.setStamp(stmp.getStamp());
                meltStampList.add(obj);
            }
            this.setMeltingList(meltStampList);
            this.showList = true;
        }
    }

    public void editDfltMltng() {
        this.setDfltMelting(this.selectedDfltMltng);
    }
}