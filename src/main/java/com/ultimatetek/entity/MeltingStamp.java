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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 *
 * @author ULTCPU20
 */
@Data
@Entity
@Table(name = "melting_stamp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MeltingStamp.findAll", query = "SELECT m FROM MeltingStamp m"),
    @NamedQuery(name = "MeltingStamp.findByMeltStampId", query = "SELECT m FROM MeltingStamp m WHERE m.meltStampId = :meltStampId"),
    @NamedQuery(name = "MeltingStamp.findByMeltingPer", query = "SELECT m FROM MeltingStamp m WHERE m.meltingPer = :meltingPer"),
    @NamedQuery(name = "MeltingStamp.findByStamp", query = "SELECT m FROM MeltingStamp m WHERE m.stamp = :stamp"),
    @NamedQuery(name = "MeltingStamp.findByDfltFlg", query = "SELECT m FROM MeltingStamp m WHERE m.dfltFlg = :dfltFlg"),
    @NamedQuery(name = "MeltingStamp.findByCustCode", query = "SELECT m FROM MeltingStamp m WHERE m.custCode = :custCode")})
public class MeltingStamp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "melt_stamp_id")
    private Integer meltStampId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "melting_per")
    private Integer meltingPer;
    @Size(max = 10)
    @Column(name = "stamp")
    private String stamp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dflt_flg")
    private short dfltFlg;
    @Size(max = 10)
    @Column(name = "cust_code")
    private String custCode;

}
