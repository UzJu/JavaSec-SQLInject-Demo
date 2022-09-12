/*
 Navicat Premium Data Transfer

 Source Server         : 本地MacPhpstudy
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 12/09/2022 21:45:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `userName` varchar(32) NOT NULL,
  `passWord` varchar(50) NOT NULL,
  `realName` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `userName`, `passWord`, `realName`) VALUES (1, 'admin', 'admin', 'haha');
INSERT INTO `user` (`id`, `userName`, `passWord`, `realName`) VALUES (2, 'test', 'test', 'test');
INSERT INTO `user` (`id`, `userName`, `passWord`, `realName`) VALUES (3, 'asd', 'asd', 'asd');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
