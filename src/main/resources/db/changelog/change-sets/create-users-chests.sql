
DROP TABLE IF EXISTS `users_chests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_chests` (
                                `userId` int(11) unsigned NOT NULL AUTO_INCREMENT,
                                `chestId` int(11) unsigned NOT NULL,
                                PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;