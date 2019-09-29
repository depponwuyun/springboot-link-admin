/*
Navicat MySQL Data Transfer

Source Server         : aliyun
Source Server Version : 50725
Source Host           : 47.103.154.36:3306
Source Database       : link_admin

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-09-29 16:20:20
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
) ENGINE=InnoDB AUTO_INCREMENT=289 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_web_logs
-- ----------------------------
INSERT INTO `t_web_logs` VALUES ('258', 'admin', '管理员', '用户列表', 'http://linkadmin/rest/user/list', '127.0.0.1', '2019-09-29 15:16:25', '10', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('259', 'admin', '管理员', '用户列表', 'http://linkadmin/rest/user/list', '127.0.0.1', '2019-09-29 15:16:26', '9', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('260', 'admin', '管理员', '用户信息', 'http://linkadmin/rest/user/info', '127.0.0.1', '2019-09-29 15:23:46', '6', 'GET', null, null);
INSERT INTO `t_web_logs` VALUES ('261', 'admin', '管理员', '用户列表', 'http://linkadmin/rest/user/list', '127.0.0.1', '2019-09-29 15:23:46', '10', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('262', 'admin', '管理员', '用户信息', 'http://linkadmin/rest/user/info', '127.0.0.1', '2019-09-29 15:26:34', '12', 'GET', null, null);
INSERT INTO `t_web_logs` VALUES ('263', 'admin', '管理员', '用户列表', 'http://linkadmin/rest/user/list', '127.0.0.1', '2019-09-29 15:26:35', '9', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('264', null, null, '登录', 'http://linkadmin/rest/user/login', '127.0.0.1', '2019-09-29 15:28:49', '49', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('265', 'admin', '管理员', '用户信息', 'http://linkadmin/rest/user/info', '127.0.0.1', '2019-09-29 15:28:49', '20', 'GET', null, null);
INSERT INTO `t_web_logs` VALUES ('266', 'admin', '管理员', '用户列表', 'http://linkadmin/rest/user/list', '127.0.0.1', '2019-09-29 15:28:49', '12', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('267', 'admin', '管理员', '用户列表', 'http://linkadmin/rest/user/list', '127.0.0.1', '2019-09-29 15:28:53', '9', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('268', null, null, '登录', 'http://linkadmin/rest/user/login', '127.0.0.1', '2019-09-29 15:30:50', '3', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('269', 'admin', '管理员', '用户信息', 'http://linkadmin/rest/user/info', '127.0.0.1', '2019-09-29 15:30:50', '5', 'GET', null, null);
INSERT INTO `t_web_logs` VALUES ('270', null, null, '登录', 'http://47.103.154.36:8888/rest/user/login', '116.228.112.130', '2019-09-29 15:46:02', '39847', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('271', null, null, '登录', 'http://47.103.154.36:8888/rest/user/login', '116.228.112.130', '2019-09-29 15:46:23', '19391', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('272', null, null, '登录', 'http://47.103.154.36:8888/rest/user/login', '116.228.112.130', '2019-09-29 15:46:12', '30221', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('273', null, null, '登录', 'http://47.103.154.36:8888/rest/user/login', '116.228.112.130', '2019-09-29 15:47:12', '3', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('274', 'admin', '管理员', '用户信息', 'http://47.103.154.36:8888/rest/user/info', '116.228.112.130', '2019-09-29 15:47:12', '99', 'GET', null, null);
INSERT INTO `t_web_logs` VALUES ('275', 'admin', '管理员', '登录', 'http://47.103.154.36:8888/rest/user/login', '116.228.112.130', '2019-09-29 15:47:16', '4', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('276', null, null, '退出', 'http://47.103.154.36:8888/rest/user/logout', '116.228.112.130', '2019-09-29 15:47:28', '0', 'GET', null, null);
INSERT INTO `t_web_logs` VALUES ('277', null, null, '登录', 'http://47.103.154.36:8888/rest/user/login', '116.228.112.130', '2019-09-29 15:47:29', '2', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('278', 'admin', '管理员', '用户信息', 'http://47.103.154.36:8888/rest/user/info', '116.228.112.130', '2019-09-29 15:47:29', '18', 'GET', null, null);
INSERT INTO `t_web_logs` VALUES ('279', 'admin', '管理员', '用户列表', 'http://47.103.154.36:8888/rest/user/list', '116.228.112.130', '2019-09-29 15:47:29', '41', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('280', 'admin', '管理员', '用户列表', 'http://47.103.154.36:8888/rest/user/list', '116.228.112.130', '2019-09-29 15:47:31', '14', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('281', null, null, '退出', 'http://47.103.154.36:8888/rest/user/logout', '116.228.112.130', '2019-09-29 15:50:20', '5', 'GET', null, null);
INSERT INTO `t_web_logs` VALUES ('282', null, null, '登录', 'http://47.103.154.36:8888/rest/user/login', '116.228.112.130', '2019-09-29 15:50:22', '10551', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('283', null, null, '登录', 'http://47.103.154.36:8888/rest/user/login', '116.228.112.130', '2019-09-29 15:50:49', '9', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('284', 'admin', '管理员', '用户信息', 'http://47.103.154.36:8888/rest/user/info', '116.228.112.130', '2019-09-29 15:50:49', '50', 'GET', null, null);
INSERT INTO `t_web_logs` VALUES ('285', 'admin', '管理员', '用户列表', 'http://47.103.154.36:8888/rest/user/list', '116.228.112.130', '2019-09-29 15:51:09', '25', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('286', 'admin', '管理员', '角色列表', 'http://47.103.154.36:8888/rest/role/list', '116.228.112.130', '2019-09-29 15:51:13', '9', 'POST', 'application/json;charset=UTF-8', null);
INSERT INTO `t_web_logs` VALUES ('287', 'admin', '管理员', '用户信息', 'http://47.103.154.36:8888/rest/user/info', '116.228.112.130', '2019-09-29 15:53:05', '26', 'GET', null, null);
INSERT INTO `t_web_logs` VALUES ('288', 'admin', '管理员', '用户信息', 'http://47.103.154.36:8888/rest/user/info', '116.228.112.130', '2019-09-29 15:53:21', '7', 'GET', null, null);

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
