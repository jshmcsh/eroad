/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50163
 Source Host           : localhost
 Source Database       : eroad_test

 Target Server Type    : MySQL
 Target Server Version : 50163
 File Encoding         : utf-8

 Date: 03/15/2016 15:28:05 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `bidding`
-- ----------------------------
DROP TABLE IF EXISTS `bidding`;
CREATE TABLE `bidding` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `price` decimal(10,2) NOT NULL COMMENT '报价',
  `car_id` int(10) unsigned NOT NULL COMMENT '车id',
  `order_id` int(10) unsigned NOT NULL COMMENT '订单id',
  PRIMARY KEY (`id`),
  KEY `car_id` (`car_id`),
  KEY `order_id` (`order_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `car_account`
-- ----------------------------
DROP TABLE IF EXISTS `car_account`;
CREATE TABLE `car_account` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `passwd` varchar(50) NOT NULL COMMENT '密码',
  `state` set('pass','rejected','check') NOT NULL DEFAULT 'check' COMMENT '审核',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `car_detail`
-- ----------------------------
DROP TABLE IF EXISTS `car_detail`;
CREATE TABLE `car_detail` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `driver_licence_path` varchar(100) NOT NULL COMMENT '驾驶员驾照',
  `driver_licence_number` varchar(50) NOT NULL COMMENT '驾照号',
  `car_number` varchar(20) NOT NULL COMMENT '车牌号',
  `onlineflag` set('在线','离线') NOT NULL DEFAULT '在线' COMMENT '是否在线',
  `state` set('运输中','空闲') NOT NULL DEFAULT '空闲' COMMENT '车辆状态',
  `car_id` int(10) unsigned NOT NULL COMMENT '车id,外键',
  `phone_number` varchar(11) NOT NULL COMMENT '司机手机号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `driver_licence_number` (`driver_licence_number`),
  UNIQUE KEY `car_number` (`car_number`),
  KEY `car_id` (`car_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

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
  `company_license` varchar(50) DEFAULT NULL COMMENT '营业执照号码',
  `phone_number` varchar(11) NOT NULL COMMENT '公司电话',
  `companylicence_pic_path` varchar(100) DEFAULT NULL COMMENT '公司营业执照照片',
  `company_represent` varchar(20) NOT NULL COMMENT '法人代表名字',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `company_name` (`company_name`),
  UNIQUE KEY `company_license` (`company_license`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `launching`
-- ----------------------------
DROP TABLE IF EXISTS `launching`;
CREATE TABLE `launching` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `launching_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布订单时间',
  `flag` set('valid','invalid') NOT NULL DEFAULT 'invalid' COMMENT '标志位，一旦达成交易要转为invalid',
  `company_id` int(10) unsigned NOT NULL COMMENT '发布不公司的id，外键',
  `order_id` int(10) unsigned NOT NULL COMMENT '订单id，外键',
  PRIMARY KEY (`id`),
  KEY `company_id` (`company_id`),
  KEY `order_id` (`order_id`)
) ENGINE=MyISAM AUTO_INCREMENT=141 DEFAULT CHARSET=utf8;

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
) ENGINE=MyISAM AUTO_INCREMENT=108 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `order_number` varchar(50) NOT NULL DEFAULT '' COMMENT '订单号',
  `start_time` date NOT NULL COMMENT '开始时间',
  `exact_end_time` date DEFAULT NULL COMMENT '实际结束时间',
  `expect_end_time` date NOT NULL COMMENT '预期结束时间',
  `expect_fare` decimal(8,2) NOT NULL COMMENT '货主理想费用',
  `exact_fare` decimal(8,2) DEFAULT NULL COMMENT '实际费用',
  `start_address` varchar(200) NOT NULL COMMENT '出发地址',
  `destination` varchar(200) NOT NULL COMMENT '收货地址',
  `description` varchar(255) NOT NULL COMMENT '描述',
  `state` set('displaying','executing','invalid','completed') NOT NULL DEFAULT 'invalid' COMMENT '订单状态',
  `sketch` varchar(100) DEFAULT NULL COMMENT '概述',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单生成时间',
  `exact_start_time` date DEFAULT NULL COMMENT '期望开始时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_number` (`order_number`)
) ENGINE=MyISAM AUTO_INCREMENT=145 DEFAULT CHARSET=utf8;

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
--  Table structure for `remark`
-- ----------------------------
DROP TABLE IF EXISTS `remark`;
CREATE TABLE `remark` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `evaluate` set('1','2','3','4','5') NOT NULL DEFAULT '1' COMMENT '评价（数字，用于画星星）',
  `remark` varchar(255) DEFAULT NULL COMMENT '评价（文字描述）',
  `car_id` int(10) unsigned NOT NULL COMMENT '车id，外键',
  `order_id` int(10) unsigned NOT NULL COMMENT '订单id，外键',
  `company_id` int(10) unsigned NOT NULL COMMENT '公司id，外键',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生成时间',
  PRIMARY KEY (`id`),
  KEY `car_id` (`car_id`),
  KEY `order_id` (`order_id`)
) ENGINE=MyISAM AUTO_INCREMENT=153 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `track`
-- ----------------------------
DROP TABLE IF EXISTS `track`;
CREATE TABLE `track` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '时间点',
  `longtitude` varchar(50) NOT NULL COMMENT '经度',
  `latitude` varchar(50) NOT NULL COMMENT '纬度',
  `orders_id` int(10) unsigned DEFAULT NULL COMMENT '订单编号，外键',
  `car_id` int(10) unsigned NOT NULL COMMENT '车的id，外键',
  `province` varchar(20) DEFAULT NULL COMMENT '省',
  `city` varchar(20) DEFAULT NULL,
  `district` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `car_id` (`car_id`),
  KEY `orders_id` (`orders_id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

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

-- ----------------------------
--  View structure for `bidding_order_list`
-- ----------------------------
DROP VIEW IF EXISTS `bidding_order_list`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`127.0.0.1` SQL SECURITY DEFINER VIEW `bidding_order_list` AS select `launching`.`company_id` AS `id`,`orders`.`create_time` AS `create_time`,`orders`.`destination` AS `destination`,`orders`.`start_address` AS `start_address`,`orders`.`expect_fare` AS `expect_fare`,`orders`.`start_time` AS `start_time`,`orders`.`expect_end_time` AS `expect_end_time`,`orders`.`sketch` AS `sketch`,`bidding`.`price` AS `price`,`car_account`.`username` AS `username`,`car_detail`.`phone_number` AS `phone_number`,`car_detail`.`car_number` AS `car_number` from ((((`bidding` join `orders` on((`bidding`.`order_id` = `orders`.`id`))) join `car_account` on((`bidding`.`car_id` = `car_account`.`id`))) join `car_detail` on((`bidding`.`car_id` = `car_detail`.`car_id`))) join `launching` on((`bidding`.`order_id` = `launching`.`order_id`))) where (`orders`.`state` = 'displaying');

-- ----------------------------
--  View structure for `executing_order_detail`
-- ----------------------------
DROP VIEW IF EXISTS `executing_order_detail`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`127.0.0.1` SQL SECURITY DEFINER VIEW `executing_order_detail` AS select `orders`.`id` AS `order_id`,`orders`.`order_number` AS `order_number`,`orders`.`start_time` AS `start_time`,`orders`.`exact_start_time` AS `exact_start_time`,`orders`.`expect_end_time` AS `expect_end_time`,`orders`.`description` AS `description`,`orders`.`create_time` AS `create_time`,`orders`.`sketch` AS `sketch`,`orders`.`start_address` AS `start_address`,`orders`.`destination` AS `destination`,`car_account`.`username` AS `username`,`car_detail`.`car_number` AS `car_number`,`car_detail`.`phone_number` AS `phone_number`,`track`.`latitude` AS `latitude`,`track`.`longtitude` AS `longtitude` from (((((`order_relation_detail` `o` join `company` on((`o`.`company_id` = `company`.`id`))) join `car_detail` on((`o`.`car_id` = `car_detail`.`car_id`))) join `car_account` on((`o`.`car_id` = `car_account`.`id`))) join `orders` on((`o`.`orders_id` = `orders`.`id`))) join `track` on((`o`.`orders_id` = `track`.`orders_id`))) where (`orders`.`state` = 'executing') order by `track`.`time` desc;

-- ----------------------------
--  View structure for `executing_order_list`
-- ----------------------------
DROP VIEW IF EXISTS `executing_order_list`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`127.0.0.1` SQL SECURITY DEFINER VIEW `executing_order_list` AS select `company`.`id` AS `id`,`orders`.`id` AS `order_id`,`car_account`.`username` AS `username`,`car_detail`.`car_number` AS `car_number`,`car_detail`.`phone_number` AS `phone_number`,`orders`.`sketch` AS `sketch`,`orders`.`start_address` AS `start_address`,`orders`.`destination` AS `destination`,`track`.`latitude` AS `latitude`,`track`.`longtitude` AS `longtitude`,`track`.`time` AS `track_time` from (((((`order_relation_detail` `o` join `company` on((`o`.`company_id` = `company`.`id`))) join `car_detail` on((`o`.`car_id` = `car_detail`.`car_id`))) join `car_account` on((`o`.`car_id` = `car_account`.`id`))) join `orders` on((`o`.`orders_id` = `orders`.`id`))) join `track` on((`o`.`orders_id` = `track`.`orders_id`))) where (`orders`.`state` = 'executing') group by `orders`.`id`,`track`.`time` order by `track`.`orders_id`,`track`.`time` desc;

-- ----------------------------
--  View structure for `history_order`
-- ----------------------------
DROP VIEW IF EXISTS `history_order`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`127.0.0.1` SQL SECURITY DEFINER VIEW `history_order` AS select `order_relation_detail`.`company_id` AS `company_id`,`orders`.`order_number` AS `order_number`,timestampdiff(DAY,`orders`.`start_time`,`orders`.`exact_end_time`) AS `last_time`,`orders`.`exact_fare` AS `exact_fare`,`orders`.`expect_fare` AS `expect_fare`,`orders`.`expect_end_time` AS `expect_end_time`,`orders`.`exact_end_time` AS `exact_end_time`,`orders`.`start_time` AS `start_time`,`orders`.`start_address` AS `start_address`,`orders`.`destination` AS `destination`,`orders`.`sketch` AS `sketch`,`orders`.`description` AS `description`,`orders`.`create_time` AS `create_time`,`car_account`.`username` AS `username` from ((`order_relation_detail` join `orders` on((`orders`.`id` = `order_relation_detail`.`orders_id`))) join `car_account` on((`order_relation_detail`.`car_id` = `car_account`.`id`))) where (`orders`.`state` = 'completed');

-- ----------------------------
--  View structure for `v_show_car_around`
-- ----------------------------
DROP VIEW IF EXISTS `v_show_car_around`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`127.0.0.1` SQL SECURITY DEFINER VIEW `v_show_car_around` AS select `car_account`.`id` AS `id`,`car_account`.`username` AS `username`,`car_detail`.`car_number` AS `car_number`,`car_detail`.`phone_number` AS `phone_number`,`track`.`latitude` AS `latitude`,`track`.`longtitude` AS `longtitude`,`track`.`time` AS `time`,`track`.`province` AS `province`,`track`.`city` AS `city` from ((`track` join `car_account` on((`car_account`.`id` = `track`.`car_id`))) join `car_detail` on((`car_detail`.`car_id` = `track`.`car_id`))) where ((`car_account`.`state` = 'pass') and (`car_detail`.`onlineflag` = '在线') and (`car_detail`.`state` = '空闲'));

-- ----------------------------
--  View structure for `xcar_remark`
-- ----------------------------
DROP VIEW IF EXISTS `xcar_remark`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`127.0.0.1` SQL SECURITY DEFINER VIEW `xcar_remark` AS select `car_account`.`id` AS `id`,`remark`.`evaluate` AS `evaluate`,`remark`.`remark` AS `remark`,`car_account`.`username` AS `username`,`car_detail`.`car_number` AS `car_number`,`company`.`company_name` AS `company_name`,`remark`.`create_time` AS `create_time` from (((`remark` join `company` on((`remark`.`company_id` = `company`.`id`))) join `car_detail` on((`remark`.`car_id` = `car_detail`.`car_id`))) join `car_account` on((`remark`.`car_id` = `car_account`.`id`)));

SET FOREIGN_KEY_CHECKS = 1;
