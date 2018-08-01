CREATE TABLE IF NOT EXISTS `test`.`rbac_user_role` (
  `id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  `role_id` INT(11) NOT NULL,
  `create_date` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `test`.`rbac_user` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NULL,
  `is_admin` TINYINT(1) NOT NULL,
  `status` TINYINT(1) NOT NULL,
  `create_date` VARCHAR(45) NOT NULL,
  `update_date` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `test`.`rbac_role` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `status` TINYINT(1) NOT NULL,
  `create_date` VARCHAR(45) NOT NULL,
  `update_date` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `test`.`rbac_access` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `urls` VARCHAR(100) NULL,
  `status` TINYINT(1) NOT NULL,
  `create_date` VARCHAR(45) NOT NULL,
  `update_date` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `test`.`rbac_role_access` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `role_id` INT(11) NOT NULL,
  `access_id` INT(11) NOT NULL,
  `create_date` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB

CREATE TABLE `orm_country` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `name_en` varchar(100) NOT NULL,
  `abbreviation` varchar(4) NOT NULL,
  `three_abbreviation` varchar(6) NOT NULL,
  `create_date` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=250 DEFAULT CHARSET=utf8;