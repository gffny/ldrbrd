CREATE DATABASE `ldrbrdv2` /*!40100 DEFAULT CHARACTER SET latin1 */;

CREATE TABLE `ldrbrd_competition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `competition_name` varchar(45) NOT NULL,
  `start_date` datetime DEFAULT NULL,
  `competitor_limit` int(11) DEFAULT NULL,
  `organising_society` int(11) DEFAULT NULL,
  `organising_golfer` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `ldrbrd_competition_entry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `competition_id` int(11) NOT NULL,
  `golfer_id` int(11) NOT NULL,
  `entry_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `ldrbrd_competition_round` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `competition_id` int(11) NOT NULL,
  `round_number` int(11) NOT NULL,
  `start_date` date DEFAULT NULL,
  `course_did` varchar(45) NOT NULL,
  `scoring_format` int(11) DEFAULT NULL,
  `initial_tee_time` time DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cmptnnrnd_cmpttn_fk_idx` (`competition_id`),
  CONSTRAINT `cmpttn_rnd_cmpttn` FOREIGN KEY (`competition_id`) REFERENCES `ldrbrd_competition` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `ldrbrd_competition_round_score` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `competition_entry_id` int(11) NOT NULL,
  `scorecard_id` varchar(45) NOT NULL,
  `golfer_signature` varchar(45) DEFAULT NULL,
  `scorer_signature` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `ldrbrd_golfer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `profile_handle` varchar(45) NOT NULL,
  `handicap` int(11) DEFAULT '18',
  `email_address` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `profile_name_UNIQUE` (`profile_handle`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE `ldrbrd_hole_score` (
  `scorecard_id` int(11) NOT NULL,
  `hole_number` int(11) NOT NULL,
  `score` int(11) NOT NULL,
  `hole_did` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`scorecard_id`,`hole_number`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `ldrbrd_scorecard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_did` varchar(45) NOT NULL,
  `golfer_id` int(11) NOT NULL,
  `round_date` datetime NOT NULL,
  `conditions` varchar(45) DEFAULT NULL,
  `handicap` varchar(45) DEFAULT NULL,
  `scorecard_notes` varchar(45) DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  `scorecard_signature` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
