package com.example.flowable.service;

import com.alibaba.fastjson.JSONObject;

import com.example.common.api.ResultCode;
import com.example.common.api.ResultEntity;
import com.example.common.utils.ComException;
import com.example.common.utils.DateUtil;
import com.example.flowable.domain.FlowForm;
import com.example.flowable.domain.FlowFormDetail;
import com.example.flowable.mapper.DeployFormMapper;
import com.example.flowable.mapper.FlowFormMapper;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.*;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Service
public class FormDataServiceImpl  {
    private final static String TABLE_COL_NAME_PROC_DEF_ID = "procDefId";
    private final static String TABLE_COL_NAME_FLOW_ID = "flowId";
    private final static String TABLE_COL_NAME_FLOW_NODE_ID = "flowNodeId";
    private final static String TABLE_COL_NAME_UPDATE_BY = "updateBy";
    private final static String TABLE_COL_NAME_UPDATE_TIME = "updateTime";
    private final static String TABLE_NAME_FORM_DATA_PREFIX = "sky_form_data";
    private final static String PARAM_NAME_CURRENT_USER = "currentUser";

    @Value("${spring.datasource.url}")
    String url;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Resource
    FlowFormMapper flowFormMapper;

    @Resource
    DeployFormMapper deployFormMapper;

    /**
     * 根据表单id创建数据存储表
     */
    public boolean createFormDataTableById(Long formId) {
        // 获取流程表单详情
        FlowForm flowForm = flowFormMapper.selectFlowFormById(formId);
        List<FlowFormDetail> flowFormDetailList = flowFormMapper.selectFlowFormDetailsById(formId);
        String tableName = String.format("%s_%s_%s", TABLE_NAME_FORM_DATA_PREFIX, formId, flowForm.getVersion());
        if(StringUtils.isNotEmpty(flowForm.getFormTableName())){
            tableName =flowForm.getFormTableName();
        }
        StringBuffer sbf = new StringBuffer("");
        sbf.append(String.format("create table if not exists %s(id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,", tableName));
        sbf.append("procdefid VARCHAR(64)NOT NULL COMMENT '流程定义id',");
        sbf.append("flowid VARCHAR(64)NOT NULL COMMENT '流程实例id',");
        sbf.append("flownodeid VARCHAR(64)NOT NULL COMMENT '流程节点id',");
        sbf.append("updateby VARCHAR(32)NOT NULL COMMENT '更新人id',");
        sbf.append("updatetime DATETIME COMMENT '更新时间',"); //更新时间
        int cnt = 0;
        for (FlowFormDetail flowFormDetail : flowFormDetailList) {
            cnt++;
            String param;
            switch (flowFormDetail.getParaSize()) {
                case 0: //text blob
                    param = String.format("%s %s COMMENT '%s',", flowFormDetail.getParaName().toLowerCase(Locale.ROOT),
                            flowFormDetail.getParaType(), flowFormDetail.getParaDisplayName());
                    break;
                default:
                    param = String.format("%s %s(%s) COMMENT '%s',", flowFormDetail.getParaName().toLowerCase(Locale.ROOT),
                            flowFormDetail.getParaType(),
                            flowFormDetail.getParaSize(),
                            flowFormDetail.getParaDisplayName());
                    break;
            }
            if (cnt == flowFormDetailList.size()) {
                param = param.replace(",", "");
            }
            sbf.append(param);
        }
        sbf.append(")");
        executeSql(sbf.toString());
        sbf.delete(0, sbf.length());
        return true;
    }

