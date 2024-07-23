package com.itheima.service;

import com.itheima.dtos.NotifyDto;

import java.util.Map;

public interface NativePayService {
    Map<String, String> payNotify(NotifyDto dto);
}
