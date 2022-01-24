package com.zdj.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.zdj.demo.bean.RequestBean;
import com.zdj.demo.bean.ResponseBean;
import com.zdj.demo.util.UiUtils;
import com.zdj.net_frame.IDataListener;
import com.zdj.net_frame.ZdjApi;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(v -> {
            /**
             * 拿公网地址测试下
             */
            String url = "https://v.juhe.cn/historyWeather/citys";
            RequestBean param = new RequestBean("1", "99998888zdjabcd");
            ZdjApi.sendJsonRequest(url, param, ResponseBean.class, new IDataListener<ResponseBean>() {
                @Override
                public void onSuccess(ResponseBean responseBean) {
                    Toast.makeText(MainActivity.this, responseBean.getResultCode() + "," + responseBean.getErrorCode(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure() {

                }
            });
        });

        ImageView iv = findViewById(R.id.iv);
        String url = "https://t7.baidu.com/it/u=2621658848,3952322712&fm=193&f=GIF";
        ZdjApi.loadImage(url, (int) UiUtils.dpToPx(this, 60), (int)UiUtils.dpToPx(this, 60), new IDataListener<Bitmap>() {
            @Override
            public void onSuccess(Bitmap bitmap) {
                iv.setImageBitmap(bitmap);
            }

            @Override
            public void onFailure() {

            }
        });
    }
}