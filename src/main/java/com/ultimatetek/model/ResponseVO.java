package com.ultimatetek.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseVO {

    private ResultVO result;
    private DataVO data;

    public ResultVO getResult() {
        return result;
    }

    public void setResult(ResultVO result) {
        this.result = result;
    }

    public DataVO getData() {
        return data;
    }

    public void setData(DataVO data) {
        this.data = data;
    }

}
