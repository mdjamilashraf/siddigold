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
import com.ultimatetek.config.ProjectConst;
import com.ultimatetek.config.StringUtils;
import com.ultimatetek.dao.CustomerRepo;
import com.ultimatetek.dao.DefaultMeltingRepo;
import com.ultimatetek.dao.GeneralRepo;
import com.ultimatetek.dao.ItemMstRepo;
import com.ultimatetek.dao.OrderDetailsRepo;
import com.ultimatetek.dao.SalesOrderRepo;
import com.ultimatetek.dao.UserDetailsRepo;
import com.ultimatetek.entity.CustomerDetailOthr;
import com.ultimatetek.entity.OrderDetailsJewellery;
import com.ultimatetek.entity.SalesOrder;
import com.ultimatetek.entity.UserDetails;
import com.ultimatetek.model.CustomerVO;
import com.ultimatetek.model.DataVO;
import com.ultimatetek.model.DefaultMeltingVO;
import com.ultimatetek.model.OrderDetailsVO;
import com.ultimatetek.model.ResponseVO;
import com.ultimatetek.model.ResultVO;
import com.ultimatetek.model.SalesOrderVO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.faces.model.SelectItem;
import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author JunaidKhan
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private GeneralRepo gnrRepo;
    @Autowired
    private SalesOrderRepo salesOrderRepo;
    @Autowired
    private OrderDetailsRepo orderDtlRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private GeneralRepo generalRepo;
    @Autowired
    private ItemMstRepo itemMstRepo;
    @Autowired
    private UserDetailsRepo userDetailsRepo;
    @Autowired
    private DefaultMeltingRepo defaultMeltingRepo;
    @Autowired
    private CustomerRepo custRepo;

    @Override
    public ResponseVO getScrOnLoadOrderDetails() {
        ResponseVO response = new ResponseVO();
        DataVO data = new DataVO();
        List<SalesOrder> salesOrder = salesOrderRepo.findAll();
        List<SalesOrderVO> salesOrderList = new ArrayList<>();
        Set<Long> setOfOrderNo = new HashSet<>();
        for (SalesOrder salesObj : salesOrder) {
            SalesOrderVO entity = new SalesOrderVO();
            entity.setOrderNo(salesObj.getOrderNo());
            entity.setOrderDate(salesObj.getOrderDate());
            salesOrderList.add(entity);
            setOfOrderNo.add(salesObj.getOrderNo());
        }
        List<OrderDetailsJewellery> lstOrderDetails = null;
        if (!setOfOrderNo.isEmpty()) {
            lstOrderDetails = orderDtlRepo.getOrderDtlsListByOrderNo(Arrays.asList(setOfOrderNo.toArray()));
        } else {
            lstOrderDetails = new ArrayList<>();
        }
        for (SalesOrderVO slsOrdrVO : salesOrderList) {
            List<OrderDetailsVO> ordrDtls = new ArrayList<>();
            Iterator<OrderDetailsJewellery> itr = lstOrderDetails.iterator();
            while (itr.hasNext()) {
                OrderDetailsJewellery ordrDtlsJewellery = itr.next();
                if (ordrDtlsJewellery.getSalesOrder().getOrderNo().equals(slsOrdrVO.getOrderNo())) {
                    OrderDetailsVO orderDetailsVO = new OrderDetailsVO();
                    orderDetailsVO.setItemCode(ordrDtlsJewellery.getItemCode());
                    orderDetailsVO.setOrderStatus(ordrDtlsJewellery.getOrderStatus());
                    orderDetailsVO.setRefNo(ordrDtlsJewellery.getRefNo());
                    orderDetailsVO.setOrderDtlNo(ordrDtlsJewellery.getOrderDtlNo());
                    ordrDtls.add(orderDetailsVO);
                }
            }
            slsOrdrVO.setOrdersDtls(ordrDtls);
        }
        List<SelectItem> list = new ArrayList<>();
        List<Object[]> objects = generalRepo.getDropdownItems("ItemMst", "itmCode", "itmName");
        List<Object[]> meltingObject = generalRepo.getDropdownItems("ItemMst", "itmCode", "itmName");
        for (Object[] obj : objects) {
            list.add(new SelectItem(obj[0], (String) obj[1]));
        }
        if (!salesOrderList.isEmpty()) {
            data.setItemNameList(list);
            data.setSalesOrderList(salesOrderList);
            response.setResult(new ResultVO(HttpStatus.OK.value(), "Success"));
            response.setData(data);
        } else {
            response.setResult(new ResultVO(HttpStatus.NOT_FOUND.value(), "Not Found"));
        }
        return response;
    }

    @Override
    public ResponseVO getOrderDtlsByOrdrNo(Long orderNo) {
        ResponseVO response = new ResponseVO();
        DataVO data = new DataVO();
        List<OrderDetailsJewellery> entities = orderDtlRepo.getOrderDtlsListByCode(orderNo);
        List<OrderDetailsVO> orderDtlsEntities = new ArrayList();
        if (entities.isEmpty()) {
            response.setResult(new ResultVO(HttpStatus.NOT_FOUND.value(), "orderNo Not Exist"));
        } else {
            for (OrderDetailsJewellery ordrDtlsObj : entities) {
                OrderDetailsVO entity = new OrderDetailsVO();
                entity.setOrderDtlNo(ordrDtlsObj.getOrderDtlNo());
                entity.setOrderNo(ordrDtlsObj.getSalesOrder().getOrderNo());
                entity.setItemCode(ordrDtlsObj.getItemCode());
                entity.setItemName(itemMstRepo.getItemNameByCode(ordrDtlsObj.getItemCode()));
                entity.setItemUnit(ordrDtlsObj.getItemUnit());
                entity.setWeight(ordrDtlsObj.getItemWeight());
                entity.setItemSize(ordrDtlsObj.getItemSize());
                entity.setQty(ordrDtlsObj.getItemQty());
                entity.setMeltPer(ordrDtlsObj.getMeltPer());
                entity.setStamp(ordrDtlsObj.getStamp());
                entity.setHook(ordrDtlsObj.getHook());
                entity.setDesignSample(ordrDtlsObj.getDesignSample());
                entity.setSizeSample(ordrDtlsObj.getSizeSample());
                entity.setRefNo(ordrDtlsObj.getRefNo());
                entity.setRemark(ordrDtlsObj.getRemarks());
                entity.setItemPrice(ordrDtlsObj.getItemPrice());
                entity.setOrderTyp(ordrDtlsObj.getOrderType());
                entity.setOrderStatus(ordrDtlsObj.getOrderStatus());
                orderDtlsEntities.add(entity);
            }
            if (!orderDtlsEntities.isEmpty()) {
                data.setOrderDtlsList(orderDtlsEntities);
                response.setResult(new ResultVO(HttpStatus.OK.value(), "Success"));
                response.setData(data);
            } else {
                response.setResult(new ResultVO(HttpStatus.NOT_FOUND.value(), "orderNo does'nt Exist"));
            }
        }
        return response;
    }

    @Override
    @Transactional
    public ResponseVO addOrder(String custCode, OrderDetailsVO ordrDtl) {
        ResponseVO response = new ResponseVO();
        SalesOrder entity = new SalesOrder();
        long ordrNo = (long) (Integer) gnrRepo.getAutoIncrementVal("SalesOrder", "orderNo",null);
        entity.setOrderNo(++ordrNo);
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
        entity.setCrtUsrNo(userDetailsRepo.getUserNoByCode(custCode));
        entity.setCrtDate(DateUtils.getTodayDate());
        entity.setCrtTrmnlNm("mobile");
        entity.setUpdCnt(0);
        entity.setStopSeq(0);
        int rcrdNo = 0;
        long ordrDtlNo = (long) (Integer) gnrRepo.getAutoIncrementVal("OrderDetailsJewellery", "orderDtlNo",null);
        OrderDetailsJewellery ordrDtlEntity = new OrderDetailsJewellery();
        ordrDtlEntity.setOrderDtlNo(++ordrDtlNo);
        ordrDtlEntity.setItemCode(itemMstRepo.getItemCodeByItemName(ordrDtl.getItemName()));
        ordrDtlEntity.setItemWeight(ordrDtl.getWeight());
        ordrDtlEntity.setItemSize(ordrDtl.getItemSize());
        ordrDtlEntity.setItemUnit(ordrDtl.getItemUnit());
        ordrDtlEntity.setItemQty(ordrDtl.getQty());
        ordrDtlEntity.setMeltPer(ordrDtl.getMeltPer());
        ordrDtlEntity.setStamp(ordrDtl.getStamp());
        ordrDtlEntity.setRefNo(ordrDtl.getRefNo());
        ordrDtlEntity.setDays(ordrDtl.getDays());
        LocalDate currentdDate1 = LocalDate.now();
        LocalDate currentDatePlus1 = currentdDate1.plusDays(ordrDtl.getDays());
        ordrDtlEntity.setDueDate(java.sql.Date.valueOf(currentDatePlus1));
        String priceTyp = ordrDtl.getFixRate().substring(0, 1);
        ordrDtlEntity.setPriceType(priceTyp);
        String price = ordrDtl.getFixRate().substring(1);
        ordrDtlEntity.setItemPrice(Double.parseDouble(price));
        ordrDtlEntity.setOrderType(1);
        ordrDtlEntity.setOrderStatus(5);//set Order Recived
        ordrDtlEntity.setCrtDate(DateUtils.getTodayDate());
        ordrDtlEntity.setCrtTrmnlNm("mobile");
        ordrDtlEntity.setCrtUsrNo(userDetailsRepo.getUserNoByCode(custCode));
        ordrDtlEntity.setUpdCnt(0);
        ordrDtlEntity.setRcrdNo(++rcrdNo);
        if (ordrDtl.getRcvSample()) {
            ordrDtlEntity.setRcvSample((short) 1);
        } else {
            ordrDtlEntity.setRcvSample((short) 0);
        }
        if (ordrDtl.getWrkshpStatus() != null) {
            ordrDtlEntity.setWrkshpStatus(ordrDtl.getWrkshpStatus());
        } else {
            ordrDtlEntity.setWrkshpStatus(0);
        }
        if (ordrDtl.getWrkshpStatusDate() == null) {
            ordrDtlEntity.setWrkshpStatusDate(DateUtils.getTodayDate());
        }
        if (StringUtils.isEmpty(ordrDtl.getDesignSample())) {
            ordrDtlEntity.setDesignSample(null);
        } else {
            ordrDtlEntity.setDesignSample(ordrDtl.getDesignSample());
        }
        if (StringUtils.isEmpty(ordrDtl.getSizeSample())) {
            ordrDtlEntity.setSizeSample(null);
        } else {
            ordrDtlEntity.setSizeSample(ordrDtl.getSizeSample());
        }
        if (StringUtils.isEmpty(ordrDtl.getHook())) {
            ordrDtlEntity.setHook(null);
        } else {
            ordrDtlEntity.setHook(ordrDtl.getHook());
        }
        if (StringUtils.isEmpty(ordrDtl.getRemark())) {
            ordrDtlEntity.setRemarks(null);
        } else {
            ordrDtlEntity.setRemarks(ordrDtl.getRemark());
        }
        ordrDtlEntity.setSalesOrder(entity);
        if (ordrDtlEntity != null) {
            try {
                salesOrderRepo.save(entity);
                if (ordrDtl.getImageData() != null && ordrDtl.getImageData().length > 0 && ordrDtl.getImageData().length <= 4) {
                    String images = null;
                    String crntDateFrmtForImage = DateUtils.convertDateToStringFormat(ordrDtlEntity.getCrtDate(), "yyyyMMdd");
                    this.uploadImage(ordrDtl.getImageData(), crntDateFrmtForImage, ordrDtlNo);
                    for (int i = 0; i < ordrDtl.getImageData().length; i++) {
//                         String extension = "png";
                        switch (i) {
                            case 0:
                                images = crntDateFrmtForImage + "-" + ordrDtlNo + "-" + i;
                                break;
                            case 1:
                                images = images + "," + crntDateFrmtForImage + "-" + ordrDtlNo + "-" + i;
                                break;
                            case 2:
                                images = images + "," + crntDateFrmtForImage + "-" + ordrDtlNo + "-" + i;
                                break;
                            case 3:
                                images = images + "," + crntDateFrmtForImage + "-" + ordrDtlNo + "-" + i;
                                break;
                            default:
                                break;
                        }
                    }
                    ordrDtlEntity.setImgPath1(images);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                orderDtlRepo.save(ordrDtlEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.setResult(new ResultVO(HttpStatus.OK.value(), "success"));
        } else {
            response.setResult(new ResultVO(HttpStatus.BAD_REQUEST.value(), "Given Input is not valid"));
        }

        return response;
    }

    @Override
    public ResponseVO getOrderDtlsByUsrCode(String userCode) {
        ResponseVO response = new ResponseVO();
        DataVO data = new DataVO();
        //check UserRole By userCode
        UserDetails usrDtls = userDetailsRepo.getUserDtlsByUserCode(userCode);
        if (usrDtls != null && usrDtls.getGroupNo() == 1) {
            List<UserDetails> CustDtlsList = userDetailsRepo.getCustomerList();
            List<CustomerVO> custDtlsList = new ArrayList<>();
            for (UserDetails usr : CustDtlsList) {
                CustomerDetailOthr custDtls = custRepo.getCustomerDtlsByCode(usr.getUserCode());
                if (custDtls != null) {
                    CustomerVO custObj = new CustomerVO();
                    custObj.setCustCode(usr.getUserCode());
                    custObj.setCustName(usr.getUserFirstName());
                    if (!StringUtils.isEmpty(custDtls.getCustAddr())) {
                        custObj.setCustAddr(custDtls.getCustAddr());
                    } else {
                        custObj.setCustAddr(null);
                    }
                    String strFormatCustCrtDate = DateUtils.convertDateToStringFormat(custDtls.getCrtDate(), "dd/MM/yyyy");
                    custObj.setFormatedCustCrtDate(strFormatCustCrtDate);
                    custObj.setCustInactiveFlg(custDtls.getInactiveFlg());
                    custDtlsList.add(custObj);
                }
            }
            data.setCustomerList(custDtlsList);
            response.setData(data);
            response.setResult(new ResultVO(HttpStatus.OK.value(), "Success"));
        } else {
            List<Object[]> orderData = generalRepo.getOrdrDtlsBycustCode(userCode);
            List<OrderDetailsVO> orderDtls = new ArrayList<>();
            if (!orderData.isEmpty()) {
                for (Object[] obj : orderData) {
                    OrderDetailsVO orderDetailsVO = new OrderDetailsVO();
                    orderDetailsVO.setFormatedDueDate((String) obj[0]);
                    orderDetailsVO.setFormatedStatusDate((String) obj[1]);
                    orderDetailsVO.setRefNo((String) obj[2]);
                    orderDetailsVO.setOrderStatus((int) obj[3]);
                    if (null != orderDetailsVO.getOrderStatus()) {
                        switch (orderDetailsVO.getOrderStatus()) {
                            case 1:
                                orderDetailsVO.setStatus("Confirmed");
                                break;
                            case 2:
                                orderDetailsVO.setStatus("Assigned");
                                break;
                            case 3:
                                orderDetailsVO.setStatus("Closed");
                                break;
                            case 4:
                                orderDetailsVO.setStatus("Cancelled");
                                break;
                            case 5:
                                orderDetailsVO.setStatus("Order Received");
                                break;
                            default:
                                break;
                        }
                    }
                    orderDetailsVO.setItemCode((String) obj[4]);
                    orderDetailsVO.setCustCode(userCode);
                    orderDetailsVO.setItemName(itemMstRepo.getItemNameByCode(orderDetailsVO.getItemCode()));
                    orderDetailsVO.setOrderNo((Long) obj[5]);
                    orderDetailsVO.setSrlNo(Short.valueOf(obj[6].toString()));
                    orderDetailsVO.setOrderDtlNo((Long) obj[7]);
                    orderDtls.add(orderDetailsVO);
                }
                data.setCustomerOrdrDtls(orderDtls);
                response.setData(data);
                response.setResult(new ResultVO(HttpStatus.OK.value(), "Success"));
            } else if (orderData.isEmpty()) {
                OrderDetailsVO orderDetailsVO = new OrderDetailsVO();
                orderDetailsVO.setCustCode(userCode);
                orderDtls.add(orderDetailsVO);
                data.setCustomerOrdrDtls(orderDtls);
                response.setData(data);
                response.setResult(new ResultVO(HttpStatus.BAD_REQUEST.value(), "BadRequest"));
            }
        }
        return response;
    }

    @Override
    public ResponseVO getOrderDtlsByOrderDtlNo(Long orderDtlNo) {
        ResponseVO response = new ResponseVO();
        DataVO data = new DataVO();
        OrderDetailsJewellery orderDetailsJewellery = orderDtlRepo.getOrderDtlsByOrderDtlNo(orderDtlNo);
        OrderDetailsVO orderEntity = new OrderDetailsVO();
        orderEntity.setOrderNo(orderDetailsJewellery.getSalesOrder().getOrderNo());
        String strFormatOrdrDt = DateUtils.convertDateToStringFormat(orderDetailsJewellery.getSalesOrder().getOrderDate(), "dd/MM/yyyy");
        String strFormatDueDt = DateUtils.convertDateToStringFormat(orderDetailsJewellery.getDueDate(), "dd/MM/yyyy");
        orderEntity.setFormatedOrdrDate(strFormatOrdrDt);
        orderEntity.setItemCode(orderDetailsJewellery.getItemCode());
        orderEntity.setItemName(itemMstRepo.getItemNameByCode(orderDetailsJewellery.getItemCode()));
        orderEntity.setWeight(orderDetailsJewellery.getItemWeight());
        orderEntity.setItemSize(orderDetailsJewellery.getItemSize());
        orderEntity.setQty(orderDetailsJewellery.getItemQty());
        orderEntity.setMeltPer(orderDetailsJewellery.getMeltPer());
        orderEntity.setStamp(orderDetailsJewellery.getStamp());
        orderEntity.setHook(orderDetailsJewellery.getHook());
        orderEntity.setDesignSample(orderDetailsJewellery.getDesignSample());
        orderEntity.setSizeSample(orderDetailsJewellery.getSizeSample());
        orderEntity.setRefNo(orderDetailsJewellery.getRefNo());
        orderEntity.setDays(orderDetailsJewellery.getDays());
        orderEntity.setFormatedDueDate(strFormatDueDt);
        orderEntity.setCustName(custRepo.getCustomerNmbyCode(orderDetailsJewellery.getSalesOrder().getCustCode()));
        orderEntity.setSrlNo((short) orderDetailsJewellery.getRcrdNo());
        String fileName = orderDtlRepo.getImagePath(orderDetailsJewellery.getOrderDtlNo());
        if (!StringUtils.isEmpty(fileName)) {
            String[] fileNamesSeparator = fileName.split(",");

            List<String> listOfImages = new ArrayList<>(Arrays.asList(fileNamesSeparator));
            List<String> base64Images = new ArrayList<>();
            for (int i = 0; i < listOfImages.size(); i++) {
                try {
                    String path = ProjectConst.ROOT_DIR + File.separator + "design_sample" + File.separator + listOfImages.get(i) + ".png";
                    System.out.println("Image is coming from ::::" +path);
                    byte[] imageBytes = Files.readAllBytes(Path.of(path));
                    String base64Image = java.util.Base64.getEncoder().encodeToString(imageBytes);
                    base64Images.add(base64Image);
                } catch (IOException e) {
                    System.err.println("Failed to convert image to Base64: " + e.getMessage());
                }
            }
            String[] imagePathArray = base64Images.toArray(new String[0]);
//            orderEntity.setImgPath1(imagePathArray);
            orderEntity.setImageData(imagePathArray);
        }
        if (orderDetailsJewellery.getOrderType() == 1) {
            orderEntity.setType("Order");
        } else {
            orderEntity.setType("Repair");
           
        }
        String strFormatDtFrWrkShop = DateUtils.convertDateToStringFormat(orderDetailsJewellery.getWrkshpStatusDate(), "dd/MM/yyyy");
        if (null != orderDetailsJewellery.getOrderStatus() && strFormatDtFrWrkShop != null) {
            switch (orderDetailsJewellery.getOrderStatus()) {
                case 1:
                    orderEntity.setStatus("Confirmed");
                    orderEntity.setFormatedStatusDate(strFormatDtFrWrkShop);
                    break;
                case 2:
                    orderEntity.setStatus("Assigned");
                    orderEntity.setFormatedStatusDate(strFormatDtFrWrkShop);
                    break;
                case 3:
                    orderEntity.setStatus("Closed");
                    orderEntity.setFormatedStatusDate(strFormatDtFrWrkShop);
                    break;
                case 4:
                    orderEntity.setStatus("Cancelled");
                    orderEntity.setFormatedStatusDate(strFormatDtFrWrkShop);
                    break;
                case 5:
                    orderEntity.setStatus("Order Received");
                    orderEntity.setFormatedStatusDate(strFormatDtFrWrkShop);
                    break;
                default:
                    break;
            }
        }
        if (orderDetailsJewellery.getOrderDtlNo() != null) {
            data.setOrdrDtlsByOrdrDtlNo(orderEntity);
            response.setData(data);
            response.setResult(new ResultVO(HttpStatus.OK.value(), "Success"));
        } else {
            response.setResult(new ResultVO(HttpStatus.NOT_FOUND.value(), "orderDtlNo does'nt Exist"));
        }
        return response;
    }

    @Override
    public ResponseVO getOnChangeMeltingPer(String custCode, String meltingPer) {
        ResponseVO response = new ResponseVO();
        DataVO data = new DataVO();

        if (meltingPer != null) {
            DefaultMeltingVO dfltMltng = new DefaultMeltingVO();
            String stamp = defaultMeltingRepo.getCustomerStamp(custCode, Integer.parseInt(meltingPer));
            if (stamp == null) {
                stamp = defaultMeltingRepo.getDfltMeltingStamp(Integer.parseInt(meltingPer));
                if (StringUtils.isEmpty(stamp)) {
                    stamp = "INVALID";
                }
            }
            dfltMltng.setStamp(stamp);
            data.setMeltingStamp(dfltMltng);
            response.setData(data);
            response.setResult(new ResultVO(HttpStatus.OK.value(), "Success"));
        } else {
            response.setResult(new ResultVO(HttpStatus.NOT_FOUND.value(), "Invalid Melt%"));
        }
        return response;
    }

    @Override
    public ResponseVO getOnChangeDays(Integer days) {
        ResponseVO response = new ResponseVO();
        DataVO data = new DataVO();
        Date dueDate = DateUtils.incrementDateNew(new Date(), days);
        String strFormatDt = DateUtils.convertDateToStringFormat(dueDate, "dd/MM/yyyy");
        if (strFormatDt != null) {
            data.setDueDate(strFormatDt);
            response.setData(data);
            response.setResult(new ResultVO(HttpStatus.OK.value(), "Success"));
        } else {
            response.setResult(new ResultVO(HttpStatus.NOT_FOUND.value(), "Given Days is Invalid"));
        }
        return response;
    }

    @Override
    public ResponseVO getPreAddRefNo(String custCode) {
        ResponseVO response = new ResponseVO();
        DataVO data = new DataVO();
        String customerCode = custCode.substring(0, 3);
        Long CheckOrders = salesOrderRepo.checkSalesOrdrByCustCode(custCode);
        Long maxOrderDtls = orderDtlRepo.getMaxOrdrByCustCode(custCode);
        UserDetails user = userDetailsRepo.getUserDtlsByUserCode(custCode);
        if (CheckOrders >= 1 && user != null) {
            data.setRefNo(customerCode + "-" + maxOrderDtls);
            response.setData(data);
            response.setResult(new ResultVO(HttpStatus.OK.value(), "Success"));
        } else if (CheckOrders == 0 && user != null) {
            Long newOrderDtls = (maxOrderDtls == null || maxOrderDtls == 0) ? 1 : ++maxOrderDtls;
            data.setRefNo(customerCode + "-" + newOrderDtls);
            response.setData(data);
            response.setResult(new ResultVO(HttpStatus.OK.value(), "Success"));
        } else {
            response.setResult(new ResultVO(HttpStatus.NOT_FOUND.value(), "Invalid custCode"));
        }
        return response;
    }

    private void uploadImage(String[] imageData, String dateFormat, Long ordrDtlNo) {
        if (imageData == null) {
            return;
        }
        String extension = "png";

        List<String> listOfImages = new ArrayList<>(Arrays.asList(imageData));
        for (int i = 0; i < listOfImages.size(); i++) {
            String fileName = dateFormat + "-" + ordrDtlNo + "-" + i;
            try {
                byte[] imageBytes = java.util.Base64.getDecoder().decode(listOfImages.get(i));
                // Creating the directory to store file/data/image
                String path = ProjectConst.ROOT_DIR + File.separator + "design_sample";
                File fileSaveDir = new File(path);
                // Creates the save directory if it does not exists
                if (!fileSaveDir.exists()) {
                    fileSaveDir.mkdirs();
                }
                BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageBytes));
                // write the image to a file
                if (image != null) {
//                // Resize the image to a desired resolution
                    int maxWidth = 900;   // Maximum width in pixels
                    int maxHeight = 700;  // Maximum height in pixels

                    int originalWidth = image.getWidth();
                    int originalHeight = image.getHeight();

                    int newWidth = originalWidth;
                    int newHeight = originalHeight;

                    // Check if resizing is required
                    if (originalWidth > maxWidth || originalHeight > maxHeight) {
                        // Calculate new dimensions while maintaining aspect ratio
                        double widthRatio = (double) maxWidth / originalWidth;
                        double heightRatio = (double) maxHeight / originalHeight;

                        // Choose the minimum ratio to ensure the image fits within the specified limits
                        double ratio = Math.min(widthRatio, heightRatio);

                        newWidth = (int) (originalWidth * ratio);
                        newHeight = (int) (originalHeight * ratio);
                    }

                    // Resize the image
                    Image resizedImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

                    // Convert the resized image to a BufferedImage
                    BufferedImage compressedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
                    compressedImage.getGraphics().drawImage(resizedImage, 0, 0, null);

                    // Encode the compressed image back to Base64
                    File outputfile = new File(path + File.separator + fileName + "." + extension);
                    outputfile.createNewFile();
                    ImageIO.write(compressedImage, "png", outputfile);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ResponseVO getPaging(Integer page, Integer itemSize) {
        ResponseVO response = new ResponseVO();
        DataVO data = new DataVO();
        PageRequest pageRequest = new PageRequest(page, itemSize);
        Page<SalesOrder> pagedResult = salesOrderRepo.findAll(pageRequest);
        if (pagedResult.hasContent()) {
            List<SalesOrderVO> list = new ArrayList();
            for (SalesOrder sls : pagedResult.getContent()) {
                SalesOrderVO salesOrderVO = new SalesOrderVO();
                salesOrderVO.setOrderNo(sls.getOrderNo());
                salesOrderVO.setCustCode(sls.getCustCode());
                CustomerDetailOthr custEntity = custRepo.getCustomerDtlsByCode(sls.getCustCode());
                salesOrderVO.setCustName(custEntity.getCustName());
                if (!StringUtils.isEmpty(custEntity.getCustAddr())) {
                    salesOrderVO.setCustAddr(custEntity.getCustAddr());
                } else {
                    salesOrderVO.setCustAddr("Customer has no Address");
                }
                String strFormatCustCrtDate = DateUtils.convertDateToStringFormat(custEntity.getCrtDate(), "dd/MM/yyyy");
                salesOrderVO.setFormatedCustCrtDate(strFormatCustCrtDate);
                salesOrderVO.setCustInactiveFlg(custEntity.getInactiveFlg());
                list.add(salesOrderVO);
            }
            data.setSalesOrderList(list);
            response.setData(data);
            response.setResult(new ResultVO(HttpStatus.OK.value(), "Success"));
        } else {
            response.setResult(new ResultVO(HttpStatus.NOT_FOUND.value(), "Page Not Found"));
        }
        return response;
    }

}
