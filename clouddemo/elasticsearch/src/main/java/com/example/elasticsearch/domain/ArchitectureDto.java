package com.example.elasticsearch.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Document(indexName = "es_architecture")
public class ArchitectureDto {

    @ApiModelProperty(value = "id", required = true)
    @Id
    private String id;

    @ApiModelProperty(value = "建筑名称", required = true)
    @Field(type = FieldType.Text,analyzer = "ik-max-word")
    private String name;

    @ApiModelProperty(value = "所在省份", required = true)
    @Field(type = FieldType.Text)
    private String province;

    @ApiModelProperty(value = "所在城市", required = true)
    @Field(type = FieldType.Text)
    private String city;

    @ApiModelProperty(value = "所在区", required = true)
    @Field(type = FieldType.Text)
    private String area;

    @ApiModelProperty(value = "详细街道地址", required = true)
    @Field(type = FieldType.Text)
    private String address;

    @ApiModelProperty(value = "经纬度", required = true)
    private LocationPo location;

    @ApiModelProperty(value = "描述", required = true)
    @Field(type = FieldType.Text)
    private String description;

    @ApiModelProperty(value = "评分", required = true)
    @Field(type = FieldType.Double)
    private double score;

    @ApiModelProperty(value = "门票价格", required = true)
    @Field(type = FieldType.Double)
    private double price;
}
