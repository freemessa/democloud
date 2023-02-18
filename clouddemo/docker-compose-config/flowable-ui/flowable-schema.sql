/******************************************/
/*   ���ݿ�ȫ�� = _process   */
/*   ������ = act_hi_operate_log   */
/******************************************/
CREATE TABLE `act_hi_operate_log` (
                                      `id` int NOT NULL AUTO_INCREMENT COMMENT '����',
                                      `operateType` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '�������ͣ����� ���������� ��',
                                      `processDefinitionId` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '���̶���id',
                                      `processInstanceId` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '����ʵ��id',
                                      `taskDefinitionKey` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '����key',
                                      `taskId` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '����id',
                                      `operateUserId` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '�����˹���',
                                      `operateRoleId` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '�����˽�ɫid',
                                      `operateTime` datetime DEFAULT NULL COMMENT '����ʱ��',
                                      `assignee` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '������',
                                      PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COMMENT='���������¼��';

/******************************************/
/*   ���ݿ�ȫ�� = _process   */
/*   ������ = sky_file_info   */
/******************************************/
CREATE TABLE `sky_file_info` (
                                 `file_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '����',
                                 `file_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '�ļ���',
                                 `file_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '�ļ�����',
                                 `file_size` int NOT NULL COMMENT '�ļ���С',
                                 `group_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '����id(minio bucket�ռ���)',
                                 `res_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '��Դid(minio ·��)',
                                 `res_index` int NOT NULL COMMENT '��Դ����',
                                 `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '������',
                                 `create_time` datetime NOT NULL COMMENT '����ʱ��',
                                 `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '������',
                                 `update_time` datetime DEFAULT NULL COMMENT '����ʱ��',
                                 `file_del` int NOT NULL DEFAULT '0' COMMENT '�ļ�״̬(0-������1-��ɾ��)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='�ĵ���Ϣ��¼��';

/******************************************/
/*   ���ݿ�ȫ�� = sky_process   */
/*   ������ = sky_flow_form_detail   */
/******************************************/
CREATE TABLE `sky_flow_form_detail` (
                                        `form_detail_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '����',
                                        `form_id` bigint NOT NULL COMMENT '�����id',
                                        `para_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '�ֶ���',
                                        `para_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '�ֶ�����',
                                        `para_size` int DEFAULT NULL COMMENT '�ֶδ�С',
                                        `para_disp_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '�ֶ���ʾ����',
                                        `table_zone` int DEFAULT NULL COMMENT '�����',
                                        `table_row` int DEFAULT NULL COMMENT '������',
                                        `table_col` int DEFAULT NULL COMMENT '������',
                                        `remark` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '��ע',
                                        `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '������',
                                        `create_time` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '����ʱ��',
                                        `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '������',
                                        `update_time` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '����ʱ��',
                                        `para_index` int DEFAULT NULL COMMENT '�ֶ�����',
                                        `file_num` tinyint DEFAULT '0' COMMENT '�ļ���Դ����',
                                        PRIMARY KEY (`form_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='������';

/******************************************/
/*   ���ݿ�ȫ�� = sky_process   */
/*   ������ = sky_flow_form_list   */
/******************************************/
CREATE TABLE `sky_flow_form_list` (
                                      `form_id` bigint NOT NULL AUTO_INCREMENT COMMENT '����',
                                      `form_name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '����',
                                      `remark` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '��ע',
                                      `version` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '�汾',
                                      `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '������',
                                      `create_time` datetime DEFAULT NULL COMMENT '����ʱ��',
                                      `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '������',
                                      `update_time` datetime DEFAULT NULL COMMENT '����ʱ��',
                                      `is_deploy` tinyint(1) DEFAULT NULL COMMENT '�Ƿ񷢲�',
                                      PRIMARY KEY (`form_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='���̱���';

/******************************************/
/*   ���ݿ�ȫ�� = sky_process   */
/*   ������ = sky_flow_res_info   */
/******************************************/
CREATE TABLE `sky_flow_res_info` (
                                     `res_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '����',
                                     `flow_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '����id',
                                     `flow_node_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '���̽ڵ�id',
                                     `file_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '�ļ�id',
                                     `file_index` int NOT NULL COMMENT '�ļ����',
                                     `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '������',
                                     `create_time` datetime DEFAULT NULL COMMENT '����ʱ��',
                                     `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '������',
                                     `update_time` datetime DEFAULT NULL COMMENT '����ʱ��',
                                     `remark` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='������Դ��Ϣ��';

/******************************************/
/*   ���ݿ�ȫ�� = sky_process   */
/*   ������ = sys_deploy_form   */
/******************************************/
CREATE TABLE `sys_deploy_form` (
                                   `id` bigint NOT NULL AUTO_INCREMENT COMMENT '����',
                                   `form_id` bigint DEFAULT NULL COMMENT '������',
                                   `deploy_id` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '���̶�������',
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COMMENT='����ʵ��������';

/******************************************/
/*   ���ݿ�ȫ�� = sky_process   */
/*   ������ = sys_dict_data   */
/******************************************/
CREATE TABLE `sys_dict_data` (
                                 `dict_code` bigint NOT NULL AUTO_INCREMENT COMMENT '�ֵ����',
                                 `dict_sort` int DEFAULT '0' COMMENT '�ֵ�����',
                                 `dict_label` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT '' COMMENT '�ֵ��ǩ',
                                 `dict_value` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT '' COMMENT '�ֵ��ֵ',
                                 `dict_type` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT '' COMMENT '�ֵ�����',
                                 `css_class` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '��ʽ���ԣ�������ʽ��չ��',
                                 `list_class` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '��������ʽ',
                                 `is_default` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT 'N' COMMENT '�Ƿ�Ĭ�ϣ�Y�� N��',
                                 `status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT '0' COMMENT '״̬��0���� 1ͣ�ã�',
                                 `create_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT '' COMMENT '������',
                                 `create_time` datetime DEFAULT NULL COMMENT '����ʱ��',
                                 `update_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT '' COMMENT '������',
                                 `update_time` datetime DEFAULT NULL COMMENT '����ʱ��',
                                 `remark` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '��ע',
                                 PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='�ֵ����ݱ�';