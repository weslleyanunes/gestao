-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: projetogestao
-- ------------------------------------------------------
-- Server version	8.0.43

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `projetos`
--

DROP TABLE IF EXISTS `projetos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projetos` (
  `idprojeto` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(150) COLLATE utf8mb4_general_ci NOT NULL,
  `descricao` text COLLATE utf8mb4_general_ci,
  `datainicio` date NOT NULL,
  `dataterminoprevisto` date DEFAULT NULL,
  `dataterminoreal` date DEFAULT NULL,
  `status` enum('Planejado','Em_andamento','Concluido','Cancelado') COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'Planejado',
  `idgerente` int NOT NULL,
  PRIMARY KEY (`idprojeto`),
  KEY `idgerente` (`idgerente`),
  CONSTRAINT `projetos_ibfk_1` FOREIGN KEY (`idgerente`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projetos`
--

LOCK TABLES `projetos` WRITE;
/*!40000 ALTER TABLE `projetos` DISABLE KEYS */;
INSERT INTO `projetos` VALUES (1,'Sistema de Vendas','Sistema para gerenciar vendas online','2025-09-01','2025-12-15',NULL,'Em_andamento',2),(2,'App Entregas','Aplicativo para entregas próprias de uma Confeitaria','2025-08-10','2026-01-30',NULL,'Planejado',2),(3,'Site Editorial','Analise de dados do site de uma editora','2025-09-10','2025-11-30',NULL,'Planejado',2),(4,'Site Institucional','Novo site de uma empresa','2025-07-01','2025-08-20',NULL,'Concluido',3),(5,'App Academia','Aplicação de saúde e bem-estar com acompanhamento de métricas para rede de academias','2025-08-25','2026-01-10',NULL,'Em_andamento',3);
/*!40000 ALTER TABLE `projetos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-09-09 11:53:53
