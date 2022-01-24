package com.zdj.net_frame.implementations;

import com.zdj.net_frame.common.HttpType;
import com.zdj.net_frame.interface_top.IHttpListener;
import com.zdj.net_frame.interface_top.IHttpRequest;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * <pre>
 *     author : dejinzhang
 *     time : 2022/01/10
 *     desc : 并采用OkHttp的请求
 * </pre>
 */
public class OkHttpRequest implements IHttpRequest {
    private String url;
    private String type;
    private byte[] params;
    private IHttpListener iHttpListener;

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void setParams(byte[] params) {
        this.params = params;
    }

    @Override
    public void setListener(IHttpListener iHttpListener) {
        this.iHttpListener = iHttpListener;
    }

    @Override
    public void execute() {
        OkHttpClient client = new OkHttpClient();
        Request request = null;
        if (!HttpType.GET_TYPE.equals(type)) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), params);
            request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
        } else {
            request = new Request.Builder()
                    .url(url)
                    .build();
        }
        Call call = client.newCall(request);
        try {
            Response response  = call.execute();
            if (response != null && response.isSuccessful()) {
                iHttpListener.onSuccess(response.body().byteStream());
            } else {
                throw new RuntimeException("请求失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("请求失败");
        }
    }
}
