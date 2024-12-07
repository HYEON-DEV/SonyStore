-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: sony
-- ------------------------------------------------------
-- Server version	8.0.20

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
  `cartid` int NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `count` int NOT NULL COMMENT '수량',
  `memberid` int NOT NULL COMMENT '회원의 일련번호',
  `prodid` int NOT NULL COMMENT '상품의 일련번호',
  `color` varchar(45) DEFAULT NULL COMMENT '상품의 색상',
  PRIMARY KEY (`cartid`),
  KEY `fk_carts_members1_idx` (`memberid`),
  KEY `fk_carts_products1_idx1` (`prodid`),
  CONSTRAINT `fk_carts_members1` FOREIGN KEY (`memberid`) REFERENCES `members` (`memberid`),
  CONSTRAINT `fk_carts_products1` FOREIGN KEY (`prodid`) REFERENCES `products` (`prodid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='장바구니';
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
  `colorid` int NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `color` varchar(45) NOT NULL COMMENT '색상',
  `prodid` int DEFAULT NULL COMMENT '상품의 일련번호',
  `pcolor` enum('Y','N') NOT NULL DEFAULT 'N' COMMENT '기본색상',
  PRIMARY KEY (`colorid`),
  KEY `fk_colors_products1_idx1` (`prodid`),
  CONSTRAINT `fk_colors_products1` FOREIGN KEY (`prodid`) REFERENCES `products` (`prodid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='색상';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `colors`
--

LOCK TABLES `colors` WRITE;
/*!40000 ALTER TABLE `colors` DISABLE KEYS */;
INSERT INTO `colors` VALUES (1,'white',1,'Y'),(2,'black',1,'N'),(3,'black',2,'Y'),(4,'white',2,'N'),(5,'silver',4,'Y'),(6,'black',4,'N'),(7,'white',5,'Y'),(8,'black',5,'N'),(9,'white',6,'Y'),(10,'black',6,'N'),(11,'black',15,'Y'),(12,'silver',15,'N');
/*!40000 ALTER TABLE `colors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `events` (
  `eventid` int NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `title` varchar(255) NOT NULL COMMENT '제목',
  `caution` text COMMENT '유의사항',
  `thumbnail` varchar(255) NOT NULL COMMENT '기획전 대표 이미지 경로',
  `startdate` date DEFAULT NULL COMMENT '기획전 시작날짜',
  `enddate` date DEFAULT NULL COMMENT '기획전 종료날짜',
  `eventdesc` varchar(255) DEFAULT NULL COMMENT '기획전 설명',
  `image` varchar(255) NOT NULL COMMENT '기획전 상세이미지 경로',
  PRIMARY KEY (`eventid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='기획전';
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
  `imgid` int NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `filepath` varchar(255) NOT NULL COMMENT '이미지 파일 경로',
  `thumbnail` enum('Y','N') NOT NULL DEFAULT 'N' COMMENT '상품 대표 이미지 (Y/N)',
  `prodid` int NOT NULL COMMENT '상품의 일련번호',
  `colorid` int DEFAULT NULL COMMENT '색상의 일련번호',
  PRIMARY KEY (`imgid`),
  KEY `fk_images_products1_idx` (`prodid`),
  KEY `fk_images_colors1_idx` (`colorid`),
  CONSTRAINT `fk_images_colors1` FOREIGN KEY (`colorid`) REFERENCES `colors` (`colorid`),
  CONSTRAINT `fk_images_products1` FOREIGN KEY (`prodid`) REFERENCES `products` (`prodid`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8 COMMENT='상품 이미지';
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
  `memberid` int NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `email` varchar(45) NOT NULL COMMENT '이메일',
  `userpw` varchar(255) NOT NULL COMMENT '비밀번호',
  `username` varchar(45) NOT NULL COMMENT '이름',
  `gender` enum('M','F') NOT NULL COMMENT '성별 (M/F)',
  `birthdate` date NOT NULL COMMENT '생년월일',
  `phone` varchar(20) NOT NULL COMMENT '휴대폰번호',
  `editdate` datetime NOT NULL COMMENT '변경일시',
  `postcode` char(5) DEFAULT NULL COMMENT '우편번호',
  `addr1` varchar(255) DEFAULT NULL COMMENT '검색된 주소',
  `addr2` varchar(255) DEFAULT NULL COMMENT '상세 주소',
  `isout` enum('Y','N') NOT NULL DEFAULT 'N' COMMENT '탈퇴 여부 (Y/N)',
  `logindate` datetime DEFAULT NULL COMMENT '마지막 로그인 일시',
  `regdate` datetime NOT NULL COMMENT '등록일시',
  `isadmin` enum('Y','N') NOT NULL DEFAULT 'N' COMMENT '관리자 여부 (Y/ N)',
  `receiveemail` enum('Y','N') NOT NULL DEFAULT 'N' COMMENT '광고성 정보 이메일 수신 여부 (Y/N)',
  `receivesms` enum('Y','N') NOT NULL DEFAULT 'N' COMMENT '광고성 정보 문자 수신 여부 (Y/N)',
  PRIMARY KEY (`memberid`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='회원정보';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` VALUES (18,'jinsu4205@gmail.com','Aa123456789@@!','박재한','F','1996-09-05','01033063205','2024-12-06 11:34:55','06779','서울 서초구 강남대로 48-3 (양재동)','102호','N','2024-12-06 16:34:27','2024-12-02 16:49:06','N','Y','Y'),(19,'wlstn4205@gmailw.com','Aa123456789@@','박진수지메일','M','1996-09-05','01033062222','2024-12-05 16:33:50',NULL,NULL,NULL,'N','2024-12-05 17:47:22','2024-12-05 16:33:50','N','Y','Y'),(20,'jinsu4205@gmail.comm','Aa123456789@@','박진수수','F','1996-09-04','01045782656','2024-12-05 17:52:50',NULL,NULL,NULL,'N','2024-12-05 17:53:20','2024-12-05 17:52:50','N','N','N'),(21,'leekh4232@gmail.com','1234qwer!@#$','박진수','M','2027-01-03','01022084232','2024-12-06 17:43:14',NULL,NULL,NULL,'N',NULL,'2024-12-06 17:43:14','N','N','N'),(22,'jinsu4205@gmail.coma','Aa123456789@@','박진수','M','2025-02-06','01011111111','2024-12-06 17:46:16',NULL,NULL,NULL,'N',NULL,'2024-12-06 17:46:16','N','Y','Y'),(23,'leekh4232@kakao.com','Aa123456789@@','박진수','M','2024-12-06','01033064212','2024-12-06 17:48:18',NULL,NULL,NULL,'N','2024-12-06 17:48:31','2024-12-06 17:48:18','N','Y','Y');
/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notices`
--

DROP TABLE IF EXISTS `notices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notices` (
  `noticeid` int NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `title` varchar(255) NOT NULL COMMENT '제목',
  `regdate` date NOT NULL COMMENT '등록일자',
  `watch` int NOT NULL DEFAULT '0' COMMENT '조회수',
  `content` text NOT NULL COMMENT '공지사항 내용',
  PRIMARY KEY (`noticeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='공지사항';
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
  `paylistid` int NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `payid` int NOT NULL COMMENT '결제내역의 일련번호',
  `prodid` int DEFAULT NULL COMMENT '상품의 일련번호',
  `prodthumbnail` varchar(255) DEFAULT NULL COMMENT '상품의 대표 이미지 경로',
  `prodtitle` varchar(45) NOT NULL COMMENT '상품의 이름',
  `prodcolor` varchar(45) DEFAULT NULL COMMENT '구매한 상품의 색상',
  `count` int NOT NULL COMMENT '상품의 수량',
  `prodprice` int NOT NULL COMMENT '상품의 가격',
  PRIMARY KEY (`paylistid`),
  KEY `fk_paylist_payments_idx` (`payid`),
  CONSTRAINT `fk_paylist_payments` FOREIGN KEY (`payid`) REFERENCES `payments` (`payid`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='결제 상품 목록';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paylist`
--

LOCK TABLES `paylist` WRITE;
/*!40000 ALTER TABLE `paylist` DISABLE KEYS */;
INSERT INTO `paylist` VALUES (1,1,4,'/files/products/ILCE-7CM2/clr1_0.png','ILCE-7CM2','black',1,2690000),(2,2,3,'/files/products/ILCE-9M3/clr0_0.png','ILCE-9M3','null',1,7980000),(9,6,4,'/files/products/ILCE-7CM2/clr0_0.png','ILCE-7CM2','silver',11,2690000),(10,6,4,'/files/products/ILCE-7CM2/clr1_0.png','ILCE-7CM2','black',4,2690000),(12,8,2,'/files/products/ZV-E10M2/clr0_0.png','ZV-E10M2','black',1,1340000),(13,9,2,'/files/products/ZV-E10M2/clr0_0.png','ZV-E10M2','black',1,1340000),(17,13,2,'/files/products/ZV-E10M2/clr1_0.png','ZV-E10M2','white',1,1340000),(18,14,2,'/files/products/ZV-E10M2/clr1_0.png','ZV-E10M2','white',1,1340000),(19,15,1,'/files/products/ZV-E10M2K/clr0_0.png','ZV-E10M2K','white',1,1490000);
/*!40000 ALTER TABLE `paylist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS `payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payments` (
  `payid` int NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `date` datetime DEFAULT NULL COMMENT '결제날짜',
  `status` varchar(10) NOT NULL COMMENT '처리상태',
  `memberid` int DEFAULT NULL COMMENT '회원의 일련번호',
  `ordername` varchar(30) DEFAULT NULL COMMENT '주문자 이름',
  `orderemail` varchar(45) DEFAULT NULL,
  `orderphone` varchar(20) DEFAULT NULL COMMENT '주문자 휴대폰번호',
  `receivername` varchar(30) DEFAULT NULL COMMENT '수령인 이름',
  `receiverphone` varchar(20) DEFAULT NULL COMMENT '수령인 휴대폰번호',
  `postcode` char(5) DEFAULT NULL COMMENT '우편번호',
  `addr1` varchar(45) DEFAULT NULL COMMENT '검색된 주소',
  `addr2` varchar(45) DEFAULT NULL COMMENT '상세 주소',
  `request` varchar(45) DEFAULT NULL COMMENT '배송 요청 사항',
  `dlvdate` varchar(45) DEFAULT NULL COMMENT '배송일',
  `totalcount` int NOT NULL DEFAULT '0' COMMENT '전체 수량',
  `total` int NOT NULL COMMENT '총 결제금액',
  `payoption` varchar(20) NOT NULL COMMENT '결제방법',
  `insertdate` datetime NOT NULL COMMENT '구매 희망 일시',
  `paycheck` enum('Y','N') NOT NULL DEFAULT 'N' COMMENT '결제유무',
  PRIMARY KEY (`payid`),
  KEY `fk_payments_members1_idx` (`memberid`),
  CONSTRAINT `fk_payments_members1` FOREIGN KEY (`memberid`) REFERENCES `members` (`memberid`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='결제';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments`
--

LOCK TABLES `payments` WRITE;
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
INSERT INTO `payments` VALUES (1,'2024-12-06 10:55:52','결제완료',18,'박진숭','jinsu4205@gmail.com','01033063205','박진수','01033063205','06100','서울 강남구 선릉로 635 (논현동)','123','null','null',1,2690000,'신용카드','2024-12-06 10:55:12','Y'),(2,'2024-12-06 11:30:02','결제완료',18,'박진숭','jinsu4205@gmail.com','01033063205','박재한','01045786589','18148','경기 오산시 부산중앙로 11 (부산동, 오산시티자이 1단지)','101호','부재 시 경비실에 맡겨 주세요.','2024-12-11',1,7980000,'네이버페이','2024-12-06 11:29:19','Y'),(6,'2024-12-06 11:37:03','결제완료',18,'박재한','jinsu4205@gmail.com','01033063205','박재한','01045786589','47874','부산 동래구 종합운동장로 29 (사직동)','101호','null','2024-12-26',15,40350000,'신용카드','2024-12-06 11:36:25','Y'),(8,'2024-12-06 16:13:30','결제완료',18,'박재한','jinsu4205@gmail.com','01033063205','박재한','01045786589','','','101호','null','null',1,1340000,'신용카드','2024-12-06 16:09:51','Y'),(9,'2024-12-06 16:17:00','결제완료',18,'박재한','jinsu4205@gmail.com','01033063205','박재한','01045786589','','','101호','null','null',1,1340000,'신용카드','2024-12-06 16:16:45','Y'),(13,'2024-12-06 16:27:41','결제완료',18,'박재한','jinsu4205@gmail.com','01033063205','박재한','01045786589','','','101호','null','null',1,1340000,'신용카드','2024-12-06 16:27:33','Y'),(14,'2024-12-06 16:28:22','결제완료',18,'박재한','jinsu4205@gmail.com','01033063205','박재한','01045786589','','','101호','null','null',1,1340000,'신용카드','2024-12-06 16:28:14','Y'),(15,'2024-12-06 16:30:23','결제완료',18,'박재한','jinsu4205@gmail.com','01033063205','박재한','01045786589','','','101호','null','null',1,1490000,'신용카드','2024-12-06 16:29:46','Y');
/*!40000 ALTER TABLE `payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `prodid` int NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `title` varchar(45) NOT NULL COMMENT '상품 이름',
  `proddesc` varchar(255) DEFAULT NULL COMMENT '상품 설명',
  `price` int NOT NULL COMMENT '상품 가격',
  `type1` varchar(45) NOT NULL COMMENT '상품 카테고리1',
  `type2` varchar(45) DEFAULT NULL COMMENT '상품 카테고리2',
  `type3` varchar(45) DEFAULT NULL COMMENT '상품 카테고리3',
  `date` date DEFAULT NULL COMMENT '출시일',
  `detailimage1` varchar(255) NOT NULL COMMENT '상품 상세의 이미지 경로1',
  `youtube` varchar(255) DEFAULT NULL COMMENT '상품 상세의 유튜브 경로',
  `detailgif` varchar(255) DEFAULT NULL COMMENT '상품 상세의 gif 경로',
  `detailimage2` varchar(255) DEFAULT NULL COMMENT '상품 상세의 이미지 경로2',
  `detailspec` varchar(255) DEFAULT NULL COMMENT '상품 상세의 스펙',
  `soldout` enum('Y','N') NOT NULL DEFAULT 'N' COMMENT '상품의 품절여부 (Y/N)',
  `sale` enum('Y','N') NOT NULL DEFAULT 'N' COMMENT '상품의 할인 유무 (Y/N)',
  `event` enum('Y','N') NOT NULL DEFAULT 'N' COMMENT '상품의 기획전 유무 (Y/N)',
  `keyword` varchar(255) DEFAULT NULL COMMENT '검색 결과 키워드',
  PRIMARY KEY (`prodid`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='상품';
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
-- Table structure for table `spring_session`
--

DROP TABLE IF EXISTS `spring_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spring_session` (
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
-- Dumping data for table `spring_session`
--

--
-- Table structure for table `spring_session_attributes`
--

DROP TABLE IF EXISTS `spring_session_attributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spring_session_attributes` (
  `SESSION_PRIMARY_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL,
  PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`),
  CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `spring_session` (`PRIMARY_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spring_session_attributes`
--

-- Dump completed on 2024-12-06 18:10:07
