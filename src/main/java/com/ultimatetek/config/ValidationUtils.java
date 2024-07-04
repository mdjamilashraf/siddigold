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
package com.ultimatetek.config;

import com.ultimatetek.model.OrderDetailsVO;
import com.ultimatetek.model.ResultVO;
import java.util.List;

/**
 *
 * @author jamil
 */
public class ValidationUtils {

    public static ResultVO validateOrders(String custCode, List<OrderDetailsVO> orders, Integer usrGrpNo) {
        ResultVO result = null;
        if (StringUtils.isEmpty(custCode)) {
            result = new ResultVO(400, "Invalid Customer code");
        } else if (!orders.isEmpty()) {
            for (OrderDetailsVO ordrDtlVo : orders) {
                if (usrGrpNo == 1) {
                    if (ordrDtlVo.getItemCode() != null
                            && (ordrDtlVo.getItemName() == null
                            || ordrDtlVo.getItemUnit() == null
                            || ordrDtlVo.getQty() == null
                            || ordrDtlVo.getItemSize() == null
                            || ordrDtlVo.getMeltPer() == null
                            || ordrDtlVo.getWeight() == null
                            || ordrDtlVo.getDays() == null
                            || ordrDtlVo.getDueDate() == null
                            || ordrDtlVo.getWorkshop() == null
                            || ordrDtlVo.getOrderTyp() == null)) {
                        result = new ResultVO(400, "Invalid Order Row");
                        break;
                    }
                } else if (usrGrpNo == 3) {
                    if (ordrDtlVo.getItemCode() != null
                            && (ordrDtlVo.getItemUnit() == null
                            || ordrDtlVo.getQty() == null
                            || ordrDtlVo.getItemSize() == null
                            || ordrDtlVo.getMeltPer() == null
                            || ordrDtlVo.getWeight() == null
                            || ordrDtlVo.getDays() == null
                            || ordrDtlVo.getDueDate() == null)) {
                        result = new ResultVO(400, "Invalid Order Row");
                        break;
                    }
                }
            }
        }
        return result;
    }
}
