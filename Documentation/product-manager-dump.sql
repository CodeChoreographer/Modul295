-- MySQL dump 10.13  Distrib 8.2.0, for Win64 (x86_64)
--
-- Host: localhost    Database: product-manager
-- ------------------------------------------------------
-- Server version	8.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Electronics',1),(2,'Haushaltsgeräte',0),(4,'Home & Kitchen',1),(5,'Toys',1),(6,'Sports',1),(7,'Automotive',1),(8,'Health & Beauty',1),(9,'Office Supplies',1),(10,'Garden & Outdoors',1),(11,'Neue Kategorie3',1);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flyway_schema_history`
--

DROP TABLE IF EXISTS `flyway_schema_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flyway_schema_history` (
  `installed_rank` int NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flyway_schema_history`
--

LOCK TABLES `flyway_schema_history` WRITE;
/*!40000 ALTER TABLE `flyway_schema_history` DISABLE KEYS */;
INSERT INTO `flyway_schema_history` VALUES (1,'1','Create categories table','SQL','V1__Create_categories_table.sql',-1739967739,'root','2024-09-27 07:02:10',21,1),(2,'2','Create products table','SQL','V2__Create_products_table.sql',1895047139,'root','2024-09-27 07:02:10',50,1),(3,'3','Create users table','SQL','V3__Create_users_table.sql',1007380249,'root','2024-09-27 07:02:11',21,1);
/*!40000 ALTER TABLE `flyway_schema_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(500) NOT NULL,
  `description` mediumtext,
  `price` float NOT NULL,
  `stock` int NOT NULL,
  `sku` varchar(100) NOT NULL,
  `image` varchar(1000) DEFAULT NULL,
  `category_id` int NOT NULL,
  `active` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_category` (`category_id`),
  CONSTRAINT `FK_category` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Wireless Headphones','Bluetooth over-ear headphones with noise cancellation',89.99,50,'WH-2023','https://example.com/images/wh-2023.jpg',1,_binary '\0'),(2,'Produktname','Produktbeschreibung',29.99,100,'123456789','image-url.jpg',1,_binary ''),(5,'Action Figure Set','Set of 5 collectible action figures from popular series',39.99,200,'AF-320','https://example.com/images/action-figures.jpg',5,_binary '\0'),(6,'Mountain Bike Helmet','Lightweight helmet with shock absorption for biking',49.99,40,'MBH-350','https://example.com/images/bike-helmet.jpg',6,_binary '\0'),(7,'Car Vacuum Cleaner','Portable handheld vacuum cleaner for cars and trucks',24.99,60,'CV-240','https://example.com/images/car-vacuum.jpg',7,_binary '\0'),(8,'Organic Skincare Set','Includes cleanser, toner, and moisturizer',74.99,25,'SK-101','https://example.com/images/skincare-set.jpg',8,_binary '\0'),(9,'Desk Organizer','5-compartment desk organizer for office supplies',15.99,150,'DO-102','https://example.com/images/desk-organizer.jpg',9,_binary '\0'),(10,'Garden Tool Set','Includes spade, rake, and trowel for gardening',34.99,100,'GT-203','https://example.com/images/garden-tools.jpg',10,_binary '\0'),(11,'Produktname','Produktbeschreibung',29.99,100,'123456789','image-url.jpg',1,_binary '');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(255) NOT NULL,
  `active` bit(1) NOT NULL,
  `is_admin` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'john_doe','$2a$10$6FQHJiWQJ0pz2gwQ0d3L3eu7OacqvD0bVeobpGXiODsH3BWcQ5rVy','john@example.com',_binary '',_binary '\0'),(2,'jane_admin','$2a$10$6FQHJiWQJ0pz2gwQ0d3L3eu7OacqvD0bVeobpGXiODsH3BWcQ5rVy','jane@example.com',_binary '',_binary ''),(3,'alice_user','$2a$10$6FQHJiWQJ0pz2gwQ0d3L3eu7OacqvD0bVeobpGXiODsH3BWcQ5rVy','alice@example.com',_binary '',_binary '\0'),(4,'bob_smith','$2a$10$6FQHJiWQJ0pz2gwQ0d3L3eu7OacqvD0bVeobpGXiODsH3BWcQ5rVy','bob@example.com',_binary '',_binary '\0'),(5,'charlie_admin','$2a$10$6FQHJiWQJ0pz2gwQ0d3L3eu7OacqvD0bVeobpGXiODsH3BWcQ5rVy','charlie@example.com',_binary '',_binary ''),(6,'dave_user','$2a$10$6FQHJiWQJ0pz2gwQ0d3L3eu7OacqvD0bVeobpGXiODsH3BWcQ5rVy','dave@example.com',_binary '',_binary '\0'),(7,'ellen_manager','$2a$10$6FQHJiWQJ0pz2gwQ0d3L3eu7OacqvD0bVeobpGXiODsH3BWcQ5rVy','ellen@example.com',_binary '',_binary ''),(8,'frank_guest','$2a$10$6FQHJiWQJ0pz2gwQ0d3L3eu7OacqvD0bVeobpGXiODsH3BWcQ5rVy','frank@example.com',_binary '',_binary '\0'),(9,'george_super','$2a$10$6FQHJiWQJ0pz2gwQ0d3L3eu7OacqvD0bVeobpGXiODsH3BWcQ5rVy','george@example.com',_binary '',_binary ''),(10,'helen_user','$2a$10$6FQHJiWQJ0pz2gwQ0d3L3eu7OacqvD0bVeobpGXiODsH3BWcQ5rVy','helen@example.com',_binary '',_binary '\0'),(11,'Admin','$2a$10$kY36mI1QQOnlcqP8xkk8x.wt72D.FQLMK7Q7rX/XXi7RtInhooGnG','admin@example.com',_binary '',_binary ''),(13,'TestUser','$2a$10$gu/hwn08uSsDoRUjpgECf.IiEMzfEFtfr/WbCcnDb1RkhbhNhAhDi','testuser@example.com',_binary '',_binary '');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-27 13:58:11
