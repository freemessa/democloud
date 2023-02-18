package com.example.flowable.domain;

//import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 流程实例关联表单对象 sys_instance_form
 *
 * @author XuanXuan Xuan
 * @date 2021-03-30
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeployForm extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 表单主键 */
    //@Excel(name = "表单主键")
    private Long formId;

    /** 流程定义主键 */
    //@Excel(name = "流程定义主键")
    private String deployId;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("formId", getFormId())
                .append("deployId", getDeployId())
                .toString();
    }
}