-- MySQL dump 10.13  Distrib 5.7.26, for Linux (x86_64)
--
-- Host: localhost    Database: hydosky_send
-- ------------------------------------------------------
-- Server version	5.7.26-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

CREATE DATABASE `hydosky_send` DEFAULT CHARACTER SET utf8;

use `hydosky_send`;
--
-- Table structure for table `base_data`
--

DROP TABLE IF EXISTS `base_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `base_data` (
  `city_name` varchar(20) NOT NULL COMMENT '城市名称',
  `day_order_count` int(11) DEFAULT '0' COMMENT '日订单量',
  `start_time` varchar(5) NOT NULL COMMENT '开始营业时间，如：9:00',
  `end_time` varchar(5) NOT NULL COMMENT '结束营业时间，如：18:00',
  `days` int(3) NOT NULL COMMENT '删除days天前的数据，针对随时间增长的数据',
  `goods_count_scope` varchar(8) NOT NULL COMMENT '货物件数区间',
  `price_scope` varchar(8) NOT NULL COMMENT '订单金额区间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `base_data`
--

LOCK TABLES `base_data` WRITE;
/*!40000 ALTER TABLE `base_data` DISABLE KEYS */;
INSERT INTO `base_data` VALUES ('赣州',110,'9:00','18:00',5,'2:4','18:22');
/*!40000 ALTER TABLE `base_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` int(11) DEFAULT '0' COMMENT '父级分类',
  `name` varchar(10) NOT NULL COMMENT '分类名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,0,'汽摩配件'),(2,0,'五金机电'),(3,0,'建材家居'),(4,0,'日用百货'),(5,0,'服装鞋包'),(6,0,'农资农机'),(7,0,'粮油副食'),(8,1,'汽车配件'),(9,1,'汽车用品'),(10,1,'汽保工具'),(11,1,'汽车电器'),(12,1,'汽车整车'),(13,1,'摩电整车'),(14,2,'五金工具'),(15,2,'机械设备'),(16,2,'日用五金'),(17,2,'水暖五金'),(18,2,'建筑五金'),(19,3,'卫浴家装'),(20,3,'建材配件'),(21,3,'安防器材'),(22,3,'厨房家装'),(23,3,'办公家具'),(24,3,'户外家具'),(25,3,'成套家具'),(26,4,'家用电器'),(27,4,'日化用品'),(28,4,'日杂百货'),(29,4,'小家电'),(30,4,'针织家纺'),(31,4,'窗帘布艺'),(32,4,'玩具益智'),(33,4,'文体用品'),(34,5,'箱包皮具'),(35,5,'图书音像'),(36,5,'服装饰品'),(37,6,'农机农资'),(38,7,'粮油副食');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `city` (
  `name` varchar(20) NOT NULL COMMENT '城市名称',
  `lng` decimal(10,7) DEFAULT NULL COMMENT '经度',
  `lat` decimal(10,7) DEFAULT NULL COMMENT '纬度',
  `order_count` bigint(20) DEFAULT '0' COMMENT '订单数量',
  `weight` decimal(4,3) DEFAULT '0.000' COMMENT '系数(权重)',
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES ('兰州',103.8235570,36.0580390,21121,0.113),('柳州',109.4117030,24.3146170,19626,0.105),('济宁',116.5872450,35.4153930,10280,0.055),('绵阳',104.7417220,31.4640200,87102,0.466),('菏泽',115.4693810,35.2465310,1495,0.008),('衡阳',112.6076930,26.9003580,2056,0.011),('赣州',114.9402780,25.8509700,31962,0.171),('长沙',112.9822790,28.1940900,12710,0.068);
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city_price_goodscount`
--

DROP TABLE IF EXISTS `city_price_goodscount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `city_price_goodscount` (
  `year` char(4) NOT NULL COMMENT '年份',
  `city` varchar(20) NOT NULL COMMENT '城市名称',
  `order_price` bigint(20) DEFAULT '0' COMMENT '订单总额',
  `goods_count` bigint(20) DEFAULT '0' COMMENT '货物总量',
  UNIQUE KEY `unique_year_city` (`year`,`city`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city_price_goodscount`
--

