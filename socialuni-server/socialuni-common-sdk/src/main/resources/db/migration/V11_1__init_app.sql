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

-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'appGender', 'all', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'authCodeCount', '30', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'authCodeInterval', '120', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'authCodeIpCount', '200', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'authCodePhoneCount', '30', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'authCodeValidMinute', '30', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'childProtectUrl', 'https://www.socialuni.cn/qingchi/childProtect', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'cityTabName', '同城', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'contactExpenseShell', '100', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'contactUserReceiveShell', '50', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'contactUsUrl', 'https://www.socialuni.cn/qingchi/contact', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'defaultChatGroups', '交友', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'devPublishDataApiUrl', '', '开发者服务器url', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'disableContentHasContactInfo', 'false', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'disableContentHasQrCode', 'false', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'disableUnderageContent', 'false', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'errorMsg601UnLogin', '未登录，请进行登录，是否前往登录', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'errorMsg604SystemError', '系统异常，有任何疑问请联系客服', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'errorMsgContactService', '有任何疑问请联系客服', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'followTabName', '关注', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'highLimitReportCount', '20', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'homeTabName', '首页', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'homeUrl', 'https://www.socialuni.cn/', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'mp_qq_auditing', 'false', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'mp_wx_auditing', 'false', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'mustSetSchoolCanPost', 'false', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'qq_account', '491369310', '客服qq/微信', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'reportCountHide', '1', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'rewardedAdLimit', '3', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'serviceWeChat', '', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'showSwipers', 'true', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'suggestUrl', 'https://www.socialuni.cn/qingchi/suggest', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'swiperHeight', '130', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'sysServiceReceiveRatio', '', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'systemUserPhoneNum', '11111111111', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'tabNames', '关注,首页,同城', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'talkShowAdCount', '10', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'talkShowAdIndexList', '7,15,27,43,63,87,111,135,159,183', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'talkShowAdInterval', '8', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'userAgreementUrl', 'https://www.socialuni.cn/qingchi/agreement', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'userPrivacyUrl', 'https://www.socialuni.cn/qingchi/privacy', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'vipPrice', '1000', '', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'wx_app_id', '', '微信app-id', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'wx_merchant_id', '', '微信商户id', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'wx_merchant_key', '', '微信商户秘钥', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'wx_mp_id', '', '微信小程序id', 1);
-- INSERT INTO s_app_config (dev_id, config_key, value, label, status) VALUES (0, 'wx_mp_secret', '', '微信小程序秘钥', 1);
