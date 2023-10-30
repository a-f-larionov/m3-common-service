
DROP TABLE IF EXISTS `payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payments` (
                            `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
                            `time` int(11) unsigned NOT NULL DEFAULT '0',
                            `userId` int(11) unsigned NOT NULL DEFAULT '0',
                            `orderId` int(11) unsigned NOT NULL DEFAULT '0',
                            `itemPrice` int(11) unsigned NOT NULL DEFAULT '0',
                            PRIMARY KEY (`id`),
                            KEY `orderId` (`orderId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
