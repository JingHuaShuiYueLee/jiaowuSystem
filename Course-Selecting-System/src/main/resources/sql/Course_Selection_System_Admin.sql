
DROP TABLE IF EXISTS `Admin`;

CREATE TABLE `Admin` (
  `adminId` int(11) NOT NULL,
  `adminName` varchar(200) NOT NULL,
  `adminPass` varchar(200) NOT NULL,
  `sex` varchar(200) NOT NULL,
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


LOCK TABLES `Admin` WRITE;
INSERT INTO `Admin` VALUES (1111000001,'花天狂骨','1111000001','男'),(1111000002,'蓝染','1111000002','男'),(1111000003,'小乌','1111000003','男');
UNLOCK TABLES;
