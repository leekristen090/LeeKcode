CREATE DATABASE  IF NOT EXISTS `lotr_lee_k` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `lotr_lee_k`;
-- MySQL dump 10.13  Distrib 8.0.38, for macos14 (x86_64)
--
-- Host: localhost    Database: lotr_lee_k
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `book_id` int NOT NULL,
  `b_title` varchar(64) NOT NULL,
  PRIMARY KEY (`book_id`,`b_title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'the fellowship of the ring'),(2,'the two towers'),(3,'the return of the king');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `first_encounter`
--

DROP TABLE IF EXISTS `first_encounter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `first_encounter` (
  `character1_name` varchar(64) NOT NULL,
  `character2_name` varchar(64) NOT NULL,
  `book_id` int NOT NULL,
  `region_name` varchar(64) NOT NULL,
  KEY `character1_name` (`character1_name`),
  KEY `character2_name` (`character2_name`),
  KEY `book_id` (`book_id`),
  KEY `region_name` (`region_name`),
  CONSTRAINT `first_encounter_ibfk_1` FOREIGN KEY (`character1_name`) REFERENCES `lotr_character` (`character_name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `first_encounter_ibfk_2` FOREIGN KEY (`character2_name`) REFERENCES `lotr_character` (`character_name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `first_encounter_ibfk_3` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `first_encounter_ibfk_4` FOREIGN KEY (`region_name`) REFERENCES `region` (`region_name`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `first_encounter`
--

LOCK TABLES `first_encounter` WRITE;
/*!40000 ALTER TABLE `first_encounter` DISABLE KEYS */;
INSERT INTO `first_encounter` VALUES ('Aragorn','Eowyn',2,'rohan'),('Faramir','Frodo',3,'mordor'),('Frodo','Aragorn',1,'bree'),('Frodo','Elrond',1,'rivendell'),('Gandalf','Frodo',1,'shire'),('Gimli','Elrond',1,'rivendell'),('Gimli','Legolas',1,'rivendell'),('Saruman','Aragorn',2,'isengard'),('Sauron','Frodo',1,'bree');
/*!40000 ALTER TABLE `first_encounter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lotr_character`
--

DROP TABLE IF EXISTS `lotr_character`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lotr_character` (
  `character_name` varchar(64) NOT NULL,
  `homeland` varchar(64) NOT NULL,
  `royalty` tinyint(1) DEFAULT '0',
  `fellowship` tinyint(1) DEFAULT '0',
  `survive` tinyint(1) DEFAULT '1',
  `alias` varchar(256) NOT NULL,
  `book_number` int NOT NULL,
  `species_name` varchar(64) NOT NULL,
  PRIMARY KEY (`character_name`),
  UNIQUE KEY `alias` (`alias`),
  KEY `homeland` (`homeland`),
  KEY `book_number` (`book_number`),
  KEY `species_name` (`species_name`),
  CONSTRAINT `lotr_character_ibfk_1` FOREIGN KEY (`homeland`) REFERENCES `region` (`region_name`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `lotr_character_ibfk_2` FOREIGN KEY (`book_number`) REFERENCES `book` (`book_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `lotr_character_ibfk_3` FOREIGN KEY (`species_name`) REFERENCES `species` (`species_name`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lotr_character`
--

LOCK TABLES `lotr_character` WRITE;
/*!40000 ALTER TABLE `lotr_character` DISABLE KEYS */;
INSERT INTO `lotr_character` VALUES ('Aragorn','gondor',1,1,1,'strider',1,'human'),('Elrond','rivendell',1,0,1,'lord of rivendell',1,'elf'),('Eowyn','rohan',1,0,1,'white lady of rohan',2,'human'),('Faramir','gondor',1,0,1,'captain of the white tower',3,'human'),('Frodo','shire',0,1,1,'bearer of the one ring',1,'hobbit'),('Gandalf','undying lands',0,1,1,'greybeard',1,'Maiar'),('Gimli','lonely mountain',0,1,1,'lockbearer',1,'dwarf'),('Legolas','mirkwood',1,1,1,'prince of the woodlands',1,'elf'),('Saruman','isengard',0,0,0,'saruman the white',1,'Maiar'),('Sauron','mordor',0,0,0,'dark lord',1,'Maiar');
/*!40000 ALTER TABLE `lotr_character` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `region`
--

DROP TABLE IF EXISTS `region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `region` (
  `region_name` varchar(64) NOT NULL,
  `major_species` varchar(64) NOT NULL,
  `r_description` varchar(256) NOT NULL,
  `middle_earth_location` varchar(256) DEFAULT NULL,
  `leader` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`region_name`),
  KEY `major_species` (`major_species`),
  CONSTRAINT `region_ibfk_1` FOREIGN KEY (`major_species`) REFERENCES `species` (`species_name`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `region`
--

LOCK TABLES `region` WRITE;
/*!40000 ALTER TABLE `region` DISABLE KEYS */;
INSERT INTO `region` VALUES ('bree','human','village','north on the crossroads',NULL),('gondor','human','mountainous terrain','south','Kings of Gondor'),('isengard','orc','broad plain containing the tower of Orthanc','south','Saruman'),('lonely mountain','dwarf','cave','northeast','Durin folk'),('mirkwood','elf','forested elven village','east','Thranduil'),('mordor','orc','volcanic plain','southeast','Sauron'),('rivendell','elf','well hidden elf village in the foot hills of the Misty Mountains',NULL,'Elrond'),('rohan','human','green and fertile area','south','King of Rohan'),('shire','hobbit','grassy rolling hills','northwest','King of Arthedain'),('undying lands','Maiar','beyond middle earth','outside middle earth',NULL);
/*!40000 ALTER TABLE `region` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `species`
--

DROP TABLE IF EXISTS `species`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `species` (
  `species_name` varchar(64) NOT NULL,
  `s_description` varchar(256) NOT NULL,
  `size` int DEFAULT NULL,
  PRIMARY KEY (`species_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `species`
--

LOCK TABLES `species` WRITE;
/*!40000 ALTER TABLE `species` DISABLE KEYS */;
INSERT INTO `species` VALUES ('balrog','Ancient evil creatures',5),('dwarf','Dwellers of the mountains of middle earth mines for precious metals',2),('elf','Fairest and wisest species',4),('ent','Ancient beings inhabiting trees that protect the forest',6),('hobbit','Also known as halflings; mortal ancient creatures that enjoy the simple life',1),('human','Created during the first age of middle earth; three ages after elves and other middle earth species. Mortal creatures',3),('Maiar','Holy ones with mystical powers',0),('orc','Enslaved elves that were tortured and generated a new evil species',3);
/*!40000 ALTER TABLE `species` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-17 20:32:35
