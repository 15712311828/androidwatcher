package com.androidwatcher.vo.param;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class PageQueryParam {

    @Min(value = 1,message = "页长必须大于0")
    Integer pageNum;

    @Min(value = 1,message = "页码必须大于0")
    Integer pageSize;
}
