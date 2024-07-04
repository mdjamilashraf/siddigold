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

import com.ultimatetek.model.OrderDetailsVO;
import com.ultimatetek.model.ResponseVO;

/**
 *
 * @author JunaidKhan
 */
public interface OrderService {

    public ResponseVO getScrOnLoadOrderDetails();

    public ResponseVO getOrderDtlsByOrdrNo(Long orderNo);

    public ResponseVO addOrder(String custCode, OrderDetailsVO ordrDtl);

    public ResponseVO getOrderDtlsByUsrCode(String userCode);

//    public ResponseVO getCustOrdrDtlsByCustRefNo(String refNo);
    public ResponseVO getOrderDtlsByOrderDtlNo(Long orderDtlNo);

    public ResponseVO getOnChangeMeltingPer(String custCode, String meltingPer);

    public ResponseVO getOnChangeDays(Integer days);

    public ResponseVO getPreAddRefNo(String custUsrCode);

    public ResponseVO getPaging(Integer page, Integer itemSize);

}
