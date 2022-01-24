package com.zdj.net_frame;

import com.zdj.net_frame.common.HttpType;
import com.zdj.net_frame.implementations.BitmapHttpListener;
import com.zdj.net_frame.implementations.JsonHttpListener;
import com.zdj.net_frame.implementations.HttpUrlConnectionRequest;
import com.zdj.net_frame.interface_top.IHttpListener;
import com.zdj.net_frame.interface_top.IHttpRequest;
import com.zdj.net_frame.task.EmptyDataHttpTask;
import com.zdj.net_frame.task.HttpTask;
import com.zdj.net_frame.task.JsonHttpTask;

/**
 * <pre>
 *     author : dejinzhang
 *     time : 2021/03/09
 *     desc : api测（即框架使用的入口）
 * </pre>
 */
public class ZdjApi {
    /**
     * 发送Json请求（内部网络库使用HttpUrlConnection）
     * T 输入的实体，即请求参数
     * M 输出的实体，即用户收到的结果
     */
    public static<T, M> void sendJsonRequest(String url, T requestParams, Class<M> response, IDataListener iDataListener) {
        IHttpRequest iHttpRequest = new HttpUrlConnectionRequest();
        IHttpListener iHttpListener = new JsonHttpListener<>(response, iDataListener);
        HttpTask httpTask = new JsonHttpTask(url, HttpType.POST_TYPE, requestParams, iHttpRequest, iHttpListener);
        ThreadManager.getInstance().addTask(httpTask);
    }

    /**
     * 如果我们要再支持发送xml的request，我们只需增加一个类似于sendJsonRequest的方法，并且扩展两个实现类，就可以了。
     * 同理如果我们要再支持发送image的request，也是如此。
     * 所以说，扩展性很好。
     */

    /**
     * 加载图片
     * @param width 显示图片的控件的宽度
     * @param height 显示图片的控件的高度
     */
    public static void loadImage(String url, int width, int height, IDataListener iDataListener) {
        IHttpRequest iHttpRequest = new HttpUrlConnectionRequest();
        IHttpListener iHttpListener = new BitmapHttpListener(width, height, iDataListener);
        HttpTask httpTask = new EmptyDataHttpTask(url, HttpType.GET_TYPE, iHttpRequest, iHttpListener);
        ThreadManager.getInstance().addTask(httpTask);
    }
}
