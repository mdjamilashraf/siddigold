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

import com.ultimatetek.dao.UserDetailsRepo;
import com.ultimatetek.entity.UserDetails;
import java.security.SignatureException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Md Junaid Khan
 */
@Service
public class ResetPasswordToEncryptImpl implements ResetPasswordToEncryptInterface {

    @Autowired
    private UserDetailsRepo userDetailsRepo;

    //dont run this file ever
    @Override
    public void changeAllUserPasswordtoencrypt() {
        List<UserDetails> ListOfUsers = userDetailsRepo.findAll();
        for (UserDetails user : ListOfUsers) {
            try {
                if (PswdSecurity.checkPasswordPolicy(user.getPassword())) {
                    System.out.println("password is already encripted for user=" + user.getUserCode());
                } else {
                    String encryptedPsword = PswdSecurity.calculateRFC2104HMAC(user.getPassword(), ProjectConst.SECRET_KEY);
                    System.out.println("password is not encripted for user =" + user.getUserCode());
                    user.setPassword(encryptedPsword);
//                userDetailsRepo.save(user);
                }

            } catch (SignatureException e) {
                e.printStackTrace();
            }

        }
    }
}
