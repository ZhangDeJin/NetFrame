package com.zdj.net_frame.task;

import com.zdj.net_frame.interface_top.IHttpListener;
import com.zdj.net_frame.interface_top.IHttpRequest;

/**
 * <pre>
 *     author : dejinzhang
 *     time : 2022/01/24
 *     desc : 空传输数据的HttpTask的实现子类
 * </pre>
 */
public class EmptyDataHttpTask extends HttpTask{
    public EmptyDataHttpTask(String url, String type, IHttpRequest iHttpRequest, IHttpListener iHttpListener) {
        super(url, type, iHttpRequest, iHttpListener);
    }
}
