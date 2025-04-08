CREATE DATABASE  IF NOT EXISTS `bus_schema_leek` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bus_schema_leek`;
-- MySQL dump 10.13  Distrib 8.0.38, for macos14 (x86_64)
--
-- Host: localhost    Database: bus_schema_leek
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
-- Table structure for table `bus_journey_booking`
--

DROP TABLE IF EXISTS `bus_journey_booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bus_journey_booking` (
  `booking_id` int NOT NULL AUTO_INCREMENT,
  `payment_method` enum('Credit Card','Debit Card','Online Wallet') DEFAULT NULL,
  `seats_booked` int NOT NULL,
  `route_instance` int NOT NULL,
  `username` varchar(64) NOT NULL,
  PRIMARY KEY (`booking_id`),
  KEY `route_instance` (`route_instance`),
  KEY `username` (`username`),
  CONSTRAINT `bus_journey_booking_ibfk_1` FOREIGN KEY (`route_instance`) REFERENCES `route_instance` (`r_instance`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `bus_journey_booking_ibfk_2` FOREIGN KEY (`username`) REFERENCES `customer` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_journey_booking`
--

LOCK TABLES `bus_journey_booking` WRITE;
/*!40000 ALTER TABLE `bus_journey_booking` DISABLE KEYS */;
INSERT INTO `bus_journey_booking` VALUES (2,'Online Wallet',3,3,'cust3'),(3,'Credit Card',4,4,'cust3'),(4,'Debit Card',2,5,'cust3'),(5,'Online Wallet',3,6,'cust3'),(6,'Credit Card',1,7,'cust3'),(7,'Debit Card',2,3,'lemo'),(8,'Online Wallet',3,4,'lemo'),(9,'Credit Card',4,5,'lemo'),(10,'Debit Card',1,6,'lemo'),(11,'Online Wallet',2,7,'lemo'),(12,'Credit Card',2,3,'cust3'),(13,'Debit Card',3,4,'cust3'),(14,'Online Wallet',2,5,'cust3'),(15,'Credit Card',1,6,'cust3'),(16,'Debit Card',2,7,'cust3'),(17,'Credit Card',2,3,'lemo'),(18,'Debit Card',3,4,'lemo'),(19,'Online Wallet',2,5,'lemo'),(20,'Credit Card',1,6,'lemo'),(21,'Debit Card',2,7,'lemo'),(22,'Credit Card',2,3,'aden@123'),(23,'Debit Card',3,4,'aden@123'),(24,'Online Wallet',2,5,'aden@123'),(25,'Credit Card',1,6,'aden@123'),(26,'Debit Card',2,7,'aden@123'),(28,'Credit Card',2,18,'aden@123'),(29,'Debit Card',3,19,'aden@123'),(30,'Online Wallet',2,20,'aden@123'),(31,'Credit Card',1,21,'aden@123'),(32,'Debit Card',2,22,'aden@123'),(33,'Online Wallet',3,18,'aden@123'),(34,'Credit Card',1,19,'aden@123'),(35,'Debit Card',2,20,'aden@123'),(36,'Online Wallet',3,21,'aden@123'),(37,'Credit Card',4,22,'aden@123'),(38,'Credit Card',2,23,'lemo'),(39,'Debit Card',3,24,'lemo'),(40,'Online Wallet',2,5,'lemo'),(41,'Credit Card',1,6,'lemo'),(42,'Debit Card',2,7,'lemo'),(43,'Credit Card',2,23,'lemo'),(44,'Debit Card',3,24,'lemo'),(45,'Online Wallet',2,5,'lemo'),(46,'Credit Card',1,6,'lemo'),(47,'Debit Card',2,7,'lemo'),(48,'Online Wallet',3,23,'cust3'),(49,'Credit Card',4,24,'cust3'),(50,'Debit Card',2,5,'cust3'),(51,'Online Wallet',3,6,'cust3'),(52,'Credit Card',1,7,'cust3'),(53,'Online Wallet',3,23,'cust3'),(54,'Credit Card',1,24,'cust3'),(55,'Debit Card',2,5,'cust3'),(56,'Online Wallet',3,6,'cust3'),(57,'Credit Card',4,7,'cust3'),(58,'Credit Card',2,7,'kathleen'),(59,'Credit Card',1,9,'lemo'),(60,'Credit Card',5,24,'cust3'),(61,'Debit Card',1,17,'cust3'),(62,'Credit Card',2,19,'kathleen'),(65,'Debit Card',1,17,'kathleen'),(66,'Debit Card',1,17,'kathleen');
/*!40000 ALTER TABLE `bus_journey_booking` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `r_instance_update_after_insert_journey` AFTER INSERT ON `bus_journey_booking` FOR EACH ROW BEGIN  
	DECLARE bus_capacity INT;
    DECLARE seats_sold INT;
    
    SELECT capacity INTO bus_capacity
    FROM bus_type AS bt JOIN route_instance AS ri ON bt.bus_id = ri.bus_id
    WHERE r_instance = NEW.route_instance
    ;
    
    SELECT IFNULL(SUM(bjb.seats_booked),0) INTO seats_sold
    FROM bus_journey_booking AS bjb -- JOIN route_instance AS ri ON bjb.route_instance = ri.r_instance
    WHERE bjb.route_instance = NEW.route_instance
    ;
    
    UPDATE route_instance
    SET num_seats_available = bus_capacity - seats_sold
    WHERE r_instance = NEW.route_instance
    ;
    
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `r_instance_update_after_delete_journey` AFTER DELETE ON `bus_journey_booking` FOR EACH ROW BEGIN
	UPDATE route_instance AS ri 
    JOIN bus_journey_booking AS bjb ON ri.r_instance = bjb.route_instance
    SET ri.num_seats_available = ri.num_seats_available + OLD.seats_booked
    WHERE ri.r_instance = OLD.route_instance
    ;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `bus_not_used`
--

DROP TABLE IF EXISTS `bus_not_used`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bus_not_used` (
  `bus_id` int NOT NULL DEFAULT '0',
  `type` enum('sleeper','seater','Double Decker','Shuttle') DEFAULT NULL,
  `capacity` int DEFAULT NULL,
  `model` varchar(64) DEFAULT NULL,
  `color` varchar(64) DEFAULT NULL,
  `license_plate` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_not_used`
--

LOCK TABLES `bus_not_used` WRITE;
/*!40000 ALTER TABLE `bus_not_used` DISABLE KEYS */;
INSERT INTO `bus_not_used` VALUES (10,'seater',28,'Volvo YZA','White','YZA890'),(11,'sleeper',48,'Mercedes BCD','Gray','BCD123'),(12,'seater',33,'Scania EFG','Silver','EFG456'),(13,'sleeper',42,'Volvo HIJ','Gold','HIJ789'),(14,'seater',27,'Mercedes KLM','Cyan','KLM012'),(19,'sleeper',47,'Volvo ZAB','Olive','ZAB567'),(20,'seater',26,'Mercedes CDE','Turquoise','CDE890'),(21,'sleeper',53,'Scania FGH','Violet','FGH123'),(22,'seater',34,'Volvo IJK','Teal','IJK456'),(23,'sleeper',39,'Mercedes LMN','Beige','LMN789'),(24,'seater',36,'Scania OPQ','Salmon','OPQ012'),(25,'sleeper',44,'Volvo RST','Khaki','RST345'),(26,'seater',23,'Mercedes UVW','Coral','UVW678'),(27,'sleeper',49,'Scania XYZ','Aquamarine','XYZ901'),(28,'seater',37,'Volvo ABC','Plum','ABC234'),(29,'sleeper',41,'Mercedes DEF','NavajoWhite','DEF567'),(30,'sleeper',40,'DreamSleeper','Blue','ABC987'),(31,'seater',50,'TravelMax','Red','XYZ789'),(32,'Double Decker',80,'DoubleDeluxe','White','DEF987'),(33,'Shuttle',20,'CityHopper','Green','GHI798'),(34,'sleeper',45,'CozyNap','Purple','JKL014'),(35,'seater',55,'EconomyExpress','Yellow','MNO345'),(36,'Double Decker',75,'LuxuryLiner','Silver','PQR678'),(37,'Shuttle',25,'QuickShuttle','Orange','STU901'),(38,'sleeper',42,'SlumberRide','Green','VWX234'),(39,'seater',48,'CityComfort','Blue','YZA567'),(40,'Double Decker',82,'SuperDeck','Red','BCD890'),(41,'Shuttle',22,'MetroShuttle','Yellow','EFG123'),(42,'sleeper',38,'RestfulRide','Gray','HIJ456'),(43,'seater',60,'RoyalTransit','Black','KLM789'),(44,'Double Decker',85,'MegaDeck','Purple','NOP012'),(45,'Shuttle',18,'SpeedyShuttle','Orange','QRS345'),(46,'sleeper',44,'SweetDreams','Blue','TUV678'),(47,'seater',53,'ExpressWay','Green','WXY901'),(48,'Double Decker',78,'HighDeck','Red','ZAB234'),(50,'Double Decker',80,'DD101','Red','ABC123');
/*!40000 ALTER TABLE `bus_not_used` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus_type`
--

DROP TABLE IF EXISTS `bus_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bus_type` (
  `bus_id` int NOT NULL AUTO_INCREMENT,
  `type` enum('sleeper','seater','Double Decker','Shuttle') DEFAULT NULL,
  `capacity` int DEFAULT NULL,
  `model` varchar(64) DEFAULT NULL,
  `color` varchar(64) DEFAULT NULL,
  `license_plate` varchar(64) NOT NULL,
  PRIMARY KEY (`bus_id`),
  UNIQUE KEY `license_plate` (`license_plate`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_type`
--

LOCK TABLES `bus_type` WRITE;
/*!40000 ALTER TABLE `bus_type` DISABLE KEYS */;
INSERT INTO `bus_type` VALUES (1,'sleeper',50,'Volvo XYZ','Blue','XYZ123'),(2,'seater',30,'Mercedes ABC','Red','ABC456'),(3,'sleeper',40,'Scania DEF','Green','DEF789'),(4,'seater',35,'Volvo GHI','Yellow','GHI012'),(5,'sleeper',45,'Mercedes JKL','Purple','JKL345'),(6,'seater',25,'Scania MNO','Orange','MNO678'),(7,'sleeper',55,'Volvo PQR','Pink','PQR901'),(8,'seater',32,'Mercedes STU','Brown','STU234'),(9,'sleeper',38,'Scania VWX','Black','VWX567'),(10,'seater',28,'Volvo YZA','White','YZA890'),(11,'sleeper',48,'Mercedes BCD','Gray','BCD123'),(12,'seater',33,'Scania EFG','Silver','EFG456'),(13,'sleeper',42,'Volvo HIJ','Gold','HIJ789'),(14,'seater',27,'Mercedes KLM','Cyan','KLM012'),(15,'sleeper',52,'Scania NOP','Magenta','NOP345'),(16,'seater',29,'Volvo QRS','Indigo','QRS678'),(17,'sleeper',43,'Mercedes TUV','Lime','TUV901'),(18,'seater',31,'Scania WXY','Maroon','WXY234'),(19,'sleeper',47,'Volvo ZAB','Olive','ZAB567'),(20,'seater',26,'Mercedes CDE','Turquoise','CDE890'),(21,'sleeper',53,'Scania FGH','Violet','FGH123'),(22,'seater',34,'Volvo IJK','Teal','IJK456'),(23,'sleeper',39,'Mercedes LMN','Beige','LMN789'),(24,'seater',36,'Scania OPQ','Salmon','OPQ012'),(25,'sleeper',44,'Volvo RST','Khaki','RST345'),(26,'seater',23,'Mercedes UVW','Coral','UVW678'),(27,'sleeper',49,'Scania XYZ','Aquamarine','XYZ901'),(28,'seater',37,'Volvo ABC','Plum','ABC234'),(29,'sleeper',41,'Mercedes DEF','NavajoWhite','DEF567'),(30,'sleeper',40,'DreamSleeper','Blue','ABC987'),(31,'seater',50,'TravelMax','Red','XYZ789'),(32,'Double Decker',80,'DoubleDeluxe','White','DEF987'),(33,'Shuttle',20,'CityHopper','Green','GHI798'),(34,'sleeper',45,'CozyNap','Purple','JKL014'),(35,'seater',55,'EconomyExpress','Yellow','MNO345'),(36,'Double Decker',75,'LuxuryLiner','Silver','PQR678'),(37,'Shuttle',25,'QuickShuttle','Orange','STU901'),(38,'sleeper',42,'SlumberRide','Green','VWX234'),(39,'seater',48,'CityComfort','Blue','YZA567'),(40,'Double Decker',82,'SuperDeck','Red','BCD890'),(41,'Shuttle',22,'MetroShuttle','Yellow','EFG123'),(42,'sleeper',38,'RestfulRide','Gray','HIJ456'),(43,'seater',60,'RoyalTransit','Black','KLM789'),(44,'Double Decker',85,'MegaDeck','Purple','NOP012'),(45,'Shuttle',18,'SpeedyShuttle','Orange','QRS345'),(46,'sleeper',44,'SweetDreams','Blue','TUV678'),(47,'seater',53,'ExpressWay','Green','WXY901'),(48,'Double Decker',78,'HighDeck','Red','ZAB234'),(49,'Shuttle',30,'CityMover','Silver','BCD567'),(50,'Double Decker',80,'DD101','Red','ABC123'),(51,'Double Decker',80,'DD102','Blue','DEF456'),(52,'Double Decker',80,'DD103','Green','GHI789'),(53,'Double Decker',80,'DD104','Yellow','JKL012');
/*!40000 ALTER TABLE `bus_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `city_id` int NOT NULL AUTO_INCREMENT,
  `city_name` varchar(64) DEFAULT NULL,
  `state` char(2) NOT NULL,
  PRIMARY KEY (`city_id`),
  UNIQUE KEY `city_name` (`city_name`,`state`),
  KEY `state` (`state`),
  CONSTRAINT `city_ibfk_1` FOREIGN KEY (`state`) REFERENCES `state_info` (`abbreviation`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (2,'Boston','MA'),(3,'Chicago','IL'),(4,'Houston','TX'),(5,'Miami','FL'),(1,'New York','NY'),(9,'Portland','OR'),(6,'San Francisco','CA');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `first_name` varchar(64) NOT NULL,
  `last_name` varchar(64) NOT NULL,
  `email` varchar(64) NOT NULL,
  `phone_no` char(10) NOT NULL,
  `username` varchar(64) NOT NULL,
  `user_type` enum('admin','customer') DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `phone_no` (`phone_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('aden','pitt','adenp@gmail.com','7463456898','aden@123','customer'),('Joe','Kelly','kelly@gmail.com','2345678901','Bos_admin','admin'),('Jane','Doe','janedoe@gmail.com','8576473827','Chicago_Admin','admin'),('jay','z','jayz@yahoo.com','8657465734','cust3','customer'),('Kathleen','Grant','kath@gmail.com','1234567890','kathleen','customer'),('lemo','nade','lemonade@gmail.com','7453675898','lemo','customer'),('daisy','flower','df31@hotmail.com','9085647856','Super_Admin','admin');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `driver`
--

DROP TABLE IF EXISTS `driver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `driver` (
  `driver_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(64) NOT NULL,
  `last_name` varchar(64) NOT NULL,
  `phone_no` bigint NOT NULL,
  `license_no` varchar(10) NOT NULL,
  PRIMARY KEY (`driver_id`),
  UNIQUE KEY `license_no` (`license_no`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `driver`
--

LOCK TABLES `driver` WRITE;
/*!40000 ALTER TABLE `driver` DISABLE KEYS */;
INSERT INTO `driver` VALUES (1,'John','Doe',1234567890,'DL12345'),(2,'Alice','Smith',9876543210,'DL67890'),(3,'Bob','Johnson',8765432109,'DL54321'),(4,'Emma','Wilson',7654321098,'DL09876'),(5,'David','Lee',6543210987,'DL23456'),(6,'Sarah','Brown',5432109876,'DL65432'),(7,'Ryan','Miller',4321098765,'DL78901'),(8,'Olivia','Wong',3210987654,'DL32109'),(9,'Michael','Chen',2109876543,'DL56789'),(10,'Bob','Johnson',8765432109,''),(11,'Jackie','Gleason',4567890123,'XXY123');
/*!40000 ALTER TABLE `driver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `route`
--

DROP TABLE IF EXISTS `route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `route` (
  `route_id` int NOT NULL AUTO_INCREMENT,
  `origin_city_name` varchar(64) NOT NULL,
  `origin_state` char(2) NOT NULL,
  `destination_city_name` varchar(64) NOT NULL,
  `destination_state` char(2) NOT NULL,
  `estimated_time` int DEFAULT NULL,
  `estimated_miles` int DEFAULT NULL,
  PRIMARY KEY (`route_id`),
  KEY `origin_city_name` (`origin_city_name`,`origin_state`),
  KEY `destination_city_name` (`destination_city_name`,`destination_state`),
  CONSTRAINT `route_ibfk_1` FOREIGN KEY (`origin_city_name`, `origin_state`) REFERENCES `city` (`city_name`, `state`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `route_ibfk_2` FOREIGN KEY (`destination_city_name`, `destination_state`) REFERENCES `city` (`city_name`, `state`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route`
--

LOCK TABLES `route` WRITE;
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
INSERT INTO `route` VALUES (1,'Boston','MA','Chicago','IL',22,849),(2,'Boston','MA','Houston','TX',52,1602),(3,'Boston','MA','Miami','FL',33,1258),(4,'Boston','MA','New York','NY',4,190),(5,'Chicago','IL','Boston','MA',22,849),(6,'Chicago','IL','Houston','TX',23,937),(7,'Chicago','IL','New York','NY',20,711),(8,'Chicago','IL','Miami','FL',27,1089),(9,'Houston','TX','Boston','MA',52,1602),(10,'Houston','TX','Chicago','IL',23,937),(11,'Houston','TX','Miami','FL',77,966),(12,'Houston','TX','New York','NY',53,1416),(13,'Miami','FL','Boston','MA',33,1258),(14,'Miami','FL','Chicago','IL',33,1258),(15,'Miami','FL','Houston','TX',77,966),(16,'Miami','FL','New York','NY',27,1089),(17,'New York','NY','Boston','MA',4,190),(18,'New York','NY','Chicago','IL',20,711),(19,'New York','NY','Houston','TX',53,1416),(20,'New York','NY','Miami','FL',27,1089);
/*!40000 ALTER TABLE `route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `route_instance`
--

DROP TABLE IF EXISTS `route_instance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `route_instance` (
  `r_instance` int NOT NULL AUTO_INCREMENT,
  `route_id` int NOT NULL,
  `fare_price` int NOT NULL,
  `scheduled_date_time` datetime NOT NULL,
  `driver_id` int NOT NULL,
  `bus_id` int NOT NULL,
  `num_seats_available` int DEFAULT NULL,
  PRIMARY KEY (`r_instance`),
  UNIQUE KEY `route_id` (`route_id`,`scheduled_date_time`),
  KEY `driver_id` (`driver_id`),
  KEY `bus_id` (`bus_id`),
  CONSTRAINT `route_instance_ibfk_1` FOREIGN KEY (`route_id`) REFERENCES `route` (`route_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `route_instance_ibfk_2` FOREIGN KEY (`driver_id`) REFERENCES `driver` (`driver_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `route_instance_ibfk_3` FOREIGN KEY (`bus_id`) REFERENCES `bus_type` (`bus_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route_instance`
--

LOCK TABLES `route_instance` WRITE;
/*!40000 ALTER TABLE `route_instance` DISABLE KEYS */;
INSERT INTO `route_instance` VALUES (1,3,100,'2024-06-30 08:00:00',1,1,50),(2,4,80,'2024-06-25 10:00:00',2,2,30),(3,5,120,'2024-07-30 12:00:00',3,3,29),(4,6,150,'2024-09-01 14:00:00',4,4,19),(5,7,90,'2024-09-27 09:00:00',5,5,25),(6,8,50,'2024-11-01 08:00:00',3,51,65),(7,9,40,'2024-11-28 10:00:00',4,52,60),(8,10,60,'2024-12-27 12:00:00',5,53,80),(9,11,70,'2024-09-26 14:00:00',4,49,29),(10,12,45,'2024-10-26 09:00:00',5,15,52),(11,13,80,'2024-10-26 08:00:00',6,16,29),(12,14,90,'2024-11-29 10:00:00',7,17,43),(13,15,100,'2024-12-25 12:00:00',8,18,31),(14,16,85,'2024-11-29 14:00:00',6,18,31),(15,18,50,'2024-10-30 08:00:00',3,51,80),(16,19,40,'2024-11-29 10:00:00',4,52,80),(17,20,60,'2024-12-30 12:00:00',5,53,77),(18,3,70,'2024-11-28 14:00:00',4,6,20),(19,6,45,'2024-11-25 09:00:00',5,7,49),(20,5,80,'2024-10-28 08:00:00',6,8,28),(21,4,90,'2024-12-01 10:00:00',7,9,34),(22,3,100,'2024-12-27 12:00:00',8,1,44),(23,1,85,'2024-10-30 14:00:00',6,9,28),(24,3,75,'2024-11-27 09:00:00',9,1,34),(25,17,80,'2025-01-25 08:00:00',7,15,52),(26,17,80,'2024-12-30 08:00:00',7,9,38);
/*!40000 ALTER TABLE `route_instance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `state_info`
--

DROP TABLE IF EXISTS `state_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `state_info` (
  `state_name` varchar(48) NOT NULL,
  `abbreviation` char(2) NOT NULL,
  PRIMARY KEY (`abbreviation`),
  UNIQUE KEY `state_name` (`state_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `state_info`
--

LOCK TABLES `state_info` WRITE;
/*!40000 ALTER TABLE `state_info` DISABLE KEYS */;
INSERT INTO `state_info` VALUES ('Alabama','AL'),('Alaska','AK'),('American Samoa','AS'),('Arizona','AZ'),('Arkansas','AR'),('California','CA'),('Colorado','CO'),('Connecticut','CT'),('Delaware','DE'),('District of Columbia','DC'),('Florida','FL'),('Georgia','GA'),('Guam','GU'),('Hawaii','HI'),('Idaho','ID'),('Illinois','IL'),('Indiana','IN'),('Iowa','IA'),('Kansas','KS'),('Kentucky','KY'),('Louisiana','LA'),('Maine','ME'),('Maryland','MD'),('Massachusetts','MA'),('Michigan','MI'),('Minnesota','MN'),('Mississippi','MS'),('Missouri','MO'),('Montana','MT'),('Nebraska','NE'),('Nevada','NV'),('New Hampshire','NH'),('New Jersey','NJ'),('New Mexico','NM'),('New York','NY'),('North Carolina','NC'),('North Dakota','ND'),('Northern Mariana Islands','MP'),('Ohio','OH'),('Oklahoma','OK'),('Oregon','OR'),('Pennsylvania','PA'),('Puerto Rico','PR'),('Rhode Island','RI'),('South Carolina','SC'),('South Dakota','SD'),('Tennessee','TN'),('Texas','TX'),('Trust Territories','TT'),('Utah','UT'),('Vermont','VT'),('Virgin Islands','VI'),('Virginia','VA'),('Washington','WA'),('West Virginia','WV'),('Wisconsin','WI'),('Wyoming','WY');
/*!40000 ALTER TABLE `state_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'bus_schema_leek'
--

--
-- Dumping routines for database 'bus_schema_leek'
--
/*!50003 DROP FUNCTION IF EXISTS `get_driver_journey_num` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `get_driver_journey_num`(
	driver_p INT
) RETURNS int
    READS SQL DATA
    DETERMINISTIC
BEGIN 
	DECLARE driver_var INT;
	SELECT COUNT(r_instance) INTO driver_var
	FROM route_instance
    WHERE driver_p = driver_id
	GROUP BY driver_id
    ;
    RETURN driver_var;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `more_route_instances` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `more_route_instances`(
	email1_p VARCHAR(64),
    email2_p VARCHAR(64)
) RETURNS int
    READS SQL DATA
    DETERMINISTIC
BEGIN 

	DECLARE calc_email1 INT;
    DECLARE calc_email2 INT;
    
    SELECT COUNT(DISTINCT route_instance) INTO calc_email1
    FROM customer AS c JOIN bus_journey_booking AS bjb ON c.username = bjb.username
    WHERE c.email = email1_p
    ;
    
    SELECT COUNT(DISTINCT route_instance) INTO calc_email2
    FROM customer AS c JOIN bus_journey_booking AS bjb ON c.username = bjb.username
    WHERE c.email = email2_p
    ;
    
    IF calc_email1 > calc_email2 THEN 
		RETURN 1;
	ELSEIF calc_email1 < calc_email2 THEN 
		RETURN -1;
	ELSE 
		RETURN 0;
    END IF;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `num_buses_with_type` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `num_buses_with_type`(
	bus_type_p enum('sleeper', 'seater', 'Double Decker', 'Shuttle')
) RETURNS int
    READS SQL DATA
    DETERMINISTIC
BEGIN  
	DECLARE bus_type_var INT;
    
    SELECT COUNT(type) INTO bus_type_var
    FROM bus_type 
    WHERE type = bus_type_p
    ;
    RETURN (bus_type_var);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `add_num_seats_available_column` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_num_seats_available_column`()
BEGIN
	IF NOT EXISTS (
		SELECT * 
		FROM information_schema.COLUMNS 
		WHERE TABLE_NAME = 'route_instance' 
		AND TABLE_SCHEMA = 'bus_schema_LeeK'  -- Replace with your database name
		AND COLUMN_NAME = 'num_seats_available'
	) THEN 
		ALTER TABLE route_instance
		ADD COLUMN num_seats_available INTEGER;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `buy_tickets` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `buy_tickets`(
	origin_city_p VARCHAR(64), 
    origin_state_p CHAR(2), 
    destination_city_p VARCHAR(64), 
    destination_state_p CHAR(2), 
    username_p VARCHAR(64), 
	num_tickets_p INT, 
    payment_method_p enum('Credit Card','Debit Card','Online Wallet')
)
BEGIN 
	DECLARE available_seats INT;
    DECLARE route_inst_id_var INT; 
    DECLARE total_capacity INT;
    
    -- checking user validity
    IF NOT EXISTS (SELECT 1 FROM customer WHERE username = username_p) THEN 
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'User does not exist.';
    END IF;
    
    SELECT r_instance, capacity, (capacity - IFNULL(SUM(seats_booked), 0)) INTO route_inst_id_var, total_capacity, available_seats
	FROM bus_type AS bt JOIN route_instance AS ri ON bt.bus_id = ri.bus_id
		LEFT JOIN bus_journey_booking AS bjb ON ri.r_instance = bjb.route_instance
        JOIN route AS r ON ri.route_id = r.route_id
	WHERE origin_city_name = origin_city_p
		AND origin_state = origin_state_p
        AND destination_city_name = destination_city_p
        AND destination_state = destination_state_p
        AND scheduled_date_time > NOW()
	GROUP BY r_instance
    ORDER BY ri.scheduled_date_time ASC 
    LIMIT 1
	;
    
    -- check future
    IF route_inst_id_var IS NULL THEN 
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'No future scheduled journeys for this route.';
    END IF;
    
    -- enough seats?
    IF available_seats < num_tickets_p THEN 
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Not enough tickets available.';
    END IF;
    
    INSERT INTO bus_journey_booking ( username, route_instance, seats_booked, payment_method)
    VAlUES(username_p, route_inst_id_var, num_tickets_p,payment_method_p);
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `city_has_trips` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `city_has_trips`(
	city_id_p INT
)
BEGIN
	DECLARE cityName VARCHAR(255);
    DECLARE state_name VARCHAR(255);

    SELECT city_name, state INTO cityName, state_name 
    FROM city 
    WHERE city_id = city_id_p;
    
    IF cityName IS NULL THEN 
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid city id.';
	ELSE 
		SELECT city_id, c.city_name AS cityName, state AS state_name, scheduled_date_time, fare_price
		FROM state_info AS si JOIN city AS c ON si.abbreviation = c.state
		JOIN route AS r ON c.state = r.origin_state
		JOIN route_instance AS ri ON r.route_id = ri.route_id
        WHERE city_id = city_id_p
        ;
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `customers_to_destination` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `customers_to_destination`(
	dest_city_p VARCHAR(64),
    dest_state_p CHAR(2)
)
BEGIN 
	DECLARE bus_exists INT;
    DECLARE error_message VARCHAR(500);
    
    SELECT COUNT(*) INTO bus_exists
    FROM route AS r JOIN route_instance AS ri ON r.route_id = ri.route_id
    WHERE r.destination_city_name = dest_city_p AND r.destination_state = dest_state_p
    ;
    IF bus_exists = 0 THEN 
		SET error_message = CONCAT('No buses to that destination city ', dest_city_p, ', ', dest_state_p);
        SIGNAL SQLSTATE '02000' SET MESSAGE_TEXT = error_message;          
	ELSE 
		SELECT c.username,first_name, last_name, scheduled_date_time, r_instance,fare_price, destination_city_name, destination_state
		FROM customer AS c JOIN bus_journey_booking AS bjb ON c.username = bjb.username
		JOIN route_instance AS ri ON bjb.route_instance = ri.r_instance
		JOIN route AS r ON ri.route_id = r.route_id
		WHERE dest_city_p = destination_city_name AND dest_state_p = destination_state
		;
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `destination_in_less_than` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `destination_in_less_than`(
	city_p INT, 
    hours_p INT 
)
BEGIN 
	DECLARE originC VARCHAR(255);
    DECLARE originS VARCHAR(255);
    SELECT DISTINCT c.city_name, r.origin_state INTO originC, originS
    FROM city AS c JOIN route AS r ON c.city_name = r.origin_city_name
    WHERE city_id = city_p
    ;
    
	SELECT destination_city_name, destination_state, estimated_time, estimated_miles, fare_price
	FROM route AS r LEFT JOIN route_instance AS ri ON r.route_id = ri.route_id
    -- use left join for routes without bus assigned yet
	WHERE origin_city_name = originC
		AND origin_state = originS
		AND hours_p >= estimated_time
    ;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_customer_journeys` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_customer_journeys`(
	username_p VARCHAR(64)
)
BEGIN 
	SELECT c.username, first_name, last_name, payment_method, seats_booked, fare_price, scheduled_date_time,
	origin_city_name, origin_state, destination_city_name, destination_state, type, model, license_plate, bt.bus_id
	FROM customer AS c JOIN bus_journey_booking AS bjb ON c.username = bjb.username
		JOIN route_instance AS ri ON bjb.route_instance = ri.r_instance
		JOIN route AS r ON ri.route_id = r.route_id
		JOIN bus_type AS bt ON ri.bus_id = bt.bus_id
        WHERE c.username = username_p
	ORDER BY scheduled_date_time, origin_city_name
;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_future_trips_with_available_seats` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_future_trips_with_available_seats`()
BEGIN 
	SELECT origin_city_name, origin_state, destination_city_name, destination_state, scheduled_date_time, r_instance, type, model, fare_price,
		capacity, SUM(seats_booked) AS sold, (capacity - IFNULL(SUM(seats_booked), 0)) AS available 
	FROM bus_type AS bt JOIN route_instance AS ri ON bt.bus_id = ri.bus_id
		LEFT JOIN bus_journey_booking AS bjb ON ri.r_instance = bjb.route_instance
		JOIN route AS r ON ri.route_id = r.route_id
	WHERE scheduled_date_time > NOW()
	GROUP BY r_instance
    ORDER BY origin_city_name, scheduled_date_time ASC
	;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `set_num_seats_list` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `set_num_seats_list`(
	route_instance INT
)
BEGIN 
	DECLARE bus_capacity INT;
    DECLARE seats_sold INT;
    DECLARE seats_available INT;
    
    SELECT capacity INTO bus_capacity
    FROM bus_type AS bt JOIN route_instance AS ri ON bt.bus_id = ri.bus_id
    WHERE r_instance = route_instance
    ;
    
    SELECT IFNULL(SUM(bjb.seats_booked),0) INTO seats_sold
    FROM bus_journey_booking AS bjb JOIN route_instance AS ri ON bjb.route_instance = ri.r_instance
    WHERE bjb.route_instance = route_instance
    ;
    
    SET seats_available = bus_capacity - seats_sold;
    
    UPDATE route_instance
    SET num_seats_available = seats_available
    WHERE r_instance = route_instance;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_all_available_seats` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_all_available_seats`()
BEGIN 
	DECLARE done TINYINT DEFAULT FALSE;
    DECLARE current_thing INT;
    
    DECLARE seats_cursor CURSOR FOR
		SELECT r_instance
        FROM route_instance
        ;
	DECLARE CONTINUE HANDLER FOR NOT FOUND
		SET done = TRUE;
        
	OPEN seats_cursor;
    WHILE done = FALSE DO 
		FETCH seats_cursor INTO current_thing;
        IF NOT done THEN 
			CALL set_num_seats_list(current_thing);
		END IF;
    END WHILE;
    CLOSE seats_cursor;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-08 16:05:42
