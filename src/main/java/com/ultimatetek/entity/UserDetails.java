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
package com.ultimatetek.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 *
 * @author JunaidKhan
 */
@Entity
@Table(name = "user_details")
@Data
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserDetails.findAll", query = "SELECT u FROM UserDetails u"),
    @NamedQuery(name = "UserDetails.findByUserNo", query = "SELECT u FROM UserDetails u WHERE u.userNo = :userNo"),
    @NamedQuery(name = "UserDetails.findByUserCode", query = "SELECT u FROM UserDetails u WHERE u.userCode = :userCode"),
    @NamedQuery(name = "UserDetails.findByUserFirstName", query = "SELECT u FROM UserDetails u WHERE u.userFirstName = :userFirstName"),
    @NamedQuery(name = "UserDetails.findByUserLastName", query = "SELECT u FROM UserDetails u WHERE u.userLastName = :userLastName"),
    @NamedQuery(name = "UserDetails.findByPassword", query = "SELECT u FROM UserDetails u WHERE u.password = :password"),
    @NamedQuery(name = "UserDetails.findByUserPhone", query = "SELECT u FROM UserDetails u WHERE u.userPhone = :userPhone"),
    @NamedQuery(name = "UserDetails.findByUserEmail", query = "SELECT u FROM UserDetails u WHERE u.userEmail = :userEmail"),
    @NamedQuery(name = "UserDetails.findByStartDateTime", query = "SELECT u FROM UserDetails u WHERE u.startDateTime = :startDateTime"),
    @NamedQuery(name = "UserDetails.findByEndDateTime", query = "SELECT u FROM UserDetails u WHERE u.endDateTime = :endDateTime"),
    @NamedQuery(name = "UserDetails.findByPasswordLoginAttempt", query = "SELECT u FROM UserDetails u WHERE u.passwordLoginAttempt = :passwordLoginAttempt"),
    @NamedQuery(name = "UserDetails.findByInactiveFlg", query = "SELECT u FROM UserDetails u WHERE u.inactiveFlg = :inactiveFlg"),
    @NamedQuery(name = "UserDetails.findByCrtUsrNo", query = "SELECT u FROM UserDetails u WHERE u.crtUsrNo = :crtUsrNo"),
    @NamedQuery(name = "UserDetails.findByCrtDate", query = "SELECT u FROM UserDetails u WHERE u.crtDate = :crtDate"),
    @NamedQuery(name = "UserDetails.findByCrtTrmnlNm", query = "SELECT u FROM UserDetails u WHERE u.crtTrmnlNm = :crtTrmnlNm"),
    @NamedQuery(name = "UserDetails.findByUpdUsrNo", query = "SELECT u FROM UserDetails u WHERE u.updUsrNo = :updUsrNo"),
    @NamedQuery(name = "UserDetails.findByUpdDate", query = "SELECT u FROM UserDetails u WHERE u.updDate = :updDate"),
    @NamedQuery(name = "UserDetails.findByUpdTrmnlNm", query = "SELECT u FROM UserDetails u WHERE u.updTrmnlNm = :updTrmnlNm"),
    @NamedQuery(name = "UserDetails.findByUpdCnt", query = "SELECT u FROM UserDetails u WHERE u.updCnt = :updCnt")})
public class UserDetails implements Serializable {

    @JoinColumn(name = "group_no", referencedColumnName = "group_no")
    @ManyToOne(optional = false)

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_no")
    private Long userNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "user_code")
    private String userCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "user_first_name")
    private String userFirstName;
    @Size(max = 2147483647)
    @Column(name = "user_last_name")
    private String userLastName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "password")
    private String password;
    @Size(max = 2147483647)
    @Column(name = "user_phone")
    private String userPhone;
    @Size(max = 2147483647)
    @Column(name = "user_email")
    private String userEmail;
    @Column(name = "start_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateTime;
    @Column(name = "end_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateTime;
    @Column(name = "password_login_attempt")
    private Short passwordLoginAttempt;
    @Column(name = "inactive_flg")
    private Short inactiveFlg;
    @Basic(optional = false)
    @NotNull
    @Column(name = "crt_usr_no", updatable = false)
    private long crtUsrNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "crt_date", updatable = false)
    @Temporal(TemporalType.DATE)
    private Date crtDate;
    @Size(max = 2147483647)
    @Column(name = "crt_trmnl_nm")
    private String crtTrmnlNm;
    @Column(name = "upd_usr_no")
    private Long updUsrNo;
    @Column(name = "upd_date")
    @Temporal(TemporalType.DATE)
    private Date updDate;
    @Size(max = 2147483647)
    @Column(name = "upd_trmnl_nm")
    private String updTrmnlNm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "upd_cnt")
    private int updCnt;
    @Column(name = "group_no")
    private int groupNo;

}
