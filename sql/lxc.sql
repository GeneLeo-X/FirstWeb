/*
Navicat MySQL Data Transfer

Source Server         : LXC
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : lxc

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2022-07-09 10:42:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(255) DEFAULT NULL COMMENT '分类名',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '数码产品');
INSERT INTO `category` VALUES ('2', '化妆品');
INSERT INTO `category` VALUES ('3', '服装');
INSERT INTO `category` VALUES ('4', '食品');

-- ----------------------------
-- Table structure for order_product
-- ----------------------------
DROP TABLE IF EXISTS `order_product`;
CREATE TABLE `order_product` (
  `oid` bigint(20) DEFAULT NULL,
  `pid` bigint(20) DEFAULT NULL,
  KEY `pid` (`pid`),
  KEY `oid` (`oid`),
  CONSTRAINT `oid` FOREIGN KEY (`oid`) REFERENCES `t_order` (`oid`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `pid` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_product
-- ----------------------------
INSERT INTO `order_product` VALUES ('1', '2');
INSERT INTO `order_product` VALUES ('1', '3');
INSERT INTO `order_product` VALUES ('1', '1');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `pid` bigint(20) NOT NULL AUTO_INCREMENT,
  `pname` varchar(255) DEFAULT '' COMMENT '商品名称',
  `cid` int(11) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL COMMENT '商品价格',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`pid`),
  KEY `cid` (`cid`),
  CONSTRAINT `cid` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '联想电脑', '1', '7600.00', '2022-07-22 15:27:33');
INSERT INTO `product` VALUES ('2', 'VIVO手机', '1', '1300.00', '2022-07-02 15:27:37');
INSERT INTO `product` VALUES ('3', 'NiKE服装', '3', '500.00', '2022-06-28 15:27:42');
INSERT INTO `product` VALUES ('5', 'Lancome香水', '2', '4300.00', '2022-07-23 15:27:46');
INSERT INTO `product` VALUES ('7', '精品黄瓜', '4', '123.00', '2022-07-29 17:46:46');
INSERT INTO `product` VALUES ('8', '精品黄瓜', '4', '123.00', null);
INSERT INTO `product` VALUES ('9', '精品黄瓜', '4', '123.00', null);
INSERT INTO `product` VALUES ('10', '精品黄瓜', '4', '123.00', null);
INSERT INTO `product` VALUES ('11', '精品黄瓜', '4', '123.00', null);
INSERT INTO `product` VALUES ('12', '精品黄瓜', '4', '123.00', null);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `sid` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学生姓名',
  `sno` varchar(30) CHARACTER SET utf8 COLLATE utf8_croatian_ci DEFAULT NULL COMMENT '学号',
  `age` int(3) DEFAULT '0' COMMENT '年龄',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=20200005 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('20200003', '张飞', '10002', '30');
INSERT INTO `student` VALUES ('20200004', '韩磊', '10001', '18');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `oid` bigint(20) NOT NULL AUTO_INCREMENT,
  `ono` varchar(255) DEFAULT NULL COMMENT '订单号',
  `create_time` datetime DEFAULT NULL COMMENT '订单创建时间',
  `total_price` decimal(10,2) DEFAULT NULL COMMENT '总金额',
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('1', '123456', '2022-07-05 15:37:41', '100000.00');
INSERT INTO `t_order` VALUES ('2', '123457', '2022-07-04 15:38:44', '200000.00');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT COMMENT 'uid',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `name` varchar(255) DEFAULT NULL COMMENT '名字',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '张珊', '战斗机', '123');
INSERT INTO `user` VALUES ('2', '贺涵', '坦克', '123');
INSERT INTO `user` VALUES ('3', '李展', '三轮', '123');
