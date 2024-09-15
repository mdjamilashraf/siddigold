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
package com.ultimatetek.dao;

import com.ultimatetek.config.DateUtils;
import com.ultimatetek.config.StringUtils;
import com.ultimatetek.entity.OrderDetailsJewellery;
import com.ultimatetek.model.DashboardData;
import com.ultimatetek.model.OrderDetailsVO;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author JunaidKhan
 */
@Repository
public class GeneralRepoImpl implements GeneralRepo {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(GeneralRepoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Object getAutoIncrementVal(String tabNm, String fldNm, String usrWhr) {
        String HQL = "SELECT COALESCE(MAX(cast( " + fldNm + " as integer)),0) from " + tabNm;
        if(usrWhr!=null){
           HQL+=" where 1=1 "+usrWhr;
        }
        Query query = entityManager.createQuery(HQL);
        return query.getSingleResult();
    }
    
    @Override
    public void resetAllExistData(String tblNm, String fldNm, Object Value) {
        String HQL = "update "+ tblNm +" set " + fldNm + "=:fldVal";
        Query query = entityManager.createQuery(HQL);
        query.setParameter("fldVal", Value);
        query.executeUpdate();
    }
    @Override
    public String getPreAddRefNo(String custCode, Long orderNo) {
        String HQL = "Select Max(odj.refNo) from SalesOrder so,OrderDetailsJewellery odj where 1=1 and so.orderNo=odj.salesOrder.orderNo ";
        if (orderNo != null) {
            HQL += "and odj.salesOrder.orderNo=:orderNo";
        }
        if (!StringUtils.isEmpty(custCode)) {
            HQL += "and so.custCode=:custCode";
        }
        Query query = entityManager.createQuery(HQL);
        if (orderNo != null) {
            query.setParameter("orderNo", orderNo);
        }
        if (!StringUtils.isEmpty(custCode)) {
            query.setParameter("custCode", custCode);
        }
        return (String) query.getSingleResult();
    }

    @Override
    public List<Object[]> getDropdownItems(String tableNm, String itemVal, String itemLbl) {
        String HQL = "SELECT  " + itemVal + "," + itemLbl + " from " + tableNm;
        Query query = entityManager.createQuery(HQL);
        return query.getResultList();
    }

    /*@Override
    public List<Object[]> getDropdownItems(String tableNm, String itemVal, String itemLbl, String whr) {
        String HQL = "SELECT  " + itemVal + "," + itemLbl + " from " + tableNm;
        if (!StringUtils.isEmpty(whr)) {
            HQL += " where 1=1 and " + whr;
        }
        Query query = entityManager.createQuery(HQL);
        return query.getResultList();
    }*/
    @Override
    public void delByCode(String tabNm, String fldNm, Object fldVal) {
        StringBuilder sb = new StringBuilder("delete from ");
        sb.append(tabNm);
        sb.append(" where ").append(fldNm).append(" = :fldVal");
        String HQL = sb.toString();
        Query query = entityManager.createQuery(HQL);
        query.setParameter("fldVal", fldVal);
        query.executeUpdate();
    }

    @Override
    public void delAllByCode(String tblNm, String fldNm, List<Object> orderNo) {
        StringBuilder sb = new StringBuilder("delete from ");
        sb.append(tblNm);
        sb.append(" where ").append(fldNm).append(" IN :orderNos");  
        String hql = sb.toString();
        Query query = entityManager.createQuery(hql);
        query.setParameter("orderNos", orderNo);  
        query.executeUpdate();
    }

    @Override
    public List<OrderDetailsJewellery> getSalesOrderListByParam(Long orderNo, Integer rcrdNo, String custCode, Integer ordrStatus, String wrkshpCode, Integer orderTyp, String itemCode, String refNo, Integer wrkshpStatus, String wrkrCode, Date dueDate, Date statusDate) {
        String HQL = "select ordrDtl from OrderDetailsJewellery ordrDtl where 1=1 ";
        if (orderNo != null) {
            HQL += "and ordrDtl.salesOrder.orderNo=:orderNo ";
        }
        if (!StringUtils.isEmpty(custCode)) {
            HQL += "and ordrDtl.salesOrder.custCode=:custCode ";
        }
        if (ordrStatus != null && ordrStatus > 0) {
            HQL += "and ordrDtl.orderStatus = :ordrStatus ";
        }
        if (!StringUtils.isEmpty(wrkshpCode)) {
            HQL += "and ordrDtl.wrkshpCode=:wrkshpCode ";
        }
        if (orderTyp != null && orderTyp > 0) {
            HQL += "and ordrDtl.orderType = :orderTyp ";
        }
        if (!StringUtils.isEmpty(itemCode)) {
            HQL += "and UPPER(ordrDtl.itemCode) = :itemCode ";
        }
        if (!StringUtils.isEmpty(refNo)) {
            HQL += "and ordrDtl.refNo = :refNo ";
        }
        if (wrkshpStatus != null && wrkshpStatus > 0) {
            HQL += "and ordrDtl.wrkshpStatus = :wrkshpStatus ";
        }
        if (!StringUtils.isEmpty(wrkrCode)) {
            HQL += "and ordrDtl.wrkrName = :wrkrName ";
        }
        if (dueDate != null) {
            HQL += "and ordrDtl.dueDate = :dueDate ";
        }
        if (statusDate != null) {
            HQL += "and ordrDtl.wrkshpStatusDate = :statusDate ";
        }
        if (rcrdNo != null && rcrdNo > 0) {
            HQL += " and ordrDtl.rcrdNo = :rcrdNo ";
        }
        HQL += " order by ordrDtl.orderDtlNo";

        Query query = entityManager.createQuery(HQL);
        if (orderNo != null) {
            query.setParameter("orderNo", orderNo);
        }
        if (!StringUtils.isEmpty(custCode)) {
            query.setParameter("custCode", custCode);
        }
        if (ordrStatus != null && ordrStatus > 0) {
            query.setParameter("ordrStatus", ordrStatus);
        }
        if (!StringUtils.isEmpty(wrkshpCode)) {
            query.setParameter("wrkshpCode", wrkshpCode);
        }
        if (orderTyp != null && orderTyp > 0) {
            query.setParameter("orderTyp", orderTyp);
        }
        if (!StringUtils.isEmpty(itemCode)) {
            query.setParameter("itemCode", itemCode.toUpperCase());
        }
        if (!StringUtils.isEmpty(refNo)) {
            query.setParameter("refNo", refNo);
        }
        if (wrkshpStatus != null && wrkshpStatus > 0) {
            query.setParameter("wrkshpStatus", wrkshpStatus);
        }
        if (!StringUtils.isEmpty(wrkrCode)) {
            query.setParameter("wrkrName", wrkrCode);
        }
        if (dueDate != null) {
            query.setParameter("dueDate", dueDate);
        }
        if (statusDate != null) {
            query.setParameter("statusDate", statusDate);
        }
        if (rcrdNo != null && rcrdNo > 0) {
            query.setParameter("rcrdNo", rcrdNo);
        }
        return query.getResultList();
    }

    @Override
    public Map<Object, Object> getSelectItemMap(String entityName, String itemVal, String itemLbl, Set<String> inClaus) {
        Map<Object, Object> map = new HashMap<>();
        String HQL = "SELECT  " + itemVal + ", " + itemLbl + " from " + entityName + " where 1=1";
        if (!inClaus.isEmpty()) {
            HQL += " and " + itemVal + " in :list";
        }
        Query query = entityManager.createQuery(HQL);
        if (!inClaus.isEmpty()) {
            query.setParameter("list", inClaus);
        }
        List<Object[]> objects = query.getResultList();
        for (Object[] obj : objects) {
            map.put(obj[0], obj[1]);
        }
        return map;
    }

    @Override
    public void updateOrderStatusByAdmin(OrderDetailsVO ordrDtl) {
        String HQL = "update OrderDetailsJewellery d set d.orderStatus=:status";
        Integer wrkshpStatus = this.checkWorkShopStatus(ordrDtl.getOrderDtlNo());
        if (null != ordrDtl.getOrderStatus()) switch (ordrDtl.getOrderStatus()) {
            case 2:
                HQL += " ,d.wrkshpStatus=:wrkshpStatus, d.wrkshpStatusDate=:wrkshpStatusDate";
                break;
            case 3:
                //order close
                HQL += " ,d.wrkshpStatus=:wrkshpStatus,d.orderCloseDate=:orderCloseDate,d.wrkshpStatusDate=:wrkshpStatusDate";
                if (wrkshpStatus != 3) {
                    HQL += ",d.wrkshpCloseDate=:wrkshpCloseDate";
                }   break;
            case 4:
                //order cancel
                HQL += ",d.wrkshpStatus=:wrkshpStatus,d.wrkshpStatusDate=:wrkshpStatusDate ";
                if (wrkshpStatus != 4) {
                    HQL += ",d.wrkshpCancelDate=:wrkshpCancelDate";
                }   break;
            default:
                break;
        }
        HQL += " where d.salesOrder.orderNo=:orderNo and d.rcrdNo=:rcrdNo";
        LOGGER.info("Admin HQL = "+HQL);
        LOGGER.info("wrkshpStatus: = "+wrkshpStatus+"Status: = "+ordrDtl.getOrderStatus()+"\tOrder No"+ordrDtl.getOrderNo()+"\t"+ordrDtl.getSrlNo());
        Query query = entityManager.createQuery(HQL);
        query.setParameter("status", ordrDtl.getOrderStatus());
        if (null != ordrDtl.getOrderStatus()) switch (ordrDtl.getOrderStatus()) {
            case 2:
//                if (wrkshpStatus==2) {
//                    query.setParameter("wrkshpStatus", wrkshpStatus);
//                } else {
//                    query.setParameter("wrkshpStatus", 1); //wrkshop status 1 means workshop received
//                }
                query.setParameter("wrkshpStatus", wrkshpStatus);
                query.setParameter("wrkshpStatusDate", new Date());
                break;
            case 3:
                query.setParameter("wrkshpStatus", 3);
                query.setParameter("orderCloseDate", new Date());
                query.setParameter("wrkshpStatusDate", new Date());
                if (wrkshpStatus != 3) {
                    query.setParameter("wrkshpCloseDate", new Date());
                }   break;
            case 4:
                query.setParameter("wrkshpStatus", 4);
                query.setParameter("wrkshpStatusDate", new Date());
                if (wrkshpStatus != 4) {
                    query.setParameter("wrkshpCancelDate", new Date());
                }   break;
            default:
                break;
        }
        query.setParameter("orderNo", ordrDtl.getOrderNo());
        query.setParameter("rcrdNo", (int) ordrDtl.getSrlNo());
        query.executeUpdate();
    }

    @Override
    public void updateWrkshpStatus(OrderDetailsVO ordrDtl) {
        String HQL = "update OrderDetailsJewellery d set d.wrkshpStatus=:status ";
        if (null != ordrDtl.getWrkshpStatus()) switch (ordrDtl.getWrkshpStatus()) {
            case 2:
                //in process
                HQL += ",d.wrkshpIssueWrkrDate=:wrkshpIssueWrkrDate ";
                break;
            case 3:
                //close in workshop
                HQL += ",d.wrkshpCloseDate=:wrkshpCloseDate ";
                break;
            case 4:
                //cancel in workshop
                HQL += ",d.wrkshpCancelDate=:wrkshpCancelDate ";
                break;
            default:
                break;
        }
        if (!StringUtils.isEmpty(ordrDtl.getWrkrName())) {
            HQL += ", d.wrkrName=:wrkrNm ";
        }
        HQL += "where d.salesOrder.orderNo=:orderNo and d.rcrdNo=:rcrdNo";
        LOGGER.info("HQL = "+HQL);
        LOGGER.info("Status: = "+ordrDtl.getWrkshpStatus()+"\tOrder No"+ordrDtl.getOrderNo()+"\t"+ordrDtl.getSrlNo());
        Query query = entityManager.createQuery(HQL);
        
        query.setParameter("status", ordrDtl.getWrkshpStatus());
        if (null != ordrDtl.getWrkshpStatus()) switch (ordrDtl.getWrkshpStatus()) {
            case 2:
                query.setParameter("wrkshpIssueWrkrDate", new Date());
                break;
            case 3:
                query.setParameter("wrkshpCloseDate", new Date());
                break;
            case 4:
                query.setParameter("wrkshpCancelDate", new Date());
                break;
            default:
                break;
        }
        if (!StringUtils.isEmpty(ordrDtl.getWrkrName())) {
            query.setParameter("wrkrNm", ordrDtl.getWrkrName());
        }
        query.setParameter("orderNo", ordrDtl.getOrderNo());
        query.setParameter("rcrdNo", (int) ordrDtl.getSrlNo());
        query.executeUpdate();
    }

    @Override
    public void editCustOrder(OrderDetailsVO ordrDtl) {
        String SQL_UPDATE = "update OrderDetailsJewellery d set d.wrkshpCode=:wrkshpCode, itemCode=:itemCode, d.itemSize=:itemSize, d.meltPer=:meltPer, d.stamp=:stamp,"
                + "d.days=:days, d.dueDate=:dueDate, d.refNo=:refNo,"
                + "itemQty=:itemQty,d.itemWeight=:weight, d.hook=:hook, d.rcvSample=:rcvSample, d.designSample=:designSample,"
                + "d.sizeSample=:sizeSample, d.priceType=:priceType, d.itemPrice=:itemPrice, d.orderType=:orderType, d.remarks=:remarks,"
                + "updUsrNo=:updUsrNo, updDate=:updDate,updCnt=updCnt+1 "
                + "where d.salesOrder.orderNo=:orderNo and d.rcrdNo=:rcrdNo";
        Query query = entityManager.createQuery(SQL_UPDATE);
        query.setParameter("wrkshpCode", ordrDtl.getWrkshpCode());
        query.setParameter("itemCode", ordrDtl.getItemCode());
        query.setParameter("itemQty", ordrDtl.getQty());
        query.setParameter("weight", ordrDtl.getWeight());
        query.setParameter("hook", ordrDtl.getHook());
        query.setParameter("itemSize", ordrDtl.getItemSize());
        query.setParameter("meltPer", ordrDtl.getMeltPer());
        query.setParameter("stamp", ordrDtl.getStamp());
        query.setParameter("days", ordrDtl.getDays());
        query.setParameter("dueDate", ordrDtl.getDueDate());
        query.setParameter("refNo", ordrDtl.getRefNo());
        if (ordrDtl.getRcvSample()) {
            query.setParameter("rcvSample", (short) 1);
        } else {
            query.setParameter("rcvSample", (short) 0);
        }
        query.setParameter("designSample", ordrDtl.getDesignSample());
        query.setParameter("sizeSample", ordrDtl.getSizeSample());
        query.setParameter("priceType", ordrDtl.getFixRate().substring(0, 1));
        if (!StringUtils.isEmpty(ordrDtl.getFixRate().substring(1))
                && StringUtils.isNumericVal(ordrDtl.getFixRate().substring(1))) {
            query.setParameter("itemPrice", Double.parseDouble(ordrDtl.getFixRate().substring(1)));
        } else {
            query.setParameter("itemPrice", ordrDtl.getItemPrice());
        }
        query.setParameter("orderType", ordrDtl.getOrderTyp());
        query.setParameter("remarks", ordrDtl.getRemark());
        query.setParameter("updUsrNo", ordrDtl.getUpdUsrNo());
        query.setParameter("updDate", ordrDtl.getUpdDate());
        query.setParameter("orderNo", ordrDtl.getOrderNo());
        query.setParameter("rcrdNo", (int) ordrDtl.getSrlNo());
        query.executeUpdate();
    }

    @Override
    public Long chkFldValueExist(String tableNm, String fldNm, String fldVal, String usrWhr) {
        StringBuilder sb = new StringBuilder("Select count(1) from ");
        sb.append(tableNm);
        sb.append(" where lower(").append(fldNm).append(")");
        sb.append("=lower(:fldVal)");
        if (usrWhr != null) {
            sb.append(usrWhr);
        }
        String HQL = sb.toString();
        Query query = entityManager.createQuery(HQL);
        query.setParameter("fldVal", fldVal);
        return (Long) query.getSingleResult();
    }

    @Override
    public List<Object[]> getCustMeltingStampMap() {
        String HQL = "Select custCode, meltingPer, stamp from MeltingStamp where dfltFlg=0 and custCode is not null";
        Query query = entityManager.createQuery(HQL);
        return query.getResultList();
    }

    @Override
    public List<Object[]> getOrdrDtlsBycustCode(String custCode) {
        String HQL = "Select to_char(so.orderDate, 'DD/MM/YYYY'), to_char(odj.wrkshpStatusDate,'DD/MM/YYYY'),odj.refNo, odj.orderStatus, odj.itemCode,odj.salesOrder.orderNo,odj.rcrdNo,odj.orderDtlNo from SalesOrder so,OrderDetailsJewellery odj where so.orderNo=odj.salesOrder.orderNo and so.custCode=:custCode order by odj.orderDtlNo";
        Query query = entityManager.createQuery(HQL);
        query.setParameter("custCode", custCode);
        return query.getResultList();
    }

    @Override
    public List<Object[]> getDropdownItemsOrderBy(String tableNm, String itemVal, String itemLbl, String usrWhr, String ordrByFld) {
        String HQL = "SELECT  " + itemVal + "," + itemLbl + " from " + tableNm;
        if (!StringUtils.isEmpty(usrWhr)) {
            HQL += " where 1=1 and " + usrWhr;
        }
        if (!StringUtils.isEmpty(ordrByFld)) {
            HQL += " order by " + ordrByFld;
        }
        Query query = entityManager.createQuery(HQL);
        return query.getResultList();
    }

    @Override
    public Object getFieldValue(String tblNm, String fldVal, Map<String, Object> parameter) {

        String HQL = "Select " + fldVal + " from " + tblNm + " where 1=1";
        for (Map.Entry<String, Object> pair : parameter.entrySet()) {
            HQL += " AND " + pair.getKey() + " = :" + pair.getKey();
        }
        Query query = entityManager.createQuery(HQL);
        for (Map.Entry<String, Object> pair : parameter.entrySet()) {
            query.setParameter(pair.getKey(), pair.getValue());
        }
        return query.getSingleResult();
    }

    @Override
    public List<OrderDetailsJewellery> getOrdersBySearchParam(Map<String, Object> parameter) {
        try {
            String HQL = "Select d from OrderDetailsJewellery d where 1=1";
            Query query = null;

            for (Map.Entry<String, Object> pair : parameter.entrySet()) {
                if (pair.getKey().equals("custCode")) {
                    HQL += " AND d.salesOrder.custCode = :" + pair.getKey();
                } else if (pair.getKey().equals("orderNo")) {
                    HQL += " AND d.salesOrder.orderNo = :" + pair.getKey();
                } else if (pair.getKey().equals("from")) {
                    String from = DateUtils.getFormattedDateForMysql((Date) pair.getValue());
                    HQL += " AND d.salesOrder.orderDate between '" + from + "'";
                } else if (pair.getKey().equals("to")) {
                    String to = DateUtils.getFormattedDateForMysql((Date) pair.getValue());
                    HQL += " AND '" + to + "'";
                } else {
                    HQL += " AND d." + pair.getKey() + " = :" + pair.getKey();
                }
            }

            query = entityManager.createQuery(HQL);

            for (Map.Entry<String, Object> pair : parameter.entrySet()) {
                if (pair.getKey().equals("from") || pair.getKey().equals("to")) {
                    // no need to set any value here
                } else {
                    query.setParameter(pair.getKey(), pair.getValue());
                }
            }

            HQL += " order by d.orderDtlNo";
            System.out.println(HQL);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Object> getAutoCompleteFldLst(String tableNm, String fldNm) {
        String HQL = "SELECT  " + fldNm + " from " + tableNm + " order by " + fldNm;
        Query query = entityManager.createQuery(HQL);
        return query.getResultList();
    }

    @Override
    public int updateFldVal(String tblNm, String fldNm, Object fldVal, Map<String, Object> parameter) {
        String HQL = "Update " + tblNm + " ";
        for (Map.Entry<String, Object> pair : parameter.entrySet()) {
            HQL += " set " + pair.getKey() + " = :" + pair.getKey();
        }
        HQL += " where " + fldNm + "=:fldNm";
        Query query = entityManager.createQuery(HQL);
        for (Map.Entry<String, Object> pair : parameter.entrySet()) {
            query.setParameter(pair.getKey(), pair.getValue());
        }
        query.setParameter(fldNm, fldVal);
        return query.executeUpdate();
    }

    private Integer checkWorkShopStatus(Long orderDtlNo) {
        String HQL = "Select d.wrkshpStatus from OrderDetailsJewellery d where 1=1 and d.orderDtlNo = :orderDtlNo";
        Query query = entityManager.createQuery(HQL);
        query.setParameter("orderDtlNo", orderDtlNo);
        return (Integer) query.getSingleResult();
    }

    @Override
    public void insertIntoOrderDetailsHist(Long minOrdrNo, Long maxOrdrNo) {
        String HQL = "insert into order_details_jewellery_hist\n" +
                    "select * from order_details_jewellery\n" +
                    "where order_no between :minOrdrNo and :maxOrdrNo order by order_no , order_dtl_no";
        
        Query query = entityManager.createNativeQuery(HQL);
        query.setParameter("minOrdrNo", minOrdrNo);
        query.setParameter("maxOrdrNo", maxOrdrNo);
        
        query.executeUpdate();
    }

    @Override
    public void insertIntoSalesOrdrHist(Long minOrdrNo, Long maxOrdrNo) {
                String HQL = "insert into sales_order_hist\n" +
                "select * from sales_order \n" +
                "where order_no between :minOrdrNo and :maxOrdrNo order by order_no";

        Query query = entityManager.createNativeQuery(HQL);
        query.setParameter("minOrdrNo", minOrdrNo);
        query.setParameter("maxOrdrNo", maxOrdrNo);
        
        query.executeUpdate();
    }

    @Override
    public DashboardData getDashboardData() {
        String todayDate =  DateUtils.getFormattedDateForMysql(new Date());
        String mnthStartDate = DateUtils.getMonthStartDate();
        String SQL = "select (select cast(SUM(item_weight) as  float8)  from order_details_jewellery m "
                + "where 1=1 and m.crt_date=\'"+todayDate+"\') today_wt,"
                + "(select cast(SUM(item_weight) as  float8) from order_details_jewellery m where 1=1 "
                + "and m.crt_date>=\'"+mnthStartDate+"\') monthly_wt,"
                + "(select cast(count(1) as INTEGER) from order_details_jewellery "
                + "where due_date <= \'"+todayDate+"\' and wrkshp_status in (1,2)) today_due";
        Query query = entityManager.createNativeQuery(SQL);
        Object[] objects =  (Object[]) query.getSingleResult();
        if (objects != null ) {
            DashboardData data = new DashboardData();
            double todayOrderCnt = objects[0] == null ? 0.0 : (Double) objects[0];
            double monthlyOrderCnt = objects[1] == null ? 0.0 : (Double) objects[1];

            data.setTodayOrderCnt(Math.round(todayOrderCnt * 100.0) / 100.0);
            data.setMonthlyOrderCnt(Math.round(monthlyOrderCnt * 100.0) / 100.0);
            data.setDailyDueCount((Integer)objects[2]);

            return data;
        } else {
            return new DashboardData();
        }
    }

    @Override
    public List<OrderDetailsVO> getTodayOrderDue(String todayDate) {
        String HQL = "Select d.rcrdNo, d.itemCode, d.itemWeight, d.itemSize, d.itemQty, d.dueDate, d.crtDate, "
                + "d.refNo, d.salesOrder.orderNo, c.custName, d.wrkshpCode, "
                + "(select itmName from ItemMst t where t.itmCode=d.itemCode) "
                + "from SalesOrder m, OrderDetailsJewellery d, CustomerDetailOthr c "
                + "where m.orderNo = d.salesOrder.orderNo and m.custCode = c.custCode and "
                + "d.dueDate <= \'"+todayDate+"\' and wrkshp_status in (1,2) order by m.orderNo";
        Query query = entityManager.createQuery(HQL);
        List<Object[]> objects = query.getResultList();
        List<OrderDetailsVO> list = new ArrayList<>();
        if (objects != null) {
            for (Object[] obj : objects) {
                OrderDetailsVO ordrDtl = new OrderDetailsVO();
                ordrDtl.setSrlNo(((Integer) obj[0]).shortValue());
                ordrDtl.setItemCode((String) obj[1]);
                ordrDtl.setWeight((Float) obj[2]);
                ordrDtl.setItemSize((String) obj[3]);
                ordrDtl.setQty((Integer) obj[4]);
                ordrDtl.setDueDate((Date) obj[5]);
                ordrDtl.setFormatedDueDate(DateUtils.convertDateToStringFormat((Date) obj[5], "dd/MM/yyyy"));
                ordrDtl.setOrderDate((Date) obj[6]);
                ordrDtl.setFormatedOrdrDate(DateUtils.convertDateToStringFormat((Date) obj[6], "dd/MM/yyyy"));
                ordrDtl.setRefNo((String) obj[7]);
                ordrDtl.setOrderNo((Long) obj[8]);
                ordrDtl.setCustName((String) obj[9]);
                ordrDtl.setWrkshpCode((String) obj[10]);
                ordrDtl.setItemName((String) obj[11]);
                list.add(ordrDtl);
            }
        }
        return list;
    }
    
    @Override
    public List<Object[]> getTopCustomersByWeight(Date startDate) {
        String hql = "SELECT m.custCode, c.custName, SUM(d.itemWeight) " +
                     "FROM SalesOrder m, OrderDetailsJewellery d, CustomerDetailOthr c " +
                     "WHERE m.custCode=c.custCode and m.orderNo = d.salesOrder.orderNo " +
                     "and m.orderDate > :startDate " +
                     "GROUP BY m.custCode, c.custName " +
                     "ORDER BY SUM(d.itemWeight) DESC";

        Query query = entityManager.createQuery(hql);
        query.setParameter("startDate", startDate);
        query.setMaxResults(10);

        return query.getResultList();
    }
}
