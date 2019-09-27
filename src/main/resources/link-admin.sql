/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : link-admin

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2019-09-27 09:37:17
*/

SET FOREIGN_KEY_CHECKS=0;



-- ----------------------------
-- Table structure for `t_web_datascope`
-- ----------------------------
DROP TABLE IF EXISTS `t_web_datascope`;
CREATE TABLE `t_web_datascope` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `targetCategory` varchar(30) DEFAULT NULL,
  `targetId` varchar(32) DEFAULT NULL,
  `targetName` varchar(30) DEFAULT NULL,
  `targetUrl` varchar(150) NOT NULL,
  `permissionId` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_web_datascope
-- ----------------------------

-- ----------------------------
-- Table structure for `t_web_dept`
-- ----------------------------
DROP TABLE IF EXISTS `t_web_dept`;
CREATE TABLE `t_web_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `levels` int(11) DEFAULT NULL,
  `for_service` int(11) DEFAULT NULL,
  `deleted` int(11) DEFAULT NULL,
  `sorts` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_web_dept
-- ----------------------------
INSERT INTO `t_web_dept` VALUES ('1', 'Link公司', '0', '0', '0', '1', '0');
INSERT INTO `t_web_dept` VALUES ('2', '上海集团', '0', '0', null, '0', '1');
INSERT INTO `t_web_dept` VALUES ('3', '徐汇分公司1', '0', '1', null, '1', '11');
INSERT INTO `t_web_dept` VALUES ('4', '青浦分公司', '2', '1', null, '0', '12');
INSERT INTO `t_web_dept` VALUES ('5', '徐汇分公司', '2', '1', null, '0', '13');
INSERT INTO `t_web_dept` VALUES ('6', '人事部', '4', '2', null, '0', '11');
INSERT INTO `t_web_dept` VALUES ('7', '财务部', '4', '2', null, '0', '12');
INSERT INTO `t_web_dept` VALUES ('8', '信息中心', '4', '2', null, '0', '13');
INSERT INTO `t_web_dept` VALUES ('9', '运维部', '4', '2', null, '0', '14');
INSERT INTO `t_web_dept` VALUES ('10', '安全部', '4', '2', null, '1', '15');
INSERT INTO `t_web_dept` VALUES ('11', 'IT与流程', '5', '2', null, '0', '21');
INSERT INTO `t_web_dept` VALUES ('12', '快递系统研发部', '11', '2', null, '0', '12');
INSERT INTO `t_web_dept` VALUES ('13', '渠道系统研发部', '11', '2', null, '0', '13');
INSERT INTO `t_web_dept` VALUES ('14', '整车业务研发部', '11', '2', null, '0', '13');
INSERT INTO `t_web_dept` VALUES ('15', '物流系统研发部', '11', '2', null, '0', '14');

-- ----------------------------
-- Table structure for `t_web_logs`
-- ----------------------------
DROP TABLE IF EXISTS `t_web_logs`;
CREATE TABLE `t_web_logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginuser` varchar(30) DEFAULT NULL,
  `vsername` varchar(30) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `ip` varchar(30) DEFAULT NULL,
  `cratetime` datetime DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `requestmethod` varchar(30) DEFAULT NULL,
  `contenttype` varchar(50) DEFAULT NULL,
  `requestparams` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=195 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_web_logs
-- ----------------------------
INSERT INTO `t_web_logs` VALUES ('1', 'admin', '管理员', '用户列表', 'http://localhost:8888/rest/user/list', '0:0:0:0:0:0:0:1', '2019-09-26 13:51:46', '626', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('2', 'admin', '管理员', '用户列表', 'http://localhost:8888/rest/user/list', '0:0:0:0:0:0:0:1', '2019-09-26 13:51:51', '35', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('3', 'admin', '管理员', '角色列表', 'http://localhost:8888/rest/role/list', '0:0:0:0:0:0:0:1', '2019-09-26 13:52:05', '25', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('4', 'admin', '管理员', '用户信息', 'http://localhost:8888/rest/user/info', '0:0:0:0:0:0:0:1', '2019-09-26 13:53:54', '63', 'GET', null, null);
INSERT INTO `t_web_logs` VALUES ('5', 'admin', '管理员', '用户信息', 'http://localhost:8888/rest/user/info', '0:0:0:0:0:0:0:1', '2019-09-26 13:54:43', '143', 'GET', null, null);
INSERT INTO `t_web_logs` VALUES ('6', 'admin', '管理员', '用户信息', 'http://localhost:8888/rest/user/info', '0:0:0:0:0:0:0:1', '2019-09-26 13:54:53', '48', 'GET', null, null);
INSERT INTO `t_web_logs` VALUES ('7', 'admin', '管理员', '用户信息', 'http://localhost:8888/rest/user/info', '0:0:0:0:0:0:0:1', '2019-09-26 13:55:16', '84', 'GET', null, null);
INSERT INTO `t_web_logs` VALUES ('8', 'admin', '管理员', '角色列表', 'http://localhost:8888/rest/role/list', '0:0:0:0:0:0:0:1', '2019-09-26 13:55:36', '54', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('9', 'admin', '管理员', '修改角色', 'http://localhost:8888/rest/role/update', '0:0:0:0:0:0:0:1', '2019-09-26 13:55:42', '198', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('10', 'admin', '管理员', '角色列表', 'http://localhost:8888/rest/role/list', '0:0:0:0:0:0:0:1', '2019-09-26 13:55:42', '26', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('11', 'admin', '管理员', '用户信息', 'http://localhost:8888/rest/user/info', '0:0:0:0:0:0:0:1', '2019-09-26 13:55:48', '20', 'GET', null, null);
INSERT INTO `t_web_logs` VALUES ('12', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 13:55:59', '11', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('13', 'admin', '管理员', '用户信息', 'http://localhost:8888/rest/user/info', '0:0:0:0:0:0:0:1', '2019-09-26 13:56:01', '17', 'GET', null, null);
INSERT INTO `t_web_logs` VALUES ('14', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 13:56:01', '2', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('15', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 13:56:16', '1', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('16', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 13:58:38', '6', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('17', 'admin', '管理员', '用户信息', 'http://localhost:8888/rest/user/info', '0:0:0:0:0:0:0:1', '2019-09-26 13:58:53', '121', 'GET', null, null);
INSERT INTO `t_web_logs` VALUES ('18', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 14:02:56', '7', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('19', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 14:15:16', '8', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('20', 'admin', '管理员', '用户列表', 'http://localhost:8888/rest/user/list', '0:0:0:0:0:0:0:1', '2019-09-26 14:27:13', '62', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('21', 'admin', '管理员', '用户列表', 'http://localhost:8888/rest/user/list', '0:0:0:0:0:0:0:1', '2019-09-26 14:31:54', '94', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('22', 'admin', '管理员', '用户信息', 'http://localhost:8888/rest/user/info', '0:0:0:0:0:0:0:1', '2019-09-26 14:40:48', '56', 'GET', null, null);
INSERT INTO `t_web_logs` VALUES ('23', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 14:43:02', '1', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('24', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 14:46:13', '13558', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('25', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 14:47:15', '2', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('26', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 14:47:54', '7', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('27', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 14:48:42', '89', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('28', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 14:52:11', '7', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('29', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 14:52:25', '3', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('30', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 14:52:37', '15', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('31', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 14:53:17', '9', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('32', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 14:53:56', '9195', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('33', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 14:54:07', '34289', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('34', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 14:55:07', '95845', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('35', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 14:56:51', '14976', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('36', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 14:57:13', '14123', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('37', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 14:58:12', '22111', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('38', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 14:58:42', '8426', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('39', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 14:59:27', '7252', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('40', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 15:01:22', '21918', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('41', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 15:03:00', '4572', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('42', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 15:03:16', '42472', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('43', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 15:04:06', '1658', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('44', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 15:04:15', '1427', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('45', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 15:04:40', '2920', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('46', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 15:06:07', '83', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('47', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 15:06:12', '44', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('48', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 15:06:13', '11', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('49', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 15:06:20', '30', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('50', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 15:06:20', '7', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('51', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 15:06:22', '10', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('52', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 15:06:25', '12', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('53', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 15:06:27', '17', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('54', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 15:06:35', '15', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('55', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 15:06:37', '5', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('56', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 15:06:40', '21', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('57', 'admin', '管理员', '用户列表', 'http://localhost:8888/rest/user/list', '0:0:0:0:0:0:0:1', '2019-09-26 15:06:54', '64', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('58', 'admin', '管理员', '用户列表', 'http://localhost:8888/rest/user/list', '0:0:0:0:0:0:0:1', '2019-09-26 15:06:58', '11', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('59', 'admin', '管理员', '用户列表', 'http://localhost:8888/rest/user/list', '0:0:0:0:0:0:0:1', '2019-09-26 15:07:00', '29', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('60', 'admin', '管理员', '角色列表', 'http://localhost:8888/rest/role/list', '0:0:0:0:0:0:0:1', '2019-09-26 15:07:02', '6', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('61', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 15:12:42', '0', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('62', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 15:13:03', '17', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('63', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 15:15:36', '20', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('64', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 15:15:38', '7', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('65', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 15:17:07', '20', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('66', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 15:17:09', '13', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('67', 'admin', '管理员', '用户信息', 'http://localhost:8888/rest/user/info', '0:0:0:0:0:0:0:1', '2019-09-26 15:26:43', '217', 'GET', null, null);
INSERT INTO `t_web_logs` VALUES ('68', 'admin', '管理员', '用户信息', 'http://localhost:8888/rest/user/info', '0:0:0:0:0:0:0:1', '2019-09-26 15:28:47', '21', 'GET', null, null);
INSERT INTO `t_web_logs` VALUES ('69', 'admin', '管理员', '用户信息', 'http://localhost:8888/rest/user/info', '0:0:0:0:0:0:0:1', '2019-09-26 15:29:23', '38', 'GET', null, null);
INSERT INTO `t_web_logs` VALUES ('70', 'admin', '管理员', '用户信息', 'http://localhost:8888/rest/user/info', '0:0:0:0:0:0:0:1', '2019-09-26 15:29:46', '28', 'GET', null, null);
INSERT INTO `t_web_logs` VALUES ('71', 'admin', '管理员', '用户信息', 'http://localhost:8888/rest/user/info', '0:0:0:0:0:0:0:1', '2019-09-26 15:30:42', '45', 'GET', null, null);
INSERT INTO `t_web_logs` VALUES ('72', 'admin', '管理员', '用户信息', 'http://localhost:8888/rest/user/info', '0:0:0:0:0:0:0:1', '2019-09-26 15:30:50', '47', 'GET', null, null);
INSERT INTO `t_web_logs` VALUES ('73', 'admin', '管理员', '用户信息', 'http://localhost:8888/rest/user/info', '0:0:0:0:0:0:0:1', '2019-09-26 15:31:35', '45', 'GET', null, null);
INSERT INTO `t_web_logs` VALUES ('74', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 15:32:41', '13', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('75', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 15:33:18', '11', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('76', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 15:34:01', '6', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('77', 'admin', '管理员', '用户信息', 'http://localhost:8888/rest/user/info', '0:0:0:0:0:0:0:1', '2019-09-26 15:34:25', '27', 'GET', null, null);
INSERT INTO `t_web_logs` VALUES ('78', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 15:34:55', '7', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('79', 'admin', '管理员', '用户信息', 'http://localhost:8888/rest/user/info', '0:0:0:0:0:0:0:1', '2019-09-26 15:36:53', '29', 'GET', null, null);
INSERT INTO `t_web_logs` VALUES ('80', 'admin', '管理员', '用户信息', 'http://localhost:8888/rest/user/info', '0:0:0:0:0:0:0:1', '2019-09-26 15:36:54', '24', 'GET', null, null);
INSERT INTO `t_web_logs` VALUES ('81', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 15:38:21', '12', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('82', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 15:38:25', '16', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('83', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 15:38:26', '7', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('84', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 15:38:26', '6', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('85', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 15:38:31', '13', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('86', 'admin', '管理员', '用户信息', 'http://localhost:8888/rest/user/info', '0:0:0:0:0:0:0:1', '2019-09-26 16:00:24', '38', 'GET', null, null);
INSERT INTO `t_web_logs` VALUES ('87', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:01:30', '16', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('88', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:01:34', '10', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('89', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:01:35', '13', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('90', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:01:36', '14', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('91', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:01:37', '6', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('92', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:01:38', '25', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('93', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:01:41', '12', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('94', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:01:45', '12', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('95', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:01:47', '9', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('96', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:01:51', '10', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('97', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:01:54', '8', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('98', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:01:58', '10', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('99', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:02:01', '10', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('100', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:03:23', '21', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('101', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:03:25', '10', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('102', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:03:28', '16', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('103', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:03:32', '16', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('104', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:03:35', '6', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('105', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:03:38', '11', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('106', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:03:40', '8', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('107', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:06:26', '8', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('108', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:07:02', '7', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('109', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:07:04', '9', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('110', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:08:16', '18', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('111', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:08:23', '13', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('112', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:08:25', '7', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('113', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:15:55', '10', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('114', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:15:56', '10', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('115', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:52:34', '32', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('116', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:52:36', '11', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('117', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:52:37', '15', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('118', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:52:40', '7', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('119', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:53:00', '10', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('120', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:53:19', '8', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('121', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:53:20', '6', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('122', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:53:22', '7', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('123', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:53:27', '7', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('124', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:53:49', '3', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('125', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:54:04', '6', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('126', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:54:10', '13', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('127', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:54:16', '9', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('128', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:54:20', '7', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('129', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:54:22', '5', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('130', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:54:24', '10', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('131', 'admin', '管理员', '用户列表', 'http://localhost:8888/rest/user/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:54:32', '56', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('132', 'editor', '测试员1', '用户信息', 'http://localhost:8888/rest/user/info', '0:0:0:0:0:0:0:1', '2019-09-26 16:54:40', '34', 'GET', null, null);
INSERT INTO `t_web_logs` VALUES ('133', 'editor', '测试员1', '用户信息', 'http://localhost:8888/rest/user/info', '0:0:0:0:0:0:0:1', '2019-09-26 16:54:43', '27', 'GET', null, null);
INSERT INTO `t_web_logs` VALUES ('134', 'admin', '管理员', '用户信息', 'http://localhost:8888/rest/user/info', '0:0:0:0:0:0:0:1', '2019-09-26 16:55:02', '8', 'GET', null, null);
INSERT INTO `t_web_logs` VALUES ('135', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:55:30', '9', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('136', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:55:50', '5', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('137', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:57:24', '523', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('138', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:57:28', '14', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('139', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:57:58', '83', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('140', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:57:59', '11', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('141', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:58:55', '70', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('142', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:59:01', '7', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('143', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:59:07', '7', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('144', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:59:13', '6', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('145', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:59:14', '6', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('146', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:59:14', '6', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('147', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:59:21', '7', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('148', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:59:26', '17', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('149', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:59:26', '8', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('150', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:59:27', '6', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('151', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:59:33', '12', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('152', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:59:35', '12', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('153', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 16:59:38', '7', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('154', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 17:00:33', '77', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('155', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 17:00:36', '14', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('156', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 17:00:36', '11', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('157', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 17:00:38', '6', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('158', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 17:00:39', '12', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('159', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 17:02:01', '7', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('160', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 17:02:04', '0', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('161', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 17:02:16', '4', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('162', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 17:02:20', '10', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('163', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 17:02:22', '8', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('164', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 17:04:13', '7', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('165', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 17:04:14', '14', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('166', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 17:04:15', '12', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('167', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-26 17:04:16', '7', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('168', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-27 08:40:02', '26', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('169', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-27 08:40:05', '9', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('170', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-27 08:40:08', '12', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('171', 'admin', '管理员', '日志列表', 'http://localhost:8888/rest/logs/blog/list', '0:0:0:0:0:0:0:1', '2019-09-27 08:40:08', '8', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('172', 'admin', '管理员', '用户信息', 'http://localhost:8888/rest/user/info', '0:0:0:0:0:0:0:1', '2019-09-27 08:41:36', '311', 'GET', null, null);
INSERT INTO `t_web_logs` VALUES ('173', 'admin', '管理员', '用户信息', 'http://localhost:8888/rest/user/info', '0:0:0:0:0:0:0:1', '2019-09-27 08:44:29', '38', 'GET', null, null);
INSERT INTO `t_web_logs` VALUES ('174', 'admin', '管理员', '用户信息', 'http://localhost:8888/rest/user/info', '0:0:0:0:0:0:0:1', '2019-09-27 08:53:40', '45', 'GET', null, null);
INSERT INTO `t_web_logs` VALUES ('175', 'admin', '管理员', '用户列表', 'http://localhost:8888/rest/user/list', '0:0:0:0:0:0:0:1', '2019-09-27 08:54:41', '104', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('176', 'admin', '管理员', '角色列表', 'http://localhost:8888/rest/role/list', '0:0:0:0:0:0:0:1', '2019-09-27 08:55:14', '4', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('177', 'admin', '管理员', '用户信息', 'http://localhost:8888/rest/user/info', '0:0:0:0:0:0:0:1', '2019-09-27 09:16:02', '17', 'GET', null, null);
INSERT INTO `t_web_logs` VALUES ('178', 'admin', '管理员', '修改权限', 'http://localhost:8888/rest/permission/update', '0:0:0:0:0:0:0:1', '2019-09-27 09:16:33', '72', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('179', 'admin', '管理员', '用户列表', 'http://localhost:8888/rest/user/list', '0:0:0:0:0:0:0:1', '2019-09-27 09:17:01', '33', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('180', 'admin', '管理员', '用户列表', 'http://localhost:8888/rest/user/list', '0:0:0:0:0:0:0:1', '2019-09-27 09:17:20', '28', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('181', 'admin', '管理员', '用户列表', 'http://localhost:8888/rest/user/list', '0:0:0:0:0:0:0:1', '2019-09-27 09:17:29', '31', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('182', 'admin', '管理员', '用户列表', 'http://localhost:8888/rest/user/list', '0:0:0:0:0:0:0:1', '2019-09-27 09:17:35', '33', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('183', 'admin', '管理员', '用户列表', 'http://localhost:8888/rest/user/list', '0:0:0:0:0:0:0:1', '2019-09-27 09:17:42', '26', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('184', 'admin', '管理员', '用户列表', 'http://localhost:8888/rest/user/list', '0:0:0:0:0:0:0:1', '2019-09-27 09:17:47', '6', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('185', 'admin', '管理员', '用户列表', 'http://localhost:8888/rest/user/list', '0:0:0:0:0:0:0:1', '2019-09-27 09:17:48', '17', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('186', 'admin', '管理员', '用户信息', 'http://localhost:8888/rest/user/info', '0:0:0:0:0:0:0:1', '2019-09-27 09:34:05', '26', 'GET', null, null);
INSERT INTO `t_web_logs` VALUES ('187', 'admin', '管理员', '用户列表', 'http://localhost:8888/rest/user/list', '0:0:0:0:0:0:0:1', '2019-09-27 09:34:11', '104', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('188', 'admin', '管理员', '用户列表', 'http://localhost:8888/rest/user/list', '0:0:0:0:0:0:0:1', '2019-09-27 09:34:13', '11', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('189', 'admin', '管理员', '用户列表', 'http://localhost:8888/rest/user/list', '0:0:0:0:0:0:0:1', '2019-09-27 09:34:14', '16', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('190', 'admin', '管理员', '角色列表', 'http://localhost:8888/rest/role/list', '0:0:0:0:0:0:0:1', '2019-09-27 09:34:16', '4', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('191', 'admin', '管理员', '角色列表', 'http://localhost:8888/rest/role/list', '0:0:0:0:0:0:0:1', '2019-09-27 09:34:19', '3', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('192', 'admin', '管理员', '用户列表', 'http://localhost:8888/rest/user/list', '0:0:0:0:0:0:0:1', '2019-09-27 09:36:41', '155', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('193', 'admin', '管理员', '用户列表', 'http://localhost:8888/rest/user/list', '0:0:0:0:0:0:0:1', '2019-09-27 09:36:42', '10', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('194', 'admin', '管理员', '用户列表', 'http://localhost:8888/rest/user/list', '0:0:0:0:0:0:0:1', '2019-09-27 09:36:43', '25', 'POST', 'application/json;charset=UTF-8', null);

-- ----------------------------
-- Table structure for `t_web_right`
-- ----------------------------
DROP TABLE IF EXISTS `t_web_right`;
CREATE TABLE `t_web_right` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `parent_id` int(11) NOT NULL,
  `types` int(11) DEFAULT NULL,
  `css` varchar(50) DEFAULT NULL,
  `url` varchar(150) DEFAULT NULL,
  `levels` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `sorts` int(11) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `dataScope` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_web_right
-- ----------------------------
INSERT INTO `t_web_right` VALUES ('1', '首页', '0', '0', null, '/', null, null, '1', null, null);
INSERT INTO `t_web_right` VALUES ('2', '文档', '0', '0', null, '/documentation', null, null, '6', null, null);
INSERT INTO `t_web_right` VALUES ('4', '外链', '0', '0', null, 'https://github.com/252956/vue-link-admin', null, null, '8', null, null);
INSERT INTO `t_web_right` VALUES ('5', '错误页面', '0', '0', null, '/error', null, null, '3', null, null);
INSERT INTO `t_web_right` VALUES ('6', '401', '5', '0', null, '/error/401', null, null, '30', null, null);
INSERT INTO `t_web_right` VALUES ('7', '404', '5', '0', null, '/error/404', null, null, '31', null, null);
INSERT INTO `t_web_right` VALUES ('8', '系统权限', '0', '0', null, '/permission', null, null, '2', null, null);
INSERT INTO `t_web_right` VALUES ('9', '用户管理', '8', '0', null, '/permission/user', null, null, '20', null, null);
INSERT INTO `t_web_right` VALUES ('10', '角色管理', '8', '0', null, '/permission/role', null, null, '21', null, null);
INSERT INTO `t_web_right` VALUES ('11', '权限管理', '8', '0', null, '/permission/permission', null, null, '22', null, null);
INSERT INTO `t_web_right` VALUES ('13', '部门管理', '8', '0', null, '/permission/dept', null, null, '24', null, null);
INSERT INTO `t_web_right` VALUES ('14', '图标', '0', '0', null, '/icon', null, null, '7', null, null);
INSERT INTO `t_web_right` VALUES ('16', '查询用户', '9', '1', null, '/rest/user/list', null, null, '200', null, null);
INSERT INTO `t_web_right` VALUES ('17', '新增用户', '9', '1', null, '/rest/user/add', null, null, '201', null, null);
INSERT INTO `t_web_right` VALUES ('18', '修改用户', '9', '1', null, '/rest/user/update', null, null, '202', null, null);
INSERT INTO `t_web_right` VALUES ('19', '删除用户', '9', '1', null, '/rest/user/delete', null, null, '203', null, null);
INSERT INTO `t_web_right` VALUES ('20', '查询列表', '10', '1', null, '/rest/role/list', null, null, '210', null, null);
INSERT INTO `t_web_right` VALUES ('21', '新增角色', '10', '1', null, '/rest/role/add', null, null, '211', null, null);
INSERT INTO `t_web_right` VALUES ('22', '修改角色', '10', '1', null, '/rest/role/update', null, null, '212', null, null);
INSERT INTO `t_web_right` VALUES ('23', '删除角色', '10', '1', null, '/rest/role/delete', null, null, '213', null, null);
INSERT INTO `t_web_right` VALUES ('24', '查询', '11', '1', null, '/rest/permission/all', null, null, '220', null, null);
INSERT INTO `t_web_right` VALUES ('25', '新增权限', '11', '1', null, '/rest/permission/add', null, null, '221', null, null);
INSERT INTO `t_web_right` VALUES ('26', '修改权限', '11', '1', null, '/rest/permission/update', null, null, '222', null, null);
INSERT INTO `t_web_right` VALUES ('27', '删除权限', '11', '1', null, '/rest/permission/delete', null, null, '223', null, null);
INSERT INTO `t_web_right` VALUES ('28', '查询', '13', '1', null, '/rest/department/all', null, null, '240', null, null);
INSERT INTO `t_web_right` VALUES ('29', '新增部门', '13', '1', null, '/rest/department/add', null, null, '241', null, null);
INSERT INTO `t_web_right` VALUES ('30', '修改部门', '13', '1', null, '/rest/department/update', null, null, '242', null, null);
INSERT INTO `t_web_right` VALUES ('31', '删除部门', '13', '1', null, '/rest/department/delete', null, null, '243', null, null);
INSERT INTO `t_web_right` VALUES ('32', '业务日志', '0', '0', null, '/logs', null, null, '4', null, null);
INSERT INTO `t_web_right` VALUES ('33', '错误日志', '32', '0', null, '/logs/error-log', null, null, '40', null, null);
INSERT INTO `t_web_right` VALUES ('34', '业务日志', '32', '0', null, '/logs/blog', null, null, '41', null, null);
INSERT INTO `t_web_right` VALUES ('35', '查询', '34', '1', null, '/rest/logs/blog/list', null, null, null, null, null);

-- ----------------------------
-- Table structure for `t_web_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_web_role`;
CREATE TABLE `t_web_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_web_role
-- ----------------------------
INSERT INTO `t_web_role` VALUES ('1', 'admin', '超级管理员');
INSERT INTO `t_web_role` VALUES ('2', 'editor', null);
INSERT INTO `t_web_role` VALUES ('8', '1', '');
INSERT INTO `t_web_role` VALUES ('9', '2', '');
INSERT INTO `t_web_role` VALUES ('10', '3', '');

