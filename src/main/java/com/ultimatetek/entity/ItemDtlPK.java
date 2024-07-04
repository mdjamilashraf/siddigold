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
package com.ultimatetek.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author JunaidKhan
 */
@Embeddable
public class ItemDtlPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "itm_code")
    private String itmCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "itm_unt")
    private String itmUnt;

    public ItemDtlPK() {
    }

    public ItemDtlPK(String itmCode, String itmUnt) {
        this.itmCode = itmCode;
        this.itmUnt = itmUnt;
    }

    public String getItmCode() {
        return itmCode;
    }

    public void setItmCode(String itmCode) {
        this.itmCode = itmCode;
    }

    public String getItmUnt() {
        return itmUnt;
    }

    public void setItmUnt(String itmUnt) {
        this.itmUnt = itmUnt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itmCode != null ? itmCode.hashCode() : 0);
        hash += (itmUnt != null ? itmUnt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemDtlPK)) {
            return false;
        }
        ItemDtlPK other = (ItemDtlPK) object;
        if ((this.itmCode == null && other.itmCode != null) || (this.itmCode != null && !this.itmCode.equals(other.itmCode))) {
            return false;
        }
        if ((this.itmUnt == null && other.itmUnt != null) || (this.itmUnt != null && !this.itmUnt.equals(other.itmUnt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ultimatetek.entity.ItemDtlPK[ itmCode=" + itmCode + ", itmUnt=" + itmUnt + " ]";
    }
    
}