LOCK TABLES `city_price_goodscount` WRITE;
/*!40000 ALTER TABLE `city_price_goodscount` DISABLE KEYS */;
INSERT INTO `city_price_goodscount` VALUES ('2019','兰州',422420,63363),('2019','柳州',392520,58878),('2019','济宁',205600,30840),('2019','绵阳',1742040,261306),('2019','菏泽',29900,4485),('2019','衡阳',41120,6168),('2019','赣州',639240,95886),('2019','长沙',254200,38130);
/*!40000 ALTER TABLE `city_price_goodscount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `date_hot_type`
--

DROP TABLE IF EXISTS `date_hot_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `date_hot_type` (
  `date` date DEFAULT NULL COMMENT '日期',
  `type` varchar(10) NOT NULL COMMENT '二级分类',
  `order_count` int(11) DEFAULT '0' COMMENT '订单数量',
  UNIQUE KEY `unique_date_type` (`date`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `date_hot_type`
--

LOCK TABLES `date_hot_type` WRITE;
/*!40000 ALTER TABLE `date_hot_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `date_hot_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `date_statistics`
--

DROP TABLE IF EXISTS `date_statistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `date_statistics` (
  `date` date DEFAULT NULL COMMENT '日期',
  `order_count` int(11) DEFAULT '0' COMMENT '订单数量',
  `order_price` bigint(20) DEFAULT '0' COMMENT '订单总额',
  `goods_count` bigint(20) DEFAULT '0' COMMENT '货物总量',
  UNIQUE KEY `unique_date` (`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `date_statistics`
--

LOCK TABLES `date_statistics` WRITE;
/*!40000 ALTER TABLE `date_statistics` DISABLE KEYS */;
/*!40000 ALTER TABLE `date_statistics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history_statistics`
--

DROP TABLE IF EXISTS `history_statistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `history_statistics` (
  `id` varchar(10) DEFAULT NULL,
  `order_count` bigint(20) DEFAULT '0' COMMENT '订单数量',
  `order_price` bigint(20) DEFAULT '0' COMMENT '订单总额',
  `goods_count` bigint(20) DEFAULT '0' COMMENT '货物总量',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history_statistics`
--

LOCK TABLES `history_statistics` WRITE;
/*!40000 ALTER TABLE `history_statistics` DISABLE KEYS */;
INSERT INTO `history_statistics` VALUES ('history',186915,3738309,560764);
/*!40000 ALTER TABLE `history_statistics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hot_category`
--

DROP TABLE IF EXISTS `hot_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hot_category` (
  `category` varchar(10) NOT NULL COMMENT '一级分类',
  `order_count` bigint(20) DEFAULT '0' COMMENT '订单数量',
  UNIQUE KEY `unique_category` (`category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hot_category`
--

LOCK TABLES `hot_category` WRITE;
/*!40000 ALTER TABLE `hot_category` DISABLE KEYS */;
INSERT INTO `hot_category` VALUES ('五金机电',79852),('农资农机',11892),('建材家居',31962),('日用百货',21222),('服装鞋包',12892),('汽摩配件',28421),('粮油副食',10821);
/*!40000 ALTER TABLE `hot_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `category` varchar(10) DEFAULT NULL COMMENT '货物一级分类',
  `type` varchar(10) DEFAULT NULL COMMENT '货物二级分类',
  `number` int(11) DEFAULT NULL COMMENT '货物数量',
  `price` int(11) DEFAULT NULL COMMENT '订单运费',
  `city` varchar(20) DEFAULT NULL COMMENT '城市',
  `create_time` datetime DEFAULT NULL COMMENT '生成时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `week_price_trend`
--

DROP TABLE IF EXISTS `week_price_trend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `week_price_trend` (
  `mon_date` date DEFAULT NULL COMMENT '周一',
  `order_price` bigint(20) DEFAULT '0' COMMENT '周订单金额',
  UNIQUE KEY `mon_date` (`mon_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `week_price_trend`
--

LOCK TABLES `week_price_trend` WRITE;
/*!40000 ALTER TABLE `week_price_trend` DISABLE KEYS */;
INSERT INTO `week_price_trend` VALUES ('2019-12-16',396620),('2019-10-21',345634),('2019-10-28',389943),('2019-11-04',432354),('2019-11-11',456734),('2019-11-18',476734),('2019-11-25',426764),('2019-12-02',396764),('2019-12-09',416764);
/*!40000 ALTER TABLE `week_price_trend` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-22 17:33:22
