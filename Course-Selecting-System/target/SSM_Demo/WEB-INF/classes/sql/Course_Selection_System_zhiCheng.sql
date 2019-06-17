
DROP TABLE IF EXISTS `Professionaltitle`;

CREATE TABLE `Professionaltitle` (
  `pId` int(11) NOT NULL,
  `pName` varchar(200) NOT NULL,
  PRIMARY KEY (`pId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


LOCK TABLES `Professionaltitle` WRITE;
INSERT INTO `Professionaltitle` VALUES (1,'助教'),(2,'讲师'),(3,'副教授'),(4,'教授');
UNLOCK TABLES;
