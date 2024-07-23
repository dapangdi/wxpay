package com.hxc.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Amount {
    private Integer total;
    private String currency;
}