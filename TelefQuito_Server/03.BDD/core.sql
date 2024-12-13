-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: core
-- ------------------------------------------------------
-- Server version	8.0.37

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
-- Table structure for table `amortizacion`
--

DROP TABLE IF EXISTS `amortizacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `amortizacion` (
  `COD_AMORTIZACION` int NOT NULL AUTO_INCREMENT,
  `COD_CREDITO` int NOT NULL,
  `CUOTA` int NOT NULL,
  `VALOR_CUOTA` decimal(10,2) NOT NULL,
  `INTERES_PAGADO` decimal(10,2) NOT NULL,
  `CAPITAL_PAGADO` decimal(10,2) NOT NULL,
  `SALDO` decimal(10,2) NOT NULL,
  PRIMARY KEY (`COD_AMORTIZACION`),
  KEY `FK_AMORTIZACION_CREDITO` (`COD_CREDITO`),
  CONSTRAINT `FK_AMORTIZACION_CREDITO` FOREIGN KEY (`COD_CREDITO`) REFERENCES `credito` (`COD_CREDITO`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `amortizacion`
--

LOCK TABLES `amortizacion` WRITE;
/*!40000 ALTER TABLE `amortizacion` DISABLE KEYS */;
INSERT INTO `amortizacion` VALUES (1,1,1,429.88,20.83,409.05,4590.95),(2,1,2,429.88,19.13,410.75,4180.20),(3,1,3,429.88,17.42,412.46,3767.74),(4,1,4,429.88,15.70,414.18,3353.56),(5,1,5,429.88,13.97,415.91,2937.65),(6,1,6,429.88,12.24,417.64,2519.99),(7,1,7,429.88,10.50,419.38,2100.61),(8,1,8,429.88,8.75,421.13,1679.48),(9,1,9,429.88,7.00,422.88,1256.60),(10,1,10,429.88,5.23,424.65,831.95),(11,1,11,429.88,3.47,426.41,405.54),(12,1,12,429.88,1.69,428.19,0.00),(14,7,1,454.84,68.75,386.09,4613.91),(15,7,2,454.84,63.44,391.40,4222.51),(16,7,3,454.84,58.06,396.78,3825.74),(17,7,4,454.84,52.60,402.23,3423.50),(18,7,5,454.84,47.07,407.77,3015.74),(19,7,6,454.84,41.47,413.37,2602.37),(20,7,7,454.84,35.78,419.06,2183.31),(21,7,8,454.84,30.02,424.82,1758.49),(22,7,9,454.84,24.18,430.66,1327.83),(23,7,10,454.84,18.26,436.58,891.25),(24,7,11,454.84,12.25,442.58,448.67),(25,7,12,454.84,6.17,448.67,0.00),(26,8,1,78.65,6.19,72.46,377.54),(27,8,2,78.65,5.19,73.46,304.08),(28,8,3,78.65,4.18,74.47,229.61),(29,8,4,78.65,3.16,75.49,154.12),(30,8,5,78.65,2.12,76.53,77.58),(31,8,6,78.65,1.07,77.58,0.00),(80,13,1,0.91,0.14,0.77,9.22),(81,13,2,0.91,0.13,0.78,8.44),(82,13,3,0.91,0.12,0.79,7.64),(83,13,4,0.91,0.11,0.80,6.84),(84,13,5,0.91,0.09,0.81,6.03),(85,13,6,0.91,0.08,0.83,5.20),(86,13,7,0.91,0.07,0.84,4.36),(87,13,8,0.91,0.06,0.85,3.51),(88,13,9,0.91,0.05,0.86,2.65),(89,13,10,0.91,0.04,0.87,1.78),(90,13,11,0.91,0.02,0.88,0.90),(91,13,12,0.91,0.01,0.90,0.00);
/*!40000 ALTER TABLE `amortizacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `COD_CLIENTE` int NOT NULL AUTO_INCREMENT,
  `CEDULA` varchar(100) NOT NULL,
  `NOMBRE` varchar(100) NOT NULL,
  `GENERO` varchar(1) NOT NULL,
  `FECHA_NACIMIENTO` date NOT NULL,
  PRIMARY KEY (`COD_CLIENTE`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'0102030405','Juan Carlos Pérez','M','1990-05-14'),(2,'0203040506','María López','F','1985-08-22'),(3,'0304050607','Carlos Sánchez','M','1978-12-05'),(4,'0405060708','Ana Gómez','F','1992-03-19'),(5,'0506070809','Lucía Rodríguez','F','2000-07-30'),(6,'1234567890','Luis Martínez','M','1995-06-15');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente_comercializadora`
--

DROP TABLE IF EXISTS `cliente_comercializadora`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente_comercializadora` (
  `codc_cliente` int NOT NULL AUTO_INCREMENT,
  `cedula` varchar(100) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `genero` varchar(1) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  PRIMARY KEY (`codc_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente_comercializadora`
--

LOCK TABLES `cliente_comercializadora` WRITE;
/*!40000 ALTER TABLE `cliente_comercializadora` DISABLE KEYS */;
INSERT INTO `cliente_comercializadora` VALUES (1,'1234567890','Juan Perez','M','1990-01-15'),(2,'0203040506','Maria Gomez','F','1985-05-20'),(3,'0506070809','Carlos Ruiz','M','1995-07-30'),(4,'2233445566','Ana Martinez','F','1992-03-10'),(5,'3344556677','Luis Fernandez','M','1988-11-25');
/*!40000 ALTER TABLE `cliente_comercializadora` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compras`
--

DROP TABLE IF EXISTS `compras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compras` (
  `cod_compra` int NOT NULL AUTO_INCREMENT,
  `forma_pago` varchar(45) NOT NULL,
  `fecha` date NOT NULL,
  `cod_telefono` int NOT NULL,
  `codc_cliente` int NOT NULL,
  `descuento` decimal(10,2) DEFAULT NULL,
  `precio_final` decimal(10,3) NOT NULL,
  PRIMARY KEY (`cod_compra`),
  KEY `cod_telefono_idx` (`cod_telefono`),
  KEY `codc_cliente_idx` (`codc_cliente`),
  CONSTRAINT `cod_telefono` FOREIGN KEY (`cod_telefono`) REFERENCES `telefonos` (`cod_telefono`),
  CONSTRAINT `codc_cliente` FOREIGN KEY (`codc_cliente`) REFERENCES `cliente_comercializadora` (`codc_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compras`
--

LOCK TABLES `compras` WRITE;
/*!40000 ALTER TABLE `compras` DISABLE KEYS */;
INSERT INTO `compras` VALUES (1,'Efectivo','2023-12-01',1,1,420.00,579.994),(2,'Crédito Directo','2023-12-02',2,2,NULL,799.990),(3,'Efectivo','2023-12-03',3,3,252.00,347.994),(4,'Crédito Directo','2023-12-04',4,4,NULL,729.990),(5,'Efectivo','2023-12-05',5,5,357.00,492.994),(6,'Efectivo','2024-12-13',1,1,420.00,579.994),(7,'Efectivo','2024-12-13',1,2,420.00,579.994),(9,'Crédito Directo','2024-12-13',5,3,NULL,0.000),(10,'Crédito Directo','2024-12-13',5,3,NULL,9.990);
/*!40000 ALTER TABLE `compras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credito`
--

DROP TABLE IF EXISTS `credito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `credito` (
  `COD_CREDITO` int NOT NULL AUTO_INCREMENT,
  `COD_CLIENTE` int NOT NULL,
  `MONTO` decimal(10,2) NOT NULL,
  `PLAZO_MESES` int NOT NULL,
  `TASA_INTERES` decimal(5,2) NOT NULL,
  `FECHA_INICIO` date NOT NULL,
  PRIMARY KEY (`COD_CREDITO`),
  KEY `FK_CREDITO_CLIENTE` (`COD_CLIENTE`),
  CONSTRAINT `FK_CREDITO_CLIENTE` FOREIGN KEY (`COD_CLIENTE`) REFERENCES `cliente` (`COD_CLIENTE`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credito`
--

LOCK TABLES `credito` WRITE;
/*!40000 ALTER TABLE `credito` DISABLE KEYS */;
INSERT INTO `credito` VALUES (1,1,5000.00,12,5.00,'2024-01-01'),(2,2,10000.00,24,4.50,'2024-01-01'),(3,3,7500.00,18,6.00,'2024-02-01'),(4,4,3000.00,6,7.00,'2024-03-01'),(7,1,5000.00,12,16.50,'2024-01-01'),(8,2,450.00,6,16.50,'2024-12-12'),(13,5,9.99,12,16.50,'2024-12-13');
/*!40000 ALTER TABLE `credito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuenta`
--

DROP TABLE IF EXISTS `cuenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuenta` (
  `NUM_CUENTA` varchar(8) NOT NULL,
  `COD_CLIENTE` int NOT NULL,
  `SALDO` decimal(10,2) NOT NULL,
  PRIMARY KEY (`NUM_CUENTA`),
  KEY `COD_CLIENTE_idx` (`COD_CLIENTE`),
  CONSTRAINT `COD_CLIENTE` FOREIGN KEY (`COD_CLIENTE`) REFERENCES `cliente` (`COD_CLIENTE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta`
--

LOCK TABLES `cuenta` WRITE;
/*!40000 ALTER TABLE `cuenta` DISABLE KEYS */;
INSERT INTO `cuenta` VALUES ('10000001',1,2500.00),('10000002',2,10000.50),('10000003',3,500.00),('10000004',4,750.75),('10000005',5,3000.00);
/*!40000 ALTER TABLE `cuenta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimiento`
--

DROP TABLE IF EXISTS `movimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movimiento` (
  `COD_MOVIMIENTO` int NOT NULL AUTO_INCREMENT,
  `NUM_CUENTA` varchar(8) NOT NULL,
  `TIPO` varchar(3) NOT NULL,
  `VALOR` decimal(10,2) NOT NULL,
  `FECHA` date NOT NULL,
  PRIMARY KEY (`COD_MOVIMIENTO`),
  KEY `NUM_CUENTA_idx` (`NUM_CUENTA`),
  CONSTRAINT `NUM_CUENTA` FOREIGN KEY (`NUM_CUENTA`) REFERENCES `cuenta` (`NUM_CUENTA`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimiento`
--

LOCK TABLES `movimiento` WRITE;
/*!40000 ALTER TABLE `movimiento` DISABLE KEYS */;
INSERT INTO `movimiento` VALUES (1,'10000001','DEP',200.00,'2024-12-01'),(2,'10000001','RET',150.00,'2024-12-02'),(3,'10000001','DEP',300.00,'2024-12-03'),(4,'10000001','RET',50.00,'2024-12-04'),(5,'10000001','DEP',100.00,'2024-12-05'),(6,'10000002','DEP',500.00,'2024-12-01'),(7,'10000002','RET',100.00,'2024-12-02'),(8,'10000002','DEP',200.00,'2024-12-03'),(9,'10000002','RET',300.00,'2024-12-04'),(10,'10000002','DEP',400.00,'2024-12-05'),(11,'10000003','RET',50.00,'2024-12-01'),(12,'10000003','DEP',100.00,'2024-12-02'),(13,'10000003','RET',30.00,'2024-12-03'),(14,'10000003','DEP',200.00,'2024-12-04'),(15,'10000003','RET',50.00,'2024-12-05'),(16,'10000004','DEP',300.00,'2024-12-01'),(17,'10000004','RET',150.00,'2024-12-02'),(18,'10000004','DEP',400.00,'2024-12-03'),(19,'10000004','RET',100.00,'2024-12-04'),(20,'10000004','DEP',200.00,'2024-12-05'),(21,'10000005','DEP',100.00,'2024-12-01'),(22,'10000005','RET',50.00,'2024-12-02'),(23,'10000005','DEP',150.00,'2024-12-03'),(24,'10000005','RET',200.00,'2024-12-04'),(25,'10000005','DEP',100.00,'2024-12-05'),(26,'10000001','RET',80.00,'2024-12-06'),(27,'10000002','DEP',120.00,'2024-12-06'),(28,'10000003','RET',20.00,'2024-12-06'),(29,'10000004','DEP',50.00,'2024-12-06'),(30,'10000005','RET',70.00,'2024-12-06'),(31,'10000001','DEP',90.00,'2024-12-07'),(32,'10000002','RET',60.00,'2024-12-07'),(33,'10000003','DEP',200.00,'2024-12-07'),(34,'10000004','RET',120.00,'2024-12-07'),(35,'10000005','DEP',180.00,'2024-12-07'),(36,'10000001','RET',100.00,'2024-12-08'),(37,'10000002','DEP',50.00,'2024-12-08'),(38,'10000003','RET',30.00,'2024-12-08'),(39,'10000004','DEP',70.00,'2024-12-08'),(40,'10000005','RET',20.00,'2024-12-08'),(41,'10000001','DEP',300.00,'2024-12-09'),(42,'10000002','RET',200.00,'2024-12-09'),(43,'10000003','DEP',500.00,'2024-12-09'),(44,'10000004','RET',150.00,'2024-12-09'),(45,'10000005','DEP',400.00,'2024-12-09'),(46,'10000001','RET',50.00,'2024-12-10'),(47,'10000002','DEP',150.00,'2024-12-10'),(48,'10000003','RET',100.00,'2024-12-10'),(49,'10000004','DEP',200.00,'2024-12-10'),(50,'10000005','RET',300.00,'2024-12-10');
/*!40000 ALTER TABLE `movimiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefonos`
--

DROP TABLE IF EXISTS `telefonos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `telefonos` (
  `cod_telefono` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  PRIMARY KEY (`cod_telefono`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefonos`
--

LOCK TABLES `telefonos` WRITE;
/*!40000 ALTER TABLE `telefonos` DISABLE KEYS */;
INSERT INTO `telefonos` VALUES (1,'iPhone 13',999.99),(2,'Samsung Galaxy S21',799.99),(3,'Google Pixel 6',599.99),(4,'OnePlus 9',729.99),(5,'Sony Xperia 5',9.99);
/*!40000 ALTER TABLE `telefonos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-13  3:29:23
