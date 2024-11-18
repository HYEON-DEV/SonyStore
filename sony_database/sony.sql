CREATE DATABASE  IF NOT EXISTS `sony` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sony`;
-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: sony
-- ------------------------------------------------------
-- Server version	8.0.20

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='장바구니';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carts`
--

LOCK TABLES `carts` WRITE;
/*!40000 ALTER TABLE `carts` DISABLE KEYS */;
INSERT INTO `carts` VALUES (1,1,1,0,NULL);
/*!40000 ALTER TABLE `carts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `colors`
--

DROP TABLE IF EXISTS `colors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `colors` (
  `colorid` int NOT NULL AUTO_INCREMENT COMMENT '색상 번호',
  `color` varchar(45) NOT NULL COMMENT '색상',
  `prodid` int DEFAULT NULL COMMENT '상품의 일련번호',
  PRIMARY KEY (`colorid`),
  KEY `fk_colors_products1_idx1` (`prodid`),
  CONSTRAINT `fk_colors_products1` FOREIGN KEY (`prodid`) REFERENCES `products` (`prodid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='색상 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `colors`
--

LOCK TABLES `colors` WRITE;
/*!40000 ALTER TABLE `colors` DISABLE KEYS */;
INSERT INTO `colors` VALUES (1,'black',NULL);
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
  `thumbnail` enum('Y','N') NOT NULL DEFAULT 'N' COMMENT '상품 대표 이미지',
  `prodid` int NOT NULL COMMENT '상품의 일련번호',
  `colorid` int DEFAULT NULL COMMENT '색상의 일련번호',
  PRIMARY KEY (`imgid`),
  KEY `fk_images_products1_idx` (`prodid`),
  KEY `fk_images_colors1_idx` (`colorid`),
  CONSTRAINT `fk_images_colors1` FOREIGN KEY (`colorid`) REFERENCES `colors` (`colorid`),
  CONSTRAINT `fk_images_products1` FOREIGN KEY (`prodid`) REFERENCES `products` (`prodid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='상품 이미지';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `images`
--

LOCK TABLES `images` WRITE;
/*!40000 ALTER TABLE `images` DISABLE KEYS */;
/*!40000 ALTER TABLE `images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `members` (
  `memberid` int NOT NULL AUTO_INCREMENT COMMENT '회원의 일련번호',
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
  PRIMARY KEY (`memberid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='회원정보';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` VALUES (1,'chang@gmail.com','200820e3227815ed1756a6b531e7e0d2','홍창기','M','1993-05-01','01011112222','2024-11-18 02:52:08','12345','서울특별시 잠실동','123','N',NULL,'2024-11-18 02:52:08','N');
/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notices`
--

DROP TABLE IF EXISTS `notices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notices` (
  `noticeid` int NOT NULL AUTO_INCREMENT COMMENT '번호',
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
  `paylistid` int NOT NULL AUTO_INCREMENT COMMENT '구매한 상품의 일련번호',
  `prodid` int NOT NULL COMMENT '상품의 일련번호',
  `prodcolor` varchar(45) DEFAULT NULL COMMENT '구매한 상품의 색상',
  `prodthumbnail` varchar(255) NOT NULL COMMENT '상품의 대표 이미지 경로',
  `prodtitle` varchar(45) NOT NULL COMMENT '상품의 이름',
  `prodprice` int NOT NULL COMMENT '상품의 가격',
  `count` int NOT NULL COMMENT '상품의 수량',
  `payid` int NOT NULL COMMENT '결제내역의 일련번호',
  PRIMARY KEY (`paylistid`),
  KEY `fk_paylist_payments_idx` (`payid`),
  CONSTRAINT `fk_paylist_payments` FOREIGN KEY (`payid`) REFERENCES `payments` (`payid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='결제 상품 목록';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paylist`
--

LOCK TABLES `paylist` WRITE;
/*!40000 ALTER TABLE `paylist` DISABLE KEYS */;
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
  `total` int NOT NULL COMMENT '총 결제금액',
  `date` date NOT NULL COMMENT '결제날짜',
  `option` varchar(20) NOT NULL COMMENT '결제방법',
  `status` varchar(10) NOT NULL COMMENT '처리상태',
  `paycheck` enum('Y','N') NOT NULL DEFAULT 'N' COMMENT '결제유무',
  `insertdate` datetime NOT NULL COMMENT '구매 희망 일시',
  `receivername` varchar(30) DEFAULT NULL COMMENT '수령인 이름',
  `phone` varchar(20) DEFAULT NULL COMMENT '수령인 휴대폰번호',
  `postcode` char(5) DEFAULT NULL COMMENT '우편번호',
  `addr1` varchar(45) DEFAULT NULL COMMENT '검색된 주소',
  `addr2` varchar(45) DEFAULT NULL COMMENT '상세 주소',
  `request` varchar(45) DEFAULT NULL COMMENT '배송 요청 사항',
  `dlvdate` varchar(45) DEFAULT NULL COMMENT '배송일',
  `totalcount` int NOT NULL DEFAULT '0' COMMENT '전체 수량',
  `memberid` int DEFAULT NULL COMMENT '회원의 일련번호',
  PRIMARY KEY (`payid`),
  KEY `fk_payments_members1_idx` (`memberid`),
  CONSTRAINT `fk_payments_members1` FOREIGN KEY (`memberid`) REFERENCES `members` (`memberid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='결제';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments`
--

LOCK TABLES `payments` WRITE;
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
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
  `title` varchar(45) NOT NULL COMMENT '상품명',
  `proddesc` varchar(255) DEFAULT NULL COMMENT '상품 설명',
  `price` int NOT NULL COMMENT '상품 가격',
  `type1` varchar(45) NOT NULL COMMENT '상품 카테고리 1',
  `type2` varchar(45) DEFAULT NULL COMMENT '상품 카테고리 2',
  `type3` varchar(45) DEFAULT NULL COMMENT '상품 카테고리 3',
  `date` date DEFAULT NULL COMMENT '출시일',
  `detailimage1` varchar(255) NOT NULL COMMENT '상품 상세의 이미지 경로 1',
  `youtube` varchar(255) DEFAULT NULL COMMENT '상품 상세 유튜브 경로',
  `detailgif` varchar(255) DEFAULT NULL COMMENT '상품 상세의 gif 경로',
  `detailimage2` varchar(255) DEFAULT NULL COMMENT '상품 상세의 이미지 경로 2',
  `detailspec` varchar(255) DEFAULT NULL COMMENT '상품 상세의 스펙',
  `soldout` enum('Y','N') NOT NULL DEFAULT 'N' COMMENT '상품의 품절여부',
  `sale` enum('Y','N') NOT NULL DEFAULT 'N' COMMENT '상품의 할인 유무',
  `event` enum('Y','N') NOT NULL DEFAULT 'N' COMMENT '상품의 기획전 유무',
  PRIMARY KEY (`prodid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='상품';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'ILCE-7CM2L','원핸드 컴팩트 풀프레임',3090000,'카메라','렌즈교환식','풀프레임','2023-03-01','../../detailimage1.png','sflsfjowjfyoutube.com','../../gif.gif','../../detailimage2.png','../../spec.png','N','N','N');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-18 19:38:47
