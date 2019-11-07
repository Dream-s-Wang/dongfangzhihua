/*
 Navicat MySQL Data Transfer

 Source Server         : 阿里
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : 47.100.94.90:3306
 Source Schema         : hua

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 06/11/2019 20:49:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for data
-- ----------------------------
DROP TABLE IF EXISTS `data`;
CREATE TABLE `data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `province` varchar(20) DEFAULT NULL COMMENT '省',
  `city` varchar(20) DEFAULT NULL COMMENT '市',
  `area` varchar(20) DEFAULT NULL COMMENT '区',
  `name` varchar(20) DEFAULT NULL COMMENT '名',
  `type` varchar(20) DEFAULT NULL COMMENT '类型',
  `nationality` varchar(20) DEFAULT NULL COMMENT '民族',
  `introduction` varchar(2000) DEFAULT NULL COMMENT '简介',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `route` varchar(255) DEFAULT NULL COMMENT '路线',
  `time` varchar(255) DEFAULT NULL COMMENT '时间',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `ticket` varchar(255) DEFAULT NULL COMMENT '票务',
  `images` varchar(255) DEFAULT NULL COMMENT '图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自动编号',
  `filename` varchar(255) NOT NULL DEFAULT '' COMMENT '文件的命名',
  `url` varchar(255) NOT NULL DEFAULT '' COMMENT '文件url',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用来存储文件';

-- ----------------------------
-- Table structure for filerel
-- ----------------------------
DROP TABLE IF EXISTS `filerel`;
CREATE TABLE `filerel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自动编号',
  `fid` bigint(20) DEFAULT NULL COMMENT '与文件id对应',
  `item` varchar(255) DEFAULT NULL COMMENT '与文件对应的项目(关系)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='存储文件与项目（如页面）之间的关系';

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`series`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '昵称',
  `password` varchar(70) DEFAULT NULL,
  `role` varchar(50) DEFAULT '1' COMMENT '1:学生 2:教师',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='学生信息表';

SET FOREIGN_KEY_CHECKS = 1;
