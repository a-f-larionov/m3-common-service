
DROP TABLE IF EXISTS `cache_top_score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cache_top_score` (
                                   `userId` int(11) unsigned NOT NULL DEFAULT '0',
                                   `pointId` int(11) unsigned NOT NULL DEFAULT '0',
                                   `place1Uid` int(11) unsigned NOT NULL DEFAULT '0',
                                   `place2Uid` int(11) unsigned NOT NULL DEFAULT '0',
                                   `place3Uid` int(11) unsigned NOT NULL DEFAULT '0',
                                   `pos` int(11) unsigned NOT NULL DEFAULT '0',
                                   PRIMARY KEY (`userId`,`pointId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;