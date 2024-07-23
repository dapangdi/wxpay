package com.itheima.dtos;

import lombok.Data;

@Data
public class NotifyDto {

    private String id;
    private String create_time;
    private String event_type;
    private String resource_type;
    private ResourceDto resource;
    private String summary;
}