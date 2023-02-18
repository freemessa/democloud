package com.example.flowable.mapper;

import com.example.flowable.domain.DeployForm;
import com.example.flowable.domain.FlowForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 流程实例关联表单Mapper接口
 *
 * @author XuanXuan Xuan
 * @date 2021-03-30
 */
@Mapper
public interface DeployFormMapper
{
    /**
     * 查询流程实例关联表单
     *
     * @param id 流程实例关联表单ID
     * @return 流程实例关联表单
     */
    DeployForm selectSysDeployFormById(@Param("id") Long id);

    /**
     * 查询流程实例关联表单列表
     *
     * @param deployForm 流程实例关联表单
     * @return 流程实例关联表单集合
     */
    List<DeployForm> selectSysDeployFormList(DeployForm deployForm);

    /**
     * 新增流程实例关联表单
     *
     * @param deployForm 流程实例关联表单
     * @return 结果
     */
    int insertDeployForm(DeployForm deployForm);

    /**
     * 修改流程实例关联表单
     *
     * @param deployForm 流程实例关联表单
     * @return 结果
     */
    int updateDeployForm(DeployForm deployForm);

    /**
     * 删除流程实例关联表单
     *
     * @param id 流程实例关联表单ID
     * @return 结果
     */
    int deleteDeployFormById(@Param("id") Long id);

    /**
     * 批量删除流程实例关联表单
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteDeployFormByIds(@Param("ids") Long[] ids);

    /**
     * 查询流程挂着的表单
     * @param deployId 流程定义id
     * @return
     */
    FlowForm selectDeployFormByDeployId(@Param("deployId") String deployId);
}