package com.hxc.core;

import com.alibaba.fastjson.JSON;
import com.hxc.config.WxPayProperties;
import com.hxc.dtos.Amount;
import com.hxc.dtos.NativePayParams;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;


public class WxPayTemplate {


    private WxPayProperties wxPayProperties;

    private CloseableHttpClient httpClient;

    public WxPayTemplate(WxPayProperties wxPayProperties,CloseableHttpClient httpClient) {
        this.wxPayProperties = wxPayProperties;
        this.httpClient = httpClient;
    }


    public String CreateOrder(Integer total,String description,String outTradeNo) throws Exception{

        String code_url = "";
        HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/v3/pay/transactions/native");
        // 请求body参数

        Amount amount = Amount.builder()
                .currency("CNY")
                .total(total)
                .build();
        NativePayParams payParams = NativePayParams.builder()
                .appid(wxPayProperties.getAppId())
                .mchid(wxPayProperties.getMchId())
                .description(description)
                .out_trade_no(outTradeNo)
                .notify_url("https://36d5634033.vicp.fun/native/notify")  //设置支付成功后的回调地址
                .amount(amount)
                .build();


        String reqdata = JSON.toJSONString(payParams);
        StringEntity entity = new StringEntity(reqdata,"utf-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");

        //完成签名并执行请求
        CloseableHttpResponse response = httpClient.execute(httpPost);

        try {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) { //处理成功
                System.out.println("success,return body = " + EntityUtils.toString(response.getEntity()));
                code_url = JSON.parseObject(EntityUtils.toString(response.getEntity()),Map.class).get("code_url").toString();
            } else if (statusCode == 204) { //处理成功，无返回Body
                System.out.println("success");
            } else {
                System.out.println("failed,resp code = " + statusCode+ ",return body = " + EntityUtils.toString(response.getEntity()));
                throw new IOException("request failed");
            }
        } finally {
            response.close();
        }
        return code_url;
    }
}
