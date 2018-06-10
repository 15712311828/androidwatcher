package com.androidwatcher.vo.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class HealthStatusParam {

    @NotNull
    String deviceName;
}
