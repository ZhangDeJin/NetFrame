package com.zdj.net_frame.task;

import com.zdj.net_frame.interface_top.IHttpListener;
import com.zdj.net_frame.interface_top.IHttpRequest;

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
public class HttpTask implements Runnable{
    private IHttpRequest iHttpRequest;
    private IHttpListener iHttpListener;

    public HttpTask(String url, String type, IHttpRequest iHttpRequest, IHttpListener iHttpListener) {
        this.iHttpRequest = iHttpRequest;
        this.iHttpListener = iHttpListener;
        this.iHttpRequest.setUrl(url);
        this.iHttpRequest.setType(type);
        this.iHttpRequest.setListener(this.iHttpListener);
    }

    @Override
    public void run() {
        iHttpRequest.execute();
    }
}
