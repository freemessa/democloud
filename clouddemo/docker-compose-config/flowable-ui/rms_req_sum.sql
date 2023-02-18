/******************************************/
/*   数据库全名 = skydb   */
/*   表名称 = sky_req_sum   */
/******************************************/
CREATE TABLE `sky_req_sum` (
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               `procdefid` varchar(64) NOT NULL COMMENT '流程定义id',
                               `flowid` varchar(64) NOT NULL COMMENT '流程实例id',
                               `flownodeid` varchar(64) NOT NULL COMMENT '流程节点id',
                               `updateby` varchar(32) NOT NULL COMMENT '更新人id',
                               `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
                               `reqtype` varchar(32) DEFAULT NULL COMMENT '需求类型',
                               `reqname` varchar(256) DEFAULT NULL COMMENT '需求名称',
                               `creatorid` varchar(64) DEFAULT NULL COMMENT '创建人id',
                               `creatorname` varchar(32) DEFAULT NULL COMMENT '创建人姓名',
                               `createtime` date DEFAULT NULL COMMENT '创建时间',
                               `series` varchar(32) DEFAULT NULL COMMENT '品系',
                               `cartype` varchar(32) DEFAULT NULL COMMENT '车型',
                               `reqdesc` varchar(256) DEFAULT NULL COMMENT '需求描述',
                               `casedesc` varchar(256) DEFAULT NULL COMMENT '场景描述',
                               `reqstatus` varchar(32) DEFAULT NULL COMMENT '需求状态',
                               `reqteam` varchar(64) DEFAULT NULL COMMENT '所属团队',
                               `savepath` varchar(128) DEFAULT NULL COMMENT '归档路径',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;