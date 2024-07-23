package com.itheima.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NativePayParams {

    private String appid; // 应用id
    private String mchid;  // 商户id
    private String description; //商品描述
    private String out_trade_no; //订单号
    private String notify_url; // 支付成功回调通知地址
    private Amount amount; //订单金额信息
}