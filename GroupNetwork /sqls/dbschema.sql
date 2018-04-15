DROP SCHEMA IF EXISTS group_talk_db;

CREATE SCHEMA group_talk_db;

CREATE TABLE `group_talk_db`.`groups` (
  `group_id` MEDIUMINT NOT NULL AUTO_INCREMENT,
  `group_name` VARCHAR(45) NULL,
  `row_updated_on` timestamp default CURRENT_TIMESTAMP,
  PRIMARY KEY (`group_id`),
  UNIQUE KEY `UNIQ_USER_NAME` (`group_id`));

INSERT INTO `group_talk_db`.`groups` (`group_id`, `group_name`) VALUES (1, 'Group 1');
INSERT INTO `group_talk_db`.`groups` (`group_id`, `group_name`) VALUES (2, 'Group 2');
INSERT INTO `group_talk_db`.`groups` (`group_id`, `group_name`) VALUES (3, 'Group 3');
INSERT INTO `group_talk_db`.`groups` (`group_id`, `group_name`) VALUES (4, 'Group 4');


CREATE TABLE `group_talk_db`.`users` (
  `phone_number` VARCHAR(12) NOT NULL,  
  `user_name` VARCHAR(45) NOT NULL,
  `gcm_token` VARCHAR(1000) NULL,
  `row_updated_on` timestamp default CURRENT_TIMESTAMP,
  PRIMARY KEY (`phone_number`),
  UNIQUE KEY `UNIQ_USER_NAME` (`phone_number`));

INSERT INTO `group_talk_db`.`users` (`phone_number`, `user_name`) VALUES ('6501234567', 'Poonam');
INSERT INTO `group_talk_db`.`users` (`phone_number`, `user_name`) VALUES ('6501236666', 'Siddhi');
INSERT INTO `group_talk_db`.`users` (`phone_number`, `user_name`) VALUES ('6501237777', 'Poornima');
INSERT INTO `group_talk_db`.`users` (`phone_number`, `user_name`) VALUES ('6501238888', 'Aparajita');


CREATE TABLE `group_talk_db`.`user_groups` (
  `phone_number` VARCHAR(12) NOT NULL, 
  `user_name` VARCHAR(45) NOT NULL,
  `group_id` INT NOT NULL,
  `group_name` VARCHAR(45) NULL,
  `is_owner` VARCHAR(1) NULL,
  `accepted_on` timestamp NULL,
  `denied_on` timestamp NULL,
  `left_on` timestamp NULL,
  `row_updated_on` timestamp default CURRENT_TIMESTAMP,
  PRIMARY KEY (`phone_number`,`group_id`));

INSERT INTO `group_talk_db`.`user_groups` (`phone_number`, `user_name`, `group_id`,`group_name`,`is_owner`) VALUES ('6501234567', 'Poonam', 1, 'Group 1','Y');
INSERT INTO `group_talk_db`.`user_groups` (`phone_number`, `user_name`, `group_id`,`group_name`,`is_owner`) VALUES ('6501236666', 'Siddhi', 1, 'Group 1', '');
INSERT INTO `group_talk_db`.`user_groups` (`phone_number`, `user_name`, `group_id`,`group_name`,`is_owner`) VALUES ('6501237777', 'Poornima', 1, 'Group 1', '');
INSERT INTO `group_talk_db`.`user_groups` (`phone_number`, `user_name`, `group_id`,`group_name`,`is_owner`) VALUES ('6501238888', 'Aparajita', 1, 'Group 1', '');

INSERT INTO `group_talk_db`.`user_groups` (`phone_number`, `user_name`, `group_id`,`group_name`,`is_owner`) VALUES ('6501234567', 'Poonam', 2, 'Group 2', '');
INSERT INTO `group_talk_db`.`user_groups` (`phone_number`, `user_name`, `group_id`,`group_name`,`is_owner`) VALUES ('6501236666', 'Siddhi', 2, 'Group 2', 'Y');
INSERT INTO `group_talk_db`.`user_groups` (`phone_number`, `user_name`, `group_id`,`group_name`,`is_owner`) VALUES ('6501237777', 'Poornima', 2, 'Group 2', '');


INSERT INTO `group_talk_db`.`user_groups` (`phone_number`, `user_name`, `group_id`,`group_name`,`is_owner`) VALUES ('6501234567', 'Poonam', 3, 'Group 3', 'Y');
INSERT INTO `group_talk_db`.`user_groups` (`phone_number`, `user_name`, `group_id`,`group_name`,`is_owner`) VALUES ('6501236666', 'Siddhi', 3, 'Group 3', '');
INSERT INTO `group_talk_db`.`user_groups` (`phone_number`, `user_name`, `group_id`,`group_name`,`is_owner`) VALUES ('6501238888', 'Aparajita', 3, 'Group 3', '');


CREATE TABLE `group_talk_db`.`locations` (
  `phone_number` VARCHAR(12) NOT NULL,
  `latitude` VARCHAR(20) NOT NULL,
  `longitude` VARCHAR(20) NOT NULL,
  `row_updated_on` timestamp default CURRENT_TIMESTAMP,
  PRIMARY KEY (`phone_number`));


INSERT INTO `group_talk_db`.`locations` (`phone_number`, `latitude`, `longitude`) VALUES ('6501234567', '37.3596862', '-121.8863');
INSERT INTO `group_talk_db`.`locations` (`phone_number`, `latitude`, `longitude`) VALUES ('6501236666', '37.3383', '-121.8863');
INSERT INTO `group_talk_db`.`locations` (`phone_number`, `latitude`, `longitude`) VALUES ('6501237777', '37.3380', '-121.8863');
INSERT INTO `group_talk_db`.`locations` (`phone_number`, `latitude`, `longitude`) VALUES ('6501238888', '37.3381', '-121.8863');
