package com.example.elasticsearch.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationPo {


    @ApiModelProperty(value = "经度", required = true)
    private double lon;

    @ApiModelProperty(value = "纬度", required = true)
    private double lat;
}
