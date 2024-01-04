V2__init_db.sql/*
SQLyog  v12.2.6 (64 bit)
MySQL - 5.7.18-cynos-log : Database - socialuni_dev
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;
/*!40101 SET SQL_MODE=''*/;

DROP TABLE IF EXISTS `s_app_config`;

CREATE TABLE `s_app_config` (
                                `dev_id` int(11) NOT NULL,
                                `config_key` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                                `value` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                `label` varchar(300) null,
                                `status` int(11) NOT NULL,
                                `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                PRIMARY KEY (`config_key`,`dev_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci comment '全局配置表';

INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'appGender', 'false', '', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'disableUnderageContent', 'all', '', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'disableContentHasQrCode', 'false', '', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'disableContentHasContactInfo', 'false', '', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'mustSetSchoolCanPost', 'false', '', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'followTabName', '关注', '', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'homeTabName', '首页', '', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'cityTabName', '同城', '', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'tabNames', '关注,首页,同城', '', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'serviceWeChat', '', '', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'vipPrice', '1000', '', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'swiperHeight', '130', '', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'homeUrl', 'https://www.socialuni.cn/', '', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'suggestUrl', 'https://www.socialuni.cn/qingchi/suggest', '', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'contactUsUrl', 'https://www.socialuni.cn/qingchi/contact', '', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'userAgreementUrl', 'https://www.socialuni.cn/qingchi/agreement', '', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'userPrivacyUrl', 'https://www.socialuni.cn/qingchi/privacy', '', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'childProtectUrl', 'https://www.socialuni.cn/qingchi/childProtect', '', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'rewardedAdLimit', '3', '', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'contactExpenseShell', '9999', '', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'contactUserReceiveShell', '50', '', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'errorMsgContactService', '有任何疑问请联系客服', '', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'errorMsg601UnLogin', '未登录，请进行登录，是否前往登录', '', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'errorMsg604SystemError', '系统异常，有任何疑问请联系客服', '', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'authCodeInterval', '120', '', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'reportCountHide', '1', '', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'showSwipers', 'true', '', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'talkShowAdInterval', '8', '', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'talkShowAdCount', '10', '', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'authCodeCount', '30', '', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'authCodeIpCount', '200', '', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'authCodePhoneCount', '30', '', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'authCodeValidMinute', '30', '', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'highLimitReportCount', '20', '', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'qq_account', '491369310', '客服qq/微信', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'wx_mp_id', '', '微信小程序id', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'wx_app_id', '', '微信app-id', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'wx_mp_secret', '', '微信小程序秘钥', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'wx_merchant_id', '', '微信商户id', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'wx_merchant_key', '', '微信商户秘钥', 1);
INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'devPublishDataApiUrl', '', '开发者服务器url', 1);




/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*Table structure for table `s_community_tag_type` */

DROP TABLE IF EXISTS `s_community_tag_type`;

CREATE TABLE `s_community_tag_type` (
                                        `id` int(11) NOT NULL AUTO_INCREMENT,
                                        `count` int(11) DEFAULT NULL,
                                        `create_time` datetime DEFAULT NULL,
                                        `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                        `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                        `show_in_home` bit(1) DEFAULT NULL,
                                        `show_wx_account` bit(1) DEFAULT NULL,
                                        `status` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                        `talk_count` int(11) DEFAULT NULL,
                                        `update_time` datetime DEFAULT NULL,
                                        `order_level` int(11) DEFAULT '0',
                                        `user_id` int(11) NOT NULL,
                                        PRIMARY KEY (`id`),
                                        UNIQUE KEY `UKbfknx81d06qrao6tv5s90lbq7` (`name`),
                                        KEY `IDXh0w7a335r4bdfqlu58rcv39lr` (`order_level`),
                                        KEY `IDXk4hevhey34wlg0241rsv6t2sq` (`talk_count`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `s_community_tag_type` */

insert  into `s_community_tag_type`(`id`,`count`,`create_time`,`description`,`name`,`show_in_home`,`show_wx_account`,`status`,`talk_count`,`update_time`,`order_level`,`user_id`) values

                                                                                               (1,1394245,NULL,NULL,'交友',NULL,NULL,'正常',120999,NULL,0,0),

                                                                                               (2,4070,NULL,NULL,'生活',NULL,NULL,'正常',151,NULL,0,0),

                                                                                               (3,0,NULL,NULL,'综艺',NULL,NULL,'正常',0,NULL,0,0),

                                                                                               (4,13,NULL,NULL,'电视剧',NULL,NULL,'正常',0,NULL,0,0),

                                                                                               (5,0,NULL,NULL,'高校',NULL,NULL,'正常',0,NULL,0,0),

                                                                                               (6,0,NULL,NULL,'中小学',NULL,NULL,'正常',0,NULL,0,0),

                                                                                               (7,0,NULL,NULL,'科技',NULL,NULL,'正常',0,NULL,0,0),

                                                                                               (8,0,NULL,NULL,'金融',NULL,NULL,'正常',0,NULL,0,0),

                                                                                               (9,119,NULL,NULL,'音乐',NULL,NULL,'正常',14,NULL,0,0),

                                                                                               (10,0,NULL,NULL,'企业',NULL,NULL,'正常',0,NULL,0,0),

                                                                                               (11,0,NULL,NULL,'服务',NULL,NULL,'正常',0,NULL,0,0),

                                                                                               (12,25,NULL,NULL,'商业',NULL,NULL,'正常',13,NULL,0,0),

                                                                                               (13,0,NULL,NULL,'社会',NULL,NULL,'正常',0,NULL,0,0),

                                                                                               (14,149,NULL,NULL,'名人',NULL,NULL,'正常',2,NULL,0,0),

                                                                                               (15,67,NULL,NULL,'品牌',NULL,NULL,'正常',0,NULL,0,0),

                                                                                               (16,28,NULL,NULL,'文学',NULL,NULL,'正常',2,NULL,0,0),

                                                                                               (17,0,NULL,NULL,'三农',NULL,NULL,'正常',0,NULL,0,0),

                                                                                               (18,44,NULL,NULL,'教育',NULL,NULL,'正常',3,NULL,0,0),

                                                                                               (19,0,NULL,NULL,'历史',NULL,NULL,'正常',0,NULL,0,0),

                                                                                               (20,0,NULL,NULL,'自然',NULL,NULL,'正常',0,NULL,0,0),

                                                                                               (21,1328,NULL,NULL,'地区',NULL,NULL,'正常',311,NULL,0,0),

                                                                                               (22,28347,NULL,NULL,'情感',NULL,NULL,'正常',1271,NULL,0,0),

                                                                                               (23,22,NULL,NULL,'电影',NULL,NULL,'正常',2,NULL,0,0),

                                                                                               (24,4,NULL,NULL,'明星',NULL,NULL,'正常',0,NULL,0,0),

                                                                                               (25,0,NULL,NULL,'数码',NULL,NULL,'正常',0,NULL,0,0),

                                                                                               (26,201,NULL,NULL,'体育',NULL,NULL,'正常',37,NULL,0,0),

                                                                                               (27,767,NULL,NULL,'动漫',NULL,NULL,'正常',107,NULL,0,0),

                                                                                               (28,141096,NULL,NULL,'游戏',NULL,NULL,'正常',18572,NULL,0,0),

                                                                                               (29,399,NULL,NULL,'官方',NULL,NULL,'正常',22,NULL,0,0),

                                                                                               (30,572294,NULL,NULL,'年龄',NULL,NULL,'正常',57421,NULL,0,0),

                                                                                               (31,94,NULL,NULL,'星座',NULL,NULL,'正常',9,NULL,0,0),

                                                                                               (32,0,NULL,NULL,'app',NULL,NULL,'正常',111887,NULL,0,0),

                                                                                               (33,0,NULL,NULL,'女生专属',NULL,NULL,'正常',1170,NULL,100,0),

                                                                                               (34,0,NULL,NULL,'男生专属',NULL,NULL,'正常',244,NULL,60,0),

                                                                                               (35,0,NULL,NULL,'测试环境',NULL,NULL,'正常',40,NULL,0,0),

                                                                                               (36,0,NULL,NULL,'大学',NULL,NULL,'正常',NULL,NULL,40,0);

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;
/*Table structure for table `s_community_tag` */

DROP TABLE IF EXISTS `s_community_tag`;

CREATE TABLE `s_community_tag` (
                                   `id` int(11) NOT NULL AUTO_INCREMENT,
                                   `count` int(11) DEFAULT NULL,
                                   `create_reason` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                   `create_time` datetime DEFAULT NULL,
                                   `create_user` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                   `tag_type_id` int(11) DEFAULT NULL,
                                   `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                   `status` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                   `talk_count` int(11) DEFAULT NULL,
                                   `update_time` datetime DEFAULT NULL,
                                   `apply_user_id` int(11) DEFAULT NULL,
                                   `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                   `show_in_home` bit(1) DEFAULT NULL,
                                   `show_wx_account` bit(1) DEFAULT NULL,
                                   `tag_classify_id` int(11) DEFAULT NULL,
                                   `avatar` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                   `gender` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                   `dev_id` int(11) DEFAULT NULL,
                                   `show_front` bit(1) DEFAULT NULL,
                                   `visible_gender` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                   PRIMARY KEY (`id`),
                                   UNIQUE KEY `UKfk1hod8xyxbkhqlqsvgo3lw55` (`name`),
                                   KEY `IDXio375tvs3bij2hxrn67la818a` (`status`),
                                   KEY `IDXmn1td4ig90iym7drmaee05by8` (`show_front`),
                                   KEY `IDX9o75muk9u9q7gmhrwcqapwx2e` (`count`),
                                   KEY `IDXjhrcmy6w1kyqe25mlg0qk6se` (`visible_gender`)
) ENGINE=InnoDB AUTO_INCREMENT=653 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `s_community_tag` */

insert  into `s_community_tag`(`id`,`count`,`create_reason`,`create_time`,`create_user`,`tag_type_id`,`name`,`status`,`talk_count`,`update_time`,`apply_user_id`,`description`,`show_in_home`,`show_wx_account`,`tag_classify_id`,`avatar`,`gender`,`dev_id`,`show_front`,`visible_gender`) values

(1,880111,NULL,NULL,NULL,1,'处对象','正常',51609,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(2,58625,NULL,NULL,NULL,1,'处v友','正常',6664,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(3,233310,NULL,NULL,NULL,1,'处q友','正常',19220,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(4,10057,NULL,NULL,NULL,1,'扩列','正常',1360,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(5,31,NULL,NULL,NULL,2,'愿望','正常',1,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(6,8,NULL,NULL,NULL,2,'秀美食','正常',0,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(7,3678,NULL,NULL,NULL,22,'秀恩爱','正常',73,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(8,1149,NULL,NULL,NULL,27,'二次元','正常',88,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(9,65,NULL,NULL,NULL,29,'问题建议','正常',2,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(10,28,NULL,NULL,NULL,2,'抱怨','正常',1,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(11,74,NULL,NULL,NULL,28,'lol','正常',1,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(12,160454,NULL,NULL,NULL,28,'王者荣耀','正常',13921,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(13,162,NULL,NULL,NULL,28,'绝地求生','正常',1,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(14,13830,NULL,NULL,NULL,28,'和平精英','正常',1305,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(15,407,NULL,NULL,NULL,22,'情话','正常',8,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(16,49,NULL,NULL,NULL,2,'理想','正常',0,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(17,39,NULL,NULL,NULL,2,'秀生日','正常',0,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(18,699,NULL,NULL,NULL,2,'秀自拍','正常',11,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(19,361,NULL,NULL,NULL,29,'官方','正常',21,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(20,10058,NULL,NULL,NULL,30,'90后','正常',215,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(21,624250,NULL,NULL,NULL,30,'00后','正常',38267,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(22,804,NULL,NULL,NULL,30,'80后','正常',9,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(23,1862,NULL,NULL,NULL,1,'处姐妹','正常',58,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(24,2565,NULL,NULL,NULL,1,'处兄弟','正常',86,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(25,48,NULL,NULL,NULL,28,'守望先锋','正常',1,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(26,20,NULL,NULL,NULL,2,'旅行','正常',1,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(27,626,NULL,NULL,NULL,22,'失恋','正常',10,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(28,295,NULL,NULL,NULL,2,'想法','正常',7,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(29,2446,NULL,'2019-12-21 13:19:55',NULL,1,'交友','正常',344,'2019-12-21 13:19:55',108,'交友',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(30,4,NULL,'2019-12-23 12:31:21',NULL,1,'牛逼1','已删除',0,'2019-12-23 12:31:21',481,'比牛逼',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(31,49,NULL,'2019-12-23 12:31:21',NULL,1,'牛逼','已删除',0,'2019-12-23 12:31:21',481,'比牛逼',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(32,19,NULL,'2019-12-24 20:54:25',NULL,2,'平安夜','正常',0,'2019-12-24 20:54:25',415,'平安夜',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(33,293,NULL,'2019-12-24 20:54:25',NULL,28,'二次元1','正常',6,'2019-12-24 20:54:25',415,'二次元',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(34,345,NULL,'2019-12-27 21:20:28',NULL,1,'处WeChat好友','正常',1,'2019-12-27 21:20:28',2019,'加微信好友',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(35,53,NULL,'2019-12-29 16:06:49',NULL,15,'趴赛','正常',0,'2019-12-29 16:06:49',2474,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(36,379,NULL,'2019-12-29 22:29:09',NULL,28,'英雄联盟','正常',10,'2019-12-29 22:29:09',2584,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(37,2689,NULL,'2020-01-01 09:11:41',NULL,22,'介绍对象','正常',18,'2020-01-01 09:11:41',1430,'就是像月老一样',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(38,23832,NULL,'2020-01-01 22:21:22',NULL,1,'可以聊天，打游戏','正常',2783,'2020-01-01 22:21:22',3104,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(39,254,NULL,'2020-01-02 19:15:48',NULL,1,'养火','正常',14,'2020-01-02 19:15:48',3431,'加q养火',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(40,1347,NULL,'2020-01-02 20:41:38',NULL,1,'闺蜜','正常',37,'2020-01-02 20:41:38',3445,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','女',NULL,'','girl'),

(41,114365,NULL,'2020-01-04 19:04:35',NULL,1,'小哥哥','正常',11515,'2020-01-04 19:04:35',4076,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(42,4235,NULL,'2020-01-05 11:40:16',NULL,1,'处关系','正常',496,'2020-01-05 11:40:16',4243,'就是出很多关系',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(43,10678,NULL,'2020-01-06 22:00:08',NULL,1,'找哥哥','正常',583,'2020-01-06 22:00:08',5197,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(44,82083,NULL,'2020-01-06 23:54:27',NULL,1,'处对象，专一，真心','已删除',0,'2020-01-06 23:54:27',5258,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(45,38,NULL,'2020-01-07 22:51:55',NULL,2,'生活','正常',2,'2020-01-07 22:51:55',5598,'过得好吗',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(46,8564,NULL,'2020-01-08 15:43:54',NULL,1,'处cp','正常',820,'2020-01-08 15:43:54',4873,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(47,42,NULL,'2020-01-08 16:09:03',NULL,28,'谁是卧底','正常',0,'2020-01-08 16:09:03',5807,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(48,365,NULL,'2020-01-08 18:22:51',NULL,28,'QQ飞车','正常',16,'2020-01-08 18:22:51',5860,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(49,5308,NULL,'2020-01-09 17:46:58',NULL,1,'阳光帅气男孩','正常',170,'2020-01-09 17:46:58',6298,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(50,215,NULL,'2020-01-11 17:08:09',NULL,2,'不知道干嘛','正常',1,'2020-01-11 17:08:09',5114,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(51,8649,NULL,'2020-01-11 21:02:44',NULL,33,'处闺蜜','正常',735,'2020-01-11 21:02:44',7195,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','girl'),

(52,487,NULL,'2020-01-11 21:04:33',NULL,1,'处闺蜜，用闺头，专一，住','正常',14,'2020-01-11 21:04:33',7195,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(53,1044,NULL,'2020-01-11 21:05:34',NULL,1,'处闺蜜，够铁，用闺头','正常',54,'2020-01-11 21:05:34',7195,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(54,2828,NULL,'2020-01-11 23:28:24',NULL,1,'宠妻','正常',225,'2020-01-11 23:28:24',7290,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(55,2434,NULL,'2020-01-13 15:39:30',NULL,1,'处哥哥','正常',73,'2020-01-13 15:39:30',7144,'处哥哥',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(56,893,NULL,'2020-01-14 22:18:38',NULL,1,'喜欢wuwu的','已删除',0,'2020-01-14 22:18:38',8386,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(57,979,NULL,'2020-01-15 18:49:52',NULL,1,'交朋友','正常',106,'2020-01-15 18:49:52',8625,'交朋友',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(58,6111,NULL,'2020-01-17 19:40:42',NULL,22,'恋爱','正常',588,'2020-01-17 19:40:42',9784,'专一不花心',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(59,58,NULL,'2020-01-19 16:51:56',NULL,2,'不食人间烟火','正常',1,'2020-01-19 16:51:56',11097,'对一个人的相信，信任，包容',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(60,10617,NULL,'2020-01-20 19:24:46',NULL,1,'找男闺蜜','正常',989,'2020-01-20 19:24:46',12072,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(61,2429,NULL,'2020-01-21 09:41:16',NULL,22,'男朋友','正常',35,'2020-01-21 09:41:16',12639,'曝照，公开恋情，霸道12~14',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(62,7352,NULL,'2020-01-25 15:36:22',NULL,1,'颜控','正常',715,'2020-01-25 15:36:22',11914,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(63,72,NULL,'2020-01-27 22:07:14',NULL,2,'#','正常',2,'2020-01-27 22:07:14',15744,'可以聊天，处对象。',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(64,1117,NULL,'2020-01-31 13:58:50',NULL,1,'聊污','已删除',0,'2020-01-31 13:58:50',16235,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(65,842,NULL,'2020-02-01 17:26:46',NULL,1,'真心，专一','正常',197,'2020-02-01 17:26:46',14873,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(66,227,NULL,'2020-02-01 17:26:47',NULL,1,'真心','正常',39,'2020-02-01 17:26:47',14873,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(67,552,NULL,'2020-02-01 22:39:48',NULL,1,'聊天','已删除',0,'2020-02-01 22:39:48',18983,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(68,21173,NULL,'2020-02-01 22:40:06',NULL,1,'学生','正常',2191,'2020-02-01 22:40:06',18983,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(69,88,NULL,'2020-02-02 06:12:43',NULL,1,'wuwu的小姐姐','已删除',0,'2020-02-02 06:12:43',19338,'有没有wu唔的小姐姐哦',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(70,91,NULL,'2020-02-02 06:13:08',NULL,1,'有没有唔唔的小姐姐','已删除',0,'2020-02-02 06:13:08',19338,'啦啦啦啦啦',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(71,171,NULL,'2020-02-02 06:16:00',NULL,1,'喜欢呜呜的小姐姐','已删除',0,'2020-02-02 06:16:00',19338,'啦啦啦啦啦啦啦',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(72,21,NULL,'2020-02-02 10:06:20',NULL,1,'板混','正常',0,'2020-02-02 10:06:20',18655,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(73,170,NULL,'2020-02-02 16:02:32',NULL,1,'聊骚','已删除',0,'2020-02-02 16:02:32',19643,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(74,2574,NULL,'2020-02-02 16:02:52',NULL,1,'文爱','已删除',0,'2020-02-02 16:02:52',19643,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(75,221,NULL,'2020-02-02 21:26:07',NULL,9,'音乐','正常',14,'2020-02-02 21:26:07',19970,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(76,19159,NULL,'2020-02-03 18:48:01',NULL,1,'姐弟恋','正常',1139,'2020-02-03 18:48:01',20818,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(77,2165,NULL,'2020-02-03 20:05:25',NULL,1,'身材控','正常',141,'2020-02-03 20:05:25',20573,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(78,2226,NULL,'2020-02-03 20:05:45',NULL,1,'非颜控','正常',174,'2020-02-03 20:05:45',20573,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(79,6545,NULL,'2020-02-03 22:07:28',NULL,1,'爆照','正常',504,'2020-02-03 22:07:28',16230,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(80,3171,NULL,'2020-02-05 00:20:07',NULL,1,'#处对象','正常',62,'2020-02-05 00:20:07',22223,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(81,687,NULL,'2020-02-05 00:22:58',NULL,30,'2004','正常',9,'2020-02-05 00:22:58',22223,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(82,10577,NULL,'2020-02-05 00:59:35',NULL,1,'小奶狗或小狼狗','正常',652,'2020-02-05 00:59:35',22276,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(83,17,NULL,'2020-02-05 10:43:21',NULL,1,'不要污的男孩','已删除',0,'2020-02-05 10:43:21',22482,'太色的不要',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(84,511,NULL,'2020-02-05 11:20:58',NULL,28,'第五人格','正常',36,'2020-02-05 11:20:58',22498,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(85,1107,NULL,'2020-02-05 19:27:42',NULL,1,'处兄妹','正常',56,'2020-02-05 19:27:42',22931,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(86,814,NULL,'2020-02-05 21:27:17',NULL,1,'真心对待','正常',208,'2020-02-05 21:27:17',23077,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(87,38,NULL,'2020-02-08 15:09:12',NULL,2,'情绪三分钟1','正常',0,'2020-02-08 15:09:12',24669,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(88,43,NULL,'2020-02-08 15:09:13',NULL,2,'情绪三分钟','正常',1,'2020-02-08 15:09:13',24669,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(89,229,NULL,'2020-02-09 00:21:35',NULL,21,'香港内地交友群','正常',2,'2020-02-09 00:21:35',25511,'正规认真交友群',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(90,161,NULL,'2020-02-09 23:17:45',NULL,1,'专一','正常',37,'2020-02-09 23:17:45',26516,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(91,689,NULL,'2020-02-10 22:32:21',NULL,1,'师傅','正常',32,'2020-02-10 22:32:21',27538,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(92,860,NULL,'2020-02-10 23:25:08',NULL,1,'好看','正常',44,'2020-02-10 23:25:08',27612,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(93,46751,NULL,'2020-02-12 09:05:17',NULL,1,'小姐姐','正常',9150,'2020-02-12 09:05:17',14531,'好听',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(94,1823,NULL,'2020-02-12 22:58:09',NULL,28,'吃鸡','正常',174,'2020-02-12 22:58:09',24710,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(95,409,NULL,'2020-02-13 02:12:52',NULL,1,'找cp','正常',32,'2020-02-13 02:12:52',29790,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(96,23406,NULL,'2020-02-13 03:22:35',NULL,1,'百合','违规',580,'2020-02-13 03:22:35',29793,'女孩子处对象',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(97,2788,NULL,'2020-02-13 21:40:46',NULL,1,'找个女闺蜜','正常',91,'2020-02-13 21:40:46',30535,'陪我打王者打吃鸡长的一般就都可以',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','girl'),

(98,677,NULL,'2020-02-13 23:21:40',NULL,1,'处对象，','正常',58,'2020-02-13 23:21:40',27728,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(99,76,NULL,'2020-02-13 23:22:24',NULL,1,'真心，阳光','正常',1,'2020-02-13 23:22:24',27728,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(100,30,NULL,'2020-02-14 08:19:31',NULL,2,'情人节快乐！','正常',0,'2020-02-14 08:19:31',26367,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(101,4490,NULL,'2020-02-14 12:45:24',NULL,1,'找妹妹','正常',291,'2020-02-14 12:45:24',18726,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(102,1399,NULL,'2020-02-15 14:09:35',NULL,1,'#声控#','正常',151,'2020-02-15 14:09:35',33473,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(103,34,NULL,'2020-02-16 19:16:25',NULL,2,'尬','正常',0,'2020-02-16 19:16:25',35154,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(104,335,NULL,'2020-02-17 21:33:11',NULL,28,'王者','正常',10,'2020-02-17 21:33:11',35199,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(105,58,NULL,'2020-02-17 22:51:37',NULL,28,'qq飞车手游','正常',2,'2020-02-17 22:51:37',36742,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(106,1167,NULL,'2020-02-18 17:30:16',NULL,1,'等待一起用情头的那个人','正常',108,'2020-02-18 17:30:16',28820,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(107,754,NULL,'2020-02-18 18:42:20',NULL,1,'处对象，cp','正常',14,'2020-02-18 18:42:20',37595,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(108,5027,NULL,'2020-02-18 18:42:54',NULL,1,'找小姐姐','正常',326,'2020-02-18 18:42:54',37595,'颜控，声控',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(109,1401,NULL,'2020-02-18 18:43:37',NULL,1,'本人暖男，175。15岁','正常',78,'2020-02-18 18:43:37',37595,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(110,337,NULL,'2020-02-18 18:44:05',NULL,28,'王者吃鸡上分','正常',27,'2020-02-18 18:44:05',37595,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(111,2012,NULL,'2020-02-18 18:56:14',NULL,22,'找男朋友','正常',106,'2020-02-18 18:56:14',37570,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(112,293,NULL,'2020-02-18 18:56:14',NULL,22,'找男朋友1','正常',7,'2020-02-18 18:56:14',37570,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(113,332,NULL,'2020-02-18 18:56:14',NULL,22,'找男朋友2','正常',14,'2020-02-18 18:56:14',37570,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(114,566,NULL,'2020-02-18 18:56:14',NULL,22,'找男朋友3','正常',19,'2020-02-18 18:56:14',37570,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(115,335,NULL,'2020-02-20 19:21:33',NULL,1,'找暖心哥哥','正常',12,'2020-02-20 19:21:33',21294,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(116,317,NULL,'2020-02-20 21:35:55',NULL,1,'狼狗','正常',46,'2020-02-20 21:35:55',38927,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(117,332,NULL,'2020-02-20 21:36:16',NULL,1,'一丢丢颜控','正常',49,'2020-02-20 21:36:16',38927,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(118,580,NULL,'2020-02-20 21:36:34',NULL,1,'宠我','正常',112,'2020-02-20 21:36:34',38927,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(119,341,NULL,'2020-02-20 21:37:36',NULL,1,'处闺蜜（真心真心）','正常',22,'2020-02-20 21:37:36',38927,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(120,99,NULL,'2020-02-21 02:31:54',NULL,22,'一见钟情❤','正常',2,'2020-02-21 02:31:54',39372,'我希望找到一个，担心失去我的人❤',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(121,662,NULL,'2020-02-21 04:43:13',NULL,1,'处妹妹','正常',20,'2020-02-21 04:43:13',39410,'11',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(122,921,NULL,'2020-02-21 09:27:03',NULL,2,'学习','正常',83,'2020-02-21 09:27:03',37613,'好好学习',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(123,129,NULL,'2020-02-21 19:27:48',NULL,31,'狮子座','正常',8,'2020-02-21 19:27:48',39866,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(124,253,NULL,'2020-02-21 19:28:03',NULL,1,'处对象专一','正常',18,'2020-02-21 19:28:03',39866,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(125,339,NULL,'2020-02-21 20:05:11',NULL,1,'处师傅','正常',13,'2020-02-21 20:05:11',37427,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(126,239,NULL,'2020-02-22 08:58:34',NULL,1,'扩列闲聊','正常',64,'2020-02-22 08:58:34',40485,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(127,7919,NULL,'2020-02-22 11:04:05',NULL,1,'不聊污','正常',1527,'2020-02-22 11:04:05',40598,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(128,31,NULL,'2020-02-22 14:55:50',NULL,1,'#喜欢的人','正常',0,'2020-02-22 14:55:50',40741,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(129,153,NULL,'2020-02-23 16:52:34',NULL,1,'妻官严','正常',16,'2020-02-23 16:52:34',41291,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(130,623,NULL,'2020-02-24 16:00:48',NULL,1,'各种关系','正常',97,'2020-02-24 16:00:48',37306,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(131,100,NULL,'2020-02-25 21:59:50',NULL,1,'手控','正常',7,'2020-02-25 21:59:50',43890,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(132,96,NULL,'2020-02-25 22:58:21',NULL,1,'尬聊','正常',8,'2020-02-25 22:58:21',44000,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(133,69,NULL,'2020-02-26 02:07:51',NULL,22,'对象','正常',0,'2020-02-26 02:07:51',44236,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(134,236,NULL,'2020-02-26 02:08:16',NULL,1,'拒颜控','正常',22,'2020-02-26 02:08:16',44236,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(135,6113,NULL,'2020-02-26 03:47:44',NULL,1,'可以奔现的那种哦','正常',906,'2020-02-26 03:47:44',44298,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(136,957,NULL,'2020-02-26 06:59:58',NULL,1,'找个姐姐','正常',57,'2020-02-26 06:59:58',44335,'姐姐',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(137,142,NULL,'2020-02-26 15:47:04',NULL,1,'可爱的','正常',6,'2020-02-26 15:47:04',44552,'卖闺密',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(138,121,NULL,'2020-02-26 15:48:01',NULL,1,'卖闺密','正常',3,'2020-02-26 15:48:01',44552,'卖闺密',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(139,764,NULL,'2020-02-26 19:53:24',NULL,1,'暖男小哥哥','正常',34,'2020-02-26 19:53:24',44926,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(140,281,NULL,'2020-02-26 19:53:41',NULL,1,'男闺蜜','正常',21,'2020-02-26 19:53:41',44926,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(141,1585,NULL,'2020-02-26 19:54:14',NULL,1,'拒污','正常',468,'2020-02-26 19:54:14',44926,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(142,103,NULL,'2020-02-27 19:37:42',NULL,1,'处闺蜜(❁´ω`❁)','正常',7,'2020-02-27 19:37:42',45707,'真心的',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(143,130,NULL,'2020-02-27 19:40:30',NULL,2,'伤心的话，我陪你','正常',4,'2020-02-27 19:40:30',45707,'本人是直，真直，这来自我朋友的形容?️',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(144,159,NULL,'2020-02-27 20:12:43',NULL,2,'女装大佬','正常',0,'2020-02-27 20:12:43',46070,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(145,230,NULL,'2020-02-28 10:43:45',NULL,22,'爱','正常',8,'2020-02-28 10:43:45',46455,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(146,2630,NULL,'2020-02-28 22:12:29',NULL,1,'聊污加','已删除',0,'2020-02-28 22:12:29',47102,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(147,162,NULL,'2020-02-28 23:07:53',NULL,2,'爱钱','正常',10,'2020-02-28 23:07:53',46220,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(148,2329,NULL,'2020-02-29 05:33:49',NULL,22,'处对象，真心的','正常',105,'2020-02-29 05:33:49',29805,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(149,12052,NULL,'2020-02-29 12:42:17',NULL,22,'处对象，专一，去污','正常',802,'2020-02-29 12:42:17',47626,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(150,115,NULL,'2020-02-29 22:38:08',NULL,1,'暖男','正常',4,'2020-02-29 22:38:08',48226,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(151,137,NULL,'2020-02-29 22:38:54',NULL,1,'会宠人','正常',20,'2020-02-29 22:38:54',48226,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(152,173,NULL,'2020-02-29 23:10:12',NULL,1,'洛丽塔','正常',4,'2020-02-29 23:10:12',46220,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(153,745,NULL,'2020-02-29 23:10:34',NULL,1,'洛丽塔jk','正常',20,'2020-02-29 23:10:34',46220,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(154,25,NULL,'2020-03-01 00:55:54',NULL,1,'处兑现','正常',0,'2020-03-01 00:55:54',48361,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(155,159,NULL,'2020-03-01 00:56:25',NULL,1,'处对象……','正常',3,'2020-03-01 00:56:25',48361,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(156,299,NULL,'2020-03-01 12:19:54',NULL,1,'一起打游戏','正常',43,'2020-03-01 12:19:54',48226,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(157,408,NULL,'2020-03-01 12:20:18',NULL,1,'会吃醋','正常',78,'2020-03-01 12:20:18',48226,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(158,983,NULL,'2020-03-01 16:19:00',NULL,1,'陪聊','正常',135,'2020-03-01 16:19:00',47652,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(159,776,NULL,'2020-03-01 16:46:58',NULL,1,'腹肌','正常',63,'2020-03-01 16:46:58',48964,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(160,3789,NULL,'2020-03-01 18:22:45',NULL,21,'同城','正常',284,'2020-03-01 18:22:45',49072,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(161,429,NULL,'2020-03-02 06:54:52',NULL,30,'同岁','正常',4,'2020-03-02 06:54:52',49711,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(162,41,NULL,'2020-03-02 06:55:34',NULL,1,'活泼开朗','正常',4,'2020-03-02 06:55:34',49711,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(163,49,NULL,'2020-03-02 07:03:31',NULL,14,'肖战','正常',0,'2020-03-02 07:03:31',47089,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(164,94,NULL,'2020-03-02 07:03:52',NULL,14,'肖战哥哥','正常',1,'2020-03-02 07:03:52',47089,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(165,299,NULL,'2020-03-02 07:04:26',NULL,2,'封B站','正常',1,'2020-03-02 07:04:26',47089,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(166,9,NULL,'2020-03-02 07:04:47',NULL,2,'封猫耳','正常',0,'2020-03-02 07:04:47',47089,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(167,9,NULL,'2020-03-02 07:05:14',NULL,2,'墙AO3','正常',0,'2020-03-02 07:05:14',47089,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(168,13,NULL,'2020-03-02 07:05:37',NULL,2,'封老福特','正常',0,'2020-03-02 07:05:37',47089,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(169,84,NULL,'2020-03-02 11:01:09',NULL,1,'处对象  真心谈','违规',0,'2020-03-02 11:01:09',49741,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(170,38,NULL,'2020-03-02 11:13:29',NULL,1,'运动','正常',8,'2020-03-02 11:13:29',42253,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(171,2453,NULL,'2020-03-02 12:23:48',NULL,22,'双性恋','正常',119,'2020-03-02 12:23:48',49906,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(172,15,NULL,'2020-03-02 15:20:02',NULL,1,'没有话题就是最好的话题','违规',0,'2020-03-02 15:20:02',50151,'心情',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(173,68,NULL,'2020-03-02 16:12:19',NULL,1,'超甜','正常',7,'2020-03-02 16:12:19',50076,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(174,1875,NULL,'2020-03-02 19:43:33',NULL,1,'处炮友','违规',0,'2020-03-02 19:43:33',49165,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(175,413,NULL,'2020-03-03 09:46:10',NULL,1,'05后处对象➕','违规',0,'2020-03-03 09:46:10',28203,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(176,166,NULL,'2020-03-03 15:34:59',NULL,1,'甜甜的恋爱','正常',19,'2020-03-03 15:34:59',51551,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(177,122,NULL,'2020-03-03 15:35:28',NULL,28,'游戏陪玩','正常',10,'2020-03-03 15:35:28',51551,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(178,40,NULL,'2020-03-03 15:36:47',NULL,1,'有趣的灵魂','正常',3,'2020-03-03 15:36:47',51551,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(179,103,NULL,'2020-03-03 18:30:26',NULL,1,'沙雕','正常',20,'2020-03-03 18:30:26',49858,'活泼开朗，不是污的，很很很会让朋友开心',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(180,362,NULL,'2020-03-03 19:28:04',NULL,30,'本人20岁','正常',6,'2020-03-03 19:28:04',51701,'一个找想真心相爱的女孩  年龄17～21',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(181,891,NULL,'2020-03-04 03:53:19',NULL,30,'95后','正常',22,'2020-03-04 03:53:19',52502,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(182,152,NULL,'2020-03-04 09:01:37',NULL,1,'认真专一','正常',66,'2020-03-04 09:01:37',48587,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(183,91,NULL,'2020-03-04 13:35:52',NULL,1,'互动谈心','正常',14,'2020-03-04 13:35:52',52905,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(184,387,NULL,'2020-03-04 19:06:16',NULL,26,'篮球','正常',32,'2020-03-04 19:06:16',53440,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(185,937,NULL,'2020-03-04 21:52:44',NULL,1,'不颜控','正常',74,'2020-03-04 21:52:44',53653,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(186,85,NULL,'2020-03-05 03:01:14',NULL,1,'抑郁症','正常',2,'2020-03-05 03:01:14',54087,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(187,1850,NULL,'2020-03-05 03:01:41',NULL,2,'乱。','正常',143,'2020-03-05 03:01:41',54087,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(188,55,NULL,'2020-03-05 03:02:22',NULL,2,'思想复杂','正常',2,'2020-03-05 03:02:22',54087,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(189,537,NULL,'2020-03-05 21:20:02',NULL,1,'找基友','正常',8,'2020-03-05 21:20:02',29541,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(190,264,NULL,'2020-03-05 21:20:31',NULL,1,'找闺蜜','正常',29,'2020-03-05 21:20:31',29541,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(191,438,NULL,'2020-03-06 12:48:51',NULL,1,'小奶狗','正常',40,'2020-03-06 12:48:51',55517,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(192,754,NULL,'2020-03-06 14:29:56',NULL,1,'腿','正常',34,'2020-03-06 14:29:56',55594,'丝袜',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(193,166,NULL,'2020-03-06 19:04:04',NULL,1,'处对象啊','正常',8,'2020-03-06 19:04:04',6187,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(194,65,NULL,'2020-03-06 22:48:01',NULL,1,'帅锅锅','正常',3,'2020-03-06 22:48:01',46792,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(195,226,NULL,'2020-03-06 22:51:57',NULL,1,'不玩游戏','正常',21,'2020-03-06 22:51:57',56105,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(196,6221,NULL,'2020-03-07 13:13:07',NULL,30,'05后+','正常',376,'2020-03-07 13:13:07',56731,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(197,893,NULL,'2020-03-07 14:14:12',NULL,1,'聊so','已删除',0,'2020-03-07 14:14:12',56826,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(198,179,NULL,'2020-03-07 15:42:59',NULL,1,'00处对','正常',9,'2020-03-07 15:42:59',56962,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(199,93,NULL,'2020-03-08 00:16:45',NULL,1,'小奶狗小','正常',4,'2020-03-08 00:16:45',57622,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(200,54,NULL,'2020-03-08 11:05:24',NULL,1,'互赞','正常',7,'2020-03-08 11:05:24',28548,'暖空间',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(201,34,NULL,'2020-03-08 15:07:13',NULL,26,'健身','正常',1,'2020-03-08 15:07:13',58427,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(202,154,NULL,'2020-03-08 18:45:17',NULL,28,'爱玩游戏','正常',15,'2020-03-08 18:45:17',58862,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(203,126,NULL,'2020-03-08 19:12:36',NULL,1,'零零后','正常',9,'2020-03-08 19:12:36',58893,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(204,124,NULL,'2020-03-08 22:16:05',NULL,22,'恋爱嘛','正常',16,'2020-03-08 22:16:05',59334,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(205,25,NULL,'2020-03-08 22:32:41',NULL,1,'。','正常',1,'2020-03-08 22:32:41',58657,'。',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(206,212,NULL,'2020-03-08 22:35:23',NULL,1,'陪睡','违规',0,'2020-03-08 22:35:23',58657,'。',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(207,1025,NULL,'2020-03-08 23:25:10',NULL,32,'集美','正常',23,'2020-03-08 23:25:10',58657,'。',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','女',2,'\0','girl'),

(208,104,NULL,'2020-03-08 23:50:51',NULL,2,'无聊','正常',7,'2020-03-08 23:50:51',59578,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(209,85,NULL,'2020-03-08 23:52:10',NULL,1,'活泼可爱','正常',11,'2020-03-08 23:52:10',59623,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(210,133,NULL,'2020-03-09 08:19:11',NULL,27,'动漫','正常',9,'2020-03-09 08:19:11',58416,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(211,43,NULL,'2020-03-09 09:47:00',NULL,16,'古诗词','正常',1,'2020-03-09 09:47:00',60162,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(212,476,NULL,'2020-03-09 11:07:03',NULL,1,'有腹肌','正常',35,'2020-03-09 11:07:03',60289,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(213,188,NULL,'2020-03-09 11:08:04',NULL,1,'霸道','正常',16,'2020-03-09 11:08:04',60289,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(214,1767,NULL,'2020-03-09 11:37:57',NULL,1,'富婆','正常',47,'2020-03-09 11:37:57',30612,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(215,334,NULL,'2020-03-09 19:47:32',NULL,1,'文❤️','已删除',0,'2020-03-09 19:47:32',61448,'就是这么直接',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(216,204,NULL,'2020-03-09 21:32:41',NULL,2,'彩虹','正常',3,'2020-03-09 21:32:41',36118,'?',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(217,218,NULL,'2020-03-09 22:11:40',NULL,1,'连麦','正常',27,'2020-03-09 22:11:40',10067,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(218,93,NULL,'2020-03-10 12:39:32',NULL,1,'处cp嗯','正常',2,'2020-03-10 12:39:32',62114,'发现附近的你',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(219,90,NULL,'2020-03-10 21:36:08',NULL,1,'户看户看','违规',0,'2020-03-10 21:36:08',63322,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(220,4,NULL,'2020-03-10 23:59:21',NULL,1,'陕西省0','违规',0,'2020-03-10 23:59:21',63573,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(221,220,NULL,'2020-03-10 23:59:37',NULL,21,'陕西西安','正常',1,'2020-03-10 23:59:37',63573,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(222,48,NULL,'2020-03-11 09:06:40',NULL,1,'要话多的','正常',4,'2020-03-11 09:06:40',60065,'要话多说不完的',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(223,109,NULL,'2020-03-11 11:19:00',NULL,1,'渣男','正常',1,'2020-03-11 11:19:00',54165,'渣男',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(224,1961,NULL,'2020-03-11 12:30:31',NULL,1,'父女恋','正常',125,'2020-03-11 12:30:31',64072,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(225,116,NULL,'2020-03-12 09:52:51',NULL,21,'长春','正常',1,'2020-03-12 09:52:51',65430,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(226,13,NULL,'2020-03-12 10:23:43',NULL,14,'貂蝉','正常',0,'2020-03-12 10:23:43',39810,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(227,39,NULL,'2020-03-13 12:41:20',NULL,1,'快手','正常',0,'2020-03-13 12:41:20',47714,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(228,78,NULL,'2020-03-13 13:45:50',NULL,2,'我的世界','正常',10,'2020-03-13 13:45:50',26690,'游戏',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(229,212,NULL,'2020-03-14 17:20:28',NULL,1,'卖妹妹了','正常',1,'2020-03-14 17:20:28',58079,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(230,796,NULL,'2020-03-15 08:31:01',NULL,1,'找弟弟','正常',29,'2020-03-15 08:31:01',67153,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(231,207,NULL,'2020-03-15 17:10:49',NULL,1,'福利','违规',0,'2020-03-15 17:10:49',53766,'红包群',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(232,35,NULL,'2020-03-15 17:11:15',NULL,1,'红包','违规',0,'2020-03-15 17:11:15',53766,'红包群',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(233,311,NULL,'2020-03-15 22:01:28',NULL,1,'要爆照','正常',30,'2020-03-15 22:01:28',67320,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(234,49,NULL,'2020-03-16 23:19:36',NULL,1,'面基','正常',7,'2020-03-16 23:19:36',72646,'线下见面',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(235,63,NULL,'2020-03-18 00:30:11',NULL,23,'电影剧情','正常',1,'2020-03-18 00:30:11',68733,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(236,161,NULL,'2020-03-18 10:22:32',NULL,22,'随便cp','正常',4,'2020-03-18 10:22:32',74881,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(237,66,NULL,'2020-03-18 17:21:00',NULL,1,'处','正常',1,'2020-03-18 17:21:00',62457,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(238,198,NULL,'2020-03-18 21:42:03',NULL,1,'求金主','正常',9,'2020-03-18 21:42:03',51446,'额额额',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(239,13,NULL,'2020-03-19 00:10:13',NULL,4,'追剧秀','正常',0,'2020-03-19 00:10:13',68733,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(240,14,NULL,'2020-03-19 11:38:06',NULL,15,'球鞋','正常',0,'2020-03-19 11:38:06',76978,'喜欢球鞋，并且也喜欢篮球，也喜欢球鞋文化',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(241,262,NULL,'2020-03-19 13:11:30',NULL,1,'可狼可奶','正常',82,'2020-03-19 13:11:30',77094,'小奶狗可奶可狼，',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(242,45,NULL,'2020-03-20 12:46:34',NULL,1,'招女婿','正常',0,'2020-03-20 12:46:34',60320,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(243,69,NULL,'2020-03-20 18:36:16',NULL,1,'可爱','正常',5,'2020-03-20 18:36:16',75573,'本人14想找一个可爱的小姐姐13一16',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(244,20,NULL,'2020-03-21 07:53:46',NULL,1,'合意网友','正常',1,'2020-03-21 07:53:46',80308,'寻找失散多年的兄弟，giao',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(245,101,NULL,'2020-03-21 13:52:41',NULL,1,'交友扩列','正常',27,'2020-03-21 13:52:41',78294,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(246,188,NULL,'2020-03-21 18:13:16',NULL,1,'拉拉交处','正常',16,'2020-03-21 18:13:16',81065,'拉拉创建处，是志同道合的姐妹们，就一起加入我们吧…??总有一片天是属于自由的，属于我们拉拉的⛺',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(247,8,NULL,'2020-03-21 18:26:45',NULL,1,'LES界','正常',0,'2020-03-21 18:26:45',81065,'同为LES的你还等什么，这里是属于我们的场合???，彩虹一般的存在为何惧怕其他因素带给你不好的思想??',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(248,39,NULL,'2020-03-22 01:23:01',NULL,1,'唠嗑','正常',7,'2020-03-22 01:23:01',67139,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(249,53,NULL,'2020-03-22 09:49:43',NULL,1,'女神野王','正常',1,'2020-03-22 09:49:43',81861,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(250,120,NULL,'2020-03-22 09:50:18',NULL,1,'颜值声优','正常',6,'2020-03-22 09:50:18',81861,'高颜值，声优',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(251,40,NULL,'2020-03-22 19:34:55',NULL,1,'深入了解','违规',0,'2020-03-22 19:34:55',82538,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(252,55,NULL,'2020-03-23 18:31:03',NULL,1,'拜师','正常',5,'2020-03-23 18:31:03',74397,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(253,126,NULL,'2020-03-23 19:27:35',NULL,30,'20下加','正常',2,'2020-03-23 19:27:35',6909,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(254,194,NULL,'2020-03-23 19:28:46',NULL,1,'连麦睡觉','正常',22,'2020-03-23 19:28:46',73039,'加上好友连麦聊天睡觉',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(255,46,NULL,'2020-03-24 14:09:57',NULL,14,'秦霄贤','正常',1,'2020-03-24 14:09:57',36072,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(256,128,NULL,'2020-03-24 19:25:41',NULL,2,'各种控','正常',9,'2020-03-24 19:25:41',86141,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(257,126,NULL,'2020-03-25 00:14:29',NULL,22,'直男','正常',12,'2020-03-25 00:14:29',86778,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(258,23,NULL,'2020-03-25 08:58:54',NULL,14,'欧文','正常',0,'2020-03-25 08:58:54',87038,'欧文球迷',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(259,20,NULL,'2020-03-25 08:59:17',NULL,26,'NBA','正常',1,'2020-03-25 08:59:17',87038,'NBAstar',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(260,44,NULL,'2020-03-25 11:38:19',NULL,1,'快手互粉','正常',4,'2020-03-25 11:38:19',67880,'快手互粉',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(261,11,NULL,'2020-03-26 07:02:01',NULL,2,'前程似锦','正常',1,'2020-03-26 07:02:01',88776,'社会没有遮天树，只有一物降一物',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(262,82,NULL,'2020-03-26 15:42:04',NULL,18,'中考','正常',2,'2020-03-26 15:42:04',59255,'学习',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(263,8,NULL,'2020-03-26 18:21:40',NULL,2,'你好','正常',0,'2020-03-26 18:21:40',89769,'我14岁，想找一个宠我，不喝酒，不蹦迪，不对我发脾气，能给我安全感，我比较粘人。\n愿意的话加我QQ\n1548348985',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(264,72,NULL,'2020-03-27 04:12:53',NULL,22,'感情专一','正常',12,'2020-03-27 04:12:53',12260,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(265,9,NULL,'2020-03-27 10:21:59',NULL,29,'##官方','正常',0,'2020-03-27 10:21:59',90320,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(266,159,NULL,'2020-03-27 12:25:41',NULL,22,'希望奔现','正常',8,'2020-03-27 12:25:41',90844,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(267,13,NULL,'2020-03-27 12:43:56',NULL,1,'SM','违规',0,'2020-03-27 12:43:56',84666,'SM',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(268,40,NULL,'2020-03-27 15:41:58',NULL,31,'天蝎','正常',1,'2020-03-27 15:41:58',91131,'小蝎子',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(269,20,NULL,'2020-03-27 20:49:53',NULL,1,'家族招人','正常',1,'2020-03-27 20:49:53',84399,'进家族吗集美们，心灼家族，是一个新的家族哦，只有大家的努力，我们才会越来越好，家族的意义是什么呢，是你的避风港，你背后的势力，当有人欺负你的时候，我们会给予你帮助，在这里，就是你的家，不过，家有家规，还是得遵守的，想要高管活跃就好，哦，对了，前提想加入的话必须改名哦，不是群名，必须是你QQ的昵称，格式：心灼⋆＋网名，欢迎各位的加入趴，必须等级一个月亮哦，加我QQ946480898，我会给你指导的。',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(270,2044,NULL,'2020-03-27 20:56:41',NULL,1,'555','已删除',124,'2020-03-27 20:56:41',86858,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(271,278,NULL,'2020-03-27 22:21:50',NULL,22,'奔现','正常',26,'2020-03-27 22:21:50',91722,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(272,85,NULL,'2020-03-28 09:50:49',NULL,30,'02后','正常',0,'2020-03-28 09:50:49',92134,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(273,96,NULL,'2020-03-28 10:01:55',NULL,1,'找弟弟妹','正常',3,'2020-03-28 10:01:55',92139,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(274,279,NULL,'2020-03-28 10:02:47',NULL,30,'比我小','正常',7,'2020-03-28 10:02:47',92139,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(275,99,NULL,'2020-03-28 11:44:54',NULL,28,'吃鸡苟分','正常',7,'2020-03-28 11:44:54',42732,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(276,335,NULL,'2020-03-28 20:32:59',NULL,21,'广东广州','正常',6,'2020-03-28 20:32:59',50985,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(277,4066,NULL,'2020-03-28 20:33:16',NULL,1,'同性','违规',356,'2020-03-28 20:33:16',75903,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(278,3,NULL,'2020-03-28 21:45:37',NULL,28,'量子特工','正常',0,'2020-03-28 21:45:37',93044,'玩量子的',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(279,69,NULL,'2020-03-28 22:06:47',NULL,1,'师父','正常',4,'2020-03-28 22:06:47',54263,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(280,14,NULL,'2020-03-28 22:23:21',NULL,1,'志同道合','正常',1,'2020-03-28 22:23:21',93089,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(281,3,NULL,'2020-03-28 22:56:53',NULL,1,'儿砸','违规',0,'2020-03-28 22:56:53',90035,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(282,307,NULL,'2020-03-29 15:43:12',NULL,1,'找师傅','正常',41,'2020-03-29 15:43:12',93884,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(283,9,NULL,'2020-03-29 18:22:19',NULL,28,'xf手游','正常',0,'2020-03-29 18:22:19',93124,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(284,98,NULL,'2020-03-29 18:22:53',NULL,28,'cf手游','正常',5,'2020-03-29 18:22:53',93124,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(285,120,NULL,'2020-03-29 19:03:37',NULL,21,'江西','正常',1,'2020-03-29 19:03:37',94109,'江西',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(286,11,NULL,'2020-03-29 20:11:41',NULL,12,'购物','正常',0,'2020-03-29 20:11:41',94208,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(287,21,NULL,'2020-03-30 00:07:59',NULL,2,'emmm','正常',4,'2020-03-30 00:07:59',94589,'来者不拒',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(288,7,NULL,'2020-03-30 16:37:11',NULL,2,'黑暗','正常',0,'2020-03-30 16:37:11',85249,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(289,11,NULL,'2020-03-30 17:41:16',NULL,2,'蘑菇头','正常',0,'2020-03-30 17:41:16',79818,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(290,923,NULL,'2020-03-30 18:30:58',NULL,30,'2006','正常',26,'2020-03-30 18:30:58',95275,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(291,81,NULL,'2020-03-30 21:10:12',NULL,27,'火影忍者','正常',6,'2020-03-30 21:10:12',95969,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(292,25,NULL,'2020-03-31 08:42:13',NULL,26,'喜欢跑步','正常',3,'2020-03-31 08:42:13',96583,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(293,644,NULL,'2020-03-31 15:15:39',NULL,1,'不爆照','正常',103,'2020-03-31 15:15:39',97129,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(294,18,NULL,'2020-04-01 00:19:22',NULL,1,'非声控','正常',0,'2020-04-01 00:19:22',83344,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(295,15,NULL,'2020-04-01 17:33:08',NULL,1,'暖空间','正常',2,'2020-04-01 17:33:08',28548,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(296,98,NULL,'2020-04-01 21:21:34',NULL,1,'暖群','正常',0,'2020-04-01 21:21:34',88512,'在群里多发言',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(297,535,NULL,'2020-04-01 23:19:09',NULL,1,'不互看','正常',20,'2020-04-01 23:19:09',99832,'不互看',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(298,228,NULL,'2020-04-01 23:27:06',NULL,21,'广西','正常',1,'2020-04-01 23:27:06',99840,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(299,55,NULL,'2020-04-01 23:28:18',NULL,1,'可盐可甜','正常',14,'2020-04-01 23:28:18',99844,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(300,96,NULL,'2020-04-02 10:36:22',NULL,1,'#找弟弟','正常',1,'2020-04-02 10:36:22',99648,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(301,86,NULL,'2020-04-02 13:31:05',NULL,1,'要小哥哥','正常',3,'2020-04-02 13:31:05',9752,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(302,201,NULL,'2020-04-02 15:28:18',NULL,27,'玩cos','正常',5,'2020-04-02 15:28:18',94109,'cos',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(303,22,NULL,'2020-04-03 05:09:42',NULL,1,'找老乡','正常',4,'2020-04-03 05:09:42',101726,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(304,51,NULL,'2020-04-03 07:59:31',NULL,1,'卖闺蜜','正常',3,'2020-04-03 07:59:31',101467,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(305,73,NULL,'2020-04-03 15:30:49',NULL,28,'游戏','正常',11,'2020-04-03 15:30:49',102472,'找个小姐姐打游戏开麦',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(306,20,NULL,'2020-04-03 21:09:19',NULL,18,'高考文科','正常',2,'2020-04-03 21:09:19',102856,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(307,199,NULL,'2020-04-04 14:48:11',NULL,1,'cp男男','正常',6,'2020-04-04 14:48:11',73468,'男攻',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(308,38,NULL,'2020-04-04 20:37:50',NULL,1,'音色好听','正常',4,'2020-04-04 20:37:50',105554,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(309,108,NULL,'2020-04-06 10:50:21',NULL,1,'找爸爸','违规',0,'2020-04-06 10:50:21',73346,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(310,20,NULL,'2020-04-06 14:36:08',NULL,1,'出来玩','正常',3,'2020-04-06 14:36:08',108575,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(311,68,NULL,'2020-04-07 13:58:53',NULL,2,'兼职赚米','正常',12,'2020-04-07 13:58:53',110230,'一起赚票票',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(312,29,NULL,'2020-04-07 21:45:44',NULL,2,'明日之后','正常',3,'2020-04-07 21:45:44',110902,'一款游戏',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(313,119,NULL,'2020-04-08 01:12:02',NULL,1,'很丑','正常',5,'2020-04-08 01:12:02',111407,'本人脾气臭，脾气臭，长得丑，爱生气有时候有时候笨的',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(314,85,NULL,'2020-04-08 09:10:41',NULL,1,'有Q自加','正常',18,'2020-04-08 09:10:41',111594,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(315,25,NULL,'2020-04-08 22:59:42',NULL,2,'只玩迷你','正常',1,'2020-04-08 22:59:42',113266,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(316,17,NULL,'2020-04-09 13:20:17',NULL,2,'暖','正常',3,'2020-04-09 13:20:17',103396,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(317,194,NULL,'2020-04-10 17:20:46',NULL,1,'找姐姐','正常',28,'2020-04-10 17:20:46',115971,'，，',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(318,78,NULL,'2020-04-10 21:41:17',NULL,1,'比较黏人','正常',18,'2020-04-10 21:41:17',85783,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(319,118,NULL,'2020-04-11 00:02:48',NULL,1,'找女徒弟','正常',9,'2020-04-11 00:02:48',73346,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(320,23,NULL,'2020-04-12 18:47:44',NULL,1,'德云女孩','正常',2,'2020-04-12 18:47:44',105188,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(321,29,NULL,'2020-04-12 23:05:18',NULL,1,'游戏cp','正常',2,'2020-04-12 23:05:18',107509,'只一起打游戏当游戏中是情侣',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(322,4367,NULL,'2020-04-13 09:36:21',NULL,30,'16以下','正常',270,'2020-04-13 09:36:21',110743,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(323,46,NULL,'2020-04-13 09:38:24',NULL,1,'有点颜控','正常',11,'2020-04-13 09:38:24',110743,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(324,137,NULL,'2020-04-13 10:27:37',NULL,1,'微信','正常',14,'2020-04-13 10:27:37',111594,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(325,739,NULL,'2020-04-13 11:41:34',NULL,1,'找女儿','正常',104,'2020-04-13 11:41:34',76118,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(326,17,NULL,'2020-04-13 13:45:27',NULL,28,'王者代打','正常',2,'2020-04-13 13:45:27',117037,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(327,48,NULL,'2020-04-13 17:14:26',NULL,1,'追星','正常',10,'2020-04-13 17:14:26',119223,'?本哥',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(328,84,NULL,'2020-04-14 17:05:45',NULL,1,'可奶可狼','正常',20,'2020-04-14 17:05:45',116239,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(329,218,NULL,'2020-04-14 17:20:41',NULL,1,'jk','正常',22,'2020-04-14 17:20:41',119390,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(330,34,NULL,'2020-04-17 06:24:32',NULL,1,'不处对象','正常',11,'2020-04-17 06:24:32',98549,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(331,39,NULL,'2020-04-17 08:36:26',NULL,1,'社会人','正常',0,'2020-04-17 08:36:26',75005,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(332,48,NULL,'2020-04-17 16:42:55',NULL,1,'徒弟','正常',6,'2020-04-17 16:42:55',107171,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(333,128,NULL,'2020-04-18 06:55:19',NULL,1,'语音聊天','正常',22,'2020-04-18 06:55:19',125314,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(334,179,NULL,'2020-04-18 11:04:22',NULL,1,'wu对象','违规',0,'2020-04-18 11:04:22',125482,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(335,49,NULL,'2020-04-18 22:46:28',NULL,1,'有微自➕','正常',4,'2020-04-18 22:46:28',125314,'有微信的可以自行添加',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(336,338,NULL,'2020-04-19 09:42:44',NULL,30,'06年','正常',14,'2020-04-19 09:42:44',95275,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(337,462,NULL,'2020-04-20 18:20:44',NULL,1,'小萝莉','正常',39,'2020-04-20 18:20:44',74432,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(338,38,NULL,'2020-04-20 22:34:15',NULL,1,'处主仆','违规',0,'2020-04-20 22:34:15',127700,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(339,16,NULL,'2020-04-24 20:37:27',NULL,1,'养火花','正常',6,'2020-04-24 20:37:27',81447,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(340,140,NULL,'2020-04-25 01:48:23',NULL,12,'赚钱','正常',12,'2020-04-25 01:48:23',38269,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(341,61,NULL,'2020-04-25 01:51:22',NULL,1,'汉服','正常',14,'2020-04-25 01:51:22',64606,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(342,20,NULL,'2020-04-26 05:49:08',NULL,1,'不打遊戲','正常',6,'2020-04-26 05:49:08',82719,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(343,9,NULL,'2020-04-26 05:49:08',NULL,1,'不打遊戲1','正常',7,'2020-04-26 05:49:08',82719,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(344,26,NULL,'2020-04-26 17:14:35',NULL,1,'奶狗','正常',6,'2020-04-26 17:14:35',132471,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(345,42,NULL,'2020-04-27 17:47:04',NULL,9,'说唱','正常',7,'2020-04-27 17:47:04',136534,'嘻哈音乐',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(346,2109,NULL,'2020-04-29 03:02:02',NULL,1,'互看姐姐','违规',105,'2020-04-29 03:02:02',138110,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(347,6,NULL,'2020-04-29 19:41:43',NULL,2,'仓鼠宠物','正常',1,'2020-04-29 19:41:43',79902,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(348,163,NULL,'2020-04-29 22:47:39',NULL,22,'爱媳妇儿','正常',12,'2020-04-29 22:47:39',95761,'有主人的男生。',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(349,24,NULL,'2020-04-30 00:09:43',NULL,1,'喜欢暖男','正常',6,'2020-04-30 00:09:43',139210,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(350,20,NULL,'2020-05-02 09:54:25',NULL,22,'情感咨询','正常',2,'2020-05-02 09:54:25',142734,'情感陪聊，诉说情感，解决情感问题，咨询心理问题，15元一个问题',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(351,16,NULL,'2020-05-03 03:05:07',NULL,1,'处陪伴','正常',8,'2020-05-03 03:05:07',144092,'陪伴你我，让世界充满爱',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(352,51,NULL,'2020-05-04 05:55:49',NULL,16,'人间失格','正常',1,'2020-05-04 05:55:49',144717,'人世',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(353,17,NULL,'2020-05-04 10:41:17',NULL,1,'姐姐哥哥','正常',6,'2020-05-04 10:41:17',109282,'找哥哥姐姐学习好的',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(354,17,NULL,'2020-05-09 18:59:14',NULL,1,'不渣','正常',12,'2020-05-09 18:59:14',150893,'要专一，不要渣男',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(355,8,NULL,'2020-05-13 19:50:35',NULL,1,'找小哥哥6','正常',1,'2020-05-13 19:50:35',134032,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(356,9,NULL,'2020-05-13 19:50:35',NULL,1,'找小哥哥5','正常',1,'2020-05-13 19:50:35',134032,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(357,9,NULL,'2020-05-13 19:50:35',NULL,1,'找小哥哥4','正常',4,'2020-05-13 19:50:35',134032,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(358,9,NULL,'2020-05-13 19:50:35',NULL,1,'找小哥哥3','正常',3,'2020-05-13 19:50:35',134032,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(359,8,NULL,'2020-05-13 19:50:35',NULL,1,'找小哥哥2','正常',0,'2020-05-13 19:50:35',134032,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(360,13,NULL,'2020-05-13 19:50:35',NULL,1,'找小哥哥1','正常',3,'2020-05-13 19:50:35',134032,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(361,17,NULL,'2020-05-13 19:50:35',NULL,1,'找小哥哥','正常',3,'2020-05-13 19:50:35',134032,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(362,539,NULL,'2020-05-13 23:46:25',NULL,1,'腐女','正常',33,'2020-05-13 23:46:25',101517,'女生喜欢和男生玩。',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(363,12,NULL,'2020-05-14 16:36:05',NULL,1,'会唱歌的','正常',6,'2020-05-14 16:36:05',153410,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(364,162,NULL,'2020-05-15 04:00:40',NULL,1,'我是处男','正常',7,'2020-05-15 04:00:40',153690,'我也挺难',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(365,28,NULL,'2020-05-16 19:25:16',NULL,28,'打吃鸡','正常',6,'2020-05-16 19:25:16',32292,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(366,13,NULL,'2020-05-17 14:59:27',NULL,1,'处cp别','正常',0,'2020-05-17 14:59:27',155519,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(367,10,NULL,'2020-05-19 07:00:24',NULL,22,'520','正常',2,'2020-05-19 07:00:24',29711,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(368,60,NULL,'2020-05-21 14:18:48',NULL,9,'网易云','正常',7,'2020-05-21 14:18:48',157377,'网易云音乐',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(369,6,NULL,'2020-05-22 23:32:28',NULL,12,'广告','正常',2,'2020-05-22 23:32:28',108,'商业广告',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(370,21,NULL,'2020-05-23 23:12:28',NULL,21,'幸运字符','正常',1,'2020-05-23 23:12:28',99398,'一起来抽字符刷字符啊',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(371,15,NULL,'2020-05-25 01:31:40',NULL,1,'找嫂子啊','正常',1,'2020-05-25 01:31:40',121684,'快快快',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(372,4,NULL,'2020-05-25 01:31:41',NULL,1,'找嫂子啊1','正常',0,'2020-05-25 01:31:41',121684,'快快快',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(373,5,NULL,'2020-05-28 19:13:45',NULL,1,'小螃蟹','正常',1,'2020-05-28 19:13:45',161013,'只有四叶草和王俊凯的粉丝可参与的话题',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(374,5,NULL,'2020-05-28 19:22:09',NULL,24,'王俊凯','正常',0,'2020-05-28 19:22:09',161013,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(375,18,NULL,'2020-05-29 16:58:25',NULL,1,'求by','正常',1,'2020-05-29 16:58:25',92021,'by',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(376,6,NULL,'2020-05-30 13:23:35',NULL,1,'ghs','违规',1,'2020-05-30 13:23:35',104443,'ghs',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(377,272,NULL,'2020-05-31 09:57:56',NULL,22,'可以奔现','正常',85,'2020-05-31 09:57:56',50985,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(379,9,NULL,'2020-05-31 20:40:48',NULL,1,'找兄弟','正常',4,'2020-05-31 20:40:48',162659,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(380,378,NULL,'2020-06-01 01:35:13',NULL,30,'05后','正常',24,'2020-06-01 01:35:13',26613,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(381,3,NULL,'2020-06-01 21:23:57',NULL,2,'分享穿搭','正常',1,'2020-06-01 21:23:57',154256,'有的小姐妹需要，逛街买衣服太麻烦，网上买衣服又不怕，所以只能来分享穿搭',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(382,0,NULL,'2020-06-01 21:23:57',NULL,2,'分享穿搭1','已删除',0,'2020-06-01 21:23:57',154256,'有的小姐妹需要，逛街买衣服太麻烦，网上买衣服又不怕，所以只能来分享穿搭',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(383,95,NULL,'2020-06-01 21:43:02',NULL,22,'找对象','正常',4,'2020-06-01 21:43:02',160403,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(384,4,NULL,'2020-06-02 05:00:56',NULL,1,'m系列','违规',1,'2020-06-02 05:00:56',163226,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(385,13,NULL,'2020-06-02 11:16:04',NULL,1,'声控处对','正常',4,'2020-06-02 11:16:04',163318,'13声控处对象，14.15',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(386,30,NULL,'2020-06-03 15:40:31',NULL,1,'可以爆照','正常',6,'2020-06-03 15:40:31',160403,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(387,27,NULL,'2020-06-04 03:12:02',NULL,1,'病娇','正常',5,'2020-06-04 03:12:02',106515,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(388,0,NULL,'2020-06-09 23:45:18',NULL,1,'测试','已删除',0,'2020-06-09 23:45:18',108,'急急急',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(389,21,NULL,'2020-06-13 01:50:06',NULL,1,'文案','正常',9,'2020-06-13 01:50:06',108,'好玩的文案',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(390,16,NULL,'2020-06-15 12:20:52',NULL,1,'找徒弟','正常',10,'2020-06-15 12:20:52',35076,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(391,120,NULL,'2020-06-16 01:03:05',NULL,1,'看文','正常',5,'2020-06-16 01:03:05',166117,'看文交流',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(392,13,NULL,'2020-06-18 21:00:03',NULL,1,'处姐姐','正常',6,'2020-06-18 21:00:03',168659,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(393,14,NULL,'2020-06-20 16:32:17',NULL,1,'加好友','正常',7,'2020-06-20 16:32:17',169608,'1492966928QQ',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(394,24,NULL,'2020-06-20 18:11:36',NULL,1,'宠我？','正常',13,'2020-06-20 18:11:36',75429,'喜欢我，陪着我，爱我，不辜负我——',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(395,5,NULL,'2020-06-21 21:20:58',NULL,1,'养巨轮','正常',2,'2020-06-21 21:20:58',75429,'就是一起说话',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(396,4,NULL,'2020-06-25 11:21:13',NULL,1,'原相机','正常',3,'2020-06-25 11:21:13',137854,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(397,13,NULL,'2020-06-25 17:05:43',NULL,1,'我很霸道','正常',4,'2020-06-25 17:05:43',75429,'听我的',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(398,2,NULL,'2020-06-25 19:23:28',NULL,1,'＃冲＃','正常',2,'2020-06-25 19:23:28',172259,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(399,2,NULL,'2020-06-26 14:07:51',NULL,1,'卑微','正常',2,'2020-06-26 14:07:51',172259,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(400,11,NULL,'2020-06-28 01:08:08',NULL,1,'王者CP','正常',3,'2020-06-28 01:08:08',81363,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(401,1,NULL,'2020-06-28 03:02:47',NULL,1,'双子座','正常',1,'2020-06-28 03:02:47',173803,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(402,2,NULL,'2020-06-28 16:43:44',NULL,1,'平凡人','正常',2,'2020-06-28 16:43:44',172503,'平凡的世界平凡的你',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(403,28,NULL,'2020-06-28 22:09:42',NULL,1,'大叔','正常',13,'2020-06-28 22:09:42',174254,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(404,0,NULL,'2020-06-29 14:02:30',NULL,1,'时候啊！','正常',0,'2020-06-29 14:02:30',174530,'没啥',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(405,7,NULL,'2020-07-04 10:57:55',NULL,1,'颜控声控','正常',6,'2020-07-04 10:57:55',175474,'颜值高并且声音好听',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(406,14,NULL,'2020-07-04 11:01:49',NULL,1,'颜值声控','正常',9,'2020-07-04 11:01:49',175474,'颜值高并且声音好听',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(407,4,NULL,'2020-07-07 14:29:56',NULL,1,'没有qq','正常',3,'2020-07-07 14:29:56',177821,'没有qq',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(408,3,NULL,'2020-07-07 17:21:07',NULL,1,'月老','正常',1,'2020-07-07 17:21:07',177821,'月老服务',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(409,2,NULL,'2020-07-07 17:21:20',NULL,1,'红娘','正常',2,'2020-07-07 17:21:20',177821,'红娘',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(410,5,NULL,'2020-07-07 17:21:39',NULL,1,'不花钱','正常',3,'2020-07-07 17:21:39',177821,'不花钱',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(411,12,NULL,'2020-07-08 12:14:30',NULL,1,'找闺蜜。','正常',6,'2020-07-08 12:14:30',178393,'14至16岁。我跟你熟了之后有点儿烦。别嫌弃。',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(412,8,NULL,'2020-07-08 15:43:10',NULL,1,'18岁','正常',4,'2020-07-08 15:43:10',178565,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(413,14,NULL,'2020-07-09 00:52:22',NULL,1,'女闺蜜','正常',4,'2020-07-09 00:52:22',178929,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(414,7,NULL,'2020-07-11 18:01:29',NULL,1,'小仙女','正常',4,'2020-07-11 18:01:29',166993,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(415,30,NULL,'2020-07-11 20:07:11',NULL,1,'聊群','正常',8,'2020-07-11 20:07:11',177821,'聊群',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(416,7,NULL,'2020-07-11 20:07:32',NULL,1,'单身狗的','正常',3,'2020-07-11 20:07:32',177821,'哎嘿嘿',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(417,306,NULL,'2020-07-13 18:43:21',NULL,1,'深入交流','正常',31,'2020-07-13 18:43:21',84758,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(418,10,NULL,'2020-07-15 15:29:21',NULL,1,'迷你世界','正常',5,'2020-07-15 15:29:21',174569,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(419,4,NULL,'2020-07-19 03:54:37',NULL,1,'喝酒蹦迪','正常',2,'2020-07-19 03:54:37',183575,'喝喝酒聊聊天蹦迪',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(420,2,NULL,'2020-07-19 08:12:00',NULL,1,'什么？','正常',1,'2020-07-19 08:12:00',75429,'什么鬼？',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(421,13,NULL,'2020-07-19 23:38:37',NULL,1,'找姐妹','正常',5,'2020-07-19 23:38:37',166409,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(422,4993,NULL,'2020-07-20 01:02:04',NULL,1,'wu互看','违规',296,'2020-07-20 01:02:04',143849,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(423,41,NULL,'2020-07-20 20:22:39',NULL,1,'找女cP','正常',5,'2020-07-20 20:22:39',182886,'帮朋友找cP',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(424,4,NULL,'2020-07-29 09:28:34',NULL,1,'萌新','正常',3,'2020-07-29 09:28:34',188383,'新来的',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(425,63,NULL,'2020-07-31 20:19:05',NULL,1,'找t','正常',7,'2020-07-31 20:19:05',189418,'找攻攻的百合姐姐',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(426,72,NULL,'2020-07-31 22:38:35',NULL,1,'光遇交友','正常',62,'2020-07-31 22:38:35',185513,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(427,0,NULL,'2020-08-01 07:37:27',NULL,1,'食','正常',0,'2020-08-01 07:37:27',189140,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(428,1,NULL,'2020-08-01 07:43:29',NULL,1,'食玩图纸','正常',1,'2020-08-01 07:43:29',189140,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(429,20,NULL,'2020-08-05 02:09:19',NULL,1,'盗墓笔记','正常',2,'2020-08-05 02:09:19',187870,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(430,1,NULL,'2020-08-05 22:57:34',NULL,1,'白羊座','正常',0,'2020-08-05 22:57:34',191736,'星座',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(431,3,NULL,'2020-08-08 20:12:50',NULL,1,'大学','正常',2,'2020-08-08 20:12:50',63363,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(432,4,NULL,'2020-08-14 00:59:35',NULL,1,'穿越火线','正常',4,'2020-08-14 00:59:35',195459,'穿越火线枪战王者',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(433,1,NULL,'2020-08-14 11:51:10',NULL,1,'咕咕','正常',1,'2020-08-14 11:51:10',58946,'交真心朋友',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(434,2,NULL,'2020-08-14 15:15:12',NULL,1,'帅气小哥','正常',2,'2020-08-14 15:15:12',195700,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(435,4,NULL,'2020-08-14 15:15:30',NULL,1,'帅哥','正常',2,'2020-08-14 15:15:30',195700,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(436,10,NULL,'2020-08-17 09:25:58',NULL,1,'本地','正常',2,'2020-08-17 09:25:58',197089,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(437,10,NULL,'2020-08-18 23:06:03',NULL,1,'03后','正常',5,'2020-08-18 23:06:03',198398,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(438,5,NULL,'2020-08-18 23:12:45',NULL,1,'灵魂伴侣','正常',3,'2020-08-18 23:12:45',198398,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(439,31,NULL,'2020-08-20 08:29:49',NULL,1,'聊唔','违规',2,'2020-08-20 08:29:49',199396,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(440,13,NULL,'2020-08-22 13:53:18',NULL,1,'看小说','正常',12,'2020-08-22 13:53:18',67819,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(441,25,NULL,'2020-08-27 04:45:16',NULL,1,'占有欲强','正常',14,'2020-08-27 04:45:16',86136,'占有欲强烈',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(442,3,NULL,'2020-08-27 10:29:55',NULL,1,'住校','正常',2,'2020-08-27 10:29:55',188915,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(443,0,NULL,'2020-08-27 15:40:46',NULL,1,'找dk','违规',0,'2020-08-27 15:40:46',203426,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(444,434,NULL,'2020-08-29 10:49:34',NULL,1,'同性恋','违规',71,'2020-08-29 10:49:34',8258,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(445,2,NULL,'2020-09-13 22:02:37',NULL,1,'C','正常',0,'2020-09-13 22:02:37',207923,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(446,1,NULL,'2020-09-15 20:37:57',NULL,1,'粉墨','正常',1,'2020-09-15 20:37:57',175474,'Blackpink的粉丝',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(447,1,NULL,'2020-09-15 20:42:06',NULL,1,'阿米','正常',1,'2020-09-15 20:42:06',175474,'BTS防弹少年团的粉丝',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(448,61,NULL,'2020-09-15 22:52:28',NULL,1,'兄妹恋','正常',9,'2020-09-15 22:52:28',208726,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(449,6,NULL,'2020-09-16 16:03:19',NULL,1,'唯一','正常',4,'2020-09-16 16:03:19',208972,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(450,109,NULL,'2020-09-16 22:06:26',NULL,1,'同性cp','违规',8,'2020-09-16 22:06:26',188520,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(451,74,NULL,'2020-09-19 13:52:45',NULL,1,'中学生','正常',13,'2020-09-19 13:52:45',210208,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(452,7,NULL,'2020-09-19 21:13:10',NULL,1,'小可爱','正常',3,'2020-09-19 21:13:10',210569,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(453,0,NULL,'2020-09-22 13:51:47',NULL,1,'红包群主','违规',0,'2020-09-22 13:51:47',207073,'红包群主',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(454,9,NULL,'2020-09-26 18:00:35',NULL,1,'渣男勿扰','正常',8,'2020-09-26 18:00:35',197682,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(455,4,NULL,'2020-09-27 20:57:00',NULL,1,'务必真心','正常',3,'2020-09-27 20:57:00',75429,'真心对待！',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(456,2,NULL,'2020-09-29 20:11:32',NULL,1,'暴富','正常',0,'2020-09-29 20:11:32',213694,'带砖?',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(457,3,NULL,'2020-09-30 22:38:55',NULL,1,'高三','正常',2,'2020-09-30 22:38:55',172259,'淦',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(458,10,NULL,'2020-10-03 15:11:36',NULL,1,'段嘉许','正常',2,'2020-10-03 15:11:36',191974,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(459,97,NULL,'2020-10-07 15:28:23',NULL,1,'女m','违规',4,'2020-10-07 15:28:23',211361,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(460,6,NULL,'2020-10-11 07:39:57',NULL,1,'字母','违规',1,'2020-10-11 07:39:57',214815,'懂的都懂',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(461,0,NULL,'2020-10-12 12:27:03',NULL,1,'说实话','正常',0,'2020-10-12 12:27:03',219175,'小孩子家家16以下就别出来处对象了',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(462,2,NULL,'2020-10-18 06:48:51',NULL,1,'富豪','正常',2,'2020-10-18 06:48:51',220349,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(463,4,NULL,'2020-10-18 06:49:51',NULL,1,'富二代','违规',1,'2020-10-18 06:49:51',220349,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(464,7,NULL,'2020-10-19 00:31:38',NULL,1,'狼尾女孩','正常',1,'2020-10-19 00:31:38',51446,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(465,8,NULL,'2020-10-21 19:15:16',NULL,1,'12','正常',1,'2020-10-21 19:15:16',220345,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(466,2,NULL,'2020-10-24 17:30:52',NULL,1,'永远爱你','正常',1,'2020-10-24 17:30:52',157143,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(467,8,NULL,'2020-10-24 17:31:20',NULL,1,'你是唯一','正常',8,'2020-10-24 17:31:20',157143,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(468,1,NULL,'2020-10-25 11:33:13',NULL,1,'愿你安好','正常',1,'2020-10-25 11:33:13',157143,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(469,10,NULL,'2020-10-27 21:23:18',NULL,1,'学习渣女','正常',0,'2020-10-27 21:23:18',221279,'渣渣渣',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(470,4,NULL,'2020-10-30 20:28:21',NULL,1,'非人学院','正常',2,'2020-10-30 20:28:21',200466,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(471,6,NULL,'2020-10-31 12:16:46',NULL,1,'脾气好','正常',4,'2020-10-31 12:16:46',223029,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(472,3,NULL,'2020-10-31 12:17:43',NULL,1,'菜还爱玩','正常',3,'2020-10-31 12:17:43',223029,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(473,2,NULL,'2020-10-31 12:19:10',NULL,1,'菜且爱玩','正常',2,'2020-10-31 12:19:10',223029,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(474,53,NULL,'2020-10-31 23:46:11',NULL,1,'寻找富婆','正常',16,'2020-10-31 23:46:11',124290,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(475,4,NULL,'2020-11-04 20:57:11',NULL,1,'cos','正常',0,'2020-11-04 20:57:11',212713,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(476,4,NULL,'2020-11-21 17:59:56',NULL,1,'新人报到','正常',3,'2020-11-21 17:59:56',225095,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(477,12,NULL,'2020-11-23 19:24:51',NULL,1,'女同gl','违规',2,'2020-11-23 19:24:51',225343,'同性恋',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(478,19,NULL,'2020-12-01 00:51:14',NULL,1,'高中生','正常',6,'2020-12-01 00:51:14',227284,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(479,3,NULL,'2020-12-04 14:35:28',NULL,1,'浙江','正常',1,'2020-12-04 14:35:28',227916,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(480,2,NULL,'2020-12-04 14:35:45',NULL,1,'靓仔','正常',0,'2020-12-04 14:35:45',227916,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(481,16,NULL,'2020-12-04 20:25:46',NULL,1,'体育生呦','正常',9,'2020-12-04 20:25:46',221545,'高，帅气，',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(482,1,NULL,'2020-12-06 22:54:23',NULL,1,'mc','正常',1,'2020-12-06 22:54:23',161389,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(483,11,NULL,'2020-12-07 01:39:40',NULL,1,'小猫咪','正常',4,'2020-12-07 01:39:40',45221,'小猫咪的设定就是要听主人话。',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(484,1,NULL,'2020-12-07 23:20:11',NULL,1,'exo','正常',1,'2020-12-07 23:20:11',228883,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(485,5,NULL,'2020-12-09 22:24:55',NULL,1,'偏爱给你','正常',3,'2020-12-09 22:24:55',229470,'偏爱和例外都给你',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(486,2,NULL,'2020-12-10 00:37:06',NULL,1,'求解谢谢','正常',1,'2020-12-10 00:37:06',229541,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(487,9,NULL,'2020-12-12 04:15:17',NULL,1,'处母子','违规',1,'2020-12-12 04:15:17',230071,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(488,5,NULL,'2020-12-13 02:36:48',NULL,1,'死肥宅','正常',3,'2020-12-13 02:36:48',216709,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(489,0,NULL,'2020-12-16 21:35:23',NULL,1,'王一博','正常',0,'2020-12-16 21:35:23',231652,'追星女孩',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(490,225,NULL,'2020-12-19 22:45:05',NULL,1,'污污','违规',22,'2020-12-19 22:45:05',232634,'找污污的小姐姐',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(491,0,NULL,'2020-12-21 21:16:00',NULL,1,'一起跨年','正常',0,'2020-12-21 21:16:00',233073,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(492,22,NULL,'2020-12-22 17:03:14',NULL,1,'不要污','正常',10,'2020-12-22 17:03:14',231758,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(493,2,NULL,'2020-12-22 22:49:14',NULL,1,'话唠','正常',2,'2020-12-22 22:49:14',233757,'话多者必备?',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(494,65,NULL,'2020-12-25 08:43:08',NULL,1,'处w友','违规',2,'2020-12-25 08:43:08',234283,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(495,111,NULL,'2020-12-29 18:30:29',NULL,1,'聊www','违规',7,'2020-12-29 18:30:29',141179,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(496,40,NULL,'2020-12-30 22:29:28',NULL,1,'约炮','违规',3,'2020-12-30 22:29:28',193241,'男和男性爱',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(497,4,NULL,'2020-12-30 22:29:51',NULL,1,'约泡','违规',0,'2020-12-30 22:29:51',193241,'男和男性爱',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(498,3,NULL,'2021-01-08 01:52:58',NULL,1,'小妹妹','正常',2,'2021-01-08 01:52:58',237072,'妹妹',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(499,0,NULL,'2021-01-08 14:54:23',NULL,1,'妄想山海','正常',0,'2021-01-08 14:54:23',239974,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(500,1,NULL,'2021-01-09 15:52:26',NULL,1,'测试66','正常',1,'2021-01-09 15:52:26',240416,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(501,2,NULL,'2021-01-10 07:04:43',NULL,1,'文ai爱','违规',0,'2021-01-10 07:04:43',221987,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(502,3,NULL,'2021-01-11 13:52:03',NULL,1,'测试男友','正常',0,'2021-01-11 13:52:03',241134,'非诚勿扰',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(503,7,NULL,'2021-01-12 09:48:48',NULL,1,'聊wu~','违规',0,'2021-01-12 09:48:48',241778,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(504,0,NULL,'2021-01-21 00:45:18',NULL,1,'使命召唤','正常',0,'2021-01-21 00:45:18',100930,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(505,0,NULL,'2021-01-21 18:57:59',NULL,1,'地方','正常',0,'2021-01-21 18:57:59',117803,'安徽省六安市裕安区',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(506,2,NULL,'2021-01-23 18:21:46',NULL,1,'CSGO','正常',1,'2021-01-23 18:21:46',245177,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(507,1,NULL,'2021-01-24 23:26:34',NULL,1,'cosp','正常',0,'2021-01-24 23:26:34',225504,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(508,0,NULL,'2021-01-26 15:34:48',NULL,1,'黑丝控','违规',0,'2021-01-26 15:34:48',246881,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(509,1,NULL,'2021-01-27 12:58:03',NULL,1,'奶油丁沟','正常',0,'2021-01-27 12:58:03',247270,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(510,5,NULL,'2021-01-28 23:43:22',NULL,1,'住宿生','正常',4,'2021-01-28 23:43:22',175173,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(511,12,NULL,'2021-01-30 15:31:00',NULL,1,'约啪啪啪','违规',0,'2021-01-30 15:31:00',246969,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(512,2,NULL,'2021-01-30 21:42:36',NULL,1,'13岁','正常',0,'2021-01-30 21:42:36',247259,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(513,2,NULL,'2021-02-01 01:21:09',NULL,1,'读书','正常',2,'2021-02-01 01:21:09',108,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(514,4,NULL,'2021-02-01 18:58:08',NULL,1,'小狼狗','正常',2,'2021-02-01 18:58:08',250363,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(515,1,NULL,'2021-02-01 19:20:32',NULL,1,'粉丝','正常',1,'2021-02-01 19:20:32',147211,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(516,1,NULL,'2021-02-02 20:00:18',NULL,1,'找w友','违规',1,'2021-02-02 20:00:18',226264,'vv的人➕',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(517,0,NULL,'2021-02-02 22:32:52',NULL,1,'东北老妹','违规',0,'2021-02-02 22:32:52',249541,'东北',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(518,10,NULL,'2021-02-05 23:22:22',NULL,1,'les','正常',5,'2021-02-05 23:22:22',253413,'女同',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(519,0,NULL,'2021-02-06 16:07:57',NULL,1,'找bab','正常',0,'2021-02-06 16:07:57',253754,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(520,972,NULL,'2021-02-06 23:49:40',NULL,1,'互看','正常',135,'2021-02-06 23:49:40',247726,'互看',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(521,9,NULL,'2021-02-06 23:50:13',NULL,1,'5','已删除',0,'2021-02-06 23:50:13',247726,'5',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(522,3,NULL,'2021-02-07 04:39:59',NULL,1,'爱腹肌','正常',3,'2021-02-07 04:39:59',253754,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(523,3,NULL,'2021-02-07 16:03:11',NULL,1,'艺体生','正常',1,'2021-02-07 16:03:11',254413,'中国舞专业',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(524,0,NULL,'2021-02-07 16:03:41',NULL,1,'舞蹈艺体','正常',0,'2021-02-07 16:03:41',254413,'中国舞',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(525,1,NULL,'2021-02-10 11:15:02',NULL,1,'找弟妹','正常',1,'2021-02-10 11:15:02',198787,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(526,0,NULL,'2021-02-10 22:24:51',NULL,1,'宫圈','正常',0,'2021-02-10 22:24:51',159676,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(527,2,NULL,'2021-02-11 13:25:01',NULL,1,'新年快乐','正常',2,'2021-02-11 13:25:01',255400,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(528,150,NULL,'2021-02-13 01:07:40',NULL,1,'聊5','违规',14,'2021-02-13 01:07:40',236660,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(529,0,NULL,'2021-02-13 20:08:47',NULL,1,'华晨宇','正常',0,'2021-02-13 20:08:47',257719,'华晨宇粉丝',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(530,29,NULL,'2021-02-17 18:37:47',NULL,1,'wu','违规',4,'2021-02-17 18:37:47',41581,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(531,6,NULL,'2021-02-18 22:05:03',NULL,1,'JK萝莉','正常',1,'2021-02-18 22:05:03',195007,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(532,0,NULL,'2021-02-19 16:58:09',NULL,1,'ttl','正常',0,'2021-02-19 16:58:09',240805,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(533,0,NULL,'2021-02-20 02:51:52',NULL,1,'关注姐姐','正常',0,'2021-02-20 02:51:52',198787,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(534,2,NULL,'2021-02-20 16:18:49',NULL,1,'崽崽','正常',1,'2021-02-20 16:18:49',256898,'游戏',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(535,5,NULL,'2021-02-23 09:12:45',NULL,1,'不好看','正常',3,'2021-02-23 09:12:45',263292,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(536,2,NULL,'2021-02-24 11:04:23',NULL,1,'#表演#','违规',2,'2021-02-24 11:04:23',246285,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(537,3,NULL,'2021-02-24 19:03:48',NULL,1,'美女','正常',2,'2021-02-24 19:03:48',252542,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(538,0,NULL,'2021-02-28 17:23:42',NULL,1,'帮找','正常',0,'2021-02-28 17:23:42',209867,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(539,166,NULL,'2021-03-01 23:23:10',NULL,1,'闷骚','正常',25,'2021-03-01 23:23:10',261143,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(540,0,NULL,'2021-03-04 18:40:49',NULL,1,'可爆照','正常',0,'2021-03-04 18:40:49',7641,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(541,1,NULL,'2021-03-08 23:31:07',NULL,1,'接陪玩','正常',1,'2021-03-08 23:31:07',268490,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(542,0,NULL,'2021-03-11 15:43:22',NULL,1,'分手','正常',0,'2021-03-11 15:43:22',268965,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(543,0,NULL,'2021-03-11 20:15:17',NULL,1,'鞍山','正常',0,'2021-03-11 20:15:17',246511,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(544,4,NULL,'2021-03-18 10:43:43',NULL,1,'工作','正常',0,'2021-03-18 10:43:43',108,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(545,1,NULL,'2021-03-20 19:36:50',NULL,1,'原耽','正常',1,'2021-03-20 19:36:50',271120,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(546,6,NULL,'2021-03-21 20:54:52',NULL,1,'胖妹妹','正常',3,'2021-03-21 20:54:52',270257,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(547,0,NULL,'2021-03-21 20:55:23',NULL,1,'胖妹妹的','正常',0,'2021-03-21 20:55:23',270257,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(548,2,NULL,'2021-03-21 20:56:20',NULL,1,'!!!!','正常',2,'2021-03-21 20:56:20',270257,'胖妹妹的烦恼',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(549,0,NULL,'2021-03-23 23:38:05',NULL,1,'约p','违规',0,'2021-03-23 23:38:05',271882,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(550,1,NULL,'2021-03-27 02:23:50',NULL,1,'哈哈哈嗝','正常',1,'2021-03-27 02:23:50',169327,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(551,0,NULL,'2021-03-27 02:24:32',NULL,1,'哈哈哈','正常',0,'2021-03-27 02:24:32',169327,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(552,1,NULL,'2021-04-05 10:01:40',NULL,1,'李知恩','正常',1,'2021-04-05 10:01:40',264256,'我喜欢啊，我是她粉丝。',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(553,65380,NULL,'2021-04-05 10:01:40',NULL,32,'清池','正常',40312,'2021-04-05 10:01:40',108,'交友软件',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',1,'\0','all'),

(554,0,NULL,'2021-04-05 10:01:40',NULL,32,'集美1','已删除',0,'2021-04-05 10:01:40',108,'女孩闺蜜交友',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all'),

(555,10218,NULL,'2021-04-05 10:01:40',NULL,33,'女生世界','正常',346,'2021-04-05 10:01:40',108,'女孩',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'\0','girl'),

(557,2008,NULL,'2021-04-05 10:01:40',NULL,34,'男生世界','正常',120,'2021-04-05 10:01:40',108,'女孩',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'\0','boy'),

(558,2,NULL,'2021-04-10 02:02:00',NULL,1,'离谱','正常',2,'2021-04-10 02:02:00',276426,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(559,9,NULL,'2021-04-18 23:32:51',NULL,1,'找女朋友','正常',5,'2021-04-18 23:32:51',278127,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(560,7,NULL,'2021-04-23 00:24:13',NULL,1,'cpdd','正常',7,'2021-04-23 00:24:13',279498,'。',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(561,1,NULL,'2021-04-24 17:56:03',NULL,1,'声音好听','正常',0,'2021-04-24 17:56:03',279059,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(562,2,NULL,'2021-04-30 23:02:03',NULL,1,'耽美','正常',2,'2021-04-30 23:02:03',281552,'男男女女同性的代称',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(563,6,NULL,'2021-05-01 03:10:33',NULL,1,'夜场','正常',0,'2021-05-01 03:10:33',80128,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(564,2,NULL,'2021-05-02 19:19:06',NULL,1,'凹凸世界','正常',0,'2021-05-02 19:19:06',282357,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(565,0,NULL,'2021-05-07 22:56:33',NULL,1,'奥斯卡','正常',0,'2021-05-07 22:56:33',284098,'昆明奥斯卡酒吧，',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(566,1,NULL,'2021-05-15 04:07:31',NULL,1,'狼尾','正常',0,'2021-05-15 04:07:31',286789,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(567,2,NULL,'2021-05-16 09:03:27',NULL,1,'闺女','正常',0,'2021-05-16 09:03:27',230916,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(568,22,NULL,'2021-05-16 21:21:05',NULL,1,'原神','正常',22,'2021-05-16 21:21:05',287485,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(569,17,NULL,'2021-05-23 14:05:23',NULL,1,'三坑少女','正常',5,'2021-05-23 14:05:23',289048,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(570,0,NULL,'2021-05-23 21:49:13',NULL,1,'白河','正常',0,'2021-05-23 21:49:13',248100,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(571,3,NULL,'2021-05-24 20:38:27',NULL,1,'反差','正常',2,'2021-05-24 20:38:27',289585,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,0,'','all'),

(572,84,NULL,'2021-05-24 20:38:27',NULL,32,'猫头鹰','正常',3,'2021-05-24 20:38:27',108,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,4,'','all'),

(575,3,NULL,'2021-06-08 22:21:45',NULL,1,'扩列好友','正常',3,'2021-06-08 22:21:45',294503,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(576,6,NULL,'2021-06-10 17:53:32',NULL,1,'可打游戏','正常',5,'2021-06-10 17:53:32',294960,'可打游戏',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(577,3,NULL,'2021-06-10 18:04:10',NULL,1,'会打游戏','正常',3,'2021-06-10 18:04:10',294960,'会打游戏',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(578,423,NULL,'2021-06-13 14:05:38',NULL,1,'可w','违规',154,'2021-06-13 14:05:38',276835,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(579,31,NULL,'2021-06-13 14:06:01',NULL,1,'lsp','正常',7,'2021-06-13 14:06:01',276835,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(580,1,NULL,'2021-06-20 10:23:40',NULL,1,'本人直男','正常',1,'2021-06-20 10:23:40',282660,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(581,0,NULL,'2021-06-21 01:38:41',NULL,1,'找队友','正常',0,'2021-06-21 01:38:41',298871,'就找队友能开麦且带耳机的',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(582,2,NULL,'2021-06-21 22:21:18',NULL,1,'知己','正常',2,'2021-06-21 22:21:18',151613,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(583,1,NULL,'2021-06-27 20:43:04',NULL,1,'生活趣事','正常',0,'2021-06-27 20:43:04',300898,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(584,1,NULL,'2021-07-06 17:28:21',NULL,1,'李宏毅','正常',0,'2021-07-06 17:28:21',242505,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(585,2,NULL,'2021-07-13 18:40:26',NULL,1,'找儿子.','正常',0,'2021-07-13 18:40:26',302728,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(586,2,NULL,'2021-07-15 01:54:52',NULL,1,'唔','正常',2,'2021-07-15 01:54:52',224986,'唔',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(587,0,NULL,'2021-07-19 17:18:55',NULL,1,'平安京','正常',0,'2021-07-19 17:18:55',309756,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(588,2,NULL,'2021-07-20 21:16:23',NULL,1,'初三','正常',2,'2021-07-20 21:16:23',49906,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(589,4,NULL,'2021-07-23 13:35:59',NULL,1,'小受受','正常',3,'2021-07-23 13:35:59',236739,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(590,0,NULL,'2021-07-25 14:41:27',NULL,1,'开宝箱','正常',0,'2021-07-25 14:41:27',311760,'钓鱼开宝箱 礼物可提',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(591,2,NULL,'2021-07-27 12:44:31',NULL,1,'学霸','正常',1,'2021-07-27 12:44:31',312487,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(592,1,NULL,'2021-07-29 01:11:28',NULL,1,'06','正常',1,'2021-07-29 01:11:28',31914,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(593,0,NULL,'2021-07-30 01:14:16',NULL,1,'要夜猫子','正常',0,'2021-07-30 01:14:16',206213,'多晚都可以多晚都可以陪聊',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(594,0,NULL,'2021-07-31 14:56:26',NULL,1,'可语音鸭','正常',0,'2021-07-31 14:56:26',313992,'有各种声音都过来吧',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(595,1,NULL,'2021-08-12 10:20:07',NULL,1,'七夕出租','正常',1,'2021-08-12 10:20:07',318116,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(596,1,NULL,'2021-08-22 15:38:52',NULL,1,'第四爱','正常',1,'2021-08-22 15:38:52',309132,'女攻男受',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(597,15,NULL,'2021-09-04 12:17:13',NULL,1,'女同','违规',15,'2021-09-04 12:17:13',233845,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(598,2,NULL,'2021-09-04 12:17:30',NULL,1,'女同小姐','违规',2,'2021-09-04 12:17:30',233845,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(600,8,NULL,'2021-09-05 02:27:41',NULL,1,'处5对象','正常',8,'2021-09-05 02:27:41',236660,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(602,17,NULL,'2021-09-05 17:53:56',NULL,32,'小星星','正常',17,'2021-09-05 17:53:56',108,'开发者对应的话题',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,5,'\0','all'),

(603,0,NULL,'2021-09-05 19:59:53',NULL,32,'赤友','正常',0,'2021-09-05 19:59:53',108,'开发者对应的话题',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,7,'\0','all'),

(604,0,NULL,'2021-09-05 20:03:41',NULL,32,'蛇交','正常',0,'2021-09-05 20:03:41',108,'开发者对应的话题',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,6,'\0','all'),

(608,4,NULL,'2021-09-05 20:03:41',NULL,32,'演示系统','正常',4,'2021-09-05 20:03:41',108,'开发者对应的话题',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,3,'\0','all'),

(609,10,NULL,'2021-09-06 01:04:28',NULL,1,'w','已删除',10,'2021-09-06 01:04:28',326801,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(610,0,NULL,'2021-09-06 22:15:04',NULL,32,'小星事','正常',0,'2021-09-06 22:15:04',108,'开发者对应的话题',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,8,'\0','all'),

(611,0,NULL,'2021-09-12 18:38:18',NULL,1,'混艺术','正常',0,'2021-09-12 18:38:18',327787,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(613,1,NULL,'2021-09-17 10:28:02',NULL,1,'s','已删除',1,'2021-09-17 10:28:02',264219,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(614,0,NULL,'2021-09-19 17:58:03',NULL,1,'电音','正常',0,'2021-09-19 17:58:03',161389,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(615,1,NULL,'2021-10-11 13:17:27',NULL,1,'艺术生','正常',1,'2021-10-11 13:17:27',93089,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(616,0,NULL,'2021-10-13 13:48:29',NULL,1,'广东韶关','正常',0,'2021-10-13 13:48:29',332967,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(617,21,NULL,'2021-10-14 21:01:55',NULL,1,'光遇','正常',21,'2021-10-14 21:01:55',333500,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(618,0,NULL,'2021-10-18 22:45:41',NULL,32,'校园墙','正常',0,'2021-10-18 22:45:41',108,'开发者对应的话题',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,13,'\0','all'),

(619,0,NULL,'2021-10-20 07:24:14',NULL,32,'迈赞','正常',0,'2021-10-20 07:24:14',108,'开发者对应的话题',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,14,'\0','all'),

(620,1,NULL,'2021-10-30 00:54:06',NULL,32,'清风交友','正常',1,'2021-10-30 00:54:06',108,'开发者对应的话题',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,10,'\0','all'),

(621,0,NULL,'2021-11-03 10:05:08',NULL,1,'鞠婧祎','正常',0,'2021-11-03 10:05:08',415,'明星',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(622,0,NULL,'2021-11-18 17:06:26',NULL,32,'比晒场','正常',0,'2021-11-18 17:06:26',108,'开发者对应的话题',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,19,'\0','all'),

(623,1,NULL,'2021-11-26 20:25:19',NULL,32,'小小留言板','正常',1,'2021-11-26 20:25:19',108,'开发者对应的话题',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,21,'\0','all'),

(624,0,NULL,'2021-12-01 11:33:55',NULL,32,'活动大师兄','正常',0,'2021-12-01 11:33:55',108,'开发者对应的话题',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,23,'\0','all'),

(625,1,NULL,'2021-12-16 23:09:50',NULL,1,'辣妹','正常',1,'2021-12-16 23:09:50',43783,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(626,0,NULL,'2021-12-20 13:55:00',NULL,32,'若依','正常',0,'2021-12-20 13:55:00',108,'开发者对应的话题',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,25,'\0','all'),

(627,2,NULL,'2021-12-22 15:54:34',NULL,32,'驭园','正常',2,'2021-12-22 15:54:34',108,'开发者对应的话题',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,24,'\0','all'),

(628,0,NULL,'2022-01-30 17:21:26',NULL,32,'Net','正常',0,'2022-01-30 17:21:26',108,'开发者对应的话题',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,30,'\0','all'),

(629,0,NULL,'2022-02-16 22:47:05',NULL,1,'体育生','正常',0,'2022-02-16 22:47:05',44113,'体育生',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(630,0,NULL,'2022-04-28 13:05:10',NULL,32,'jty','正常',0,'2022-04-28 13:05:10',108,'开发者对应的话题',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,35,'\0','all'),

(631,0,NULL,'2022-05-09 22:21:38',NULL,1,'哈利波特','正常',0,'2022-05-09 22:21:38',360414,'哈利波特',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(633,0,NULL,'2022-05-24 17:41:06',NULL,32,'突兀社交','正常',0,'2022-05-24 17:41:06',108,'开发者对应的话题',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,38,'\0','all'),

(638,1,NULL,'2022-06-25 15:44:48',NULL,1,'不可w','正常',1,'2022-06-25 15:44:48',366300,'不交污友 单纯交朋友 单纯处cp',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(639,24,NULL,'2022-06-25 15:44:48',NULL,35,'测试环境','正常',24,'2022-06-25 15:44:48',366300,'不交污友 单纯交朋友 单纯处cp',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(640,0,NULL,'2022-10-23 14:40:25',NULL,1,'爆米花','正常',0,'2022-10-23 14:40:25',1972541,'时代少年团粉丝',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(642,0,NULL,'2022-10-28 22:17:13',NULL,1,'能看','正常',0,'2022-10-28 22:17:13',1975425,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(643,1,NULL,'2022-10-29 11:42:23',NULL,1,'白嫖勿扰','正常',1,'2022-10-29 11:42:23',1975425,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(644,3,NULL,'2022-10-31 19:25:55',NULL,1,'w互','违规',3,'2022-10-31 19:25:55',215746,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(645,0,NULL,'2022-11-08 00:25:41',NULL,1,'不拒wu','违规',0,'2022-11-08 00:25:41',1958040,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(648,0,NULL,'2022-11-12 11:27:45',NULL,1,'英国','正常',0,'2022-11-12 11:27:45',1982261,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(649,0,NULL,'2022-11-19 00:07:38',NULL,1,'聆听者','正常',0,'2022-11-19 00:07:38',357630,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all'),

(652,0,NULL,'2022-12-16 23:53:50',NULL,1,'学习搭档','正常',0,'2022-12-16 23:53:50',1994942,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all');


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*Table structure for table `s_community_circle` */

DROP TABLE IF EXISTS `s_community_circle`;

CREATE TABLE `s_community_circle` (
                                      `id` int(11) NOT NULL AUTO_INCREMENT,
                                      `count` int(11) DEFAULT NULL,
                                      `create_reason` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                      `create_time` datetime DEFAULT NULL,
                                      `create_user` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                      `tag_type_id` int(11) DEFAULT NULL,
                                      `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                      `status` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                      `talk_count` int(11) DEFAULT NULL,
                                      `update_time` datetime DEFAULT NULL,
                                      `apply_user_id` int(11) DEFAULT NULL,
                                      `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                      `show_in_home` bit(1) DEFAULT NULL,
                                      `show_wx_account` bit(1) DEFAULT NULL,
                                      `tag_classify_id` int(11) DEFAULT NULL,
                                      `avatar` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                      `gender` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                      `dev_id` int(11) DEFAULT NULL,
                                      `show_front` bit(1) DEFAULT NULL,
                                      `visible_gender` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                      `city_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                      `user_id` int(11) NOT NULL,
                                      PRIMARY KEY (`id`),
                                      UNIQUE KEY `UKb8ln648ukufmpxs50n1b8u1fk` (`name`),
                                      UNIQUE KEY `UKskibejye898ahg6ubvunql2i6` (`dev_id`),
                                      KEY `IDX5vqexjdcrxmyebs924xuanhue` (`status`),
                                      KEY `IDXohfnwy1da2p4nmcuwc8j8m54k` (`show_front`),
                                      KEY `IDXtl2dtra0f3b37qqjhnef6ckn` (`count`),
                                      KEY `IDX90q0wgie5egud3ifmgwjg66l7` (`visible_gender`)
) ENGINE=InnoDB AUTO_INCREMENT=3262 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `s_community_circle` */

insert  into `s_community_circle`(`id`,`count`,`create_reason`,`create_time`,`create_user`,`tag_type_id`,`name`,`status`,`talk_count`,`update_time`,`apply_user_id`,`description`,`show_in_home`,`show_wx_account`,`tag_classify_id`,`avatar`,`gender`,`dev_id`,`show_front`,`visible_gender`,`city_name`,`user_id`) values

(1,7029,NULL,NULL,NULL,1,'处对象','正常',0,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2,3,NULL,NULL,NULL,1,'处v友','正常',0,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3,0,NULL,NULL,NULL,1,'处q友','正常',0,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(4,1460,NULL,NULL,NULL,1,'扩列','正常',0,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(5,0,NULL,NULL,NULL,2,'愿望','正常',0,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(6,0,NULL,NULL,NULL,2,'秀美食','正常',0,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(7,0,NULL,NULL,NULL,22,'秀恩爱','正常',0,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(8,0,NULL,NULL,NULL,27,'二次元','正常',0,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(9,0,NULL,NULL,NULL,29,'问题建议','正常',0,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(10,0,NULL,NULL,NULL,2,'抱怨','正常',0,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(11,0,NULL,NULL,NULL,28,'lol','正常',0,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(12,0,NULL,NULL,NULL,28,'王者荣耀','正常',0,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(13,0,NULL,NULL,NULL,28,'绝地求生','正常',0,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(14,0,NULL,NULL,NULL,28,'和平精英','正常',0,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(15,0,NULL,NULL,NULL,22,'情话','正常',0,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(16,0,NULL,NULL,NULL,2,'理想','正常',0,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(17,0,NULL,NULL,NULL,2,'秀生日','正常',0,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(18,0,NULL,NULL,NULL,2,'秀自拍','正常',0,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(19,0,NULL,NULL,NULL,29,'官方','正常',0,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(20,0,NULL,NULL,NULL,30,'90后','正常',0,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(21,6,NULL,NULL,NULL,30,'00后','正常',0,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(22,0,NULL,NULL,NULL,30,'80后','正常',0,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(23,0,NULL,NULL,NULL,1,'处姐妹','正常',0,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(24,0,NULL,NULL,NULL,1,'处兄弟','正常',0,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(25,0,NULL,NULL,NULL,28,'守望先锋','正常',0,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(26,0,NULL,NULL,NULL,2,'旅行','正常',0,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(27,0,NULL,NULL,NULL,22,'失恋','正常',0,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(28,0,NULL,NULL,NULL,2,'想法','正常',0,NULL,NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(29,1,NULL,'2019-12-21 13:19:55',NULL,1,'交友','正常',0,'2019-12-21 13:19:55',108,'交友',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(30,0,NULL,'2019-12-23 12:31:21',NULL,1,'牛逼1','已删除',0,'2019-12-23 12:31:21',481,'比牛逼',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(31,0,NULL,'2019-12-23 12:31:21',NULL,1,'牛逼','已删除',0,'2019-12-23 12:31:21',481,'比牛逼',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(32,0,NULL,'2019-12-24 20:54:25',NULL,2,'平安夜','正常',0,'2019-12-24 20:54:25',415,'平安夜',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(33,0,NULL,'2019-12-24 20:54:25',NULL,28,'二次元1','正常',0,'2019-12-24 20:54:25',415,'二次元',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(34,0,NULL,'2019-12-27 21:20:28',NULL,1,'处WeChat好友','正常',0,'2019-12-27 21:20:28',2019,'加微信好友',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(35,0,NULL,'2019-12-29 16:06:49',NULL,15,'趴赛','正常',0,'2019-12-29 16:06:49',2474,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(36,1,NULL,'2019-12-29 22:29:09',NULL,28,'英雄联盟','正常',0,'2019-12-29 22:29:09',2584,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(37,0,NULL,'2020-01-01 09:11:41',NULL,22,'介绍对象','正常',0,'2020-01-01 09:11:41',1430,'就是像月老一样',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(38,0,NULL,'2020-01-01 22:21:22',NULL,1,'可以聊天，打游戏','正常',0,'2020-01-01 22:21:22',3104,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(39,0,NULL,'2020-01-02 19:15:48',NULL,1,'养火','正常',0,'2020-01-02 19:15:48',3431,'加q养火',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(40,822,NULL,'2020-01-02 20:41:38',NULL,1,'闺蜜','正常',0,'2020-01-02 20:41:38',3445,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','女',NULL,'','girl',NULL,0),

(41,0,NULL,'2020-01-04 19:04:35',NULL,1,'小哥哥','正常',0,'2020-01-04 19:04:35',4076,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(42,0,NULL,'2020-01-05 11:40:16',NULL,1,'处关系','正常',0,'2020-01-05 11:40:16',4243,'就是出很多关系',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(43,108,NULL,'2020-01-06 22:00:08',NULL,1,'找哥哥','正常',0,'2020-01-06 22:00:08',5197,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(44,0,NULL,'2020-01-06 23:54:27',NULL,1,'处对象，专一，真心','已删除',0,'2020-01-06 23:54:27',5258,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(45,113,NULL,'2020-01-07 22:51:55',NULL,2,'生活','正常',0,'2020-01-07 22:51:55',5598,'过得好吗',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(46,0,NULL,'2020-01-08 15:43:54',NULL,1,'处cp','正常',0,'2020-01-08 15:43:54',4873,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(47,0,NULL,'2020-01-08 16:09:03',NULL,28,'谁是卧底','正常',0,'2020-01-08 16:09:03',5807,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(48,0,NULL,'2020-01-08 18:22:51',NULL,28,'QQ飞车','正常',0,'2020-01-08 18:22:51',5860,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(49,0,NULL,'2020-01-09 17:46:58',NULL,1,'阳光帅气男孩','正常',0,'2020-01-09 17:46:58',6298,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(50,0,NULL,'2020-01-11 17:08:09',NULL,2,'不知道干嘛','正常',0,'2020-01-11 17:08:09',5114,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(51,36,NULL,'2020-01-11 21:02:44',NULL,33,'处闺蜜','正常',0,'2020-01-11 21:02:44',7195,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','girl',NULL,0),

(52,0,NULL,'2020-01-11 21:04:33',NULL,1,'处闺蜜，用闺头，专一，住','正常',0,'2020-01-11 21:04:33',7195,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(53,0,NULL,'2020-01-11 21:05:34',NULL,1,'处闺蜜，够铁，用闺头','正常',0,'2020-01-11 21:05:34',7195,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(54,0,NULL,'2020-01-11 23:28:24',NULL,1,'宠妻','正常',0,'2020-01-11 23:28:24',7290,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(55,0,NULL,'2020-01-13 15:39:30',NULL,1,'处哥哥','正常',0,'2020-01-13 15:39:30',7144,'处哥哥',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(56,0,NULL,'2020-01-14 22:18:38',NULL,1,'喜欢wuwu的','已删除',0,'2020-01-14 22:18:38',8386,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(57,0,NULL,'2020-01-15 18:49:52',NULL,1,'交朋友','正常',0,'2020-01-15 18:49:52',8625,'交朋友',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(58,0,NULL,'2020-01-17 19:40:42',NULL,22,'恋爱','正常',0,'2020-01-17 19:40:42',9784,'专一不花心',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(59,0,NULL,'2020-01-19 16:51:56',NULL,2,'不食人间烟火','正常',0,'2020-01-19 16:51:56',11097,'对一个人的相信，信任，包容',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(60,0,NULL,'2020-01-20 19:24:46',NULL,1,'找男闺蜜','正常',0,'2020-01-20 19:24:46',12072,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(61,0,NULL,'2020-01-21 09:41:16',NULL,22,'男朋友','正常',0,'2020-01-21 09:41:16',12639,'曝照，公开恋情，霸道12~14',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(62,0,NULL,'2020-01-25 15:36:22',NULL,1,'颜控','正常',0,'2020-01-25 15:36:22',11914,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(63,0,NULL,'2020-01-27 22:07:14',NULL,2,'#','正常',0,'2020-01-27 22:07:14',15744,'可以聊天，处对象。',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(64,0,NULL,'2020-01-31 13:58:50',NULL,1,'聊污','已删除',0,'2020-01-31 13:58:50',16235,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(65,0,NULL,'2020-02-01 17:26:46',NULL,1,'真心，专一','正常',0,'2020-02-01 17:26:46',14873,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(66,0,NULL,'2020-02-01 17:26:47',NULL,1,'真心','正常',0,'2020-02-01 17:26:47',14873,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(67,0,NULL,'2020-02-01 22:39:48',NULL,1,'聊天','已删除',0,'2020-02-01 22:39:48',18983,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(68,2,NULL,'2020-02-01 22:40:06',NULL,1,'学生','正常',0,'2020-02-01 22:40:06',18983,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(69,0,NULL,'2020-02-02 06:12:43',NULL,1,'wuwu的小姐姐','已删除',0,'2020-02-02 06:12:43',19338,'有没有wu唔的小姐姐哦',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(70,0,NULL,'2020-02-02 06:13:08',NULL,1,'有没有唔唔的小姐姐','已删除',0,'2020-02-02 06:13:08',19338,'啦啦啦啦啦',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(71,0,NULL,'2020-02-02 06:16:00',NULL,1,'喜欢呜呜的小姐姐','已删除',0,'2020-02-02 06:16:00',19338,'啦啦啦啦啦啦啦',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(72,0,NULL,'2020-02-02 10:06:20',NULL,1,'板混','正常',0,'2020-02-02 10:06:20',18655,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(73,0,NULL,'2020-02-02 16:02:32',NULL,1,'聊骚','已删除',0,'2020-02-02 16:02:32',19643,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(74,0,NULL,'2020-02-02 16:02:52',NULL,1,'文爱','已删除',0,'2020-02-02 16:02:52',19643,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(75,0,NULL,'2020-02-02 21:26:07',NULL,9,'音乐','正常',0,'2020-02-02 21:26:07',19970,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(76,0,NULL,'2020-02-03 18:48:01',NULL,1,'姐弟恋','正常',0,'2020-02-03 18:48:01',20818,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(77,0,NULL,'2020-02-03 20:05:25',NULL,1,'身材控','正常',0,'2020-02-03 20:05:25',20573,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(78,0,NULL,'2020-02-03 20:05:45',NULL,1,'非颜控','正常',0,'2020-02-03 20:05:45',20573,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(79,0,NULL,'2020-02-03 22:07:28',NULL,1,'爆照','正常',0,'2020-02-03 22:07:28',16230,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(80,0,NULL,'2020-02-05 00:20:07',NULL,1,'#处对象','正常',0,'2020-02-05 00:20:07',22223,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(81,1,NULL,'2020-02-05 00:22:58',NULL,30,'2004','正常',0,'2020-02-05 00:22:58',22223,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(82,0,NULL,'2020-02-05 00:59:35',NULL,1,'小奶狗或小狼狗','正常',0,'2020-02-05 00:59:35',22276,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(83,0,NULL,'2020-02-05 10:43:21',NULL,1,'不要污的男孩','已删除',0,'2020-02-05 10:43:21',22482,'太色的不要',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(84,0,NULL,'2020-02-05 11:20:58',NULL,28,'第五人格','正常',0,'2020-02-05 11:20:58',22498,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(85,0,NULL,'2020-02-05 19:27:42',NULL,1,'处兄妹','正常',0,'2020-02-05 19:27:42',22931,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(86,0,NULL,'2020-02-05 21:27:17',NULL,1,'真心对待','正常',0,'2020-02-05 21:27:17',23077,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(87,0,NULL,'2020-02-08 15:09:12',NULL,2,'情绪三分钟1','正常',0,'2020-02-08 15:09:12',24669,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(88,0,NULL,'2020-02-08 15:09:13',NULL,2,'情绪三分钟','正常',0,'2020-02-08 15:09:13',24669,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(89,0,NULL,'2020-02-09 00:21:35',NULL,21,'香港内地交友群','正常',0,'2020-02-09 00:21:35',25511,'正规认真交友群',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(90,0,NULL,'2020-02-09 23:17:45',NULL,1,'专一','正常',0,'2020-02-09 23:17:45',26516,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(91,0,NULL,'2020-02-10 22:32:21',NULL,1,'师傅','正常',0,'2020-02-10 22:32:21',27538,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(92,0,NULL,'2020-02-10 23:25:08',NULL,1,'好看','正常',0,'2020-02-10 23:25:08',27612,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(93,0,NULL,'2020-02-12 09:05:17',NULL,1,'小姐姐','正常',0,'2020-02-12 09:05:17',14531,'好听',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(94,0,NULL,'2020-02-12 22:58:09',NULL,28,'吃鸡','正常',0,'2020-02-12 22:58:09',24710,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(95,0,NULL,'2020-02-13 02:12:52',NULL,1,'找cp','正常',0,'2020-02-13 02:12:52',29790,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(96,0,NULL,'2020-02-13 03:22:35',NULL,1,'百合','违规',0,'2020-02-13 03:22:35',29793,'女孩子处对象',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(97,0,NULL,'2020-02-13 21:40:46',NULL,1,'找个女闺蜜','正常',0,'2020-02-13 21:40:46',30535,'陪我打王者打吃鸡长的一般就都可以',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','girl',NULL,0),

(98,0,NULL,'2020-02-13 23:21:40',NULL,1,'处对象，','正常',0,'2020-02-13 23:21:40',27728,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(99,0,NULL,'2020-02-13 23:22:24',NULL,1,'真心，阳光','正常',0,'2020-02-13 23:22:24',27728,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(100,0,NULL,'2020-02-14 08:19:31',NULL,2,'情人节快乐！','正常',0,'2020-02-14 08:19:31',26367,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(101,0,NULL,'2020-02-14 12:45:24',NULL,1,'找妹妹','正常',0,'2020-02-14 12:45:24',18726,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(102,0,NULL,'2020-02-15 14:09:35',NULL,1,'#声控#','正常',0,'2020-02-15 14:09:35',33473,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(103,0,NULL,'2020-02-16 19:16:25',NULL,2,'尬','正常',0,'2020-02-16 19:16:25',35154,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(104,3,NULL,'2020-02-17 21:33:11',NULL,28,'王者','正常',0,'2020-02-17 21:33:11',35199,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(105,0,NULL,'2020-02-17 22:51:37',NULL,28,'qq飞车手游','正常',0,'2020-02-17 22:51:37',36742,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(106,0,NULL,'2020-02-18 17:30:16',NULL,1,'等待一起用情头的那个人','正常',0,'2020-02-18 17:30:16',28820,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(107,0,NULL,'2020-02-18 18:42:20',NULL,1,'处对象，cp','正常',0,'2020-02-18 18:42:20',37595,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(108,0,NULL,'2020-02-18 18:42:54',NULL,1,'找小姐姐','正常',0,'2020-02-18 18:42:54',37595,'颜控，声控',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(109,0,NULL,'2020-02-18 18:43:37',NULL,1,'本人暖男，175。15岁','正常',0,'2020-02-18 18:43:37',37595,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(110,0,NULL,'2020-02-18 18:44:05',NULL,28,'王者吃鸡上分','正常',0,'2020-02-18 18:44:05',37595,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(111,3,NULL,'2020-02-18 18:56:14',NULL,22,'找男朋友','正常',0,'2020-02-18 18:56:14',37570,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(112,0,NULL,'2020-02-18 18:56:14',NULL,22,'找男朋友1','正常',0,'2020-02-18 18:56:14',37570,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(113,0,NULL,'2020-02-18 18:56:14',NULL,22,'找男朋友2','正常',0,'2020-02-18 18:56:14',37570,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(114,0,NULL,'2020-02-18 18:56:14',NULL,22,'找男朋友3','正常',0,'2020-02-18 18:56:14',37570,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(115,0,NULL,'2020-02-20 19:21:33',NULL,1,'找暖心哥哥','正常',0,'2020-02-20 19:21:33',21294,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(116,0,NULL,'2020-02-20 21:35:55',NULL,1,'狼狗','正常',0,'2020-02-20 21:35:55',38927,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(117,0,NULL,'2020-02-20 21:36:16',NULL,1,'一丢丢颜控','正常',0,'2020-02-20 21:36:16',38927,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(118,0,NULL,'2020-02-20 21:36:34',NULL,1,'宠我','正常',0,'2020-02-20 21:36:34',38927,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(119,0,NULL,'2020-02-20 21:37:36',NULL,1,'处闺蜜（真心真心）','正常',0,'2020-02-20 21:37:36',38927,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(120,0,NULL,'2020-02-21 02:31:54',NULL,22,'一见钟情❤','正常',0,'2020-02-21 02:31:54',39372,'我希望找到一个，担心失去我的人❤',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(121,0,NULL,'2020-02-21 04:43:13',NULL,1,'处妹妹','正常',0,'2020-02-21 04:43:13',39410,'11',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(122,0,NULL,'2020-02-21 09:27:03',NULL,2,'学习','正常',0,'2020-02-21 09:27:03',37613,'好好学习',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(123,0,NULL,'2020-02-21 19:27:48',NULL,31,'狮子座','正常',0,'2020-02-21 19:27:48',39866,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(124,0,NULL,'2020-02-21 19:28:03',NULL,1,'处对象专一','正常',0,'2020-02-21 19:28:03',39866,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(125,0,NULL,'2020-02-21 20:05:11',NULL,1,'处师傅','正常',0,'2020-02-21 20:05:11',37427,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(126,0,NULL,'2020-02-22 08:58:34',NULL,1,'扩列闲聊','正常',0,'2020-02-22 08:58:34',40485,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(127,0,NULL,'2020-02-22 11:04:05',NULL,1,'不聊污','正常',0,'2020-02-22 11:04:05',40598,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(128,0,NULL,'2020-02-22 14:55:50',NULL,1,'#喜欢的人','正常',0,'2020-02-22 14:55:50',40741,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(129,0,NULL,'2020-02-23 16:52:34',NULL,1,'妻官严','正常',0,'2020-02-23 16:52:34',41291,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(130,0,NULL,'2020-02-24 16:00:48',NULL,1,'各种关系','正常',0,'2020-02-24 16:00:48',37306,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(131,0,NULL,'2020-02-25 21:59:50',NULL,1,'手控','正常',0,'2020-02-25 21:59:50',43890,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(132,0,NULL,'2020-02-25 22:58:21',NULL,1,'尬聊','正常',0,'2020-02-25 22:58:21',44000,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(133,0,NULL,'2020-02-26 02:07:51',NULL,22,'对象','正常',0,'2020-02-26 02:07:51',44236,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(134,0,NULL,'2020-02-26 02:08:16',NULL,1,'拒颜控','正常',0,'2020-02-26 02:08:16',44236,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(135,0,NULL,'2020-02-26 03:47:44',NULL,1,'可以奔现的那种哦','正常',0,'2020-02-26 03:47:44',44298,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(136,0,NULL,'2020-02-26 06:59:58',NULL,1,'找个姐姐','正常',0,'2020-02-26 06:59:58',44335,'姐姐',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(137,0,NULL,'2020-02-26 15:47:04',NULL,1,'可爱的','正常',0,'2020-02-26 15:47:04',44552,'卖闺密',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(138,0,NULL,'2020-02-26 15:48:01',NULL,1,'卖闺密','正常',0,'2020-02-26 15:48:01',44552,'卖闺密',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(139,0,NULL,'2020-02-26 19:53:24',NULL,1,'暖男小哥哥','正常',0,'2020-02-26 19:53:24',44926,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(140,0,NULL,'2020-02-26 19:53:41',NULL,1,'男闺蜜','正常',0,'2020-02-26 19:53:41',44926,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(141,0,NULL,'2020-02-26 19:54:14',NULL,1,'拒污','正常',0,'2020-02-26 19:54:14',44926,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(142,0,NULL,'2020-02-27 19:37:42',NULL,1,'处闺蜜(❁´ω`❁)','正常',0,'2020-02-27 19:37:42',45707,'真心的',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(143,0,NULL,'2020-02-27 19:40:30',NULL,2,'伤心的话，我陪你','正常',0,'2020-02-27 19:40:30',45707,'本人是直，真直，这来自我朋友的形容?️',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(144,0,NULL,'2020-02-27 20:12:43',NULL,2,'女装大佬','正常',0,'2020-02-27 20:12:43',46070,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(145,0,NULL,'2020-02-28 10:43:45',NULL,22,'爱','正常',0,'2020-02-28 10:43:45',46455,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(146,0,NULL,'2020-02-28 22:12:29',NULL,1,'聊污加','已删除',0,'2020-02-28 22:12:29',47102,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(147,0,NULL,'2020-02-28 23:07:53',NULL,2,'爱钱','正常',0,'2020-02-28 23:07:53',46220,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(148,10,NULL,'2020-02-29 05:33:49',NULL,22,'处对象，真心的','正常',0,'2020-02-29 05:33:49',29805,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(149,0,NULL,'2020-02-29 12:42:17',NULL,22,'处对象，专一，去污','正常',0,'2020-02-29 12:42:17',47626,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(150,0,NULL,'2020-02-29 22:38:08',NULL,1,'暖男','正常',0,'2020-02-29 22:38:08',48226,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(151,0,NULL,'2020-02-29 22:38:54',NULL,1,'会宠人','正常',0,'2020-02-29 22:38:54',48226,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(152,0,NULL,'2020-02-29 23:10:12',NULL,1,'洛丽塔','正常',0,'2020-02-29 23:10:12',46220,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(153,0,NULL,'2020-02-29 23:10:34',NULL,1,'洛丽塔jk','正常',0,'2020-02-29 23:10:34',46220,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(154,0,NULL,'2020-03-01 00:55:54',NULL,1,'处兑现','正常',0,'2020-03-01 00:55:54',48361,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(155,0,NULL,'2020-03-01 00:56:25',NULL,1,'处对象……','正常',0,'2020-03-01 00:56:25',48361,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(156,0,NULL,'2020-03-01 12:19:54',NULL,1,'一起打游戏','正常',0,'2020-03-01 12:19:54',48226,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(157,0,NULL,'2020-03-01 12:20:18',NULL,1,'会吃醋','正常',0,'2020-03-01 12:20:18',48226,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(158,0,NULL,'2020-03-01 16:19:00',NULL,1,'陪聊','正常',0,'2020-03-01 16:19:00',47652,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(159,0,NULL,'2020-03-01 16:46:58',NULL,1,'腹肌','正常',0,'2020-03-01 16:46:58',48964,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(160,162,NULL,'2020-03-01 18:22:45',NULL,21,'同城','正常',0,'2020-03-01 18:22:45',49072,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(161,1,NULL,'2020-03-02 06:54:52',NULL,30,'同岁','正常',0,'2020-03-02 06:54:52',49711,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(162,0,NULL,'2020-03-02 06:55:34',NULL,1,'活泼开朗','正常',0,'2020-03-02 06:55:34',49711,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(163,0,NULL,'2020-03-02 07:03:31',NULL,14,'肖战','正常',0,'2020-03-02 07:03:31',47089,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(164,0,NULL,'2020-03-02 07:03:52',NULL,14,'肖战哥哥','正常',0,'2020-03-02 07:03:52',47089,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(165,0,NULL,'2020-03-02 07:04:26',NULL,2,'封B站','正常',0,'2020-03-02 07:04:26',47089,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(166,0,NULL,'2020-03-02 07:04:47',NULL,2,'封猫耳','正常',0,'2020-03-02 07:04:47',47089,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(167,0,NULL,'2020-03-02 07:05:14',NULL,2,'墙AO3','正常',0,'2020-03-02 07:05:14',47089,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(168,0,NULL,'2020-03-02 07:05:37',NULL,2,'封老福特','正常',0,'2020-03-02 07:05:37',47089,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(169,0,NULL,'2020-03-02 11:01:09',NULL,1,'处对象  真心谈','违规',0,'2020-03-02 11:01:09',49741,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(170,0,NULL,'2020-03-02 11:13:29',NULL,1,'运动','正常',0,'2020-03-02 11:13:29',42253,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(171,0,NULL,'2020-03-02 12:23:48',NULL,22,'双性恋','正常',0,'2020-03-02 12:23:48',49906,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(172,0,NULL,'2020-03-02 15:20:02',NULL,1,'没有话题就是最好的话题','违规',0,'2020-03-02 15:20:02',50151,'心情',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(173,0,NULL,'2020-03-02 16:12:19',NULL,1,'超甜','正常',0,'2020-03-02 16:12:19',50076,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(174,0,NULL,'2020-03-02 19:43:33',NULL,1,'处炮友','违规',0,'2020-03-02 19:43:33',49165,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(175,0,NULL,'2020-03-03 09:46:10',NULL,1,'05后处对象➕','违规',0,'2020-03-03 09:46:10',28203,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(176,0,NULL,'2020-03-03 15:34:59',NULL,1,'甜甜的恋爱','正常',0,'2020-03-03 15:34:59',51551,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(177,0,NULL,'2020-03-03 15:35:28',NULL,28,'游戏陪玩','正常',0,'2020-03-03 15:35:28',51551,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(178,0,NULL,'2020-03-03 15:36:47',NULL,1,'有趣的灵魂','正常',0,'2020-03-03 15:36:47',51551,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(179,0,NULL,'2020-03-03 18:30:26',NULL,1,'沙雕','正常',0,'2020-03-03 18:30:26',49858,'活泼开朗，不是污的，很很很会让朋友开心',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(180,0,NULL,'2020-03-03 19:28:04',NULL,30,'本人20岁','正常',0,'2020-03-03 19:28:04',51701,'一个找想真心相爱的女孩  年龄17～21',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(181,0,NULL,'2020-03-04 03:53:19',NULL,30,'95后','正常',0,'2020-03-04 03:53:19',52502,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(182,0,NULL,'2020-03-04 09:01:37',NULL,1,'认真专一','正常',0,'2020-03-04 09:01:37',48587,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(183,0,NULL,'2020-03-04 13:35:52',NULL,1,'互动谈心','正常',0,'2020-03-04 13:35:52',52905,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(184,0,NULL,'2020-03-04 19:06:16',NULL,26,'篮球','正常',0,'2020-03-04 19:06:16',53440,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(185,0,NULL,'2020-03-04 21:52:44',NULL,1,'不颜控','正常',0,'2020-03-04 21:52:44',53653,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(186,0,NULL,'2020-03-05 03:01:14',NULL,1,'抑郁症','正常',0,'2020-03-05 03:01:14',54087,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(187,0,NULL,'2020-03-05 03:01:41',NULL,2,'乱。','正常',0,'2020-03-05 03:01:41',54087,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(188,0,NULL,'2020-03-05 03:02:22',NULL,2,'思想复杂','正常',0,'2020-03-05 03:02:22',54087,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(189,0,NULL,'2020-03-05 21:20:02',NULL,1,'找基友','正常',0,'2020-03-05 21:20:02',29541,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(190,0,NULL,'2020-03-05 21:20:31',NULL,1,'找闺蜜','正常',0,'2020-03-05 21:20:31',29541,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(191,0,NULL,'2020-03-06 12:48:51',NULL,1,'小奶狗','正常',0,'2020-03-06 12:48:51',55517,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(192,0,NULL,'2020-03-06 14:29:56',NULL,1,'腿','正常',0,'2020-03-06 14:29:56',55594,'丝袜',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(193,0,NULL,'2020-03-06 19:04:04',NULL,1,'处对象啊','正常',0,'2020-03-06 19:04:04',6187,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(194,0,NULL,'2020-03-06 22:48:01',NULL,1,'帅锅锅','正常',0,'2020-03-06 22:48:01',46792,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(195,0,NULL,'2020-03-06 22:51:57',NULL,1,'不玩游戏','正常',0,'2020-03-06 22:51:57',56105,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(196,1,NULL,'2020-03-07 13:13:07',NULL,30,'05后+','正常',0,'2020-03-07 13:13:07',56731,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(197,0,NULL,'2020-03-07 14:14:12',NULL,1,'聊so','已删除',0,'2020-03-07 14:14:12',56826,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(198,0,NULL,'2020-03-07 15:42:59',NULL,1,'00处对','正常',0,'2020-03-07 15:42:59',56962,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(199,0,NULL,'2020-03-08 00:16:45',NULL,1,'小奶狗小','正常',0,'2020-03-08 00:16:45',57622,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(200,0,NULL,'2020-03-08 11:05:24',NULL,1,'互赞','正常',0,'2020-03-08 11:05:24',28548,'暖空间',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(201,0,NULL,'2020-03-08 15:07:13',NULL,26,'健身','正常',0,'2020-03-08 15:07:13',58427,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(202,0,NULL,'2020-03-08 18:45:17',NULL,28,'爱玩游戏','正常',0,'2020-03-08 18:45:17',58862,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(203,0,NULL,'2020-03-08 19:12:36',NULL,1,'零零后','正常',0,'2020-03-08 19:12:36',58893,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(204,0,NULL,'2020-03-08 22:16:05',NULL,22,'恋爱嘛','正常',0,'2020-03-08 22:16:05',59334,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(205,0,NULL,'2020-03-08 22:32:41',NULL,1,'。','正常',0,'2020-03-08 22:32:41',58657,'。',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(206,0,NULL,'2020-03-08 22:35:23',NULL,1,'陪睡','违规',0,'2020-03-08 22:35:23',58657,'。',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(207,5,NULL,'2020-03-08 23:25:10',NULL,32,'集美','正常',0,'2020-03-08 23:25:10',58657,'。',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','女',2,'\0','girl',NULL,0),

(208,0,NULL,'2020-03-08 23:50:51',NULL,2,'无聊','正常',0,'2020-03-08 23:50:51',59578,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(209,0,NULL,'2020-03-08 23:52:10',NULL,1,'活泼可爱','正常',0,'2020-03-08 23:52:10',59623,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(210,0,NULL,'2020-03-09 08:19:11',NULL,27,'动漫','正常',0,'2020-03-09 08:19:11',58416,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(211,0,NULL,'2020-03-09 09:47:00',NULL,16,'古诗词','正常',0,'2020-03-09 09:47:00',60162,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(212,0,NULL,'2020-03-09 11:07:03',NULL,1,'有腹肌','正常',0,'2020-03-09 11:07:03',60289,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(213,0,NULL,'2020-03-09 11:08:04',NULL,1,'霸道','正常',0,'2020-03-09 11:08:04',60289,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(214,0,NULL,'2020-03-09 11:37:57',NULL,1,'富婆','正常',0,'2020-03-09 11:37:57',30612,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(215,0,NULL,'2020-03-09 19:47:32',NULL,1,'文❤️','已删除',0,'2020-03-09 19:47:32',61448,'就是这么直接',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(216,0,NULL,'2020-03-09 21:32:41',NULL,2,'彩虹','正常',0,'2020-03-09 21:32:41',36118,'?',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(217,0,NULL,'2020-03-09 22:11:40',NULL,1,'连麦','正常',0,'2020-03-09 22:11:40',10067,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(218,0,NULL,'2020-03-10 12:39:32',NULL,1,'处cp嗯','正常',0,'2020-03-10 12:39:32',62114,'发现附近的你',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(219,0,NULL,'2020-03-10 21:36:08',NULL,1,'户看户看','违规',0,'2020-03-10 21:36:08',63322,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(220,0,NULL,'2020-03-10 23:59:21',NULL,1,'陕西省0','违规',0,'2020-03-10 23:59:21',63573,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(221,0,NULL,'2020-03-10 23:59:37',NULL,21,'陕西西安','正常',0,'2020-03-10 23:59:37',63573,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(222,0,NULL,'2020-03-11 09:06:40',NULL,1,'要话多的','正常',0,'2020-03-11 09:06:40',60065,'要话多说不完的',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(223,0,NULL,'2020-03-11 11:19:00',NULL,1,'渣男','正常',0,'2020-03-11 11:19:00',54165,'渣男',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(224,0,NULL,'2020-03-11 12:30:31',NULL,1,'父女恋','正常',0,'2020-03-11 12:30:31',64072,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(225,0,NULL,'2020-03-12 09:52:51',NULL,21,'长春','正常',0,'2020-03-12 09:52:51',65430,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(226,0,NULL,'2020-03-12 10:23:43',NULL,14,'貂蝉','正常',0,'2020-03-12 10:23:43',39810,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(227,0,NULL,'2020-03-13 12:41:20',NULL,1,'快手','正常',0,'2020-03-13 12:41:20',47714,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(228,1,NULL,'2020-03-13 13:45:50',NULL,2,'我的世界','正常',0,'2020-03-13 13:45:50',26690,'游戏',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(229,0,NULL,'2020-03-14 17:20:28',NULL,1,'卖妹妹了','正常',0,'2020-03-14 17:20:28',58079,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(230,0,NULL,'2020-03-15 08:31:01',NULL,1,'找弟弟','正常',0,'2020-03-15 08:31:01',67153,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(231,0,NULL,'2020-03-15 17:10:49',NULL,1,'福利','违规',0,'2020-03-15 17:10:49',53766,'红包群',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(232,0,NULL,'2020-03-15 17:11:15',NULL,1,'红包','违规',0,'2020-03-15 17:11:15',53766,'红包群',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(233,0,NULL,'2020-03-15 22:01:28',NULL,1,'要爆照','正常',0,'2020-03-15 22:01:28',67320,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(234,0,NULL,'2020-03-16 23:19:36',NULL,1,'面基','正常',0,'2020-03-16 23:19:36',72646,'线下见面',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(235,0,NULL,'2020-03-18 00:30:11',NULL,23,'电影剧情','正常',0,'2020-03-18 00:30:11',68733,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(236,0,NULL,'2020-03-18 10:22:32',NULL,22,'随便cp','正常',0,'2020-03-18 10:22:32',74881,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(237,0,NULL,'2020-03-18 17:21:00',NULL,1,'处','正常',0,'2020-03-18 17:21:00',62457,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(238,0,NULL,'2020-03-18 21:42:03',NULL,1,'求金主','正常',0,'2020-03-18 21:42:03',51446,'额额额',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(239,0,NULL,'2020-03-19 00:10:13',NULL,4,'追剧秀','正常',0,'2020-03-19 00:10:13',68733,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(240,0,NULL,'2020-03-19 11:38:06',NULL,15,'球鞋','正常',0,'2020-03-19 11:38:06',76978,'喜欢球鞋，并且也喜欢篮球，也喜欢球鞋文化',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(241,0,NULL,'2020-03-19 13:11:30',NULL,1,'可狼可奶','正常',0,'2020-03-19 13:11:30',77094,'小奶狗可奶可狼，',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(242,0,NULL,'2020-03-20 12:46:34',NULL,1,'招女婿','正常',0,'2020-03-20 12:46:34',60320,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(243,0,NULL,'2020-03-20 18:36:16',NULL,1,'可爱','正常',0,'2020-03-20 18:36:16',75573,'本人14想找一个可爱的小姐姐13一16',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(244,0,NULL,'2020-03-21 07:53:46',NULL,1,'合意网友','正常',0,'2020-03-21 07:53:46',80308,'寻找失散多年的兄弟，giao',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(245,0,NULL,'2020-03-21 13:52:41',NULL,1,'交友扩列','正常',0,'2020-03-21 13:52:41',78294,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(246,0,NULL,'2020-03-21 18:13:16',NULL,1,'拉拉交处','正常',0,'2020-03-21 18:13:16',81065,'拉拉创建处，是志同道合的姐妹们，就一起加入我们吧…??总有一片天是属于自由的，属于我们拉拉的⛺',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(247,0,NULL,'2020-03-21 18:26:45',NULL,1,'LES界','正常',0,'2020-03-21 18:26:45',81065,'同为LES的你还等什么，这里是属于我们的场合???，彩虹一般的存在为何惧怕其他因素带给你不好的思想??',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(248,0,NULL,'2020-03-22 01:23:01',NULL,1,'唠嗑','正常',0,'2020-03-22 01:23:01',67139,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(249,0,NULL,'2020-03-22 09:49:43',NULL,1,'女神野王','正常',0,'2020-03-22 09:49:43',81861,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(250,0,NULL,'2020-03-22 09:50:18',NULL,1,'颜值声优','正常',0,'2020-03-22 09:50:18',81861,'高颜值，声优',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(251,0,NULL,'2020-03-22 19:34:55',NULL,1,'深入了解','违规',0,'2020-03-22 19:34:55',82538,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(252,0,NULL,'2020-03-23 18:31:03',NULL,1,'拜师','正常',0,'2020-03-23 18:31:03',74397,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(253,0,NULL,'2020-03-23 19:27:35',NULL,30,'20下加','正常',0,'2020-03-23 19:27:35',6909,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(254,0,NULL,'2020-03-23 19:28:46',NULL,1,'连麦睡觉','正常',0,'2020-03-23 19:28:46',73039,'加上好友连麦聊天睡觉',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(255,0,NULL,'2020-03-24 14:09:57',NULL,14,'秦霄贤','正常',0,'2020-03-24 14:09:57',36072,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(256,0,NULL,'2020-03-24 19:25:41',NULL,2,'各种控','正常',0,'2020-03-24 19:25:41',86141,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(257,0,NULL,'2020-03-25 00:14:29',NULL,22,'直男','正常',0,'2020-03-25 00:14:29',86778,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(258,0,NULL,'2020-03-25 08:58:54',NULL,14,'欧文','正常',0,'2020-03-25 08:58:54',87038,'欧文球迷',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(259,0,NULL,'2020-03-25 08:59:17',NULL,26,'NBA','正常',0,'2020-03-25 08:59:17',87038,'NBAstar',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(260,0,NULL,'2020-03-25 11:38:19',NULL,1,'快手互粉','正常',0,'2020-03-25 11:38:19',67880,'快手互粉',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(261,0,NULL,'2020-03-26 07:02:01',NULL,2,'前程似锦','正常',0,'2020-03-26 07:02:01',88776,'社会没有遮天树，只有一物降一物',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(262,0,NULL,'2020-03-26 15:42:04',NULL,18,'中考','正常',0,'2020-03-26 15:42:04',59255,'学习',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(263,0,NULL,'2020-03-26 18:21:40',NULL,2,'你好','正常',0,'2020-03-26 18:21:40',89769,'我14岁，想找一个宠我，不喝酒，不蹦迪，不对我发脾气，能给我安全感，我比较粘人。\n愿意的话加我QQ\n1548348985',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(264,0,NULL,'2020-03-27 04:12:53',NULL,22,'感情专一','正常',0,'2020-03-27 04:12:53',12260,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(265,0,NULL,'2020-03-27 10:21:59',NULL,29,'##官方','正常',0,'2020-03-27 10:21:59',90320,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(266,1,NULL,'2020-03-27 12:25:41',NULL,22,'希望奔现','正常',0,'2020-03-27 12:25:41',90844,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(267,0,NULL,'2020-03-27 12:43:56',NULL,1,'SM','违规',0,'2020-03-27 12:43:56',84666,'SM',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(268,0,NULL,'2020-03-27 15:41:58',NULL,31,'天蝎','正常',0,'2020-03-27 15:41:58',91131,'小蝎子',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(269,0,NULL,'2020-03-27 20:49:53',NULL,1,'家族招人','正常',0,'2020-03-27 20:49:53',84399,'进家族吗集美们，心灼家族，是一个新的家族哦，只有大家的努力，我们才会越来越好，家族的意义是什么呢，是你的避风港，你背后的势力，当有人欺负你的时候，我们会给予你帮助，在这里，就是你的家，不过，家有家规，还是得遵守的，想要高管活跃就好，哦，对了，前提想加入的话必须改名哦，不是群名，必须是你QQ的昵称，格式：心灼⋆＋网名，欢迎各位的加入趴，必须等级一个月亮哦，加我QQ946480898，我会给你指导的。',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(270,0,NULL,'2020-03-27 20:56:41',NULL,1,'555','正常',0,'2020-03-27 20:56:41',86858,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(271,0,NULL,'2020-03-27 22:21:50',NULL,22,'奔现','正常',0,'2020-03-27 22:21:50',91722,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(272,0,NULL,'2020-03-28 09:50:49',NULL,30,'02后','正常',0,'2020-03-28 09:50:49',92134,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(273,0,NULL,'2020-03-28 10:01:55',NULL,1,'找弟弟妹','正常',0,'2020-03-28 10:01:55',92139,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(274,0,NULL,'2020-03-28 10:02:47',NULL,30,'比我小','正常',0,'2020-03-28 10:02:47',92139,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(275,0,NULL,'2020-03-28 11:44:54',NULL,28,'吃鸡苟分','正常',0,'2020-03-28 11:44:54',42732,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(276,0,NULL,'2020-03-28 20:32:59',NULL,21,'广东广州','正常',0,'2020-03-28 20:32:59',50985,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(277,0,NULL,'2020-03-28 20:33:16',NULL,1,'同性','正常',0,'2020-03-28 20:33:16',75903,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(278,0,NULL,'2020-03-28 21:45:37',NULL,28,'量子特工','正常',0,'2020-03-28 21:45:37',93044,'玩量子的',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(279,0,NULL,'2020-03-28 22:06:47',NULL,1,'师父','正常',0,'2020-03-28 22:06:47',54263,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(280,0,NULL,'2020-03-28 22:23:21',NULL,1,'志同道合','正常',0,'2020-03-28 22:23:21',93089,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(281,0,NULL,'2020-03-28 22:56:53',NULL,1,'儿砸','违规',0,'2020-03-28 22:56:53',90035,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(282,0,NULL,'2020-03-29 15:43:12',NULL,1,'找师傅','正常',0,'2020-03-29 15:43:12',93884,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(283,0,NULL,'2020-03-29 18:22:19',NULL,28,'xf手游','正常',0,'2020-03-29 18:22:19',93124,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(284,0,NULL,'2020-03-29 18:22:53',NULL,28,'cf手游','正常',0,'2020-03-29 18:22:53',93124,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(285,1,NULL,'2020-03-29 19:03:37',NULL,21,'江西','正常',0,'2020-03-29 19:03:37',94109,'江西',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(286,0,NULL,'2020-03-29 20:11:41',NULL,12,'购物','正常',0,'2020-03-29 20:11:41',94208,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(287,0,NULL,'2020-03-30 00:07:59',NULL,2,'emmm','正常',0,'2020-03-30 00:07:59',94589,'来者不拒',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(288,0,NULL,'2020-03-30 16:37:11',NULL,2,'黑暗','正常',0,'2020-03-30 16:37:11',85249,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(289,0,NULL,'2020-03-30 17:41:16',NULL,2,'蘑菇头','正常',0,'2020-03-30 17:41:16',79818,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(290,0,NULL,'2020-03-30 18:30:58',NULL,30,'2006','正常',0,'2020-03-30 18:30:58',95275,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(291,0,NULL,'2020-03-30 21:10:12',NULL,27,'火影忍者','正常',0,'2020-03-30 21:10:12',95969,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(292,0,NULL,'2020-03-31 08:42:13',NULL,26,'喜欢跑步','正常',0,'2020-03-31 08:42:13',96583,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(293,0,NULL,'2020-03-31 15:15:39',NULL,1,'不爆照','正常',0,'2020-03-31 15:15:39',97129,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(294,0,NULL,'2020-04-01 00:19:22',NULL,1,'非声控','正常',0,'2020-04-01 00:19:22',83344,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(295,0,NULL,'2020-04-01 17:33:08',NULL,1,'暖空间','正常',0,'2020-04-01 17:33:08',28548,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(296,0,NULL,'2020-04-01 21:21:34',NULL,1,'暖群','正常',0,'2020-04-01 21:21:34',88512,'在群里多发言',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(297,0,NULL,'2020-04-01 23:19:09',NULL,1,'不互看','正常',0,'2020-04-01 23:19:09',99832,'不互看',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(298,0,NULL,'2020-04-01 23:27:06',NULL,21,'广西','正常',0,'2020-04-01 23:27:06',99840,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(299,0,NULL,'2020-04-01 23:28:18',NULL,1,'可盐可甜','正常',0,'2020-04-01 23:28:18',99844,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(300,0,NULL,'2020-04-02 10:36:22',NULL,1,'#找弟弟','正常',0,'2020-04-02 10:36:22',99648,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(301,0,NULL,'2020-04-02 13:31:05',NULL,1,'要小哥哥','正常',0,'2020-04-02 13:31:05',9752,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(302,0,NULL,'2020-04-02 15:28:18',NULL,27,'玩cos','正常',0,'2020-04-02 15:28:18',94109,'cos',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(303,0,NULL,'2020-04-03 05:09:42',NULL,1,'找老乡','正常',0,'2020-04-03 05:09:42',101726,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(304,0,NULL,'2020-04-03 07:59:31',NULL,1,'卖闺蜜','正常',0,'2020-04-03 07:59:31',101467,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(305,2,NULL,'2020-04-03 15:30:49',NULL,28,'游戏','正常',0,'2020-04-03 15:30:49',102472,'找个小姐姐打游戏开麦',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(306,0,NULL,'2020-04-03 21:09:19',NULL,18,'高考文科','正常',0,'2020-04-03 21:09:19',102856,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(307,0,NULL,'2020-04-04 14:48:11',NULL,1,'cp男男','正常',0,'2020-04-04 14:48:11',73468,'男攻',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(308,0,NULL,'2020-04-04 20:37:50',NULL,1,'音色好听','正常',0,'2020-04-04 20:37:50',105554,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(309,0,NULL,'2020-04-06 10:50:21',NULL,1,'找爸爸','违规',0,'2020-04-06 10:50:21',73346,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(310,0,NULL,'2020-04-06 14:36:08',NULL,1,'出来玩','正常',0,'2020-04-06 14:36:08',108575,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(311,0,NULL,'2020-04-07 13:58:53',NULL,2,'兼职赚米','正常',0,'2020-04-07 13:58:53',110230,'一起赚票票',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(312,0,NULL,'2020-04-07 21:45:44',NULL,2,'明日之后','正常',0,'2020-04-07 21:45:44',110902,'一款游戏',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(313,0,NULL,'2020-04-08 01:12:02',NULL,1,'很丑','正常',0,'2020-04-08 01:12:02',111407,'本人脾气臭，脾气臭，长得丑，爱生气有时候有时候笨的',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(314,0,NULL,'2020-04-08 09:10:41',NULL,1,'有Q自加','正常',0,'2020-04-08 09:10:41',111594,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(315,0,NULL,'2020-04-08 22:59:42',NULL,2,'只玩迷你','正常',0,'2020-04-08 22:59:42',113266,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(316,0,NULL,'2020-04-09 13:20:17',NULL,2,'暖','正常',0,'2020-04-09 13:20:17',103396,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(317,2,NULL,'2020-04-10 17:20:46',NULL,1,'找姐姐','正常',0,'2020-04-10 17:20:46',115971,'，，',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(318,0,NULL,'2020-04-10 21:41:17',NULL,1,'比较黏人','正常',0,'2020-04-10 21:41:17',85783,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(319,0,NULL,'2020-04-11 00:02:48',NULL,1,'找女徒弟','正常',0,'2020-04-11 00:02:48',73346,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(320,0,NULL,'2020-04-12 18:47:44',NULL,1,'德云女孩','正常',0,'2020-04-12 18:47:44',105188,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(321,0,NULL,'2020-04-12 23:05:18',NULL,1,'游戏cp','正常',0,'2020-04-12 23:05:18',107509,'只一起打游戏当游戏中是情侣',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(322,7,NULL,'2020-04-13 09:36:21',NULL,30,'16以下','正常',0,'2020-04-13 09:36:21',110743,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(323,0,NULL,'2020-04-13 09:38:24',NULL,1,'有点颜控','正常',0,'2020-04-13 09:38:24',110743,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(324,0,NULL,'2020-04-13 10:27:37',NULL,1,'微信','正常',0,'2020-04-13 10:27:37',111594,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(325,0,NULL,'2020-04-13 11:41:34',NULL,1,'找女儿','正常',0,'2020-04-13 11:41:34',76118,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(326,0,NULL,'2020-04-13 13:45:27',NULL,28,'王者代打','正常',0,'2020-04-13 13:45:27',117037,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(327,0,NULL,'2020-04-13 17:14:26',NULL,1,'追星','正常',0,'2020-04-13 17:14:26',119223,'?本哥',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(328,0,NULL,'2020-04-14 17:05:45',NULL,1,'可奶可狼','正常',0,'2020-04-14 17:05:45',116239,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(329,0,NULL,'2020-04-14 17:20:41',NULL,1,'jk','正常',0,'2020-04-14 17:20:41',119390,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(330,0,NULL,'2020-04-17 06:24:32',NULL,1,'不处对象','正常',0,'2020-04-17 06:24:32',98549,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(331,0,NULL,'2020-04-17 08:36:26',NULL,1,'社会人','正常',0,'2020-04-17 08:36:26',75005,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(332,0,NULL,'2020-04-17 16:42:55',NULL,1,'徒弟','正常',0,'2020-04-17 16:42:55',107171,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(333,0,NULL,'2020-04-18 06:55:19',NULL,1,'语音聊天','正常',0,'2020-04-18 06:55:19',125314,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(334,0,NULL,'2020-04-18 11:04:22',NULL,1,'wu对象','违规',0,'2020-04-18 11:04:22',125482,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(335,0,NULL,'2020-04-18 22:46:28',NULL,1,'有微自➕','正常',0,'2020-04-18 22:46:28',125314,'有微信的可以自行添加',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(336,0,NULL,'2020-04-19 09:42:44',NULL,30,'06年','正常',0,'2020-04-19 09:42:44',95275,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(337,0,NULL,'2020-04-20 18:20:44',NULL,1,'小萝莉','正常',0,'2020-04-20 18:20:44',74432,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(338,0,NULL,'2020-04-20 22:34:15',NULL,1,'处主仆','违规',0,'2020-04-20 22:34:15',127700,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(339,0,NULL,'2020-04-24 20:37:27',NULL,1,'养火花','正常',0,'2020-04-24 20:37:27',81447,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(340,0,NULL,'2020-04-25 01:48:23',NULL,12,'赚钱','正常',0,'2020-04-25 01:48:23',38269,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(341,0,NULL,'2020-04-25 01:51:22',NULL,1,'汉服','正常',0,'2020-04-25 01:51:22',64606,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(342,0,NULL,'2020-04-26 05:49:08',NULL,1,'不打遊戲','正常',0,'2020-04-26 05:49:08',82719,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(343,0,NULL,'2020-04-26 05:49:08',NULL,1,'不打遊戲1','正常',0,'2020-04-26 05:49:08',82719,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(344,0,NULL,'2020-04-26 17:14:35',NULL,1,'奶狗','正常',0,'2020-04-26 17:14:35',132471,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(345,0,NULL,'2020-04-27 17:47:04',NULL,9,'说唱','正常',0,'2020-04-27 17:47:04',136534,'嘻哈音乐',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(346,0,NULL,'2020-04-29 03:02:02',NULL,1,'互看姐姐','违规',0,'2020-04-29 03:02:02',138110,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(347,0,NULL,'2020-04-29 19:41:43',NULL,2,'仓鼠宠物','正常',0,'2020-04-29 19:41:43',79902,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(348,0,NULL,'2020-04-29 22:47:39',NULL,22,'爱媳妇儿','正常',0,'2020-04-29 22:47:39',95761,'有主人的男生。',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(349,0,NULL,'2020-04-30 00:09:43',NULL,1,'喜欢暖男','正常',0,'2020-04-30 00:09:43',139210,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(350,0,NULL,'2020-05-02 09:54:25',NULL,22,'情感咨询','正常',0,'2020-05-02 09:54:25',142734,'情感陪聊，诉说情感，解决情感问题，咨询心理问题，15元一个问题',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(351,0,NULL,'2020-05-03 03:05:07',NULL,1,'处陪伴','正常',0,'2020-05-03 03:05:07',144092,'陪伴你我，让世界充满爱',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(352,0,NULL,'2020-05-04 05:55:49',NULL,16,'人间失格','正常',0,'2020-05-04 05:55:49',144717,'人世',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(353,0,NULL,'2020-05-04 10:41:17',NULL,1,'姐姐哥哥','正常',0,'2020-05-04 10:41:17',109282,'找哥哥姐姐学习好的',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(354,0,NULL,'2020-05-09 18:59:14',NULL,1,'不渣','正常',0,'2020-05-09 18:59:14',150893,'要专一，不要渣男',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(355,0,NULL,'2020-05-13 19:50:35',NULL,1,'找小哥哥6','正常',0,'2020-05-13 19:50:35',134032,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(356,0,NULL,'2020-05-13 19:50:35',NULL,1,'找小哥哥5','正常',0,'2020-05-13 19:50:35',134032,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(357,0,NULL,'2020-05-13 19:50:35',NULL,1,'找小哥哥4','正常',0,'2020-05-13 19:50:35',134032,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(358,0,NULL,'2020-05-13 19:50:35',NULL,1,'找小哥哥3','正常',0,'2020-05-13 19:50:35',134032,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(359,0,NULL,'2020-05-13 19:50:35',NULL,1,'找小哥哥2','正常',0,'2020-05-13 19:50:35',134032,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(360,0,NULL,'2020-05-13 19:50:35',NULL,1,'找小哥哥1','正常',0,'2020-05-13 19:50:35',134032,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(361,0,NULL,'2020-05-13 19:50:35',NULL,1,'找小哥哥','正常',0,'2020-05-13 19:50:35',134032,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(362,0,NULL,'2020-05-13 23:46:25',NULL,1,'腐女','正常',0,'2020-05-13 23:46:25',101517,'女生喜欢和男生玩。',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(363,0,NULL,'2020-05-14 16:36:05',NULL,1,'会唱歌的','正常',0,'2020-05-14 16:36:05',153410,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(364,0,NULL,'2020-05-15 04:00:40',NULL,1,'我是处男','正常',0,'2020-05-15 04:00:40',153690,'我也挺难',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(365,0,NULL,'2020-05-16 19:25:16',NULL,28,'打吃鸡','正常',0,'2020-05-16 19:25:16',32292,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(366,0,NULL,'2020-05-17 14:59:27',NULL,1,'处cp别','正常',0,'2020-05-17 14:59:27',155519,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(367,0,NULL,'2020-05-19 07:00:24',NULL,22,'520','正常',0,'2020-05-19 07:00:24',29711,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(368,0,NULL,'2020-05-21 14:18:48',NULL,9,'网易云','正常',0,'2020-05-21 14:18:48',157377,'网易云音乐',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(369,0,NULL,'2020-05-22 23:32:28',NULL,12,'广告','正常',0,'2020-05-22 23:32:28',108,'商业广告',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(370,1,NULL,'2020-05-23 23:12:28',NULL,21,'幸运字符','正常',0,'2020-05-23 23:12:28',99398,'一起来抽字符刷字符啊',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(371,0,NULL,'2020-05-25 01:31:40',NULL,1,'找嫂子啊','正常',0,'2020-05-25 01:31:40',121684,'快快快',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(372,0,NULL,'2020-05-25 01:31:41',NULL,1,'找嫂子啊1','正常',0,'2020-05-25 01:31:41',121684,'快快快',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(373,0,NULL,'2020-05-28 19:13:45',NULL,1,'小螃蟹','正常',0,'2020-05-28 19:13:45',161013,'只有四叶草和王俊凯的粉丝可参与的话题',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(374,0,NULL,'2020-05-28 19:22:09',NULL,24,'王俊凯','正常',0,'2020-05-28 19:22:09',161013,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(375,0,NULL,'2020-05-29 16:58:25',NULL,1,'求by','正常',0,'2020-05-29 16:58:25',92021,'by',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(376,0,NULL,'2020-05-30 13:23:35',NULL,1,'ghs','违规',0,'2020-05-30 13:23:35',104443,'ghs',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(377,0,NULL,'2020-05-31 09:57:56',NULL,22,'可以奔现','正常',0,'2020-05-31 09:57:56',50985,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(379,0,NULL,'2020-05-31 20:40:48',NULL,1,'找兄弟','正常',0,'2020-05-31 20:40:48',162659,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(380,0,NULL,'2020-06-01 01:35:13',NULL,30,'05后','正常',0,'2020-06-01 01:35:13',26613,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(381,0,NULL,'2020-06-01 21:23:57',NULL,2,'分享穿搭','正常',0,'2020-06-01 21:23:57',154256,'有的小姐妹需要，逛街买衣服太麻烦，网上买衣服又不怕，所以只能来分享穿搭',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(382,0,NULL,'2020-06-01 21:23:57',NULL,2,'分享穿搭1','已删除',0,'2020-06-01 21:23:57',154256,'有的小姐妹需要，逛街买衣服太麻烦，网上买衣服又不怕，所以只能来分享穿搭',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(383,0,NULL,'2020-06-01 21:43:02',NULL,22,'找对象','正常',0,'2020-06-01 21:43:02',160403,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(384,0,NULL,'2020-06-02 05:00:56',NULL,1,'m系列','违规',0,'2020-06-02 05:00:56',163226,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(385,0,NULL,'2020-06-02 11:16:04',NULL,1,'声控处对','正常',0,'2020-06-02 11:16:04',163318,'13声控处对象，14.15',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(386,0,NULL,'2020-06-03 15:40:31',NULL,1,'可以爆照','正常',0,'2020-06-03 15:40:31',160403,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(387,0,NULL,'2020-06-04 03:12:02',NULL,1,'病娇','正常',0,'2020-06-04 03:12:02',106515,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(388,0,NULL,'2020-06-09 23:45:18',NULL,1,'测试','已删除',0,'2020-06-09 23:45:18',108,'急急急',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(389,0,NULL,'2020-06-13 01:50:06',NULL,1,'文案','正常',0,'2020-06-13 01:50:06',108,'好玩的文案',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(390,0,NULL,'2020-06-15 12:20:52',NULL,1,'找徒弟','正常',0,'2020-06-15 12:20:52',35076,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(391,0,NULL,'2020-06-16 01:03:05',NULL,1,'看文','正常',0,'2020-06-16 01:03:05',166117,'看文交流',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(392,0,NULL,'2020-06-18 21:00:03',NULL,1,'处姐姐','正常',0,'2020-06-18 21:00:03',168659,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(393,0,NULL,'2020-06-20 16:32:17',NULL,1,'加好友','正常',0,'2020-06-20 16:32:17',169608,'1492966928QQ',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(394,0,NULL,'2020-06-20 18:11:36',NULL,1,'宠我？','正常',0,'2020-06-20 18:11:36',75429,'喜欢我，陪着我，爱我，不辜负我——',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(395,0,NULL,'2020-06-21 21:20:58',NULL,1,'养巨轮','正常',0,'2020-06-21 21:20:58',75429,'就是一起说话',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(396,0,NULL,'2020-06-25 11:21:13',NULL,1,'原相机','正常',0,'2020-06-25 11:21:13',137854,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(397,0,NULL,'2020-06-25 17:05:43',NULL,1,'我很霸道','正常',0,'2020-06-25 17:05:43',75429,'听我的',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(398,0,NULL,'2020-06-25 19:23:28',NULL,1,'＃冲＃','正常',0,'2020-06-25 19:23:28',172259,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(399,0,NULL,'2020-06-26 14:07:51',NULL,1,'卑微','正常',0,'2020-06-26 14:07:51',172259,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(400,0,NULL,'2020-06-28 01:08:08',NULL,1,'王者CP','正常',0,'2020-06-28 01:08:08',81363,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(401,0,NULL,'2020-06-28 03:02:47',NULL,1,'双子座','正常',0,'2020-06-28 03:02:47',173803,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(402,0,NULL,'2020-06-28 16:43:44',NULL,1,'平凡人','正常',0,'2020-06-28 16:43:44',172503,'平凡的世界平凡的你',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(403,0,NULL,'2020-06-28 22:09:42',NULL,1,'大叔','正常',0,'2020-06-28 22:09:42',174254,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(404,0,NULL,'2020-06-29 14:02:30',NULL,1,'时候啊！','正常',0,'2020-06-29 14:02:30',174530,'没啥',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(405,0,NULL,'2020-07-04 10:57:55',NULL,1,'颜控声控','正常',0,'2020-07-04 10:57:55',175474,'颜值高并且声音好听',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(406,0,NULL,'2020-07-04 11:01:49',NULL,1,'颜值声控','正常',0,'2020-07-04 11:01:49',175474,'颜值高并且声音好听',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(407,0,NULL,'2020-07-07 14:29:56',NULL,1,'没有qq','正常',0,'2020-07-07 14:29:56',177821,'没有qq',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(408,0,NULL,'2020-07-07 17:21:07',NULL,1,'月老','正常',0,'2020-07-07 17:21:07',177821,'月老服务',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(409,0,NULL,'2020-07-07 17:21:20',NULL,1,'红娘','正常',0,'2020-07-07 17:21:20',177821,'红娘',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(410,0,NULL,'2020-07-07 17:21:39',NULL,1,'不花钱','正常',0,'2020-07-07 17:21:39',177821,'不花钱',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(411,0,NULL,'2020-07-08 12:14:30',NULL,1,'找闺蜜。','正常',0,'2020-07-08 12:14:30',178393,'14至16岁。我跟你熟了之后有点儿烦。别嫌弃。',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(412,0,NULL,'2020-07-08 15:43:10',NULL,1,'18岁','正常',0,'2020-07-08 15:43:10',178565,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(413,0,NULL,'2020-07-09 00:52:22',NULL,1,'女闺蜜','正常',0,'2020-07-09 00:52:22',178929,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(414,0,NULL,'2020-07-11 18:01:29',NULL,1,'小仙女','正常',0,'2020-07-11 18:01:29',166993,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(415,0,NULL,'2020-07-11 20:07:11',NULL,1,'聊群','正常',0,'2020-07-11 20:07:11',177821,'聊群',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(416,0,NULL,'2020-07-11 20:07:32',NULL,1,'单身狗的','正常',0,'2020-07-11 20:07:32',177821,'哎嘿嘿',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(417,0,NULL,'2020-07-13 18:43:21',NULL,1,'深入交流','正常',0,'2020-07-13 18:43:21',84758,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(418,0,NULL,'2020-07-15 15:29:21',NULL,1,'迷你世界','正常',0,'2020-07-15 15:29:21',174569,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(419,0,NULL,'2020-07-19 03:54:37',NULL,1,'喝酒蹦迪','正常',0,'2020-07-19 03:54:37',183575,'喝喝酒聊聊天蹦迪',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(420,0,NULL,'2020-07-19 08:12:00',NULL,1,'什么？','正常',0,'2020-07-19 08:12:00',75429,'什么鬼？',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(421,0,NULL,'2020-07-19 23:38:37',NULL,1,'找姐妹','正常',0,'2020-07-19 23:38:37',166409,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(422,0,NULL,'2020-07-20 01:02:04',NULL,1,'wu互看','违规',0,'2020-07-20 01:02:04',143849,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(423,0,NULL,'2020-07-20 20:22:39',NULL,1,'找女cP','正常',0,'2020-07-20 20:22:39',182886,'帮朋友找cP',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(424,0,NULL,'2020-07-29 09:28:34',NULL,1,'萌新','正常',0,'2020-07-29 09:28:34',188383,'新来的',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(425,0,NULL,'2020-07-31 20:19:05',NULL,1,'找t','正常',0,'2020-07-31 20:19:05',189418,'找攻攻的百合姐姐',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(426,1,NULL,'2020-07-31 22:38:35',NULL,1,'光遇交友','正常',0,'2020-07-31 22:38:35',185513,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(427,0,NULL,'2020-08-01 07:37:27',NULL,1,'食','正常',0,'2020-08-01 07:37:27',189140,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(428,0,NULL,'2020-08-01 07:43:29',NULL,1,'食玩图纸','正常',0,'2020-08-01 07:43:29',189140,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(429,0,NULL,'2020-08-05 02:09:19',NULL,1,'盗墓笔记','正常',0,'2020-08-05 02:09:19',187870,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(430,0,NULL,'2020-08-05 22:57:34',NULL,1,'白羊座','正常',0,'2020-08-05 22:57:34',191736,'星座',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(431,0,NULL,'2020-08-08 20:12:50',NULL,1,'大学','正常',0,'2020-08-08 20:12:50',63363,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(432,0,NULL,'2020-08-14 00:59:35',NULL,1,'穿越火线','正常',0,'2020-08-14 00:59:35',195459,'穿越火线枪战王者',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(433,0,NULL,'2020-08-14 11:51:10',NULL,1,'咕咕','正常',0,'2020-08-14 11:51:10',58946,'交真心朋友',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(434,0,NULL,'2020-08-14 15:15:12',NULL,1,'帅气小哥','正常',0,'2020-08-14 15:15:12',195700,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(435,0,NULL,'2020-08-14 15:15:30',NULL,1,'帅哥','正常',0,'2020-08-14 15:15:30',195700,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(436,0,NULL,'2020-08-17 09:25:58',NULL,1,'本地','正常',0,'2020-08-17 09:25:58',197089,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(437,0,NULL,'2020-08-18 23:06:03',NULL,1,'03后','正常',0,'2020-08-18 23:06:03',198398,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(438,0,NULL,'2020-08-18 23:12:45',NULL,1,'灵魂伴侣','正常',0,'2020-08-18 23:12:45',198398,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(439,0,NULL,'2020-08-20 08:29:49',NULL,1,'聊唔','违规',0,'2020-08-20 08:29:49',199396,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(440,1,NULL,'2020-08-22 13:53:18',NULL,1,'看小说','正常',0,'2020-08-22 13:53:18',67819,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(441,0,NULL,'2020-08-27 04:45:16',NULL,1,'占有欲强','正常',0,'2020-08-27 04:45:16',86136,'占有欲强烈',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(442,0,NULL,'2020-08-27 10:29:55',NULL,1,'住校','正常',0,'2020-08-27 10:29:55',188915,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(443,0,NULL,'2020-08-27 15:40:46',NULL,1,'找dk','违规',0,'2020-08-27 15:40:46',203426,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(444,2,NULL,'2020-08-29 10:49:34',NULL,1,'同性恋','正常',0,'2020-08-29 10:49:34',8258,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(445,0,NULL,'2020-09-13 22:02:37',NULL,1,'C','正常',0,'2020-09-13 22:02:37',207923,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(446,0,NULL,'2020-09-15 20:37:57',NULL,1,'粉墨','正常',0,'2020-09-15 20:37:57',175474,'Blackpink的粉丝',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(447,0,NULL,'2020-09-15 20:42:06',NULL,1,'阿米','正常',0,'2020-09-15 20:42:06',175474,'BTS防弹少年团的粉丝',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(448,0,NULL,'2020-09-15 22:52:28',NULL,1,'兄妹恋','正常',0,'2020-09-15 22:52:28',208726,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(449,0,NULL,'2020-09-16 16:03:19',NULL,1,'唯一','正常',0,'2020-09-16 16:03:19',208972,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(450,0,NULL,'2020-09-16 22:06:26',NULL,1,'同性cp','正常',0,'2020-09-16 22:06:26',188520,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(451,0,NULL,'2020-09-19 13:52:45',NULL,1,'中学生','正常',0,'2020-09-19 13:52:45',210208,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(452,0,NULL,'2020-09-19 21:13:10',NULL,1,'小可爱','正常',0,'2020-09-19 21:13:10',210569,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(453,0,NULL,'2020-09-22 13:51:47',NULL,1,'红包群主','违规',0,'2020-09-22 13:51:47',207073,'红包群主',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(454,0,NULL,'2020-09-26 18:00:35',NULL,1,'渣男勿扰','正常',0,'2020-09-26 18:00:35',197682,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(455,0,NULL,'2020-09-27 20:57:00',NULL,1,'务必真心','正常',0,'2020-09-27 20:57:00',75429,'真心对待！',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(456,0,NULL,'2020-09-29 20:11:32',NULL,1,'暴富','正常',0,'2020-09-29 20:11:32',213694,'带砖?',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(457,0,NULL,'2020-09-30 22:38:55',NULL,1,'高三','正常',0,'2020-09-30 22:38:55',172259,'淦',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(458,0,NULL,'2020-10-03 15:11:36',NULL,1,'段嘉许','正常',0,'2020-10-03 15:11:36',191974,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(459,0,NULL,'2020-10-07 15:28:23',NULL,1,'女m','违规',0,'2020-10-07 15:28:23',211361,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(460,0,NULL,'2020-10-11 07:39:57',NULL,1,'字母','违规',0,'2020-10-11 07:39:57',214815,'懂的都懂',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(461,0,NULL,'2020-10-12 12:27:03',NULL,1,'说实话','正常',0,'2020-10-12 12:27:03',219175,'小孩子家家16以下就别出来处对象了',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(462,0,NULL,'2020-10-18 06:48:51',NULL,1,'富豪','正常',0,'2020-10-18 06:48:51',220349,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(463,0,NULL,'2020-10-18 06:49:51',NULL,1,'富二代','违规',0,'2020-10-18 06:49:51',220349,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(464,0,NULL,'2020-10-19 00:31:38',NULL,1,'狼尾女孩','正常',0,'2020-10-19 00:31:38',51446,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(465,0,NULL,'2020-10-21 19:15:16',NULL,1,'12','正常',0,'2020-10-21 19:15:16',220345,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(466,0,NULL,'2020-10-24 17:30:52',NULL,1,'永远爱你','正常',0,'2020-10-24 17:30:52',157143,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(467,0,NULL,'2020-10-24 17:31:20',NULL,1,'你是唯一','正常',0,'2020-10-24 17:31:20',157143,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(468,0,NULL,'2020-10-25 11:33:13',NULL,1,'愿你安好','正常',0,'2020-10-25 11:33:13',157143,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(469,0,NULL,'2020-10-27 21:23:18',NULL,1,'学习渣女','正常',0,'2020-10-27 21:23:18',221279,'渣渣渣',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(470,0,NULL,'2020-10-30 20:28:21',NULL,1,'非人学院','正常',0,'2020-10-30 20:28:21',200466,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(471,0,NULL,'2020-10-31 12:16:46',NULL,1,'脾气好','正常',0,'2020-10-31 12:16:46',223029,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(472,0,NULL,'2020-10-31 12:17:43',NULL,1,'菜还爱玩','正常',0,'2020-10-31 12:17:43',223029,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(473,0,NULL,'2020-10-31 12:19:10',NULL,1,'菜且爱玩','正常',0,'2020-10-31 12:19:10',223029,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(474,0,NULL,'2020-10-31 23:46:11',NULL,1,'寻找富婆','正常',0,'2020-10-31 23:46:11',124290,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(475,0,NULL,'2020-11-04 20:57:11',NULL,1,'cos','正常',0,'2020-11-04 20:57:11',212713,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(476,0,NULL,'2020-11-21 17:59:56',NULL,1,'新人报到','正常',0,'2020-11-21 17:59:56',225095,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(477,0,NULL,'2020-11-23 19:24:51',NULL,1,'女同gl','违规',0,'2020-11-23 19:24:51',225343,'同性恋',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(478,0,NULL,'2020-12-01 00:51:14',NULL,1,'高中生','正常',0,'2020-12-01 00:51:14',227284,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(479,0,NULL,'2020-12-04 14:35:28',NULL,1,'浙江','正常',0,'2020-12-04 14:35:28',227916,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(480,0,NULL,'2020-12-04 14:35:45',NULL,1,'靓仔','正常',0,'2020-12-04 14:35:45',227916,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(481,0,NULL,'2020-12-04 20:25:46',NULL,1,'体育生呦','正常',0,'2020-12-04 20:25:46',221545,'高，帅气，',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(482,0,NULL,'2020-12-06 22:54:23',NULL,1,'mc','正常',0,'2020-12-06 22:54:23',161389,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(483,0,NULL,'2020-12-07 01:39:40',NULL,1,'小猫咪','正常',0,'2020-12-07 01:39:40',45221,'小猫咪的设定就是要听主人话。',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(484,0,NULL,'2020-12-07 23:20:11',NULL,1,'exo','正常',0,'2020-12-07 23:20:11',228883,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(485,0,NULL,'2020-12-09 22:24:55',NULL,1,'偏爱给你','正常',0,'2020-12-09 22:24:55',229470,'偏爱和例外都给你',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(486,0,NULL,'2020-12-10 00:37:06',NULL,1,'求解谢谢','正常',0,'2020-12-10 00:37:06',229541,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(487,0,NULL,'2020-12-12 04:15:17',NULL,1,'处母子','违规',0,'2020-12-12 04:15:17',230071,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(488,0,NULL,'2020-12-13 02:36:48',NULL,1,'死肥宅','正常',0,'2020-12-13 02:36:48',216709,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(489,0,NULL,'2020-12-16 21:35:23',NULL,1,'王一博','正常',0,'2020-12-16 21:35:23',231652,'追星女孩',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(490,0,NULL,'2020-12-19 22:45:05',NULL,1,'污污','违规',0,'2020-12-19 22:45:05',232634,'找污污的小姐姐',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(491,0,NULL,'2020-12-21 21:16:00',NULL,1,'一起跨年','正常',0,'2020-12-21 21:16:00',233073,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(492,0,NULL,'2020-12-22 17:03:14',NULL,1,'不要污','正常',0,'2020-12-22 17:03:14',231758,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(493,0,NULL,'2020-12-22 22:49:14',NULL,1,'话唠','正常',0,'2020-12-22 22:49:14',233757,'话多者必备?',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(494,0,NULL,'2020-12-25 08:43:08',NULL,1,'处w友','违规',0,'2020-12-25 08:43:08',234283,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(495,0,NULL,'2020-12-29 18:30:29',NULL,1,'聊www','违规',0,'2020-12-29 18:30:29',141179,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(496,0,NULL,'2020-12-30 22:29:28',NULL,1,'约炮','违规',0,'2020-12-30 22:29:28',193241,'男和男性爱',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(497,0,NULL,'2020-12-30 22:29:51',NULL,1,'约泡','违规',0,'2020-12-30 22:29:51',193241,'男和男性爱',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(498,0,NULL,'2021-01-08 01:52:58',NULL,1,'小妹妹','正常',0,'2021-01-08 01:52:58',237072,'妹妹',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(499,0,NULL,'2021-01-08 14:54:23',NULL,1,'妄想山海','正常',0,'2021-01-08 14:54:23',239974,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(500,0,NULL,'2021-01-09 15:52:26',NULL,1,'测试66','正常',0,'2021-01-09 15:52:26',240416,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(501,0,NULL,'2021-01-10 07:04:43',NULL,1,'文ai爱','违规',0,'2021-01-10 07:04:43',221987,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(502,0,NULL,'2021-01-11 13:52:03',NULL,1,'测试男友','正常',0,'2021-01-11 13:52:03',241134,'非诚勿扰',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(503,0,NULL,'2021-01-12 09:48:48',NULL,1,'聊wu~','违规',0,'2021-01-12 09:48:48',241778,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(504,0,NULL,'2021-01-21 00:45:18',NULL,1,'使命召唤','正常',0,'2021-01-21 00:45:18',100930,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(505,0,NULL,'2021-01-21 18:57:59',NULL,1,'地方','正常',0,'2021-01-21 18:57:59',117803,'安徽省六安市裕安区',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(506,0,NULL,'2021-01-23 18:21:46',NULL,1,'CSGO','正常',0,'2021-01-23 18:21:46',245177,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(507,0,NULL,'2021-01-24 23:26:34',NULL,1,'cosp','正常',0,'2021-01-24 23:26:34',225504,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(508,0,NULL,'2021-01-26 15:34:48',NULL,1,'黑丝控','违规',0,'2021-01-26 15:34:48',246881,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(509,0,NULL,'2021-01-27 12:58:03',NULL,1,'奶油丁沟','正常',0,'2021-01-27 12:58:03',247270,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(510,0,NULL,'2021-01-28 23:43:22',NULL,1,'住宿生','正常',0,'2021-01-28 23:43:22',175173,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(511,0,NULL,'2021-01-30 15:31:00',NULL,1,'约啪啪啪','违规',0,'2021-01-30 15:31:00',246969,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(512,0,NULL,'2021-01-30 21:42:36',NULL,1,'13岁','正常',0,'2021-01-30 21:42:36',247259,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(513,0,NULL,'2021-02-01 01:21:09',NULL,1,'读书','正常',0,'2021-02-01 01:21:09',108,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(514,0,NULL,'2021-02-01 18:58:08',NULL,1,'小狼狗','正常',0,'2021-02-01 18:58:08',250363,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(515,0,NULL,'2021-02-01 19:20:32',NULL,1,'粉丝','正常',0,'2021-02-01 19:20:32',147211,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(516,0,NULL,'2021-02-02 20:00:18',NULL,1,'找w友','违规',0,'2021-02-02 20:00:18',226264,'vv的人➕',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(517,0,NULL,'2021-02-02 22:32:52',NULL,1,'东北老妹','违规',0,'2021-02-02 22:32:52',249541,'东北',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(518,0,NULL,'2021-02-05 23:22:22',NULL,1,'les','正常',0,'2021-02-05 23:22:22',253413,'女同',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(519,0,NULL,'2021-02-06 16:07:57',NULL,1,'找bab','正常',0,'2021-02-06 16:07:57',253754,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(520,0,NULL,'2021-02-06 23:49:40',NULL,1,'互看','正常',0,'2021-02-06 23:49:40',247726,'互看',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(521,0,NULL,'2021-02-06 23:50:13',NULL,1,'5','正常',0,'2021-02-06 23:50:13',247726,'5',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(522,0,NULL,'2021-02-07 04:39:59',NULL,1,'爱腹肌','正常',0,'2021-02-07 04:39:59',253754,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(523,0,NULL,'2021-02-07 16:03:11',NULL,1,'艺体生','正常',0,'2021-02-07 16:03:11',254413,'中国舞专业',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(524,0,NULL,'2021-02-07 16:03:41',NULL,1,'舞蹈艺体','正常',0,'2021-02-07 16:03:41',254413,'中国舞',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(525,0,NULL,'2021-02-10 11:15:02',NULL,1,'找弟妹','正常',0,'2021-02-10 11:15:02',198787,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(526,0,NULL,'2021-02-10 22:24:51',NULL,1,'宫圈','正常',0,'2021-02-10 22:24:51',159676,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(527,0,NULL,'2021-02-11 13:25:01',NULL,1,'新年快乐','正常',0,'2021-02-11 13:25:01',255400,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(528,0,NULL,'2021-02-13 01:07:40',NULL,1,'聊5','违规',0,'2021-02-13 01:07:40',236660,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(529,0,NULL,'2021-02-13 20:08:47',NULL,1,'华晨宇','正常',0,'2021-02-13 20:08:47',257719,'华晨宇粉丝',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(530,0,NULL,'2021-02-17 18:37:47',NULL,1,'wu','违规',0,'2021-02-17 18:37:47',41581,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(531,0,NULL,'2021-02-18 22:05:03',NULL,1,'JK萝莉','正常',0,'2021-02-18 22:05:03',195007,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(532,0,NULL,'2021-02-19 16:58:09',NULL,1,'ttl','正常',0,'2021-02-19 16:58:09',240805,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(533,0,NULL,'2021-02-20 02:51:52',NULL,1,'关注姐姐','正常',0,'2021-02-20 02:51:52',198787,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(534,0,NULL,'2021-02-20 16:18:49',NULL,1,'崽崽','正常',0,'2021-02-20 16:18:49',256898,'游戏',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(535,0,NULL,'2021-02-23 09:12:45',NULL,1,'不好看','正常',0,'2021-02-23 09:12:45',263292,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(536,0,NULL,'2021-02-24 11:04:23',NULL,1,'#表演#','违规',0,'2021-02-24 11:04:23',246285,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(537,0,NULL,'2021-02-24 19:03:48',NULL,1,'美女','正常',0,'2021-02-24 19:03:48',252542,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(538,0,NULL,'2021-02-28 17:23:42',NULL,1,'帮找','正常',0,'2021-02-28 17:23:42',209867,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(539,0,NULL,'2021-03-01 23:23:10',NULL,1,'闷骚','正常',0,'2021-03-01 23:23:10',261143,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(540,0,NULL,'2021-03-04 18:40:49',NULL,1,'可爆照','正常',0,'2021-03-04 18:40:49',7641,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(541,0,NULL,'2021-03-08 23:31:07',NULL,1,'接陪玩','正常',0,'2021-03-08 23:31:07',268490,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(542,0,NULL,'2021-03-11 15:43:22',NULL,1,'分手','正常',0,'2021-03-11 15:43:22',268965,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(543,0,NULL,'2021-03-11 20:15:17',NULL,1,'鞍山','正常',0,'2021-03-11 20:15:17',246511,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(544,0,NULL,'2021-03-18 10:43:43',NULL,1,'工作','正常',0,'2021-03-18 10:43:43',108,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(545,0,NULL,'2021-03-20 19:36:50',NULL,1,'原耽','正常',0,'2021-03-20 19:36:50',271120,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(546,0,NULL,'2021-03-21 20:54:52',NULL,1,'胖妹妹','正常',0,'2021-03-21 20:54:52',270257,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(547,0,NULL,'2021-03-21 20:55:23',NULL,1,'胖妹妹的','正常',0,'2021-03-21 20:55:23',270257,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(548,0,NULL,'2021-03-21 20:56:20',NULL,1,'!!!!','正常',0,'2021-03-21 20:56:20',270257,'胖妹妹的烦恼',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(549,0,NULL,'2021-03-23 23:38:05',NULL,1,'约p','违规',0,'2021-03-23 23:38:05',271882,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(550,0,NULL,'2021-03-27 02:23:50',NULL,1,'哈哈哈嗝','正常',0,'2021-03-27 02:23:50',169327,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(551,0,NULL,'2021-03-27 02:24:32',NULL,1,'哈哈哈','正常',0,'2021-03-27 02:24:32',169327,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(552,0,NULL,'2021-04-05 10:01:40',NULL,1,'李知恩','正常',0,'2021-04-05 10:01:40',264256,'我喜欢啊，我是她粉丝。',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(553,12,NULL,'2021-04-05 10:01:40',NULL,32,'清池','正常',0,'2021-04-05 10:01:40',108,'交友软件',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',1,'\0','all',NULL,0),

(554,0,NULL,'2021-04-05 10:01:40',NULL,32,'集美1','已删除',0,'2021-04-05 10:01:40',108,'女孩闺蜜交友',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(555,3,NULL,'2021-04-05 10:01:40',NULL,33,'女生世界','正常',0,'2021-04-05 10:01:40',108,'女孩',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'\0','girl',NULL,0),

(557,4,NULL,'2021-04-05 10:01:40',NULL,34,'男生世界','正常',0,'2021-04-05 10:01:40',108,'女孩',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'\0','boy',NULL,0),

(558,0,NULL,'2021-04-10 02:02:00',NULL,1,'离谱','正常',0,'2021-04-10 02:02:00',276426,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(559,0,NULL,'2021-04-18 23:32:51',NULL,1,'找女朋友','正常',0,'2021-04-18 23:32:51',278127,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(560,0,NULL,'2021-04-23 00:24:13',NULL,1,'cpdd','正常',0,'2021-04-23 00:24:13',279498,'。',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(561,0,NULL,'2021-04-24 17:56:03',NULL,1,'声音好听','正常',0,'2021-04-24 17:56:03',279059,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(562,0,NULL,'2021-04-30 23:02:03',NULL,1,'耽美','正常',0,'2021-04-30 23:02:03',281552,'男男女女同性的代称',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(563,0,NULL,'2021-05-01 03:10:33',NULL,1,'夜场','正常',0,'2021-05-01 03:10:33',80128,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(564,0,NULL,'2021-05-02 19:19:06',NULL,1,'凹凸世界','正常',0,'2021-05-02 19:19:06',282357,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(565,0,NULL,'2021-05-07 22:56:33',NULL,1,'奥斯卡','正常',0,'2021-05-07 22:56:33',284098,'昆明奥斯卡酒吧，',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(566,0,NULL,'2021-05-15 04:07:31',NULL,1,'狼尾','正常',0,'2021-05-15 04:07:31',286789,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(567,0,NULL,'2021-05-16 09:03:27',NULL,1,'闺女','正常',0,'2021-05-16 09:03:27',230916,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(568,3,NULL,'2021-05-16 21:21:05',NULL,1,'原神','正常',0,'2021-05-16 21:21:05',287485,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(569,0,NULL,'2021-05-23 14:05:23',NULL,1,'三坑少女','正常',0,'2021-05-23 14:05:23',289048,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(570,0,NULL,'2021-05-23 21:49:13',NULL,1,'白河','正常',0,'2021-05-23 21:49:13',248100,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(571,0,NULL,'2021-05-24 20:38:27',NULL,1,'反差','正常',0,'2021-05-24 20:38:27',289585,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,0,'','all',NULL,0),

(572,2,NULL,'2021-05-24 20:38:27',NULL,32,'猫头鹰','正常',0,'2021-05-24 20:38:27',108,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,4,'','all',NULL,0),

(575,0,NULL,'2021-06-08 22:21:45',NULL,1,'扩列好友','正常',0,'2021-06-08 22:21:45',294503,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(576,0,NULL,'2021-06-10 17:53:32',NULL,1,'可打游戏','正常',0,'2021-06-10 17:53:32',294960,'可打游戏',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(577,0,NULL,'2021-06-10 18:04:10',NULL,1,'会打游戏','正常',0,'2021-06-10 18:04:10',294960,'会打游戏',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(578,15,NULL,'2021-06-13 14:05:38',NULL,1,'可w','正常',0,'2021-06-13 14:05:38',276835,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(579,0,NULL,'2021-06-13 14:06:01',NULL,1,'lsp','正常',0,'2021-06-13 14:06:01',276835,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(580,0,NULL,'2021-06-20 10:23:40',NULL,1,'本人直男','正常',0,'2021-06-20 10:23:40',282660,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(581,0,NULL,'2021-06-21 01:38:41',NULL,1,'找队友','正常',0,'2021-06-21 01:38:41',298871,'就找队友能开麦且带耳机的',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(582,0,NULL,'2021-06-21 22:21:18',NULL,1,'知己','正常',0,'2021-06-21 22:21:18',151613,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(583,0,NULL,'2021-06-27 20:43:04',NULL,1,'生活趣事','正常',0,'2021-06-27 20:43:04',300898,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(584,0,NULL,'2021-07-06 17:28:21',NULL,1,'李宏毅','正常',0,'2021-07-06 17:28:21',242505,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(585,0,NULL,'2021-07-13 18:40:26',NULL,1,'找儿子.','正常',0,'2021-07-13 18:40:26',302728,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(586,0,NULL,'2021-07-15 01:54:52',NULL,1,'唔','正常',0,'2021-07-15 01:54:52',224986,'唔',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(587,0,NULL,'2021-07-19 17:18:55',NULL,1,'平安京','正常',0,'2021-07-19 17:18:55',309756,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(588,0,NULL,'2021-07-20 21:16:23',NULL,1,'初三','正常',0,'2021-07-20 21:16:23',49906,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(589,0,NULL,'2021-07-23 13:35:59',NULL,1,'小受受','正常',0,'2021-07-23 13:35:59',236739,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(590,0,NULL,'2021-07-25 14:41:27',NULL,1,'开宝箱','正常',0,'2021-07-25 14:41:27',311760,'钓鱼开宝箱 礼物可提',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(591,0,NULL,'2021-07-27 12:44:31',NULL,1,'学霸','正常',0,'2021-07-27 12:44:31',312487,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(592,0,NULL,'2021-07-29 01:11:28',NULL,1,'06','正常',0,'2021-07-29 01:11:28',31914,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(593,0,NULL,'2021-07-30 01:14:16',NULL,1,'要夜猫子','正常',0,'2021-07-30 01:14:16',206213,'多晚都可以多晚都可以陪聊',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(594,0,NULL,'2021-07-31 14:56:26',NULL,1,'可语音鸭','正常',0,'2021-07-31 14:56:26',313992,'有各种声音都过来吧',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(595,0,NULL,'2021-08-12 10:20:07',NULL,1,'七夕出租','正常',0,'2021-08-12 10:20:07',318116,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(596,0,NULL,'2021-08-22 15:38:52',NULL,1,'第四爱','正常',0,'2021-08-22 15:38:52',309132,'女攻男受',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(597,0,NULL,'2021-09-04 12:17:13',NULL,1,'女同','正常',0,'2021-09-04 12:17:13',233845,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(598,0,NULL,'2021-09-04 12:17:30',NULL,1,'女同小姐','正常',0,'2021-09-04 12:17:30',233845,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(600,0,NULL,'2021-09-05 02:27:41',NULL,1,'处5对象','正常',0,'2021-09-05 02:27:41',236660,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(602,0,NULL,'2021-09-05 17:53:56',NULL,32,'小星星','正常',0,'2021-09-05 17:53:56',108,'开发者对应的话题',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,5,'\0','all',NULL,0),

(603,1,NULL,'2021-09-05 19:59:53',NULL,32,'赤友','正常',0,'2021-09-05 19:59:53',108,'开发者对应的话题',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,7,'\0','all',NULL,0),

(604,4,NULL,'2021-09-05 20:03:41',NULL,32,'蛇交','正常',0,'2021-09-05 20:03:41',108,'开发者对应的话题',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,6,'\0','all',NULL,0),

(608,0,NULL,'2021-09-05 20:03:41',NULL,32,'演示系统','正常',0,'2021-09-05 20:03:41',108,'开发者对应的话题',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,3,'\0','all',NULL,0),

(609,0,NULL,'2021-09-06 01:04:28',NULL,1,'w','正常',0,'2021-09-06 01:04:28',326801,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(610,0,NULL,'2021-09-06 22:15:04',NULL,32,'小星事','正常',0,'2021-09-06 22:15:04',108,'开发者对应的话题',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,8,'\0','all',NULL,0),

(611,0,NULL,'2021-09-12 18:38:18',NULL,1,'混艺术','正常',0,'2021-09-12 18:38:18',327787,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(613,0,NULL,'2021-09-17 10:28:02',NULL,1,'s','正常',0,'2021-09-17 10:28:02',264219,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(614,0,NULL,'2021-09-19 17:58:03',NULL,1,'电音','正常',0,'2021-09-19 17:58:03',161389,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(615,0,NULL,'2021-10-11 13:17:27',NULL,1,'艺术生','正常',0,'2021-10-11 13:17:27',93089,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(616,0,NULL,'2021-10-13 13:48:29',NULL,1,'广东韶关','正常',0,'2021-10-13 13:48:29',332967,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(617,0,NULL,'2021-10-14 21:01:55',NULL,1,'光遇','正常',0,'2021-10-14 21:01:55',333500,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(618,5,NULL,'2021-10-18 22:45:41',NULL,32,'校园墙','正常',0,'2021-10-18 22:45:41',108,'开发者对应的话题',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,13,'\0','all',NULL,0),

(619,0,NULL,'2021-10-20 07:24:14',NULL,32,'迈赞','正常',0,'2021-10-20 07:24:14',108,'开发者对应的话题',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,14,'\0','all',NULL,0),

(620,79,NULL,'2021-10-30 00:54:06',NULL,32,'清风交友','正常',0,'2021-10-30 00:54:06',108,'开发者对应的话题',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,10,'\0','all',NULL,0),

(621,0,NULL,'2021-11-03 10:05:08',NULL,1,'鞠婧祎','正常',0,'2021-11-03 10:05:08',415,'明星',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(622,0,NULL,'2021-11-18 17:06:26',NULL,32,'比晒场','正常',0,'2021-11-18 17:06:26',108,'开发者对应的话题',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,19,'\0','all',NULL,0),

(623,0,NULL,'2021-11-26 20:25:19',NULL,32,'小小留言板','正常',0,'2021-11-26 20:25:19',108,'开发者对应的话题',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,21,'\0','all',NULL,0),

(624,0,NULL,'2021-12-01 11:33:55',NULL,32,'活动大师兄','正常',0,'2021-12-01 11:33:55',108,'开发者对应的话题',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,23,'\0','all',NULL,0),

(625,26,NULL,'2021-12-16 23:09:50',NULL,1,'辣妹','正常',0,'2021-12-16 23:09:50',43783,'',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(626,1,NULL,'2021-12-20 13:55:00',NULL,32,'若依','正常',0,'2021-12-20 13:55:00',108,'开发者对应的话题',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,25,'\0','all',NULL,0),

(627,19,NULL,'2021-12-22 15:54:34',NULL,32,'驭园','正常',0,'2021-12-22 15:54:34',108,'开发者对应的话题',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,24,'\0','all',NULL,0),

(628,1,NULL,'2022-01-30 17:21:26',NULL,32,'Net','正常',0,'2022-01-30 17:21:26',108,'开发者对应的话题',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,30,'\0','all',NULL,0),

(629,1,NULL,'2022-02-16 22:47:05',NULL,1,'体育生','正常',0,'2022-02-16 22:47:05',44113,'体育生',NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar',NULL,NULL,'','all',NULL,0),

(630,0,NULL,'2022-09-15 19:47:31',NULL,36,' 杭州医学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(631,0,NULL,'2022-09-15 19:47:31',NULL,36,'驻马店职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(632,0,NULL,'2022-09-15 19:47:31',NULL,36,'七台河职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(633,0,NULL,'2022-09-15 19:47:31',NULL,36,'三亚中瑞酒店管理职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(634,0,NULL,'2022-09-15 19:47:31',NULL,36,'三亚城市职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(635,0,NULL,'2022-09-15 19:47:31',NULL,36,'三亚学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(636,0,NULL,'2022-09-15 19:47:31',NULL,36,'三亚理工职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(637,0,NULL,'2022-09-15 19:47:31',NULL,36,'三亚航空旅游职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(638,0,NULL,'2022-09-15 19:47:31',NULL,36,'三峡大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(639,0,NULL,'2022-09-15 19:47:31',NULL,36,'三峡大学科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(640,0,NULL,'2022-09-15 19:47:31',NULL,36,'三峡旅游职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(641,0,NULL,'2022-09-15 19:47:31',NULL,36,'三峡电力职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(642,0,NULL,'2022-09-15 19:47:31',NULL,36,'三明医学科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(643,0,NULL,'2022-09-15 19:47:31',NULL,36,'三明学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(644,0,NULL,'2022-09-15 19:47:31',NULL,36,'三江学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(645,0,NULL,'2022-09-15 19:47:31',NULL,36,'三门峡社会管理职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(646,0,NULL,'2022-09-15 19:47:31',NULL,36,'三门峡职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(647,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海东海职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(648,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海中侨职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(649,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海中医药大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(650,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海中华职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(651,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海交通大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(652,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海交通职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(653,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海体育学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(654,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海体育职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(655,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海健康医学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(656,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海公安学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(657,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海兴伟学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(658,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海农林职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(659,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海出版印刷高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(660,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海商学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(661,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海城建职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(662,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海外国语大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(663,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海外国语大学贤达经济人文学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(664,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(665,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海对外经贸大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(666,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海工会管理职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(667,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海工商外国语职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(668,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海工商职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(669,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海工程技术大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(670,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海工艺美术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(671,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(672,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海师范大学天华学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(673,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海应用技术大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(674,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海建桥学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(675,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海思博职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(676,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海戏剧学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(677,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海政法学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(678,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海旅游高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(679,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海杉达学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(680,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海欧华职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(681,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海民航职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(682,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海民远职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(683,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海济光职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(684,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海海事大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(685,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海海事职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(686,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海海关学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(687,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海海洋大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(688,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海理工大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(689,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海电力学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(690,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海电子信息职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(691,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海电影艺术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(692,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海电机学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(693,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海科学技术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(694,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海科技大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(695,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海立信会计金融学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(696,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海立达职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(697,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海第二工业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(698,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海纽约大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(699,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海行健职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(700,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海视觉艺术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(701,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海财经大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(702,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海财经大学浙江学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(703,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海邦德职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(704,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海震旦职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(705,0,NULL,'2022-09-15 19:47:31',NULL,36,'上海音乐学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(706,0,NULL,'2022-09-15 19:47:31',NULL,36,'上饶师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(707,0,NULL,'2022-09-15 19:47:31',NULL,36,'上饶幼儿师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(708,0,NULL,'2022-09-15 19:47:31',NULL,36,'上饶职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(709,0,NULL,'2022-09-15 19:47:31',NULL,36,'东北农业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(710,0,NULL,'2022-09-15 19:47:31',NULL,36,'东北大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(711,0,NULL,'2022-09-15 19:47:31',NULL,36,'东北师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(712,0,NULL,'2022-09-15 19:47:31',NULL,36,'东北师范大学人文学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(713,0,NULL,'2022-09-15 19:47:31',NULL,36,'东北林业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(714,0,NULL,'2022-09-15 19:47:31',NULL,36,'东北电力大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(715,0,NULL,'2022-09-15 19:47:31',NULL,36,'东北石油大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(716,0,NULL,'2022-09-15 19:47:31',NULL,36,'东北财经大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(717,0,NULL,'2022-09-15 19:47:31',NULL,36,'东华大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(718,0,NULL,'2022-09-15 19:47:31',NULL,36,'东华理工大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(719,0,NULL,'2022-09-15 19:47:31',NULL,36,'东华理工大学长江学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(720,0,NULL,'2022-09-15 19:47:31',NULL,36,'东南大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(721,0,NULL,'2022-09-15 19:47:31',NULL,36,'东南大学成贤学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(722,0,NULL,'2022-09-15 19:47:31',NULL,36,'东莞理工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(723,0,NULL,'2022-09-15 19:47:31',NULL,36,'东莞理工学院城市学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(724,0,NULL,'2022-09-15 19:47:31',NULL,36,'东莞职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(725,0,NULL,'2022-09-15 19:47:31',NULL,36,'东营科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(726,0,NULL,'2022-09-15 19:47:31',NULL,36,'东营职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(727,0,NULL,'2022-09-15 19:47:31',NULL,36,'中北大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(728,0,NULL,'2022-09-15 19:47:31',NULL,36,'中北大学信息商务学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(729,0,NULL,'2022-09-15 19:47:31',NULL,36,'中华女子学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(730,0,NULL,'2022-09-15 19:47:31',NULL,36,'中南大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(731,0,NULL,'2022-09-15 19:47:31',NULL,36,'中南林业科技大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(732,0,NULL,'2022-09-15 19:47:31',NULL,36,'中南林业科技大学涉外学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(733,0,NULL,'2022-09-15 19:47:31',NULL,36,'中南民族大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(734,0,NULL,'2022-09-15 19:47:31',NULL,36,'中南财经政法大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(735,0,NULL,'2022-09-15 19:47:31',NULL,36,'中原工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(736,0,NULL,'2022-09-15 19:47:31',NULL,36,'中原工学院信息商务学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(737,0,NULL,'2022-09-15 19:47:31',NULL,36,'中国人民公安大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(738,0,NULL,'2022-09-15 19:47:31',NULL,36,'中国人民大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(739,0,NULL,'2022-09-15 19:47:31',NULL,36,'中国人民武装警察部队学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(740,0,NULL,'2022-09-15 19:47:31',NULL,36,'中国传媒大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(741,0,NULL,'2022-09-15 19:47:31',NULL,36,'中国传媒大学南广学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(742,0,NULL,'2022-09-15 19:47:31',NULL,36,'中国农业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(743,0,NULL,'2022-09-15 19:47:31',NULL,36,'中国刑事警察学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(744,0,NULL,'2022-09-15 19:47:31',NULL,36,'中国劳动关系学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(745,0,NULL,'2022-09-15 19:47:31',NULL,36,'中国医科大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(746,0,NULL,'2022-09-15 19:47:31',NULL,36,'中国医科大学临床医药学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(747,0,NULL,'2022-09-15 19:47:31',NULL,36,'中国地质大学（北京）','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(748,0,NULL,'2022-09-15 19:47:31',NULL,36,'中国地质大学（武汉）','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(749,0,NULL,'2022-09-15 19:47:31',NULL,36,'中国地质大学长城学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(750,0,NULL,'2022-09-15 19:47:31',NULL,36,'中国戏曲学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(751,0,NULL,'2022-09-15 19:47:31',NULL,36,'中国政法大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(752,0,NULL,'2022-09-15 19:47:31',NULL,36,'中国民用航空飞行学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(753,0,NULL,'2022-09-15 19:47:31',NULL,36,'中国民航大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(754,0,NULL,'2022-09-15 19:47:31',NULL,36,'中国海洋大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(755,0,NULL,'2022-09-15 19:47:31',NULL,36,'中国石油大学（北京）','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(756,0,NULL,'2022-09-15 19:47:31',NULL,36,'中国石油大学（华东）','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(757,0,NULL,'2022-09-15 19:47:31',NULL,36,'中国石油大学胜利学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(758,0,NULL,'2022-09-15 19:47:31',NULL,36,'中国矿业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(759,0,NULL,'2022-09-15 19:47:31',NULL,36,'中国矿业大学（北京）','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(760,0,NULL,'2022-09-15 19:47:31',NULL,36,'中国矿业大学徐海学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(761,0,NULL,'2022-09-15 19:47:31',NULL,36,'中国矿业大学银川学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(762,0,NULL,'2022-09-15 19:47:31',NULL,36,'中国社会科学院大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(763,0,NULL,'2022-09-15 19:47:31',NULL,36,'中国科学技术大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(764,0,NULL,'2022-09-15 19:47:31',NULL,36,'中国科学院大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(765,0,NULL,'2022-09-15 19:47:31',NULL,36,'中国美术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(766,0,NULL,'2022-09-15 19:47:31',NULL,36,'中国药科大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(767,0,NULL,'2022-09-15 19:47:31',NULL,36,'中国计量大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(768,0,NULL,'2022-09-15 19:47:31',NULL,36,'中国计量大学现代科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(769,0,NULL,'2022-09-15 19:47:31',NULL,36,'中国青年政治学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(770,0,NULL,'2022-09-15 19:47:31',NULL,36,'中国音乐学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(771,0,NULL,'2022-09-15 19:47:31',NULL,36,'中央司法警官学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(772,0,NULL,'2022-09-15 19:47:31',NULL,36,'中央戏剧学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(773,0,NULL,'2022-09-15 19:47:31',NULL,36,'中央民族大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(774,0,NULL,'2022-09-15 19:47:31',NULL,36,'中央美术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(775,0,NULL,'2022-09-15 19:47:31',NULL,36,'中央财经大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(776,0,NULL,'2022-09-15 19:47:31',NULL,36,'中央音乐学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(777,0,NULL,'2022-09-15 19:47:31',NULL,36,'中山大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(778,0,NULL,'2022-09-15 19:47:31',NULL,36,'中山大学南方学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(779,0,NULL,'2022-09-15 19:47:31',NULL,36,'中山大学新华学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(780,0,NULL,'2022-09-15 19:47:31',NULL,36,'中山火炬职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(781,0,NULL,'2022-09-15 19:47:31',NULL,36,'中山职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(782,0,NULL,'2022-09-15 19:47:31',NULL,36,'临夏现代职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(783,0,NULL,'2022-09-15 19:47:31',NULL,36,'临汾职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(784,0,NULL,'2022-09-15 19:47:31',NULL,36,'临沂大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(785,0,NULL,'2022-09-15 19:47:31',NULL,36,'临沂职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(786,0,NULL,'2022-09-15 19:47:31',NULL,36,'丽水学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(787,0,NULL,'2022-09-15 19:47:31',NULL,36,'丽水职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(788,0,NULL,'2022-09-15 19:47:31',NULL,36,'丽江师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(789,0,NULL,'2022-09-15 19:47:31',NULL,36,'义乌工商职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(790,0,NULL,'2022-09-15 19:47:31',NULL,36,'乌兰察布医学高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(791,0,NULL,'2022-09-15 19:47:31',NULL,36,'乌兰察布职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(792,0,NULL,'2022-09-15 19:47:31',NULL,36,'乌海职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(793,0,NULL,'2022-09-15 19:47:31',NULL,36,'乌鲁木齐职业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(794,0,NULL,'2022-09-15 19:47:31',NULL,36,'乐山师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(795,0,NULL,'2022-09-15 19:47:31',NULL,36,'乐山职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(796,0,NULL,'2022-09-15 19:47:31',NULL,36,'九州职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(797,0,NULL,'2022-09-15 19:47:31',NULL,36,'九江学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(798,0,NULL,'2022-09-15 19:47:31',NULL,36,'九江职业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(799,0,NULL,'2022-09-15 19:47:31',NULL,36,'九江职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(800,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南三鑫职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(801,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南中医学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(802,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南交通职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(803,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南交通运输职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(804,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南体育运动职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(805,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南农业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(806,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南农业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(807,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南司法警官职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(808,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南商务职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(809,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南国土资源职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(810,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南国防工业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(811,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南城市建设职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(812,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南外事外语职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(813,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(814,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南大学旅游文化学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(815,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南大学滇池学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(816,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南工商学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(817,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南工程职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(818,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南工贸职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(819,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(820,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南师范大学商学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(821,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南师范大学文理学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(822,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南文化艺术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(823,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南新兴职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(824,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南旅游职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(825,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南机电职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(826,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南林业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(827,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南民族大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(828,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南水利水电职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(829,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南特殊教育职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(830,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南现代职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(831,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南科技信息职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(832,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南经济管理学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(833,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南经贸外事职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(834,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南能源职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(835,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南艺术 学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(836,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南艺术学院文华学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(837,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南警官学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(838,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南财经大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(839,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南财经职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(840,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南轻纺职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(841,0,NULL,'2022-09-15 19:47:31',NULL,36,'云南锡业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(842,0,NULL,'2022-09-15 19:47:31',NULL,36,'五邑大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(843,0,NULL,'2022-09-15 19:47:31',NULL,36,'井冈山大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(844,0,NULL,'2022-09-15 19:47:31',NULL,36,'亳州学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(845,0,NULL,'2022-09-15 19:47:31',NULL,36,'亳州职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(846,0,NULL,'2022-09-15 19:47:31',NULL,36,'仙桃职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(847,0,NULL,'2022-09-15 19:47:31',NULL,36,'仰恩大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(848,0,NULL,'2022-09-15 19:47:31',NULL,36,'仲恺农业工程学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(849,0,NULL,'2022-09-15 19:47:31',NULL,36,'伊春职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(850,0,NULL,'2022-09-15 19:47:31',NULL,36,'伊犁师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(851,0,NULL,'2022-09-15 19:47:31',NULL,36,'伊犁职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(852,0,NULL,'2022-09-15 19:47:31',NULL,36,'佛山科学技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(853,0,NULL,'2022-09-15 19:47:31',NULL,36,'佛山职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(854,0,NULL,'2022-09-15 19:47:31',NULL,36,'佳木斯大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(855,0,NULL,'2022-09-15 19:47:31',NULL,36,'佳木斯职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(856,0,NULL,'2022-09-15 19:47:31',NULL,36,'保定学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(857,0,NULL,'2022-09-15 19:47:31',NULL,36,'保定幼儿师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(858,0,NULL,'2022-09-15 19:47:31',NULL,36,'保定电力职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(859,0,NULL,'2022-09-15 19:47:31',NULL,36,'保定职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(860,0,NULL,'2022-09-15 19:47:31',NULL,36,'保山中医药高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(861,0,NULL,'2022-09-15 19:47:31',NULL,36,'保山学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(862,0,NULL,'2022-09-15 19:47:31',NULL,36,'保险职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(863,0,NULL,'2022-09-15 19:47:31',NULL,36,'信阳农林学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(864,0,NULL,'2022-09-15 19:47:31',NULL,36,'信阳学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(865,0,NULL,'2022-09-15 19:47:31',NULL,36,'信阳师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(866,0,NULL,'2022-09-15 19:47:31',NULL,36,'信阳涉外职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(867,0,NULL,'2022-09-15 19:47:31',NULL,36,'信阳职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(868,0,NULL,'2022-09-15 19:47:31',NULL,36,'克拉玛依职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(869,0,NULL,'2022-09-15 19:47:31',NULL,36,'公安海警学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(870,0,NULL,'2022-09-15 19:47:31',NULL,36,'公安消防部队高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(871,0,NULL,'2022-09-15 19:47:31',NULL,36,'公安边防部队高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(872,0,NULL,'2022-09-15 19:47:31',NULL,36,'六安职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(873,0,NULL,'2022-09-15 19:47:31',NULL,36,'六盘水师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(874,0,NULL,'2022-09-15 19:47:31',NULL,36,'六盘水职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(875,0,NULL,'2022-09-15 19:47:31',NULL,36,'兰州交通大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(876,0,NULL,'2022-09-15 19:47:31',NULL,36,'兰州交通大学博文学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(877,0,NULL,'2022-09-15 19:47:31',NULL,36,'兰州城市学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(878,0,NULL,'2022-09-15 19:47:31',NULL,36,'兰州外语职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(879,0,NULL,'2022-09-15 19:47:31',NULL,36,'兰州大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(880,0,NULL,'2022-09-15 19:47:31',NULL,36,'兰州工业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(881,0,NULL,'2022-09-15 19:47:31',NULL,36,'兰州文理学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(882,0,NULL,'2022-09-15 19:47:31',NULL,36,'兰州现代职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(883,0,NULL,'2022-09-15 19:47:31',NULL,36,'兰州理工大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(884,0,NULL,'2022-09-15 19:47:31',NULL,36,'兰州理工大学技术工程学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(885,0,NULL,'2022-09-15 19:47:31',NULL,36,'兰州石化职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(886,0,NULL,'2022-09-15 19:47:31',NULL,36,'兰州科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(887,0,NULL,'2022-09-15 19:47:31',NULL,36,'兰州职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(888,0,NULL,'2022-09-15 19:47:31',NULL,36,'兰州财经大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(889,0,NULL,'2022-09-15 19:47:31',NULL,36,'兰州财经大学长青学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(890,0,NULL,'2022-09-15 19:47:31',NULL,36,'兰州财经大学陇桥学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(891,0,NULL,'2022-09-15 19:47:31',NULL,36,'兰州资源环境职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(892,0,NULL,'2022-09-15 19:47:31',NULL,36,'共青科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(893,0,NULL,'2022-09-15 19:47:31',NULL,36,'兴义民族师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(894,0,NULL,'2022-09-15 19:47:31',NULL,36,'兴安职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(895,0,NULL,'2022-09-15 19:47:31',NULL,36,'冀中职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(896,0,NULL,'2022-09-15 19:47:31',NULL,36,'内江师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(897,0,NULL,'2022-09-15 19:47:31',NULL,36,'内江职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(898,0,NULL,'2022-09-15 19:47:31',NULL,36,'内蒙古丰州职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(899,0,NULL,'2022-09-15 19:47:31',NULL,36,'内蒙古交通职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(900,0,NULL,'2022-09-15 19:47:31',NULL,36,'内蒙古体育职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(901,0,NULL,'2022-09-15 19:47:31',NULL,36,'内蒙古农业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(902,0,NULL,'2022-09-15 19:47:31',NULL,36,'内蒙古化工职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(903,0,NULL,'2022-09-15 19:47:31',NULL,36,'内蒙古北方职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(904,0,NULL,'2022-09-15 19:47:31',NULL,36,'内蒙古医科大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(905,0,NULL,'2022-09-15 19:47:31',NULL,36,'内蒙古商贸职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(906,0,NULL,'2022-09-15 19:47:31',NULL,36,'内蒙古大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(907,0,NULL,'2022-09-15 19:47:31',NULL,36,'内蒙古大学创业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(908,0,NULL,'2022-09-15 19:47:31',NULL,36,'内蒙古工业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(909,0,NULL,'2022-09-15 19:47:31',NULL,36,'内蒙古工业职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(910,0,NULL,'2022-09-15 19:47:31',NULL,36,'内蒙古师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(911,0,NULL,'2022-09-15 19:47:31',NULL,36,'内蒙古师范大学鸿德学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(912,0,NULL,'2022-09-15 19:47:31',NULL,36,'内蒙古建筑职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(913,0,NULL,'2022-09-15 19:47:31',NULL,36,'内蒙古机电职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(914,0,NULL,'2022-09-15 19:47:31',NULL,36,'内蒙古民族大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(915,0,NULL,'2022-09-15 19:47:31',NULL,36,'内蒙古民族幼儿师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(916,0,NULL,'2022-09-15 19:47:31',NULL,36,'内蒙古电子信息职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(917,0,NULL,'2022-09-15 19:47:31',NULL,36,'内蒙古科技大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(918,0,NULL,'2022-09-15 19:47:31',NULL,36,'内蒙古科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(919,0,NULL,'2022-09-15 19:47:31',NULL,36,'内蒙古经贸外语职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(920,0,NULL,'2022-09-15 19:47:31',NULL,36,'内蒙古美术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(921,0,NULL,'2022-09-15 19:47:31',NULL,36,'内蒙古能源职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(922,0,NULL,'2022-09-15 19:47:31',NULL,36,'内蒙古艺术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(923,0,NULL,'2022-09-15 19:47:31',NULL,36,'内蒙古警察职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(924,0,NULL,'2022-09-15 19:47:31',NULL,36,'内蒙古财经大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(925,0,NULL,'2022-09-15 19:47:31',NULL,36,'凯里学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(926,0,NULL,'2022-09-15 19:47:31',NULL,36,'包头职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(927,0,NULL,'2022-09-15 19:47:31',NULL,36,'包头轻工职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(928,0,NULL,'2022-09-15 19:47:31',NULL,36,'包头钢铁职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(929,0,NULL,'2022-09-15 19:47:31',NULL,36,'包头铁道职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(930,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京中医药大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(931,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京中医药大学东方学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(932,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京交通大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(933,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京交通大学海滨学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(934,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京交通职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(935,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京交通运输职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(936,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京京北职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(937,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京体育大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(938,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京体育职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(939,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京信息科技大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(940,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京信息职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(941,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京农业职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(942,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京农学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(943,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京劳动保障职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(944,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京化工大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(945,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京北大方正软件职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(946,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京协和医学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(947,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京卫生职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(948,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京印刷学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(949,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京吉利学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(950,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京城市学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(951,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京培黎职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(952,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京外国语大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(953,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(954,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京工业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(955,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京工业大学耿丹学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(956,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京工业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(957,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京工商大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(958,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京工商大学嘉华学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(959,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(960,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京师范大学-香港浸会大学联合国际学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(961,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京师范大学珠海分校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(962,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京建筑大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(963,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京戏曲艺术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(964,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京政法职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(965,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京服装学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(966,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京林业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(967,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京汇佳职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(968,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京物资学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(969,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京理工大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(970,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京理工大学珠海学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(971,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京电子科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(972,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京电子科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(973,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京电影学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(974,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京电影学院现代创意媒体学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(975,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京石油化工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(976,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京社会管理职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(977,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京科技大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(978,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京科技大学天津学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(979,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京科技经营管理学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(980,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(981,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京第二外国语学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(982,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京第二外国语学院中瑞酒店管理学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(983,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京经济技术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(984,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京经济管理职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(985,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京经贸职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(986,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京网络职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(987,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京联合大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(988,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京舞蹈学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(989,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京航空航天大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(990,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京航空航天大学北海学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(991,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京艺术传媒职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(992,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京警察学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(993,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京语言大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(994,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京财贸职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(995,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京邮电大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(996,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京邮电大学世纪学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(997,0,NULL,'2022-09-15 19:47:31',NULL,36,'北京青年政治学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(998,0,NULL,'2022-09-15 19:47:31',NULL,36,'北华大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(999,0,NULL,'2022-09-15 19:47:31',NULL,36,'北华航天工业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1000,0,NULL,'2022-09-15 19:47:31',NULL,36,'北方工业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1001,0,NULL,'2022-09-15 19:47:31',NULL,36,'北方民族大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1002,0,NULL,'2022-09-15 19:47:31',NULL,36,'北海职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1003,0,NULL,'2022-09-15 19:47:31',NULL,36,'北海艺术设计学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1004,0,NULL,'2022-09-15 19:47:31',NULL,36,'华东交通大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1005,0,NULL,'2022-09-15 19:47:31',NULL,36,'华东交通大学理工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1006,0,NULL,'2022-09-15 19:47:31',NULL,36,'华东师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1007,0,NULL,'2022-09-15 19:47:31',NULL,36,'华东政法大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1008,0,NULL,'2022-09-15 19:47:31',NULL,36,'华东理工大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1009,0,NULL,'2022-09-15 19:47:31',NULL,36,'华中农业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1010,0,NULL,'2022-09-15 19:47:31',NULL,36,'华中师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1011,0,NULL,'2022-09-15 19:47:31',NULL,36,'华中科技大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1012,0,NULL,'2022-09-15 19:47:31',NULL,36,'华侨大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1013,0,NULL,'2022-09-15 19:47:31',NULL,36,'华北水利水电大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1014,0,NULL,'2022-09-15 19:47:31',NULL,36,'华北理工大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1015,0,NULL,'2022-09-15 19:47:31',NULL,36,'华北理工大学冀唐学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1016,0,NULL,'2022-09-15 19:47:31',NULL,36,'华北理工大学轻工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1017,0,NULL,'2022-09-15 19:47:31',NULL,36,'华北电力大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1018,0,NULL,'2022-09-15 19:47:31',NULL,36,'华北电力大学科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1019,0,NULL,'2022-09-15 19:47:31',NULL,36,'华北科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1020,0,NULL,'2022-09-15 19:47:31',NULL,36,'华南农业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1021,0,NULL,'2022-09-15 19:47:31',NULL,36,'华南农业大学珠江学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1022,0,NULL,'2022-09-15 19:47:31',NULL,36,'华南师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1023,0,NULL,'2022-09-15 19:47:31',NULL,36,'华南理工大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1024,0,NULL,'2022-09-15 19:47:31',NULL,36,'华南理工大学广州学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1025,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京中医药大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1026,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京中医药大学翰林学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1027,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京交通职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1028,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京体育学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1029,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京信息工程大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1030,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京信息工程大学滨江学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1031,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京信息职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1032,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京农业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1033,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京医科大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1034,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京医科大学康达学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1035,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京城市职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1036,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1037,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京大学金陵学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1038,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京审计大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1039,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京审计大学金审学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1040,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京工业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1041,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京工业大学浦江学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1042,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京工业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1043,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京工程学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1044,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1045,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京师范大学中北学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1046,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京师范大学泰州学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1047,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京旅游职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1048,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京晓庄学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1049,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京机电职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1050,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京林业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1051,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京森林警察学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1052,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京特殊教育师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1053,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京理工大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1054,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京理工大学泰州科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1055,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京理工大学紫金学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1056,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1057,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京航空航天大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1058,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京航空航天大学金城学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1059,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京艺术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1060,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京视觉艺术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1061,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京财经大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1062,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京财经大学红山学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1063,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京邮电大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1064,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京邮电大学通达学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1065,0,NULL,'2022-09-15 19:47:31',NULL,36,'南京铁道职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1066,0,NULL,'2022-09-15 19:47:31',NULL,36,'南充职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1067,0,NULL,'2022-09-15 19:47:31',NULL,36,'南华大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1068,0,NULL,'2022-09-15 19:47:31',NULL,36,'南华大学船山学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1069,0,NULL,'2022-09-15 19:47:31',NULL,36,'南宁学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1070,0,NULL,'2022-09-15 19:47:31',NULL,36,'南宁职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1071,0,NULL,'2022-09-15 19:47:31',NULL,36,'南开大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1072,0,NULL,'2022-09-15 19:47:31',NULL,36,'南开大学滨海学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1073,0,NULL,'2022-09-15 19:47:31',NULL,36,'南方医科大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1074,0,NULL,'2022-09-15 19:47:31',NULL,36,'南方科技大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1075,0,NULL,'2022-09-15 19:47:31',NULL,36,'南昌大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1076,0,NULL,'2022-09-15 19:47:31',NULL,36,'南昌大学共青学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1077,0,NULL,'2022-09-15 19:47:31',NULL,36,'南昌大学科学技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1078,0,NULL,'2022-09-15 19:47:31',NULL,36,'南昌工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1079,0,NULL,'2022-09-15 19:47:31',NULL,36,'南昌工程学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1080,0,NULL,'2022-09-15 19:47:31',NULL,36,'南昌师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1081,0,NULL,'2022-09-15 19:47:31',NULL,36,'南昌影视传播职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1082,0,NULL,'2022-09-15 19:47:31',NULL,36,'南昌理工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1083,0,NULL,'2022-09-15 19:47:31',NULL,36,'南昌职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1084,0,NULL,'2022-09-15 19:47:31',NULL,36,'南昌航空大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1085,0,NULL,'2022-09-15 19:47:31',NULL,36,'南昌航空大学科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1086,0,NULL,'2022-09-15 19:47:31',NULL,36,'南通大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1087,0,NULL,'2022-09-15 19:47:31',NULL,36,'南通大学杏林学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1088,0,NULL,'2022-09-15 19:47:31',NULL,36,'南通师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1089,0,NULL,'2022-09-15 19:47:31',NULL,36,'南通理工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1090,0,NULL,'2022-09-15 19:47:31',NULL,36,'南通科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1091,0,NULL,'2022-09-15 19:47:31',NULL,36,'南通职业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1092,0,NULL,'2022-09-15 19:47:31',NULL,36,'南通航运职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1093,0,NULL,'2022-09-15 19:47:31',NULL,36,'南阳农业职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1094,0,NULL,'2022-09-15 19:47:31',NULL,36,'南阳医学高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1095,0,NULL,'2022-09-15 19:47:31',NULL,36,'南阳师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1096,0,NULL,'2022-09-15 19:47:31',NULL,36,'南阳理工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1097,0,NULL,'2022-09-15 19:47:31',NULL,36,'南阳职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1098,0,NULL,'2022-09-15 19:47:31',NULL,36,'博尔塔拉职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1099,0,NULL,'2022-09-15 19:47:31',NULL,36,'厦门东海职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1100,0,NULL,'2022-09-15 19:47:31',NULL,36,'厦门兴才职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1101,0,NULL,'2022-09-15 19:47:31',NULL,36,'厦门医学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1102,0,NULL,'2022-09-15 19:47:31',NULL,36,'厦门华厦学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1103,0,NULL,'2022-09-15 19:47:31',NULL,36,'厦门华天涉外职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1104,0,NULL,'2022-09-15 19:47:31',NULL,36,'厦门南洋职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1105,0,NULL,'2022-09-15 19:47:31',NULL,36,'厦门城市职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1106,0,NULL,'2022-09-15 19:47:31',NULL,36,'厦门大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1107,0,NULL,'2022-09-15 19:47:31',NULL,36,'厦门大学嘉庚学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1108,0,NULL,'2022-09-15 19:47:31',NULL,36,'厦门安防科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1109,0,NULL,'2022-09-15 19:47:31',NULL,36,'厦门工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1110,0,NULL,'2022-09-15 19:47:31',NULL,36,'厦门海洋职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1111,0,NULL,'2022-09-15 19:47:31',NULL,36,'厦门演艺职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1112,0,NULL,'2022-09-15 19:47:31',NULL,36,'厦门理工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1113,0,NULL,'2022-09-15 19:47:31',NULL,36,'厦门软件职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1114,0,NULL,'2022-09-15 19:47:31',NULL,36,'台州学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1115,0,NULL,'2022-09-15 19:47:31',NULL,36,'台州科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1116,0,NULL,'2022-09-15 19:47:31',NULL,36,'台州职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1117,0,NULL,'2022-09-15 19:47:31',NULL,36,'右江民族医学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1118,0,NULL,'2022-09-15 19:47:31',NULL,36,'合肥信息技术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1119,0,NULL,'2022-09-15 19:47:31',NULL,36,'合肥共达职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1120,0,NULL,'2022-09-15 19:47:31',NULL,36,'合肥学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1121,0,NULL,'2022-09-15 19:47:31',NULL,36,'合肥工业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1122,0,NULL,'2022-09-15 19:47:31',NULL,36,'合肥师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1123,0,NULL,'2022-09-15 19:47:31',NULL,36,'合肥幼儿师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1124,0,NULL,'2022-09-15 19:47:31',NULL,36,'合肥科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1125,0,NULL,'2022-09-15 19:47:31',NULL,36,'合肥职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1126,0,NULL,'2022-09-15 19:47:31',NULL,36,'合肥通用职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1127,0,NULL,'2022-09-15 19:47:31',NULL,36,'吉安职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1128,0,NULL,'2022-09-15 19:47:31',NULL,36,'吉林交通职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1129,0,NULL,'2022-09-15 19:47:31',NULL,36,'吉林体育学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1130,0,NULL,'2022-09-15 19:47:31',NULL,36,'吉林农业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1131,0,NULL,'2022-09-15 19:47:31',NULL,36,'吉林农业科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1132,0,NULL,'2022-09-15 19:47:31',NULL,36,'吉林动画学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1133,0,NULL,'2022-09-15 19:47:31',NULL,36,'吉林化工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1134,0,NULL,'2022-09-15 19:47:31',NULL,36,'吉林医药学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1135,0,NULL,'2022-09-15 19:47:31',NULL,36,'吉林华桥外国语学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1136,0,NULL,'2022-09-15 19:47:31',NULL,36,'吉林司法警官职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1137,0,NULL,'2022-09-15 19:47:31',NULL,36,'吉林城市职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1138,0,NULL,'2022-09-15 19:47:31',NULL,36,'吉林大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1139,0,NULL,'2022-09-15 19:47:31',NULL,36,'吉林大学珠海学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1140,0,NULL,'2022-09-15 19:47:31',NULL,36,'吉林工业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1141,0,NULL,'2022-09-15 19:47:31',NULL,36,'吉林工商学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1142,0,NULL,'2022-09-15 19:47:31',NULL,36,'吉林工程技术师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1143,0,NULL,'2022-09-15 19:47:31',NULL,36,'吉林工程职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1144,0,NULL,'2022-09-15 19:47:31',NULL,36,'吉林师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1145,0,NULL,'2022-09-15 19:47:31',NULL,36,'吉林师范大学博达学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1146,0,NULL,'2022-09-15 19:47:31',NULL,36,'吉林建筑大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1147,0,NULL,'2022-09-15 19:47:31',NULL,36,'吉林建筑大学城建学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1148,0,NULL,'2022-09-15 19:47:31',NULL,36,'吉林水利电力职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1149,0,NULL,'2022-09-15 19:47:31',NULL,36,'吉林电子信息职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1150,0,NULL,'2022-09-15 19:47:31',NULL,36,'吉林科技职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1151,0,NULL,'2022-09-15 19:47:31',NULL,36,'吉林职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1152,0,NULL,'2022-09-15 19:47:31',NULL,36,'吉林艺术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1153,0,NULL,'2022-09-15 19:47:31',NULL,36,'吉林警察学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1154,0,NULL,'2022-09-15 19:47:31',NULL,36,'吉林财经大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1155,0,NULL,'2022-09-15 19:47:31',NULL,36,'吉林铁道职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1156,0,NULL,'2022-09-15 19:47:31',NULL,36,'吉首大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1157,0,NULL,'2022-09-15 19:47:31',NULL,36,'吉首大学张家界学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1158,0,NULL,'2022-09-15 19:47:31',NULL,36,'同济大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1159,0,NULL,'2022-09-15 19:47:31',NULL,36,'同济大学浙江学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1160,0,NULL,'2022-09-15 19:47:31',NULL,36,'吐鲁番职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1161,0,NULL,'2022-09-15 19:47:31',NULL,36,'吕梁学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1162,0,NULL,'2022-09-15 19:47:31',NULL,36,'吕梁职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1163,0,NULL,'2022-09-15 19:47:31',NULL,36,'周口师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1164,0,NULL,'2022-09-15 19:47:31',NULL,36,'周口科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1165,0,NULL,'2022-09-15 19:47:31',NULL,36,'周口职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1166,0,NULL,'2022-09-15 19:47:31',NULL,36,'呼伦贝尔学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1167,0,NULL,'2022-09-15 19:47:31',NULL,36,'呼伦贝尔职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1168,0,NULL,'2022-09-15 19:47:31',NULL,36,'呼和浩特民族学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1169,0,NULL,'2022-09-15 19:47:31',NULL,36,'呼和浩特职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1170,0,NULL,'2022-09-15 19:47:31',NULL,36,'和田师范专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1171,0,NULL,'2022-09-15 19:47:31',NULL,36,'咸宁职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1172,0,NULL,'2022-09-15 19:47:31',NULL,36,'咸阳师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1173,0,NULL,'2022-09-15 19:47:31',NULL,36,'咸阳职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1174,0,NULL,'2022-09-15 19:47:31',NULL,36,'哈密职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1175,0,NULL,'2022-09-15 19:47:31',NULL,36,'哈尔滨传媒职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1176,0,NULL,'2022-09-15 19:47:31',NULL,36,'哈尔滨体育学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1177,0,NULL,'2022-09-15 19:47:31',NULL,36,'哈尔滨信息工程学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1178,0,NULL,'2022-09-15 19:47:31',NULL,36,'哈尔滨剑桥学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1179,0,NULL,'2022-09-15 19:47:31',NULL,36,'哈尔滨医科大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1180,0,NULL,'2022-09-15 19:47:31',NULL,36,'哈尔滨华德学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1181,0,NULL,'2022-09-15 19:47:31',NULL,36,'哈尔滨商业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1182,0,NULL,'2022-09-15 19:47:31',NULL,36,'哈尔滨城市职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1183,0,NULL,'2022-09-15 19:47:31',NULL,36,'哈尔滨学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1184,0,NULL,'2022-09-15 19:47:31',NULL,36,'哈尔滨工业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1185,0,NULL,'2022-09-15 19:47:31',NULL,36,'哈尔滨工程大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1186,0,NULL,'2022-09-15 19:47:31',NULL,36,'哈尔滨师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1187,0,NULL,'2022-09-15 19:47:31',NULL,36,'哈尔滨幼儿师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1188,0,NULL,'2022-09-15 19:47:31',NULL,36,'哈尔滨广厦学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1189,0,NULL,'2022-09-15 19:47:31',NULL,36,'哈尔滨应用职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1190,0,NULL,'2022-09-15 19:47:31',NULL,36,'哈尔滨理工大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1191,0,NULL,'2022-09-15 19:47:31',NULL,36,'哈尔滨电力职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1192,0,NULL,'2022-09-15 19:47:31',NULL,36,'哈尔滨石油学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1193,0,NULL,'2022-09-15 19:47:31',NULL,36,'哈尔滨科学技术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1194,0,NULL,'2022-09-15 19:47:31',NULL,36,'哈尔滨职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1195,0,NULL,'2022-09-15 19:47:31',NULL,36,'哈尔滨远东理工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1196,0,NULL,'2022-09-15 19:47:31',NULL,36,'哈尔滨金融学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1197,0,NULL,'2022-09-15 19:47:31',NULL,36,'哈尔滨铁道职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1198,0,NULL,'2022-09-15 19:47:31',NULL,36,'哈尔滨音乐学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1199,0,NULL,'2022-09-15 19:47:31',NULL,36,'唐山学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1200,0,NULL,'2022-09-15 19:47:31',NULL,36,'唐山工业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1201,0,NULL,'2022-09-15 19:47:31',NULL,36,'唐山师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1202,0,NULL,'2022-09-15 19:47:31',NULL,36,'唐山幼儿师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1203,0,NULL,'2022-09-15 19:47:31',NULL,36,'唐山科技职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1204,0,NULL,'2022-09-15 19:47:31',NULL,36,'唐山职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1205,0,NULL,'2022-09-15 19:47:31',NULL,36,'商丘医学高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1206,0,NULL,'2022-09-15 19:47:31',NULL,36,'商丘学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1207,0,NULL,'2022-09-15 19:47:31',NULL,36,'商丘工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1208,0,NULL,'2022-09-15 19:47:31',NULL,36,'商丘师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1209,0,NULL,'2022-09-15 19:47:31',NULL,36,'商丘职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1210,0,NULL,'2022-09-15 19:47:31',NULL,36,'商洛学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1211,0,NULL,'2022-09-15 19:47:31',NULL,36,'商洛职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1212,0,NULL,'2022-09-15 19:47:31',NULL,36,'喀什大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1213,0,NULL,'2022-09-15 19:47:31',NULL,36,'嘉兴南洋职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1214,0,NULL,'2022-09-15 19:47:31',NULL,36,'嘉兴学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1215,0,NULL,'2022-09-15 19:47:31',NULL,36,'嘉兴学院南湖学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1216,0,NULL,'2022-09-15 19:47:31',NULL,36,'嘉兴职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1217,0,NULL,'2022-09-15 19:47:31',NULL,36,'嘉应学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1218,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川三河职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1219,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川中医药高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1220,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川交通职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1221,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川传媒学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1222,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川信息职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1223,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川农业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1224,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川化工职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1225,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川华新现代职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1226,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川卫生康复职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1227,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川司法警官职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1228,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川商务职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1229,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川国际标榜职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1230,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川城市职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1231,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川外国语大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1232,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川外国语大学成都学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1233,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川外国语大学重庆南方翻译学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1234,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1235,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川大学锦城学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1236,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川大学锦江学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1237,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川工业科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1238,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川工商学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1239,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川工商职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1240,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川工程职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1241,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1242,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川希望汽车职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1243,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川幼儿师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1244,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川应用技术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1245,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川建筑职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1246,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川托普信息技术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1247,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川护理职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1248,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川文化产业职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1249,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川文化传媒职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1250,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川文化艺术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1251,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川文理学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1252,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川文轩职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1253,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川旅游学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1254,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川机电职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1255,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川民族学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1256,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川水利职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1257,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川汽车职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1258,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川现代职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1259,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川理工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1260,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川电力职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1261,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川电子机械职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1262,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川电影电视学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1263,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1264,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川管理职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1265,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川美术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1266,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1267,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川航天职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1268,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川艺术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1269,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川西南航空职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1270,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川警察学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1271,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川财经职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1272,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川邮电职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1273,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川长江职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1274,0,NULL,'2022-09-15 19:47:31',NULL,36,'四川音乐学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1275,0,NULL,'2022-09-15 19:47:31',NULL,36,'四平职业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1276,0,NULL,'2022-09-15 19:47:31',NULL,36,'国际关系学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1277,0,NULL,'2022-09-15 19:47:31',NULL,36,'塔里木大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1278,0,NULL,'2022-09-15 19:47:31',NULL,36,'复旦大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1279,0,NULL,'2022-09-15 19:47:31',NULL,36,'外交学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1280,0,NULL,'2022-09-15 19:47:31',NULL,36,'大兴安岭职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1281,0,NULL,'2022-09-15 19:47:31',NULL,36,'大同煤炭职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1282,0,NULL,'2022-09-15 19:47:31',NULL,36,'大庆医学高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1283,0,NULL,'2022-09-15 19:47:31',NULL,36,'大庆师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1284,0,NULL,'2022-09-15 19:47:31',NULL,36,'大庆职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1285,0,NULL,'2022-09-15 19:47:31',NULL,36,'大理农林职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1286,0,NULL,'2022-09-15 19:47:31',NULL,36,'大理大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1287,0,NULL,'2022-09-15 19:47:31',NULL,36,'大理护理职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1288,0,NULL,'2022-09-15 19:47:31',NULL,36,'大连东软信息学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1289,0,NULL,'2022-09-15 19:47:31',NULL,36,'大连交通大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1290,0,NULL,'2022-09-15 19:47:31',NULL,36,'大连医科大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1291,0,NULL,'2022-09-15 19:47:31',NULL,36,'大连医科大学中山学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1292,0,NULL,'2022-09-15 19:47:31',NULL,36,'大连商务职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1293,0,NULL,'2022-09-15 19:47:31',NULL,36,'大连外国语大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1294,0,NULL,'2022-09-15 19:47:31',NULL,36,'大连大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1295,0,NULL,'2022-09-15 19:47:31',NULL,36,'大连工业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1296,0,NULL,'2022-09-15 19:47:31',NULL,36,'大连工业大学艺术与信息工程学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1297,0,NULL,'2022-09-15 19:47:31',NULL,36,'大连枫叶职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1298,0,NULL,'2022-09-15 19:47:31',NULL,36,'大连民族大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1299,0,NULL,'2022-09-15 19:47:31',NULL,36,'大连汽车职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1300,0,NULL,'2022-09-15 19:47:31',NULL,36,'大连海事大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1301,0,NULL,'2022-09-15 19:47:31',NULL,36,'大连海洋大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1302,0,NULL,'2022-09-15 19:47:31',NULL,36,'大连理工大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1303,0,NULL,'2022-09-15 19:47:31',NULL,36,'大连理工大学城市学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1304,0,NULL,'2022-09-15 19:47:31',NULL,36,'大连科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1305,0,NULL,'2022-09-15 19:47:31',NULL,36,'大连翻译职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1306,0,NULL,'2022-09-15 19:47:31',NULL,36,'大连职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1307,0,NULL,'2022-09-15 19:47:31',NULL,36,'大连航运职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1308,0,NULL,'2022-09-15 19:47:31',NULL,36,'大连艺术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1309,0,NULL,'2022-09-15 19:47:31',NULL,36,'大连装备制造职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1310,0,NULL,'2022-09-15 19:47:31',NULL,36,'大连财经学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1311,0,NULL,'2022-09-15 19:47:31',NULL,36,'大连软件职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1312,0,NULL,'2022-09-15 19:47:31',NULL,36,'天水师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1313,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津中医药大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1314,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津中德应用技术大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1315,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津交通职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1316,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津体育学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1317,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津体育学院运动与文化艺术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1318,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津体育职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1319,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津公安警官职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1320,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津农学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1321,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津冶金职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1322,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津医学高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1323,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津医科大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1324,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津医科大学临床医学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1325,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津商业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1326,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津商业大学宝德学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1327,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津商务职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1328,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津国土资源和房屋职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1329,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津城市建设管理职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1330,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津城市职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1331,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津城建大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1332,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津外国语大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1333,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津外国语大学滨海外事学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1334,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1335,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津大学仁爱学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1336,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津天狮学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1337,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津工业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1338,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津工程职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1339,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津工艺美术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1340,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津市职业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1341,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1342,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津师范大学津沽学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1343,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津广播影视职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1344,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津开发区职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1345,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津机电职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1346,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津海运职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1347,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津渤海职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1348,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津滨海汽车工程职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1349,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津滨海职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1350,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津现代职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1351,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津理工大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1352,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津理工大学中环信息学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1353,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津生物工程职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1354,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津电子信息职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1355,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津石油职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1356,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津科技大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1357,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津美术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1358,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津职业技术师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1359,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津艺术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1360,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津财经大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1361,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津财经大学珠江学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1362,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津轻工职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1363,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津铁道职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1364,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津青年职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1365,0,NULL,'2022-09-15 19:47:31',NULL,36,'天津音乐学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1366,0,NULL,'2022-09-15 19:47:31',NULL,36,'天门职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1367,0,NULL,'2022-09-15 19:47:31',NULL,36,'太原城市职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1368,0,NULL,'2022-09-15 19:47:31',NULL,36,'太原学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1369,0,NULL,'2022-09-15 19:47:31',NULL,36,'太原工业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1370,0,NULL,'2022-09-15 19:47:31',NULL,36,'太原师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1371,0,NULL,'2022-09-15 19:47:31',NULL,36,'太原旅游职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1372,0,NULL,'2022-09-15 19:47:31',NULL,36,'太原理工大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1373,0,NULL,'2022-09-15 19:47:31',NULL,36,'太原理工大学现代科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1374,0,NULL,'2022-09-15 19:47:31',NULL,36,'太原科技大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1375,0,NULL,'2022-09-15 19:47:31',NULL,36,'太原科技大学华科学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1376,0,NULL,'2022-09-15 19:47:31',NULL,36,'太湖创意职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1377,0,NULL,'2022-09-15 19:47:31',NULL,36,'威海海洋职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1378,0,NULL,'2022-09-15 19:47:31',NULL,36,'威海职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1379,0,NULL,'2022-09-15 19:47:31',NULL,36,'娄底职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1380,0,NULL,'2022-09-15 19:47:31',NULL,36,'宁夏体育职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1381,0,NULL,'2022-09-15 19:47:31',NULL,36,'宁夏医科大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1382,0,NULL,'2022-09-15 19:47:31',NULL,36,'宁夏大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1383,0,NULL,'2022-09-15 19:47:31',NULL,36,'宁夏大学新华学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1384,0,NULL,'2022-09-15 19:47:31',NULL,36,'宁夏工业职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1385,0,NULL,'2022-09-15 19:47:31',NULL,36,'宁夏工商职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1386,0,NULL,'2022-09-15 19:47:31',NULL,36,'宁夏师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1387,0,NULL,'2022-09-15 19:47:31',NULL,36,'宁夏幼儿师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1388,0,NULL,'2022-09-15 19:47:31',NULL,36,'宁夏建设职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1389,0,NULL,'2022-09-15 19:47:31',NULL,36,'宁夏民族职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1390,0,NULL,'2022-09-15 19:47:31',NULL,36,'宁夏理工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1391,0,NULL,'2022-09-15 19:47:31',NULL,36,'宁夏职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1392,0,NULL,'2022-09-15 19:47:31',NULL,36,'宁夏艺术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1393,0,NULL,'2022-09-15 19:47:31',NULL,36,'宁夏葡萄酒与防沙治沙职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1394,0,NULL,'2022-09-15 19:47:31',NULL,36,'宁夏警官职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1395,0,NULL,'2022-09-15 19:47:31',NULL,36,'宁夏财经职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1396,0,NULL,'2022-09-15 19:47:31',NULL,36,'宁德师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1397,0,NULL,'2022-09-15 19:47:31',NULL,36,'宁德职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1398,0,NULL,'2022-09-15 19:47:31',NULL,36,'宁波卫生职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1399,0,NULL,'2022-09-15 19:47:31',NULL,36,'宁波城市职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1400,0,NULL,'2022-09-15 19:47:31',NULL,36,'宁波大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1401,0,NULL,'2022-09-15 19:47:31',NULL,36,'宁波大学科学技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1402,0,NULL,'2022-09-15 19:47:31',NULL,36,'宁波大红鹰学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1403,0,NULL,'2022-09-15 19:47:31',NULL,36,'宁波工程学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1404,0,NULL,'2022-09-15 19:47:31',NULL,36,'宁波职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1405,0,NULL,'2022-09-15 19:47:31',NULL,36,'宁波诺丁汉大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1406,0,NULL,'2022-09-15 19:47:31',NULL,36,'安庆医药高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1407,0,NULL,'2022-09-15 19:47:31',NULL,36,'安庆师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1408,0,NULL,'2022-09-15 19:47:31',NULL,36,'安庆职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1409,0,NULL,'2022-09-15 19:47:31',NULL,36,'安康学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1410,0,NULL,'2022-09-15 19:47:31',NULL,36,'安康职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1411,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽三联学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1412,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽中医药大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1413,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽中医药高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1414,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽中澳科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1415,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽交通职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1416,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽体育运动职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1417,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽信息工程学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1418,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽公安职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1419,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽农业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1420,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽农业大学经济技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1421,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽冶金科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1422,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽医学高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1423,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽医科大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1424,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽医科大学临床医学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1425,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽卫生健康职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1426,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽商贸职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1427,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽国防科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1428,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽国际商务职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1429,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽城市管理职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1430,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽外国语学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1431,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1432,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽大学江淮学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1433,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽审计职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1434,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽工业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1435,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽工业大学工商学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1436,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽工业经济职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1437,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽工业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1438,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽工商职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1439,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽工程大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1440,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽工贸职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1441,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1442,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽师范大学皖江学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1443,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽广播影视职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1444,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽建筑大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1445,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽建筑大学城市建设学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1446,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽扬子职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1447,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽文达信息工程学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1448,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽新华学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1449,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽新闻出版职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1450,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽机电职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1451,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽林业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1452,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽水利水电职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1453,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽汽车职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1454,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽涉外经济职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1455,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽现代信息工程职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1456,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽理工大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1457,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽电子信息职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1458,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽电气工程职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1459,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽矿业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1460,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1461,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽粮食工程职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1462,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽绿海商务职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1463,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1464,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽艺术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1465,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽警官职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1466,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽财经大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1467,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽财经大学商学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1468,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽财贸职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1469,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽邮电职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1470,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽长江职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1471,0,NULL,'2022-09-15 19:47:31',NULL,36,'安徽黄梅戏艺术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1472,0,NULL,'2022-09-15 19:47:31',NULL,36,'安阳学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1473,0,NULL,'2022-09-15 19:47:31',NULL,36,'安阳工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1474,0,NULL,'2022-09-15 19:47:31',NULL,36,'安阳师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1475,0,NULL,'2022-09-15 19:47:31',NULL,36,'安阳幼儿师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1476,0,NULL,'2022-09-15 19:47:31',NULL,36,'安阳职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1477,0,NULL,'2022-09-15 19:47:31',NULL,36,'安顺学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1478,0,NULL,'2022-09-15 19:47:31',NULL,36,'安顺职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1479,0,NULL,'2022-09-15 19:47:31',NULL,36,'定西师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1480,0,NULL,'2022-09-15 19:47:31',NULL,36,'宜宾学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1481,0,NULL,'2022-09-15 19:47:31',NULL,36,'宜宾职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1482,0,NULL,'2022-09-15 19:47:31',NULL,36,'宜春学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1483,0,NULL,'2022-09-15 19:47:31',NULL,36,'宜春幼儿师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1484,0,NULL,'2022-09-15 19:47:31',NULL,36,'宜春职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1485,0,NULL,'2022-09-15 19:47:31',NULL,36,'宝鸡文理学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1486,0,NULL,'2022-09-15 19:47:31',NULL,36,'宝鸡职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1487,0,NULL,'2022-09-15 19:47:31',NULL,36,'宣化科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1488,0,NULL,'2022-09-15 19:47:31',NULL,36,'宣城职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1489,0,NULL,'2022-09-15 19:47:31',NULL,36,'宿州学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1490,0,NULL,'2022-09-15 19:47:31',NULL,36,'宿州职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1491,0,NULL,'2022-09-15 19:47:31',NULL,36,'宿迁学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1492,0,NULL,'2022-09-15 19:47:31',NULL,36,'宿迁泽达职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1493,0,NULL,'2022-09-15 19:47:31',NULL,36,'宿迁职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1494,0,NULL,'2022-09-15 19:47:31',NULL,36,'对外经济贸易大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1495,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东中医药大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1496,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东中医药高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1497,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东交通学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1498,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东交通职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1499,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东传媒职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1500,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东体育学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1501,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东信息职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1502,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东农业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1503,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东农业工程学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1504,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东凯文科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1505,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东力明科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1506,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东劳动职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1507,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东化工职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1508,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东医学高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1509,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东华宇工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1510,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东协和学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1511,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东司法警官职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1512,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东商业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1513,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东商务职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1514,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东圣翰财贸职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1515,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东城市建设职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1516,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东外事翻译职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1517,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东外国语职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1518,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东外贸职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1519,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1520,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东女子学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1521,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东工业职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1522,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东工商学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1523,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东工艺美术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1524,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1525,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东师范大学历山学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1526,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东建筑大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1527,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东政法学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1528,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东文化产业职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1529,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东旅游职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1530,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东服装职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1531,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东杏林科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1532,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东水利职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1533,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东海事职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1534,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东特殊教育职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1535,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东现代学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1536,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东理工大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1537,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东理工职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1538,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东电力高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1539,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东电子职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1540,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东畜牧兽医职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1541,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东科技大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1542,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东科技大学泰山科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1543,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1544,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东管理学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1545,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东经贸职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1546,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1547,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东胜利职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1548,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东艺术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1549,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东艺术设计职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1550,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东英才学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1551,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东药品食品职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1552,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东警察学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1553,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东财经大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1554,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东财经大学东方学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1555,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东财经大学燕山学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1556,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东轻工职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1557,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东铝业职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1558,0,NULL,'2022-09-15 19:47:31',NULL,36,'山东青年政治学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1559,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西中医药大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1560,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西交通职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1561,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西传媒学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1562,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西体育职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1563,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西信息职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1564,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西农业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1565,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西农业大学信息学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1566,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西医科大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1567,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西医科大学晋祠学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1568,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西华澳商贸职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1569,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西同文职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1570,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西国际商务职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1571,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西大同大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1572,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1573,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西大学商务学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1574,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西工商学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1575,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西工程技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1576,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西工程职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1577,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1578,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西师范大学现代文理学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1579,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西应用科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1580,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西建筑职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1581,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西戏剧职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1582,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西旅游职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1583,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西机电职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1584,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西林业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1585,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西水利职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1586,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西煤炭职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1587,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西电力职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1588,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西省财政税务专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1589,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西管理职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1590,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西经贸职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1591,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西老区职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1592,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1593,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西能源学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1594,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西艺术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1595,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西药科职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1596,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西警官职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1597,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西警察学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1598,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西财经大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1599,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西财经大学华商学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1600,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西财贸职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1601,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西轻工职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1602,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西运城农业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1603,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西金融职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1604,0,NULL,'2022-09-15 19:47:31',NULL,36,'山西青年职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1605,0,NULL,'2022-09-15 19:47:31',NULL,36,'岭南师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1606,0,NULL,'2022-09-15 19:47:31',NULL,36,'岳阳职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1607,0,NULL,'2022-09-15 19:47:31',NULL,36,'嵩山少林武术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1608,0,NULL,'2022-09-15 19:47:31',NULL,36,'川北医学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1609,0,NULL,'2022-09-15 19:47:31',NULL,36,'川北幼儿师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1610,0,NULL,'2022-09-15 19:47:31',NULL,36,'川南幼儿师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1611,0,NULL,'2022-09-15 19:47:31',NULL,36,'巢湖学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1612,0,NULL,'2022-09-15 19:47:31',NULL,36,'巴中职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1613,0,NULL,'2022-09-15 19:47:31',NULL,36,'巴音郭楞职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1614,0,NULL,'2022-09-15 19:47:31',NULL,36,'常州信息职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1615,0,NULL,'2022-09-15 19:47:31',NULL,36,'常州大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1616,0,NULL,'2022-09-15 19:47:31',NULL,36,'常州大学怀德学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1617,0,NULL,'2022-09-15 19:47:31',NULL,36,'常州工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1618,0,NULL,'2022-09-15 19:47:31',NULL,36,'常州工程职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1619,0,NULL,'2022-09-15 19:47:31',NULL,36,'常州机电职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1620,0,NULL,'2022-09-15 19:47:31',NULL,36,'常州纺织服装职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1621,0,NULL,'2022-09-15 19:47:31',NULL,36,'常州轻工职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1622,0,NULL,'2022-09-15 19:47:31',NULL,36,'常德职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1623,0,NULL,'2022-09-15 19:47:31',NULL,36,'常熟理工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1624,0,NULL,'2022-09-15 19:47:31',NULL,36,'平凉职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1625,0,NULL,'2022-09-15 19:47:31',NULL,36,'平顶山学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1626,0,NULL,'2022-09-15 19:47:31',NULL,36,'平顶山工业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1627,0,NULL,'2022-09-15 19:47:31',NULL,36,'平顶山文化艺术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1628,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东东软学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1629,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东亚视演艺职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1630,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东交通职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1631,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东以色列理工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1632,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东体育职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1633,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东信息工程职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1634,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东农工商职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1635,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东创新科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1636,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东医科大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1637,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东南华工商职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1638,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东南方职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1639,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东司法警官职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1640,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东培正学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1641,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东外语外贸大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1642,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东外语外贸大学南国商学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1643,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东女子职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1644,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东岭南职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1645,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东工业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1646,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东工业大学华立学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1647,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东工商职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1648,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东工程职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1649,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东工贸职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1650,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东建设职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1651,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东技术师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1652,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东技术师范学院天河学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1653,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东文理职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1654,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东文艺职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1655,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东新安职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1656,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东机电职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1657,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东松山职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1658,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东水利电力职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1659,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东江门中医药职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1660,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东海洋大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1661,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东海洋大学寸金学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1662,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东环境保护工程职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1663,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东理工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1664,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东理工职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1665,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东生态工程职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1666,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东白云学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1667,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东省外语艺术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1668,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东石油化工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1669,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东碧桂园职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1670,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东科学技术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1671,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1672,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东科贸职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1673,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东第二师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1674,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1675,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东舞蹈戏剧职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1676,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东茂名健康职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1677,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东茂名幼儿师范专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1678,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东药科大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1679,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东行政职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1680,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东警官学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1681,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东财经大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1682,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东财经大学华商学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1683,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东轻工职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1684,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东邮电职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1685,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东酒店管理职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1686,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东金融学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1687,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东青年职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1688,0,NULL,'2022-09-15 19:47:31',NULL,36,'广东食品药品职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1689,0,NULL,'2022-09-15 19:47:31',NULL,36,'广安职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1690,0,NULL,'2022-09-15 19:47:31',NULL,36,'广州东华职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1691,0,NULL,'2022-09-15 19:47:31',NULL,36,'广州中医药大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1692,0,NULL,'2022-09-15 19:47:31',NULL,36,'广州体育学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1693,0,NULL,'2022-09-15 19:47:31',NULL,36,'广州体育职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1694,0,NULL,'2022-09-15 19:47:31',NULL,36,'广州医科大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1695,0,NULL,'2022-09-15 19:47:31',NULL,36,'广州华南商贸职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1696,0,NULL,'2022-09-15 19:47:31',NULL,36,'广州华商职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1697,0,NULL,'2022-09-15 19:47:31',NULL,36,'广州华夏职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1698,0,NULL,'2022-09-15 19:47:31',NULL,36,'广州华立科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1699,0,NULL,'2022-09-15 19:47:31',NULL,36,'广州南洋理工职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1700,0,NULL,'2022-09-15 19:47:31',NULL,36,'广州卫生职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1701,0,NULL,'2022-09-15 19:47:31',NULL,36,'广州商学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1702,0,NULL,'2022-09-15 19:47:31',NULL,36,'广州城市职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1703,0,NULL,'2022-09-15 19:47:31',NULL,36,'广州城建职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1704,0,NULL,'2022-09-15 19:47:31',NULL,36,'广州大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1705,0,NULL,'2022-09-15 19:47:31',NULL,36,'广州大学华软软件学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1706,0,NULL,'2022-09-15 19:47:31',NULL,36,'广州大学松田学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1707,0,NULL,'2022-09-15 19:47:31',NULL,36,'广州工商学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1708,0,NULL,'2022-09-15 19:47:31',NULL,36,'广州工程技术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1709,0,NULL,'2022-09-15 19:47:31',NULL,36,'广州康大职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1710,0,NULL,'2022-09-15 19:47:31',NULL,36,'广州松田职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1711,0,NULL,'2022-09-15 19:47:31',NULL,36,'广州民航职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1712,0,NULL,'2022-09-15 19:47:31',NULL,36,'广州涉外经济职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1713,0,NULL,'2022-09-15 19:47:31',NULL,36,'广州现代信息工程职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1714,0,NULL,'2022-09-15 19:47:31',NULL,36,'广州珠江职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1715,0,NULL,'2022-09-15 19:47:31',NULL,36,'广州番禺职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1716,0,NULL,'2022-09-15 19:47:31',NULL,36,'广州科技职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1717,0,NULL,'2022-09-15 19:47:31',NULL,36,'广州科技贸易职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1718,0,NULL,'2022-09-15 19:47:31',NULL,36,'广州美术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1719,0,NULL,'2022-09-15 19:47:31',NULL,36,'广州航海学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1720,0,NULL,'2022-09-15 19:47:31',NULL,36,'广州铁路职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1721,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西中医药大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1722,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西中医药大学赛恩斯新医药学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1723,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西中远职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1724,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西交通职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1725,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西体育高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1726,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西农业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1727,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西医科大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1728,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西卫生职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1729,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西国际商务职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1730,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西城市职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1731,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西培贤国际职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1732,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西外国语学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1733,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1734,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西大学行健文理学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1735,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西安全工程职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1736,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西工业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1737,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西工商职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1738,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西工程职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1739,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1740,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西师范大学漓江学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1741,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1742,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西师范学院师园学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1743,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西幼儿师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1744,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西建设职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1745,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西机电职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1746,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西民族大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1747,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西民族大学相思湖学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1748,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西民族师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1749,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西水利电力职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1750,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西演艺职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1751,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西现代职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1752,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西理工职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1753,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西生态工程职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1754,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西电力职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1755,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西科技大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1756,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西科技大学鹿山学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1757,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西科技师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1758,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1759,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西经济职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1760,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西经贸职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1761,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1762,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西艺术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1763,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西英华国际职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1764,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西蓝天航空职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1765,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西警察学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1766,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西财经学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1767,0,NULL,'2022-09-15 19:47:31',NULL,36,'广西金融职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1768,0,NULL,'2022-09-15 19:47:31',NULL,36,'庆阳职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1769,0,NULL,'2022-09-15 19:47:31',NULL,36,'应天职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1770,0,NULL,'2022-09-15 19:47:31',NULL,36,'廊坊卫生职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1771,0,NULL,'2022-09-15 19:47:31',NULL,36,'廊坊师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1772,0,NULL,'2022-09-15 19:47:31',NULL,36,'廊坊燕京职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1773,0,NULL,'2022-09-15 19:47:31',NULL,36,'廊坊职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1774,0,NULL,'2022-09-15 19:47:31',NULL,36,'延安大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1775,0,NULL,'2022-09-15 19:47:31',NULL,36,'延安大学西安创新学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1776,0,NULL,'2022-09-15 19:47:31',NULL,36,'延安职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1777,0,NULL,'2022-09-15 19:47:31',NULL,36,'延边大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1778,0,NULL,'2022-09-15 19:47:31',NULL,36,'延边职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1779,0,NULL,'2022-09-15 19:47:31',NULL,36,'建东职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1780,0,NULL,'2022-09-15 19:47:31',NULL,36,'开封大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1781,0,NULL,'2022-09-15 19:47:31',NULL,36,'开封文化艺术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1782,0,NULL,'2022-09-15 19:47:31',NULL,36,'张家口学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1783,0,NULL,'2022-09-15 19:47:31',NULL,36,'张家口职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1784,0,NULL,'2022-09-15 19:47:31',NULL,36,'张家界航空工业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1785,0,NULL,'2022-09-15 19:47:31',NULL,36,'徐州医科大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1786,0,NULL,'2022-09-15 19:47:31',NULL,36,'徐州工业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1787,0,NULL,'2022-09-15 19:47:31',NULL,36,'徐州工程学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1788,0,NULL,'2022-09-15 19:47:31',NULL,36,'徐州幼儿师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1789,0,NULL,'2022-09-15 19:47:31',NULL,36,'徐州生物工程职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1790,0,NULL,'2022-09-15 19:47:31',NULL,36,'德宏师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1791,0,NULL,'2022-09-15 19:47:31',NULL,36,'德宏职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1792,0,NULL,'2022-09-15 19:47:31',NULL,36,'德州学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1793,0,NULL,'2022-09-15 19:47:31',NULL,36,'德州科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1794,0,NULL,'2022-09-15 19:47:31',NULL,36,'德州职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1795,0,NULL,'2022-09-15 19:47:31',NULL,36,'徽商职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1796,0,NULL,'2022-09-15 19:47:31',NULL,36,'忻州师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1797,0,NULL,'2022-09-15 19:47:31',NULL,36,'忻州职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1798,0,NULL,'2022-09-15 19:47:31',NULL,36,'怀化学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1799,0,NULL,'2022-09-15 19:47:31',NULL,36,'怀化职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1800,0,NULL,'2022-09-15 19:47:31',NULL,36,'恩施职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1801,0,NULL,'2022-09-15 19:47:31',NULL,36,'惠州卫生职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1802,0,NULL,'2022-09-15 19:47:31',NULL,36,'惠州城市职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1803,0,NULL,'2022-09-15 19:47:31',NULL,36,'惠州学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1804,0,NULL,'2022-09-15 19:47:31',NULL,36,'惠州工程职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1805,0,NULL,'2022-09-15 19:47:31',NULL,36,'惠州经济职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1806,0,NULL,'2022-09-15 19:47:31',NULL,36,'成都东软学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1807,0,NULL,'2022-09-15 19:47:31',NULL,36,'成都中医药大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1808,0,NULL,'2022-09-15 19:47:31',NULL,36,'成都体育学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1809,0,NULL,'2022-09-15 19:47:31',NULL,36,'成都信息工程大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1810,0,NULL,'2022-09-15 19:47:31',NULL,36,'成都信息工程大学银杏酒店管理学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1811,0,NULL,'2022-09-15 19:47:31',NULL,36,'成都农业科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1812,0,NULL,'2022-09-15 19:47:31',NULL,36,'成都医学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1813,0,NULL,'2022-09-15 19:47:31',NULL,36,'成都学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1814,0,NULL,'2022-09-15 19:47:31',NULL,36,'成都工业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1815,0,NULL,'2022-09-15 19:47:31',NULL,36,'成都工业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1816,0,NULL,'2022-09-15 19:47:31',NULL,36,'成都工贸职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1817,0,NULL,'2022-09-15 19:47:31',NULL,36,'成都师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1818,0,NULL,'2022-09-15 19:47:31',NULL,36,'成都文理学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1819,0,NULL,'2022-09-15 19:47:31',NULL,36,'成都理工大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1820,0,NULL,'2022-09-15 19:47:31',NULL,36,'成都理工大学工程技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1821,0,NULL,'2022-09-15 19:47:31',NULL,36,'成都纺织高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1822,0,NULL,'2022-09-15 19:47:31',NULL,36,'成都职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1823,0,NULL,'2022-09-15 19:47:31',NULL,36,'成都航空职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1824,0,NULL,'2022-09-15 19:47:31',NULL,36,'成都艺术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1825,0,NULL,'2022-09-15 19:47:31',NULL,36,'扎兰屯职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1826,0,NULL,'2022-09-15 19:47:31',NULL,36,'扬州中瑞酒店职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1827,0,NULL,'2022-09-15 19:47:31',NULL,36,'扬州大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1828,0,NULL,'2022-09-15 19:47:31',NULL,36,'扬州大学广陵学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1829,0,NULL,'2022-09-15 19:47:31',NULL,36,'扬州工业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1830,0,NULL,'2022-09-15 19:47:31',NULL,36,'扬州市职业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1831,0,NULL,'2022-09-15 19:47:31',NULL,36,'扬州环境资源职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1832,0,NULL,'2022-09-15 19:47:31',NULL,36,'承德医学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1833,0,NULL,'2022-09-15 19:47:31',NULL,36,'承德护理职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1834,0,NULL,'2022-09-15 19:47:31',NULL,36,'承德石油高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1835,0,NULL,'2022-09-15 19:47:31',NULL,36,'抚州幼儿师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1836,0,NULL,'2022-09-15 19:47:31',NULL,36,'抚州职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1837,0,NULL,'2022-09-15 19:47:31',NULL,36,'抚顺师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1838,0,NULL,'2022-09-15 19:47:31',NULL,36,'抚顺职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1839,0,NULL,'2022-09-15 19:47:31',NULL,36,'拉萨师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1840,0,NULL,'2022-09-15 19:47:31',NULL,36,'揭阳职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1841,0,NULL,'2022-09-15 19:47:31',NULL,36,'攀枝花学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1842,0,NULL,'2022-09-15 19:47:31',NULL,36,'文华学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1843,0,NULL,'2022-09-15 19:47:31',NULL,36,'文山学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1844,0,NULL,'2022-09-15 19:47:31',NULL,36,'新乡医学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1845,0,NULL,'2022-09-15 19:47:31',NULL,36,'新乡医学院三全学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1846,0,NULL,'2022-09-15 19:47:31',NULL,36,'新乡学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1847,0,NULL,'2022-09-15 19:47:31',NULL,36,'新乡职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1848,0,NULL,'2022-09-15 19:47:31',NULL,36,'新余学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1849,0,NULL,'2022-09-15 19:47:31',NULL,36,'新疆交通职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1850,0,NULL,'2022-09-15 19:47:31',NULL,36,'新疆体育职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1851,0,NULL,'2022-09-15 19:47:31',NULL,36,'新疆兵团警官高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1852,0,NULL,'2022-09-15 19:47:31',NULL,36,'新疆农业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1853,0,NULL,'2022-09-15 19:47:31',NULL,36,'新疆农业大学科学技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1854,0,NULL,'2022-09-15 19:47:31',NULL,36,'新疆农业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1855,0,NULL,'2022-09-15 19:47:31',NULL,36,'新疆医科大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1856,0,NULL,'2022-09-15 19:47:31',NULL,36,'新疆医科大学厚博学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1857,0,NULL,'2022-09-15 19:47:31',NULL,36,'新疆大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1858,0,NULL,'2022-09-15 19:47:31',NULL,36,'新疆大学科学技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1859,0,NULL,'2022-09-15 19:47:31',NULL,36,'新疆天山职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1860,0,NULL,'2022-09-15 19:47:31',NULL,36,'新疆工业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1861,0,NULL,'2022-09-15 19:47:31',NULL,36,'新疆工程学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1862,0,NULL,'2022-09-15 19:47:31',NULL,36,'新疆师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1863,0,NULL,'2022-09-15 19:47:31',NULL,36,'新疆师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1864,0,NULL,'2022-09-15 19:47:31',NULL,36,'新疆应用职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1865,0,NULL,'2022-09-15 19:47:31',NULL,36,'新疆建设职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1866,0,NULL,'2022-09-15 19:47:31',NULL,36,'新疆机电职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1867,0,NULL,'2022-09-15 19:47:31',NULL,36,'新疆现代职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1868,0,NULL,'2022-09-15 19:47:31',NULL,36,'新疆生产建设兵团兴新职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1869,0,NULL,'2022-09-15 19:47:31',NULL,36,'新疆石河子职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1870,0,NULL,'2022-09-15 19:47:31',NULL,36,'新疆科技职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1871,0,NULL,'2022-09-15 19:47:31',NULL,36,'新疆维吾尔医学专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1872,0,NULL,'2022-09-15 19:47:31',NULL,36,'新疆职业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1873,0,NULL,'2022-09-15 19:47:31',NULL,36,'新疆能源职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1874,0,NULL,'2022-09-15 19:47:31',NULL,36,'新疆艺术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1875,0,NULL,'2022-09-15 19:47:31',NULL,36,'新疆警察学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1876,0,NULL,'2022-09-15 19:47:31',NULL,36,'新疆财经大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1877,0,NULL,'2022-09-15 19:47:31',NULL,36,'新疆财经大学商务学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1878,0,NULL,'2022-09-15 19:47:31',NULL,36,'新疆轻工职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1879,0,NULL,'2022-09-15 19:47:31',NULL,36,'新疆铁道职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1880,0,NULL,'2022-09-15 19:47:31',NULL,36,'无锡南洋职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1881,0,NULL,'2022-09-15 19:47:31',NULL,36,'无锡商业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1882,0,NULL,'2022-09-15 19:47:31',NULL,36,'无锡城市职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1883,0,NULL,'2022-09-15 19:47:31',NULL,36,'无锡太湖学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1884,0,NULL,'2022-09-15 19:47:31',NULL,36,'无锡工艺职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1885,0,NULL,'2022-09-15 19:47:31',NULL,36,'无锡科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1886,0,NULL,'2022-09-15 19:47:31',NULL,36,'无锡职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1887,0,NULL,'2022-09-15 19:47:31',NULL,36,'日照职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1888,0,NULL,'2022-09-15 19:47:31',NULL,36,'日照航海工程职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1889,0,NULL,'2022-09-15 19:47:31',NULL,36,'昆山杜克大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1890,0,NULL,'2022-09-15 19:47:31',NULL,36,'昆山登云科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1891,0,NULL,'2022-09-15 19:47:31',NULL,36,'昆明冶金高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1892,0,NULL,'2022-09-15 19:47:31',NULL,36,'昆明医科大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1893,0,NULL,'2022-09-15 19:47:31',NULL,36,'昆明医科大学海源学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1894,0,NULL,'2022-09-15 19:47:31',NULL,36,'昆明卫生职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1895,0,NULL,'2022-09-15 19:47:31',NULL,36,'昆明学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1896,0,NULL,'2022-09-15 19:47:31',NULL,36,'昆明工业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1897,0,NULL,'2022-09-15 19:47:31',NULL,36,'昆明理工大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1898,0,NULL,'2022-09-15 19:47:31',NULL,36,'昆明理工大学津桥学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1899,0,NULL,'2022-09-15 19:47:31',NULL,36,'昆明艺术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1900,0,NULL,'2022-09-15 19:47:31',NULL,36,'昆明铁道职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1901,0,NULL,'2022-09-15 19:47:31',NULL,36,'昌吉学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1902,0,NULL,'2022-09-15 19:47:31',NULL,36,'昌吉职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1903,0,NULL,'2022-09-15 19:47:31',NULL,36,'明达职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1904,0,NULL,'2022-09-15 19:47:31',NULL,36,'星海音乐学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1905,0,NULL,'2022-09-15 19:47:31',NULL,36,'昭通卫生职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1906,0,NULL,'2022-09-15 19:47:31',NULL,36,'昭通学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1907,0,NULL,'2022-09-15 19:47:31',NULL,36,'晋中学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1908,0,NULL,'2022-09-15 19:47:31',NULL,36,'晋中师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1909,0,NULL,'2022-09-15 19:47:31',NULL,36,'晋中职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1910,0,NULL,'2022-09-15 19:47:31',NULL,36,'晋城职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1911,0,NULL,'2022-09-15 19:47:31',NULL,36,'普洱学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1912,0,NULL,'2022-09-15 19:47:31',NULL,36,'景德镇学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1913,0,NULL,'2022-09-15 19:47:31',NULL,36,'景德镇陶瓷大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1914,0,NULL,'2022-09-15 19:47:31',NULL,36,'景德镇陶瓷大学科技艺术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1915,0,NULL,'2022-09-15 19:47:31',NULL,36,'景德镇陶瓷职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1916,0,NULL,'2022-09-15 19:47:31',NULL,36,'暨南大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1917,0,NULL,'2022-09-15 19:47:31',NULL,36,'曲阜师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1918,0,NULL,'2022-09-15 19:47:31',NULL,36,'曲阜远东职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1919,0,NULL,'2022-09-15 19:47:31',NULL,36,'曲靖医学高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1920,0,NULL,'2022-09-15 19:47:31',NULL,36,'曲靖师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1921,0,NULL,'2022-09-15 19:47:31',NULL,36,'曹妃甸职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1922,0,NULL,'2022-09-15 19:47:31',NULL,36,'朔州师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1923,0,NULL,'2022-09-15 19:47:31',NULL,36,'朔州职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1924,0,NULL,'2022-09-15 19:47:31',NULL,36,'朝阳师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1925,0,NULL,'2022-09-15 19:47:31',NULL,36,'杨凌职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1926,0,NULL,'2022-09-15 19:47:31',NULL,36,'杭州万向职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1927,0,NULL,'2022-09-15 19:47:31',NULL,36,'杭州师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1928,0,NULL,'2022-09-15 19:47:31',NULL,36,'杭州师范大学钱江学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1929,0,NULL,'2022-09-15 19:47:31',NULL,36,'杭州电子科技大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1930,0,NULL,'2022-09-15 19:47:31',NULL,36,'杭州电子科技大学信息工程学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1931,0,NULL,'2022-09-15 19:47:31',NULL,36,'杭州科技职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1932,0,NULL,'2022-09-15 19:47:31',NULL,36,'杭州职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1933,0,NULL,'2022-09-15 19:47:31',NULL,36,'松原职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1934,0,NULL,'2022-09-15 19:47:31',NULL,36,'枣庄学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1935,0,NULL,'2022-09-15 19:47:31',NULL,36,'枣庄科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1936,0,NULL,'2022-09-15 19:47:31',NULL,36,'枣庄职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1937,0,NULL,'2022-09-15 19:47:31',NULL,36,'柳州城市职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1938,0,NULL,'2022-09-15 19:47:31',NULL,36,'柳州职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1939,0,NULL,'2022-09-15 19:47:31',NULL,36,'柳州铁道职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1940,0,NULL,'2022-09-15 19:47:31',NULL,36,'株洲师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1941,0,NULL,'2022-09-15 19:47:31',NULL,36,'桂林医学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1942,0,NULL,'2022-09-15 19:47:31',NULL,36,'桂林山水职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1943,0,NULL,'2022-09-15 19:47:31',NULL,36,'桂林师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1944,0,NULL,'2022-09-15 19:47:31',NULL,36,'桂林旅游学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1945,0,NULL,'2022-09-15 19:47:31',NULL,36,'桂林理工大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1946,0,NULL,'2022-09-15 19:47:31',NULL,36,'桂林理工大学博文管理学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1947,0,NULL,'2022-09-15 19:47:31',NULL,36,'桂林电子科技大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1948,0,NULL,'2022-09-15 19:47:31',NULL,36,'桂林电子科技大学信息科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1949,0,NULL,'2022-09-15 19:47:31',NULL,36,'桂林航天工业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1950,0,NULL,'2022-09-15 19:47:31',NULL,36,'桐城师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1951,0,NULL,'2022-09-15 19:47:31',NULL,36,'梧州学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1952,0,NULL,'2022-09-15 19:47:31',NULL,36,'梧州职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1953,0,NULL,'2022-09-15 19:47:31',NULL,36,'楚雄医药高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1954,0,NULL,'2022-09-15 19:47:31',NULL,36,'楚雄师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1955,0,NULL,'2022-09-15 19:47:31',NULL,36,'榆林学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1956,0,NULL,'2022-09-15 19:47:31',NULL,36,'榆林职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1957,0,NULL,'2022-09-15 19:47:31',NULL,36,'正德职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1958,0,NULL,'2022-09-15 19:47:31',NULL,36,'武夷学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1959,0,NULL,'2022-09-15 19:47:31',NULL,36,'武夷山职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1960,0,NULL,'2022-09-15 19:47:31',NULL,36,'武威职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1961,0,NULL,'2022-09-15 19:47:31',NULL,36,'武昌工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1962,0,NULL,'2022-09-15 19:47:31',NULL,36,'武昌理工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1963,0,NULL,'2022-09-15 19:47:31',NULL,36,'武昌职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1964,0,NULL,'2022-09-15 19:47:31',NULL,36,'武昌首义学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1965,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉东湖学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1966,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉交通职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1967,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉传媒学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1968,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉体育学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1969,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉体育学院体育科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1970,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉信息传播职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1971,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉光谷职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1972,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉华夏理工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1973,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉商学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1974,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉商贸职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1975,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉城市职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1976,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉外语外事职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1977,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1978,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1979,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉工业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1980,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉工商学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1981,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉工程大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1982,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉工程大学邮电与信息工程学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1983,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉工程科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1984,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉工程职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1985,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉工贸职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1986,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉晴川学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1987,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉民政职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1988,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉海事职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1989,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉理工大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1990,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉生物工程学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1991,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉电力职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1992,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉科技大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1993,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉科技大学城市学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1994,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1995,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉纺织大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1996,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉纺织大学外经贸学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1997,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1998,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉航海职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(1999,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉船舶职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2000,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉警官职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2001,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉设计工程学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2002,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉软件工程职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2003,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉轻工大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2004,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉铁路桥梁职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2005,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉铁路职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2006,0,NULL,'2022-09-15 19:47:31',NULL,36,'武汉音乐学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2007,0,NULL,'2022-09-15 19:47:31',NULL,36,'毕节医学高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2008,0,NULL,'2022-09-15 19:47:31',NULL,36,'毕节幼儿师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2009,0,NULL,'2022-09-15 19:47:31',NULL,36,'毕节职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2010,0,NULL,'2022-09-15 19:47:31',NULL,36,'民办万博科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2011,0,NULL,'2022-09-15 19:47:31',NULL,36,'民办合肥滨湖职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2012,0,NULL,'2022-09-15 19:47:31',NULL,36,'民办合肥经济技术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2013,0,NULL,'2022-09-15 19:47:31',NULL,36,'民办合肥财经职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2014,0,NULL,'2022-09-15 19:47:31',NULL,36,'民办四川天一学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2015,0,NULL,'2022-09-15 19:47:31',NULL,36,'民办安徽旅游职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2016,0,NULL,'2022-09-15 19:47:31',NULL,36,'永城职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2017,0,NULL,'2022-09-15 19:47:31',NULL,36,'永州职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2018,0,NULL,'2022-09-15 19:47:31',NULL,36,'汉中职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2019,0,NULL,'2022-09-15 19:47:31',NULL,36,'汉口学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2020,0,NULL,'2022-09-15 19:47:31',NULL,36,'汉江师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2021,0,NULL,'2022-09-15 19:47:31',NULL,36,'汕头大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2022,0,NULL,'2022-09-15 19:47:31',NULL,36,'汕头职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2023,0,NULL,'2022-09-15 19:47:31',NULL,36,'汕尾职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2024,0,NULL,'2022-09-15 19:47:31',NULL,36,'江南大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2025,0,NULL,'2022-09-15 19:47:31',NULL,36,'江南影视艺术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2026,0,NULL,'2022-09-15 19:47:31',NULL,36,'江汉大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2027,0,NULL,'2022-09-15 19:47:31',NULL,36,'江汉大学文理学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2028,0,NULL,'2022-09-15 19:47:31',NULL,36,'江汉艺术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2029,0,NULL,'2022-09-15 19:47:31',NULL,36,'江海职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2030,0,NULL,'2022-09-15 19:47:31',NULL,36,'江苏信息职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2031,0,NULL,'2022-09-15 19:47:31',NULL,36,'江苏农林职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2032,0,NULL,'2022-09-15 19:47:31',NULL,36,'江苏农牧科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2033,0,NULL,'2022-09-15 19:47:31',NULL,36,'江苏医药职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2034,0,NULL,'2022-09-15 19:47:31',NULL,36,'江苏卫生健康职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2035,0,NULL,'2022-09-15 19:47:31',NULL,36,'江苏商贸职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2036,0,NULL,'2022-09-15 19:47:31',NULL,36,'江苏城乡建设职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2037,0,NULL,'2022-09-15 19:47:31',NULL,36,'江苏城市职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2038,0,NULL,'2022-09-15 19:47:31',NULL,36,'江苏大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2039,0,NULL,'2022-09-15 19:47:31',NULL,36,'江苏大学京江学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2040,0,NULL,'2022-09-15 19:47:31',NULL,36,'江苏安全技术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2041,0,NULL,'2022-09-15 19:47:31',NULL,36,'江苏工程职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2042,0,NULL,'2022-09-15 19:47:31',NULL,36,'江苏师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2043,0,NULL,'2022-09-15 19:47:31',NULL,36,'江苏师范大学科文学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2044,0,NULL,'2022-09-15 19:47:31',NULL,36,'江苏建筑职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2045,0,NULL,'2022-09-15 19:47:31',NULL,36,'江苏护理职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2046,0,NULL,'2022-09-15 19:47:31',NULL,36,'江苏旅游职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2047,0,NULL,'2022-09-15 19:47:31',NULL,36,'江苏海事职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2048,0,NULL,'2022-09-15 19:47:31',NULL,36,'江苏理工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2049,0,NULL,'2022-09-15 19:47:31',NULL,36,'江苏科技大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2050,0,NULL,'2022-09-15 19:47:31',NULL,36,'江苏科技大学苏州理工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2051,0,NULL,'2022-09-15 19:47:31',NULL,36,'江苏第二师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2052,0,NULL,'2022-09-15 19:47:31',NULL,36,'江苏经贸职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2053,0,NULL,'2022-09-15 19:47:31',NULL,36,'江苏联合职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2054,0,NULL,'2022-09-15 19:47:31',NULL,36,'江苏航空职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2055,0,NULL,'2022-09-15 19:47:31',NULL,36,'江苏警官学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2056,0,NULL,'2022-09-15 19:47:31',NULL,36,'江苏财会职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2057,0,NULL,'2022-09-15 19:47:31',NULL,36,'江苏财经职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2058,0,NULL,'2022-09-15 19:47:31',NULL,36,'江苏食品药品职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2059,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西中医药大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2060,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西中医药大学科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2061,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西中医药高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2062,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西交通职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2063,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西传媒职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2064,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西信息应用职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2065,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西先锋软件职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2066,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西农业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2067,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西农业大学南昌商学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2068,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西农业工程职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2069,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西冶金职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2070,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西制造职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2071,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西医学高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2072,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西卫生职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2073,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西司法警官职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2074,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西外语外贸职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2075,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西工业工程职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2076,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西工业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2077,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西工业贸易职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2078,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西工商职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2079,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西工程学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2080,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西工程职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2081,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2082,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西师范大学科学技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2083,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2084,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西应用工程职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2085,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西应用技术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2086,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西应用科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2087,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西建设职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2088,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西新能源科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2089,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西旅游商贸职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2090,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西服装学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2091,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西机电职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2092,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西枫林涉外经贸职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2093,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西水利职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2094,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西泰豪动漫职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2095,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西洪州职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2096,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西环境工程职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2097,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西现代职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2098,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西理工大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2099,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西理工大学应用科学学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2100,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西生物科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2101,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西电力职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2102,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2103,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西科技师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2104,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西科技师范大学理工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2105,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2106,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西管理职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2107,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西经济管理职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2108,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西航空职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2109,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西艺术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2110,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西警察学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2111,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西财经大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2112,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西财经大学现代经济管理学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2113,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西财经职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2114,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西陶瓷工艺美术职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2115,0,NULL,'2022-09-15 19:47:31',NULL,36,'江西青年职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2116,0,NULL,'2022-09-15 19:47:31',NULL,36,'江门职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2117,0,NULL,'2022-09-15 19:47:31',NULL,36,'江阴职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2118,0,NULL,'2022-09-15 19:47:31',NULL,36,'池州学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2119,0,NULL,'2022-09-15 19:47:31',NULL,36,'池州职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2120,0,NULL,'2022-09-15 19:47:31',NULL,36,'沈阳体育学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2121,0,NULL,'2022-09-15 19:47:31',NULL,36,'沈阳农业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2122,0,NULL,'2022-09-15 19:47:31',NULL,36,'沈阳化工大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2123,0,NULL,'2022-09-15 19:47:31',NULL,36,'沈阳北软信息职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2124,0,NULL,'2022-09-15 19:47:31',NULL,36,'沈阳医学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2125,0,NULL,'2022-09-15 19:47:31',NULL,36,'沈阳城市学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2126,0,NULL,'2022-09-15 19:47:31',NULL,36,'沈阳城市建设学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2127,0,NULL,'2022-09-15 19:47:31',NULL,36,'沈阳大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2128,0,NULL,'2022-09-15 19:47:31',NULL,36,'沈阳工业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2129,0,NULL,'2022-09-15 19:47:31',NULL,36,'沈阳工业大学工程学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2130,0,NULL,'2022-09-15 19:47:31',NULL,36,'沈阳工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2131,0,NULL,'2022-09-15 19:47:31',NULL,36,'沈阳工程学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2132,0,NULL,'2022-09-15 19:47:31',NULL,36,'沈阳师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2133,0,NULL,'2022-09-15 19:47:31',NULL,36,'沈阳建筑大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2134,0,NULL,'2022-09-15 19:47:31',NULL,36,'沈阳理工大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2135,0,NULL,'2022-09-15 19:47:31',NULL,36,'沈阳科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2136,0,NULL,'2022-09-15 19:47:31',NULL,36,'沈阳职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2137,0,NULL,'2022-09-15 19:47:31',NULL,36,'沈阳航空职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2138,0,NULL,'2022-09-15 19:47:31',NULL,36,'沈阳航空航天大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2139,0,NULL,'2022-09-15 19:47:31',NULL,36,'沈阳航空航天大学北方科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2140,0,NULL,'2022-09-15 19:47:31',NULL,36,'沈阳药科大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2141,0,NULL,'2022-09-15 19:47:31',NULL,36,'沈阳音乐学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2142,0,NULL,'2022-09-15 19:47:31',NULL,36,'沙洲职业工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2143,0,NULL,'2022-09-15 19:47:31',NULL,36,'沧州医学高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2144,0,NULL,'2022-09-15 19:47:31',NULL,36,'沧州师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2145,0,NULL,'2022-09-15 19:47:31',NULL,36,'沧州职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2146,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北东方学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2147,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北中医学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2148,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北交通职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2149,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北传媒学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2150,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北体育学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2151,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北公安警察职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2152,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北农业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2153,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北农业大学现代科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2154,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北劳动关系职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2155,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北化工医药职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2156,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北北方学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2157,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北医科大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2158,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北医科大学临床学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2159,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北司法警官职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2160,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北地质大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2161,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北地质大学华信学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2162,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北外国语学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2163,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2164,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北大学工商学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2165,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北女子职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2166,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北对外经贸职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2167,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北工业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2168,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北工业大学城市学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2169,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北工业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2170,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北工程大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2171,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北工程大学科信学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2172,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北工程技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2173,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北工艺美术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2174,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2175,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北师范大学汇华学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2176,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北建材职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2177,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北建筑工程学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2178,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北政法职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2179,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北旅游职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2180,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北机电职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2181,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北民族师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2182,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北水利电力学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2183,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北环境工程学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2184,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北省艺术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2185,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北石油职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2186,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北科技大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2187,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北科技大学理工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2188,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2189,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北科技师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2190,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北经贸大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2191,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北经贸大学经济管理学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2192,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北美术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2193,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北能源职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2194,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北轨道运输职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2195,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北软件职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2196,0,NULL,'2022-09-15 19:47:31',NULL,36,'河北金融学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2197,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南中医药大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2198,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南交通职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2199,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南信息统计职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2200,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南农业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2201,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南农业职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2202,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南医学高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2203,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南司法警官职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2204,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南城建学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2205,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2206,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南大学民生学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2207,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南工业和信息化职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2208,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南工业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2209,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南工业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2210,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南工业贸易职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2211,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2212,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南工程学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2213,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2214,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南师范大学新联学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2215,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南应用技术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2216,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南建筑职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2217,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南护理职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2218,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南推拿职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2219,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南机电职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2220,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南林业职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2221,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南检察职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2222,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南水利与环境职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2223,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南测绘职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2224,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南牧业经济学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2225,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南理工大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2226,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南科技大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2227,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2228,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南科技学院新科学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2229,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南经贸职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2230,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2231,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南艺术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2232,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南警察学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2233,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南财政金融学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2234,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南财经政法大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2235,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南质量工程职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2236,0,NULL,'2022-09-15 19:47:31',NULL,36,'河南轻工职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2237,0,NULL,'2022-09-15 19:47:31',NULL,36,'河套学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2238,0,NULL,'2022-09-15 19:47:31',NULL,36,'河池学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2239,0,NULL,'2022-09-15 19:47:31',NULL,36,'河海大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2240,0,NULL,'2022-09-15 19:47:31',NULL,36,'河海大学文天学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2241,0,NULL,'2022-09-15 19:47:31',NULL,36,'河源职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2242,0,NULL,'2022-09-15 19:47:31',NULL,36,'河西学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2243,0,NULL,'2022-09-15 19:47:31',NULL,36,'泉州信息工程学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2244,0,NULL,'2022-09-15 19:47:31',NULL,36,'泉州医学高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2245,0,NULL,'2022-09-15 19:47:31',NULL,36,'泉州华光职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2246,0,NULL,'2022-09-15 19:47:31',NULL,36,'泉州工程职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2247,0,NULL,'2022-09-15 19:47:31',NULL,36,'泉州工艺美术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2248,0,NULL,'2022-09-15 19:47:31',NULL,36,'泉州师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2249,0,NULL,'2022-09-15 19:47:31',NULL,36,'泉州幼儿师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2250,0,NULL,'2022-09-15 19:47:31',NULL,36,'泉州海洋职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2251,0,NULL,'2022-09-15 19:47:31',NULL,36,'泉州理工职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2252,0,NULL,'2022-09-15 19:47:31',NULL,36,'泉州纺织服装职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2253,0,NULL,'2022-09-15 19:47:31',NULL,36,'泉州经贸职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2254,0,NULL,'2022-09-15 19:47:31',NULL,36,'泉州轻工职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2255,0,NULL,'2022-09-15 19:47:31',NULL,36,'泊头职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2256,0,NULL,'2022-09-15 19:47:31',NULL,36,'泰山医学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2257,0,NULL,'2022-09-15 19:47:31',NULL,36,'泰山学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2258,0,NULL,'2022-09-15 19:47:31',NULL,36,'泰山护理职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2259,0,NULL,'2022-09-15 19:47:31',NULL,36,'泰山职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2260,0,NULL,'2022-09-15 19:47:31',NULL,36,'泰州学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2261,0,NULL,'2022-09-15 19:47:31',NULL,36,'泰州职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2262,0,NULL,'2022-09-15 19:47:31',NULL,36,'泸州职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2263,0,NULL,'2022-09-15 19:47:31',NULL,36,'洛阳师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2264,0,NULL,'2022-09-15 19:47:31',NULL,36,'洛阳理工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2265,0,NULL,'2022-09-15 19:47:31',NULL,36,'洛阳科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2266,0,NULL,'2022-09-15 19:47:31',NULL,36,'洛阳职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2267,0,NULL,'2022-09-15 19:47:31',NULL,36,'济南大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2268,0,NULL,'2022-09-15 19:47:31',NULL,36,'济南大学泉城学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2269,0,NULL,'2022-09-15 19:47:31',NULL,36,'济南工程职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2270,0,NULL,'2022-09-15 19:47:31',NULL,36,'济南幼儿师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2271,0,NULL,'2022-09-15 19:47:31',NULL,36,'济南护理职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2272,0,NULL,'2022-09-15 19:47:31',NULL,36,'济南职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2273,0,NULL,'2022-09-15 19:47:31',NULL,36,'济宁医学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2274,0,NULL,'2022-09-15 19:47:31',NULL,36,'济宁学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2275,0,NULL,'2022-09-15 19:47:31',NULL,36,'济宁职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2276,0,NULL,'2022-09-15 19:47:31',NULL,36,'济源职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2277,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江万里学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2278,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江东方职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2279,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江中医药大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2280,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江中医药大学滨江学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2281,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江交通职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2282,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江传媒学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2283,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江体育职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2284,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江农业商贸职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2285,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江农林大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2286,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江农林大学暨阳学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2287,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江医药高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2288,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江同济科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2289,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江商业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2290,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江国际海运职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2291,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江外国语学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2292,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2293,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江大学城市学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2294,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江大学宁波理工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2295,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江安防职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2296,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江工业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2297,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江工业大学之江学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2298,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江工业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2299,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江工商大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2300,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江工商大学杭州商学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2301,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江工商职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2302,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江工贸职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2303,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2304,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江师范大学行知学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2305,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江广厦建设职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2306,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江建设职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2307,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江旅游职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2308,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江机电职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2309,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江树人学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2310,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江横店影视职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2311,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江水利水电学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2312,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江汽车职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2313,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江海洋大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2314,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江海洋大学东海科学技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2315,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江特殊教育职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2316,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江理工大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2317,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江理工大学科技与艺术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2318,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江电力职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2319,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2320,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江纺织服装职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2321,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江经济职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2322,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江经贸职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2323,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江育英职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2324,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江舟山群岛新区旅游与健康职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2325,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江艺术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2326,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江警官职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2327,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江警察学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2328,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江财经大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2329,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江财经大学东方学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2330,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江越秀外国语学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2331,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江邮电职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2332,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江金融职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2333,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江长征职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2334,0,NULL,'2022-09-15 19:47:31',NULL,36,'浙江音乐学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2335,0,NULL,'2022-09-15 19:47:31',NULL,36,'海南体育职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2336,0,NULL,'2022-09-15 19:47:31',NULL,36,'海南医学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2337,0,NULL,'2022-09-15 19:47:31',NULL,36,'海南外国语职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2338,0,NULL,'2022-09-15 19:47:31',NULL,36,'海南大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2339,0,NULL,'2022-09-15 19:47:31',NULL,36,'海南工商职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2340,0,NULL,'2022-09-15 19:47:31',NULL,36,'海南师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2341,0,NULL,'2022-09-15 19:47:31',NULL,36,'海南政法职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2342,0,NULL,'2022-09-15 19:47:31',NULL,36,'海南热带海洋学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2343,0,NULL,'2022-09-15 19:47:31',NULL,36,'海南科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2344,0,NULL,'2022-09-15 19:47:31',NULL,36,'海南经贸职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2345,0,NULL,'2022-09-15 19:47:31',NULL,36,'海南职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2346,0,NULL,'2022-09-15 19:47:31',NULL,36,'海南软件职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2347,0,NULL,'2022-09-15 19:47:31',NULL,36,'海口经济学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2348,0,NULL,'2022-09-15 19:47:31',NULL,36,'淄博师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2349,0,NULL,'2022-09-15 19:47:31',NULL,36,'淄博职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2350,0,NULL,'2022-09-15 19:47:31',NULL,36,'淮北师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2351,0,NULL,'2022-09-15 19:47:31',NULL,36,'淮北师范大学信息学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2352,0,NULL,'2022-09-15 19:47:31',NULL,36,'淮北职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2353,0,NULL,'2022-09-15 19:47:31',NULL,36,'淮南师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2354,0,NULL,'2022-09-15 19:47:31',NULL,36,'淮南职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2355,0,NULL,'2022-09-15 19:47:31',NULL,36,'淮南联合大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2356,0,NULL,'2022-09-15 19:47:31',NULL,36,'淮安信息职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2357,0,NULL,'2022-09-15 19:47:31',NULL,36,'淮海工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2358,0,NULL,'2022-09-15 19:47:31',NULL,36,'淮阴工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2359,0,NULL,'2022-09-15 19:47:31',NULL,36,'淮阴师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2360,0,NULL,'2022-09-15 19:47:31',NULL,36,'深圳信息职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2361,0,NULL,'2022-09-15 19:47:31',NULL,36,'深圳北理莫斯科大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2362,0,NULL,'2022-09-15 19:47:31',NULL,36,'深圳大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2363,0,NULL,'2022-09-15 19:47:31',NULL,36,'深圳职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2364,0,NULL,'2022-09-15 19:47:31',NULL,36,'清华大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2365,0,NULL,'2022-09-15 19:47:31',NULL,36,'清远职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2366,0,NULL,'2022-09-15 19:47:31',NULL,36,'渤海大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2367,0,NULL,'2022-09-15 19:47:31',NULL,36,'渤海理工职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2368,0,NULL,'2022-09-15 19:47:31',NULL,36,'渤海石油职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2369,0,NULL,'2022-09-15 19:47:31',NULL,36,'渤海船舶职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2370,0,NULL,'2022-09-15 19:47:31',NULL,36,'温州医科大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2371,0,NULL,'2022-09-15 19:47:31',NULL,36,'温州医科大学仁济学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2372,0,NULL,'2022-09-15 19:47:31',NULL,36,'温州商学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2373,0,NULL,'2022-09-15 19:47:31',NULL,36,'温州大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2374,0,NULL,'2022-09-15 19:47:31',NULL,36,'温州大学瓯江学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2375,0,NULL,'2022-09-15 19:47:31',NULL,36,'温州科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2376,0,NULL,'2022-09-15 19:47:31',NULL,36,'温州职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2377,0,NULL,'2022-09-15 19:47:31',NULL,36,'温州肯恩大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2378,0,NULL,'2022-09-15 19:47:31',NULL,36,'渭南师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2379,0,NULL,'2022-09-15 19:47:31',NULL,36,'渭南职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2380,0,NULL,'2022-09-15 19:47:31',NULL,36,'湄洲湾职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2381,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北三峡职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2382,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北中医药大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2383,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北中医药高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2384,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北交通职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2385,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北体育职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2386,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北医药学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2387,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北医药学院药护学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2388,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北商贸学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2389,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北国土资源职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2390,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北城市建设职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2391,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2392,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北大学知行学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2393,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北工业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2394,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北工业大学工程技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2395,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北工业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2396,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北工程学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2397,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北工程学院新技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2398,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北工程职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2399,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2400,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北师范大学文理学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2401,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北幼儿师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2402,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北开放职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2403,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北文理学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2404,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北文理学院理工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2405,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北民族学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2406,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北民族学院科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2407,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北水利水电职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2408,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北汽车工业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2409,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北汽车工业学院科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2410,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北理工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2411,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北生态工程职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2412,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北生物科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2413,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2414,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2415,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北第二师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2416,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北经济学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2417,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北经济学院法商学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2418,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北美术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2419,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2420,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北艺术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2421,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北警官学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2422,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北财税职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2423,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北轻工职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2424,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北铁道运输职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2425,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖北青年职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2426,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南三一工业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2427,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南中医药大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2428,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南中医药大学湘杏学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2429,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南中医药高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2430,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南九嶷职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2431,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南交通工程学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2432,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南交通职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2433,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南人文科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2434,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南体育职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2435,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南信息学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2436,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南信息职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2437,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南农业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2438,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南农业大学东方科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2439,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南冶金职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2440,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南劳动人事职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2441,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南化工职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2442,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南医药学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2443,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南司法警官职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2444,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南吉利汽车职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2445,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南商务职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2446,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南商学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2447,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南商学院北津学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2448,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南国防工业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2449,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南城市学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2450,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南城建职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2451,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南外国语职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2452,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南外贸职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2453,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南大众传媒职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2454,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2455,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南女子学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2456,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南安全技术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2457,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南工业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2458,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南工业大学科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2459,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南工业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2460,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南工商职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2461,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2462,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南工程学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2463,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南工程学院应用技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2464,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南工程职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2465,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南工艺美术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2466,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2467,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南师范大学树达学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2468,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南幼儿师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2469,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南应用技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2470,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南文理学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2471,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南文理学院芙蓉学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2472,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南有色金属职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2473,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南机电职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2474,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南民族职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2475,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南水利水电职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2476,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南汽车工程职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2477,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南涉外经济学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2478,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南环境生物职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2479,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南现代物流职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2480,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南理工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2481,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南理工学院南湖学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2482,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南理工职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2483,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南生物机电职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2484,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南电子科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2485,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南电气职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2486,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南石油化工职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2487,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南科技大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2488,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南科技大学潇湘学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2489,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2490,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2491,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南税务高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2492,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南第一师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2493,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南网络工程职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2494,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南艺术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2495,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南警察学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2496,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南财政经济学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2497,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南财经工业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2498,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南软件职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2499,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南邮电职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2500,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南都市职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2501,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南铁路科技职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2502,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南铁道职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2503,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南食品药品职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2504,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南高尔夫旅游职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2505,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖南高速铁路职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2506,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖州师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2507,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖州师范学院求真学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2508,0,NULL,'2022-09-15 19:47:31',NULL,36,'湖州职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2509,0,NULL,'2022-09-15 19:47:31',NULL,36,'湘中幼儿师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2510,0,NULL,'2022-09-15 19:47:31',NULL,36,'湘南学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2511,0,NULL,'2022-09-15 19:47:31',NULL,36,'湘南幼儿师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2512,0,NULL,'2022-09-15 19:47:31',NULL,36,'湘潭医卫职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2513,0,NULL,'2022-09-15 19:47:31',NULL,36,'湘潭大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2514,0,NULL,'2022-09-15 19:47:31',NULL,36,'湘潭大学兴湘学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2515,0,NULL,'2022-09-15 19:47:31',NULL,36,'湘西民族职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2516,0,NULL,'2022-09-15 19:47:31',NULL,36,'湛江幼儿师范专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2517,0,NULL,'2022-09-15 19:47:31',NULL,36,'滁州城市职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2518,0,NULL,'2022-09-15 19:47:31',NULL,36,'滁州学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2519,0,NULL,'2022-09-15 19:47:31',NULL,36,'滁州职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2520,0,NULL,'2022-09-15 19:47:31',NULL,36,'滇西应用技术大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2521,0,NULL,'2022-09-15 19:47:31',NULL,36,'滇西科技师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2522,0,NULL,'2022-09-15 19:47:31',NULL,36,'满洲里俄语职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2523,0,NULL,'2022-09-15 19:47:31',NULL,36,'滨州医学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2524,0,NULL,'2022-09-15 19:47:31',NULL,36,'滨州学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2525,0,NULL,'2022-09-15 19:47:31',NULL,36,'滨州职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2526,0,NULL,'2022-09-15 19:47:31',NULL,36,'漯河医学高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2527,0,NULL,'2022-09-15 19:47:31',NULL,36,'漯河职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2528,0,NULL,'2022-09-15 19:47:31',NULL,36,'漯河食品职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2529,0,NULL,'2022-09-15 19:47:31',NULL,36,'漳州卫生职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2530,0,NULL,'2022-09-15 19:47:31',NULL,36,'漳州城市职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2531,0,NULL,'2022-09-15 19:47:31',NULL,36,'漳州理工职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2532,0,NULL,'2022-09-15 19:47:31',NULL,36,'漳州科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2533,0,NULL,'2022-09-15 19:47:31',NULL,36,'漳州职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2534,0,NULL,'2022-09-15 19:47:31',NULL,36,'潇湘职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2535,0,NULL,'2022-09-15 19:47:31',NULL,36,'潍坊医学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2536,0,NULL,'2022-09-15 19:47:31',NULL,36,'潍坊学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2537,0,NULL,'2022-09-15 19:47:31',NULL,36,'潍坊工商职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2538,0,NULL,'2022-09-15 19:47:31',NULL,36,'潍坊工程职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2539,0,NULL,'2022-09-15 19:47:31',NULL,36,'潍坊护理职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2540,0,NULL,'2022-09-15 19:47:31',NULL,36,'潍坊科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2541,0,NULL,'2022-09-15 19:47:31',NULL,36,'潍坊职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2542,0,NULL,'2022-09-15 19:47:31',NULL,36,'潞安职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2543,0,NULL,'2022-09-15 19:47:31',NULL,36,'潮汕职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2544,0,NULL,'2022-09-15 19:47:31',NULL,36,'濮阳医学高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2545,0,NULL,'2022-09-15 19:47:31',NULL,36,'濮阳职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2546,0,NULL,'2022-09-15 19:47:31',NULL,36,'炎黄职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2547,0,NULL,'2022-09-15 19:47:31',NULL,36,'烟台南山学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2548,0,NULL,'2022-09-15 19:47:31',NULL,36,'烟台大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2549,0,NULL,'2022-09-15 19:47:31',NULL,36,'烟台大学文经学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2550,0,NULL,'2022-09-15 19:47:31',NULL,36,'烟台工程职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2551,0,NULL,'2022-09-15 19:47:31',NULL,36,'烟台汽车工程职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2552,0,NULL,'2022-09-15 19:47:31',NULL,36,'烟台职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2553,0,NULL,'2022-09-15 19:47:31',NULL,36,'烟台黄金职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2554,0,NULL,'2022-09-15 19:47:31',NULL,36,'焦作大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2555,0,NULL,'2022-09-15 19:47:31',NULL,36,'焦作工贸职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2556,0,NULL,'2022-09-15 19:47:31',NULL,36,'焦作师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2557,0,NULL,'2022-09-15 19:47:31',NULL,36,'燕京理工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2558,0,NULL,'2022-09-15 19:47:31',NULL,36,'燕山大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2559,0,NULL,'2022-09-15 19:47:31',NULL,36,'燕山大学里仁学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2560,0,NULL,'2022-09-15 19:47:31',NULL,36,'牡丹江医学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2561,0,NULL,'2022-09-15 19:47:31',NULL,36,'牡丹江大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2562,0,NULL,'2022-09-15 19:47:31',NULL,36,'牡丹江师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2563,0,NULL,'2022-09-15 19:47:31',NULL,36,'玉林师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2564,0,NULL,'2022-09-15 19:47:31',NULL,36,'玉柴职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2565,0,NULL,'2022-09-15 19:47:31',NULL,36,'玉溪农业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2566,0,NULL,'2022-09-15 19:47:31',NULL,36,'玉溪师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2567,0,NULL,'2022-09-15 19:47:31',NULL,36,'珠海城市职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2568,0,NULL,'2022-09-15 19:47:31',NULL,36,'珠海艺术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2569,0,NULL,'2022-09-15 19:47:31',NULL,36,'琼台师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2570,0,NULL,'2022-09-15 19:47:31',NULL,36,'甘肃中医药大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2571,0,NULL,'2022-09-15 19:47:31',NULL,36,'甘肃交通职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2572,0,NULL,'2022-09-15 19:47:31',NULL,36,'甘肃农业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2573,0,NULL,'2022-09-15 19:47:31',NULL,36,'甘肃农业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2574,0,NULL,'2022-09-15 19:47:31',NULL,36,'甘肃医学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2575,0,NULL,'2022-09-15 19:47:31',NULL,36,'甘肃卫生职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2576,0,NULL,'2022-09-15 19:47:31',NULL,36,'甘肃工业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2577,0,NULL,'2022-09-15 19:47:31',NULL,36,'甘肃建筑职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2578,0,NULL,'2022-09-15 19:47:31',NULL,36,'甘肃政法学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2579,0,NULL,'2022-09-15 19:47:31',NULL,36,'甘肃有色冶金职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2580,0,NULL,'2022-09-15 19:47:31',NULL,36,'甘肃机电职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2581,0,NULL,'2022-09-15 19:47:31',NULL,36,'甘肃林业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2582,0,NULL,'2022-09-15 19:47:31',NULL,36,'甘肃民族师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2583,0,NULL,'2022-09-15 19:47:31',NULL,36,'甘肃畜牧工程职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2584,0,NULL,'2022-09-15 19:47:31',NULL,36,'甘肃能源化工职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2585,0,NULL,'2022-09-15 19:47:31',NULL,36,'甘肃警察职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2586,0,NULL,'2022-09-15 19:47:31',NULL,36,'甘肃财贸职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2587,0,NULL,'2022-09-15 19:47:31',NULL,36,'甘肃钢铁职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2588,0,NULL,'2022-09-15 19:47:31',NULL,36,'电子科技大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2589,0,NULL,'2022-09-15 19:47:31',NULL,36,'电子科技大学中山学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2590,0,NULL,'2022-09-15 19:47:31',NULL,36,'电子科技大学成都学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2591,0,NULL,'2022-09-15 19:47:31',NULL,36,'白城医学高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2592,0,NULL,'2022-09-15 19:47:31',NULL,36,'白城师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2593,0,NULL,'2022-09-15 19:47:31',NULL,36,'白城职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2594,0,NULL,'2022-09-15 19:47:31',NULL,36,'白银矿冶职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2595,0,NULL,'2022-09-15 19:47:31',NULL,36,'百色学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2596,0,NULL,'2022-09-15 19:47:31',NULL,36,'百色职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2597,0,NULL,'2022-09-15 19:47:31',NULL,36,'皖北卫生职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2598,0,NULL,'2022-09-15 19:47:31',NULL,36,'皖南医学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2599,0,NULL,'2022-09-15 19:47:31',NULL,36,'皖西卫生职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2600,0,NULL,'2022-09-15 19:47:31',NULL,36,'皖西学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2601,0,NULL,'2022-09-15 19:47:31',NULL,36,'益阳医学高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2602,0,NULL,'2022-09-15 19:47:31',NULL,36,'益阳职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2603,0,NULL,'2022-09-15 19:47:31',NULL,36,'盐城工业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2604,0,NULL,'2022-09-15 19:47:31',NULL,36,'盐城工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2605,0,NULL,'2022-09-15 19:47:31',NULL,36,'盐城师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2606,0,NULL,'2022-09-15 19:47:31',NULL,36,'盐城幼儿师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2607,0,NULL,'2022-09-15 19:47:31',NULL,36,'盘锦职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2608,0,NULL,'2022-09-15 19:47:31',NULL,36,'眉山职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2609,0,NULL,'2022-09-15 19:47:31',NULL,36,'石家庄人民医学高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2610,0,NULL,'2022-09-15 19:47:31',NULL,36,'石家庄信息工程职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2611,0,NULL,'2022-09-15 19:47:31',NULL,36,'石家庄医学高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2612,0,NULL,'2022-09-15 19:47:31',NULL,36,'石家庄城市经济职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2613,0,NULL,'2022-09-15 19:47:31',NULL,36,'石家庄学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2614,0,NULL,'2022-09-15 19:47:31',NULL,36,'石家庄工商职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2615,0,NULL,'2022-09-15 19:47:31',NULL,36,'石家庄工程职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2616,0,NULL,'2022-09-15 19:47:31',NULL,36,'石家庄幼儿师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2617,0,NULL,'2022-09-15 19:47:31',NULL,36,'石家庄理工职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2618,0,NULL,'2022-09-15 19:47:31',NULL,36,'石家庄科技信息职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2619,0,NULL,'2022-09-15 19:47:31',NULL,36,'石家庄科技工程职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2620,0,NULL,'2022-09-15 19:47:31',NULL,36,'石家庄科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2621,0,NULL,'2022-09-15 19:47:31',NULL,36,'石家庄经济职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2622,0,NULL,'2022-09-15 19:47:31',NULL,36,'石家庄职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2623,0,NULL,'2022-09-15 19:47:31',NULL,36,'石家庄财经职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2624,0,NULL,'2022-09-15 19:47:31',NULL,36,'石家庄邮电职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2625,0,NULL,'2022-09-15 19:47:31',NULL,36,'石家庄铁路职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2626,0,NULL,'2022-09-15 19:47:31',NULL,36,'石家庄铁道大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2627,0,NULL,'2022-09-15 19:47:31',NULL,36,'石家庄铁道大学四方学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2628,0,NULL,'2022-09-15 19:47:31',NULL,36,'石河子大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2629,0,NULL,'2022-09-15 19:47:31',NULL,36,'石河子大学科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2630,0,NULL,'2022-09-15 19:47:31',NULL,36,'硅湖职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2631,0,NULL,'2022-09-15 19:47:31',NULL,36,'福州墨尔本理工职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2632,0,NULL,'2022-09-15 19:47:31',NULL,36,'福州外语外贸学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2633,0,NULL,'2022-09-15 19:47:31',NULL,36,'福州大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2634,0,NULL,'2022-09-15 19:47:31',NULL,36,'福州大学至诚学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2635,0,NULL,'2022-09-15 19:47:31',NULL,36,'福州理工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2636,0,NULL,'2022-09-15 19:47:31',NULL,36,'福州科技职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2637,0,NULL,'2022-09-15 19:47:31',NULL,36,'福州职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2638,0,NULL,'2022-09-15 19:47:31',NULL,36,'福州英华职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2639,0,NULL,'2022-09-15 19:47:31',NULL,36,'福州软件职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2640,0,NULL,'2022-09-15 19:47:31',NULL,36,'福州黎明职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2641,0,NULL,'2022-09-15 19:47:31',NULL,36,'福建中医药大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2642,0,NULL,'2022-09-15 19:47:31',NULL,36,'福建体育职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2643,0,NULL,'2022-09-15 19:47:31',NULL,36,'福建信息职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2644,0,NULL,'2022-09-15 19:47:31',NULL,36,'福建农业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2645,0,NULL,'2022-09-15 19:47:31',NULL,36,'福建农林大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2646,0,NULL,'2022-09-15 19:47:31',NULL,36,'福建农林大学东方学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2647,0,NULL,'2022-09-15 19:47:31',NULL,36,'福建农林大学金山学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2648,0,NULL,'2022-09-15 19:47:31',NULL,36,'福建医科大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2649,0,NULL,'2022-09-15 19:47:31',NULL,36,'福建华南女子职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2650,0,NULL,'2022-09-15 19:47:31',NULL,36,'福建卫生职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2651,0,NULL,'2022-09-15 19:47:31',NULL,36,'福建商学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2652,0,NULL,'2022-09-15 19:47:31',NULL,36,'福建对外经济贸易职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2653,0,NULL,'2022-09-15 19:47:31',NULL,36,'福建工程学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2654,0,NULL,'2022-09-15 19:47:31',NULL,36,'福建师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2655,0,NULL,'2022-09-15 19:47:31',NULL,36,'福建师范大学协和学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2656,0,NULL,'2022-09-15 19:47:31',NULL,36,'福建师范大学闽南科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2657,0,NULL,'2022-09-15 19:47:31',NULL,36,'福建幼儿师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2658,0,NULL,'2022-09-15 19:47:31',NULL,36,'福建林业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2659,0,NULL,'2022-09-15 19:47:31',NULL,36,'福建水利电力职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2660,0,NULL,'2022-09-15 19:47:31',NULL,36,'福建江夏学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2661,0,NULL,'2022-09-15 19:47:31',NULL,36,'福建生物工程职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2662,0,NULL,'2022-09-15 19:47:31',NULL,36,'福建电力职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2663,0,NULL,'2022-09-15 19:47:31',NULL,36,'福建船政交通职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2664,0,NULL,'2022-09-15 19:47:31',NULL,36,'福建艺术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2665,0,NULL,'2022-09-15 19:47:31',NULL,36,'福建警官职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2666,0,NULL,'2022-09-15 19:47:31',NULL,36,'福建警察学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2667,0,NULL,'2022-09-15 19:47:31',NULL,36,'私立华联学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2668,0,NULL,'2022-09-15 19:47:31',NULL,36,'科尔沁艺术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2669,0,NULL,'2022-09-15 19:47:31',NULL,36,'秦皇岛职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2670,0,NULL,'2022-09-15 19:47:31',NULL,36,'红河卫生职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2671,0,NULL,'2022-09-15 19:47:31',NULL,36,'红河学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2672,0,NULL,'2022-09-15 19:47:31',NULL,36,'绍兴文理学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2673,0,NULL,'2022-09-15 19:47:31',NULL,36,'绍兴文理学院元培学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2674,0,NULL,'2022-09-15 19:47:31',NULL,36,'绍兴职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2675,0,NULL,'2022-09-15 19:47:31',NULL,36,'绥化学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2676,0,NULL,'2022-09-15 19:47:31',NULL,36,'绵阳师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2677,0,NULL,'2022-09-15 19:47:31',NULL,36,'绵阳职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2678,0,NULL,'2022-09-15 19:47:31',NULL,36,'罗定职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2679,0,NULL,'2022-09-15 19:47:31',NULL,36,'聊城大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2680,0,NULL,'2022-09-15 19:47:31',NULL,36,'聊城大学东昌学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2681,0,NULL,'2022-09-15 19:47:31',NULL,36,'聊城职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2682,0,NULL,'2022-09-15 19:47:31',NULL,36,'肇庆医学高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2683,0,NULL,'2022-09-15 19:47:31',NULL,36,'肇庆学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2684,0,NULL,'2022-09-15 19:47:31',NULL,36,'芜湖职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2685,0,NULL,'2022-09-15 19:47:31',NULL,36,'苏州信息职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2686,0,NULL,'2022-09-15 19:47:31',NULL,36,'苏州健雄职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2687,0,NULL,'2022-09-15 19:47:31',NULL,36,'苏州农业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2688,0,NULL,'2022-09-15 19:47:31',NULL,36,'苏州卫生职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2689,0,NULL,'2022-09-15 19:47:31',NULL,36,'苏州大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2690,0,NULL,'2022-09-15 19:47:31',NULL,36,'苏州大学应用技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2691,0,NULL,'2022-09-15 19:47:31',NULL,36,'苏州大学文正学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2692,0,NULL,'2022-09-15 19:47:31',NULL,36,'苏州工业园区服务外包职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2693,0,NULL,'2022-09-15 19:47:31',NULL,36,'苏州工业园区职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2694,0,NULL,'2022-09-15 19:47:31',NULL,36,'苏州工业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2695,0,NULL,'2022-09-15 19:47:31',NULL,36,'苏州工艺美术职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2696,0,NULL,'2022-09-15 19:47:31',NULL,36,'苏州幼儿师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2697,0,NULL,'2022-09-15 19:47:31',NULL,36,'苏州托普信息职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2698,0,NULL,'2022-09-15 19:47:31',NULL,36,'苏州百年职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2699,0,NULL,'2022-09-15 19:47:31',NULL,36,'苏州科技大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2700,0,NULL,'2022-09-15 19:47:31',NULL,36,'苏州科技大学天平学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2701,0,NULL,'2022-09-15 19:47:31',NULL,36,'苏州经贸职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2702,0,NULL,'2022-09-15 19:47:31',NULL,36,'苏州职业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2703,0,NULL,'2022-09-15 19:47:31',NULL,36,'苏州高博软件技术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2704,0,NULL,'2022-09-15 19:47:31',NULL,36,'茂名职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2705,0,NULL,'2022-09-15 19:47:31',NULL,36,'茅台学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2706,0,NULL,'2022-09-15 19:47:31',NULL,36,'荆州理工职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2707,0,NULL,'2022-09-15 19:47:31',NULL,36,'荆州职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2708,0,NULL,'2022-09-15 19:47:31',NULL,36,'荆楚理工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2709,0,NULL,'2022-09-15 19:47:31',NULL,36,'荆门职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2710,0,NULL,'2022-09-15 19:47:31',NULL,36,'莆田学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2711,0,NULL,'2022-09-15 19:47:31',NULL,36,'莱芜职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2712,0,NULL,'2022-09-15 19:47:31',NULL,36,'菏泽医学专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2713,0,NULL,'2022-09-15 19:47:31',NULL,36,'菏泽学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2714,0,NULL,'2022-09-15 19:47:31',NULL,36,'菏泽家政职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2715,0,NULL,'2022-09-15 19:47:31',NULL,36,'菏泽职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2716,0,NULL,'2022-09-15 19:47:31',NULL,36,'萍乡学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2717,0,NULL,'2022-09-15 19:47:31',NULL,36,'营口理工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2718,0,NULL,'2022-09-15 19:47:31',NULL,36,'营口职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2719,0,NULL,'2022-09-15 19:47:31',NULL,36,'蚌埠医学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2720,0,NULL,'2022-09-15 19:47:31',NULL,36,'蚌埠学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2721,0,NULL,'2022-09-15 19:47:31',NULL,36,'蚌埠经济技术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2722,0,NULL,'2022-09-15 19:47:31',NULL,36,'衡水学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2723,0,NULL,'2022-09-15 19:47:31',NULL,36,'衡水职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2724,0,NULL,'2022-09-15 19:47:31',NULL,36,'衡阳师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2725,0,NULL,'2022-09-15 19:47:31',NULL,36,'衡阳师范学院南岳学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2726,0,NULL,'2022-09-15 19:47:31',NULL,36,'衢州学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2727,0,NULL,'2022-09-15 19:47:31',NULL,36,'衢州职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2728,0,NULL,'2022-09-15 19:47:31',NULL,36,'襄阳汽车职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2729,0,NULL,'2022-09-15 19:47:31',NULL,36,'襄阳职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2730,0,NULL,'2022-09-15 19:47:31',NULL,36,'西交利物浦大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2731,0,NULL,'2022-09-15 19:47:31',NULL,36,'西京学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2732,0,NULL,'2022-09-15 19:47:31',NULL,36,'西北农林科技大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2733,0,NULL,'2022-09-15 19:47:31',NULL,36,'西北大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2734,0,NULL,'2022-09-15 19:47:31',NULL,36,'西北大学现代学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2735,0,NULL,'2022-09-15 19:47:31',NULL,36,'西北工业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2736,0,NULL,'2022-09-15 19:47:31',NULL,36,'西北工业大学明德学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2737,0,NULL,'2022-09-15 19:47:31',NULL,36,'西北师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2738,0,NULL,'2022-09-15 19:47:31',NULL,36,'西北师范大学知行学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2739,0,NULL,'2022-09-15 19:47:31',NULL,36,'西北政法大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2740,0,NULL,'2022-09-15 19:47:31',NULL,36,'西北民族大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2741,0,NULL,'2022-09-15 19:47:31',NULL,36,'西华大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2742,0,NULL,'2022-09-15 19:47:31',NULL,36,'西华师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2743,0,NULL,'2022-09-15 19:47:31',NULL,36,'西南交通大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2744,0,NULL,'2022-09-15 19:47:31',NULL,36,'西南交通大学希望学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2745,0,NULL,'2022-09-15 19:47:31',NULL,36,'西南医科大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2746,0,NULL,'2022-09-15 19:47:31',NULL,36,'西南大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2747,0,NULL,'2022-09-15 19:47:31',NULL,36,'西南政法大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2748,0,NULL,'2022-09-15 19:47:31',NULL,36,'西南林业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2749,0,NULL,'2022-09-15 19:47:31',NULL,36,'西南民族大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2750,0,NULL,'2022-09-15 19:47:31',NULL,36,'西南石油大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2751,0,NULL,'2022-09-15 19:47:31',NULL,36,'西南科技大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2752,0,NULL,'2022-09-15 19:47:31',NULL,36,'西南科技大学城市学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2753,0,NULL,'2022-09-15 19:47:31',NULL,36,'西南财经大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2754,0,NULL,'2022-09-15 19:47:31',NULL,36,'西南财经大学天府学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2755,0,NULL,'2022-09-15 19:47:31',NULL,36,'西双版纳职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2756,0,NULL,'2022-09-15 19:47:31',NULL,36,'西宁城市职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2757,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安东方亚太职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2758,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安交通大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2759,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安交通大学城市学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2760,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安交通工程学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2761,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安体育学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2762,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安医学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2763,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安医学高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2764,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安城市建设职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2765,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安培华学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2766,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安外事学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2767,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安外国语大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2768,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安工业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2769,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安工业大学北方信息工程学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2770,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安工程大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2771,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安建筑科技大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2772,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安建筑科技大学华清学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2773,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安思源学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2774,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安文理学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2775,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安欧亚学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2776,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安汽车科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2777,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安海棠职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2778,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安理工大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2779,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安理工大学高科学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2780,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安电力高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2781,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安电子科技大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2782,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安电子科技大学长安学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2783,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安石油大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2784,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安科技大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2785,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安科技大学高新学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2786,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安美术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2787,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安翻译学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2788,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2789,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安航空学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2790,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安航空职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2791,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安财经学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2792,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安财经学院行知学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2793,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安邮电大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2794,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安铁路职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2795,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安音乐学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2796,0,NULL,'2022-09-15 19:47:31',NULL,36,'西安高新科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2797,0,NULL,'2022-09-15 19:47:31',NULL,36,'西昌学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2798,0,NULL,'2022-09-15 19:47:31',NULL,36,'西藏农牧学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2799,0,NULL,'2022-09-15 19:47:31',NULL,36,'西藏大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2800,0,NULL,'2022-09-15 19:47:31',NULL,36,'西藏民族大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2801,0,NULL,'2022-09-15 19:47:31',NULL,36,'西藏职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2802,0,NULL,'2022-09-15 19:47:31',NULL,36,'西藏藏医学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2803,0,NULL,'2022-09-15 19:47:31',NULL,36,'西藏警官高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2804,0,NULL,'2022-09-15 19:47:31',NULL,36,'许昌学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2805,0,NULL,'2022-09-15 19:47:31',NULL,36,'许昌电气职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2806,0,NULL,'2022-09-15 19:47:31',NULL,36,'许昌职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2807,0,NULL,'2022-09-15 19:47:31',NULL,36,'许昌陶瓷职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2808,0,NULL,'2022-09-15 19:47:31',NULL,36,'豫章师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2809,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州交通职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2810,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州健康职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2811,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州农业职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2812,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州医科大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2813,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州医科大学神奇民族医药学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2814,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州商学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2815,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州城市职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2816,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2817,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州大学明德学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2818,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州大学科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2819,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州工业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2820,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州工商职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2821,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州工程应用技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2822,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州工程职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2823,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州工贸职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2824,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2825,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州师范大学求是学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2826,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2827,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州应用技术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2828,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州建设职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2829,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州护理职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2830,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州民族大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2831,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州民族大学人文科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2832,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州水利水电职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2833,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州理工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2834,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州电力职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2835,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州电子信息职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2836,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州电子商务职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2837,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州电子科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2838,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州盛华职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2839,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州经贸职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2840,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2841,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州航天职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2842,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州航空职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2843,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州装备制造职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2844,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州警察学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2845,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州财经大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2846,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州财经大学商务学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2847,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州轻工职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2848,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵州食品工程职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2849,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵阳中医学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2850,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵阳中医学院时珍学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2851,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵阳学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2852,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵阳幼儿师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2853,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵阳护理职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2854,0,NULL,'2022-09-15 19:47:31',NULL,36,'贵阳职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2855,0,NULL,'2022-09-15 19:47:31',NULL,36,'贺州学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2856,0,NULL,'2022-09-15 19:47:31',NULL,36,'赣南医学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2857,0,NULL,'2022-09-15 19:47:31',NULL,36,'赣南卫生健康职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2858,0,NULL,'2022-09-15 19:47:31',NULL,36,'赣南师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2859,0,NULL,'2022-09-15 19:47:31',NULL,36,'赣南师范大学科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2860,0,NULL,'2022-09-15 19:47:31',NULL,36,'赣州师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2861,0,NULL,'2022-09-15 19:47:31',NULL,36,'赣西科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2862,0,NULL,'2022-09-15 19:47:31',NULL,36,'赤峰学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2863,0,NULL,'2022-09-15 19:47:31',NULL,36,'赤峰工业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2864,0,NULL,'2022-09-15 19:47:31',NULL,36,'赤峰职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2865,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽东学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2866,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁中医药大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2867,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁中医药大学杏林学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2868,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁传媒学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2869,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁体育运动职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2870,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁何氏医学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2871,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁农业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2872,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁冶金职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2873,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁医药职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2874,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁商贸职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2875,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁地质工程职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2876,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁城市建设职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2877,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2878,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁对外经贸学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2879,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁工业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2880,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁工程技术大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2881,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁工程职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2882,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2883,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁师范大学海华学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2884,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁广告职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2885,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁建筑职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2886,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁政法职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2887,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁机电职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2888,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁林业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2889,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁民族师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2890,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁水利职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2891,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁特殊教育师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2892,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁现代服务职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2893,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁理工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2894,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁理工职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2895,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁省交通高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2896,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁石化职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2897,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁石油化工大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2898,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁石油化工大学顺华能源学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2899,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁科技大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2900,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2901,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁税务高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2902,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁经济职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2903,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2904,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁装备制造职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2905,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁警察学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2906,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁财贸学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2907,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁轨道交通职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2908,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁轻工职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2909,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁金融职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2910,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽宁铁道职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2911,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽河石油职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2912,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽源职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2913,0,NULL,'2022-09-15 19:47:31',NULL,36,'辽阳职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2914,0,NULL,'2022-09-15 19:47:31',NULL,36,'达州职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2915,0,NULL,'2022-09-15 19:47:31',NULL,36,'运城学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2916,0,NULL,'2022-09-15 19:47:31',NULL,36,'运城师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2917,0,NULL,'2022-09-15 19:47:31',NULL,36,'运城幼儿师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2918,0,NULL,'2022-09-15 19:47:31',NULL,36,'运城护理职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2919,0,NULL,'2022-09-15 19:47:31',NULL,36,'运城职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2920,0,NULL,'2022-09-15 19:47:31',NULL,36,'连云港师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2921,0,NULL,'2022-09-15 19:47:31',NULL,36,'连云港职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2922,0,NULL,'2022-09-15 19:47:31',NULL,36,'通化师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2923,0,NULL,'2022-09-15 19:47:31',NULL,36,'通辽职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2924,0,NULL,'2022-09-15 19:47:31',NULL,36,'遵义医学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2925,0,NULL,'2022-09-15 19:47:31',NULL,36,'遵义医学院医学与科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2926,0,NULL,'2022-09-15 19:47:31',NULL,36,'遵义医药高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2927,0,NULL,'2022-09-15 19:47:31',NULL,36,'遵义师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2928,0,NULL,'2022-09-15 19:47:31',NULL,36,'遵义职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2929,0,NULL,'2022-09-15 19:47:31',NULL,36,'邢台医学高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2930,0,NULL,'2022-09-15 19:47:31',NULL,36,'邢台学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2931,0,NULL,'2022-09-15 19:47:31',NULL,36,'邢台职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2932,0,NULL,'2022-09-15 19:47:31',NULL,36,'邯郸学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2933,0,NULL,'2022-09-15 19:47:31',NULL,36,'邯郸职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2934,0,NULL,'2022-09-15 19:47:31',NULL,36,'邵阳学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2935,0,NULL,'2022-09-15 19:47:31',NULL,36,'邵阳职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2936,0,NULL,'2022-09-15 19:47:31',NULL,36,'郑州信息工程职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2937,0,NULL,'2022-09-15 19:47:31',NULL,36,'郑州信息科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2938,0,NULL,'2022-09-15 19:47:31',NULL,36,'郑州升达经贸管理学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2939,0,NULL,'2022-09-15 19:47:31',NULL,36,'郑州商贸旅游职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2940,0,NULL,'2022-09-15 19:47:31',NULL,36,'郑州城市职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2941,0,NULL,'2022-09-15 19:47:31',NULL,36,'郑州大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2942,0,NULL,'2022-09-15 19:47:31',NULL,36,'郑州工业安全职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2943,0,NULL,'2022-09-15 19:47:31',NULL,36,'郑州工业应用技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2944,0,NULL,'2022-09-15 19:47:31',NULL,36,'郑州工商学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2945,0,NULL,'2022-09-15 19:47:31',NULL,36,'郑州工程技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2946,0,NULL,'2022-09-15 19:47:31',NULL,36,'郑州师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2947,0,NULL,'2022-09-15 19:47:31',NULL,36,'郑州幼儿师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2948,0,NULL,'2022-09-15 19:47:31',NULL,36,'郑州成功财经学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2949,0,NULL,'2022-09-15 19:47:31',NULL,36,'郑州旅游职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2950,0,NULL,'2022-09-15 19:47:31',NULL,36,'郑州澍青医学高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2951,0,NULL,'2022-09-15 19:47:31',NULL,36,'郑州理工职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2952,0,NULL,'2022-09-15 19:47:31',NULL,36,'郑州电力职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2953,0,NULL,'2022-09-15 19:47:31',NULL,36,'郑州电力高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2954,0,NULL,'2022-09-15 19:47:31',NULL,36,'郑州电子信息职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2955,0,NULL,'2022-09-15 19:47:31',NULL,36,'郑州科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2956,0,NULL,'2022-09-15 19:47:31',NULL,36,'郑州职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2957,0,NULL,'2022-09-15 19:47:31',NULL,36,'郑州航空工业管理学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2958,0,NULL,'2022-09-15 19:47:31',NULL,36,'郑州财税金融职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2959,0,NULL,'2022-09-15 19:47:31',NULL,36,'郑州财经学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2960,0,NULL,'2022-09-15 19:47:31',NULL,36,'郑州轻工业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2961,0,NULL,'2022-09-15 19:47:31',NULL,36,'郑州铁路职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2962,0,NULL,'2022-09-15 19:47:31',NULL,36,'郑州黄河护理职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2963,0,NULL,'2022-09-15 19:47:31',NULL,36,'郴州职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2964,0,NULL,'2022-09-15 19:47:31',NULL,36,'鄂东职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2965,0,NULL,'2022-09-15 19:47:31',NULL,36,'鄂尔多斯应用技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2966,0,NULL,'2022-09-15 19:47:31',NULL,36,'鄂尔多斯生态环境职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2967,0,NULL,'2022-09-15 19:47:31',NULL,36,'鄂尔多斯职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2968,0,NULL,'2022-09-15 19:47:31',NULL,36,'鄂州职业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2969,0,NULL,'2022-09-15 19:47:31',NULL,36,'酒泉职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2970,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆三峡医药高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2971,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆三峡学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2972,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆三峡职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2973,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆交通大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2974,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆交通职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2975,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆人文科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2976,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆传媒职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2977,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆信息技术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2978,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆公共运输职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2979,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆化工职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2980,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆医科大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2981,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆医药高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2982,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆商务职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2983,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆城市管理职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2984,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆城市职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2985,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2986,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆大学城市科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2987,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆安全技术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2988,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆工业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2989,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆工商大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2990,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆工商大学派斯学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2991,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆工商大学融智学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2992,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆工商职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2993,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆工程学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2994,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆工程职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2995,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆工贸职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2996,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2997,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆师范大学涉外商贸学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2998,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆幼儿师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(2999,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆应用技术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3000,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆建筑工程职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3001,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆房地产职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3002,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆护理职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3003,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆文化艺术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3004,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆文理学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3005,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆旅游职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3006,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆机电职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3007,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆水利电力职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3008,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆海联职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3009,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆理工大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3010,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆电信职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3011,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆电力高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3012,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆电子工程职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3013,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆电讯职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3014,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆科创职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3015,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3016,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3017,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆第二师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3018,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆经贸职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3019,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆能源职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3020,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆航天职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3021,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆艺术工程职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3022,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆警察学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3023,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆财经职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3024,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆资源与环境保护职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3025,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆轻工职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3026,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆邮电大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3027,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆邮电大学移通学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3028,0,NULL,'2022-09-15 19:47:31',NULL,36,'重庆青年职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3029,0,NULL,'2022-09-15 19:47:31',NULL,36,'金华职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3030,0,NULL,'2022-09-15 19:47:31',NULL,36,'金山职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3031,0,NULL,'2022-09-15 19:47:31',NULL,36,'金肯职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3032,0,NULL,'2022-09-15 19:47:31',NULL,36,'金陵科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3033,0,NULL,'2022-09-15 19:47:31',NULL,36,'钟山职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3034,0,NULL,'2022-09-15 19:47:31',NULL,36,'钦州学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3035,0,NULL,'2022-09-15 19:47:31',NULL,36,'铁岭卫生职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3036,0,NULL,'2022-09-15 19:47:31',NULL,36,'铁岭师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3037,0,NULL,'2022-09-15 19:47:31',NULL,36,'铁道警察学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3038,0,NULL,'2022-09-15 19:47:31',NULL,36,'铜仁学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3039,0,NULL,'2022-09-15 19:47:31',NULL,36,'铜仁幼儿师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3040,0,NULL,'2022-09-15 19:47:31',NULL,36,'铜仁职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3041,0,NULL,'2022-09-15 19:47:31',NULL,36,'铜川职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3042,0,NULL,'2022-09-15 19:47:31',NULL,36,'铜陵学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3043,0,NULL,'2022-09-15 19:47:31',NULL,36,'铜陵职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3044,0,NULL,'2022-09-15 19:47:31',NULL,36,'银川能源学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3045,0,NULL,'2022-09-15 19:47:31',NULL,36,'锡林郭勒职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3046,0,NULL,'2022-09-15 19:47:31',NULL,36,'锦州医科大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3047,0,NULL,'2022-09-15 19:47:31',NULL,36,'锦州医科大学医疗学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3048,0,NULL,'2022-09-15 19:47:31',NULL,36,'锦州师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3049,0,NULL,'2022-09-15 19:47:31',NULL,36,'镇江市高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3050,0,NULL,'2022-09-15 19:47:31',NULL,36,'长垣烹饪职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3051,0,NULL,'2022-09-15 19:47:31',NULL,36,'长安大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3052,0,NULL,'2022-09-15 19:47:31',NULL,36,'长安大学兴华学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3053,0,NULL,'2022-09-15 19:47:31',NULL,36,'长春东方职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3054,0,NULL,'2022-09-15 19:47:31',NULL,36,'长春中医药大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3055,0,NULL,'2022-09-15 19:47:31',NULL,36,'长春信息技术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3056,0,NULL,'2022-09-15 19:47:31',NULL,36,'长春健康职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3057,0,NULL,'2022-09-15 19:47:31',NULL,36,'长春光华学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3058,0,NULL,'2022-09-15 19:47:31',NULL,36,'长春医学高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3059,0,NULL,'2022-09-15 19:47:31',NULL,36,'长春大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3060,0,NULL,'2022-09-15 19:47:31',NULL,36,'长春大学旅游学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3061,0,NULL,'2022-09-15 19:47:31',NULL,36,'长春工业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3062,0,NULL,'2022-09-15 19:47:31',NULL,36,'长春工业大学人文信息学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3063,0,NULL,'2022-09-15 19:47:31',NULL,36,'长春工程学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3064,0,NULL,'2022-09-15 19:47:31',NULL,36,'长春师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3065,0,NULL,'2022-09-15 19:47:31',NULL,36,'长春师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3066,0,NULL,'2022-09-15 19:47:31',NULL,36,'长春建筑学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3067,0,NULL,'2022-09-15 19:47:31',NULL,36,'长春汽车工业高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3068,0,NULL,'2022-09-15 19:47:31',NULL,36,'长春理工大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3069,0,NULL,'2022-09-15 19:47:31',NULL,36,'长春理工大学光电信息学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3070,0,NULL,'2022-09-15 19:47:31',NULL,36,'长春科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3071,0,NULL,'2022-09-15 19:47:31',NULL,36,'长春职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3072,0,NULL,'2022-09-15 19:47:31',NULL,36,'长春财经学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3073,0,NULL,'2022-09-15 19:47:31',NULL,36,'长春金融高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3074,0,NULL,'2022-09-15 19:47:31',NULL,36,'长江大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3075,0,NULL,'2022-09-15 19:47:31',NULL,36,'长江大学工程技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3076,0,NULL,'2022-09-15 19:47:31',NULL,36,'长江大学文理学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3077,0,NULL,'2022-09-15 19:47:31',NULL,36,'长江工程职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3078,0,NULL,'2022-09-15 19:47:31',NULL,36,'长江师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3079,0,NULL,'2022-09-15 19:47:31',NULL,36,'长江职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3080,0,NULL,'2022-09-15 19:47:31',NULL,36,'长江艺术工程职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3081,0,NULL,'2022-09-15 19:47:31',NULL,36,'长沙医学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3082,0,NULL,'2022-09-15 19:47:31',NULL,36,'长沙南方职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3083,0,NULL,'2022-09-15 19:47:31',NULL,36,'长沙卫生职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3084,0,NULL,'2022-09-15 19:47:31',NULL,36,'长沙商贸旅游职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3085,0,NULL,'2022-09-15 19:47:31',NULL,36,'长沙学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3086,0,NULL,'2022-09-15 19:47:31',NULL,36,'长沙师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3087,0,NULL,'2022-09-15 19:47:31',NULL,36,'长沙民政职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3088,0,NULL,'2022-09-15 19:47:31',NULL,36,'长沙环境保护职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3089,0,NULL,'2022-09-15 19:47:31',NULL,36,'长沙理工大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3090,0,NULL,'2022-09-15 19:47:31',NULL,36,'长沙理工大学城南学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3091,0,NULL,'2022-09-15 19:47:31',NULL,36,'长沙电力职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3092,0,NULL,'2022-09-15 19:47:31',NULL,36,'长沙职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3093,0,NULL,'2022-09-15 19:47:31',NULL,36,'长沙航空职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3094,0,NULL,'2022-09-15 19:47:31',NULL,36,'长治医学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3095,0,NULL,'2022-09-15 19:47:31',NULL,36,'长治学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3096,0,NULL,'2022-09-15 19:47:31',NULL,36,'长治职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3097,0,NULL,'2022-09-15 19:47:31',NULL,36,'长白山职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3098,0,NULL,'2022-09-15 19:47:31',NULL,36,'闽北职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3099,0,NULL,'2022-09-15 19:47:31',NULL,36,'闽南师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3100,0,NULL,'2022-09-15 19:47:31',NULL,36,'闽南理工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3101,0,NULL,'2022-09-15 19:47:31',NULL,36,'闽江学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3102,0,NULL,'2022-09-15 19:47:31',NULL,36,'闽江师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3103,0,NULL,'2022-09-15 19:47:31',NULL,36,'闽西职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3104,0,NULL,'2022-09-15 19:47:31',NULL,36,'阜新高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3105,0,NULL,'2022-09-15 19:47:31',NULL,36,'阜阳师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3106,0,NULL,'2022-09-15 19:47:31',NULL,36,'阜阳师范学院信息工程学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3107,0,NULL,'2022-09-15 19:47:31',NULL,36,'阜阳幼儿师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3108,0,NULL,'2022-09-15 19:47:31',NULL,36,'阜阳科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3109,0,NULL,'2022-09-15 19:47:31',NULL,36,'阜阳职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3110,0,NULL,'2022-09-15 19:47:31',NULL,36,'防灾科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3111,0,NULL,'2022-09-15 19:47:31',NULL,36,'阳光学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3112,0,NULL,'2022-09-15 19:47:31',NULL,36,'阳江职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3113,0,NULL,'2022-09-15 19:47:31',NULL,36,'阳泉师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3114,0,NULL,'2022-09-15 19:47:31',NULL,36,'阳泉职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3115,0,NULL,'2022-09-15 19:47:31',NULL,36,'阿克苏职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3116,0,NULL,'2022-09-15 19:47:31',NULL,36,'阿坝师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3117,0,NULL,'2022-09-15 19:47:31',NULL,36,'阿拉善职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3118,0,NULL,'2022-09-15 19:47:31',NULL,36,'陇东学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3119,0,NULL,'2022-09-15 19:47:31',NULL,36,'陇南师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3120,0,NULL,'2022-09-15 19:47:31',NULL,36,'陕西中医药大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3121,0,NULL,'2022-09-15 19:47:31',NULL,36,'陕西交通职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3122,0,NULL,'2022-09-15 19:47:31',NULL,36,'陕西国防工业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3123,0,NULL,'2022-09-15 19:47:31',NULL,36,'陕西国际商贸学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3124,0,NULL,'2022-09-15 19:47:31',NULL,36,'陕西学前师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3125,0,NULL,'2022-09-15 19:47:31',NULL,36,'陕西工业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3126,0,NULL,'2022-09-15 19:47:31',NULL,36,'陕西工商职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3127,0,NULL,'2022-09-15 19:47:31',NULL,36,'陕西师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3128,0,NULL,'2022-09-15 19:47:31',NULL,36,'陕西旅游烹饪职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3129,0,NULL,'2022-09-15 19:47:31',NULL,36,'陕西服装工程学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3130,0,NULL,'2022-09-15 19:47:31',NULL,36,'陕西机电职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3131,0,NULL,'2022-09-15 19:47:31',NULL,36,'陕西理工大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3132,0,NULL,'2022-09-15 19:47:31',NULL,36,'陕西电子信息职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3133,0,NULL,'2022-09-15 19:47:31',NULL,36,'陕西电子科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3134,0,NULL,'2022-09-15 19:47:31',NULL,36,'陕西科技大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3135,0,NULL,'2022-09-15 19:47:31',NULL,36,'陕西科技大学镐京学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3136,0,NULL,'2022-09-15 19:47:31',NULL,36,'陕西经济管理职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3137,0,NULL,'2022-09-15 19:47:31',NULL,36,'陕西职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3138,0,NULL,'2022-09-15 19:47:31',NULL,36,'陕西能源职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3139,0,NULL,'2022-09-15 19:47:31',NULL,36,'陕西航空职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3140,0,NULL,'2022-09-15 19:47:31',NULL,36,'陕西艺术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3141,0,NULL,'2022-09-15 19:47:31',NULL,36,'陕西警官职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3142,0,NULL,'2022-09-15 19:47:31',NULL,36,'陕西财经职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3143,0,NULL,'2022-09-15 19:47:31',NULL,36,'陕西邮电职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3144,0,NULL,'2022-09-15 19:47:31',NULL,36,'陕西铁路工程职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3145,0,NULL,'2022-09-15 19:47:31',NULL,36,'陕西青年职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3146,0,NULL,'2022-09-15 19:47:31',NULL,36,'随州职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3147,0,NULL,'2022-09-15 19:47:31',NULL,36,'雅安职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3148,0,NULL,'2022-09-15 19:47:31',NULL,36,'集宁师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3149,0,NULL,'2022-09-15 19:47:31',NULL,36,'集美大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3150,0,NULL,'2022-09-15 19:47:31',NULL,36,'集美大学诚毅学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3151,0,NULL,'2022-09-15 19:47:31',NULL,36,'青岛农业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3152,0,NULL,'2022-09-15 19:47:31',NULL,36,'青岛农业大学海都学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3153,0,NULL,'2022-09-15 19:47:31',NULL,36,'青岛大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3154,0,NULL,'2022-09-15 19:47:31',NULL,36,'青岛工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3155,0,NULL,'2022-09-15 19:47:31',NULL,36,'青岛恒星科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3156,0,NULL,'2022-09-15 19:47:31',NULL,36,'青岛求实职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3157,0,NULL,'2022-09-15 19:47:31',NULL,36,'青岛港湾职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3158,0,NULL,'2022-09-15 19:47:31',NULL,36,'青岛滨海学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3159,0,NULL,'2022-09-15 19:47:31',NULL,36,'青岛理工大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3160,0,NULL,'2022-09-15 19:47:31',NULL,36,'青岛理工大学琴岛学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3161,0,NULL,'2022-09-15 19:47:31',NULL,36,'青岛科技大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3162,0,NULL,'2022-09-15 19:47:31',NULL,36,'青岛职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3163,0,NULL,'2022-09-15 19:47:31',NULL,36,'青岛远洋船员职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3164,0,NULL,'2022-09-15 19:47:31',NULL,36,'青岛酒店管理职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3165,0,NULL,'2022-09-15 19:47:31',NULL,36,'青岛飞洋职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3166,0,NULL,'2022-09-15 19:47:31',NULL,36,'青岛黄海学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3167,0,NULL,'2022-09-15 19:47:31',NULL,36,'青海交通职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3168,0,NULL,'2022-09-15 19:47:31',NULL,36,'青海卫生职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3169,0,NULL,'2022-09-15 19:47:31',NULL,36,'青海大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3170,0,NULL,'2022-09-15 19:47:31',NULL,36,'青海大学昆仑学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3171,0,NULL,'2022-09-15 19:47:31',NULL,36,'青海师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3172,0,NULL,'2022-09-15 19:47:31',NULL,36,'青海建筑职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3173,0,NULL,'2022-09-15 19:47:31',NULL,36,'青海柴达木职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3174,0,NULL,'2022-09-15 19:47:31',NULL,36,'青海民族大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3175,0,NULL,'2022-09-15 19:47:31',NULL,36,'青海畜牧兽医职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3176,0,NULL,'2022-09-15 19:47:31',NULL,36,'青海警官职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3177,0,NULL,'2022-09-15 19:47:31',NULL,36,'青海高等职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3178,0,NULL,'2022-09-15 19:47:31',NULL,36,'鞍山师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3179,0,NULL,'2022-09-15 19:47:31',NULL,36,'韩山师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3180,0,NULL,'2022-09-15 19:47:31',NULL,36,'韶关学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3181,0,NULL,'2022-09-15 19:47:31',NULL,36,'顺德职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3182,0,NULL,'2022-09-15 19:47:31',NULL,36,'首都体育学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3183,0,NULL,'2022-09-15 19:47:31',NULL,36,'首都医科大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3184,0,NULL,'2022-09-15 19:47:31',NULL,36,'首都师范大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3185,0,NULL,'2022-09-15 19:47:31',NULL,36,'首都师范大学科德学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3186,0,NULL,'2022-09-15 19:47:31',NULL,36,'首都经济贸易大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3187,0,NULL,'2022-09-15 19:47:31',NULL,36,'首钢工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3188,0,NULL,'2022-09-15 19:47:31',NULL,36,'香港中文大学（深圳）','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3189,0,NULL,'2022-09-15 19:47:31',NULL,36,'马鞍山师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3190,0,NULL,'2022-09-15 19:47:31',NULL,36,'马鞍山职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3191,0,NULL,'2022-09-15 19:47:31',NULL,36,'驻马店幼儿师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3192,0,NULL,'2022-09-15 19:47:31',NULL,36,'鲁东大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3193,0,NULL,'2022-09-15 19:47:31',NULL,36,'鲁迅美术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3194,0,NULL,'2022-09-15 19:47:31',NULL,36,'鹤壁汽车工程职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3195,0,NULL,'2022-09-15 19:47:31',NULL,36,'鹤壁职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3196,0,NULL,'2022-09-15 19:47:31',NULL,36,'鹤壁能源化工职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3197,0,NULL,'2022-09-15 19:47:31',NULL,36,'鹤岗师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3198,0,NULL,'2022-09-15 19:47:31',NULL,36,'鹰潭职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3199,0,NULL,'2022-09-15 19:47:31',NULL,36,'黄冈师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3200,0,NULL,'2022-09-15 19:47:31',NULL,36,'黄冈科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3201,0,NULL,'2022-09-15 19:47:31',NULL,36,'黄冈职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3202,0,NULL,'2022-09-15 19:47:31',NULL,36,'黄山学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3203,0,NULL,'2022-09-15 19:47:31',NULL,36,'黄山职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3204,0,NULL,'2022-09-15 19:47:31',NULL,36,'黄河交通学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3205,0,NULL,'2022-09-15 19:47:31',NULL,36,'黄河水利职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3206,0,NULL,'2022-09-15 19:47:31',NULL,36,'黄河科技学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3207,0,NULL,'2022-09-15 19:47:31',NULL,36,'黄淮学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3208,0,NULL,'2022-09-15 19:47:31',NULL,36,'黎明职业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3209,0,NULL,'2022-09-15 19:47:31',NULL,36,'黑河学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3210,0,NULL,'2022-09-15 19:47:31',NULL,36,'黑龙江三江美术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3211,0,NULL,'2022-09-15 19:47:31',NULL,36,'黑龙江东方学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3212,0,NULL,'2022-09-15 19:47:31',NULL,36,'黑龙江中医药大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3213,0,NULL,'2022-09-15 19:47:31',NULL,36,'黑龙江交通职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3214,0,NULL,'2022-09-15 19:47:31',NULL,36,'黑龙江信息技术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3215,0,NULL,'2022-09-15 19:47:31',NULL,36,'黑龙江八一农垦大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3216,0,NULL,'2022-09-15 19:47:31',NULL,36,'黑龙江公安警官职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3217,0,NULL,'2022-09-15 19:47:31',NULL,36,'黑龙江农业工程职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3218,0,NULL,'2022-09-15 19:47:31',NULL,36,'黑龙江农业经济职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3219,0,NULL,'2022-09-15 19:47:31',NULL,36,'黑龙江农业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3220,0,NULL,'2022-09-15 19:47:31',NULL,36,'黑龙江农垦科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3221,0,NULL,'2022-09-15 19:47:31',NULL,36,'黑龙江农垦职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3222,0,NULL,'2022-09-15 19:47:31',NULL,36,'黑龙江冰雪体育职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3223,0,NULL,'2022-09-15 19:47:31',NULL,36,'黑龙江司法警官职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3224,0,NULL,'2022-09-15 19:47:31',NULL,36,'黑龙江商业职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3225,0,NULL,'2022-09-15 19:47:31',NULL,36,'黑龙江外国语学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3226,0,NULL,'2022-09-15 19:47:31',NULL,36,'黑龙江大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3227,0,NULL,'2022-09-15 19:47:31',NULL,36,'黑龙江工业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3228,0,NULL,'2022-09-15 19:47:31',NULL,36,'黑龙江工商学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3229,0,NULL,'2022-09-15 19:47:31',NULL,36,'黑龙江工程学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3230,0,NULL,'2022-09-15 19:47:31',NULL,36,'黑龙江工程学院昆仑旅游学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3231,0,NULL,'2022-09-15 19:47:31',NULL,36,'黑龙江幼儿师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3232,0,NULL,'2022-09-15 19:47:31',NULL,36,'黑龙江建筑职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3233,0,NULL,'2022-09-15 19:47:31',NULL,36,'黑龙江护理高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3234,0,NULL,'2022-09-15 19:47:31',NULL,36,'黑龙江旅游职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3235,0,NULL,'2022-09-15 19:47:31',NULL,36,'黑龙江林业职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3236,0,NULL,'2022-09-15 19:47:31',NULL,36,'黑龙江民族职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3237,0,NULL,'2022-09-15 19:47:31',NULL,36,'黑龙江生态工程职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3238,0,NULL,'2022-09-15 19:47:31',NULL,36,'黑龙江生物科技职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3239,0,NULL,'2022-09-15 19:47:31',NULL,36,'黑龙江科技大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3240,0,NULL,'2022-09-15 19:47:31',NULL,36,'黑龙江粮食职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3241,0,NULL,'2022-09-15 19:47:31',NULL,36,'黑龙江职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3242,0,NULL,'2022-09-15 19:47:31',NULL,36,'黑龙江能源职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3243,0,NULL,'2022-09-15 19:47:31',NULL,36,'黑龙江艺术职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3244,0,NULL,'2022-09-15 19:47:31',NULL,36,'黑龙江财经学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3245,0,NULL,'2022-09-15 19:47:31',NULL,36,'黔东南民族职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3246,0,NULL,'2022-09-15 19:47:31',NULL,36,'黔南民族医学高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3247,0,NULL,'2022-09-15 19:47:31',NULL,36,'黔南民族师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3248,0,NULL,'2022-09-15 19:47:31',NULL,36,'黔南民族幼儿师范高等专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3249,0,NULL,'2022-09-15 19:47:31',NULL,36,'黔南民族职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3250,0,NULL,'2022-09-15 19:47:31',NULL,36,'黔西南民族职业技术学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3251,0,NULL,'2022-09-15 19:47:31',NULL,36,'齐鲁医药学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3252,0,NULL,'2022-09-15 19:47:31',NULL,36,'齐鲁工业大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3253,0,NULL,'2022-09-15 19:47:31',NULL,36,'齐鲁师范学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3254,0,NULL,'2022-09-15 19:47:31',NULL,36,'齐鲁理工学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3255,0,NULL,'2022-09-15 19:47:31',NULL,36,'齐齐哈尔医学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3256,0,NULL,'2022-09-15 19:47:31',NULL,36,'齐齐哈尔大学','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3257,0,NULL,'2022-09-15 19:47:31',NULL,36,'齐齐哈尔工程学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3258,0,NULL,'2022-09-15 19:47:31',NULL,36,'齐齐哈尔理工职业学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3259,0,NULL,'2022-09-15 19:47:31',NULL,36,'齐齐哈尔高等师范专科学校','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),

(3260,0,NULL,'2022-09-15 19:47:31',NULL,36,'龙岩学院','正常',0,'2022-09-15 19:48:19',NULL,NULL,NULL,NULL,NULL,'https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/qclogo.jpg!avatar','全部',NULL,'','all',NULL,0),


