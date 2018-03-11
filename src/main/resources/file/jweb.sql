/*
Navicat MySQL Data Transfer

Source Server         : qdm199527548_db
Source Server Version : 50173
Source Host           : qdm199527548.my3w.com:3306
Source Database       : qdm199527548_db

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2018-03-11 22:06:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_organization
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE `sys_organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '组织ID',
  `organization` varchar(100) DEFAULT NULL COMMENT '组织代号',
  `name` varchar(100) DEFAULT NULL COMMENT '组织名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父组织',
  `parent_ids` varchar(100) DEFAULT NULL COMMENT '所有父组织',
  `available` tinyint(1) DEFAULT '0' COMMENT '有效',
  PRIMARY KEY (`id`),
  KEY `idx_sys_organization_parent_id` (`parent_id`),
  KEY `idx_sys_organization_parent_ids` (`parent_ids`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_organization
-- ----------------------------
INSERT INTO `sys_organization` VALUES ('1', 'head', '总公司', '0', '0/', '0');
INSERT INTO `sys_organization` VALUES ('2', 'branch1', '分公司1', '1', '0/1/', '0');
INSERT INTO `sys_organization` VALUES ('3', 'branch2', '分公司2', '1', '0/1/', '0');
INSERT INTO `sys_organization` VALUES ('4', 'branch11', '分公司11', '2', '0/1/2/', '0');

-- ----------------------------
-- Table structure for sys_organization_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization_role`;
CREATE TABLE `sys_organization_role` (
  `organization_id` bigint(20) DEFAULT NULL COMMENT '组织ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_organization_role
-- ----------------------------
INSERT INTO `sys_organization_role` VALUES ('1', '1');

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '资源ID',
  `name` varchar(100) DEFAULT NULL COMMENT '资源名称',
  `type` varchar(50) DEFAULT NULL COMMENT '资源类型（menu，button）',
  `url` varchar(200) DEFAULT NULL COMMENT '资源链接',
  `description` varchar(200) DEFAULT '' COMMENT '页面描述',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父资源',
  `parent_ids` varchar(100) DEFAULT NULL COMMENT '所有父资源',
  `permission` varchar(100) DEFAULT NULL COMMENT '资源许可代码',
  `icon` varchar(100) DEFAULT '' COMMENT '图标代码',
  `available` tinyint(1) DEFAULT '0' COMMENT '有效',
  PRIMARY KEY (`id`),
  KEY `idx_sys_resource_parent_id` (`parent_id`),
  KEY `idx_sys_resource_parent_ids` (`parent_ids`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('1', '资源', 'menu', '', '', '0', '0/', '', 'fa fa-gear', '0');
INSERT INTO `sys_resource` VALUES ('11', '组织机构管理', 'menu', 'organization', '机构管理', '1', '0/1/', 'organization:page', '', '0');
INSERT INTO `sys_resource` VALUES ('12', '组织机构新增', 'button', '', '', '11', '0/1/11/', 'organization:create', '', '0');
INSERT INTO `sys_resource` VALUES ('13', '组织机构修改', 'button', '', '', '11', '0/1/11/', 'organization:update', '', '0');
INSERT INTO `sys_resource` VALUES ('14', '组织机构删除', 'button', '', '', '11', '0/1/11/', 'organization:delete', '', '0');
INSERT INTO `sys_resource` VALUES ('15', '组织机构查看', 'button', '', '', '11', '0/1/11/', 'organization:view', '', '0');
INSERT INTO `sys_resource` VALUES ('21', '用户管理', 'menu', 'user', '用户管理', '1', '0/1/', 'user:page', '', '0');
INSERT INTO `sys_resource` VALUES ('22', '用户新增', 'button', '', '', '21', '0/1/21/', 'user:create', '', '0');
INSERT INTO `sys_resource` VALUES ('23', '用户修改', 'button', '', '', '21', '0/1/21/', 'user:update', '', '0');
INSERT INTO `sys_resource` VALUES ('24', '用户删除', 'button', '', '', '21', '0/1/21/', 'user:delete', '', '0');
INSERT INTO `sys_resource` VALUES ('25', '用户查看', 'button', '', '', '21', '0/1/21/', 'user:view', '', '0');
INSERT INTO `sys_resource` VALUES ('31', '资源管理', 'menu', 'resource', '资源管理', '1', '0/1/', 'resource:page', '', '0');
INSERT INTO `sys_resource` VALUES ('32', '资源新增', 'button', '', '', '31', '0/1/31/', 'resource:create', '', '0');
INSERT INTO `sys_resource` VALUES ('33', '资源修改', 'button', '', '', '31', '0/1/31/', 'resource:update', '', '0');
INSERT INTO `sys_resource` VALUES ('34', '资源删除', 'button', '', '', '31', '0/1/31/', 'resource:delete', '', '0');
INSERT INTO `sys_resource` VALUES ('35', '资源查看', 'button', '', '', '31', '0/1/31/', 'resource:view', '', '0');
INSERT INTO `sys_resource` VALUES ('41', '角色管理', 'menu', 'role', '角色管理', '1', '0/1/', 'role:page', '', '0');
INSERT INTO `sys_resource` VALUES ('42', '角色新增', 'button', '', '', '41', '0/1/41/', 'role:create', '', '0');
INSERT INTO `sys_resource` VALUES ('43', '角色修改', 'button', '', '', '41', '0/1/41/', 'role:update', '', '0');
INSERT INTO `sys_resource` VALUES ('44', '角色删除', 'button', '', '', '41', '0/1/41/', 'role:delete', '', '0');
INSERT INTO `sys_resource` VALUES ('45', '角色查看', 'button', '', '', '41', '0/1/41/', 'role:view', '', '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role` varchar(100) DEFAULT NULL COMMENT '角色代号',
  `name` varchar(100) DEFAULT NULL COMMENT '角色名',
  `available` tinyint(1) DEFAULT '0' COMMENT '有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', '超级管理员', '0');

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `resource_id` bigint(20) DEFAULT NULL COMMENT '资源ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------
INSERT INTO `sys_role_resource` VALUES ('1', '1');
INSERT INTO `sys_role_resource` VALUES ('1', '11');
INSERT INTO `sys_role_resource` VALUES ('1', '12');
INSERT INTO `sys_role_resource` VALUES ('1', '13');
INSERT INTO `sys_role_resource` VALUES ('1', '14');
INSERT INTO `sys_role_resource` VALUES ('1', '15');
INSERT INTO `sys_role_resource` VALUES ('1', '21');
INSERT INTO `sys_role_resource` VALUES ('1', '22');
INSERT INTO `sys_role_resource` VALUES ('1', '23');
INSERT INTO `sys_role_resource` VALUES ('1', '24');
INSERT INTO `sys_role_resource` VALUES ('1', '25');
INSERT INTO `sys_role_resource` VALUES ('1', '31');
INSERT INTO `sys_role_resource` VALUES ('1', '32');
INSERT INTO `sys_role_resource` VALUES ('1', '33');
INSERT INTO `sys_role_resource` VALUES ('1', '34');
INSERT INTO `sys_role_resource` VALUES ('1', '35');
INSERT INTO `sys_role_resource` VALUES ('1', '41');
INSERT INTO `sys_role_resource` VALUES ('1', '42');
INSERT INTO `sys_role_resource` VALUES ('1', '43');
INSERT INTO `sys_role_resource` VALUES ('1', '44');
INSERT INTO `sys_role_resource` VALUES ('1', '45');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `organization_id` bigint(20) DEFAULT NULL COMMENT '组织ID',
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `username` varchar(100) DEFAULT NULL COMMENT '用户登录名',
  `password` varchar(100) DEFAULT NULL COMMENT '用户登录密码',
  `salt` varchar(100) DEFAULT NULL COMMENT '盐',
  `locked` tinyint(1) DEFAULT '0' COMMENT '锁定',
  `available` tinyint(1) DEFAULT '0' COMMENT '有效',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_user_username` (`username`),
  KEY `idx_sys_user_organization_id` (`organization_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统用户信息';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '1', 'admin', 'admin', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', '0', '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
