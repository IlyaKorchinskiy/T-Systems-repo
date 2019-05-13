CREATE DATABASE  IF NOT EXISTS `shop` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `shop`;
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: 192.168.99.100    Database: shop
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `address_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (7,'Saint-Petersburg some address 76575436','CLIENT'),(15,'Saint-Petersburg some address 848815','CLIENT'),(16,'Saint-Petersburg some address 181518','CLIENT'),(25,'Saint-Petersburg some address 84354','CLIENT'),(26,'Saint-Petersburg some address 82282','CLIENT'),(28,'Saint-Petersburg some address 131713558','CLIENT'),(29,'Saint-Petersburg some address 131743643','CLIENT'),(30,'Saint-Petersburg address 1','PICKUP'),(31,'Saint-Petersburg address 2','PICKUP'),(32,'Saint-Petersburg address 3','PICKUP'),(33,'Saint-Petersburg address 4','PICKUP'),(34,'Saint-Petersburg address 5','PICKUP'),(35,'Saint-Petersburg address 6','PICKUP'),(36,'Saint-Petersburg some address 5745645','CLIENT'),(37,'Saint-Petersburg some address 52525451','CLIENT'),(38,'Saint-Petersburg some address 7824698','CLIENT'),(39,'Saint-Petersburg some address 9321418','CLIENT'),(40,'Saint-Petersburg some address 82268181','CLIENT'),(41,'Saint-Petersburg some address 131713','CLIENT'),(42,'Saint-Petersburg some address 181518','CLIENT'),(43,'Saint-Petersburg some address 5435352','CLIENT'),(44,'Saint-Petersburg some address 877546','CLIENT'),(45,'Saint-Petersburg some address 352531541','CLIENT'),(46,'Saint-Petersburg some address 828929','CLIENT'),(47,'Saint-Petersburg some address 228186','CLIENT'),(48,'Saint-Petersburg some address 13834831','CLIENT');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cart_user_idx` (`user_id`),
  CONSTRAINT `fk_cart_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (36,1),(37,10),(38,11),(39,12),(40,13),(41,21),(42,22),(43,23),(44,25);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_product`
--

DROP TABLE IF EXISTS `cart_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cart_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cart_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cart_product_id_idx` (`product_id`),
  KEY `fk_cart_cart_id_idx` (`cart_id`),
  CONSTRAINT `fk_cart_cart_id` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_cart_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=189 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_product`
--

LOCK TABLES `cart_product` WRITE;
/*!40000 ALTER TABLE `cart_product` DISABLE KEYS */;
INSERT INTO `cart_product` VALUES (169,37,18,2);
/*!40000 ALTER TABLE `cart_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Fiction',0),(2,'Fantasy',0),(3,'Business',0),(4,'Science and education',0),(5,'Psychology',0),(6,'Detective',0),(9,'Adventure',0),(10,'Horror, mystery',0),(11,'Modern prose',20),(12,'Thriller',6),(14,'Horror',10),(15,'Space fiction',1),(17,'Battle fiction',1),(18,'Social fiction',1),(19,'Philosophy',0),(20,'Modern literature',0),(24,'History',0),(25,'Biology',4),(29,'Science fiction',1);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery_type`
--

DROP TABLE IF EXISTS `delivery_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `delivery_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `delivery_type` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery_type`
--

LOCK TABLES `delivery_type` WRITE;
/*!40000 ALTER TABLE `delivery_type` DISABLE KEYS */;
INSERT INTO `delivery_type` VALUES (1,'Delivery'),(2,'Pickup');
/*!40000 ALTER TABLE `delivery_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_history`
--

DROP TABLE IF EXISTS `order_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `order_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `payment_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `delivery_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `payment_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `order_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `sum` double NOT NULL,
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_idx` (`order_id`),
  CONSTRAINT `fk_order_history_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=252 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_history`
--

