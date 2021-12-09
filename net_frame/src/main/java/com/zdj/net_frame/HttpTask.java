package com.zdj.net_frame;

import com.alibaba.fastjson.JSON;
import com.zdj.net_frame.interface_top.IHttpListener;
import com.zdj.net_frame.interface_top.IHttpRequest;

import java.io.UnsupportedEncodingException;

/**
 * <pre>
 *     author : dejinzhang
 *     time : 2021/03/09
 *     desc : Http任务
 *           包括：
 *           1、设置URL
 *           2、设置请求参数
 *           3、发送请求
 *           4、处理响应
 *           5、回调调用者
 * </pre>
 */
public class HttpTask<T> implements Runnable{
    private IHttpRequest iHttpRequest;
    private IHttpListener iHttpListener;

    public HttpTask(String url, T requestData, IHttpRequest iHttpRequest, IHttpListener iHttpListener) {
        this.iHttpRequest = iHttpRequest;
        this.iHttpListener = iHttpListener;
        this.iHttpRequest.setUrl(url);
        this.iHttpRequest.setListener(this.iHttpListener);

        /**
         * 这里目前我们暂时只支持json，以json为例。
         * 如果需要支持json、xml等等，我们可以写一个模版方法给子类去使用即可。
         */
        if (requestData != null) {
            String dataStr = JSON.toJSONString(requestData);
            try {
                this.iHttpRequest.setParams(dataStr.getBytes("utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        iHttpRequest.execute();
    }
}
