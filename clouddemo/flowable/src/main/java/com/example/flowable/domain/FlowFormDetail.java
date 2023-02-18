package com.example.flowable.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 流程表单详情表 sky_flow_form_detail
 *
 * @author sky
 * @date 2023-01-17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlowFormDetail extends BaseEntity {
    @ApiModelProperty("表单明细主键")
    private String formDetailId;

    @ApiModelProperty("表单id")
    private long formId;

    @ApiModelProperty("字段名称")
    private String paraName;

    @ApiModelProperty("字段显示名")
    private String paraDisplayName;

    @ApiModelProperty("字段数据类型")
    private String paraType;

    @ApiModelProperty("字段大小")
    private Integer paraSize;

    @ApiModelProperty("字段排序")
    private Integer paraIndex;

    @ApiModelProperty("表分区")
    private Integer tableZone;

    @ApiModelProperty("行坐标")
    private Integer tableRow;

    @ApiModelProperty("列坐标")
    private Integer tableCol;

    @ApiModelProperty("文件数量 0:非文件 ； 1：单文件； 2：多文件")
    private Integer fileNum;
}
