package com.zdj.net_frame.implementations;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.zdj.net_frame.IDataListener;
import com.zdj.net_frame.interface_top.IHttpListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * <pre>
 *     author : dejinzhang
 *     time : 2021/03/09
 *     desc : 数据格式为Json的Http响应监听器
 * </pre>
 */
public class JsonHttpListener<T> implements IHttpListener {
    /**
     * 用户用什么样的实体来接收数据
     */
    private Class<T> response;
    /**
     * 将最后的结果以对象的方式交给研发工程师
     */
    private IDataListener iDataListener;

    public JsonHttpListener(Class<T> response, IDataListener iDataListener) {
        this.response = response;
        this.iDataListener = iDataListener;
    }

    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void onSuccess(InputStream inputStream) {
        //inputStream中携带网络响应的数据
        String content = getContent(inputStream);
        Log.i("content", content);
        final T responseObject = JSON.parseObject(content, response);
        //线程切换
        handler.post(() -> iDataListener.onSuccess(responseObject));
    }

    @Override
    public void onFailure() {

    }

    /**
     * inputStream转为String类型
     * @param inputStream
     * @return
     */
    private String getContent(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString().replace("\n", "");
    }
}
