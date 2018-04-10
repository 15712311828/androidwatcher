package com.androidwatcher.vo.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class LoginParam {

    @Length(min=3,message = "名字不能少于三个字符")
    String name;
}
