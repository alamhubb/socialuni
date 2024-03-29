/*!40101 SET NAMES utf8 */;
/*!40101 SET SQL_MODE=''*/;

DROP TABLE IF EXISTS `s_community_district`;

CREATE TABLE `s_community_district` (
                                        `id` int(11) NOT NULL AUTO_INCREMENT,
                                        `ad_code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                        `ad_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                        `city_code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                        `city_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                        `count` int(11) DEFAULT NULL,
                                        `district_code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                        `district_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                        `parent_ad_code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                        `province_code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                        `province_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                        `status` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                        `talk_count` int(11) DEFAULT NULL,
                                        PRIMARY KEY (`id`),
                                        UNIQUE KEY `UKntmcv2rxrlxjec1eprvih7rnf` (`ad_code`),
                                        KEY `IDXaxpf7rmdqlmlflehf4nmjg5hs` (`count`),
                                        KEY `IDXlwsfvurm01eolghtfo2twvhf8` (`talk_count`),
                                        KEY `IDXfm45wpdyr5uj7gbmp0k4i7qj2` (`status`),
                                        KEY `IDXmkjjv73cg30sx9117wda502ai` (`province_code`),
                                        KEY `IDX54qlmhae05yymndu57voqhsw6` (`city_code`),
                                        KEY `IDXapcqvl4gjbjdaau7p4mwtceln` (`district_code`),
                                        KEY `IDXwe1gkjhvjxkxkcg3mbv8gvqk` (`parent_ad_code`)
) ENGINE=InnoDB AUTO_INCREMENT=3264 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `s_community_district` */
insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1,'100000','中国','1','',5400689,'1','',NULL,'100000','中国','正常',125278);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2,'110000','北京市','1','',6505,'1','','100000','110000','北京市','正常',681);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3,'110101','东城区','1','',209,'110101','东城区','110000','110000','北京市','正常',18);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (4,'110102','西城区','1','',183,'110102','西城区','110000','110000','北京市','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (5,'110105','朝阳区','1','',573,'110105','朝阳区','110000','110000','北京市','正常',75);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (6,'110106','丰台区','1','',522,'110106','丰台区','110000','110000','北京市','正常',115);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (7,'110107','石景山区','1','',82,'110107','石景山区','110000','110000','北京市','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (8,'110108','海淀区','1','',386,'110108','海淀区','110000','110000','北京市','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (9,'110109','门头沟区','1','',19,'110109','门头沟区','110000','110000','北京市','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (10,'110111','房山区','1','',168,'110111','房山区','110000','110000','北京市','正常',62);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (11,'110112','通州区','1','',598,'110112','通州区','110000','110000','北京市','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (12,'110113','顺义区','1','',226,'110113','顺义区','110000','110000','北京市','正常',27);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (13,'110114','昌平区','1','',141,'110114','昌平区','110000','110000','北京市','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (14,'110115','大兴区','1','',310,'110115','大兴区','110000','110000','北京市','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (15,'110116','怀柔区','1','',76,'110116','怀柔区','110000','110000','北京市','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (16,'110117','平谷区','1','',32,'110117','平谷区','110000','110000','北京市','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (17,'110118','密云区','1','',106,'110118','密云区','110000','110000','北京市','正常',49);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (18,'110119','延庆区','1','',35,'110119','延庆区','110000','110000','北京市','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (19,'120000','天津市','1','',2814,'1','','100000','120000','天津市','正常',279);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (20,'120101','和平区','1','',55,'120101','和平区','120000','120000','天津市','正常',47);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (21,'120102','河东区','1','',47,'120102','河东区','120000','120000','天津市','正常',16);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (22,'120103','河西区','1','',82,'120103','河西区','120000','120000','天津市','正常',32);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (23,'120104','南开区','1','',74,'120104','南开区','120000','120000','天津市','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (24,'120105','河北区','1','',119,'120105','河北区','120000','120000','天津市','正常',35);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (25,'120106','红桥区','1','',107,'120106','红桥区','120000','120000','天津市','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (26,'120110','东丽区','1','',168,'120110','东丽区','120000','120000','天津市','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (27,'120111','西青区','1','',124,'120111','西青区','120000','120000','天津市','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (28,'120112','津南区','1','',101,'120112','津南区','120000','120000','天津市','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (29,'120113','北辰区','1','',258,'120113','北辰区','120000','120000','天津市','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (30,'120114','武清区','1','',306,'120114','武清区','120000','120000','天津市','正常',26);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (31,'120115','宝坻区','1','',162,'120115','宝坻区','120000','120000','天津市','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (32,'120116','滨海新区','1','',327,'120116','滨海新区','120000','120000','天津市','正常',29);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (33,'120117','宁河区','1','',19,'120117','宁河区','120000','120000','天津市','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (34,'120118','静海区','1','',148,'120118','静海区','120000','120000','天津市','正常',40);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (35,'120119','蓟州区','1','',129,'120119','蓟州区','120000','120000','天津市','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (36,'130000','河北省','1','',44474,'','','100000','130000','河北省','正常',1718);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (37,'130100','石家庄市','130100','石家庄市',3839,'1','','130000','130000','河北省','正常',226);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (38,'130102','长安区','130100','石家庄市',336,'130102','长安区','130100','130000','河北省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (39,'130104','桥西区','130100','石家庄市',268,'130104','桥西区','130100','130000','河北省','正常',15);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (40,'130105','新华区','130100','石家庄市',313,'130105','新华区','130100','130000','河北省','正常',16);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (41,'130107','井陉矿区','130100','石家庄市',73,'130107','井陉矿区','130100','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (42,'130108','裕华区','130100','石家庄市',442,'130108','裕华区','130100','130000','河北省','正常',34);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (43,'130109','藁城区','130100','石家庄市',75,'130109','藁城区','130100','130000','河北省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (44,'130110','鹿泉区','130100','石家庄市',277,'130110','鹿泉区','130100','130000','河北省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (45,'130111','栾城区','130100','石家庄市',75,'130111','栾城区','130100','130000','河北省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (46,'130121','井陉县','130100','石家庄市',105,'130121','井陉县','130100','130000','河北省','正常',28);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (47,'130123','正定县','130100','石家庄市',277,'130123','正定县','130100','130000','河北省','正常',34);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (48,'130125','行唐县','130100','石家庄市',35,'130125','行唐县','130100','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (49,'130126','灵寿县','130100','石家庄市',113,'130126','灵寿县','130100','130000','河北省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (50,'130127','高邑县','130100','石家庄市',22,'130127','高邑县','130100','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (51,'130128','深泽县','130100','石家庄市',26,'130128','深泽县','130100','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (52,'130129','赞皇县','130100','石家庄市',26,'130129','赞皇县','130100','130000','河北省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (53,'130130','无极县','130100','石家庄市',10,'130130','无极县','130100','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (54,'130131','平山县','130100','石家庄市',68,'130131','平山县','130100','130000','河北省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (55,'130132','元氏县','130100','石家庄市',50,'130132','元氏县','130100','130000','河北省','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (56,'130133','赵县','130100','石家庄市',156,'130133','赵县','130100','130000','河北省','正常',22);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (57,'130181','辛集市','130100','石家庄市',60,'130181','辛集市','130100','130000','河北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (58,'130183','晋州市','130100','石家庄市',78,'130183','晋州市','130100','130000','河北省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (59,'130184','新乐市','130100','石家庄市',66,'130184','新乐市','130100','130000','河北省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (60,'130200','唐山市','130200','唐山市',2181,'1','','130000','130000','河北省','正常',94);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (61,'130202','路南区','130200','唐山市',70,'130202','路南区','130200','130000','河北省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (62,'130203','路北区','130200','唐山市',608,'130203','路北区','130200','130000','河北省','正常',37);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (63,'130204','古冶区','130200','唐山市',73,'130204','古冶区','130200','130000','河北省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (64,'130205','开平区','130200','唐山市',39,'130205','开平区','130200','130000','河北省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (65,'130207','丰南区','130200','唐山市',98,'130207','丰南区','130200','130000','河北省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (66,'130208','丰润区','130200','唐山市',64,'130208','丰润区','130200','130000','河北省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (67,'130209','曹妃甸区','130200','唐山市',472,'130209','曹妃甸区','130200','130000','河北省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (68,'130284','滦州市','130200','唐山市',96,'130284','滦州市','130200','130000','河北省','正常',16);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (69,'130224','滦南县','130200','唐山市',30,'130224','滦南县','130200','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (70,'130225','乐亭县','130200','唐山市',27,'130225','乐亭县','130200','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (71,'130227','迁西县','130200','唐山市',15,'130227','迁西县','130200','130000','河北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (72,'130229','玉田县','130200','唐山市',85,'130229','玉田县','130200','130000','河北省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (73,'130281','遵化市','130200','唐山市',98,'130281','遵化市','130200','130000','河北省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (74,'130283','迁安市','130200','唐山市',130,'130283','迁安市','130200','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (75,'130300','秦皇岛市','130300','秦皇岛市',377,'1','','130000','130000','河北省','正常',33);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (76,'130302','海港区','130300','秦皇岛市',144,'130302','海港区','130300','130000','河北省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (77,'130303','山海关区','130300','秦皇岛市',34,'130303','山海关区','130300','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (78,'130304','北戴河区','130300','秦皇岛市',26,'130304','北戴河区','130300','130000','河北省','正常',22);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (79,'130306','抚宁区','130300','秦皇岛市',0,'130306','抚宁区','130300','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (80,'130321','青龙满族自治县','130300','秦皇岛市',124,'130321','青龙满族自治县','130300','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (81,'130322','昌黎县','130300','秦皇岛市',5,'130322','昌黎县','130300','130000','河北省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (82,'130324','卢龙县','130300','秦皇岛市',3,'130324','卢龙县','130300','130000','河北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (83,'130400','邯郸市','130400','邯郸市',3101,'1','','130000','130000','河北省','正常',253);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (84,'130402','邯山区','130400','邯郸市',288,'130402','邯山区','130400','130000','河北省','正常',16);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (85,'130403','丛台区','130400','邯郸市',274,'130403','丛台区','130400','130000','河北省','正常',23);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (86,'130404','复兴区','130400','邯郸市',109,'130404','复兴区','130400','130000','河北省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (87,'130406','峰峰矿区','130400','邯郸市',33,'130406','峰峰矿区','130400','130000','河北省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (88,'130407','肥乡区','130400','邯郸市',122,'130407','肥乡区','130400','130000','河北省','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (89,'130408','永年区','130400','邯郸市',176,'130408','永年区','130400','130000','河北省','正常',29);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (90,'130423','临漳县','130400','邯郸市',117,'130423','临漳县','130400','130000','河北省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (91,'130424','成安县','130400','邯郸市',74,'130424','成安县','130400','130000','河北省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (92,'130425','大名县','130400','邯郸市',70,'130425','大名县','130400','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (93,'130426','涉县','130400','邯郸市',155,'130426','涉县','130400','130000','河北省','正常',14);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (94,'130427','磁县','130400','邯郸市',25,'130427','磁县','130400','130000','河北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (95,'130430','邱县','130400','邯郸市',56,'130430','邱县','130400','130000','河北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (96,'130431','鸡泽县','130400','邯郸市',50,'130431','鸡泽县','130400','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (97,'130432','广平县','130400','邯郸市',58,'130432','广平县','130400','130000','河北省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (98,'130433','馆陶县','130400','邯郸市',27,'130433','馆陶县','130400','130000','河北省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (99,'130434','魏县','130400','邯郸市',124,'130434','魏县','130400','130000','河北省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (100,'130435','曲周县','130400','邯郸市',49,'130435','曲周县','130400','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (101,'130481','武安市','130400','邯郸市',194,'130481','武安市','130400','130000','河北省','正常',76);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (102,'130500','邢台市','130500','邢台市',1372,'1','','130000','130000','河北省','正常',61);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (103,'130502','桥东区','130500','邢台市',66,'130502','桥东区','130500','130000','河北省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (104,'130503','桥西区','130500','邢台市',132,'130503','桥西区','130500','130000','河北省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (105,'130521','邢台县','130500','邢台市',144,'130521','邢台县','130500','130000','河北省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (106,'130522','临城县','130500','邢台市',0,'130522','临城县','130500','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (107,'130523','内丘县','130500','邢台市',54,'130523','内丘县','130500','130000','河北省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (108,'130524','柏乡县','130500','邢台市',0,'130524','柏乡县','130500','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (109,'130525','隆尧县','130500','邢台市',42,'130525','隆尧县','130500','130000','河北省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (110,'130526','任县','130500','邢台市',20,'130526','任县','130500','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (111,'130527','南和县','130500','邢台市',23,'130527','南和县','130500','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (112,'130528','宁晋县','130500','邢台市',56,'130528','宁晋县','130500','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (113,'130529','巨鹿县','130500','邢台市',29,'130529','巨鹿县','130500','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (114,'130530','新河县','130500','邢台市',6,'130530','新河县','130500','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (115,'130531','广宗县','130500','邢台市',9,'130531','广宗县','130500','130000','河北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (116,'130532','平乡县','130500','邢台市',58,'130532','平乡县','130500','130000','河北省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (117,'130533','威县','130500','邢台市',35,'130533','威县','130500','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (118,'130534','清河县','130500','邢台市',88,'130534','清河县','130500','130000','河北省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (119,'130535','临西县','130500','邢台市',21,'130535','临西县','130500','130000','河北省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (120,'130581','南宫市','130500','邢台市',52,'130581','南宫市','130500','130000','河北省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (121,'130582','沙河市','130500','邢台市',281,'130582','沙河市','130500','130000','河北省','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (122,'130600','保定市','130600','保定市',1905,'1','','130000','130000','河北省','正常',200);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (123,'130602','竞秀区','130600','保定市',139,'130602','竞秀区','130600','130000','河北省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (124,'130606','莲池区','130600','保定市',98,'130606','莲池区','130600','130000','河北省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (125,'130607','满城区','130600','保定市',26,'130607','满城区','130600','130000','河北省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (126,'130608','清苑区','130600','保定市',10,'130608','清苑区','130600','130000','河北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (127,'130609','徐水区','130600','保定市',109,'130609','徐水区','130600','130000','河北省','正常',56);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (128,'130623','涞水县','130600','保定市',85,'130623','涞水县','130600','130000','河北省','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (129,'130624','阜平县','130600','保定市',18,'130624','阜平县','130600','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (130,'130626','定兴县','130600','保定市',77,'130626','定兴县','130600','130000','河北省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (131,'130627','唐县','130600','保定市',36,'130627','唐县','130600','130000','河北省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (132,'130628','高阳县','130600','保定市',119,'130628','高阳县','130600','130000','河北省','正常',37);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (133,'130629','容城县','130600','保定市',5,'130629','容城县','130600','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (134,'130630','涞源县','130600','保定市',22,'130630','涞源县','130600','130000','河北省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (135,'130631','望都县','130600','保定市',5,'130631','望都县','130600','130000','河北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (136,'130632','安新县','130600','保定市',21,'130632','安新县','130600','130000','河北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (137,'130633','易县','130600','保定市',45,'130633','易县','130600','130000','河北省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (138,'130634','曲阳县','130600','保定市',103,'130634','曲阳县','130600','130000','河北省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (139,'130635','蠡县','130600','保定市',39,'130635','蠡县','130600','130000','河北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (140,'130636','顺平县','130600','保定市',8,'130636','顺平县','130600','130000','河北省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (141,'130637','博野县','130600','保定市',12,'130637','博野县','130600','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (142,'130638','雄县','130600','保定市',97,'130638','雄县','130600','130000','河北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (143,'130681','涿州市','130600','保定市',39,'130681','涿州市','130600','130000','河北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (144,'130682','定州市','130600','保定市',109,'130682','定州市','130600','130000','河北省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (145,'130683','安国市','130600','保定市',282,'130683','安国市','130600','130000','河北省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (146,'130684','高碑店市','130600','保定市',31,'130684','高碑店市','130600','130000','河北省','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (147,'130700','张家口市','130700','张家口市',838,'1','','130000','130000','河北省','正常',56);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (148,'130702','桥东区','130700','张家口市',124,'130702','桥东区','130700','130000','河北省','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (149,'130703','桥西区','130700','张家口市',63,'130703','桥西区','130700','130000','河北省','正常',19);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (150,'130705','宣化区','130700','张家口市',57,'130705','宣化区','130700','130000','河北省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (151,'130706','下花园区','130700','张家口市',11,'130706','下花园区','130700','130000','河北省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (152,'130708','万全区','130700','张家口市',12,'130708','万全区','130700','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (153,'130709','崇礼区','130700','张家口市',19,'130709','崇礼区','130700','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (154,'130722','张北县','130700','张家口市',63,'130722','张北县','130700','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (155,'130723','康保县','130700','张家口市',19,'130723','康保县','130700','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (156,'130724','沽源县','130700','张家口市',42,'130724','沽源县','130700','130000','河北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (157,'130725','尚义县','130700','张家口市',4,'130725','尚义县','130700','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (158,'130726','蔚县','130700','张家口市',96,'130726','蔚县','130700','130000','河北省','正常',14);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (159,'130727','阳原县','130700','张家口市',90,'130727','阳原县','130700','130000','河北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (160,'130728','怀安县','130700','张家口市',1,'130728','怀安县','130700','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (161,'130730','怀来县','130700','张家口市',40,'130730','怀来县','130700','130000','河北省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (162,'130731','涿鹿县','130700','张家口市',9,'130731','涿鹿县','130700','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (163,'130732','赤城县','130700','张家口市',62,'130732','赤城县','130700','130000','河北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (164,'130800','承德市','130800','承德市',421,'1','','130000','130000','河北省','正常',46);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (165,'130802','双桥区','130800','承德市',57,'130802','双桥区','130800','130000','河北省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (166,'130803','双滦区','130800','承德市',18,'130803','双滦区','130800','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (167,'130804','鹰手营子矿区','130800','承德市',0,'130804','鹰手营子矿区','130800','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (168,'130821','承德县','130800','承德市',42,'130821','承德县','130800','130000','河北省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (169,'130822','兴隆县','130800','承德市',27,'130822','兴隆县','130800','130000','河北省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (170,'130824','滦平县','130800','承德市',20,'130824','滦平县','130800','130000','河北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (171,'130825','隆化县','130800','承德市',71,'130825','隆化县','130800','130000','河北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (172,'130826','丰宁满族自治县','130800','承德市',61,'130826','丰宁满族自治县','130800','130000','河北省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (173,'130827','宽城满族自治县','130800','承德市',52,'130827','宽城满族自治县','130800','130000','河北省','正常',25);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (174,'130828','围场满族蒙古族自治县','130800','承德市',20,'130828','围场满族蒙古族自治县','130800','130000','河北省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (175,'130881','平泉市','130800','承德市',24,'130881','平泉市','130800','130000','河北省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (176,'130900','沧州市','130900','沧州市',1102,'1','','130000','130000','河北省','正常',99);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (177,'130902','新华区','130900','沧州市',53,'130902','新华区','130900','130000','河北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (178,'130903','运河区','130900','沧州市',86,'130903','运河区','130900','130000','河北省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (179,'130921','沧县','130900','沧州市',20,'130921','沧县','130900','130000','河北省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (180,'130922','青县','130900','沧州市',32,'130922','青县','130900','130000','河北省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (181,'130923','东光县','130900','沧州市',21,'130923','东光县','130900','130000','河北省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (182,'130924','海兴县','130900','沧州市',1,'130924','海兴县','130900','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (183,'130925','盐山县','130900','沧州市',79,'130925','盐山县','130900','130000','河北省','正常',16);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (184,'130926','肃宁县','130900','沧州市',28,'130926','肃宁县','130900','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (185,'130927','南皮县','130900','沧州市',72,'130927','南皮县','130900','130000','河北省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (186,'130928','吴桥县','130900','沧州市',21,'130928','吴桥县','130900','130000','河北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (187,'130929','献县','130900','沧州市',64,'130929','献县','130900','130000','河北省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (188,'130930','孟村回族自治县','130900','沧州市',9,'130930','孟村回族自治县','130900','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (189,'130981','泊头市','130900','沧州市',58,'130981','泊头市','130900','130000','河北省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (190,'130982','任丘市','130900','沧州市',158,'130982','任丘市','130900','130000','河北省','正常',22);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (191,'130983','黄骅市','130900','沧州市',129,'130983','黄骅市','130900','130000','河北省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (192,'130984','河间市','130900','沧州市',6,'130984','河间市','130900','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (193,'131000','廊坊市','131000','廊坊市',1088,'1','','130000','130000','河北省','正常',93);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (194,'131002','安次区','131000','廊坊市',69,'131002','安次区','131000','130000','河北省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (195,'131003','广阳区','131000','廊坊市',259,'131003','广阳区','131000','130000','河北省','正常',32);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (196,'131022','固安县','131000','廊坊市',21,'131022','固安县','131000','130000','河北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (197,'131023','永清县','131000','廊坊市',44,'131023','永清县','131000','130000','河北省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (198,'131024','香河县','131000','廊坊市',32,'131024','香河县','131000','130000','河北省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (199,'131025','大城县','131000','廊坊市',47,'131025','大城县','131000','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (200,'131026','文安县','131000','廊坊市',76,'131026','文安县','131000','130000','河北省','正常',14);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (201,'131028','大厂回族自治县','131000','廊坊市',4,'131028','大厂回族自治县','131000','130000','河北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (202,'131081','霸州市','131000','廊坊市',150,'131081','霸州市','131000','130000','河北省','正常',17);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (203,'131082','三河市','131000','廊坊市',191,'131082','三河市','131000','130000','河北省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (204,'131100','衡水市','131100','衡水市',1041,'1','','130000','130000','河北省','正常',95);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (205,'131102','桃城区','131100','衡水市',256,'131102','桃城区','131100','130000','河北省','正常',14);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (206,'131103','冀州区','131100','衡水市',148,'131103','冀州区','131100','130000','河北省','正常',14);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (207,'131121','枣强县','131100','衡水市',119,'131121','枣强县','131100','130000','河北省','正常',12);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (208,'131122','武邑县','131100','衡水市',45,'131122','武邑县','131100','130000','河北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (209,'131123','武强县','131100','衡水市',1,'131123','武强县','131100','130000','河北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (210,'131124','饶阳县','131100','衡水市',38,'131124','饶阳县','131100','130000','河北省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (211,'131125','安平县','131100','衡水市',52,'131125','安平县','131100','130000','河北省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (212,'131126','故城县','131100','衡水市',66,'131126','故城县','131100','130000','河北省','正常',34);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (213,'131127','景县','131100','衡水市',25,'131127','景县','131100','130000','河北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (214,'131128','阜城县','131100','衡水市',68,'131128','阜城县','131100','130000','河北省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (215,'131182','深州市','131100','衡水市',60,'131182','深州市','131100','130000','河北省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (216,'140000','山西省','1','',10308,'','','100000','140000','山西省','正常',581);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (217,'140100','太原市','140100','太原市',1443,'1','','140000','140000','山西省','正常',36);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (218,'140105','小店区','140100','太原市',355,'140105','小店区','140100','140000','山西省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (219,'140106','迎泽区','140100','太原市',80,'140106','迎泽区','140100','140000','山西省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (220,'140107','杏花岭区','140100','太原市',80,'140107','杏花岭区','140100','140000','山西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (221,'140108','尖草坪区','140100','太原市',94,'140108','尖草坪区','140100','140000','山西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (222,'140109','万柏林区','140100','太原市',258,'140109','万柏林区','140100','140000','山西省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (223,'140110','晋源区','140100','太原市',87,'140110','晋源区','140100','140000','山西省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (224,'140121','清徐县','140100','太原市',72,'140121','清徐县','140100','140000','山西省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (225,'140122','阳曲县','140100','太原市',25,'140122','阳曲县','140100','140000','山西省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (226,'140123','娄烦县','140100','太原市',1,'140123','娄烦县','140100','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (227,'140181','古交市','140100','太原市',34,'140181','古交市','140100','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (228,'140200','大同市','140200','大同市',738,'1','','140000','140000','山西省','正常',74);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (229,'140213','平城区','140200','大同市',313,'140213','平城区','140200','140000','山西省','正常',43);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (230,'140214','云冈区','140200','大同市',164,'140214','云冈区','140200','140000','山西省','正常',22);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (231,'140212','新荣区','140200','大同市',0,'140212','新荣区','140200','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (232,'140221','阳高县','140200','大同市',22,'140221','阳高县','140200','140000','山西省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (233,'140222','天镇县','140200','大同市',23,'140222','天镇县','140200','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (234,'140223','广灵县','140200','大同市',1,'140223','广灵县','140200','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (235,'140224','灵丘县','140200','大同市',14,'140224','灵丘县','140200','140000','山西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (236,'140225','浑源县','140200','大同市',24,'140225','浑源县','140200','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (237,'140226','左云县','140200','大同市',10,'140226','左云县','140200','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (238,'140215','云州区','140200','大同市',17,'140215','云州区','140200','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (239,'140300','阳泉市','140300','阳泉市',415,'1','','140000','140000','山西省','正常',23);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (240,'140302','城区','140300','阳泉市',26,'140302','城区','140300','140000','山西省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (241,'140303','矿区','140300','阳泉市',57,'140303','矿区','140300','140000','山西省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (242,'140311','郊区','140300','阳泉市',120,'140311','郊区','140300','140000','山西省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (243,'140321','平定县','140300','阳泉市',106,'140321','平定县','140300','140000','山西省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (244,'140322','盂县','140300','阳泉市',40,'140322','盂县','140300','140000','山西省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (245,'140400','长治市','140400','长治市',749,'1','','140000','140000','山西省','正常',95);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (246,'140403','潞州区','140400','长治市',153,'140403','潞州区','140400','140000','山西省','正常',39);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (247,'140404','上党区','140400','长治市',59,'140404','上党区','140400','140000','山西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (248,'140423','襄垣县','140400','长治市',15,'140423','襄垣县','140400','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (249,'140405','屯留区','140400','长治市',20,'140405','屯留区','140400','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (250,'140425','平顺县','140400','长治市',10,'140425','平顺县','140400','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (251,'140426','黎城县','140400','长治市',28,'140426','黎城县','140400','140000','山西省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (252,'140427','壶关县','140400','长治市',37,'140427','壶关县','140400','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (253,'140428','长子县','140400','长治市',5,'140428','长子县','140400','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (254,'140429','武乡县','140400','长治市',11,'140429','武乡县','140400','140000','山西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (255,'140430','沁县','140400','长治市',75,'140430','沁县','140400','140000','山西省','正常',12);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (256,'140431','沁源县','140400','长治市',38,'140431','沁源县','140400','140000','山西省','正常',26);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (257,'140406','潞城区','140400','长治市',4,'140406','潞城区','140400','140000','山西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (258,'140500','晋城市','140500','晋城市',466,'1','','140000','140000','山西省','正常',12);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (259,'140502','城区','140500','晋城市',148,'140502','城区','140500','140000','山西省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (260,'140521','沁水县','140500','晋城市',0,'140521','沁水县','140500','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (261,'140522','阳城县','140500','晋城市',25,'140522','阳城县','140500','140000','山西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (262,'140524','陵川县','140500','晋城市',12,'140524','陵川县','140500','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (263,'140525','泽州县','140500','晋城市',59,'140525','泽州县','140500','140000','山西省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (264,'140581','高平市','140500','晋城市',33,'140581','高平市','140500','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (265,'140600','朔州市','140600','朔州市',473,'1','','140000','140000','山西省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (266,'140602','朔城区','140600','朔州市',209,'140602','朔城区','140600','140000','山西省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (267,'140603','平鲁区','140600','朔州市',55,'140603','平鲁区','140600','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (268,'140621','山阴县','140600','朔州市',36,'140621','山阴县','140600','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (269,'140622','应县','140600','朔州市',25,'140622','应县','140600','140000','山西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (270,'140623','右玉县','140600','朔州市',25,'140623','右玉县','140600','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (271,'140681','怀仁市','140600','朔州市',47,'140681','怀仁市','140600','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (272,'140700','晋中市','140700','晋中市',979,'1','','140000','140000','山西省','正常',34);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (273,'140702','榆次区','140700','晋中市',279,'140702','榆次区','140700','140000','山西省','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (274,'140721','榆社县','140700','晋中市',15,'140721','榆社县','140700','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (275,'140722','左权县','140700','晋中市',32,'140722','左权县','140700','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (276,'140723','和顺县','140700','晋中市',18,'140723','和顺县','140700','140000','山西省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (277,'140724','昔阳县','140700','晋中市',68,'140724','昔阳县','140700','140000','山西省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (278,'140725','寿阳县','140700','晋中市',199,'140725','寿阳县','140700','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (279,'140726','太谷县','140700','晋中市',39,'140726','太谷县','140700','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (280,'140727','祁县','140700','晋中市',61,'140727','祁县','140700','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (281,'140728','平遥县','140700','晋中市',49,'140728','平遥县','140700','140000','山西省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (282,'140729','灵石县','140700','晋中市',3,'140729','灵石县','140700','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (283,'140781','介休市','140700','晋中市',109,'140781','介休市','140700','140000','山西省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (284,'140800','运城市','140800','运城市',621,'1','','140000','140000','山西省','正常',36);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (285,'140802','盐湖区','140800','运城市',154,'140802','盐湖区','140800','140000','山西省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (286,'140821','临猗县','140800','运城市',69,'140821','临猗县','140800','140000','山西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (287,'140822','万荣县','140800','运城市',1,'140822','万荣县','140800','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (288,'140823','闻喜县','140800','运城市',4,'140823','闻喜县','140800','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (289,'140824','稷山县','140800','运城市',27,'140824','稷山县','140800','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (290,'140825','新绛县','140800','运城市',10,'140825','新绛县','140800','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (291,'140826','绛县','140800','运城市',3,'140826','绛县','140800','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (292,'140827','垣曲县','140800','运城市',6,'140827','垣曲县','140800','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (293,'140828','夏县','140800','运城市',27,'140828','夏县','140800','140000','山西省','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (294,'140829','平陆县','140800','运城市',5,'140829','平陆县','140800','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (295,'140830','芮城县','140800','运城市',36,'140830','芮城县','140800','140000','山西省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (296,'140881','永济市','140800','运城市',12,'140881','永济市','140800','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (297,'140882','河津市','140800','运城市',32,'140882','河津市','140800','140000','山西省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (298,'140900','忻州市','140900','忻州市',466,'1','','140000','140000','山西省','正常',88);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (299,'140902','忻府区','140900','忻州市',16,'140902','忻府区','140900','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (300,'140921','定襄县','140900','忻州市',80,'140921','定襄县','140900','140000','山西省','正常',56);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (301,'140922','五台县','140900','忻州市',4,'140922','五台县','140900','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (302,'140923','代县','140900','忻州市',13,'140923','代县','140900','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (303,'140924','繁峙县','140900','忻州市',68,'140924','繁峙县','140900','140000','山西省','正常',16);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (304,'140925','宁武县','140900','忻州市',35,'140925','宁武县','140900','140000','山西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (305,'140926','静乐县','140900','忻州市',44,'140926','静乐县','140900','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (306,'140927','神池县','140900','忻州市',3,'140927','神池县','140900','140000','山西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (307,'140928','五寨县','140900','忻州市',11,'140928','五寨县','140900','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (308,'140929','岢岚县','140900','忻州市',1,'140929','岢岚县','140900','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (309,'140930','河曲县','140900','忻州市',5,'140930','河曲县','140900','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (310,'140931','保德县','140900','忻州市',41,'140931','保德县','140900','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (311,'140932','偏关县','140900','忻州市',4,'140932','偏关县','140900','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (312,'140981','原平市','140900','忻州市',58,'140981','原平市','140900','140000','山西省','正常',14);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (313,'141000','临汾市','141000','临汾市',1309,'1','','140000','140000','山西省','正常',76);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (314,'141002','尧都区','141000','临汾市',430,'141002','尧都区','141000','140000','山西省','正常',41);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (315,'141021','曲沃县','141000','临汾市',1,'141021','曲沃县','141000','140000','山西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (316,'141022','翼城县','141000','临汾市',37,'141022','翼城县','141000','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (317,'141023','襄汾县','141000','临汾市',8,'141023','襄汾县','141000','140000','山西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (318,'141024','洪洞县','141000','临汾市',153,'141024','洪洞县','141000','140000','山西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (319,'141025','古县','141000','临汾市',0,'141025','古县','141000','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (320,'141026','安泽县','141000','临汾市',16,'141026','安泽县','141000','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (321,'141027','浮山县','141000','临汾市',15,'141027','浮山县','141000','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (322,'141028','吉县','141000','临汾市',30,'141028','吉县','141000','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (323,'141029','乡宁县','141000','临汾市',9,'141029','乡宁县','141000','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (324,'141030','大宁县','141000','临汾市',107,'141030','大宁县','141000','140000','山西省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (325,'141031','隰县','141000','临汾市',22,'141031','隰县','141000','140000','山西省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (326,'141032','永和县','141000','临汾市',6,'141032','永和县','141000','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (327,'141033','蒲县','141000','临汾市',65,'141033','蒲县','141000','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (328,'141034','汾西县','141000','临汾市',13,'141034','汾西县','141000','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (329,'141081','侯马市','141000','临汾市',18,'141081','侯马市','141000','140000','山西省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (330,'141082','霍州市','141000','临汾市',86,'141082','霍州市','141000','140000','山西省','正常',14);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (331,'141100','吕梁市','141100','吕梁市',655,'1','','140000','140000','山西省','正常',59);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (332,'141102','离石区','141100','吕梁市',191,'141102','离石区','141100','140000','山西省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (333,'141121','文水县','141100','吕梁市',25,'141121','文水县','141100','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (334,'141122','交城县','141100','吕梁市',15,'141122','交城县','141100','140000','山西省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (335,'141123','兴县','141100','吕梁市',0,'141123','兴县','141100','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (336,'141124','临县','141100','吕梁市',106,'141124','临县','141100','140000','山西省','正常',17);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (337,'141125','柳林县','141100','吕梁市',37,'141125','柳林县','141100','140000','山西省','正常',21);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (338,'141126','石楼县','141100','吕梁市',3,'141126','石楼县','141100','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (339,'141127','岚县','141100','吕梁市',8,'141127','岚县','141100','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (340,'141128','方山县','141100','吕梁市',1,'141128','方山县','141100','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (341,'141129','中阳县','141100','吕梁市',12,'141129','中阳县','141100','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (342,'141130','交口县','141100','吕梁市',16,'141130','交口县','141100','140000','山西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (343,'141181','孝义市','141100','吕梁市',48,'141181','孝义市','141100','140000','山西省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (344,'141182','汾阳市','141100','吕梁市',55,'141182','汾阳市','141100','140000','山西省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (345,'150000','内蒙古自治区','1','',3782,'','','100000','150000','内蒙古自治区','正常',173);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (346,'150100','呼和浩特市','150100','呼和浩特市',632,'1','','150000','150000','内蒙古自治区','正常',39);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (347,'150102','新城区','150100','呼和浩特市',73,'150102','新城区','150100','150000','内蒙古自治区','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (348,'150103','回民区','150100','呼和浩特市',47,'150103','回民区','150100','150000','内蒙古自治区','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (349,'150104','玉泉区','150100','呼和浩特市',112,'150104','玉泉区','150100','150000','内蒙古自治区','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (350,'150105','赛罕区','150100','呼和浩特市',240,'150105','赛罕区','150100','150000','内蒙古自治区','正常',20);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (351,'150121','土默特左旗','150100','呼和浩特市',5,'150121','土默特左旗','150100','150000','内蒙古自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (352,'150122','托克托县','150100','呼和浩特市',0,'150122','托克托县','150100','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (353,'150123','和林格尔县','150100','呼和浩特市',11,'150123','和林格尔县','150100','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (354,'150124','清水河县','150100','呼和浩特市',3,'150124','清水河县','150100','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (355,'150125','武川县','150100','呼和浩特市',1,'150125','武川县','150100','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (356,'150200','包头市','150200','包头市',297,'1','','150000','150000','内蒙古自治区','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (357,'150202','东河区','150200','包头市',61,'150202','东河区','150200','150000','内蒙古自治区','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (358,'150203','昆都仑区','150200','包头市',90,'150203','昆都仑区','150200','150000','内蒙古自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (359,'150204','青山区','150200','包头市',49,'150204','青山区','150200','150000','内蒙古自治区','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (360,'150205','石拐区','150200','包头市',0,'150205','石拐区','150200','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (361,'150206','白云鄂博矿区','150200','包头市',0,'150206','白云鄂博矿区','150200','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (362,'150207','九原区','150200','包头市',12,'150207','九原区','150200','150000','内蒙古自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (363,'150221','土默特右旗','150200','包头市',37,'150221','土默特右旗','150200','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (364,'150222','固阳县','150200','包头市',0,'150222','固阳县','150200','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (365,'150223','达尔罕茂明安联合旗','150200','包头市',0,'150223','达尔罕茂明安联合旗','150200','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (366,'150300','乌海市','150300','乌海市',78,'1','','150000','150000','内蒙古自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (367,'150302','海勃湾区','150300','乌海市',57,'150302','海勃湾区','150300','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (368,'150303','海南区','150300','乌海市',0,'150303','海南区','150300','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (369,'150304','乌达区','150300','乌海市',15,'150304','乌达区','150300','150000','内蒙古自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (370,'150400','赤峰市','150400','赤峰市',1101,'1','','150000','150000','内蒙古自治区','正常',51);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (371,'150402','红山区','150400','赤峰市',116,'150402','红山区','150400','150000','内蒙古自治区','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (372,'150403','元宝山区','150400','赤峰市',15,'150403','元宝山区','150400','150000','内蒙古自治区','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (373,'150404','松山区','150400','赤峰市',273,'150404','松山区','150400','150000','内蒙古自治区','正常',15);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (374,'150421','阿鲁科尔沁旗','150400','赤峰市',26,'150421','阿鲁科尔沁旗','150400','150000','内蒙古自治区','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (375,'150422','巴林左旗','150400','赤峰市',37,'150422','巴林左旗','150400','150000','内蒙古自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (376,'150423','巴林右旗','150400','赤峰市',12,'150423','巴林右旗','150400','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (377,'150424','林西县','150400','赤峰市',30,'150424','林西县','150400','150000','内蒙古自治区','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (378,'150425','克什克腾旗','150400','赤峰市',0,'150425','克什克腾旗','150400','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (379,'150426','翁牛特旗','150400','赤峰市',39,'150426','翁牛特旗','150400','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (380,'150428','喀喇沁旗','150400','赤峰市',125,'150428','喀喇沁旗','150400','150000','内蒙古自治区','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (381,'150429','宁城县','150400','赤峰市',29,'150429','宁城县','150400','150000','内蒙古自治区','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (382,'150430','敖汉旗','150400','赤峰市',45,'150430','敖汉旗','150400','150000','内蒙古自治区','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (383,'150500','通辽市','150500','通辽市',232,'1','','150000','150000','内蒙古自治区','正常',20);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (384,'150502','科尔沁区','150500','通辽市',76,'150502','科尔沁区','150500','150000','内蒙古自治区','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (385,'150521','科尔沁左翼中旗','150500','通辽市',3,'150521','科尔沁左翼中旗','150500','150000','内蒙古自治区','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (386,'150522','科尔沁左翼后旗','150500','通辽市',2,'150522','科尔沁左翼后旗','150500','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (387,'150523','开鲁县','150500','通辽市',29,'150523','开鲁县','150500','150000','内蒙古自治区','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (388,'150524','库伦旗','150500','通辽市',3,'150524','库伦旗','150500','150000','内蒙古自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (389,'150525','奈曼旗','150500','通辽市',34,'150525','奈曼旗','150500','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (390,'150526','扎鲁特旗','150500','通辽市',9,'150526','扎鲁特旗','150500','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (391,'150581','霍林郭勒市','150500','通辽市',23,'150581','霍林郭勒市','150500','150000','内蒙古自治区','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (392,'150600','鄂尔多斯市','150600','鄂尔多斯市',194,'1','','150000','150000','内蒙古自治区','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (393,'150602','东胜区','150600','鄂尔多斯市',71,'150602','东胜区','150600','150000','内蒙古自治区','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (394,'150603','康巴什区','150600','鄂尔多斯市',0,'150603','康巴什区','150600','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (395,'150621','达拉特旗','150600','鄂尔多斯市',33,'150621','达拉特旗','150600','150000','内蒙古自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (396,'150622','准格尔旗','150600','鄂尔多斯市',47,'150622','准格尔旗','150600','150000','内蒙古自治区','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (397,'150623','鄂托克前旗','150600','鄂尔多斯市',0,'150623','鄂托克前旗','150600','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (398,'150624','鄂托克旗','150600','鄂尔多斯市',2,'150624','鄂托克旗','150600','150000','内蒙古自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (399,'150625','杭锦旗','150600','鄂尔多斯市',0,'150625','杭锦旗','150600','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (400,'150626','乌审旗','150600','鄂尔多斯市',13,'150626','乌审旗','150600','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (401,'150627','伊金霍洛旗','150600','鄂尔多斯市',8,'150627','伊金霍洛旗','150600','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (402,'150700','呼伦贝尔市','150700','呼伦贝尔市',231,'1','','150000','150000','内蒙古自治区','正常',16);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (403,'150702','海拉尔区','150700','呼伦贝尔市',45,'150702','海拉尔区','150700','150000','内蒙古自治区','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (404,'150703','扎赉诺尔区','150700','呼伦贝尔市',25,'150703','扎赉诺尔区','150700','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (405,'150721','阿荣旗','150700','呼伦贝尔市',4,'150721','阿荣旗','150700','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (406,'150722','莫力达瓦达斡尔族自治旗','150700','呼伦贝尔市',45,'150722','莫力达瓦达斡尔族自治旗','150700','150000','内蒙古自治区','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (407,'150723','鄂伦春自治旗','150700','呼伦贝尔市',5,'150723','鄂伦春自治旗','150700','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (408,'150724','鄂温克族自治旗','150700','呼伦贝尔市',2,'150724','鄂温克族自治旗','150700','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (409,'150725','陈巴尔虎旗','150700','呼伦贝尔市',13,'150725','陈巴尔虎旗','150700','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (410,'150726','新巴尔虎左旗','150700','呼伦贝尔市',6,'150726','新巴尔虎左旗','150700','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (411,'150727','新巴尔虎右旗','150700','呼伦贝尔市',0,'150727','新巴尔虎右旗','150700','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (412,'150781','满洲里市','150700','呼伦贝尔市',13,'150781','满洲里市','150700','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (413,'150782','牙克石市','150700','呼伦贝尔市',0,'150782','牙克石市','150700','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (414,'150783','扎兰屯市','150700','呼伦贝尔市',43,'150783','扎兰屯市','150700','150000','内蒙古自治区','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (415,'150784','额尔古纳市','150700','呼伦贝尔市',8,'150784','额尔古纳市','150700','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (416,'150785','根河市','150700','呼伦贝尔市',0,'150785','根河市','150700','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (417,'150800','巴彦淖尔市','150800','巴彦淖尔市',195,'1','','150000','150000','内蒙古自治区','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (418,'150802','临河区','150800','巴彦淖尔市',81,'150802','临河区','150800','150000','内蒙古自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (419,'150821','五原县','150800','巴彦淖尔市',49,'150821','五原县','150800','150000','内蒙古自治区','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (420,'150822','磴口县','150800','巴彦淖尔市',3,'150822','磴口县','150800','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (421,'150823','乌拉特前旗','150800','巴彦淖尔市',12,'150823','乌拉特前旗','150800','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (422,'150824','乌拉特中旗','150800','巴彦淖尔市',3,'150824','乌拉特中旗','150800','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (423,'150825','乌拉特后旗','150800','巴彦淖尔市',6,'150825','乌拉特后旗','150800','150000','内蒙古自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (424,'150826','杭锦后旗','150800','巴彦淖尔市',29,'150826','杭锦后旗','150800','150000','内蒙古自治区','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (425,'150900','乌兰察布市','150900','乌兰察布市',248,'1','','150000','150000','内蒙古自治区','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (426,'150902','集宁区','150900','乌兰察布市',61,'150902','集宁区','150900','150000','内蒙古自治区','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (427,'150921','卓资县','150900','乌兰察布市',0,'150921','卓资县','150900','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (428,'150922','化德县','150900','乌兰察布市',17,'150922','化德县','150900','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (429,'150923','商都县','150900','乌兰察布市',113,'150923','商都县','150900','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (430,'150924','兴和县','150900','乌兰察布市',4,'150924','兴和县','150900','150000','内蒙古自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (431,'150925','凉城县','150900','乌兰察布市',0,'150925','凉城县','150900','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (432,'150926','察哈尔右翼前旗','150900','乌兰察布市',1,'150926','察哈尔右翼前旗','150900','150000','内蒙古自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (433,'150927','察哈尔右翼中旗','150900','乌兰察布市',0,'150927','察哈尔右翼中旗','150900','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (434,'150928','察哈尔右翼后旗','150900','乌兰察布市',7,'150928','察哈尔右翼后旗','150900','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (435,'150929','四子王旗','150900','乌兰察布市',2,'150929','四子王旗','150900','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (436,'150981','丰镇市','150900','乌兰察布市',26,'150981','丰镇市','150900','150000','内蒙古自治区','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (437,'152200','兴安盟','152200','兴安盟',146,'1','','150000','150000','内蒙古自治区','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (438,'152201','乌兰浩特市','152200','兴安盟',63,'152201','乌兰浩特市','152200','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (439,'152202','阿尔山市','152200','兴安盟',0,'152202','阿尔山市','152200','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (440,'152221','科尔沁右翼前旗','152200','兴安盟',6,'152221','科尔沁右翼前旗','152200','150000','内蒙古自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (441,'152222','科尔沁右翼中旗','152200','兴安盟',14,'152222','科尔沁右翼中旗','152200','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (442,'152223','扎赉特旗','152200','兴安盟',22,'152223','扎赉特旗','152200','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (443,'152224','突泉县','152200','兴安盟',25,'152224','突泉县','152200','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (444,'152500','锡林郭勒盟','152500','锡林郭勒盟',100,'1','','150000','150000','内蒙古自治区','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (445,'152501','二连浩特市','152500','锡林郭勒盟',10,'152501','二连浩特市','152500','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (446,'152502','锡林浩特市','152500','锡林郭勒盟',52,'152502','锡林浩特市','152500','150000','内蒙古自治区','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (447,'152522','阿巴嘎旗','152500','锡林郭勒盟',0,'152522','阿巴嘎旗','152500','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (448,'152523','苏尼特左旗','152500','锡林郭勒盟',0,'152523','苏尼特左旗','152500','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (449,'152524','苏尼特右旗','152500','锡林郭勒盟',4,'152524','苏尼特右旗','152500','150000','内蒙古自治区','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (450,'152525','东乌珠穆沁旗','152500','锡林郭勒盟',5,'152525','东乌珠穆沁旗','152500','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (451,'152526','西乌珠穆沁旗','152500','锡林郭勒盟',9,'152526','西乌珠穆沁旗','152500','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (452,'152527','太仆寺旗','152500','锡林郭勒盟',12,'152527','太仆寺旗','152500','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (453,'152528','镶黄旗','152500','锡林郭勒盟',0,'152528','镶黄旗','152500','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (454,'152529','正镶白旗','152500','锡林郭勒盟',0,'152529','正镶白旗','152500','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (455,'152530','正蓝旗','152500','锡林郭勒盟',0,'152530','正蓝旗','152500','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (456,'152531','多伦县','152500','锡林郭勒盟',0,'152531','多伦县','152500','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (457,'152900','阿拉善盟','152900','阿拉善盟',19,'1','','150000','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (458,'152921','阿拉善左旗','152900','阿拉善盟',10,'152921','阿拉善左旗','152900','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (459,'152922','阿拉善右旗','152900','阿拉善盟',3,'152922','阿拉善右旗','152900','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (460,'152923','额济纳旗','152900','阿拉善盟',0,'152923','额济纳旗','152900','150000','内蒙古自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (461,'210000','辽宁省','1','',9759,'','','100000','210000','辽宁省','正常',583);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (462,'210100','沈阳市','210100','沈阳市',2439,'1','','210000','210000','辽宁省','正常',105);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (463,'210102','和平区','210100','沈阳市',128,'210102','和平区','210100','210000','辽宁省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (464,'210103','沈河区','210100','沈阳市',84,'210103','沈河区','210100','210000','辽宁省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (465,'210104','大东区','210100','沈阳市',201,'210104','大东区','210100','210000','辽宁省','正常',12);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (466,'210105','皇姑区','210100','沈阳市',167,'210105','皇姑区','210100','210000','辽宁省','正常',19);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (467,'210106','铁西区','210100','沈阳市',441,'210106','铁西区','210100','210000','辽宁省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (468,'210111','苏家屯区','210100','沈阳市',60,'210111','苏家屯区','210100','210000','辽宁省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (469,'210112','浑南区','210100','沈阳市',147,'210112','浑南区','210100','210000','辽宁省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (470,'210113','沈北新区','210100','沈阳市',155,'210113','沈北新区','210100','210000','辽宁省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (471,'210114','于洪区','210100','沈阳市',187,'210114','于洪区','210100','210000','辽宁省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (472,'210115','辽中区','210100','沈阳市',85,'210115','辽中区','210100','210000','辽宁省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (473,'210123','康平县','210100','沈阳市',11,'210123','康平县','210100','210000','辽宁省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (474,'210124','法库县','210100','沈阳市',23,'210124','法库县','210100','210000','辽宁省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (475,'210181','新民市','210100','沈阳市',70,'210181','新民市','210100','210000','辽宁省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (476,'210200','大连市','210200','大连市',1383,'1','','210000','210000','辽宁省','正常',47);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (477,'210202','中山区','210200','大连市',64,'210202','中山区','210200','210000','辽宁省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (478,'210203','西岗区','210200','大连市',43,'210203','西岗区','210200','210000','辽宁省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (479,'210204','沙河口区','210200','大连市',51,'210204','沙河口区','210200','210000','辽宁省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (480,'210211','甘井子区','210200','大连市',527,'210211','甘井子区','210200','210000','辽宁省','正常',15);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (481,'210212','旅顺口区','210200','大连市',17,'210212','旅顺口区','210200','210000','辽宁省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (482,'210213','金州区','210200','大连市',220,'210213','金州区','210200','210000','辽宁省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (483,'210214','普兰店区','210200','大连市',10,'210214','普兰店区','210200','210000','辽宁省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (484,'210224','长海县','210200','大连市',0,'210224','长海县','210200','210000','辽宁省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (485,'210281','瓦房店市','210200','大连市',98,'210281','瓦房店市','210200','210000','辽宁省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (486,'210283','庄河市','210200','大连市',29,'210283','庄河市','210200','210000','辽宁省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (487,'210300','鞍山市','210300','鞍山市',672,'1','','210000','210000','辽宁省','正常',206);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (488,'210302','铁东区','210300','鞍山市',205,'210302','铁东区','210300','210000','辽宁省','正常',182);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (489,'210303','铁西区','210300','鞍山市',107,'210303','铁西区','210300','210000','辽宁省','正常',14);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (490,'210304','立山区','210300','鞍山市',80,'210304','立山区','210300','210000','辽宁省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (491,'210311','千山区','210300','鞍山市',0,'210311','千山区','210300','210000','辽宁省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (492,'210321','台安县','210300','鞍山市',21,'210321','台安县','210300','210000','辽宁省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (493,'210323','岫岩满族自治县','210300','鞍山市',19,'210323','岫岩满族自治县','210300','210000','辽宁省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (494,'210381','海城市','210300','鞍山市',166,'210381','海城市','210300','210000','辽宁省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (495,'210400','抚顺市','210400','抚顺市',307,'1','','210000','210000','辽宁省','正常',40);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (496,'210402','新抚区','210400','抚顺市',2,'210402','新抚区','210400','210000','辽宁省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (497,'210403','东洲区','210400','抚顺市',180,'210403','东洲区','210400','210000','辽宁省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (498,'210404','望花区','210400','抚顺市',44,'210404','望花区','210400','210000','辽宁省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (499,'210411','顺城区','210400','抚顺市',63,'210411','顺城区','210400','210000','辽宁省','正常',27);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (500,'210421','抚顺县','210400','抚顺市',0,'210421','抚顺县','210400','210000','辽宁省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (501,'210422','新宾满族自治县','210400','抚顺市',2,'210422','新宾满族自治县','210400','210000','辽宁省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (502,'210423','清原满族自治县','210400','抚顺市',9,'210423','清原满族自治县','210400','210000','辽宁省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (503,'210500','本溪市','210500','本溪市',116,'1','','210000','210000','辽宁省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (504,'210502','平山区','210500','本溪市',21,'210502','平山区','210500','210000','辽宁省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (505,'210503','溪湖区','210500','本溪市',24,'210503','溪湖区','210500','210000','辽宁省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (506,'210504','明山区','210500','本溪市',6,'210504','明山区','210500','210000','辽宁省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (507,'210505','南芬区','210500','本溪市',4,'210505','南芬区','210500','210000','辽宁省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (508,'210521','本溪满族自治县','210500','本溪市',25,'210521','本溪满族自治县','210500','210000','辽宁省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (509,'210522','桓仁满族自治县','210500','本溪市',23,'210522','桓仁满族自治县','210500','210000','辽宁省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (510,'210600','丹东市','210600','丹东市',276,'1','','210000','210000','辽宁省','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (511,'210602','元宝区','210600','丹东市',77,'210602','元宝区','210600','210000','辽宁省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (512,'210603','振兴区','210600','丹东市',30,'210603','振兴区','210600','210000','辽宁省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (513,'210604','振安区','210600','丹东市',8,'210604','振安区','210600','210000','辽宁省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (514,'210624','宽甸满族自治县','210600','丹东市',31,'210624','宽甸满族自治县','210600','210000','辽宁省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (515,'210681','东港市','210600','丹东市',72,'210681','东港市','210600','210000','辽宁省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (516,'210682','凤城市','210600','丹东市',38,'210682','凤城市','210600','210000','辽宁省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (517,'210700','锦州市','210700','锦州市',483,'1','','210000','210000','辽宁省','正常',14);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (518,'210702','古塔区','210700','锦州市',86,'210702','古塔区','210700','210000','辽宁省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (519,'210703','凌河区','210700','锦州市',24,'210703','凌河区','210700','210000','辽宁省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (520,'210711','太和区','210700','锦州市',39,'210711','太和区','210700','210000','辽宁省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (521,'210726','黑山县','210700','锦州市',16,'210726','黑山县','210700','210000','辽宁省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (522,'210727','义县','210700','锦州市',3,'210727','义县','210700','210000','辽宁省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (523,'210781','凌海市','210700','锦州市',99,'210781','凌海市','210700','210000','辽宁省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (524,'210782','北镇市','210700','锦州市',163,'210782','北镇市','210700','210000','辽宁省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (525,'210800','营口市','210800','营口市',436,'1','','210000','210000','辽宁省','正常',30);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (526,'210802','站前区','210800','营口市',28,'210802','站前区','210800','210000','辽宁省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (527,'210803','西市区','210800','营口市',22,'210803','西市区','210800','210000','辽宁省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (528,'210804','鲅鱼圈区','210800','营口市',151,'210804','鲅鱼圈区','210800','210000','辽宁省','正常',14);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (529,'210811','老边区','210800','营口市',18,'210811','老边区','210800','210000','辽宁省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (530,'210881','盖州市','210800','营口市',79,'210881','盖州市','210800','210000','辽宁省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (531,'210882','大石桥市','210800','营口市',26,'210882','大石桥市','210800','210000','辽宁省','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (532,'210900','阜新市','210900','阜新市',262,'1','','210000','210000','辽宁省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (533,'210902','海州区','210900','阜新市',15,'210902','海州区','210900','210000','辽宁省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (534,'210903','新邱区','210900','阜新市',84,'210903','新邱区','210900','210000','辽宁省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (535,'210904','太平区','210900','阜新市',17,'210904','太平区','210900','210000','辽宁省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (536,'210905','清河门区','210900','阜新市',3,'210905','清河门区','210900','210000','辽宁省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (537,'210911','细河区','210900','阜新市',45,'210911','细河区','210900','210000','辽宁省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (538,'210921','阜新蒙古族自治县','210900','阜新市',56,'210921','阜新蒙古族自治县','210900','210000','辽宁省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (539,'210922','彰武县','210900','阜新市',8,'210922','彰武县','210900','210000','辽宁省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (540,'211000','辽阳市','211000','辽阳市',428,'1','','210000','210000','辽宁省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (541,'211002','白塔区','211000','辽阳市',45,'211002','白塔区','211000','210000','辽宁省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (542,'211003','文圣区','211000','辽阳市',6,'211003','文圣区','211000','210000','辽宁省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (543,'211004','宏伟区','211000','辽阳市',47,'211004','宏伟区','211000','210000','辽宁省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (544,'211005','弓长岭区','211000','辽阳市',0,'211005','弓长岭区','211000','210000','辽宁省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (545,'211011','太子河区','211000','辽阳市',29,'211011','太子河区','211000','210000','辽宁省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (546,'211021','辽阳县','211000','辽阳市',12,'211021','辽阳县','211000','210000','辽宁省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (547,'211081','灯塔市','211000','辽阳市',214,'211081','灯塔市','211000','210000','辽宁省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (548,'211100','盘锦市','211100','盘锦市',370,'1','','210000','210000','辽宁省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (549,'211102','双台子区','211100','盘锦市',76,'211102','双台子区','211100','210000','辽宁省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (550,'211103','兴隆台区','211100','盘锦市',144,'211103','兴隆台区','211100','210000','辽宁省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (551,'211104','大洼区','211100','盘锦市',28,'211104','大洼区','211100','210000','辽宁省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (552,'211122','盘山县','211100','盘锦市',64,'211122','盘山县','211100','210000','辽宁省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (553,'211200','铁岭市','211200','铁岭市',237,'1','','210000','210000','辽宁省','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (554,'211202','银州区','211200','铁岭市',68,'211202','银州区','211200','210000','辽宁省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (555,'211204','清河区','211200','铁岭市',2,'211204','清河区','211200','210000','辽宁省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (556,'211221','铁岭县','211200','铁岭市',23,'211221','铁岭县','211200','210000','辽宁省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (557,'211223','西丰县','211200','铁岭市',6,'211223','西丰县','211200','210000','辽宁省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (558,'211224','昌图县','211200','铁岭市',14,'211224','昌图县','211200','210000','辽宁省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (559,'211281','调兵山市','211200','铁岭市',14,'211281','调兵山市','211200','210000','辽宁省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (560,'211282','开原市','211200','铁岭市',56,'211282','开原市','211200','210000','辽宁省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (561,'211300','朝阳市','211300','朝阳市',447,'1','','210000','210000','辽宁省','正常',58);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (562,'211302','双塔区','211300','朝阳市',149,'211302','双塔区','211300','210000','辽宁省','正常',31);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (563,'211303','龙城区','211300','朝阳市',23,'211303','龙城区','211300','210000','辽宁省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (564,'211321','朝阳县','211300','朝阳市',1,'211321','朝阳县','211300','210000','辽宁省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (565,'211322','建平县','211300','朝阳市',14,'211322','建平县','211300','210000','辽宁省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (566,'211324','喀喇沁左翼蒙古族自治县','211300','朝阳市',36,'211324','喀喇沁左翼蒙古族自治县','211300','210000','辽宁省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (567,'211381','北票市','211300','朝阳市',32,'211381','北票市','211300','210000','辽宁省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (568,'211382','凌源市','211300','朝阳市',73,'211382','凌源市','211300','210000','辽宁省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (569,'211400','葫芦岛市','211400','葫芦岛市',340,'1','','210000','210000','辽宁省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (570,'211402','连山区','211400','葫芦岛市',123,'211402','连山区','211400','210000','辽宁省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (571,'211403','龙港区','211400','葫芦岛市',44,'211403','龙港区','211400','210000','辽宁省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (572,'211404','南票区','211400','葫芦岛市',5,'211404','南票区','211400','210000','辽宁省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (573,'211421','绥中县','211400','葫芦岛市',72,'211421','绥中县','211400','210000','辽宁省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (574,'211422','建昌县','211400','葫芦岛市',29,'211422','建昌县','211400','210000','辽宁省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (575,'211481','兴城市','211400','葫芦岛市',25,'211481','兴城市','211400','210000','辽宁省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (576,'220000','吉林省','1','',5806,'','','100000','220000','吉林省','正常',290);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (577,'220100','长春市','220100','长春市',2416,'1','','220000','220000','吉林省','正常',114);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (578,'220102','南关区','220100','长春市',205,'220102','南关区','220100','220000','吉林省','正常',26);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (579,'220103','宽城区','220100','长春市',252,'220103','宽城区','220100','220000','吉林省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (580,'220104','朝阳区','220100','长春市',159,'220104','朝阳区','220100','220000','吉林省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (581,'220105','二道区','220100','长春市',201,'220105','二道区','220100','220000','吉林省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (582,'220106','绿园区','220100','长春市',162,'220106','绿园区','220100','220000','吉林省','正常',19);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (583,'220112','双阳区','220100','长春市',17,'220112','双阳区','220100','220000','吉林省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (584,'220113','九台区','220100','长春市',112,'220113','九台区','220100','220000','吉林省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (585,'220122','农安县','220100','长春市',89,'220122','农安县','220100','220000','吉林省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (586,'220182','榆树市','220100','长春市',128,'220182','榆树市','220100','220000','吉林省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (587,'220183','德惠市','220100','长春市',221,'220183','德惠市','220100','220000','吉林省','正常',25);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (588,'220200','吉林市','220200','吉林市',771,'1','','220000','220000','吉林省','正常',67);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (589,'220202','昌邑区','220200','吉林市',261,'220202','昌邑区','220200','220000','吉林省','正常',16);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (590,'220203','龙潭区','220200','吉林市',44,'220203','龙潭区','220200','220000','吉林省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (591,'220204','船营区','220200','吉林市',26,'220204','船营区','220200','220000','吉林省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (592,'220211','丰满区','220200','吉林市',33,'220211','丰满区','220200','220000','吉林省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (593,'220221','永吉县','220200','吉林市',8,'220221','永吉县','220200','220000','吉林省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (594,'220281','蛟河市','220200','吉林市',35,'220281','蛟河市','220200','220000','吉林省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (595,'220282','桦甸市','220200','吉林市',103,'220282','桦甸市','220200','220000','吉林省','正常',25);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (596,'220283','舒兰市','220200','吉林市',130,'220283','舒兰市','220200','220000','吉林省','正常',16);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (597,'220284','磐石市','220200','吉林市',12,'220284','磐石市','220200','220000','吉林省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (598,'220300','四平市','220300','四平市',334,'1','','220000','220000','吉林省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (599,'220302','铁西区','220300','四平市',42,'220302','铁西区','220300','220000','吉林省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (600,'220303','铁东区','220300','四平市',57,'220303','铁东区','220300','220000','吉林省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (601,'220322','梨树县','220300','四平市',17,'220322','梨树县','220300','220000','吉林省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (602,'220323','伊通满族自治县','220300','四平市',71,'220323','伊通满族自治县','220300','220000','吉林省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (603,'220381','公主岭市','220300','四平市',24,'220381','公主岭市','220300','220000','吉林省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (604,'220382','双辽市','220300','四平市',4,'220382','双辽市','220300','220000','吉林省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (605,'220400','辽源市','220400','辽源市',108,'1','','220000','220000','吉林省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (606,'220402','龙山区','220400','辽源市',39,'220402','龙山区','220400','220000','吉林省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (607,'220403','西安区','220400','辽源市',19,'220403','西安区','220400','220000','吉林省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (608,'220421','东丰县','220400','辽源市',29,'220421','东丰县','220400','220000','吉林省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (609,'220422','东辽县','220400','辽源市',1,'220422','东辽县','220400','220000','吉林省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (610,'220500','通化市','220500','通化市',155,'1','','220000','220000','吉林省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (611,'220502','东昌区','220500','通化市',17,'220502','东昌区','220500','220000','吉林省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (612,'220503','二道江区','220500','通化市',5,'220503','二道江区','220500','220000','吉林省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (613,'220521','通化县','220500','通化市',67,'220521','通化县','220500','220000','吉林省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (614,'220523','辉南县','220500','通化市',4,'220523','辉南县','220500','220000','吉林省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (615,'220524','柳河县','220500','通化市',1,'220524','柳河县','220500','220000','吉林省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (616,'220581','梅河口市','220500','通化市',36,'220581','梅河口市','220500','220000','吉林省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (617,'220582','集安市','220500','通化市',0,'220582','集安市','220500','220000','吉林省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (618,'220600','白山市','220600','白山市',107,'1','','220000','220000','吉林省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (619,'220602','浑江区','220600','白山市',24,'220602','浑江区','220600','220000','吉林省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (620,'220605','江源区','220600','白山市',18,'220605','江源区','220600','220000','吉林省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (621,'220621','抚松县','220600','白山市',5,'220621','抚松县','220600','220000','吉林省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (622,'220622','靖宇县','220600','白山市',7,'220622','靖宇县','220600','220000','吉林省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (623,'220623','长白朝鲜族自治县','220600','白山市',34,'220623','长白朝鲜族自治县','220600','220000','吉林省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (624,'220681','临江市','220600','白山市',4,'220681','临江市','220600','220000','吉林省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (625,'220700','松原市','220700','松原市',390,'1','','220000','220000','吉林省','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (626,'220702','宁江区','220700','松原市',230,'220702','宁江区','220700','220000','吉林省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (627,'220721','前郭尔罗斯蒙古族自治县','220700','松原市',12,'220721','前郭尔罗斯蒙古族自治县','220700','220000','吉林省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (628,'220722','长岭县','220700','松原市',18,'220722','长岭县','220700','220000','吉林省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (629,'220723','乾安县','220700','松原市',27,'220723','乾安县','220700','220000','吉林省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (630,'220781','扶余市','220700','松原市',15,'220781','扶余市','220700','220000','吉林省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (631,'220800','白城市','220800','白城市',220,'1','','220000','220000','吉林省','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (632,'220802','洮北区','220800','白城市',43,'220802','洮北区','220800','220000','吉林省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (633,'220821','镇赉县','220800','白城市',49,'220821','镇赉县','220800','220000','吉林省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (634,'220822','通榆县','220800','白城市',17,'220822','通榆县','220800','220000','吉林省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (635,'220881','洮南市','220800','白城市',19,'220881','洮南市','220800','220000','吉林省','正常',12);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (636,'220882','大安市','220800','白城市',50,'220882','大安市','220800','220000','吉林省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (637,'222400','延边朝鲜族自治州','222400','延边朝鲜族自治州',304,'1','','220000','220000','吉林省','正常',50);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (638,'222401','延吉市','222400','延边朝鲜族自治州',80,'222401','延吉市','222400','220000','吉林省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (639,'222402','图们市','222400','延边朝鲜族自治州',16,'222402','图们市','222400','220000','吉林省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (640,'222403','敦化市','222400','延边朝鲜族自治州',3,'222403','敦化市','222400','220000','吉林省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (641,'222404','珲春市','222400','延边朝鲜族自治州',125,'222404','珲春市','222400','220000','吉林省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (642,'222405','龙井市','222400','延边朝鲜族自治州',1,'222405','龙井市','222400','220000','吉林省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (643,'222406','和龙市','222400','延边朝鲜族自治州',46,'222406','和龙市','222400','220000','吉林省','正常',46);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (644,'222424','汪清县','222400','延边朝鲜族自治州',0,'222424','汪清县','222400','220000','吉林省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (645,'222426','安图县','222400','延边朝鲜族自治州',4,'222426','安图县','222400','220000','吉林省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (646,'230000','黑龙江省','1','',6497,'','','100000','230000','黑龙江省','正常',415);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (647,'230100','哈尔滨市','230100','哈尔滨市',1946,'1','','230000','230000','黑龙江省','正常',116);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (648,'230102','道里区','230100','哈尔滨市',265,'230102','道里区','230100','230000','黑龙江省','正常',49);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (649,'230103','南岗区','230100','哈尔滨市',260,'230103','南岗区','230100','230000','黑龙江省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (650,'230104','道外区','230100','哈尔滨市',116,'230104','道外区','230100','230000','黑龙江省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (651,'230108','平房区','230100','哈尔滨市',20,'230108','平房区','230100','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (652,'230109','松北区','230100','哈尔滨市',66,'230109','松北区','230100','230000','黑龙江省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (653,'230110','香坊区','230100','哈尔滨市',64,'230110','香坊区','230100','230000','黑龙江省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (654,'230111','呼兰区','230100','哈尔滨市',53,'230111','呼兰区','230100','230000','黑龙江省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (655,'230112','阿城区','230100','哈尔滨市',85,'230112','阿城区','230100','230000','黑龙江省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (656,'230113','双城区','230100','哈尔滨市',115,'230113','双城区','230100','230000','黑龙江省','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (657,'230123','依兰县','230100','哈尔滨市',33,'230123','依兰县','230100','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (658,'230124','方正县','230100','哈尔滨市',24,'230124','方正县','230100','230000','黑龙江省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (659,'230125','宾县','230100','哈尔滨市',108,'230125','宾县','230100','230000','黑龙江省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (660,'230126','巴彦县','230100','哈尔滨市',24,'230126','巴彦县','230100','230000','黑龙江省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (661,'230127','木兰县','230100','哈尔滨市',9,'230127','木兰县','230100','230000','黑龙江省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (662,'230128','通河县','230100','哈尔滨市',1,'230128','通河县','230100','230000','黑龙江省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (663,'230129','延寿县','230100','哈尔滨市',6,'230129','延寿县','230100','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (664,'230183','尚志市','230100','哈尔滨市',31,'230183','尚志市','230100','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (665,'230184','五常市','230100','哈尔滨市',91,'230184','五常市','230100','230000','黑龙江省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (666,'230200','齐齐哈尔市','230200','齐齐哈尔市',686,'1','','230000','230000','黑龙江省','正常',43);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (667,'230202','龙沙区','230200','齐齐哈尔市',125,'230202','龙沙区','230200','230000','黑龙江省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (668,'230203','建华区','230200','齐齐哈尔市',86,'230203','建华区','230200','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (669,'230204','铁锋区','230200','齐齐哈尔市',42,'230204','铁锋区','230200','230000','黑龙江省','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (670,'230205','昂昂溪区','230200','齐齐哈尔市',2,'230205','昂昂溪区','230200','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (671,'230206','富拉尔基区','230200','齐齐哈尔市',4,'230206','富拉尔基区','230200','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (672,'230207','碾子山区','230200','齐齐哈尔市',0,'230207','碾子山区','230200','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (673,'230208','梅里斯达斡尔族区','230200','齐齐哈尔市',3,'230208','梅里斯达斡尔族区','230200','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (674,'230221','龙江县','230200','齐齐哈尔市',33,'230221','龙江县','230200','230000','黑龙江省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (675,'230223','依安县','230200','齐齐哈尔市',18,'230223','依安县','230200','230000','黑龙江省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (676,'230224','泰来县','230200','齐齐哈尔市',20,'230224','泰来县','230200','230000','黑龙江省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (677,'230225','甘南县','230200','齐齐哈尔市',38,'230225','甘南县','230200','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (678,'230227','富裕县','230200','齐齐哈尔市',14,'230227','富裕县','230200','230000','黑龙江省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (679,'230229','克山县','230200','齐齐哈尔市',18,'230229','克山县','230200','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (680,'230230','克东县','230200','齐齐哈尔市',6,'230230','克东县','230200','230000','黑龙江省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (681,'230231','拜泉县','230200','齐齐哈尔市',13,'230231','拜泉县','230200','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (682,'230281','讷河市','230200','齐齐哈尔市',38,'230281','讷河市','230200','230000','黑龙江省','正常',14);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (683,'230300','鸡西市','230300','鸡西市',141,'1','','230000','230000','黑龙江省','正常',23);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (684,'230302','鸡冠区','230300','鸡西市',21,'230302','鸡冠区','230300','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (685,'230303','恒山区','230300','鸡西市',3,'230303','恒山区','230300','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (686,'230304','滴道区','230300','鸡西市',4,'230304','滴道区','230300','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (687,'230305','梨树区','230300','鸡西市',0,'230305','梨树区','230300','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (688,'230306','城子河区','230300','鸡西市',0,'230306','城子河区','230300','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (689,'230307','麻山区','230300','鸡西市',0,'230307','麻山区','230300','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (690,'230321','鸡东县','230300','鸡西市',0,'230321','鸡东县','230300','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (691,'230381','虎林市','230300','鸡西市',7,'230381','虎林市','230300','230000','黑龙江省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (692,'230382','密山市','230300','鸡西市',52,'230382','密山市','230300','230000','黑龙江省','正常',22);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (693,'230400','鹤岗市','230400','鹤岗市',82,'1','','230000','230000','黑龙江省','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (694,'230402','向阳区','230400','鹤岗市',0,'230402','向阳区','230400','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (695,'230403','工农区','230400','鹤岗市',7,'230403','工农区','230400','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (696,'230404','南山区','230400','鹤岗市',0,'230404','南山区','230400','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (697,'230405','兴安区','230400','鹤岗市',0,'230405','兴安区','230400','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (698,'230406','东山区','230400','鹤岗市',0,'230406','东山区','230400','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (699,'230407','兴山区','230400','鹤岗市',0,'230407','兴山区','230400','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (700,'230421','萝北县','230400','鹤岗市',15,'230421','萝北县','230400','230000','黑龙江省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (701,'230422','绥滨县','230400','鹤岗市',56,'230422','绥滨县','230400','230000','黑龙江省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (702,'230500','双鸭山市','230500','双鸭山市',153,'1','','230000','230000','黑龙江省','正常',38);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (703,'230502','尖山区','230500','双鸭山市',90,'230502','尖山区','230500','230000','黑龙江省','正常',30);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (704,'230503','岭东区','230500','双鸭山市',0,'230503','岭东区','230500','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (705,'230505','四方台区','230500','双鸭山市',0,'230505','四方台区','230500','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (706,'230506','宝山区','230500','双鸭山市',2,'230506','宝山区','230500','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (707,'230521','集贤县','230500','双鸭山市',17,'230521','集贤县','230500','230000','黑龙江省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (708,'230522','友谊县','230500','双鸭山市',2,'230522','友谊县','230500','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (709,'230523','宝清县','230500','双鸭山市',6,'230523','宝清县','230500','230000','黑龙江省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (710,'230524','饶河县','230500','双鸭山市',0,'230524','饶河县','230500','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (711,'230600','大庆市','230600','大庆市',625,'1','','230000','230000','黑龙江省','正常',56);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (712,'230602','萨尔图区','230600','大庆市',32,'230602','萨尔图区','230600','230000','黑龙江省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (713,'230603','龙凤区','230600','大庆市',42,'230603','龙凤区','230600','230000','黑龙江省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (714,'230604','让胡路区','230600','大庆市',159,'230604','让胡路区','230600','230000','黑龙江省','正常',36);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (715,'230605','红岗区','230600','大庆市',12,'230605','红岗区','230600','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (716,'230606','大同区','230600','大庆市',15,'230606','大同区','230600','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (717,'230621','肇州县','230600','大庆市',70,'230621','肇州县','230600','230000','黑龙江省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (718,'230622','肇源县','230600','大庆市',42,'230622','肇源县','230600','230000','黑龙江省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (719,'230623','林甸县','230600','大庆市',25,'230623','林甸县','230600','230000','黑龙江省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (720,'230624','杜尔伯特蒙古族自治县','230600','大庆市',69,'230624','杜尔伯特蒙古族自治县','230600','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (721,'230700','伊春市','230700','伊春市',35,'1','','230000','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (722,'230702','伊春区','230700','伊春市',29,'230702','伊春区','230700','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (723,'230703','南岔区','230700','伊春市',0,'230703','南岔区','230700','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (724,'230704','友好区','230700','伊春市',0,'230704','友好区','230700','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (725,'230705','西林区','230700','伊春市',0,'230705','西林区','230700','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (726,'230706','翠峦区','230700','伊春市',0,'230706','翠峦区','230700','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (727,'230707','新青区','230700','伊春市',0,'230707','新青区','230700','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (728,'230708','美溪区','230700','伊春市',0,'230708','美溪区','230700','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (729,'230709','金山屯区','230700','伊春市',0,'230709','金山屯区','230700','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (730,'230710','五营区','230700','伊春市',0,'230710','五营区','230700','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (731,'230711','乌马河区','230700','伊春市',0,'230711','乌马河区','230700','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (732,'230712','汤旺河区','230700','伊春市',0,'230712','汤旺河区','230700','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (733,'230713','带岭区','230700','伊春市',0,'230713','带岭区','230700','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (734,'230714','乌伊岭区','230700','伊春市',0,'230714','乌伊岭区','230700','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (735,'230715','红星区','230700','伊春市',0,'230715','红星区','230700','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (736,'230716','上甘岭区','230700','伊春市',0,'230716','上甘岭区','230700','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (737,'230722','嘉荫县','230700','伊春市',0,'230722','嘉荫县','230700','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (738,'230781','铁力市','230700','伊春市',1,'230781','铁力市','230700','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (739,'230800','佳木斯市','230800','佳木斯市',357,'1','','230000','230000','黑龙江省','正常',37);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (740,'230803','向阳区','230800','佳木斯市',39,'230803','向阳区','230800','230000','黑龙江省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (741,'230804','前进区','230800','佳木斯市',12,'230804','前进区','230800','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (742,'230805','东风区','230800','佳木斯市',51,'230805','东风区','230800','230000','黑龙江省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (743,'230811','郊区','230800','佳木斯市',14,'230811','郊区','230800','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (744,'230822','桦南县','230800','佳木斯市',35,'230822','桦南县','230800','230000','黑龙江省','正常',12);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (745,'230826','桦川县','230800','佳木斯市',8,'230826','桦川县','230800','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (746,'230828','汤原县','230800','佳木斯市',0,'230828','汤原县','230800','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (747,'230881','同江市','230800','佳木斯市',8,'230881','同江市','230800','230000','黑龙江省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (748,'230882','富锦市','230800','佳木斯市',68,'230882','富锦市','230800','230000','黑龙江省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (749,'230883','抚远市','230800','佳木斯市',5,'230883','抚远市','230800','230000','黑龙江省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (750,'230900','七台河市','230900','七台河市',63,'1','','230000','230000','黑龙江省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (751,'230902','新兴区','230900','七台河市',24,'230902','新兴区','230900','230000','黑龙江省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (752,'230903','桃山区','230900','七台河市',9,'230903','桃山区','230900','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (753,'230904','茄子河区','230900','七台河市',8,'230904','茄子河区','230900','230000','黑龙江省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (754,'230921','勃利县','230900','七台河市',1,'230921','勃利县','230900','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (755,'231000','牡丹江市','231000','牡丹江市',457,'1','','230000','230000','黑龙江省','正常',31);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (756,'231002','东安区','231000','牡丹江市',112,'231002','东安区','231000','230000','黑龙江省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (757,'231003','阳明区','231000','牡丹江市',42,'231003','阳明区','231000','230000','黑龙江省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (758,'231004','爱民区','231000','牡丹江市',30,'231004','爱民区','231000','230000','黑龙江省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (759,'231005','西安区','231000','牡丹江市',67,'231005','西安区','231000','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (760,'231025','林口县','231000','牡丹江市',63,'231025','林口县','231000','230000','黑龙江省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (761,'231081','绥芬河市','231000','牡丹江市',40,'231081','绥芬河市','231000','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (762,'231083','海林市','231000','牡丹江市',6,'231083','海林市','231000','230000','黑龙江省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (763,'231084','宁安市','231000','牡丹江市',8,'231084','宁安市','231000','230000','黑龙江省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (764,'231085','穆棱市','231000','牡丹江市',35,'231085','穆棱市','231000','230000','黑龙江省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (765,'231086','东宁市','231000','牡丹江市',5,'231086','东宁市','231000','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (766,'231100','黑河市','231100','黑河市',228,'1','','230000','230000','黑龙江省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (767,'231102','爱辉区','231100','黑河市',15,'231102','爱辉区','231100','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (768,'231121','嫩江县','231100','黑河市',11,'231121','嫩江县','231100','230000','黑龙江省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (769,'231123','逊克县','231100','黑河市',4,'231123','逊克县','231100','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (770,'231124','孙吴县','231100','黑河市',90,'231124','孙吴县','231100','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (771,'231181','北安市','231100','黑河市',9,'231181','北安市','231100','230000','黑龙江省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (772,'231182','五大连池市','231100','黑河市',81,'231182','五大连池市','231100','230000','黑龙江省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (773,'231200','绥化市','231200','绥化市',609,'1','','230000','230000','黑龙江省','正常',32);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (774,'231202','北林区','231200','绥化市',112,'231202','北林区','231200','230000','黑龙江省','正常',12);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (775,'231221','望奎县','231200','绥化市',90,'231221','望奎县','231200','230000','黑龙江省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (776,'231222','兰西县','231200','绥化市',5,'231222','兰西县','231200','230000','黑龙江省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (777,'231223','青冈县','231200','绥化市',26,'231223','青冈县','231200','230000','黑龙江省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (778,'231224','庆安县','231200','绥化市',31,'231224','庆安县','231200','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (779,'231225','明水县','231200','绥化市',2,'231225','明水县','231200','230000','黑龙江省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (780,'231226','绥棱县','231200','绥化市',117,'231226','绥棱县','231200','230000','黑龙江省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (781,'231281','安达市','231200','绥化市',29,'231281','安达市','231200','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (782,'231282','肇东市','231200','绥化市',41,'231282','肇东市','231200','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (783,'231283','海伦市','231200','绥化市',30,'231283','海伦市','231200','230000','黑龙江省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (784,'232700','大兴安岭地区','232700','大兴安岭地区',46,'1','','230000','230000','黑龙江省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (785,'232718','加格达奇区','232700','大兴安岭地区',12,'232718','加格达奇区','232700','230000','黑龙江省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (786,'232721','呼玛县','232700','大兴安岭地区',0,'232721','呼玛县','232700','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (787,'232722','塔河县','232700','大兴安岭地区',34,'232722','塔河县','232700','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (788,'232701','漠河市','232700','大兴安岭地区',0,'232701','漠河市','232700','230000','黑龙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (789,'310000','上海市','1','',3492,'1','','100000','310000','上海市','正常',271);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (790,'310101','黄浦区','1','',72,'310101','黄浦区','310000','310000','上海市','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (791,'310104','徐汇区','1','',353,'310104','徐汇区','310000','310000','上海市','正常',42);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (792,'310105','长宁区','1','',17,'310105','长宁区','310000','310000','上海市','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (793,'310106','静安区','1','',54,'310106','静安区','310000','310000','上海市','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (794,'310107','普陀区','1','',117,'310107','普陀区','310000','310000','上海市','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (795,'310109','虹口区','1','',19,'310109','虹口区','310000','310000','上海市','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (796,'310110','杨浦区','1','',31,'310110','杨浦区','310000','310000','上海市','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (797,'310112','闵行区','1','',464,'310112','闵行区','310000','310000','上海市','正常',31);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (798,'310113','宝山区','1','',301,'310113','宝山区','310000','310000','上海市','正常',38);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (799,'310114','嘉定区','1','',185,'310114','嘉定区','310000','310000','上海市','正常',27);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (800,'310115','浦东新区','1','',655,'310115','浦东新区','310000','310000','上海市','正常',61);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (801,'310116','金山区','1','',100,'310116','金山区','310000','310000','上海市','正常',28);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (802,'310117','松江区','1','',194,'310117','松江区','310000','310000','上海市','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (803,'310118','青浦区','1','',43,'310118','青浦区','310000','310000','上海市','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (804,'310120','奉贤区','1','',67,'310120','奉贤区','310000','310000','上海市','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (805,'310151','崇明区','1','',45,'310151','崇明区','310000','310000','上海市','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (806,'320000','江苏省','1','',25280,'','','100000','320000','江苏省','正常',1311);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (807,'320100','南京市','320100','南京市',2259,'1','','320000','320000','江苏省','正常',168);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (808,'320102','玄武区','320100','南京市',23,'320102','玄武区','320100','320000','江苏省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (809,'320104','秦淮区','320100','南京市',90,'320104','秦淮区','320100','320000','江苏省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (810,'320105','建邺区','320100','南京市',164,'320105','建邺区','320100','320000','江苏省','正常',41);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (811,'320106','鼓楼区','320100','南京市',143,'320106','鼓楼区','320100','320000','江苏省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (812,'320111','浦口区','320100','南京市',132,'320111','浦口区','320100','320000','江苏省','正常',15);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (813,'320113','栖霞区','320100','南京市',138,'320113','栖霞区','320100','320000','江苏省','正常',20);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (814,'320114','雨花台区','320100','南京市',193,'320114','雨花台区','320100','320000','江苏省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (815,'320115','江宁区','320100','南京市',506,'320115','江宁区','320100','320000','江苏省','正常',61);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (816,'320116','六合区','320100','南京市',457,'320116','六合区','320100','320000','江苏省','正常',12);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (817,'320117','溧水区','320100','南京市',59,'320117','溧水区','320100','320000','江苏省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (818,'320118','高淳区','320100','南京市',157,'320118','高淳区','320100','320000','江苏省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (819,'320200','无锡市','320200','无锡市',1506,'1','','320000','320000','江苏省','正常',109);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (820,'320205','锡山区','320200','无锡市',225,'320205','锡山区','320200','320000','江苏省','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (821,'320206','惠山区','320200','无锡市',207,'320206','惠山区','320200','320000','江苏省','正常',22);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (822,'320211','滨湖区','320200','无锡市',94,'320211','滨湖区','320200','320000','江苏省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (823,'320213','梁溪区','320200','无锡市',124,'320213','梁溪区','320200','320000','江苏省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (824,'320214','新吴区','320200','无锡市',258,'320214','新吴区','320200','320000','江苏省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (825,'320281','江阴市','320200','无锡市',203,'320281','江阴市','320200','320000','江苏省','正常',21);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (826,'320282','宜兴市','320200','无锡市',154,'320282','宜兴市','320200','320000','江苏省','正常',23);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (827,'320300','徐州市','320300','徐州市',1374,'1','','320000','320000','江苏省','正常',207);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (828,'320302','鼓楼区','320300','徐州市',135,'320302','鼓楼区','320300','320000','江苏省','正常',27);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (829,'320303','云龙区','320300','徐州市',48,'320303','云龙区','320300','320000','江苏省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (830,'320305','贾汪区','320300','徐州市',45,'320305','贾汪区','320300','320000','江苏省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (831,'320311','泉山区','320300','徐州市',107,'320311','泉山区','320300','320000','江苏省','正常',62);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (832,'320312','铜山区','320300','徐州市',116,'320312','铜山区','320300','320000','江苏省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (833,'320321','丰县','320300','徐州市',29,'320321','丰县','320300','320000','江苏省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (834,'320322','沛县','320300','徐州市',123,'320322','沛县','320300','320000','江苏省','正常',23);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (835,'320324','睢宁县','320300','徐州市',143,'320324','睢宁县','320300','320000','江苏省','正常',42);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (836,'320381','新沂市','320300','徐州市',79,'320381','新沂市','320300','320000','江苏省','正常',14);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (837,'320382','邳州市','320300','徐州市',332,'320382','邳州市','320300','320000','江苏省','正常',18);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (838,'320400','常州市','320400','常州市',939,'1','','320000','320000','江苏省','正常',58);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (839,'320402','天宁区','320400','常州市',193,'320402','天宁区','320400','320000','江苏省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (840,'320404','钟楼区','320400','常州市',71,'320404','钟楼区','320400','320000','江苏省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (841,'320411','新北区','320400','常州市',149,'320411','新北区','320400','320000','江苏省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (842,'320412','武进区','320400','常州市',370,'320412','武进区','320400','320000','江苏省','正常',22);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (843,'320413','金坛区','320400','常州市',59,'320413','金坛区','320400','320000','江苏省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (844,'320481','溧阳市','320400','常州市',64,'320481','溧阳市','320400','320000','江苏省','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (845,'320500','苏州市','320500','苏州市',2839,'1','','320000','320000','江苏省','正常',271);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (846,'320505','虎丘区','320500','苏州市',197,'320505','虎丘区','320500','320000','江苏省','正常',17);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (847,'320506','吴中区','320500','苏州市',287,'320506','吴中区','320500','320000','江苏省','正常',32);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (848,'320507','相城区','320500','苏州市',183,'320507','相城区','320500','320000','江苏省','正常',26);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (849,'320508','姑苏区','320500','苏州市',309,'320508','姑苏区','320500','320000','江苏省','正常',33);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (850,'320509','吴江区','320500','苏州市',275,'320509','吴江区','320500','320000','江苏省','正常',26);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (851,'320571','苏州工业园区','320500','苏州市',246,'320571','苏州工业园区','320500','320000','江苏省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (852,'320581','常熟市','320500','苏州市',263,'320581','常熟市','320500','320000','江苏省','正常',37);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (853,'320582','张家港市','320500','苏州市',234,'320582','张家港市','320500','320000','江苏省','正常',38);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (854,'320583','昆山市','320500','苏州市',507,'320583','昆山市','320500','320000','江苏省','正常',30);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (855,'320585','太仓市','320500','苏州市',86,'320585','太仓市','320500','320000','江苏省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (856,'320600','南通市','320600','南通市',549,'1','','320000','320000','江苏省','正常',41);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (857,'320602','崇川区','320600','南通市',214,'320602','崇川区','320600','320000','江苏省','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (858,'320611','港闸区','320600','南通市',44,'320611','港闸区','320600','320000','江苏省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (859,'320612','通州区','320600','南通市',58,'320612','通州区','320600','320000','江苏省','正常',16);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (860,'320685','海安市','320600','南通市',30,'320685','海安市','320600','320000','江苏省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (861,'320623','如东县','320600','南通市',7,'320623','如东县','320600','320000','江苏省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (862,'320681','启东市','320600','南通市',42,'320681','启东市','320600','320000','江苏省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (863,'320682','如皋市','320600','南通市',46,'320682','如皋市','320600','320000','江苏省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (864,'320684','海门市','320600','南通市',43,'320684','海门市','320600','320000','江苏省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (865,'320700','连云港市','320700','连云港市',688,'1','','320000','320000','江苏省','正常',68);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (866,'320703','连云区','320700','连云港市',60,'320703','连云区','320700','320000','江苏省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (867,'320706','海州区','320700','连云港市',95,'320706','海州区','320700','320000','江苏省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (868,'320707','赣榆区','320700','连云港市',112,'320707','赣榆区','320700','320000','江苏省','正常',27);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (869,'320722','东海县','320700','连云港市',214,'320722','东海县','320700','320000','江苏省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (870,'320723','灌云县','320700','连云港市',59,'320723','灌云县','320700','320000','江苏省','正常',23);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (871,'320724','灌南县','320700','连云港市',36,'320724','灌南县','320700','320000','江苏省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (872,'320800','淮安市','320800','淮安市',1109,'1','','320000','320000','江苏省','正常',34);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (873,'320803','淮安区','320800','淮安市',234,'320803','淮安区','320800','320000','江苏省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (874,'320804','淮阴区','320800','淮安市',317,'320804','淮阴区','320800','320000','江苏省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (875,'320812','清江浦区','320800','淮安市',332,'320812','清江浦区','320800','320000','江苏省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (876,'320813','洪泽区','320800','淮安市',36,'320813','洪泽区','320800','320000','江苏省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (877,'320826','涟水县','320800','淮安市',41,'320826','涟水县','320800','320000','江苏省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (878,'320830','盱眙县','320800','淮安市',84,'320830','盱眙县','320800','320000','江苏省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (879,'320831','金湖县','320800','淮安市',9,'320831','金湖县','320800','320000','江苏省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (880,'320900','盐城市','320900','盐城市',857,'1','','320000','320000','江苏省','正常',76);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (881,'320902','亭湖区','320900','盐城市',144,'320902','亭湖区','320900','320000','江苏省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (882,'320903','盐都区','320900','盐城市',70,'320903','盐都区','320900','320000','江苏省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (883,'320904','大丰区','320900','盐城市',49,'320904','大丰区','320900','320000','江苏省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (884,'320921','响水县','320900','盐城市',43,'320921','响水县','320900','320000','江苏省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (885,'320922','滨海县','320900','盐城市',174,'320922','滨海县','320900','320000','江苏省','正常',50);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (886,'320923','阜宁县','320900','盐城市',85,'320923','阜宁县','320900','320000','江苏省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (887,'320924','射阳县','320900','盐城市',93,'320924','射阳县','320900','320000','江苏省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (888,'320925','建湖县','320900','盐城市',50,'320925','建湖县','320900','320000','江苏省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (889,'320981','东台市','320900','盐城市',32,'320981','东台市','320900','320000','江苏省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (890,'321000','扬州市','321000','扬州市',539,'1','','320000','320000','江苏省','正常',32);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (891,'321002','广陵区','321000','扬州市',83,'321002','广陵区','321000','320000','江苏省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (892,'321003','邗江区','321000','扬州市',153,'321003','邗江区','321000','320000','江苏省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (893,'321012','江都区','321000','扬州市',67,'321012','江都区','321000','320000','江苏省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (894,'321023','宝应县','321000','扬州市',56,'321023','宝应县','321000','320000','江苏省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (895,'321081','仪征市','321000','扬州市',45,'321081','仪征市','321000','320000','江苏省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (896,'321084','高邮市','321000','扬州市',47,'321084','高邮市','321000','320000','江苏省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (897,'321100','镇江市','321100','镇江市',492,'1','','320000','320000','江苏省','正常',34);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (898,'321102','京口区','321100','镇江市',46,'321102','京口区','321100','320000','江苏省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (899,'321111','润州区','321100','镇江市',90,'321111','润州区','321100','320000','江苏省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (900,'321112','丹徒区','321100','镇江市',71,'321112','丹徒区','321100','320000','江苏省','正常',24);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (901,'321181','丹阳市','321100','镇江市',104,'321181','丹阳市','321100','320000','江苏省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (902,'321182','扬中市','321100','镇江市',16,'321182','扬中市','321100','320000','江苏省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (903,'321183','句容市','321100','镇江市',125,'321183','句容市','321100','320000','江苏省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (904,'321200','泰州市','321200','泰州市',699,'1','','320000','320000','江苏省','正常',21);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (905,'321202','海陵区','321200','泰州市',51,'321202','海陵区','321200','320000','江苏省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (906,'321203','高港区','321200','泰州市',154,'321203','高港区','321200','320000','江苏省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (907,'321204','姜堰区','321200','泰州市',60,'321204','姜堰区','321200','320000','江苏省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (908,'321281','兴化市','321200','泰州市',55,'321281','兴化市','321200','320000','江苏省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (909,'321282','靖江市','321200','泰州市',279,'321282','靖江市','321200','320000','江苏省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (910,'321283','泰兴市','321200','泰州市',35,'321283','泰兴市','321200','320000','江苏省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (911,'321300','宿迁市','321300','宿迁市',534,'1','','320000','320000','江苏省','正常',34);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (912,'321302','宿城区','321300','宿迁市',92,'321302','宿城区','321300','320000','江苏省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (913,'321311','宿豫区','321300','宿迁市',34,'321311','宿豫区','321300','320000','江苏省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (914,'321322','沭阳县','321300','宿迁市',111,'321322','沭阳县','321300','320000','江苏省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (915,'321323','泗阳县','321300','宿迁市',158,'321323','泗阳县','321300','320000','江苏省','正常',17);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (916,'321324','泗洪县','321300','宿迁市',82,'321324','泗洪县','321300','320000','江苏省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (917,'330000','浙江省','1','',16188,'','','100000','330000','浙江省','正常',919);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (918,'330100','杭州市','330100','杭州市',2312,'1','','330000','330000','浙江省','正常',248);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (919,'330102','上城区','330100','杭州市',12,'330102','上城区','330100','330000','浙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (920,'330103','下城区','330100','杭州市',63,'330103','下城区','330100','330000','浙江省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (921,'330104','江干区','330100','杭州市',294,'330104','江干区','330100','330000','浙江省','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (922,'330105','拱墅区','330100','杭州市',85,'330105','拱墅区','330100','330000','浙江省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (923,'330106','西湖区','330100','杭州市',202,'330106','西湖区','330100','330000','浙江省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (924,'330108','滨江区','330100','杭州市',97,'330108','滨江区','330100','330000','浙江省','正常',18);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (925,'330109','萧山区','330100','杭州市',219,'330109','萧山区','330100','330000','浙江省','正常',15);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (926,'330110','余杭区','330100','杭州市',804,'330110','余杭区','330100','330000','浙江省','正常',151);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (927,'330111','富阳区','330100','杭州市',76,'330111','富阳区','330100','330000','浙江省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (928,'330112','临安区','330100','杭州市',64,'330112','临安区','330100','330000','浙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (929,'330122','桐庐县','330100','杭州市',62,'330122','桐庐县','330100','330000','浙江省','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (930,'330127','淳安县','330100','杭州市',20,'330127','淳安县','330100','330000','浙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (931,'330182','建德市','330100','杭州市',20,'330182','建德市','330100','330000','浙江省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (932,'330200','宁波市','330200','宁波市',1447,'1','','330000','330000','浙江省','正常',90);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (933,'330203','海曙区','330200','宁波市',179,'330203','海曙区','330200','330000','浙江省','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (934,'330205','江北区','330200','宁波市',56,'330205','江北区','330200','330000','浙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (935,'330206','北仑区','330200','宁波市',78,'330206','北仑区','330200','330000','浙江省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (936,'330211','镇海区','330200','宁波市',37,'330211','镇海区','330200','330000','浙江省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (937,'330212','鄞州区','330200','宁波市',333,'330212','鄞州区','330200','330000','浙江省','正常',27);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (938,'330213','奉化区','330200','宁波市',27,'330213','奉化区','330200','330000','浙江省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (939,'330225','象山县','330200','宁波市',61,'330225','象山县','330200','330000','浙江省','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (940,'330226','宁海县','330200','宁波市',48,'330226','宁海县','330200','330000','浙江省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (941,'330281','余姚市','330200','宁波市',272,'330281','余姚市','330200','330000','浙江省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (942,'330282','慈溪市','330200','宁波市',216,'330282','慈溪市','330200','330000','浙江省','正常',21);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (943,'330300','温州市','330300','温州市',1167,'1','','330000','330000','浙江省','正常',122);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (944,'330302','鹿城区','330300','温州市',110,'330302','鹿城区','330300','330000','浙江省','正常',12);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (945,'330303','龙湾区','330300','温州市',76,'330303','龙湾区','330300','330000','浙江省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (946,'330304','瓯海区','330300','温州市',128,'330304','瓯海区','330300','330000','浙江省','正常',31);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (947,'330305','洞头区','330300','温州市',3,'330305','洞头区','330300','330000','浙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (948,'330324','永嘉县','330300','温州市',25,'330324','永嘉县','330300','330000','浙江省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (949,'330326','平阳县','330300','温州市',110,'330326','平阳县','330300','330000','浙江省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (950,'330327','苍南县','330300','温州市',86,'330327','苍南县','330300','330000','浙江省','正常',35);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (951,'330328','文成县','330300','温州市',3,'330328','文成县','330300','330000','浙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (952,'330329','泰顺县','330300','温州市',52,'330329','泰顺县','330300','330000','浙江省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (953,'330381','瑞安市','330300','温州市',134,'330381','瑞安市','330300','330000','浙江省','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (954,'330382','乐清市','330300','温州市',304,'330382','乐清市','330300','330000','浙江省','正常',14);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (955,'330400','嘉兴市','330400','嘉兴市',867,'1','','330000','330000','浙江省','正常',61);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (956,'330402','南湖区','330400','嘉兴市',279,'330402','南湖区','330400','330000','浙江省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (957,'330411','秀洲区','330400','嘉兴市',85,'330411','秀洲区','330400','330000','浙江省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (958,'330421','嘉善县','330400','嘉兴市',69,'330421','嘉善县','330400','330000','浙江省','正常',17);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (959,'330424','海盐县','330400','嘉兴市',24,'330424','海盐县','330400','330000','浙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (960,'330481','海宁市','330400','嘉兴市',173,'330481','海宁市','330400','330000','浙江省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (961,'330482','平湖市','330400','嘉兴市',76,'330482','平湖市','330400','330000','浙江省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (962,'330483','桐乡市','330400','嘉兴市',77,'330483','桐乡市','330400','330000','浙江省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (963,'330500','湖州市','330500','湖州市',332,'1','','330000','330000','浙江省','正常',52);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (964,'330502','吴兴区','330500','湖州市',51,'330502','吴兴区','330500','330000','浙江省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (965,'330503','南浔区','330500','湖州市',165,'330503','南浔区','330500','330000','浙江省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (966,'330521','德清县','330500','湖州市',50,'330521','德清县','330500','330000','浙江省','正常',32);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (967,'330522','长兴县','330500','湖州市',28,'330522','长兴县','330500','330000','浙江省','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (968,'330523','安吉县','330500','湖州市',13,'330523','安吉县','330500','330000','浙江省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (969,'330600','绍兴市','330600','绍兴市',677,'1','','330000','330000','浙江省','正常',61);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (970,'330602','越城区','330600','绍兴市',131,'330602','越城区','330600','330000','浙江省','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (971,'330603','柯桥区','330600','绍兴市',227,'330603','柯桥区','330600','330000','浙江省','正常',15);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (972,'330604','上虞区','330600','绍兴市',134,'330604','上虞区','330600','330000','浙江省','正常',21);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (973,'330624','新昌县','330600','绍兴市',51,'330624','新昌县','330600','330000','浙江省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (974,'330681','诸暨市','330600','绍兴市',70,'330681','诸暨市','330600','330000','浙江省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (975,'330683','嵊州市','330600','绍兴市',33,'330683','嵊州市','330600','330000','浙江省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (976,'330700','金华市','330700','金华市',896,'1','','330000','330000','浙江省','正常',67);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (977,'330702','婺城区','330700','金华市',50,'330702','婺城区','330700','330000','浙江省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (978,'330703','金东区','330700','金华市',46,'330703','金东区','330700','330000','浙江省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (979,'330723','武义县','330700','金华市',34,'330723','武义县','330700','330000','浙江省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (980,'330726','浦江县','330700','金华市',33,'330726','浦江县','330700','330000','浙江省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (981,'330727','磐安县','330700','金华市',1,'330727','磐安县','330700','330000','浙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (982,'330781','兰溪市','330700','金华市',20,'330781','兰溪市','330700','330000','浙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (983,'330782','义乌市','330700','金华市',285,'330782','义乌市','330700','330000','浙江省','正常',34);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (984,'330783','东阳市','330700','金华市',131,'330783','东阳市','330700','330000','浙江省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (985,'330784','永康市','330700','金华市',191,'330784','永康市','330700','330000','浙江省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (986,'330800','衢州市','330800','衢州市',205,'1','','330000','330000','浙江省','正常',14);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (987,'330802','柯城区','330800','衢州市',33,'330802','柯城区','330800','330000','浙江省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (988,'330803','衢江区','330800','衢州市',14,'330803','衢江区','330800','330000','浙江省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (989,'330822','常山县','330800','衢州市',13,'330822','常山县','330800','330000','浙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (990,'330824','开化县','330800','衢州市',23,'330824','开化县','330800','330000','浙江省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (991,'330825','龙游县','330800','衢州市',16,'330825','龙游县','330800','330000','浙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (992,'330881','江山市','330800','衢州市',7,'330881','江山市','330800','330000','浙江省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (993,'330900','舟山市','330900','舟山市',56,'1','','330000','330000','浙江省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (994,'330902','定海区','330900','舟山市',24,'330902','定海区','330900','330000','浙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (995,'330903','普陀区','330900','舟山市',25,'330903','普陀区','330900','330000','浙江省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (996,'330921','岱山县','330900','舟山市',4,'330921','岱山县','330900','330000','浙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (997,'330922','嵊泗县','330900','舟山市',0,'330922','嵊泗县','330900','330000','浙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (998,'331000','台州市','331000','台州市',1025,'1','','330000','330000','浙江省','正常',50);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (999,'331002','椒江区','331000','台州市',148,'331002','椒江区','331000','330000','浙江省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1000,'331003','黄岩区','331000','台州市',65,'331003','黄岩区','331000','330000','浙江省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1001,'331004','路桥区','331000','台州市',97,'331004','路桥区','331000','330000','浙江省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1002,'331022','三门县','331000','台州市',99,'331022','三门县','331000','330000','浙江省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1003,'331023','天台县','331000','台州市',110,'331023','天台县','331000','330000','浙江省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1004,'331024','仙居县','331000','台州市',62,'331024','仙居县','331000','330000','浙江省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1005,'331081','温岭市','331000','台州市',213,'331081','温岭市','331000','330000','浙江省','正常',20);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1006,'331082','临海市','331000','台州市',92,'331082','临海市','331000','330000','浙江省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1007,'331083','玉环市','331000','台州市',63,'331083','玉环市','331000','330000','浙江省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1008,'331100','丽水市','331100','丽水市',501,'1','','330000','330000','浙江省','正常',42);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1009,'331102','莲都区','331100','丽水市',102,'331102','莲都区','331100','330000','浙江省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1010,'331121','青田县','331100','丽水市',60,'331121','青田县','331100','330000','浙江省','正常',24);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1011,'331122','缙云县','331100','丽水市',105,'331122','缙云县','331100','330000','浙江省','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1012,'331123','遂昌县','331100','丽水市',14,'331123','遂昌县','331100','330000','浙江省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1013,'331124','松阳县','331100','丽水市',73,'331124','松阳县','331100','330000','浙江省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1014,'331125','云和县','331100','丽水市',2,'331125','云和县','331100','330000','浙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1015,'331126','庆元县','331100','丽水市',18,'331126','庆元县','331100','330000','浙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1016,'331127','景宁畲族自治县','331100','丽水市',1,'331127','景宁畲族自治县','331100','330000','浙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1017,'331181','龙泉市','331100','丽水市',64,'331181','龙泉市','331100','330000','浙江省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1018,'340000','安徽省','1','',21408,'','','100000','340000','安徽省','正常',846);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1019,'340100','合肥市','340100','合肥市',2313,'1','','340000','340000','安徽省','正常',128);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1020,'340102','瑶海区','340100','合肥市',255,'340102','瑶海区','340100','340000','安徽省','正常',22);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1021,'340103','庐阳区','340100','合肥市',125,'340103','庐阳区','340100','340000','安徽省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1022,'340104','蜀山区','340100','合肥市',447,'340104','蜀山区','340100','340000','安徽省','正常',23);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1023,'340111','包河区','340100','合肥市',272,'340111','包河区','340100','340000','安徽省','正常',18);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1024,'340121','长丰县','340100','合肥市',245,'340121','长丰县','340100','340000','安徽省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1025,'340122','肥东县','340100','合肥市',210,'340122','肥东县','340100','340000','安徽省','正常',16);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1026,'340123','肥西县','340100','合肥市',154,'340123','肥西县','340100','340000','安徽省','正常',12);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1027,'340124','庐江县','340100','合肥市',124,'340124','庐江县','340100','340000','安徽省','正常',17);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1028,'340181','巢湖市','340100','合肥市',90,'340181','巢湖市','340100','340000','安徽省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1029,'340200','芜湖市','340200','芜湖市',275,'1','','340000','340000','安徽省','正常',12);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1030,'340202','镜湖区','340200','芜湖市',38,'340202','镜湖区','340200','340000','安徽省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1031,'340203','弋江区','340200','芜湖市',35,'340203','弋江区','340200','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1032,'340207','鸠江区','340200','芜湖市',72,'340207','鸠江区','340200','340000','安徽省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1033,'340208','三山区','340200','芜湖市',10,'340208','三山区','340200','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1034,'340221','芜湖县','340200','芜湖市',29,'340221','芜湖县','340200','340000','安徽省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1035,'340222','繁昌县','340200','芜湖市',13,'340222','繁昌县','340200','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1036,'340223','南陵县','340200','芜湖市',21,'340223','南陵县','340200','340000','安徽省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1037,'340225','无为县','340200','芜湖市',25,'340225','无为县','340200','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1038,'340300','蚌埠市','340300','蚌埠市',485,'1','','340000','340000','安徽省','正常',34);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1039,'340302','龙子湖区','340300','蚌埠市',33,'340302','龙子湖区','340300','340000','安徽省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1040,'340303','蚌山区','340300','蚌埠市',90,'340303','蚌山区','340300','340000','安徽省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1041,'340304','禹会区','340300','蚌埠市',57,'340304','禹会区','340300','340000','安徽省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1042,'340311','淮上区','340300','蚌埠市',73,'340311','淮上区','340300','340000','安徽省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1043,'340321','怀远县','340300','蚌埠市',103,'340321','怀远县','340300','340000','安徽省','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1044,'340322','五河县','340300','蚌埠市',34,'340322','五河县','340300','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1045,'340323','固镇县','340300','蚌埠市',10,'340323','固镇县','340300','340000','安徽省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1046,'340400','淮南市','340400','淮南市',402,'1','','340000','340000','安徽省','正常',41);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1047,'340402','大通区','340400','淮南市',28,'340402','大通区','340400','340000','安徽省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1048,'340403','田家庵区','340400','淮南市',64,'340403','田家庵区','340400','340000','安徽省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1049,'340404','谢家集区','340400','淮南市',41,'340404','谢家集区','340400','340000','安徽省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1050,'340405','八公山区','340400','淮南市',1,'340405','八公山区','340400','340000','安徽省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1051,'340406','潘集区','340400','淮南市',30,'340406','潘集区','340400','340000','安徽省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1052,'340421','凤台县','340400','淮南市',134,'340421','凤台县','340400','340000','安徽省','正常',19);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1053,'340422','寿县','340400','淮南市',12,'340422','寿县','340400','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1054,'340500','马鞍山市','340500','马鞍山市',210,'1','','340000','340000','安徽省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1055,'340503','花山区','340500','马鞍山市',39,'340503','花山区','340500','340000','安徽省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1056,'340504','雨山区','340500','马鞍山市',38,'340504','雨山区','340500','340000','安徽省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1057,'340506','博望区','340500','马鞍山市',14,'340506','博望区','340500','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1058,'340521','当涂县','340500','马鞍山市',25,'340521','当涂县','340500','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1059,'340522','含山县','340500','马鞍山市',36,'340522','含山县','340500','340000','安徽省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1060,'340523','和县','340500','马鞍山市',46,'340523','和县','340500','340000','安徽省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1061,'340600','淮北市','340600','淮北市',231,'1','','340000','340000','安徽省','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1062,'340602','杜集区','340600','淮北市',1,'340602','杜集区','340600','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1063,'340603','相山区','340600','淮北市',90,'340603','相山区','340600','340000','安徽省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1064,'340604','烈山区','340600','淮北市',28,'340604','烈山区','340600','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1065,'340621','濉溪县','340600','淮北市',75,'340621','濉溪县','340600','340000','安徽省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1066,'340700','铜陵市','340700','铜陵市',145,'1','','340000','340000','安徽省','正常',12);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1067,'340705','铜官区','340700','铜陵市',86,'340705','铜官区','340700','340000','安徽省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1068,'340706','义安区','340700','铜陵市',12,'340706','义安区','340700','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1069,'340711','郊区','340700','铜陵市',9,'340711','郊区','340700','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1070,'340722','枞阳县','340700','铜陵市',29,'340722','枞阳县','340700','340000','安徽省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1071,'340800','安庆市','340800','安庆市',792,'1','','340000','340000','安徽省','正常',37);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1072,'340802','迎江区','340800','安庆市',128,'340802','迎江区','340800','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1073,'340803','大观区','340800','安庆市',274,'340803','大观区','340800','340000','安徽省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1074,'340811','宜秀区','340800','安庆市',54,'340811','宜秀区','340800','340000','安徽省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1075,'340822','怀宁县','340800','安庆市',25,'340822','怀宁县','340800','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1076,'340882','潜山市','340800','安庆市',17,'340882','潜山市','340800','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1077,'340825','太湖县','340800','安庆市',19,'340825','太湖县','340800','340000','安徽省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1078,'340826','宿松县','340800','安庆市',23,'340826','宿松县','340800','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1079,'340827','望江县','340800','安庆市',4,'340827','望江县','340800','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1080,'340828','岳西县','340800','安庆市',2,'340828','岳西县','340800','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1081,'340881','桐城市','340800','安庆市',146,'340881','桐城市','340800','340000','安徽省','正常',21);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1082,'341000','黄山市','341000','黄山市',97,'1','','340000','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1083,'341002','屯溪区','341000','黄山市',5,'341002','屯溪区','341000','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1084,'341003','黄山区','341000','黄山市',0,'341003','黄山区','341000','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1085,'341004','徽州区','341000','黄山市',24,'341004','徽州区','341000','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1086,'341021','歙县','341000','黄山市',20,'341021','歙县','341000','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1087,'341022','休宁县','341000','黄山市',7,'341022','休宁县','341000','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1088,'341023','黟县','341000','黄山市',0,'341023','黟县','341000','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1089,'341024','祁门县','341000','黄山市',23,'341024','祁门县','341000','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1090,'341100','滁州市','341100','滁州市',825,'1','','340000','340000','安徽省','正常',101);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1091,'341102','琅琊区','341100','滁州市',154,'341102','琅琊区','341100','340000','安徽省','正常',60);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1092,'341103','南谯区','341100','滁州市',134,'341103','南谯区','341100','340000','安徽省','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1093,'341122','来安县','341100','滁州市',20,'341122','来安县','341100','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1094,'341124','全椒县','341100','滁州市',34,'341124','全椒县','341100','340000','安徽省','正常',12);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1095,'341125','定远县','341100','滁州市',289,'341125','定远县','341100','340000','安徽省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1096,'341126','凤阳县','341100','滁州市',33,'341126','凤阳县','341100','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1097,'341181','天长市','341100','滁州市',36,'341181','天长市','341100','340000','安徽省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1098,'341182','明光市','341100','滁州市',16,'341182','明光市','341100','340000','安徽省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1099,'341200','阜阳市','341200','阜阳市',1409,'1','','340000','340000','安徽省','正常',131);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1100,'341202','颍州区','341200','阜阳市',164,'341202','颍州区','341200','340000','安徽省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1101,'341203','颍东区','341200','阜阳市',180,'341203','颍东区','341200','340000','安徽省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1102,'341204','颍泉区','341200','阜阳市',148,'341204','颍泉区','341200','340000','安徽省','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1103,'341221','临泉县','341200','阜阳市',169,'341221','临泉县','341200','340000','安徽省','正常',49);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1104,'341222','太和县','341200','阜阳市',103,'341222','太和县','341200','340000','安徽省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1105,'341225','阜南县','341200','阜阳市',118,'341225','阜南县','341200','340000','安徽省','正常',15);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1106,'341226','颍上县','341200','阜阳市',158,'341226','颍上县','341200','340000','安徽省','正常',18);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1107,'341282','界首市','341200','阜阳市',52,'341282','界首市','341200','340000','安徽省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1108,'341300','宿州市','341300','宿州市',481,'1','','340000','340000','安徽省','正常',24);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1109,'341302','埇桥区','341300','宿州市',208,'341302','埇桥区','341300','340000','安徽省','正常',14);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1110,'341321','砀山县','341300','宿州市',21,'341321','砀山县','341300','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1111,'341322','萧县','341300','宿州市',57,'341322','萧县','341300','340000','安徽省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1112,'341323','灵璧县','341300','宿州市',79,'341323','灵璧县','341300','340000','安徽省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1113,'341324','泗县','341300','宿州市',24,'341324','泗县','341300','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1114,'341500','六安市','341500','六安市',506,'1','','340000','340000','安徽省','正常',41);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1115,'341502','金安区','341500','六安市',113,'341502','金安区','341500','340000','安徽省','正常',18);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1116,'341503','裕安区','341500','六安市',195,'341503','裕安区','341500','340000','安徽省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1117,'341504','叶集区','341500','六安市',28,'341504','叶集区','341500','340000','安徽省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1118,'341522','霍邱县','341500','六安市',34,'341522','霍邱县','341500','340000','安徽省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1119,'341523','舒城县','341500','六安市',55,'341523','舒城县','341500','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1120,'341524','金寨县','341500','六安市',10,'341524','金寨县','341500','340000','安徽省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1121,'341525','霍山县','341500','六安市',3,'341525','霍山县','341500','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1122,'341600','亳州市','341600','亳州市',649,'1','','340000','340000','安徽省','正常',43);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1123,'341602','谯城区','341600','亳州市',175,'341602','谯城区','341600','340000','安徽省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1124,'341621','涡阳县','341600','亳州市',97,'341621','涡阳县','341600','340000','安徽省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1125,'341622','蒙城县','341600','亳州市',182,'341622','蒙城县','341600','340000','安徽省','正常',14);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1126,'341623','利辛县','341600','亳州市',95,'341623','利辛县','341600','340000','安徽省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1127,'341700','池州市','341700','池州市',86,'1','','340000','340000','安徽省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1128,'341702','贵池区','341700','池州市',47,'341702','贵池区','341700','340000','安徽省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1129,'341721','东至县','341700','池州市',18,'341721','东至县','341700','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1130,'341722','石台县','341700','池州市',0,'341722','石台县','341700','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1131,'341723','青阳县','341700','池州市',7,'341723','青阳县','341700','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1132,'341800','宣城市','341800','宣城市',98,'1','','340000','340000','安徽省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1133,'341802','宣州区','341800','宣城市',33,'341802','宣州区','341800','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1134,'341821','郎溪县','341800','宣城市',19,'341821','郎溪县','341800','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1135,'341822','广德县','341800','宣城市',8,'341822','广德县','341800','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1136,'341823','泾县','341800','宣城市',4,'341823','泾县','341800','340000','安徽省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1137,'341824','绩溪县','341800','宣城市',8,'341824','绩溪县','341800','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1138,'341825','旌德县','341800','宣城市',0,'341825','旌德县','341800','340000','安徽省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1139,'341881','宁国市','341800','宣城市',13,'341881','宁国市','341800','340000','安徽省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1140,'350000','福建省','1','',8605,'','','100000','350000','福建省','正常',464);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1141,'350100','福州市','350100','福州市',1835,'1','','350000','350000','福建省','正常',111);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1142,'350102','鼓楼区','350100','福州市',11,'350102','鼓楼区','350100','350000','福建省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1143,'350103','台江区','350100','福州市',12,'350103','台江区','350100','350000','福建省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1144,'350104','仓山区','350100','福州市',364,'350104','仓山区','350100','350000','福建省','正常',49);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1145,'350105','马尾区','350100','福州市',22,'350105','马尾区','350100','350000','福建省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1146,'350111','晋安区','350100','福州市',142,'350111','晋安区','350100','350000','福建省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1147,'350112','长乐区','350100','福州市',37,'350112','长乐区','350100','350000','福建省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1148,'350121','闽侯县','350100','福州市',190,'350121','闽侯县','350100','350000','福建省','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1149,'350122','连江县','350100','福州市',110,'350122','连江县','350100','350000','福建省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1150,'350123','罗源县','350100','福州市',40,'350123','罗源县','350100','350000','福建省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1151,'350124','闽清县','350100','福州市',0,'350124','闽清县','350100','350000','福建省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1152,'350125','永泰县','350100','福州市',81,'350125','永泰县','350100','350000','福建省','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1153,'350128','平潭县','350100','福州市',49,'350128','平潭县','350100','350000','福建省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1154,'350181','福清市','350100','福州市',257,'350181','福清市','350100','350000','福建省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1155,'350200','厦门市','350200','厦门市',1103,'1','','350000','350000','福建省','正常',45);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1156,'350203','思明区','350200','厦门市',100,'350203','思明区','350200','350000','福建省','正常',18);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1157,'350205','海沧区','350200','厦门市',62,'350205','海沧区','350200','350000','福建省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1158,'350206','湖里区','350200','厦门市',303,'350206','湖里区','350200','350000','福建省','正常',16);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1159,'350211','集美区','350200','厦门市',198,'350211','集美区','350200','350000','福建省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1160,'350212','同安区','350200','厦门市',193,'350212','同安区','350200','350000','福建省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1161,'350213','翔安区','350200','厦门市',57,'350213','翔安区','350200','350000','福建省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1162,'350300','莆田市','350300','莆田市',681,'1','','350000','350000','福建省','正常',36);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1163,'350302','城厢区','350300','莆田市',194,'350302','城厢区','350300','350000','福建省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1164,'350303','涵江区','350300','莆田市',23,'350303','涵江区','350300','350000','福建省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1165,'350304','荔城区','350300','莆田市',150,'350304','荔城区','350300','350000','福建省','正常',14);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1166,'350305','秀屿区','350300','莆田市',27,'350305','秀屿区','350300','350000','福建省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1167,'350322','仙游县','350300','莆田市',111,'350322','仙游县','350300','350000','福建省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1168,'350400','三明市','350400','三明市',438,'1','','350000','350000','福建省','正常',46);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1169,'350402','梅列区','350400','三明市',49,'350402','梅列区','350400','350000','福建省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1170,'350403','三元区','350400','三明市',24,'350403','三元区','350400','350000','福建省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1171,'350421','明溪县','350400','三明市',0,'350421','明溪县','350400','350000','福建省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1172,'350423','清流县','350400','三明市',10,'350423','清流县','350400','350000','福建省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1173,'350424','宁化县','350400','三明市',47,'350424','宁化县','350400','350000','福建省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1174,'350425','大田县','350400','三明市',33,'350425','大田县','350400','350000','福建省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1175,'350426','尤溪县','350400','三明市',51,'350426','尤溪县','350400','350000','福建省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1176,'350427','沙县','350400','三明市',62,'350427','沙县','350400','350000','福建省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1177,'350428','将乐县','350400','三明市',41,'350428','将乐县','350400','350000','福建省','正常',14);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1178,'350429','泰宁县','350400','三明市',15,'350429','泰宁县','350400','350000','福建省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1179,'350430','建宁县','350400','三明市',10,'350430','建宁县','350400','350000','福建省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1180,'350481','永安市','350400','三明市',40,'350481','永安市','350400','350000','福建省','正常',25);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1181,'350500','泉州市','350500','泉州市',1457,'1','','350000','350000','福建省','正常',70);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1182,'350502','鲤城区','350500','泉州市',140,'350502','鲤城区','350500','350000','福建省','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1183,'350503','丰泽区','350500','泉州市',89,'350503','丰泽区','350500','350000','福建省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1184,'350504','洛江区','350500','泉州市',36,'350504','洛江区','350500','350000','福建省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1185,'350505','泉港区','350500','泉州市',29,'350505','泉港区','350500','350000','福建省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1186,'350521','惠安县','350500','泉州市',27,'350521','惠安县','350500','350000','福建省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1187,'350524','安溪县','350500','泉州市',195,'350524','安溪县','350500','350000','福建省','正常',15);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1188,'350525','永春县','350500','泉州市',29,'350525','永春县','350500','350000','福建省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1189,'350526','德化县','350500','泉州市',39,'350526','德化县','350500','350000','福建省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1190,'350527','金门县','350500','泉州市',0,'350527','金门县','350500','350000','福建省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1191,'350581','石狮市','350500','泉州市',101,'350581','石狮市','350500','350000','福建省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1192,'350582','晋江市','350500','泉州市',339,'350582','晋江市','350500','350000','福建省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1193,'350583','南安市','350500','泉州市',77,'350583','南安市','350500','350000','福建省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1194,'350600','漳州市','350600','漳州市',633,'1','','350000','350000','福建省','正常',41);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1195,'350602','芗城区','350600','漳州市',80,'350602','芗城区','350600','350000','福建省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1196,'350603','龙文区','350600','漳州市',61,'350603','龙文区','350600','350000','福建省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1197,'350622','云霄县','350600','漳州市',7,'350622','云霄县','350600','350000','福建省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1198,'350623','漳浦县','350600','漳州市',53,'350623','漳浦县','350600','350000','福建省','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1199,'350624','诏安县','350600','漳州市',42,'350624','诏安县','350600','350000','福建省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1200,'350625','长泰县','350600','漳州市',43,'350625','长泰县','350600','350000','福建省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1201,'350626','东山县','350600','漳州市',2,'350626','东山县','350600','350000','福建省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1202,'350627','南靖县','350600','漳州市',1,'350627','南靖县','350600','350000','福建省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1203,'350628','平和县','350600','漳州市',46,'350628','平和县','350600','350000','福建省','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1204,'350629','华安县','350600','漳州市',6,'350629','华安县','350600','350000','福建省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1205,'350681','龙海市','350600','漳州市',113,'350681','龙海市','350600','350000','福建省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1206,'350700','南平市','350700','南平市',369,'1','','350000','350000','福建省','正常',26);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1207,'350702','延平区','350700','南平市',63,'350702','延平区','350700','350000','福建省','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1208,'350703','建阳区','350700','南平市',50,'350703','建阳区','350700','350000','福建省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1209,'350721','顺昌县','350700','南平市',0,'350721','顺昌县','350700','350000','福建省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1210,'350722','浦城县','350700','南平市',103,'350722','浦城县','350700','350000','福建省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1211,'350723','光泽县','350700','南平市',14,'350723','光泽县','350700','350000','福建省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1212,'350724','松溪县','350700','南平市',9,'350724','松溪县','350700','350000','福建省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1213,'350725','政和县','350700','南平市',55,'350725','政和县','350700','350000','福建省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1214,'350781','邵武市','350700','南平市',19,'350781','邵武市','350700','350000','福建省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1215,'350782','武夷山市','350700','南平市',9,'350782','武夷山市','350700','350000','福建省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1216,'350783','建瓯市','350700','南平市',16,'350783','建瓯市','350700','350000','福建省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1217,'350800','龙岩市','350800','龙岩市',306,'1','','350000','350000','福建省','正常',17);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1218,'350802','新罗区','350800','龙岩市',111,'350802','新罗区','350800','350000','福建省','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1219,'350803','永定区','350800','龙岩市',12,'350803','永定区','350800','350000','福建省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1220,'350821','长汀县','350800','龙岩市',22,'350821','长汀县','350800','350000','福建省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1221,'350823','上杭县','350800','龙岩市',41,'350823','上杭县','350800','350000','福建省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1222,'350824','武平县','350800','龙岩市',4,'350824','武平县','350800','350000','福建省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1223,'350825','连城县','350800','龙岩市',8,'350825','连城县','350800','350000','福建省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1224,'350881','漳平市','350800','龙岩市',49,'350881','漳平市','350800','350000','福建省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1225,'350900','宁德市','350900','宁德市',410,'1','','350000','350000','福建省','正常',53);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1226,'350902','蕉城区','350900','宁德市',68,'350902','蕉城区','350900','350000','福建省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1227,'350921','霞浦县','350900','宁德市',74,'350921','霞浦县','350900','350000','福建省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1228,'350922','古田县','350900','宁德市',6,'350922','古田县','350900','350000','福建省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1229,'350923','屏南县','350900','宁德市',0,'350923','屏南县','350900','350000','福建省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1230,'350924','寿宁县','350900','宁德市',18,'350924','寿宁县','350900','350000','福建省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1231,'350925','周宁县','350900','宁德市',31,'350925','周宁县','350900','350000','福建省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1232,'350926','柘荣县','350900','宁德市',3,'350926','柘荣县','350900','350000','福建省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1233,'350981','福安市','350900','宁德市',83,'350981','福安市','350900','350000','福建省','正常',23);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1234,'350982','福鼎市','350900','宁德市',74,'350982','福鼎市','350900','350000','福建省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1235,'360000','江西省','1','',16909,'','','100000','360000','江西省','正常',604);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1236,'360100','南昌市','360100','南昌市',1529,'1','','360000','360000','江西省','正常',99);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1237,'360102','东湖区','360100','南昌市',97,'360102','东湖区','360100','360000','江西省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1238,'360103','西湖区','360100','南昌市',60,'360103','西湖区','360100','360000','江西省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1239,'360104','青云谱区','360100','南昌市',60,'360104','青云谱区','360100','360000','江西省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1240,'360105','湾里区','360100','南昌市',47,'360105','湾里区','360100','360000','江西省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1241,'360111','青山湖区','360100','南昌市',292,'360111','青山湖区','360100','360000','江西省','正常',31);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1242,'360112','新建区','360100','南昌市',236,'360112','新建区','360100','360000','江西省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1243,'360121','南昌县','360100','南昌市',280,'360121','南昌县','360100','360000','江西省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1244,'360123','安义县','360100','南昌市',1,'360123','安义县','360100','360000','江西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1245,'360124','进贤县','360100','南昌市',239,'360124','进贤县','360100','360000','江西省','正常',21);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1246,'360200','景德镇市','360200','景德镇市',232,'1','','360000','360000','江西省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1247,'360202','昌江区','360200','景德镇市',74,'360202','昌江区','360200','360000','江西省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1248,'360203','珠山区','360200','景德镇市',71,'360203','珠山区','360200','360000','江西省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1249,'360222','浮梁县','360200','景德镇市',22,'360222','浮梁县','360200','360000','江西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1250,'360281','乐平市','360200','景德镇市',43,'360281','乐平市','360200','360000','江西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1251,'360300','萍乡市','360300','萍乡市',90,'1','','360000','360000','江西省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1252,'360302','安源区','360300','萍乡市',55,'360302','安源区','360300','360000','江西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1253,'360313','湘东区','360300','萍乡市',0,'360313','湘东区','360300','360000','江西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1254,'360321','莲花县','360300','萍乡市',0,'360321','莲花县','360300','360000','江西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1255,'360322','上栗县','360300','萍乡市',13,'360322','上栗县','360300','360000','江西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1256,'360323','芦溪县','360300','萍乡市',4,'360323','芦溪县','360300','360000','江西省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1257,'360400','九江市','360400','九江市',726,'1','','360000','360000','江西省','正常',24);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1258,'360402','濂溪区','360400','九江市',49,'360402','濂溪区','360400','360000','江西省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1259,'360403','浔阳区','360400','九江市',108,'360403','浔阳区','360400','360000','江西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1260,'360404','柴桑区','360400','九江市',64,'360404','柴桑区','360400','360000','江西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1261,'360423','武宁县','360400','九江市',30,'360423','武宁县','360400','360000','江西省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1262,'360424','修水县','360400','九江市',128,'360424','修水县','360400','360000','江西省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1263,'360425','永修县','360400','九江市',52,'360425','永修县','360400','360000','江西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1264,'360426','德安县','360400','九江市',13,'360426','德安县','360400','360000','江西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1265,'360428','都昌县','360400','九江市',43,'360428','都昌县','360400','360000','江西省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1266,'360429','湖口县','360400','九江市',41,'360429','湖口县','360400','360000','江西省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1267,'360430','彭泽县','360400','九江市',9,'360430','彭泽县','360400','360000','江西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1268,'360481','瑞昌市','360400','九江市',83,'360481','瑞昌市','360400','360000','江西省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1269,'360482','共青城市','360400','九江市',32,'360482','共青城市','360400','360000','江西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1270,'360483','庐山市','360400','九江市',10,'360483','庐山市','360400','360000','江西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1271,'360500','新余市','360500','新余市',153,'1','','360000','360000','江西省','正常',16);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1272,'360502','渝水区','360500','新余市',115,'360502','渝水区','360500','360000','江西省','正常',12);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1273,'360521','分宜县','360500','新余市',23,'360521','分宜县','360500','360000','江西省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1274,'360600','鹰潭市','360600','鹰潭市',154,'1','','360000','360000','江西省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1275,'360602','月湖区','360600','鹰潭市',90,'360602','月湖区','360600','360000','江西省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1276,'360603','余江区','360600','鹰潭市',3,'360603','余江区','360600','360000','江西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1277,'360681','贵溪市','360600','鹰潭市',45,'360681','贵溪市','360600','360000','江西省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1278,'360700','赣州市','360700','赣州市',1764,'1','','360000','360000','江西省','正常',85);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1279,'360702','章贡区','360700','赣州市',475,'360702','章贡区','360700','360000','江西省','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1280,'360703','南康区','360700','赣州市',209,'360703','南康区','360700','360000','江西省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1281,'360704','赣县区','360700','赣州市',61,'360704','赣县区','360700','360000','江西省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1282,'360722','信丰县','360700','赣州市',66,'360722','信丰县','360700','360000','江西省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1283,'360723','大余县','360700','赣州市',5,'360723','大余县','360700','360000','江西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1284,'360724','上犹县','360700','赣州市',39,'360724','上犹县','360700','360000','江西省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1285,'360725','崇义县','360700','赣州市',3,'360725','崇义县','360700','360000','江西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1286,'360726','安远县','360700','赣州市',32,'360726','安远县','360700','360000','江西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1287,'360727','龙南县','360700','赣州市',17,'360727','龙南县','360700','360000','江西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1288,'360728','定南县','360700','赣州市',55,'360728','定南县','360700','360000','江西省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1289,'360729','全南县','360700','赣州市',36,'360729','全南县','360700','360000','江西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1290,'360730','宁都县','360700','赣州市',128,'360730','宁都县','360700','360000','江西省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1291,'360731','于都县','360700','赣州市',48,'360731','于都县','360700','360000','江西省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1292,'360732','兴国县','360700','赣州市',93,'360732','兴国县','360700','360000','江西省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1293,'360733','会昌县','360700','赣州市',37,'360733','会昌县','360700','360000','江西省','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1294,'360734','寻乌县','360700','赣州市',20,'360734','寻乌县','360700','360000','江西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1295,'360735','石城县','360700','赣州市',24,'360735','石城县','360700','360000','江西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1296,'360781','瑞金市','360700','赣州市',79,'360781','瑞金市','360700','360000','江西省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1297,'360800','吉安市','360800','吉安市',842,'1','','360000','360000','江西省','正常',65);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1298,'360802','吉州区','360800','吉安市',84,'360802','吉州区','360800','360000','江西省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1299,'360803','青原区','360800','吉安市',49,'360803','青原区','360800','360000','江西省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1300,'360821','吉安县','360800','吉安市',116,'360821','吉安县','360800','360000','江西省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1301,'360822','吉水县','360800','吉安市',35,'360822','吉水县','360800','360000','江西省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1302,'360823','峡江县','360800','吉安市',7,'360823','峡江县','360800','360000','江西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1303,'360824','新干县','360800','吉安市',107,'360824','新干县','360800','360000','江西省','正常',12);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1304,'360825','永丰县','360800','吉安市',60,'360825','永丰县','360800','360000','江西省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1305,'360826','泰和县','360800','吉安市',98,'360826','泰和县','360800','360000','江西省','正常',20);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1306,'360827','遂川县','360800','吉安市',109,'360827','遂川县','360800','360000','江西省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1307,'360828','万安县','360800','吉安市',17,'360828','万安县','360800','360000','江西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1308,'360829','安福县','360800','吉安市',15,'360829','安福县','360800','360000','江西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1309,'360830','永新县','360800','吉安市',14,'360830','永新县','360800','360000','江西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1310,'360881','井冈山市','360800','吉安市',6,'360881','井冈山市','360800','360000','江西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1311,'360900','宜春市','360900','宜春市',1006,'1','','360000','360000','江西省','正常',83);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1312,'360902','袁州区','360900','宜春市',312,'360902','袁州区','360900','360000','江西省','正常',59);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1313,'360921','奉新县','360900','宜春市',61,'360921','奉新县','360900','360000','江西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1314,'360922','万载县','360900','宜春市',54,'360922','万载县','360900','360000','江西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1315,'360923','上高县','360900','宜春市',23,'360923','上高县','360900','360000','江西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1316,'360924','宜丰县','360900','宜春市',14,'360924','宜丰县','360900','360000','江西省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1317,'360925','靖安县','360900','宜春市',12,'360925','靖安县','360900','360000','江西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1318,'360926','铜鼓县','360900','宜春市',10,'360926','铜鼓县','360900','360000','江西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1319,'360981','丰城市','360900','宜春市',226,'360981','丰城市','360900','360000','江西省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1320,'360982','樟树市','360900','宜春市',123,'360982','樟树市','360900','360000','江西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1321,'360983','高安市','360900','宜春市',56,'360983','高安市','360900','360000','江西省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1322,'361000','抚州市','361000','抚州市',527,'1','','360000','360000','江西省','正常',28);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1323,'361002','临川区','361000','抚州市',223,'361002','临川区','361000','360000','江西省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1324,'361003','东乡区','361000','抚州市',26,'361003','东乡区','361000','360000','江西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1325,'361021','南城县','361000','抚州市',98,'361021','南城县','361000','360000','江西省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1326,'361022','黎川县','361000','抚州市',11,'361022','黎川县','361000','360000','江西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1327,'361023','南丰县','361000','抚州市',26,'361023','南丰县','361000','360000','江西省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1328,'361024','崇仁县','361000','抚州市',18,'361024','崇仁县','361000','360000','江西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1329,'361025','乐安县','361000','抚州市',23,'361025','乐安县','361000','360000','江西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1330,'361026','宜黄县','361000','抚州市',22,'361026','宜黄县','361000','360000','江西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1331,'361027','金溪县','361000','抚州市',20,'361027','金溪县','361000','360000','江西省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1332,'361028','资溪县','361000','抚州市',0,'361028','资溪县','361000','360000','江西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1333,'361030','广昌县','361000','抚州市',10,'361030','广昌县','361000','360000','江西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1334,'361100','上饶市','361100','上饶市',543,'1','','360000','360000','江西省','正常',35);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1335,'361102','信州区','361100','上饶市',51,'361102','信州区','361100','360000','江西省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1336,'361103','广丰区','361100','上饶市',74,'361103','广丰区','361100','360000','江西省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1337,'361121','上饶县','361100','上饶市',20,'361121','上饶县','361100','360000','江西省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1338,'361123','玉山县','361100','上饶市',44,'361123','玉山县','361100','360000','江西省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1339,'361124','铅山县','361100','上饶市',18,'361124','铅山县','361100','360000','江西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1340,'361125','横峰县','361100','上饶市',13,'361125','横峰县','361100','360000','江西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1341,'361126','弋阳县','361100','上饶市',32,'361126','弋阳县','361100','360000','江西省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1342,'361127','余干县','361100','上饶市',35,'361127','余干县','361100','360000','江西省','正常',14);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1343,'361128','鄱阳县','361100','上饶市',79,'361128','鄱阳县','361100','360000','江西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1344,'361129','万年县','361100','上饶市',56,'361129','万年县','361100','360000','江西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1345,'361130','婺源县','361100','上饶市',4,'361130','婺源县','361100','360000','江西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1346,'361181','德兴市','361100','上饶市',28,'361181','德兴市','361100','360000','江西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1347,'370000','山东省','1','',40347,'','','100000','370000','山东省','正常',1445);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1348,'370100','济南市','370100','济南市',1951,'1','','370000','370000','山东省','正常',101);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1349,'370102','历下区','370100','济南市',135,'370102','历下区','370100','370000','山东省','正常',12);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1350,'370103','市中区','370100','济南市',143,'370103','市中区','370100','370000','山东省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1351,'370104','槐荫区','370100','济南市',157,'370104','槐荫区','370100','370000','山东省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1352,'370105','天桥区','370100','济南市',215,'370105','天桥区','370100','370000','山东省','正常',15);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1353,'370112','历城区','370100','济南市',344,'370112','历城区','370100','370000','山东省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1354,'370113','长清区','370100','济南市',93,'370113','长清区','370100','370000','山东省','正常',21);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1355,'370114','章丘区','370100','济南市',161,'370114','章丘区','370100','370000','山东省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1356,'370124','平阴县','370100','济南市',18,'370124','平阴县','370100','370000','山东省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1357,'370115','济阳区','370100','济南市',41,'370115','济阳区','370100','370000','山东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1358,'370126','商河县','370100','济南市',89,'370126','商河县','370100','370000','山东省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1359,'370200','青岛市','370200','青岛市',2479,'1','','370000','370000','山东省','正常',191);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1360,'370202','市南区','370200','青岛市',98,'370202','市南区','370200','370000','山东省','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1361,'370203','市北区','370200','青岛市',412,'370203','市北区','370200','370000','山东省','正常',57);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1362,'370211','黄岛区','370200','青岛市',248,'370211','黄岛区','370200','370000','山东省','正常',22);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1363,'370212','崂山区','370200','青岛市',190,'370212','崂山区','370200','370000','山东省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1364,'370213','李沧区','370200','青岛市',141,'370213','李沧区','370200','370000','山东省','正常',15);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1365,'370214','城阳区','370200','青岛市',255,'370214','城阳区','370200','370000','山东省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1366,'370215','即墨区','370200','青岛市',335,'370215','即墨区','370200','370000','山东省','正常',16);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1367,'370281','胶州市','370200','青岛市',184,'370281','胶州市','370200','370000','山东省','正常',30);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1368,'370283','平度市','370200','青岛市',95,'370283','平度市','370200','370000','山东省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1369,'370285','莱西市','370200','青岛市',121,'370285','莱西市','370200','370000','山东省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1370,'370300','淄博市','370300','淄博市',789,'1','','370000','370000','山东省','正常',55);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1371,'370302','淄川区','370300','淄博市',126,'370302','淄川区','370300','370000','山东省','正常',15);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1372,'370303','张店区','370300','淄博市',279,'370303','张店区','370300','370000','山东省','正常',24);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1373,'370304','博山区','370300','淄博市',38,'370304','博山区','370300','370000','山东省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1374,'370305','临淄区','370300','淄博市',92,'370305','临淄区','370300','370000','山东省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1375,'370306','周村区','370300','淄博市',44,'370306','周村区','370300','370000','山东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1376,'370321','桓台县','370300','淄博市',105,'370321','桓台县','370300','370000','山东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1377,'370322','高青县','370300','淄博市',17,'370322','高青县','370300','370000','山东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1378,'370323','沂源县','370300','淄博市',29,'370323','沂源县','370300','370000','山东省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1379,'370400','枣庄市','370400','枣庄市',760,'1','','370000','370000','山东省','正常',49);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1380,'370402','市中区','370400','枣庄市',166,'370402','市中区','370400','370000','山东省','正常',16);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1381,'370403','薛城区','370400','枣庄市',62,'370403','薛城区','370400','370000','山东省','正常',16);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1382,'370404','峄城区','370400','枣庄市',26,'370404','峄城区','370400','370000','山东省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1383,'370405','台儿庄区','370400','枣庄市',83,'370405','台儿庄区','370400','370000','山东省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1384,'370406','山亭区','370400','枣庄市',9,'370406','山亭区','370400','370000','山东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1385,'370481','滕州市','370400','枣庄市',269,'370481','滕州市','370400','370000','山东省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1386,'370500','东营市','370500','东营市',390,'1','','370000','370000','山东省','正常',20);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1387,'370502','东营区','370500','东营市',265,'370502','东营区','370500','370000','山东省','正常',12);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1388,'370503','河口区','370500','东营市',8,'370503','河口区','370500','370000','山东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1389,'370505','垦利区','370500','东营市',40,'370505','垦利区','370500','370000','山东省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1390,'370522','利津县','370500','东营市',4,'370522','利津县','370500','370000','山东省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1391,'370523','广饶县','370500','东营市',25,'370523','广饶县','370500','370000','山东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1392,'370600','烟台市','370600','烟台市',999,'1','','370000','370000','山东省','正常',33);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1393,'370602','芝罘区','370600','烟台市',112,'370602','芝罘区','370600','370000','山东省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1394,'370611','福山区','370600','烟台市',181,'370611','福山区','370600','370000','山东省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1395,'370612','牟平区','370600','烟台市',56,'370612','牟平区','370600','370000','山东省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1396,'370613','莱山区','370600','烟台市',82,'370613','莱山区','370600','370000','山东省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1397,'370634','长岛县','370600','烟台市',5,'370634','长岛县','370600','370000','山东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1398,'370681','龙口市','370600','烟台市',184,'370681','龙口市','370600','370000','山东省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1399,'370682','莱阳市','370600','烟台市',47,'370682','莱阳市','370600','370000','山东省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1400,'370683','莱州市','370600','烟台市',41,'370683','莱州市','370600','370000','山东省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1401,'370684','蓬莱市','370600','烟台市',32,'370684','蓬莱市','370600','370000','山东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1402,'370685','招远市','370600','烟台市',118,'370685','招远市','370600','370000','山东省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1403,'370686','栖霞市','370600','烟台市',15,'370686','栖霞市','370600','370000','山东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1404,'370687','海阳市','370600','烟台市',20,'370687','海阳市','370600','370000','山东省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1405,'370700','潍坊市','370700','潍坊市',2177,'1','','370000','370000','山东省','正常',115);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1406,'370702','潍城区','370700','潍坊市',71,'370702','潍城区','370700','370000','山东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1407,'370703','寒亭区','370700','潍坊市',50,'370703','寒亭区','370700','370000','山东省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1408,'370704','坊子区','370700','潍坊市',113,'370704','坊子区','370700','370000','山东省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1409,'370705','奎文区','370700','潍坊市',367,'370705','奎文区','370700','370000','山东省','正常',28);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1410,'370724','临朐县','370700','潍坊市',22,'370724','临朐县','370700','370000','山东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1411,'370725','昌乐县','370700','潍坊市',81,'370725','昌乐县','370700','370000','山东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1412,'370781','青州市','370700','潍坊市',79,'370781','青州市','370700','370000','山东省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1413,'370782','诸城市','370700','潍坊市',71,'370782','诸城市','370700','370000','山东省','正常',22);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1414,'370783','寿光市','370700','潍坊市',759,'370783','寿光市','370700','370000','山东省','正常',22);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1415,'370784','安丘市','370700','潍坊市',85,'370784','安丘市','370700','370000','山东省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1416,'370785','高密市','370700','潍坊市',127,'370785','高密市','370700','370000','山东省','正常',22);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1417,'370786','昌邑市','370700','潍坊市',45,'370786','昌邑市','370700','370000','山东省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1418,'370800','济宁市','370800','济宁市',1851,'1','','370000','370000','山东省','正常',53);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1419,'370811','任城区','370800','济宁市',227,'370811','任城区','370800','370000','山东省','正常',12);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1420,'370812','兖州区','370800','济宁市',221,'370812','兖州区','370800','370000','山东省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1421,'370826','微山县','370800','济宁市',10,'370826','微山县','370800','370000','山东省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1422,'370827','鱼台县','370800','济宁市',11,'370827','鱼台县','370800','370000','山东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1423,'370828','金乡县','370800','济宁市',98,'370828','金乡县','370800','370000','山东省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1424,'370829','嘉祥县','370800','济宁市',286,'370829','嘉祥县','370800','370000','山东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1425,'370830','汶上县','370800','济宁市',119,'370830','汶上县','370800','370000','山东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1426,'370831','泗水县','370800','济宁市',35,'370831','泗水县','370800','370000','山东省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1427,'370832','梁山县','370800','济宁市',150,'370832','梁山县','370800','370000','山东省','正常',14);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1428,'370881','曲阜市','370800','济宁市',32,'370881','曲阜市','370800','370000','山东省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1429,'370883','邹城市','370800','济宁市',320,'370883','邹城市','370800','370000','山东省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1430,'370900','泰安市','370900','泰安市',657,'1','','370000','370000','山东省','正常',22);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1431,'370902','泰山区','370900','泰安市',256,'370902','泰山区','370900','370000','山东省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1432,'370911','岱岳区','370900','泰安市',102,'370911','岱岳区','370900','370000','山东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1433,'370921','宁阳县','370900','泰安市',28,'370921','宁阳县','370900','370000','山东省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1434,'370923','东平县','370900','泰安市',61,'370923','东平县','370900','370000','山东省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1435,'370982','新泰市','370900','泰安市',34,'370982','新泰市','370900','370000','山东省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1436,'370983','肥城市','370900','泰安市',63,'370983','肥城市','370900','370000','山东省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1437,'371000','威海市','371000','威海市',624,'1','','370000','370000','山东省','正常',68);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1438,'371002','环翠区','371000','威海市',178,'371002','环翠区','371000','370000','山东省','正常',14);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1439,'371003','文登区','371000','威海市',295,'371003','文登区','371000','370000','山东省','正常',12);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1440,'371082','荣成市','371000','威海市',76,'371082','荣成市','371000','370000','山东省','正常',40);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1441,'371083','乳山市','371000','威海市',11,'371083','乳山市','371000','370000','山东省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1442,'371100','日照市','371100','日照市',377,'1','','370000','370000','山东省','正常',18);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1443,'371102','东港区','371100','日照市',189,'371102','东港区','371100','370000','山东省','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1444,'371103','岚山区','371100','日照市',21,'371103','岚山区','371100','370000','山东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1445,'371121','五莲县','371100','日照市',27,'371121','五莲县','371100','370000','山东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1446,'371122','莒县','371100','日照市',106,'371122','莒县','371100','370000','山东省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1447,'370116','莱芜区','370100','济南市',138,'370116','莱芜区','370100','370000','山东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1448,'370117','钢城区','370100','济南市',33,'370117','钢城区','370100','370000','山东省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1449,'371300','临沂市','371300','临沂市',1959,'1','','370000','370000','山东省','正常',132);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1450,'371302','兰山区','371300','临沂市',519,'371302','兰山区','371300','370000','山东省','正常',44);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1451,'371311','罗庄区','371300','临沂市',115,'371311','罗庄区','371300','370000','山东省','正常',12);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1452,'371312','河东区','371300','临沂市',85,'371312','河东区','371300','370000','山东省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1453,'371321','沂南县','371300','临沂市',48,'371321','沂南县','371300','370000','山东省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1454,'371322','郯城县','371300','临沂市',90,'371322','郯城县','371300','370000','山东省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1455,'371323','沂水县','371300','临沂市',72,'371323','沂水县','371300','370000','山东省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1456,'371324','兰陵县','371300','临沂市',150,'371324','兰陵县','371300','370000','山东省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1457,'371325','费县','371300','临沂市',133,'371325','费县','371300','370000','山东省','正常',27);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1458,'371326','平邑县','371300','临沂市',44,'371326','平邑县','371300','370000','山东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1459,'371327','莒南县','371300','临沂市',34,'371327','莒南县','371300','370000','山东省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1460,'371328','蒙阴县','371300','临沂市',155,'371328','蒙阴县','371300','370000','山东省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1461,'371329','临沭县','371300','临沂市',70,'371329','临沭县','371300','370000','山东省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1462,'371400','德州市','371400','德州市',1086,'1','','370000','370000','山东省','正常',47);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1463,'371402','德城区','371400','德州市',578,'371402','德城区','371400','370000','山东省','正常',16);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1464,'371403','陵城区','371400','德州市',58,'371403','陵城区','371400','370000','山东省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1465,'371422','宁津县','371400','德州市',28,'371422','宁津县','371400','370000','山东省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1466,'371423','庆云县','371400','德州市',18,'371423','庆云县','371400','370000','山东省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1467,'371424','临邑县','371400','德州市',35,'371424','临邑县','371400','370000','山东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1468,'371425','齐河县','371400','德州市',26,'371425','齐河县','371400','370000','山东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1469,'371426','平原县','371400','德州市',30,'371426','平原县','371400','370000','山东省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1470,'371427','夏津县','371400','德州市',42,'371427','夏津县','371400','370000','山东省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1471,'371428','武城县','371400','德州市',60,'371428','武城县','371400','370000','山东省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1472,'371481','乐陵市','371400','德州市',47,'371481','乐陵市','371400','370000','山东省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1473,'371482','禹城市','371400','德州市',55,'371482','禹城市','371400','370000','山东省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1474,'371500','聊城市','371500','聊城市',757,'1','','370000','370000','山东省','正常',36);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1475,'371502','东昌府区','371500','聊城市',192,'371502','东昌府区','371500','370000','山东省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1476,'371521','阳谷县','371500','聊城市',18,'371521','阳谷县','371500','370000','山东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1477,'371522','莘县','371500','聊城市',33,'371522','莘县','371500','370000','山东省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1478,'371523','茌平县','371500','聊城市',23,'371523','茌平县','371500','370000','山东省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1479,'371524','东阿县','371500','聊城市',98,'371524','东阿县','371500','370000','山东省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1480,'371525','冠县','371500','聊城市',49,'371525','冠县','371500','370000','山东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1481,'371526','高唐县','371500','聊城市',16,'371526','高唐县','371500','370000','山东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1482,'371581','临清市','371500','聊城市',137,'371581','临清市','371500','370000','山东省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1483,'371600','滨州市','371600','滨州市',773,'1','','370000','370000','山东省','正常',39);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1484,'371602','滨城区','371600','滨州市',187,'371602','滨城区','371600','370000','山东省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1485,'371603','沾化区','371600','滨州市',23,'371603','沾化区','371600','370000','山东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1486,'371621','惠民县','371600','滨州市',75,'371621','惠民县','371600','370000','山东省','正常',20);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1487,'371622','阳信县','371600','滨州市',3,'371622','阳信县','371600','370000','山东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1488,'371623','无棣县','371600','滨州市',11,'371623','无棣县','371600','370000','山东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1489,'371625','博兴县','371600','滨州市',55,'371625','博兴县','371600','370000','山东省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1490,'371681','邹平市','371600','滨州市',304,'371681','邹平市','371600','370000','山东省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1491,'371700','菏泽市','371700','菏泽市',1235,'1','','370000','370000','山东省','正常',76);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1492,'371702','牡丹区','371700','菏泽市',376,'371702','牡丹区','371700','370000','山东省','正常',30);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1493,'371703','定陶区','371700','菏泽市',70,'371703','定陶区','371700','370000','山东省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1494,'371721','曹县','371700','菏泽市',132,'371721','曹县','371700','370000','山东省','正常',18);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1495,'371722','单县','371700','菏泽市',71,'371722','单县','371700','370000','山东省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1496,'371723','成武县','371700','菏泽市',48,'371723','成武县','371700','370000','山东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1497,'371724','巨野县','371700','菏泽市',31,'371724','巨野县','371700','370000','山东省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1498,'371725','郓城县','371700','菏泽市',71,'371725','郓城县','371700','370000','山东省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1499,'371726','鄄城县','371700','菏泽市',85,'371726','鄄城县','371700','370000','山东省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1500,'371728','东明县','371700','菏泽市',66,'371728','东明县','371700','370000','山东省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1501,'410000','河南省','1','',65523,'','','100000','410000','河南省','正常',2607);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1502,'410100','郑州市','410100','郑州市',4781,'1','','410000','410000','河南省','正常',682);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1503,'410102','中原区','410100','郑州市',285,'410102','中原区','410100','410000','河南省','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1504,'410103','二七区','410100','郑州市',208,'410103','二七区','410100','410000','河南省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1505,'410104','管城回族区','410100','郑州市',370,'410104','管城回族区','410100','410000','河南省','正常',41);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1506,'410105','金水区','410100','郑州市',1016,'410105','金水区','410100','410000','河南省','正常',397);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1507,'410106','上街区','410100','郑州市',66,'410106','上街区','410100','410000','河南省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1508,'410108','惠济区','410100','郑州市',189,'410108','惠济区','410100','410000','河南省','正常',21);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1509,'410122','中牟县','410100','郑州市',335,'410122','中牟县','410100','410000','河南省','正常',27);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1510,'410181','巩义市','410100','郑州市',95,'410181','巩义市','410100','410000','河南省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1511,'410182','荥阳市','410100','郑州市',365,'410182','荥阳市','410100','410000','河南省','正常',19);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1512,'410183','新密市','410100','郑州市',168,'410183','新密市','410100','410000','河南省','正常',19);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1513,'410184','新郑市','410100','郑州市',211,'410184','新郑市','410100','410000','河南省','正常',18);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1514,'410185','登封市','410100','郑州市',299,'410185','登封市','410100','410000','河南省','正常',58);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1515,'410200','开封市','410200','开封市',593,'1','','410000','410000','河南省','正常',37);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1516,'410202','龙亭区','410200','开封市',39,'410202','龙亭区','410200','410000','河南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1517,'410203','顺河回族区','410200','开封市',23,'410203','顺河回族区','410200','410000','河南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1518,'410204','鼓楼区','410200','开封市',41,'410204','鼓楼区','410200','410000','河南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1519,'410205','禹王台区','410200','开封市',13,'410205','禹王台区','410200','410000','河南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1520,'410212','祥符区','410200','开封市',38,'410212','祥符区','410200','410000','河南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1521,'410221','杞县','410200','开封市',88,'410221','杞县','410200','410000','河南省','正常',25);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1522,'410222','通许县','410200','开封市',38,'410222','通许县','410200','410000','河南省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1523,'410223','尉氏县','410200','开封市',109,'410223','尉氏县','410200','410000','河南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1524,'410225','兰考县','410200','开封市',31,'410225','兰考县','410200','410000','河南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1525,'410300','洛阳市','410300','洛阳市',1440,'1','','410000','410000','河南省','正常',57);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1526,'410302','老城区','410300','洛阳市',48,'410302','老城区','410300','410000','河南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1527,'410303','西工区','410300','洛阳市',98,'410303','西工区','410300','410000','河南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1528,'410304','瀍河回族区','410300','洛阳市',82,'410304','瀍河回族区','410300','410000','河南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1529,'410305','涧西区','410300','洛阳市',104,'410305','涧西区','410300','410000','河南省','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1530,'410306','吉利区','410300','洛阳市',31,'410306','吉利区','410300','410000','河南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1531,'410311','洛龙区','410300','洛阳市',139,'410311','洛龙区','410300','410000','河南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1532,'410322','孟津县','410300','洛阳市',35,'410322','孟津县','410300','410000','河南省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1533,'410323','新安县','410300','洛阳市',48,'410323','新安县','410300','410000','河南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1534,'410324','栾川县','410300','洛阳市',28,'410324','栾川县','410300','410000','河南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1535,'410325','嵩县','410300','洛阳市',119,'410325','嵩县','410300','410000','河南省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1536,'410326','汝阳县','410300','洛阳市',52,'410326','汝阳县','410300','410000','河南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1537,'410327','宜阳县','410300','洛阳市',158,'410327','宜阳县','410300','410000','河南省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1538,'410328','洛宁县','410300','洛阳市',5,'410328','洛宁县','410300','410000','河南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1539,'410329','伊川县','410300','洛阳市',125,'410329','伊川县','410300','410000','河南省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1540,'410381','偃师市','410300','洛阳市',89,'410381','偃师市','410300','410000','河南省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1541,'410400','平顶山市','410400','平顶山市',966,'1','','410000','410000','河南省','正常',61);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1542,'410402','新华区','410400','平顶山市',47,'410402','新华区','410400','410000','河南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1543,'410403','卫东区','410400','平顶山市',63,'410403','卫东区','410400','410000','河南省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1544,'410404','石龙区','410400','平顶山市',3,'410404','石龙区','410400','410000','河南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1545,'410411','湛河区','410400','平顶山市',62,'410411','湛河区','410400','410000','河南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1546,'410421','宝丰县','410400','平顶山市',89,'410421','宝丰县','410400','410000','河南省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1547,'410422','叶县','410400','平顶山市',103,'410422','叶县','410400','410000','河南省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1548,'410423','鲁山县','410400','平顶山市',58,'410423','鲁山县','410400','410000','河南省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1549,'410425','郏县','410400','平顶山市',51,'410425','郏县','410400','410000','河南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1550,'410481','舞钢市','410400','平顶山市',60,'410481','舞钢市','410400','410000','河南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1551,'410482','汝州市','410400','平顶山市',202,'410482','汝州市','410400','410000','河南省','正常',23);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1552,'410500','安阳市','410500','安阳市',802,'1','','410000','410000','河南省','正常',55);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1553,'410502','文峰区','410500','安阳市',169,'410502','文峰区','410500','410000','河南省','正常',12);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1554,'410503','北关区','410500','安阳市',67,'410503','北关区','410500','410000','河南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1555,'410505','殷都区','410500','安阳市',47,'410505','殷都区','410500','410000','河南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1556,'410506','龙安区','410500','安阳市',70,'410506','龙安区','410500','410000','河南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1557,'410522','安阳县','410500','安阳市',68,'410522','安阳县','410500','410000','河南省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1558,'410523','汤阴县','410500','安阳市',30,'410523','汤阴县','410500','410000','河南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1559,'410526','滑县','410500','安阳市',54,'410526','滑县','410500','410000','河南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1560,'410527','内黄县','410500','安阳市',46,'410527','内黄县','410500','410000','河南省','正常',23);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1561,'410581','林州市','410500','安阳市',115,'410581','林州市','410500','410000','河南省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1562,'410600','鹤壁市','410600','鹤壁市',459,'1','','410000','410000','河南省','正常',15);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1563,'410602','鹤山区','410600','鹤壁市',0,'410602','鹤山区','410600','410000','河南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1564,'410603','山城区','410600','鹤壁市',105,'410603','山城区','410600','410000','河南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1565,'410611','淇滨区','410600','鹤壁市',167,'410611','淇滨区','410600','410000','河南省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1566,'410621','浚县','410600','鹤壁市',67,'410621','浚县','410600','410000','河南省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1567,'410622','淇县','410600','鹤壁市',67,'410622','淇县','410600','410000','河南省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1568,'410700','新乡市','410700','新乡市',741,'1','','410000','410000','河南省','正常',41);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1569,'410702','红旗区','410700','新乡市',78,'410702','红旗区','410700','410000','河南省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1570,'410703','卫滨区','410700','新乡市',40,'410703','卫滨区','410700','410000','河南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1571,'410704','凤泉区','410700','新乡市',8,'410704','凤泉区','410700','410000','河南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1572,'410711','牧野区','410700','新乡市',72,'410711','牧野区','410700','410000','河南省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1573,'410721','新乡县','410700','新乡市',19,'410721','新乡县','410700','410000','河南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1574,'410724','获嘉县','410700','新乡市',42,'410724','获嘉县','410700','410000','河南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1575,'410725','原阳县','410700','新乡市',54,'410725','原阳县','410700','410000','河南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1576,'410726','延津县','410700','新乡市',11,'410726','延津县','410700','410000','河南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1577,'410727','封丘县','410700','新乡市',13,'410727','封丘县','410700','410000','河南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1578,'410728','长垣县','410700','新乡市',25,'410728','长垣县','410700','410000','河南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1579,'410781','卫辉市','410700','新乡市',72,'410781','卫辉市','410700','410000','河南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1580,'410782','辉县市','410700','新乡市',160,'410782','辉县市','410700','410000','河南省','正常',15);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1581,'410800','焦作市','410800','焦作市',676,'1','','410000','410000','河南省','正常',38);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1582,'410802','解放区','410800','焦作市',119,'410802','解放区','410800','410000','河南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1583,'410803','中站区','410800','焦作市',11,'410803','中站区','410800','410000','河南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1584,'410804','马村区','410800','焦作市',17,'410804','马村区','410800','410000','河南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1585,'410811','山阳区','410800','焦作市',102,'410811','山阳区','410800','410000','河南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1586,'410821','修武县','410800','焦作市',34,'410821','修武县','410800','410000','河南省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1587,'410822','博爱县','410800','焦作市',46,'410822','博爱县','410800','410000','河南省','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1588,'410823','武陟县','410800','焦作市',147,'410823','武陟县','410800','410000','河南省','正常',14);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1589,'410825','温县','410800','焦作市',24,'410825','温县','410800','410000','河南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1590,'410882','沁阳市','410800','焦作市',56,'410882','沁阳市','410800','410000','河南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1591,'410883','孟州市','410800','焦作市',27,'410883','孟州市','410800','410000','河南省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1592,'410900','濮阳市','410900','濮阳市',802,'1','','410000','410000','河南省','正常',70);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1593,'410902','华龙区','410900','濮阳市',314,'410902','华龙区','410900','410000','河南省','正常',22);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1594,'410922','清丰县','410900','濮阳市',47,'410922','清丰县','410900','410000','河南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1595,'410923','南乐县','410900','濮阳市',17,'410923','南乐县','410900','410000','河南省','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1596,'410926','范县','410900','濮阳市',58,'410926','范县','410900','410000','河南省','正常',17);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1597,'410927','台前县','410900','濮阳市',76,'410927','台前县','410900','410000','河南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1598,'410928','濮阳县','410900','濮阳市',165,'410928','濮阳县','410900','410000','河南省','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1599,'411000','许昌市','411000','许昌市',860,'1','','410000','410000','河南省','正常',59);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1600,'411002','魏都区','411000','许昌市',257,'411002','魏都区','411000','410000','河南省','正常',22);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1601,'411003','建安区','411000','许昌市',66,'411003','建安区','411000','410000','河南省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1602,'411024','鄢陵县','411000','许昌市',53,'411024','鄢陵县','411000','410000','河南省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1603,'411025','襄城县','411000','许昌市',164,'411025','襄城县','411000','410000','河南省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1604,'411081','禹州市','411000','许昌市',129,'411081','禹州市','411000','410000','河南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1605,'411082','长葛市','411000','许昌市',61,'411082','长葛市','411000','410000','河南省','正常',14);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1606,'411100','漯河市','411100','漯河市',431,'1','','410000','410000','河南省','正常',34);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1607,'411102','源汇区','411100','漯河市',66,'411102','源汇区','411100','410000','河南省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1608,'411103','郾城区','411100','漯河市',36,'411103','郾城区','411100','410000','河南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1609,'411104','召陵区','411100','漯河市',129,'411104','召陵区','411100','410000','河南省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1610,'411121','舞阳县','411100','漯河市',14,'411121','舞阳县','411100','410000','河南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1611,'411122','临颍县','411100','漯河市',128,'411122','临颍县','411100','410000','河南省','正常',23);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1612,'411200','三门峡市','411200','三门峡市',251,'1','','410000','410000','河南省','正常',15);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1613,'411202','湖滨区','411200','三门峡市',59,'411202','湖滨区','411200','410000','河南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1614,'411203','陕州区','411200','三门峡市',13,'411203','陕州区','411200','410000','河南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1615,'411221','渑池县','411200','三门峡市',44,'411221','渑池县','411200','410000','河南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1616,'411224','卢氏县','411200','三门峡市',44,'411224','卢氏县','411200','410000','河南省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1617,'411281','义马市','411200','三门峡市',18,'411281','义马市','411200','410000','河南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1618,'411282','灵宝市','411200','三门峡市',55,'411282','灵宝市','411200','410000','河南省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1619,'411300','南阳市','411300','南阳市',2208,'1','','410000','410000','河南省','正常',133);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1620,'411302','宛城区','411300','南阳市',274,'411302','宛城区','411300','410000','河南省','正常',15);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1621,'411303','卧龙区','411300','南阳市',454,'411303','卧龙区','411300','410000','河南省','正常',29);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1622,'411321','南召县','411300','南阳市',24,'411321','南召县','411300','410000','河南省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1623,'411322','方城县','411300','南阳市',136,'411322','方城县','411300','410000','河南省','正常',19);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1624,'411323','西峡县','411300','南阳市',74,'411323','西峡县','411300','410000','河南省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1625,'411324','镇平县','411300','南阳市',129,'411324','镇平县','411300','410000','河南省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1626,'411325','内乡县','411300','南阳市',43,'411325','内乡县','411300','410000','河南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1627,'411326','淅川县','411300','南阳市',148,'411326','淅川县','411300','410000','河南省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1628,'411327','社旗县','411300','南阳市',64,'411327','社旗县','411300','410000','河南省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1629,'411328','唐河县','411300','南阳市',95,'411328','唐河县','411300','410000','河南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1630,'411329','新野县','411300','南阳市',48,'411329','新野县','411300','410000','河南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1631,'411330','桐柏县','411300','南阳市',40,'411330','桐柏县','411300','410000','河南省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1632,'411381','邓州市','411300','南阳市',337,'411381','邓州市','411300','410000','河南省','正常',20);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1633,'411400','商丘市','411400','商丘市',1148,'1','','410000','410000','河南省','正常',62);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1634,'411402','梁园区','411400','商丘市',177,'411402','梁园区','411400','410000','河南省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1635,'411403','睢阳区','411400','商丘市',105,'411403','睢阳区','411400','410000','河南省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1636,'411421','民权县','411400','商丘市',63,'411421','民权县','411400','410000','河南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1637,'411422','睢县','411400','商丘市',86,'411422','睢县','411400','410000','河南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1638,'411423','宁陵县','411400','商丘市',47,'411423','宁陵县','411400','410000','河南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1639,'411424','柘城县','411400','商丘市',24,'411424','柘城县','411400','410000','河南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1640,'411425','虞城县','411400','商丘市',33,'411425','虞城县','411400','410000','河南省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1641,'411426','夏邑县','411400','商丘市',119,'411426','夏邑县','411400','410000','河南省','正常',16);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1642,'411481','永城市','411400','商丘市',173,'411481','永城市','411400','410000','河南省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1643,'411500','信阳市','411500','信阳市',1163,'1','','410000','410000','河南省','正常',93);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1644,'411502','浉河区','411500','信阳市',156,'411502','浉河区','411500','410000','河南省','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1645,'411503','平桥区','411500','信阳市',188,'411503','平桥区','411500','410000','河南省','正常',36);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1646,'411521','罗山县','411500','信阳市',79,'411521','罗山县','411500','410000','河南省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1647,'411522','光山县','411500','信阳市',174,'411522','光山县','411500','410000','河南省','正常',14);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1648,'411523','新县','411500','信阳市',22,'411523','新县','411500','410000','河南省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1649,'411524','商城县','411500','信阳市',127,'411524','商城县','411500','410000','河南省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1650,'411525','固始县','411500','信阳市',86,'411525','固始县','411500','410000','河南省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1651,'411526','潢川县','411500','信阳市',134,'411526','潢川县','411500','410000','河南省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1652,'411527','淮滨县','411500','信阳市',9,'411527','淮滨县','411500','410000','河南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1653,'411528','息县','411500','信阳市',71,'411528','息县','411500','410000','河南省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1654,'411600','周口市','411600','周口市',1348,'1','','410000','410000','河南省','正常',116);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1655,'411602','川汇区','411600','周口市',76,'411602','川汇区','411600','410000','河南省','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1656,'411621','扶沟县','411600','周口市',27,'411621','扶沟县','411600','410000','河南省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1657,'411622','西华县','411600','周口市',4,'411622','西华县','411600','410000','河南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1658,'411623','商水县','411600','周口市',27,'411623','商水县','411600','410000','河南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1659,'411624','沈丘县','411600','周口市',93,'411624','沈丘县','411600','410000','河南省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1660,'411625','郸城县','411600','周口市',189,'411625','郸城县','411600','410000','河南省','正常',34);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1661,'411626','淮阳县','411600','周口市',30,'411626','淮阳县','411600','410000','河南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1662,'411627','太康县','411600','周口市',58,'411627','太康县','411600','410000','河南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1663,'411628','鹿邑县','411600','周口市',124,'411628','鹿邑县','411600','410000','河南省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1664,'411681','项城市','411600','周口市',215,'411681','项城市','411600','410000','河南省','正常',12);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1665,'411700','驻马店市','411700','驻马店市',943,'1','','410000','410000','河南省','正常',75);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1666,'411702','驿城区','411700','驻马店市',235,'411702','驿城区','411700','410000','河南省','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1667,'411721','西平县','411700','驻马店市',22,'411721','西平县','411700','410000','河南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1668,'411722','上蔡县','411700','驻马店市',97,'411722','上蔡县','411700','410000','河南省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1669,'411723','平舆县','411700','驻马店市',33,'411723','平舆县','411700','410000','河南省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1670,'411724','正阳县','411700','驻马店市',26,'411724','正阳县','411700','410000','河南省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1671,'411725','确山县','411700','驻马店市',34,'411725','确山县','411700','410000','河南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1672,'411726','泌阳县','411700','驻马店市',281,'411726','泌阳县','411700','410000','河南省','正常',14);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1673,'411727','汝南县','411700','驻马店市',7,'411727','汝南县','411700','410000','河南省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1674,'411728','遂平县','411700','驻马店市',27,'411728','遂平县','411700','410000','河南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1675,'411729','新蔡县','411700','驻马店市',11,'411729','新蔡县','411700','410000','河南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1676,'419001','济源市','419000','直辖市县区',86,'419001','济源市','419000','410000','河南省','正常',12);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1677,'420000','湖北省','1','',11984,'','','100000','420000','湖北省','正常',672);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1678,'420100','武汉市','420100','武汉市',2715,'1','','420000','420000','湖北省','正常',183);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1679,'420102','江岸区','420100','武汉市',151,'420102','江岸区','420100','420000','湖北省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1680,'420103','江汉区','420100','武汉市',58,'420103','江汉区','420100','420000','湖北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1681,'420104','硚口区','420100','武汉市',24,'420104','硚口区','420100','420000','湖北省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1682,'420105','汉阳区','420100','武汉市',137,'420105','汉阳区','420100','420000','湖北省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1683,'420106','武昌区','420100','武汉市',96,'420106','武昌区','420100','420000','湖北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1684,'420107','青山区','420100','武汉市',45,'420107','青山区','420100','420000','湖北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1685,'420111','洪山区','420100','武汉市',558,'420111','洪山区','420100','420000','湖北省','正常',58);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1686,'420112','东西湖区','420100','武汉市',117,'420112','东西湖区','420100','420000','湖北省','正常',26);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1687,'420113','汉南区','420100','武汉市',18,'420113','汉南区','420100','420000','湖北省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1688,'420114','蔡甸区','420100','武汉市',77,'420114','蔡甸区','420100','420000','湖北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1689,'420115','江夏区','420100','武汉市',257,'420115','江夏区','420100','420000','湖北省','正常',30);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1690,'420116','黄陂区','420100','武汉市',71,'420116','黄陂区','420100','420000','湖北省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1691,'420117','新洲区','420100','武汉市',74,'420117','新洲区','420100','420000','湖北省','正常',17);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1692,'420200','黄石市','420200','黄石市',879,'1','','420000','420000','湖北省','正常',26);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1693,'420202','黄石港区','420200','黄石市',19,'420202','黄石港区','420200','420000','湖北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1694,'420203','西塞山区','420200','黄石市',14,'420203','西塞山区','420200','420000','湖北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1695,'420204','下陆区','420200','黄石市',50,'420204','下陆区','420200','420000','湖北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1696,'420205','铁山区','420200','黄石市',1,'420205','铁山区','420200','420000','湖北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1697,'420222','阳新县','420200','黄石市',423,'420222','阳新县','420200','420000','湖北省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1698,'420281','大冶市','420200','黄石市',224,'420281','大冶市','420200','420000','湖北省','正常',12);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1699,'420300','十堰市','420300','十堰市',832,'1','','420000','420000','湖北省','正常',62);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1700,'420302','茅箭区','420300','十堰市',149,'420302','茅箭区','420300','420000','湖北省','正常',22);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1701,'420303','张湾区','420300','十堰市',158,'420303','张湾区','420300','420000','湖北省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1702,'420304','郧阳区','420300','十堰市',48,'420304','郧阳区','420300','420000','湖北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1703,'420322','郧西县','420300','十堰市',27,'420322','郧西县','420300','420000','湖北省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1704,'420323','竹山县','420300','十堰市',48,'420323','竹山县','420300','420000','湖北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1705,'420324','竹溪县','420300','十堰市',31,'420324','竹溪县','420300','420000','湖北省','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1706,'420325','房县','420300','十堰市',13,'420325','房县','420300','420000','湖北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1707,'420381','丹江口市','420300','十堰市',165,'420381','丹江口市','420300','420000','湖北省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1708,'420500','宜昌市','420500','宜昌市',291,'1','','420000','420000','湖北省','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1709,'420502','西陵区','420500','宜昌市',50,'420502','西陵区','420500','420000','湖北省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1710,'420503','伍家岗区','420500','宜昌市',10,'420503','伍家岗区','420500','420000','湖北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1711,'420504','点军区','420500','宜昌市',0,'420504','点军区','420500','420000','湖北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1712,'420505','猇亭区','420500','宜昌市',8,'420505','猇亭区','420500','420000','湖北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1713,'420506','夷陵区','420500','宜昌市',57,'420506','夷陵区','420500','420000','湖北省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1714,'420525','远安县','420500','宜昌市',0,'420525','远安县','420500','420000','湖北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1715,'420526','兴山县','420500','宜昌市',1,'420526','兴山县','420500','420000','湖北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1716,'420527','秭归县','420500','宜昌市',25,'420527','秭归县','420500','420000','湖北省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1717,'420528','长阳土家族自治县','420500','宜昌市',25,'420528','长阳土家族自治县','420500','420000','湖北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1718,'420529','五峰土家族自治县','420500','宜昌市',0,'420529','五峰土家族自治县','420500','420000','湖北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1719,'420581','宜都市','420500','宜昌市',40,'420581','宜都市','420500','420000','湖北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1720,'420582','当阳市','420500','宜昌市',24,'420582','当阳市','420500','420000','湖北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1721,'420583','枝江市','420500','宜昌市',11,'420583','枝江市','420500','420000','湖北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1722,'420600','襄阳市','420600','襄阳市',1221,'1','','420000','420000','湖北省','正常',68);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1723,'420602','襄城区','420600','襄阳市',62,'420602','襄城区','420600','420000','湖北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1724,'420606','樊城区','420600','襄阳市',357,'420606','樊城区','420600','420000','湖北省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1725,'420607','襄州区','420600','襄阳市',196,'420607','襄州区','420600','420000','湖北省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1726,'420624','南漳县','420600','襄阳市',6,'420624','南漳县','420600','420000','湖北省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1727,'420625','谷城县','420600','襄阳市',38,'420625','谷城县','420600','420000','湖北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1728,'420626','保康县','420600','襄阳市',5,'420626','保康县','420600','420000','湖北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1729,'420682','老河口市','420600','襄阳市',73,'420682','老河口市','420600','420000','湖北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1730,'420683','枣阳市','420600','襄阳市',111,'420683','枣阳市','420600','420000','湖北省','正常',38);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1731,'420684','宜城市','420600','襄阳市',17,'420684','宜城市','420600','420000','湖北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1732,'420700','鄂州市','420700','鄂州市',75,'1','','420000','420000','湖北省','正常',17);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1733,'420702','梁子湖区','420700','鄂州市',9,'420702','梁子湖区','420700','420000','湖北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1734,'420703','华容区','420700','鄂州市',24,'420703','华容区','420700','420000','湖北省','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1735,'420704','鄂城区','420700','鄂州市',26,'420704','鄂城区','420700','420000','湖北省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1736,'420800','荆门市','420800','荆门市',332,'1','','420000','420000','湖北省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1737,'420802','东宝区','420800','荆门市',23,'420802','东宝区','420800','420000','湖北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1738,'420804','掇刀区','420800','荆门市',57,'420804','掇刀区','420800','420000','湖北省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1739,'420882','京山市','420800','荆门市',22,'420882','京山市','420800','420000','湖北省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1740,'420822','沙洋县','420800','荆门市',92,'420822','沙洋县','420800','420000','湖北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1741,'420881','钟祥市','420800','荆门市',55,'420881','钟祥市','420800','420000','湖北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1742,'420900','孝感市','420900','孝感市',659,'1','','420000','420000','湖北省','正常',32);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1743,'420902','孝南区','420900','孝感市',99,'420902','孝南区','420900','420000','湖北省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1744,'420921','孝昌县','420900','孝感市',60,'420921','孝昌县','420900','420000','湖北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1745,'420922','大悟县','420900','孝感市',33,'420922','大悟县','420900','420000','湖北省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1746,'420923','云梦县','420900','孝感市',193,'420923','云梦县','420900','420000','湖北省','正常',17);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1747,'420981','应城市','420900','孝感市',36,'420981','应城市','420900','420000','湖北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1748,'420982','安陆市','420900','孝感市',64,'420982','安陆市','420900','420000','湖北省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1749,'420984','汉川市','420900','孝感市',73,'420984','汉川市','420900','420000','湖北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1750,'421000','荆州市','421000','荆州市',684,'1','','420000','420000','湖北省','正常',35);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1751,'421002','沙市区','421000','荆州市',109,'421002','沙市区','421000','420000','湖北省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1752,'421003','荆州区','421000','荆州市',30,'421003','荆州区','421000','420000','湖北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1753,'421022','公安县','421000','荆州市',41,'421022','公安县','421000','420000','湖北省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1754,'421023','监利县','421000','荆州市',173,'421023','监利县','421000','420000','湖北省','正常',17);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1755,'421024','江陵县','421000','荆州市',3,'421024','江陵县','421000','420000','湖北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1756,'421081','石首市','421000','荆州市',42,'421081','石首市','421000','420000','湖北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1757,'421083','洪湖市','421000','荆州市',26,'421083','洪湖市','421000','420000','湖北省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1758,'421087','松滋市','421000','荆州市',155,'421087','松滋市','421000','420000','湖北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1759,'421100','黄冈市','421100','黄冈市',629,'1','','420000','420000','湖北省','正常',12);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1760,'421102','黄州区','421100','黄冈市',32,'421102','黄州区','421100','420000','湖北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1761,'421121','团风县','421100','黄冈市',10,'421121','团风县','421100','420000','湖北省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1762,'421122','红安县','421100','黄冈市',187,'421122','红安县','421100','420000','湖北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1763,'421123','罗田县','421100','黄冈市',26,'421123','罗田县','421100','420000','湖北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1764,'421124','英山县','421100','黄冈市',54,'421124','英山县','421100','420000','湖北省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1765,'421125','浠水县','421100','黄冈市',24,'421125','浠水县','421100','420000','湖北省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1766,'421126','蕲春县','421100','黄冈市',4,'421126','蕲春县','421100','420000','湖北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1767,'421127','黄梅县','421100','黄冈市',18,'421127','黄梅县','421100','420000','湖北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1768,'421181','麻城市','421100','黄冈市',62,'421181','麻城市','421100','420000','湖北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1769,'421182','武穴市','421100','黄冈市',46,'421182','武穴市','421100','420000','湖北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1770,'421200','咸宁市','421200','咸宁市',569,'1','','420000','420000','湖北省','正常',57);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1771,'421202','咸安区','421200','咸宁市',135,'421202','咸安区','421200','420000','湖北省','正常',23);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1772,'421221','嘉鱼县','421200','咸宁市',17,'421221','嘉鱼县','421200','420000','湖北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1773,'421222','通城县','421200','咸宁市',79,'421222','通城县','421200','420000','湖北省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1774,'421223','崇阳县','421200','咸宁市',64,'421223','崇阳县','421200','420000','湖北省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1775,'421224','通山县','421200','咸宁市',77,'421224','通山县','421200','420000','湖北省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1776,'421281','赤壁市','421200','咸宁市',145,'421281','赤壁市','421200','420000','湖北省','正常',18);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1777,'421300','随州市','421300','随州市',255,'1','','420000','420000','湖北省','正常',26);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1778,'421303','曾都区','421300','随州市',103,'421303','曾都区','421300','420000','湖北省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1779,'421321','随县','421300','随州市',8,'421321','随县','421300','420000','湖北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1780,'421381','广水市','421300','随州市',113,'421381','广水市','421300','420000','湖北省','正常',20);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1781,'422800','恩施土家族苗族自治州','422800','恩施土家族苗族自治州',465,'1','','420000','420000','湖北省','正常',76);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1782,'422801','恩施市','422800','恩施土家族苗族自治州',148,'422801','恩施市','422800','420000','湖北省','正常',67);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1783,'422802','利川市','422800','恩施土家族苗族自治州',106,'422802','利川市','422800','420000','湖北省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1784,'422822','建始县','422800','恩施土家族苗族自治州',11,'422822','建始县','422800','420000','湖北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1785,'422823','巴东县','422800','恩施土家族苗族自治州',61,'422823','巴东县','422800','420000','湖北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1786,'422825','宣恩县','422800','恩施土家族苗族自治州',26,'422825','宣恩县','422800','420000','湖北省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1787,'422826','咸丰县','422800','恩施土家族苗族自治州',2,'422826','咸丰县','422800','420000','湖北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1788,'422827','来凤县','422800','恩施土家族苗族自治州',6,'422827','来凤县','422800','420000','湖北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1789,'422828','鹤峰县','422800','恩施土家族苗族自治州',5,'422828','鹤峰县','422800','420000','湖北省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1790,'429004','仙桃市','429000','直辖市县区',155,'429004','仙桃市','429000','420000','湖北省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1791,'429005','潜江市','429000','直辖市县区',89,'429005','潜江市','429000','420000','湖北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1792,'429006','天门市','429000','直辖市县区',110,'429006','天门市','429000','420000','湖北省','正常',15);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1793,'429021','神农架林区','429000','直辖市县区',6,'429021','神农架林区','429000','420000','湖北省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1794,'430000','湖南省','1','',21540,'','','100000','430000','湖南省','正常',1036);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1795,'430100','长沙市','430100','长沙市',1732,'1','','430000','430000','湖南省','正常',195);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1796,'430102','芙蓉区','430100','长沙市',70,'430102','芙蓉区','430100','430000','湖南省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1797,'430103','天心区','430100','长沙市',122,'430103','天心区','430100','430000','湖南省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1798,'430104','岳麓区','430100','长沙市',175,'430104','岳麓区','430100','430000','湖南省','正常',20);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1799,'430105','开福区','430100','长沙市',87,'430105','开福区','430100','430000','湖南省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1800,'430111','雨花区','430100','长沙市',305,'430111','雨花区','430100','430000','湖南省','正常',111);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1801,'430112','望城区','430100','长沙市',85,'430112','望城区','430100','430000','湖南省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1802,'430121','长沙县','430100','长沙市',197,'430121','长沙县','430100','430000','湖南省','正常',15);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1803,'430181','浏阳市','430100','长沙市',175,'430181','浏阳市','430100','430000','湖南省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1804,'430182','宁乡市','430100','长沙市',133,'430182','宁乡市','430100','430000','湖南省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1805,'430200','株洲市','430200','株洲市',370,'1','','430000','430000','湖南省','正常',27);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1806,'430202','荷塘区','430200','株洲市',70,'430202','荷塘区','430200','430000','湖南省','正常',23);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1807,'430203','芦淞区','430200','株洲市',127,'430203','芦淞区','430200','430000','湖南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1808,'430204','石峰区','430200','株洲市',36,'430204','石峰区','430200','430000','湖南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1809,'430211','天元区','430200','株洲市',20,'430211','天元区','430200','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1810,'430212','渌口区','430200','株洲市',55,'430212','渌口区','430200','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1811,'430223','攸县','430200','株洲市',1,'430223','攸县','430200','430000','湖南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1812,'430224','茶陵县','430200','株洲市',7,'430224','茶陵县','430200','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1813,'430225','炎陵县','430200','株洲市',0,'430225','炎陵县','430200','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1814,'430281','醴陵市','430200','株洲市',4,'430281','醴陵市','430200','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1815,'430300','湘潭市','430300','湘潭市',299,'1','','430000','430000','湖南省','正常',46);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1816,'430302','雨湖区','430300','湘潭市',78,'430302','雨湖区','430300','430000','湖南省','正常',30);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1817,'430304','岳塘区','430300','湘潭市',92,'430304','岳塘区','430300','430000','湖南省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1818,'430321','湘潭县','430300','湘潭市',76,'430321','湘潭县','430300','430000','湖南省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1819,'430381','湘乡市','430300','湘潭市',20,'430381','湘乡市','430300','430000','湖南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1820,'430382','韶山市','430300','湘潭市',3,'430382','韶山市','430300','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1821,'430400','衡阳市','430400','衡阳市',1315,'1','','430000','430000','湖南省','正常',72);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1822,'430405','珠晖区','430400','衡阳市',182,'430405','珠晖区','430400','430000','湖南省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1823,'430406','雁峰区','430400','衡阳市',48,'430406','雁峰区','430400','430000','湖南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1824,'430407','石鼓区','430400','衡阳市',60,'430407','石鼓区','430400','430000','湖南省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1825,'430408','蒸湘区','430400','衡阳市',201,'430408','蒸湘区','430400','430000','湖南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1826,'430412','南岳区','430400','衡阳市',1,'430412','南岳区','430400','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1827,'430421','衡阳县','430400','衡阳市',84,'430421','衡阳县','430400','430000','湖南省','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1828,'430422','衡南县','430400','衡阳市',93,'430422','衡南县','430400','430000','湖南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1829,'430423','衡山县','430400','衡阳市',18,'430423','衡山县','430400','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1830,'430424','衡东县','430400','衡阳市',29,'430424','衡东县','430400','430000','湖南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1831,'430426','祁东县','430400','衡阳市',29,'430426','祁东县','430400','430000','湖南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1832,'430481','耒阳市','430400','衡阳市',154,'430481','耒阳市','430400','430000','湖南省','正常',14);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1833,'430482','常宁市','430400','衡阳市',118,'430482','常宁市','430400','430000','湖南省','正常',15);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1834,'430500','邵阳市','430500','邵阳市',861,'1','','430000','430000','湖南省','正常',120);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1835,'430502','双清区','430500','邵阳市',78,'430502','双清区','430500','430000','湖南省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1836,'430503','大祥区','430500','邵阳市',77,'430503','大祥区','430500','430000','湖南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1837,'430511','北塔区','430500','邵阳市',6,'430511','北塔区','430500','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1838,'430521','邵东县','430500','邵阳市',68,'430521','邵东县','430500','430000','湖南省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1839,'430522','新邵县','430500','邵阳市',115,'430522','新邵县','430500','430000','湖南省','正常',87);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1840,'430523','邵阳县','430500','邵阳市',42,'430523','邵阳县','430500','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1841,'430524','隆回县','430500','邵阳市',119,'430524','隆回县','430500','430000','湖南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1842,'430525','洞口县','430500','邵阳市',107,'430525','洞口县','430500','430000','湖南省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1843,'430527','绥宁县','430500','邵阳市',1,'430527','绥宁县','430500','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1844,'430528','新宁县','430500','邵阳市',10,'430528','新宁县','430500','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1845,'430529','城步苗族自治县','430500','邵阳市',0,'430529','城步苗族自治县','430500','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1846,'430581','武冈市','430500','邵阳市',35,'430581','武冈市','430500','430000','湖南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1847,'430600','岳阳市','430600','岳阳市',483,'1','','430000','430000','湖南省','正常',12);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1848,'430602','岳阳楼区','430600','岳阳市',98,'430602','岳阳楼区','430600','430000','湖南省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1849,'430603','云溪区','430600','岳阳市',22,'430603','云溪区','430600','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1850,'430611','君山区','430600','岳阳市',13,'430611','君山区','430600','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1851,'430621','岳阳县','430600','岳阳市',25,'430621','岳阳县','430600','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1852,'430623','华容县','430600','岳阳市',6,'430623','华容县','430600','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1853,'430624','湘阴县','430600','岳阳市',9,'430624','湘阴县','430600','430000','湖南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1854,'430626','平江县','430600','岳阳市',43,'430626','平江县','430600','430000','湖南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1855,'430681','汨罗市','430600','岳阳市',25,'430681','汨罗市','430600','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1856,'430682','临湘市','430600','岳阳市',192,'430682','临湘市','430600','430000','湖南省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1857,'430700','常德市','430700','常德市',253,'1','','430000','430000','湖南省','正常',40);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1858,'430702','武陵区','430700','常德市',72,'430702','武陵区','430700','430000','湖南省','正常',26);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1859,'430703','鼎城区','430700','常德市',6,'430703','鼎城区','430700','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1860,'430721','安乡县','430700','常德市',4,'430721','安乡县','430700','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1861,'430722','汉寿县','430700','常德市',24,'430722','汉寿县','430700','430000','湖南省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1862,'430723','澧县','430700','常德市',58,'430723','澧县','430700','430000','湖南省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1863,'430724','临澧县','430700','常德市',6,'430724','临澧县','430700','430000','湖南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1864,'430725','桃源县','430700','常德市',8,'430725','桃源县','430700','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1865,'430726','石门县','430700','常德市',5,'430726','石门县','430700','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1866,'430781','津市市','430700','常德市',13,'430781','津市市','430700','430000','湖南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1867,'430800','张家界市','430800','张家界市',62,'1','','430000','430000','湖南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1868,'430802','永定区','430800','张家界市',43,'430802','永定区','430800','430000','湖南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1869,'430811','武陵源区','430800','张家界市',7,'430811','武陵源区','430800','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1870,'430821','慈利县','430800','张家界市',7,'430821','慈利县','430800','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1871,'430822','桑植县','430800','张家界市',2,'430822','桑植县','430800','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1872,'430900','益阳市','430900','益阳市',317,'1','','430000','430000','湖南省','正常',20);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1873,'430902','资阳区','430900','益阳市',0,'430902','资阳区','430900','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1874,'430903','赫山区','430900','益阳市',197,'430903','赫山区','430900','430000','湖南省','正常',15);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1875,'430921','南县','430900','益阳市',40,'430921','南县','430900','430000','湖南省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1876,'430922','桃江县','430900','益阳市',11,'430922','桃江县','430900','430000','湖南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1877,'430923','安化县','430900','益阳市',2,'430923','安化县','430900','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1878,'430981','沅江市','430900','益阳市',7,'430981','沅江市','430900','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1879,'431000','郴州市','431000','郴州市',735,'1','','430000','430000','湖南省','正常',72);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1880,'431002','北湖区','431000','郴州市',119,'431002','北湖区','431000','430000','湖南省','正常',27);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1881,'431003','苏仙区','431000','郴州市',55,'431003','苏仙区','431000','430000','湖南省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1882,'431021','桂阳县','431000','郴州市',62,'431021','桂阳县','431000','430000','湖南省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1883,'431022','宜章县','431000','郴州市',52,'431022','宜章县','431000','430000','湖南省','正常',12);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1884,'431023','永兴县','431000','郴州市',44,'431023','永兴县','431000','430000','湖南省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1885,'431024','嘉禾县','431000','郴州市',112,'431024','嘉禾县','431000','430000','湖南省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1886,'431025','临武县','431000','郴州市',44,'431025','临武县','431000','430000','湖南省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1887,'431026','汝城县','431000','郴州市',34,'431026','汝城县','431000','430000','湖南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1888,'431027','桂东县','431000','郴州市',0,'431027','桂东县','431000','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1889,'431028','安仁县','431000','郴州市',37,'431028','安仁县','431000','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1890,'431081','资兴市','431000','郴州市',35,'431081','资兴市','431000','430000','湖南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1891,'431100','永州市','431100','永州市',596,'1','','430000','430000','湖南省','正常',37);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1892,'431102','零陵区','431100','永州市',21,'431102','零陵区','431100','430000','湖南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1893,'431103','冷水滩区','431100','永州市',103,'431103','冷水滩区','431100','430000','湖南省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1894,'431121','祁阳县','431100','永州市',29,'431121','祁阳县','431100','430000','湖南省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1895,'431122','东安县','431100','永州市',22,'431122','东安县','431100','430000','湖南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1896,'431123','双牌县','431100','永州市',12,'431123','双牌县','431100','430000','湖南省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1897,'431124','道县','431100','永州市',153,'431124','道县','431100','430000','湖南省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1898,'431125','江永县','431100','永州市',12,'431125','江永县','431100','430000','湖南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1899,'431126','宁远县','431100','永州市',52,'431126','宁远县','431100','430000','湖南省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1900,'431127','蓝山县','431100','永州市',47,'431127','蓝山县','431100','430000','湖南省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1901,'431128','新田县','431100','永州市',16,'431128','新田县','431100','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1902,'431129','江华瑶族自治县','431100','永州市',26,'431129','江华瑶族自治县','431100','430000','湖南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1903,'431200','怀化市','431200','怀化市',456,'1','','430000','430000','湖南省','正常',36);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1904,'431202','鹤城区','431200','怀化市',154,'431202','鹤城区','431200','430000','湖南省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1905,'431221','中方县','431200','怀化市',32,'431221','中方县','431200','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1906,'431222','沅陵县','431200','怀化市',10,'431222','沅陵县','431200','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1907,'431223','辰溪县','431200','怀化市',13,'431223','辰溪县','431200','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1908,'431224','溆浦县','431200','怀化市',19,'431224','溆浦县','431200','430000','湖南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1909,'431225','会同县','431200','怀化市',7,'431225','会同县','431200','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1910,'431226','麻阳苗族自治县','431200','怀化市',15,'431226','麻阳苗族自治县','431200','430000','湖南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1911,'431227','新晃侗族自治县','431200','怀化市',13,'431227','新晃侗族自治县','431200','430000','湖南省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1912,'431228','芷江侗族自治县','431200','怀化市',41,'431228','芷江侗族自治县','431200','430000','湖南省','正常',12);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1913,'431229','靖州苗族侗族自治县','431200','怀化市',14,'431229','靖州苗族侗族自治县','431200','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1914,'431230','通道侗族自治县','431200','怀化市',4,'431230','通道侗族自治县','431200','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1915,'431281','洪江市','431200','怀化市',4,'431281','洪江市','431200','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1916,'431300','娄底市','431300','娄底市',508,'1','','430000','430000','湖南省','正常',19);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1917,'431302','娄星区','431300','娄底市',110,'431302','娄星区','431300','430000','湖南省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1918,'431321','双峰县','431300','娄底市',46,'431321','双峰县','431300','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1919,'431322','新化县','431300','娄底市',180,'431322','新化县','431300','430000','湖南省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1920,'431381','冷水江市','431300','娄底市',64,'431381','冷水江市','431300','430000','湖南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1921,'431382','涟源市','431300','娄底市',31,'431382','涟源市','431300','430000','湖南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1922,'433100','湘西土家族苗族自治州','433100','湘西土家族苗族自治州',404,'1','','430000','430000','湖南省','正常',68);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1923,'433101','吉首市','433100','湘西土家族苗族自治州',215,'433101','吉首市','433100','430000','湖南省','正常',61);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1924,'433122','泸溪县','433100','湘西土家族苗族自治州',6,'433122','泸溪县','433100','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1925,'433123','凤凰县','433100','湘西土家族苗族自治州',9,'433123','凤凰县','433100','430000','湖南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1926,'433124','花垣县','433100','湘西土家族苗族自治州',3,'433124','花垣县','433100','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1927,'433125','保靖县','433100','湘西土家族苗族自治州',0,'433125','保靖县','433100','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1928,'433126','古丈县','433100','湘西土家族苗族自治州',0,'433126','古丈县','433100','430000','湖南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1929,'433127','永顺县','433100','湘西土家族苗族自治州',30,'433127','永顺县','433100','430000','湖南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1930,'433130','龙山县','433100','湘西土家族苗族自治州',121,'433130','龙山县','433100','430000','湖南省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1931,'440000','广东省','1','',57140,'','','100000','440000','广东省','正常',2631);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1932,'440100','广州市','440100','广州市',4617,'1','','440000','440000','广东省','正常',379);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1933,'440103','荔湾区','440100','广州市',87,'440103','荔湾区','440100','440000','广东省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1934,'440104','越秀区','440100','广州市',798,'440104','越秀区','440100','440000','广东省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1935,'440105','海珠区','440100','广州市',180,'440105','海珠区','440100','440000','广东省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1936,'440106','天河区','440100','广州市',490,'440106','天河区','440100','440000','广东省','正常',29);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1937,'440111','白云区','440100','广州市',851,'440111','白云区','440100','440000','广东省','正常',197);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1938,'440112','黄埔区','440100','广州市',199,'440112','黄埔区','440100','440000','广东省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1939,'440113','番禺区','440100','广州市',697,'440113','番禺区','440100','440000','广东省','正常',55);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1940,'440114','花都区','440100','广州市',339,'440114','花都区','440100','440000','广东省','正常',45);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1941,'440115','南沙区','440100','广州市',67,'440115','南沙区','440100','440000','广东省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1942,'440117','从化区','440100','广州市',36,'440117','从化区','440100','440000','广东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1943,'440118','增城区','440100','广州市',382,'440118','增城区','440100','440000','广东省','正常',16);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1944,'440200','韶关市','440200','韶关市',326,'1','','440000','440000','广东省','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1945,'440203','武江区','440200','韶关市',85,'440203','武江区','440200','440000','广东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1946,'440204','浈江区','440200','韶关市',92,'440204','浈江区','440200','440000','广东省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1947,'440205','曲江区','440200','韶关市',8,'440205','曲江区','440200','440000','广东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1948,'440222','始兴县','440200','韶关市',12,'440222','始兴县','440200','440000','广东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1949,'440224','仁化县','440200','韶关市',7,'440224','仁化县','440200','440000','广东省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1950,'440229','翁源县','440200','韶关市',15,'440229','翁源县','440200','440000','广东省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1951,'440232','乳源瑶族自治县','440200','韶关市',34,'440232','乳源瑶族自治县','440200','440000','广东省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1952,'440233','新丰县','440200','韶关市',1,'440233','新丰县','440200','440000','广东省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1953,'440281','乐昌市','440200','韶关市',14,'440281','乐昌市','440200','440000','广东省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1954,'440282','南雄市','440200','韶关市',8,'440282','南雄市','440200','440000','广东省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1955,'440300','深圳市','440300','深圳市',3900,'1','','440000','440000','广东省','正常',207);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1956,'440303','罗湖区','440300','深圳市',97,'440303','罗湖区','440300','440000','广东省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1957,'440304','福田区','440300','深圳市',476,'440304','福田区','440300','440000','广东省','正常',52);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1958,'440305','南山区','440300','深圳市',402,'440305','南山区','440300','440000','广东省','正常',19);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1959,'440306','宝安区','440300','深圳市',648,'440306','宝安区','440300','440000','广东省','正常',34);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1960,'440307','龙岗区','440300','深圳市',864,'440307','龙岗区','440300','440000','广东省','正常',41);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1961,'440308','盐田区','440300','深圳市',21,'440308','盐田区','440300','440000','广东省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1962,'440309','龙华区','440300','深圳市',435,'440309','龙华区','440300','440000','广东省','正常',27);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1963,'440310','坪山区','440300','深圳市',130,'440310','坪山区','440300','440000','广东省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1964,'440311','光明区','440300','深圳市',234,'440311','光明区','440300','440000','广东省','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1965,'440400','珠海市','440400','珠海市',659,'1','','440000','440000','广东省','正常',40);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1966,'440402','香洲区','440400','珠海市',321,'440402','香洲区','440400','440000','广东省','正常',33);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1967,'440403','斗门区','440400','珠海市',132,'440403','斗门区','440400','440000','广东省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1968,'440404','金湾区','440400','珠海市',135,'440404','金湾区','440400','440000','广东省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1969,'440500','汕头市','440500','汕头市',1463,'1','','440000','440000','广东省','正常',227);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1970,'440507','龙湖区','440500','汕头市',94,'440507','龙湖区','440500','440000','广东省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1971,'440511','金平区','440500','汕头市',224,'440511','金平区','440500','440000','广东省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1972,'440512','濠江区','440500','汕头市',37,'440512','濠江区','440500','440000','广东省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1973,'440513','潮阳区','440500','汕头市',188,'440513','潮阳区','440500','440000','广东省','正常',18);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1974,'440514','潮南区','440500','汕头市',201,'440514','潮南区','440500','440000','广东省','正常',16);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1975,'440515','澄海区','440500','汕头市',503,'440515','澄海区','440500','440000','广东省','正常',155);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1976,'440523','南澳县','440500','汕头市',0,'440523','南澳县','440500','440000','广东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1977,'440600','佛山市','440600','佛山市',2679,'1','','440000','440000','广东省','正常',249);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1978,'440604','禅城区','440600','佛山市',318,'440604','禅城区','440600','440000','广东省','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1979,'440605','南海区','440600','佛山市',967,'440605','南海区','440600','440000','广东省','正常',120);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1980,'440606','顺德区','440600','佛山市',843,'440606','顺德区','440600','440000','广东省','正常',50);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1981,'440607','三水区','440600','佛山市',219,'440607','三水区','440600','440000','广东省','正常',42);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1982,'440608','高明区','440600','佛山市',70,'440608','高明区','440600','440000','广东省','正常',17);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1983,'440700','江门市','440700','江门市',651,'1','','440000','440000','广东省','正常',60);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1984,'440703','蓬江区','440700','江门市',272,'440703','蓬江区','440700','440000','广东省','正常',42);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1985,'440704','江海区','440700','江门市',44,'440704','江海区','440700','440000','广东省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1986,'440705','新会区','440700','江门市',122,'440705','新会区','440700','440000','广东省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1987,'440781','台山市','440700','江门市',32,'440781','台山市','440700','440000','广东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1988,'440783','开平市','440700','江门市',102,'440783','开平市','440700','440000','广东省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1989,'440784','鹤山市','440700','江门市',13,'440784','鹤山市','440700','440000','广东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1990,'440785','恩平市','440700','江门市',15,'440785','恩平市','440700','440000','广东省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1991,'440800','湛江市','440800','湛江市',900,'1','','440000','440000','广东省','正常',54);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1992,'440802','赤坎区','440800','湛江市',68,'440802','赤坎区','440800','440000','广东省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1993,'440803','霞山区','440800','湛江市',123,'440803','霞山区','440800','440000','广东省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1994,'440804','坡头区','440800','湛江市',30,'440804','坡头区','440800','440000','广东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1995,'440811','麻章区','440800','湛江市',83,'440811','麻章区','440800','440000','广东省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1996,'440823','遂溪县','440800','湛江市',66,'440823','遂溪县','440800','440000','广东省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1997,'440825','徐闻县','440800','湛江市',43,'440825','徐闻县','440800','440000','广东省','正常',12);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1998,'440881','廉江市','440800','湛江市',71,'440881','廉江市','440800','440000','广东省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (1999,'440882','雷州市','440800','湛江市',119,'440882','雷州市','440800','440000','广东省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2000,'440883','吴川市','440800','湛江市',102,'440883','吴川市','440800','440000','广东省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2001,'440900','茂名市','440900','茂名市',958,'1','','440000','440000','广东省','正常',85);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2002,'440902','茂南区','440900','茂名市',87,'440902','茂南区','440900','440000','广东省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2003,'440904','电白区','440900','茂名市',335,'440904','电白区','440900','440000','广东省','正常',41);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2004,'440981','高州市','440900','茂名市',95,'440981','高州市','440900','440000','广东省','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2005,'440982','化州市','440900','茂名市',156,'440982','化州市','440900','440000','广东省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2006,'440983','信宜市','440900','茂名市',154,'440983','信宜市','440900','440000','广东省','正常',20);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2007,'441200','肇庆市','441200','肇庆市',533,'1','','440000','440000','广东省','正常',79);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2008,'441202','端州区','441200','肇庆市',103,'441202','端州区','441200','440000','广东省','正常',18);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2009,'441203','鼎湖区','441200','肇庆市',7,'441203','鼎湖区','441200','440000','广东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2010,'441204','高要区','441200','肇庆市',156,'441204','高要区','441200','440000','广东省','正常',43);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2011,'441223','广宁县','441200','肇庆市',24,'441223','广宁县','441200','440000','广东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2012,'441224','怀集县','441200','肇庆市',27,'441224','怀集县','441200','440000','广东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2013,'441225','封开县','441200','肇庆市',14,'441225','封开县','441200','440000','广东省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2014,'441226','德庆县','441200','肇庆市',16,'441226','德庆县','441200','440000','广东省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2015,'441284','四会市','441200','肇庆市',127,'441284','四会市','441200','440000','广东省','正常',14);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2016,'441300','惠州市','441300','惠州市',1032,'1','','440000','440000','广东省','正常',66);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2017,'441302','惠城区','441300','惠州市',304,'441302','惠城区','441300','440000','广东省','正常',21);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2018,'441303','惠阳区','441300','惠州市',326,'441303','惠阳区','441300','440000','广东省','正常',29);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2019,'441322','博罗县','441300','惠州市',129,'441322','博罗县','441300','440000','广东省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2020,'441323','惠东县','441300','惠州市',193,'441323','惠东县','441300','440000','广东省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2021,'441324','龙门县','441300','惠州市',13,'441324','龙门县','441300','440000','广东省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2022,'441400','梅州市','441400','梅州市',635,'1','','440000','440000','广东省','正常',30);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2023,'441402','梅江区','441400','梅州市',132,'441402','梅江区','441400','440000','广东省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2024,'441403','梅县区','441400','梅州市',32,'441403','梅县区','441400','440000','广东省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2025,'441422','大埔县','441400','梅州市',31,'441422','大埔县','441400','440000','广东省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2026,'441423','丰顺县','441400','梅州市',62,'441423','丰顺县','441400','440000','广东省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2027,'441424','五华县','441400','梅州市',108,'441424','五华县','441400','440000','广东省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2028,'441426','平远县','441400','梅州市',14,'441426','平远县','441400','440000','广东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2029,'441427','蕉岭县','441400','梅州市',22,'441427','蕉岭县','441400','440000','广东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2030,'441481','兴宁市','441400','梅州市',174,'441481','兴宁市','441400','440000','广东省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2031,'441500','汕尾市','441500','汕尾市',431,'1','','440000','440000','广东省','正常',20);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2032,'441502','城区','441500','汕尾市',43,'441502','城区','441500','440000','广东省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2033,'441521','海丰县','441500','汕尾市',97,'441521','海丰县','441500','440000','广东省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2034,'441523','陆河县','441500','汕尾市',11,'441523','陆河县','441500','440000','广东省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2035,'441581','陆丰市','441500','汕尾市',234,'441581','陆丰市','441500','440000','广东省','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2036,'441600','河源市','441600','河源市',541,'1','','440000','440000','广东省','正常',48);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2037,'441602','源城区','441600','河源市',134,'441602','源城区','441600','440000','广东省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2038,'441621','紫金县','441600','河源市',101,'441621','紫金县','441600','440000','广东省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2039,'441622','龙川县','441600','河源市',47,'441622','龙川县','441600','440000','广东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2040,'441623','连平县','441600','河源市',84,'441623','连平县','441600','440000','广东省','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2041,'441624','和平县','441600','河源市',36,'441624','和平县','441600','440000','广东省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2042,'441625','东源县','441600','河源市',30,'441625','东源县','441600','440000','广东省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2043,'441700','阳江市','441700','阳江市',305,'1','','440000','440000','广东省','正常',16);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2044,'441702','江城区','441700','阳江市',91,'441702','江城区','441700','440000','广东省','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2045,'441704','阳东区','441700','阳江市',52,'441704','阳东区','441700','440000','广东省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2046,'441721','阳西县','441700','阳江市',26,'441721','阳西县','441700','440000','广东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2047,'441781','阳春市','441700','阳江市',96,'441781','阳春市','441700','440000','广东省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2048,'441800','清远市','441800','清远市',876,'1','','440000','440000','广东省','正常',53);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2049,'441802','清城区','441800','清远市',302,'441802','清城区','441800','440000','广东省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2050,'441803','清新区','441800','清远市',265,'441803','清新区','441800','440000','广东省','正常',15);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2051,'441821','佛冈县','441800','清远市',6,'441821','佛冈县','441800','440000','广东省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2052,'441823','阳山县','441800','清远市',29,'441823','阳山县','441800','440000','广东省','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2053,'441825','连山壮族瑶族自治县','441800','清远市',1,'441825','连山壮族瑶族自治县','441800','440000','广东省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2054,'441826','连南瑶族自治县','441800','清远市',2,'441826','连南瑶族自治县','441800','440000','广东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2055,'441881','英德市','441800','清远市',101,'441881','英德市','441800','440000','广东省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2056,'441882','连州市','441800','清远市',115,'441882','连州市','441800','440000','广东省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2057,'441900','东莞市','441900','东莞市',3178,'1','','440000','440000','广东省','正常',216);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2058,'442000','中山市','442000','中山市',710,'1','','440000','440000','广东省','正常',28);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2059,'442101','东沙群岛','442100','直辖市县区',0,'442101','东沙群岛','442100','440000','广东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2060,'445100','潮州市','445100','潮州市',219,'1','','440000','440000','广东省','正常',72);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2061,'445102','湘桥区','445100','潮州市',31,'445102','湘桥区','445100','440000','广东省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2062,'445103','潮安区','445100','潮州市',131,'445103','潮安区','445100','440000','广东省','正常',58);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2063,'445122','饶平县','445100','潮州市',33,'445122','饶平县','445100','440000','广东省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2064,'445200','揭阳市','445200','揭阳市',1101,'1','','440000','440000','广东省','正常',103);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2065,'445202','榕城区','445200','揭阳市',291,'445202','榕城区','445200','440000','广东省','正常',36);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2066,'445203','揭东区','445200','揭阳市',149,'445203','揭东区','445200','440000','广东省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2067,'445222','揭西县','445200','揭阳市',69,'445222','揭西县','445200','440000','广东省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2068,'445224','惠来县','445200','揭阳市',45,'445224','惠来县','445200','440000','广东省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2069,'445281','普宁市','445200','揭阳市',422,'445281','普宁市','445200','440000','广东省','正常',58);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2070,'445300','云浮市','445300','云浮市',281,'1','','440000','440000','广东省','正常',19);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2071,'445302','云城区','445300','云浮市',9,'445302','云城区','445300','440000','广东省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2072,'445303','云安区','445300','云浮市',6,'445303','云安区','445300','440000','广东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2073,'445321','新兴县','445300','云浮市',6,'445321','新兴县','445300','440000','广东省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2074,'445322','郁南县','445300','云浮市',105,'445322','郁南县','445300','440000','广东省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2075,'445381','罗定市','445300','云浮市',143,'445381','罗定市','445300','440000','广东省','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2076,'450000','广西壮族自治区','1','',20772,'','','100000','450000','广西壮族自治区','正常',756);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2077,'450100','南宁市','450100','南宁市',1552,'1','','450000','450000','广西壮族自治区','正常',113);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2078,'450102','兴宁区','450100','南宁市',194,'450102','兴宁区','450100','450000','广西壮族自治区','正常',15);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2079,'450103','青秀区','450100','南宁市',234,'450103','青秀区','450100','450000','广西壮族自治区','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2080,'450105','江南区','450100','南宁市',163,'450105','江南区','450100','450000','广西壮族自治区','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2081,'450107','西乡塘区','450100','南宁市',350,'450107','西乡塘区','450100','450000','广西壮族自治区','正常',37);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2082,'450108','良庆区','450100','南宁市',118,'450108','良庆区','450100','450000','广西壮族自治区','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2083,'450109','邕宁区','450100','南宁市',4,'450109','邕宁区','450100','450000','广西壮族自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2084,'450110','武鸣区','450100','南宁市',45,'450110','武鸣区','450100','450000','广西壮族自治区','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2085,'450123','隆安县','450100','南宁市',3,'450123','隆安县','450100','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2086,'450124','马山县','450100','南宁市',5,'450124','马山县','450100','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2087,'450125','上林县','450100','南宁市',4,'450125','上林县','450100','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2088,'450126','宾阳县','450100','南宁市',81,'450126','宾阳县','450100','450000','广西壮族自治区','正常',20);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2089,'450127','横县','450100','南宁市',22,'450127','横县','450100','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2090,'450200','柳州市','450200','柳州市',627,'1','','450000','450000','广西壮族自治区','正常',90);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2091,'450202','城中区','450200','柳州市',55,'450202','城中区','450200','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2092,'450203','鱼峰区','450200','柳州市',84,'450203','鱼峰区','450200','450000','广西壮族自治区','正常',19);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2093,'450204','柳南区','450200','柳州市',91,'450204','柳南区','450200','450000','广西壮族自治区','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2094,'450205','柳北区','450200','柳州市',134,'450205','柳北区','450200','450000','广西壮族自治区','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2095,'450206','柳江区','450200','柳州市',54,'450206','柳江区','450200','450000','广西壮族自治区','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2096,'450222','柳城县','450200','柳州市',11,'450222','柳城县','450200','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2097,'450223','鹿寨县','450200','柳州市',12,'450223','鹿寨县','450200','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2098,'450224','融安县','450200','柳州市',11,'450224','融安县','450200','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2099,'450225','融水苗族自治县','450200','柳州市',7,'450225','融水苗族自治县','450200','450000','广西壮族自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2100,'450226','三江侗族自治县','450200','柳州市',10,'450226','三江侗族自治县','450200','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2101,'450300','桂林市','450300','桂林市',361,'1','','450000','450000','广西壮族自治区','正常',31);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2102,'450302','秀峰区','450300','桂林市',18,'450302','秀峰区','450300','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2103,'450303','叠彩区','450300','桂林市',88,'450303','叠彩区','450300','450000','广西壮族自治区','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2104,'450304','象山区','450300','桂林市',24,'450304','象山区','450300','450000','广西壮族自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2105,'450305','七星区','450300','桂林市',17,'450305','七星区','450300','450000','广西壮族自治区','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2106,'450311','雁山区','450300','桂林市',22,'450311','雁山区','450300','450000','广西壮族自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2107,'450312','临桂区','450300','桂林市',27,'450312','临桂区','450300','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2108,'450321','阳朔县','450300','桂林市',24,'450321','阳朔县','450300','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2109,'450323','灵川县','450300','桂林市',5,'450323','灵川县','450300','450000','广西壮族自治区','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2110,'450324','全州县','450300','桂林市',0,'450324','全州县','450300','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2111,'450325','兴安县','450300','桂林市',8,'450325','兴安县','450300','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2112,'450326','永福县','450300','桂林市',5,'450326','永福县','450300','450000','广西壮族自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2113,'450327','灌阳县','450300','桂林市',27,'450327','灌阳县','450300','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2114,'450328','龙胜各族自治县','450300','桂林市',0,'450328','龙胜各族自治县','450300','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2115,'450329','资源县','450300','桂林市',1,'450329','资源县','450300','450000','广西壮族自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2116,'450330','平乐县','450300','桂林市',5,'450330','平乐县','450300','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2117,'450381','荔浦市','450300','桂林市',5,'450381','荔浦市','450300','450000','广西壮族自治区','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2118,'450332','恭城瑶族自治县','450300','桂林市',20,'450332','恭城瑶族自治县','450300','450000','广西壮族自治区','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2119,'450400','梧州市','450400','梧州市',434,'1','','450000','450000','广西壮族自治区','正常',16);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2120,'450403','万秀区','450400','梧州市',44,'450403','万秀区','450400','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2121,'450405','长洲区','450400','梧州市',150,'450405','长洲区','450400','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2122,'450406','龙圩区','450400','梧州市',22,'450406','龙圩区','450400','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2123,'450421','苍梧县','450400','梧州市',3,'450421','苍梧县','450400','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2124,'450422','藤县','450400','梧州市',93,'450422','藤县','450400','450000','广西壮族自治区','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2125,'450423','蒙山县','450400','梧州市',4,'450423','蒙山县','450400','450000','广西壮族自治区','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2126,'450481','岑溪市','450400','梧州市',93,'450481','岑溪市','450400','450000','广西壮族自治区','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2127,'450500','北海市','450500','北海市',179,'1','','450000','450000','广西壮族自治区','正常',32);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2128,'450502','海城区','450500','北海市',104,'450502','海城区','450500','450000','广西壮族自治区','正常',21);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2129,'450503','银海区','450500','北海市',55,'450503','银海区','450500','450000','广西壮族自治区','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2130,'450512','铁山港区','450500','北海市',0,'450512','铁山港区','450500','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2131,'450521','合浦县','450500','北海市',16,'450521','合浦县','450500','450000','广西壮族自治区','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2132,'450600','防城港市','450600','防城港市',334,'1','','450000','450000','广西壮族自治区','正常',23);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2133,'450602','港口区','450600','防城港市',163,'450602','港口区','450600','450000','广西壮族自治区','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2134,'450603','防城区','450600','防城港市',135,'450603','防城区','450600','450000','广西壮族自治区','正常',19);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2135,'450621','上思县','450600','防城港市',3,'450621','上思县','450600','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2136,'450681','东兴市','450600','防城港市',24,'450681','东兴市','450600','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2137,'450700','钦州市','450700','钦州市',235,'1','','450000','450000','广西壮族自治区','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2138,'450702','钦南区','450700','钦州市',47,'450702','钦南区','450700','450000','广西壮族自治区','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2139,'450703','钦北区','450700','钦州市',79,'450703','钦北区','450700','450000','广西壮族自治区','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2140,'450721','灵山县','450700','钦州市',54,'450721','灵山县','450700','450000','广西壮族自治区','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2141,'450722','浦北县','450700','钦州市',3,'450722','浦北县','450700','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2142,'450800','贵港市','450800','贵港市',648,'1','','450000','450000','广西壮族自治区','正常',33);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2143,'450802','港北区','450800','贵港市',182,'450802','港北区','450800','450000','广西壮族自治区','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2144,'450803','港南区','450800','贵港市',3,'450803','港南区','450800','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2145,'450804','覃塘区','450800','贵港市',9,'450804','覃塘区','450800','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2146,'450821','平南县','450800','贵港市',80,'450821','平南县','450800','450000','广西壮族自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2147,'450881','桂平市','450800','贵港市',233,'450881','桂平市','450800','450000','广西壮族自治区','正常',20);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2148,'450900','玉林市','450900','玉林市',683,'1','','450000','450000','广西壮族自治区','正常',40);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2149,'450902','玉州区','450900','玉林市',135,'450902','玉州区','450900','450000','广西壮族自治区','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2150,'450903','福绵区','450900','玉林市',19,'450903','福绵区','450900','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2151,'450921','容县','450900','玉林市',20,'450921','容县','450900','450000','广西壮族自治区','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2152,'450922','陆川县','450900','玉林市',42,'450922','陆川县','450900','450000','广西壮族自治区','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2153,'450923','博白县','450900','玉林市',170,'450923','博白县','450900','450000','广西壮族自治区','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2154,'450924','兴业县','450900','玉林市',3,'450924','兴业县','450900','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2155,'450981','北流市','450900','玉林市',156,'450981','北流市','450900','450000','广西壮族自治区','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2156,'451000','百色市','451000','百色市',397,'1','','450000','450000','广西壮族自治区','正常',32);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2157,'451002','右江区','451000','百色市',101,'451002','右江区','451000','450000','广西壮族自治区','正常',15);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2158,'451021','田阳县','451000','百色市',0,'451021','田阳县','451000','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2159,'451022','田东县','451000','百色市',30,'451022','田东县','451000','450000','广西壮族自治区','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2160,'451023','平果县','451000','百色市',128,'451023','平果县','451000','450000','广西壮族自治区','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2161,'451024','德保县','451000','百色市',0,'451024','德保县','451000','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2162,'451026','那坡县','451000','百色市',19,'451026','那坡县','451000','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2163,'451027','凌云县','451000','百色市',20,'451027','凌云县','451000','450000','广西壮族自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2164,'451028','乐业县','451000','百色市',11,'451028','乐业县','451000','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2165,'451029','田林县','451000','百色市',6,'451029','田林县','451000','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2166,'451030','西林县','451000','百色市',9,'451030','西林县','451000','450000','广西壮族自治区','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2167,'451031','隆林各族自治县','451000','百色市',27,'451031','隆林各族自治县','451000','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2168,'451081','靖西市','451000','百色市',6,'451081','靖西市','451000','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2169,'451100','贺州市','451100','贺州市',156,'1','','450000','450000','广西壮族自治区','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2170,'451102','八步区','451100','贺州市',58,'451102','八步区','451100','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2171,'451103','平桂区','451100','贺州市',25,'451103','平桂区','451100','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2172,'451121','昭平县','451100','贺州市',28,'451121','昭平县','451100','450000','广西壮族自治区','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2173,'451122','钟山县','451100','贺州市',8,'451122','钟山县','451100','450000','广西壮族自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2174,'451123','富川瑶族自治县','451100','贺州市',6,'451123','富川瑶族自治县','451100','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2175,'451200','河池市','451200','河池市',190,'1','','450000','450000','广西壮族自治区','正常',15);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2176,'451202','金城江区','451200','河池市',28,'451202','金城江区','451200','450000','广西壮族自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2177,'451203','宜州区','451200','河池市',21,'451203','宜州区','451200','450000','广西壮族自治区','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2178,'451221','南丹县','451200','河池市',10,'451221','南丹县','451200','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2179,'451222','天峨县','451200','河池市',11,'451222','天峨县','451200','450000','广西壮族自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2180,'451223','凤山县','451200','河池市',6,'451223','凤山县','451200','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2181,'451224','东兰县','451200','河池市',2,'451224','东兰县','451200','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2182,'451225','罗城仫佬族自治县','451200','河池市',9,'451225','罗城仫佬族自治县','451200','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2183,'451226','环江毛南族自治县','451200','河池市',28,'451226','环江毛南族自治县','451200','450000','广西壮族自治区','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2184,'451227','巴马瑶族自治县','451200','河池市',17,'451227','巴马瑶族自治县','451200','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2185,'451228','都安瑶族自治县','451200','河池市',29,'451228','都安瑶族自治县','451200','450000','广西壮族自治区','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2186,'451229','大化瑶族自治县','451200','河池市',1,'451229','大化瑶族自治县','451200','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2187,'451300','来宾市','451300','来宾市',296,'1','','450000','450000','广西壮族自治区','正常',23);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2188,'451302','兴宾区','451300','来宾市',151,'451302','兴宾区','451300','450000','广西壮族自治区','正常',19);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2189,'451321','忻城县','451300','来宾市',13,'451321','忻城县','451300','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2190,'451322','象州县','451300','来宾市',9,'451322','象州县','451300','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2191,'451323','武宣县','451300','来宾市',36,'451323','武宣县','451300','450000','广西壮族自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2192,'451324','金秀瑶族自治县','451300','来宾市',0,'451324','金秀瑶族自治县','451300','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2193,'451381','合山市','451300','来宾市',16,'451381','合山市','451300','450000','广西壮族自治区','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2194,'451400','崇左市','451400','崇左市',174,'1','','450000','450000','广西壮族自治区','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2195,'451402','江州区','451400','崇左市',4,'451402','江州区','451400','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2196,'451421','扶绥县','451400','崇左市',68,'451421','扶绥县','451400','450000','广西壮族自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2197,'451422','宁明县','451400','崇左市',30,'451422','宁明县','451400','450000','广西壮族自治区','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2198,'451423','龙州县','451400','崇左市',20,'451423','龙州县','451400','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2199,'451424','大新县','451400','崇左市',17,'451424','大新县','451400','450000','广西壮族自治区','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2200,'451425','天等县','451400','崇左市',9,'451425','天等县','451400','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2201,'451481','凭祥市','451400','崇左市',19,'451481','凭祥市','451400','450000','广西壮族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2202,'460000','海南省','1','',2098,'','','100000','460000','海南省','正常',138);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2203,'460100','海口市','460100','海口市',864,'1','','460000','460000','海南省','正常',26);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2204,'460105','秀英区','460100','海口市',134,'460105','秀英区','460100','460000','海南省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2205,'460106','龙华区','460100','海口市',153,'460106','龙华区','460100','460000','海南省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2206,'460107','琼山区','460100','海口市',237,'460107','琼山区','460100','460000','海南省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2207,'460108','美兰区','460100','海口市',153,'460108','美兰区','460100','460000','海南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2208,'460200','三亚市','460200','三亚市',238,'1','','460000','460000','海南省','正常',69);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2209,'460202','海棠区','460200','三亚市',1,'460202','海棠区','460200','460000','海南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2210,'460203','吉阳区','460200','三亚市',160,'460203','吉阳区','460200','460000','海南省','正常',69);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2211,'460204','天涯区','460200','三亚市',13,'460204','天涯区','460200','460000','海南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2212,'460205','崖州区','460200','三亚市',6,'460205','崖州区','460200','460000','海南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2213,'460300','三沙市','460300','三沙市',1,'1','','460000','460000','海南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2214,'460321','西沙群岛','460300','三沙市',0,'460321','西沙群岛','460300','460000','海南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2215,'460322','南沙群岛','460300','三沙市',0,'460322','南沙群岛','460300','460000','海南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2216,'460323','中沙群岛的岛礁及其海域','460300','三沙市',0,'460323','中沙群岛的岛礁及其海域','460300','460000','海南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2217,'460400','儋州市','460400','儋州市',56,'1','','460000','460000','海南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2218,'469001','五指山市','469000','直辖市县区',3,'469001','五指山市','469000','460000','海南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2219,'469002','琼海市','469000','直辖市县区',22,'469002','琼海市','469000','460000','海南省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2220,'469005','文昌市','469000','直辖市县区',94,'469005','文昌市','469000','460000','海南省','正常',12);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2221,'469006','万宁市','469000','直辖市县区',65,'469006','万宁市','469000','460000','海南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2222,'469007','东方市','469000','直辖市县区',57,'469007','东方市','469000','460000','海南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2223,'469021','定安县','469000','直辖市县区',36,'469021','定安县','469000','460000','海南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2224,'469022','屯昌县','469000','直辖市县区',29,'469022','屯昌县','469000','460000','海南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2225,'469023','澄迈县','469000','直辖市县区',35,'469023','澄迈县','469000','460000','海南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2226,'469024','临高县','469000','直辖市县区',31,'469024','临高县','469000','460000','海南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2227,'469025','白沙黎族自治县','469000','直辖市县区',15,'469025','白沙黎族自治县','469000','460000','海南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2228,'469026','昌江黎族自治县','469000','直辖市县区',3,'469026','昌江黎族自治县','469000','460000','海南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2229,'469027','乐东黎族自治县','469000','直辖市县区',12,'469027','乐东黎族自治县','469000','460000','海南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2230,'469028','陵水黎族自治县','469000','直辖市县区',7,'469028','陵水黎族自治县','469000','460000','海南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2231,'469029','保亭黎族苗族自治县','469000','直辖市县区',9,'469029','保亭黎族苗族自治县','469000','460000','海南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2232,'469030','琼中黎族苗族自治县','469000','直辖市县区',11,'469030','琼中黎族苗族自治县','469000','460000','海南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2233,'500000','重庆市','1','',6854,'1','','100000','500000','重庆市','正常',396);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2234,'500101','万州区','1','',359,'500101','万州区','500000','500000','重庆市','正常',47);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2235,'500102','涪陵区','1','',113,'500102','涪陵区','500000','500000','重庆市','正常',29);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2236,'500103','渝中区','1','',104,'500103','渝中区','500000','500000','重庆市','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2237,'500104','大渡口区','1','',18,'500104','大渡口区','500000','500000','重庆市','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2238,'500105','江北区','1','',172,'500105','江北区','500000','500000','重庆市','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2239,'500106','沙坪坝区','1','',205,'500106','沙坪坝区','500000','500000','重庆市','正常',14);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2240,'500107','九龙坡区','1','',632,'500107','九龙坡区','500000','500000','重庆市','正常',72);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2241,'500108','南岸区','1','',366,'500108','南岸区','500000','500000','重庆市','正常',19);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2242,'500109','北碚区','1','',132,'500109','北碚区','500000','500000','重庆市','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2243,'500110','綦江区','1','',101,'500110','綦江区','500000','500000','重庆市','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2244,'500111','大足区','1','',242,'500111','大足区','500000','500000','重庆市','正常',17);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2245,'500112','渝北区','1','',500,'500112','渝北区','500000','500000','重庆市','正常',35);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2246,'500113','巴南区','1','',256,'500113','巴南区','500000','500000','重庆市','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2247,'500114','黔江区','1','',88,'500114','黔江区','500000','500000','重庆市','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2248,'500115','长寿区','1','',137,'500115','长寿区','500000','500000','重庆市','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2249,'500116','江津区','1','',203,'500116','江津区','500000','500000','重庆市','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2250,'500117','合川区','1','',187,'500117','合川区','500000','500000','重庆市','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2251,'500118','永川区','1','',101,'500118','永川区','500000','500000','重庆市','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2252,'500119','南川区','1','',120,'500119','南川区','500000','500000','重庆市','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2253,'500120','璧山区','1','',30,'500120','璧山区','500000','500000','重庆市','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2254,'500151','铜梁区','1','',158,'500151','铜梁区','500000','500000','重庆市','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2255,'500152','潼南区','1','',125,'500152','潼南区','500000','500000','重庆市','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2256,'500153','荣昌区','1','',189,'500153','荣昌区','500000','500000','重庆市','正常',16);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2257,'500154','开州区','1','',194,'500154','开州区','500000','500000','重庆市','正常',19);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2258,'500155','梁平区','1','',112,'500155','梁平区','500000','500000','重庆市','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2259,'500156','武隆区','1','',20,'500156','武隆区','500000','500000','重庆市','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2260,'500229','城口县','500200','重庆市郊县',6,'500229','城口县','500200','500000','重庆市','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2261,'500230','丰都县','500200','重庆市郊县',252,'500230','丰都县','500200','500000','重庆市','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2262,'500231','垫江县','500200','重庆市郊县',241,'500231','垫江县','500200','500000','重庆市','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2263,'500233','忠县','500200','重庆市郊县',64,'500233','忠县','500200','500000','重庆市','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2264,'500235','云阳县','500200','重庆市郊县',215,'500235','云阳县','500200','500000','重庆市','正常',32);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2265,'500236','奉节县','500200','重庆市郊县',141,'500236','奉节县','500200','500000','重庆市','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2266,'500237','巫山县','500200','重庆市郊县',33,'500237','巫山县','500200','500000','重庆市','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2267,'500238','巫溪县','500200','重庆市郊县',62,'500238','巫溪县','500200','500000','重庆市','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2268,'500240','石柱土家族自治县','500200','重庆市郊县',133,'500240','石柱土家族自治县','500200','500000','重庆市','正常',18);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2269,'500241','秀山土家族苗族自治县','500200','重庆市郊县',147,'500241','秀山土家族苗族自治县','500200','500000','重庆市','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2270,'500242','酉阳土家族苗族自治县','500200','重庆市郊县',80,'500242','酉阳土家族苗族自治县','500200','500000','重庆市','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2271,'500243','彭水苗族土家族自治县','500200','重庆市郊县',71,'500243','彭水苗族土家族自治县','500200','500000','重庆市','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2272,'510000','四川省','1','',28791,'','','100000','510000','四川省','正常',1415);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2273,'510100','成都市','510100','成都市',3669,'1','','510000','510000','四川省','正常',280);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2274,'510104','锦江区','510100','成都市',346,'510104','锦江区','510100','510000','四川省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2275,'510105','青羊区','510100','成都市',112,'510105','青羊区','510100','510000','四川省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2276,'510106','金牛区','510100','成都市',163,'510106','金牛区','510100','510000','四川省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2277,'510107','武侯区','510100','成都市',463,'510107','武侯区','510100','510000','四川省','正常',107);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2278,'510108','成华区','510100','成都市',227,'510108','成华区','510100','510000','四川省','正常',25);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2279,'510112','龙泉驿区','510100','成都市',264,'510112','龙泉驿区','510100','510000','四川省','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2280,'510113','青白江区','510100','成都市',73,'510113','青白江区','510100','510000','四川省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2281,'510114','新都区','510100','成都市',200,'510114','新都区','510100','510000','四川省','正常',20);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2282,'510115','温江区','510100','成都市',90,'510115','温江区','510100','510000','四川省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2283,'510116','双流区','510100','成都市',300,'510116','双流区','510100','510000','四川省','正常',15);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2284,'510117','郫都区','510100','成都市',161,'510117','郫都区','510100','510000','四川省','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2285,'510121','金堂县','510100','成都市',126,'510121','金堂县','510100','510000','四川省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2286,'510129','大邑县','510100','成都市',72,'510129','大邑县','510100','510000','四川省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2287,'510131','蒲江县','510100','成都市',26,'510131','蒲江县','510100','510000','四川省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2288,'510132','新津县','510100','成都市',50,'510132','新津县','510100','510000','四川省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2289,'510181','都江堰市','510100','成都市',104,'510181','都江堰市','510100','510000','四川省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2290,'510182','彭州市','510100','成都市',30,'510182','彭州市','510100','510000','四川省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2291,'510183','邛崃市','510100','成都市',78,'510183','邛崃市','510100','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2292,'510184','崇州市','510100','成都市',44,'510184','崇州市','510100','510000','四川省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2293,'510185','简阳市','510100','成都市',64,'510185','简阳市','510100','510000','四川省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2294,'510300','自贡市','510300','自贡市',436,'1','','510000','510000','四川省','正常',23);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2295,'510302','自流井区','510300','自贡市',141,'510302','自流井区','510300','510000','四川省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2296,'510303','贡井区','510300','自贡市',26,'510303','贡井区','510300','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2297,'510304','大安区','510300','自贡市',49,'510304','大安区','510300','510000','四川省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2298,'510311','沿滩区','510300','自贡市',33,'510311','沿滩区','510300','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2299,'510321','荣县','510300','自贡市',12,'510321','荣县','510300','510000','四川省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2300,'510322','富顺县','510300','自贡市',128,'510322','富顺县','510300','510000','四川省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2301,'510400','攀枝花市','510400','攀枝花市',370,'1','','510000','510000','四川省','正常',23);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2302,'510402','东区','510400','攀枝花市',36,'510402','东区','510400','510000','四川省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2303,'510403','西区','510400','攀枝花市',14,'510403','西区','510400','510000','四川省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2304,'510411','仁和区','510400','攀枝花市',264,'510411','仁和区','510400','510000','四川省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2305,'510421','米易县','510400','攀枝花市',13,'510421','米易县','510400','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2306,'510422','盐边县','510400','攀枝花市',0,'510422','盐边县','510400','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2307,'510500','泸州市','510500','泸州市',712,'1','','510000','510000','四川省','正常',23);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2308,'510502','江阳区','510500','泸州市',101,'510502','江阳区','510500','510000','四川省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2309,'510503','纳溪区','510500','泸州市',33,'510503','纳溪区','510500','510000','四川省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2310,'510504','龙马潭区','510500','泸州市',203,'510504','龙马潭区','510500','510000','四川省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2311,'510521','泸县','510500','泸州市',66,'510521','泸县','510500','510000','四川省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2312,'510522','合江县','510500','泸州市',146,'510522','合江县','510500','510000','四川省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2313,'510524','叙永县','510500','泸州市',39,'510524','叙永县','510500','510000','四川省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2314,'510525','古蔺县','510500','泸州市',21,'510525','古蔺县','510500','510000','四川省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2315,'510600','德阳市','510600','德阳市',625,'1','','510000','510000','四川省','正常',22);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2316,'510603','旌阳区','510600','德阳市',181,'510603','旌阳区','510600','510000','四川省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2317,'510623','中江县','510600','德阳市',60,'510623','中江县','510600','510000','四川省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2318,'510604','罗江区','510600','德阳市',57,'510604','罗江区','510600','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2319,'510681','广汉市','510600','德阳市',211,'510681','广汉市','510600','510000','四川省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2320,'510682','什邡市','510600','德阳市',38,'510682','什邡市','510600','510000','四川省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2321,'510683','绵竹市','510600','德阳市',26,'510683','绵竹市','510600','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2322,'510700','绵阳市','510700','绵阳市',472,'1','','510000','510000','四川省','正常',36);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2323,'510703','涪城区','510700','绵阳市',127,'510703','涪城区','510700','510000','四川省','正常',15);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2324,'510704','游仙区','510700','绵阳市',25,'510704','游仙区','510700','510000','四川省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2325,'510705','安州区','510700','绵阳市',61,'510705','安州区','510700','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2326,'510722','三台县','510700','绵阳市',54,'510722','三台县','510700','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2327,'510723','盐亭县','510700','绵阳市',24,'510723','盐亭县','510700','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2328,'510725','梓潼县','510700','绵阳市',6,'510725','梓潼县','510700','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2329,'510726','北川羌族自治县','510700','绵阳市',3,'510726','北川羌族自治县','510700','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2330,'510727','平武县','510700','绵阳市',0,'510727','平武县','510700','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2331,'510781','江油市','510700','绵阳市',101,'510781','江油市','510700','510000','四川省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2332,'510800','广元市','510800','广元市',228,'1','','510000','510000','四川省','正常',14);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2333,'510802','利州区','510800','广元市',99,'510802','利州区','510800','510000','四川省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2334,'510811','昭化区','510800','广元市',55,'510811','昭化区','510800','510000','四川省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2335,'510812','朝天区','510800','广元市',0,'510812','朝天区','510800','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2336,'510821','旺苍县','510800','广元市',40,'510821','旺苍县','510800','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2337,'510822','青川县','510800','广元市',5,'510822','青川县','510800','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2338,'510823','剑阁县','510800','广元市',0,'510823','剑阁县','510800','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2339,'510824','苍溪县','510800','广元市',11,'510824','苍溪县','510800','510000','四川省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2340,'510900','遂宁市','510900','遂宁市',351,'1','','510000','510000','四川省','正常',54);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2341,'510903','船山区','510900','遂宁市',215,'510903','船山区','510900','510000','四川省','正常',48);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2342,'510904','安居区','510900','遂宁市',32,'510904','安居区','510900','510000','四川省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2343,'510921','蓬溪县','510900','遂宁市',11,'510921','蓬溪县','510900','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2344,'510922','射洪县','510900','遂宁市',26,'510922','射洪县','510900','510000','四川省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2345,'510923','大英县','510900','遂宁市',42,'510923','大英县','510900','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2346,'511000','内江市','511000','内江市',312,'1','','510000','510000','四川省','正常',89);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2347,'511002','市中区','511000','内江市',29,'511002','市中区','511000','510000','四川省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2348,'511011','东兴区','511000','内江市',138,'511011','东兴区','511000','510000','四川省','正常',66);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2349,'511024','威远县','511000','内江市',69,'511024','威远县','511000','510000','四川省','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2350,'511025','资中县','511000','内江市',17,'511025','资中县','511000','510000','四川省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2351,'511083','隆昌市','511000','内江市',14,'511083','隆昌市','511000','510000','四川省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2352,'511100','乐山市','511100','乐山市',363,'1','','510000','510000','四川省','正常',49);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2353,'511102','市中区','511100','乐山市',96,'511102','市中区','511100','510000','四川省','正常',17);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2354,'511111','沙湾区','511100','乐山市',12,'511111','沙湾区','511100','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2355,'511112','五通桥区','511100','乐山市',46,'511112','五通桥区','511100','510000','四川省','正常',12);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2356,'511113','金口河区','511100','乐山市',0,'511113','金口河区','511100','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2357,'511123','犍为县','511100','乐山市',71,'511123','犍为县','511100','510000','四川省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2358,'511124','井研县','511100','乐山市',16,'511124','井研县','511100','510000','四川省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2359,'511126','夹江县','511100','乐山市',18,'511126','夹江县','511100','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2360,'511129','沐川县','511100','乐山市',1,'511129','沐川县','511100','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2361,'511132','峨边彝族自治县','511100','乐山市',3,'511132','峨边彝族自治县','511100','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2362,'511133','马边彝族自治县','511100','乐山市',0,'511133','马边彝族自治县','511100','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2363,'511181','峨眉山市','511100','乐山市',70,'511181','峨眉山市','511100','510000','四川省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2364,'511300','南充市','511300','南充市',465,'1','','510000','510000','四川省','正常',82);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2365,'511302','顺庆区','511300','南充市',48,'511302','顺庆区','511300','510000','四川省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2366,'511303','高坪区','511300','南充市',22,'511303','高坪区','511300','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2367,'511304','嘉陵区','511300','南充市',44,'511304','嘉陵区','511300','510000','四川省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2368,'511321','南部县','511300','南充市',74,'511321','南部县','511300','510000','四川省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2369,'511322','营山县','511300','南充市',69,'511322','营山县','511300','510000','四川省','正常',35);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2370,'511323','蓬安县','511300','南充市',21,'511323','蓬安县','511300','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2371,'511324','仪陇县','511300','南充市',23,'511324','仪陇县','511300','510000','四川省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2372,'511325','西充县','511300','南充市',45,'511325','西充县','511300','510000','四川省','正常',21);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2373,'511381','阆中市','511300','南充市',36,'511381','阆中市','511300','510000','四川省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2374,'511400','眉山市','511400','眉山市',309,'1','','510000','510000','四川省','正常',33);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2375,'511402','东坡区','511400','眉山市',90,'511402','东坡区','511400','510000','四川省','正常',23);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2376,'511403','彭山区','511400','眉山市',12,'511403','彭山区','511400','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2377,'511421','仁寿县','511400','眉山市',60,'511421','仁寿县','511400','510000','四川省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2378,'511423','洪雅县','511400','眉山市',100,'511423','洪雅县','511400','510000','四川省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2379,'511424','丹棱县','511400','眉山市',1,'511424','丹棱县','511400','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2380,'511425','青神县','511400','眉山市',15,'511425','青神县','511400','510000','四川省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2381,'511500','宜宾市','511500','宜宾市',630,'1','','510000','510000','四川省','正常',42);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2382,'511502','翠屏区','511500','宜宾市',139,'511502','翠屏区','511500','510000','四川省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2383,'511503','南溪区','511500','宜宾市',76,'511503','南溪区','511500','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2384,'511504','叙州区','511500','宜宾市',44,'511504','叙州区','511500','510000','四川省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2385,'511523','江安县','511500','宜宾市',30,'511523','江安县','511500','510000','四川省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2386,'511524','长宁县','511500','宜宾市',68,'511524','长宁县','511500','510000','四川省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2387,'511525','高县','511500','宜宾市',23,'511525','高县','511500','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2388,'511526','珙县','511500','宜宾市',13,'511526','珙县','511500','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2389,'511527','筠连县','511500','宜宾市',65,'511527','筠连县','511500','510000','四川省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2390,'511528','兴文县','511500','宜宾市',107,'511528','兴文县','511500','510000','四川省','正常',21);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2391,'511529','屏山县','511500','宜宾市',1,'511529','屏山县','511500','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2392,'511600','广安市','511600','广安市',409,'1','','510000','510000','四川省','正常',45);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2393,'511602','广安区','511600','广安市',55,'511602','广安区','511600','510000','四川省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2394,'511603','前锋区','511600','广安市',45,'511603','前锋区','511600','510000','四川省','正常',15);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2395,'511621','岳池县','511600','广安市',85,'511621','岳池县','511600','510000','四川省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2396,'511622','武胜县','511600','广安市',52,'511622','武胜县','511600','510000','四川省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2397,'511623','邻水县','511600','广安市',108,'511623','邻水县','511600','510000','四川省','正常',20);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2398,'511681','华蓥市','511600','广安市',26,'511681','华蓥市','511600','510000','四川省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2399,'511700','达州市','511700','达州市',666,'1','','510000','510000','四川省','正常',44);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2400,'511702','通川区','511700','达州市',225,'511702','通川区','511700','510000','四川省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2401,'511703','达川区','511700','达州市',87,'511703','达川区','511700','510000','四川省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2402,'511722','宣汉县','511700','达州市',99,'511722','宣汉县','511700','510000','四川省','正常',14);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2403,'511723','开江县','511700','达州市',11,'511723','开江县','511700','510000','四川省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2404,'511724','大竹县','511700','达州市',59,'511724','大竹县','511700','510000','四川省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2405,'511725','渠县','511700','达州市',40,'511725','渠县','511700','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2406,'511781','万源市','511700','达州市',36,'511781','万源市','511700','510000','四川省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2407,'511800','雅安市','511800','雅安市',89,'1','','510000','510000','四川省','正常',16);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2408,'511802','雨城区','511800','雅安市',43,'511802','雨城区','511800','510000','四川省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2409,'511803','名山区','511800','雅安市',2,'511803','名山区','511800','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2410,'511822','荥经县','511800','雅安市',3,'511822','荥经县','511800','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2411,'511823','汉源县','511800','雅安市',7,'511823','汉源县','511800','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2412,'511824','石棉县','511800','雅安市',13,'511824','石棉县','511800','510000','四川省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2413,'511825','天全县','511800','雅安市',4,'511825','天全县','511800','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2414,'511826','芦山县','511800','雅安市',0,'511826','芦山县','511800','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2415,'511827','宝兴县','511800','雅安市',0,'511827','宝兴县','511800','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2416,'511900','巴中市','511900','巴中市',359,'1','','510000','510000','四川省','正常',40);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2417,'511902','巴州区','511900','巴中市',139,'511902','巴州区','511900','510000','四川省','正常',27);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2418,'511903','恩阳区','511900','巴中市',1,'511903','恩阳区','511900','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2419,'511921','通江县','511900','巴中市',41,'511921','通江县','511900','510000','四川省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2420,'511922','南江县','511900','巴中市',40,'511922','南江县','511900','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2421,'511923','平昌县','511900','巴中市',75,'511923','平昌县','511900','510000','四川省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2422,'512000','资阳市','512000','资阳市',320,'1','','510000','510000','四川省','正常',25);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2423,'512002','雁江区','512000','资阳市',76,'512002','雁江区','512000','510000','四川省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2424,'512021','安岳县','512000','资阳市',174,'512021','安岳县','512000','510000','四川省','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2425,'512022','乐至县','512000','资阳市',51,'512022','乐至县','512000','510000','四川省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2426,'513200','阿坝藏族羌族自治州','513200','阿坝藏族羌族自治州',9,'1','','510000','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2427,'513201','马尔康市','513200','阿坝藏族羌族自治州',0,'513201','马尔康市','513200','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2428,'513221','汶川县','513200','阿坝藏族羌族自治州',6,'513221','汶川县','513200','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2429,'513222','理县','513200','阿坝藏族羌族自治州',0,'513222','理县','513200','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2430,'513223','茂县','513200','阿坝藏族羌族自治州',0,'513223','茂县','513200','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2431,'513224','松潘县','513200','阿坝藏族羌族自治州',0,'513224','松潘县','513200','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2432,'513225','九寨沟县','513200','阿坝藏族羌族自治州',1,'513225','九寨沟县','513200','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2433,'513226','金川县','513200','阿坝藏族羌族自治州',0,'513226','金川县','513200','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2434,'513227','小金县','513200','阿坝藏族羌族自治州',0,'513227','小金县','513200','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2435,'513228','黑水县','513200','阿坝藏族羌族自治州',0,'513228','黑水县','513200','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2436,'513230','壤塘县','513200','阿坝藏族羌族自治州',0,'513230','壤塘县','513200','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2437,'513231','阿坝县','513200','阿坝藏族羌族自治州',0,'513231','阿坝县','513200','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2438,'513232','若尔盖县','513200','阿坝藏族羌族自治州',0,'513232','若尔盖县','513200','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2439,'513233','红原县','513200','阿坝藏族羌族自治州',0,'513233','红原县','513200','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2440,'513300','甘孜藏族自治州','513300','甘孜藏族自治州',47,'1','','510000','510000','四川省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2441,'513301','康定市','513300','甘孜藏族自治州',6,'513301','康定市','513300','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2442,'513322','泸定县','513300','甘孜藏族自治州',37,'513322','泸定县','513300','510000','四川省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2443,'513323','丹巴县','513300','甘孜藏族自治州',0,'513323','丹巴县','513300','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2444,'513324','九龙县','513300','甘孜藏族自治州',1,'513324','九龙县','513300','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2445,'513325','雅江县','513300','甘孜藏族自治州',0,'513325','雅江县','513300','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2446,'513326','道孚县','513300','甘孜藏族自治州',0,'513326','道孚县','513300','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2447,'513327','炉霍县','513300','甘孜藏族自治州',0,'513327','炉霍县','513300','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2448,'513328','甘孜县','513300','甘孜藏族自治州',3,'513328','甘孜县','513300','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2449,'513329','新龙县','513300','甘孜藏族自治州',0,'513329','新龙县','513300','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2450,'513330','德格县','513300','甘孜藏族自治州',0,'513330','德格县','513300','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2451,'513331','白玉县','513300','甘孜藏族自治州',0,'513331','白玉县','513300','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2452,'513332','石渠县','513300','甘孜藏族自治州',0,'513332','石渠县','513300','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2453,'513333','色达县','513300','甘孜藏族自治州',0,'513333','色达县','513300','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2454,'513334','理塘县','513300','甘孜藏族自治州',0,'513334','理塘县','513300','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2455,'513335','巴塘县','513300','甘孜藏族自治州',0,'513335','巴塘县','513300','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2456,'513336','乡城县','513300','甘孜藏族自治州',0,'513336','乡城县','513300','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2457,'513337','稻城县','513300','甘孜藏族自治州',0,'513337','稻城县','513300','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2458,'513338','得荣县','513300','甘孜藏族自治州',0,'513338','得荣县','513300','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2459,'513400','凉山彝族自治州','513400','凉山彝族自治州',280,'1','','510000','510000','四川省','正常',20);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2460,'513401','西昌市','513400','凉山彝族自治州',52,'513401','西昌市','513400','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2461,'513422','木里藏族自治县','513400','凉山彝族自治州',0,'513422','木里藏族自治县','513400','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2462,'513423','盐源县','513400','凉山彝族自治州',13,'513423','盐源县','513400','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2463,'513424','德昌县','513400','凉山彝族自治州',36,'513424','德昌县','513400','510000','四川省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2464,'513425','会理县','513400','凉山彝族自治州',57,'513425','会理县','513400','510000','四川省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2465,'513426','会东县','513400','凉山彝族自治州',58,'513426','会东县','513400','510000','四川省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2466,'513427','宁南县','513400','凉山彝族自治州',6,'513427','宁南县','513400','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2467,'513428','普格县','513400','凉山彝族自治州',2,'513428','普格县','513400','510000','四川省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2468,'513429','布拖县','513400','凉山彝族自治州',0,'513429','布拖县','513400','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2469,'513430','金阳县','513400','凉山彝族自治州',0,'513430','金阳县','513400','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2470,'513431','昭觉县','513400','凉山彝族自治州',4,'513431','昭觉县','513400','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2471,'513432','喜德县','513400','凉山彝族自治州',1,'513432','喜德县','513400','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2472,'513433','冕宁县','513400','凉山彝族自治州',8,'513433','冕宁县','513400','510000','四川省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2473,'513434','越西县','513400','凉山彝族自治州',0,'513434','越西县','513400','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2474,'513435','甘洛县','513400','凉山彝族自治州',0,'513435','甘洛县','513400','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2475,'513436','美姑县','513400','凉山彝族自治州',0,'513436','美姑县','513400','510000','四川省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2476,'513437','雷波县','513400','凉山彝族自治州',5,'513437','雷波县','513400','510000','四川省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2477,'520000','贵州省','1','',8562,'','','100000','520000','贵州省','正常',434);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2478,'520100','贵阳市','520100','贵阳市',1746,'1','','520000','520000','贵州省','正常',144);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2479,'520102','南明区','520100','贵阳市',233,'520102','南明区','520100','520000','贵州省','正常',80);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2480,'520103','云岩区','520100','贵阳市',324,'520103','云岩区','520100','520000','贵州省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2481,'520111','花溪区','520100','贵阳市',298,'520111','花溪区','520100','520000','贵州省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2482,'520112','乌当区','520100','贵阳市',20,'520112','乌当区','520100','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2483,'520113','白云区','520100','贵阳市',138,'520113','白云区','520100','520000','贵州省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2484,'520115','观山湖区','520100','贵阳市',138,'520115','观山湖区','520100','520000','贵州省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2485,'520121','开阳县','520100','贵阳市',73,'520121','开阳县','520100','520000','贵州省','正常',23);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2486,'520122','息烽县','520100','贵阳市',7,'520122','息烽县','520100','520000','贵州省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2487,'520123','修文县','520100','贵阳市',15,'520123','修文县','520100','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2488,'520181','清镇市','520100','贵阳市',25,'520181','清镇市','520100','520000','贵州省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2489,'520200','六盘水市','520200','六盘水市',764,'1','','520000','520000','贵州省','正常',40);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2490,'520201','钟山区','520200','六盘水市',249,'520201','钟山区','520200','520000','贵州省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2491,'520203','六枝特区','520200','六盘水市',50,'520203','六枝特区','520200','520000','贵州省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2492,'520221','水城县','520200','六盘水市',41,'520221','水城县','520200','520000','贵州省','正常',12);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2493,'520281','盘州市','520200','六盘水市',218,'520281','盘州市','520200','520000','贵州省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2494,'520300','遵义市','520300','遵义市',1409,'1','','520000','520000','贵州省','正常',82);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2495,'520302','红花岗区','520300','遵义市',197,'520302','红花岗区','520300','520000','贵州省','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2496,'520303','汇川区','520300','遵义市',125,'520303','汇川区','520300','520000','贵州省','正常',38);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2497,'520304','播州区','520300','遵义市',201,'520304','播州区','520300','520000','贵州省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2498,'520322','桐梓县','520300','遵义市',37,'520322','桐梓县','520300','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2499,'520323','绥阳县','520300','遵义市',32,'520323','绥阳县','520300','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2500,'520324','正安县','520300','遵义市',64,'520324','正安县','520300','520000','贵州省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2501,'520325','道真仡佬族苗族自治县','520300','遵义市',15,'520325','道真仡佬族苗族自治县','520300','520000','贵州省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2502,'520326','务川仡佬族苗族自治县','520300','遵义市',26,'520326','务川仡佬族苗族自治县','520300','520000','贵州省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2503,'520327','凤冈县','520300','遵义市',7,'520327','凤冈县','520300','520000','贵州省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2504,'520328','湄潭县','520300','遵义市',24,'520328','湄潭县','520300','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2505,'520329','余庆县','520300','遵义市',53,'520329','余庆县','520300','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2506,'520330','习水县','520300','遵义市',63,'520330','习水县','520300','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2507,'520381','赤水市','520300','遵义市',29,'520381','赤水市','520300','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2508,'520382','仁怀市','520300','遵义市',33,'520382','仁怀市','520300','520000','贵州省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2509,'520400','安顺市','520400','安顺市',246,'1','','520000','520000','贵州省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2510,'520402','西秀区','520400','安顺市',93,'520402','西秀区','520400','520000','贵州省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2511,'520403','平坝区','520400','安顺市',6,'520403','平坝区','520400','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2512,'520422','普定县','520400','安顺市',34,'520422','普定县','520400','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2513,'520423','镇宁布依族苗族自治县','520400','安顺市',7,'520423','镇宁布依族苗族自治县','520400','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2514,'520424','关岭布依族苗族自治县','520400','安顺市',20,'520424','关岭布依族苗族自治县','520400','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2515,'520425','紫云苗族布依族自治县','520400','安顺市',6,'520425','紫云苗族布依族自治县','520400','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2516,'520500','毕节市','520500','毕节市',843,'1','','520000','520000','贵州省','正常',24);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2517,'520502','七星关区','520500','毕节市',256,'520502','七星关区','520500','520000','贵州省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2518,'520521','大方县','520500','毕节市',60,'520521','大方县','520500','520000','贵州省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2519,'520522','黔西县','520500','毕节市',64,'520522','黔西县','520500','520000','贵州省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2520,'520523','金沙县','520500','毕节市',28,'520523','金沙县','520500','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2521,'520524','织金县','520500','毕节市',46,'520524','织金县','520500','520000','贵州省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2522,'520525','纳雍县','520500','毕节市',34,'520525','纳雍县','520500','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2523,'520526','威宁彝族回族苗族自治县','520500','毕节市',18,'520526','威宁彝族回族苗族自治县','520500','520000','贵州省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2524,'520527','赫章县','520500','毕节市',32,'520527','赫章县','520500','520000','贵州省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2525,'520600','铜仁市','520600','铜仁市',643,'1','','520000','520000','贵州省','正常',22);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2526,'520602','碧江区','520600','铜仁市',78,'520602','碧江区','520600','520000','贵州省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2527,'520603','万山区','520600','铜仁市',14,'520603','万山区','520600','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2528,'520621','江口县','520600','铜仁市',18,'520621','江口县','520600','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2529,'520622','玉屏侗族自治县','520600','铜仁市',48,'520622','玉屏侗族自治县','520600','520000','贵州省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2530,'520623','石阡县','520600','铜仁市',4,'520623','石阡县','520600','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2531,'520624','思南县','520600','铜仁市',35,'520624','思南县','520600','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2532,'520625','印江土家族苗族自治县','520600','铜仁市',26,'520625','印江土家族苗族自治县','520600','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2533,'520626','德江县','520600','铜仁市',90,'520626','德江县','520600','520000','贵州省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2534,'520627','沿河土家族自治县','520600','铜仁市',43,'520627','沿河土家族自治县','520600','520000','贵州省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2535,'520628','松桃苗族自治县','520600','铜仁市',60,'520628','松桃苗族自治县','520600','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2536,'522300','黔西南布依族苗族自治州','522300','黔西南布依族苗族自治州',495,'1','','520000','520000','贵州省','正常',19);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2537,'522301','兴义市','522300','黔西南布依族苗族自治州',135,'522301','兴义市','522300','520000','贵州省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2538,'522302','兴仁市','522300','黔西南布依族苗族自治州',8,'522302','兴仁市','522300','520000','贵州省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2539,'522323','普安县','522300','黔西南布依族苗族自治州',17,'522323','普安县','522300','520000','贵州省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2540,'522324','晴隆县','522300','黔西南布依族苗族自治州',46,'522324','晴隆县','522300','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2541,'522325','贞丰县','522300','黔西南布依族苗族自治州',27,'522325','贞丰县','522300','520000','贵州省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2542,'522326','望谟县','522300','黔西南布依族苗族自治州',4,'522326','望谟县','522300','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2543,'522327','册亨县','522300','黔西南布依族苗族自治州',15,'522327','册亨县','522300','520000','贵州省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2544,'522328','安龙县','522300','黔西南布依族苗族自治州',5,'522328','安龙县','522300','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2545,'522600','黔东南苗族侗族自治州','522600','黔东南苗族侗族自治州',550,'1','','520000','520000','贵州省','正常',18);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2546,'522601','凯里市','522600','黔东南苗族侗族自治州',124,'522601','凯里市','522600','520000','贵州省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2547,'522622','黄平县','522600','黔东南苗族侗族自治州',3,'522622','黄平县','522600','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2548,'522623','施秉县','522600','黔东南苗族侗族自治州',4,'522623','施秉县','522600','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2549,'522624','三穗县','522600','黔东南苗族侗族自治州',149,'522624','三穗县','522600','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2550,'522625','镇远县','522600','黔东南苗族侗族自治州',5,'522625','镇远县','522600','520000','贵州省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2551,'522626','岑巩县','522600','黔东南苗族侗族自治州',0,'522626','岑巩县','522600','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2552,'522627','天柱县','522600','黔东南苗族侗族自治州',22,'522627','天柱县','522600','520000','贵州省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2553,'522628','锦屏县','522600','黔东南苗族侗族自治州',15,'522628','锦屏县','522600','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2554,'522629','剑河县','522600','黔东南苗族侗族自治州',57,'522629','剑河县','522600','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2555,'522630','台江县','522600','黔东南苗族侗族自治州',21,'522630','台江县','522600','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2556,'522631','黎平县','522600','黔东南苗族侗族自治州',14,'522631','黎平县','522600','520000','贵州省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2557,'522632','榕江县','522600','黔东南苗族侗族自治州',0,'522632','榕江县','522600','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2558,'522633','从江县','522600','黔东南苗族侗族自治州',6,'522633','从江县','522600','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2559,'522634','雷山县','522600','黔东南苗族侗族自治州',3,'522634','雷山县','522600','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2560,'522635','麻江县','522600','黔东南苗族侗族自治州',0,'522635','麻江县','522600','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2561,'522636','丹寨县','522600','黔东南苗族侗族自治州',2,'522636','丹寨县','522600','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2562,'522700','黔南布依族苗族自治州','522700','黔南布依族苗族自治州',385,'1','','520000','520000','贵州省','正常',44);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2563,'522701','都匀市','522700','黔南布依族苗族自治州',44,'522701','都匀市','522700','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2564,'522702','福泉市','522700','黔南布依族苗族自治州',26,'522702','福泉市','522700','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2565,'522722','荔波县','522700','黔南布依族苗族自治州',0,'522722','荔波县','522700','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2566,'522723','贵定县','522700','黔南布依族苗族自治州',0,'522723','贵定县','522700','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2567,'522725','瓮安县','522700','黔南布依族苗族自治州',137,'522725','瓮安县','522700','520000','贵州省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2568,'522726','独山县','522700','黔南布依族苗族自治州',29,'522726','独山县','522700','520000','贵州省','正常',29);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2569,'522727','平塘县','522700','黔南布依族苗族自治州',10,'522727','平塘县','522700','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2570,'522728','罗甸县','522700','黔南布依族苗族自治州',10,'522728','罗甸县','522700','520000','贵州省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2571,'522729','长顺县','522700','黔南布依族苗族自治州',9,'522729','长顺县','522700','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2572,'522730','龙里县','522700','黔南布依族苗族自治州',17,'522730','龙里县','522700','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2573,'522731','惠水县','522700','黔南布依族苗族自治州',10,'522731','惠水县','522700','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2574,'522732','三都水族自治县','522700','黔南布依族苗族自治州',11,'522732','三都水族自治县','522700','520000','贵州省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2575,'530000','云南省','1','',6821,'','','100000','530000','云南省','正常',404);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2576,'530100','昆明市','530100','昆明市',2227,'1','','530000','530000','云南省','正常',157);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2577,'530102','五华区','530100','昆明市',398,'530102','五华区','530100','530000','云南省','正常',52);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2578,'530103','盘龙区','530100','昆明市',134,'530103','盘龙区','530100','530000','云南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2579,'530111','官渡区','530100','昆明市',480,'530111','官渡区','530100','530000','云南省','正常',73);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2580,'530112','西山区','530100','昆明市',193,'530112','西山区','530100','530000','云南省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2581,'530113','东川区','530100','昆明市',22,'530113','东川区','530100','530000','云南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2582,'530114','呈贡区','530100','昆明市',94,'530114','呈贡区','530100','530000','云南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2583,'530115','晋宁区','530100','昆明市',59,'530115','晋宁区','530100','530000','云南省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2584,'530124','富民县','530100','昆明市',0,'530124','富民县','530100','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2585,'530125','宜良县','530100','昆明市',5,'530125','宜良县','530100','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2586,'530126','石林彝族自治县','530100','昆明市',12,'530126','石林彝族自治县','530100','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2587,'530127','嵩明县','530100','昆明市',43,'530127','嵩明县','530100','530000','云南省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2588,'530128','禄劝彝族苗族自治县','530100','昆明市',44,'530128','禄劝彝族苗族自治县','530100','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2589,'530129','寻甸回族彝族自治县','530100','昆明市',30,'530129','寻甸回族彝族自治县','530100','530000','云南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2590,'530181','安宁市','530100','昆明市',33,'530181','安宁市','530100','530000','云南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2591,'530300','曲靖市','530300','曲靖市',571,'1','','530000','530000','云南省','正常',34);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2592,'530302','麒麟区','530300','曲靖市',143,'530302','麒麟区','530300','530000','云南省','正常',21);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2593,'530303','沾益区','530300','曲靖市',11,'530303','沾益区','530300','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2594,'530304','马龙区','530300','曲靖市',27,'530304','马龙区','530300','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2595,'530322','陆良县','530300','曲靖市',38,'530322','陆良县','530300','530000','云南省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2596,'530323','师宗县','530300','曲靖市',12,'530323','师宗县','530300','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2597,'530324','罗平县','530300','曲靖市',8,'530324','罗平县','530300','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2598,'530325','富源县','530300','曲靖市',28,'530325','富源县','530300','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2599,'530326','会泽县','530300','曲靖市',48,'530326','会泽县','530300','530000','云南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2600,'530381','宣威市','530300','曲靖市',78,'530381','宣威市','530300','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2601,'530400','玉溪市','530400','玉溪市',373,'1','','530000','530000','云南省','正常',46);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2602,'530402','红塔区','530400','玉溪市',165,'530402','红塔区','530400','530000','云南省','正常',30);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2603,'530403','江川区','530400','玉溪市',22,'530403','江川区','530400','530000','云南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2604,'530422','澄江县','530400','玉溪市',15,'530422','澄江县','530400','530000','云南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2605,'530423','通海县','530400','玉溪市',44,'530423','通海县','530400','530000','云南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2606,'530424','华宁县','530400','玉溪市',11,'530424','华宁县','530400','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2607,'530425','易门县','530400','玉溪市',13,'530425','易门县','530400','530000','云南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2608,'530426','峨山彝族自治县','530400','玉溪市',13,'530426','峨山彝族自治县','530400','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2609,'530427','新平彝族傣族自治县','530400','玉溪市',13,'530427','新平彝族傣族自治县','530400','530000','云南省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2610,'530428','元江哈尼族彝族傣族自治县','530400','玉溪市',8,'530428','元江哈尼族彝族傣族自治县','530400','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2611,'530500','保山市','530500','保山市',58,'1','','530000','530000','云南省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2612,'530502','隆阳区','530500','保山市',19,'530502','隆阳区','530500','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2613,'530521','施甸县','530500','保山市',2,'530521','施甸县','530500','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2614,'530523','龙陵县','530500','保山市',1,'530523','龙陵县','530500','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2615,'530524','昌宁县','530500','保山市',10,'530524','昌宁县','530500','530000','云南省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2616,'530581','腾冲市','530500','保山市',2,'530581','腾冲市','530500','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2617,'530600','昭通市','530600','昭通市',526,'1','','530000','530000','云南省','正常',35);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2618,'530602','昭阳区','530600','昭通市',63,'530602','昭阳区','530600','530000','云南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2619,'530621','鲁甸县','530600','昭通市',11,'530621','鲁甸县','530600','530000','云南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2620,'530622','巧家县','530600','昭通市',38,'530622','巧家县','530600','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2621,'530623','盐津县','530600','昭通市',5,'530623','盐津县','530600','530000','云南省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2622,'530624','大关县','530600','昭通市',3,'530624','大关县','530600','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2623,'530625','永善县','530600','昭通市',25,'530625','永善县','530600','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2624,'530626','绥江县','530600','昭通市',17,'530626','绥江县','530600','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2625,'530627','镇雄县','530600','昭通市',53,'530627','镇雄县','530600','530000','云南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2626,'530628','彝良县','530600','昭通市',7,'530628','彝良县','530600','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2627,'530629','威信县','530600','昭通市',42,'530629','威信县','530600','530000','云南省','正常',20);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2628,'530681','水富市','530600','昭通市',9,'530681','水富市','530600','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2629,'530700','丽江市','530700','丽江市',135,'1','','530000','530000','云南省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2630,'530702','古城区','530700','丽江市',11,'530702','古城区','530700','530000','云南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2631,'530721','玉龙纳西族自治县','530700','丽江市',3,'530721','玉龙纳西族自治县','530700','530000','云南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2632,'530722','永胜县','530700','丽江市',42,'530722','永胜县','530700','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2633,'530723','华坪县','530700','丽江市',57,'530723','华坪县','530700','530000','云南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2634,'530724','宁蒗彝族自治县','530700','丽江市',1,'530724','宁蒗彝族自治县','530700','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2635,'530800','普洱市','530800','普洱市',145,'1','','530000','530000','云南省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2636,'530802','思茅区','530800','普洱市',31,'530802','思茅区','530800','530000','云南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2637,'530821','宁洱哈尼族彝族自治县','530800','普洱市',3,'530821','宁洱哈尼族彝族自治县','530800','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2638,'530822','墨江哈尼族自治县','530800','普洱市',14,'530822','墨江哈尼族自治县','530800','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2639,'530823','景东彝族自治县','530800','普洱市',0,'530823','景东彝族自治县','530800','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2640,'530824','景谷傣族彝族自治县','530800','普洱市',6,'530824','景谷傣族彝族自治县','530800','530000','云南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2641,'530825','镇沅彝族哈尼族拉祜族自治县','530800','普洱市',1,'530825','镇沅彝族哈尼族拉祜族自治县','530800','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2642,'530826','江城哈尼族彝族自治县','530800','普洱市',12,'530826','江城哈尼族彝族自治县','530800','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2643,'530827','孟连傣族拉祜族佤族自治县','530800','普洱市',0,'530827','孟连傣族拉祜族佤族自治县','530800','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2644,'530828','澜沧拉祜族自治县','530800','普洱市',9,'530828','澜沧拉祜族自治县','530800','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2645,'530829','西盟佤族自治县','530800','普洱市',0,'530829','西盟佤族自治县','530800','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2646,'530900','临沧市','530900','临沧市',108,'1','','530000','530000','云南省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2647,'530902','临翔区','530900','临沧市',69,'530902','临翔区','530900','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2648,'530921','凤庆县','530900','临沧市',4,'530921','凤庆县','530900','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2649,'530922','云县','530900','临沧市',5,'530922','云县','530900','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2650,'530923','永德县','530900','临沧市',0,'530923','永德县','530900','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2651,'530924','镇康县','530900','临沧市',0,'530924','镇康县','530900','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2652,'530925','双江拉祜族佤族布朗族傣族自治县','530900','临沧市',0,'530925','双江拉祜族佤族布朗族傣族自治县','530900','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2653,'530926','耿马傣族佤族自治县','530900','临沧市',0,'530926','耿马傣族佤族自治县','530900','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2654,'530927','沧源佤族自治县','530900','临沧市',0,'530927','沧源佤族自治县','530900','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2655,'532300','楚雄彝族自治州','532300','楚雄彝族自治州',234,'1','','530000','530000','云南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2656,'532301','楚雄市','532300','楚雄彝族自治州',99,'532301','楚雄市','532300','530000','云南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2657,'532322','双柏县','532300','楚雄彝族自治州',36,'532322','双柏县','532300','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2658,'532323','牟定县','532300','楚雄彝族自治州',0,'532323','牟定县','532300','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2659,'532324','南华县','532300','楚雄彝族自治州',2,'532324','南华县','532300','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2660,'532325','姚安县','532300','楚雄彝族自治州',2,'532325','姚安县','532300','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2661,'532326','大姚县','532300','楚雄彝族自治州',0,'532326','大姚县','532300','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2662,'532327','永仁县','532300','楚雄彝族自治州',0,'532327','永仁县','532300','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2663,'532328','元谋县','532300','楚雄彝族自治州',1,'532328','元谋县','532300','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2664,'532329','武定县','532300','楚雄彝族自治州',19,'532329','武定县','532300','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2665,'532331','禄丰县','532300','楚雄彝族自治州',11,'532331','禄丰县','532300','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2666,'532500','红河哈尼族彝族自治州','532500','红河哈尼族彝族自治州',443,'1','','530000','530000','云南省','正常',52);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2667,'532501','个旧市','532500','红河哈尼族彝族自治州',60,'532501','个旧市','532500','530000','云南省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2668,'532502','开远市','532500','红河哈尼族彝族自治州',98,'532502','开远市','532500','530000','云南省','正常',31);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2669,'532503','蒙自市','532500','红河哈尼族彝族自治州',46,'532503','蒙自市','532500','530000','云南省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2670,'532504','弥勒市','532500','红河哈尼族彝族自治州',35,'532504','弥勒市','532500','530000','云南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2671,'532523','屏边苗族自治县','532500','红河哈尼族彝族自治州',1,'532523','屏边苗族自治县','532500','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2672,'532524','建水县','532500','红河哈尼族彝族自治州',22,'532524','建水县','532500','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2673,'532525','石屏县','532500','红河哈尼族彝族自治州',6,'532525','石屏县','532500','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2674,'532527','泸西县','532500','红河哈尼族彝族自治州',30,'532527','泸西县','532500','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2675,'532528','元阳县','532500','红河哈尼族彝族自治州',8,'532528','元阳县','532500','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2676,'532529','红河县','532500','红河哈尼族彝族自治州',2,'532529','红河县','532500','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2677,'532530','金平苗族瑶族傣族自治县','532500','红河哈尼族彝族自治州',11,'532530','金平苗族瑶族傣族自治县','532500','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2678,'532531','绿春县','532500','红河哈尼族彝族自治州',0,'532531','绿春县','532500','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2679,'532532','河口瑶族自治县','532500','红河哈尼族彝族自治州',1,'532532','河口瑶族自治县','532500','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2680,'532600','文山壮族苗族自治州','532600','文山壮族苗族自治州',336,'1','','530000','530000','云南省','正常',28);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2681,'532601','文山市','532600','文山壮族苗族自治州',58,'532601','文山市','532600','530000','云南省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2682,'532622','砚山县','532600','文山壮族苗族自治州',15,'532622','砚山县','532600','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2683,'532623','西畴县','532600','文山壮族苗族自治州',2,'532623','西畴县','532600','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2684,'532624','麻栗坡县','532600','文山壮族苗族自治州',7,'532624','麻栗坡县','532600','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2685,'532625','马关县','532600','文山壮族苗族自治州',27,'532625','马关县','532600','530000','云南省','正常',19);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2686,'532626','丘北县','532600','文山壮族苗族自治州',5,'532626','丘北县','532600','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2687,'532627','广南县','532600','文山壮族苗族自治州',82,'532627','广南县','532600','530000','云南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2688,'532628','富宁县','532600','文山壮族苗族自治州',63,'532628','富宁县','532600','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2689,'532800','西双版纳傣族自治州','532800','西双版纳傣族自治州',81,'1','','530000','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2690,'532801','景洪市','532800','西双版纳傣族自治州',40,'532801','景洪市','532800','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2691,'532822','勐海县','532800','西双版纳傣族自治州',31,'532822','勐海县','532800','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2692,'532823','勐腊县','532800','西双版纳傣族自治州',5,'532823','勐腊县','532800','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2693,'532900','大理白族自治州','532900','大理白族自治州',188,'1','','530000','530000','云南省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2694,'532901','大理市','532900','大理白族自治州',85,'532901','大理市','532900','530000','云南省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2695,'532922','漾濞彝族自治县','532900','大理白族自治州',0,'532922','漾濞彝族自治县','532900','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2696,'532923','祥云县','532900','大理白族自治州',19,'532923','祥云县','532900','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2697,'532924','宾川县','532900','大理白族自治州',14,'532924','宾川县','532900','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2698,'532925','弥渡县','532900','大理白族自治州',2,'532925','弥渡县','532900','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2699,'532926','南涧彝族自治县','532900','大理白族自治州',1,'532926','南涧彝族自治县','532900','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2700,'532927','巍山彝族回族自治县','532900','大理白族自治州',0,'532927','巍山彝族回族自治县','532900','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2701,'532928','永平县','532900','大理白族自治州',9,'532928','永平县','532900','530000','云南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2702,'532929','云龙县','532900','大理白族自治州',2,'532929','云龙县','532900','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2703,'532930','洱源县','532900','大理白族自治州',12,'532930','洱源县','532900','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2704,'532931','剑川县','532900','大理白族自治州',4,'532931','剑川县','532900','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2705,'532932','鹤庆县','532900','大理白族自治州',0,'532932','鹤庆县','532900','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2706,'533100','德宏傣族景颇族自治州','533100','德宏傣族景颇族自治州',75,'1','','530000','530000','云南省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2707,'533102','瑞丽市','533100','德宏傣族景颇族自治州',20,'533102','瑞丽市','533100','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2708,'533103','芒市','533100','德宏傣族景颇族自治州',26,'533103','芒市','533100','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2709,'533122','梁河县','533100','德宏傣族景颇族自治州',0,'533122','梁河县','533100','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2710,'533123','盈江县','533100','德宏傣族景颇族自治州',18,'533123','盈江县','533100','530000','云南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2711,'533124','陇川县','533100','德宏傣族景颇族自治州',4,'533124','陇川县','533100','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2712,'533300','怒江傈僳族自治州','533300','怒江傈僳族自治州',14,'1','','530000','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2713,'533301','泸水市','533300','怒江傈僳族自治州',13,'533301','泸水市','533300','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2714,'533323','福贡县','533300','怒江傈僳族自治州',0,'533323','福贡县','533300','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2715,'533324','贡山独龙族怒族自治县','533300','怒江傈僳族自治州',0,'533324','贡山独龙族怒族自治县','533300','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2716,'533325','兰坪白族普米族自治县','533300','怒江傈僳族自治州',0,'533325','兰坪白族普米族自治县','533300','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2717,'533400','迪庆藏族自治州','533400','迪庆藏族自治州',15,'1','','530000','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2718,'533401','香格里拉市','533400','迪庆藏族自治州',0,'533401','香格里拉市','533400','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2719,'533422','德钦县','533400','迪庆藏族自治州',0,'533422','德钦县','533400','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2720,'533423','维西傈僳族自治县','533400','迪庆藏族自治州',15,'533423','维西傈僳族自治县','533400','530000','云南省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2721,'540000','西藏自治区','1','',103,'','','100000','540000','西藏自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2722,'540100','拉萨市','540100','拉萨市',64,'1','','540000','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2723,'540102','城关区','540100','拉萨市',21,'540102','城关区','540100','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2724,'540103','堆龙德庆区','540100','拉萨市',24,'540103','堆龙德庆区','540100','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2725,'540104','达孜区','540100','拉萨市',0,'540104','达孜区','540100','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2726,'540121','林周县','540100','拉萨市',0,'540121','林周县','540100','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2727,'540122','当雄县','540100','拉萨市',0,'540122','当雄县','540100','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2728,'540123','尼木县','540100','拉萨市',0,'540123','尼木县','540100','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2729,'540124','曲水县','540100','拉萨市',0,'540124','曲水县','540100','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2730,'540127','墨竹工卡县','540100','拉萨市',0,'540127','墨竹工卡县','540100','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2731,'540200','日喀则市','540200','日喀则市',2,'1','','540000','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2732,'540202','桑珠孜区','540200','日喀则市',0,'540202','桑珠孜区','540200','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2733,'540221','南木林县','540200','日喀则市',0,'540221','南木林县','540200','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2734,'540222','江孜县','540200','日喀则市',0,'540222','江孜县','540200','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2735,'540223','定日县','540200','日喀则市',0,'540223','定日县','540200','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2736,'540224','萨迦县','540200','日喀则市',0,'540224','萨迦县','540200','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2737,'540225','拉孜县','540200','日喀则市',0,'540225','拉孜县','540200','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2738,'540226','昂仁县','540200','日喀则市',0,'540226','昂仁县','540200','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2739,'540227','谢通门县','540200','日喀则市',0,'540227','谢通门县','540200','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2740,'540228','白朗县','540200','日喀则市',0,'540228','白朗县','540200','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2741,'540229','仁布县','540200','日喀则市',0,'540229','仁布县','540200','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2742,'540230','康马县','540200','日喀则市',0,'540230','康马县','540200','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2743,'540231','定结县','540200','日喀则市',0,'540231','定结县','540200','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2744,'540232','仲巴县','540200','日喀则市',1,'540232','仲巴县','540200','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2745,'540233','亚东县','540200','日喀则市',0,'540233','亚东县','540200','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2746,'540234','吉隆县','540200','日喀则市',0,'540234','吉隆县','540200','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2747,'540235','聂拉木县','540200','日喀则市',0,'540235','聂拉木县','540200','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2748,'540236','萨嘎县','540200','日喀则市',0,'540236','萨嘎县','540200','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2749,'540237','岗巴县','540200','日喀则市',0,'540237','岗巴县','540200','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2750,'540300','昌都市','540300','昌都市',6,'1','','540000','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2751,'540302','卡若区','540300','昌都市',6,'540302','卡若区','540300','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2752,'540321','江达县','540300','昌都市',0,'540321','江达县','540300','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2753,'540322','贡觉县','540300','昌都市',0,'540322','贡觉县','540300','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2754,'540323','类乌齐县','540300','昌都市',0,'540323','类乌齐县','540300','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2755,'540324','丁青县','540300','昌都市',0,'540324','丁青县','540300','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2756,'540325','察雅县','540300','昌都市',0,'540325','察雅县','540300','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2757,'540326','八宿县','540300','昌都市',0,'540326','八宿县','540300','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2758,'540327','左贡县','540300','昌都市',0,'540327','左贡县','540300','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2759,'540328','芒康县','540300','昌都市',0,'540328','芒康县','540300','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2760,'540329','洛隆县','540300','昌都市',0,'540329','洛隆县','540300','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2761,'540330','边坝县','540300','昌都市',0,'540330','边坝县','540300','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2762,'540400','林芝市','540400','林芝市',0,'1','','540000','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2763,'540402','巴宜区','540400','林芝市',0,'540402','巴宜区','540400','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2764,'540421','工布江达县','540400','林芝市',0,'540421','工布江达县','540400','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2765,'540422','米林县','540400','林芝市',0,'540422','米林县','540400','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2766,'540423','墨脱县','540400','林芝市',0,'540423','墨脱县','540400','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2767,'540424','波密县','540400','林芝市',0,'540424','波密县','540400','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2768,'540425','察隅县','540400','林芝市',0,'540425','察隅县','540400','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2769,'540426','朗县','540400','林芝市',0,'540426','朗县','540400','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2770,'540500','山南市','540500','山南市',6,'1','','540000','540000','西藏自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2771,'540502','乃东区','540500','山南市',0,'540502','乃东区','540500','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2772,'540521','扎囊县','540500','山南市',0,'540521','扎囊县','540500','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2773,'540522','贡嘎县','540500','山南市',0,'540522','贡嘎县','540500','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2774,'540523','桑日县','540500','山南市',1,'540523','桑日县','540500','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2775,'540524','琼结县','540500','山南市',0,'540524','琼结县','540500','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2776,'540525','曲松县','540500','山南市',0,'540525','曲松县','540500','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2777,'540526','措美县','540500','山南市',0,'540526','措美县','540500','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2778,'540527','洛扎县','540500','山南市',0,'540527','洛扎县','540500','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2779,'540528','加查县','540500','山南市',0,'540528','加查县','540500','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2780,'540529','隆子县','540500','山南市',0,'540529','隆子县','540500','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2781,'540530','错那县','540500','山南市',0,'540530','错那县','540500','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2782,'540531','浪卡子县','540500','山南市',2,'540531','浪卡子县','540500','540000','西藏自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2783,'540600','那曲市','540600','那曲市',13,'1','','540000','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2784,'540602','色尼区','540600','那曲市',0,'540602','色尼区','540600','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2785,'540621','嘉黎县','540600','那曲市',0,'540621','嘉黎县','540600','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2786,'540622','比如县','540600','那曲市',0,'540622','比如县','540600','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2787,'540623','聂荣县','540600','那曲市',0,'540623','聂荣县','540600','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2788,'540624','安多县','540600','那曲市',0,'540624','安多县','540600','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2789,'540625','申扎县','540600','那曲市',0,'540625','申扎县','540600','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2790,'540626','索县','540600','那曲市',0,'540626','索县','540600','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2791,'540627','班戈县','540600','那曲市',0,'540627','班戈县','540600','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2792,'540628','巴青县','540600','那曲市',0,'540628','巴青县','540600','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2793,'540629','尼玛县','540600','那曲市',13,'540629','尼玛县','540600','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2794,'540630','双湖县','540600','那曲市',0,'540630','双湖县','540600','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2795,'542500','阿里地区','542500','阿里地区',0,'1','','540000','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2796,'542521','普兰县','542500','阿里地区',0,'542521','普兰县','542500','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2797,'542522','札达县','542500','阿里地区',0,'542522','札达县','542500','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2798,'542523','噶尔县','542500','阿里地区',0,'542523','噶尔县','542500','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2799,'542524','日土县','542500','阿里地区',0,'542524','日土县','542500','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2800,'542525','革吉县','542500','阿里地区',0,'542525','革吉县','542500','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2801,'542526','改则县','542500','阿里地区',0,'542526','改则县','542500','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2802,'542527','措勤县','542500','阿里地区',0,'542527','措勤县','542500','540000','西藏自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2803,'610000','陕西省','1','',11899,'','','100000','610000','陕西省','正常',621);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2804,'610100','西安市','610100','西安市',5382,'1','','610000','610000','陕西省','正常',317);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2805,'610102','新城区','610100','西安市',154,'610102','新城区','610100','610000','陕西省','正常',10);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2806,'610103','碑林区','610100','西安市',192,'610103','碑林区','610100','610000','陕西省','正常',23);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2807,'610104','莲湖区','610100','西安市',237,'610104','莲湖区','610100','610000','陕西省','正常',12);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2808,'610111','灞桥区','610100','西安市',433,'610111','灞桥区','610100','610000','陕西省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2809,'610112','未央区','610100','西安市',668,'610112','未央区','610100','610000','陕西省','正常',46);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2810,'610113','雁塔区','610100','西安市',857,'610113','雁塔区','610100','610000','陕西省','正常',104);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2811,'610114','阎良区','610100','西安市',204,'610114','阎良区','610100','610000','陕西省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2812,'610115','临潼区','610100','西安市',100,'610115','临潼区','610100','610000','陕西省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2813,'610116','长安区','610100','西安市',285,'610116','长安区','610100','610000','陕西省','正常',25);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2814,'610117','高陵区','610100','西安市',76,'610117','高陵区','610100','610000','陕西省','正常',18);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2815,'610118','鄠邑区','610100','西安市',129,'610118','鄠邑区','610100','610000','陕西省','正常',27);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2816,'610122','蓝田县','610100','西安市',53,'610122','蓝田县','610100','610000','陕西省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2817,'610124','周至县','610100','西安市',52,'610124','周至县','610100','610000','陕西省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2818,'610200','铜川市','610200','铜川市',43,'1','','610000','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2819,'610202','王益区','610200','铜川市',20,'610202','王益区','610200','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2820,'610203','印台区','610200','铜川市',0,'610203','印台区','610200','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2821,'610204','耀州区','610200','铜川市',12,'610204','耀州区','610200','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2822,'610222','宜君县','610200','铜川市',0,'610222','宜君县','610200','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2823,'610300','宝鸡市','610300','宝鸡市',753,'1','','610000','610000','陕西省','正常',25);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2824,'610302','渭滨区','610300','宝鸡市',110,'610302','渭滨区','610300','610000','陕西省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2825,'610303','金台区','610300','宝鸡市',37,'610303','金台区','610300','610000','陕西省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2826,'610304','陈仓区','610300','宝鸡市',120,'610304','陈仓区','610300','610000','陕西省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2827,'610322','凤翔县','610300','宝鸡市',24,'610322','凤翔县','610300','610000','陕西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2828,'610323','岐山县','610300','宝鸡市',95,'610323','岐山县','610300','610000','陕西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2829,'610324','扶风县','610300','宝鸡市',18,'610324','扶风县','610300','610000','陕西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2830,'610326','眉县','610300','宝鸡市',86,'610326','眉县','610300','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2831,'610327','陇县','610300','宝鸡市',1,'610327','陇县','610300','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2832,'610328','千阳县','610300','宝鸡市',15,'610328','千阳县','610300','610000','陕西省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2833,'610329','麟游县','610300','宝鸡市',12,'610329','麟游县','610300','610000','陕西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2834,'610330','凤县','610300','宝鸡市',5,'610330','凤县','610300','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2835,'610331','太白县','610300','宝鸡市',0,'610331','太白县','610300','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2836,'610400','咸阳市','610400','咸阳市',1074,'1','','610000','610000','陕西省','正常',80);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2837,'610402','秦都区','610400','咸阳市',283,'610402','秦都区','610400','610000','陕西省','正常',20);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2838,'610403','杨陵区','610400','咸阳市',61,'610403','杨陵区','610400','610000','陕西省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2839,'610404','渭城区','610400','咸阳市',69,'610404','渭城区','610400','610000','陕西省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2840,'610422','三原县','610400','咸阳市',97,'610422','三原县','610400','610000','陕西省','正常',32);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2841,'610423','泾阳县','610400','咸阳市',26,'610423','泾阳县','610400','610000','陕西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2842,'610424','乾县','610400','咸阳市',10,'610424','乾县','610400','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2843,'610425','礼泉县','610400','咸阳市',31,'610425','礼泉县','610400','610000','陕西省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2844,'610426','永寿县','610400','咸阳市',16,'610426','永寿县','610400','610000','陕西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2845,'610482','彬州市','610400','咸阳市',29,'610482','彬州市','610400','610000','陕西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2846,'610428','长武县','610400','咸阳市',16,'610428','长武县','610400','610000','陕西省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2847,'610429','旬邑县','610400','咸阳市',4,'610429','旬邑县','610400','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2848,'610430','淳化县','610400','咸阳市',0,'610430','淳化县','610400','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2849,'610431','武功县','610400','咸阳市',23,'610431','武功县','610400','610000','陕西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2850,'610481','兴平市','610400','咸阳市',52,'610481','兴平市','610400','610000','陕西省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2851,'610500','渭南市','610500','渭南市',743,'1','','610000','610000','陕西省','正常',72);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2852,'610502','临渭区','610500','渭南市',174,'610502','临渭区','610500','610000','陕西省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2853,'610503','华州区','610500','渭南市',8,'610503','华州区','610500','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2854,'610522','潼关县','610500','渭南市',42,'610522','潼关县','610500','610000','陕西省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2855,'610523','大荔县','610500','渭南市',89,'610523','大荔县','610500','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2856,'610524','合阳县','610500','渭南市',6,'610524','合阳县','610500','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2857,'610525','澄城县','610500','渭南市',14,'610525','澄城县','610500','610000','陕西省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2858,'610526','蒲城县','610500','渭南市',45,'610526','蒲城县','610500','610000','陕西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2859,'610527','白水县','610500','渭南市',3,'610527','白水县','610500','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2860,'610528','富平县','610500','渭南市',40,'610528','富平县','610500','610000','陕西省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2861,'610581','韩城市','610500','渭南市',118,'610581','韩城市','610500','610000','陕西省','正常',16);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2862,'610582','华阴市','610500','渭南市',10,'610582','华阴市','610500','610000','陕西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2863,'610600','延安市','610600','延安市',638,'1','','610000','610000','陕西省','正常',31);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2864,'610602','宝塔区','610600','延安市',281,'610602','宝塔区','610600','610000','陕西省','正常',12);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2865,'610603','安塞区','610600','延安市',42,'610603','安塞区','610600','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2866,'610621','延长县','610600','延安市',0,'610621','延长县','610600','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2867,'610622','延川县','610600','延安市',15,'610622','延川县','610600','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2868,'610623','子长县','610600','延安市',28,'610623','子长县','610600','610000','陕西省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2869,'610625','志丹县','610600','延安市',19,'610625','志丹县','610600','610000','陕西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2870,'610626','吴起县','610600','延安市',22,'610626','吴起县','610600','610000','陕西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2871,'610627','甘泉县','610600','延安市',0,'610627','甘泉县','610600','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2872,'610628','富县','610600','延安市',20,'610628','富县','610600','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2873,'610629','洛川县','610600','延安市',28,'610629','洛川县','610600','610000','陕西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2874,'610630','宜川县','610600','延安市',10,'610630','宜川县','610600','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2875,'610631','黄龙县','610600','延安市',8,'610631','黄龙县','610600','610000','陕西省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2876,'610632','黄陵县','610600','延安市',5,'610632','黄陵县','610600','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2877,'610700','汉中市','610700','汉中市',428,'1','','610000','610000','陕西省','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2878,'610702','汉台区','610700','汉中市',90,'610702','汉台区','610700','610000','陕西省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2879,'610703','南郑区','610700','汉中市',20,'610703','南郑区','610700','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2880,'610722','城固县','610700','汉中市',142,'610722','城固县','610700','610000','陕西省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2881,'610723','洋县','610700','汉中市',14,'610723','洋县','610700','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2882,'610724','西乡县','610700','汉中市',37,'610724','西乡县','610700','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2883,'610725','勉县','610700','汉中市',12,'610725','勉县','610700','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2884,'610726','宁强县','610700','汉中市',1,'610726','宁强县','610700','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2885,'610727','略阳县','610700','汉中市',12,'610727','略阳县','610700','610000','陕西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2886,'610728','镇巴县','610700','汉中市',15,'610728','镇巴县','610700','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2887,'610729','留坝县','610700','汉中市',0,'610729','留坝县','610700','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2888,'610730','佛坪县','610700','汉中市',0,'610730','佛坪县','610700','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2889,'610800','榆林市','610800','榆林市',986,'1','','610000','610000','陕西省','正常',37);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2890,'610802','榆阳区','610800','榆林市',507,'610802','榆阳区','610800','610000','陕西省','正常',21);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2891,'610803','横山区','610800','榆林市',113,'610803','横山区','610800','610000','陕西省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2892,'610822','府谷县','610800','榆林市',47,'610822','府谷县','610800','610000','陕西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2893,'610824','靖边县','610800','榆林市',27,'610824','靖边县','610800','610000','陕西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2894,'610825','定边县','610800','榆林市',24,'610825','定边县','610800','610000','陕西省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2895,'610826','绥德县','610800','榆林市',14,'610826','绥德县','610800','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2896,'610827','米脂县','610800','榆林市',0,'610827','米脂县','610800','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2897,'610828','佳县','610800','榆林市',0,'610828','佳县','610800','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2898,'610829','吴堡县','610800','榆林市',21,'610829','吴堡县','610800','610000','陕西省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2899,'610830','清涧县','610800','榆林市',0,'610830','清涧县','610800','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2900,'610831','子洲县','610800','榆林市',0,'610831','子洲县','610800','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2901,'610881','神木市','610800','榆林市',137,'610881','神木市','610800','610000','陕西省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2902,'610900','安康市','610900','安康市',264,'1','','610000','610000','陕西省','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2903,'610902','汉滨区','610900','安康市',154,'610902','汉滨区','610900','610000','陕西省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2904,'610921','汉阴县','610900','安康市',6,'610921','汉阴县','610900','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2905,'610922','石泉县','610900','安康市',0,'610922','石泉县','610900','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2906,'610923','宁陕县','610900','安康市',0,'610923','宁陕县','610900','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2907,'610924','紫阳县','610900','安康市',1,'610924','紫阳县','610900','610000','陕西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2908,'610925','岚皋县','610900','安康市',15,'610925','岚皋县','610900','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2909,'610926','平利县','610900','安康市',10,'610926','平利县','610900','610000','陕西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2910,'610927','镇坪县','610900','安康市',3,'610927','镇坪县','610900','610000','陕西省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2911,'610928','旬阳县','610900','安康市',4,'610928','旬阳县','610900','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2912,'610929','白河县','610900','安康市',4,'610929','白河县','610900','610000','陕西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2913,'611000','商洛市','611000','商洛市',171,'1','','610000','610000','陕西省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2914,'611002','商州区','611000','商洛市',25,'611002','商州区','611000','610000','陕西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2915,'611021','洛南县','611000','商洛市',9,'611021','洛南县','611000','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2916,'611022','丹凤县','611000','商洛市',5,'611022','丹凤县','611000','610000','陕西省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2917,'611023','商南县','611000','商洛市',46,'611023','商南县','611000','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2918,'611024','山阳县','611000','商洛市',33,'611024','山阳县','611000','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2919,'611025','镇安县','611000','商洛市',3,'611025','镇安县','611000','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2920,'611026','柞水县','611000','商洛市',16,'611026','柞水县','611000','610000','陕西省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2921,'620000','甘肃省','1','',5018,'','','100000','620000','甘肃省','正常',267);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2922,'620100','兰州市','620100','兰州市',1064,'1','','620000','620000','甘肃省','正常',62);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2923,'620102','城关区','620100','兰州市',277,'620102','城关区','620100','620000','甘肃省','正常',24);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2924,'620103','七里河区','620100','兰州市',154,'620103','七里河区','620100','620000','甘肃省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2925,'620104','西固区','620100','兰州市',73,'620104','西固区','620100','620000','甘肃省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2926,'620105','安宁区','620100','兰州市',81,'620105','安宁区','620100','620000','甘肃省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2927,'620111','红古区','620100','兰州市',32,'620111','红古区','620100','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2928,'620121','永登县','620100','兰州市',76,'620121','永登县','620100','620000','甘肃省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2929,'620122','皋兰县','620100','兰州市',15,'620122','皋兰县','620100','620000','甘肃省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2930,'620123','榆中县','620100','兰州市',52,'620123','榆中县','620100','620000','甘肃省','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2931,'620200','嘉峪关市','620200','嘉峪关市',159,'1','','620000','620000','甘肃省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2932,'620300','金昌市','620300','金昌市',76,'1','','620000','620000','甘肃省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2933,'620302','金川区','620300','金昌市',37,'620302','金川区','620300','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2934,'620321','永昌县','620300','金昌市',25,'620321','永昌县','620300','620000','甘肃省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2935,'620400','白银市','620400','白银市',210,'1','','620000','620000','甘肃省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2936,'620402','白银区','620400','白银市',74,'620402','白银区','620400','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2937,'620403','平川区','620400','白银市',42,'620403','平川区','620400','620000','甘肃省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2938,'620421','靖远县','620400','白银市',10,'620421','靖远县','620400','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2939,'620422','会宁县','620400','白银市',48,'620422','会宁县','620400','620000','甘肃省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2940,'620423','景泰县','620400','白银市',5,'620423','景泰县','620400','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2941,'620500','天水市','620500','天水市',418,'1','','620000','620000','甘肃省','正常',34);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2942,'620502','秦州区','620500','天水市',95,'620502','秦州区','620500','620000','甘肃省','正常',14);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2943,'620503','麦积区','620500','天水市',172,'620503','麦积区','620500','620000','甘肃省','正常',15);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2944,'620521','清水县','620500','天水市',29,'620521','清水县','620500','620000','甘肃省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2945,'620522','秦安县','620500','天水市',6,'620522','秦安县','620500','620000','甘肃省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2946,'620523','甘谷县','620500','天水市',28,'620523','甘谷县','620500','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2947,'620524','武山县','620500','天水市',11,'620524','武山县','620500','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2948,'620525','张家川回族自治县','620500','天水市',4,'620525','张家川回族自治县','620500','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2949,'620600','武威市','620600','武威市',279,'1','','620000','620000','甘肃省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2950,'620602','凉州区','620600','武威市',128,'620602','凉州区','620600','620000','甘肃省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2951,'620621','民勤县','620600','武威市',0,'620621','民勤县','620600','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2952,'620622','古浪县','620600','武威市',64,'620622','古浪县','620600','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2953,'620623','天祝藏族自治县','620600','武威市',15,'620623','天祝藏族自治县','620600','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2954,'620700','张掖市','620700','张掖市',224,'1','','620000','620000','甘肃省','正常',20);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2955,'620702','甘州区','620700','张掖市',146,'620702','甘州区','620700','620000','甘肃省','正常',16);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2956,'620721','肃南裕固族自治县','620700','张掖市',0,'620721','肃南裕固族自治县','620700','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2957,'620722','民乐县','620700','张掖市',5,'620722','民乐县','620700','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2958,'620723','临泽县','620700','张掖市',2,'620723','临泽县','620700','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2959,'620724','高台县','620700','张掖市',2,'620724','高台县','620700','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2960,'620725','山丹县','620700','张掖市',36,'620725','山丹县','620700','620000','甘肃省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2961,'620800','平凉市','620800','平凉市',230,'1','','620000','620000','甘肃省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2962,'620802','崆峒区','620800','平凉市',69,'620802','崆峒区','620800','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2963,'620821','泾川县','620800','平凉市',30,'620821','泾川县','620800','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2964,'620822','灵台县','620800','平凉市',3,'620822','灵台县','620800','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2965,'620823','崇信县','620800','平凉市',7,'620823','崇信县','620800','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2966,'620881','华亭市','620800','平凉市',13,'620881','华亭市','620800','620000','甘肃省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2967,'620825','庄浪县','620800','平凉市',39,'620825','庄浪县','620800','620000','甘肃省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2968,'620826','静宁县','620800','平凉市',27,'620826','静宁县','620800','620000','甘肃省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2969,'620900','酒泉市','620900','酒泉市',246,'1','','620000','620000','甘肃省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2970,'620902','肃州区','620900','酒泉市',65,'620902','肃州区','620900','620000','甘肃省','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2971,'620921','金塔县','620900','酒泉市',0,'620921','金塔县','620900','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2972,'620922','瓜州县','620900','酒泉市',0,'620922','瓜州县','620900','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2973,'620923','肃北蒙古族自治县','620900','酒泉市',0,'620923','肃北蒙古族自治县','620900','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2974,'620924','阿克塞哈萨克族自治县','620900','酒泉市',0,'620924','阿克塞哈萨克族自治县','620900','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2975,'620981','玉门市','620900','酒泉市',115,'620981','玉门市','620900','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2976,'620982','敦煌市','620900','酒泉市',11,'620982','敦煌市','620900','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2977,'621000','庆阳市','621000','庆阳市',277,'1','','620000','620000','甘肃省','正常',24);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2978,'621002','西峰区','621000','庆阳市',61,'621002','西峰区','621000','620000','甘肃省','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2979,'621021','庆城县','621000','庆阳市',11,'621021','庆城县','621000','620000','甘肃省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2980,'621022','环县','621000','庆阳市',9,'621022','环县','621000','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2981,'621023','华池县','621000','庆阳市',11,'621023','华池县','621000','620000','甘肃省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2982,'621024','合水县','621000','庆阳市',5,'621024','合水县','621000','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2983,'621025','正宁县','621000','庆阳市',25,'621025','正宁县','621000','620000','甘肃省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2984,'621026','宁县','621000','庆阳市',48,'621026','宁县','621000','620000','甘肃省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2985,'621027','镇原县','621000','庆阳市',2,'621027','镇原县','621000','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2986,'621100','定西市','621100','定西市',347,'1','','620000','620000','甘肃省','正常',24);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2987,'621102','安定区','621100','定西市',77,'621102','安定区','621100','620000','甘肃省','正常',16);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2988,'621121','通渭县','621100','定西市',32,'621121','通渭县','621100','620000','甘肃省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2989,'621122','陇西县','621100','定西市',44,'621122','陇西县','621100','620000','甘肃省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2990,'621123','渭源县','621100','定西市',0,'621123','渭源县','621100','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2991,'621124','临洮县','621100','定西市',47,'621124','临洮县','621100','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2992,'621125','漳县','621100','定西市',0,'621125','漳县','621100','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2993,'621126','岷县','621100','定西市',18,'621126','岷县','621100','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2994,'621200','陇南市','621200','陇南市',314,'1','','620000','620000','甘肃省','正常',34);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2995,'621202','武都区','621200','陇南市',76,'621202','武都区','621200','620000','甘肃省','正常',19);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2996,'621221','成县','621200','陇南市',6,'621221','成县','621200','620000','甘肃省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2997,'621222','文县','621200','陇南市',1,'621222','文县','621200','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2998,'621223','宕昌县','621200','陇南市',0,'621223','宕昌县','621200','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (2999,'621224','康县','621200','陇南市',7,'621224','康县','621200','620000','甘肃省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3000,'621225','西和县','621200','陇南市',42,'621225','西和县','621200','620000','甘肃省','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3001,'621226','礼县','621200','陇南市',63,'621226','礼县','621200','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3002,'621227','徽县','621200','陇南市',7,'621227','徽县','621200','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3003,'621228','两当县','621200','陇南市',7,'621228','两当县','621200','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3004,'622900','临夏回族自治州','622900','临夏回族自治州',269,'1','','620000','620000','甘肃省','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3005,'622901','临夏市','622900','临夏回族自治州',107,'622901','临夏市','622900','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3006,'622921','临夏县','622900','临夏回族自治州',2,'622921','临夏县','622900','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3007,'622922','康乐县','622900','临夏回族自治州',0,'622922','康乐县','622900','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3008,'622923','永靖县','622900','临夏回族自治州',9,'622923','永靖县','622900','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3009,'622924','广河县','622900','临夏回族自治州',34,'622924','广河县','622900','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3010,'622925','和政县','622900','临夏回族自治州',25,'622925','和政县','622900','620000','甘肃省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3011,'622926','东乡族自治县','622900','临夏回族自治州',0,'622926','东乡族自治县','622900','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3012,'622927','积石山保安族东乡族撒拉族自治县','622900','临夏回族自治州',41,'622927','积石山保安族东乡族撒拉族自治县','622900','620000','甘肃省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3013,'623000','甘南藏族自治州','623000','甘南藏族自治州',36,'1','','620000','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3014,'623001','合作市','623000','甘南藏族自治州',1,'623001','合作市','623000','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3015,'623021','临潭县','623000','甘南藏族自治州',0,'623021','临潭县','623000','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3016,'623022','卓尼县','623000','甘南藏族自治州',3,'623022','卓尼县','623000','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3017,'623023','舟曲县','623000','甘南藏族自治州',4,'623023','舟曲县','623000','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3018,'623024','迭部县','623000','甘南藏族自治州',2,'623024','迭部县','623000','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3019,'623025','玛曲县','623000','甘南藏族自治州',0,'623025','玛曲县','623000','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3020,'623026','碌曲县','623000','甘南藏族自治州',0,'623026','碌曲县','623000','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3021,'623027','夏河县','623000','甘南藏族自治州',12,'623027','夏河县','623000','620000','甘肃省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3022,'630000','青海省','1','',975,'','','100000','630000','青海省','正常',62);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3023,'630100','西宁市','630100','西宁市',489,'1','','630000','630000','青海省','正常',24);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3024,'630102','城东区','630100','西宁市',56,'630102','城东区','630100','630000','青海省','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3025,'630103','城中区','630100','西宁市',35,'630103','城中区','630100','630000','青海省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3026,'630104','城西区','630100','西宁市',79,'630104','城西区','630100','630000','青海省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3027,'630105','城北区','630100','西宁市',139,'630105','城北区','630100','630000','青海省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3028,'630121','大通回族土族自治县','630100','西宁市',25,'630121','大通回族土族自治县','630100','630000','青海省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3029,'630122','湟中县','630100','西宁市',14,'630122','湟中县','630100','630000','青海省','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3030,'630123','湟源县','630100','西宁市',12,'630123','湟源县','630100','630000','青海省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3031,'630200','海东市','630200','海东市',307,'1','','630000','630000','青海省','正常',28);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3032,'630202','乐都区','630200','海东市',39,'630202','乐都区','630200','630000','青海省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3033,'630203','平安区','630200','海东市',12,'630203','平安区','630200','630000','青海省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3034,'630222','民和回族土族自治县','630200','海东市',56,'630222','民和回族土族自治县','630200','630000','青海省','正常',28);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3035,'630223','互助土族自治县','630200','海东市',41,'630223','互助土族自治县','630200','630000','青海省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3036,'630224','化隆回族自治县','630200','海东市',93,'630224','化隆回族自治县','630200','630000','青海省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3037,'630225','循化撒拉族自治县','630200','海东市',11,'630225','循化撒拉族自治县','630200','630000','青海省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3038,'632200','海北藏族自治州','632200','海北藏族自治州',20,'1','','630000','630000','青海省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3039,'632221','门源回族自治县','632200','海北藏族自治州',15,'632221','门源回族自治县','632200','630000','青海省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3040,'632222','祁连县','632200','海北藏族自治州',0,'632222','祁连县','632200','630000','青海省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3041,'632223','海晏县','632200','海北藏族自治州',0,'632223','海晏县','632200','630000','青海省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3042,'632224','刚察县','632200','海北藏族自治州',5,'632224','刚察县','632200','630000','青海省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3043,'632300','黄南藏族自治州','632300','黄南藏族自治州',1,'1','','630000','630000','青海省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3044,'632321','同仁县','632300','黄南藏族自治州',1,'632321','同仁县','632300','630000','青海省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3045,'632322','尖扎县','632300','黄南藏族自治州',0,'632322','尖扎县','632300','630000','青海省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3046,'632323','泽库县','632300','黄南藏族自治州',0,'632323','泽库县','632300','630000','青海省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3047,'632324','河南蒙古族自治县','632300','黄南藏族自治州',0,'632324','河南蒙古族自治县','632300','630000','青海省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3048,'632500','海南藏族自治州','632500','海南藏族自治州',9,'1','','630000','630000','青海省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3049,'632521','共和县','632500','海南藏族自治州',5,'632521','共和县','632500','630000','青海省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3050,'632522','同德县','632500','海南藏族自治州',0,'632522','同德县','632500','630000','青海省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3051,'632523','贵德县','632500','海南藏族自治州',4,'632523','贵德县','632500','630000','青海省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3052,'632524','兴海县','632500','海南藏族自治州',0,'632524','兴海县','632500','630000','青海省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3053,'632525','贵南县','632500','海南藏族自治州',0,'632525','贵南县','632500','630000','青海省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3054,'632600','果洛藏族自治州','632600','果洛藏族自治州',4,'1','','630000','630000','青海省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3055,'632621','玛沁县','632600','果洛藏族自治州',0,'632621','玛沁县','632600','630000','青海省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3056,'632622','班玛县','632600','果洛藏族自治州',0,'632622','班玛县','632600','630000','青海省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3057,'632623','甘德县','632600','果洛藏族自治州',0,'632623','甘德县','632600','630000','青海省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3058,'632624','达日县','632600','果洛藏族自治州',4,'632624','达日县','632600','630000','青海省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3059,'632625','久治县','632600','果洛藏族自治州',0,'632625','久治县','632600','630000','青海省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3060,'632626','玛多县','632600','果洛藏族自治州',0,'632626','玛多县','632600','630000','青海省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3061,'632700','玉树藏族自治州','632700','玉树藏族自治州',2,'1','','630000','630000','青海省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3062,'632701','玉树市','632700','玉树藏族自治州',0,'632701','玉树市','632700','630000','青海省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3063,'632722','杂多县','632700','玉树藏族自治州',0,'632722','杂多县','632700','630000','青海省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3064,'632723','称多县','632700','玉树藏族自治州',0,'632723','称多县','632700','630000','青海省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3065,'632724','治多县','632700','玉树藏族自治州',0,'632724','治多县','632700','630000','青海省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3066,'632725','囊谦县','632700','玉树藏族自治州',0,'632725','囊谦县','632700','630000','青海省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3067,'632726','曲麻莱县','632700','玉树藏族自治州',2,'632726','曲麻莱县','632700','630000','青海省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3068,'632800','海西蒙古族藏族自治州','632800','海西蒙古族藏族自治州',49,'1','','630000','630000','青海省','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3069,'632801','格尔木市','632800','海西蒙古族藏族自治州',14,'632801','格尔木市','632800','630000','青海省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3070,'632802','德令哈市','632800','海西蒙古族藏族自治州',25,'632802','德令哈市','632800','630000','青海省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3071,'632803','茫崖市','632800','海西蒙古族藏族自治州',1,'632803','茫崖市','632800','630000','青海省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3072,'632821','乌兰县','632800','海西蒙古族藏族自治州',4,'632821','乌兰县','632800','630000','青海省','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3073,'632822','都兰县','632800','海西蒙古族藏族自治州',0,'632822','都兰县','632800','630000','青海省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3074,'632823','天峻县','632800','海西蒙古族藏族自治州',0,'632823','天峻县','632800','630000','青海省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3075,'632825','海西蒙古族藏族自治州直辖','632800','海西蒙古族藏族自治州',1,'632825','海西蒙古族藏族自治州直辖','632800','630000','青海省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3076,'640000','宁夏回族自治区','1','',2318,'','','100000','640000','宁夏回族自治区','正常',86);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3077,'640100','银川市','640100','银川市',777,'1','','640000','640000','宁夏回族自治区','正常',25);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3078,'640104','兴庆区','640100','银川市',217,'640104','兴庆区','640100','640000','宁夏回族自治区','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3079,'640105','西夏区','640100','银川市',105,'640105','西夏区','640100','640000','宁夏回族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3080,'640106','金凤区','640100','银川市',26,'640106','金凤区','640100','640000','宁夏回族自治区','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3081,'640121','永宁县','640100','银川市',68,'640121','永宁县','640100','640000','宁夏回族自治区','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3082,'640122','贺兰县','640100','银川市',104,'640122','贺兰县','640100','640000','宁夏回族自治区','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3083,'640181','灵武市','640100','银川市',101,'640181','灵武市','640100','640000','宁夏回族自治区','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3084,'640200','石嘴山市','640200','石嘴山市',306,'1','','640000','640000','宁夏回族自治区','正常',14);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3085,'640202','大武口区','640200','石嘴山市',46,'640202','大武口区','640200','640000','宁夏回族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3086,'640205','惠农区','640200','石嘴山市',106,'640205','惠农区','640200','640000','宁夏回族自治区','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3087,'640221','平罗县','640200','石嘴山市',125,'640221','平罗县','640200','640000','宁夏回族自治区','正常',8);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3088,'640300','吴忠市','640300','吴忠市',618,'1','','640000','640000','宁夏回族自治区','正常',21);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3089,'640302','利通区','640300','吴忠市',386,'640302','利通区','640300','640000','宁夏回族自治区','正常',15);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3090,'640303','红寺堡区','640300','吴忠市',5,'640303','红寺堡区','640300','640000','宁夏回族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3091,'640323','盐池县','640300','吴忠市',19,'640323','盐池县','640300','640000','宁夏回族自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3092,'640324','同心县','640300','吴忠市',29,'640324','同心县','640300','640000','宁夏回族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3093,'640381','青铜峡市','640300','吴忠市',120,'640381','青铜峡市','640300','640000','宁夏回族自治区','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3094,'640400','固原市','640400','固原市',282,'1','','640000','640000','宁夏回族自治区','正常',13);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3095,'640402','原州区','640400','固原市',75,'640402','原州区','640400','640000','宁夏回族自治区','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3096,'640422','西吉县','640400','固原市',140,'640422','西吉县','640400','640000','宁夏回族自治区','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3097,'640423','隆德县','640400','固原市',3,'640423','隆德县','640400','640000','宁夏回族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3098,'640424','泾源县','640400','固原市',1,'640424','泾源县','640400','640000','宁夏回族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3099,'640425','彭阳县','640400','固原市',13,'640425','彭阳县','640400','640000','宁夏回族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3100,'640500','中卫市','640500','中卫市',126,'1','','640000','640000','宁夏回族自治区','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3101,'640502','沙坡头区','640500','中卫市',54,'640502','沙坡头区','640500','640000','宁夏回族自治区','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3102,'640521','中宁县','640500','中卫市',41,'640521','中宁县','640500','640000','宁夏回族自治区','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3103,'640522','海原县','640500','中卫市',19,'640522','海原县','640500','640000','宁夏回族自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3104,'650000','新疆维吾尔自治区','1','',2371,'','','100000','650000','新疆维吾尔自治区','正常',139);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3105,'650100','乌鲁木齐市','650100','乌鲁木齐市',870,'1','','650000','650000','新疆维吾尔自治区','正常',58);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3106,'650102','天山区','650100','乌鲁木齐市',67,'650102','天山区','650100','650000','新疆维吾尔自治区','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3107,'650103','沙依巴克区','650100','乌鲁木齐市',161,'650103','沙依巴克区','650100','650000','新疆维吾尔自治区','正常',26);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3108,'650104','新市区','650100','乌鲁木齐市',202,'650104','新市区','650100','650000','新疆维吾尔自治区','正常',17);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3109,'650105','水磨沟区','650100','乌鲁木齐市',72,'650105','水磨沟区','650100','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3110,'650106','头屯河区','650100','乌鲁木齐市',28,'650106','头屯河区','650100','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3111,'650107','达坂城区','650100','乌鲁木齐市',0,'650107','达坂城区','650100','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3112,'650109','米东区','650100','乌鲁木齐市',106,'650109','米东区','650100','650000','新疆维吾尔自治区','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3113,'650121','乌鲁木齐县','650100','乌鲁木齐市',0,'650121','乌鲁木齐县','650100','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3114,'650200','克拉玛依市','650200','克拉玛依市',10,'1','','650000','650000','新疆维吾尔自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3115,'650202','独山子区','650200','克拉玛依市',0,'650202','独山子区','650200','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3116,'650203','克拉玛依区','650200','克拉玛依市',9,'650203','克拉玛依区','650200','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3117,'650204','白碱滩区','650200','克拉玛依市',0,'650204','白碱滩区','650200','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3118,'650205','乌尔禾区','650200','克拉玛依市',0,'650205','乌尔禾区','650200','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3119,'650400','吐鲁番市','650400','吐鲁番市',9,'1','','650000','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3120,'650402','高昌区','650400','吐鲁番市',9,'650402','高昌区','650400','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3121,'650421','鄯善县','650400','吐鲁番市',0,'650421','鄯善县','650400','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3122,'650422','托克逊县','650400','吐鲁番市',0,'650422','托克逊县','650400','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3123,'650500','哈密市','650500','哈密市',14,'1','','650000','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3124,'650502','伊州区','650500','哈密市',4,'650502','伊州区','650500','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3125,'650521','巴里坤哈萨克自治县','650500','哈密市',0,'650521','巴里坤哈萨克自治县','650500','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3126,'650522','伊吾县','650500','哈密市',0,'650522','伊吾县','650500','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3127,'652300','昌吉回族自治州','652300','昌吉回族自治州',102,'1','','650000','650000','新疆维吾尔自治区','正常',6);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3128,'652301','昌吉市','652300','昌吉回族自治州',18,'652301','昌吉市','652300','650000','新疆维吾尔自治区','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3129,'652302','阜康市','652300','昌吉回族自治州',23,'652302','阜康市','652300','650000','新疆维吾尔自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3130,'652323','呼图壁县','652300','昌吉回族自治州',20,'652323','呼图壁县','652300','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3131,'652324','玛纳斯县','652300','昌吉回族自治州',17,'652324','玛纳斯县','652300','650000','新疆维吾尔自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3132,'652325','奇台县','652300','昌吉回族自治州',18,'652325','奇台县','652300','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3133,'652327','吉木萨尔县','652300','昌吉回族自治州',0,'652327','吉木萨尔县','652300','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3134,'652328','木垒哈萨克自治县','652300','昌吉回族自治州',0,'652328','木垒哈萨克自治县','652300','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3135,'652700','博尔塔拉蒙古自治州','652700','博尔塔拉蒙古自治州',44,'1','','650000','650000','新疆维吾尔自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3136,'652701','博乐市','652700','博尔塔拉蒙古自治州',17,'652701','博乐市','652700','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3137,'652702','阿拉山口市','652700','博尔塔拉蒙古自治州',5,'652702','阿拉山口市','652700','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3138,'652722','精河县','652700','博尔塔拉蒙古自治州',16,'652722','精河县','652700','650000','新疆维吾尔自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3139,'652723','温泉县','652700','博尔塔拉蒙古自治州',1,'652723','温泉县','652700','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3140,'652800','巴音郭楞蒙古自治州','652800','巴音郭楞蒙古自治州',186,'1','','650000','650000','新疆维吾尔自治区','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3141,'652801','库尔勒市','652800','巴音郭楞蒙古自治州',159,'652801','库尔勒市','652800','650000','新疆维吾尔自治区','正常',9);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3142,'652822','轮台县','652800','巴音郭楞蒙古自治州',3,'652822','轮台县','652800','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3143,'652823','尉犁县','652800','巴音郭楞蒙古自治州',0,'652823','尉犁县','652800','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3144,'652824','若羌县','652800','巴音郭楞蒙古自治州',0,'652824','若羌县','652800','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3145,'652825','且末县','652800','巴音郭楞蒙古自治州',0,'652825','且末县','652800','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3146,'652826','焉耆回族自治县','652800','巴音郭楞蒙古自治州',0,'652826','焉耆回族自治县','652800','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3147,'652827','和静县','652800','巴音郭楞蒙古自治州',13,'652827','和静县','652800','650000','新疆维吾尔自治区','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3148,'652828','和硕县','652800','巴音郭楞蒙古自治州',0,'652828','和硕县','652800','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3149,'652829','博湖县','652800','巴音郭楞蒙古自治州',0,'652829','博湖县','652800','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3150,'652900','阿克苏地区','652900','阿克苏地区',156,'1','','650000','650000','新疆维吾尔自治区','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3151,'652901','阿克苏市','652900','阿克苏地区',73,'652901','阿克苏市','652900','650000','新疆维吾尔自治区','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3152,'652922','温宿县','652900','阿克苏地区',5,'652922','温宿县','652900','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3153,'652923','库车县','652900','阿克苏地区',3,'652923','库车县','652900','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3154,'652924','沙雅县','652900','阿克苏地区',5,'652924','沙雅县','652900','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3155,'652925','新和县','652900','阿克苏地区',1,'652925','新和县','652900','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3156,'652926','拜城县','652900','阿克苏地区',15,'652926','拜城县','652900','650000','新疆维吾尔自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3157,'652927','乌什县','652900','阿克苏地区',0,'652927','乌什县','652900','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3158,'652928','阿瓦提县','652900','阿克苏地区',2,'652928','阿瓦提县','652900','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3159,'652929','柯坪县','652900','阿克苏地区',20,'652929','柯坪县','652900','650000','新疆维吾尔自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3160,'653000','克孜勒苏柯尔克孜自治州','653000','克孜勒苏柯尔克孜自治州',20,'1','','650000','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3161,'653001','阿图什市','653000','克孜勒苏柯尔克孜自治州',5,'653001','阿图什市','653000','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3162,'653022','阿克陶县','653000','克孜勒苏柯尔克孜自治州',14,'653022','阿克陶县','653000','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3163,'653023','阿合奇县','653000','克孜勒苏柯尔克孜自治州',0,'653023','阿合奇县','653000','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3164,'653024','乌恰县','653000','克孜勒苏柯尔克孜自治州',2,'653024','乌恰县','653000','650000','新疆维吾尔自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3165,'653100','喀什地区','653100','喀什地区',173,'1','','650000','650000','新疆维吾尔自治区','正常',20);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3166,'653101','喀什市','653100','喀什地区',60,'653101','喀什市','653100','650000','新疆维吾尔自治区','正常',16);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3167,'653121','疏附县','653100','喀什地区',3,'653121','疏附县','653100','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3168,'653122','疏勒县','653100','喀什地区',33,'653122','疏勒县','653100','650000','新疆维吾尔自治区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3169,'653123','英吉沙县','653100','喀什地区',14,'653123','英吉沙县','653100','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3170,'653124','泽普县','653100','喀什地区',1,'653124','泽普县','653100','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3171,'653125','莎车县','653100','喀什地区',14,'653125','莎车县','653100','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3172,'653126','叶城县','653100','喀什地区',9,'653126','叶城县','653100','650000','新疆维吾尔自治区','正常',3);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3173,'653127','麦盖提县','653100','喀什地区',2,'653127','麦盖提县','653100','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3174,'653128','岳普湖县','653100','喀什地区',2,'653128','岳普湖县','653100','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3175,'653129','伽师县','653100','喀什地区',3,'653129','伽师县','653100','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3176,'653130','巴楚县','653100','喀什地区',1,'653130','巴楚县','653100','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3177,'653131','塔什库尔干塔吉克自治县','653100','喀什地区',0,'653131','塔什库尔干塔吉克自治县','653100','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3178,'653200','和田地区','653200','和田地区',46,'1','','650000','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3179,'653201','和田市','653200','和田地区',4,'653201','和田市','653200','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3180,'653221','和田县','653200','和田地区',0,'653221','和田县','653200','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3181,'653222','墨玉县','653200','和田地区',13,'653222','墨玉县','653200','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3182,'653223','皮山县','653200','和田地区',0,'653223','皮山县','653200','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3183,'653224','洛浦县','653200','和田地区',4,'653224','洛浦县','653200','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3184,'653225','策勒县','653200','和田地区',0,'653225','策勒县','653200','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3185,'653226','于田县','653200','和田地区',6,'653226','于田县','653200','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3186,'653227','民丰县','653200','和田地区',0,'653227','民丰县','653200','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3187,'654000','伊犁哈萨克自治州','654000','伊犁哈萨克自治州',229,'1','','650000','650000','新疆维吾尔自治区','正常',12);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3188,'654002','伊宁市','654000','伊犁哈萨克自治州',121,'654002','伊宁市','654000','650000','新疆维吾尔自治区','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3189,'654003','奎屯市','654000','伊犁哈萨克自治州',43,'654003','奎屯市','654000','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3190,'654004','霍尔果斯市','654000','伊犁哈萨克自治州',7,'654004','霍尔果斯市','654000','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3191,'654021','伊宁县','654000','伊犁哈萨克自治州',9,'654021','伊宁县','654000','650000','新疆维吾尔自治区','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3192,'654022','察布查尔锡伯自治县','654000','伊犁哈萨克自治州',13,'654022','察布查尔锡伯自治县','654000','650000','新疆维吾尔自治区','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3193,'654023','霍城县','654000','伊犁哈萨克自治州',5,'654023','霍城县','654000','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3194,'654024','巩留县','654000','伊犁哈萨克自治州',0,'654024','巩留县','654000','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3195,'654025','新源县','654000','伊犁哈萨克自治州',0,'654025','新源县','654000','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3196,'654026','昭苏县','654000','伊犁哈萨克自治州',1,'654026','昭苏县','654000','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3197,'654027','特克斯县','654000','伊犁哈萨克自治州',0,'654027','特克斯县','654000','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3198,'654028','尼勒克县','654000','伊犁哈萨克自治州',9,'654028','尼勒克县','654000','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3199,'654200','塔城地区','654200','塔城地区',57,'1','','650000','650000','新疆维吾尔自治区','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3200,'654201','塔城市','654200','塔城地区',0,'654201','塔城市','654200','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3201,'654202','乌苏市','654200','塔城地区',11,'654202','乌苏市','654200','650000','新疆维吾尔自治区','正常',4);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3202,'654221','额敏县','654200','塔城地区',10,'654221','额敏县','654200','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3203,'654223','沙湾县','654200','塔城地区',27,'654223','沙湾县','654200','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3204,'654224','托里县','654200','塔城地区',0,'654224','托里县','654200','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3205,'654225','裕民县','654200','塔城地区',0,'654225','裕民县','654200','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3206,'654226','和布克赛尔蒙古自治县','654200','塔城地区',5,'654226','和布克赛尔蒙古自治县','654200','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3207,'654300','阿勒泰地区','654300','阿勒泰地区',20,'1','','650000','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3208,'654301','阿勒泰市','654300','阿勒泰地区',0,'654301','阿勒泰市','654300','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3209,'654321','布尔津县','654300','阿勒泰地区',0,'654321','布尔津县','654300','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3210,'654322','富蕴县','654300','阿勒泰地区',14,'654322','富蕴县','654300','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3211,'654323','福海县','654300','阿勒泰地区',0,'654323','福海县','654300','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3212,'654324','哈巴河县','654300','阿勒泰地区',1,'654324','哈巴河县','654300','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3213,'654325','青河县','654300','阿勒泰地区',2,'654325','青河县','654300','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3214,'654326','吉木乃县','654300','阿勒泰地区',0,'654326','吉木乃县','654300','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3215,'659001','石河子市','659000','直辖市县区',94,'659001','石河子市','659000','650000','新疆维吾尔自治区','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3216,'659002','阿拉尔市','659000','直辖市县区',29,'659002','阿拉尔市','659000','650000','新疆维吾尔自治区','正常',7);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3217,'659003','图木舒克市','659000','直辖市县区',0,'659003','图木舒克市','659000','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3218,'659004','五家渠市','659000','直辖市县区',4,'659004','五家渠市','659000','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3219,'659005','北屯市','659000','直辖市县区',3,'659005','北屯市','659000','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3220,'659006','铁门关市','659000','直辖市县区',0,'659006','铁门关市','659000','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3221,'659007','双河市','659000','直辖市县区',7,'659007','双河市','659000','650000','新疆维吾尔自治区','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3222,'659008','可克达拉市','659000','直辖市县区',6,'659008','可克达拉市','659000','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3223,'659009','昆玉市','659000','直辖市县区',3,'659009','昆玉市','659000','650000','新疆维吾尔自治区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3224,'710000','台湾省','1','',42,'','','100000','710000','台湾省','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3225,'810000','香港特别行政区','1','',617,'','','100000','810000','香港特别行政区','正常',11);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3226,'810001','中西区','1','',0,'810001','中西区','810000','810000','香港特别行政区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3227,'810002','湾仔区','1','',0,'810002','湾仔区','810000','810000','香港特别行政区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3228,'810003','东区','1','',9,'810003','东区','810000','810000','香港特别行政区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3229,'810004','南区','1','',117,'810004','南区','810000','810000','香港特别行政区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3230,'810005','油尖旺区','1','',10,'810005','油尖旺区','810000','810000','香港特别行政区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3231,'810006','深水埗区','1','',0,'810006','深水埗区','810000','810000','香港特别行政区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3232,'810007','九龙城区','1','',4,'810007','九龙城区','810000','810000','香港特别行政区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3233,'810008','黄大仙区','1','',10,'810008','黄大仙区','810000','810000','香港特别行政区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3234,'810009','观塘区','1','',2,'810009','观塘区','810000','810000','香港特别行政区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3235,'810010','荃湾区','1','',0,'810010','荃湾区','810000','810000','香港特别行政区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3236,'810011','屯门区','1','',25,'810011','屯门区','810000','810000','香港特别行政区','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3237,'810012','元朗区','1','',15,'810012','元朗区','810000','810000','香港特别行政区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3238,'810013','北区','1','',8,'810013','北区','810000','810000','香港特别行政区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3239,'810014','大埔区','1','',63,'810014','大埔区','810000','810000','香港特别行政区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3240,'810015','西贡区','1','',0,'810015','西贡区','810000','810000','香港特别行政区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3241,'810016','沙田区','1','',1,'810016','沙田区','810000','810000','香港特别行政区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3242,'810017','葵青区','1','',21,'810017','葵青区','810000','810000','香港特别行政区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3243,'810018','离岛区','1','',3,'810018','离岛区','810000','810000','香港特别行政区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3244,'820000','澳门特别行政区','1','',139,'','','100000','820000','澳门特别行政区','正常',12);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3245,'820001','花地玛堂区','1','',14,'820001','花地玛堂区','820000','820000','澳门特别行政区','正常',5);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3246,'820002','花王堂区','1','',0,'820002','花王堂区','820000','820000','澳门特别行政区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3247,'820003','望德堂区','1','',0,'820003','望德堂区','820000','820000','澳门特别行政区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3248,'820004','大堂区','1','',1,'820004','大堂区','820000','820000','澳门特别行政区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3249,'820005','风顺堂区','1','',1,'820005','风顺堂区','820000','820000','澳门特别行政区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3250,'820006','嘉模堂区','1','',50,'820006','嘉模堂区','820000','820000','澳门特别行政区','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3251,'820007','路凼填海区','1','',0,'820007','路凼填海区','820000','820000','澳门特别行政区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3252,'820008','圣方济各堂区','1','',0,'820008','圣方济各堂区','820000','820000','澳门特别行政区','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3253,'900000','国外','1','',4,'','','100000','900000','外国','正常',0);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3254,'419000','直辖市县区','419000','直辖市县区',1,'1','','410000','410000','河南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3259,'429000','直辖市县区','429000','直辖市县区',1,'1','','420000','420000','湖北省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3260,'442100','直辖市县区','442100','直辖市县区',1,'1','','440000','440000','广东省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3261,'469000','直辖市县区','469000','直辖市县区',1,'1','','460000','460000','海南省','正常',1);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3262,'500200','重庆市郊县','1','重庆市郊县',2,'1','','500000','500000','重庆市','正常',2);

insert  into `s_community_district`(`id`,`ad_code`,`ad_name`,`city_code`,`city_name`,`count`,`district_code`,`district_name`,`parent_ad_code`,`province_code`,`province_name`,`status`,`talk_count`) values (3263,'659000','直辖市县区','659000','直辖市县区',1,'1','','650000','650000','新疆维吾尔自治区','正常',1);