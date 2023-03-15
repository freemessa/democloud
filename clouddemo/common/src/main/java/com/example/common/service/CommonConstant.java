package com.example.common.service;

public @interface CommonConstant {
    /**
     * 正常状态
     */
    public static final Integer STATUS_NORMAL = 0;

    /**
     * 禁用状态
     */
    public static final Integer STATUS_DISABLE = -1;

    /**
     * 删除标志
     */
    public static final Integer DEL_FLAG_DELETED = 1;

    /**
     * 未删除
     */
    public static final Integer DEL_FLAG_UNDELETED = 0;

    /**
     * 系统日志类型： 登录
     */
    public static final int LOG_TYPE_1 = 1;

    /**
     * 系统日志类型： 操作
     */
    public static final int LOG_TYPE_2 = 2;

    /**
     * 系统日志类型： 访问
     */
    public static final int LOG_TYPE_3 = 3;
    /**
     * 系统日志类型： 异常
     */
    public static final int LOG_TYPE_4 = 4;

    /**
     * 系统日志类型： 定时任务
     */
    public static final int LOG_TYPE_5 = 5;

    /**
     * 系统日志类型： 用户管理
     */
    public static final int LOG_TYPE_6 = 6;

    /**
     * 系统登陆日志：正常账户密码登录
     */
    public static final int OPERATE_TYPE_LT1_1 = 1;

    /**
     * 系统登陆日志：二维码登陆
     */
    public static final int OPERATE_TYPE_LT1_2 = 2;

    /**
     * 系统登陆日志：单点登陆
     */
    public static final int OPERATE_TYPE_LT1_3 = 3;

    /**
     * 系统登陆日志：登出
     */
    public static final int OPERATE_TYPE_LT1_4 = 4;

    /**
     * 系统登陆日志：模拟登陆
     */
    public static final int OPERATE_TYPE_LT1_5 = 5;

    /**
     * 操作日志类型： 查询
     */
    public static final int OPERATE_TYPE_LT2_1 = 1;

    /**
     * 操作日志类型： 添加
     */
    public static final int OPERATE_TYPE_LT2_2 = 2;

    /**
     * 操作日志类型： 更新
     */
    public static final int OPERATE_TYPE_LT2_3 = 3;

    /**
     * 操作日志类型： 删除
     */
    public static final int OPERATE_TYPE_LT2_4 = 4;

    /**
     * 操作日志类型： 导入
     */
    public static final int OPERATE_TYPE_LT2_5 = 5;

    /**
     * 操作日志类型： 导出
     */
    public static final int OPERATE_TYPE_LT2_6 = 6;

    /**
     * 访问日志类型： 进入
     */
    public static final int OPERATE_TYPE_LT3_1 = 1;
    /**
     * 异常日志类型： 普通操作即代码错误
     */
    public static final int OPERATE_TYPE_LT4_1 = 1;
    /**
     * 异常日志类型： 非法操作即越权操作
     */
    public static final int OPERATE_TYPE_LT4_2 = 2;


    public static final String CLIENT_TYPE_PC="0";
    public static final String CLIENT_TYPE_MOBILE="1";


    /** {@code 500 Server Error} (HTTP/1.0 - RFC 1945) */
    public static final Integer SC_INTERNAL_SERVER_ERROR_500 = 500;
    /** {@code 200 OK} (HTTP/1.0 - RFC 1945) */
    public static final Integer SC_OK_200 = 200;

    /**访问权限认证未通过 510*/
    public static final Integer SC_JEECG_NO_AUTHZ=510;

    /** 登录用户Shiro权限缓存KEY前缀 */
    public static String PREFIX_USER_SHIRO_CACHE  = "shiro:cache:org.jeecg.modules.shiro.authc.ShiroRealm.authorizationCache:";
    /** 登录用户Token令牌缓存KEY前缀 */
    public static final String PREFIX_USER_TOKEN  = "prefix_user_token_";
    /** Token缓存时间：3600秒即一小时 */
    public static final int  TOKEN_EXPIRE_TIME  = 3600;


    /**
     *  0：一级菜单
     */
    public static final Integer MENU_TYPE_0  = 0;
    /**
     *  1：子菜单
     */
    public static final Integer MENU_TYPE_1  = 1;
    /**
     *  2：按钮权限
     */
    public static final Integer MENU_TYPE_2  = 2;

