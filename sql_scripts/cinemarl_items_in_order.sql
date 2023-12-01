-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: cinemarl
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `items_in_order`
--

DROP TABLE IF EXISTS `items_in_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items_in_order` (
  `idItemsOrder` int NOT NULL AUTO_INCREMENT,
  `quantity` int DEFAULT NULL,
  `price` decimal(9,2) DEFAULT NULL,
  `idOrder` int NOT NULL,
  `idProduct` int DEFAULT NULL,
  `idProjection` int DEFAULT NULL,
  `normalSeats` varchar(400) DEFAULT NULL,
  `specialSeats` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`idItemsOrder`),
  KEY `idOrder` (`idOrder`),
  KEY `idProduct` (`idProduct`),
  KEY `idProjection` (`idProjection`),
  CONSTRAINT `items_in_order_ibfk_1` FOREIGN KEY (`idOrder`) REFERENCES `orders` (`idOrder`),
  CONSTRAINT `items_in_order_ibfk_2` FOREIGN KEY (`idProduct`) REFERENCES `products` (`idProduct`),
  CONSTRAINT `items_in_order_ibfk_3` FOREIGN KEY (`idProjection`) REFERENCES `projections` (`idProjection`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items_in_order`
--

LOCK TABLES `items_in_order` WRITE;
/*!40000 ALTER TABLE `items_in_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `items_in_order` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-30 21:45:44
