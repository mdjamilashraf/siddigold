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
package com.ultimatetek.model;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author jamil
 */
@Data
public class OrderSearchParamVO {
    private Short srlNo;
    private String itemCode;
    private String refNo;
    private Date dueDate;
    private String wrkshpCode;
    private Integer orderStatus;
    private Integer orderTyp;
    private Long orderNo;
    private Date orderDate;
    private String custCode;
    private Integer wrkshpStatus;
    private Date statusDate;
    private String workerCode;
    private Date from;
    private Date to;
    private String priorityTyp;
    
}
