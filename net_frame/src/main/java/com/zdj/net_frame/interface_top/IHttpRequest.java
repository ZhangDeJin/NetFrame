package com.zdj.net_frame.interface_top;

/**
 * <pre>
 *     author : dejinzhang
 *     time : 2021/03/09
 *     desc : 请求的顶层接口
 * </pre>
 */
public interface IHttpRequest {
    void setUrl(String url);
    void setType(String type);
    void setParams(byte[] params);
    void setListener(IHttpListener iHttpListener);
    void execute();
}
