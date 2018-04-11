package com.androidwatcher.vo.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class UserAddParam {

    @Length(min=3,message = "名字不能少于三个字符")
    @NotNull
    String name;

    @NotNull
    @Length(min=6,message = "密码不能少于六个字符")
    String password;
}
