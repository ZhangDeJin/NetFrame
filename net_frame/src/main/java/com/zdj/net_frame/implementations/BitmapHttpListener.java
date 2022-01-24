package com.zdj.net_frame.implementations;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;

import com.zdj.net_frame.IDataListener;
import com.zdj.net_frame.interface_top.IHttpListener;
import com.zdj.net_frame.util.BitmapUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * <pre>
 *     author : dejinzhang
 *     time : 2022/01/24
 *     desc : 数据格式为Bitmap的Http响应监听器
 * </pre>
 */
public class BitmapHttpListener implements IHttpListener {
    /**
     * 显示图片的控件的宽/高
     */
    private int width, height;

    /**
     * 将最后的结果以对象的方式交给研发工程师
     */
    private IDataListener iDataListener;

    public BitmapHttpListener(int width, int height, IDataListener iDataListener) {
        this.width = width;
        this.height = height;
        this.iDataListener = iDataListener;
    }

    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void onSuccess(InputStream inputStream) {
        byte[] byteArray = getByteArray(inputStream);
        final Bitmap bitmap = BitmapUtils.decodeSampledBitmapFromByteArray(byteArray, width, height);
        handler.post(() -> iDataListener.onSuccess(bitmap));
    }

    @Override
    public void onFailure() {

    }

    /**
     * inputStream转为ByteArray类型
     * @param inputStream
     * @return
     */
    private byte[] getByteArray(InputStream inputStream) {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
        byte[] buffer = new byte[1024 * 8];
        int len = 0;
        byte[] byteArray = null;
        try {
            while ((len = bufferedInputStream.read(buffer)) > 0) {
                bufferedOutputStream.write(buffer, 0, len);
            }
            byteArray = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                byteArrayOutputStream.close();
                bufferedInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return byteArray;
    }
}
