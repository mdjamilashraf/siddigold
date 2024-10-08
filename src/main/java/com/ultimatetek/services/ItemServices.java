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

import com.ultimatetek.entity.ItemDtl;
import com.ultimatetek.entity.ItemMst;
import com.ultimatetek.model.ItemVO;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author JunaidKhan
 */
public interface ItemServices {

    public void saveItem(ItemVO item);

    public List<ItemMst> getItemMstList();

    public List<ItemDtl> getItemDtlList();

    public List<SelectItem> getItemCodeList();

    public short chkPieceWiseValue(String itemCode);

}
