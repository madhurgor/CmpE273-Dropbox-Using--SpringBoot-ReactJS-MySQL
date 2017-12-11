-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: test
-- ------------------------------------------------------
-- Server version	5.7.19-log

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

--
-- Table structure for table `user_activity`
--

DROP TABLE IF EXISTS `user_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_activity` (
  `activity_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) NOT NULL,
  `timestamp` varchar(100) NOT NULL,
  `activity` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_activity`
--

LOCK TABLES `user_activity` WRITE;
/*!40000 ALTER TABLE `user_activity` DISABLE KEYS */;
INSERT INTO `user_activity` VALUES (1,'m@g.c1','2017/12/11 01:19:24','Logged In'),(2,'m@g.c1','2017/12/11 01:19:24','Fetching Starred Files'),(3,'m@g.c1','2017/12/11 01:19:26','Fetching Regular Files'),(4,'m@g.c1','2017/12/11 01:19:28','Fetching Starred Files'),(5,'m@g.c1','2017/12/11 01:19:29','Checking Personal Info'),(6,'m@g.c1','2017/12/11 01:21:48','Logged In'),(7,'m@g.c1','2017/12/11 01:21:48','Fetching Starred Files'),(8,'m@g.c1','2017/12/11 01:22:13','Logged In'),(9,'m@g.c1','2017/12/11 01:22:13','Fetching Starred Files'),(10,'m@g.c1','2017/12/11 01:22:23','Logged Out'),(11,'m@g.c1','2017/12/11 01:22:58','Logged In'),(12,'m@g.c1','2017/12/11 01:22:58','Fetching Starred Files'),(13,'m@g.c1','2017/12/11 01:23:00','Fetching Regular Files'),(14,'m@g.c1','2017/12/11 01:23:02','Logged Out'),(15,'m@g.c1','2017/12/11 01:25:23','Logged In'),(16,'m@g.c1','2017/12/11 01:25:23','Fetching Starred Files'),(17,'m@g.c1','2017/12/11 01:25:27','Checking Personal Info'),(18,'m@g.c1','2017/12/11 01:25:30','Logged Out'),(19,'m@g.c1','2017/12/11 01:25:51','Logged In'),(20,'m@g.c1','2017/12/11 01:25:52','Fetching Starred Files'),(21,'m@g.c1','2017/12/11 01:25:56','Logged Out'),(22,'m@g.c1','2017/12/11 01:32:59','Logged In'),(23,'m@g.c1','2017/12/11 01:32:59','Fetching Starred Files'),(24,'m@g.c1','2017/12/11 01:33:03','Fetching Regular Files'),(25,'m@g.c1','2017/12/11 01:33:08','Uploaded a File 202 questions and node  js.txt'),(26,'m@g.c1','2017/12/11 01:33:23','Uploaded a File shimon shim 3rd lect.txt'),(27,'m@g.c1','2017/12/11 01:33:49','Logged Out'),(28,'m@g.c1','2017/12/11 01:36:57','Logged In'),(29,'m@g.c1','2017/12/11 01:36:58','Fetching Starred Files'),(30,'m@g.c1','2017/12/11 01:37:04','Downloaded a File 272.txt'),(31,'m@g.c1','2017/12/11 01:37:55','Fetching Regular Files'),(32,'m@g.c1','2017/12/11 01:37:57','Downloaded a File 202 questions and node  js.txt'),(33,'m@g.c1','2017/12/11 01:38:09','Fetching Starred Files'),(34,'m@g.c1','2017/12/11 01:38:11','Downloaded a File 272.txt'),(35,'m@g.c1','2017/12/11 01:38:58','Downloaded a File 272.txt'),(36,'m@g.c1','2017/12/11 01:40:33','Logged Out'),(37,'m@g.c1','2017/12/11 01:40:39','Logged In'),(38,'m@g.c1','2017/12/11 01:40:40','Fetching Starred Files'),(39,'m@g.c1','2017/12/11 01:40:41','Downloaded a File 272.txt'),(40,'m@g.c1','2017/12/11 01:41:04','Fetching Regular Files'),(41,'m@g.c1','2017/12/11 01:41:07','Starred a File 202 questions and node  js.txt'),(42,'m@g.c1','2017/12/11 01:41:08','Fetching Starred Files'),(43,'m@g.c1','2017/12/11 01:41:12','Downloaded a File 202 questions and node  js.txt'),(44,'m@g.c1','2017/12/11 01:42:24','Downloaded a File 202 questions and node  js.txt'),(45,'m@g.c1','2017/12/11 01:42:31','Downloaded a File 272.txt'),(46,'m@g.c1','2017/12/11 01:42:45','Fetching Regular Files'),(47,'m@g.c1','2017/12/11 01:42:46','Fetching Starred Files'),(48,'m@g.c1','2017/12/11 01:42:48','Fetching Regular Files'),(49,'m@g.c1','2017/12/11 01:44:36','Fetching Starred Files'),(50,'m@g.c1','2017/12/11 01:44:49','Fetching Regular Files'),(51,'m@g.c1','2017/12/11 01:45:03','Checking Personal Info'),(52,'m@g.c1','2017/12/11 01:45:07','Checking Personal Info'),(53,'m@g.c1','2017/12/11 01:45:26','Logged Out'),(54,'m@g.c2','2017/12/11 01:45:47','Signed Up'),(55,'m@g.c2','2017/12/11 01:46:08','Logged In'),(56,'m@g.c2','2017/12/11 01:46:08','Fetching Starred Files'),(57,'m@g.c2','2017/12/11 01:46:21','Logged Out');
/*!40000 ALTER TABLE `user_activity` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-11  2:05:47
