package com.androidwatcher.vo.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class UserChangePasswordParam {

    @NotNull
    @Length(min=6,message = "密码不能少于六个字符")
    String password;
}
