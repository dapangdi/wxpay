package com.itheima.controller;

import com.itheima.dtos.NotifyDto;
import com.itheima.service.NativePayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/native")
public class NativePayController {


    @Autowired
    private NativePayService nativePayService;

    @PostMapping("/notify")
    public Map<String,String> payNotify(@RequestBody NotifyDto dto){

        return nativePayService.payNotify(dto);
    }
}
