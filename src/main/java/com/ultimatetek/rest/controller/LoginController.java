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
import com.ultimatetek.services.AuthenticationService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author JunaidKhan
 */
@RestController
//@CrossOrigin("*")
public class LoginController {

    @Autowired
    private AuthenticationService authService;

    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Login User", required = true, content = @Content(examples = {
        @ExampleObject(value = UsrJsonInputReq.LOGIN)}))
    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody RequestVO req) throws Exception {
        ResponseVO response = authService.authenticateService(req);
        if (response.getResult().getErrNo() == 200) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

//    @PostMapping("/forgotPassword")
//    public ResponseEntity<?> forgotPassword(@RequestBody RequestVO req) throws Exception {
//        ResponseVO response = authService.forgotPassword(req);
//        if (response.getResult().getErrNo() == 200) {
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//        }
//
//    }
}
