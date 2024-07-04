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
package com.ultimatetek.services;

import com.ultimatetek.entity.UserDetails;
import com.ultimatetek.entity.WorkshopDetailOthr;
import com.ultimatetek.model.WorkshopVO;
import java.util.List;
import java.util.Optional;
import javax.faces.model.SelectItem;

/**
 *
 * @author JunaidKhan
 */
public interface WorkShopUserService {

    public void saveUserWorkshop(WorkshopVO wrkshpVO, Long userId);

    public List<SelectItem> getWorkshopUserList();

    public void updateWorkshopUser(WorkshopVO wrkshpVO);

    public void deleteWorkShopDtls(List<WorkshopVO> selectedWrkshp);

    public WorkshopDetailOthr getWorkshopDtls(String wrkshpCode);

    public Optional<UserDetails> getUserDetailsByCode(String wrkshpCode);

    public List<WorkshopDetailOthr> getWorkshopDtlsList();

    public List<WorkshopDetailOthr> getWorkshopListByCode(String wrkshpCode);

    public WorkshopDetailOthr getWorkshopDtlsByCode(String wrkshpCode);
}
