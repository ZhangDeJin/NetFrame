package com.zdj.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.zdj.demo.bean.RequestBean;
import com.zdj.demo.bean.ResponseBean;
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
    }
}