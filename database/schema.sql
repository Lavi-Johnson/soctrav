#MySql Database
CREATE TABLE `locations` (
  `location_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `location_name` varchar(50) NOT NULL DEFAULT '',
  `status` tinyint(1) NOT NULL DEFAULT '2',
  `city` varchar(50) NOT NULL,
  `province_name` varchar(50) NOT NULL DEFAULT '',
  `country` varchar(2) NOT NULL,
  `zip_code` varchar(15) DEFAULT NULL,
  `unjoined_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `joined_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `zipcode` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`location_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `messages` (
  `message_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `room_id` bigint(20) NOT NULL,
  `update_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_id` bigint(20) unsigned NOT NULL,
  `message` text NOT NULL,
  PRIMARY KEY (`message_id`),
  KEY `timestamp` (`update_dt`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `room_user` (
  `room_user_id` bigint(20) NOT NULL,
  `room_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '2',
  `update_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`room_user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `rooms` (
  `room_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `room_name` varchar(50) NOT NULL DEFAULT '',
  `location_id` bigint(20) NOT NULL,
  `update_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `message_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`room_id`),
  KEY `timestamp` (`update_dt`),
  KEY `FK_7ok36d9kn49gq160scmsb2v1o` (`location_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `status` (
  `status_id` bigint(20) unsigned NOT NULL,
  `description` varchar(39) NOT NULL DEFAULT '',
  PRIMARY KEY (`status_id`),
  UNIQUE KEY `description` (`description`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `user_type` (
  `user_type_id` bigint(20) unsigned NOT NULL,
  `description` varchar(39) NOT NULL DEFAULT '',
  PRIMARY KEY (`user_type_id`),
  UNIQUE KEY `description` (`description`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `UserAuthority` (
  `authority` varchar(255) NOT NULL,
  `user_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_user_id`,`authority`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `usermeta` (
  `user_meta_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `meta_key` varchar(255) DEFAULT NULL,
  `meta_value` longtext,
  PRIMARY KEY (`user_meta_id`),
  KEY `user_id` (`user_id`),
  KEY `meta_key` (`meta_key`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `users` (
  `user_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `status` tinyint(1) NOT NULL DEFAULT '2',
  `update_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_pass` varchar(64) NOT NULL DEFAULT '',
  `user_name` varchar(50) NOT NULL DEFAULT '',
  `first_name` varchar(50) NOT NULL DEFAULT '',
  `middle_name` varchar(50) NOT NULL DEFAULT '',
  `last_name` varchar(50) NOT NULL DEFAULT '',
  `user_email` varchar(100) NOT NULL DEFAULT '',
  `user_registered` datetime NOT NULL,
  `user_activation_key` varchar(60) NOT NULL DEFAULT '',
  `alias` varchar(255) NOT NULL DEFAULT '',
  `user_type_id` bigint(20) NOT NULL,
  `accountEnabled` bit(1) NOT NULL,
  `accountExpired` bit(1) NOT NULL,
  `accountLocked` bit(1) NOT NULL,
  `credentialsExpired` bit(1) NOT NULL,
  `expires` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO `users` (`user_id`, `status`, `update_dt`, `user_pass`, `user_name`, `first_name`, `middle_name`, `last_name`, `user_email`, `user_registered`, `user_activation_key`, `alias`, `user_type_id`, `accountEnabled`, `accountExpired`, `accountLocked`, `credentialsExpired`, `expires`)
VALUES
	(1, 1, '2017-12-17 20:28:34', '$2a$10$HYlZycWQMxuOGCNrSslJQ.C3OEYc6bVoq2fTpDjFKb4FgpxreAP0G', 'ypeng', 'yanni', '', 'peng', 'yanni.peng@socialtraveler.com', '2017-12-17 19:51:48', 'youractivated', 'yannmulaa', 1, 0, 0, 0, 0, 0);
INSERT INTO `users` (`user_id`, `status`, `update_dt`, `user_pass`, `user_name`, `first_name`, `middle_name`, `last_name`, `user_email`, `user_registered`, `user_activation_key`, `alias`, `user_type_id`, `accountEnabled`, `accountExpired`, `accountLocked`, `credentialsExpired`, `expires`)
VALUES
	(2, 1, '2017-12-17 20:28:34', '$2a$10$HYlZycWQMxuOGCNrSslJQ.C3OEYc6bVoq2fTpDjFKb4FgpxreAP0G', 'usr_random', 'rick', '', 'flair', 'user@socialtraveler.com', '2017-12-17 19:51:48', 'youractivated', 'randomuser', 3, 0, 0, 0, 0, 0);

INSERT INTO `user_type` (`user_type_id`, `description`)
VALUES
	(1, 'SUPER_ADMIN'),
	(2, 'ADMIN'),
	(3, 'USER');


