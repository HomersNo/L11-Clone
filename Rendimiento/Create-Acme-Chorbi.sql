start transaction;

drop database if exists `acme-chorbi`;
create database `acme-chorbi`;

use `acme-chorbi`;

-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: Acme-Chorbi
-- ------------------------------------------------------
-- Server version	5.5.29

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
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_idt4b4u259p6vs4pyr9lax4eg` (`userAccount_id`),
  KEY `AdministratorUK_a99onpf5vqpr7eas9f06dihm0` (`name`),
  KEY `AdministratorUK_hn5atmqw0ai39935ekgv39gsu` (`surname`),
  CONSTRAINT `FK_idt4b4u259p6vs4pyr9lax4eg` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (89,0,'admin1@gmail.com','Administrador','1234','1',75),(90,0,'admin2@gmail.com','Administrador','2345','2',76);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chirp`
--

DROP TABLE IF EXISTS `chirp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chirp` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `moment` datetime DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `folder_id` int(11) NOT NULL,
  `recipient_id` int(11) NOT NULL,
  `sender_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_5l4nrmy0309lw7kb0r5apr5m7` (`folder_id`),
  KEY `UK_7nnun85ngke6yiv2qnerep5uc` (`sender_id`),
  KEY `UK_20arsrvo5x8fqqrj7o5tomu6f` (`recipient_id`),
  CONSTRAINT `FK_5l4nrmy0309lw7kb0r5apr5m7` FOREIGN KEY (`folder_id`) REFERENCES `folder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chirp`
--

