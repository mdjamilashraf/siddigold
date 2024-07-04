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

import com.ultimatetek.config.ProjectConst;
import com.ultimatetek.config.PswdSecurity;
import com.ultimatetek.dao.UserDetailsRepo;
import com.ultimatetek.entity.UserDetails;
import com.ultimatetek.model.DataVO;
import com.ultimatetek.model.RequestVO;
import com.ultimatetek.model.ResponseVO;
import com.ultimatetek.model.ResultVO;
import com.ultimatetek.model.UserDetailsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author JunaidKhan
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserDetailsRepo userDetailsRepo;

    @Override
    public UserDetails getUserDetails(String userName) {
        return userDetailsRepo.getUserDetails(userName);
    }

    @Override
    public ResponseVO authenticateService(RequestVO req) {
        ResponseVO response = new ResponseVO();
        DataVO data = new DataVO();
        String userCode = req.getData().getLoginData().getUserName();
        String password = req.getData().getLoginData().getPassword();
        try {
            UserDetails userDetails = userDetailsRepo.getUserDetailsByUsrCode(userCode);
            if (userDetails != null) {
                String encryptedPsword = PswdSecurity.calculateRFC2104HMAC(password, ProjectConst.SECRET_KEY);
                if (!encryptedPsword.equals(userDetails.getPassword())) {
                    response.setResult(new ResultVO(400, "Invalid password"));
                } else {
                    UserDetailsVO userDetailsVO = new UserDetailsVO();
                    userDetailsVO.setUserCode(userDetails.getUserCode());
                    userDetailsVO.setUserName(userDetails.getUserFirstName());
                    userDetailsVO.setGroupNo(userDetails.getGroupNo());
                    data.setUserDetails(userDetailsVO);
                    response.setData(data);
                    response.setResult(new ResultVO(200, "Login Success!"));
                }
            } else {
                response.setResult(new ResultVO(400, "Invalid UserCode"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

//    @Override
//    public ResponseVO forgotPassword(RequestVO req) {
//        ResponseVO response = new ResponseVO();
//        String userCode = req.getData().getLoginData().getUserName();
//        String password = req.getData().getLoginData().getPassword();
//        String cnfPassword = req.getData().getLoginData().getCnfPassword();
//        Optional<UserDetails> optional = userDetailsRepo.getUserDetailsByUserCode(userCode);
//        if (optional.isPresent()) {
//            UserDetails detachedEntity = optional.get();
//            String oldPaswordFrmEntity = detachedEntity.getPassword();
//            if (password.equals(cnfPassword)) {
//                detachedEntity.setPassword(cnfPassword);
//                response.setResult(new ResultVO(HttpStatus.OK.value(), "You have successfully changed your password!"));
//            }else {
//                response.setResult(new ResultVO(HttpStatus.BAD_REQUEST.value(), "Passwords do not match!"));
//            }
//        }
//    }
    @Override
    public String getPasswordAuthentication(String userName) {
        return userDetailsRepo.getPassword(userName);
    }

}
