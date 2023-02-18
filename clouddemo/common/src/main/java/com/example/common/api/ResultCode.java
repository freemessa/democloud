package com.example.common.api;

/**
 * 统一返回状态码
 * #1000～1999 区间表示参数错误
 * #2000～2999 区间表示用户错误
 * #3000～3999 区间表示接口异常
 */

public enum ResultCode {
    /**
     * 成功状态码
     */
    AUTH_EXPIRE(401, "授权已到期，请联系管理员。"),

    SUCCESS(200, "操作成功。"),

    UNAUTHORIZED(401, "未登录或登录已经过期。"),
    FORBIDDEN(403, "没有相关权限，请联系管理员。"),
    NOTFOUND(404, "找不到资源，请重试！"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误!"),
    ERROR(1001, "操作失败，请重试！"),
    ILLEGAL_ARGUMENT(1002, "输入信息校验不通过，请重试！"),
    PASSWORD_LOGIN_FAIL(1003, "用户名或密码错误，请确认后重试！"),
    USERNAME_ERROR_FAIL(1004, "用户名不存在，请确认后重试！如需新增请联系胡博/116086、周琼/69766"),
    NOTFOUND_USER(1005, "用户信息获取失败，可尝试重新登录。"),
    DUPLICATION_ERROR(1006, "名称重复。"),
    ARGUMENT_NOT_VALID(1007, "参数不符合要求。"),
    DATA_NOT_IN_TABLE(1010, "没有找到数据"),
    FILE_EMPTY(1020, "文件内容为空。"),
    TEMPLATE_CORRESPOND(1021, "文件列和模板对应不上。"),
    FILE_NAME_ERROR(1022, "文件名错误。"),
    FILE_PROPERTY_ERROR(1023, "导入文件属性配置错误，请联系管理员。"),
    FILE_COLUMN_ERROR(1024, "文件字段有误，请核查。"),
    FILE_OTHER_ERROR(1025, "未识别错误，请联系管理员。"),

    FILE_EXCEL_PARSE_ERROR(1026, "Excel文件解析失败，请联系管理员。"),
    JSON_TRANSFER_ERROR(1027, "JSON数据信息转换失败。"),

    AUTH_CHECK_FAILURE(1101, "授权校验失败。"),

    ORGANIZATION_IS_EMPTY(2001, "用户部门信息为空。"),
    DATA_ALREADY_EXISTS(2002, "数据已存在"),

    USERS_PROJECT_EXISTS(3001, "部门或者子部门下有用户,项目,请先删除！"),

    CADRE_NOCHECK(4001, "有人员未完成盘点或审核！"),

    NOT_AUTHORITY_SEE_CADRE_INFO(4002, "暂无权限查看该信息！"),

    DELETE_POST_STAFF_ERROR(4003, "该员工正在盘点中，无法删除！"),
    PERFORMANCE_EXISTS(4004, "绩效考核已启动！"),
    PERFORMANCE_NOT_START(4005, "绩效考核未启动！"),
    PERFORMANCE_IS_GOING(4006, "绩效考核正在考核中，无法重置！"),
    WRONG_PERFOR_SCORE_CONFIG(4007, "绩效分数配置有误，请检查分数是否为空或是否连续。"),
    PERFORMANCE_HAS_COMPLETE(4011, "本次绩效考核已完成"),
    SERVICE_ORGAN_IS_EMPTY(4012, "用户服务部门为空。"),

    PERFOR_ALREADY_PUBLIC(4008, "绩效已公示！"),
    NOT_FIND_EMAILS(4009, "请配置收件人邮箱！"),
    NOT_FIND_RATER(4010, "未查询到考评人！"),
    STAFF_ALREADY_EXISTS(4013, "该员工已存在，请到页面修改"),
    PAY_WARN_CONFIG(4014, "请进行部门预警配置！"),
    STAFF_NOT_IN_POOL(4015, "未从资源池中查询到员工!"),
    AMONTS_NOT_NULL(4016, "到电子金额不能为空!"),
    REVENUEDATE_NOT_NULL(4017, "到电子流时间不能为空!"),
    FP_ONE_REVENUE_BALANCE(4018, "该PO(FP项目)已存在这个验收类型!"),
    EXPECTAMONTS_NOT_NULL(4019, "预计回款金额不能为空!"),

    COOP_STAFFID_ERROR(4020, "工号已经关联其他外包编号，请重新填写工号！"),

    SHIFT_EXIST_ERROR(4021, "同一公司下该班次名称已存在，请修改"),
    /**
     * 5000-5100 为mq resultcode
     */
    NOT_FOUND_EXCHANGE(5001, "找不到交换机。"),
    EXCHANGE_IS_EMPTY(5002, "交换机为空。"),
    MESSAGE_IS_EMPTY(5003, "消息内容为空。"),

    WECOM_ERROR(6001, "企业微信接口调用失败。"),

    WECOM_CORPID_IS_EMPTY(6002, "企业ID信息为空。"),

    WECOM_ACCESS_TOKEN_IS_EMPTY(6003, "企业微信接口调用凭证信息为空。"),

    WECOM_USERINFO_IS_EMPTY(6004, "企业微信用户信息为空。"),

    WECOM_USERID_IS_EMPTY(6005, "企业微信用户ID为空。"),

    WECOM_OPEN_SSO_IS_EMPTY(6006, "企业微信扫码登录信息与手机号绑定信息不符。"),

    WECOM_DEPARTMENT_IS_EMPTY(6007, "企业微信用户部门信息为空。"),

    WECOM_SEND_MESSAGE_ERROR(6008, "企业微信发送应用消息失败。"),
    BCENTER_GET_TOKEN_ERROR(6009, "营销门户获取用户Token信息失败。"),
    BCENTER_GET_USER_INFO_ERROR(6010, "营销门户获取用户信息失败。");

    private Integer status;
    private String message;

    ResultCode(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
