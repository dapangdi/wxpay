package com.hxc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "wxpay")
public class WxPayProperties {

    private String mchId = "xxx"; //商户号
    //private String appId = "wx6592a2db3f85ed25"; //应用号
    private String appId = "xxxx"; //应用号
    private String privateKey = "xxxxn"; //私钥字符串
    private String mchSerialNo = "xxxxx"; //商户证书序列号
    private String apiV3Key = "xxxx"; //V3密钥


}