    /**通告对象类型（USER:指定用户，ALL:全体用户）*/
    public static final String MSG_TYPE_UESR  = "USER";
    public static final String MSG_TYPE_ALL  = "ALL";

    /**发布状态（0未发布，1已发布，2已撤销）*/
    public static final String NO_SEND  = "0";
    public static final String HAS_SEND  = "1";
    public static final String HAS_CANCLE  = "2";

    /**阅读状态（0未读，1已读）*/
    public static final String HAS_READ_FLAG  = "1";
    public static final String NO_READ_FLAG  = "0";

    /**优先级（L低，M中，H高）*/
    public static final String PRIORITY_L  = "L";
    public static final String PRIORITY_M  = "M";
    public static final String PRIORITY_H  = "H";

    /**
     * 短信模板方式  0 .登录模板、1.注册模板、2.忘记密码模板
     */
    public static final String SMS_TPL_TYPE_0  = "0";
    public static final String SMS_TPL_TYPE_1  = "1";
    public static final String SMS_TPL_TYPE_2  = "2";

    /**
     * 状态(0无效1有效)
     */
    public static final String STATUS_0 = "0";
    public static final String STATUS_1 = "1";

    /**
     * 同步工作流引擎1同步0不同步
     */
    public static final String ACT_SYNC_0 = "0";
    public static final String ACT_SYNC_1 = "1";

    /**
     * 消息类型1:通知公告2:系统消息
     */
    public static final String MSG_CATEGORY_1 = "1";
    public static final String MSG_CATEGORY_2 = "2";

    /**
     * 是否配置菜单的数据权限 1是0否
     */
    public static final Integer RULE_FLAG_0 = 0;
    public static final Integer RULE_FLAG_1 = 1;

    /**
     * 用户状态 0冻结 1正常 2待定
     */
    public static final Integer USER_FREEZE = 0;
    public static final Integer USER_NORMAL = 1;


    /**
     * 用户删除标志位 0未删 1已删
     */
    public static final Integer USER_DELETE_NO=0;
    public static final Integer USER_DELETE_YES=1;

    /**字典翻译文本后缀*/
    public static final String DICT_TEXT_SUFFIX = "_dictText";

    public static final String ITEM_DISPLAY = "_display";

    /**
     * 表单设计器主表类型
     */
    public static final Integer DESIGN_FORM_TYPE_MAIN = 1;

    /**
     * 表单设计器子表表类型
     */
    public static final Integer DESIGN_FORM_TYPE_SUB = 2;

    /**
     * 表单设计器URL授权通过
     */
    public static final Integer DESIGN_FORM_URL_STATUS_PASSED = 1;

    /**
     * 表单设计器URL授权未通过
     */
    public static final Integer DESIGN_FORM_URL_STATUS_NOT_PASSED = 2;

    /**
     * 表单设计器新增 Flag
     */
    public static final String DESIGN_FORM_URL_TYPE_ADD = "add";
    /**
     * 表单设计器修改 Flag
     */
    public static final String DESIGN_FORM_URL_TYPE_EDIT = "edit";
    /**
     * 表单设计器详情 Flag
     */
    public static final String DESIGN_FORM_URL_TYPE_DETAIL = "detail";
    /**
     * 表单设计器复用数据 Flag
     */
    public static final String DESIGN_FORM_URL_TYPE_REUSE = "reuse";
    /**
     * 表单设计器编辑 Flag （已弃用）
     */
    public static final String DESIGN_FORM_URL_TYPE_VIEW = "view";

    /**
     * online参数值设置（是：Y, 否：N）
     */
    public static final String ONLINE_PARAM_VAL_IS_TURE = "Y";
    public static final String ONLINE_PARAM_VAL_IS_FALSE = "N";

    /**
     * 文件上传类型（本地：local，Minio：minio，阿里云：alioss）
     */
    public static final String UPLOAD_TYPE_LOCAL = "local";
    public static final String UPLOAD_TYPE_MINIO = "minio";
    public static final String UPLOAD_TYPE_OSS = "alioss";

    /**
     * 员工身份 （1:普通员工  2:上级）
     */
    public static final Integer USER_IDENTITY_1 = 1;
    public static final Integer USER_IDENTITY_2 = 2;

    /**
     * 日期格式
     */
    public static final String TIME_FORMAT_YMD = "yyyy-MM-dd";
    public static final String TIME_FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_FORMAT_YMDHMSSZ = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
}
