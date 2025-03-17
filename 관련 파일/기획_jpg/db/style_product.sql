-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: style
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `no` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `price` int NOT NULL,
  `detail` longtext,
  `uploaded_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `category` int NOT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (3,'[연말룩/1만장돌파]퍼 스퀘어넥 겨울 니트',27500,'<p>보송보송한 텍스처가 매력적인</p><p>사랑스러운 무드의 니트웨어 ♥</p><div><br></div>','2025-01-02 21:07:32',5),(4,'[양기모]겨울 기모 리본 자수 루즈핏 맨투맨(2color)',44900,'<p><span style=\"font-size: var(--font-size-normal);\">한층 러블리한 룩을 완성해줄</span></p><p>도톰한 양기모 소재의 맨투맨 ♡</p>','2025-01-02 21:08:10',5),(5,'[MADE]수플레 겨울 오프숄더 셔링 드레이프 골지 티셔츠(6color)',27900,'<p>센스있는 투웨이 디자인</p><p>페미닌한 무드를 가득 담은 티셔츠 ♡</p>','2025-01-02 21:08:36',5),(6,'겨울 기모 프린팅 오버핏 긴팔 맨투맨(2color)',42900,'<p><span style=\"font-size: var(--font-size-normal);\">키치한 프린팅이 매력적인</span></p><p>오버한 핏감의 기모 맨투맨 !</p>','2025-01-02 21:09:04',5),(7,'[울60%/MADE]울 빈티지 오버핏 니트 가디건(3color)',66900,'<p>핸드메이드 스웨터를 재현한</p><p>프렌치오브 제작 니트 가디건 ♥</p>','2025-01-02 21:09:34',6),(8,'겨울니트 미니 버킷 토트백 가방(3color)',49800,'<p>포슬포슬 헤어리한 울 부클 텍스처</p><p>퀄리티 높게 제작된 니트 토트백 ✿</p>','2025-01-09 16:59:46',2),(9,'[?하객룩/4천장돌파] 겨울 트위드 하객 미니 스커트(3color)',31900,'<p>견고한 트위드 짜임</p><p>하객룩으로 추천드리는 미니 스커트 ♡</p>','2025-01-09 17:00:06',4),(10,'[MADE]기모 조거 트레이닝 와이드 팬츠(7color)',36000,'<p>사랑스러운 컬러감 + 코지한 핏감</p><p>데일리한 투웨이 트레이닝 팬츠 ♡</p><div><br></div>','2025-01-09 17:00:33',4),(11,'신발',9999,'<p>신발</p>','2025-01-09 17:00:48',3),(12,'[울50%]겨울 플리츠 치마바지 미니 스커트(2color)',45900,'<p>걸리쉬 무드의 플리츠 디자인</p><p>팬츠 안감으로 부담없이 착용될 스커트 ☺</p>','2025-01-09 17:17:11',4),(13,' 플래닛 봄 파스텔 코튼 치마 미니 스커트(3color)',21900,'<p>스커트</p>','2025-01-09 17:17:39',4),(14,'검정신발',9999,'<p>신발</p>','2025-01-09 17:18:24',3),(15,'흰색신발',9999,'<p>흰색신발</p>','2025-01-09 17:18:46',3),(16,'하트 레더 벨트 미니백 가방',59300,'<p>로맨틱 + 세련된 코디를 완성시켜 줄</p><p>포인트 미니백 ♥</p>','2025-01-09 17:22:56',2),(17,'[MADE]겨울 뽀글이 하프 누빔 코트(2color)',93400,'<p>뽀용한 컬러감 + 부클 소재감</p><p>아방한 실루엣의 제작 하프 코트 ♥</p>','2025-01-09 17:26:48',6);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-05 19:12:59
