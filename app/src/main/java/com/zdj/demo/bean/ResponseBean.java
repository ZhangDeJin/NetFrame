package com.zdj.demo.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * <pre>
 *     author : dejinzhang
 *     time : 2021/12/08
 *     desc : 响应实体
 * </pre>
 */
public class ResponseBean {
    private int errorCode;
    private String resultCode;

    public int getErrorCode() {
        return errorCode;
    }

    @JSONField(name = "error_code")
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
}