    /**
     * 保存表单数据. 空流程id为预存数据
     */
    public ResultEntity saveFormDataTable(JSONObject dataJson) {
        // example : {"procDefId":"oneTaskProcess:1:0b93089...","flowId":"1","flowNodeId":"1","formId":1,"currentUser":"100313","para1":"3","para2":35,"para3":"address 2","para4":"resid2"}
        String procDefId = dataJson.getString(TABLE_COL_NAME_PROC_DEF_ID);
        String flowId = dataJson.getString(TABLE_COL_NAME_FLOW_ID);
        String currentUser = dataJson.getString(PARAM_NAME_CURRENT_USER);
        if (StringUtils.isEmpty(currentUser)) {
            ResultEntity.failure(ResultCode.ERROR.getStatus(), "用户不能为空");
        }
        String flowNodeId = dataJson.getString(TABLE_COL_NAME_FLOW_NODE_ID);

        FlowForm flowForm = flowFormMapper.selectFlowFormByFlowId(flowId);
        if (Objects.isNull(flowForm)) {
            return ResultEntity.failure(ResultCode.ERROR.getStatus(), "流程表单不存在");
        }
        long formId = flowForm.getFormId();
        String tableName = String.format("%s_%s_%s", TABLE_NAME_FORM_DATA_PREFIX,
                formId, flowForm.getVersion());
        if(StringUtils.isNotEmpty(flowForm.getFormTableName())){
            tableName =flowForm.getFormTableName();
        }
        StringBuffer sbfParamName = new StringBuffer("");
        StringBuffer sbfParamValue = new StringBuffer("");
        List<FlowFormDetail> flowFormDetailList = flowFormMapper.selectFlowFormDetailsById(formId);
        for (FlowFormDetail flowFormDetail : flowFormDetailList) {
            String paramName = flowFormDetail.getParaName();
            if (dataJson.containsKey(paramName)) {
                sbfParamName.append(paramName + ',');
                String paramType = flowFormDetail.getParaType();
                switch (paramType.toLowerCase(Locale.ROOT)) {
                    case "bool":
                    case "decimal":
                    case "int":
                        sbfParamValue.append(dataJson.getString(paramName) + ",");
                        break;
                    default:
                        sbfParamValue.append("'" + dataJson.getString(paramName) + "',");
                        break;
                }
            }
        }
        sbfParamName.append(String.format("%s,%s,%s,%s,%s", TABLE_COL_NAME_PROC_DEF_ID,
                TABLE_COL_NAME_FLOW_ID, TABLE_COL_NAME_FLOW_NODE_ID,
                TABLE_COL_NAME_UPDATE_BY, TABLE_COL_NAME_UPDATE_TIME));
        sbfParamValue.append(String.format("'%s','%s','%s','%s','%s'",
                procDefId, flowId, flowNodeId, currentUser, DateUtil.getTime()));
        String sql = String.format("insert into %s (%s) values (%s)", tableName, sbfParamName, sbfParamValue);
        boolean bRes = true;
        try {
            executeSql(sql);
        } catch (ComException ex) {
            bRes = false;
        }
        sbfParamName.delete(0, sbfParamName.length());
        sbfParamValue.delete(0, sbfParamValue.length());
        return bRes ? ResultEntity.success(): ResultEntity.failure();///
    }

    private void executeSql(String sql) {
        Connection con = null;
        try {
            // 获得数据库连接
            con = getConnection();
            Statement sm = null;
            try {
                sm = con.createStatement();
                sm.execute(sql);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ComException(ResultCode.ERROR.getStatus(), "数据库执行失败");
            } finally {
                closeStatement(sm);
            }
        } finally {
            // 关闭数据库连接
            closeConnection(con);
        }
    }

    /**
     * 获取流程表单的预提交数据
     *
     * @param procDefId 流程id
     * @param userId 用户id
     */
    public JSONObject getPreFormDataByProcDefId(String procDefId, String userId) {
        JSONObject jsonObject = new JSONObject();
        // 通过定义id获取关联表单
        FlowForm flowForm = deployFormMapper.selectDeployFormByDeployId(procDefId);
        if (Objects.isNull(flowForm)) {
            throw new ComException(ResultCode.ERROR.getStatus(), "流程表单不存在");
        }
        Long formId = flowForm.getFormId();
        List<FlowFormDetail> flowFormDetailList = flowFormMapper.selectFlowFormDetailsById(formId);
        String tableName = String.format("%s_%s_%s", TABLE_NAME_FORM_DATA_PREFIX,
                formId, flowForm.getVersion());
        if(StringUtils.isNotEmpty(flowForm.getFormTableName())){
            tableName =flowForm.getFormTableName();
        }
        String sql = String.format("SELECT * FROM %s WHERE %s = '' AND %s = '%s' and %s = '%s' ORDER BY %s DESC LIMIT 1",
                tableName, TABLE_COL_NAME_FLOW_ID,
                TABLE_COL_NAME_PROC_DEF_ID, procDefId,
                TABLE_COL_NAME_UPDATE_BY, userId, TABLE_COL_NAME_UPDATE_TIME);
        return getJsonObject(jsonObject, flowFormDetailList, sql);

    }

