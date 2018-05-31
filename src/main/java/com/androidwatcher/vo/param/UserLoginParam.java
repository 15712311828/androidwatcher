package com.androidwatcher.vo.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class UserLoginParam {

    @NotNull
    Integer rsaKeyId;

    @NotNull
    String name;

    @NotNull
    String password;
}
