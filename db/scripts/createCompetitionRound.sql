CREATE TABLE `ldrbrd_competition_round` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `competition_id` int(11) NOT NULL,
  `round_number` int(11) NOT NULL,
  `start_date` date DEFAULT NULL,
  `course_did` varchar(45) NOT NULL,
  `scoring_format` int(11) DEFAULT NULL,
  `initial_tee_time` time DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cmptnnrnd_cmpttn_fk_idx` (`competition_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
