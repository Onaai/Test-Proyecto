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
-- Table structure for table `comentario`
--

DROP TABLE IF EXISTS `comentario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comentario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fecha_comentario` datetime(6) DEFAULT NULL,
  `texto` varchar(500) NOT NULL,
  `libro_id` bigint NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdqwq6t891ll3co9uixpadetbg` (`libro_id`),
  KEY `FKsn53lyh11hoc4hlwohd0jh6e8` (`user_id`),
  CONSTRAINT `FKdqwq6t891ll3co9uixpadetbg` FOREIGN KEY (`libro_id`) REFERENCES `peliculas` (`id`),
  CONSTRAINT `FKsn53lyh11hoc4hlwohd0jh6e8` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comentario`
--

LOCK TABLES `comentario` WRITE;
/*!40000 ALTER TABLE `comentario` DISABLE KEYS */;
INSERT INTO `comentario` VALUES (1,'2024-10-10 18:13:31.306216','a',26,602),(2,'2024-10-10 18:13:43.407103','el pepe',26,602),(3,'2024-10-10 18:17:09.048097','boca la concha de tu madre\r\n',26,602),(4,'2024-10-10 18:18:36.125090','RIVER ESTAS EN LA B PUTOOO\r\n',26,852),(5,'2024-10-11 22:54:04.239361','11/10 alexby maricon',26,602),(6,'2024-10-12 11:03:39.358838','Bueno, a mi me gustó mucho, muy lindo libro  (No leí)',25,602);
/*!40000 ALTER TABLE `comentario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comentarios`
--

DROP TABLE IF EXISTS `comentarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comentarios` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `comentario` text NOT NULL,
  `fecha` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` int NOT NULL,
  `pelicula_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `pelicula_id` (`pelicula_id`),
  CONSTRAINT `comentarios_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  CONSTRAINT `comentarios_ibfk_2` FOREIGN KEY (`pelicula_id`) REFERENCES `peliculas` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comentarios`
--

LOCK TABLES `comentarios` WRITE;
/*!40000 ALTER TABLE `comentarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `comentarios` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordenes`
--

LOCK TABLES `ordenes` WRITE;
/*!40000 ALTER TABLE `ordenes` DISABLE KEYS */;
INSERT INTO `ordenes` VALUES (45,'2024-09-02 23:00:42.651489',2000,11,1,602),(46,'2024-09-02 23:05:26.881312',4000,NULL,2,602),(47,'2024-09-03 14:25:52.657596',4000,NULL,1,602),(48,'2024-09-03 14:26:10.238698',4000,NULL,1,602),(49,'2024-09-03 14:28:34.480484',350,NULL,1,602),(50,'2024-09-03 14:41:22.069862',2100,13,1,602),(51,'2024-09-03 15:20:04.913101',1750,13,1,602),(52,'2024-09-03 15:39:31.155419',2750,11,1,602),(53,'2024-10-06 14:58:57.543133',720,NULL,1,602),(54,'2024-10-06 15:04:50.776117',720,NULL,1,602),(55,'2024-10-06 15:05:31.062841',4000,NULL,1,602);
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
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `peliculas`
--

