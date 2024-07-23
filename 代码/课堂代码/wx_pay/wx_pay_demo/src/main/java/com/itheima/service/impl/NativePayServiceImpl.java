package com.itheima.service.impl;

import com.alibaba.fastjson.JSON;
import com.itheima.dtos.NotifyDto;
import com.itheima.service.NativePayService;
import com.wechat.pay.contrib.apache.httpclient.util.AesUtil;
import org.springframework.stereotype.Service;

import java.security.GeneralSecurityException;
import java.util.Map;

@Service
public class NativePayServiceImpl implements NativePayService {
    private String apiV3Key = "CZBK51236435wxpay435434323FFDuv3"; //V3密钥
    @Override
    public Map<String, String> payNotify(NotifyDto dto) {
        Map<String,String > res = null;
        try {
            //解密微信传递过来的参数
            String json = new AesUtil(apiV3Key.getBytes()).decryptToString(dto.getResource().getAssociated_data().getBytes(),
                    dto.getResource().getNonce().getBytes(),
                    dto.getResource().getCiphertext());

            String outTradeNo = JSON.parseObject(json, Map.class).get("out_trade_no").toString();

            System.out.println("-------支付成功的订单号："+outTradeNo);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            res.put("code","FAIL");
            res.put("message","失败");
        }

        return res;
    }
}
