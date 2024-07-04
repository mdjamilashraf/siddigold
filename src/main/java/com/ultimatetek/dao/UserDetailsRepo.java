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

import com.ultimatetek.entity.UserDetails;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ULTCPU20
 */
@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetails, Long> {

    @Query("SELECT ud FROM UserDetails ud where ud.userCode = ?1")
    public UserDetails getUserDetails(String userCode);

    @Query("SELECT ud FROM UserDetails ud where lower(ud.userCode) =lower(?1)")
    public UserDetails getUserDetailsByUsrCode(String userCode);

    @Query("SELECT ud FROM UserDetails ud where ud.userCode = ?1")
    public UserDetails getUserDtlsByUserCode(String userCode);

    @Query("SELECT ud FROM UserDetails ud where 1=1 and ud.userCode = ?1")
    public Optional<UserDetails> getUserDetailsByUserCode(String userCode);

    @Query("SELECT ud FROM UserDetails ud where 1=1 and ud.groupNo=1")
    public List<UserDetails> getAdminList();

    @Query("SELECT ud FROM UserDetails ud where 1=1 and ud.groupNo=3 order by userNo")
    public List<UserDetails> getCustomerList();

    @Query("SELECT ud.userNo FROM UserDetails ud where ud.userCode = ?1")
    public Long getUserNoByCode(String userCode);

    @Query("SELECT ud.password FROM UserDetails ud WHERE 1=1 and ud.userCode=?1")
    public String getPassword(String userCode);

    @Query("SELECT count(1) FROM UserDetails ud where lower(ud.userCode) =lower(?1)")
    public Integer checkUserByUserCode(String userCode);

}
