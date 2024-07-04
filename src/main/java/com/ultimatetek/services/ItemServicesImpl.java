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

import com.ultimatetek.config.JSFUtils;
import com.ultimatetek.dao.ItemDtlRepo;
import com.ultimatetek.entity.ItemMst;
import com.ultimatetek.model.ItemVO;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ultimatetek.dao.ItemMstRepo;
import com.ultimatetek.entity.ItemDtl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.faces.model.SelectItem;
import javax.transaction.Transactional;

/**
 *
 * @author JunaidKhan
 */
@Service
public class ItemServicesImpl implements ItemServices {

    @Autowired
    private ItemMstRepo itemMstRepo;
    @Autowired
    private ItemDtlRepo itemDtlRepo;

    @Override
    @Transactional
    public void saveItem(ItemVO item) {
        Optional<ItemMst> opt = itemMstRepo.getItemMstByCode(item.getItemCode());
        Optional<ItemDtl> optional = itemDtlRepo.getItemDtlsByCode(item.getItemCode());
        if (opt.isPresent() && optional.isPresent()) {
            ItemMst mstDetachedEntity = opt.get();
            ItemDtl dtlDetachedEntity = optional.get();
            //adding item in itemMst
            mstDetachedEntity.setItmCode(item.getItemCode());
            mstDetachedEntity.setItmName(item.getItemName());
            mstDetachedEntity.setCrtDate(mstDetachedEntity.getCrtDate());
            mstDetachedEntity.setUpdDate(new Date());
            mstDetachedEntity.setUpdCnt(mstDetachedEntity.getUpdCnt() + 1);
            //Adding item in ItmDtl
            dtlDetachedEntity.setItmCode(item.getItemCode());
            dtlDetachedEntity.setItmSize(dtlDetachedEntity.getItmSize());
            dtlDetachedEntity.setItmPrice(dtlDetachedEntity.getSalesRate());
            dtlDetachedEntity.setVatPer(item.getVatPer());
            dtlDetachedEntity.setItmUnt(item.getItemUom());
            dtlDetachedEntity.setCstPer(item.getCstPer());
            dtlDetachedEntity.setCostRate(item.getCostRate());
            dtlDetachedEntity.setSalesRate(item.getSalesRate());
            short piecewiseVal = item.getValPiecewise() ? (short)1 : (short)0;
            dtlDetachedEntity.setPieceWiseValue(piecewiseVal);
            dtlDetachedEntity.setCrtDate(dtlDetachedEntity.getCrtDate());
            dtlDetachedEntity.setCrtTrmnlNm(dtlDetachedEntity.getCrtTrmnlNm());
            dtlDetachedEntity.setUpdDate(new Date());
            dtlDetachedEntity.setUpdCnt(dtlDetachedEntity.getUpdCnt() + 1);
            itemMstRepo.save(mstDetachedEntity);
            itemDtlRepo.save(dtlDetachedEntity);
            JSFUtils.addFacesInformationMessage("updated Successfully!");
        } else {
            ItemMst itmMstEntity = new ItemMst();
            ItemDtl itemDtlEntity = new ItemDtl();
            //adding item in itemMst
            itmMstEntity.setItmCode(item.getItemCode());
            itmMstEntity.setItmName(item.getItemName());
            itmMstEntity.setCrtUsrNo(1);
            itmMstEntity.setCrtDate(new Date());
            //Adding item in ItmDtl
            itemDtlEntity.setItmCode(item.getItemCode());
            itemDtlEntity.setVatPer(item.getVatPer());
            itemDtlEntity.setItmUnt(item.getItemUom());
            itemDtlEntity.setCstPer(item.getCstPer());
            itemDtlEntity.setCostRate(item.getCostRate());
            itemDtlEntity.setSalesRate(item.getSalesRate());
            itemDtlEntity.setItmSize(1);
            itemDtlEntity.setItmPrice(item.getSalesRate());
            short piecewiseVal = item.getValPiecewise() ? (short)1 : (short)0;
            itemDtlEntity.setPieceWiseValue(piecewiseVal);
            itemDtlEntity.setCrtUsrNo(1);
            itemDtlEntity.setCrtDate(new Date());
            itemDtlEntity.setCrtTrmnlNm("server");
            if (item != null) {
                itemMstRepo.save(itmMstEntity);
                itemDtlRepo.save(itemDtlEntity);
            }
            JSFUtils.addFacesInformationMessage("added Successfully!");
        }
    }

    @Override
    public List<ItemMst> getItemMstList() {
        return itemMstRepo.findAll();
    }

    @Override
    public List<ItemDtl> getItemDtlList() {
        return itemDtlRepo.findAll();
    }

    @Override
    public List<SelectItem> getItemCodeList() {
        List<SelectItem> list = new ArrayList<>();
        List<ItemMst> entities = itemMstRepo.findAll();
        list.add(new SelectItem(null, "Select One..."));
        for (ItemMst itm : entities) {
            list.add(new SelectItem(itm.getItmCode(), itm.getItmCode()));
        }
        return list;
    }

    @Override
    public short chkPieceWiseValue(String itemCode) {
        Object obj = itemDtlRepo.chkPieceWiseValue(itemCode, "Gram");
        short pieceWiseVal = obj == null ? (short) 0 : (Short) obj;
        return pieceWiseVal;
    }

}
