/*
 Navicat Premium Data Transfer

 Source Server         : cihog
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : guns

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 03/08/2019 20:39:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for estate_customer
-- ----------------------------
DROP TABLE IF EXISTS `estate_customer`;
CREATE TABLE `estate_customer`  (
  `customer_id` bigint(20) NOT NULL COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '姓名',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号',
  `rental` double(20, 2) DEFAULT NULL COMMENT '租价',
  `price` double(20, 2) DEFAULT NULL COMMENT '售价',
  `price_start` datetime(0) DEFAULT NULL COMMENT '售房时间',
  `rental_start` datetime(0) DEFAULT NULL COMMENT '租房起始时间',
  `rental_end` datetime(0) DEFAULT NULL COMMENT '租房结束时间',
  `price_tax` double(20, 2) DEFAULT NULL COMMENT '售价税费',
  `house_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '户型',
  `direction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '方向',
  `building_block` bigint(20) DEFAULT NULL COMMENT '栋座名称',
  `floor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '楼层',
  `room_number` int(10) DEFAULT NULL COMMENT '房号',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `customer_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '客户类型',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`customer_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of estate_customer
-- ----------------------------
INSERT INTO `estate_customer` VALUES (1157519799778029570, '李利光', '13711919653', 1500.00, 250.00, '2019-08-03 00:00:00', '2019-08-03 00:00:00', '2019-08-03 00:00:00', 2.00, '1-1', '东南', 1152769933717860353, '5', 502, 'jalsf/dfhklasas.hrfaskj.fkjaswe我看了案件反垄断法', '潜在客户', NULL, '2019-08-03 19:37:34');
INSERT INTO `estate_customer` VALUES (1157582871611928577, '李利光', '13711919653', 1800.00, 250.00, '2019-08-03 00:00:00', '2019-08-03 00:00:00', '2019-08-03 00:00:00', 2.50, '1-2', '东', 1152769933717860353, '4', 405, '开始阿萨德', '目标客户', '2019-08-03 17:23:58', '2019-08-03 19:37:26');
INSERT INTO `estate_customer` VALUES (1157586737128660993, '李利大', '13711919653', 1800.00, 250.00, '2019-08-03 00:00:00', '2019-08-03 00:00:00', '2019-08-03 00:00:00', 2.60, '4-4', '东', 1152769933717860353, '4', 405, '123123', '目标客户', '2019-08-03 17:39:20', '2019-08-03 19:37:21');

SET FOREIGN_KEY_CHECKS = 1;
