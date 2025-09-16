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
-- Table structure for table `tarefas`
--

DROP TABLE IF EXISTS `tarefas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tarefas` (
  `idtarefa` int NOT NULL AUTO_INCREMENT,
  `idprojeto` int NOT NULL,
  `titulo` varchar(150) COLLATE utf8mb4_general_ci NOT NULL,
  `descricao` text COLLATE utf8mb4_general_ci,
  `idresponsavel` int DEFAULT NULL,
  `status` enum('Pendente','Em_execucao','Concluida') COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'Pendente',
  `datainicioprevista` date NOT NULL,
  `dataterminoprevista` date DEFAULT NULL,
  `datainicioreal` date DEFAULT NULL,
  `dataterminoreal` date DEFAULT NULL,
  PRIMARY KEY (`idtarefa`),
  KEY `idprojeto` (`idprojeto`),
  KEY `idresponsavel` (`idresponsavel`),
  CONSTRAINT `tarefas_ibfk_1` FOREIGN KEY (`idprojeto`) REFERENCES `projetos` (`idprojeto`) ON DELETE CASCADE,
  CONSTRAINT `tarefas_ibfk_2` FOREIGN KEY (`idresponsavel`) REFERENCES `usuarios` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarefas`
--

LOCK TABLES `tarefas` WRITE;
/*!40000 ALTER TABLE `tarefas` DISABLE KEYS */;
INSERT INTO `tarefas` VALUES (1,1,'Cadastro de Clientes','Implementar tela de cadastro de clientes no sistema',4,'Concluida','2025-09-02','2025-09-10','2025-09-02','2025-09-09'),(2,1,'Integração de Pagamentos','Conectar sistema com gateway de pagamentos',6,'Em_execucao','2025-09-11','2025-09-20','2025-09-11',NULL),(3,1,'Teste de Vendas','Testar funcionalidades de vendas e relatórios',3,'Pendente','2025-09-21','2025-09-30',NULL,NULL),(4,2,'Tela de Entregas','Criar interface de entrega de pedidos',7,'Em_execucao','2025-08-15','2025-09-05','2025-08-16',NULL),(5,2,'Integração com Banco','Implementar pagamentos via banco parceiro',6,'Pendente','2025-09-06','2025-09-15',NULL,NULL),(6,3,'Análise de Dados do Site','Gerar relatórios de tráfego e usuários',5,'Concluida','2025-09-12','2025-09-25','2025-09-12','2025-09-24'),(7,3,'Otimizacao SEO','Melhorar posicionamento em buscadores',5,'Pendente','2025-09-26','2025-10-05',NULL,NULL),(8,4,'Design do Site','Criar layout e protótipo do site institucional',4,'Concluida','2025-07-02','2025-07-20','2025-07-02','2025-07-19'),(9,4,'Programação Frontend','Implementar interface do site',7,'Em_execucao','2025-07-21','2025-08-10','2025-07-21',NULL),(10,5,'Implementar Dashboard','Desenvolver dashboard de métricas para usuários da academia',6,'Pendente','2025-08-26','2025-09-15',NULL,NULL),(11,5,'Integração com API de Saúde','Conectar app com dispositivos de monitoramento de métricas',4,'Pendente','2025-09-16','2025-09-30',NULL,NULL),(12,5,'Análise de Dados','Gerar relatórios de tráfego e usuários',5,'Pendente','2025-09-12','2025-09-26','2025-09-15',NULL);
/*!40000 ALTER TABLE `tarefas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-09-09 11:53:51