LOCK TABLES `peliculas` WRITE;
/*!40000 ALTER TABLE `peliculas` DISABLE KEYS */;
INSERT INTO `peliculas` VALUES (25,'https://www.loqueleo.com/mx/uploads/2018/07/resized/800_portada-quijote-web.jpg','Don Quijote de la Mancha','https://libros.com/don-quijote',1200),(26,'https://s3.amazonaws.com/adg-bucket/1984-george-orwell/3423-medium.jpg','1984','https://libros.com/1984',1500),(27,'https://mir-s3-cdn-cf.behance.net/project_modules/hd/cc8a8d34051824.56c7461a62ad2.jpg','Moby Dick','https://libros.com/moby-dick',1300),(28,'https://www.penguinlibros.com/ar/1598663/crimen-y-castigo.jpg','Crimen y Castigo','https://libros.com/crimen-y-castigo',1400),(29,'https://www.penguinlibros.com/ar/1602771-large_default/el-extranjero.webp','El extranjero','https://libros.com/orgullo-y-prejuicio',1100),(30,'https://images.cdn3.buscalibre.com/fit-in/360x360/be/f5/bef58803f001add87e2f0c33ca9fa87f.jpg','El Principito','https://libros.com/el-principito',1000),(31,'https://www.penguinlibros.com/ar/1598456/iliada-los-mejores-clasicos.jpg','La Ilíada','https://libros.com/la-iliada',2000),(32,'https://acdn.mitiendanube.com/stores/001/029/689/products/fahrenheit-4511-1419fa9f0258bc1abe16873684185644-640-0.jpg','Fahrenheit 451','https://libros.com/fahrenheit-451',1600),(33,'https://cdn.prod.website-files.com/6034d7d1f3e0f52c50b2adee/625453f925449ca10afedf7c_6034d7d1f3e0f5292db2b291_La-metamorfosis-y-otros-relatos-franz-kafka-editorial-alma.jpeg','La Metamorfosis','https://libros.com/la-metamorfosis',900),(34,'https://cdn.prod.website-files.com/6034d7d1f3e0f52c50b2adee/625452e64ed610baadce9101_6034d7d1f3e0f561eab2b16e_Anna-karenina-lev-tolstoi-editorial-alma.jpeg','Anna Karenina','https://libros.com/la-metamorfosis',1000);
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
INSERT INTO `peliculas_genero` VALUES (25,2),(25,5),(26,4),(27,2),(28,1),(29,1),(30,5),(31,2),(32,4),(33,6);
/*!40000 ALTER TABLE `peliculas_genero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `puntuacion`
--

DROP TABLE IF EXISTS `puntuacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `puntuacion` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `libro_id` bigint DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `puntuacion` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `libro_id` (`libro_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `puntuacion_ibfk_1` FOREIGN KEY (`libro_id`) REFERENCES `peliculas` (`id`),
  CONSTRAINT `puntuacion_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `puntuacion`
--

LOCK TABLES `puntuacion` WRITE;
/*!40000 ALTER TABLE `puntuacion` DISABLE KEYS */;
INSERT INTO `puntuacion` VALUES (4,26,602,4),(6,27,602,5),(7,25,602,4);
/*!40000 ALTER TABLE `puntuacion` ENABLE KEYS */;
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
INSERT INTO `user_roles` VALUES (602,1),(653,1),(702,1),(703,1),(852,1),(654,2),(802,2);
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
INSERT INTO `users` VALUES (602,'onai','$2a$10$o/Ub590NRrP65OLqlDs8fuoaRm48aLe/8Fz0sM0Mi7H1pa.XxZTW2','campos','emi',_binary '','2024-08-05 18:28:06.179254'),(653,'zozo','$2a$10$v/MUAqeBp0qq1rUDSnUPQupKvFJAa/C4rfuqEmt1wQXOQ9SiQjDZq','s','e',_binary '','2024-08-05 18:36:52.417025'),(654,'onai1','$2a$10$G7urH1MFX5BAzk4G9N93TujgU5Sc8Z/stAJ8nUHpWi.vPeBxvXf7C','ee','ee',_binary '','2024-08-05 18:37:36.438020'),(702,'lanita','$2a$10$LyQEBMmsfKjp6akk6m/hYucn.w53rRweQvIKRn4eyKCn2QQZFqq0W','Grant','Elizabeth',_binary '','2024-09-03 11:00:42.784552'),(703,'lanaza','$2a$10$vqZ0ceqcGlnajQnPja/cEegRamSK.WFwb4GJBPnoYZaNQBKiYkvVO','granto','Elizabeta',_binary '','2024-09-03 11:01:48.485632'),(802,'joji','$2a$10$Xl8BWBgfvIKekrKTWNnPZe6.t1MluNJpOGfSBmO1BgILF1WOzjo3C','Kusunoki','George',_binary '','2024-09-03 15:28:57.046485'),(852,'bocateamo123','$2a$10$WgaiYjlf3RiNVlNxDgSbqeNUazbiIRlj4fYSZe8oz5AHDZTtZ3kla','santillan','julio',_binary '','2024-10-10 18:18:22.291107');
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
INSERT INTO `users_seq` VALUES (951);
/*!40000 ALTER TABLE `users_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `watchlist`
--

DROP TABLE IF EXISTS `watchlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `watchlist` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `libro_id` bigint NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK48y31bqc2lxx56ig2b02eseaa` (`libro_id`),
  KEY `FKden7i83qo2swnr5br4wgc5r7j` (`user_id`),
  CONSTRAINT `FK48y31bqc2lxx56ig2b02eseaa` FOREIGN KEY (`libro_id`) REFERENCES `peliculas` (`id`),
  CONSTRAINT `FKden7i83qo2swnr5br4wgc5r7j` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `watchlist`
--

LOCK TABLES `watchlist` WRITE;
/*!40000 ALTER TABLE `watchlist` DISABLE KEYS */;
/*!40000 ALTER TABLE `watchlist` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-12 11:32:56