LOCK TABLES `chirp` WRITE;
/*!40000 ALTER TABLE `chirp` DISABLE KEYS */;
INSERT INTO `chirp` VALUES (125,0,'2017-03-03 23:49:00','Hola','Estoy jugando a la Play',115,91,92),(126,0,'2017-03-03 23:49:00','Hola','Estoy jugando a la Play',114,91,92),(127,0,'2017-03-03 23:49:00','Hola','¿Te vienes a cultura?',113,92,91),(128,0,'2017-03-03 23:49:00','Hola','¿Te vienes a cultura?',116,92,91),(129,0,'2017-03-03 23:49:00','Illo','La Switch mola',118,93,94),(130,0,'2017-03-03 23:49:00','Illo','La Switch mola',119,93,94),(131,0,'2017-03-03 23:49:00','¿Estás interesado en esto?','Era una broma',119,93,94),(132,0,'2017-03-03 23:49:00','¿Estás interesado en esto?','Era una broma',118,93,94);
/*!40000 ALTER TABLE `chirp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chirp_attachments`
--

DROP TABLE IF EXISTS `chirp_attachments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chirp_attachments` (
  `Chirp_id` int(11) NOT NULL,
  `attachments` varchar(255) DEFAULT NULL,
  KEY `FK_19xst1rktyonkumt1r20fe0gh` (`Chirp_id`),
  CONSTRAINT `FK_19xst1rktyonkumt1r20fe0gh` FOREIGN KEY (`Chirp_id`) REFERENCES `chirp` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chirp_attachments`
--

LOCK TABLES `chirp_attachments` WRITE;
/*!40000 ALTER TABLE `chirp_attachments` DISABLE KEYS */;
INSERT INTO `chirp_attachments` VALUES (125,'http://www.bouncepen.com/wp-content/themes/twentyfifteen/uploads/user-photo/dummy-image.png'),(127,'http://www.bouncepen.com/wp-content/themes/twentyfifteen/uploads/user-photo/dummy-image.png'),(128,'http://www.bouncepen.com/wp-content/themes/twentyfifteen/uploads/user-photo/dummy-image.png'),(129,'http://www.bouncepen.com/wp-content/themes/twentyfifteen/uploads/user-photo/dummy-image.png'),(129,'http://www.bouncepen.com/wp-content/themes/twentyfifteen/uploads/user-photo/dummy-image.png'),(130,'http://www.bouncepen.com/wp-content/themes/twentyfifteen/uploads/user-photo/dummy-image.png'),(130,'http://www.bouncepen.com/wp-content/themes/twentyfifteen/uploads/user-photo/dummy-image.png'),(130,'http://www.bouncepen.com/wp-content/themes/twentyfifteen/uploads/user-photo/dummy-image.png'),(130,'http://www.bouncepen.com/wp-content/themes/twentyfifteen/uploads/user-photo/dummy-image.png'),(132,'http://www.bouncepen.com/wp-content/themes/twentyfifteen/uploads/user-photo/dummy-image.png'),(132,'http://www.bouncepen.com/wp-content/themes/twentyfifteen/uploads/user-photo/dummy-image.png');
/*!40000 ALTER TABLE `chirp_attachments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chorbi`
--

DROP TABLE IF EXISTS `chorbi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chorbi` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `cumulatedFee` double DEFAULT NULL,
  `banned` bit(1) DEFAULT NULL,
  `birthDate` date DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `relationshipType` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_qrvmwkp25xc5exr6m3jgaxu4x` (`userAccount_id`),
  KEY `ChorbiCreditHolderUK_a99onpf5vqpr7eas9f06dihm0` (`name`),
  KEY `ChorbiCreditHolderUK_hn5atmqw0ai39935ekgv39gsu` (`surname`),
  KEY `UK_fmbo9760r9xuihqon0jw7bq8n` (`relationshipType`),
  KEY `UK_ovkyoevssjuf41lisig22x0b8` (`birthDate`),
  KEY `UK_hdlfsw7lxgcbclq2iom6me4h8` (`genre`),
  KEY `UK_km8ak84t5ibyqk4lbi9a1uy5` (`banned`),
  KEY `UK_aqvabuvofsj94o9oj3pc9h7kw` (`country`),
  KEY `UK_5wdj0ha7n2mjpiojoe3gy0qh9` (`description`),
  KEY `UK_n2d3ptbt8wsvb46r1aqakw1ke` (`state`),
  KEY `UK_sj1guwjtekslj008sgqukax0l` (`province`),
  KEY `UK_jx9qwh09r59k08x9ykh2gss0t` (`city`),
  CONSTRAINT `FK_qrvmwkp25xc5exr6m3jgaxu4x` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chorbi`
--

LOCK TABLES `chorbi` WRITE;
/*!40000 ALTER TABLE `chorbi` DISABLE KEYS */;
INSERT INTO `chorbi` VALUES (91,0,'customer1@gmail.com','Edurne','2345','Almagro',77,4,'\0','1989-06-05','Alicante','España','Mira mi foto','WOMAN','http://www.que.es/archivos/201511/edurne-985xXx80.jpg','Alicante','ACTIVITIES','Comunidad Valenciana'),(92,0,'customer2@gmail.com','Pablo','2345','Nefstrife',78,3,'\0','1999-01-11','El zaudín','España','VIKINGO','MAN','http://www.metal-archives.com/images/8/4/1/6/84165_logo.jpg','Sevilla','LOVE','Andalucía'),(93,0,'customer3@gmail.com','Paco Javier','2345','De la Fuente Bonilla',79,0,'\0','1995-10-06','Camas','España','El año que viene me saco estadística','MAN','https://yts.ag/assets/images/movies/Labyrinth_1986/large-screenshot2.jpg','Sevilla','FRIENDSHIP','Andalucía'),(94,0,'customer4@gmail.com','Talia','2345','Al Ghul',80,1,'\0','1959-04-01','Kansas City','USA','No pain no bane','WOMAN','http://vignette1.wikia.nocookie.net/batman/images/d/d0/Miranda.jpg/revision/latest?cb=20121205211641','Wyandotte','LOVE','Kansas'),(95,0,'vamosARio@gmail.com','Arturian','2345','Kurosakian',81,7,'\0','1995-11-03','En la plaza esa rara','España','Voy por falla 85, top mil ez','MAN','http://imgs.abduzeedo.com/files/articles/wheat-fields-photography/5.jpg','Triana','LOVE','Sevilla'),(96,0,'vapingAllDay@gmail.com','Alvaracha','2345','Acha',82,6,'\0','1993-04-01','Sevilla','España','Pandilla a Patches es bueno','MAN','http://marmoles.mex.tl/imagesnew2/0/0/0/1/0/9/2/6/3/2/marmol%20blanco%20royal%20block.jpg','Sevilla','FRIENDSHIP','Sevilla');
/*!40000 ALTER TABLE `chorbi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creditcard`
--

DROP TABLE IF EXISTS `creditcard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `creditcard` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `CVV` int(11) NOT NULL,
  `brandName` varchar(255) DEFAULT NULL,
  `creditCardNumber` varchar(16) DEFAULT NULL,
  `expirationMonth` int(11) NOT NULL,
  `expirationYear` int(11) NOT NULL,
  `holderName` varchar(255) DEFAULT NULL,
  `holder_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ptwgpqa7quo97yholegwrh7bm` (`holder_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creditcard`
--

LOCK TABLES `creditcard` WRITE;
/*!40000 ALTER TABLE `creditcard` DISABLE KEYS */;
INSERT INTO `creditcard` VALUES (103,0,842,'VISA','4532896667885495',12,19,'Edurne',91),(104,0,842,'VISA','5497733952520638',7,18,'Nefasto',92),(105,0,122,'VISA','6011989537619189',4,22,'Francisco',93),(106,0,122,'VISA','6011989537619189',4,22,'Arturo',95),(107,0,122,'VISA','6011989537619189',4,22,'Alvaro',96),(108,0,122,'VISA','6011989537619189',4,22,'Javi',97),(109,0,122,'VISA','6011989537619189',4,22,'Diego',98),(110,0,122,'VISA','6011989537619189',4,22,'Alberto',99),(111,0,122,'VISA','6011989537619189',4,22,'Pablo',100),(112,0,122,'VISA','6011989537619189',4,22,'Jose',101);
/*!40000 ALTER TABLE `creditcard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creditholder`
--

DROP TABLE IF EXISTS `creditholder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `creditholder` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `cumulatedFee` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_swnd9jnejwb9r0wumconbl592` (`userAccount_id`),
  KEY `CreditHolderUK_a99onpf5vqpr7eas9f06dihm0` (`name`),
  KEY `CreditHolderUK_hn5atmqw0ai39935ekgv39gsu` (`surname`),
  CONSTRAINT `FK_swnd9jnejwb9r0wumconbl592` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creditholder`
--

LOCK TABLES `creditholder` WRITE;
/*!40000 ALTER TABLE `creditholder` DISABLE KEYS */;
/*!40000 ALTER TABLE `creditholder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `numberSeat` int(11) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `organiser_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_qv73ucd7ekbqiyibyjfumei3h` (`organiser_id`),
  CONSTRAINT `FK_qv73ucd7ekbqiyibyjfumei3h` FOREIGN KEY (`organiser_id`) REFERENCES `manager` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (142,0,'Best party ever','2017-07-23 15:00:00',1300,'http://img10.deviantart.net/b3f4/i/2012/091/3/c/mlp__fim_pony_party_summary_comic_hi_res_by_moviefreaq-d4ulfqo.png','MLP Party',97),(143,0,'Barco ready','2018-07-23 10:00:00',500,'http://www.holaislascanarias.com/sites/default/files/lanzarote.jpg','Viaje a canarias',99),(144,0,'En Triana, bueno bueno','2017-05-30 21:00:00',10,'http://www.infofranquicias.com/media/mediauploads/Papasa-foto1.jpg','Papasá',98),(145,0,'Full equip or kick','2017-09-23 10:00:00',25,'https://lh3.googleusercontent.com/-htwgMWOeoxo/TpRONlsITNI/AAAAAAAAAL0/dnC35Uoz78Y/s1024/BT_InstanceMap.jpg','Raideo Mítico',97),(146,0,'No monk no party','2017-05-10 00:00:00',3,'http://i.imgur.com/Vi1WVMy.jpg','Greater rift lvl 987',101),(147,0,'Ni cebada ni trigo ni avena ni ná','2021-04-11 10:00:00',45,'http://cienciauanl.uanl.mx/wp-content/uploads/2015/04/glutenalimentos.jpg','Reunión sin gluten',100);
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_chorbi`
--

DROP TABLE IF EXISTS `event_chorbi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_chorbi` (
  `Event_id` int(11) NOT NULL,
  `registered_id` int(11) NOT NULL,
  KEY `FK_bjo8bqc53ul48jveck19qp3f9` (`registered_id`),
  KEY `FK_5uahmqgn27ap6mv4m43irmwjy` (`Event_id`),
  CONSTRAINT `FK_5uahmqgn27ap6mv4m43irmwjy` FOREIGN KEY (`Event_id`) REFERENCES `event` (`id`),
  CONSTRAINT `FK_bjo8bqc53ul48jveck19qp3f9` FOREIGN KEY (`registered_id`) REFERENCES `chorbi` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_chorbi`
--

LOCK TABLES `event_chorbi` WRITE;
/*!40000 ALTER TABLE `event_chorbi` DISABLE KEYS */;
INSERT INTO `event_chorbi` VALUES (143,94),(143,95),(144,91),(144,92),(144,96),(145,91),(146,94),(147,96),(147,95);
/*!40000 ALTER TABLE `event_chorbi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `folder`
--

DROP TABLE IF EXISTS `folder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `folder` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `chorbi_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_p4jovc1rl6j8t25x39rygrotu` (`chorbi_id`),
  KEY `UK_l1kp977466ddsv762wign7kdh` (`name`),
  CONSTRAINT `FK_p4jovc1rl6j8t25x39rygrotu` FOREIGN KEY (`chorbi_id`) REFERENCES `chorbi` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `folder`
--

LOCK TABLES `folder` WRITE;
/*!40000 ALTER TABLE `folder` DISABLE KEYS */;
INSERT INTO `folder` VALUES (113,0,'Sent',91),(114,0,'Received',91),(115,0,'Sent',92),(116,0,'Received',92),(117,0,'Sent',93),(118,0,'Received',93),(119,0,'Sent',94),(120,0,'Received',94),(121,0,'Sent',95),(122,0,'Received',95),(123,0,'Sent',96),(124,0,'Received',96);
/*!40000 ALTER TABLE `folder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('DomainEntity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `likes` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `stars` int(11) DEFAULT NULL,
  `chorbi_id` int(11) NOT NULL,
  `liked_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_nxdfictlcowqlvpx85xy4v2wx` (`chorbi_id`,`liked_id`),
  KEY `FK_5f2yipvghjdnd82byd8c8au33` (`liked_id`),
  CONSTRAINT `FK_5f2yipvghjdnd82byd8c8au33` FOREIGN KEY (`liked_id`) REFERENCES `chorbi` (`id`),
  CONSTRAINT `FK_9ukdh4g921mejdk8ppdmloudu` FOREIGN KEY (`chorbi_id`) REFERENCES `chorbi` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
INSERT INTO `likes` VALUES (136,0,'Guapi','2017-03-25 13:45:00',2,94,93),(137,0,'Un dark souls?','2017-03-30 21:43:00',3,91,93),(138,0,'De SeBihllYaaA??¿?','2017-03-31 12:00:00',3,92,94),(139,0,'Una cebadita?','2017-03-31 12:00:00',3,96,95),(140,0,'¿Quedamos?','2017-03-31 12:00:00',1,91,96),(141,0,'Mi num 2345','2017-03-31 12:00:00',3,94,92);
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manager` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `cumulatedFee` double DEFAULT NULL,
  `VATNumber` varchar(255) DEFAULT NULL,
  `companyName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_84bmmxlq61tiaoc7dy7kdcghh` (`userAccount_id`),
  KEY `ManagerCreditHolderUK_a99onpf5vqpr7eas9f06dihm0` (`name`),
  KEY `ManagerCreditHolderUK_hn5atmqw0ai39935ekgv39gsu` (`surname`),
  CONSTRAINT `FK_84bmmxlq61tiaoc7dy7kdcghh` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES (97,0,'matriculaOrDie@gmail.com','Javier','2345','Ballena',83,4,'43321','Google'),(98,0,'queFresquitoHace@gmail.com','Diego','2345','Alonso',84,4,'896234','Portugal SA'),(99,0,'siNoLlegoALeyendaMeRapo@gmail.com','Alberto','2345','Bujarranse',85,0,'852842','Youtube'),(100,0,'mañanaEntregoLaDashboard@gmail.com','Pavfvfvflo','2345','Vasques',86,1,'43321','Fixing PCs Inc'),(101,0,'mañanaEntregoLaDashboard@gmail.com','José Luis','2345','Marmol',87,6,'43321','Openinguess'),(102,0,'noEstarasEstudiandoEnCultura@gmail.com','Emilito','2345','Casper',88,0,'43321','No me duele SL');
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `searchtemplate`
--

DROP TABLE IF EXISTS `searchtemplate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `searchtemplate` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `relationshipType` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `chorbi_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_n6tillyj5veb60uuot1b24lac` (`chorbi_id`),
  CONSTRAINT `FK_n6tillyj5veb60uuot1b24lac` FOREIGN KEY (`chorbi_id`) REFERENCES `chorbi` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `searchtemplate`
--

LOCK TABLES `searchtemplate` WRITE;
/*!40000 ALTER TABLE `searchtemplate` DISABLE KEYS */;
INSERT INTO `searchtemplate` VALUES (133,0,30,'Sevilla','España','WOMAN','futa','2017-03-30 00:00:00','Sevilla','LOVE','Andalucía',91),(134,0,25,'Alicante','España','MAN','ball','2017-03-30 00:00:00','Alicante','ACTIVITIES','Comunidad Valenciana',92),(135,0,55,'Sevilla','España','WOMAN','welp','2017-03-30 00:00:00','Sevilla','LOVE','Andalucía',94);
/*!40000 ALTER TABLE `searchtemplate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `searchtemplate_chorbi`
--

DROP TABLE IF EXISTS `searchtemplate_chorbi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `searchtemplate_chorbi` (
  `SearchTemplate_id` int(11) NOT NULL,
  `chorbies_id` int(11) NOT NULL,
  KEY `FK_3t03s45pt13r22kb7y510060k` (`chorbies_id`),
  KEY `FK_8qqojnmpd0r5ur1bo34ymb973` (`SearchTemplate_id`),
  CONSTRAINT `FK_8qqojnmpd0r5ur1bo34ymb973` FOREIGN KEY (`SearchTemplate_id`) REFERENCES `searchtemplate` (`id`),
  CONSTRAINT `FK_3t03s45pt13r22kb7y510060k` FOREIGN KEY (`chorbies_id`) REFERENCES `chorbi` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `searchtemplate_chorbi`
--

LOCK TABLES `searchtemplate_chorbi` WRITE;
/*!40000 ALTER TABLE `searchtemplate_chorbi` DISABLE KEYS */;
INSERT INTO `searchtemplate_chorbi` VALUES (133,92),(133,93),(134,94);
/*!40000 ALTER TABLE `searchtemplate_chorbi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `systemconfiguration`
--

DROP TABLE IF EXISTS `systemconfiguration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `systemconfiguration` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `cacheTime` time DEFAULT NULL,
  `feeChorbi` double DEFAULT NULL,
  `feeManager` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `systemconfiguration`
--

LOCK TABLES `systemconfiguration` WRITE;
/*!40000 ALTER TABLE `systemconfiguration` DISABLE KEYS */;
INSERT INTO `systemconfiguration` VALUES (148,0,'12:00:00',1,1);
/*!40000 ALTER TABLE `systemconfiguration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `systemconfiguration_banners`
--

DROP TABLE IF EXISTS `systemconfiguration_banners`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `systemconfiguration_banners` (
  `SystemConfiguration_id` int(11) NOT NULL,
  `banners` varchar(255) DEFAULT NULL,
  KEY `FK_4liej1n7c84naefsns1kruy13` (`SystemConfiguration_id`),
  CONSTRAINT `FK_4liej1n7c84naefsns1kruy13` FOREIGN KEY (`SystemConfiguration_id`) REFERENCES `systemconfiguration` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `systemconfiguration_banners`
--

LOCK TABLES `systemconfiguration_banners` WRITE;
/*!40000 ALTER TABLE `systemconfiguration_banners` DISABLE KEYS */;
INSERT INTO `systemconfiguration_banners` VALUES (148,'http://i.imgur.com/lDhkAcr.jpg'),(148,'http://i.imgur.com/hZkZS9z.jpg'),(148,'http://i.imgur.com/bP4ykEJ.jpg');
/*!40000 ALTER TABLE `systemconfiguration_banners` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount`
--

DROP TABLE IF EXISTS `useraccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `isEnabled` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_csivo9yqa08nrbkog71ycilh5` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount`
--

LOCK TABLES `useraccount` WRITE;
/*!40000 ALTER TABLE `useraccount` DISABLE KEYS */;
INSERT INTO `useraccount` VALUES (75,0,'','','21232f297a57a5a743894a0e4a801fc3','admin'),(76,0,'','','c84258e9c39059a89ab77d846ddab909','admin2'),(77,0,'','','3daa859a294cdefb20a84840240a76f5','chorbi1'),(78,0,'','','0c8746de81268518ff83490301db8652','chorbi2'),(79,0,'','','a2da05a88eead7e64593826cafc6a7a7','chorbi3'),(80,0,'','','a09dd233386632e297a7f4f461989563','chorbi4'),(81,0,'','','7e062f6f2a4c0f7ec5abacef2917e033','chorbi5'),(82,0,'','','0b41c51bd4b755c5b639498f55058fd3','chorbi6'),(83,0,'','','c240642ddef994358c96da82c0361a58','manager1'),(84,0,'','','8df5127cd164b5bc2d2b78410a7eea0c','manager2'),(85,0,'','','2d3a5db4a2a9717b43698520a8de57d0','manager3'),(86,0,'','','e1ec6fc941af3ba79a4ac5242dd39735','manager4'),(87,0,'','','029cb1d27c0b9c551703ccba2591c334','manager5'),(88,0,'','','46bae9ead851e0f288529e6322f4fd77','manager6');
/*!40000 ALTER TABLE `useraccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount_authorities`
--

DROP TABLE IF EXISTS `useraccount_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount_authorities` (
  `UserAccount_id` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_b63ua47r0u1m7ccc9lte2ui4r` (`UserAccount_id`),
  CONSTRAINT `FK_b63ua47r0u1m7ccc9lte2ui4r` FOREIGN KEY (`UserAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount_authorities`
--

LOCK TABLES `useraccount_authorities` WRITE;
/*!40000 ALTER TABLE `useraccount_authorities` DISABLE KEYS */;
INSERT INTO `useraccount_authorities` VALUES (75,'ADMIN'),(76,'ADMIN'),(77,'CHORBI'),(78,'CHORBI'),(79,'CHORBI'),(80,'CHORBI'),(81,'CHORBI'),(82,'CHORBI'),(83,'MANAGER'),(84,'MANAGER'),(85,'MANAGER'),(86,'MANAGER'),(87,'MANAGER'),(88,'MANAGER');
/*!40000 ALTER TABLE `useraccount_authorities` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-21 20:25:36

commit;