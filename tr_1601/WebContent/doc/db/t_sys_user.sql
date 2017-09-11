/*
Navicat MySQL Data Transfer

Source Server         : myserver
Source Server Version : 50715
Source Host           : localhost:3306
Source Database       : tr_1601db

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2017-05-22 09:33:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `t_sysUser_id` varchar(50) NOT NULL,
  `t_sysUser_name` varchar(50) DEFAULT NULL,
  `t_sysUser_pwd` varchar(50) DEFAULT NULL,
  `t_sysUser_role` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`t_sysUser_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES ('2', '汤姆', '123', '1');
INSERT INTO `t_sys_user` VALUES ('3e967de5-2dcc-4aa5-aafd-377a718f3cea', 'waha', '123', null);
INSERT INTO `t_sys_user` VALUES ('57c762b7-5727-4e19-8508-132833f5dde6', '测试1', '1234', null);
INSERT INTO `t_sys_user` VALUES ('685ee0c2-8462-4164-9ea3-95962f88a231', '测试1', '1234', null);
INSERT INTO `t_sys_user` VALUES ('774e1de3-d704-4be4-adb4-86df9a928f5c', '测试1', '1234', null);
INSERT INTO `t_sys_user` VALUES ('78ecd62c-c298-4eda-85ff-c7ada52115e4', '测试1', '1234', null);
INSERT INTO `t_sys_user` VALUES ('7c41c44c-cf45-4f9b-8266-69e49adbc17b', '测试1', '1234', null);
INSERT INTO `t_sys_user` VALUES ('a6bad20d-4eb8-4022-9caa-b3491362f2ff', '测试1', '1234', null);
INSERT INTO `t_sys_user` VALUES ('b6d5cbc7-ccad-4602-978c-57227aa8d7fa', '普通用户', '123', null);
INSERT INTO `t_sys_user` VALUES ('d23213ed-932b-4b90-b757-9a0c68e57230', 'lisi', '123', null);
INSERT INTO `t_sys_user` VALUES ('d6655df0-1943-4344-bdc9-fdb1ce49d286', '测试1', '1234', null);
INSERT INTO `t_sys_user` VALUES ('ef4966d1-ed1b-4513-a086-a33b301e6fe7', 'michael', '123', null);
