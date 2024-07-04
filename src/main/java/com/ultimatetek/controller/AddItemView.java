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
package com.ultimatetek.controller;

import com.ultimatetek.config.JSFUtils;
import com.ultimatetek.entity.ItemDtl;
import com.ultimatetek.entity.ItemMst;
import com.ultimatetek.model.ItemVO;
import com.ultimatetek.services.ItemServices;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
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
public class AddItemView implements Serializable {

    private ItemVO item = new ItemVO();
    private List<ItemVO> itemList;
    private ItemVO SelectedItems;
    private Boolean showList = false;

    @Autowired
    private ItemServices itemServices;

    @PostConstruct
    public void init() {
        this.getItemsList();
        if (itemList != null && !itemList.isEmpty()) {
            this.showList = true;
        } else {
            this.showList = false;
        }
    }

    public String addItem() {
        try {
            itemServices.saveItem(this.item);
            this.getItemsList();
            this.resetData();
            return "addItem";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    private void getItemsList() {
        List<ItemMst> itemMstList = itemServices.getItemMstList();
        Map<String, String> mapItemNm = new HashMap<>();
        for (ItemMst itm : itemMstList) {
            mapItemNm.put(itm.getItmCode(), itm.getItmName());
        }
        List<ItemDtl> itemDtlList = itemServices.getItemDtlList();
        List<ItemVO> itemVOs = new ArrayList<>();
        for (ItemDtl itemDtl : itemDtlList) {
            ItemVO itmVO = new ItemVO();
            itmVO.setItemCode(itemDtl.getItmCode());
            itmVO.setItemUom(itemDtl.getItmUnt());
            if (itemDtl.getVatPer() != null) {
                itmVO.setVatPer(itemDtl.getVatPer());
            }
            if (itemDtl.getCstPer() != null) {
                itmVO.setCstPer(itemDtl.getCstPer());
            }
            if (itemDtl.getCostRate() != null) {
                itmVO.setCostRate(itemDtl.getCostRate());
            }
            if (itemDtl.getSalesRate() != null) {
                itmVO.setSalesRate(itemDtl.getSalesRate());
            }
            itmVO.setPieceWiseValue(itemDtl.getPieceWiseValue());
            if (itmVO.getPieceWiseValue() == 0) {
                itmVO.setValPiecewise(false);
            } else {
                itmVO.setValPiecewise(true);
            }
            itmVO.setItemName(mapItemNm.get(itemDtl.getItmCode()));
            itemVOs.add(itmVO);
        }

        this.setItemList(itemVOs);
        this.showList = true;
    }

    public void resetData() {
        this.item.setItemCode(null);
        this.item.setItemName(null);
        this.item.setItemUom(null);
        this.item.setVatPer(null);
        this.item.setCstPer(null);
        this.item.setCostRate(null);
        this.item.setSalesRate(null);
        this.item.setValPiecewise(Boolean.FALSE);
        JSFUtils.reloadThePage();
    }

    public void editItem() {
        this.setItem(this.SelectedItems);
    }
}
