-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: sony
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `backgrounds`
--

DROP TABLE IF EXISTS `backgrounds`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `backgrounds` (
  `bgid` int NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL COMMENT '제품의 타입',
  `filepath` varchar(255) DEFAULT NULL COMMENT '파일 경로',
  PRIMARY KEY (`bgid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='배경이미지 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `backgrounds`
--

LOCK TABLES `backgrounds` WRITE;
/*!40000 ALTER TABLE `backgrounds` DISABLE KEYS */;
INSERT INTO `backgrounds` VALUES (1,'camera','/products/background/bg_cat_camera.jpg'),(2,'video','/products/background/bg_cat_video.jpg'),(3,'lens_change','/products/background/bg_lens_change.jpg'),(4,'compact','/products/background/bg_compact_camera.jpg'),(5,'cinema','/products/background/bg_cinema_camera.jpg'),(6,'camcorder','/products/background/bg_camcorder.jpg'),(7,'lens','/products/background/lens.jpg'),(8,'fullframe','/products/background/fullframe.jpg'),(9,'APS-C','/products/background/APS-C.jpg');
/*!40000 ALTER TABLE `backgrounds` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carts`
--

DROP TABLE IF EXISTS `carts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carts` (
  `cartid` int NOT NULL AUTO_INCREMENT COMMENT '?Ϸù',
  `count` int NOT NULL COMMENT '?',
  `memberid` int NOT NULL COMMENT 'ȸ???? ?Ϸù',
  `prodid` int NOT NULL COMMENT '??ǰ?? ?Ϸù',
  `color` varchar(45) DEFAULT NULL COMMENT '??ǰ?? ?',
  PRIMARY KEY (`cartid`),
  KEY `fk_carts_members1_idx` (`memberid`),
  KEY `fk_carts_products1_idx1` (`prodid`),
  CONSTRAINT `fk_carts_members1` FOREIGN KEY (`memberid`) REFERENCES `members` (`memberid`),
  CONSTRAINT `fk_carts_products1` FOREIGN KEY (`prodid`) REFERENCES `products` (`prodid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='???ٱ??';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carts`
--

LOCK TABLES `carts` WRITE;
/*!40000 ALTER TABLE `carts` DISABLE KEYS */;
INSERT INTO `carts` VALUES (13,1,23,4,'silver');
/*!40000 ALTER TABLE `carts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `colors`
--

DROP TABLE IF EXISTS `colors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `colors` (
  `colorid` int NOT NULL AUTO_INCREMENT COMMENT '???? ?',
  `color` varchar(45) NOT NULL COMMENT '?',
  `prodid` int DEFAULT NULL COMMENT '??ǰ?? ?Ϸù',
  `pcolor` enum('Y','N') NOT NULL DEFAULT 'N' COMMENT '기본색상',
  PRIMARY KEY (`colorid`),
  KEY `fk_colors_products1_idx1` (`prodid`),
  CONSTRAINT `fk_colors_products1` FOREIGN KEY (`prodid`) REFERENCES `products` (`prodid`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='색상';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `colors`
--

LOCK TABLES `colors` WRITE;
/*!40000 ALTER TABLE `colors` DISABLE KEYS */;
INSERT INTO `colors` VALUES (1,'white',1,'Y'),(2,'black',1,'N'),(3,'black',2,'Y'),(4,'white',2,'N'),(5,'silver',4,'Y'),(6,'black',4,'N'),(7,'white',5,'Y'),(8,'black',5,'N'),(9,'white',6,'Y'),(10,'black',6,'N'),(11,'black',15,'Y'),(12,'silver',15,'N'),(14,'default',3,'N'),(15,'default',7,'N'),(16,'default',8,'N'),(17,'default',9,'N'),(18,'default',10,'N'),(19,'default',11,'N'),(20,'default',12,'N'),(21,'default',13,'N'),(22,'default',14,'N'),(23,'default',16,'N');
/*!40000 ALTER TABLE `colors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `events` (
  `eventid` int NOT NULL AUTO_INCREMENT COMMENT '?Ϸù',
  `title` varchar(255) NOT NULL COMMENT '???',
  `caution` text COMMENT '???ǻ??',
  `thumbnail` varchar(255) NOT NULL COMMENT '??ȹ?? ??ǥ ?̹??? ???',
  `startdate` date DEFAULT NULL COMMENT '??ȹ?? ???۳?¥',
  `enddate` date DEFAULT NULL COMMENT '??ȹ?? ???ᳯ¥',
  `eventdesc` varchar(255) DEFAULT NULL COMMENT '??ȹ?? ???',
  `image` varchar(255) NOT NULL COMMENT '??ȹ?? ?????̹??? ???',
  PRIMARY KEY (`eventid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='??ȹ?';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events`
--

LOCK TABLES `events` WRITE;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
/*!40000 ALTER TABLE `events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `images`
--

DROP TABLE IF EXISTS `images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `images` (
  `imgid` int NOT NULL AUTO_INCREMENT COMMENT '?Ϸù',
  `filepath` varchar(255) NOT NULL COMMENT '?̹??? ???? ???',
  `thumbnail` enum('Y','N') NOT NULL DEFAULT 'N' COMMENT '??ǰ ??ǥ ?̹??',
  `prodid` int NOT NULL COMMENT '??ǰ?? ?Ϸù',
  `colorid` int DEFAULT NULL COMMENT '?????? ?Ϸù',
  PRIMARY KEY (`imgid`),
  KEY `fk_images_products1_idx` (`prodid`),
  KEY `fk_images_colors1_idx` (`colorid`),
  CONSTRAINT `fk_images_colors1` FOREIGN KEY (`colorid`) REFERENCES `colors` (`colorid`),
  CONSTRAINT `fk_images_products1` FOREIGN KEY (`prodid`) REFERENCES `products` (`prodid`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8 COMMENT='??ǰ ?̹??';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `images`
--

LOCK TABLES `images` WRITE;
/*!40000 ALTER TABLE `images` DISABLE KEYS */;
INSERT INTO `images` VALUES (1,'/products/ZV-E10M2K/clr0_0.png','Y',1,1),(2,'/products/ZV-E10M2K/clr0_1.png','N',1,1),(3,'/products/ZV-E10M2K/clr0_2.png','N',1,1),(4,'/products/ZV-E10M2K/clr0_3.png','N',1,1),(5,'/products/ZV-E10M2K/clr0_4.png','N',1,1),(6,'/products/ZV-E10M2K/clr0_5.png','N',1,1),(7,'/products/ZV-E10M2K/clr0_6.png','N',1,1),(8,'/products/ZV-E10M2K/clr0_7.png','N',1,1),(9,'/products/ZV-E10M2K/clr1_0.png','Y',1,2),(10,'/products/ZV-E10M2K/clr1_1.png','N',1,2),(11,'/products/ZV-E10M2K/clr1_2.png','N',1,2),(12,'/products/ZV-E10M2K/clr1_3.png','N',1,2),(13,'/products/ZV-E10M2K/clr1_4.png','N',1,2),(14,'/products/ZV-E10M2K/clr1_5.png','N',1,2),(15,'/products/ZV-E10M2K/clr1_6.png','N',1,2),(16,'/products/ZV-E10M2K/clr1_7.png','N',1,2),(17,'/products/ZV-E10M2/clr0_0.png','Y',2,3),(18,'/products/ZV-E10M2/clr0_1.png','N',2,3),(19,'/products/ZV-E10M2/clr0_2.png','N',2,3),(20,'/products/ZV-E10M2/clr0_3.png','N',2,3),(21,'/products/ZV-E10M2/clr0_4.png','N',2,3),(22,'/products/ZV-E10M2/clr0_5.png','N',2,3),(23,'/products/ZV-E10M2/clr1_0.png','Y',2,4),(24,'/products/ZV-E10M2/clr1_1.png','N',2,4),(25,'/products/ZV-E10M2/clr1_2.png','N',2,4),(26,'/products/ZV-E10M2/clr1_3.png','N',2,4),(27,'/products/ZV-E10M2/clr1_4.png','N',2,4),(28,'/products/ZV-E10M2/clr1_5.png','N',2,4),(29,'/products/ILCE-9M3/clr0_0.png','Y',3,NULL),(30,'/products/ILCE-9M3/clr0_1.png','N',3,NULL),(31,'/products/ILCE-9M3/clr0_2.png','N',3,NULL),(32,'/products/ILCE-9M3/clr0_3.png','N',3,NULL),(33,'/products/ILCE-7CM2/clr0_0.png','Y',4,5),(34,'/products/ILCE-7CM2/clr0_1.png','N',4,5),(35,'/products/ILCE-7CM2/clr0_2.png','N',4,5),(36,'/products/ILCE-7CM2/clr0_3.png','N',4,5),(37,'/products/ILCE-7CM2/clr1_0.png','Y',4,6),(38,'/products/ILCE-7CM2/clr1_1.png','N',4,6),(39,'/products/ILCE-7CM2/clr1_2.png','N',4,6),(40,'/products/ILCE-7CM2/clr1_3.png','N',4,6),(41,'/products/ZV-1F/clr0_0.png','Y',5,7),(42,'/products/ZV-1F/clr0_1.png','N',5,7),(43,'/products/ZV-1F/clr0_2.png','N',5,7),(44,'/products/ZV-1F/clr0_3.png','N',5,7),(45,'/products/ZV-1F/clr1_0.png','Y',5,8),(46,'/products/ZV-1F/clr1_1.png','N',5,8),(47,'/products/ZV-1F/clr1_2.png','N',5,8),(48,'/products/ZV-1F/clr1_3.png','N',5,8),(49,'/products/ZV-1/clr0_0.png','Y',6,9),(50,'/products/ZV-1/clr0_1.png','N',6,9),(51,'/products/ZV-1/clr0_2.png','N',6,9),(52,'/products/ZV-1/clr0_3.png','N',6,9),(53,'/products/ZV-1/clr0_4.png','N',6,9),(54,'/products/ZV-1/clr1_0.png','Y',6,10),(55,'/products/ZV-1/clr1_1.png','N',6,10),(56,'/products/ZV-1/clr1_2.png','N',6,10),(57,'/products/ZV-1/clr1_3.png','N',6,10),(58,'/products/ZV-1/clr1_4.png','N',6,10),(59,'/products/ZV-1/clr1_5.png','N',6,10),(60,'/products/BURANO/clr0_0.png','Y',7,NULL),(61,'/products/BURANO/clr0_1.png','N',7,NULL),(62,'/products/BURANO/clr0_2.png','N',7,NULL),(63,'/products/BURANO/clr0_3.png','N',7,NULL),(64,'/products/BURANO/clr0_4.png','N',7,NULL),(65,'/products/BURANO/clr0_5.png','N',7,NULL),(66,'/products/ILME-FR7/clr0_0.png','Y',8,NULL),(67,'/products/ILME-FR7/clr0_1.png','N',8,NULL),(68,'/products/ILME-FR7/clr0_2.png','N',8,NULL),(69,'/products/ILME-FR7/clr0_3.png','N',8,NULL),(71,'/products/FDR-AX43A/clr0_0.png','Y',9,NULL),(72,'/products/FDR-AX43A/clr0_1.png','N',9,NULL),(73,'/products/FDR-AX43A/clr0_2.png','N',9,NULL),(74,'/products/FDR-AX43A/clr0_3.png','N',9,NULL),(75,'/products/FDR-AX43A/clr0_4.png','N',9,NULL),(76,'/products/FDR-AX700/clr0_0.png','Y',10,NULL),(77,'/products/FDR-AX700/clr0_1.png','N',10,NULL),(78,'/products/FDR-AX700/clr0_2.png','N',10,NULL),(79,'/products/FDR-AX700/clr0_3.png','N',10,NULL),(80,'/products/FDR-AX700/clr0_4.png','N',10,NULL),(81,'/products/HXR-NX800/clr0_0.png','Y',11,NULL),(82,'/products/HXR-NX800/clr0_1.png','N',11,NULL),(83,'/products/HXR-NX800/clr0_2.png','N',11,NULL),(84,'/products/HXR-NX800/clr0_3.png','N',11,NULL),(85,'/products/PXW-Z200/clr0_0.png','Y',12,NULL),(86,'/products/PXW-Z200/clr0_1.png','N',12,NULL),(87,'/products/PXW-Z200/clr0_2.png','N',12,NULL),(88,'/products/PXW-Z200/clr0_3.png','N',12,NULL),(89,'/products/PXW-Z200/clr0_4.png','N',12,NULL),(90,'/products/SEL2450G/Lens1.png','Y',13,NULL),(91,'/products/SEL2450G/Lens2.png','N',13,NULL),(92,'/products/SEL2450G/Lens3.png','N',13,NULL),(93,'/products/SEL2450G/Lens4.png','N',13,NULL),(94,'/products/SEL300F28GM/Lens1.png','Y',14,NULL),(95,'/products/SEL300F28GM/Lens2.png','N',14,NULL),(96,'/products/SEL300F28GM/Lens3.png','N',14,NULL),(97,'/products/SEL300F28GM/Lens4.png','N',14,NULL),(98,'/products/SEL55210/Lens1.png','Y',15,11),(99,'/products/SEL55210/Lens2.png','N',15,11),(100,'/products/SEL55210/Lens3.png','Y',15,12),(101,'/products/SEL70350G/Lens1.png','Y',16,NULL),(102,'/products/SEL70350G/Lens2.png','N',16,NULL),(103,'/products/SEL70350G/Lens3.png','N',16,NULL);
/*!40000 ALTER TABLE `images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `members` (
  `memberid` int NOT NULL AUTO_INCREMENT COMMENT 'ȸ???? ?Ϸù',
  `email` varchar(45) NOT NULL COMMENT '?̸??',
  `userpw` varchar(255) NOT NULL COMMENT '???й',
  `username` varchar(45) NOT NULL COMMENT '?̸?',
  `gender` enum('M','F') NOT NULL COMMENT '???? (M/F)',
  `birthdate` date NOT NULL COMMENT '?????',
  `phone` varchar(20) NOT NULL COMMENT '?޴????',
  `editdate` datetime NOT NULL COMMENT '?????Ͻ',
  `postcode` char(5) DEFAULT NULL COMMENT '?????',
  `addr1` varchar(255) DEFAULT NULL COMMENT '?˻??? ?ּ',
  `addr2` varchar(255) DEFAULT NULL COMMENT '???? ?ּ',
  `isout` enum('Y','N') NOT NULL DEFAULT 'N' COMMENT 'Ż?? ???? (Y/N)',
  `logindate` datetime DEFAULT NULL COMMENT '?????? ?α??? ?Ͻ',
  `regdate` datetime NOT NULL COMMENT '?????Ͻ',
  `isadmin` enum('Y','N') NOT NULL DEFAULT 'N' COMMENT '?????? ???? (Y/ N)',
  `receiveemail` enum('Y','N') NOT NULL DEFAULT 'N' COMMENT '광고성 정보 이메일 수신 여부 (Y/N)',
  `receivesms` enum('Y','N') NOT NULL DEFAULT 'N' COMMENT '광고성 정보 문자 수신 여부 (Y/N)',
  PRIMARY KEY (`memberid`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='ȸ??????';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` VALUES (18,'jinsu4205@gmail.com','Aa123456789@','박진수','F','1996-09-05','01033063205','2024-12-18 11:48:13','06779','서울 서초구 강남대로 48-3 (양재동)','102호','Y','2024-12-18 11:44:02','2024-12-02 16:49:06','N','Y','Y'),(19,'wlstn4205@gmailw.com','Aa123456789@@','박진수지메일','M','1996-09-05','01033062222','2024-12-05 16:33:50',NULL,NULL,NULL,'N','2024-12-05 17:47:22','2024-12-05 16:33:50','N','Y','Y'),(20,'jinsu4205@gmail.comm','Aa123456789@@','박진수수','F','1996-09-04','01045782656','2024-12-05 17:52:50',NULL,NULL,NULL,'N','2024-12-05 17:53:20','2024-12-05 17:52:50','N','N','N'),(21,'leekh4232@gmail.com','1234qwer!@#$','박진수','M','2027-01-03','01022084232','2024-12-06 17:43:14',NULL,NULL,NULL,'N',NULL,'2024-12-06 17:43:14','N','N','N'),(22,'jinsu4205@gmail.coma','Aa123456789@@','박진수','M','2025-02-06','01011111111','2024-12-06 17:46:16',NULL,NULL,NULL,'N',NULL,'2024-12-06 17:46:16','N','Y','Y'),(23,'leekh4232@kakao.com','Aa123456789@@','박진수','M','2024-12-06','01033064212','2024-12-06 17:48:18',NULL,NULL,NULL,'N','2024-12-06 17:48:31','2024-12-06 17:48:18','N','Y','Y');
/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notices`
--

DROP TABLE IF EXISTS `notices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notices` (
  `noticeid` int NOT NULL AUTO_INCREMENT COMMENT '?',
  `title` varchar(255) NOT NULL COMMENT '???',
  `regdate` date NOT NULL COMMENT '???????',
  `watch` int NOT NULL DEFAULT '0' COMMENT '??ȸ?',
  `content` text NOT NULL COMMENT '???????? ???',
  PRIMARY KEY (`noticeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='???????';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notices`
--

LOCK TABLES `notices` WRITE;
/*!40000 ALTER TABLE `notices` DISABLE KEYS */;
/*!40000 ALTER TABLE `notices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paylist`
--

DROP TABLE IF EXISTS `paylist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paylist` (
  `paylistid` int NOT NULL AUTO_INCREMENT COMMENT '?????? ??ǰ?? ?Ϸù',
  `payid` int NOT NULL COMMENT '결제내역의 일련번호',
  `prodid` int DEFAULT NULL COMMENT '상품의 일련번호',
  `prodthumbnail` varchar(255) DEFAULT NULL COMMENT '상품의 대표 이미지 경로',
  `prodtitle` varchar(45) NOT NULL COMMENT '??ǰ?? ?̸?',
  `prodcolor` varchar(45) DEFAULT NULL COMMENT '구매한 상품의 색상',
  `count` int NOT NULL COMMENT '상품의 수량',
  `prodprice` int NOT NULL COMMENT '??ǰ?? ???',
  PRIMARY KEY (`paylistid`),
  KEY `fk_paylist_payments_idx` (`payid`),
  CONSTRAINT `fk_paylist_payments` FOREIGN KEY (`payid`) REFERENCES `payments` (`payid`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='???? ??ǰ ?';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paylist`
--

LOCK TABLES `paylist` WRITE;
/*!40000 ALTER TABLE `paylist` DISABLE KEYS */;
INSERT INTO `paylist` VALUES (1,1,4,'/files/products/ILCE-7CM2/clr1_0.png','ILCE-7CM2','black',1,2690000),(2,2,3,'/files/products/ILCE-9M3/clr0_0.png','ILCE-9M3','null',1,7980000),(9,6,4,'/files/products/ILCE-7CM2/clr0_0.png','ILCE-7CM2','silver',11,2690000),(10,6,4,'/files/products/ILCE-7CM2/clr1_0.png','ILCE-7CM2','black',4,2690000),(12,8,2,'/files/products/ZV-E10M2/clr0_0.png','ZV-E10M2','black',1,1340000),(13,9,2,'/files/products/ZV-E10M2/clr0_0.png','ZV-E10M2','black',1,1340000),(17,13,2,'/files/products/ZV-E10M2/clr1_0.png','ZV-E10M2','white',1,1340000),(18,14,2,'/files/products/ZV-E10M2/clr1_0.png','ZV-E10M2','white',1,1340000),(19,15,1,'/files/products/ZV-E10M2K/clr0_0.png','ZV-E10M2K','white',1,1490000),(21,17,2,'/files/products/ZV-E10M2/clr1_0.png','ZV-E10M2','white',1,1340000),(22,18,2,'/files/products/ZV-E10M2/clr1_0.png','ZV-E10M2','white',1,1340000),(23,19,1,'/files/products/ZV-E10M2K/clr0_0.png','ZV-E10M2K','white',1,1490000),(24,20,2,'/files/products/ZV-E10M2/clr1_0.png','ZV-E10M2','white',1,1340000);
/*!40000 ALTER TABLE `paylist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS `payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payments` (
  `payid` int NOT NULL AUTO_INCREMENT COMMENT '?Ϸù',
  `date` datetime DEFAULT NULL COMMENT '결제날짜',
  `status` varchar(10) NOT NULL COMMENT 'ó???',
  `memberid` int DEFAULT NULL COMMENT '회원의 일련번호',
  `ordername` varchar(30) DEFAULT NULL COMMENT '주문자 이름',
  `orderemail` varchar(45) DEFAULT NULL,
  `orderphone` varchar(20) DEFAULT NULL COMMENT '주문자 휴대폰번호',
  `receivername` varchar(30) DEFAULT NULL COMMENT '?????? ?̸?',
  `receiverphone` varchar(20) DEFAULT NULL COMMENT '수령인 휴대폰번호',
  `postcode` char(5) DEFAULT NULL COMMENT '?????',
  `addr1` varchar(45) DEFAULT NULL COMMENT '?˻??? ?ּ',
  `addr2` varchar(45) DEFAULT NULL COMMENT '???? ?ּ',
  `request` varchar(45) DEFAULT NULL COMMENT '???? ??û ???',
  `dlvdate` varchar(45) DEFAULT NULL COMMENT '?????',
  `totalcount` int NOT NULL DEFAULT '0' COMMENT '??ü ?',
  `total` int NOT NULL COMMENT '총 결제금액',
  `payoption` varchar(20) NOT NULL COMMENT '결제방법',
  `insertdate` datetime NOT NULL COMMENT '구매 희망 일시',
  `paycheck` enum('Y','N') NOT NULL DEFAULT 'N' COMMENT '결제유무',
  PRIMARY KEY (`payid`),
  KEY `fk_payments_members1_idx` (`memberid`),
  CONSTRAINT `fk_payments_members1` FOREIGN KEY (`memberid`) REFERENCES `members` (`memberid`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='????';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments`
--

LOCK TABLES `payments` WRITE;
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
INSERT INTO `payments` VALUES (1,'2024-12-06 10:55:52','결제완료',18,'박진숭','jinsu4205@gmail.com','01033063205','박진수','01033063205','06100','서울 강남구 선릉로 635 (논현동)','123','null','null',1,2690000,'신용카드','2024-12-06 10:55:12','Y'),(2,'2024-12-06 11:30:02','결제완료',18,'박진숭','jinsu4205@gmail.com','01033063205','박재한','01045786589','18148','경기 오산시 부산중앙로 11 (부산동, 오산시티자이 1단지)','101호','부재 시 경비실에 맡겨 주세요.','2024-12-11',1,7980000,'네이버페이','2024-12-06 11:29:19','Y'),(6,'2024-12-06 11:37:03','결제완료',18,'박재한','jinsu4205@gmail.com','01033063205','박재한','01045786589','47874','부산 동래구 종합운동장로 29 (사직동)','101호','null','2024-12-26',15,40350000,'신용카드','2024-12-06 11:36:25','Y'),(8,'2024-12-06 16:13:30','결제완료',18,'박재한','jinsu4205@gmail.com','01033063205','박재한','01045786589','','','101호','null','null',1,1340000,'신용카드','2024-12-06 16:09:51','Y'),(9,'2024-12-06 16:17:00','결제완료',18,'박재한','jinsu4205@gmail.com','01033063205','박재한','01045786589','','','101호','null','null',1,1340000,'신용카드','2024-12-06 16:16:45','Y'),(13,'2024-12-06 16:27:41','결제완료',18,'박재한','jinsu4205@gmail.com','01033063205','박재한','01045786589','','','101호','null','null',1,1340000,'신용카드','2024-12-06 16:27:33','Y'),(14,'2024-12-06 16:28:22','결제완료',18,'박재한','jinsu4205@gmail.com','01033063205','박재한','01045786589','','','101호','null','null',1,1340000,'신용카드','2024-12-06 16:28:14','Y'),(15,'2024-12-06 16:30:23','결제완료',18,'박재한','jinsu4205@gmail.com','01033063205','박재한','01045786589','','','101호','null','null',1,1490000,'신용카드','2024-12-06 16:29:46','Y'),(17,'2024-12-11 15:19:15','결제완료',18,'박재한','jinsu4205@gmail.com','01033063205','박재한','01045786589','18148','경기 오산시 부산중앙로 11 (부산동, 오산시티자이 1단지)','101호','null','null',1,1340000,'신용카드','2024-12-11 15:18:57','Y'),(18,'2024-12-11 15:26:36','결제완료',18,'박재한','jinsu4205@gmail.com','01033063205','박재한','01045786589','47874','부산 동래구 종합운동장로 29 (사직동)','101호','null','null',1,1340000,'신용카드','2024-12-11 15:26:29','Y'),(19,'2024-12-11 15:28:02','결제완료',18,'박재한','jinsu4205@gmail.com','01033063205','박재한','01045786589','18148','경기 오산시 부산중앙로 11 (부산동, 오산시티자이 1단지)','101호','null','null',1,1490000,'신용카드','2024-12-11 15:27:52','Y'),(20,'2024-12-11 17:58:57','결제완료',18,'박재한','jinsu4205@gmail.com','01033063205','박진수','01033063205','06100','서울 강남구 선릉로 635 (논현동)','123','null','null',1,1340000,'신용카드','2024-12-11 17:58:50','Y');
/*!40000 ALTER TABLE `payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `prodid` int NOT NULL AUTO_INCREMENT COMMENT '?Ϸù',
  `title` varchar(45) NOT NULL COMMENT '??ǰ?',
  `proddesc` varchar(255) DEFAULT NULL COMMENT '??ǰ ???',
  `price` int NOT NULL COMMENT '??ǰ ???',
  `type1` varchar(45) NOT NULL COMMENT '??ǰ ī?װ??? 1',
  `type2` varchar(45) DEFAULT NULL COMMENT '??ǰ ī?װ??? 2',
  `type3` varchar(45) DEFAULT NULL COMMENT '??ǰ ī?װ??? 3',
  `date` date DEFAULT NULL COMMENT '?????',
  `detailimage1` varchar(255) NOT NULL COMMENT '??ǰ ?????? ?̹??? ???? 1',
  `youtube` varchar(255) DEFAULT NULL COMMENT '??ǰ ???? ??Ʃ?? ???',
  `detailgif` varchar(255) DEFAULT NULL COMMENT '??ǰ ?????? gif ???',
  `detailimage2` varchar(255) DEFAULT NULL COMMENT '??ǰ ?????? ?̹??? ???? 2',
  `detailspec` varchar(255) DEFAULT NULL COMMENT '??ǰ ?????? ???',
  `soldout` enum('Y','N') NOT NULL DEFAULT 'N' COMMENT '??ǰ?? ǰ?????',
  `sale` enum('Y','N') NOT NULL DEFAULT 'N' COMMENT '??ǰ?? ???? ????',
  `event` enum('Y','N') NOT NULL DEFAULT 'N' COMMENT '??ǰ?? ??ȹ?? ????',
  `keyword` varchar(255) DEFAULT NULL COMMENT '검색 결과 키워드',
  PRIMARY KEY (`prodid`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='?';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'ZV-E10M2K','기록의 완성',1490000,'camera','lens_change','APS-C','2024-07-01','/products/ZV-E10M2K/zv-e10m2_960_1.jpg','https://www.youtube.com/embed/JXDZVOCB4Ts','/products/ZV-E10M2K/zv-e10m2_360_960.webp','/products/ZV-E10M2K/zv-e10m2_960_2.jpg','/products/ZV-E10M2K/zv-e10m2_960_spec.gif','N','Y','Y','ZV-E10M2K, 카메라, 렌즈 체인지, APS-C, camera'),(2,'ZV-E10M2','기록의 완성',1340000,'camera','lens_change','APS-C','2024-07-01','/products/ZV-E10M2/zv-e10m2_960_1.jpg','https://www.youtube.com/embed/JXDZVOCB4Ts','/products/ZV-E10M2/zv-e10m2_360_960.webp','/products/ZV-E10M2/zv-e10m2_960_2.jpg','/products/ZV-E10M2/zv-e10m2_960_spec.gif','N','N','N','ZV-E10M2, 카메라, 렌즈 체인지, APS-C, camera'),(3,'ILCE-9M3','The Power of One Frame',7980000,'camera','lens_change','풀프레임','2024-01-01','/products/ILCE-9M3/ilce-9m3_960_1.jpg','','/products/ILCE-9M3/ilce-9m3_960_shutter.gif','/products/ILCE-9M3/ilce-9m3_960_2.jpg','/products/ILCE-9M3/ilce-9m3_960_spec.gif','N','N','N','ILCE-9M3, 카메라, 렌즈 체인지, 풀프레임, camera'),(4,'ILCE-7CM2','원핸드 컴팩트 풀프레임',2690000,'camera','lens_change','풀프레임','2024-09-01','/products/ILCE-7CM2/detail1.jpg','https://www.youtube.com/embed/-D3Oc4lgVRw','/products/ILCE-7CM2/gif.gif','/products/ILCE-7CM2/detail2.jpg','/products/ILCE-7CM2/spec.jpg','N','N','N','ILCE-7CM2 , 카메라, 렌즈 체인지, 풀프레임, camera'),(5,'ZV-1F','나를 담는 브이로그 카메라 ZV-1F',759000,'camera','compact','','2022-08-01','/products/ZV-1F/detail1.jpg','','/products/ZV-1F/gif.gif','/products/ZV-1F/detail2.jpg','/products/ZV-1F/spec.jpg','N','N','N','ZV-1F, 카메라,컴팩트, camera'),(6,'ZV-1','나의 첫번째 브이로그 카메라',999000,'camera','compact','','2020-04-01','/products/ZV-1/detail1.jpg','','/products/ZV-1/gif.gif','/products/ZV-1/detail2.jpg','/products/ZV-1/spec.jpg','N','N','N','ZV-1 , 카메라, 컴팩트, camera'),(7,'BURANO','풀프레임 8.6K 시네마 카메라',36990000,'video','cinema','','2024-02-01','/products/BURANO/detail1.jpg','','','/products/BURANO/detail2.jpg','/products/BURANO/spec.jpg','N','N','N','BURANO, 비디오 카메라, 시네마, video'),(8,'ILME-FR7','시네마라인 렌즈교환식 PTZ 카메라',14999000,'video','cinema','','2022-11-01','/products/ILME-FR7/detail1.jpg','','','','/products/ILME-FR7/spec.jpg','N','N','N','ILME-FR7, 비디오 카메라, 시네마, video'),(9,'FDR-AX43A','콘텐츠 크리에이터를 위한 4K 캠코더',1449000,'video','camcorder','4k핸디캠','2022-06-01','/products/FDR-AX43A/detail1.jpg','https://www.youtube.com/embed/GEP8M3KzWvo','/products/FDR-AX43A/gif.jpg','/products/FDR-AX43A/detail2.jpg','/products/FDR-AX43A/spec.jpg','N','N','N','FDR-AX43A, 비디오 카메라, 캠코더, 4k 핸디캠, video'),(10,'FDR-AX700','4K HDR(HLG), Fast Hybrid AF가 탑재된 전문가급 1인치 핸디캠',2499000,'video','camcorder','4k핸디캠','2017-10-01','/products/FDR-AX700/detail1.jpg','','/products/FDR-AX700/gif.jpg','/products/FDR-AX700/detail2.jpg','/products/FDR-AX700/spec.jpg','N','N','N','FDR-AX700, 비디오 카메라, 캠코더, 4k 핸디캠, video'),(11,'HXR-NX800','AI로 완성하다 : 차세대 4K 프로페셔널 캠코더',4720000,'video','camcorder','4k핸드헬드','2024-09-01','/products/HXR-NX800/detail1.jpg','','','','/products/HXR-NX800/spec.gif','N','N','N','HXR-NX800, 비디오 카메라, 캠코더, 4k 핸디캠, video'),(12,'PXW-Z200','AI로 완성하다 : 차세대 4K 프로페셔널 캠코더',5590000,'video','camcorder','4k핸드헬드','2024-09-01','/products/PXW-Z200/detail1.jpg','','','','/products/PXW-Z200/spec.gif','N','N','N','PXW-Z200, 비디오 카메라, 캠코더, 4k 핸디캠,video'),(13,'SEL2450G','작고 가벼운 첫 번째 F2.8 G렌즈 (표준 줌)',1799000,'lens','fullframe','표준렌즈','2024-05-01','/products/SEL2450G/detail1.jpg','','','','/products/SEL2450G/spec.gif','N','N','N','SEL2450G, 렌즈, 풀프레임, 표준렌즈, lens'),(14,'SEL300F28GM','세계 최경량 300mm F2.8 렌즈',8900000,'lens','fullframe','망원렌즈','2024-01-01','/products/SEL300F28GM/detail1.jpg','','','','/products/SEL300F28GM/spec.gif','N','N','N','SEL300F28GM, 렌즈, 풀프레임, 망원렌즈, lens'),(15,'SEL55210','작고 가벼운 망원 줌 렌즈',429000,'lens','APS-C','표준렌즈','2014-01-01','/products/SEL55210/detail1.jpg','','','','/products/SEL55210/spec.png','N','N','N','SEL55210, 렌즈, APS-C, 표준렌즈, lens'),(16,'SEL70350G','프리미엄 망원 5배 줌 렌즈',1149000,'lens','APS-C','망원렌즈','2014-01-01','/products/SEL70350G/detail1.jpg','','/products/SEL70350G/gif.jpg','/products/SEL70350G/detail2.jpg','/products/SEL70350G/spec.jpg','N','N','N','SEL70350G, 렌즈,APS-C,망원렌즈,lens');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SPRING_SESSION`
--

DROP TABLE IF EXISTS `SPRING_SESSION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SPRING_SESSION` (
  `PRIMARY_ID` char(36) NOT NULL,
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint NOT NULL,
  `LAST_ACCESS_TIME` bigint NOT NULL,
  `MAX_INACTIVE_INTERVAL` int NOT NULL,
  `EXPIRY_TIME` bigint NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`PRIMARY_ID`),
  UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
  KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
  KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SPRING_SESSION`
--

LOCK TABLES `SPRING_SESSION` WRITE;
/*!40000 ALTER TABLE `SPRING_SESSION` DISABLE KEYS */;
/*!40000 ALTER TABLE `SPRING_SESSION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SPRING_SESSION_ATTRIBUTES`
--

DROP TABLE IF EXISTS `SPRING_SESSION_ATTRIBUTES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SPRING_SESSION_ATTRIBUTES` (
  `SESSION_PRIMARY_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL,
  PRIMARY KEY (`SESSION_PRIMARY_ID`),
  CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `SPRING_SESSION` (`PRIMARY_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SPRING_SESSION_ATTRIBUTES`
--

LOCK TABLES `SPRING_SESSION_ATTRIBUTES` WRITE;
/*!40000 ALTER TABLE `SPRING_SESSION_ATTRIBUTES` DISABLE KEYS */;
/*!40000 ALTER TABLE `SPRING_SESSION_ATTRIBUTES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `today_bestproduct`
--

DROP TABLE IF EXISTS `today_bestproduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `today_bestproduct` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL COMMENT '???ų?¥',
  `title` varchar(255) DEFAULT NULL COMMENT '??ǰ?',
  `cnt` int DEFAULT NULL COMMENT '???',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=152 DEFAULT CHARSET=utf8mb4 COMMENT='?α???ǰ?';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `today_bestproduct`
--

LOCK TABLES `today_bestproduct` WRITE;
/*!40000 ALTER TABLE `today_bestproduct` DISABLE KEYS */;
INSERT INTO `today_bestproduct` VALUES (1,'2024-11-01 00:00:00','WH-1000XM5',32),(2,'2024-11-02 00:00:00','PS5',45),(3,'2024-11-03 00:00:00','SRS-XG500',34),(4,'2024-11-04 00:00:00','PS5',50),(5,'2024-11-05 00:00:00','XR-55A80K',13),(6,'2024-11-06 00:00:00','ILCE-7M4',13),(7,'2024-11-07 00:00:00','SRS-XG500',35),(8,'2024-11-08 00:00:00','PS5',29),(9,'2024-11-09 00:00:00','KD-65X85K',13),(10,'2024-11-10 00:00:00','KD-65X85K',28),(11,'2024-11-11 00:00:00','SRS-XG500',28),(12,'2024-11-12 00:00:00','XR-55A80K',14),(13,'2024-11-13 00:00:00','ZV-E10M2',43),(14,'2024-11-14 00:00:00','PS5',30),(15,'2024-11-15 00:00:00','KD-65X85K',29),(16,'2024-11-16 00:00:00','XR-55A80K',19),(17,'2024-11-17 00:00:00','ZV-E10M2',18),(18,'2024-11-18 00:00:00','ILCE-7M4',47),(19,'2024-11-19 00:00:00','Xperia-10IV',6),(20,'2024-11-20 00:00:00','PS5',23),(21,'2024-11-21 00:00:00','Xperia-10IV',12),(22,'2024-11-22 00:00:00','ZV-E10M2',34),(23,'2024-11-23 00:00:00','ZV-E10M2',40),(24,'2024-11-24 00:00:00','ILCE-7M4',26),(25,'2024-11-25 00:00:00','ILCE-7M4',31),(26,'2024-11-26 00:00:00','XR-55A80K',46),(27,'2024-11-27 00:00:00','ILCE-7M4',38),(28,'2024-11-28 00:00:00','KD-65X85K',15),(29,'2024-11-29 00:00:00','ZV-E10M2',46),(30,'2024-11-30 00:00:00','PS5',20),(31,'2024-12-01 00:00:00','PS5',18),(32,'2024-12-02 00:00:00','SRS-XG500',44),(33,'2024-12-03 00:00:00','VR2',43),(34,'2024-12-04 00:00:00','KD-65X85K',47),(35,'2024-12-05 00:00:00','WH-1000XM5',34),(36,'2024-12-06 00:00:00','VR2',44),(37,'2024-12-07 00:00:00','KD-65X85K',33),(38,'2024-12-08 00:00:00','Xperia-10IV',28),(39,'2024-12-09 00:00:00','WH-1000XM5',49),(40,'2024-12-10 00:00:00','WH-1000XM5',40),(41,'2024-12-11 00:00:00','KD-65X85K',29),(42,'2024-12-12 00:00:00','Xperia-5V',29),(43,'2024-12-13 00:00:00','SRS-XG500',5),(44,'2024-12-14 00:00:00','Xperia-10IV',12),(45,'2024-12-15 00:00:00','PS5',43),(46,'2024-12-16 00:00:00','Xperia-5V',9),(47,'2024-12-17 00:00:00','PS5',16),(48,'2024-12-18 00:00:00','KD-65X85K',15),(49,'2024-12-19 00:00:00','Xperia-10IV',33),(50,'2024-12-20 00:00:00','PS5',40),(51,'2024-12-21 00:00:00','VR2',48),(52,'2024-12-22 00:00:00','SRS-XG500',16),(53,'2024-12-23 00:00:00','VR2',26),(54,'2024-12-24 00:00:00','XR-55A80K',34),(55,'2024-12-25 00:00:00','PS5',34),(56,'2024-12-26 00:00:00','Xperia-10IV',19),(57,'2024-12-27 00:00:00','XR-55A80K',14),(58,'2024-12-28 00:00:00','XR-55A80K',43),(59,'2024-12-29 00:00:00','Xperia-10IV',40),(60,'2024-12-30 00:00:00','ILCE-7M4',42),(61,'2024-12-31 00:00:00','WH-1000XM5',5),(62,'2025-01-01 00:00:00','SRS-XG500',32),(63,'2025-01-02 00:00:00','WH-1000XM5',24),(64,'2025-01-03 00:00:00','VR2',40),(65,'2025-01-04 00:00:00','PS5',41),(66,'2025-01-05 00:00:00','Xperia-10IV',44),(67,'2025-01-06 00:00:00','KD-65X85K',45),(68,'2025-01-07 00:00:00','SRS-XG500',8),(69,'2025-01-08 00:00:00','VR2',20),(70,'2025-01-09 00:00:00','PS5',24),(71,'2025-01-10 00:00:00','VR2',47),(72,'2025-01-11 00:00:00','SRS-XG500',19),(73,'2025-01-12 00:00:00','KD-65X85K',43),(74,'2025-01-13 00:00:00','ZV-E10M2',28),(75,'2025-01-14 00:00:00','PS5',49),(76,'2025-01-15 00:00:00','PS5',24),(77,'2025-01-16 00:00:00','XR-55A80K',50),(78,'2025-01-17 00:00:00','PS5',37),(79,'2025-01-18 00:00:00','ZV-E10M2',36),(80,'2025-01-19 00:00:00','WH-1000XM5',17),(81,'2025-01-20 00:00:00','ZV-E10M2',37),(82,'2025-01-21 00:00:00','WH-1000XM5',28),(83,'2025-01-22 00:00:00','WH-1000XM5',28),(84,'2025-01-23 00:00:00','WH-1000XM5',47),(85,'2025-01-24 00:00:00','XR-55A80K',27),(86,'2025-01-25 00:00:00','XR-55A80K',28),(87,'2025-01-26 00:00:00','VR2',10),(88,'2025-01-27 00:00:00','VR2',39),(89,'2025-01-28 00:00:00','VR2',11),(90,'2025-01-29 00:00:00','Xperia-10IV',32),(91,'2025-01-30 00:00:00','SRS-XG500',46),(92,'2025-01-31 00:00:00','ILCE-7M4',19),(93,'2025-02-01 00:00:00','ILCE-7M4',36),(94,'2025-02-02 00:00:00','VR2',37),(95,'2025-02-03 00:00:00','WH-1000XM5',47),(96,'2025-02-04 00:00:00','XR-55A80K',10),(97,'2025-02-05 00:00:00','KD-65X85K',32),(98,'2025-02-06 00:00:00','Xperia-10IV',18),(99,'2025-02-07 00:00:00','KD-65X85K',49),(100,'2025-02-08 00:00:00','PS5',34),(101,'2025-02-09 00:00:00','ZV-E10M2',42),(102,'2025-02-10 00:00:00','WH-1000XM5',39),(103,'2025-02-11 00:00:00','WH-1000XM5',6),(104,'2025-02-12 00:00:00','Xperia-10IV',25),(105,'2025-02-13 00:00:00','PS5',26),(106,'2025-02-14 00:00:00','Xperia-10IV',32),(107,'2025-02-15 00:00:00','KD-65X85K',11),(108,'2025-02-16 00:00:00','Xperia-10IV',29),(109,'2025-02-17 00:00:00','VR2',32),(110,'2025-02-18 00:00:00','Xperia-10IV',18),(111,'2025-02-19 00:00:00','ZV-E10M2',26),(112,'2025-02-20 00:00:00','VR2',10),(113,'2025-02-21 00:00:00','Xperia-5V',5),(114,'2025-02-22 00:00:00','ILCE-7M4',14),(115,'2025-02-23 00:00:00','ZV-E10M2',29),(116,'2025-02-24 00:00:00','ILCE-7M4',10),(117,'2025-02-25 00:00:00','PS5',39),(118,'2025-02-26 00:00:00','SRS-XG500',46),(119,'2025-02-27 00:00:00','XR-55A80K',33),(120,'2025-02-28 00:00:00','VR2',33),(121,'2025-03-01 00:00:00','Xperia-5V',34),(122,'2025-03-02 00:00:00','Xperia-10IV',5),(123,'2025-03-03 00:00:00','WH-1000XM5',27),(124,'2025-03-04 00:00:00','XR-55A80K',48),(125,'2025-03-05 00:00:00','ZV-E10M2',46),(126,'2025-03-06 00:00:00','WH-1000XM5',25),(127,'2025-03-07 00:00:00','VR2',5),(128,'2025-03-08 00:00:00','KD-65X85K',19),(129,'2025-03-09 00:00:00','Xperia-10IV',8),(130,'2025-03-10 00:00:00','WH-1000XM5',13),(131,'2025-03-11 00:00:00','Xperia-5V',41),(132,'2025-03-12 00:00:00','VR2',36),(133,'2025-03-13 00:00:00','ZV-E10M2',13),(134,'2025-03-14 00:00:00','SRS-XG500',31),(135,'2025-03-15 00:00:00','WH-1000XM5',12),(136,'2025-03-16 00:00:00','VR2',50),(137,'2025-03-17 00:00:00','ILCE-7M4',47),(138,'2025-03-18 00:00:00','ILCE-7M4',41),(139,'2025-03-19 00:00:00','Xperia-10IV',33),(140,'2025-03-20 00:00:00','XR-55A80K',42),(141,'2025-03-21 00:00:00','SRS-XG500',6),(142,'2025-03-22 00:00:00','ZV-E10M2',43),(143,'2025-03-23 00:00:00','SRS-XG500',36),(144,'2025-03-24 00:00:00','XR-55A80K',37),(145,'2025-03-25 00:00:00','SRS-XG500',42),(146,'2025-03-26 00:00:00','PS5',6),(147,'2025-03-27 00:00:00','Xperia-10IV',25),(148,'2025-03-28 00:00:00','VR2',23),(149,'2025-03-29 00:00:00','VR2',16),(150,'2025-03-30 00:00:00','ZV-E10M2',40),(151,'2025-03-31 00:00:00','SRS-XG500',8);
/*!40000 ALTER TABLE `today_bestproduct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `today_member`
--

DROP TABLE IF EXISTS `today_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `today_member` (
  `count` int NOT NULL DEFAULT '0' COMMENT '가입자 수',
  `date` datetime NOT NULL COMMENT '가입한 날짜',
  `id` int NOT NULL AUTO_INCREMENT COMMENT '집계 번호',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=utf8 COMMENT='가입자 수';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `today_member`
--

LOCK TABLES `today_member` WRITE;
/*!40000 ALTER TABLE `today_member` DISABLE KEYS */;
INSERT INTO `today_member` VALUES (3,'2024-12-06 00:00:00',1),(100,'2024-12-17 00:00:00',2),(120,'2024-12-18 00:00:00',3),(95,'2024-12-19 00:00:00',4),(110,'2024-12-20 00:00:00',5),(130,'2024-12-21 00:00:00',6),(150,'2024-12-22 00:00:00',7),(140,'2024-12-23 00:00:00',8),(2,'2024-12-05 00:00:00',9),(30,'2024-10-01 00:00:00',10),(25,'2024-10-02 00:00:00',11),(35,'2024-10-03 00:00:00',12),(40,'2024-10-04 00:00:00',13),(50,'2024-10-05 00:00:00',14),(45,'2024-10-06 00:00:00',15),(60,'2024-10-07 00:00:00',16),(38,'2024-10-08 00:00:00',17),(42,'2024-10-09 00:00:00',18),(41,'2024-10-10 00:00:00',19),(37,'2024-10-11 00:00:00',20),(48,'2024-10-12 00:00:00',21),(50,'2024-10-13 00:00:00',22),(55,'2024-10-14 00:00:00',23),(45,'2024-10-15 00:00:00',24),(60,'2024-10-16 00:00:00',25),(63,'2024-10-17 00:00:00',26),(50,'2024-10-18 00:00:00',27),(55,'2024-10-19 00:00:00',28),(47,'2024-10-20 00:00:00',29),(52,'2024-10-21 00:00:00',30),(50,'2024-10-22 00:00:00',31),(43,'2024-10-23 00:00:00',32),(39,'2024-10-24 00:00:00',33),(50,'2024-10-25 00:00:00',34),(55,'2024-10-26 00:00:00',35),(60,'2024-10-27 00:00:00',36),(45,'2024-10-28 00:00:00',37),(40,'2024-10-29 00:00:00',38),(48,'2024-10-30 00:00:00',39),(53,'2024-10-31 00:00:00',40),(60,'2024-11-01 00:00:00',41),(55,'2024-11-02 00:00:00',42),(58,'2024-11-03 00:00:00',43),(50,'2024-11-04 00:00:00',44),(42,'2024-11-05 00:00:00',45),(60,'2024-11-06 00:00:00',46),(48,'2024-11-07 00:00:00',47),(50,'2024-11-08 00:00:00',48),(57,'2024-11-09 00:00:00',49),(63,'2024-11-10 00:00:00',50),(50,'2024-11-11 00:00:00',51),(55,'2024-11-12 00:00:00',52),(58,'2024-11-13 00:00:00',53),(61,'2024-11-14 00:00:00',54),(59,'2024-11-15 00:00:00',55),(55,'2024-11-16 00:00:00',56),(60,'2024-11-17 00:00:00',57),(65,'2024-11-18 00:00:00',58),(70,'2024-11-19 00:00:00',59),(67,'2024-11-20 00:00:00',60),(60,'2024-11-21 00:00:00',61),(55,'2024-11-22 00:00:00',62),(63,'2024-11-23 00:00:00',63),(58,'2024-11-24 00:00:00',64),(60,'2024-11-25 00:00:00',65),(62,'2024-11-26 00:00:00',66),(60,'2024-11-27 00:00:00',67),(63,'2024-11-28 00:00:00',68),(70,'2024-11-29 00:00:00',69),(75,'2024-11-30 00:00:00',70),(72,'2024-12-01 00:00:00',71),(68,'2024-12-02 00:00:00',72),(65,'2024-12-03 00:00:00',73),(70,'2024-12-04 00:00:00',74),(62,'2024-12-07 00:00:00',75),(70,'2024-12-08 00:00:00',76),(72,'2024-12-09 00:00:00',77),(75,'2024-12-10 00:00:00',78),(77,'2024-12-11 00:00:00',79),(80,'2024-12-12 00:00:00',80),(70,'2024-12-13 00:00:00',81),(72,'2024-12-14 00:00:00',82),(75,'2024-12-15 00:00:00',83),(80,'2024-12-16 00:00:00',84),(105,'2024-12-24 00:00:00',85),(110,'2024-12-25 00:00:00',86),(120,'2024-12-26 00:00:00',87),(75,'2024-12-27 00:00:00',88),(60,'2024-12-28 00:00:00',89),(85,'2024-12-29 00:00:00',90),(70,'2024-12-30 00:00:00',91),(90,'2024-12-31 00:00:00',92),(120,'2025-01-01 00:00:00',93),(80,'2025-01-02 00:00:00',94),(110,'2025-01-03 00:00:00',95),(95,'2025-01-04 00:00:00',96),(130,'2025-01-05 00:00:00',97),(100,'2025-01-06 00:00:00',98),(140,'2025-01-07 00:00:00',99),(115,'2025-01-08 00:00:00',100),(150,'2025-01-09 00:00:00',101),(125,'2025-01-10 00:00:00',102),(160,'2025-01-11 00:00:00',103),(135,'2025-01-12 00:00:00',104),(170,'2025-01-13 00:00:00',105),(145,'2025-01-14 00:00:00',106),(180,'2025-01-15 00:00:00',107),(155,'2025-01-16 00:00:00',108),(190,'2025-01-17 00:00:00',109),(165,'2025-01-18 00:00:00',110),(200,'2025-01-19 00:00:00',111),(175,'2025-01-20 00:00:00',112),(210,'2025-01-21 00:00:00',113),(185,'2025-01-22 00:00:00',114),(220,'2025-01-23 00:00:00',115),(195,'2025-01-24 00:00:00',116),(230,'2025-01-25 00:00:00',117),(215,'2025-01-26 00:00:00',118),(240,'2025-01-27 00:00:00',119),(225,'2025-01-28 00:00:00',120),(250,'2025-01-29 00:00:00',121),(235,'2025-01-30 00:00:00',122),(260,'2025-01-31 00:00:00',123),(245,'2025-02-01 00:00:00',124),(270,'2025-02-02 00:00:00',125),(255,'2025-02-03 00:00:00',126),(280,'2025-02-04 00:00:00',127),(265,'2025-02-05 00:00:00',128),(290,'2025-02-06 00:00:00',129),(275,'2025-02-07 00:00:00',130),(300,'2025-02-08 00:00:00',131),(285,'2025-02-09 00:00:00',132),(310,'2025-02-10 00:00:00',133),(295,'2025-02-11 00:00:00',134),(320,'2025-02-12 00:00:00',135),(305,'2025-02-13 00:00:00',136),(330,'2025-02-14 00:00:00',137),(315,'2025-02-15 00:00:00',138),(340,'2025-02-16 00:00:00',139),(325,'2025-02-17 00:00:00',140),(350,'2025-02-18 00:00:00',141),(335,'2025-02-19 00:00:00',142),(360,'2025-02-20 00:00:00',143),(345,'2025-02-21 00:00:00',144),(370,'2025-02-22 00:00:00',145),(355,'2025-02-23 00:00:00',146),(380,'2025-02-24 00:00:00',147),(365,'2025-02-25 00:00:00',148),(390,'2025-02-26 00:00:00',149),(375,'2025-02-27 00:00:00',150),(400,'2025-02-28 00:00:00',151),(385,'2025-03-01 00:00:00',152);
/*!40000 ALTER TABLE `today_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `today_sales`
--

DROP TABLE IF EXISTS `today_sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `today_sales` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '?Ϸù',
  `date` date NOT NULL COMMENT '??¥',
  `total` int NOT NULL DEFAULT '0' COMMENT '???',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=223 DEFAULT CHARSET=utf8mb4 COMMENT='?? ???';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `today_sales`
--

LOCK TABLES `today_sales` WRITE;
/*!40000 ALTER TABLE `today_sales` DISABLE KEYS */;
INSERT INTO `today_sales` VALUES (66,'2024-12-23',2690000),(72,'2024-12-24',0),(74,'2024-12-22',12160000),(75,'2024-12-21',1490000),(76,'2024-12-20',9470000),(77,'2024-12-19',3409000),(78,'2024-12-18',2690000),(79,'2024-12-17',759000),(80,'2024-12-16',3839000),(82,'2024-12-15',3409000),(83,'2024-12-14',2690000),(85,'2024-12-13',0),(86,'2024-12-12',9470000),(87,'2024-11-20',2690000),(88,'2024-11-21',12160000),(89,'2024-11-22',0),(90,'2024-11-23',9470000),(91,'2024-11-24',2690000),(92,'2024-11-25',3409000),(93,'2024-11-26',2690000),(94,'2024-11-27',3839000),(95,'2024-11-28',759000),(96,'2024-11-29',2690000),(97,'2024-11-30',3409000),(98,'2024-12-01',1490000),(99,'2024-12-02',12160000),(100,'2024-12-03',9470000),(101,'2024-12-04',0),(102,'2024-12-05',3409000),(103,'2024-12-06',12160000),(104,'2024-12-07',1490000),(105,'2024-12-08',2690000),(106,'2024-12-09',9470000),(107,'2024-12-10',3409000),(108,'2024-12-11',2690000),(109,'2024-12-25',7590000),(127,'2024-12-26',28437000),(128,'2024-12-27',2690000),(129,'2024-12-28',12160000),(130,'2024-12-29',9470000),(131,'2024-12-30',3409000),(132,'2024-12-31',2690000),(133,'2025-01-01',759000),(134,'2025-01-02',3839000),(135,'2025-01-03',2690000),(136,'2025-01-04',3409000),(137,'2025-01-05',12160000),(138,'2025-01-06',1490000),(139,'2025-01-07',2690000),(140,'2025-01-08',9470000),(141,'2025-01-09',3409000),(142,'2025-01-10',2690000),(143,'2025-01-11',12160000),(144,'2025-01-12',0),(145,'2025-01-13',9470000),(146,'2025-01-14',2690000),(147,'2025-01-15',3409000),(148,'2025-01-16',1490000),(149,'2025-01-17',12160000),(150,'2025-01-18',3839000),(151,'2025-01-19',2690000),(152,'2025-01-20',759000),(153,'2025-01-21',3409000),(154,'2025-01-22',2690000),(155,'2025-01-23',12160000),(156,'2025-01-24',0),(157,'2025-01-25',9470000),(158,'2025-01-26',3409000),(159,'2025-01-27',2690000),(160,'2025-01-28',1490000),(161,'2025-01-29',12160000),(162,'2025-01-30',9470000),(163,'2025-01-31',3409000),(164,'2025-02-01',2690000),(165,'2025-02-02',759000),(166,'2025-02-03',3839000),(167,'2025-02-04',2690000),(168,'2025-02-05',3409000),(169,'2025-02-06',12160000),(170,'2025-02-07',1490000),(171,'2025-02-08',2690000),(172,'2025-02-09',9470000),(173,'2025-02-10',3409000),(174,'2025-02-11',2690000),(175,'2025-02-12',12160000),(176,'2025-02-13',0),(177,'2025-02-14',9470000),(178,'2025-02-15',2690000),(179,'2025-02-16',3409000),(180,'2025-02-17',1490000),(181,'2025-02-18',12160000),(182,'2025-02-19',3839000),(183,'2025-02-20',2690000),(184,'2025-02-21',759000),(185,'2025-02-22',3409000),(186,'2025-02-23',2690000),(187,'2025-02-24',12160000),(188,'2025-02-25',0),(189,'2025-02-26',9470000),(190,'2025-02-27',3409000),(191,'2025-02-28',2690000),(192,'2025-03-01',1490000),(193,'2025-03-02',12160000),(194,'2025-03-03',9470000),(195,'2025-03-04',0),(196,'2025-03-05',3409000),(197,'2025-03-06',12160000),(198,'2025-03-07',1490000),(199,'2025-03-08',2690000),(200,'2025-03-09',9470000),(201,'2025-03-10',3409000),(202,'2025-03-11',2690000),(203,'2025-03-12',12160000),(204,'2025-03-13',0),(205,'2025-03-14',9470000),(206,'2025-03-15',2690000),(207,'2025-03-16',3409000),(208,'2025-03-17',1490000),(209,'2025-03-18',12160000),(210,'2025-03-19',3839000),(211,'2025-03-20',2690000),(212,'2025-03-21',759000),(213,'2025-03-22',3409000),(214,'2025-03-23',2690000),(215,'2025-03-24',12160000),(216,'2025-03-25',0),(217,'2025-03-26',9470000),(218,'2025-03-27',3409000),(219,'2025-03-28',2690000),(220,'2025-03-29',1490000),(221,'2025-03-30',12160000),(222,'2025-03-31',9470000);
/*!40000 ALTER TABLE `today_sales` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-06 16:13:10
