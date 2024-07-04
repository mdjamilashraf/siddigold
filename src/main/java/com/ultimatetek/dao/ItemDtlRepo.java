/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ultimatetek.dao;

import com.ultimatetek.entity.ItemDtl;
import com.ultimatetek.entity.ItemDtlPK;
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
public interface ItemDtlRepo extends JpaRepository<ItemDtl, ItemDtlPK> {

    @Query("select pieceWiseValue from ItemDtl where itmCode=?1 and itmUnt=?2")
    public Object chkPieceWiseValue(String itemCode, String itemUnt);

    @Query("Select i from ItemDtl i where i.itmCode=?1")
    public Optional<ItemDtl> getItemDtlsByCode(String itemCode);
    
    @Query("Select i from ItemDtl i order by itmCode")
    public List<ItemDtl> getItemDtlListOrdrByCode();
}
