/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50163
 Source Host           : localhost
 Source Database       : eroad

 Target Server Type    : MySQL
 Target Server Version : 50163
 File Encoding         : utf-8

 Date: 03/03/2016 16:18:26 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `car_account`
-- ----------------------------
DROP TABLE IF EXISTS `car_account`;
CREATE TABLE `car_account` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `passwd` varchar(50) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `car_detail`
-- ----------------------------
DROP TABLE IF EXISTS `car_detail`;
CREATE TABLE `car_detail` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `driver_licence_path` varchar(100) NOT NULL COMMENT '驾驶员驾照',
  `driver_licence_number` varchar(50) NOT NULL COMMENT '驾照号',
  `car_number` varchar(20) NOT NULL COMMENT '车牌号',
  `credit` set('0','1','2','3','4','5') NOT NULL DEFAULT '0' COMMENT '星级',
  `onlineflag` set('在线','离线') NOT NULL DEFAULT '在线' COMMENT '是否在线',
  `state` set('运输中','空闲') NOT NULL DEFAULT '空闲' COMMENT '车辆状态',
  `car_id` int(10) unsigned NOT NULL COMMENT '车id,外键',
  PRIMARY KEY (`id`),
  UNIQUE KEY `driver_licence_number` (`driver_licence_number`),
  UNIQUE KEY `car_number` (`car_number`),
  KEY `car_id` (`car_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `company`
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `passwd` varchar(50) NOT NULL COMMENT '密码',
  `state` set('审核','正常','无效') NOT NULL DEFAULT '审核' COMMENT '用户状态',
  `company_name` varchar(50) NOT NULL COMMENT '公司名称',
  `company_license` varchar(50) NOT NULL COMMENT '营业执照号码',
  `phone_number` varchar(11) NOT NULL COMMENT '公司电话',
  `companylicence_pic_path` varchar(100) DEFAULT NULL COMMENT '公司营业执照照片',
  `company_represent` varchar(20) NOT NULL COMMENT '法人代表名字',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `company_name` (`company_name`),
  UNIQUE KEY `company_license` (`company_license`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `order_relation_detail`
-- ----------------------------
DROP TABLE IF EXISTS `order_relation_detail`;
CREATE TABLE `order_relation_detail` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `car_id` int(10) unsigned NOT NULL COMMENT '车的id，外键',
  `orders_id` int(10) unsigned NOT NULL COMMENT '订单编号，外键',
  `company_id` int(10) unsigned NOT NULL COMMENT '公司id，外键',
  PRIMARY KEY (`id`),
  KEY `car_id` (`car_id`),
  KEY `orders_id` (`orders_id`),
  KEY `company_id` (`company_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `order_number` varchar(50) NOT NULL DEFAULT '' COMMENT '订单号',
  `start_time` date NOT NULL COMMENT '开始时间',
  `exact_end_time` date NOT NULL COMMENT '实际结束时间',
  `expect_end_time` date NOT NULL COMMENT '预期结束时间',
  `expect_fare` decimal(8,2) NOT NULL COMMENT '货主理想费用',
  `exact_fare` decimal(8,2) NOT NULL COMMENT '实际费用',
  `start_address` varchar(200) NOT NULL COMMENT '出发地址',
  `destination` varchar(200) NOT NULL COMMENT '收货地址',
  `description` varchar(255) NOT NULL COMMENT '简述',
  `state` set('发布中','运行中') NOT NULL DEFAULT '发布中' COMMENT '订单状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_number` (`order_number`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `platform`
-- ----------------------------
DROP TABLE IF EXISTS `platform`;
CREATE TABLE `platform` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `passwd` varchar(50) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `track`
-- ----------------------------
DROP TABLE IF EXISTS `track`;
CREATE TABLE `track` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '时间点',
  `longitude` varchar(50) NOT NULL COMMENT '经度',
  `latitude` varchar(50) NOT NULL COMMENT '纬度',
  `orders_id` int(10) unsigned NOT NULL COMMENT '订单编号，外键',
  `car_id` int(10) unsigned NOT NULL COMMENT '车的id，外键',
  PRIMARY KEY (`id`),
  KEY `car_id` (`car_id`),
  KEY `orders_id` (`orders_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `verify`
-- ----------------------------
DROP TABLE IF EXISTS `verify`;
CREATE TABLE `verify` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `vtime` date NOT NULL COMMENT '审核时间',
  `type` set('company','car') NOT NULL COMMENT '车还是公司',
  `car_id` int(10) unsigned NOT NULL COMMENT '车id，外键',
  `company_id` int(10) unsigned NOT NULL COMMENT '公司id，外键',
  `state` set('pass','rejected') NOT NULL DEFAULT 'rejected' COMMENT '最后结果',
  PRIMARY KEY (`id`),
  KEY `car_id` (`car_id`),
  KEY `company_id` (`company_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
