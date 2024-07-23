package com.hxc.config;

import com.hxc.core.WxPayTemplate;
import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.AutoUpdateCertificatesVerifier;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.PrivateKey;

@EnableConfigurationProperties(WxPayProperties.class)
@Configuration
public class WxAutoConfiguration {

    @Bean
    public CloseableHttpClient httpClient(WxPayProperties wxPayProperties) throws IOException {
        // 加载商户私钥（privateKey：私钥字符串）
        PrivateKey merchantPrivateKey = PemUtil
                .loadPrivateKey(new ByteArrayInputStream(wxPayProperties.getPrivateKey().getBytes("utf-8")));

        // 加载平台证书（mchId：商户号,mchSerialNo：商户证书序列号,apiV3Key：V3密钥）
        AutoUpdateCertificatesVerifier verifier = new AutoUpdateCertificatesVerifier(
                new WechatPay2Credentials(wxPayProperties.getMchId(), new PrivateKeySigner(wxPayProperties.getMchSerialNo(), merchantPrivateKey)),wxPayProperties.getApiV3Key().getBytes("utf-8"));

        // 初始化httpClient
        return WechatPayHttpClientBuilder.create()
                .withMerchant(wxPayProperties.getMchId(), wxPayProperties.getMchSerialNo(), merchantPrivateKey)
                .withValidator(new WechatPay2Validator(verifier)).build();
    }
    @Bean
    public WxPayTemplate wxPayTemplate(WxPayProperties wxPayProperties, CloseableHttpClient httpClient){
        return new WxPayTemplate(wxPayProperties,httpClient);
    }
}
