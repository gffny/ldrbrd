SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `ldrbrd` ;
CREATE SCHEMA IF NOT EXISTS `ldrbrd` DEFAULT CHARACTER SET latin1 ;
USE `ldrbrd` ;

-- -----------------------------------------------------
-- Table `ldrbrd`.`t_club`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ldrbrd`.`t_club` ;

CREATE  TABLE IF NOT EXISTS `ldrbrd`.`t_club` (
  `id` VARCHAR(45) NOT NULL ,
  `clb_nm` VARCHAR(100) NOT NULL ,
  `address` VARCHAR(300) NULL DEFAULT NULL ,
  `mngr_nm` VARCHAR(100) NULL DEFAULT NULL ,
  `drs_cd_plcy` VARCHAR(200) NULL DEFAULT NULL ,
  `grn_kpr_nm` VARCHAR(100) NULL DEFAULT NULL ,
  `pro_glfr_nm` VARCHAR(100) NULL DEFAULT NULL ,
  `vrsn` INT(11) NULL DEFAULT NULL ,
  `crtdby` VARCHAR(45) NULL DEFAULT NULL ,
  `updtdby` VARCHAR(45) NULL DEFAULT NULL ,
  `crtddt` DATETIME NULL DEFAULT NULL ,
  `updtddt` DATETIME NULL DEFAULT NULL ,
  `skpcrtddt` TINYINT(1) NULL DEFAULT NULL ,
  `syncvrsnid` INT(11) NULL DEFAULT NULL ,
  `obslt` TINYINT(1) NULL DEFAULT NULL ,
  `archv` TINYINT(1) NULL DEFAULT NULL ,
  `dlt` TINYINT(1) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `clb_id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `clb_nm_UNIQUE` (`clb_nm` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ldrbrd`.`t_course`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ldrbrd`.`t_course` ;

CREATE  TABLE IF NOT EXISTS `ldrbrd`.`t_course` (
  `id` VARCHAR(45) NOT NULL ,
  `crs_nm` VARCHAR(100) NOT NULL ,
  `clb_id` VARCHAR(45) NOT NULL ,
  `t_clr` INT(11) NULL DEFAULT NULL COMMENT 'Ordinal value for enum in code' ,
  `slp_indx` DOUBLE NULL DEFAULT NULL ,
  `par` INT(11) NULL DEFAULT NULL ,
  `crs_img_id` VARCHAR(45) NULL DEFAULT NULL ,
  `vrsn` INT(11) NULL DEFAULT NULL ,
  `crtdby` VARCHAR(45) NULL DEFAULT NULL ,
  `updtdby` VARCHAR(45) NULL DEFAULT NULL ,
  `crtddt` DATETIME NULL DEFAULT NULL ,
  `updtddt` DATETIME NULL DEFAULT NULL ,
  `skpcrtddt` TINYINT(1) NULL DEFAULT NULL ,
  `syncvrsnid` INT(11) NULL DEFAULT NULL ,
  `obslt` TINYINT(1) NULL DEFAULT NULL ,
  `archv` TINYINT(1) NULL DEFAULT NULL ,
  `dlt` TINYINT(1) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `crs_id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `crs_nm_UNIQUE` (`crs_nm` ASC) ,
  INDEX `fk_t_course_t_club` (`clb_id` ASC) ,
  INDEX `crs_clb_fk` (`clb_id` ASC) ,
  CONSTRAINT `crs_clb_fk`
    FOREIGN KEY (`clb_id` )
    REFERENCES `ldrbrd`.`t_club` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ldrbrd`.`t_golfer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ldrbrd`.`t_golfer` ;

CREATE  TABLE IF NOT EXISTS `ldrbrd`.`t_golfer` (
  `id` VARCHAR(45) NOT NULL ,
  `frst_nm` VARCHAR(45) NULL ,
  `lst_nm` VARCHAR(45) NULL ,
  `eml_addrss` VARCHAR(45) NULL ,
  `psswrd` VARCHAR(45) NULL ,
  `dsply_nm` VARCHAR(45) NULL ,
  `is_nbld` TINYINT(1) NULL ,
  `lst_lgn_dt` TIMESTAMP NULL ,
  `fld_lgn_attmpts` INT(11) NULL ,
  `hndcp` INT(11) NULL DEFAULT NULL ,
  `glfr_img_ref` VARCHAR(45) NULL DEFAULT NULL ,
  `hnddnss` INT(11) NULL DEFAULT NULL ,
  `vrsn` INT(11) NULL DEFAULT NULL ,
  `crtdby` VARCHAR(45) NULL DEFAULT NULL ,
  `updtdby` VARCHAR(45) NULL DEFAULT NULL ,
  `crtddt` DATETIME NULL DEFAULT NULL ,
  `updtddt` DATETIME NULL DEFAULT NULL ,
  `skpcrtddt` TINYINT(1) NULL DEFAULT NULL ,
  `syncvrsnid` INT(11) NULL DEFAULT NULL ,
  `obslt` TINYINT(1) NULL DEFAULT NULL ,
  `archv` TINYINT(1) NULL DEFAULT NULL ,
  `dlt` TINYINT(1) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `plyr_id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ldrbrd`.`t_golfclub`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ldrbrd`.`t_golfclub` ;

CREATE  TABLE IF NOT EXISTS `ldrbrd`.`t_golfclub` (
  `id` VARCHAR(45) NOT NULL ,
  `clb_mnfctrr` INT(11) NULL DEFAULT '0' ,
  `clb_typ` INT(11) NULL DEFAULT '0' ,
  `clb_lft` INT(11) NULL DEFAULT '0' ,
  `clb_ctgry` INT(11) NULL DEFAULT '0' ,
  `clb_nm` VARCHAR(45) NULL DEFAULT NULL ,
  `is_dflt` TINYINT(1) NULL DEFAULT '0' ,
  `vrsn` INT(11) NULL DEFAULT NULL ,
  `crtdby` VARCHAR(45) NULL DEFAULT NULL ,
  `updtdby` VARCHAR(45) NULL DEFAULT NULL ,
  `crtddt` DATETIME NULL DEFAULT NULL ,
  `updtddt` DATETIME NULL DEFAULT NULL ,
  `skpcrtddt` TINYINT(1) NULL DEFAULT NULL ,
  `syncvrsnid` INT(11) NULL DEFAULT NULL ,
  `obslt` TINYINT(1) NULL DEFAULT NULL ,
  `archv` TINYINT(1) NULL DEFAULT NULL ,
  `dlt` TINYINT(1) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `glfclb_id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `clb_mnfctrr` (`clb_mnfctrr` ASC, `clb_typ` ASC, `clb_nm` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ldrbrd`.`t_golf_bag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ldrbrd`.`t_golf_bag` ;

CREATE  TABLE IF NOT EXISTS `ldrbrd`.`t_golf_bag` (
  `id` VARCHAR(45) NOT NULL ,
  `glfr_id` VARCHAR(45) NOT NULL ,
  `glfclb_id` VARCHAR(45) NOT NULL ,
  `glf_bg_d` DATE NULL DEFAULT NULL ,
  `vrsn` INT(11) NULL DEFAULT NULL ,
  `crtdby` VARCHAR(45) NULL DEFAULT NULL ,
  `updtdby` VARCHAR(45) NULL DEFAULT NULL ,
  `crtddt` DATETIME NULL DEFAULT NULL ,
  `updtddt` DATETIME NULL DEFAULT NULL ,
  `skpcrtddt` TINYINT(1) NULL DEFAULT NULL ,
  `syncvrsnid` INT(11) NULL DEFAULT NULL ,
  `obslt` TINYINT(1) NULL DEFAULT NULL ,
  `archv` TINYINT(1) NULL DEFAULT NULL ,
  `dlt` TINYINT(1) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `glf_bg_id_UNIQUE` (`id` ASC) ,
  INDEX `glf_bgr_glfr_fk` (`glfr_id` ASC) ,
  INDEX `glf_bg_glfclb_fk` (`glfclb_id` ASC) ,
  CONSTRAINT `glf_bgr_glfr_fk`
    FOREIGN KEY (`glfr_id` )
    REFERENCES `ldrbrd`.`t_golfer` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `glf_bg_glfclb_fk`
    FOREIGN KEY (`glfclb_id` )
    REFERENCES `ldrbrd`.`t_golfclub` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ldrbrd`.`t_hole`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ldrbrd`.`t_hole` ;

CREATE  TABLE IF NOT EXISTS `ldrbrd`.`t_hole` (
  `id` VARCHAR(45) NOT NULL ,
  `hl_nmbr` INT(11) NULL DEFAULT NULL ,
  `hl_dstnc` INT(11) NULL DEFAULT NULL ,
  `hl_dsc` VARCHAR(255) NULL DEFAULT NULL ,
  `crs_id` VARCHAR(45) NOT NULL ,
  `hl_nm` VARCHAR(45) NULL DEFAULT NULL ,
  `hl_img_ref` VARCHAR(45) NULL DEFAULT NULL ,
  `vrsn` INT(11) NULL DEFAULT NULL ,
  `crtdby` VARCHAR(45) NULL DEFAULT NULL ,
  `updtdby` VARCHAR(45) NULL DEFAULT NULL ,
  `crtddt` DATETIME NULL DEFAULT NULL ,
  `updtddt` DATETIME NULL DEFAULT NULL ,
  `skpcrtddt` TINYINT(1) NULL DEFAULT NULL ,
  `syncvrsnid` INT(11) NULL DEFAULT NULL ,
  `obslt` TINYINT(1) NULL DEFAULT NULL ,
  `archv` TINYINT(1) NULL DEFAULT NULL ,
  `dlt` TINYINT(1) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `hl_id_UNIQUE` (`id` ASC) ,
  INDEX `fk_t_hole_t_course1` (`crs_id` ASC) ,
  INDEX `hl_crs_fk` (`crs_id` ASC) ,
  CONSTRAINT `hl_crs_fk`
    FOREIGN KEY (`crs_id` )
    REFERENCES `ldrbrd`.`t_course` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ldrbrd`.`t_scorecard`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ldrbrd`.`t_scorecard` ;

CREATE  TABLE IF NOT EXISTS `ldrbrd`.`t_scorecard` (
  `id` VARCHAR(45) NOT NULL ,
  `scrcrd_d` DATE NOT NULL ,
  `glfr_id` VARCHAR(45) NOT NULL ,
  `crs_id` VARCHAR(45) NOT NULL ,
  `rnd_typ` INT(11) NULL DEFAULT NULL ,
  `vrsn` INT(11) NULL DEFAULT NULL ,
  `crtdby` VARCHAR(45) NULL DEFAULT NULL ,
  `updtdby` VARCHAR(45) NULL DEFAULT NULL ,
  `crtddt` DATETIME NULL DEFAULT NULL ,
  `updtddt` DATETIME NULL DEFAULT NULL ,
  `skpcrtddt` TINYINT(1) NULL DEFAULT NULL ,
  `syncvrsnid` INT(11) NULL DEFAULT NULL ,
  `obslt` TINYINT(1) NULL DEFAULT NULL ,
  `archv` TINYINT(1) NULL DEFAULT NULL ,
  `dlt` TINYINT(1) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `scrcrd_id_UNIQUE` (`id` ASC) ,
  INDEX `scrd_glfr_fk` (`glfr_id` ASC) ,
  INDEX `scrd_crs_fk` (`crs_id` ASC) ,
  CONSTRAINT `scrd_glfr_fk`
    FOREIGN KEY (`glfr_id` )
    REFERENCES `ldrbrd`.`t_golfer` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `scrd_crs_fk`
    FOREIGN KEY (`crs_id` )
    REFERENCES `ldrbrd`.`t_course` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ldrbrd`.`t_hole_score`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ldrbrd`.`t_hole_score` ;

CREATE  TABLE IF NOT EXISTS `ldrbrd`.`t_hole_score` (
  `id` VARCHAR(45) NOT NULL ,
  `scrcrd_id` VARCHAR(45) NOT NULL ,
  `hl_id` VARCHAR(45) NOT NULL ,
  `vrsn` INT(11) NULL DEFAULT NULL ,
  `crtdby` VARCHAR(45) NULL DEFAULT NULL ,
  `updtdby` VARCHAR(45) NULL DEFAULT NULL ,
  `crtddt` DATETIME NULL DEFAULT NULL ,
  `updtddt` DATETIME NULL DEFAULT NULL ,
  `skpcrtddt` TINYINT(1) NULL DEFAULT NULL ,
  `syncvrsnid` INT(11) NULL DEFAULT NULL ,
  `obslt` TINYINT(1) NULL DEFAULT NULL ,
  `archv` TINYINT(1) NULL DEFAULT NULL ,
  `dlt` TINYINT(1) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `hl_scr_id_UNIQUE` (`id` ASC) ,
  INDEX `hl_scr_scrcrd_fk` (`scrcrd_id` ASC) ,
  INDEX `hl_scr_hl_fk` (`hl_id` ASC) ,
  CONSTRAINT `hl_scr_scrcrd_fk`
    FOREIGN KEY (`scrcrd_id` )
    REFERENCES `ldrbrd`.`t_scorecard` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `hl_scr_hl_fk`
    FOREIGN KEY (`hl_id` )
    REFERENCES `ldrbrd`.`t_hole` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ldrbrd`.`t_shot`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ldrbrd`.`t_shot` ;

CREATE  TABLE IF NOT EXISTS `ldrbrd`.`t_shot` (
  `sht_id` VARCHAR(45) NOT NULL ,
  `sht_dt` DATETIME NOT NULL ,
  `glfclb_id` VARCHAR(45) NOT NULL ,
  `hl_scr_id` VARCHAR(45) NOT NULL ,
  `vrsn` INT(11) NULL DEFAULT NULL ,
  `crtdby` VARCHAR(45) NULL DEFAULT NULL ,
  `updtdby` VARCHAR(45) NULL DEFAULT NULL ,
  `crtddt` DATETIME NULL DEFAULT NULL ,
  `updtddt` DATETIME NULL DEFAULT NULL ,
  `skpcrtddt` TINYINT(1) NULL DEFAULT NULL ,
  `syncvrsnid` INT(11) NULL DEFAULT NULL ,
  `obslt` TINYINT(1) NULL DEFAULT NULL ,
  `archv` TINYINT(1) NULL DEFAULT NULL ,
  `dlt` TINYINT(1) NULL DEFAULT NULL ,
  PRIMARY KEY (`sht_id`) ,
  UNIQUE INDEX `sht_id_UNIQUE` (`sht_id` ASC) ,
  INDEX `sht_glfclb_fk` (`glfclb_id` ASC) ,
  CONSTRAINT `sht_glfclb_fk`
    FOREIGN KEY (`glfclb_id` )
    REFERENCES `ldrbrd`.`t_golfclub` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `sht_hl_scr_fk`
    FOREIGN KEY (`hl_scr_id` )
    REFERENCES `ldrbrd`.`t_hole_score` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ldrbrd`.`t_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ldrbrd`.`t_user` ;

CREATE  TABLE IF NOT EXISTS `ldrbrd`.`t_user` (
  `id` VARCHAR(45) NOT NULL ,
  `frst_nm` VARCHAR(45) NOT NULL ,
  `lst_nm` VARCHAR(45) NOT NULL ,
  `eml_addrss` VARCHAR(45) NULL DEFAULT NULL ,
  `psswrd` VARCHAR(45) NULL DEFAULT NULL ,
  `dsply_nm` VARCHAR(45) NOT NULL ,
  `img_ref` VARCHAR(200) NULL ,
  `is_nbld` TINYINT(1) NOT NULL DEFAULT '1' ,
  `lst_lgn_dt` DATETIME NULL DEFAULT NULL ,
  `fld_lgn_attmpts` INT(11) NULL DEFAULT NULL ,
  `vrsn` INT(11) NULL DEFAULT NULL ,
  `crtdby` VARCHAR(45) NULL DEFAULT NULL ,
  `updtdby` VARCHAR(45) NULL DEFAULT NULL ,
  `crtddt` DATETIME NULL DEFAULT NULL ,
  `updtddt` DATETIME NULL DEFAULT NULL ,
  `skpcrtddt` TINYINT(1) NULL DEFAULT NULL ,
  `syncvrsnid` INT(11) NULL DEFAULT NULL ,
  `obslt` TINYINT(1) NULL DEFAULT NULL ,
  `archv` TINYINT(1) NULL DEFAULT NULL ,
  `dlt` TINYINT(1) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `usr_id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `eml_addrss_UNIQUE` (`eml_addrss` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