LOCK TABLES `order_history` WRITE;
/*!40000 ALTER TABLE `order_history` DISABLE KEYS */;
INSERT INTO `order_history` VALUES (22,24,'CASH','PICKUP','WAITING_FOR_PAYMENT','NEW',918,'','2019-03-29 13:09:44'),(31,24,'CASH','PICKUP','WAITING_FOR_PAYMENT','CONFIRMED',918,'','2019-03-30 08:18:35'),(35,26,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','NEW',1836,'','2019-03-30 10:03:26'),(36,27,'CREDIT_CARD','PICKUP','WAITING_FOR_PAYMENT','NEW',1696,'','2019-03-30 10:04:43'),(37,28,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','NEW',1856,'','2019-03-30 10:06:33'),(38,29,'CASH','PICKUP','WAITING_FOR_PAYMENT','NEW',1936,'','2019-03-30 10:09:06'),(39,24,'CASH','PICKUP','WAITING_FOR_PAYMENT','SHIPPED',918,'','2019-03-30 10:10:00'),(40,24,'CASH','PICKUP','WAITING_FOR_PAYMENT','DELIVERED',918,'','2019-03-30 10:10:05'),(41,26,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','CONFIRMED',1836,'','2019-03-30 10:10:38'),(42,26,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','SHIPPED',1836,'','2019-03-30 10:10:42'),(43,26,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',1836,'','2019-03-30 10:10:46'),(44,27,'CREDIT_CARD','PICKUP','WAITING_FOR_PAYMENT','CONFIRMED',1696,'','2019-03-30 10:14:10'),(45,27,'CREDIT_CARD','PICKUP','WAITING_FOR_PAYMENT','SHIPPED',1696,'','2019-03-30 10:14:13'),(46,27,'CREDIT_CARD','PICKUP','WAITING_FOR_PAYMENT','DELIVERED',1696,'','2019-03-30 10:14:18'),(47,28,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','CONFIRMED',1856,'','2019-03-30 10:15:07'),(48,28,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','SHIPPED',1856,'','2019-03-30 10:15:10'),(49,28,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',1856,'','2019-03-30 10:15:15'),(50,29,'CASH','PICKUP','WAITING_FOR_PAYMENT','CONFIRMED',1936,'','2019-03-30 10:19:26'),(51,29,'CASH','PICKUP','WAITING_FOR_PAYMENT','SHIPPED',1936,'','2019-03-30 10:19:31'),(52,29,'CASH','PICKUP','WAITING_FOR_PAYMENT','DELIVERED',1936,'','2019-03-30 10:19:35'),(53,30,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','NEW',1277,'','2019-03-31 17:08:01'),(54,31,'CASH','PICKUP','WAITING_FOR_PAYMENT','NEW',898,'','2019-04-01 05:44:54'),(55,32,'CASH','DELIVERY','WAITING_FOR_PAYMENT','NEW',948,'','2019-04-01 05:57:50'),(56,30,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','CONFIRMED',1277,'','2019-04-01 06:57:21'),(57,30,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','SHIPPED',1277,'','2019-04-01 06:57:26'),(58,30,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',1277,'','2019-04-01 06:57:30'),(59,31,'CASH','PICKUP','WAITING_FOR_PAYMENT','CONFIRMED',898,'','2019-04-01 06:58:41'),(60,31,'CASH','PICKUP','WAITING_FOR_PAYMENT','SHIPPED',898,'','2019-04-01 06:58:45'),(61,31,'CASH','PICKUP','WAITING_FOR_PAYMENT','DELIVERED',898,'','2019-04-01 06:58:48'),(62,33,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','NEW',1816,'','2019-04-01 10:35:55'),(63,32,'CASH','DELIVERY','WAITING_FOR_PAYMENT','CONFIRMED',948,'','2019-04-01 10:36:07'),(65,33,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','CONFIRMED',1816,'','2019-04-01 12:16:47'),(66,34,'CASH','PICKUP','WAITING_FOR_PAYMENT','NEW',878,'','2019-04-01 18:53:11'),(67,32,'CASH','DELIVERY','WAITING_FOR_PAYMENT','SHIPPED',948,'','2019-04-01 18:53:29'),(68,35,'CASH','DELIVERY','WAITING_FOR_PAYMENT','NEW',479,'','2019-04-02 12:04:30'),(69,35,'CASH','DELIVERY','WAITING_FOR_PAYMENT','CONFIRMED',479,'','2019-04-02 12:08:24'),(70,36,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','NEW',918,'','2019-04-02 13:27:23'),(71,32,'CASH','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',948,'','2019-04-02 17:50:29'),(72,37,'CASH','PICKUP','WAITING_FOR_PAYMENT','NEW',1477,'','2019-04-02 17:52:36'),(74,36,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','CONFIRMED',918,'','2019-04-03 09:16:23'),(76,35,'CASH','DELIVERY','WAITING_FOR_PAYMENT','SHIPPED',479,'','2019-04-03 16:43:13'),(77,35,'CASH','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',479,'','2019-04-03 16:43:18'),(78,37,'CASH','PICKUP','WAITING_FOR_PAYMENT','CONFIRMED',1477,'','2019-04-03 16:45:27'),(79,37,'CASH','PICKUP','WAITING_FOR_PAYMENT','SHIPPED',1477,'','2019-04-03 16:45:30'),(80,37,'CASH','PICKUP','WAITING_FOR_PAYMENT','DELIVERED',1477,'','2019-04-03 16:45:34'),(81,40,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','NEW',758,'','2019-04-03 17:54:39'),(82,41,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','NEW',928,'','2019-04-03 18:03:25'),(83,41,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','CONFIRMED',928,'','2019-04-03 18:03:57'),(86,43,'CASH','DELIVERY','WAITING_FOR_PAYMENT','NEW',968,'','2019-04-05 05:43:52'),(87,43,'CASH','DELIVERY','WAITING_FOR_PAYMENT','CONFIRMED',968,'','2019-04-05 05:44:23'),(88,41,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','SHIPPED',928,'','2019-04-05 05:45:17'),(89,41,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',928,'','2019-04-05 05:45:21'),(90,44,'CASH','PICKUP','WAITING_FOR_PAYMENT','NEW',1148,'','2019-04-05 07:44:52'),(91,45,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','NEW',898,'','2019-04-05 11:01:37'),(93,43,'CASH','DELIVERY','WAITING_FOR_PAYMENT','SHIPPED',968,'','2019-04-05 11:13:47'),(94,45,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','CONFIRMED',898,'','2019-04-05 11:14:18'),(95,45,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','SHIPPED',898,'','2019-04-05 11:14:21'),(96,45,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',898,'','2019-04-05 11:14:24'),(97,33,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','SHIPPED',1816,'','2019-04-05 11:21:17'),(98,33,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',1816,'','2019-04-05 11:21:21'),(101,47,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','NEW',439,'','2019-04-05 13:33:55'),(102,47,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','CONFIRMED',439,'','2019-04-05 13:34:47'),(103,34,'CASH','PICKUP','WAITING_FOR_PAYMENT','CONFIRMED',878,'','2019-04-09 07:01:38'),(104,48,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','NEW',828,'','2019-04-09 07:06:26'),(105,40,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','CONFIRMED',758,'','2019-04-12 05:23:02'),(133,76,'CASH','DELIVERY','WAITING_FOR_PAYMENT','NEW',738,'','2019-04-16 09:02:15'),(134,76,'CASH','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',738,'','2019-04-16 09:18:07'),(135,44,'CASH','PICKUP','WAITING_FOR_PAYMENT','DELIVERED',1148,'','2019-04-16 09:20:34'),(136,34,'CASH','PICKUP','WAITING_FOR_PAYMENT','DELIVERED',878,'','2019-04-16 11:07:40'),(137,40,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',758,'','2019-04-16 11:18:35'),(138,36,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',918,'','2019-04-16 11:41:53'),(139,43,'CASH','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',968,'','2019-04-16 16:40:26'),(146,47,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','SHIPPED',439,'','2019-04-16 17:26:52'),(162,47,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',439,'','2019-04-17 08:14:54'),(163,48,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',828,'','2019-04-17 09:04:03'),(164,77,'CASH','DELIVERY','WAITING_FOR_PAYMENT','NEW',2516,'','2019-04-17 09:17:29'),(165,77,'CASH','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',2516,'','2019-04-17 09:20:42'),(166,78,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','NEW',2076,'','2019-04-17 10:41:59'),(167,78,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',2076,'','2019-04-17 10:43:24'),(168,79,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','NEW',958,'','2019-04-17 11:09:51'),(169,79,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',958,'','2019-04-17 11:10:02'),(170,80,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','NEW',2356,'','2019-04-17 11:22:34'),(171,80,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',2356,'','2019-04-17 11:22:42'),(172,81,'CASH','DELIVERY','WAITING_FOR_PAYMENT','NEW',1137,'','2019-04-17 11:26:33'),(173,81,'CASH','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',1137,'','2019-04-17 11:26:47'),(174,82,'CASH','DELIVERY','WAITING_FOR_PAYMENT','NEW',1557,'','2019-04-17 11:31:22'),(175,82,'CASH','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',1557,'','2019-04-17 11:31:40'),(176,83,'CASH','DELIVERY','WAITING_FOR_PAYMENT','NEW',1806,'','2019-04-17 12:35:45'),(177,83,'CASH','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',1806,'','2019-04-17 12:35:57'),(178,84,'CASH','DELIVERY','WAITING_FOR_PAYMENT','NEW',1347,'','2019-04-17 12:38:44'),(179,84,'CASH','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',1347,'','2019-04-17 12:38:52'),(180,85,'CASH','DELIVERY','WAITING_FOR_PAYMENT','NEW',2215,'','2019-04-23 08:26:14'),(181,85,'CASH','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',2215,'','2019-04-23 08:33:45'),(185,86,'CASH','PICKUP','WAITING_FOR_PAYMENT','NEW',1148,'Saint-Petersburg address 3','2019-05-01 09:35:05'),(186,86,'CASH','PICKUP','WAITING_FOR_PAYMENT','CONFIRMED',1148,'Saint-Petersburg address 3','2019-05-01 14:52:47'),(187,86,'CASH','PICKUP','WAITING_FOR_PAYMENT','SHIPPED',1148,'Saint-Petersburg address 3','2019-05-01 14:54:52'),(188,87,'CASH','DELIVERY','WAITING_FOR_PAYMENT','NEW',888,'Saint-Petersburg some address181518','2019-05-01 14:56:32'),(189,88,'CASH','PICKUP','WAITING_FOR_PAYMENT','NEW',629,'Saint-Petersburg address 5','2019-05-01 15:42:12'),(190,89,'CASH','DELIVERY','WAITING_FOR_PAYMENT','NEW',918,'Saint-Petersburg some address 351515','2019-05-01 16:05:23'),(191,90,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','NEW',918,'Saint-Petersburg some address 5815365','2019-05-02 09:32:48'),(192,91,'CASH','DELIVERY','WAITING_FOR_PAYMENT','NEW',828,'Saint-Petersburg some address 7824698','2019-05-02 09:40:18'),(193,92,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','NEW',629,NULL,'2019-05-02 10:31:39'),(194,93,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','NEW',519,'Saint-Petersburg some address 228186','2019-05-02 11:29:07'),(202,94,'CASH','DELIVERY','WAITING_FOR_PAYMENT','NEW',589,'Saint-Petersburg some address 13834831','2019-05-02 14:36:03'),(204,95,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','NEW',1167,'Saint-Petersburg some address 13834831','2019-05-02 14:38:16'),(206,96,'CASH','DELIVERY','WAITING_FOR_PAYMENT','NEW',978,'Saint-Petersburg some address 131713','2019-05-02 14:39:34'),(207,97,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','NEW',1437,'Saint-Petersburg some address 131713','2019-05-02 14:40:34'),(211,98,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','NEW',1317,'Saint-Petersburg some address 828929','2019-05-02 15:17:24'),(212,99,'CASH','DELIVERY','WAITING_FOR_PAYMENT','NEW',758,'Saint-Petersburg some address 828929','2019-05-02 15:19:16'),(215,100,'CASH','DELIVERY','WAITING_FOR_PAYMENT','NEW',738,'Saint-Petersburg some address 228186','2019-05-02 15:30:55'),(216,101,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','NEW',1347,'Saint-Petersburg some address 228186','2019-05-02 15:32:12'),(219,102,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','NEW',1038,'Saint-Petersburg some address 848815','2019-05-02 15:34:48'),(220,103,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','NEW',1467,'Saint-Petersburg some address 848815','2019-05-02 15:35:08'),(223,86,'CASH','PICKUP','WAITING_FOR_PAYMENT','SHIPPED',1148,'Saint-Petersburg address 3','2019-05-02 15:42:19'),(224,87,'CASH','DELIVERY','WAITING_FOR_PAYMENT','SHIPPED',888,'Saint-Petersburg some address181518','2019-05-02 15:42:30'),(225,88,'CASH','PICKUP','WAITING_FOR_PAYMENT','SHIPPED',629,'Saint-Petersburg address 5','2019-05-02 15:42:41'),(226,89,'CASH','DELIVERY','WAITING_FOR_PAYMENT','SHIPPED',918,'Saint-Petersburg some address 351515','2019-05-02 15:43:03'),(227,86,'CASH','PICKUP','WAITING_FOR_PAYMENT','DELIVERED',1148,'Saint-Petersburg address 3','2019-05-02 15:44:57'),(228,87,'CASH','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',888,'Saint-Petersburg some address181518','2019-05-02 15:45:20'),(229,88,'CASH','PICKUP','WAITING_FOR_PAYMENT','DELIVERED',629,'Saint-Petersburg address 5','2019-05-02 15:45:34'),(230,89,'CASH','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',918,'Saint-Petersburg some address 351515','2019-05-02 15:45:58'),(231,90,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',918,'Saint-Petersburg some address 5815365','2019-05-02 15:59:21'),(232,91,'CASH','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',828,'Saint-Petersburg some address 7824698','2019-05-02 15:59:42'),(233,92,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',629,NULL,'2019-05-02 16:00:00'),(234,93,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',519,'Saint-Petersburg some address 228186','2019-05-02 16:00:19'),(235,94,'CASH','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',589,'Saint-Petersburg some address 13834831','2019-05-02 16:00:38'),(236,95,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',1167,'Saint-Petersburg some address 13834831','2019-05-02 16:00:57'),(237,96,'CASH','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',978,'Saint-Petersburg some address 131713','2019-05-02 16:04:51'),(238,97,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',1437,'Saint-Petersburg some address 131713','2019-05-03 08:03:35'),(239,98,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',1317,'Saint-Petersburg some address 828929','2019-05-03 08:04:02'),(240,99,'CASH','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',758,'Saint-Petersburg some address 828929','2019-05-03 08:04:19'),(241,104,'CASH','DELIVERY','WAITING_FOR_PAYMENT','NEW',389,'Saint-Petersburg some address 828929','2019-05-05 11:42:12'),(242,105,'CASH','DELIVERY','WAITING_FOR_PAYMENT','NEW',389,'Saint-Petersburg some address 828929','2019-05-05 11:43:02'),(243,106,'CASH','DELIVERY','WAITING_FOR_PAYMENT','NEW',389,'Saint-Petersburg some address 828929','2019-05-06 06:22:18'),(244,107,'CASH','DELIVERY','WAITING_FOR_PAYMENT','NEW',389,'Saint-Petersburg some address 828929','2019-05-06 06:32:28'),(245,100,'CASH','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',738,'Saint-Petersburg some address 228186','2019-05-07 08:49:15'),(248,110,'CASH','PICKUP','WAITING_FOR_PAYMENT','NEW',1178,'Saint-Petersburg address 1','2019-05-07 10:20:23'),(249,101,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',1347,'Saint-Petersburg some address 228186','2019-05-07 10:21:48'),(250,102,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',1038,'Saint-Petersburg some address 848815','2019-05-07 10:22:50'),(251,103,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',1467,'Saint-Petersburg some address 848815','2019-05-07 10:23:10');
/*!40000 ALTER TABLE `order_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_product`
--

DROP TABLE IF EXISTS `order_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `order_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `cost` double NOT NULL,
  `amount` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_idx` (`order_id`),
  KEY `fk_product_idx` (`product_id`),
  CONSTRAINT `fk_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_product`
--

LOCK TABLES `order_product` WRITE;
/*!40000 ALTER TABLE `order_product` DISABLE KEYS */;
INSERT INTO `order_product` VALUES (24,24,14,459,2),(27,26,5,479,2),(28,26,2,389,1),(29,26,1,489,1),(30,27,7,379,1),(31,27,10,369,1),(32,27,14,459,1),(33,27,1,489,1),(34,28,6,439,1),(35,28,7,379,1),(36,28,9,519,2),(37,29,9,519,2),(38,29,8,449,2),(39,30,10,369,1),(40,30,2,389,1),(41,30,9,519,1),(42,31,8,449,2),(43,32,1,489,1),(44,32,14,459,1),(45,33,5,479,1),(46,33,2,389,1),(47,33,14,459,1),(48,33,1,489,1),(49,34,2,389,1),(50,34,1,489,1),(51,35,5,479,1),(52,36,5,479,1),(53,36,6,439,1),(54,37,9,519,2),(55,37,6,439,1),(60,40,7,379,2),(61,41,6,439,1),(62,41,1,489,1),(66,43,9,519,1),(67,43,8,449,1),(68,44,16,629,1),(69,44,18,519,1),(70,45,18,519,1),(71,45,7,379,1),(73,47,6,439,1),(74,48,6,439,1),(75,48,2,389,1),(103,76,10,369,2),(104,77,16,629,4),(105,78,18,519,4),(106,79,5,479,2),(107,80,4,589,4),(108,81,7,379,3),(109,82,9,519,3),(110,83,1,489,2),(111,83,2,389,1),(112,83,6,439,1),(113,84,8,449,3),(114,85,4,589,1),(115,85,18,519,1),(116,85,10,369,3),(117,86,18,519,1),(118,86,16,629,1),(119,87,9,519,1),(120,87,10,369,1),(121,88,16,629,1),(122,89,14,459,2),(123,90,14,459,2),(124,91,8,449,1),(125,91,7,379,1),(126,92,16,629,1),(127,93,9,519,1),(128,94,4,589,2),(129,95,2,389,3),(130,96,1,489,2),(131,97,5,479,3),(132,98,6,439,3),(133,99,7,379,2),(134,100,10,369,2),(135,101,8,449,3),(136,102,18,519,2),(137,103,1,489,3),(138,104,2,389,1),(139,105,2,389,1),(140,106,2,389,1),(141,107,2,389,1),(145,110,4,589,2);
/*!40000 ALTER TABLE `order_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `payment_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `delivery_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `payment_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `order_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `sum` double NOT NULL,
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_client_idx` (`user_id`),
  KEY `fk_orders_payment_status_idx` (`payment_status`),
  KEY `fk_orders_order_status_idx` (`order_status`),
  CONSTRAINT `fk_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (24,10,'CASH','PICKUP','WAITING_FOR_PAYMENT','DELIVERED',918,'','2019-03-29 13:09:44'),(26,10,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',1836,'','2019-03-30 10:03:26'),(27,11,'CREDIT_CARD','PICKUP','WAITING_FOR_PAYMENT','DELIVERED',1696,'','2019-03-30 10:04:43'),(28,1,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',1856,'','2019-03-30 10:06:33'),(29,12,'CASH','PICKUP','WAITING_FOR_PAYMENT','DELIVERED',1936,'','2019-03-30 10:09:06'),(30,13,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',1277,'','2019-03-31 17:07:01'),(31,23,'CASH','PICKUP','WAITING_FOR_PAYMENT','DELIVERED',898,'','2019-04-01 05:42:52'),(32,22,'CASH','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',948,'','2019-04-01 05:57:38'),(33,21,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',1816,'','2019-04-01 10:35:55'),(34,22,'CASH','PICKUP','WAITING_FOR_PAYMENT','DELIVERED',878,'','2019-04-01 18:53:10'),(35,11,'CASH','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',479,'','2019-04-02 12:04:30'),(36,10,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',918,'','2019-04-02 13:27:23'),(37,1,'CASH','PICKUP','WAITING_FOR_PAYMENT','DELIVERED',1477,'','2019-04-02 17:52:36'),(40,10,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',758,'','2019-04-03 17:54:39'),(41,11,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',928,'','2019-04-03 18:03:25'),(43,10,'CASH','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',968,'','2019-04-05 05:43:52'),(44,25,'CASH','PICKUP','WAITING_FOR_PAYMENT','DELIVERED',1148,'','2019-04-05 07:44:52'),(45,25,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',898,'','2019-04-05 11:01:37'),(47,1,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',439,'','2019-04-05 13:33:55'),(48,1,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',828,'','2019-04-09 07:06:26'),(76,1,'CASH','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',738,'','2019-04-16 09:02:15'),(77,13,'CASH','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',2516,'','2019-04-17 09:17:29'),(78,12,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',2076,'','2019-04-17 10:41:59'),(79,23,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',958,'','2019-04-17 11:09:51'),(80,11,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',2356,'','2019-04-17 11:22:34'),(81,21,'CASH','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',1137,'','2019-04-17 11:26:33'),(82,22,'CASH','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',1557,'','2019-04-17 11:31:22'),(83,1,'CASH','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',1806,'','2019-04-17 12:35:45'),(84,23,'CASH','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',1347,'','2019-04-17 12:38:44'),(85,25,'CASH','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',2215,'','2019-04-23 08:26:14'),(86,12,'CASH','PICKUP','WAITING_FOR_PAYMENT','DELIVERED',1148,'Saint-Petersburg address 3','2019-05-01 09:35:05'),(87,10,'CASH','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',888,'Saint-Petersburg some address181518','2019-05-01 14:56:32'),(88,11,'CASH','PICKUP','WAITING_FOR_PAYMENT','DELIVERED',629,'Saint-Petersburg address 5','2019-05-01 15:42:12'),(89,11,'CASH','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',918,'Saint-Petersburg some address 351515','2019-05-01 16:05:23'),(90,21,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',918,'Saint-Petersburg some address 5815365','2019-05-02 09:32:48'),(91,21,'CASH','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',828,'Saint-Petersburg some address 7824698','2019-05-02 09:40:18'),(92,10,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',629,NULL,'2019-05-02 10:31:39'),(93,23,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',519,'Saint-Petersburg some address 228186','2019-05-02 11:29:07'),(94,22,'CASH','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',589,'Saint-Petersburg some address 13834831','2019-05-02 14:36:03'),(95,22,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',1167,'Saint-Petersburg some address 13834831','2019-05-02 14:38:16'),(96,25,'CASH','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',978,'Saint-Petersburg some address 131713','2019-05-02 14:39:34'),(97,25,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',1437,'Saint-Petersburg some address 131713','2019-05-02 14:40:34'),(98,1,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',1317,'Saint-Petersburg some address 828929','2019-05-02 15:17:24'),(99,1,'CASH','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',758,'Saint-Petersburg some address 828929','2019-05-02 15:19:16'),(100,23,'CASH','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',738,'Saint-Petersburg some address 228186','2019-05-02 15:30:55'),(101,23,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',1347,'Saint-Petersburg some address 228186','2019-05-02 15:32:12'),(102,12,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',1038,'Saint-Petersburg some address 848815','2019-05-02 15:34:48'),(103,12,'CREDIT_CARD','DELIVERY','WAITING_FOR_PAYMENT','DELIVERED',1467,'Saint-Petersburg some address 848815','2019-05-02 15:35:08'),(104,1,'CASH','DELIVERY','WAITING_FOR_PAYMENT','NEW',389,'Saint-Petersburg some address 828929','2019-05-05 11:42:12'),(105,1,'CASH','DELIVERY','WAITING_FOR_PAYMENT','NEW',389,'Saint-Petersburg some address 828929','2019-05-05 11:43:02'),(106,1,'CASH','DELIVERY','WAITING_FOR_PAYMENT','NEW',389,'Saint-Petersburg some address 828929','2019-05-06 06:22:17'),(107,1,'CASH','DELIVERY','WAITING_FOR_PAYMENT','NEW',389,'Saint-Petersburg some address 828929','2019-05-06 06:32:28'),(110,11,'CASH','PICKUP','WAITING_FOR_PAYMENT','NEW',1178,'Saint-Petersburg address 1','2019-05-07 10:20:23');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_type`
--

DROP TABLE IF EXISTS `payment_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `payment_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `payment_type` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_type`
--

LOCK TABLES `payment_type` WRITE;
/*!40000 ALTER TABLE `payment_type` DISABLE KEYS */;
INSERT INTO `payment_type` VALUES (1,'Cash'),(2,'Credit card');
/*!40000 ALTER TABLE `payment_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `author` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `year` varchar(4) COLLATE utf8mb4_unicode_ci NOT NULL,
  `cost` double NOT NULL,
  `amount` int(11) NOT NULL,
  `description` varchar(1500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `md_photo` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `sm_photo` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Cell','Stephen King','2006',489,4,'Graphic artist Clay Riddell was in the heart of Boston on that brilliant autumn afternoon when hell was unleashed before his eyes. Without warning, carnage and chaos reigned. Ordinary people fell victim to the basest, most animalistic destruction. And the apocalypse began with the ring of a cell phone....','1.jpg','1aa612a2-bc2e-44cb-8ba3-1bbdff5ee7932.jpg','2019-03-10 00:00:00'),(2,'The War of Worlds','Herbert Wells','2002',389,3,'With H.G. Wells’ other novels, The War of the Worlds was one of the first and greatest works of science fiction ever to be written. Even long before man had learned to fly, H.G. Wells wrote this story of the Martian attack on England. These unearthly creatures arrive in huge cylinders, from which they escape as soon as the metal is cool. The first falls near Woking and is regarded as a curiosity rather than a danger until the Martians climb out of it and kill many of the gaping crowd with a Heat-Ray. These unearthly creatures have heads four feet in diameter and colossal round bodies, and by manipulating two terrifying machines – the Handling Machine and the Fighting Machine – they are as versatile as humans and at the same time insuperable. They cause boundless destruction. The inhabitants of the Earth are powerless against them, and it looks as if the end of the World has come. But there is one factor which the Martians, in spite of their superior intelligence, have not reckoned on. It is this which brings about a miraculous conclusion to this famous work of the imagination.','2.jpg','583cee33-56d6-4d9c-ab96-2ee208669ee12.jpg','2019-03-10 00:00:00'),(4,'Hunger Games','Suzanne Collins','2008',589,4,'Could you survive on your own, in the wild, with everyone out to make sure you don\'t live to see the morning? In the ruins of a place once known as North America lies the nation of Panem, a shining Capitol surrounded by twelve outlying districts. The Capitol is harsh and cruel and keeps the districts in line by forcing them all to send one boy and one girl between the ages of twelve and eighteen to participate in the annual Hunger Games, a fight to the death on live TV. Sixteen-year-old Katniss Everdeen, who lives alone with her mother and younger sister, regards it as a death sentence when she is forced to represent her district in the Games. But Katniss has been close to dead before - and survival, for her, is second nature. Without really meaning to, she becomes a contender. But if she is to win, she will have to start making choices that weigh survival against humanity and life against love.','4.jpg','131fdc27-e9eb-49e1-a2ad-a1cda74ee0412.jpg','2019-03-10 00:00:00'),(5,'The Maze Runner','James Dashner','2009',479,7,'If you ain’t scared, you ain’t human. When Thomas wakes up in the lift, the only thing he can remember is his name. He’s surrounded by strangers—boys whose memories are also gone. Nice to meet ya, shank. Welcome to the Glade. Outside the towering stone walls that surround the Glade is a limitless, ever-changing maze. It’s the only way out—and no one’s ever made it through alive. Everything is going to change. Then a girl arrives. The first girl ever. And the message she delivers is terrifying. Remember. Survive. Run.','5.jpg','0e0f5e42-d769-4796-92a5-5b08a1d045562.jpg','2019-03-10 00:00:00'),(6,'Fahrenheit 451','Ray Bradbury','2011',439,7,'Guy Montag is a fireman. In his world, where television rules and literature is on the brink of extinction, firemen start fires rather than put them out. His job is to destroy the most illegal of commodities, the printed book, along with the houses in which they are hidden. Montag never questions the destruction and ruin his actions produce, returning each day to his bland life and wife, Mildred, who spends all day with her television \'family\'. But then he meets an eccentric young neighbor, Clarisse, who introduces him to a past where people did not live in fear and to a present where one sees the world through the ideas in books instead of the mindless chatter of television. When Mildred attempts suicide and Clarisse suddenly disappears, Montag begins to question everything he has ever known.','6.jpg','4a0ae74d-27af-44bd-b6ef-e8f48cf4cf192.jpg','2019-03-10 00:00:00'),(7,'The 5th Wave','Rick Yancey','2013',379,7,'After the 1st wave, only darkness remains. After the 2nd, only the lucky escape. And after the 3rd, only the unlucky survive. After the 4th wave, only one rule applies: trust no one. Now, it\'s the dawn of the 5th wave, and on a lonely stretch of highway, Cassie runs from Them. The beings who only look human, who roam the countryside killing anyone they see. Who have scattered Earth\'s last survivors. To stay alone is to stay alive, Cassie believes, until she meets Evan Walker. Beguiling and mysterious, Evan Walker may be Cassie\'s only hope for rescuing her brother-or even saving herself. But Cassie must choose: between trust and despair, between defiance and surrender, between life and death. To give up or to get up.','7.jpg','553c007d-eab0-4f86-9163-0267b744a4b82.jpg','2019-03-10 00:00:00'),(8,'Inter Ice Age 4','Kobo Abe','1971',449,4,'This is yet another of Mr. Abe\'s ominous configurations (Woman in the Dunes etc.) this time staking out its uncertain ideological imperatives in a grave new world submerged under water. In the beginning, however, Professor Katsumi who has a computer capable of making predictions, has no idea of the work undertaken in a still more dehumanized laboratory. But a double murder, an analysis of one of the bodies & some anonymous phone calls (this is all quite exciting) alert him to a traffic in human fetuses corroborated by his wife\'s enforced curettage. Witnessing the works in progress--growing rooms for human submarine colonies which will make human survival possible--he is also threatened with his own extinction betrayed by his own machine & he\'s made to consider various ethical conjectures & priorities: should one deny one\'s self--should the present be expendable in the interest of the future? While not everybody\'s book, Abe\'s conceptual startler has a chilly precision which makes the unthinkable only too threateningly possible.--Kirkus','8.jpg','22d5ac09-a8fd-45bd-acb6-1e433f26ef2a2.jpg','2019-03-10 00:00:00'),(9,'Under the Dome','Stephen King','2009',519,7,'On an entirely normal, beautiful fall day in Chester’s Mill, Maine, the town is inexplicably and suddenly sealed off from the rest of the world by an invisible force field. Planes crash into it and fall from the sky in flaming wreckage, a gardener’s hand is severed as “the dome” comes down on it, people running errands in the neighboring town are divided from their families, and cars explode on impact. No one can fathom what this barrier is, where it came from, and when—or if—it will go away. Dale Barbara, Iraq vet and now a short-order cook, finds himself teamed with a few intrepid citizens—town newspaper owner Julia Shumway, a physician’s assistant at the hospital, a selectwoman, and three brave kids. Against them stands Big Jim Rennie, a politician who will stop at nothing—even murder—to hold the reins of power, and his son, who is keeping a horrible secret in a dark pantry. But their main adversary is the Dome itself. Because time isn’t just short. It’s running out.','9.jpg','300c7512-936d-4c40-8c3e-ed3a395611112.jpg','2019-03-10 00:00:00'),(10,'A Sound of Thunder','Ray Bradbury','1999',369,7,'The short story, A Sound of Thunder, involves a Time Travel Safari where rich businessmen pay to travel back to prehistoric times and hunt real live dinosaurs.','10.jpg','8a894d64-4eb9-4587-a31d-1cf5231baf452.jpg','2019-03-10 00:00:00'),(14,'The Shining','Stephen King','1980',459,7,'Jack Torrance\'s new job at the Overlook Hotel is the perfect chance for a fresh start. As the off-season caretaker at the atmospheric old hotel, he\'ll have plenty of time to spend reconnecting with his family and working on his writing. But as the harsh winter weather sets in, the idyllic location feels ever more remote...and more sinister. And the only one to notice the strange and terrible forces gathering around the Overlook is Danny Torrance, a uniquely gifted five-year-old.','eebe031f-1830-4b23-9af4-c41e8543ddffmd.jpg','f1f8dec9-d726-4ad2-b584-a9b7d26269632.jpg','2019-03-28 09:54:18'),(16,'Harry Potter and the Deathly Hallows','Joanne Rowling','2007',629,5,'Harry Potter is leaving Privet Drive for the last time. But as he climbs into the sidecar of Hagrid’s motorbike and they take to the skies, he knows Lord Voldemort and the Death Eaters will not be far behind. The protective charm that has kept him safe until now is broken. But the Dark Lord is breathing fear into everything he loves. And he knows he can’t keep hiding. To stop Voldemort, Harry knows he must find the remaining Horcruxes and destroy them. He will have to face his enemy in one final battle.','d8fd5301-a1ec-43d2-8528-7a765cd43b0c2.jpg','8f4faa0f-d30e-4db2-bb09-9787e388a1da1.jpg','2019-04-03 09:23:06'),(18,'Harry Potter and the Goblet of Fire','Joanne Rowling','2000',519,5,'Harry Potter is midway through his training as a wizard and his coming of age. Harry wants to get away from the pernicious Dursleys and go to the International Quidditch Cup. He wants to find out about the mysterious event that\'s supposed to take place at Hogwarts this year, an event involving two other rival schools of magic, and a competition that hasn\'t happened for a hundred years. He wants to be a normal, fourteen-year-old wizard. But unfortunately for Harry Potter, he\'s not normal - even by wizarding standards. And in his case, different can be deadly.','31d047d2-9b83-4c74-aea8-a0891d01c2751.jpg','928c0bce-4478-46a8-aa6a-96294e7c43332.jpg','2019-04-05 06:11:28');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_category`
--

DROP TABLE IF EXISTS `product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `product_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_category_product_idx` (`product_id`),
  KEY `fk_product_category_idx` (`category_id`),
  CONSTRAINT `fk_category_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_product_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_category`
--

LOCK TABLES `product_category` WRITE;
/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
INSERT INTO `product_category` VALUES (1,1,12),(3,1,14),(18,1,10),(23,1,6),(24,6,1),(27,6,18),(28,7,1),(32,7,17),(33,7,18),(35,8,1),(39,9,1),(42,9,18),(45,14,14),(46,14,10),(50,18,2),(56,9,29),(69,2,1),(70,2,15),(71,2,17),(72,2,29),(83,5,1),(84,5,18),(89,4,1),(90,4,17),(91,4,18),(94,16,2),(95,16,9),(96,10,1),(97,10,29);
/*!40000 ALTER TABLE `product_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_stats`
--

DROP TABLE IF EXISTS `product_stats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `product_stats` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `month` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_stats_product_idx` (`product_id`),
  CONSTRAINT `fk_product_stats_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_stats`
--

LOCK TABLES `product_stats` WRITE;
/*!40000 ALTER TABLE `product_stats` DISABLE KEYS */;
INSERT INTO `product_stats` VALUES (16,14,3,3,2019),(17,5,2,3,2019),(18,2,1,3,2019),(19,1,2,3,2019),(20,7,2,3,2019),(21,10,1,3,2019),(22,6,1,3,2019),(23,9,4,3,2019),(24,8,2,3,2019),(25,10,6,4,2019),(26,2,6,4,2019),(27,9,7,4,2019),(28,8,6,4,2019),(29,14,7,4,2019),(30,1,7,4,2019),(31,5,6,4,2019),(32,6,6,4,2019),(33,18,7,4,2019),(34,7,6,4,2019),(35,16,5,4,2019),(36,4,5,4,2019),(49,18,3,5,2019),(50,16,3,5,2019),(51,9,2,5,2019),(52,10,3,5,2019),(53,14,4,5,2019),(54,8,4,5,2019),(55,7,3,5,2019),(56,4,2,5,2019),(57,2,3,5,2019),(58,1,5,5,2019),(59,5,3,5,2019),(60,6,3,5,2019);
/*!40000 ALTER TABLE `product_stats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_CLIENT'),(2,'ROLE_ADMIN'),(3,'ROLE_SUPER_ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `lastname` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `birthday` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone_number` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'John','Doe','1979-05-05','johnDoe@gmail.com','$2a$10$BYUh.bVI5qdudsMlj.zqiesSDOZiRFpT1NxzwbMO6s8Nt1lqOoh8K','+12345678901'),(10,'James','Gosling','1984-12-15','jamesGosling@gmail.com','$2a$10$U6/EGqZEs5dpc/pIbpAnleFy6k7zxMNic/SsMTVY/Df2XTPLgT8RG','+71234567890'),(11,'Linus','Torvalds','1879-01-01','linusTorvalds@gmail.com','$2a$10$k82SQi1HskGj.pzK1dNogeN5OLjCV8s4ec9FgBx8tw3ByDTCldDWi','+17894562310'),(12,'Donald','Knuth',NULL,'donaldKnuth@gmail.com','$2a$10$q/R0T3PDOlJaWpSflyIPYuyPn92wa.Ravt1Hkdx6./JmNRyhcbBuS','28608428'),(13,'Anders','Hejlsberg',NULL,'andersHejlsberg@gmail.com','$2a$10$/ADM5Q0.ZLU.ZthPO8C4SuyQui1R.qoLdyCRXeelp/5PWlQWY5xUm','53282288'),(21,'Bram','Cohen','1975-10-12','bramCohen@gmail.com','$2a$10$bwu0wYUvgKd/oX944Tlmp.e4NopPGAOy89S5oBfK6ddcN4/4zoTl6','+15887415698'),(22,'Brendan','Eich',NULL,'brendanEich@gmail.com','$2a$10$1VULRjAbLEc7v5D665/mfOmmLI87ONvW6fxTFqpv1iBFWC8zeR5lO','+18569875423'),(23,'Timothy','Berners-Lee','1955-06-07','timothyBerners-Lee@gmail.com','$2a$10$v6UIRPBzqI4BK5PrAeafTen.Yatzf3SfWEhXT7Xe/H0y5AII4zbMi','+15629518536'),(25,'Jane','Doe','1991-05-17','janeDoe@gmail.com','$2a$10$3l6rb17AYdF8lpoAaogBL.DFuqL9iEXmEc26Mjq0O5yTd5p2n6jOu','+74567896321');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_address`
--

DROP TABLE IF EXISTS `user_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `address_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_client_idx` (`user_id`),
  KEY `fk_client_addressx` (`user_id`),
  KEY `fk_client_address_idx` (`address_id`),
  CONSTRAINT `fk_user_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=173 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_address`
--

LOCK TABLES `user_address` WRITE;
/*!40000 ALTER TABLE `user_address` DISABLE KEYS */;
INSERT INTO `user_address` VALUES (95,12,15),(96,13,16),(161,21,38),(162,21,39),(163,11,40),(164,25,41),(167,10,44),(170,1,46),(171,23,47),(172,22,48);
/*!40000 ALTER TABLE `user_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_idx` (`user_id`),
  KEY `fk_role_idx` (`role_id`),
  CONSTRAINT `fk_role_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_user_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1,3),(2,10,1),(3,11,1),(4,12,1),(5,13,1),(13,21,1),(14,22,1),(15,23,1),(17,25,1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_stats`
--

DROP TABLE IF EXISTS `user_stats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_stats` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `sum` double NOT NULL,
  `month` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_stats_user_idx` (`user_id`),
  CONSTRAINT `fk_user_stats_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_stats`
--

LOCK TABLES `user_stats` WRITE;
/*!40000 ALTER TABLE `user_stats` DISABLE KEYS */;
INSERT INTO `user_stats` VALUES (1,10,2754,3,2019),(2,11,1696,3,2019),(3,1,1856,3,2019),(4,12,1936,3,2019),(5,13,3793,4,2019),(6,23,3203,4,2019),(7,10,4480,4,2019),(8,22,3383,4,2019),(9,11,3763,4,2019),(10,1,5288,4,2019),(11,25,4261,4,2019),(12,21,4769,4,2019),(13,12,2076,4,2019),(22,12,3653,5,2019),(23,10,1517,5,2019),(24,11,1547,5,2019),(25,21,1746,5,2019),(26,23,2604,5,2019),(27,22,1756,5,2019),(28,25,2415,5,2019),(29,1,2075,5,2019);
/*!40000 ALTER TABLE `user_stats` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-08  9:53:05
