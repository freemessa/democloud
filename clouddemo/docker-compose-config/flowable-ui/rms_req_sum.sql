/******************************************/
/*   ���ݿ�ȫ�� = skydb   */
/*   ������ = sky_req_sum   */
/******************************************/
CREATE TABLE `sky_req_sum` (
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               `procdefid` varchar(64) NOT NULL COMMENT '���̶���id',
                               `flowid` varchar(64) NOT NULL COMMENT '����ʵ��id',
                               `flownodeid` varchar(64) NOT NULL COMMENT '���̽ڵ�id',
                               `updateby` varchar(32) NOT NULL COMMENT '������id',
                               `updatetime` datetime DEFAULT NULL COMMENT '����ʱ��',
                               `reqtype` varchar(32) DEFAULT NULL COMMENT '��������',
                               `reqname` varchar(256) DEFAULT NULL COMMENT '��������',
                               `creatorid` varchar(64) DEFAULT NULL COMMENT '������id',
                               `creatorname` varchar(32) DEFAULT NULL COMMENT '����������',
                               `createtime` date DEFAULT NULL COMMENT '����ʱ��',
                               `series` varchar(32) DEFAULT NULL COMMENT 'Ʒϵ',
                               `cartype` varchar(32) DEFAULT NULL COMMENT '����',
                               `reqdesc` varchar(256) DEFAULT NULL COMMENT '��������',
                               `casedesc` varchar(256) DEFAULT NULL COMMENT '��������',
                               `reqstatus` varchar(32) DEFAULT NULL COMMENT '����״̬',
                               `reqteam` varchar(64) DEFAULT NULL COMMENT '�����Ŷ�',
                               `savepath` varchar(128) DEFAULT NULL COMMENT '�鵵·��',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;