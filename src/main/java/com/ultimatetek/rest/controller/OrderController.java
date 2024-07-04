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
package com.ultimatetek.rest.controller;

import com.ultimatetek.model.RequestVO;
import com.ultimatetek.model.ResponseVO;
import com.ultimatetek.services.OrderService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author JunaidKhan
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "add Order", required = true, content = @Content(examples = {
        @ExampleObject(value = UsrJsonInputReq.ADDORDER)}))
    @PostMapping("/{custCode}")
    public ResponseEntity<Object> addOrder(@PathVariable(name = "custCode") String custCode, @RequestBody RequestVO req) throws Exception {
//        String custCode = req.getData().getOrdersDtls().getCustCode();
        ResponseVO response = orderService.addOrder(custCode, req.getData().getOrdersDtls());
        if (response.getResult().getErrNo() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/srcOnLoadOrderDtl")
    public ResponseEntity<Object> getScrOnLoadOrderDetails() throws Exception {
        ResponseVO response = orderService.getScrOnLoadOrderDetails();
        if (response.getResult().getErrNo() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/refNo/{custCode}")
    public ResponseEntity<Object> getPreAddRefNo(@PathVariable(name = "custCode") String custCode) throws Exception {
        ResponseVO response = orderService.getPreAddRefNo(custCode);
        if (response.getResult().getErrNo() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/orderNo/{orderNo}")
    public ResponseEntity<Object> getOrderDtlsByOrdrNo(@PathVariable(name = "orderNo") Long orderNo) throws Exception {
        ResponseVO response = orderService.getOrderDtlsByOrdrNo(orderNo);
        if (response.getResult().getErrNo() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getOrderDtls/{userCode}")
    public ResponseEntity<Object> getOrdrDtlsByUserCode(@PathVariable(name = "userCode") String userCode) throws Exception {
        ResponseVO response = orderService.getOrderDtlsByUsrCode(userCode);
        if (response.getResult().getErrNo() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/orderDtlNo/{orderDtlNo}")
    public ResponseEntity<Object> getOrderDtlsByOrderDtlNo(@PathVariable(name = "orderDtlNo") Long orderDtlNo) throws Exception {
        ResponseVO response = orderService.getOrderDtlsByOrderDtlNo(orderDtlNo);
        if (response.getResult().getErrNo() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/meltPer/{meltingPer}/{custCode}")
    public ResponseEntity<Object> getOnChangeMeltingPer(@PathVariable(required = false, value = "meltingPer") String meltingPer, @PathVariable String custCode) throws Exception {
        ResponseVO response = orderService.getOnChangeMeltingPer(custCode, meltingPer);
        if (response.getResult().getErrNo() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/days/{days}")
    public ResponseEntity<Object> getOnChangeDays(@PathVariable(name = "days") Integer days) throws Exception {
        ResponseVO response = orderService.getOnChangeDays(days);
        if (response.getResult().getErrNo() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/page")
    public ResponseEntity<Object> getOrderPaging(@RequestParam(name = "page") Integer page,
            @RequestParam(name = "itemSize") Integer itemSize) throws Exception {
        ResponseVO response = orderService.getPaging(page, itemSize);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
