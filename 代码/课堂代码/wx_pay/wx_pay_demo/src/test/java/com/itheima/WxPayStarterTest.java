package com.itheima;

import com.hxc.core.WxPayTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WxPayStarterTest {


    @Autowired
    private WxPayTemplate wxPayTemplate;

    @Test
    public void testNativePay() throws Exception{
        String code_url = wxPayTemplate.CreateOrder(2, "javaEE企业级开发", "SDFASDFSD23ADSFASD");
    }
}
