package com.zdj.net_frame;

/**
 * <pre>
 *     author : dejinzhang
 *     time : 2021/03/09
 *     desc : 数据接口（注意，这个是给研发工程师用的。即通过框架获取的数据的出口。）
 * </pre>
 */
public interface IDataListener<T> {
    void onSuccess(T t);
    void onFailure();
}