-- ----------------------------
-- Table structure for `t_web_role_right`
-- ----------------------------
DROP TABLE IF EXISTS `t_web_role_right`;
CREATE TABLE `t_web_role_right` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `right_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=191 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_web_role_right
-- ----------------------------
INSERT INTO `t_web_role_right` VALUES ('57', '8', '1');
INSERT INTO `t_web_role_right` VALUES ('58', '9', '1');
INSERT INTO `t_web_role_right` VALUES ('59', '10', '1');
INSERT INTO `t_web_role_right` VALUES ('120', '2', '1');
INSERT INTO `t_web_role_right` VALUES ('121', '2', '5');
INSERT INTO `t_web_role_right` VALUES ('122', '2', '6');
INSERT INTO `t_web_role_right` VALUES ('123', '2', '7');
INSERT INTO `t_web_role_right` VALUES ('125', '2', '4');
INSERT INTO `t_web_role_right` VALUES ('126', '2', '2');
INSERT INTO `t_web_role_right` VALUES ('127', '2', '14');
INSERT INTO `t_web_role_right` VALUES ('159', '1', '1');
INSERT INTO `t_web_role_right` VALUES ('160', '1', '8');
INSERT INTO `t_web_role_right` VALUES ('161', '1', '9');
INSERT INTO `t_web_role_right` VALUES ('162', '1', '16');
INSERT INTO `t_web_role_right` VALUES ('163', '1', '17');
INSERT INTO `t_web_role_right` VALUES ('164', '1', '18');
INSERT INTO `t_web_role_right` VALUES ('165', '1', '19');
INSERT INTO `t_web_role_right` VALUES ('166', '1', '10');
INSERT INTO `t_web_role_right` VALUES ('167', '1', '20');
INSERT INTO `t_web_role_right` VALUES ('168', '1', '21');
INSERT INTO `t_web_role_right` VALUES ('169', '1', '22');
INSERT INTO `t_web_role_right` VALUES ('170', '1', '23');
INSERT INTO `t_web_role_right` VALUES ('171', '1', '11');
INSERT INTO `t_web_role_right` VALUES ('172', '1', '24');
INSERT INTO `t_web_role_right` VALUES ('173', '1', '25');
INSERT INTO `t_web_role_right` VALUES ('174', '1', '26');
INSERT INTO `t_web_role_right` VALUES ('175', '1', '27');
INSERT INTO `t_web_role_right` VALUES ('176', '1', '13');
INSERT INTO `t_web_role_right` VALUES ('177', '1', '28');
INSERT INTO `t_web_role_right` VALUES ('178', '1', '29');
INSERT INTO `t_web_role_right` VALUES ('179', '1', '30');
INSERT INTO `t_web_role_right` VALUES ('180', '1', '31');
INSERT INTO `t_web_role_right` VALUES ('181', '1', '5');
INSERT INTO `t_web_role_right` VALUES ('182', '1', '6');
INSERT INTO `t_web_role_right` VALUES ('183', '1', '7');
INSERT INTO `t_web_role_right` VALUES ('184', '1', '32');
INSERT INTO `t_web_role_right` VALUES ('185', '1', '33');
INSERT INTO `t_web_role_right` VALUES ('186', '1', '34');
INSERT INTO `t_web_role_right` VALUES ('187', '1', '35');
INSERT INTO `t_web_role_right` VALUES ('188', '1', '2');
INSERT INTO `t_web_role_right` VALUES ('189', '1', '14');
INSERT INTO `t_web_role_right` VALUES ('190', '1', '4');

-- ----------------------------
-- Table structure for `t_web_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_web_user`;
CREATE TABLE `t_web_user` (
  `uid` varchar(32) NOT NULL,
  `name` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL,
  `vsername` varchar(30) DEFAULT NULL,
  `mobile` varchar(50) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `deptid` varchar(11) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `lastLoginTime` datetime DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_web_user
-- ----------------------------
INSERT INTO `t_web_user` VALUES ('1', 'admin', 'E10ADC3949BA59ABBE56E057F20F883E', '管理员', '17601269', '2019-09-07 10:08:25', '1', '4', '', null);
INSERT INTO `t_web_user` VALUES ('2', 'editor', 'E10ADC3949BA59ABBE56E057F20F883E', '测试员1', '121212121212', '2019-09-09 16:40:43', '0', '7', null, null);
INSERT INTO `t_web_user` VALUES ('2a1d17d2f4184e238816fd8b96195a3e', '1', 'C4CA4238A0B923820DCC509A6F75849B', '1', '', '2019-09-17 15:57:10', '1', '2', '', null);
INSERT INTO `t_web_user` VALUES ('5029d7aa882546839b6dac1f5b5c1ef7', '1', 'C4CA4238A0B923820DCC509A6F75849B', '1', '', '2019-09-18 10:21:42', '0', '2', '', null);
INSERT INTO `t_web_user` VALUES ('594f179c609945689def728c01ddf8f1', '3', 'ECCBC87E4B5CE2FE28308FD9F2A7BAF3', '3', '123456', '2019-09-17 16:00:10', '0', '11', null, null);
INSERT INTO `t_web_user` VALUES ('93bad36e94ab48ab977f9718147c573f', '6', '1679091C5A880FAF6FB5E6087EB1B2DC', '6', '', '2019-09-18 15:16:45', '0', '5', '', null);
INSERT INTO `t_web_user` VALUES ('ab334041dc2c4786a7bddb49bf8b7816', '7', '8F14E45FCEEA167A5A36DEDD4BEA2543', '7', '', '2019-09-19 09:32:48', '1', '7', '', null);
INSERT INTO `t_web_user` VALUES ('ad904a794a10434b8dec1de8ce23a288', '辉桑', 'E10ADC3949BA59ABBE56E057F20F883E', '辉桑', '1111111', '2019-09-18 13:47:51', '0', '12', null, null);
INSERT INTO `t_web_user` VALUES ('b88bb916dc054870ae124d92710ac3d3', '云哥', 'E10ADC3949BA59ABBE56E057F20F883E', '云哥', '1760126', '2019-09-18 11:11:39', '0', '12', null, null);
INSERT INTO `t_web_user` VALUES ('c2bd6773d48643a9ac4540a551ba6ffb', '用嗓', 'E10ADC3949BA59ABBE56E057F20F883E', '用嗓', '', '2019-09-18 15:16:13', '0', '13', '', null);
INSERT INTO `t_web_user` VALUES ('dde9b279949e4e76ac3fff531d712227', '2', 'C81E728D9D4C2F636F067F89CC14862C', '2', '', '2019-09-17 15:57:33', '0', '6', '', null);
INSERT INTO `t_web_user` VALUES ('fa350415ebcc43c2a86cc0cbad98a0a2', '5', 'E4DA3B7FBBCE2345D7772B0674A318D5', '5', '', '2019-09-18 15:16:34', '0', '4', '', null);

-- ----------------------------
-- Table structure for `t_web_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_web_user_role`;
CREATE TABLE `t_web_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_web_user_role
-- ----------------------------
INSERT INTO `t_web_user_role` VALUES ('15', 'b88bb916dc054870ae124d92710ac3d3', '2');
INSERT INTO `t_web_user_role` VALUES ('18', 'ad904a794a10434b8dec1de8ce23a288', '2');
INSERT INTO `t_web_user_role` VALUES ('19', 'ad904a794a10434b8dec1de8ce23a288', '17');
INSERT INTO `t_web_user_role` VALUES ('20', 'c2bd6773d48643a9ac4540a551ba6ffb', '2');
INSERT INTO `t_web_user_role` VALUES ('21', 'fa350415ebcc43c2a86cc0cbad98a0a2', '2');
INSERT INTO `t_web_user_role` VALUES ('22', '93bad36e94ab48ab977f9718147c573f', '2');
INSERT INTO `t_web_user_role` VALUES ('23', '2', '2');
INSERT INTO `t_web_user_role` VALUES ('24', '2', '9');
INSERT INTO `t_web_user_role` VALUES ('25', '2', '8');
INSERT INTO `t_web_user_role` VALUES ('37', '1', '1');
INSERT INTO `t_web_user_role` VALUES ('38', '1', '2');
INSERT INTO `t_web_user_role` VALUES ('39', 'ab334041dc2c4786a7bddb49bf8b7816', '2');
INSERT INTO `t_web_user_role` VALUES ('40', 'ab334041dc2c4786a7bddb49bf8b7816', '8');
INSERT INTO `t_web_user_role` VALUES ('41', 'ab334041dc2c4786a7bddb49bf8b7816', '10');
INSERT INTO `t_web_user_role` VALUES ('42', 'ab334041dc2c4786a7bddb49bf8b7816', '9');
