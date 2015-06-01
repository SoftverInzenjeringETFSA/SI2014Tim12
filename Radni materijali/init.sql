-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: tim12
-- ------------------------------------------------------
-- Server version	5.6.17

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
-- Current Database: `tim12`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `tim12` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_slovenian_ci */;

USE `tim12`;

--
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `korisnik` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(255) COLLATE utf8_slovenian_ci DEFAULT NULL,
  `PASSWORD` varchar(255) COLLATE utf8_slovenian_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8 COLLATE=utf8_slovenian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnik`
--

LOCK TABLES `korisnik` WRITE;
/*!40000 ALTER TABLE `korisnik` DISABLE KEYS */;
INSERT INTO `korisnik` VALUES (98,'doktor1','*ABB76B60DFF5C92517C7F297B59F04B56775CAC9'),(99,'doktor2','*D2A2C98AAA92040CBF0A0E11ECC63EF64408A6AE'),(100,'doktor3','*1CCFC997FABE8E205FCB1DDB8A609CA8DB5E8771'),(101,'doktor4','*7FEDEC2D17D65EC8046C40E0098CC154B78B1628'),(102,'doktor5','*CF7C3313631D20506D2B3BA636EF705B22790AA3'),(103,'doktor6','*CEB966476BB4F94B87B14452499031C29E1F0C4B'),(104,'doktor7','*5855E138260073BF6AE1A154B97F8FFE012F8E06'),(105,'doktor8','*E59580EDD0FEF388FCE00FDC3F72D08196C4C6E5'),(106,'doktor9','*EA0EA8694D05C642A83CBF7CAA57BF91DC934F5B'),(107,'sestra1','*F23ECD055AF35BBB632E032CF422E958DD0AB0D2'),(108,'sestra2','*4DBC347271803AF9678976C71FE6676AB1FE7066'),(109,'sestra3','*2B01598E7073A2AB43E82F01567239BB963AE696'),(110,'sestra4','*F6D04E885E0244E3FF68827E07FEF06A90E002FB'),(111,'sestra5','*B9E52DCF3361F92DF76905F9B1C4724410B6ABEA'),(112,'sestra6','*CED85A43707B2DF5CBD6806DD6EF3D238D7DB7F3'),(113,'sestra7','*8296E631A1345AFDE33CC7F43B23322E76D36816'),(114,'sestra8','*F53034A71892B142B9D7E420A5B6EFB2AD168421'),(115,'sestra9','*89E0F7168C8602BBABB0DA01F5EE75FF07D322AA');
/*!40000 ALTER TABLE `korisnik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materijal`
--

DROP TABLE IF EXISTS `materijal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `materijal` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAZIV` varchar(255) COLLATE utf8_slovenian_ci DEFAULT NULL,
  `MJERNAJEDINICA` varchar(255) COLLATE utf8_slovenian_ci DEFAULT NULL,
  `CIJENA` double DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=493 DEFAULT CHARSET=utf8 COLLATE=utf8_slovenian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materijal`
--

LOCK TABLES `materijal` WRITE;
/*!40000 ALTER TABLE `materijal` DISABLE KEYS */;
/*!40000 ALTER TABLE `materijal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materijaltipzahvata`
--

DROP TABLE IF EXISTS `materijaltipzahvata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `materijaltipzahvata` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `MATERIJALID` bigint(20) DEFAULT NULL,
  `TIPZAHVATAID` bigint(20) DEFAULT NULL,
  `KOLICINA` double DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=193 DEFAULT CHARSET=utf8 COLLATE=utf8_slovenian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materijaltipzahvata`
--

LOCK TABLES `materijaltipzahvata` WRITE;
/*!40000 ALTER TABLE `materijaltipzahvata` DISABLE KEYS */;
/*!40000 ALTER TABLE `materijaltipzahvata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `obavljenizahvat`
--

DROP TABLE IF EXISTS `obavljenizahvat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `obavljenizahvat` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ZAHVATID` bigint(20) DEFAULT NULL,
  `POSJETAID` bigint(20) DEFAULT NULL,
  `CIJENA` double DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=217 DEFAULT CHARSET=utf8 COLLATE=utf8_slovenian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `obavljenizahvat`
--

LOCK TABLES `obavljenizahvat` WRITE;
/*!40000 ALTER TABLE `obavljenizahvat` DISABLE KEYS */;
/*!40000 ALTER TABLE `obavljenizahvat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pacijent`
--

DROP TABLE IF EXISTS `pacijent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pacijent` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `IMEIPREZIME` varchar(255) COLLATE utf8_slovenian_ci DEFAULT NULL,
  `OPIS` varchar(255) COLLATE utf8_slovenian_ci DEFAULT NULL,
  `TELEFON` varchar(255) COLLATE utf8_slovenian_ci DEFAULT NULL,
  `DATUMRODJENJA` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=290 DEFAULT CHARSET=utf8 COLLATE=utf8_slovenian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pacijent`
--

LOCK TABLES `pacijent` WRITE;
/*!40000 ALTER TABLE `pacijent` DISABLE KEYS */;
/*!40000 ALTER TABLE `pacijent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posjeta`
--

DROP TABLE IF EXISTS `posjeta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `posjeta` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PACIJENTID` bigint(20) DEFAULT NULL,
  `DOKTOR` varchar(255) COLLATE utf8_slovenian_ci DEFAULT NULL,
  `DIJAGNOZA` varchar(255) COLLATE utf8_slovenian_ci DEFAULT NULL,
  `VRIJEME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=utf8 COLLATE=utf8_slovenian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posjeta`
--

LOCK TABLES `posjeta` WRITE;
/*!40000 ALTER TABLE `posjeta` DISABLE KEYS */;
/*!40000 ALTER TABLE `posjeta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `termin`
--

DROP TABLE IF EXISTS `termin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `termin` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PACIJENTID` bigint(20) DEFAULT NULL,
  `DOKTOR` varchar(255) COLLATE utf8_slovenian_ci DEFAULT NULL,
  `VRIJEME` datetime DEFAULT NULL,
  `OTKAZANO` bit(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8 COLLATE=utf8_slovenian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `termin`
--

LOCK TABLES `termin` WRITE;
/*!40000 ALTER TABLE `termin` DISABLE KEYS */;
/*!40000 ALTER TABLE `termin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipzahvata`
--

DROP TABLE IF EXISTS `tipzahvata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipzahvata` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAZIV` varchar(255) COLLATE utf8_slovenian_ci DEFAULT NULL,
  `OPIS` varchar(255) COLLATE utf8_slovenian_ci DEFAULT NULL,
  `CIJENA` double DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=385 DEFAULT CHARSET=utf8 COLLATE=utf8_slovenian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipzahvata`
--

LOCK TABLES `tipzahvata` WRITE;
/*!40000 ALTER TABLE `tipzahvata` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipzahvata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utrosenimaterijal`
--

DROP TABLE IF EXISTS `utrosenimaterijal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `utrosenimaterijal` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `MATERIJALID` bigint(20) DEFAULT NULL,
  `OBAVLJENIZAHVATID` bigint(20) DEFAULT NULL,
  `KOLICINA` double DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=205 DEFAULT CHARSET=utf8 COLLATE=utf8_slovenian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utrosenimaterijal`
--

LOCK TABLES `utrosenimaterijal` WRITE;
/*!40000 ALTER TABLE `utrosenimaterijal` DISABLE KEYS */;
/*!40000 ALTER TABLE `utrosenimaterijal` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-06-01 19:30:44
