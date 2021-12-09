package com.zdj.net_frame.interface_top;

import java.io.InputStream;

/**
 * <pre>
 *     author : dejinzhang
 *     time : 2021/03/09
 *     desc : 响应的顶层接口（注意，这个是给框架用的）
 * </pre>
 */
public interface IHttpListener {
    /**
     * 注意，之所以这里是使用InputStream，就是为了以后的扩展，网络上响应的东西，无法知道以后可能会是什么东西，
     * 但是我们拿到的一定会是一个流，所以我们定义成InputStream，后面想转换成什么格式，再转换成什么格式。
     * @param inputStream
     */
    void onSuccess(InputStream inputStream);
    void onFailure();
}
