package com.itheima.dtos;

import lombok.Data;

@Data
public class ResourceDto {

    private String algorithm;
    private String ciphertext;
    private String associated_data;
    private String original_type;
    private String nonce;

}