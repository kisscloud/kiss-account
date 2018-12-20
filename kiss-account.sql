# ************************************************************
# Sequel Pro SQL dump
# Version 4529
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: rm-j6ca5pt36q4h487712o.mysql.rds.aliyuncs.com (MySQL 5.7.18-log)
# Database: kiss-account
# Generation Time: 2018-12-18 08:16:49 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table accountgroups
# ------------------------------------------------------------

DROP TABLE IF EXISTS `accountgroups`;

CREATE TABLE `accountgroups` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '',
  `parentId` int(11) NOT NULL,
  `level` varchar(255) NOT NULL DEFAULT '',
  `seq` int(11) NOT NULL,
  `remark` varchar(200) DEFAULT '',
  `operatorId` int(20) NOT NULL,
  `operatorName` varchar(20) NOT NULL DEFAULT '',
  `operatorIp` varchar(20) DEFAULT '',
  `createdAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table accountroles
# ------------------------------------------------------------

DROP TABLE IF EXISTS `accountroles`;

CREATE TABLE `accountroles` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `roleId` int(11) DEFAULT NULL,
  `accountId` int(11) DEFAULT NULL,
  `operatorId` int(11) DEFAULT NULL,
  `operatorName` varchar(20) DEFAULT NULL,
  `operatorIp` varchar(20) DEFAULT NULL,
  `createdAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `roleId` (`roleId`,`accountId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table accounts
# ------------------------------------------------------------

DROP TABLE IF EXISTS `accounts`;

CREATE TABLE `accounts` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `password` varchar(128) DEFAULT NULL,
  `salt` varchar(128) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `groupId` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `operatorName` varchar(20) DEFAULT NULL,
  `operatorId` int(11) DEFAULT NULL,
  `operatorIp` varchar(20) DEFAULT NULL,
  `createdAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `mobile` (`mobile`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table authorizationtarget
# ------------------------------------------------------------

DROP TABLE IF EXISTS `authorizationtarget`;

CREATE TABLE `authorizationtarget` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `clientId` int(11) DEFAULT NULL,
  `ip` varchar(16) DEFAULT NULL,
  `remark` text,
  `createdAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table clientmodules
# ------------------------------------------------------------

DROP TABLE IF EXISTS `clientmodules`;

CREATE TABLE `clientmodules` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `clientId` int(11) DEFAULT NULL,
  `moduleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table clients
# ------------------------------------------------------------

DROP TABLE IF EXISTS `clients`;

CREATE TABLE `clients` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `clientName` varchar(60) DEFAULT NULL,
  `clientId` varchar(255) DEFAULT NULL,
  `clientSecret` varchar(255) DEFAULT NULL,
  `lastAccessAt` timestamp NULL DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `operatorId` int(11) DEFAULT NULL,
  `operatorName` varchar(64) DEFAULT NULL,
  `operatorIp` varchar(24) DEFAULT NULL,
  `createdAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table dbenums
# ------------------------------------------------------------

DROP TABLE IF EXISTS `dbenums`;

CREATE TABLE `dbenums` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `key` varchar(60) DEFAULT NULL COMMENT '状态标识',
  `option` int(11) DEFAULT NULL COMMENT '状态码',
  `language` varchar(11) DEFAULT NULL COMMENT '语言',
  `value` varchar(32) DEFAULT NULL COMMENT '状态值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table logs
# ------------------------------------------------------------

DROP TABLE IF EXISTS `logs`;

CREATE TABLE `logs` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL,
  `targetId` int(11) DEFAULT NULL,
  `oldValue` text,
  `newValue` text,
  `status` int(11) DEFAULT NULL,
  `operatorId` int(11) DEFAULT NULL,
  `operatorName` varchar(20) DEFAULT NULL,
  `operatorIp` varchar(20) DEFAULT NULL,
  `createdAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table operationlogs
# ------------------------------------------------------------

DROP TABLE IF EXISTS `operationlogs`;

CREATE TABLE `operationlogs` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `operatorId` int(11) DEFAULT NULL,
  `operatorName` varchar(50) DEFAULT NULL,
  `operatorIp` varchar(20) DEFAULT '',
  `targetType` int(11) DEFAULT NULL,
  `targetId` int(11) DEFAULT NULL,
  `beforeValue` blob,
  `afterValue` blob,
  `type` int(11) DEFAULT NULL,
  `recoveredAt` timestamp NULL DEFAULT NULL,
  `createdAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table permissionmodules
# ------------------------------------------------------------

DROP TABLE IF EXISTS `permissionmodules`;

CREATE TABLE `permissionmodules` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `permissions` int(11) DEFAULT NULL,
  `seq` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `operatorId` int(11) DEFAULT NULL,
  `operatorName` varchar(255) DEFAULT NULL,
  `operatorIp` varchar(20) DEFAULT NULL,
  `createdAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table permissions
# ------------------------------------------------------------

DROP TABLE IF EXISTS `permissions`;

CREATE TABLE `permissions` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `moduleId` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `code` varchar(200) DEFAULT NULL,
  `limitFields` text,
  `remark` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `seq` int(11) DEFAULT NULL,
  `operatorId` int(11) DEFAULT NULL,
  `operatorName` varchar(20) DEFAULT NULL,
  `operatorIp` varchar(20) DEFAULT NULL,
  `createdAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table rolepermissions
# ------------------------------------------------------------

DROP TABLE IF EXISTS `rolepermissions`;

CREATE TABLE `rolepermissions` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `roleId` int(11) DEFAULT NULL,
  `permissionId` int(11) DEFAULT NULL,
  `limitScope` text,
  `limitString` tinytext,
  `limitDescription` text,
  `operatorId` int(11) DEFAULT NULL,
  `operatorName` varchar(20) DEFAULT NULL,
  `operatorIp` varchar(20) DEFAULT NULL,
  `createdAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `roleId` (`roleId`,`permissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table roles
# ------------------------------------------------------------

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `operatorId` int(11) DEFAULT NULL,
  `operatorName` varchar(20) DEFAULT NULL,
  `operatorIp` varchar(20) DEFAULT NULL,
  `createdAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table webhooks
# ------------------------------------------------------------

DROP TABLE IF EXISTS `webhooks`;

CREATE TABLE `webhooks` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `clientId` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `operatorId` int(11) DEFAULT NULL,
  `operatorName` varchar(32) DEFAULT NULL,
  `createdAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
