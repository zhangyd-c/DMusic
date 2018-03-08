/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50624
Source Host           : 127.0.0.1:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2018-03-08 14:09:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for biz_wangyi_play
-- ----------------------------
DROP TABLE IF EXISTS `biz_wangyi_play`;
CREATE TABLE `biz_wangyi_play` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `play_id` varchar(30) NOT NULL COMMENT '网易歌单id',
  `play_name` varchar(100) NOT NULL COMMENT '网易歌单名',
  `status` tinyint(1) DEFAULT '0',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of biz_wangyi_play
-- ----------------------------
INSERT INTO `biz_wangyi_play` VALUES ('1', '448927692', '我喜欢的音乐', '1', '2018-03-08 13:25:38', '2018-03-08 13:25:38');
INSERT INTO `biz_wangyi_play` VALUES ('2', '499010115', '周星驰', '0', '2018-03-08 13:26:10', '2018-03-08 13:26:10');
INSERT INTO `biz_wangyi_play` VALUES ('3', '473216126', '伍佰', '0', '2018-03-08 13:26:16', '2018-03-08 13:26:16');
INSERT INTO `biz_wangyi_play` VALUES ('4', '477142078', '伊人月下戴红妆', '0', '2018-03-08 13:26:24', '2018-03-08 13:26:24');
INSERT INTO `biz_wangyi_play` VALUES ('5', '879855523', '好听的英文歌曲', '0', '2018-03-08 13:28:19', '2018-03-08 13:28:19');
INSERT INTO `biz_wangyi_play` VALUES ('6', '866426412', '穿透灵魂的柔情女声·十大伤感女声', '0', '2018-03-08 13:28:58', '2018-03-08 13:28:58');
INSERT INTO `biz_wangyi_play` VALUES ('7', '813229871', '最后的莫西干人', '0', '2018-03-08 13:29:13', '2018-03-08 13:29:13');
INSERT INTO `biz_wangyi_play` VALUES ('8', '522532786', '中国十大名曲', '0', '2018-03-08 13:29:23', '2018-03-08 13:29:23');
INSERT INTO `biz_wangyi_play` VALUES ('9', '520172117', '♪适合学习英语的歌曲', '0', '2018-03-08 13:29:43', '2018-03-08 13:29:43');
