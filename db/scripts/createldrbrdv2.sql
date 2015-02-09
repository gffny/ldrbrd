-- -----------------------------------------------------
-- Schema ldrbrdv2
-- -----------------------------------------------------
-- CREATE SCHEMA IF NOT EXISTS `ldrbrdv2` DEFAULT CHARACTER SET latin1 ;
-- SHOW WARNINGS;
-- USE `ldrbrdv2` ;

-- CREATE USER 'ldrbrd'@'localhost' IDENTIFIED BY 'ldrbrdv2';
-- CREATE USER 'application'@'localhost' IDENTIFIED BY 'ldrbrdv2';
-- GRANT ALL PRIVILEGES ON * . * TO 'ldrbrd'@'*';
-- GRANT ALL PRIVILEGES ON * . * TO 'application'@'*';

-- -----------------------------------------------------
-- Table `ldrbrdv2`.`ldrbrd_golfer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ldrbrdv2`.`ldrbrd_golfer` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `ldrbrdv2`.`ldrbrd_golfer` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `profile_handle` VARCHAR(45) NOT NULL,
  `handicap` INT(11) NULL DEFAULT '18',
  `email_address` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;
CREATE UNIQUE INDEX `profile_name_UNIQUE` ON `ldrbrdv2`.`ldrbrd_golfer` (`profile_handle` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `ldrbrdv2`.`ldrbrd_society`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ldrbrdv2`.`ldrbrd_society` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `ldrbrdv2`.`ldrbrd_society` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `president_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `scty_glfr_fk`
    FOREIGN KEY (`president_id`)
    REFERENCES `ldrbrdv2`.`ldrbrd_golfer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `ldrbrdv2`.`ldrbrd_society` (`id` ASC);

SHOW WARNINGS;
CREATE UNIQUE INDEX `president_id_UNIQUE` ON `ldrbrdv2`.`ldrbrd_society` (`president_id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `ldrbrdv2`.`ldrbrd_competition`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ldrbrdv2`.`ldrbrd_competition` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `ldrbrdv2`.`ldrbrd_competition` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `competition_name` VARCHAR(45) NOT NULL,
  `start_date` DATETIME NULL DEFAULT NULL,
  `competitor_limit` INT(11) NULL DEFAULT NULL,
  `organising_society` INT(11) NULL DEFAULT NULL,
  `organising_golfer` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `cmpttn_glfr_fk`
    FOREIGN KEY (`organising_golfer`)
    REFERENCES `ldrbrdv2`.`ldrbrd_golfer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `cmpttn_scty_fk`
    FOREIGN KEY (`organising_society`)
    REFERENCES `ldrbrdv2`.`ldrbrd_society` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;
CREATE INDEX `cmpttn_scty_fk_idx` ON `ldrbrdv2`.`ldrbrd_competition` (`organising_society` ASC);

SHOW WARNINGS;
CREATE INDEX `cmpttn_glfr_fk_idx` ON `ldrbrdv2`.`ldrbrd_competition` (`organising_golfer` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `ldrbrdv2`.`ldrbrd_competition_entry`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ldrbrdv2`.`ldrbrd_competition_entry` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `ldrbrdv2`.`ldrbrd_competition_entry` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `competition_id` INT(11) NOT NULL,
  `golfer_id` INT(11) NOT NULL,
  `entry_date` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `cmptnn_entry_cmpttn_fk`
    FOREIGN KEY (`competition_id`)
    REFERENCES `ldrbrdv2`.`ldrbrd_competition` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `cmptnn_entry_glfr_fk`
    FOREIGN KEY (`golfer_id`)
    REFERENCES `ldrbrdv2`.`ldrbrd_golfer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;
CREATE INDEX `cmptnn_entry_cmpttn_fk_idx` ON `ldrbrdv2`.`ldrbrd_competition_entry` (`competition_id` ASC);

SHOW WARNINGS;
CREATE INDEX `cmptnn_entry_glfr_fk_idx` ON `ldrbrdv2`.`ldrbrd_competition_entry` (`golfer_id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `ldrbrdv2`.`ldrbrd_scoring_format`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ldrbrdv2`.`ldrbrd_scoring_format` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `ldrbrdv2`.`ldrbrd_scoring_format` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `format_name` VARCHAR(45) NOT NULL,
  `scoring_class` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `ldrbrdv2`.`ldrbrd_scoring_format` (`id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `ldrbrdv2`.`ldrbrd_competition_round`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ldrbrdv2`.`ldrbrd_competition_round` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `ldrbrdv2`.`ldrbrd_competition_round` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `competition_id` INT(11) NOT NULL,
  `round_number` INT(11) NOT NULL,
  `start_date` DATE NULL DEFAULT NULL,
  `course_did` VARCHAR(45) NOT NULL,
  `scoring_format_id` INT(11) NULL DEFAULT NULL,
  `initial_tee_time` TIME NULL DEFAULT NULL,
  `is_complete` TINYINT(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  CONSTRAINT `cmpttn_rnd_cmpttn`
    FOREIGN KEY (`competition_id`)
    REFERENCES `ldrbrdv2`.`ldrbrd_competition` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `cmpttn_rnd_scrng_frmt`
    FOREIGN KEY (`scoring_format_id`)
    REFERENCES `ldrbrdv2`.`ldrbrd_scoring_format` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;
CREATE INDEX `cmptnnrnd_cmpttn_fk_idx` ON `ldrbrdv2`.`ldrbrd_competition_round` (`competition_id` ASC);

SHOW WARNINGS;
CREATE INDEX `cmpttn_rnd_scrng_frmt_idx` ON `ldrbrdv2`.`ldrbrd_competition_round` (`scoring_format_id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `ldrbrdv2`.`ldrbrd_scorecard`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ldrbrdv2`.`ldrbrd_scorecard` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `ldrbrdv2`.`ldrbrd_scorecard` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `course_did` VARCHAR(45) NOT NULL,
  `golfer_id` INT(11) NOT NULL,
  `round_date` DATETIME NOT NULL,
  `conditions` VARCHAR(45) NULL DEFAULT NULL,
  `handicap` VARCHAR(45) NULL DEFAULT NULL,
  `scorecard_notes` VARCHAR(45) NULL DEFAULT NULL,
  `is_active` TINYINT(1) NOT NULL DEFAULT '1',
  `scorecard_signature` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `scrcrd_glf_fk`
    FOREIGN KEY (`golfer_id`)
    REFERENCES `ldrbrdv2`.`ldrbrd_golfer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 63
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;
CREATE INDEX `scrcrd_glf_fk_idx` ON `ldrbrdv2`.`ldrbrd_scorecard` (`golfer_id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `ldrbrdv2`.`ldrbrd_competition_round_score`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ldrbrdv2`.`ldrbrd_competition_round_score` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `ldrbrdv2`.`ldrbrd_competition_round_score` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `competition_entry_id` INT(11) NOT NULL,
  `scorecard_id` INT(11) NOT NULL,
  `competition_round_id` INT(11) NOT NULL,
  `golfer_signature` VARCHAR(45) NULL DEFAULT NULL,
  `scorer_signature` VARCHAR(45) NULL DEFAULT NULL,
  `is_complete` TINYINT(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  CONSTRAINT `competition_round`
    FOREIGN KEY (`competition_round_id`)
    REFERENCES `ldrbrdv2`.`ldrbrd_competition_round` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `scorecard_id`
    FOREIGN KEY (`scorecard_id`)
    REFERENCES `ldrbrdv2`.`ldrbrd_scorecard` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 51
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;
CREATE UNIQUE INDEX `scorecard_id_UNIQUE` ON `ldrbrdv2`.`ldrbrd_competition_round_score` (`scorecard_id` ASC);

SHOW WARNINGS;
CREATE INDEX `scorecard_id_idx` ON `ldrbrdv2`.`ldrbrd_competition_round_score` (`scorecard_id` ASC);

SHOW WARNINGS;
CREATE INDEX `competition_round_idx` ON `ldrbrdv2`.`ldrbrd_competition_round_score` (`competition_round_id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `ldrbrdv2`.`ldrbrd_hole_score`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ldrbrdv2`.`ldrbrd_hole_score` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `ldrbrdv2`.`ldrbrd_hole_score` (
  `scorecard_id` INT(11) NOT NULL,
  `hole_number` INT(11) NOT NULL,
  `score` INT(11) NOT NULL,
  `hole_did` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`scorecard_id`, `hole_number`),
  CONSTRAINT `hl_scr_scrcrd_fk`
    FOREIGN KEY (`scorecard_id`)
    REFERENCES `ldrbrdv2`.`ldrbrd_scorecard` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `ldrbrdv2`.`ldrbrd_society_membership`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ldrbrdv2`.`ldrbrd_society_membership` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `ldrbrdv2`.`ldrbrd_society_membership` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `golfer_id` INT(11) NOT NULL,
  `society_id` INT(11) NOT NULL,
  `start_date` DATE NULL DEFAULT NULL,
  `end_date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `scty_mmbrshp_glfr`
    FOREIGN KEY (`golfer_id`)
    REFERENCES `ldrbrdv2`.`ldrbrd_golfer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `scty_mmbrshp_scty`
    FOREIGN KEY (`society_id`)
    REFERENCES `ldrbrdv2`.`ldrbrd_society` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;
CREATE UNIQUE INDEX `id_UNIQUE` ON `ldrbrdv2`.`ldrbrd_society_membership` (`id` ASC);

SHOW WARNINGS;
CREATE INDEX `scty_mmbrshp_scty_idx` ON `ldrbrdv2`.`ldrbrd_society_membership` (`society_id` ASC);

SHOW WARNINGS;
CREATE INDEX `scty_mmbrshp_glfr_idx` ON `ldrbrdv2`.`ldrbrd_society_membership` (`golfer_id` ASC);

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
