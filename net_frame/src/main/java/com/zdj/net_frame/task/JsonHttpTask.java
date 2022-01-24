package com.zdj.net_frame.task;

import com.alibaba.fastjson.JSON;
import com.zdj.net_frame.interface_top.IHttpListener;
import com.zdj.net_frame.interface_top.IHttpRequest;

import java.io.UnsupportedEncodingException;

/**
 * <pre>
 *     author : dejinzhang
 *     time : 2022/01/24
 *     desc : 针对数据格式为Json的HttpTask子类
 * </pre>
 */
public class JsonHttpTask<T> extends HttpTask{
    public JsonHttpTask(String url, String type, T requestData, IHttpRequest iHttpRequest, IHttpListener iHttpListener) {
        super(url, type, iHttpRequest, iHttpListener);
        if (requestData != null) {
            String dataStr = JSON.toJSONString(requestData);
            try {
                iHttpRequest.setParams(dataStr.getBytes("utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }
}
