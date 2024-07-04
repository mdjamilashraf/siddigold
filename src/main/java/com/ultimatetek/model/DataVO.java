package com.ultimatetek.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import javax.faces.model.SelectItem;

import lombok.Data;

/**
 *
 * @author JunaidKhan
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataVO {

    private UserDetailsVO loginDTO;
    private List<OrderDetailsVO> orderDtlsList;
    private List<CustomerVO> customerList;
    private List<SelectItem> ItemNameList;
    private List<SalesOrderVO> salesOrderList;
    private UserDetailsVO userDetails;
    private List<OrderDetailsVO> customerOrdrDtls;
    private OrderDetailsVO custOrdrDtlsByRefNo;
    private OrderDetailsVO ordrDtlsByOrdrDtlNo;
    private DefaultMeltingVO meltingStamp;
    private String dueDate;
    private String refNo;

}
