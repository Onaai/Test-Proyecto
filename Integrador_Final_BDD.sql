-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: integrador_final
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cupon`
--

DROP TABLE IF EXISTS `cupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cupon` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `codigo` varchar(255) NOT NULL,
  `porcentaje` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cupon`
--

LOCK TABLES `cupon` WRITE;
/*!40000 ALTER TABLE `cupon` DISABLE KEYS */;
INSERT INTO `cupon` VALUES (11,'A',0.5),(12,'B',0.7),(13,'C',0.3);
/*!40000 ALTER TABLE `cupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado_orden`
--

DROP TABLE IF EXISTS `estado_orden`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estado_orden` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `estado` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_orden`
--

LOCK TABLES `estado_orden` WRITE;
/*!40000 ALTER TABLE `estado_orden` DISABLE KEYS */;
INSERT INTO `estado_orden` VALUES (1,'En proceso...'),(2,'Aprobada'),(3,'Denegada');
/*!40000 ALTER TABLE `estado_orden` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `generos`
--

DROP TABLE IF EXISTS `generos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `generos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `genero` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKi2lf51pph9ftq4g8r1tkr7dyw` (`genero`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `generos`
--

LOCK TABLES `generos` WRITE;
/*!40000 ALTER TABLE `generos` DISABLE KEYS */;
INSERT INTO `generos` VALUES (3,'Acción'),(2,'Aventura'),(8,'Biografía'),(4,'Ciencia Ficción'),(5,'Comedia'),(1,'Drama'),(7,'Horror'),(6,'Thriller');
/*!40000 ALTER TABLE `generos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordenes`
--

DROP TABLE IF EXISTS `ordenes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordenes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `monto` double DEFAULT NULL,
  `cupon_id` bigint DEFAULT NULL,
  `estado_id` bigint DEFAULT NULL,
  `socio_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7246bw3aw2fryg5o7kxoyroaq` (`cupon_id`),
  KEY `FKdyyaumyqludsjdk68ishtpohd` (`estado_id`),
  KEY `FK8ilc3n5v0vmqp9ln0m46cikep` (`socio_id`),
  CONSTRAINT `FK7246bw3aw2fryg5o7kxoyroaq` FOREIGN KEY (`cupon_id`) REFERENCES `cupon` (`id`),
  CONSTRAINT `FK8ilc3n5v0vmqp9ln0m46cikep` FOREIGN KEY (`socio_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKdyyaumyqludsjdk68ishtpohd` FOREIGN KEY (`estado_id`) REFERENCES `estado_orden` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordenes`
--

LOCK TABLES `ordenes` WRITE;
/*!40000 ALTER TABLE `ordenes` DISABLE KEYS */;
INSERT INTO `ordenes` VALUES (45,'2024-09-02 23:00:42.651489',2000,11,1,602),(46,'2024-09-02 23:05:26.881312',4000,NULL,2,602),(47,'2024-09-03 14:25:52.657596',4000,NULL,1,602),(48,'2024-09-03 14:26:10.238698',4000,NULL,1,602),(49,'2024-09-03 14:28:34.480484',350,NULL,1,602),(50,'2024-09-03 14:41:22.069862',2100,13,1,602),(51,'2024-09-03 15:20:04.913101',1750,13,1,602),(52,'2024-09-03 15:39:31.155419',2750,11,1,602);
/*!40000 ALTER TABLE `ordenes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_peliculas`
--

DROP TABLE IF EXISTS `order_peliculas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_peliculas` (
  `order_id` bigint NOT NULL,
  `pelicula_id` bigint NOT NULL,
  PRIMARY KEY (`order_id`,`pelicula_id`),
  KEY `FKqjot0um7mmxnib0rh8r3couuo` (`pelicula_id`),
  CONSTRAINT `FK9tlkkoa0c14xojwayky6510bv` FOREIGN KEY (`order_id`) REFERENCES `ordenes` (`id`),
  CONSTRAINT `FKqjot0um7mmxnib0rh8r3couuo` FOREIGN KEY (`pelicula_id`) REFERENCES `peliculas` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_peliculas`
--

LOCK TABLES `order_peliculas` WRITE;
/*!40000 ALTER TABLE `order_peliculas` DISABLE KEYS */;
INSERT INTO `order_peliculas` VALUES (49,6),(45,9),(46,9),(47,9),(48,9),(52,9),(51,10),(50,18),(52,22);
/*!40000 ALTER TABLE `order_peliculas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `peliculas`
--

DROP TABLE IF EXISTS `peliculas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `peliculas` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `imagen_url` varchar(1000) DEFAULT NULL,
  `titulo` varchar(255) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `peliculas`
--

LOCK TABLES `peliculas` WRITE;
/*!40000 ALTER TABLE `peliculas` DISABLE KEYS */;
INSERT INTO `peliculas` VALUES (6,'https://a.ltrbxd.com/resized/sm/upload/fe/jp/49/l9/eDiexVN4nO3ZdDZCDMiJOX5fQ5r-0-1000-0-1500-crop.jpg?v=ef7fa718a0','2001: A Space Odyssey','https://letterboxd.com/film/2001-a-space-odyssey/',720),(9,'https://a.ltrbxd.com/resized/sm/upload/7s/m2/bw/d0/caoYMcjUamGoBVy65i1AHJBvdzw-0-1000-0-1500-crop.jpg?v=88d9d6cce4','The Shining','https://letterboxd.com/film/the-shining/',4000),(10,'https://a.ltrbxd.com/resized/sm/upload/0f/r6/bn/w7/x2JQCgLjieWOwvImeGtLmuNgsrq-0-1000-0-1500-crop.jpg?v=9c250ee969','Barry Lyndon','https://letterboxd.com/film/barry-lyndon/',2500),(12,'https://a.ltrbxd.com/resized/film-poster/5/1/5/7/8/51578-psycho-0-1000-0-1500-crop.jpg?v=7353fb3952','Psycho','https://letterboxd.com/film/psycho/',2000),(15,'https://a.ltrbxd.com/resized/sm/upload/bn/hr/ki/ay/eP2pJudgGzUlo2kZuh0tF1MInsb-0-1000-0-1500-crop.jpg?v=907dc282ae','Amadeus','https://letterboxd.com/film/amadeus/',1500),(16,'https://a.ltrbxd.com/resized/film-poster/3/6/1/9/2/36192-come-and-see-0-1000-0-1500-crop.jpg?v=741b0269bb','Come and See','https://letterboxd.com/film/come-and-see/',1500),(17,'https://a.ltrbxd.com/resized/film-poster/5/1/4/4/4/51444-pulp-fiction-0-1000-0-1500-crop.jpg?v=dee19a8077','Pulp Fiction','https://letterboxd.com/film/pulp-fiction/',1300),(18,'https://a.ltrbxd.com/resized/film-poster/4/5/4/0/9/45409-shutter-island-0-1000-0-1500-crop.jpg?v=85dd4c38e3','Shutter Island','https://letterboxd.com/film/shutter-island/',3000),(19,'https://a.ltrbxd.com/resized/film-poster/5/2/5/1/6/52516-django-unchained-0-1000-0-1500-crop.jpg?v=f02aed63a3','Django Unchained','https://letterboxd.com/film/django-unchained/',2000),(21,'https://a.ltrbxd.com/resized/sm/upload/au/nb/i6/lq/fsoTLnUXEUTNuVCBxAJMY0HPPd-0-1000-0-1500-crop.jpg?v=16789732be','Her','https://letterboxd.com/film/her/',3000),(22,'https://a.ltrbxd.com/resized/film-poster/8/6/1/1/4/86114-the-wolf-of-wall-street-0-1000-0-1500-crop.jpg?v=9fd1891260','The Wolf of Wall Street','https://letterboxd.com/film/the-wolf-of-wall-street/',1500),(23,'https://a.ltrbxd.com/resized/film-poster/2/0/7/2/2/4/207224-the-revenant-0-1000-0-1500-crop.jpg?v=f51b30a589','The Revenant','https://letterboxd.com/film/the-revenant-2015/',2270),(24,'https://a.ltrbxd.com/resized/sm/upload/lv/4b/f2/zj/muym4jTjdLx7E6as09d1wlC3sOB-0-1000-0-1500-crop.jpg?v=b4d5a4aa37','Requiem For a Dream','https://letterboxd.com/film/requiem-for-a-dream/',3200);
/*!40000 ALTER TABLE `peliculas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `peliculas_genero`
--

DROP TABLE IF EXISTS `peliculas_genero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `peliculas_genero` (
  `pelicula_id` bigint NOT NULL,
  `genero_id` int NOT NULL,
  KEY `FK1nfmn2y8mpkmq9jhb7oafwrnk` (`genero_id`),
  KEY `FKnpi8epm5v1ll3jsg91nc2kn9f` (`pelicula_id`),
  CONSTRAINT `FK1nfmn2y8mpkmq9jhb7oafwrnk` FOREIGN KEY (`genero_id`) REFERENCES `generos` (`id`),
  CONSTRAINT `FKnpi8epm5v1ll3jsg91nc2kn9f` FOREIGN KEY (`pelicula_id`) REFERENCES `peliculas` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `peliculas_genero`
--

LOCK TABLES `peliculas_genero` WRITE;
/*!40000 ALTER TABLE `peliculas_genero` DISABLE KEYS */;
INSERT INTO `peliculas_genero` VALUES (6,4),(6,1),(9,4),(9,7),(10,1),(12,4),(12,7),(15,1),(15,8),(16,6),(16,3),(17,1),(17,3),(19,5),(19,1),(18,6),(18,4),(21,1),(21,4),(24,7),(24,1),(22,1),(22,5),(23,1),(23,3);
/*!40000 ALTER TABLE `peliculas_genero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKofx66keruapi6vyqpv6f2or37` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (2,'ROLE_ADMIN'),(1,'ROLE_SOCIO');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
  CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (602,1),(653,1),(702,1),(703,1),(654,2),(802,2);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `activo` bit(1) DEFAULT NULL,
  `fecha_alta` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (602,'onai','$2a$10$o/Ub590NRrP65OLqlDs8fuoaRm48aLe/8Fz0sM0Mi7H1pa.XxZTW2','campos','emi',_binary '','2024-08-05 18:28:06.179254'),(653,'zozo','$2a$10$v/MUAqeBp0qq1rUDSnUPQupKvFJAa/C4rfuqEmt1wQXOQ9SiQjDZq','s','e',_binary '','2024-08-05 18:36:52.417025'),(654,'onai1','$2a$10$G7urH1MFX5BAzk4G9N93TujgU5Sc8Z/stAJ8nUHpWi.vPeBxvXf7C','ee','ee',_binary '','2024-08-05 18:37:36.438020'),(702,'lanita','$2a$10$LyQEBMmsfKjp6akk6m/hYucn.w53rRweQvIKRn4eyKCn2QQZFqq0W','Grant','Elizabeth',_binary '','2024-09-03 11:00:42.784552'),(703,'lanaza','$2a$10$vqZ0ceqcGlnajQnPja/cEegRamSK.WFwb4GJBPnoYZaNQBKiYkvVO','granto','Elizabeta',_binary '','2024-09-03 11:01:48.485632'),(802,'joji','$2a$10$Xl8BWBgfvIKekrKTWNnPZe6.t1MluNJpOGfSBmO1BgILF1WOzjo3C','Kusunoki','George',_binary '','2024-09-03 15:28:57.046485');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_seq`
--

DROP TABLE IF EXISTS `users_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_seq`
--

LOCK TABLES `users_seq` WRITE;
/*!40000 ALTER TABLE `users_seq` DISABLE KEYS */;
INSERT INTO `users_seq` VALUES (901);
/*!40000 ALTER TABLE `users_seq` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-03 15:42:57
