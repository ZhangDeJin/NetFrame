package com.zdj.demo.bean;

/**
 * <pre>
 *     author : dejinzhang
 *     time : 2021/12/08
 *     desc : 请求实体
 * </pre>
 */
public class RequestBean {
    private String provinceId;
    private String key;

    public RequestBean(String provinceId, String key) {
        this.provinceId = provinceId;
        this.key = key;
    }

    public RequestBean() {
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
