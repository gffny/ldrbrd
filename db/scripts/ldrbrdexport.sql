-- MySQL dump 10.13  Distrib 5.6.19, for osx10.7 (i386)
--
-- Host: localhost    Database: ldrbrdv2
-- ------------------------------------------------------
-- Server version	5.6.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `ldrbrd_competition`
--

LOCK TABLES `ldrbrd_competition` WRITE;
/*!40000 ALTER TABLE `ldrbrd_competition` DISABLE KEYS */;
INSERT INTO `ldrbrd_competition` VALUES (1,'Test Competition','2014-12-31 23:59:59',0,1,NULL),(2,'Test Competition 2','2014-12-31 23:59:59',0,1,1);
/*!40000 ALTER TABLE `ldrbrd_competition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ldrbrd_competition_entry`
--

LOCK TABLES `ldrbrd_competition_entry` WRITE;
/*!40000 ALTER TABLE `ldrbrd_competition_entry` DISABLE KEYS */;
INSERT INTO `ldrbrd_competition_entry` VALUES (1,1,1,'2014-12-13 17:05:00'),(2,1,2,'2014-12-13 17:05:00'),(3,1,3,'2014-12-13 17:05:00');
/*!40000 ALTER TABLE `ldrbrd_competition_entry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ldrbrd_competition_round`
--

LOCK TABLES `ldrbrd_competition_round` WRITE;
/*!40000 ALTER TABLE `ldrbrd_competition_round` DISABLE KEYS */;
INSERT INTO `ldrbrd_competition_round` VALUES (1,1,1,'2014-12-31','5480615e30040a226307c959',1,'23:59:59',0),(2,1,2,'2015-01-01','5480615e30040a226307c959',1,'23:59:59',0),(3,1,3,'2015-01-02','5480615e30040a226307c959',1,'23:59:59',0),(4,1,4,'2015-01-03','5480615e30040a226307c959',1,'23:59:59',0);
/*!40000 ALTER TABLE `ldrbrd_competition_round` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ldrbrd_competition_round_score`
--

LOCK TABLES `ldrbrd_competition_round_score` WRITE;
/*!40000 ALTER TABLE `ldrbrd_competition_round_score` DISABLE KEYS */;
/*!40000 ALTER TABLE `ldrbrd_competition_round_score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ldrbrd_golfer`
--

LOCK TABLES `ldrbrd_golfer` WRITE;
/*!40000 ALTER TABLE `ldrbrd_golfer` DISABLE KEYS */;
INSERT INTO `ldrbrd_golfer` VALUES (1,'John','Gaffney','gffny',16,'john@gffny.com'),(2,'Colm','Caffrey','ccaffrey',8,'ccaffrey@gmail.com'),(3,'Eoin','DeBarra','eoindeb',18,'eoindeb@gmail.com');
/*!40000 ALTER TABLE `ldrbrd_golfer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ldrbrd_hole_score`
--

LOCK TABLES `ldrbrd_hole_score` WRITE;
/*!40000 ALTER TABLE `ldrbrd_hole_score` DISABLE KEYS */;
/*!40000 ALTER TABLE `ldrbrd_hole_score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ldrbrd_scorecard`
--

LOCK TABLES `ldrbrd_scorecard` WRITE;
/*!40000 ALTER TABLE `ldrbrd_scorecard` DISABLE KEYS */;
/*!40000 ALTER TABLE `ldrbrd_scorecard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ldrbrd_scoring_format`
--

LOCK TABLES `ldrbrd_scoring_format` WRITE;
/*!40000 ALTER TABLE `ldrbrd_scoring_format` DISABLE KEYS */;
INSERT INTO `ldrbrd_scoring_format` VALUES (1,'STABLEFORD','com.gffny.ldrbrd.common.scoring.impl.Stableford');
/*!40000 ALTER TABLE `ldrbrd_scoring_format` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ldrbrd_society`
--

LOCK TABLES `ldrbrd_society` WRITE;
/*!40000 ALTER TABLE `ldrbrd_society` DISABLE KEYS */;
INSERT INTO `ldrbrd_society` VALUES (1,'Morning Golf Champs',1);
/*!40000 ALTER TABLE `ldrbrd_society` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ldrbrd_society_membership`
--

LOCK TABLES `ldrbrd_society_membership` WRITE;
/*!40000 ALTER TABLE `ldrbrd_society_membership` DISABLE KEYS */;
INSERT INTO `ldrbrd_society_membership` VALUES (1,1,1,NULL,NULL);
/*!40000 ALTER TABLE `ldrbrd_society_membership` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-01-04 16:42:24
