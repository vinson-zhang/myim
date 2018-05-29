/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : myim

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-05-29 14:02:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_friend
-- ----------------------------
DROP TABLE IF EXISTS `t_friend`;
CREATE TABLE `t_friend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `friend_id` int(11) NOT NULL,
  `status` tinyint(4) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userid-friendid` (`user_id`,`friend_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_friend
-- ----------------------------
INSERT INTO `t_friend` VALUES ('1', '4', '7', '2', '2018-05-22 21:22:53', '2018-05-22 22:00:04');
INSERT INTO `t_friend` VALUES ('2', '7', '4', '2', '2018-05-22 22:00:05', '2018-05-22 22:00:04');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `gender` tinyint(4) DEFAULT NULL COMMENT '1:男 2:女',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `age` int(11) DEFAULT NULL,
  `head_photo` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_unique_index` (`username`) USING BTREE COMMENT 'username 唯一索引',
  UNIQUE KEY `email_unique_index` (`email`),
  UNIQUE KEY `phone_unique_index` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('4', 'test', '123456', null, null, '2018-05-13 18:26:38', '2018-05-26 21:38:30', null, null, null, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527158996602&di=e076032521ec56c86d62c947d3448e0b&imgtype=0&src=http%3A%2F%2Fi5.xiachufang.com%2Fimage%2F600%2Fe234f180e03711e4b0bce0db5512b208.jpg', '蒸水蛋');
INSERT INTO `t_user` VALUES ('7', 'mike', '123456', null, null, '2018-05-22 20:53:54', '2018-05-26 21:41:11', null, null, null, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527159040332&di=ee6fb0ee82c7abb3a11ceadf2a9d7d9b&imgtype=0&src=http%3A%2F%2Fhiphotos.baidu.com%2Fnuomi%2Fpic%2Fitem%2Fcaef76094b36acaf5515513875d98d1000e99c79.jpg', '过桥米线');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('hello', '12');
INSERT INTO `user` VALUES ('Vinson', '456');
INSERT INTO `user` VALUES ('test', '123456');
INSERT INTO `user` VALUES ('test', '123456');
