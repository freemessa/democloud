package com.example.flowable.mapper;

import com.example.flowable.domain.FlowForm;
import com.example.flowable.domain.FlowFormDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 流程表单Mapper接口
 *
 * @author sky
 * @date 2023-01-16
 */
@Mapper
public interface FlowFormMapper
{
    /**
     * 查询流程表单
     *
     * @param formId 流程表单ID
     * @return 流程表单
     */
   FlowForm selectFlowFormById(@Param("formId") Long formId);

    /**
     * 通过流程实例id查询流程表单
     *
     * @param procInsId 流程实例ID
     * @return 流程表单
     */
    FlowForm selectFlowFormByFlowId(@Param("procInsId") String procInsId);

    /**
     * 查询流程表单明细
     *
     * @param formId 流程表单ID
     * @return 流程表单
     */
    List<FlowFormDetail> selectFlowFormDetailsById(@Param("formId") Long formId);

    /**
     * 查询流程表单列表
     *
     * @param param 流程表单
     * @return 流程表单集合
     */
    //List<FlowForm> selectFlowFormList(FormQueryVO param);

    /**
     * 新增流程表单
     *
     * @param flowForm 流程表单
     * @return 结果
     */
    int insertFlowForm(FlowForm flowForm);

    /**
     * 新增流程表单字段
     *
     * @param flowFormDetail 流程表单字段
     * @return 结果
     */
    int insertFlowFormDetail(FlowFormDetail flowFormDetail);

    /**
     * 修改流程表单
     *
     * @paramflowForm 流程表单
     * @return 结果
     */
    int updateFlowForm(FlowForm flowForm);

    /**
     * 修改流程表单字段
     *
     * @param flowFormDetail 流程表单字段
     * @return 结果
     */
    int updateFlowFormDetail(FlowFormDetail flowFormDetail);

    /**
     * 删除流程表单
     *
     * @param formId 流程表单ID
     * @return 结果
     */
    int deleteFlowFormById(@Param("formId") Long formId);

    /**
     * 删除流程表单字段
     *
     * @param formDetailId 流程表单字段ID
     * @return 结果
     */
    int deleteFlowFormById(@Param("formDetailId") String formDetailId);

    /**
     * 批量删除流程表单
     *
     * @param formIds 需要删除的数据ID
     * @return 结果
     */
    int deleteFlowFormByIds(@Param("formIds") Long[] formIds);

    /**
     * 批量删除流程表单字段
     *
     * @param formDetailIds 需要删除的数据ID
     * @return 结果
     */
    int deleteFlowFormDetailByIds(@Param("formIds") String[] formDetailIds);


    /**
     * 删除流程表单全部字段
     *
     * @param formId 需要删除的数据表单ID
     * @return 结果
     */
    int deleteFlowFormDetailByFormId(@Param("formId") Long formId);

    /**
     * 根据formIds查询关联的 运行中的或历史的流程实例
     */
    List<Long> selectFormsConnectProcess(@Param("formIds") String[] formIds);
}
