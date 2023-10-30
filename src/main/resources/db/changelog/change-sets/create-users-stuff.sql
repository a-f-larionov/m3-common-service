
DROP TABLE IF EXISTS `users_stuff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_stuff` (
                               `userId` int(11) unsigned NOT NULL AUTO_INCREMENT,
                               `hummerQty` int(11) unsigned NOT NULL DEFAULT '0',
                               `shuffleQty` int(11) unsigned NOT NULL DEFAULT '0',
                               `goldQty` int(11) unsigned NOT NULL DEFAULT '0',
                               `lightningQty` int(11) unsigned NOT NULL,
                               PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
