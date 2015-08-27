/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50533
Source Host           : localhost:3306
Source Database       : mydata

Target Server Type    : MYSQL
Target Server Version : 50533
File Encoding         : 65001

Date: 2013-09-04 15:06:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(16) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'cxq', '461393548@qq.com', '18910801303', '北京市昌平区天通苑', '27');
INSERT INTO `t_user` VALUES ('2', 'asdfasdf', 'asdfasdf', '1212312', '123123', '12');
INSERT INTO `t_user` VALUES ('3', 'hahh', 'asdfasdf', '1212312', '123123', '12');
INSERT INTO `t_user` VALUES ('4', 'qibasdfasd', 'sdfasd', '11899000222', '12131sfasdfasd', '12');
INSERT INTO `t_user` VALUES ('5', 'cxcasdf', 'sadfas', '2323423423', 'asefasdfasdf', '23');
INSERT INTO `t_user` VALUES ('6', 'cxasdfasdf', 'asdfas', '232343', '34534534', '12');
