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

import com.ultimatetek.config.DateUtils;
import com.ultimatetek.config.HttpJSFUtil;
import com.ultimatetek.config.SalesOrderUtils;
import com.ultimatetek.config.StringUtils;
import com.ultimatetek.dao.GeneralRepo;
import com.ultimatetek.dao.ItemMstRepo;
import com.ultimatetek.dao.OrderDetailsRepo;
import com.ultimatetek.dao.SalesOrderRepo;
import com.ultimatetek.entity.OrderDetailsJewellery;
import com.ultimatetek.entity.SalesOrder;
import com.ultimatetek.model.OrderDetailsVO;
import com.ultimatetek.model.OrderSearchParamVO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.map.MultiKeyMap;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import org.modelmapper.ModelMapper;

/**
 *
 * @author jamil
 */
@Service
public class SalesOrderServiceImpl implements SalesOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SalesOrderServiceImpl.class);
    @Autowired
    private ItemMstRepo itemMstRepo;
    @Autowired
    private GeneralRepo gnrRepo;
    @Autowired
    private SalesOrderRepo salesOrderRepo;
    @Autowired
    private OrderDetailsRepo orderDtlRepo;
//    @Autowired
//    private SalesOrderHstryRepo salesOrderHstryRepo;
//    @Autowired
//    private OrderDetailsJewelleryHstryRepo orderDetailsJewelleryHstryRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<String> getItemCodeList(String itmCode) {
        return itemMstRepo.getItemCodeList(itmCode.toUpperCase());
    }

    @Override
    public List<SelectItem> getWorkshopList() {
        List<SelectItem> list = new ArrayList<>();
        list.add(new SelectItem(null, "Select..."));
        List<Object[]> objects = gnrRepo.getDropdownItemsOrderBy("WorkshopDetailOthr", "wrkshpCode", "wrkshpName", null, "wrkshpName");
        //List<Object[]> objects = gnrRepo.getDropdownItems("WorkshopDetailOthr", "wrkshpCode", "wrkshpName");
        for (Object[] obj : objects) {
            list.add(new SelectItem(obj[0], (String) obj[1]));
        }
        return list;
    }

    @Override
    @Transactional
    public void saveSalesOrder(String custCode, List<OrderDetailsVO> ordrDtl, Long orderNo) {
        //check orderNO already exist 

        SalesOrder entity = new SalesOrder();
        entity.setOrderNo(orderNo);
        entity.setSoType((short) 1);
        entity.setOrderDate(DateUtils.getTodayDate());
        entity.setCustCode(custCode);
        entity.setCurCode("INR");
        entity.setCurRate(1);
        entity.setStockRate(1);
        entity.setDiscPer(0);
        entity.setDiscAmt(0);
        entity.setTaxPer(0);
        entity.setTaxAmt((long) 0);
        entity.setCrtUsrNo(1);
        entity.setCrtDate(DateUtils.getTodayDate());
        entity.setCrtTrmnlNm("Server");
        entity.setUpdCnt(0);
        entity.setStopSeq(0);

        int rcrdNo = 0;
        long ordrDtlNo = (long) (Integer) gnrRepo.getAutoIncrementVal("OrderDetailsJewellery", "orderDtlNo", null);
        List<OrderDetailsJewellery> list = new ArrayList<>();
        for (OrderDetailsVO ordrDtlVo : ordrDtl) {
            if (ordrDtlVo.getItemCode() == null
                    || ordrDtlVo.getItemName() == null
                    || ordrDtlVo.getItemUnit() == null
                    || ordrDtlVo.getQty() == null
                    || ordrDtlVo.getItemSize() == null
                    || ordrDtlVo.getMeltPer() == null
                    || ordrDtlVo.getWeight() == null
                    || ordrDtlVo.getDays() == null
                    || ordrDtlVo.getDueDate() == null
                    || ordrDtlVo.getWorkshop() == null
                    || ordrDtlVo.getOrderTyp() == null) {
                continue;
            }

            OrderDetailsJewellery ordrDtlEntity = new OrderDetailsJewellery();
            ordrDtlEntity.setOrderDtlNo(++ordrDtlNo);
            ordrDtlEntity.setItemCode(ordrDtlVo.getItemCode());
            ordrDtlEntity.setItemWeight(ordrDtlVo.getWeight());
            ordrDtlEntity.setItemSize(ordrDtlVo.getItemSize());
            ordrDtlEntity.setItemUnit(ordrDtlVo.getItemUnit());
            ordrDtlEntity.setItemQty(ordrDtlVo.getQty());
            ordrDtlEntity.setMeltPer(ordrDtlVo.getMeltPer());
            ordrDtlEntity.setStamp(ordrDtlVo.getStamp());
            ordrDtlEntity.setHook(ordrDtlVo.getHook());
            ordrDtlEntity.setDesignSample(ordrDtlVo.getDesignSample());
            ordrDtlEntity.setSizeSample(ordrDtlVo.getSizeSample());
            ordrDtlEntity.setRefNo(ordrDtlVo.getRefNo());
            ordrDtlEntity.setRemarks(ordrDtlVo.getRemark());
            ordrDtlEntity.setDays(ordrDtlVo.getDays());
            ordrDtlEntity.setDueDate(ordrDtlVo.getDueDate());
            ordrDtlEntity.setWrkshpCode(ordrDtlVo.getWorkshop());
            String priceTyp = ordrDtlVo.getFixRate().substring(0, 1);
            ordrDtlEntity.setPriceType(priceTyp);
            String price = ordrDtlVo.getFixRate().substring(1);
            ordrDtlEntity.setItemPrice(Double.parseDouble(price));
            ordrDtlEntity.setOrderType(ordrDtlVo.getOrderTyp());
            if (ordrDtlVo.getRcvSample()) {
                ordrDtlEntity.setRcvSample((short) 1);
            } else {
                ordrDtlEntity.setRcvSample((short) 0);
            }
            ordrDtlEntity.setOrderStatus(1);
            if (ordrDtlVo.getWorkshop() != null) {
                ordrDtlEntity.setWrkshpStatus(1);//Recive
                ordrDtlEntity.setWrkshpStatusDate(DateUtils.getTodayDate());
            }
            ordrDtlEntity.setPriority(ordrDtlVo.getPriority());
            ordrDtlEntity.setCrtDate(DateUtils.getTodayDate());
            ordrDtlEntity.setCrtTrmnlNm("Server");
            ordrDtlEntity.setCrtUsrNo(1);
            ordrDtlEntity.setUpdCnt(0);
            ordrDtlEntity.setRcrdNo(++rcrdNo);
            ordrDtlEntity.setSalesOrder(entity);
            LOGGER.info("adding ordr details in list");
            //LOGGER.info(ordrDtlVo.toString());
            list.add(ordrDtlEntity);
        }
        if (!list.isEmpty()) {
            try {
                salesOrderRepo.save(entity);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                orderDtlRepo.save(list);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public List<SelectItem> getCustList() {
        List<SelectItem> list = new ArrayList<>();
        list.add(new SelectItem(null, "Select..."));
        List<Object[]> objects = gnrRepo.getDropdownItemsOrderBy("CustomerDetailOthr", "custCode", "custName", "inactiveFlg=0", "custName");//changes usrWhr as inactiveflg=0
        for (Object[] obj : objects) {
            list.add(new SelectItem(obj[0], (String) obj[1]));
        }
        return list;
    }

    @Override
    public Long preAddOrderNo() {
        Integer newOrdrNo = (Integer) gnrRepo.getAutoIncrementVal("SalesOrder", "orderNo", " and stopSeq = 0");
        return (long) ++newOrdrNo;

        /* List<Long> existingOrdrNos = salesOrderRepo.findAllByOrderByOrderNo();
        // Find the lowest available orderNo.
        int lowestAvailableOrdrNo = findLowestAvailableOrderNo(existingOrdrNos);
        // Use the lowestAvailableOrdrNo for the new record.
        return (long) lowestAvailableOrdrNo;*/
    }

//    private static int findLowestAvailableOrderNo(List<Long> existingOrdrNos) {
//        int lowestAvailableOrdrNumber = 1;
//        for (long orderNo : existingOrdrNos) {
//            System.out.println(orderNo);
//            if (orderNo == lowestAvailableOrdrNumber) {
//                // The current order number is in sequence.
//                lowestAvailableOrdrNumber++;
//                
//            } else {
//                // Found a gap in the sequence.
//                return lowestAvailableOrdrNumber;
//            }
//        }
//        // If no gaps found, use the next number in the sequence.
//        return lowestAvailableOrdrNumber;
//    }
    @Override
    public Map<String, String> getItemCodeMap() {
        Map<String, String> map = new HashMap<>();
        List<Object[]> objects = gnrRepo.getDropdownItems("ItemMst", "itmCode", "itmName");
        for (Object[] obj : objects) {
            map.put((String) obj[0], (String) obj[1]);
        }
        return map;
    }

    @Override
    public Map<String, String> getMeltingStampMap() {
        Map<String, String> map = new HashMap<>();
        List<Object[]> objects = gnrRepo.getDropdownItemsOrderBy("MeltingStamp", "meltingPer", "stamp", " dfltFlg=1", null);
        for (Object[] obj : objects) {
            Integer value = (Integer) obj[0];
            map.put(String.valueOf(value), (String) obj[1]);
        }
        return map;
    }

    @Override
    public List<SelectItem> getOrdrStatusList() {
        List<SelectItem> list = new ArrayList<>();
        list.add(new SelectItem(0, "All"));
        list.add(new SelectItem(1, "Confirmed"));
        list.add(new SelectItem(2, "Assigned"));
        list.add(new SelectItem(3, "Closed"));
        list.add(new SelectItem(4, "Cancelled"));
        list.add(new SelectItem(5, "Pending"));
        return list;
    }

    @Override
    public List<SelectItem> getWrkshpStatusList() {
        List<SelectItem> list = new ArrayList<>();
        list.add(new SelectItem(0, "All"));
        list.add(new SelectItem(1, "Received"));
        list.add(new SelectItem(2, "Issued to worker"));
        list.add(new SelectItem(3, "Closed"));
        list.add(new SelectItem(4, "Cancelled"));
        return list;
    }

    @Override
    public List<SelectItem> getOrderTypList() {
        List<SelectItem> list = new ArrayList<>();
        list.add(new SelectItem(0, "All"));
        list.add(new SelectItem(1, "Order"));
        list.add(new SelectItem(2, "Repair"));
        return list;
    }

    @Override
    @Transactional
    public void updateOrderDtl(OrderDetailsVO ordrDtl) {
        gnrRepo.updateOrderStatusByAdmin(ordrDtl);
    }

    @Override
    @Transactional
    public void editCustOrder(OrderDetailsVO ordrDtl) {
        if (!StringUtils.isEmpty(ordrDtl.getCustCode())) {
            long cnt = gnrRepo.chkFldValueExist("SalesOrder", "custCode", ordrDtl.getCustCode(), " and orderNo=" + ordrDtl.getOrderNo());
            if (cnt == 0) {
                salesOrderRepo.updateCustCode(ordrDtl.getCustCode(), ordrDtl.getOrderNo());
            }
        }
        gnrRepo.editCustOrder(ordrDtl);
    }

    @Override
    public MultiKeyMap getCustMeltingStampMap() {
        List<Object[]> objects = gnrRepo.getCustMeltingStampMap();
        MultiKeyMap multiKeyMap = new MultiKeyMap();
        for (Object[] obj : objects) {
            multiKeyMap.put(obj[0], obj[1], obj[2]);
        }
        return multiKeyMap;
    }

    @Override
    public List<SelectItem> getWorkerList(String wrkshpCode) {
        List<SelectItem> list = new ArrayList<>();
        list.add(new SelectItem(null, "Select..."));
        List<Object[]> objects = null;
        if (!StringUtils.isEmpty(wrkshpCode)) {
            objects = gnrRepo.getDropdownItemsOrderBy("Worker", "wrkrName", "wrkrName", " wrkshpCode.wrkshpCode=\'" + wrkshpCode + "\'", "wrkrName");
        } else {
            objects = gnrRepo.getDropdownItemsOrderBy("Worker", "wrkrName", "wrkrName", null, "wrkrName");
        }
        for (Object[] obj : objects) {
            list.add(new SelectItem(obj[0], (String) obj[1]));
        }
        return list;
    }

    @Override
    @Transactional
    public void updateWrkshpStatus(OrderDetailsVO ordrDtl) {
        gnrRepo.updateWrkshpStatus(ordrDtl);
    }

    @Override
    public String getMeltingStamp(String custCode, Float meltPer) {
        if (meltPer != null && custCode != null) {
            return null;
        }
        Map<String, Object> parameter = new HashMap();
        parameter.put("custCode", custCode);
        parameter.put("meltingPer", meltPer.intValue());
        parameter.put("dfltFlg", (short) 0);
        return (String) gnrRepo.getFieldValue("MeltingStamp", "stamp", parameter);
    }

    @Override
    public List<OrderDetailsVO> getOrdersBySearchParam(OrderSearchParamVO searchParam) {

        List<OrderDetailsVO> ordrDtlList = new ArrayList<>();
        Map<String, Object> param = new HashMap();
        if (searchParam.getOrderNo() != null) {
            param.put("orderNo", searchParam.getOrderNo());
        }
        if (!StringUtils.isEmpty(searchParam.getCustCode())) {
            param.put("custCode", searchParam.getCustCode());
        }
        if (searchParam.getOrderStatus() != null && searchParam.getOrderStatus() > 0) {
            param.put("orderStatus", searchParam.getOrderStatus());
            if (searchParam.getStatusDate() != null && searchParam.getOrderStatus() == 2) {
                param.put("wrkshpStatusDate", searchParam.getStatusDate());
            } else if (searchParam.getStatusDate() != null && searchParam.getOrderStatus() == 3) {
                param.put("orderCloseDate", searchParam.getStatusDate());
            }
        }

        if (searchParam.getOrderTyp() != null && searchParam.getOrderTyp() > 0) {
            param.put("orderType", searchParam.getOrderTyp());
        }
        if (!StringUtils.isEmpty(searchParam.getItemCode())) {
            param.put("itemCode", searchParam.getItemCode().toUpperCase());
        }
        if (!StringUtils.isEmpty(searchParam.getRefNo())) {
            param.put("refNo", searchParam.getRefNo());
        }
        if (!StringUtils.isEmpty(searchParam.getWrkshpCode())) {
            param.put("wrkshpCode", searchParam.getWrkshpCode());
        }
        if (searchParam.getDueDate() != null) {
            param.put("dueDate", searchParam.getDueDate());
        }

        if (searchParam.getSrlNo() != null && searchParam.getSrlNo() > 0) {
            param.put("rcrdNo", searchParam.getSrlNo().intValue());
        }
        if (!StringUtils.isEmpty(searchParam.getWorkerCode())) {
            param.put("wrkrName", searchParam.getWorkerCode());
        }
        if (searchParam.getWrkshpStatus() != null && searchParam.getWrkshpStatus() > 0) {
            param.put("wrkshpStatus", searchParam.getWrkshpStatus());
            if (searchParam.getStatusDate() != null && searchParam.getWrkshpStatus() == 2) {
                param.put("wrkshpStatusDate", searchParam.getStatusDate());
            } else if (searchParam.getStatusDate() != null && searchParam.getWrkshpStatus() == 3) {
                param.put("wrkshpCloseDate", searchParam.getStatusDate());
            }
        }
        if (searchParam.getFrom() != null) {
            param.put("from", searchParam.getFrom());
        }
        if (searchParam.getTo() != null) {
            param.put("to", searchParam.getTo());
        }
        List<OrderDetailsJewellery> entities = gnrRepo.getOrdersBySearchParam(param);
        Set<String> setWrkShp = new HashSet();
        Set<String> setItemCode = new HashSet();
        Set<String> setCustCode = new HashSet();
        for (OrderDetailsJewellery ordrDtlJew : entities) {
            if (ordrDtlJew.getWrkshpCode() != null) {
                setWrkShp.add(ordrDtlJew.getWrkshpCode());
            }
            setItemCode.add(ordrDtlJew.getItemCode());
            setCustCode.add(ordrDtlJew.getSalesOrder().getCustCode());
        }
        Map<Object, Object> map = gnrRepo.getSelectItemMap("WorkshopDetailOthr", "wrkshpCode", "wrkshpName", setWrkShp);
        Map<Object, Object> mapItems = gnrRepo.getSelectItemMap("ItemMst", "itmCode", "itmName", setItemCode);
        Map<Object, Object> mapItmQtyWisePrice = gnrRepo.getSelectItemMap("ItemDtl", "itmCode", "pieceWiseValue", setItemCode);
        Map<Object, Object> mapCust = gnrRepo.getSelectItemMap("CustomerDetailOthr", "custCode", "custName", setCustCode);
        Map<Object, Object> mapUser = gnrRepo.getSelectItemMap("UserDetails", "userNo", "userFirstName", new HashSet());
        for (OrderDetailsJewellery entity : entities) {
            OrderDetailsVO ordrDtl = new OrderDetailsVO();
            ordrDtl.setOrderNo(entity.getSalesOrder().getOrderNo());
            ordrDtl.setOrderDtlNo(entity.getOrderDtlNo());
            ordrDtl.setSrlNo((short) entity.getRcrdNo());
            ordrDtl.setItemCode(entity.getItemCode());
            ordrDtl.setItemName((String) mapItems.get(entity.getItemCode()));
            ordrDtl.setItemUnit(entity.getItemUnit());
            ordrDtl.setWeight(entity.getItemWeight());
            if (mapItmQtyWisePrice.get(entity.getItemCode()) != null
                    && (Short) mapItmQtyWisePrice.get(entity.getItemCode()) == 1) {
                ordrDtl.setNetWeight(entity.getItemWeight() * entity.getItemQty());
            } else {
                ordrDtl.setNetWeight(entity.getItemWeight());
            }
            ordrDtl.setItemSize(entity.getItemSize());
            ordrDtl.setQty(entity.getItemQty());
            ordrDtl.setMeltPer(entity.getMeltPer());
            ordrDtl.setStamp(entity.getStamp());
            ordrDtl.setHook(entity.getHook());
            ordrDtl.setDesignSample(entity.getDesignSample());
            ordrDtl.setSizeSample(entity.getSizeSample());
            ordrDtl.setRefNo(entity.getRefNo());
            ordrDtl.setRemark(entity.getRemarks());
            ordrDtl.setDays(entity.getDays());
            ordrDtl.setDueDate(entity.getDueDate());
            ordrDtl.setFormatedDueDate(DateUtils.convertDateToStringFormat(entity.getDueDate(), "dd/MM/yyyy"));
            ordrDtl.setWorkshop((String) map.get(entity.getWrkshpCode()));
            ordrDtl.setWrkshpCode(entity.getWrkshpCode());
            ordrDtl.setRcvSample(entity.getRcvSample() == 1);
            ordrDtl.setFixRate(entity.getPriceType() + "" + entity.getItemPrice());
            ordrDtl.setOrderTyp(entity.getOrderType());
            ordrDtl.setOrderTypLbl(SalesOrderUtils.getOrderTypLbl(entity.getOrderType()));
            ordrDtl.setOrderStatus(entity.getOrderStatus());
            ordrDtl.setStatus(SalesOrderUtils.getStatus(entity.getOrderStatus()));
            ordrDtl.setOrderDate(entity.getSalesOrder().getOrderDate());
            ordrDtl.setFormatedOrdrDate(DateUtils.convertDateToStringFormat(entity.getSalesOrder().getOrderDate(), "dd/MM/yyyy"));
            ordrDtl.setWrkshpStatus(entity.getWrkshpStatus());
            ordrDtl.setWrkshpStatusNm(SalesOrderUtils.getWorkshopStatus(entity.getWrkshpStatus()));
            ordrDtl.setWrkrName(entity.getWrkrName());
            ordrDtl.setCustCode(entity.getSalesOrder().getCustCode());
            ordrDtl.setCustName((String) mapCust.get(ordrDtl.getCustCode()));
            if (entity.getWrkshpStatusDate() != null) {
                ordrDtl.setWrkshpStatusDate(entity.getWrkshpStatusDate());
                ordrDtl.setFormatedStatusDate(DateUtils.convertDateToStringFormat(entity.getWrkshpStatusDate(), "dd/MM/yyyy"));
            }
            if (entity.getWrkshpCloseDate() != null) {
                ordrDtl.setFormatedWrkshpCloseDate(DateUtils.convertDateToStringFormat(entity.getWrkshpCloseDate(), "dd/MM/yyyy"));
            }
            if (entity.getWrkshpCancelDate() != null) {
                ordrDtl.setFormatedWrkshpCancelDate(DateUtils.convertDateToStringFormat(entity.getWrkshpCancelDate(), "dd/MM/yyyy"));
            }
            if (entity.getOrderCloseDate() != null) {
                ordrDtl.setFormatedOrderCloseDate(DateUtils.convertDateToStringFormat(entity.getOrderCloseDate(), "dd/MM/yyyy"));
            }
            ordrDtl.setCrtTrmnlNm(entity.getCrtTrmnlNm() == null ? "server" : entity.getCrtTrmnlNm().toLowerCase());
            ordrDtl.setCrtDate(entity.getCrtDate());
            ordrDtl.setCrtUsrNm((String) mapUser.get(entity.getCrtUsrNo()));
            String fileName = orderDtlRepo.getImagePath(entity.getOrderDtlNo());
            if (!StringUtils.isEmpty(fileName)) {
                String[] fileNamesSeparator = fileName.split(",");
                ordrDtl.setImgPath1(fileNamesSeparator);
            }

            //ordrDtl.setWrkshpStatus(searchParam.getOrderStatus());
            ordrDtlList.add(ordrDtl);
        }
        return ordrDtlList;
    }

    @Override
    public List<SelectItem> getAllCustList() {
        List<SelectItem> list = new ArrayList<>();
        list.add(new SelectItem(null, "Select..."));
        List<Object[]> objects = gnrRepo.getDropdownItemsOrderBy("CustomerDetailOthr", "custCode", "custName", null, "custName");//changes usrWhr as inactiveflg=0
        for (Object[] obj : objects) {
            list.add(new SelectItem(obj[0], (String) obj[1]));
        }
        return list;
    }

    @Override
    public String getImageFilesName(OrderDetailsVO selectedOrders) {
        String getFiles = orderDtlRepo.getImagePath(selectedOrders.getOrderDtlNo());
        return getFiles;
    }

    @Override
    @Transactional
    public void delSelectedorders(List<OrderDetailsVO> selectedOrdrLst) {
        Set<Long> setOfOrdrNo = new HashSet<>();
        for (OrderDetailsVO ordrDtlsList : selectedOrdrLst) {
            if (!setOfOrdrNo.contains(ordrDtlsList.getOrderNo())) {
                setOfOrdrNo.add(ordrDtlsList.getOrderNo());
            }
        }
        Long minOrdrNo = Collections.min(setOfOrdrNo);
        Long maxOrdrNo = Collections.max(setOfOrdrNo);
        //Take backup into order_details_jewellery_hist
        gnrRepo.insertIntoOrderDetailsHist(minOrdrNo, maxOrdrNo);
        //Take backup into sales_order_hist
        gnrRepo.insertIntoSalesOrdrHist(minOrdrNo, maxOrdrNo);

        //Take backup into order_details_jewellery_hist
//        List<SalesOrderHstry> salesOrderHstry = new ArrayList<>();
//        List<SalesOrder> salesOrderList = salesOrderRepo.findAllByOrderNoList(Arrays.asList(setOfOrdrNo.toArray()));
//        for (SalesOrder salesOrder : salesOrderList) {
//            SalesOrderHstry entity = modelMapper.map(salesOrder, SalesOrderHstry.class);
//            salesOrderHstry.add(entity);
//        }
//        //add OrderDetailsJewelleryHstry 
//        List<OrderDetailsJewellery> orderDtlsJewelleryList = orderDtlRepo.getOrderDtlsListByOrderNo(Arrays.asList(setOfOrdrNo.toArray()));
//        List<OrderDetailsJewelleryHstry> list = new ArrayList<>();
//        for (OrderDetailsJewellery ordrDtlVo : orderDtlsJewelleryList) {
//            OrderDetailsJewelleryHstry ordrDtlHstryEntity = modelMapper.map(ordrDtlVo, OrderDetailsJewelleryHstry.class);
//            ordrDtlHstryEntity.setOrderNo(ordrDtlVo.getSalesOrder().getOrderNo());
//            list.add(ordrDtlHstryEntity);
//        }
//        if (!salesOrderHstry.isEmpty()) {
//            try {
//                salesOrderHstryRepo.save(salesOrderHstry);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        if (!list.isEmpty()) {
//            try {
//                orderDetailsJewelleryHstryRepo.save(list);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        //second delete all data from salesOrder and OrderDetails
        if (!setOfOrdrNo.isEmpty()) {
            gnrRepo.delAllByCode("OrderDetailsJewellery d", "d.salesOrder.orderNo", Arrays.asList(setOfOrdrNo.toArray()));
            gnrRepo.delAllByCode("SalesOrder s", "s.orderNo", Arrays.asList(setOfOrdrNo.toArray()));
            Long maxValue = Collections.max(setOfOrdrNo);
            gnrRepo.resetAllExistData("SalesOrder", "stopSeq = 1 where order_no >", maxValue);
        }

    }

    @Override
    public void downloadSelectedOrders(List<OrderDetailsVO> selectedOrdrLst) {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("OrderDetails");
        XSSFRow row = sheet.createRow(0);

        XSSFCellStyle style = workbook.createCellStyle();
        style.setFillBackgroundColor(IndexedColors.GOLD.getIndex());
        style.setFillPattern(FillPatternType.FINE_DOTS);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THICK);

//        //set column
        XSSFCell cell = row.createCell(0);
        cell.setCellValue("OrderNo");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("SlNo");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("OrderDate");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("Code");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("Wt(gm)");
        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue("size");
        cell.setCellStyle(style);
        cell = row.createCell(6);
        cell.setCellValue("Qty");
        cell.setCellStyle(style);
        cell = row.createCell(7);
        cell.setCellValue("Melt%");
        cell.setCellStyle(style);
        cell = row.createCell(8);
        cell.setCellValue("Stamp");
        cell.setCellStyle(style);
        cell = row.createCell(9);
        cell.setCellValue("Hook");
        cell.setCellStyle(style);
        cell = row.createCell(10);
        cell.setCellValue("Design Sample");
        cell.setCellStyle(style);
        cell = row.createCell(11);
        cell.setCellValue("Size Sample");
        cell.setCellStyle(style);
        cell = row.createCell(12);
        cell.setCellValue("RefNo");
        cell.setCellStyle(style);
        cell = row.createCell(13);
        cell.setCellValue("Remarks");
        cell.setCellStyle(style);
        cell = row.createCell(14);
        cell.setCellValue("Days");
        cell.setCellStyle(style);
        cell = row.createCell(15);
        cell.setCellValue("DueDate");
        cell.setCellStyle(style);
        cell = row.createCell(16);
        cell.setCellValue("Customer");
        cell.setCellStyle(style);
        cell = row.createCell(17);
        cell.setCellValue("Workshop");
        cell.setCellStyle(style);
        for (int i = 0; i < selectedOrdrLst.size(); i++) {
            row = sheet.createRow((short) i + 1);
            row.createCell((short) 0).setCellValue(selectedOrdrLst.get(i).getOrderNo());
            row.createCell((short) 1).setCellValue(selectedOrdrLst.get(i).getSrlNo());
            row.createCell((short) 2).setCellValue(DateUtils.convertDateToStringFormat(selectedOrdrLst.get(i).getOrderDate(), "dd/MM/yyyy"));
            row.createCell((short) 3).setCellValue(selectedOrdrLst.get(i).getItemCode());
            row.createCell((short) 4).setCellValue(selectedOrdrLst.get(i).getWeight());
            row.createCell((short) 5).setCellValue(selectedOrdrLst.get(i).getItemSize());
            row.createCell((short) 6).setCellValue(selectedOrdrLst.get(i).getQty());
            row.createCell((short) 7).setCellValue(selectedOrdrLst.get(i).getMeltPer());
            row.createCell((short) 8).setCellValue(selectedOrdrLst.get(i).getStamp());
            row.createCell((short) 9).setCellValue(selectedOrdrLst.get(i).getHook());
            row.createCell((short) 10).setCellValue(selectedOrdrLst.get(i).getDesignSample());
            row.createCell((short) 11).setCellValue(selectedOrdrLst.get(i).getSizeSample());
            row.createCell((short) 12).setCellValue(selectedOrdrLst.get(i).getRefNo());
            row.createCell((short) 13).setCellValue(selectedOrdrLst.get(i).getRemark());
            row.createCell((short) 14).setCellValue(selectedOrdrLst.get(i).getDays());
            row.createCell((short) 15).setCellValue(DateUtils.convertDateToStringFormat(selectedOrdrLst.get(i).getDueDate(), "dd/MM/yyyy"));
            row.createCell((short) 16).setCellValue(selectedOrdrLst.get(i).getCustName());
            row.createCell((short) 17).setCellValue(selectedOrdrLst.get(i).getWorkshop());

        }
        HttpServletResponse res = HttpJSFUtil.getResponse();
        res.setContentType("application/vnd.ms-excel");
        res.setHeader("Content-disposition", "attachment; filename=OrdersReport.xlsx");

        try {
            ServletOutputStream out = res.getOutputStream();
            workbook.write(out);
            out.flush();
            out.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        FacesContext faces = FacesContext.getCurrentInstance();
        faces.responseComplete();
    }

    @Override
    public int getOrderNoValue(Long orderNo) {
        long cnt = salesOrderRepo.checkValueExistByOrderNo(orderNo);
        return (int) cnt;
    }
    
    

}