    /**
     * 获取最新流程表单的数据
     *
     * @param procInsId 流程id
     */
    public JSONObject getLastFormDataTableByFlowId(String procInsId) {
        JSONObject jsonObject = new JSONObject();
        // 通过流程id获取表单id
        FlowForm flowForm = flowFormMapper.selectFlowFormByFlowId(procInsId);
        if (Objects.isNull(flowForm)) {
            return new JSONObject();
        }
        Long formId = flowForm.getFormId();
        List<FlowFormDetail> flowFormDetailList = flowFormMapper.selectFlowFormDetailsById(formId);
        String tableName = String.format("%s_%s_%s", TABLE_NAME_FORM_DATA_PREFIX,
                formId, flowForm.getVersion());
        String sql = String.format("SELECT * FROM %s WHERE %s = '%s' ORDER BY %s DESC LIMIT 1",
                tableName, TABLE_COL_NAME_FLOW_ID, procInsId, TABLE_COL_NAME_UPDATE_TIME);
        return getJsonObject(jsonObject, flowFormDetailList, sql);
    }

    private JSONObject getJsonObject(JSONObject jsonObject, List<FlowFormDetail> flowFormDetailList, String sql) {
        try {
            Connection con = getConnection();
            Statement sm = con.createStatement();
            ResultSet rs = sm.executeQuery(sql);// 首先获得表的所有数据

            if(rs.next()) {
                jsonObject.put("id", rs.getLong("id"));
                jsonObject.put(TABLE_COL_NAME_PROC_DEF_ID, rs.getString(TABLE_COL_NAME_PROC_DEF_ID.toLowerCase(Locale.ROOT)));
                jsonObject.put(TABLE_COL_NAME_FLOW_ID, rs.getString(TABLE_COL_NAME_FLOW_ID.toLowerCase(Locale.ROOT)));
                jsonObject.put(TABLE_COL_NAME_FLOW_NODE_ID, rs.getString(TABLE_COL_NAME_FLOW_NODE_ID.toLowerCase(Locale.ROOT)));
                jsonObject.put(TABLE_COL_NAME_UPDATE_BY, rs.getString(TABLE_COL_NAME_UPDATE_BY.toLowerCase(Locale.ROOT)));
                jsonObject.put(TABLE_COL_NAME_UPDATE_TIME, rs.getString(TABLE_COL_NAME_UPDATE_TIME.toLowerCase(Locale.ROOT)));
                for (FlowFormDetail flowFormDetail : flowFormDetailList) {
                    jsonObject.put(flowFormDetail.getParaName(), getRsValue(rs, flowFormDetail));
                }
            }
            rs.close();
            closeStatement(sm);
            closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    private Object getRsValue(ResultSet rs, FlowFormDetail flowFormDetail) throws SQLException{
        switch (flowFormDetail.getParaType().toLowerCase(Locale.ROOT)) {
            case "int":
                return rs.getInt(flowFormDetail.getParaName().toLowerCase(Locale.ROOT));
            default:
                return rs.getString(flowFormDetail.getParaName().toLowerCase(Locale.ROOT));
        }
    }



    /**
     * 更新流程表单的数据
     */
    public boolean updateFlowFormData(JSONObject dataJson) {
        return true;
    }

    public Connection getConnection() {
        Connection con = null;
        try {
            Class.forName(driverClassName);
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ComException(ResultCode.ERROR.getStatus(), "数据库连接失败");
        }
        return con;
    }

    /**
     * 关闭Statement
     *
     * @param sm
     */
    public static void closeStatement(Statement sm) {
        if (sm != null) {
            try {
                sm.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 关闭sql 连接
     *
     * @param con
     */
    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}


