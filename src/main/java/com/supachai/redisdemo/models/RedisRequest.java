package com.supachai.redisdemo.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@Valid
public class RedisRequest {

    @NotEmpty
    private String key;
    @NotEmpty
    private String value;
}
