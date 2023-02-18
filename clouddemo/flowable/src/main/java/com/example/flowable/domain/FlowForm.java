package com.example.flowable.domain;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 流程表单对象 _flow_form
 *
 * @author sky
 * @date 2023-01-17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlowForm extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("表单主键")
    private long formId;

    @ApiModelProperty("表单名称")
    private String formName;

    @ApiModelProperty("数据表名称")
    private String formTableName;

    @ApiModelProperty("version")
    private String version;

    @ApiModelProperty("部署ID")
    private String deploymentId;

    @ApiModelProperty("是否发布  0:否 ； 1：是")
    private boolean isDeploy;

    private List<FlowFormDetail> formDetailList;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("formId", getFormId())
                .append("formName", getFormName())
                .append("remark", getRemark())
                .append("version", getVersion())
                .append("isDeploy", isDeploy())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("createBy", getCreateBy())
                .append("updateBy", getUpdateBy())
                .append("remark", getRemark())
                .toString();
    }
}

