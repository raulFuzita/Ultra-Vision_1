-- MySQL dump 10.13  Distrib 5.7.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ultra_vision_raul
-- ------------------------------------------------------
-- Server version	5.7.24

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `membership_card` int(11) NOT NULL AUTO_INCREMENT,
  `membership_plan` varchar(45) NOT NULL,
  `password` varchar(180) NOT NULL,
  `privilege` enum('CUSTOMER','ADMIN') NOT NULL DEFAULT 'CUSTOMER',
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `phonenumber` varchar(40) DEFAULT NULL,
  `street` varchar(100) NOT NULL,
  `city` varchar(80) NOT NULL,
  `country` varchar(80) NOT NULL,
  `bank_card` varchar(45) DEFAULT NULL,
  `birthday` date NOT NULL,
  PRIMARY KEY (`membership_card`,`membership_plan`),
  KEY `membership_card` (`membership_card`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'PR','admin','ADMIN','admin','tech','0000000','Westmoreland','Dublin','Ireland','0000-0000-0000-0000','1990-01-01');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `membership_card`
--

DROP TABLE IF EXISTS `membership_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `membership_card` (
  `idmembership_card` int(11) NOT NULL,
  `points` int(11) NOT NULL,
  PRIMARY KEY (`idmembership_card`),
  CONSTRAINT `membership_card` FOREIGN KEY (`idmembership_card`) REFERENCES `customer` (`membership_card`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membership_card`
--

LOCK TABLES `membership_card` WRITE;
/*!40000 ALTER TABLE `membership_card` DISABLE KEYS */;
INSERT INTO `membership_card` VALUES (1,100);
/*!40000 ALTER TABLE `membership_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `music`
--

DROP TABLE IF EXISTS `music`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `music` (
  `code` int(11) NOT NULL,
  `artist` varchar(80) NOT NULL,
  `album` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`code`),
  KEY `fk_code_ms` (`code`),
  CONSTRAINT `fk_code_ms` FOREIGN KEY (`code`) REFERENCES `title` (`code`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `music`
--

LOCK TABLES `music` WRITE;
/*!40000 ALTER TABLE `music` DISABLE KEYS */;
INSERT INTO `music` VALUES (1,'Anna of the North','Dream Girl'),(2,'Green Day','Green Day');
/*!40000 ALTER TABLE `music` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rental`
--

DROP TABLE IF EXISTS `rental`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rental` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `customer_membership_card` int(11) NOT NULL,
  `title_code` int(11) NOT NULL,
  `rent_at` timestamp NOT NULL,
  `return_at` datetime NOT NULL,
  `is_returned` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`,`customer_membership_card`,`title_code`),
  KEY `fk_customer_has_title_customer1_idx` (`customer_membership_card`),
  KEY `title_code_idx` (`title_code`),
  CONSTRAINT `fk_customer_has_title_customer1` FOREIGN KEY (`customer_membership_card`) REFERENCES `customer` (`membership_card`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `title_code` FOREIGN KEY (`title_code`) REFERENCES `title` (`code`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rental`
--

LOCK TABLES `rental` WRITE;
/*!40000 ALTER TABLE `rental` DISABLE KEYS */;
/*!40000 ALTER TABLE `rental` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `title`
--

DROP TABLE IF EXISTS `title`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `title` (
  `code` int(11) NOT NULL,
  `name` varchar(40) DEFAULT 'unknown',
  `genre` varchar(40) DEFAULT 'unknown',
  `cost` double DEFAULT '0',
  `year` date DEFAULT NULL,
  `media_format` enum('CD','DVD','BLUERAY') NOT NULL,
  `type_title` varchar(40) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `title`
--

LOCK TABLES `title` WRITE;
/*!40000 ALTER TABLE `title` DISABLE KEYS */;
INSERT INTO `title` VALUES (1,'My Love','Alternative',1.5,'2019-01-01','CD','ML'),(2,'Hopeful Love','Rock',2.5,'2018-01-25','DVD','ML'),(3,'The Avengers','Action',4,'2012-04-11','BLUERAY','VL'),(4,'Rise and Fall of an Empire','Documentary',8,'2014-02-23','DVD','TV');
/*!40000 ALTER TABLE `title` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tv`
--

DROP TABLE IF EXISTS `tv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tv` (
  `code` int(11) NOT NULL,
  `characterSeries` varchar(100) NOT NULL,
  PRIMARY KEY (`code`),
  KEY `code` (`code`),
  CONSTRAINT `fk_cod_tv` FOREIGN KEY (`code`) REFERENCES `title` (`code`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tv`
--

LOCK TABLES `tv` WRITE;
/*!40000 ALTER TABLE `tv` DISABLE KEYS */;
INSERT INTO `tv` VALUES (4,'Rome: Rise and Fall of an Empire is a TV documentary series aired on the History channel in 2008.');
/*!40000 ALTER TABLE `tv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `video`
--

DROP TABLE IF EXISTS `video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `video` (
  `code` int(11) NOT NULL,
  `director` varchar(80) NOT NULL,
  `description` varchar(250) DEFAULT 'No description',
  PRIMARY KEY (`code`),
  KEY `code` (`code`),
  CONSTRAINT `fk_code_vd` FOREIGN KEY (`code`) REFERENCES `title` (`code`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video`
--

LOCK TABLES `video` WRITE;
/*!40000 ALTER TABLE `video` DISABLE KEYS */;
INSERT INTO `video` VALUES (3,'Joss Whedon','The Avengers premiered at Hollywood\'s El Capitan Theatre on April 11, 2012, and was released in the United States on May 4, as the last film of Phase One of the MCU.');
/*!40000 ALTER TABLE `video` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-15 23:27:45
