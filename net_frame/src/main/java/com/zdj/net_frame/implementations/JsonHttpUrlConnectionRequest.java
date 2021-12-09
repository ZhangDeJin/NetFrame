package com.zdj.net_frame.implementations;

import com.zdj.net_frame.interface_top.IHttpListener;
import com.zdj.net_frame.interface_top.IHttpRequest;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * <pre>
 *     author : dejinzhang
 *     time : 2021/03/09
 *     desc : 数据格式为Json的并采用HttpUrlConnection的请求
 * </pre>
 */
public class JsonHttpUrlConnectionRequest implements IHttpRequest {
    private String url;
    private byte[] params;
    private IHttpListener iHttpListener;

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setParams(byte[] params) {
        this.params = params;
    }

    @Override
    public void setListener(IHttpListener iHttpListener) {
        this.iHttpListener = iHttpListener;
    }


    /**
     * 网络操作在这里执行
     */
    @Override
    public void execute() {
        OutputStream outputStream = null;
        InputStream inputStream = null;
        BufferedOutputStream bos = null;
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(this.url);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(20000);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestProperty("Content-Type", "application/octet-stream");

            outputStream = httpURLConnection.getOutputStream();
            //我们可以使用缓冲流包装下，当然不用也行
            bos = new BufferedOutputStream(outputStream);
            bos.write(params);
            bos.flush();
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
                iHttpListener.onSuccess(inputStream);
            } else {
                throw new RuntimeException("请求失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("请求失败");
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
    }
}
